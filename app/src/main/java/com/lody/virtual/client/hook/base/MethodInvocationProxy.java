package com.lody.virtual.client.hook.base;

import android.content.Context;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.annotations.LogInvocation;
import com.lody.virtual.client.hook.annotations.SkipInject;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.interfaces.IInjector;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes.dex */
public abstract class MethodInvocationProxy<T extends MethodInvocationStub> implements IInjector {
    protected T mInvocationStub;

    protected void afterHookApply(T t) {
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public abstract void inject() throws Throwable;

    public MethodInvocationProxy(T t) {
        this.mInvocationStub = t;
        onBindMethods();
        afterHookApply(t);
        LogInvocation logInvocation = (LogInvocation) getClass().getAnnotation(LogInvocation.class);
        if (logInvocation != null) {
            t.setInvocationLoggingCondition(logInvocation.value());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindMethods() {
        Inject inject;
        Class<?>[] declaredClasses;
        Method[] methods;
        if (!(this.mInvocationStub == null || (inject = (Inject) getClass().getAnnotation(Inject.class)) == null)) {
            Class<?> value = inject.value();
            for (Class<?> cls : value.getDeclaredClasses()) {
                if (!Modifier.isAbstract(cls.getModifiers()) && MethodProxy.class.isAssignableFrom(cls) && cls.getAnnotation(SkipInject.class) == null) {
                    addMethodProxy(cls);
                }
            }
            for (Method method : value.getMethods()) {
                if (Modifier.isStatic(method.getModifiers()) && method.getAnnotation(SkipInject.class) == null) {
                    addMethodProxy(new DirectCallMethodProxy(method));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DirectCallMethodProxy extends StaticMethodProxy {
        private Method directCallMethod;

        public DirectCallMethodProxy(Method method) {
            super(method.getName());
            this.directCallMethod = method;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return this.directCallMethod.invoke(null, obj, method, objArr);
        }
    }

    private void addMethodProxy(Class<?> cls) {
        try {
            Constructor<?> constructor = cls.getDeclaredConstructors()[0];
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            this.mInvocationStub.addMethodProxy(constructor.getParameterTypes().length == 0 ? (MethodProxy) constructor.newInstance(new Object[0]) : (MethodProxy) constructor.newInstance(this));
        } catch (Throwable th) {
            throw new RuntimeException("Unable to instance Hook : " + cls + " : " + th.getMessage());
        }
    }

    public MethodProxy addMethodProxy(MethodProxy methodProxy) {
        return this.mInvocationStub.addMethodProxy(methodProxy);
    }

    public void setDefaultMethodProxy(MethodProxy methodProxy) {
        this.mInvocationStub.setDefaultMethodProxy(methodProxy);
    }

    public Context getContext() {
        return VirtualCore.get().getContext();
    }

    public T getInvocationStub() {
        return this.mInvocationStub;
    }
}
