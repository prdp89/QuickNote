package com.mitash.quicknote.utils;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

public final class TimeUtils {
    public static final String TAG = "TimeUtils:";

    public static long toTimestamp(String serverTime) {
        return DateTime.parse(serverTime).getMillis();
    }

    public static String toServerTime(long timeInMills) {
        return DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").print(timeInMills);
    }

    private static String toTimeFormat(long timeInMills) {
        return DateTimeFormat.forPattern("h:mm aa").print(timeInMills).replace("AM", "am").replace("PM", "pm");
    }

    public static String toDateFormat(long timeInMills) {
        return DateTimeFormat.forPattern("M-dd H:mm:ss").print(timeInMills);
    }

    public static String toYearFormat(long timeInMills) {
        return DateTimeFormat.forPattern("yyyy-M-dd H:mm:ss").print(timeInMills);
    }

    public static String toMonthFormat(long timeInMills) {
        return DateTimeFormat.forPattern("MMMM yyyy").print(timeInMills);
    }

    private static String toWeekFormat(long timeInMills) {
        return DateTimeFormat.forPattern("EEEE").print(timeInMills);
    }

    public static String toSlashFormat(long timeInMills) {
        Calendar noteTime = Calendar.getInstance();
        noteTime.setTimeInMillis(timeInMills);

        Calendar today = Calendar.getInstance();

        int difference = today.get(Calendar.DATE) - noteTime.get(Calendar.DATE);
        if (today.get(Calendar.DATE) == noteTime.get(Calendar.DATE)) {
            return toTimeFormat(timeInMills);
        } else if (difference <= 7 && difference > 1) {
            return toWeekFormat(timeInMills);
        } else if (difference == 1) {
            return "Yesterday";
        } else {
            return DateTimeFormat.forPattern("dd/MM/YY").print(timeInMills);
        }
    }

    public static Calendar getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar;
    }

    public static Calendar getThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        return calendar;
    }


}
