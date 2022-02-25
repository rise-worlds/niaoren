package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.ImageView;
import com.alipay.sdk.app.C0663l;
import com.alipay.sdk.widget.C0682p;
import java.util.Map;
import org.apache.http.HttpHost;
import org.json.JSONObject;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C3876ar;
import p110z1.C4745bt;
import p110z1.C4985cm;
import p110z1.C5019co;

/* renamed from: com.alipay.sdk.widget.j */
/* loaded from: classes.dex */
public class C0675j extends AbstractC0672g implements C0682p.AbstractC0683a, C0682p.AbstractC0684b, C0682p.AbstractC0685c {

    /* renamed from: b */
    public static final String f364b = "alipayjsbridge://";

    /* renamed from: c */
    public static final String f365c = "onBack";

    /* renamed from: d */
    public static final String f366d = "setTitle";

    /* renamed from: e */
    public static final String f367e = "onRefresh";

    /* renamed from: f */
    public static final String f368f = "showBackButton";

    /* renamed from: g */
    public static final String f369g = "onExit";

    /* renamed from: h */
    public static final String f370h = "onLoadJs";

    /* renamed from: i */
    public static final String f371i = "callNativeFunc";

    /* renamed from: j */
    public static final String f372j = "back";

    /* renamed from: k */
    public static final String f373k = "title";

    /* renamed from: l */
    public static final String f374l = "refresh";

    /* renamed from: m */
    public static final String f375m = "backButton";

    /* renamed from: n */
    public static final String f376n = "refreshButton";

    /* renamed from: o */
    public static final String f377o = "exit";

    /* renamed from: p */
    public static final String f378p = "action";

    /* renamed from: q */
    public static final String f379q = "pushWindow";

    /* renamed from: r */
    public static final String f380r = "h5JsFuncCallback";

    /* renamed from: s */
    private static final String f381s = "sdk_result_code:";

    /* renamed from: w */
    private final C4745bt f385w;

    /* renamed from: t */
    private boolean f382t = true;

    /* renamed from: u */
    private String f383u = "GET";

    /* renamed from: v */
    private boolean f384v = false;

    /* renamed from: x */
    private C0682p f386x = null;

