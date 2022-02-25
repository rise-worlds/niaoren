package com.cyjh.ddy.net.utils;

import p110z1.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* loaded from: classes.dex */
public class RetrofitUtils {
    private RetrofitUtils() {
    }

    /* renamed from: a */
    public Retrofit m21400a(String str) {
        return new Retrofit.Builder().client(OkHttpUtils.m21404b().m21405a()).addCallAdapterFactory(RxJava2CallAdapterFactory.m130a()).baseUrl(str).build();
    }

    /* renamed from: a */
    public Retrofit m21401a() {
        return new Retrofit.Builder().client(OkHttpUtils.m21404b().m21405a()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.m130a()).build();
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {

        /* renamed from: a */
        private static final RetrofitUtils f7539a = new RetrofitUtils();

        private SingletonHolder() {
        }
    }

    /* renamed from: b */
    public static RetrofitUtils m21399b() {
        return SingletonHolder.f7539a;
    }
}
