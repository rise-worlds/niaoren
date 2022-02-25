package com.tendcloud.tenddata;

import android.app.Activity;
import android.content.Context;
import java.lang.reflect.Method;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.af */
/* loaded from: classes2.dex */
public class C2671af implements AbstractC2847el {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ Context val$ctx;

    @Override // com.tendcloud.tenddata.AbstractC2847el
    public void afterMethodInvoked(Object obj, Method method, Object[] objArr, Object obj2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2671af(C3034zz zzVar, Context context) {
        this.this$0 = zzVar;
        this.val$ctx = context;
    }

    @Override // com.tendcloud.tenddata.AbstractC2847el
    public void beforeMethodInvoke(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        if (!(this.val$ctx instanceof Activity)) {
            return;
        }
        if (name.equalsIgnoreCase("activityPaused")) {
            C2681ap.m16302b((Activity) this.val$ctx, AbstractC2790d.APP);
        } else if (name.equalsIgnoreCase("activityIdle")) {
            C2681ap.m16303a((Activity) this.val$ctx, AbstractC2790d.APP);
        }
    }
}
