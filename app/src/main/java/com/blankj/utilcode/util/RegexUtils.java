package com.blankj.utilcode.util;

import android.arch.persistence.room.RoomMasterTable;
import android.support.p003v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p110z1.RegexConstants;
import p110z1.acf;

/* renamed from: com.blankj.utilcode.util.an */
/* loaded from: classes.dex */
public final class RegexUtils {

    /* renamed from: a */
    private static final SimpleArrayMap<String, String> f6703a = new SimpleArrayMap<>();

    private RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23488a(CharSequence charSequence) {
        return m23487a(RegexConstants.f21666a, charSequence);
    }

    /* renamed from: b */
    public static boolean m23484b(CharSequence charSequence) {
        return m23487a(RegexConstants.f21667b, charSequence);
    }

    /* renamed from: c */
    public static boolean m23481c(CharSequence charSequence) {
        return m23487a(RegexConstants.f21668c, charSequence);
    }

    /* renamed from: d */
    public static boolean m23480d(CharSequence charSequence) {
        return m23487a(RegexConstants.f21669d, charSequence);
    }

    /* renamed from: e */
    public static boolean m23479e(CharSequence charSequence) {
        return m23487a(RegexConstants.f21670e, charSequence);
    }

    /* renamed from: f */
    public static boolean m23478f(CharSequence charSequence) {
        if (m23479e(charSequence)) {
            int[] iArr = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            char[] cArr = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
            if (f6703a.isEmpty()) {
                f6703a.put("11", "北京");
                f6703a.put("12", "天津");
                f6703a.put("13", "河北");
                f6703a.put("14", "山西");
                f6703a.put("15", "内蒙古");
                f6703a.put("21", "辽宁");
                f6703a.put("22", "吉林");
                f6703a.put("23", "黑龙江");
                f6703a.put("31", "上海");
                f6703a.put(acf.f15191p, "江苏");
                f6703a.put("33", "浙江");
                f6703a.put("34", "安徽");
                f6703a.put("35", "福建");
                f6703a.put("36", "江西");
                f6703a.put("37", "山东");
                f6703a.put("41", "河南");
                f6703a.put(RoomMasterTable.DEFAULT_ID, "湖北");
                f6703a.put("43", "湖南");
                f6703a.put("44", "广东");
                f6703a.put("45", "广西");
                f6703a.put("46", "海南");
                f6703a.put("50", "重庆");
                f6703a.put("51", "四川");
                f6703a.put("52", "贵州");
                f6703a.put("53", "云南");
                f6703a.put("54", "西藏");
                f6703a.put("61", "陕西");
                f6703a.put("62", "甘肃");
                f6703a.put("63", "青海");
                f6703a.put(acf.f15192q, "宁夏");
                f6703a.put("65", "新疆");
                f6703a.put("71", "台湾");
                f6703a.put("81", "香港");
                f6703a.put("82", "澳门");
                f6703a.put("91", "国外");
            }
            if (f6703a.get(charSequence.subSequence(0, 2).toString()) != null) {
                int i = 0;
                for (int i2 = 0; i2 < 17; i2++) {
                    i += (charSequence.charAt(i2) - '0') * iArr[i2];
                }
                return charSequence.charAt(17) == cArr[i % 11];
            }
        }
        return false;
    }

    /* renamed from: g */
    public static boolean m23477g(CharSequence charSequence) {
        return m23487a(RegexConstants.f21671f, charSequence);
    }

    /* renamed from: h */
    public static boolean m23476h(CharSequence charSequence) {
        return m23487a(RegexConstants.f21672g, charSequence);
    }

    /* renamed from: i */
    public static boolean m23475i(CharSequence charSequence) {
        return m23487a(RegexConstants.f21673h, charSequence);
    }

    /* renamed from: j */
    public static boolean m23474j(CharSequence charSequence) {
        return m23487a(RegexConstants.f21674i, charSequence);
    }

    /* renamed from: k */
    public static boolean m23473k(CharSequence charSequence) {
        return m23487a(RegexConstants.f21675j, charSequence);
    }

    /* renamed from: l */
    public static boolean m23472l(CharSequence charSequence) {
        return m23487a(RegexConstants.f21676k, charSequence);
    }

    /* renamed from: a */
    public static boolean m23487a(String str, CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0 && Pattern.matches(str, charSequence);
    }

    /* renamed from: b */
    public static List<String> m23483b(String str, CharSequence charSequence) {
        if (charSequence == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(str).matcher(charSequence);
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String[] m23486a(String str, String str2) {
        if (str == null) {
            return new String[0];
        }
        return str.split(str2);
    }

    /* renamed from: a */
    public static String m23485a(String str, String str2, String str3) {
        return str == null ? "" : Pattern.compile(str2).matcher(str).replaceFirst(str3);
    }

    /* renamed from: b */
    public static String m23482b(String str, String str2, String str3) {
        return str == null ? "" : Pattern.compile(str2).matcher(str).replaceAll(str3);
    }
}
