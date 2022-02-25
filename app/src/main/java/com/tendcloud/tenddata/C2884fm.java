package com.tendcloud.tenddata;

import com.tendcloud.tenddata.C2881fl;
import java.util.Comparator;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fm */
/* loaded from: classes2.dex */
class C2884fm implements Comparator {
    final /* synthetic */ C2881fl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2884fm(C2881fl flVar) {
        this.this$0 = flVar;
    }

    public int compare(C2881fl.C2882a aVar, C2881fl.C2882a aVar2) {
        if (aVar.score == aVar2.score) {
            return 0;
        }
        return aVar.score < aVar2.score ? 1 : -1;
    }
}
