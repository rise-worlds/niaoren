package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.http.HttpDate;
import org.apache.http.cookie.AbstractC3144SM;
import org.apache.http.cookie.ClientCookie;
import p110z1.Consts;
import p110z1.cjm;

/* renamed from: com.b.a.r */
/* loaded from: classes.dex */
public final class Cookie {

    /* renamed from: a */
    private static final Pattern f6376a = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* renamed from: b */
    private static final Pattern f6377b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* renamed from: c */
    private static final Pattern f6378c = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* renamed from: d */
    private static final Pattern f6379d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* renamed from: e */
    private final String f6380e;

    /* renamed from: f */
    private final String f6381f;

    /* renamed from: g */
    private final long f6382g;

    /* renamed from: h */
    private final String f6383h;

    /* renamed from: i */
    private final String f6384i;

    /* renamed from: j */
    private final boolean f6385j;

    /* renamed from: k */
    private final boolean f6386k;

    /* renamed from: l */
    private final boolean f6387l;

    /* renamed from: m */
    private final boolean f6388m;

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f6380e = str;
        this.f6381f = str2;
        this.f6382g = j;
        this.f6383h = str3;
        this.f6384i = str4;
        this.f6385j = z;
        this.f6386k = z2;
        this.f6388m = z3;
        this.f6387l = z4;
    }

    /* renamed from: a */
    public final String m24384a() {
        return this.f6380e;
    }

    /* renamed from: b */
    public final String m24379b() {
        return this.f6381f;
    }

    /* renamed from: a */
    private static Cookie m24383a(long j, HttpUrl acVar, String str) {
        long j2;
        String str2;
        String str3;
        String str4;
        boolean z;
        int length = str.length();
        char c = ';';
        boolean z2 = false;
        int a = Util.m24758a(str, 0, length, ';');
        char c2 = '=';
        int a2 = Util.m24758a(str, 0, a, '=');
        String str5 = null;
        if (a2 == a) {
            return null;
        }
        String c3 = Util.m24746c(str, 0, a2);
        if (c3.isEmpty() || Util.m24749b(c3) != -1) {
            return null;
        }
        boolean z3 = true;
        String c4 = Util.m24746c(str, a2 + 1, a);
        if (Util.m24749b(c4) != -1) {
            return null;
        }
        int i = a + 1;
        String str6 = null;
        long j3 = -1;
        boolean z4 = false;
        long j4 = HttpDate.MAX_DATE;
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        while (i < length) {
            int a3 = Util.m24758a(str, i, length, c);
            int a4 = Util.m24758a(str, i, a3, c2);
            String c5 = Util.m24746c(str, i, a4);
            String c6 = a4 < a3 ? Util.m24746c(str, a4 + 1, a3) : "";
            if (c5.equalsIgnoreCase("expires")) {
                try {
                    int length2 = c6.length();
                    int i2 = z2 ? 1 : 0;
                    int i3 = z2 ? 1 : 0;
                    int i4 = z2 ? 1 : 0;
                    int a5 = m24380a(c6, i2, length2, z2);
                    Matcher matcher = f6379d.matcher(c6);
                    int i5 = -1;
                    int i6 = -1;
                    int i7 = -1;
                    int i8 = -1;
                    int i9 = -1;
                    int i10 = -1;
                    while (a5 < length2) {
                        int a6 = m24380a(c6, a5 + 1, length2, z3);
                        matcher.region(a5, a6);
                        if (i5 != -1 || !matcher.usePattern(f6379d).matches()) {
                            int i11 = -1;
                            if (i8 == -1) {
                                if (matcher.usePattern(f6378c).matches()) {
                                    i8 = Integer.parseInt(matcher.group(1));
                                } else {
                                    i11 = -1;
                                }
                            }
                            if (i6 == i11) {
                                if (matcher.usePattern(f6377b).matches()) {
                                    i6 = f6377b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
                                    i8 = i8;
                                } else {
                                    i11 = -1;
                                }
                            }
                            if (i7 != i11 || !matcher.usePattern(f6376a).matches()) {
                                i8 = i8;
                            } else {
                                i7 = Integer.parseInt(matcher.group(1));
                                i8 = i8;
                            }
                        } else {
                            int i12 = z3 ? 1 : 0;
                            int i13 = z3 ? 1 : 0;
                            int i14 = z3 ? 1 : 0;
                            int i15 = z3 ? 1 : 0;
                            i5 = Integer.parseInt(matcher.group(i12));
                            i9 = Integer.parseInt(matcher.group(2));
                            i10 = Integer.parseInt(matcher.group(3));
                        }
                        a5 = m24380a(c6, a6 + 1, length2, false);
                        z3 = true;
                    }
                    if (i7 >= 70 && i7 <= 99) {
                        i7 += 1900;
                    }
                    if (i7 >= 0 && i7 <= 69) {
                        i7 += 2000;
                    }
                    if (i7 < 1601) {
                        throw new IllegalArgumentException();
                    } else if (i6 == -1) {
                        throw new IllegalArgumentException();
                    } else if (i8 <= 0 || i8 > 31) {
                        throw new IllegalArgumentException();
                    } else if (i5 < 0 || i5 > 23) {
                        throw new IllegalArgumentException();
                    } else {
                        if (i9 >= 0 && i9 <= 59) {
                            if (i10 < 0 || i10 > 59) {
                                throw new IllegalArgumentException();
                            }
                            try {
                                GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.f5782f);
                                gregorianCalendar.setLenient(false);
                                gregorianCalendar.set(1, i7);
                                gregorianCalendar.set(2, i6 - 1);
                                gregorianCalendar.set(5, i8);
                                gregorianCalendar.set(11, i5);
                                gregorianCalendar.set(12, i9);
                                gregorianCalendar.set(13, i10);
                                gregorianCalendar.set(14, 0);
                                j4 = gregorianCalendar.getTimeInMillis();
                                z7 = true;
                            } catch (NumberFormatException | IllegalArgumentException unused) {
                            }
                        }
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException unused2) {
                }
            } else if (c5.equalsIgnoreCase(ClientCookie.MAX_AGE_ATTR)) {
                j3 = m24381a(c6);
                z7 = true;
            } else if (c5.equalsIgnoreCase(ClientCookie.DOMAIN_ATTR)) {
                if (!c6.endsWith(Consts.f23430h)) {
                    if (c6.startsWith(Consts.f23430h)) {
                        c6 = c6.substring(1);
                    }
                    String a7 = Util.m24760a(c6);
                    if (a7 != null) {
                        str5 = a7;
                        z6 = false;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } else if (c5.equalsIgnoreCase("path")) {
                str6 = c6;
            } else if (c5.equalsIgnoreCase(ClientCookie.SECURE_ATTR)) {
                z4 = true;
            } else if (c5.equalsIgnoreCase("httponly")) {
                z5 = true;
            }
            i = a3 + 1;
            c = ';';
            z2 = false;
            c2 = '=';
            z3 = true;
        }
        if (j3 == Long.MIN_VALUE) {
            j2 = Long.MIN_VALUE;
        } else if (j3 != -1) {
            long j5 = j + (j3 <= 9223372036854775L ? j3 * 1000 : cjm.f20759b);
            if (j5 >= j) {
                j2 = HttpDate.MAX_DATE;
                if (j5 <= HttpDate.MAX_DATE) {
                    j2 = j5;
                }
            } else {
                j2 = HttpDate.MAX_DATE;
            }
        } else {
            j2 = j4;
        }
        if (str5 == null) {
            str2 = acVar.f6061b;
            str3 = str6;
        } else {
            String str7 = acVar.f6061b;
            if (str7.equals(str5)) {
                z = true;
            } else {
                z = str7.endsWith(str5) && str7.charAt((str7.length() - str5.length()) - 1) == '.' && !Util.m24747c(str7);
            }
            if (!z) {
                return null;
            }
            str2 = str5;
            str3 = str6;
        }
        if (str3 == null || !str3.startsWith("/")) {
            String h = acVar.m24527h();
            int lastIndexOf = h.lastIndexOf(47);
            str4 = lastIndexOf != 0 ? h.substring(0, lastIndexOf) : "/";
        } else {
            str4 = str3;
        }
        return new Cookie(c3, c4, j2, str2, str4, z4, z5, z6, z7);
    }

    /* renamed from: a */
    private static int m24380a(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char charAt = str.charAt(i);
            if (((charAt < ' ' && charAt != '\t') || charAt >= 127 || (charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    /* renamed from: a */
    private static long m24381a(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (!str.matches("-?\\d+")) {
                throw e;
            } else if (str.startsWith("-")) {
                return Long.MIN_VALUE;
            } else {
                return cjm.f20759b;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie rVar = (Cookie) obj;
        return rVar.f6380e.equals(this.f6380e) && rVar.f6381f.equals(this.f6381f) && rVar.f6383h.equals(this.f6383h) && rVar.f6384i.equals(this.f6384i) && rVar.f6382g == this.f6382g && rVar.f6385j == this.f6385j && rVar.f6386k == this.f6386k && rVar.f6387l == this.f6387l && rVar.f6388m == this.f6388m;
    }

    public final int hashCode() {
        long j = this.f6382g;
        return ((((((((((((((((this.f6380e.hashCode() + 527) * 31) + this.f6381f.hashCode()) * 31) + this.f6383h.hashCode()) * 31) + this.f6384i.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + (!this.f6385j ? 1 : 0)) * 31) + (!this.f6386k ? 1 : 0)) * 31) + (!this.f6387l ? 1 : 0)) * 31) + (!this.f6388m ? 1 : 0);
    }

    /* renamed from: a */
    public static List<Cookie> m24382a(HttpUrl acVar, Headers aaVar) {
        List list;
        int a = aaVar.m24559a();
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (int i = 0; i < a; i++) {
            if (AbstractC3144SM.SET_COOKIE.equalsIgnoreCase(aaVar.m24558a(i))) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(2);
                }
                arrayList2.add(aaVar.m24555b(i));
            }
        }
        if (arrayList2 != null) {
            list = Collections.unmodifiableList(arrayList2);
        } else {
            list = Collections.emptyList();
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Cookie a2 = m24383a(System.currentTimeMillis(), acVar, (String) list.get(i2));
            if (a2 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(a2);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f6380e);
        sb.append('=');
        sb.append(this.f6381f);
        if (this.f6387l) {
            if (this.f6382g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(com.p018b.p019a.p020a.p023c.HttpDate.m24744a(new Date(this.f6382g)));
            }
        }
        if (!this.f6388m) {
            sb.append("; domain=");
            sb.append(this.f6383h);
        }
        sb.append("; path=");
        sb.append(this.f6384i);
        if (this.f6385j) {
            sb.append("; secure");
        }
        if (this.f6386k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }
}
