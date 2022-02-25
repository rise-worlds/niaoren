package com.lody.virtual.client.hook.proxies.dev_identifiers_policy;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import p110z1.IDeviceIdentifiersPolicyService;

@TargetApi(29)
/* loaded from: classes.dex */
public class DeviceIdentifiersPolicyServiceHub extends BinderInvocationProxy {
    public DeviceIdentifiersPolicyServiceHub() {
        super(IDeviceIdentifiersPolicyService.C5171a.asInterface, "device_identifiers");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getSerialForPackage"));
    }
}