    /* renamed from: y */
    private C0690u f387y = new C0690u();

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f384v) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public C0675j(Activity activity, C4745bt btVar) {
        super(activity);
        this.f385w = btVar;
        m25244c();
    }

    /* renamed from: c */
    private boolean m25244c() {
        try {
            this.f386x = new C0682p(this.f360a, this.f385w);
            this.f386x.setChromeProxy(this);
            this.f386x.setWebClientProxy(this);
            this.f386x.setWebEventProxy(this);
            addView(this.f386x);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: d */
    private void m25243d() {
        if (this.f382t) {
            this.f360a.finish();
        } else {
            this.f386x.m25235a("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
        }
    }

    /* renamed from: e */
    private void m25242e() {
        WebView webView = this.f386x.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        C0690u uVar = this.f387y;
        if (uVar == null || uVar.m25215b()) {
            m25249a(false);
        } else {
            m25241f();
        }
    }

    /* renamed from: a */
    public void m25250a(String str, String str2, boolean z) {
        this.f383u = str2;
        this.f386x.getTitle().setText(str);
        this.f382t = z;
    }

    /* renamed from: a */
    private void m25249a(boolean z) {
        C0663l.m25284a(z);
        this.f360a.finish();
    }

    @Override // com.alipay.sdk.widget.AbstractC0672g
    /* renamed from: a */
    public void mo25252a(String str) {
        if ("POST".equals(this.f383u)) {
            this.f386x.m25234a(str, (byte[]) null);
        } else {
            this.f386x.m25235a(str);
        }
    }

    @Override // com.alipay.sdk.widget.AbstractC0672g
    /* renamed from: a */
    public void mo25255a() {
        this.f386x.m25240a();
        this.f387y.m25214c();
    }

    @Override // com.alipay.sdk.widget.AbstractC0672g
    /* renamed from: b */
    public boolean mo25248b() {
        if (this.f384v) {
            return true;
        }
        m25243d();
        return true;
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0683a
    /* renamed from: a */
    public boolean mo25224a(C0682p pVar, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith("<head>") && str2.contains(f381s)) {
            this.f360a.runOnUiThread(new RunnableC0677k(this));
        }
        jsPromptResult.cancel();
        return true;
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0683a
    /* renamed from: a */
    public void mo25225a(C0682p pVar, String str) {
        if (!str.startsWith(HttpHost.DEFAULT_SCHEME_NAME) && !pVar.getUrl().endsWith(str)) {
            this.f386x.getTitle().setText(str);
        }
    }

    /* renamed from: f */
    private boolean m25241f() {
        if (this.f387y.m25215b()) {
            this.f360a.finish();
        } else {
            this.f384v = true;
            C0682p pVar = this.f386x;
            this.f386x = this.f387y.m25217a();
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new C0678l(this, pVar));
            pVar.setAnimation(translateAnimation);
            removeView(pVar);
            addView(this.f386x);
        }
        return true;
    }

    /* renamed from: b */
    private boolean m25245b(String str, String str2) {
        C0682p pVar = this.f386x;
        try {
            this.f386x = new C0682p(this.f360a, this.f385w);
            this.f386x.setChromeProxy(this);
            this.f386x.setWebClientProxy(this);
            this.f386x.setWebEventProxy(this);
            if (!TextUtils.isEmpty(str2)) {
                this.f386x.getTitle().setText(str2);
            }
            this.f384v = true;
            this.f387y.m25216a(pVar);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new C0679m(this, pVar, str));
            this.f386x.setAnimation(translateAnimation);
            addView(this.f386x);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0684b
    /* renamed from: b */
    public boolean mo25221b(C0682p pVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith(f364b)) {
            m25246b(str.substring(17));
            return true;
        } else if (TextUtils.equals(str, C3876ar.f17438o)) {
            m25249a(false);
            return true;
        } else if (str.startsWith("http://") || str.startsWith("https://")) {
            this.f386x.m25235a(str);
            return true;
        } else {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                this.f360a.startActivity(intent);
                return true;
            } catch (Throwable th) {
                C3754ao.m12153a(this.f385w, C3857aq.f17251b, th);
                return true;
            }
        }
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0684b
    /* renamed from: c */
    public boolean mo25220c(C0682p pVar, String str) {
        pVar.m25235a("javascript:window.prompt('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        pVar.getRefreshButton().setVisibility(0);
        return true;
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0684b
    /* renamed from: a */
    public boolean mo25223a(C0682p pVar, int i, String str, String str2) {
        C4745bt btVar = this.f385w;
        C3754ao.m12156a(btVar, C3857aq.f17235a, C3857aq.f17267r, "onReceivedError:" + str2);
        pVar.getRefreshButton().setVisibility(0);
        return false;
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0684b
    /* renamed from: a */
    public boolean mo25222a(C0682p pVar, SslErrorHandler sslErrorHandler, SslError sslError) {
        C4745bt btVar = this.f385w;
        C3754ao.m12156a(btVar, C3857aq.f17235a, C3857aq.f17267r, "2-" + sslError);
        this.f360a.runOnUiThread(new RunnableC0680n(this, sslErrorHandler));
        return true;
    }

    /* renamed from: b */
    private void m25246b(String str) {
        Map<String, String> a = C5019co.m4490a(this.f385w, str);
        if (str.startsWith(f371i)) {
            m25251a(a.get("func"), a.get("cbId"), a.get("data"));
        } else if (str.startsWith(f365c)) {
            m25242e();
        } else if (str.startsWith(f366d) && a.containsKey(f373k)) {
            this.f386x.getTitle().setText(a.get(f373k));
        } else if (str.startsWith(f367e)) {
            this.f386x.getWebView().reload();
        } else if (str.startsWith(f368f) && a.containsKey("bshow")) {
            this.f386x.getBackButton().setVisibility(TextUtils.equals("true", a.get("bshow")) ? 0 : 4);
        } else if (str.startsWith(f369g)) {
            C0663l.m25285a(a.get(C4985cm.f20833c));
            m25249a(TextUtils.equals("true", a.get("bsucc")));
        } else if (str.startsWith(f370h)) {
            this.f386x.m25235a("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
        }
    }

    /* renamed from: a */
    private void m25251a(String str, String str2, String str3) {
        JSONObject c = C5019co.m4479c(str3);
        if (f373k.equals(str) && c.has(f373k)) {
            this.f386x.getTitle().setText(c.optString(f373k, ""));
        } else if ("refresh".equals(str)) {
            this.f386x.getWebView().reload();
        } else if (f372j.equals(str)) {
            m25242e();
        } else {
            int i = 0;
            if (f377o.equals(str)) {
                C0663l.m25285a(c.optString(C4985cm.f20833c, null));
                m25249a(c.optBoolean("success", false));
            } else if (f375m.equals(str)) {
                boolean optBoolean = c.optBoolean("show", true);
                ImageView backButton = this.f386x.getBackButton();
                if (!optBoolean) {
                    i = 4;
                }
                backButton.setVisibility(i);
            } else if (f376n.equals(str)) {
                boolean optBoolean2 = c.optBoolean("show", true);
                ImageView refreshButton = this.f386x.getRefreshButton();
                if (!optBoolean2) {
                    i = 4;
                }
                refreshButton.setVisibility(i);
            } else if (f379q.equals(str) && c.optString("url", null) != null) {
                m25245b(c.optString("url"), c.optString(f373k, ""));
            }
        }
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0685c
    /* renamed from: a */
    public void mo25219a(C0682p pVar) {
        m25243d();
    }

    @Override // com.alipay.sdk.widget.C0682p.AbstractC0685c
    /* renamed from: b */
    public void mo25218b(C0682p pVar) {
        pVar.getWebView().reload();
        pVar.getRefreshButton().setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alipay.sdk.widget.j$a  reason: invalid class name */
    /* loaded from: classes.dex */
    public abstract class AbstractanimationAnimation$AnimationListenerC0676a implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private AbstractanimationAnimation$AnimationListenerC0676a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ AbstractanimationAnimation$AnimationListenerC0676a(C0675j jVar, RunnableC0677k kVar) {
            this();
        }
    }
}
