package com.tendcloud.tenddata;

import com.tendcloud.tenddata.C2873fi;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fj */
/* loaded from: classes2.dex */
public class C2879fj implements Comparator {
    final /* synthetic */ C2873fi this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2879fj(C2873fi fiVar) {
        this.this$0 = fiVar;
    }

    public int compare(C2873fi.C2877d dVar, C2873fi.C2877d dVar2) {
        if (Double.doubleToLongBits(dVar.score) == Double.doubleToLongBits(dVar2.score)) {
            return 0;
        }
        return Double.doubleToLongBits(dVar.score) < Double.doubleToLongBits(dVar2.score) ? 1 : -1;
    }
}
