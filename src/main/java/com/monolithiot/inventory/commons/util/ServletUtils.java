package com.monolithiot.inventory.commons.util;

import lombok.val;

import javax.servlet.http.HttpServletRequest;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 17:46
 * Class Name: ServletUtils
 * Author: Levent8421
 * Description:
 * Servlet 相关工具类
 *
 * @author Levent*421
 */
public class ServletUtils {
    /**
     * 判断请求是否是Ajax
     *
     * @param request request
     * @return boolean
     */
    public static boolean isAjax(HttpServletRequest request) {
        val requestWith = request.getHeader("x-requested-with");
        return "XMLHttpRequest".equalsIgnoreCase(requestWith);
    }
}
