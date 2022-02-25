package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import com.lody.virtual.server.p063pm.installer.PackageHelper;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.export.external.interfaces.IconListener;
import com.tencent.smtt.utils.TbsLog;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.u */
/* loaded from: classes2.dex */
public class X5CoreWizard {

    /* renamed from: a */
    private DexLoader f13254a;

    public X5CoreWizard(DexLoader dexLoader) {
        this.f13254a = dexLoader;
    }

    /* renamed from: a */
    public boolean m16614a() throws Throwable {
        Method method = this.f13254a.getClassLoader().loadClass("com.tencent.tbs.tbsshell.WebCoreProxy").getMethod("canUseX5", new Class[0]);
        method.setAccessible(true);
        Object invoke = method.invoke(null, new Object[0]);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return ((Boolean) invoke).booleanValue();
    }

    /* renamed from: b */
    public DexLoader m16598b() {
        return this.f13254a;
    }

    /* renamed from: c */
    public Object m16592c() {
        return this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cacheDisabled", new Class[0], new Object[0]);
    }

    /* renamed from: d */
    public boolean m16588d() {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptCookie", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: e */
    public void m16585e() {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookie", new Class[0], new Object[0]);
    }

    /* renamed from: a */
    public boolean m16601a(Map<String, String[]> map) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookies", new Class[]{Map.class}, map);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: a */
    public void m16600a(boolean z) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webview_setWebContentsDebuggingEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0075 A[RETURN] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.tencent.smtt.export.external.interfaces.IX5WebViewBase m16612a(android.content.Context r9) {
        /*
            r8 = this;
            com.tencent.smtt.export.external.DexLoader r0 = r8.f13254a
            java.lang.String r1 = "com.tencent.tbs.tbsshell.WebCoreProxy"
            java.lang.String r2 = "createSDKWebview"
            r3 = 1
            java.lang.Class[] r4 = new java.lang.Class[r3]
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r6 = 0
            r4[r6] = r5
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r6] = r9
            java.lang.Object r0 = r0.invokeStaticMethod(r1, r2, r4, r3)
            r1 = 325(0x145, float:4.55E-43)
            r2 = 0
            if (r0 != 0) goto L_0x0050
            com.tencent.smtt.export.external.DexLoader r3 = r8.f13254a     // Catch: Exception -> 0x006d
            java.lang.String r4 = "com.tencent.tbs.tbsshell.TBSShell"
            java.lang.String r5 = "getLoadFailureDetails"
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch: Exception -> 0x006d
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: Exception -> 0x006d
            java.lang.Object r3 = r3.invokeStaticMethod(r4, r5, r7, r6)     // Catch: Exception -> 0x006d
            if (r3 == 0) goto L_0x0039
            boolean r4 = r3 instanceof java.lang.Throwable     // Catch: Exception -> 0x006d
            if (r4 == 0) goto L_0x0039
            com.tencent.smtt.sdk.TbsCoreLoadStat r4 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch: Exception -> 0x006d
            r5 = r3
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch: Exception -> 0x006d
            r4.m17035a(r9, r1, r5)     // Catch: Exception -> 0x006d
        L_0x0039:
            if (r3 == 0) goto L_0x004d
            boolean r4 = r3 instanceof java.lang.String     // Catch: Exception -> 0x006d
            if (r4 == 0) goto L_0x004d
            com.tencent.smtt.sdk.TbsCoreLoadStat r4 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch: Exception -> 0x006d
            java.lang.Throwable r5 = new java.lang.Throwable     // Catch: Exception -> 0x006d
            java.lang.String r3 = (java.lang.String) r3     // Catch: Exception -> 0x006d
            r5.<init>(r3)     // Catch: Exception -> 0x006d
            r4.m17035a(r9, r1, r5)     // Catch: Exception -> 0x006d
        L_0x004d:
            r0 = r2
            r3 = r0
            goto L_0x0072
        L_0x0050:
            r3 = r0
            com.tencent.smtt.export.external.interfaces.IX5WebViewBase r3 = (com.tencent.smtt.export.external.interfaces.IX5WebViewBase) r3     // Catch: Exception -> 0x006d
            if (r3 == 0) goto L_0x0072
            android.view.View r4 = r3.getView()     // Catch: Exception -> 0x006b
            if (r4 != 0) goto L_0x0072
            com.tencent.smtt.sdk.TbsCoreLoadStat r4 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch: Exception -> 0x006b
            java.lang.Throwable r5 = new java.lang.Throwable     // Catch: Exception -> 0x006b
            java.lang.String r6 = "x5webview.getView is null!"
            r5.<init>(r6)     // Catch: Exception -> 0x006b
            r4.m17035a(r9, r1, r5)     // Catch: Exception -> 0x006b
            r0 = r2
            goto L_0x0072
        L_0x006b:
            r9 = move-exception
            goto L_0x006f
        L_0x006d:
            r9 = move-exception
            r3 = r2
        L_0x006f:
            r9.printStackTrace()
        L_0x0072:
            if (r0 != 0) goto L_0x0075
            return r2
        L_0x0075:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.X5CoreWizard.m16612a(android.content.Context):com.tencent.smtt.export.external.interfaces.IX5WebViewBase");
    }

    /* renamed from: a */
    public String m16607a(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCookie", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: f */
    public String m16582f() {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getMiniQBVersion", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: a */
    public InputStream m16602a(String str, boolean z) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCacheFile", new Class[]{String.class, Boolean.TYPE}, str, Boolean.valueOf(z));
        if (invokeStaticMethod == null) {
            return null;
        }
        return (InputStream) invokeStaticMethod;
    }

    /* renamed from: g */
    public Object m16579g() {
        return this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCachFileBaseDir", new Class[0], new Object[0]);
    }

    /* renamed from: h */
    public boolean m16576h() {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_hasCookies", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: i */
    public IX5WebChromeClient m16573i() {
        Object invokeStaticMethod;
        DexLoader dexLoader = this.f13254a;
        if (dexLoader == null || (invokeStaticMethod = dexLoader.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebChromeClient", new Class[0], new Object[0])) == null) {
            return null;
        }
        return (IX5WebChromeClient) invokeStaticMethod;
    }

    /* renamed from: j */
    public IX5WebViewClient m16570j() {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebViewClient", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (IX5WebViewClient) invokeStaticMethod;
    }

    /* renamed from: k */
    public IX5WebViewClientExtension m16568k() {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebChromeClientExtension", new Class[0], new Object[0]);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (IX5WebViewClientExtension) invokeStaticMethod;
    }

    /* renamed from: b */
    public void m16595b(String str) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "openIconDB", new Class[]{String.class}, str);
    }

    /* renamed from: a */
    public Uri[] m16613a(int i, Intent intent) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "parseFileChooserResult", new Class[]{Integer.TYPE, Intent.class}, Integer.valueOf(i), intent);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (Uri[]) invokeStaticMethod;
    }

    /* renamed from: l */
    public void m16566l() {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "removeAllIcons", null, new Object[0]);
    }

