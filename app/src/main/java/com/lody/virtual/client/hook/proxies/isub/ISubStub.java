package com.lody.virtual.client.hook.proxies.isub;

import android.os.Build;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import java.lang.reflect.Method;
import p110z1.ISub;

/* loaded from: classes.dex */
public class ISubStub extends BinderInvocationProxy {
    public ISubStub() {
        super(ISub.C5207a.asInterface, "isub");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getAllSubInfoList"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getAllSubInfoCount"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getActiveSubscriptionInfo"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getActiveSubscriptionInfoForIccId"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getActiveSubscriptionInfoForSimSlotIndex"));
        addMethodProxy(new StaticMethodProxy("getActiveSubscriptionInfoList") { // from class: com.lody.virtual.client.hook.proxies.isub.ISubStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) {
                try {
                    return super.call(obj, method, objArr);
                } catch (Throwable th) {
                    th.printStackTrace();
                    return null;
                }
            }
        });
        addMethodProxy(new ReplaceLastPkgMethodProxy("getActiveSubInfoCount"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("getSubscriptionProperty"));
        addMethodProxy(new StaticMethodProxy(Build.VERSION.SDK_INT >= 24 ? "getSimStateForSlotIdx" : "getSimStateForSubscriber"));
    }
}
