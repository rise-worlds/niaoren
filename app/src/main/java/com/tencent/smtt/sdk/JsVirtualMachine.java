package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class JsVirtualMachine {

    /* renamed from: a */
    private final Context f12773a;

    /* renamed from: b */
    private final IX5JsVirtualMachine f12774b;

    /* renamed from: c */
    private final HashSet<WeakReference<C2550a>> f12775c;

    /* renamed from: com.tencent.smtt.sdk.JsVirtualMachine$a */
    /* loaded from: classes2.dex */
    private static class C2550a implements IX5JsContext {

        /* renamed from: a */
        private WebView f12776a;

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public byte[] getNativeBuffer(int i) {
            return null;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public int getNativeBufferId() {
            return -1;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setExceptionHandler(ValueCallback<IX5JsError> valueCallback) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setName(String str) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public int setNativeBuffer(int i, byte[] bArr) {
            return -1;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setPerContextData(Object obj) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void stealValueFromOtherCtx(String str, IX5JsContext iX5JsContext, String str2) {
        }

        public C2550a(Context context) {
            this.f12776a = new WebView(context);
            this.f12776a.getSettings().setJavaScriptEnabled(true);
        }

        /* renamed from: a */
        public void m17065a() {
            WebView webView = this.f12776a;
            if (webView != null) {
                webView.onPause();
            }
        }

        /* renamed from: b */
        public void m17064b() {
            WebView webView = this.f12776a;
            if (webView != null) {
                webView.onResume();
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void addJavascriptInterface(Object obj, String str) {
            WebView webView = this.f12776a;
            if (webView != null) {
                webView.addJavascriptInterface(obj, str);
                this.f12776a.loadUrl("about:blank");
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void destroy() {
            WebView webView = this.f12776a;
            if (webView != null) {
                webView.clearHistory();
                this.f12776a.clearCache(true);
                this.f12776a.loadUrl("about:blank");
                this.f12776a.freeMemory();
                this.f12776a.pauseTimers();
                this.f12776a.destroy();
                this.f12776a = null;
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void evaluateJavascript(String str, final ValueCallback<String> valueCallback, URL url) {
            WebView webView = this.f12776a;
            if (webView != null) {
                webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() { // from class: com.tencent.smtt.sdk.JsVirtualMachine.a.1
                    /* renamed from: a */
                    public void onReceiveValue(String str2) {
                        valueCallback.onReceiveValue(str2);
                    }
                });
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public IX5JsValue evaluateScript(String str, URL url) {
            WebView webView = this.f12776a;
            if (webView == null) {
                return null;
            }
            webView.evaluateJavascript(str, null);
            return null;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void evaluateScriptAsync(String str, final ValueCallback<IX5JsValue> valueCallback, URL url) {
            WebView webView = this.f12776a;
            if (webView != null) {
                webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() { // from class: com.tencent.smtt.sdk.JsVirtualMachine.a.2
                    /* renamed from: a */
                    public void onReceiveValue(String str2) {
                        valueCallback.onReceiveValue(null);
                    }
                });
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void removeJavascriptInterface(String str) {
            WebView webView = this.f12776a;
            if (webView != null) {
                webView.removeJavascriptInterface(str);
            }
        }
    }

    public JsVirtualMachine(Context context) {
        this(context, null);
    }

    public JsVirtualMachine(Context context, Looper looper) {
        this.f12775c = new HashSet<>();
        this.f12773a = context;
        this.f12774b = X5JsCore.m16906a(context, looper);
    }

    public boolean isFallback() {
        return this.f12774b == null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public IX5JsContext m17066a() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f12774b;
        if (iX5JsVirtualMachine != null) {
            return iX5JsVirtualMachine.createJsContext();
        }
        C2550a aVar = new C2550a(this.f12773a);
        this.f12775c.add(new WeakReference<>(aVar));
        return aVar;
    }

    public void destroy() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f12774b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.destroy();
            return;
        }
        Iterator<WeakReference<C2550a>> it = this.f12775c.iterator();
        while (it.hasNext()) {
            WeakReference<C2550a> next = it.next();
            if (next.get() != null) {
                next.get().destroy();
            }
        }
    }

    public Looper getLooper() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f12774b;
        if (iX5JsVirtualMachine != null) {
            return iX5JsVirtualMachine.getLooper();
        }
        return Looper.myLooper();
    }

    public void onPause() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f12774b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onPause();
            return;
        }
        Iterator<WeakReference<C2550a>> it = this.f12775c.iterator();
        while (it.hasNext()) {
            WeakReference<C2550a> next = it.next();
            if (next.get() != null) {
                next.get().m17065a();
            }
        }
    }

    public void onResume() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f12774b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onResume();
            return;
        }
        Iterator<WeakReference<C2550a>> it = this.f12775c.iterator();
        while (it.hasNext()) {
            WeakReference<C2550a> next = it.next();
            if (next.get() != null) {
                next.get().m17064b();
            }
        }
    }
}
