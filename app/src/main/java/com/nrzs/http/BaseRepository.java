package com.nrzs.http;

import android.util.Log;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import p110z1.AndroidSchedulers;
import p110z1.Flowable;
import p110z1.FlowableTransformer;
import p110z1.Function;
import p110z1.Publisher;
import p110z1.Schedulers;
import p110z1.Subscriber;
import p110z1.apa;
import p110z1.dby;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* renamed from: com.nrzs.http.e */
/* loaded from: classes2.dex */
public class BaseRepository<T> {

    /* renamed from: b */
    private Flowable<ResponseBody> f11136b;

    /* renamed from: c */
    private ThreadCallback<T, String> f11137c;

    /* renamed from: d */
    private UICallback<T> f11138d;

    /* renamed from: g */
    private Class<T> f11141g;

    /* renamed from: h */
    private Subscriber<T> f11142h = new Subscriber<T>() { // from class: com.nrzs.http.e.1
        @Override // p110z1.Subscriber
        public void onComplete() {
        }

        @Override // p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            dbyVar.request(3L);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (BaseRepository.this.f11138d != null) {
                BaseRepository.this.f11138d.mo3022a((UICallback) t);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (BaseRepository.this.f11138d != null) {
                BaseRepository.this.f11138d.mo3021a(th);
            }
        }
    };

    /* renamed from: a */
    private BaseHttpSubscripber<T> f11135a = new BaseHttpSubscripber<>();

    /* renamed from: f */
    private FunctionToString<T> f11140f = new FunctionToString<>();

    /* renamed from: e */
    private ResponseTransformer<ResponseBody, T> f11139e = new ResponseTransformer<>();

    /* renamed from: a */
    public void m18569a(String str) {
        Api.m18582b(str).enqueue(new Callback<ResponseBody>() { // from class: com.nrzs.http.e.2
            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }
        });
    }

    /* renamed from: a */
    public BaseHttpSubscripber m18575a() {
        this.f11136b.m11186a(new FlowableTransformer<ResponseBody, T>() { // from class: com.nrzs.http.e.3
            @Override // p110z1.FlowableTransformer
            public Publisher<T> apply(Flowable<ResponseBody> arvVar) {
                return arvVar.m10817v(new Function<ResponseBody, T>() { // from class: com.nrzs.http.e.3.1
                    /* renamed from: a */
                    public T apply(ResponseBody responseBody) throws Exception {
                        if (BaseRepository.this.f11137c == null) {
                            return null;
                        }
                        BufferedSource buffer = Okio.buffer(responseBody.source());
                        String readUtf8 = buffer.readUtf8();
                        buffer.close();
                        return (T) BaseRepository.this.f11137c.onResponse(readUtf8);
                    }
                });
            }
        }).m11005c(Schedulers.m9047b()).m10929f(Schedulers.m9047b()).m11184a(AndroidSchedulers.m10005a()).subscribe(this.f11135a);
        return this.f11135a;
    }

    /* renamed from: a */
    public BaseHttpSubscripber m18574a(final int i) {
        if (this.f11137c == null) {
            this.f11137c = new ThreadCallback<T, String>() { // from class: com.nrzs.http.e.4
                @Override // com.nrzs.http.ThreadCallback
                public T onResponse(String str) {
                    Log.d("BaseRepository:", "sendForRx:" + i);
                    Log.d("BaseRepository", "json:" + str);
                    return (T) apa.m11876b(str, BaseRepository.this.f11141g);
                }
            };
            this.f11140f.m18559a(this.f11137c);
        }
        this.f11136b.m11186a(this.f11139e).m11005c(Schedulers.m9047b()).m10929f(Schedulers.m9047b()).m11184a(AndroidSchedulers.m10005a()).subscribe((Subscriber<T>) this.f11142h);
        return this.f11135a;
    }

    /* renamed from: a */
    public BaseRepository m18572a(ThreadCallback<T, String> nVar) {
        this.f11137c = nVar;
        this.f11140f.m18559a(nVar);
        return this;
    }

    /* renamed from: a */
    public BaseRepository m18571a(UICallback<T> oVar) {
        this.f11138d = oVar;
        return this;
    }

    /* renamed from: a */
    public BaseRepository m18570a(Class<T> cls) {
        this.f11141g = cls;
        return this;
    }

    public BaseRepository() {
        this.f11139e.m18549a(this.f11140f);
    }

    /* renamed from: a */
    public BaseRepository m18568a(Flowable<ResponseBody> arvVar) {
        this.f11136b = arvVar;
        return this;
    }
}
