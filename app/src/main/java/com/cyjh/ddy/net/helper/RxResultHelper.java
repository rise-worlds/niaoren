package com.cyjh.ddy.net.helper;

import com.cyjh.ddy.net.bean.base.BaseHttpResult;
import p110z1.AndroidSchedulers;
import p110z1.BackpressureStrategy;
import p110z1.Flowable;
import p110z1.FlowableEmitter;
import p110z1.FlowableOnSubscribe;
import p110z1.FlowableTransformer;
import p110z1.Function;
import p110z1.Publisher;

/* renamed from: com.cyjh.ddy.net.helper.e */
/* loaded from: classes.dex */
public class RxResultHelper {
    /* renamed from: a */
    public static <T> FlowableTransformer<BaseHttpResult<T>, T> m21411a() {
        return new FlowableTransformer<BaseHttpResult<T>, T>() { // from class: com.cyjh.ddy.net.helper.RxResultHelper$1
            @Override // p110z1.FlowableTransformer
            public Publisher<T> apply(Flowable<BaseHttpResult<T>> arvVar) {
                return arvVar.m10839p((Function<? super BaseHttpResult<T>, ? extends Publisher<? extends R>>) new Function<BaseHttpResult<T>, Publisher<T>>() { // from class: com.cyjh.ddy.net.helper.RxResultHelper$1.1
                    public Publisher<T> apply(BaseHttpResult<T> baseHttpResult) throws Exception {
                        if (baseHttpResult == 0) {
                            return Flowable.m11211a((Throwable) new Exception("网络错误"));
                        }
                        return Flowable.m11211a((Throwable) new Exception());
                    }
                }).m11184a(AndroidSchedulers.m10005a());
            }
        };
    }

    /* renamed from: b */
    public static <T> FlowableTransformer<BaseHttpResult<T>, BaseHttpResult> m21408b() {
        return new FlowableTransformer<BaseHttpResult<T>, BaseHttpResult>() { // from class: com.cyjh.ddy.net.helper.RxResultHelper$2
            @Override // p110z1.FlowableTransformer
            public Publisher<BaseHttpResult> apply(Flowable<BaseHttpResult<T>> arvVar) {
                return arvVar.m10839p((Function<? super BaseHttpResult<T>, ? extends Publisher<? extends R>>) new Function<BaseHttpResult<T>, Publisher<BaseHttpResult>>() { // from class: com.cyjh.ddy.net.helper.RxResultHelper$2.1
                    public Publisher<BaseHttpResult> apply(BaseHttpResult<T> baseHttpResult) throws Exception {
                        Flowable b;
                        if (baseHttpResult == 0) {
                            return Flowable.m11211a((Throwable) new Exception("网络错误"));
                        }
                        b = RxResultHelper.m21407b(baseHttpResult);
                        return b;
                    }
                }).m11184a(AndroidSchedulers.m10005a());
            }
        };
    }

    /* renamed from: a */
    private static <T> Flowable<T> m21409a(final T t) {
        return Flowable.m11189a((FlowableOnSubscribe) new FlowableOnSubscribe<T>() { // from class: com.cyjh.ddy.net.helper.RxResultHelper$3
            @Override // p110z1.FlowableOnSubscribe
            public void subscribe(FlowableEmitter<T> arxVar) throws Exception {
                arxVar.onNext(t);
            }
        }, BackpressureStrategy.BUFFER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static Flowable<BaseHttpResult> m21407b(final BaseHttpResult baseHttpResult) {
        return Flowable.m11189a((FlowableOnSubscribe) new FlowableOnSubscribe<BaseHttpResult>() { // from class: com.cyjh.ddy.net.helper.RxResultHelper$4
            @Override // p110z1.FlowableOnSubscribe
            public void subscribe(FlowableEmitter<BaseHttpResult> arxVar) throws Exception {
                arxVar.onNext(BaseHttpResult.this);
            }
        }, BackpressureStrategy.BUFFER);
    }
}
