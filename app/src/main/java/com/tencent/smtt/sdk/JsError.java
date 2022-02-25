package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;

/* loaded from: classes2.dex */
public class JsError {

    /* renamed from: a */
    private final IX5JsError f12770a;

    /* JADX INFO: Access modifiers changed from: protected */
    public JsError(IX5JsError iX5JsError) {
        this.f12770a = iX5JsError;
    }

    public String getMessage() {
        return this.f12770a.getMessage();
    }

    public String getStack() {
        return this.f12770a.getStack();
    }
}
