package com.cyjh.ddy.net.helper;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import p110z1.Flowable;
import p110z1.FlowableTransformer;
import p110z1.Function;
import p110z1.Publisher;

/* renamed from: com.cyjh.ddy.net.helper.f */
/* loaded from: classes.dex */
public class RxSchedulersHelper {
    /* renamed from: a */
    public static FlowableTransformer<ResponseBody, String> m21406a() {
        return new FlowableTransformer<ResponseBody, String>() { // from class: com.cyjh.ddy.net.helper.RxSchedulersHelper$1
            @Override // p110z1.FlowableTransformer
            public Publisher<String> apply(Flowable<ResponseBody> arvVar) {
                return arvVar.m10817v(new Function<ResponseBody, String>() { // from class: com.cyjh.ddy.net.helper.RxSchedulersHelper$1.1
                    public String apply(ResponseBody responseBody) throws Exception {
                        BufferedSource buffer = Okio.buffer(responseBody.source());
                        String readUtf8 = buffer.readUtf8();
                        buffer.close();
                        return readUtf8;
                    }
                });
            }
        };
    }
}
