package com.lody.virtual.client.hook.proxies.accessibility;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import java.lang.reflect.Method;
import p110z1.IAccessibilityManager;

/* loaded from: classes.dex */
public class AccessibilityManagerStub extends BinderInvocationProxy {
    public AccessibilityManagerStub() {
        super(IAccessibilityManager.C5188a.TYPE, "accessibility");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceLastUserIdProxy("addClient"));
        addMethodProxy(new ReplaceLastUserIdProxy("sendAccessibilityEvent"));
        addMethodProxy(new ReplaceLastUserIdProxy("getInstalledAccessibilityServiceList"));
        addMethodProxy(new ReplaceLastUserIdProxy("getEnabledAccessibilityServiceList"));
        addMethodProxy(new ReplaceLastUserIdProxy("getWindowToken"));
        addMethodProxy(new ReplaceLastUserIdProxy("interrupt"));
        addMethodProxy(new ReplaceLastUserIdProxy("addAccessibilityInteractionConnection"));
    }

    /* loaded from: classes.dex */
    private static class ReplaceLastUserIdProxy extends StaticMethodProxy {
        public ReplaceLastUserIdProxy(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean beforeCall(Object obj, Method method, Object... objArr) {
            int length = objArr.length - 1;
            if (length >= 0 && (objArr[length] instanceof Integer)) {
                objArr[length] = Integer.valueOf(getRealUserId());
            }
            return super.beforeCall(obj, method, objArr);
        }
    }
}
