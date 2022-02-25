package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebSettingsExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.p078a.HttpUtils;
import com.tencent.smtt.sdk.p078a.MttLoader;
import com.tencent.smtt.utils.DebugTbsPlugin;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.ReflectionUtils;
import com.tencent.smtt.utils.TbsConfigFile;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.FileLock;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class WebView extends FrameLayout implements View.OnLongClickListener {
    public static final int GETPVERROR = -1;
    public static final int NIGHT_MODE_COLOR = -16777216;
    public static final int NORMAL_MODE_ALPHA = 255;
    public static final String SCHEME_GEO = "geo:0,0?q=";
    public static final String SCHEME_MAILTO = "mailto:";
    public static final String SCHEME_TEL = "tel:";

    /* renamed from: a */
    int f13022a;

    /* renamed from: b */
    private final String f13023b;

    /* renamed from: e */
    private boolean f13024e;

    /* renamed from: f */
    private IX5WebViewBase f13025f;

    /* renamed from: g */
    private C2605a f13026g;

    /* renamed from: h */
    private WebSettings f13027h;

    /* renamed from: i */
    private Context f13028i;

    /* renamed from: k */
    private boolean f13029k;
    public WebViewCallbackClient mWebViewCallbackClient;

    /* renamed from: n */
    private WebViewClient f13030n;

    /* renamed from: o */
    private WebChromeClient f13031o;

    /* renamed from: q */
    private final int f13032q;

    /* renamed from: r */
    private final int f13033r;

    /* renamed from: s */
    private final int f13034s;

    /* renamed from: t */
    private final String f13035t;

    /* renamed from: u */
    private final String f13036u;

    /* renamed from: x */
    private Object f13037x;

    /* renamed from: y */
    private View.OnLongClickListener f13038y;

    /* renamed from: c */
    private static final Lock f13014c = new ReentrantLock();

    /* renamed from: d */
    private static OutputStream f13015d = null;

    /* renamed from: j */
    private static Context f13016j = null;
    public static boolean mWebViewCreated = false;

    /* renamed from: l */
    private static TbsConfigFile f13017l = null;

    /* renamed from: m */
    private static Method f13018m = null;

    /* renamed from: p */
    private static String f13019p = null;
    public static boolean mSysWebviewCreated = false;

    /* renamed from: v */
    private static Paint f13020v = null;

    /* renamed from: w */
    private static boolean f13021w = true;
    public static int NIGHT_MODE_ALPHA = 153;

    /* loaded from: classes2.dex */
    public interface PictureListener {
        void onNewPicture(WebView webView, Picture picture);
    }

    public static int getTbsSDKVersion(Context context) {
        return 43697;
    }

    public boolean showFindDialog(String str, boolean z) {
        return false;
    }

    /* loaded from: classes2.dex */
    public class WebViewTransport {

        /* renamed from: b */
        private WebView f13053b;

        public WebViewTransport() {
        }

        public synchronized void setWebView(WebView webView) {
            this.f13053b = webView;
        }

        public synchronized WebView getWebView() {
            return this.f13053b;
        }
    }

    /* loaded from: classes2.dex */
    public static class HitTestResult {
        @Deprecated
        public static final int ANCHOR_TYPE = 1;
        public static final int EDIT_TEXT_TYPE = 9;
        public static final int EMAIL_TYPE = 4;
        public static final int GEO_TYPE = 3;
        @Deprecated
        public static final int IMAGE_ANCHOR_TYPE = 6;
        public static final int IMAGE_TYPE = 5;
        public static final int PHONE_TYPE = 2;
        public static final int SRC_ANCHOR_TYPE = 7;
        public static final int SRC_IMAGE_ANCHOR_TYPE = 8;
        public static final int UNKNOWN_TYPE = 0;

        /* renamed from: a */
        private IX5WebViewBase.HitTestResult f13050a;

        /* renamed from: b */
        private WebView.HitTestResult f13051b;

        public HitTestResult() {
            this.f13051b = null;
            this.f13050a = null;
            this.f13051b = null;
        }

        public HitTestResult(IX5WebViewBase.HitTestResult hitTestResult) {
            this.f13051b = null;
            this.f13050a = hitTestResult;
            this.f13051b = null;
        }

        public HitTestResult(WebView.HitTestResult hitTestResult) {
            this.f13051b = null;
            this.f13050a = null;
            this.f13051b = hitTestResult;
        }

        public int getType() {
            IX5WebViewBase.HitTestResult hitTestResult = this.f13050a;
            if (hitTestResult != null) {
                return hitTestResult.getType();
            }
            WebView.HitTestResult hitTestResult2 = this.f13051b;
            if (hitTestResult2 != null) {
                return hitTestResult2.getType();
            }
            return 0;
        }

        public String getExtra() {
            IX5WebViewBase.HitTestResult hitTestResult = this.f13050a;
            if (hitTestResult != null) {
                return hitTestResult.getExtra();
            }
            WebView.HitTestResult hitTestResult2 = this.f13051b;
            if (hitTestResult2 != null) {
                return hitTestResult2.getExtra();
            }
            return "";
        }
    }

    public WebView(Context context, boolean z) {
        super(context);
        this.f13023b = "WebView";
        this.f13024e = false;
        this.f13027h = null;
        this.f13028i = null;
        this.f13022a = 0;
        this.f13029k = false;
        this.f13030n = null;
        this.f13031o = null;
        this.f13032q = 1;
        this.f13033r = 2;
        this.f13034s = 3;
        this.f13035t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.f13036u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.f13037x = null;
        this.f13038y = null;
    }

    public WebView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, false);
    }

    public WebView(Context context, AttributeSet attributeSet, int i, boolean z) {
        this(context, attributeSet, i, null, z);
    }

    @TargetApi(11)
    public WebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean z) {
        super(context, attributeSet, i);
        this.f13023b = "WebView";
        this.f13024e = false;
        this.f13027h = null;
        this.f13028i = null;
        this.f13022a = 0;
        this.f13029k = false;
        this.f13030n = null;
        this.f13031o = null;
        this.f13032q = 1;
        this.f13033r = 2;
        this.f13034s = 3;
        this.f13035t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.f13036u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.f13037x = null;
        this.f13038y = null;
        mWebViewCreated = true;
        if (!QbSdk.getIsSysWebViewForcedByOuter() || !TbsShareManager.isThirdPartyApp(context)) {
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.setWriteLogJIT(true);
            } else {
                TbsLog.setWriteLogJIT(false);
            }
            TbsLog.initIfNeed(context);
            if (context != null) {
                if (f13017l == null) {
                    f13017l = TbsConfigFile.m16379a(context);
                }
                if (f13017l.f13405a) {
                    TbsLog.m16532e("WebView", "sys WebView: debug.conf force syswebview", true);
                    QbSdk.m17056a(context, "debug.conf force syswebview!");
                }
                m16923c(context);
                this.f13028i = context;
                if (context != null) {
                    f13016j = context.getApplicationContext();
                }
                if (!this.f13024e || QbSdk.f12789a) {
                    this.f13025f = null;
                    if (TbsShareManager.isThirdPartyApp(this.f13028i)) {
                        this.f13026g = new C2605a(context, attributeSet);
                    } else {
                        this.f13026g = new C2605a(this, context);
                    }
                    TbsLog.m16531i("WebView", "SystemWebView Created Success! #2");
                    CookieManager.getInstance().m17075a(context, true, false);
                    CookieManager.getInstance().m17076a();
                    this.f13026g.setFocusableInTouchMode(true);
                    addView(this.f13026g, new FrameLayout.LayoutParams(-1, -1));
                    setDownloadListener(null);
                    TbsLog.writeLogToDisk();
                    TbsInstaller.m16740a(context);
                } else {
                    this.f13025f = X5CoreEngine.m16621a().m16619a(true).m16612a(context);
                    IX5WebViewBase iX5WebViewBase = this.f13025f;
                    if (iX5WebViewBase == null || iX5WebViewBase.getView() == null) {
                        TbsLog.m16532e("WebView", "sys WebView: failed to createTBSWebview", true);
                        this.f13025f = null;
                        this.f13024e = false;
                        QbSdk.m17056a(context, "failed to createTBSWebview!");
                        m16923c(context);
                        if (TbsShareManager.isThirdPartyApp(this.f13028i)) {
                            this.f13026g = new C2605a(context, attributeSet);
                        } else {
                            this.f13026g = new C2605a(this, context);
                        }
                        TbsLog.m16531i("WebView", "SystemWebView Created Success! #1");
                        CookieManager.getInstance().m17075a(context, true, false);
                        CookieManager.getInstance().m17076a();
                        this.f13026g.setFocusableInTouchMode(true);
                        addView(this.f13026g, new FrameLayout.LayoutParams(-1, -1));
                        try {
                            if (Build.VERSION.SDK_INT >= 11) {
                                removeJavascriptInterface("searchBoxJavaBridge_");
                                removeJavascriptInterface("accessibility");
                                removeJavascriptInterface("accessibilityTraversal");
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        TbsLog.writeLogToDisk();
                        TbsInstaller.m16740a(context);
                        return;
                    }
                    TbsLog.m16531i("WebView", "X5 WebView Created Success!!");
                    this.f13025f.getView().setFocusableInTouchMode(true);
                    m16933a(attributeSet);
                    addView(this.f13025f.getView(), new FrameLayout.LayoutParams(-1, -1));
                    this.f13025f.setDownloadListener(new DownLoadListenerAdapter(this, null, this.f13024e));
                    this.f13025f.getX5WebViewExtension().setWebViewClientExtension(new X5ProxyWebViewClientExtension(X5CoreEngine.m16621a().m16619a(true).m16568k()) { // from class: com.tencent.smtt.sdk.WebView.1
                        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                        public void invalidate() {
                        }

                        @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                        public void onScrollChanged(int i2, int i3, int i4, int i5) {
                            super.onScrollChanged(i2, i3, i4, i5);
                            WebView.this.onScrollChanged(i4, i5, i2, i3);
                        }
                    });
                }
                try {
                    if (Build.VERSION.SDK_INT >= 11) {
                        removeJavascriptInterface("searchBoxJavaBridge_");
                        removeJavascriptInterface("accessibility");
                        removeJavascriptInterface("accessibilityTraversal");
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                if ((TbsConfig.APP_QQ.equals(this.f13028i.getApplicationInfo().packageName) || TbsConfig.APP_WX.equals(this.f13028i.getApplicationInfo().packageName)) && SDKEngine.m16828a(true).m16819h() && Build.VERSION.SDK_INT >= 11) {
                    setLayerType(1, null);
                }
                if (this.f13025f != null) {
                    TbsLog.writeLogToDisk();
                    if (!TbsShareManager.isThirdPartyApp(context)) {
                        int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                        if (i2 <= 0 || i2 == TbsInstaller.m16742a().m16690h(context) || i2 != TbsInstaller.m16742a().m16688i(context)) {
                            TbsLog.m16531i("WebView", "webview construction #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + TbsInstaller.m16742a().m16690h(context) + " getTbsCoreInstalledVerInNolock is " + TbsInstaller.m16742a().m16688i(context));
                        } else {
                            TbsInstaller.m16742a().m16681n(context);
                        }
                    }
                }
                QbSdk.continueLoadSo(context);
                return;
            }
            throw new IllegalArgumentException("Invalid context argument");
        }
        this.f13028i = context;
        this.f13025f = null;
        this.f13024e = false;
        QbSdk.m17056a(context, "failed to createTBSWebview!");
        this.f13026g = new C2605a(context, attributeSet);
        CookieManager.getInstance().m17075a(context, true, false);
        CookieSyncManager.createInstance(this.f13028i).startSync();
        try {
            Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
            declaredMethod.setAccessible(true);
            ((Handler) declaredMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new SQLiteUncaughtExceptionHandler());
            mSysWebviewCreated = true;
        } catch (Exception unused) {
        }
        CookieManager.getInstance().m17076a();
        this.f13026g.setFocusableInTouchMode(true);
        addView(this.f13026g, new FrameLayout.LayoutParams(-1, -1));
        TbsLog.m16531i("WebView", "SystemWebView Created Success! #3");
        TbsLog.m16532e("WebView", "sys WebView: IsSysWebViewForcedByOuter = true", true);
        TbsCoreLoadStat.getInstance().m17035a(context, 402, new Throwable());
    }

    public Object createPrintDocumentAdapter(String str) {
        if (this.f13024e) {
            try {
                return this.f13025f.createPrintDocumentAdapter(str);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        } else if (Build.VERSION.SDK_INT < 21) {
            return null;
        } else {
            return ReflectionUtils.m16404a(this.f13026g, "createPrintDocumentAdapter", new Class[]{String.class}, str);
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        try {
            if (this.f13024e) {
                Method a = ReflectionUtils.m16405a(this.f13025f.getView(), "computeHorizontalScrollOffset", new Class[0]);
                a.setAccessible(true);
                return ((Integer) a.invoke(this.f13025f.getView(), new Object[0])).intValue();
            }
            Method a2 = ReflectionUtils.m16405a(this.f13026g, "computeHorizontalScrollOffset", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.f13026g, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        try {
            if (this.f13024e) {
                Method a = ReflectionUtils.m16405a(this.f13025f.getView(), "computeVerticalScrollOffset", new Class[0]);
                a.setAccessible(true);
                return ((Integer) a.invoke(this.f13025f.getView(), new Object[0])).intValue();
            }
            Method a2 = ReflectionUtils.m16405a(this.f13026g, "computeVerticalScrollOffset", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.f13026g, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        try {
            if (this.f13024e) {
                Method a = ReflectionUtils.m16405a(this.f13025f.getView(), "computeVerticalScrollExtent", new Class[0]);
                a.setAccessible(true);
                return ((Integer) a.invoke(this.f13025f.getView(), new Object[0])).intValue();
            }
            Method a2 = ReflectionUtils.m16405a(this.f13026g, "computeVerticalScrollExtent", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.f13026g, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        try {
            if (this.f13024e) {
                return ((Integer) ReflectionUtils.m16404a(this.f13025f.getView(), "computeHorizontalScrollRange", new Class[0], new Object[0])).intValue();
            }
            Method a = ReflectionUtils.m16405a(this.f13026g, "computeHorizontalScrollRange", new Class[0]);
            a.setAccessible(true);
            return ((Integer) a.invoke(this.f13026g, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        try {
            if (this.f13024e) {
                Method a = ReflectionUtils.m16405a(this.f13025f.getView(), "computeHorizontalScrollExtent", new Class[0]);
                a.setAccessible(true);
                return ((Integer) a.invoke(this.f13025f.getView(), new Object[0])).intValue();
            }
            Method a2 = ReflectionUtils.m16405a(this.f13026g, "computeHorizontalScrollExtent", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.f13026g, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        try {
            if (this.f13024e) {
                return ((Integer) ReflectionUtils.m16404a(this.f13025f.getView(), "computeVerticalScrollRange", new Class[0], new Object[0])).intValue();
            }
            Method a = ReflectionUtils.m16405a(this.f13026g, "computeVerticalScrollRange", new Class[0]);
            a.setAccessible(true);
            return ((Integer) a.invoke(this.f13026g, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: b */
    private boolean m16926b(Context context) {
        try {
            return context.getPackageName().indexOf(TbsConfig.APP_QQ) >= 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // android.view.View
    @TargetApi(11)
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (Build.VERSION.SDK_INT >= 21 && m16926b(this.f13028i) && isHardwareAccelerated() && i > 0 && i2 > 0) {
            getLayerType();
        }
    }

    /* renamed from: c */
    private void m16923c(Context context) {
        if (QbSdk.f12797i && TbsShareManager.isThirdPartyApp(context)) {
            TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
        }
        X5CoreEngine a = X5CoreEngine.m16621a();
        a.m16620a(context);
        this.f13024e = a.m16618b();
    }

    @Override // android.view.View
    public void setScrollBarStyle(int i) {
        if (this.f13024e) {
            this.f13025f.getView().setScrollBarStyle(i);
        } else {
            this.f13026g.setScrollBarStyle(i);
        }
    }

    public void setHorizontalScrollbarOverlay(boolean z) {
        if (!this.f13024e) {
            this.f13026g.setHorizontalScrollbarOverlay(z);
        } else {
            this.f13025f.setHorizontalScrollbarOverlay(z);
        }
    }

    public void setVerticalScrollbarOverlay(boolean z) {
        if (!this.f13024e) {
            this.f13026g.setVerticalScrollbarOverlay(z);
        } else {
            this.f13025f.setVerticalScrollbarOverlay(z);
        }
    }

    public boolean overlayHorizontalScrollbar() {
        if (!this.f13024e) {
            return this.f13026g.overlayHorizontalScrollbar();
        }
        return this.f13025f.overlayHorizontalScrollbar();
    }

    public boolean overlayVerticalScrollbar() {
        if (this.f13024e) {
            return this.f13025f.overlayVerticalScrollbar();
        }
        return this.f13026g.overlayVerticalScrollbar();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        if (this.f13024e) {
            View view2 = this.f13025f.getView();
            if (!(view2 instanceof ViewGroup)) {
                return false;
            }
            ViewGroup viewGroup = (ViewGroup) view2;
            if (view == this) {
                view = view2;
            }
            return viewGroup.requestChildRectangleOnScreen(view, rect, z);
        }
        C2605a aVar = this.f13026g;
        if (view == this) {
            view = aVar;
        }
        return aVar.requestChildRectangleOnScreen(view, rect, z);
    }

    public int getWebScrollX() {
        if (this.f13024e) {
            return this.f13025f.getView().getScrollX();
        }
        return this.f13026g.getScrollX();
    }

    public int getWebScrollY() {
        if (this.f13024e) {
            return this.f13025f.getView().getScrollY();
        }
        return this.f13026g.getScrollY();
    }

    public int getVisibleTitleHeight() {
        if (this.f13024e) {
            return this.f13025f.getVisibleTitleHeight();
        }
        Object a = ReflectionUtils.m16406a(this.f13026g, "getVisibleTitleHeight");
        if (a == null) {
            return 0;
        }
        return ((Integer) a).intValue();
    }

    public SslCertificate getCertificate() {
        if (!this.f13024e) {
            return this.f13026g.getCertificate();
        }
        return this.f13025f.getCertificate();
    }

    @Deprecated
    public void setCertificate(SslCertificate sslCertificate) {
        if (!this.f13024e) {
            this.f13026g.setCertificate(sslCertificate);
        } else {
            this.f13025f.setCertificate(sslCertificate);
        }
    }

    @Deprecated
    public void savePassword(String str, String str2, String str3) {
        if (!this.f13024e) {
            ReflectionUtils.m16404a(this.f13026g, "savePassword", new Class[]{String.class, String.class, String.class}, str, str2, str3);
        } else {
            this.f13025f.savePassword(str, str2, str3);
        }
    }

    public void setHttpAuthUsernamePassword(String str, String str2, String str3, String str4) {
        if (!this.f13024e) {
            this.f13026g.setHttpAuthUsernamePassword(str, str2, str3, str4);
        } else {
            this.f13025f.setHttpAuthUsernamePassword(str, str2, str3, str4);
        }
    }

    public String[] getHttpAuthUsernamePassword(String str, String str2) {
        if (!this.f13024e) {
            return this.f13026g.getHttpAuthUsernamePassword(str, str2);
        }
        return this.f13025f.getHttpAuthUsernamePassword(str, str2);
    }

    public void tbsWebviewDestroy(boolean z) {
        boolean z2;
        Bundle sdkQBStatisticsInfo;
        if (!this.f13029k && this.f13022a != 0) {
            this.f13029k = true;
            String str = "";
            String str2 = "";
            String str3 = "";
            if (this.f13024e && (sdkQBStatisticsInfo = this.f13025f.getX5WebViewExtension().getSdkQBStatisticsInfo()) != null) {
                str = sdkQBStatisticsInfo.getString("guid");
                str2 = sdkQBStatisticsInfo.getString("qua2");
                str3 = sdkQBStatisticsInfo.getString("lc");
            }
            if (TbsConfig.APP_QZONE.equals(this.f13028i.getApplicationInfo().packageName)) {
                int d = m16921d(this.f13028i);
                if (d == -1) {
                    d = this.f13022a;
                }
                this.f13022a = d;
                m16919e(this.f13028i);
            }
            try {
                z2 = this.f13025f.getX5WebViewExtension().isX5CoreSandboxMode();
            } catch (Throwable th) {
                TbsLog.m16527w("tbsWebviewDestroy", "exception: " + th);
                z2 = false;
            }
            HttpUtils.m16890a(this.f13028i, str, str2, str3, this.f13022a, this.f13024e, m16916h(), z2);
            this.f13022a = 0;
            this.f13029k = false;
        }
        if (!this.f13024e) {
            try {
                Class<?> cls = Class.forName("android.webkit.WebViewClassic");
                Method method = cls.getMethod("fromWebView", android.webkit.WebView.class);
                method.setAccessible(true);
                Object invoke = method.invoke(null, this.f13026g);
                if (invoke != null) {
                    Field declaredField = cls.getDeclaredField("mListBoxDialog");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(invoke);
                    if (obj != null) {
                        Dialog dialog = (Dialog) obj;
                        dialog.setOnCancelListener(null);
                        Class<?> cls2 = Class.forName("android.app.Dialog");
                        Field declaredField2 = cls2.getDeclaredField("CANCEL");
                        declaredField2.setAccessible(true);
                        int intValue = ((Integer) declaredField2.get(dialog)).intValue();
                        Field declaredField3 = cls2.getDeclaredField("mListenersHandler");
                        declaredField3.setAccessible(true);
                        ((Handler) declaredField3.get(dialog)).removeMessages(intValue);
                    }
                }
            } catch (Exception unused) {
            }
            if (z) {
                this.f13026g.destroy();
            }
            try {
                Field declaredField4 = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                declaredField4.setAccessible(true);
                ComponentCallbacks componentCallbacks = (ComponentCallbacks) declaredField4.get(null);
                if (componentCallbacks != null) {
                    declaredField4.set(null, null);
                    Field declaredField5 = Class.forName("android.view.ViewRoot").getDeclaredField("sConfigCallbacks");
                    declaredField5.setAccessible(true);
                    Object obj2 = declaredField5.get(null);
                    if (obj2 != null) {
                        List list = (List) obj2;
                        synchronized (list) {
                            list.remove(componentCallbacks);
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else if (z) {
            this.f13025f.destroy();
        }
        TbsLog.m16531i("WebView", "X5 GUID = " + QbSdk.m17049b());
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.tencent.smtt.sdk.WebView$2] */
    public void destroy() {
        try {
            if ("com.xunmeng.pinduoduo".equals(this.f13028i.getApplicationInfo().packageName)) {
                new Thread("WebviewDestroy") { // from class: com.tencent.smtt.sdk.WebView.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        WebView.this.tbsWebviewDestroy(false);
                    }
                }.start();
                if (this.f13024e) {
                    this.f13025f.destroy();
                } else {
                    this.f13026g.destroy();
                }
            } else {
                tbsWebviewDestroy(true);
            }
        } catch (Throwable unused) {
            tbsWebviewDestroy(true);
        }
    }

    /* renamed from: h */
    private long m16916h() {
        long j;
        synchronized (QbSdk.f12796h) {
            if (QbSdk.f12793e) {
                QbSdk.f12795g += System.currentTimeMillis() - QbSdk.f12794f;
                TbsLog.m16535d("sdkreport", "pv report, WebView.getWifiConnectedTime QbSdk.sWifiConnectedTime=" + QbSdk.f12795g);
            }
            j = QbSdk.f12795g / 1000;
            QbSdk.f12795g = 0L;
            QbSdk.f12794f = System.currentTimeMillis();
        }
        return j;
    }

    @Deprecated
    public static void enablePlatformNotifications() {
        if (!X5CoreEngine.m16621a().m16618b()) {
            ReflectionUtils.m16403a("android.webkit.WebView", "enablePlatformNotifications");
        }
    }

    @Deprecated
    public static void disablePlatformNotifications() {
        if (!X5CoreEngine.m16621a().m16618b()) {
            ReflectionUtils.m16403a("android.webkit.WebView", "disablePlatformNotifications");
        }
    }

    public void setNetworkAvailable(boolean z) {
        if (this.f13024e) {
            this.f13025f.setNetworkAvailable(z);
        } else if (Build.VERSION.SDK_INT >= 3) {
            this.f13026g.setNetworkAvailable(z);
        }
    }

    public WebBackForwardList saveState(Bundle bundle) {
        if (!this.f13024e) {
            return WebBackForwardList.m16942a(this.f13026g.saveState(bundle));
        }
        return WebBackForwardList.m16941a(this.f13025f.saveState(bundle));
    }

    @Deprecated
    public boolean savePicture(Bundle bundle, File file) {
        if (this.f13024e) {
            return this.f13025f.savePicture(bundle, file);
        }
        Object a = ReflectionUtils.m16404a(this.f13026g, "savePicture", new Class[]{Bundle.class, File.class}, bundle, file);
        if (a == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    @Deprecated
    public boolean restorePicture(Bundle bundle, File file) {
        if (this.f13024e) {
            return this.f13025f.restorePicture(bundle, file);
        }
        Object a = ReflectionUtils.m16404a(this.f13026g, "restorePicture", new Class[]{Bundle.class, File.class}, bundle, file);
        if (a == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    public WebBackForwardList restoreState(Bundle bundle) {
        if (!this.f13024e) {
            return WebBackForwardList.m16942a(this.f13026g.restoreState(bundle));
        }
        return WebBackForwardList.m16941a(this.f13025f.restoreState(bundle));
    }

    public JSONObject reportInitPerformance(long j, int i, long j2, long j3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("IS_X5", this.f13024e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    @TargetApi(8)
    public void loadUrl(String str, Map<String, String> map) {
        boolean z = this.f13024e;
        if (str != null && !showDebugView(str)) {
            if (this.f13024e) {
                this.f13025f.loadUrl(str, map);
            } else if (Build.VERSION.SDK_INT >= 8) {
                this.f13026g.loadUrl(str, map);
            }
        }
    }

    public void loadUrl(String str) {
        boolean z = this.f13024e;
        if (str != null && !showDebugView(str)) {
            if (!this.f13024e) {
                this.f13026g.loadUrl(str);
            } else {
                this.f13025f.loadUrl(str);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public boolean showDebugView(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("http://debugtbs.qq.com")) {
            getView().setVisibility(4);
            DebugTbsPlugin.m16488a(this.f13028i).m16486a(lowerCase, this, this.f13028i, TbsHandlerThread.m16748a().getLooper());
            return true;
        } else if (!lowerCase.startsWith("http://debugx5.qq.com") || this.f13024e) {
            return false;
        } else {
            loadDataWithBaseURL(null, "<!DOCTYPE html><html><body><head><title>无法打开debugx5</title><meta name=\"viewport\" content=\"width=device-width, user-scalable=no\" /></head><br/><br /><h2>debugx5页面仅在使用了X5内核时有效，由于当前没有使用X5内核，无法打开debugx5！</h2><br />尝试<a href=\"http://debugtbs.qq.com?10000\">进入DebugTbs安装或打开X5内核</a></body></html>", "text/html", EmailConstants.UTF_8, null);
            return true;
        }
    }

    @TargetApi(5)
    public void postUrl(String str, byte[] bArr) {
        if (!this.f13024e) {
            this.f13026g.postUrl(str, bArr);
        } else {
            this.f13025f.postUrl(str, bArr);
        }
    }

    public void loadData(String str, String str2, String str3) {
        if (!this.f13024e) {
            this.f13026g.loadData(str, str2, str3);
        } else {
            this.f13025f.loadData(str, str2, str3);
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!this.f13024e) {
            this.f13026g.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            this.f13025f.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    @TargetApi(11)
    public void saveWebArchive(String str) {
        if (this.f13024e) {
            this.f13025f.saveWebArchive(str);
        } else if (Build.VERSION.SDK_INT >= 11) {
            ReflectionUtils.m16404a(this.f13026g, "saveWebArchive", new Class[]{String.class}, str);
        }
    }

    @TargetApi(11)
    public void saveWebArchive(String str, boolean z, ValueCallback<String> valueCallback) {
        if (this.f13024e) {
            this.f13025f.saveWebArchive(str, z, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 11) {
            ReflectionUtils.m16404a(this.f13026g, "saveWebArchive", new Class[]{String.class, Boolean.TYPE, ValueCallback.class}, str, Boolean.valueOf(z), valueCallback);
        }
    }

    public void stopLoading() {
        if (!this.f13024e) {
            this.f13026g.stopLoading();
        } else {
            this.f13025f.stopLoading();
        }
    }

    public static void setWebContentsDebuggingEnabled(boolean z) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            a.m16616c().m16600a(z);
        } else if (Build.VERSION.SDK_INT >= 19) {
            try {
                f13018m = Class.forName("android.webkit.WebView").getDeclaredMethod("setWebContentsDebuggingEnabled", Boolean.TYPE);
                if (f13018m != null) {
                    f13018m.setAccessible(true);
                    f13018m.invoke(null, Boolean.valueOf(z));
                }
            } catch (Exception e) {
                TbsLog.m16533e("QbSdk", "Exception:" + e.getStackTrace());
                e.printStackTrace();
            }
        }
    }

    public void reload() {
        if (!this.f13024e) {
            this.f13026g.reload();
        } else {
            this.f13025f.reload();
        }
    }

    public boolean canGoBack() {
        if (!this.f13024e) {
            return this.f13026g.canGoBack();
        }
        return this.f13025f.canGoBack();
    }

    public void goBack() {
        if (!this.f13024e) {
            this.f13026g.goBack();
        } else {
            this.f13025f.goBack();
        }
    }

    public boolean canGoForward() {
        if (!this.f13024e) {
            return this.f13026g.canGoForward();
        }
        return this.f13025f.canGoForward();
    }

    public void goForward() {
        if (!this.f13024e) {
            this.f13026g.goForward();
        } else {
            this.f13025f.goForward();
        }
    }

    public boolean canGoBackOrForward(int i) {
        if (!this.f13024e) {
            return this.f13026g.canGoBackOrForward(i);
        }
        return this.f13025f.canGoBackOrForward(i);
    }

    public void goBackOrForward(int i) {
        if (!this.f13024e) {
            this.f13026g.goBackOrForward(i);
        } else {
            this.f13025f.goBackOrForward(i);
        }
    }

    public boolean pageUp(boolean z) {
        if (!this.f13024e) {
            return this.f13026g.pageUp(z);
        }
        return this.f13025f.pageUp(z, -1);
    }

    public boolean pageDown(boolean z) {
        if (!this.f13024e) {
            return this.f13026g.pageDown(z);
        }
        return this.f13025f.pageDown(z, -1);
    }

    @Deprecated
    public void clearView() {
        if (!this.f13024e) {
            ReflectionUtils.m16406a(this.f13026g, "clearView");
        } else {
            this.f13025f.clearView();
        }
    }

    @Deprecated
    public Picture capturePicture() {
        if (this.f13024e) {
            return this.f13025f.capturePicture();
        }
        Object a = ReflectionUtils.m16406a(this.f13026g, "capturePicture");
        if (a == null) {
            return null;
        }
        return (Picture) a;
    }

    @Deprecated
    public float getScale() {
        if (this.f13024e) {
            return this.f13025f.getScale();
        }
        Object a = ReflectionUtils.m16406a(this.f13026g, "getScale");
        if (a == null) {
            return 0.0f;
        }
        return ((Float) a).floatValue();
    }

    public void setInitialScale(int i) {
        if (!this.f13024e) {
            this.f13026g.setInitialScale(i);
        } else {
            this.f13025f.setInitialScale(i);
        }
    }

    public void invokeZoomPicker() {
        if (!this.f13024e) {
            this.f13026g.invokeZoomPicker();
        } else {
            this.f13025f.invokeZoomPicker();
        }
    }

    public HitTestResult getHitTestResult() {
        if (!this.f13024e) {
            return new HitTestResult(this.f13026g.getHitTestResult());
        }
        return new HitTestResult(this.f13025f.getHitTestResult());
    }

    public IX5WebViewBase.HitTestResult getX5HitTestResult() {
        if (!this.f13024e) {
            return null;
        }
        return this.f13025f.getHitTestResult();
    }

    public void requestFocusNodeHref(Message message) {
        if (!this.f13024e) {
            this.f13026g.requestFocusNodeHref(message);
        } else {
            this.f13025f.requestFocusNodeHref(message);
        }
    }

    public void requestImageRef(Message message) {
        if (!this.f13024e) {
            this.f13026g.requestImageRef(message);
        } else {
            this.f13025f.requestImageRef(message);
        }
    }

    public String getUrl() {
        if (!this.f13024e) {
            return this.f13026g.getUrl();
        }
        return this.f13025f.getUrl();
    }

    @TargetApi(3)
    public String getOriginalUrl() {
        if (!this.f13024e) {
            return this.f13026g.getOriginalUrl();
        }
        return this.f13025f.getOriginalUrl();
    }

    public String getTitle() {
        if (!this.f13024e) {
            return this.f13026g.getTitle();
        }
        return this.f13025f.getTitle();
    }

    public Bitmap getFavicon() {
        if (!this.f13024e) {
            return this.f13026g.getFavicon();
        }
        return this.f13025f.getFavicon();
    }

    public static PackageInfo getCurrentWebViewPackage() {
        if (X5CoreEngine.m16621a().m16618b() || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        try {
            return (PackageInfo) ReflectionUtils.m16403a("android.webkit.WebView", "getCurrentWebViewPackage");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setRendererPriorityPolicy(int i, boolean z) {
        try {
            if (!this.f13024e && Build.VERSION.SDK_INT >= 26) {
                ReflectionUtils.m16404a(this.f13026g, "setRendererPriorityPolicy", new Class[]{Integer.TYPE, Boolean.TYPE}, Integer.valueOf(i), Boolean.valueOf(z));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRendererRequestedPriority() {
        Object a;
        try {
            if (!this.f13024e && Build.VERSION.SDK_INT >= 26 && (a = ReflectionUtils.m16406a(this.f13026g, "getRendererRequestedPriority")) != null) {
                return ((Integer) a).intValue();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean getRendererPriorityWaivedWhenNotVisible() {
        Object a;
        try {
            if (!this.f13024e && Build.VERSION.SDK_INT >= 26 && (a = ReflectionUtils.m16406a(this.f13026g, "getRendererPriorityWaivedWhenNotVisible")) != null) {
                return ((Boolean) a).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public WebChromeClient getWebChromeClient() {
        return this.f13031o;
    }

    public WebViewClient getWebViewClient() {
        return this.f13030n;
    }

    public int getProgress() {
        if (!this.f13024e) {
            return this.f13026g.getProgress();
        }
        return this.f13025f.getProgress();
    }

    public int getContentHeight() {
        if (!this.f13024e) {
            return this.f13026g.getContentHeight();
        }
        return this.f13025f.getContentHeight();
    }

    public int getContentWidth() {
        if (this.f13024e) {
            return this.f13025f.getContentWidth();
        }
        Object a = ReflectionUtils.m16406a(this.f13026g, "getContentWidth");
        if (a == null) {
            return 0;
        }
        return ((Integer) a).intValue();
    }

    public void pauseTimers() {
        if (!this.f13024e) {
            this.f13026g.pauseTimers();
        } else {
            this.f13025f.pauseTimers();
        }
    }

    public void resumeTimers() {
        if (!this.f13024e) {
            this.f13026g.resumeTimers();
        } else {
            this.f13025f.resumeTimers();
        }
    }

    public void onPause() {
        if (!this.f13024e) {
            ReflectionUtils.m16406a(this.f13026g, "onPause");
        } else {
            this.f13025f.onPause();
        }
    }

    public void onResume() {
        if (!this.f13024e) {
            ReflectionUtils.m16406a(this.f13026g, "onResume");
        } else {
            this.f13025f.onResume();
        }
    }

    @Deprecated
    public void freeMemory() {
        if (!this.f13024e) {
            ReflectionUtils.m16406a(this.f13026g, "freeMemory");
        } else {
            this.f13025f.freeMemory();
        }
    }

    public void clearCache(boolean z) {
        if (!this.f13024e) {
            this.f13026g.clearCache(z);
        } else {
            this.f13025f.clearCache(z);
        }
    }

    public void clearFormData() {
        if (!this.f13024e) {
            this.f13026g.clearFormData();
        } else {
            this.f13025f.clearFormData();
        }
    }

    public void clearHistory() {
        if (!this.f13024e) {
            this.f13026g.clearHistory();
        } else {
            this.f13025f.clearHistory();
        }
    }

    public void clearSslPreferences() {
        if (!this.f13024e) {
            this.f13026g.clearSslPreferences();
        } else {
            this.f13025f.clearSslPreferences();
        }
    }

    public WebBackForwardList copyBackForwardList() {
        if (this.f13024e) {
            return WebBackForwardList.m16941a(this.f13025f.copyBackForwardList());
        }
        return WebBackForwardList.m16942a(this.f13026g.copyBackForwardList());
    }

    @TargetApi(16)
    public void setFindListener(final IX5WebViewBase.FindListener findListener) {
        if (this.f13024e) {
            this.f13025f.setFindListener(findListener);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.f13026g.setFindListener(new WebView.FindListener() { // from class: com.tencent.smtt.sdk.WebView.3
                @Override // android.webkit.WebView.FindListener
                public void onFindResultReceived(int i, int i2, boolean z) {
                    findListener.onFindResultReceived(i, i2, z);
                }
            });
        }
    }

    @TargetApi(3)
    public void findNext(boolean z) {
        if (!this.f13024e) {
            this.f13026g.findNext(z);
        } else {
            this.f13025f.findNext(z);
        }
    }

    @Deprecated
    public int findAll(String str) {
        if (this.f13024e) {
            return this.f13025f.findAll(str);
        }
        Object a = ReflectionUtils.m16404a(this.f13026g, "findAll", new Class[]{String.class}, str);
        if (a == null) {
            return 0;
        }
        return ((Integer) a).intValue();
    }

    public static String findAddress(String str) {
        if (!X5CoreEngine.m16621a().m16618b()) {
            return android.webkit.WebView.findAddress(str);
        }
        return null;
    }

    @TargetApi(16)
    public void findAllAsync(String str) {
        if (this.f13024e) {
            this.f13025f.findAllAsync(str);
        } else if (Build.VERSION.SDK_INT >= 16) {
            ReflectionUtils.m16404a(this.f13026g, "findAllAsync", new Class[]{String.class}, str);
        }
    }

    @TargetApi(3)
    public void clearMatches() {
        if (!this.f13024e) {
            this.f13026g.clearMatches();
        } else {
            this.f13025f.clearMatches();
        }
    }

    public void documentHasImages(Message message) {
        if (!this.f13024e) {
            this.f13026g.documentHasImages(message);
        } else {
            this.f13025f.documentHasImages(message);
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        SystemWebViewClient systemWebViewClient = null;
        SmttWebViewClient gVar = null;
        if (this.f13024e) {
            IX5WebViewBase iX5WebViewBase = this.f13025f;
            if (webViewClient != null) {
                gVar = new SmttWebViewClient(X5CoreEngine.m16621a().m16619a(true).m16570j(), this, webViewClient);
            }
            iX5WebViewBase.setWebViewClient(gVar);
        } else {
            C2605a aVar = this.f13026g;
            if (webViewClient != null) {
                systemWebViewClient = new SystemWebViewClient(this, webViewClient);
            }
            aVar.setWebViewClient(systemWebViewClient);
        }
        this.f13030n = webViewClient;
    }

    public void setWebViewCallbackClient(WebViewCallbackClient webViewCallbackClient) {
        this.mWebViewCallbackClient = webViewCallbackClient;
        if (this.f13024e && getX5WebViewExtension() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("flag", true);
            getX5WebViewExtension().invokeMiscMethod("setWebViewCallbackClientFlag", bundle);
        }
    }

    public void customDiskCachePathEnabled(boolean z, String str) {
        if (this.f13024e && getX5WebViewExtension() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("enabled", z);
            bundle.putString("path", str);
            getX5WebViewExtension().invokeMiscMethod("customDiskCachePathEnabled", bundle);
        }
    }

    public void setDownloadListener(final DownloadListener downloadListener) {
        boolean z = this.f13024e;
        if (!z) {
            this.f13026g.setDownloadListener(new DownloadListener() { // from class: com.tencent.smtt.sdk.WebView.4
                @Override // android.webkit.DownloadListener
                public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                    DownloadListener downloadListener2 = downloadListener;
                    if (downloadListener2 == null) {
                        ApplicationInfo applicationInfo = WebView.this.f13028i == null ? null : WebView.this.f13028i.getApplicationInfo();
                        if (applicationInfo == null || !TbsConfig.APP_WX.equals(applicationInfo.packageName)) {
                            MttLoader.m16877a(WebView.this.f13028i, str, null, null);
                            return;
                        }
                        return;
                    }
                    downloadListener2.onDownloadStart(str, str2, str3, str4, j);
                }
            });
        } else {
            this.f13025f.setDownloadListener(new DownLoadListenerAdapter(this, downloadListener, z));
        }
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        SystemWebChromeClient systemWebChromeClient = null;
        SmttWebChromeClient fVar = null;
        if (this.f13024e) {
            IX5WebViewBase iX5WebViewBase = this.f13025f;
            if (webChromeClient != null) {
                fVar = new SmttWebChromeClient(X5CoreEngine.m16621a().m16619a(true).m16573i(), this, webChromeClient);
            }
            iX5WebViewBase.setWebChromeClient(fVar);
        } else {
            C2605a aVar = this.f13026g;
            if (webChromeClient != null) {
                systemWebChromeClient = new SystemWebChromeClient(this, webChromeClient);
            }
            aVar.setWebChromeClient(systemWebChromeClient);
        }
        this.f13031o = webChromeClient;
    }

    public void setPictureListener(final PictureListener pictureListener) {
        if (!this.f13024e) {
            if (pictureListener == null) {
                this.f13026g.setPictureListener(null);
            } else {
                this.f13026g.setPictureListener(new WebView.PictureListener() { // from class: com.tencent.smtt.sdk.WebView.5
                    @Override // android.webkit.WebView.PictureListener
                    public void onNewPicture(android.webkit.WebView webView, Picture picture) {
                        WebView.this.m16931a(webView);
                        pictureListener.onNewPicture(WebView.this, picture);
                    }
                });
            }
        } else if (pictureListener == null) {
            this.f13025f.setPictureListener(null);
        } else {
            this.f13025f.setPictureListener(new IX5WebViewBase.PictureListener() { // from class: com.tencent.smtt.sdk.WebView.6
                @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
                public void onNewPictureIfHaveContent(IX5WebViewBase iX5WebViewBase, Picture picture) {
                }

                @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener
                public void onNewPicture(IX5WebViewBase iX5WebViewBase, Picture picture, boolean z) {
                    WebView.this.m16930a(iX5WebViewBase);
                    pictureListener.onNewPicture(WebView.this, picture);
                }
            });
        }
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (!this.f13024e) {
            this.f13026g.addJavascriptInterface(obj, str);
        } else {
            this.f13025f.addJavascriptInterface(obj, str);
        }
    }

    @TargetApi(11)
    public void removeJavascriptInterface(String str) {
        if (this.f13024e) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            ReflectionUtils.m16404a(this.f13026g, "removeJavascriptInterface", new Class[]{String.class}, str);
        } else {
            this.f13025f.removeJavascriptInterface(str);
        }
    }

    public WebSettings getSettings() {
        WebSettings webSettings = this.f13027h;
        if (webSettings != null) {
            return webSettings;
        }
        if (this.f13024e) {
            WebSettings webSettings2 = new WebSettings(this.f13025f.getSettings());
            this.f13027h = webSettings2;
            return webSettings2;
        }
        WebSettings webSettings3 = new WebSettings(this.f13026g.getSettings());
        this.f13027h = webSettings3;
        return webSettings3;
    }

    @Deprecated
    public static synchronized Object getPluginList() {
        synchronized (WebView.class) {
            if (X5CoreEngine.m16621a().m16618b()) {
                return null;
            }
            return ReflectionUtils.m16403a("android.webkit.WebView", "getPluginList");
        }
    }

    @Deprecated
    public void refreshPlugins(boolean z) {
        if (!this.f13024e) {
            ReflectionUtils.m16404a(this.f13026g, "refreshPlugins", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        } else {
            this.f13025f.refreshPlugins(z);
        }
    }

    @Deprecated
    public void setMapTrackballToArrowKeys(boolean z) {
        if (!this.f13024e) {
            ReflectionUtils.m16404a(this.f13026g, "setMapTrackballToArrowKeys", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        } else {
            this.f13025f.setMapTrackballToArrowKeys(z);
        }
    }

    public void flingScroll(int i, int i2) {
        if (!this.f13024e) {
            this.f13026g.flingScroll(i, i2);
        } else {
            this.f13025f.flingScroll(i, i2);
        }
    }

    @Deprecated
    public View getZoomControls() {
        if (!this.f13024e) {
            return (View) ReflectionUtils.m16406a(this.f13026g, "getZoomControls");
        }
        return this.f13025f.getZoomControls();
    }

    @Deprecated
    public boolean canZoomIn() {
        Object a;
        if (this.f13024e) {
            return this.f13025f.canZoomIn();
        }
        if (Build.VERSION.SDK_INT < 11 || (a = ReflectionUtils.m16406a(this.f13026g, "canZoomIn")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    public boolean isPrivateBrowsingEnabled() {
        Object a;
        if (this.f13024e) {
            return this.f13025f.isPrivateBrowsingEnable();
        }
        if (Build.VERSION.SDK_INT < 11 || (a = ReflectionUtils.m16406a(this.f13026g, "isPrivateBrowsingEnabled")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    @Deprecated
    public boolean canZoomOut() {
        Object a;
        if (this.f13024e) {
            return this.f13025f.canZoomOut();
        }
        if (Build.VERSION.SDK_INT < 11 || (a = ReflectionUtils.m16406a(this.f13026g, "canZoomOut")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    public boolean zoomIn() {
        if (!this.f13024e) {
            return this.f13026g.zoomIn();
        }
        return this.f13025f.zoomIn();
    }

    public boolean zoomOut() {
        if (!this.f13024e) {
            return this.f13026g.zoomOut();
        }
        return this.f13025f.zoomOut();
    }

    public void dumpViewHierarchyWithProperties(BufferedWriter bufferedWriter, int i) {
        if (!this.f13024e) {
            ReflectionUtils.m16404a(this.f13026g, "dumpViewHierarchyWithProperties", new Class[]{BufferedWriter.class, Integer.TYPE}, bufferedWriter, Integer.valueOf(i));
        } else {
            this.f13025f.dumpViewHierarchyWithProperties(bufferedWriter, i);
        }
    }

    public View findHierarchyView(String str, int i) {
        return !this.f13024e ? (View) ReflectionUtils.m16404a(this.f13026g, "findHierarchyView", new Class[]{String.class, Integer.TYPE}, str, Integer.valueOf(i)) : this.f13025f.findHierarchyView(str, i);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.f13024e) {
            this.f13026g.computeScroll();
        } else {
            this.f13025f.computeScroll();
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (!this.f13024e) {
            this.f13026g.setBackgroundColor(i);
        } else {
            this.f13025f.setBackgroundColor(i);
        }
        super.setBackgroundColor(i);
    }

    public View getView() {
        if (!this.f13024e) {
            return this.f13026g;
        }
        return this.f13025f.getView();
    }

    /* renamed from: a */
    protected void m16935a() {
        boolean z;
        Bundle sdkQBStatisticsInfo;
        if (!this.f13029k && this.f13022a != 0) {
            this.f13029k = true;
            String str = "";
            String str2 = "";
            String str3 = "";
            if (this.f13024e && (sdkQBStatisticsInfo = this.f13025f.getX5WebViewExtension().getSdkQBStatisticsInfo()) != null) {
                str = sdkQBStatisticsInfo.getString("guid");
                str2 = sdkQBStatisticsInfo.getString("qua2");
                str3 = sdkQBStatisticsInfo.getString("lc");
            }
            if (TbsConfig.APP_QZONE.equals(this.f13028i.getApplicationInfo().packageName)) {
                int d = m16921d(this.f13028i);
                if (d == -1) {
                    d = this.f13022a;
                }
                this.f13022a = d;
                m16919e(this.f13028i);
            }
            try {
                z = this.f13025f.getX5WebViewExtension().isX5CoreSandboxMode();
            } catch (Throwable th) {
                TbsLog.m16527w("tbsOnDetachedFromWindow", "exception: " + th);
                z = false;
            }
            HttpUtils.m16890a(this.f13028i, str, str2, str3, this.f13022a, this.f13024e, m16916h(), z);
            this.f13022a = 0;
            this.f13029k = false;
        }
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.tencent.smtt.sdk.WebView$7] */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        try {
            if ("com.xunmeng.pinduoduo".equals(this.f13028i.getApplicationInfo().packageName)) {
                new Thread("onDetachedFromWindow") { // from class: com.tencent.smtt.sdk.WebView.7
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            WebView.this.m16935a();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            } else {
                m16935a();
            }
        } catch (Throwable unused) {
            m16935a();
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        boolean z;
        Bundle sdkQBStatisticsInfo;
        Context context = this.f13028i;
        if (context == null) {
            super.onVisibilityChanged(view, i);
            return;
        }
        if (f13019p == null) {
            f13019p = context.getApplicationInfo().packageName;
        }
        String str = f13019p;
        if (str == null || (!str.equals(TbsConfig.APP_WX) && !f13019p.equals(TbsConfig.APP_QQ))) {
            if (!(i == 0 || this.f13029k || this.f13022a == 0)) {
                this.f13029k = true;
                String str2 = "";
                String str3 = "";
                String str4 = "";
                if (this.f13024e && (sdkQBStatisticsInfo = this.f13025f.getX5WebViewExtension().getSdkQBStatisticsInfo()) != null) {
                    str2 = sdkQBStatisticsInfo.getString("guid");
                    str3 = sdkQBStatisticsInfo.getString("qua2");
                    str4 = sdkQBStatisticsInfo.getString("lc");
                }
                if (TbsConfig.APP_QZONE.equals(this.f13028i.getApplicationInfo().packageName)) {
                    int d = m16921d(this.f13028i);
                    if (d == -1) {
                        d = this.f13022a;
                    }
                    this.f13022a = d;
                    m16919e(this.f13028i);
                }
                try {
                    z = this.f13025f.getX5WebViewExtension().isX5CoreSandboxMode();
                } catch (Throwable th) {
                    TbsLog.m16527w("onVisibilityChanged", "exception: " + th);
                    z = false;
                }
                HttpUtils.m16890a(this.f13028i, str2, str3, str4, this.f13022a, this.f13024e, m16916h(), z);
                this.f13022a = 0;
                this.f13029k = false;
            }
            super.onVisibilityChanged(view, i);
            return;
        }
        super.onVisibilityChanged(view, i);
    }

    public IX5WebViewExtension getX5WebViewExtension() {
        if (!this.f13024e) {
            return null;
        }
        return this.f13025f.getX5WebViewExtension();
    }

    public IX5WebSettingsExtension getSettingsExtension() {
        if (!this.f13024e) {
            return null;
        }
        return this.f13025f.getX5WebViewExtension().getSettingsExtension();
    }

    public void setWebViewClientExtension(IX5WebViewClientExtension iX5WebViewClientExtension) {
        if (this.f13024e) {
            this.f13025f.getX5WebViewExtension().setWebViewClientExtension(iX5WebViewClientExtension);
        }
    }

    public void setWebChromeClientExtension(IX5WebChromeClientExtension iX5WebChromeClientExtension) {
        if (this.f13024e) {
            this.f13025f.getX5WebViewExtension().setWebChromeClientExtension(iX5WebChromeClientExtension);
        }
    }

    public IX5WebChromeClientExtension getWebChromeClientExtension() {
        if (!this.f13024e) {
            return null;
        }
        return this.f13025f.getX5WebViewExtension().getWebChromeClientExtension();
    }

    public IX5WebViewClientExtension getWebViewClientExtension() {
        if (!this.f13024e) {
            return null;
        }
        return this.f13025f.getX5WebViewExtension().getWebViewClientExtension();
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (this.f13024e) {
            try {
                Method a = ReflectionUtils.m16405a(this.f13025f.getView(), "evaluateJavascript", String.class, ValueCallback.class);
                a.setAccessible(true);
                a.invoke(this.f13025f.getView(), str, valueCallback);
            } catch (Exception e) {
                e.printStackTrace();
                loadUrl(str);
            }
        } else if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.webkit.WebView").getDeclaredMethod("evaluateJavascript", String.class, ValueCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.f13026g, str, valueCallback);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static int getTbsCoreVersion(Context context) {
        return QbSdk.getTbsVersion(context);
    }

    public boolean setVideoFullScreen(Context context, boolean z) {
        if (!context.getApplicationInfo().processName.contains("com.tencent.android.qqdownloader") || this.f13025f == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt("DefaultVideoScreen", 2);
        } else {
            bundle.putInt("DefaultVideoScreen", 1);
        }
        this.f13025f.getX5WebViewExtension().invokeMiscMethod("setVideoParams", bundle);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16931a(android.webkit.WebView webView) {
        boolean z = this.f13024e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public android.webkit.WebView m16927b() {
        if (!this.f13024e) {
            return this.f13026g;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16930a(IX5WebViewBase iX5WebViewBase) {
        this.f13025f = iX5WebViewBase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public IX5WebViewBase m16924c() {
        return this.f13025f;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        getView().setOnTouchListener(onTouchListener);
    }

    /* renamed from: a */
    private void m16933a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            try {
                int attributeCount = attributeSet.getAttributeCount();
                for (int i = 0; i < attributeCount; i++) {
                    if (attributeSet.getAttributeName(i).equalsIgnoreCase("scrollbars")) {
                        int[] intArray = getResources().getIntArray(16842974);
                        int attributeIntValue = attributeSet.getAttributeIntValue(i, -1);
                        if (attributeIntValue == intArray[1]) {
                            this.f13025f.getView().setVerticalScrollBarEnabled(false);
                            this.f13025f.getView().setHorizontalScrollBarEnabled(false);
                        } else if (attributeIntValue == intArray[2]) {
                            this.f13025f.getView().setVerticalScrollBarEnabled(false);
                        } else if (attributeIntValue == intArray[3]) {
                            this.f13025f.getView().setHorizontalScrollBarEnabled(false);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.tencent.smtt.sdk.WebView$a */
    /* loaded from: classes2.dex */
    public class C2605a extends android.webkit.WebView {
        public C2605a(WebView webView, Context context) {
            this(context, null);
        }

        public C2605a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            if (!QbSdk.getIsSysWebViewForcedByOuter() || !TbsShareManager.isThirdPartyApp(context)) {
                CookieSyncManager.createInstance(WebView.this.f13028i).startSync();
                try {
                    Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
                    declaredMethod.setAccessible(true);
                    ((Handler) declaredMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new SQLiteUncaughtExceptionHandler());
                    WebView.mSysWebviewCreated = true;
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.invalidate();
            }
        }

        @Override // android.webkit.WebView
        public WebSettings getSettings() {
            try {
                return super.getSettings();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // android.webkit.WebView, android.view.View
        protected void onScrollChanged(int i, int i2, int i3, int i4) {
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.onScrollChanged(i, i2, i3, i4, this);
                return;
            }
            super.onScrollChanged(i, i2, i3, i4);
            WebView.this.onScrollChanged(i, i2, i3, i4);
        }

        /* renamed from: a */
        public void m16914a(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
        }

        @Override // android.webkit.WebView, android.view.View
        public void computeScroll() {
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.computeScroll(this);
            } else {
                super.computeScroll();
            }
        }

        /* renamed from: a */
        public void m16915a() {
            super.computeScroll();
        }

        @Override // android.webkit.WebView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!hasFocus()) {
                requestFocus();
            }
            if (WebView.this.mWebViewCallbackClient != null) {
                return WebView.this.mWebViewCallbackClient.onTouchEvent(motionEvent, this);
            }
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /* renamed from: a */
        public boolean m16911a(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
                if (!WebView.f13021w && WebView.f13020v != null) {
                    canvas.save();
                    canvas.drawPaint(WebView.f13020v);
                    canvas.restore();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.view.View
        @TargetApi(9)
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            if (WebView.this.mWebViewCallbackClient != null) {
                return WebView.this.mWebViewCallbackClient.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z, this);
            }
            if (Build.VERSION.SDK_INT >= 9) {
                return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            }
            return false;
        }

        @TargetApi(9)
        /* renamed from: a */
        public boolean m16913a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            if (Build.VERSION.SDK_INT >= 9) {
                return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            }
            return false;
        }

        @Override // android.webkit.WebView, android.view.View
        @TargetApi(9)
        public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.onOverScrolled(i, i2, z, z2, this);
            } else if (Build.VERSION.SDK_INT >= 9) {
                super.onOverScrolled(i, i2, z, z2);
            }
        }

        @TargetApi(9)
        /* renamed from: a */
        public void m16912a(int i, int i2, boolean z, boolean z2) {
            if (Build.VERSION.SDK_INT >= 9) {
                super.onOverScrolled(i, i2, z, z2);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (WebView.this.mWebViewCallbackClient != null) {
                return WebView.this.mWebViewCallbackClient.dispatchTouchEvent(motionEvent, this);
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        /* renamed from: b */
        public boolean m16910b(MotionEvent motionEvent) {
            return super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (WebView.this.mWebViewCallbackClient != null) {
                return WebView.this.mWebViewCallbackClient.onInterceptTouchEvent(motionEvent, this);
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        /* renamed from: c */
        public boolean m16909c(MotionEvent motionEvent) {
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.webkit.WebView, android.view.View
        public void setOverScrollMode(int i) {
            try {
                super.setOverScrollMode(i);
            } catch (Exception unused) {
            }
        }
    }

    public void switchNightMode(boolean z) {
        if (z != f13021w) {
            f13021w = z;
            if (f13021w) {
                TbsLog.m16533e("QB_SDK", "deleteNightMode");
                loadUrl("javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));");
                return;
            }
            TbsLog.m16533e("QB_SDK", "nightMode");
            loadUrl("javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);");
        }
    }

    public void switchToNightMode() {
        TbsLog.m16533e("QB_SDK", "switchToNightMode 01");
        if (!f13021w) {
            TbsLog.m16533e("QB_SDK", "switchToNightMode");
            loadUrl("javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);");
        }
    }

    public static synchronized void setSysDayOrNight(boolean z) {
        synchronized (WebView.class) {
            if (z != f13021w) {
                f13021w = z;
                if (f13020v == null) {
                    f13020v = new Paint();
                    f13020v.setColor(-16777216);
                }
                if (!z) {
                    if (f13020v.getAlpha() != NIGHT_MODE_ALPHA) {
                        f13020v.setAlpha(NIGHT_MODE_ALPHA);
                    }
                } else if (f13020v.getAlpha() != 255) {
                    f13020v.setAlpha(255);
                }
            }
        }
    }

    public void setDayOrNight(boolean z) {
        try {
            if (this.f13024e) {
                getSettingsExtension().setDayOrNight(z);
            }
            setSysDayOrNight(z);
            getView().postInvalidate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setARModeEnable(boolean z) {
        try {
            if (this.f13024e) {
                getSettingsExtension().setARModeEnable(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isDayMode() {
        return f13021w;
    }

    public int getSysNightModeAlpha() {
        return NIGHT_MODE_ALPHA;
    }

    public void setSysNightModeAlpha(int i) {
        NIGHT_MODE_ALPHA = i;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        View.OnLongClickListener onLongClickListener = this.f13038y;
        if (onLongClickListener == null) {
            return m16932a(view);
        }
        if (!onLongClickListener.onLongClick(view)) {
            return m16932a(view);
        }
        return true;
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (!this.f13024e) {
            this.f13026g.setOnLongClickListener(onLongClickListener);
            return;
        }
        View view = this.f13025f.getView();
        try {
            if (this.f13037x == null) {
                Method a = ReflectionUtils.m16405a(view, "getListenerInfo", new Class[0]);
                a.setAccessible(true);
                Object invoke = a.invoke(view, null);
                Field declaredField = invoke.getClass().getDeclaredField("mOnLongClickListener");
                declaredField.setAccessible(true);
                this.f13037x = declaredField.get(invoke);
            }
            this.f13038y = onLongClickListener;
            getView().setOnLongClickListener(this);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: d */
    private int m16921d(Context context) {
        FileLock a;
        Throwable th;
        Exception e;
        String str;
        StringBuilder sb;
        FileOutputStream b = FileUtil.m16445b(context, true, "tbslock.txt");
        if (b == null || (a = FileUtil.m16464a(context, b)) == null) {
            return -1;
        }
        if (f13014c.tryLock()) {
            FileInputStream fileInputStream = null;
            try {
                try {
                    File dir = context.getDir("tbs", 0);
                    File file = new File(dir + File.separator + "core_private", "pv.db");
                    if (file.exists()) {
                        Properties properties = new Properties();
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        try {
                            properties.load(fileInputStream2);
                            fileInputStream2.close();
                            String property = properties.getProperty("PV");
                            if (property == null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2) {
                                    e = e2;
                                    str = "getTbsCorePV";
                                    sb = new StringBuilder();
                                    sb.append("TbsInstaller--getTbsCorePV IOException=");
                                    sb.append(e.toString());
                                    TbsLog.m16533e(str, sb.toString());
                                    f13014c.unlock();
                                    FileUtil.m16448a(a, b);
                                    return -1;
                                }
                            } else {
                                int parseInt = Integer.parseInt(property);
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e3) {
                                    TbsLog.m16533e("getTbsCorePV", "TbsInstaller--getTbsCorePV IOException=" + e3.toString());
                                }
                                f13014c.unlock();
                                FileUtil.m16448a(a, b);
                                return parseInt;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            fileInputStream = fileInputStream2;
                            TbsLog.m16533e("getTbsCorePV", "TbsInstaller--getTbsCorePV Exception=" + e.toString());
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e5) {
                                    e = e5;
                                    str = "getTbsCorePV";
                                    sb = new StringBuilder();
                                    sb.append("TbsInstaller--getTbsCorePV IOException=");
                                    sb.append(e.toString());
                                    TbsLog.m16533e(str, sb.toString());
                                    f13014c.unlock();
                                    FileUtil.m16448a(a, b);
                                    return -1;
                                }
                            }
                            f13014c.unlock();
                            FileUtil.m16448a(a, b);
                            return -1;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e6) {
                                    TbsLog.m16533e("getTbsCorePV", "TbsInstaller--getTbsCorePV IOException=" + e6.toString());
                                }
                            }
                            f13014c.unlock();
                            FileUtil.m16448a(a, b);
                            throw th;
                        }
                    }
                } catch (Exception e7) {
                    e = e7;
                }
                f13014c.unlock();
                FileUtil.m16448a(a, b);
                return -1;
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            FileUtil.m16448a(a, b);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16934a(Context context) {
        int d;
        String str;
        if (m16921d(context) != -1) {
            str = "PV=" + String.valueOf(d + 1);
        } else {
            str = "PV=1";
        }
        File file = new File(context.getDir("tbs", 0) + File.separator + "core_private", "pv.db");
        try {
            file.getParentFile().mkdirs();
            if (!file.isFile() || !file.exists()) {
                file.createNewFile();
            }
            f13015d = new FileOutputStream(file, false);
            f13015d.write(str.getBytes());
            if (f13015d != null) {
                f13015d.flush();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: e */
    private void m16919e(Context context) {
        try {
            File dir = context.getDir("tbs", 0);
            File file = new File(dir + File.separator + "core_private", "pv.db");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            TbsLog.m16531i("getTbsCorePV", "TbsInstaller--getTbsCorePV Exception=" + e.toString());
        }
    }

    /* renamed from: a */
    private boolean m16932a(View view) {
        Object a;
        Context context = this.f13028i;
        if ((context == null || getTbsCoreVersion(context) <= 36200) && (a = ReflectionUtils.m16404a(this.f13037x, "onLongClick", new Class[]{View.class}, view)) != null) {
            return ((Boolean) a).booleanValue();
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (!this.f13024e) {
            this.f13026g.addView(view);
            return;
        }
        View view2 = this.f13025f.getView();
        try {
            Method a = ReflectionUtils.m16405a(view2, "addView", View.class);
            a.setAccessible(true);
            a.invoke(view2, view);
        } catch (Throwable unused) {
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (!this.f13024e) {
            this.f13026g.removeView(view);
            return;
        }
        View view2 = this.f13025f.getView();
        try {
            Method a = ReflectionUtils.m16405a(view2, "removeView", View.class);
            a.setAccessible(true);
            a.invoke(view2, view);
        } catch (Throwable unused) {
        }
    }

    public static String getCrashExtraMessage(Context context) {
        if (context == null) {
            return "";
        }
        String str = "tbs_core_version:" + QbSdk.getTbsVersionForCrash(context) + C4963cj.f20745b + "tbs_sdk_version:43697" + C4963cj.f20745b;
        boolean z = false;
        if (TbsConfig.APP_WX.equals(context.getApplicationInfo().packageName)) {
            try {
                Class.forName("de.robv.android.xposed.XposedBridge");
                z = true;
            } catch (ClassNotFoundException unused) {
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (z) {
            return str + "isXposed=true;";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SDKEngine.m16828a(true).m16822e());
        sb.append("\n");
        sb.append(str);
        if (!TbsShareManager.isThirdPartyApp(context) && QbSdk.f12802n != null && QbSdk.f12802n.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) && QbSdk.f12802n.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY)) {
            sb.append("\n");
            sb.append("weapp_id:" + QbSdk.f12802n.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) + C4963cj.f20745b + TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY + ":" + QbSdk.f12802n.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY) + C4963cj.f20745b);
        }
        if (sb.length() > 8192) {
            return sb.substring(sb.length() - 8192);
        }
        return sb.toString();
    }

    public static boolean getTbsNeedReboot() {
        m16922d();
        return SDKEngine.m16828a(true).m16821f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static void m16922d() {
        try {
            new Thread(new Runnable() { // from class: com.tencent.smtt.sdk.WebView.8
                @Override // java.lang.Runnable
                public void run() {
                    if (WebView.f13016j == null) {
                        TbsLog.m16535d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--mAppContext == null");
                        return;
                    }
                    SDKEngine a = SDKEngine.m16828a(true);
                    if (SDKEngine.f13138b) {
                        TbsLog.m16535d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--needReboot = true");
                        return;
                    }
                    TbsCoreVerManager a2 = TbsCoreVerManager.m16764a(WebView.f13016j);
                    int c = a2.m16756c();
                    TbsLog.m16535d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--installStatus = " + c);
                    if (c == 2) {
                        TbsLog.m16535d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--install setTbsNeedReboot true");
                        a.m16829a(String.valueOf(a2.m16760b()));
                        a.m16825b(true);
                        return;
                    }
                    int b = a2.m16757b("copy_status");
                    TbsLog.m16535d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copyStatus = " + b);
                    if (b == 1) {
                        TbsLog.m16535d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copy setTbsNeedReboot true");
                        a.m16829a(String.valueOf(a2.m16753c("copy_core_ver")));
                        a.m16825b(true);
                    } else if (X5CoreEngine.m16621a().m16618b()) {
                    } else {
                        if (c == 3 || b == 3) {
                            TbsLog.m16535d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--setTbsNeedReboot true");
                            a.m16829a(String.valueOf(SDKEngine.m16823d()));
                            a.m16825b(true);
                        }
                    }
                }
            }).start();
        } catch (Throwable th) {
            TbsLog.m16533e("webview", "updateRebootStatus excpetion: " + th);
        }
    }

    public void super_onScrollChanged(int i, int i2, int i3, int i4) {
        if (!this.f13024e) {
            this.f13026g.m16914a(i, i2, i3, i4);
            return;
        }
        try {
            ReflectionUtils.m16404a(this.f13025f.getView(), "super_onScrollChanged", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        if (!this.f13024e) {
            return this.f13026g.m16913a(i, i2, i3, i4, i5, i6, i7, i8, z);
        }
        try {
            Object a = ReflectionUtils.m16404a(this.f13025f.getView(), "super_overScrollBy", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE}, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), Boolean.valueOf(z));
            if (a == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void super_onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (!this.f13024e) {
            this.f13026g.m16912a(i, i2, z, z2);
            return;
        }
        try {
            ReflectionUtils.m16404a(this.f13025f.getView(), "super_onOverScrolled", new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE}, Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z), Boolean.valueOf(z2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.f13024e) {
            return this.f13026g.m16910b(motionEvent);
        }
        try {
            Object a = ReflectionUtils.m16404a(this.f13025f.getView(), "super_dispatchTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
            if (a == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean super_onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f13024e) {
            return this.f13026g.m16909c(motionEvent);
        }
        try {
            Object a = ReflectionUtils.m16404a(this.f13025f.getView(), "super_onInterceptTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
            if (a == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean super_onTouchEvent(MotionEvent motionEvent) {
        if (!this.f13024e) {
            return this.f13026g.m16911a(motionEvent);
        }
        try {
            Object a = ReflectionUtils.m16404a(this.f13025f.getView(), "super_onTouchEvent", new Class[]{MotionEvent.class}, motionEvent);
            if (a == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void super_computeScroll() {
        if (!this.f13024e) {
            this.f13026g.m16915a();
            return;
        }
        try {
            ReflectionUtils.m16406a(this.f13025f.getView(), "super_computeScroll");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View
    public int getScrollBarDefaultDelayBeforeFade() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarDefaultDelayBeforeFade();
    }

    @Override // android.view.View
    public int getScrollBarFadeDuration() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarFadeDuration();
    }

    @Override // android.view.View
    public int getScrollBarSize() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarSize();
    }

    @Override // android.view.View
    public int getScrollBarStyle() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarStyle();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (getView() != null) {
            getView().setVisibility(i);
        }
    }
}
