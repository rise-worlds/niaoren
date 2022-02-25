package com.lody.virtual.client.hook.proxies.system;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import p110z1.ILockSettings;
import p110z1.ServiceManager;

/* loaded from: classes.dex */
public class LockSettingsStub extends BinderInvocationProxy {
    private static final String SERVICE_NAME = "lock_settings";

    public LockSettingsStub() {
        super(new EmptyLockSettings(), SERVICE_NAME);
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        if (ServiceManager.checkService.call(SERVICE_NAME) == null) {
            super.inject();
        }
    }

    /* loaded from: classes.dex */
    static class EmptyLockSettings extends ILockSettings.AbstractBinderC5275a {
        @Override // p110z1.ILockSettings
        public void setRecoverySecretTypes(int[] iArr) {
        }

        EmptyLockSettings() {
        }

        @Override // p110z1.ILockSettings
        public int[] getRecoverySecretTypes() {
            return new int[0];
        }
    }
}
