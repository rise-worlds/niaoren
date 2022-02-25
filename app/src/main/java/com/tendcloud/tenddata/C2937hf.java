package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hf */
/* loaded from: classes2.dex */
public class C2937hf {

    /* renamed from: a */
    private static volatile C2937hf f14146a;

    /* renamed from: a */
    public static C2937hf m15514a() {
        if (f14146a == null) {
            synchronized (C2937hf.class) {
                if (f14146a == null) {
                    f14146a = new C2937hf();
                }
            }
        }
        return f14146a;
    }

    static {
        try {
            C2858ev.m15778a().register(m15514a());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public void m15513b() {
        try {
            C2852ep.f13904a.execute(new RunnableC2938hg(this));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
