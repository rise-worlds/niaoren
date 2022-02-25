package com.tendcloud.tenddata;

import android.net.TrafficStats;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hg */
/* loaded from: classes2.dex */
public class RunnableC2938hg implements Runnable {
    final /* synthetic */ C2937hf this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2938hg(C2937hf hfVar) {
        this.this$0 = hfVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (C2855es.m15807a(8)) {
                TreeMap treeMap = new TreeMap();
                treeMap.put("wifiTx", Long.valueOf(TrafficStats.getTotalTxBytes()));
                treeMap.put("wifiRx", Long.valueOf(TrafficStats.getTotalRxBytes()));
                treeMap.put("mobileTx", Long.valueOf(TrafficStats.getMobileTxBytes()));
                treeMap.put("mobileRx", Long.valueOf(TrafficStats.getMobileRxBytes()));
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = "env";
                hoVar.f14182c = "traffic";
                hoVar.f14183d = treeMap;
                hoVar.f14180a = AbstractC2790d.ENV;
                C2858ev.m15778a().post(hoVar);
            }
        } catch (Throwable unused) {
        }
    }
}
