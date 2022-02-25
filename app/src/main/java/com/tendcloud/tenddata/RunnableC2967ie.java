package com.tendcloud.tenddata;

import java.util.Calendar;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ie */
/* loaded from: classes2.dex */
public final class RunnableC2967ie implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        boolean e;
        boolean f;
        try {
            if (C2836ec.m15869c(C2664ab.f13513g)) {
                if (C2836ec.m15861k(C2664ab.f13513g)) {
                    f = C2966id.m15445f();
                    if (f) {
                        C2813ds.m15982a("http://i.tddmp.com/a/" + C2819dt.m15969a(C2664ab.f13513g), false);
                        Calendar instance = Calendar.getInstance();
                        C2843eh.m15843a(C2664ab.f13513g, C2664ab.f13527u, C2664ab.f13531y, (long) ((instance.get(6) * 1000) + instance.get(3)));
                    }
                }
                if (C2836ec.m15862j(C2664ab.f13513g)) {
                    e = C2966id.m15446e();
                    if (e) {
                        C2813ds.m15982a("http://i.tddmp.com/a/" + C2819dt.m15969a(C2664ab.f13513g), false);
                        Calendar instance2 = Calendar.getInstance();
                        C2843eh.m15843a(C2664ab.f13513g, C2664ab.f13527u, C2664ab.f13530x, (long) ((instance2.get(6) * 1000) + instance2.get(3)));
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }
}
