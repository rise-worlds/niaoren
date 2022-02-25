package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

/* renamed from: com.blankj.utilcode.util.ay */
/* loaded from: classes.dex */
public final class StringUtils {
    /* renamed from: c */
    public static String m23225c(String str) {
        return str == null ? "" : str;
    }

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23232a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /* renamed from: a */
    public static boolean m23230a(String str) {
        return str == null || str.trim().length() == 0;
    }

    /* renamed from: b */
    public static boolean m23226b(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m23231a(CharSequence charSequence, CharSequence charSequence2) {
        int length;
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || (length = charSequence.length()) != charSequence2.length()) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m23229a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equalsIgnoreCase(str2);
    }

    /* renamed from: b */
    public static int m23227b(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        return charSequence.length();
    }

    /* renamed from: d */
    public static String m23224d(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (!Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        return String.valueOf((char) (str.charAt(0) - ' ')) + str.substring(1);
    }

    /* renamed from: e */
    public static String m23223e(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (!Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        return String.valueOf((char) (str.charAt(0) + ' ')) + str.substring(1);
    }

    /* renamed from: f */
    public static String m23222f(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length <= 1) {
            return str;
        }
        int i = length >> 1;
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < i; i2++) {
            char c = charArray[i2];
            int i3 = (length - i2) - 1;
            charArray[i2] = charArray[i3];
            charArray[i3] = c;
        }
        return new String(charArray);
    }

    /* renamed from: g */
    public static String m23221g(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if (charArray[i] == 12288) {
                charArray[i] = ' ';
            } else if (65281 > charArray[i] || charArray[i] > 65374) {
                charArray[i] = charArray[i];
            } else {
                charArray[i] = (char) (charArray[i] - 65248);
            }
        }
        return new String(charArray);
    }

    /* renamed from: h */
    public static String m23220h(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if (charArray[i] == ' ') {
                charArray[i] = 12288;
            } else if ('!' > charArray[i] || charArray[i] > '~') {
                charArray[i] = charArray[i];
            } else {
                charArray[i] = (char) (charArray[i] + 65248);
            }
        }
        return new String(charArray);
    }

    /* renamed from: a */
    public static String m23234a(@StringRes int i) {
        try {
            return Utils.m24103a().getResources().getString(i);
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m23233a(@StringRes int i, Object... objArr) {
        try {
            return Utils.m24103a().getString(i, objArr);
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    /* renamed from: b */
    public static String[] m23228b(@ArrayRes int i) {
        try {
            return Utils.m24103a().getResources().getStringArray(i);
        } catch (Resources.NotFoundException unused) {
            return new String[0];
        }
    }
}
