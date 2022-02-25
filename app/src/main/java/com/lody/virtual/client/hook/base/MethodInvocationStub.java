package com.lody.virtual.client.hook.base;

import android.text.TextUtils;
import com.lody.virtual.client.hook.annotations.LogInvocation;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MethodInvocationStub<T> {
    private static final String TAG = "MethodInvocationStub";
    private T mBaseInterface;
    private MethodProxy mDefaultProxy;
    private Map<String, MethodProxy> mInternalMethodProxies;
    private LogInvocation.Condition mInvocationLoggingCondition;
    private T mProxyInterface;

    public Map<String, MethodProxy> getAllHooks() {
        return this.mInternalMethodProxies;
    }

    public MethodInvocationStub(T t, Class<?>... clsArr) {
        this.mInternalMethodProxies = new HashMap();
        this.mInvocationLoggingCondition = LogInvocation.Condition.NEVER;
        this.mBaseInterface = t;
        if (t != null) {
            this.mProxyInterface = (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), clsArr == null ? MethodParameterUtils.getAllInterface(t.getClass()) : clsArr, new HookInvocationHandler());
        }
    }

    public LogInvocation.Condition getInvocationLoggingCondition() {
        return this.mInvocationLoggingCondition;
    }

    public void setInvocationLoggingCondition(LogInvocation.Condition condition) {
        this.mInvocationLoggingCondition = condition;
    }

    public MethodInvocationStub(T t) {
        this(t, null);
    }

    public void copyMethodProxies(MethodInvocationStub methodInvocationStub) {
        this.mInternalMethodProxies.putAll(methodInvocationStub.getAllHooks());
    }

    public MethodProxy addMethodProxy(MethodProxy methodProxy) {
        if (methodProxy != null && !TextUtils.isEmpty(methodProxy.getMethodName())) {
            if (this.mInternalMethodProxies.containsKey(methodProxy.getMethodName())) {
                VLog.m18986w(TAG, "The Hook(%s, %s) you added has been in existence.", methodProxy.getMethodName(), methodProxy.getClass().getName());
                return methodProxy;
            }
            this.mInternalMethodProxies.put(methodProxy.getMethodName(), methodProxy);
        }
        return methodProxy;
    }

    public MethodProxy removeMethodProxy(String str) {
        return this.mInternalMethodProxies.remove(str);
    }

    public void removeMethodProxy(MethodProxy methodProxy) {
        if (methodProxy != null) {
            removeMethodProxy(methodProxy.getMethodName());
        }
    }

    public void removeAllMethodProxies() {
        this.mInternalMethodProxies.clear();
    }

    public <H extends MethodProxy> H getMethodProxy(String str) {
        H h = (H) this.mInternalMethodProxies.get(str);
        return h == null ? (H) this.mDefaultProxy : h;
    }

    public void setDefaultMethodProxy(MethodProxy methodProxy) {
        this.mDefaultProxy = methodProxy;
    }

    public T getProxyInterface() {
        return this.mProxyInterface;
    }

    public T getBaseInterface() {
        return this.mBaseInterface;
    }

    public int getMethodProxiesCount() {
        return this.mInternalMethodProxies.size();
    }

    /* loaded from: classes.dex */
    private class HookInvocationHandler implements InvocationHandler {
        private HookInvocationHandler() {
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x0094  */
        @Override // java.lang.reflect.InvocationHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object invoke(java.lang.Object r9, java.lang.reflect.Method r10, java.lang.Object[] r11) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 398
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.client.hook.base.MethodInvocationStub.HookInvocationHandler.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
        }
    }

    private void dumpMethodProxies() {
        StringBuilder sb = new StringBuilder(50);
        sb.append("*********************");
        for (MethodProxy methodProxy : this.mInternalMethodProxies.values()) {
            sb.append(methodProxy.getMethodName());
            sb.append("\n");
        }
        sb.append("*********************");
        VLog.m18992e(TAG, sb.toString());
    }
}
