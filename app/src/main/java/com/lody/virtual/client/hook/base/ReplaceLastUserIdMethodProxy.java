package com.lody.virtual.client.hook.base;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ReplaceLastUserIdMethodProxy extends StaticMethodProxy {
    public ReplaceLastUserIdMethodProxy(String str) {
        super(str);
    }

    @Override // com.lody.virtual.client.hook.base.MethodProxy
    public boolean beforeCall(Object obj, Method method, Object... objArr) {
        replaceLastUserId(objArr);
        return super.beforeCall(obj, method, objArr);
    }
}
