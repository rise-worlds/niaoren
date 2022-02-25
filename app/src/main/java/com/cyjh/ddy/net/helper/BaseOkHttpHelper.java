package com.cyjh.ddy.net.helper;

import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.net.inf.IAnalysisJson;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import p110z1.AndroidSchedulers;
import p110z1.Consumer;
import p110z1.Disposable;
import p110z1.Flowable;
import p110z1.FlowableTransformer;
import p110z1.Function;
import p110z1.Publisher;
import p110z1.Schedulers;

/* renamed from: com.cyjh.ddy.net.helper.b */
/* loaded from: classes.dex */
public abstract class BaseOkHttpHelper {

    /* renamed from: a */
    private Disposable f7528a;

    /* renamed from: a */
    public abstract void m21427a(Exception exc);

    /* renamed from: a */
    public abstract void m21426a(Object obj);

    /* renamed from: a */
    public void m21425a(String str, String str2, final IAnalysisJson aVar, int i) {
        this.f7528a = LoadApiServiceHelp.m21420a(str).executeGet(str2).m11186a(RxSchedulersHelper.m21406a()).m11186a(new FlowableTransformer<String, Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$3
            @Override // p110z1.FlowableTransformer
            public Publisher<Object> apply(Flowable<String> arvVar) {
                return arvVar.m10817v(new Function<String, Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$3.1
                    public Object apply(String str3) throws Exception {
                        return aVar.getData(str3);
                    }
                });
            }
        }).m11005c(Schedulers.m9047b()).m10929f(Schedulers.m9047b()).m11184a(AndroidSchedulers.m10005a()).m11057b(new Consumer<Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$1
            @Override // p110z1.Consumer
            public void accept(Object obj) throws Exception {
                BaseOkHttpHelper.this.m21426a(obj);
            }
        }, new Consumer<Throwable>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$2
            public void accept(Throwable th) throws Exception {
                BaseOkHttpHelper.this.m21427a(new Exception(th.getMessage()));
            }
        });
    }

    /* renamed from: a */
    public void m21423a(String str, String str2, Map<String, String> map, final IAnalysisJson aVar, int i) {
        this.f7528a = LoadApiServiceHelp.m21420a(str).executePost(str2, map, new HashMap()).m11186a(RxSchedulersHelper.m21406a()).m11186a(new FlowableTransformer<String, Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$6
            @Override // p110z1.FlowableTransformer
            public Publisher<Object> apply(Flowable<String> arvVar) {
                return arvVar.m10817v(new Function<String, Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$6.1
                    public Object apply(String str3) throws Exception {
                        return aVar.getData(str3);
                    }
                });
            }
        }).m11005c(Schedulers.m9047b()).m10929f(Schedulers.m9047b()).m11184a(AndroidSchedulers.m10005a()).m11057b(new Consumer<Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$4
            @Override // p110z1.Consumer
            public void accept(Object obj) throws Exception {
                BaseOkHttpHelper.this.m21426a(obj);
            }
        }, new Consumer<Throwable>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$5
            public void accept(Throwable th) throws Exception {
                BaseOkHttpHelper.this.m21427a(new Exception(th.getMessage()));
            }
        });
    }

    /* renamed from: a */
    public void m21424a(String str, String str2, String str3, Map<String, String> map, final IAnalysisJson aVar, int i) {
        File file = new File(str3);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            PrintStream printStream = System.out;
            printStream.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        builder.addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        this.f7528a = LoadApiServiceHelp.m21420a(str).uploadResourcePost(str2, builder.build()).m11186a(RxSchedulersHelper.m21406a()).m11186a(new FlowableTransformer<String, Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$9
            @Override // p110z1.FlowableTransformer
            public Publisher<Object> apply(Flowable<String> arvVar) {
                return arvVar.m10817v(new Function<String, Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$9.1
                    public Object apply(String str4) throws Exception {
                        return aVar.getData(str4);
                    }
                });
            }
        }).m11005c(Schedulers.m9047b()).m10929f(Schedulers.m9047b()).m11184a(AndroidSchedulers.m10005a()).m11057b(new Consumer<Object>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$7
            @Override // p110z1.Consumer
            public void accept(Object obj) throws Exception {
                BaseOkHttpHelper.this.m21426a(obj);
            }
        }, new Consumer<Throwable>() { // from class: com.cyjh.ddy.net.helper.BaseOkHttpHelper$8
            public void accept(Throwable th) throws Exception {
                CLog.m21882i("BaseOkhttphelper", "throl:" + th.getMessage());
            }
        });
    }

    /* renamed from: a */
    public void m21428a() {
        this.f7528a.dispose();
    }

    /* renamed from: b */
    public boolean m21422b() {
        return this.f7528a.isDisposed();
    }
}
