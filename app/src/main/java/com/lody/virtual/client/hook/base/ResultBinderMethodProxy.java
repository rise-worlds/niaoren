package com.lody.virtual.client.hook.base;

import android.os.IInterface;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
public abstract class ResultBinderMethodProxy extends AutoResultStaticMethodProxy {
    public abstract InvocationHandler createProxy(IInterface iInterface);

    public ResultBinderMethodProxy(String str) {
        super(str);
    }

    @Override // com.lody.virtual.client.hook.base.AutoResultStaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
    public Object call(Object obj, Method method, Object... objArr) throws Throwable {
        IInterface iInterface = (IInterface) super.call(obj, method, objArr);
        return newProxyInstance(iInterface, createProxy(iInterface));
    }

    public Object newProxyInstance(IInterface iInterface, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(iInterface.getClass().getClassLoader(), iInterface.getClass().getInterfaces(), invocationHandler);
    }
}
