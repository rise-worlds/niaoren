package com.lody.virtual.client.hook.base;

/* loaded from: classes.dex */
public class StaticMethodProxy extends MethodProxy {
    private String mName;

    public StaticMethodProxy(String str) {
        this.mName = str;
    }

    @Override // com.lody.virtual.client.hook.base.MethodProxy
    public String getMethodName() {
        return this.mName;
    }
}
