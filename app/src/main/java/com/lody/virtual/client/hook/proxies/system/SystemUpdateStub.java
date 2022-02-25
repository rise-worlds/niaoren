package com.lody.virtual.client.hook.proxies.system;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.p002os.ISystemUpdateManager;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import p110z1.ServiceManager;

/* loaded from: classes.dex */
public class SystemUpdateStub extends BinderInvocationProxy {
    private static final String SERVICE_NAME = "system_update";

    public SystemUpdateStub() {
        super(new EmptySystemUpdateManagerImpl(), SERVICE_NAME);
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        if (ServiceManager.checkService.call(SERVICE_NAME) == null) {
            super.inject();
        }
    }

    /* loaded from: classes.dex */
    static class EmptySystemUpdateManagerImpl extends ISystemUpdateManager.Stub {
        @Override // android.p002os.ISystemUpdateManager
        public void updateSystemUpdateInfo(PersistableBundle persistableBundle) {
        }

        EmptySystemUpdateManagerImpl() {
        }

        @Override // android.p002os.ISystemUpdateManager
        public Bundle retrieveSystemUpdateInfo() {
            Bundle bundle = new Bundle();
            bundle.putInt("status", 0);
            return bundle;
        }
    }
}
