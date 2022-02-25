package com.tencent.smtt.sdk;

/* loaded from: classes2.dex */
public final class URLUtil {
    public static String guessUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.guessUrl(str);
        }
        return a.m16616c().m16563m(str);
    }

    public static String composeSearchUrl(String str, String str2, String str3) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.composeSearchUrl(str, str2, str3);
        }
        return a.m16616c().m16603a(str, str2, str3);
    }

    public static byte[] decode(byte[] bArr) throws IllegalArgumentException {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.decode(bArr);
        }
        return a.m16616c().m16599a(bArr);
    }

    public static boolean isAssetUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isAssetUrl(str);
        }
        return a.m16616c().m16561n(str);
    }

    @Deprecated
    public static boolean isCookielessProxyUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isCookielessProxyUrl(str);
        }
        return a.m16616c().m16559o(str);
    }

    public static boolean isFileUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isFileUrl(str);
        }
        return a.m16616c().m16557p(str);
    }

    public static boolean isAboutUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isAboutUrl(str);
        }
        return a.m16616c().m16556q(str);
    }

    public static boolean isDataUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isDataUrl(str);
        }
        return a.m16616c().m16555r(str);
    }

    public static boolean isJavaScriptUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isJavaScriptUrl(str);
        }
        return a.m16616c().m16554s(str);
    }

    public static boolean isHttpUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isHttpUrl(str);
        }
        return a.m16616c().m16553t(str);
    }

    public static boolean isHttpsUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isHttpsUrl(str);
        }
        return a.m16616c().m16552u(str);
    }

    public static boolean isNetworkUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isNetworkUrl(str);
        }
        return a.m16616c().m16551v(str);
    }

    public static boolean isContentUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isContentUrl(str);
        }
        return a.m16616c().m16550w(str);
    }

    public static boolean isValidUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.isValidUrl(str);
        }
        return a.m16616c().m16549x(str);
    }

    public static String stripAnchor(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.stripAnchor(str);
        }
        return a.m16616c().m16548y(str);
    }

    public static final String guessFileName(String str, String str2, String str3) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.URLUtil.guessFileName(str, str2, str3);
        }
        return a.m16616c().m16593b(str, str2, str3);
    }
}
