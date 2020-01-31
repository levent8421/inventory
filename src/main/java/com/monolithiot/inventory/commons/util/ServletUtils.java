package com.monolithiot.inventory.commons.util;

import lombok.val;
import lombok.var;

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
    private static final String AJAX_HEADER_NAME = "x-requested-with";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    /**
     * 判断请求是否是Ajax
     *
     * @param request request
     * @return boolean
     */
    public static boolean isAjax(HttpServletRequest request) {
        val requestWith = getRequestWithHeader(request);
        return AJAX_HEADER_VALUE.equalsIgnoreCase(requestWith);
    }

    /**
     * 获取[x-request-with]Header,(忽略大小写)
     *
     * @param request request 对象
     * @return header value 可能为空
     */
    private static String getRequestWithHeader(HttpServletRequest request) {
        var ajaxValue = request.getHeader(AJAX_HEADER_NAME);
        if (TextUtils.isBlank(ajaxValue)) {
            ajaxValue = request.getHeader(AJAX_HEADER_NAME.toUpperCase());
        }
        return ajaxValue;
    }
}
