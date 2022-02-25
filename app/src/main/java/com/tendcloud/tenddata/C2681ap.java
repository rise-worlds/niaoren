package com.tendcloud.tenddata;

import android.app.Activity;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ap */
/* loaded from: classes2.dex */
public final class C2681ap {
    private C2681ap() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16303a(Activity activity, AbstractC2790d dVar) {
        try {
            C2664ab.f13520n = 0;
            C3034zz.m15215b().removeMessages(0);
            if (activity == null || (activity.getChangingConfigurations() & 128) != 128) {
                C2855es.execute(new RunnableC2682aq(dVar));
                return;
            }
            C2811dq.iForDeveloper("Ignore page changing during screen switch");
            C3034zz.f14347c = true;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m16302b(Activity activity, AbstractC2790d dVar) {
        try {
            C2664ab.f13520n = 1;
            C3034zz.m15215b().removeMessages(0);
            Message obtain = Message.obtain();
            obtain.obj = dVar;
            obtain.what = 0;
            C3034zz.m15215b().sendMessageDelayed(obtain, C2664ab.f13498O);
            C2855es.execute(new RunnableC2683ar(dVar, activity));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
