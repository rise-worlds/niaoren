package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import com.goldcoast.sdk.p052c.LogUtil;
import p110z1.C3894au;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EntryPoint.java */
/* renamed from: com.goldcoast.sdk.domain.b */
/* loaded from: classes.dex */
public final class RunnableC1395b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ ConditionVariable f9059a;

    /* renamed from: b */
    final /* synthetic */ EntryPoint f9060b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC1395b(EntryPoint entryPoint, ConditionVariable conditionVariable) {
        this.f9060b = entryPoint;
        this.f9059a = conditionVariable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        char c;
        boolean z;
        this.f9060b.f9042C = false;
        String str = C3894au.f17527j;
        int i2 = 0;
        while (true) {
            i = EntryPoint.f9035j;
            if (i2 >= i) {
                break;
            } else if (this.f9060b.getStatus()) {
                str = "ok";
                break;
            } else {
                z = this.f9060b.f9043D;
                if (!z) {
                    str = "failed";
                    break;
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.m20321a();
                LogUtil.m20319a("timer: loop");
                i2++;
            }
        }
        int hashCode = str.hashCode();
        if (hashCode != -1281977283) {
            if (hashCode == 3548 && str.equals("ok")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("failed")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                this.f9060b.m20289b("$$$ success", 0);
                break;
            case 1:
                LogUtil.m20321a();
                LogUtil.m20319a("timer: $$$ failed");
                break;
            default:
                LogUtil.m20321a();
                LogUtil.m20319a("timer: $$$ timeout");
                break;
        }
        this.f9059a.open();
    }
}
