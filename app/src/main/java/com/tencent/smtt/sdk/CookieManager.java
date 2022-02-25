package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.ReflectionUtils;
import com.tencent.smtt.utils.TbsLog;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
public class CookieManager {
    public static String LOGTAG = "CookieManager";

    /* renamed from: d */
    private static CookieManager f12742d;

    /* renamed from: a */
    CopyOnWriteArrayList<C2545b> f12743a;

    /* renamed from: b */
    String f12744b;

    /* renamed from: c */
    EnumC2544a f12745c = EnumC2544a.MODE_NONE;

    /* renamed from: e */
    private boolean f12746e = false;

    /* renamed from: f */
    private boolean f12747f = false;

    /* renamed from: com.tencent.smtt.sdk.CookieManager$a */
    /* loaded from: classes2.dex */
    public enum EnumC2544a {
        MODE_NONE,
        MODE_KEYS,
        MODE_ALL
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.tencent.smtt.sdk.CookieManager$b */
    /* loaded from: classes2.dex */
    public class C2545b {

        /* renamed from: a */
        int f12752a;

        /* renamed from: b */
        String f12753b;

        /* renamed from: c */
        String f12754c;

        /* renamed from: d */
        ValueCallback<Boolean> f12755d;

        C2545b() {
        }
    }

    private CookieManager() {
    }

    public static CookieManager getInstance() {
        if (f12742d == null) {
            synchronized (CookieManager.class) {
                if (f12742d == null) {
                    f12742d = new CookieManager();
                }
            }
        }
        return f12742d;
    }

    public void removeSessionCookie() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            ReflectionUtils.m16404a(android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public void removeAllCookie() {
        CopyOnWriteArrayList<C2545b> copyOnWriteArrayList = this.f12743a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            a.m16616c().m16585e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        CopyOnWriteArrayList<C2545b> copyOnWriteArrayList = this.f12743a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            ReflectionUtils.m16404a(android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public void flush() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (Build.VERSION.SDK_INT >= 21) {
            ReflectionUtils.m16404a(android.webkit.CookieManager.getInstance(), "flush", new Class[0], new Object[0]);
        }
    }

    public void removeExpiredCookie() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.CookieManager.getInstance().setAcceptCookie(z);
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        } else if (Build.VERSION.SDK_INT >= 21) {
            ReflectionUtils.m16404a(android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{WebView.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        }
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            Object invokeStaticMethod = a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
            if (invokeStaticMethod == null) {
                return true;
            }
            return ((Boolean) invokeStaticMethod).booleanValue();
        } else if (Build.VERSION.SDK_INT < 21) {
            return true;
        } else {
            Object a2 = ReflectionUtils.m16404a(android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{WebView.class}, webView.getView());
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        }
    }

    public synchronized void setCookie(String str, String str2) {
        setCookie(str, str2, false);
    }

    public synchronized void setCookie(String str, String str2, boolean z) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            if (this.f12747f || z) {
                android.webkit.CookieManager.getInstance().setCookie(str, str2);
            }
            if (!X5CoreEngine.m16621a().m16615d()) {
                C2545b bVar = new C2545b();
                bVar.f12752a = 2;
                bVar.f12753b = str;
                bVar.f12754c = str2;
                bVar.f12755d = null;
                if (this.f12743a == null) {
                    this.f12743a = new CopyOnWriteArrayList<>();
                }
                this.f12743a.add(bVar);
            }
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
        }
    }

