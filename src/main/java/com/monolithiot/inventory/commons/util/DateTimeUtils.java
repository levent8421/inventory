package com.monolithiot.inventory.commons.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * Create By Levent8421
 * Create Time: 2020/1/8 15:23
 * Class Name: DateTimeUtils
 * Author: Levent8421
 * Description:
 * Date Time Utils
 *
 * @author Levent*421
 */
public class DateTimeUtils {
    /**
     * Obtain Current Time
     *
     * @return DateTime Object
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 格式化日期
     *
     * @param date   日期
     * @param format 格式
     * @return 格式结果
     */
    public static String format(Date date, String format) {
        FastDateFormat dateFormat = FastDateFormat.getInstance(format);
        return dateFormat.format(date);
    }
}
