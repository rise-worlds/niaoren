package com.nrzs.http;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import p110z1.Function;

/* renamed from: com.nrzs.http.h */
/* loaded from: classes2.dex */
public class FunctionToString<T> implements Function<ResponseBody, T> {

    /* renamed from: a */
    private ThreadCallback<T, String> f11156a;

    /* renamed from: a */
    public void m18559a(ThreadCallback<T, String> nVar) {
        this.f11156a = nVar;
    }

    /* renamed from: a */
    public T apply(ResponseBody responseBody) throws Exception {
        BufferedSource buffer = Okio.buffer(responseBody.source());
        String readUtf8 = buffer.readUtf8();
        buffer.close();
        responseBody.close();
        ThreadCallback<T, String> nVar = this.f11156a;
        if (nVar != null) {
            return nVar.onResponse(readUtf8);
        }
        return null;
    }
}
