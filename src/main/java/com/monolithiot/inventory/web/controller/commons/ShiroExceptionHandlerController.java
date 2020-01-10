package com.monolithiot.inventory.web.controller.commons;

import com.monolithiot.inventory.commons.util.ServletUtils;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 17:50
 * Class Name: ShiroExceptionHandlerController
 * Author: Levent8421
 * Description:
 * Shiro 健全异常相关处理器
 *
 * @author Levent*421
 */
public class ShiroExceptionHandlerController {
    /**
     * 处理登录异常
     *
     * @return GR
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public GeneralResult<?> authenticationException(HttpServletRequest request, Exception e) {
        val res = GeneralResult.permissionDenied(e.getMessage());
        if (ServletUtils.isAjax(request)) {
            res.setData("Ajax:登录失败！");
        }
        return res;
    }

    /**
     * 处理权限异常
     *
     * @param request 请求
     * @param e       异常
     * @return GR
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public GeneralResult<?> authorizationException(HttpServletRequest request, Exception e) {
        val res = GeneralResult.permissionDenied(e.getMessage());
        if (ServletUtils.isAjax(request)) {
            res.setData("Ajax:权限不足！");
        }
        return res;
    }
}
