package com.monolithiot.inventory.security;

import com.alibaba.fastjson.JSON;
import com.monolithiot.inventory.commons.context.Constants;
import com.monolithiot.inventory.commons.util.TextUtils;
import com.monolithiot.inventory.security.encrypt.AccessTokenEncoder;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Create By Levent8421
 * Create Time: 2020/1/10 15:47
 * Class Name: StatelessAuthcFilter
 * Author: Levent8421
 * Description:
 * 无状态权限过滤器
 *
 * @author Levent*421
 */
@Slf4j
public class StatelessAuthcFilter extends AccessControlFilter {
    private final AccessTokenEncoder accessTokenEncoder;

    public StatelessAuthcFilter(AccessTokenEncoder accessTokenEncoder) {
        this.accessTokenEncoder = accessTokenEncoder;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        val digest = getClientDigest(servletRequest);
        if (TextUtils.isBlank(digest)) {
            onLoginFail(servletResponse, "未检测到令牌！");
            return false;
        }
        val loginToken = asToken(digest);
        val subject = SecurityUtils.getSubject();
        val tokenJson = decodeDigestString(loginToken.getDigest());
        if (tokenJson == null) {
            onLoginFail(servletResponse, "解析Token失败！");
            return false;
        }
        try {
            subject.login(loginToken);
            log.info("Login By Client DigestString userId=[{},{}]", tokenJson.getUserId(), loginToken.getUsername());
        } catch (Exception e) {
            log.debug("Error On Login By Token [{}]!", digest, e);
            val msg = String.format("Error[%s]-%s", e.getClass().getSimpleName(), e.getMessage());
            onLoginFail(servletResponse, msg);
            return false;
        }
        return true;
    }

    private String getClientDigest(ServletRequest request) {
        String digest = null;
        if (request instanceof HttpServletRequest) {
            digest = ((HttpServletRequest) request).getHeader(Constants.Security.CLIENT_TOKEN_DIGEST_HEADER_NAME);
        }
        if (TextUtils.isBlank(digest)) {
            digest = request.getParameter(Constants.Security.CLIENT_TOKEN_DIGEST_PARAM_NAME);
        }
        return digest;
    }

    private void onLoginFail(ServletResponse response, String msg) throws IOException {
        response.setContentType(Constants.ContentType.JSON_UTF8);
        val res = GeneralResult.permissionDenied("操作被拒绝：" + msg);
        response.getWriter().write(JSON.toJSONString(res));
    }

    private StatelessToken asToken(String digest) {
        return StatelessToken.fromDigest(digest);
    }

    private TokenJson decodeDigestString(String digestString) {
        try {
            return accessTokenEncoder.decode(digestString, TokenJson.class);
        } catch (Exception e) {
            log.warn("Error On Decode ClientDigestString [{}]", digestString, e);
            return null;
        }
    }
}
