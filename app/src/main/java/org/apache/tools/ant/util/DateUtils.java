package org.apache.tools.ant.util;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.tencent.smtt.sdk.TbsListener;
import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.slf4j.Marker;
import p110z1.TimeConstants;

/* loaded from: classes2.dex */
public final class DateUtils {
    public static final String ISO8601_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";
    public static final String ISO8601_TIME_PATTERN = "HH:mm:ss";
    private static final int ONE_HOUR = 60;
    private static final int ONE_MINUTE = 60;
    private static final int ONE_SECOND = 1000;
    private static final int TEN = 10;
    public static final DateFormat DATE_HEADER_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ", Locale.US);
    private static final DateFormat DATE_HEADER_FORMAT_INT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ", Locale.US);
    private static final MessageFormat MINUTE_SECONDS = new MessageFormat("{0}{1}");
    private static final double[] LIMITS = {0.0d, 1.0d, 2.0d};
    private static final String[] MINUTES_PART = {"", "1 minute ", "{0,number,###############} minutes "};
    private static final String[] SECONDS_PART = {"0 seconds", "1 second", "{1,number} seconds"};
    private static final ChoiceFormat MINUTES_FORMAT = new ChoiceFormat(LIMITS, MINUTES_PART);
    private static final ChoiceFormat SECONDS_FORMAT = new ChoiceFormat(LIMITS, SECONDS_PART);

    static {
        MINUTE_SECONDS.setFormat(0, MINUTES_FORMAT);
        MINUTE_SECONDS.setFormat(1, SECONDS_FORMAT);
    }

    private DateUtils() {
    }

    public static String format(long j, String str) {
        return format(new Date(j), str);
    }

    public static String format(Date date, String str) {
        return createDateFormat(str).format(date);
    }

    public static String formatElapsedTime(long j) {
        long j2 = j / 1000;
        return MINUTE_SECONDS.format(new Object[]{new Long(j2 / 60), new Long(j2 % 60)});
    }

    private static DateFormat createDateFormat(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormat.setLenient(true);
        return simpleDateFormat;
    }

    public static int getPhaseOfMoon(Calendar calendar) {
        int i = calendar.get(6);
        int i2 = ((calendar.get(1) - 1900) % 19) + 1;
        int i3 = ((i2 * 11) + 18) % 30;
        if ((i3 == 25 && i2 > 11) || i3 == 24) {
            i3++;
        }
        return (((((i + i3) * 6) + 11) % TbsListener.ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING) / 22) & 7;
    }

    public static String getDateForHeader() {
        String str;
        Calendar instance = Calendar.getInstance();
        int offset = instance.getTimeZone().getOffset(instance.get(0), instance.get(1), instance.get(2), instance.get(5), instance.get(7), instance.get(14));
        StringBuffer stringBuffer = new StringBuffer(offset < 0 ? "-" : Marker.ANY_NON_NULL_MARKER);
        int abs = Math.abs(offset);
        int i = abs / TimeConstants.f21691d;
        int i2 = (abs / 60000) - (i * 60);
        if (i < 10) {
            stringBuffer.append(ResultTypeConstant.f7213z);
        }
        stringBuffer.append(i);
        if (i2 < 10) {
            stringBuffer.append(ResultTypeConstant.f7213z);
        }
        stringBuffer.append(i2);
        synchronized (DATE_HEADER_FORMAT_INT) {
            str = DATE_HEADER_FORMAT_INT.format(instance.getTime()) + stringBuffer.toString();
        }
        return str;
    }

    public static Date parseDateFromHeader(String str) throws ParseException {
        Date parse;
        synchronized (DATE_HEADER_FORMAT_INT) {
            parse = DATE_HEADER_FORMAT_INT.parse(str);
        }
        return parse;
    }

    public static Date parseIso8601DateTime(String str) throws ParseException {
        return new SimpleDateFormat(ISO8601_DATETIME_PATTERN).parse(str);
    }

    public static Date parseIso8601Date(String str) throws ParseException {
        return new SimpleDateFormat(ISO8601_DATE_PATTERN).parse(str);
    }

    public static Date parseIso8601DateTimeOrDate(String str) throws ParseException {
        try {
            return parseIso8601DateTime(str);
        } catch (ParseException unused) {
            return parseIso8601Date(str);
        }
    }
}
