package com.blankj.utilcode.util;

import android.support.annotation.NonNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.TimeConstants;

/* renamed from: com.blankj.utilcode.util.bb */
/* loaded from: classes.dex */
public final class TimeUtils {

    /* renamed from: a */
    private static final ThreadLocal<SimpleDateFormat> f6808a = new ThreadLocal<>();

    /* renamed from: b */
    private static final String[] f6809b = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    /* renamed from: c */
    private static final int[] f6810c = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};

    /* renamed from: d */
    private static final String[] f6811d = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    /* renamed from: g */
    private static long m23054g(long j, int i) {
        return j * i;
    }

    /* renamed from: d */
    private static SimpleDateFormat m23074d() {
        SimpleDateFormat simpleDateFormat = f6808a.get();
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        f6808a.set(simpleDateFormat2);
        return simpleDateFormat2;
    }

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m23125a(long j) {
        return m23122a(j, m23074d());
    }

    /* renamed from: a */
    public static String m23122a(long j, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(new Date(j));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static long m23119a(String str) {
        return m23114a(str, m23074d());
    }

    /* renamed from: a */
    public static long m23114a(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            try {
                return dateFormat.parse(str).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return -1L;
            }
        } else {
            throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static Date m23099b(String str) {
        return m23094b(str, m23074d());
    }

    /* renamed from: b */
    public static Date m23094b(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            try {
                return dateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static String m23110a(Date date) {
        return m23107a(date, m23074d());
    }

    /* renamed from: a */
    public static String m23107a(Date date, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return dateFormat.format(date);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static long m23091b(Date date) {
        return date.getTime();
    }

    /* renamed from: b */
    public static Date m23102b(long j) {
        return new Date(j);
    }

    /* renamed from: a */
    public static long m23116a(String str, String str2, int i) {
        return m23115a(str, str2, m23074d(), i);
    }

    /* renamed from: a */
    public static long m23115a(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return m23049h(m23114a(str, dateFormat) - m23114a(str2, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static long m23105a(Date date, Date date2, int i) {
        return m23049h(m23091b(date) - m23091b(date2), i);
    }

    /* renamed from: a */
    public static long m23123a(long j, long j2, int i) {
        return m23049h(j - j2, i);
    }

    /* renamed from: b */
    public static String m23096b(String str, String str2, int i) {
        return m23044i(m23114a(str, m23074d()) - m23114a(str2, m23074d()), i);
    }

    /* renamed from: b */
    public static String m23095b(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return m23044i(m23114a(str, dateFormat) - m23114a(str2, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m23088b(Date date, Date date2, int i) {
        return m23044i(m23091b(date) - m23091b(date2), i);
    }

    /* renamed from: b */
    public static String m23100b(long j, long j2, int i) {
        return m23044i(j - j2, i);
    }

    /* renamed from: a */
    public static long m23128a() {
        return System.currentTimeMillis();
    }

    /* renamed from: b */
    public static String m23104b() {
        return m23122a(System.currentTimeMillis(), m23074d());
    }

    /* renamed from: a */
    public static String m23111a(@NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23122a(System.currentTimeMillis(), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static Date m23087c() {
        return new Date();
    }

    /* renamed from: a */
    public static long m23118a(String str, int i) {
        return m23115a(str, m23104b(), m23074d(), i);
    }

    /* renamed from: a */
    public static long m23113a(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return m23115a(str, m23111a(dateFormat), dateFormat, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static long m23109a(Date date, int i) {
        return m23105a(date, new Date(), i);
    }

    /* renamed from: a */
    public static long m23124a(long j, int i) {
        return m23123a(j, System.currentTimeMillis(), i);
    }

    /* renamed from: b */
    public static String m23098b(String str, int i) {
        return m23095b(str, m23104b(), m23074d(), i);
    }

    /* renamed from: b */
    public static String m23093b(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return m23095b(str, m23111a(dateFormat), dateFormat, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m23090b(Date date, int i) {
        return m23088b(date, m23087c(), i);
    }

    /* renamed from: b */
    public static String m23101b(long j, int i) {
        return m23100b(j, System.currentTimeMillis(), i);
    }

    /* renamed from: c */
    public static String m23083c(String str) {
        return m23080c(str, m23074d());
    }

    /* renamed from: c */
    public static String m23080c(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23086c(m23114a(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static String m23077c(Date date) {
        return m23086c(date.getTime());
    }

    /* renamed from: c */
    public static String m23086c(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis < 0) {
            return String.format("%tc", Long.valueOf(j));
        }
        if (currentTimeMillis < 1000) {
            return "刚刚";
        }
        if (currentTimeMillis < WaitFor.ONE_MINUTE) {
            return String.format(Locale.getDefault(), "%d秒前", Long.valueOf(currentTimeMillis / 1000));
        }
        if (currentTimeMillis < WaitFor.ONE_HOUR) {
            return String.format(Locale.getDefault(), "%d分钟前", Long.valueOf(currentTimeMillis / WaitFor.ONE_MINUTE));
        }
        long e = m23067e();
        return j >= e ? String.format("今天%tR", Long.valueOf(j)) : j >= e - WaitFor.ONE_DAY ? String.format("昨天%tR", Long.valueOf(j)) : String.format("%tF", Long.valueOf(j));
    }

    /* renamed from: e */
    private static long m23067e() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    /* renamed from: c */
    public static long m23084c(long j, long j2, int i) {
        return j + m23054g(j2, i);
    }

    /* renamed from: a */
    public static long m23117a(String str, long j, int i) {
        return m23112a(str, m23074d(), j, i);
    }

    /* renamed from: a */
    public static long m23112a(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return m23114a(str, dateFormat) + m23054g(j, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static long m23108a(Date date, long j, int i) {
        return m23091b(date) + m23054g(j, i);
    }

    /* renamed from: d */
    public static String m23071d(long j, long j2, int i) {
        return m23120a(j, m23074d(), j2, i);
    }

    /* renamed from: a */
    public static String m23120a(long j, @NonNull DateFormat dateFormat, long j2, int i) {
        if (dateFormat != null) {
            return m23122a(j + m23054g(j2, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m23097b(String str, long j, int i) {
        return m23092b(str, m23074d(), j, i);
    }

    /* renamed from: b */
    public static String m23092b(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return m23122a(m23114a(str, dateFormat) + m23054g(j, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m23089b(Date date, long j, int i) {
        return m23106a(date, m23074d(), j, i);
    }

    /* renamed from: a */
    public static String m23106a(Date date, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return m23122a(m23091b(date) + m23054g(j, i), dateFormat);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static Date m23064e(long j, long j2, int i) {
        return m23102b(j + m23054g(j2, i));
    }

    /* renamed from: c */
    public static Date m23081c(String str, long j, int i) {
        return m23078c(str, m23074d(), j, i);
    }

    /* renamed from: c */
    public static Date m23078c(String str, @NonNull DateFormat dateFormat, long j, int i) {
        if (dateFormat != null) {
            return m23102b(m23114a(str, dateFormat) + m23054g(j, i));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static Date m23075c(Date date, long j, int i) {
        return m23102b(m23091b(date) + m23054g(j, i));
    }

    /* renamed from: c */
    public static long m23085c(long j, int i) {
        return m23084c(m23128a(), j, i);
    }

    /* renamed from: d */
    public static String m23072d(long j, int i) {
        return m23121a(j, m23074d(), i);
    }

    /* renamed from: a */
    public static String m23121a(long j, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return m23120a(m23128a(), dateFormat, j, i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static Date m23065e(long j, int i) {
        return m23064e(m23128a(), j, i);
    }

    /* renamed from: d */
    public static boolean m23070d(String str) {
        return m23073d(m23114a(str, m23074d()));
    }

    /* renamed from: d */
    public static boolean m23069d(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23073d(m23114a(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static boolean m23068d(Date date) {
        return m23073d(date.getTime());
    }

    /* renamed from: d */
    public static boolean m23073d(long j) {
        long e = m23067e();
        return j >= e && j < e + WaitFor.ONE_DAY;
    }

    /* renamed from: e */
    public static boolean m23063e(String str) {
        return m23061e(m23094b(str, m23074d()));
    }

    /* renamed from: e */
    public static boolean m23062e(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23061e(m23094b(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static boolean m23061e(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return m23127a(instance.get(1));
    }

    /* renamed from: e */
    public static boolean m23066e(long j) {
        return m23061e(m23102b(j));
    }

    /* renamed from: a */
    public static boolean m23127a(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }

    /* renamed from: f */
    public static String m23058f(String str) {
        return m23056f(m23094b(str, m23074d()));
    }

    /* renamed from: f */
    public static String m23057f(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23056f(m23094b(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public static String m23056f(Date date) {
        return new SimpleDateFormat("E", Locale.CHINA).format(date);
    }

    /* renamed from: f */
    public static String m23060f(long j) {
        return m23056f(new Date(j));
    }

    /* renamed from: g */
    public static String m23053g(String str) {
        return m23051g(m23094b(str, m23074d()));
    }

    /* renamed from: g */
    public static String m23052g(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23051g(m23094b(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public static String m23051g(Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }

    /* renamed from: g */
    public static String m23055g(long j) {
        return m23051g(new Date(j));
    }

    /* renamed from: c */
    public static int m23082c(String str, int i) {
        return m23076c(m23094b(str, m23074d()), i);
    }

    /* renamed from: c */
    public static int m23079c(String str, @NonNull DateFormat dateFormat, int i) {
        if (dateFormat != null) {
            return m23076c(m23094b(str, dateFormat), i);
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static int m23076c(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(i);
    }

    /* renamed from: f */
    public static int m23059f(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(i);
    }

    /* renamed from: h */
    public static String m23048h(String str) {
        return m23046h(m23094b(str, m23074d()));
    }

    /* renamed from: h */
    public static String m23047h(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23046h(m23094b(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public static String m23046h(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return f6809b[instance.get(1) % 12];
    }

    /* renamed from: h */
    public static String m23050h(long j) {
        return m23046h(m23102b(j));
    }

    /* renamed from: b */
    public static String m23103b(int i) {
        return f6809b[i % 12];
    }

    /* renamed from: i */
    public static String m23043i(String str) {
        return m23041i(m23094b(str, m23074d()));
    }

    /* renamed from: i */
    public static String m23042i(String str, @NonNull DateFormat dateFormat) {
        if (dateFormat != null) {
            return m23041i(m23094b(str, dateFormat));
        }
        throw new NullPointerException("Argument 'format' of type DateFormat (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: i */
    public static String m23041i(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return m23126a(instance.get(2) + 1, instance.get(5));
    }

    /* renamed from: i */
    public static String m23045i(long j) {
        return m23041i(m23102b(j));
    }

    /* renamed from: a */
    public static String m23126a(int i, int i2) {
        String[] strArr = f6811d;
        int i3 = i - 1;
        if (i2 < f6810c[i3]) {
            i3 = (i + 10) % 12;
        }
        return strArr[i3];
    }

    /* renamed from: h */
    private static long m23049h(long j, int i) {
        return j / i;
    }

    /* renamed from: i */
    private static String m23044i(long j, int i) {
        if (i <= 0) {
            return null;
        }
        int min = Math.min(i, 5);
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        int i2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i2 == 0) {
            return 0 + strArr[min - 1];
        }
        StringBuilder sb = new StringBuilder();
        if (i2 < 0) {
            sb.append("-");
            j = -j;
        }
        int[] iArr = {TimeConstants.f21692e, TimeConstants.f21691d, 60000, 1000, 1};
        for (int i3 = 0; i3 < min; i3++) {
            if (j >= iArr[i3]) {
                long j2 = j / iArr[i3];
                j -= iArr[i3] * j2;
                sb.append(j2);
                sb.append(strArr[i3]);
            }
        }
        return sb.toString();
    }
}
