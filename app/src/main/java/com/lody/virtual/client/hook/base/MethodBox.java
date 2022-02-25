package com.lody.virtual.client.hook.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class MethodBox {
    public final Object[] args;
    public final Method method;
    public final Object who;

    public MethodBox(Method method, Object obj, Object[] objArr) {
        this.method = method;
        this.who = obj;
        this.args = objArr;
    }

    public <T> T call() throws InvocationTargetException {
        try {
            return (T) this.method.invoke(this.who, this.args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T callSafe() {
        try {
            return (T) this.method.invoke(this.who, this.args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
