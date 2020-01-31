package com.monolithiot.inventory.security;

import com.alibaba.fastjson.JSON;
import com.monolithiot.inventory.commons.context.Constants;
import com.monolithiot.inventory.commons.util.ServletUtils;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 18:02
 * Class Name: FailJsonUserFilter
 * Author: Levent8421
 * Description:
 * User Filter Base On Shiro Filter
 *
 * @author Levent*421
 */
public class FailJsonUserFilter extends UserFilter {
    private static final String PERMISSION_DENIED_ERROR_STRING = "未授权或登录失败！";

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType(Constants.ContentType.JSON_UTF8);
        if (ServletUtils.isAjax((HttpServletRequest) request)) {
            val res = GeneralResult.permissionDenied(PERMISSION_DENIED_ERROR_STRING);
            val json = JSON.toJSONString(res);
            response.getWriter().write(json);
        } else {
            super.redirectToLogin(request, response);
        }
    }
}
