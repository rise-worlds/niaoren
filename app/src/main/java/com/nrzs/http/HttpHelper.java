package com.nrzs.http;

import java.util.Map;
import p110z1.AndroidSchedulers;
import p110z1.Consumer;
import p110z1.Disposable;
import p110z1.Schedulers;

/* renamed from: com.nrzs.http.i */
/* loaded from: classes2.dex */
public class HttpHelper {

    /* renamed from: a */
    private Disposable f11157a;

    /* renamed from: a */
    public void m18556a(String str, Map<String, String> map, Map<String, String> map2) {
    }

    /* renamed from: a */
    public void m18557a(Class cls, String str, ThreadCallback nVar, UICallback oVar) {
        this.f11157a = ((ApiService) NetEngin.m18553b().m18554a().create(ApiService.class)).m18581a(str).m11005c(Schedulers.m9047b()).m10929f(Schedulers.m9047b()).m11184a(AndroidSchedulers.m10005a()).m11057b(new Consumer<Object>() { // from class: com.nrzs.http.i.1
            @Override // p110z1.Consumer
            public void accept(Object obj) {
            }
        }, new Consumer<Throwable>() { // from class: com.nrzs.http.i.2
            /* renamed from: a */
            public void accept(Throwable th) {
            }
        });
    }
}
