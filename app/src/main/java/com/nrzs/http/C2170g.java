package com.nrzs.http;

import java.io.IOException;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import p110z1.AndroidSchedulers;
import p110z1.BackpressureStrategy;
import p110z1.Flowable;
import p110z1.FlowableEmitter;
import p110z1.FlowableOnSubscribe;
import p110z1.Schedulers;
import p110z1.Subscriber;
import p110z1.cjm;
import p110z1.dby;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: Demo.java */
/* renamed from: com.nrzs.http.g */
/* loaded from: classes2.dex */
public class C2170g {

    /* renamed from: a */
    private BaseRepository<TestMsg> f11155a = new BaseRepository<>();

    /* renamed from: a */
    public void m18564a() {
    }

    /* renamed from: a */
    public void m18563a(UICallback oVar, ThreadCallback nVar) {
        this.f11155a.m18568a(Api.m18586a("http://news-at.zhihu.com/api/4/version/android/2.3.0")).m18572a(nVar).m18571a(oVar).m18574a(5);
    }

    /* renamed from: b */
    public static void m18562b() {
        Flowable.m11189a((FlowableOnSubscribe) new FlowableOnSubscribe<NetEngin>() { // from class: com.nrzs.http.g.2
            @Override // p110z1.FlowableOnSubscribe
            public void subscribe(FlowableEmitter<NetEngin> arxVar) throws Exception {
                arxVar.onNext(NetEngin.m18553b());
                arxVar.onComplete();
            }
        }, BackpressureStrategy.BUFFER).m11005c(Schedulers.m9047b()).m11184a(Schedulers.m9045d()).subscribe(new Subscriber<NetEngin>() { // from class: com.nrzs.http.g.1
            @Override // p110z1.Subscriber
            public void onComplete() {
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
            }

            @Override // p110z1.Subscriber
            public void onSubscribe(dby dbyVar) {
                dbyVar.request(cjm.f20759b);
            }

            /* renamed from: a */
            public void onNext(NetEngin jVar) {
                try {
                    Thread.sleep(6000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Call<ResponseBody> b = ((ApiService) NetEngin.m18553b().m18554a().create(ApiService.class)).m18578b("http://news-at.zhihu.com/api/4/version/android/2.3.0");
        b.enqueue(new Callback() { // from class: com.nrzs.http.g.3
            @Override // retrofit2.Callback
            public void onFailure(Call call, Throwable th) {
            }

            @Override // retrofit2.Callback
            public void onResponse(Call call, Response response) {
            }
        });
        b.cancel();
        ((ApiService) NetEngin.m18553b().m18554a().create(ApiService.class)).m18581a("http://news-at.zhihu.com/api/4/version/android/2.3.0").m11005c(Schedulers.m9047b()).m10929f(Schedulers.m9047b()).m11184a(AndroidSchedulers.m10005a()).subscribe(new Subscriber<ResponseBody>() { // from class: com.nrzs.http.g.4
            @Override // p110z1.Subscriber
            public void onComplete() {
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
            }

            @Override // p110z1.Subscriber
            public void onSubscribe(dby dbyVar) {
                dbyVar.request(cjm.f20759b);
            }

            /* renamed from: a */
            public void onNext(ResponseBody responseBody) {
                BufferedSource buffer = Okio.buffer(responseBody.source());
                try {
                    buffer.readUtf8();
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
