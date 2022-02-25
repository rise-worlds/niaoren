package com.tencent.smtt.sdk;

/* loaded from: classes2.dex */
public class MimeTypeMap {

    /* renamed from: a */
    private static MimeTypeMap f12781a;

    private MimeTypeMap() {
    }

    public static String getFileExtensionFromUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.MimeTypeMap.getFileExtensionFromUrl(str);
        }
        return a.m16616c().m16574h(str);
    }

    public boolean hasMimeType(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.MimeTypeMap.getSingleton().hasMimeType(str);
        }
        return a.m16616c().m16571i(str);
    }

    public String getMimeTypeFromExtension(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        }
        return a.m16616c().m16569j(str);
    }

    public boolean hasExtension(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.MimeTypeMap.getSingleton().hasExtension(str);
        }
        return a.m16616c().m16567k(str);
    }

    public String getExtensionFromMimeType(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        }
        return a.m16616c().m16565l(str);
    }

    public static synchronized MimeTypeMap getSingleton() {
        MimeTypeMap mimeTypeMap;
        synchronized (MimeTypeMap.class) {
            if (f12781a == null) {
                f12781a = new MimeTypeMap();
            }
            mimeTypeMap = f12781a;
        }
        return mimeTypeMap;
    }
}
