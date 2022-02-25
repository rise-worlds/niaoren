package com.cyjh.ddy.net.p036a;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p110z1.Gson;
import p110z1.TypeToken;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* renamed from: com.cyjh.ddy.net.a.a */
/* loaded from: classes.dex */
public class LoadConverterFactory extends Converter.Factory {

    /* renamed from: a */
    private final Gson f7460a;

    /* renamed from: a */
    public static LoadConverterFactory m21467a() {
        return m21464a(new Gson());
    }

    /* renamed from: a */
    public static LoadConverterFactory m21464a(Gson oxVar) {
        return new LoadConverterFactory(oxVar);
    }

    private LoadConverterFactory(Gson oxVar) {
        if (oxVar != null) {
            this.f7460a = oxVar;
            return;
        }
        throw new NullPointerException("gson == null");
    }

    /* renamed from: a */
    public Converter<ResponseBody, ?> m21466a(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new LoadResponseBodyConverter(this.f7460a, type);
    }

    /* renamed from: a */
    public Converter<?, RequestBody> m21465a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new LoadRequestBodyConverter(this.f7460a, this.f7460a.m1579a((TypeToken) TypeToken.get(type)));
    }
}
