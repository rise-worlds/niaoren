package com.blankj.utilcode.util;

import android.os.Build;
import android.text.Html;
import android.util.Base64;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* renamed from: com.blankj.utilcode.util.t */
/* loaded from: classes.dex */
public final class EncodeUtils {
    private EncodeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m22392a(String str) {
        return m22391a(str, "UTF-8");
    }

    /* renamed from: a */
    public static String m22391a(String str, String str2) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: b */
    public static String m22389b(String str) {
        return m22388b(str, "UTF-8");
    }

    /* renamed from: b */
    public static String m22388b(String str, String str2) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: c */
    public static byte[] m22386c(String str) {
        return m22390a(str.getBytes());
    }

    /* renamed from: a */
    public static byte[] m22390a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        return Base64.encode(bArr, 2);
    }

    /* renamed from: b */
    public static String m22387b(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? "" : Base64.encodeToString(bArr, 2);
    }

    /* renamed from: d */
    public static byte[] m22384d(String str) {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        return Base64.decode(str, 2);
    }

    /* renamed from: c */
    public static byte[] m22385c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        return Base64.decode(bArr, 2);
    }

    /* renamed from: a */
    public static String m22393a(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (charAt == '\"') {
                sb.append("&quot;");
            } else if (charAt == '<') {
                sb.append("&lt;");
            } else if (charAt != '>') {
                switch (charAt) {
                    case '&':
                        sb.append("&amp;");
                        continue;
                    case '\'':
                        sb.append("&#39;");
                        continue;
                    default:
                        sb.append(charAt);
                        continue;
                }
            } else {
                sb.append("&gt;");
            }
        }
        return sb.toString();
    }

    /* renamed from: e */
    public static CharSequence m22383e(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0);
        }
        return Html.fromHtml(str);
    }

    /* renamed from: f */
    public static String m22382f(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(Integer.toBinaryString(c));
            sb.append(' ');
        }
        return sb.toString();
    }

    /* renamed from: g */
    public static String m22381g(String str) {
        String[] split = str.split(ExpandableTextView.f6958c);
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            sb.append((char) Integer.parseInt(str2.replace(ExpandableTextView.f6958c, ""), 2));
        }
        return sb.toString();
    }
}
