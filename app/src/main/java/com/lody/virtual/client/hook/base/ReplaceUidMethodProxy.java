package com.lody.virtual.client.hook.base;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ReplaceUidMethodProxy extends StaticMethodProxy {
    private final int index;

    public ReplaceUidMethodProxy(String str, int i) {
        super(str);
        this.index = i;
    }

    @Override // com.lody.virtual.client.hook.base.MethodProxy
    public boolean beforeCall(Object obj, Method method, Object... objArr) {
        int intValue = ((Integer) objArr[this.index]).intValue();
        if (intValue == getVUid() || intValue == getBaseVUid()) {
            objArr[this.index] = Integer.valueOf(getRealUid());
        }
        return super.beforeCall(obj, method, objArr);
    }
}
