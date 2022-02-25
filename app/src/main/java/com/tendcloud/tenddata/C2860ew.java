package com.tendcloud.tenddata;

import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ew */
/* loaded from: classes2.dex */
public class C2860ew extends ThreadLocal {
    final /* synthetic */ C2858ev this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2860ew(C2858ev evVar) {
        this.this$0 = evVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    public ConcurrentLinkedQueue initialValue() {
        return new ConcurrentLinkedQueue();
    }
}
