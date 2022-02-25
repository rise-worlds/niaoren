package org.apache.http.impl.cookie;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class DateUtils {
    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    private static final String[] DEFAULT_PATTERNS = {PATTERN_RFC1036, "EEE, dd MMM yyyy HH:mm:ss zzz", PATTERN_ASCTIME};
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(GMT);
        calendar.set(2000, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = calendar.getTime();
    }

    public static Date parseDate(String dateValue) throws DateParseException {
        return parseDate(dateValue, null, null);
    }

    public static Date parseDate(String dateValue, String[] dateFormats) throws DateParseException {
        return parseDate(dateValue, dateFormats, null);
    }

    public static Date parseDate(String dateValue, String[] dateFormats, Date startDate) throws DateParseException {
        if (dateValue != null) {
            if (dateFormats == null) {
                dateFormats = DEFAULT_PATTERNS;
            }
            if (startDate == null) {
                startDate = DEFAULT_TWO_DIGIT_YEAR_START;
            }
            if (dateValue.length() > 1 && dateValue.startsWith("'") && dateValue.endsWith("'")) {
                dateValue = dateValue.substring(1, dateValue.length() - 1);
            }
            for (String dateFormat : dateFormats) {
                SimpleDateFormat dateParser = DateFormatHolder.formatFor(dateFormat);
                dateParser.set2DigitYearStart(startDate);
                try {
                    return dateParser.parse(dateValue);
                } catch (ParseException e) {
                }
            }
            throw new DateParseException("Unable to parse the date " + dateValue);
        }
        throw new IllegalArgumentException("dateValue is null");
    }

    public static String formatDate(Date date) {
        return formatDate(date, "EEE, dd MMM yyyy HH:mm:ss zzz");
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        } else if (pattern != null) {
            SimpleDateFormat formatter = DateFormatHolder.formatFor(pattern);
            return formatter.format(date);
        } else {
            throw new IllegalArgumentException("pattern is null");
        }
    }

    private DateUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: assets/org.apache.http.legacy.boot */
    public static final class DateFormatHolder {
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>>() { // from class: org.apache.http.impl.cookie.DateUtils.DateFormatHolder.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public SoftReference<Map<String, SimpleDateFormat>> initialValue() {
                return new SoftReference<>(new HashMap());
            }
        };

        DateFormatHolder() {
        }

        public static SimpleDateFormat formatFor(String pattern) {
            SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
            Map<String, SimpleDateFormat> formats = ref.get();
            if (formats == null) {
                formats = new HashMap();
                THREADLOCAL_FORMATS.set(new SoftReference<>(formats));
            }
            SimpleDateFormat format = formats.get(pattern);
            if (format != null) {
                return format;
            }
            SimpleDateFormat format2 = new SimpleDateFormat(pattern, Locale.US);
            format2.setTimeZone(TimeZone.getTimeZone("GMT"));
            formats.put(pattern, format2);
            return format2;
        }
    }
}
