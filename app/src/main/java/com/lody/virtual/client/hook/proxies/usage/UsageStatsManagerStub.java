package com.lody.virtual.client.hook.proxies.usage;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.client.ipc.VActivityManager;
import java.lang.reflect.Method;
import p110z1.IUsageStatsManager;

@TargetApi(22)
/* loaded from: classes.dex */
public class UsageStatsManagerStub extends BinderInvocationProxy {
    public UsageStatsManagerStub() {
        super(IUsageStatsManager.C5119a.asInterface, "usagestats");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceLastPkgMethodProxy("queryUsageStats"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("queryConfigurations"));
        addMethodProxy(new ReplaceLastPkgMethodProxy("queryEvents"));
        addMethodProxy(new StaticMethodProxy("setAppInactive") { // from class: com.lody.virtual.client.hook.proxies.usage.UsageStatsManagerStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) {
                VActivityManager.get().setAppInactive((String) objArr[0], ((Boolean) objArr[1]).booleanValue(), objArr.length > 2 ? ((Integer) objArr[2]).intValue() : 0);
                return 0;
            }
        });
        addMethodProxy(new StaticMethodProxy("isAppInactive") { // from class: com.lody.virtual.client.hook.proxies.usage.UsageStatsManagerStub.2
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) {
                return Boolean.valueOf(VActivityManager.get().isAppInactive((String) objArr[0], objArr.length > 1 ? ((Integer) objArr[1]).intValue() : 0));
            }
        });
        addMethodProxy(new ReplacePkgAndUserIdMethodProxy("whitelistAppTemporarily"));
    }

    /* loaded from: classes.dex */
    private class ReplacePkgAndUserIdMethodProxy extends ReplaceLastPkgMethodProxy {
        public ReplacePkgAndUserIdMethodProxy(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (objArr[objArr.length - 1] instanceof Integer) {
                objArr[objArr.length - 1] = 0;
            }
            return super.call(obj, method, objArr);
        }
    }
}
