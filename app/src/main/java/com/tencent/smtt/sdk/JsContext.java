package com.tencent.smtt.sdk;

import android.content.Context;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import java.net.URL;

/* loaded from: classes2.dex */
public final class JsContext {

    /* renamed from: a */
    private final JsVirtualMachine f12763a;

    /* renamed from: b */
    private final IX5JsContext f12764b;

    /* renamed from: c */
    private ExceptionHandler f12765c;

    /* renamed from: d */
    private String f12766d;

    /* loaded from: classes2.dex */
    public interface ExceptionHandler {
        void handleException(JsContext jsContext, JsError jsError);
    }

    public JsContext(Context context) {
        this(new JsVirtualMachine(context));
    }

    public JsContext(JsVirtualMachine jsVirtualMachine) {
        if (jsVirtualMachine != null) {
            this.f12763a = jsVirtualMachine;
            this.f12764b = this.f12763a.m17066a();
            try {
                this.f12764b.setPerContextData(this);
            } catch (AbstractMethodError unused) {
            }
        } else {
            throw new IllegalArgumentException("The virtualMachine value can not be null");
        }
    }

    public void addJavascriptInterface(Object obj, String str) {
        this.f12764b.addJavascriptInterface(obj, str);
    }

    public void destroy() {
        this.f12764b.destroy();
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        evaluateJavascript(str, valueCallback, null);
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url) {
        this.f12764b.evaluateJavascript(str, valueCallback, url);
    }

    public JsValue evaluateScript(String str) {
        return evaluateScript(str, null);
    }

    public JsValue evaluateScript(String str, URL url) {
        IX5JsValue evaluateScript = this.f12764b.evaluateScript(str, url);
        if (evaluateScript == null) {
            return null;
        }
        return new JsValue(this, evaluateScript);
    }

    public void evaluateScriptAsync(String str, final ValueCallback<JsValue> valueCallback, URL url) {
        this.f12764b.evaluateScriptAsync(str, valueCallback == null ? null : new ValueCallback<IX5JsValue>() { // from class: com.tencent.smtt.sdk.JsContext.1
            /* renamed from: a */
            public void onReceiveValue(IX5JsValue iX5JsValue) {
                valueCallback.onReceiveValue(iX5JsValue == null ? null : new JsValue(JsContext.this, iX5JsValue));
            }
        }, url);
    }

    public ExceptionHandler exceptionHandler() {
        return this.f12765c;
    }

    public String name() {
        return this.f12766d;
    }

    public void removeJavascriptInterface(String str) {
        this.f12764b.removeJavascriptInterface(str);
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.f12765c = exceptionHandler;
        if (exceptionHandler == null) {
            this.f12764b.setExceptionHandler(null);
        } else {
            this.f12764b.setExceptionHandler(new ValueCallback<IX5JsError>() { // from class: com.tencent.smtt.sdk.JsContext.2
                /* renamed from: a */
                public void onReceiveValue(IX5JsError iX5JsError) {
                    JsContext.this.f12765c.handleException(JsContext.this, new JsError(iX5JsError));
                }
            });
        }
    }

    public void setName(String str) {
        this.f12766d = str;
        this.f12764b.setName(str);
    }

    public void stealValueFromOtherCtx(String str, JsContext jsContext, String str2) {
        this.f12764b.stealValueFromOtherCtx(str, jsContext.f12764b, str2);
    }

    public int getNativeBufferId() {
        return this.f12764b.getNativeBufferId();
    }

    public byte[] getNativeBuffer(int i) {
        return this.f12764b.getNativeBuffer(i);
    }

    public int setNativeBuffer(int i, byte[] bArr) {
        return this.f12764b.setNativeBuffer(i, bArr);
    }

    public JsVirtualMachine virtualMachine() {
        return this.f12763a;
    }

    public static JsContext current() {
        return (JsContext) X5JsCore.m16907a();
    }
}
