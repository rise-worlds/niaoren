package com.cyjh.ddy.net.p036a;

import com.cyjh.ddy.base.utils.JsonUtil;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import p110z1.Gson;
import retrofit2.Converter;

/* renamed from: com.cyjh.ddy.net.a.c */
/* loaded from: classes.dex */
public class LoadResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    /* renamed from: a */
    private final Gson f7465a;

    /* renamed from: b */
    private Type f7466b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoadResponseBodyConverter(Gson oxVar, Type type) {
        this.f7465a = oxVar;
        this.f7466b = type;
    }

    /* renamed from: a */
    public T m21461a(ResponseBody responseBody) throws IOException {
        BufferedSource buffer = Okio.buffer(responseBody.source());
        String readUtf8 = buffer.readUtf8();
        buffer.close();
        try {
            return (T) JsonUtil.m21802a(readUtf8, this.f7466b);
        } finally {
            responseBody.close();
        }
    }
}
