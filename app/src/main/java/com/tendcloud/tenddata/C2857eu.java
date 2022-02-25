package com.tendcloud.tenddata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.eu */
/* loaded from: classes2.dex */
final class C2857eu implements InvocationHandler {
    final /* synthetic */ AbstractC2847el val$callback;
    final /* synthetic */ Object val$real;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2857eu(AbstractC2847el elVar, Object obj) {
        this.val$callback = elVar;
        this.val$real = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        this.val$callback.beforeMethodInvoke(obj, method, objArr);
        Object invoke = method.invoke(this.val$real, objArr);
        this.val$callback.afterMethodInvoked(obj, method, objArr, invoke);
        return invoke;
    }
}
