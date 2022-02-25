package com.lody.virtual.client.hook.proxies.telephony;

import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.proxies.telephony.MethodProxies;
import p110z1.IHwTelephony;

@Inject(MethodProxies.class)
/* loaded from: classes.dex */
public class HwTelephonyStub extends BinderInvocationProxy {
    public HwTelephonyStub() {
        super(IHwTelephony.C5203a.TYPE, "phone_huawei");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        addMethodProxy(new GetUniqueDeviceId());
    }

    /* loaded from: classes.dex */
    private static class GetUniqueDeviceId extends MethodProxies.GetDeviceId {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getUniqueDeviceId";
        }

        private GetUniqueDeviceId() {
        }
    }
}
