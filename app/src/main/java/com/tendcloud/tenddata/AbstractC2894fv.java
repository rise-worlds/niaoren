package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fv */
/* loaded from: classes2.dex */
public abstract class AbstractC2894fv {

    /* renamed from: a */
    AbstractC2885fn f13995a;

    /* renamed from: b */
    int f13996b;

    /* renamed from: c */
    Handler f13997c;

    /* renamed from: d */
    Handler f13998d;

    /* renamed from: e */
    C2897fy[] f13999e;

    /* renamed from: f */
    private JSONArray f14000f;

    /* renamed from: g */
    private SparseIntArray f14001g;

    /* renamed from: h */
    private int f14002h;

    /* renamed from: i */
    private int f14003i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract JSONObject mo15678a(double[] dArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC2894fv(AbstractC2885fn fnVar) {
        try {
            this.f14001g = new SparseIntArray();
            this.f14002h = 50;
            this.f14003i = 128;
            this.f13995a = fnVar;
            HandlerThread handlerThread = new HandlerThread("dataFusionHandlerThread");
            handlerThread.start();
            this.f13997c = new Handler(handlerThread.getLooper());
            HandlerThread handlerThread2 = new HandlerThread("recognitionHandlerThread");
            handlerThread2.start();
            this.f13998d = new Handler(handlerThread2.getLooper());
            m15681a();
            m15670g();
            this.f14000f = new JSONArray();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15681a() {
        try {
            this.f13996b = 0;
            this.f13999e = new C2897fy[this.f14003i];
            this.f13997c.removeCallbacksAndMessages(null);
            this.f13998d.removeCallbacksAndMessages(null);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized SparseIntArray m15677b() {
        return this.f14001g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15680a(int i) {
        this.f14002h = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public int m15675c() {
        return this.f14002h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m15676b(int i) {
        this.f14003i = i;
        this.f13999e = new C2897fy[this.f14003i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public int m15673d() {
        return this.f14003i;
    }

    /* renamed from: g */
    private void m15670g() {
        m15674c(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized void m15674c(int i) {
        this.f14001g.put(i, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public JSONArray m15672e() {
        return this.f14000f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public void m15671f() {
        this.f14000f = new JSONArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15679a(JSONObject jSONObject) {
        this.f14000f.put(jSONObject);
    }
}
