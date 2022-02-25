package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.p020a.Util;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.http.impl.cookie.DateUtils;

/* renamed from: com.b.a.a.c.d */
/* loaded from: classes.dex */
public final class HttpDate {

    /* renamed from: a */
    private static final ThreadLocal<DateFormat> f5795a = new C0858e();

    /* renamed from: b */
    private static final String[] f5796b = {"EEE, dd MMM yyyy HH:mm:ss zzz", DateUtils.PATTERN_RFC1036, DateUtils.PATTERN_ASCTIME, "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};

    /* renamed from: c */
    private static final DateFormat[] f5797c = new DateFormat[15];

    /* renamed from: a */
    public static Date m24745a(String str) {
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = f5795a.get().parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return parse;
        }
        synchronized (f5796b) {
            int length = f5796b.length;
            for (int i = 0; i < length; i++) {
                DateFormat dateFormat = f5797c[i];
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(f5796b[i], Locale.US);
                    dateFormat.setTimeZone(Util.f5782f);
                    f5797c[i] = dateFormat;
                }
                parsePosition.setIndex(0);
                Date parse2 = dateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return parse2;
                }
            }
            return null;
        }
    }

    /* renamed from: a */
    public static String m24744a(Date date) {
        return f5795a.get().format(date);
    }
}
