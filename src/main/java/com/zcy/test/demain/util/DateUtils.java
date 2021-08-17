package com.zcy.test.demain.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author liyangdan
 * @date 2020/3/31 5:39 下午
 */

@Slf4j
public class DateUtils {
    /**
     * 日期格式
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static Long fromDateToStamp(Date date) {
        return Objects.isNull(date) ? null : date.getTime();
    }

    public static Date fromStampToDate(Long stamp) {
        return Objects.isNull(stamp) ? null : new Date(stamp);
    }


    /**
     * 将Date类型转换为String 指定格式化日期为"yyyy-MM-dd hh:mm:ss"字符串
     *
     * @param date 日期
     * @param pattern 输出格式
     * @return "yyyy-MM-dd hh:mm:ss"
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return formatToString(date, pattern);
    }

    private static String formatToString(Date date, String pattern) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dtf);
    }


    public static Date pareDate(String stringText, String format) {
        if (StringUtils.isEmpty(stringText)) {
            return null;
        }

        Date result = null;
        try {
            result = org.apache.commons.lang3.time.DateUtils.parseDate(stringText, format);
        } catch (Exception e) {
            log.error("DateParseException:{},{}", stringText, e);
        }
        return result;
    }


}
