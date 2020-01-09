package com.monolithiot.inventory.context;

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
}
