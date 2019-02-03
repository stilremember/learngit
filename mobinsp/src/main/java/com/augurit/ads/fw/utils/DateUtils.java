//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.augurit.ads.fw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YEAR_MOUTH_DAY_ = "yyyy年MM月dd日";

    public DateUtils() {
    }

    public static Date stringToDate(String dateString) throws ParseException {
        return stringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date stringToDate(String dateString, String dateFormat) {
        if (dateString != null && dateString.trim().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());

            try {
                Date date = sdf.parse(dateString);
                return date;
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
        }

        return null;
    }

    public static String dateTimeToString(Date date) {
        return dateTimeToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateTimeToString(Date date, String pattern) {
        String DateString = "";
        if (date == null) {
            DateString = "";
        } else {
            SimpleDateFormat formatDate = new SimpleDateFormat(pattern, Locale.getDefault());
            DateString = formatDate.format(date);
        }

        return DateString;
    }

    public static boolean isSameDay(Date oneDay, Date anotherDay) {
        return oneDay != null && anotherDay != null ? dateTimeToString(oneDay, "yyyy-MM-dd").equals(dateTimeToString(anotherDay, "yyyy-MM-dd")) : false;
    }

    public static void main(String[] args) {
    }
}