    /* renamed from: a */
    public void m16604a(String str, IconListener iconListener) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "requestIconForPageUrl", new Class[]{String.class, IconListener.class}, str, iconListener);
    }

    /* renamed from: c */
    public void m16590c(String str) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "retainIconForPageUrl", new Class[]{String.class}, str);
    }

    /* renamed from: d */
    public void m16586d(String str) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "releaseIconForPageUrl", new Class[]{String.class}, str);
    }

    /* renamed from: m */
    public void m16564m() {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "closeIconDB", null, new Object[0]);
    }

    /* renamed from: b */
    public boolean m16597b(Context context) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasUsernamePassword", new Class[]{Context.class}, context);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: c */
    public void m16591c(Context context) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearUsernamePassword", new Class[]{Context.class}, context);
    }

    /* renamed from: d */
    public boolean m16587d(Context context) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasHttpAuthUsernamePassword", new Class[]{Context.class}, context);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: e */
    public void m16584e(Context context) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearHttpAuthUsernamePassword", new Class[]{Context.class}, context);
    }

    /* renamed from: f */
    public boolean m16581f(Context context) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasFormData", new Class[]{Context.class}, context);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: g */
    public void m16578g(Context context) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearFormData", new Class[]{Context.class}, context);
    }

    /* renamed from: a */
    public void m16608a(ValueCallback<Map> valueCallback) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetOrigins", new Class[]{ValueCallback.class}, valueCallback);
    }

    /* renamed from: a */
    public void m16605a(String str, ValueCallback<Long> valueCallback) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetUsageForOrigin", new Class[]{String.class, ValueCallback.class}, str, valueCallback);
    }

    /* renamed from: b */
    public void m16594b(String str, ValueCallback<Long> valueCallback) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetQuotaForOrigin", new Class[]{String.class, ValueCallback.class}, str, valueCallback);
    }

    /* renamed from: a */
    public void m16606a(String str, long j) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageSetQuotaForOrigin", new Class[]{String.class, Long.TYPE}, str, Long.valueOf(j));
    }

    /* renamed from: e */
    public void m16583e(String str) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageDeleteOrigin", new Class[]{String.class}, str);
    }

    /* renamed from: n */
    public void m16562n() {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageDeleteAllData", null, new Object[0]);
    }

    /* renamed from: h */
    public IX5DateSorter m16575h(Context context) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDateSorter", new Class[]{Context.class}, context);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (IX5DateSorter) invokeStaticMethod;
    }

    /* renamed from: b */
    public void m16596b(ValueCallback<Set<String>> valueCallback) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsGetOrigins", new Class[]{ValueCallback.class}, valueCallback);
    }

    /* renamed from: c */
    public void m16589c(String str, ValueCallback<Boolean> valueCallback) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsGetAllowed", new Class[]{String.class, ValueCallback.class}, str, valueCallback);
    }

    /* renamed from: f */
    public void m16580f(String str) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsClear", new Class[]{String.class}, str);
    }

    /* renamed from: g */
    public void m16577g(String str) {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsAllow", new Class[]{String.class}, str);
    }

    /* renamed from: o */
    public void m16560o() {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsClearAll", null, new Object[0]);
    }

    /* renamed from: h */
    public String m16574h(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapGetFileExtensionFromUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: i */
    public boolean m16571i(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapHasMimeType", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: j */
    public String m16569j(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapGetMimeTypeFromExtension", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: k */
    public boolean m16567k(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapHasExtension", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: l */
    public String m16565l(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapGetMimeTypeFromExtension", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: m */
    public String m16563m(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilGuessUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: a */
    public String m16603a(String str, String str2, String str3) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilComposeSearchUrl", new Class[]{String.class, String.class, String.class}, str, str2, str3);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: a */
    public byte[] m16599a(byte[] bArr) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilDecode", new Class[]{String.class}, bArr);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (byte[]) invokeStaticMethod;
    }

    /* renamed from: n */
    public boolean m16561n(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsAssetUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: o */
    public boolean m16559o(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsCookielessProxyUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: p */
    public boolean m16557p(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsFileUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: q */
    public boolean m16556q(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsAboutUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: r */
    public boolean m16555r(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsDataUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: s */
    public boolean m16554s(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsJavaScriptUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: t */
    public boolean m16553t(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsHttpUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: u */
    public boolean m16552u(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsHttpsUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: v */
    public boolean m16551v(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsNetworkUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: w */
    public boolean m16550w(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsContentUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: x */
    public boolean m16549x(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsValidUrl", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: y */
    public String m16548y(String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilStripAnchor", new Class[]{String.class}, str);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: b */
    public String m16593b(String str, String str2, String str3) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilGuessFileName", new Class[]{String.class, String.class, String.class}, str, str2, str3);
        if (invokeStaticMethod == null) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    /* renamed from: a */
    public void m16609a(Context context, boolean z) {
        TbsLog.m16527w("desktop", " tbsWizard clearAllX5Cache");
        if (z) {
            this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "clearAllCache", new Class[]{Context.class}, context);
            return;
        }
        try {
            this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "clearAllCache", new Class[]{Context.class, Boolean.TYPE}, context, Boolean.valueOf(z));
        } catch (Exception unused) {
            this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearUsernamePassword", new Class[]{Context.class}, context);
            this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearHttpAuthUsernamePassword", new Class[]{Context.class}, context);
            this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearFormData", new Class[]{Context.class}, context);
            this.f13254a.invokeStaticMethod("com.tencent.smtt.webkit.CacheManager", "removeAllCacheFiles", null, new Object[0]);
            this.f13254a.invokeStaticMethod("com.tencent.smtt.webkit.CacheManager", "clearLocalStorage", null, new Object[0]);
            Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.smtt.net.http.DnsManager", "getInstance", null, new Object[0]);
            if (invokeStaticMethod != null) {
                this.f13254a.invokeMethod(invokeStaticMethod, "com.tencent.smtt.net.http.DnsManager", "removeAllDns", null, new Object[0]);
            }
            Object invokeStaticMethod2 = this.f13254a.invokeStaticMethod("com.tencent.smtt.webkit.SmttPermanentPermissions", "getInstance", null, new Object[0]);
            if (invokeStaticMethod2 != null) {
                this.f13254a.invokeMethod(invokeStaticMethod2, "com.tencent.smtt.webkit.SmttPermanentPermissions", "clearAllPermanentPermission", null, new Object[0]);
            }
            this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "removeAllIcons", null, new Object[0]);
        }
    }

    /* renamed from: a */
    public int m16610a(Context context, String str, Map<String, String> map, String str2, ValueCallback<String> valueCallback) {
        if (TbsDownloader.getOverSea(context)) {
            return PackageHelper.INSTALL_PARSE_FAILED_NO_CERTIFICATES;
        }
        if (str2 == null) {
            Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, Map.class, ValueCallback.class}, context, str, map, valueCallback);
            if (invokeStaticMethod == null) {
                invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, Map.class}, context, str, map);
            }
            if (invokeStaticMethod == null) {
                invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class}, context, str);
            }
            return invokeStaticMethod == null ? PackageHelper.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES : ((Integer) invokeStaticMethod).intValue();
        }
        Object invokeStaticMethod2 = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, String.class}, context, str, str2);
        return invokeStaticMethod2 == null ? PackageHelper.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES : ((Integer) invokeStaticMethod2).intValue();
    }

    /* renamed from: a */
    public boolean m16611a(Context context, String str) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "canOpenFile", new Class[]{Context.class, String.class}, context, str);
        if (invokeStaticMethod instanceof Boolean) {
            return ((Boolean) invokeStaticMethod).booleanValue();
        }
        return false;
    }

    /* renamed from: p */
    public void m16558p() {
        this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "closeFileReader", new Class[0], new Object[0]);
    }

    /* renamed from: i */
    public String m16572i(Context context) {
        Object invokeStaticMethod = this.f13254a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getDefaultUserAgent", new Class[]{Context.class}, context);
        if (invokeStaticMethod instanceof String) {
            return (String) invokeStaticMethod;
        }
        return null;
    }
}
