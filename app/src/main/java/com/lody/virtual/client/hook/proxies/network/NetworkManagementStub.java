package com.lody.virtual.client.hook.proxies.network;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceUidMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import java.lang.reflect.Method;
import p110z1.INetworkManagementService;

@TargetApi(23)
/* loaded from: classes.dex */
public class NetworkManagementStub extends BinderInvocationProxy {
    public NetworkManagementStub() {
        super(INetworkManagementService.C5172a.asInterface, "network_management");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceUidMethodProxy("setUidCleartextNetworkPolicy", 0));
        addMethodProxy(new ReplaceUidMethodProxy("setUidMeteredNetworkBlacklist", 0));
        addMethodProxy(new ReplaceUidMethodProxy("setUidMeteredNetworkWhitelist", 0));
        addMethodProxy(new StaticMethodProxy("getNetworkStatsUidDetail") { // from class: com.lody.virtual.client.hook.proxies.network.NetworkManagementStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                if (((Integer) objArr[0]).intValue() == getVUid()) {
                    objArr[0] = Integer.valueOf(getRealUid());
                }
                return super.call(obj, method, objArr);
            }
        });
    }
}
