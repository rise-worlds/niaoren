package com.lody.virtual.client.hook.base;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ReplaceSpecPkgMethodProxy extends StaticMethodProxy {
    private int index;

    public ReplaceSpecPkgMethodProxy(String str, int i) {
        super(str);
        this.index = i;
    }

    @Override // com.lody.virtual.client.hook.base.MethodProxy
    public boolean beforeCall(Object obj, Method method, Object... objArr) {
        if (objArr != null) {
            int i = this.index;
            if (i < 0) {
                i += objArr.length;
            }
            if (i >= 0 && i < objArr.length && (objArr[i] instanceof String)) {
                objArr[i] = getHostPkg();
            }
        }
        return super.beforeCall(obj, method, objArr);
    }
}
