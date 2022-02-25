package p110z1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tools.ant.taskdefs.WaitFor;

/* renamed from: z1.gz */
/* loaded from: classes3.dex */
public final class CalendarParsedResult extends ParsedResult {

    /* renamed from: a */
    private static final Pattern f21833a = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");

    /* renamed from: b */
    private static final long[] f21834b = {WaitFor.ONE_WEEK, WaitFor.ONE_DAY, WaitFor.ONE_HOUR, WaitFor.ONE_MINUTE, 1000};

    /* renamed from: c */
    private static final Pattern f21835c = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");

    /* renamed from: d */
    private final String f21836d;

    /* renamed from: e */
    private final long f21837e;

    /* renamed from: f */
    private final boolean f21838f;

    /* renamed from: g */
    private final long f21839g;

    /* renamed from: h */
    private final boolean f21840h;

    /* renamed from: i */
    private final String f21841i;

    /* renamed from: j */
    private final String f21842j;

    /* renamed from: k */
    private final String[] f21843k;

    /* renamed from: l */
    private final String f21844l;

    /* renamed from: m */
    private final double f21845m;

    /* renamed from: n */
    private final double f21846n;

    public CalendarParsedResult(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String str7, double d, double d2) {
        super(ParsedResultType.f21886i);
        this.f21836d = str;
        try {
            this.f21837e = m2651a(str2);
            if (str3 == null) {
                long a = m2652a((CharSequence) str4);
                this.f21839g = a < 0 ? -1L : a + this.f21837e;
            } else {
                try {
                    this.f21839g = m2651a(str3);
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e.toString());
                }
            }
            boolean z = false;
            this.f21838f = str2.length() == 8;
            if (str3 != null && str3.length() == 8) {
                z = true;
            }
            this.f21840h = z;
            this.f21841i = str5;
            this.f21842j = str6;
            this.f21843k = strArr;
            this.f21844l = str7;
            this.f21845m = d;
            this.f21846n = d2;
        } catch (ParseException e2) {
            throw new IllegalArgumentException(e2.toString());
        }
    }

    /* renamed from: b */
    private String m2649b() {
        return this.f21836d;
    }

    @Deprecated
    /* renamed from: c */
    private Date m2647c() {
        return new Date(this.f21837e);
    }

    /* renamed from: d */
    private long m2646d() {
        return this.f21837e;
    }

    /* renamed from: e */
    private boolean m2645e() {
        return this.f21838f;
    }

    @Deprecated
    /* renamed from: f */
    private Date m2644f() {
        long j = this.f21839g;
        if (j < 0) {
            return null;
        }
        return new Date(j);
    }

    /* renamed from: g */
    private long m2643g() {
        return this.f21839g;
    }

    /* renamed from: h */
    private boolean m2642h() {
        return this.f21840h;
    }

    /* renamed from: i */
    private String m2641i() {
        return this.f21841i;
    }

    /* renamed from: j */
    private String m2640j() {
        return this.f21842j;
    }

    /* renamed from: k */
    private String[] m2639k() {
        return this.f21843k;
    }

    /* renamed from: l */
    private String m2638l() {
        return this.f21844l;
    }

    /* renamed from: m */
    private double m2637m() {
        return this.f21845m;
    }

    /* renamed from: n */
    private double m2636n() {
        return this.f21846n;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(100);
        m2597a(this.f21836d, sb);
        m2597a(m2650a(this.f21838f, this.f21837e), sb);
        m2597a(m2650a(this.f21840h, this.f21839g), sb);
        m2597a(this.f21841i, sb);
        m2597a(this.f21842j, sb);
        m2596a(this.f21843k, sb);
        m2597a(this.f21844l, sb);
        return sb.toString();
    }

    /* renamed from: a */
    private static long m2651a(String str) throws ParseException {
        if (!f21835c.matcher(str).matches()) {
            throw new ParseException(str, 0);
        } else if (str.length() == 8) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.parse(str).getTime();
        } else if (str.length() != 16 || str.charAt(15) != 'Z') {
            return m2648b(str);
        } else {
            long b = m2648b(str.substring(0, 15));
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            long j = b + gregorianCalendar.get(15);
            gregorianCalendar.setTime(new Date(j));
            return j + gregorianCalendar.get(16);
        }
    }

    /* renamed from: a */
    private static String m2650a(boolean z, long j) {
        DateFormat dateFormat;
        if (j < 0) {
            return null;
        }
        if (z) {
            dateFormat = DateFormat.getDateInstance(2);
        } else {
            dateFormat = DateFormat.getDateTimeInstance(2, 2);
        }
        return dateFormat.format(Long.valueOf(j));
    }

    /* renamed from: a */
    private static long m2652a(CharSequence charSequence) {
        if (charSequence == null) {
            return -1L;
        }
        Matcher matcher = f21833a.matcher(charSequence);
        if (!matcher.matches()) {
            return -1L;
        }
        long j = 0;
        int i = 0;
        while (i < f21834b.length) {
            int i2 = i + 1;
            String group = matcher.group(i2);
            if (group != null) {
                j += f21834b[i] * Integer.parseInt(group);
            }
            i = i2;
        }
        return j;
    }

    /* renamed from: b */
    private static long m2648b(String str) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH).parse(str).getTime();
    }
}
