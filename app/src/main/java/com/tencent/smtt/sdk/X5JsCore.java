package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class X5JsCore {

    /* renamed from: a */
    private static EnumC2606a f13058a = EnumC2606a.UNINITIALIZED;

    /* renamed from: b */
    private static EnumC2606a f13059b = EnumC2606a.UNINITIALIZED;

    /* renamed from: c */
    private static EnumC2606a f13060c = EnumC2606a.UNINITIALIZED;

    /* renamed from: d */
    private final Context f13061d;

    /* renamed from: e */
    private Object f13062e;

    /* renamed from: f */
    private WebView f13063f;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.tencent.smtt.sdk.X5JsCore$a */
    /* loaded from: classes2.dex */
    public enum EnumC2606a {
        UNINITIALIZED,
        UNAVAILABLE,
        AVAILABLE
    }

    /* renamed from: a */
    private static Object m16905a(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            X5CoreEngine a = X5CoreEngine.m16621a();
            if (a != null && a.m16618b()) {
                return a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", str, clsArr, objArr);
            }
            Log.e("X5JsCore", "X5Jscore#" + str + " - x5CoreEngine is null or is not x5core.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean canUseX5JsCoreNewAPI(Context context) {
        if (f13060c != EnumC2606a.UNINITIALIZED) {
            return f13060c == EnumC2606a.AVAILABLE;
        }
        f13060c = EnumC2606a.UNAVAILABLE;
        Object a = m16905a("canUseX5JsCoreNewAPI", new Class[]{Context.class}, context);
        if (a == null || !(a instanceof Boolean) || !((Boolean) a).booleanValue()) {
            return false;
        }
        f13060c = EnumC2606a.AVAILABLE;
        return true;
    }

    public static boolean canUseX5JsCore(Context context) {
        if (f13058a != EnumC2606a.UNINITIALIZED) {
            return f13058a == EnumC2606a.AVAILABLE;
        }
        f13058a = EnumC2606a.UNAVAILABLE;
        Object a = m16905a("canUseX5JsCore", new Class[]{Context.class}, context);
        if (a == null || !(a instanceof Boolean) || !((Boolean) a).booleanValue()) {
            return false;
        }
        m16905a("setJsValueFactory", new Class[]{Object.class}, JsValue.m17069a());
        f13058a = EnumC2606a.AVAILABLE;
        return true;
    }

    public static boolean canX5JsCoreUseNativeBuffer(Context context) {
        Object a;
        if (f13059b != EnumC2606a.UNINITIALIZED) {
            return f13059b == EnumC2606a.AVAILABLE;
        }
        f13059b = EnumC2606a.UNAVAILABLE;
        if (!canUseX5JsCore(context) || (a = m16905a("canX5JsCoreUseBuffer", new Class[]{Context.class}, context)) == null || !(a instanceof Boolean) || !((Boolean) a).booleanValue()) {
            return false;
        }
        f13059b = EnumC2606a.AVAILABLE;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static IX5JsVirtualMachine m16906a(Context context, Looper looper) {
        Object a;
        if (canUseX5JsCore(context) && (a = m16905a("createX5JsVirtualMachine", new Class[]{Context.class, Looper.class}, context, looper)) != null) {
            return (IX5JsVirtualMachine) a;
        }
        Log.e("X5JsCore", "X5JsCore#createVirtualMachine failure!");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static Object m16907a() {
        return m16905a("currentContextData", new Class[0], new Object[0]);
    }

    @Deprecated
    public X5JsCore(Context context) {
        Object a;
        this.f13062e = null;
        this.f13063f = null;
        this.f13061d = context;
        if (!canUseX5JsCore(context) || (a = m16905a("createX5JavaBridge", new Class[]{Context.class}, context)) == null) {
            Log.e("X5JsCore", "X5JsCore create X5JavaBridge failure, use fallback!");
            this.f13063f = new WebView(context);
            this.f13063f.getSettings().setJavaScriptEnabled(true);
            return;
        }
        this.f13062e = a;
    }

    @Deprecated
    public void addJavascriptInterface(Object obj, String str) {
        Object obj2 = this.f13062e;
        if (obj2 != null) {
            m16905a("addJavascriptInterface", new Class[]{Object.class, String.class, Object.class}, obj, str, obj2);
            return;
        }
        WebView webView = this.f13063f;
        if (webView != null) {
            webView.addJavascriptInterface(obj, str);
            this.f13063f.loadUrl("about:blank");
        }
    }

    @Deprecated
    public void removeJavascriptInterface(String str) {
        Object obj = this.f13062e;
        if (obj != null) {
            m16905a("removeJavascriptInterface", new Class[]{String.class, Object.class}, str, obj);
            return;
        }
        WebView webView = this.f13063f;
        if (webView != null) {
            webView.removeJavascriptInterface(str);
        }
    }

    @Deprecated
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        Object obj = this.f13062e;
        if (obj != null) {
            m16905a("evaluateJavascript", new Class[]{String.class, ValueCallback.class, Object.class}, str, valueCallback, obj);
            return;
        }
        WebView webView = this.f13063f;
        if (webView != null) {
            webView.evaluateJavascript(str, valueCallback);
        }
    }

    @Deprecated
    public void pauseTimers() {
        Object obj = this.f13062e;
        if (obj != null) {
            m16905a("pauseTimers", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public void resumeTimers() {
        Object obj = this.f13062e;
        if (obj != null) {
            m16905a("resumeTimers", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public void pause() {
        Object obj = this.f13062e;
        if (obj != null) {
            m16905a("pause", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public void resume() {
        Object obj = this.f13062e;
        if (obj != null) {
            m16905a("resume", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public int getNativeBufferId() {
        Object a;
        if (this.f13062e == null || !canX5JsCoreUseNativeBuffer(this.f13061d) || (a = m16905a("getNativeBufferId", new Class[]{Object.class}, this.f13062e)) == null || !(a instanceof Integer)) {
            return -1;
        }
        return ((Integer) a).intValue();
    }

    @Deprecated
    public void setNativeBuffer(int i, ByteBuffer byteBuffer) {
        if (this.f13062e != null && canX5JsCoreUseNativeBuffer(this.f13061d)) {
            m16905a("setNativeBuffer", new Class[]{Object.class, Integer.TYPE, ByteBuffer.class}, this.f13062e, Integer.valueOf(i), byteBuffer);
        }
    }

    @Deprecated
    public ByteBuffer getNativeBuffer(int i) {
        Object a;
        if (this.f13062e == null || !canX5JsCoreUseNativeBuffer(this.f13061d) || (a = m16905a("getNativeBuffer", new Class[]{Object.class, Integer.TYPE}, this.f13062e, Integer.valueOf(i))) == null || !(a instanceof ByteBuffer)) {
            return null;
        }
        return (ByteBuffer) a;
    }

    @Deprecated
    public void destroy() {
        Object obj = this.f13062e;
        if (obj != null) {
            m16905a("destroyX5JsCore", new Class[]{Object.class}, obj);
            this.f13062e = null;
            return;
        }
        WebView webView = this.f13063f;
        if (webView != null) {
            webView.clearHistory();
            this.f13063f.clearCache(true);
            this.f13063f.loadUrl("about:blank");
            this.f13063f.freeMemory();
            this.f13063f.pauseTimers();
            this.f13063f.destroy();
            this.f13063f = null;
        }
    }
}
