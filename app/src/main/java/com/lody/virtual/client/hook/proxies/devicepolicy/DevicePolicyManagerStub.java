package com.lody.virtual.client.hook.proxies.devicepolicy;

import android.content.ComponentName;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import java.lang.reflect.Method;
import p110z1.IDevicePolicyManager;

/* loaded from: classes.dex */
public class DevicePolicyManagerStub extends BinderInvocationProxy {
    public DevicePolicyManagerStub() {
        super(IDevicePolicyManager.C5125a.asInterface, "device_policy");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new GetStorageEncryptionStatus());
        addMethodProxy(new GetDeviceOwnerComponent());
    }

    /* loaded from: classes.dex */
    private static class GetDeviceOwnerComponent extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getDeviceOwnerComponent";
        }

        private GetDeviceOwnerComponent() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return new ComponentName(getAppPkg(), "");
        }
    }

    /* loaded from: classes.dex */
    private static class GetStorageEncryptionStatus extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getStorageEncryptionStatus";
        }

        private GetStorageEncryptionStatus() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            objArr[0] = VirtualCore.get().getHostPkg();
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }
    }
}
