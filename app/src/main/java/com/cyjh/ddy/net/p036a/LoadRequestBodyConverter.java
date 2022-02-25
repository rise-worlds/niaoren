package com.cyjh.ddy.net.p036a;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import p110z1.Gson;
import p110z1.JsonWriter;
import p110z1.TypeAdapter;
import retrofit2.Converter;

/* renamed from: com.cyjh.ddy.net.a.b */
/* loaded from: classes.dex */
public class LoadRequestBodyConverter<T> implements Converter<T, RequestBody> {

    /* renamed from: a */
    private static final MediaType f7461a = MediaType.parse("application/json; charset=UTF-8");

    /* renamed from: b */
    private static final Charset f7462b = Charset.forName("UTF-8");

    /* renamed from: c */
    private final Gson f7463c;

    /* renamed from: d */
    private final TypeAdapter<T> f7464d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoadRequestBodyConverter(Gson oxVar, TypeAdapter<T> ppVar) {
        this.f7463c = oxVar;
        this.f7464d = ppVar;
    }

    /* renamed from: a */
    public RequestBody m21462b(T t) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter a = this.f7463c.m1597a((Writer) new OutputStreamWriter(buffer.outputStream(), f7462b));
        this.f7464d.mo1235a(a, (JsonWriter) t);
        a.close();
        return RequestBody.create(f7461a, buffer.readByteString());
    }
}