    public synchronized void setCookie(String str, String str2, ValueCallback<Boolean> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            if (!X5CoreEngine.m16621a().m16615d()) {
                C2545b bVar = new C2545b();
                bVar.f12752a = 1;
                bVar.f12753b = str;
                bVar.f12754c = str2;
                bVar.f12755d = valueCallback;
                if (this.f12743a == null) {
                    this.f12743a = new CopyOnWriteArrayList<>();
                }
                this.f12743a.add(bVar);
            }
            if (this.f12747f) {
                if (Build.VERSION.SDK_INT >= 21) {
                    ReflectionUtils.m16404a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
                }
            }
        } else {
            a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
        }
    }

    public boolean hasCookies() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.CookieManager.getInstance().hasCookies();
        }
        return a.m16616c().m16576h();
    }

    public boolean acceptCookie() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return android.webkit.CookieManager.getInstance().acceptCookie();
        }
        return a.m16616c().m16588d();
    }

    public String getCookie(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            return a.m16616c().m16607a(str);
        }
        try {
            return android.webkit.CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setCookies(Map<String, String[]> map) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (!((a == null || !a.m16618b()) ? false : a.m16616c().m16601a(map))) {
            for (String str : map.keySet()) {
                for (String str2 : map.get(str)) {
                    setCookie(str, str2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m17076a() {
        this.f12747f = true;
        if (!(this.f12743a == null || this.f12743a.size() == 0)) {
            X5CoreEngine a = X5CoreEngine.m16621a();
            if (a != null && a.m16618b()) {
                Iterator<C2545b> it = this.f12743a.iterator();
                while (it.hasNext()) {
                    C2545b next = it.next();
                    switch (next.f12752a) {
                        case 1:
                            setCookie(next.f12753b, next.f12754c, next.f12755d);
                            break;
                        case 2:
                            setCookie(next.f12753b, next.f12754c);
                            break;
                    }
                }
            } else {
                Iterator<C2545b> it2 = this.f12743a.iterator();
                while (it2.hasNext()) {
                    C2545b next2 = it2.next();
                    switch (next2.f12752a) {
                        case 1:
                            if (Build.VERSION.SDK_INT < 21) {
                                break;
                            } else {
                                ReflectionUtils.m16404a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, next2.f12753b, next2.f12754c, next2.f12755d);
                                break;
                            }
                        case 2:
                            android.webkit.CookieManager.getInstance().setCookie(next2.f12753b, next2.f12754c);
                            break;
                    }
                }
            }
            this.f12743a.clear();
        }
    }

    public boolean setCookieCompatialbeMode(Context context, EnumC2544a aVar, String str, boolean z) {
        System.currentTimeMillis();
        if (context == null || !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME)) {
            return false;
        }
        this.f12745c = aVar;
        if (str != null) {
            this.f12744b = str;
        }
        if (this.f12745c == EnumC2544a.MODE_NONE || !z || X5CoreEngine.m16621a().m16615d()) {
            return true;
        }
        X5CoreEngine.m16621a().m16620a(context);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void m17075a(Context context, boolean z, boolean z2) {
        int i;
        int i2;
        if (this.f12745c != EnumC2544a.MODE_NONE && context != null && TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME) && !this.f12746e) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = 0;
            String str = LOGTAG;
            TbsLog.m16531i(str, "compatiableCookieDatabaseIfNeed,isX5Inited:" + z + ",useX5:" + z2);
            if (!z && !QbSdk.getIsSysWebViewForcedByOuter() && !QbSdk.f12789a) {
                X5CoreEngine.m16621a().m16620a(context);
                return;
            }
            boolean z3 = false;
            if (QbSdk.getIsSysWebViewForcedByOuter() || QbSdk.f12789a) {
                z2 = false;
            }
            boolean canUseFunction = TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.USEX5_FILE_NAME);
            String str2 = LOGTAG;
            TbsLog.m16531i(str2, "usex5 : mUseX5LastProcess->" + canUseFunction + ",useX5:" + z2);
            TbsExtensionFunctionManager.getInstance().setFunctionEnable(context, TbsExtensionFunctionManager.USEX5_FILE_NAME, z2);
            if (canUseFunction != z2) {
                TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                if (TextUtils.isEmpty(this.f12744b)) {
                    tbsLogInfo.setErrorCode(701);
                    i2 = 0;
                    i = 0;
                } else if (TbsInstaller.m16742a().m16688i(context) <= 0 || TbsInstaller.m16742a().m16688i(context) >= 36001) {
                    if (canUseFunction) {
                        i2 = SqliteDataManager.m16806d(context);
                        if (i2 > 0) {
                            i = getROMCookieDBVersion(context);
                            if (i <= 0) {
                                z3 = true;
                            }
                        } else {
                            i = 0;
                        }
                    } else {
                        i2 = SqliteDataManager.m16806d(context);
                        if (i2 > 0) {
                            String d = TbsInstaller.m16742a().m16702d(context, "cookies_database_version");
                            if (!TextUtils.isEmpty(d)) {
                                try {
                                    i = Integer.parseInt(d);
                                } catch (Exception unused) {
                                }
                            }
                        }
                        i = 0;
                    }
                    if (!z3 && (i2 <= 0 || i <= 0)) {
                        tbsLogInfo.setErrorCode(702);
                    } else if (i >= i2) {
                        tbsLogInfo.setErrorCode(703);
                    } else {
                        SqliteDataManager.m16811a(context, this.f12745c, this.f12744b, z3, z2);
                        tbsLogInfo.setErrorCode(TbsListener.ErrorCode.INFO_COOKIE_SWITCH_TRANSFER);
                        j = System.currentTimeMillis() - currentTimeMillis;
                    }
                } else {
                    return;
                }
                tbsLogInfo.setFailDetail("x5->sys:" + canUseFunction + " from:" + i2 + " to:" + i + ",timeused:" + j);
                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_COOKIE_DB_SWITCH, tbsLogInfo);
            }
        }
    }

    public static int getROMCookieDBVersion(Context context) {
        SharedPreferences sharedPreferences;
        if (Build.VERSION.SDK_INT >= 11) {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 4);
        } else {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 0);
        }
        return sharedPreferences.getInt("db_version", -1);
    }

    public static void setROMCookieDBVersion(Context context, int i) {
        SharedPreferences sharedPreferences;
        if (Build.VERSION.SDK_INT >= 11) {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 4);
        } else {
            sharedPreferences = context.getSharedPreferences("cookiedb_info", 0);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("db_version", i);
        edit.commit();
    }
}
