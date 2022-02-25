package com.tendcloud.tenddata;

import com.stripe.android.PaymentResultListener;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hc */
/* loaded from: classes2.dex */
public final class RunnableC2934hc implements Runnable {
    final /* synthetic */ Throwable val$throwable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2934hc(Throwable th) {
        this.val$throwable = th;
    }

    @Override // java.lang.Runnable
    public void run() {
        List list;
        List list2;
        try {
            String c = C2855es.m15786c(this.val$throwable.getMessage());
            list = C2933hb.f14142a;
            if (!list.contains(c)) {
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = "sdk";
                hoVar.f14182c = PaymentResultListener.f11903c;
                TreeMap treeMap = new TreeMap();
                treeMap.put("type", this.val$throwable.toString());
                treeMap.put("msg", this.val$throwable.getMessage());
                treeMap.put("stack", C2933hb.m15524a(this.val$throwable));
                hoVar.f14183d = treeMap;
                hoVar.f14180a = AbstractC2790d.ENV;
                C2858ev.m15778a().post(hoVar);
                list2 = C2933hb.f14142a;
                list2.add(c);
            }
        } catch (Throwable unused) {
        }
    }
}
