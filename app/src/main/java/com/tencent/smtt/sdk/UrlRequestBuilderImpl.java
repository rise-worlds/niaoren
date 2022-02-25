package com.tencent.smtt.sdk;

import android.util.Pair;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.smtt.export.external.interfaces.UrlRequest;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class UrlRequestBuilderImpl extends UrlRequest.Builder {

    /* renamed from: a */
    private static final String f12992a = "UrlRequestBuilderImpl";

    /* renamed from: b */
    private final String f12993b;

    /* renamed from: c */
    private final UrlRequest.Callback f12994c;

    /* renamed from: d */
    private final Executor f12995d;

    /* renamed from: e */
    private String f12996e;

    /* renamed from: g */
    private boolean f12998g;

    /* renamed from: f */
    private final ArrayList<Pair<String, String>> f12997f = new ArrayList<>();

    /* renamed from: h */
    private int f12999h = 3;

    public UrlRequestBuilderImpl(String str, UrlRequest.Callback callback, Executor executor) {
        if (str == null) {
            throw new NullPointerException("URL is required.");
        } else if (callback == null) {
            throw new NullPointerException("Callback is required.");
        } else if (executor != null) {
            this.f12993b = str;
            this.f12994c = callback;
            this.f12995d = executor;
        } else {
            throw new NullPointerException("Executor is required.");
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest.Builder setHttpMethod(String str) {
        if (str != null) {
            this.f12996e = str;
            return this;
        }
        throw new NullPointerException("Method is required.");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl addHeader(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Invalid header name.");
        } else if (str2 == null) {
            throw new NullPointerException("Invalid header value.");
        } else if (HttpRequest.HEADER_ACCEPT_ENCODING.equalsIgnoreCase(str)) {
            return this;
        } else {
            this.f12997f.add(Pair.create(str, str2));
            return this;
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl disableCache() {
        this.f12998g = true;
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl setPriority(int i) {
        this.f12999h = i;
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest build() throws NullPointerException {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return null;
        }
        UrlRequest urlRequest = (UrlRequest) a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "UrlRequest_getX5UrlRequestProvider", new Class[]{String.class, Integer.TYPE, UrlRequest.Callback.class, Executor.class, Boolean.TYPE, String.class, ArrayList.class}, this.f12993b, Integer.valueOf(this.f12999h), this.f12994c, this.f12995d, Boolean.valueOf(this.f12998g), this.f12996e, this.f12997f);
        if (urlRequest != null) {
            return urlRequest;
        }
        throw new NullPointerException("UrlRequest build fail");
    }
}
