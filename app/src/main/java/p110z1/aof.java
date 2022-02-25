package p110z1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tools.ant.util.DateUtils;

/* compiled from: DateUtils.java */
/* renamed from: z1.aof */
/* loaded from: classes3.dex */
public class aof {
    /* renamed from: a */
    public static String m12121a(String str, String str2, String str3) {
        Date date;
        try {
            date = new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return new SimpleDateFormat(str3).format(date);
    }

    /* renamed from: a */
    public static String m12122a(String str) {
        return m12121a(str, "yyyy-MM-dd HH:mm:ss", "MM-dd HH:mm:ss");
    }

    /* renamed from: b */
    public static String m12120b(String str) {
        return m12121a(str, DateUtils.ISO8601_DATE_PATTERN, "yy-MM-dd");
    }
}
