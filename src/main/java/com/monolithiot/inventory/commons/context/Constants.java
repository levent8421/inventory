package com.monolithiot.inventory.commons.context;

/**
 * Create By Levent8421
 * Create Time: 2020/1/8 15:24
 * Class Name: Constants
 * Author: Levent8421
 * Description:
 * 系统常量表
 *
 * @author Levent*421
 */
public class Constants {
    /**
     * 系统基础包
     */
    public static final String BASE_PACKAGE = "com.monolithiot.inventory";
    /**
     * Default Charset
     */
    public static final String CHARSET = "UTF-8";

    /**
     * MyBatis 相关常量值
     */
    public static class MyBatis {
        /**
         * ID 生成器 JDBC
         */
        public static final String GENERATOR_JDBC = "JDBC";
        /**
         * MyBatis Mapper 所在包
         */
        public static final String MAPPER_PACKAGES = BASE_PACKAGE + ".repository.mapper";
    }

    /**
     * Content Type Table
     */
    public static class ContentType {
        /**
         * JSON encode by utf-8
         */
        public static final String JSON_UTF8 = "application/json; charset=utf-8";
    }

    /**
     * 安全相关常量表
     */
    public static class Security {
        /**
         * 令牌参数名称
         */
        public static final String CLIENT_TOKEN_DIGEST_PARAM_NAME = "token";
        /**
         * 令牌请求头名称
         */
        public static final String CLIENT_TOKEN_DIGEST_HEADER_NAME = "X-Token";
        /**
         * 扩展参数：用户 参数名
         */
        public static final String USER_EXTEND_PARAM_NAME = "auth.user";
    }

    /**
     * 时间日期相关常量
     */
    public static class Datetime {
        /**
         * 日期格式
         */
        public static final String DATE_FORMAT = "yyyy-MM-dd";
        /**
         * 时间格式
         */
        public static final String TIME_FORMAT = "HH:mm:ss";
        /**
         * 时间日期格式
         */
        public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }
}
