package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.alipay.android.phone.mrpc.core.k */
/* loaded from: classes.dex */
public final class C0604k {

    /* renamed from: a */
    private static final Pattern f154a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");

    /* renamed from: b */
    private static final Pattern f155b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alipay.android.phone.mrpc.core.k$a */
    /* loaded from: classes.dex */
    public static class C0605a {

        /* renamed from: a */
        int f156a;

        /* renamed from: b */
        int f157b;

        /* renamed from: c */
        int f158c;

        C0605a(int i, int i2, int i3) {
            this.f156a = i;
            this.f157b = i2;
            this.f158c = i3;
        }
    }

    /* renamed from: a */
    public static long m25495a(String str) {
        int i;
        C0605a aVar;
        int i2;
        int i3;
        int i4;
        Matcher matcher = f154a.matcher(str);
        if (matcher.find()) {
            i4 = m25494b(matcher.group(1));
            i3 = m25493c(matcher.group(2));
            i = m25492d(matcher.group(3));
            aVar = m25491e(matcher.group(4));
        } else {
            Matcher matcher2 = f155b.matcher(str);
            if (matcher2.find()) {
                i3 = m25493c(matcher2.group(1));
                i4 = m25494b(matcher2.group(2));
                aVar = m25491e(matcher2.group(3));
                i = m25492d(matcher2.group(4));
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (i >= 2038) {
            i4 = 1;
            i3 = 0;
            i2 = 2038;
        } else {
            i2 = i;
        }
        Time time = new Time("UTC");
        time.set(aVar.f158c, aVar.f157b, aVar.f156a, i4, i3, i2);
        return time.toMillis(false);
    }

    /* renamed from: b */
    private static int m25494b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0') : str.charAt(0) - '0';
    }

    /* renamed from: c */
    private static int m25493c(String str) {
        int lowerCase = ((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291;
        if (lowerCase == 22) {
            return 0;
        }
        if (lowerCase == 26) {
            return 7;
        }
        if (lowerCase == 29) {
            return 2;
        }
        if (lowerCase == 32) {
            return 3;
        }
        if (lowerCase == 40) {
            return 6;
        }
        if (lowerCase == 42) {
            return 5;
        }
        if (lowerCase == 48) {
            return 10;
        }
        switch (lowerCase) {
            case 9:
                return 11;
            case 10:
                return 1;
            default:
                switch (lowerCase) {
                    case 35:
                        return 9;
                    case 36:
                        return 4;
                    case 37:
                        return 8;
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }

    /* renamed from: d */
    private static int m25492d(String str) {
        if (str.length() == 2) {
            int charAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            return charAt >= 70 ? charAt + 1900 : charAt + 2000;
        } else if (str.length() == 3) {
            return ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0') + 1900;
        } else {
            if (str.length() == 4) {
                return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
            }
            return 1970;
        }
    }

    /* renamed from: e */
    private static C0605a m25491e(String str) {
        int i;
        int i2;
        int i3;
        int charAt = str.charAt(0) - '0';
        if (str.charAt(1) != ':') {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - '0');
        } else {
            i = 1;
        }
        int i4 = i + 1 + 1 + 1 + 1;
        return new C0605a(charAt, ((str.charAt(i2) - '0') * 10) + (str.charAt(i3) - '0'), ((str.charAt(i4) - '0') * 10) + (str.charAt(i4 + 1) - '0'));
    }
}
