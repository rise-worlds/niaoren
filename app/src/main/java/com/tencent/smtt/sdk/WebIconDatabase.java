package com.tencent.smtt.sdk;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.webkit.WebIconDatabase;
import com.tencent.smtt.export.external.interfaces.IconListener;

@Deprecated
/* loaded from: classes2.dex */
public class WebIconDatabase {

    /* renamed from: a */
    private static WebIconDatabase f13004a;

    @Deprecated
    /* renamed from: com.tencent.smtt.sdk.WebIconDatabase$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2596a {
        /* renamed from: a */
        void m16937a(String str, Bitmap bitmap);
    }

    public void bulkRequestIconForPageUrl(ContentResolver contentResolver, String str, AbstractC2596a aVar) {
    }

    public void open(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebIconDatabase.getInstance().open(str);
        } else {
            a.m16616c().m16595b(str);
        }
    }

    public void close() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebIconDatabase.getInstance().close();
        } else {
            a.m16616c().m16564m();
        }
    }

    public void removeAllIcons() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebIconDatabase.getInstance().removeAllIcons();
        } else {
            a.m16616c().m16566l();
        }
    }

    public void requestIconForPageUrl(String str, final AbstractC2596a aVar) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebIconDatabase.getInstance().requestIconForPageUrl(str, new WebIconDatabase.IconListener() { // from class: com.tencent.smtt.sdk.WebIconDatabase.2
                @Override // android.webkit.WebIconDatabase.IconListener
                public void onReceivedIcon(String str2, Bitmap bitmap) {
                    aVar.m16937a(str2, bitmap);
                }
            });
        } else {
            a.m16616c().m16604a(str, new IconListener() { // from class: com.tencent.smtt.sdk.WebIconDatabase.1
                @Override // com.tencent.smtt.export.external.interfaces.IconListener
                public void onReceivedIcon(String str2, Bitmap bitmap) {
                    aVar.m16937a(str2, bitmap);
                }
            });
        }
    }

    public void retainIconForPageUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebIconDatabase.getInstance().retainIconForPageUrl(str);
        } else {
            a.m16616c().m16590c(str);
        }
    }

    public void releaseIconForPageUrl(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebIconDatabase.getInstance().releaseIconForPageUrl(str);
        } else {
            a.m16616c().m16586d(str);
        }
    }

    public static WebIconDatabase getInstance() {
        return m16938a();
    }

    /* renamed from: a */
    private static synchronized WebIconDatabase m16938a() {
        WebIconDatabase webIconDatabase;
        synchronized (WebIconDatabase.class) {
            if (f13004a == null) {
                f13004a = new WebIconDatabase();
            }
            webIconDatabase = f13004a;
        }
        return webIconDatabase;
    }

    private WebIconDatabase() {
    }
}
