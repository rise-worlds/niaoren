package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gg */
/* loaded from: classes2.dex */
public class C2906gg {

    /* renamed from: a */
    private static volatile C2906gg f14056a;

    /* renamed from: a */
    public static C2906gg m15625a() {
        if (f14056a == null) {
            synchronized (C2906gg.class) {
                if (f14056a == null) {
                    f14056a = new C2906gg();
                }
            }
        }
        return f14056a;
    }

    static {
        try {
            C2858ev.m15778a().register(m15625a());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public final void onTDEBEventCommonEnvironment(C2944hm hmVar) {
        try {
            if (hmVar.f14174a == 3) {
                m15624b();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    public void m15624b() {
        try {
            C2852ep.f13904a.execute(new RunnableC2907gh(this));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
