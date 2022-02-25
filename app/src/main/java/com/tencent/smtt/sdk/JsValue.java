package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class JsValue {

    /* renamed from: a */
    private final JsContext f12771a;

    /* renamed from: b */
    private final IX5JsValue f12772b;

    /* renamed from: com.tencent.smtt.sdk.JsValue$a */
    /* loaded from: classes2.dex */
    private static class C2549a implements IX5JsValue.JsValueFactory {
        private C2549a() {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue.JsValueFactory
        public String getJsValueClassName() {
            return JsValue.class.getName();
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue.JsValueFactory
        public IX5JsValue unwrap(Object obj) {
            if (obj == null || !(obj instanceof JsValue)) {
                return null;
            }
            return ((JsValue) obj).f12772b;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue.JsValueFactory
        public Object wrap(IX5JsValue iX5JsValue) {
            JsContext current;
            if (iX5JsValue == null || (current = JsContext.current()) == null) {
                return null;
            }
            return new JsValue(current, iX5JsValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static IX5JsValue.JsValueFactory m17069a() {
        return new C2549a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsValue(JsContext jsContext, IX5JsValue iX5JsValue) {
        this.f12771a = jsContext;
        this.f12772b = iX5JsValue;
    }

    public boolean isUndefined() {
        return this.f12772b.isUndefined();
    }

    public boolean isNull() {
        return this.f12772b.isNull();
    }

    public boolean isArray() {
        return this.f12772b.isArray();
    }

    public boolean isBoolean() {
        return this.f12772b.isBoolean();
    }

    public boolean toBoolean() {
        return this.f12772b.toBoolean();
    }

    public boolean isInteger() {
        return this.f12772b.isInteger();
    }

    public int toInteger() {
        return this.f12772b.toInteger();
    }

    public boolean isNumber() {
        return this.f12772b.isNumber();
    }

    public Number toNumber() {
        return this.f12772b.toNumber();
    }

    public boolean isString() {
        return this.f12772b.isString();
    }

    public String toString() {
        return this.f12772b.toString();
    }

    public boolean isObject() {
        return this.f12772b.isObject();
    }

    public <T> T toObject(Class<T> cls) {
        return (T) this.f12772b.toObject(cls);
    }

    public boolean isJavascriptInterface() {
        return this.f12772b.isJavascriptInterface();
    }

    public Object toJavascriptInterface() {
        return this.f12772b.toJavascriptInterface();
    }

    public boolean isArrayBufferOrArrayBufferView() {
        return this.f12772b.isArrayBufferOrArrayBufferView();
    }

    public ByteBuffer toByteBuffer() {
        return this.f12772b.toByteBuffer();
    }

    public boolean isFunction() {
        return this.f12772b.isFunction();
    }

    public JsValue call(Object... objArr) {
        return m17068a(this.f12772b.call(objArr));
    }

    public JsValue construct(Object... objArr) {
        return m17068a(this.f12772b.construct(objArr));
    }

    public boolean isPromise() {
        return this.f12772b.isPromise();
    }

    public void resolve(Object obj) {
        this.f12772b.resolveOrReject(obj, true);
    }

    public void reject(Object obj) {
        this.f12772b.resolveOrReject(obj, false);
    }

    public JsContext context() {
        return this.f12771a;
    }

    /* renamed from: a */
    private JsValue m17068a(IX5JsValue iX5JsValue) {
        if (iX5JsValue == null) {
            return null;
        }
        return new JsValue(this.f12771a, iX5JsValue);
    }
}
