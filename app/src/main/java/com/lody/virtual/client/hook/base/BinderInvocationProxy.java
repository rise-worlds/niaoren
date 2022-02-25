package com.lody.virtual.client.hook.base;

import android.os.IBinder;
import android.os.IInterface;
import com.lody.virtual.helper.utils.VLog;
import mirror.RefStaticMethod;
import p110z1.ServiceManager;

/* loaded from: classes.dex */
public abstract class BinderInvocationProxy extends MethodInvocationProxy<BinderInvocationStub> {
    protected String mServiceName;

    public BinderInvocationProxy(IInterface iInterface, String str) {
        this(new BinderInvocationStub(iInterface), str);
    }

    public BinderInvocationProxy(RefStaticMethod<IInterface> kVar, String str) {
        this(new BinderInvocationStub(kVar, getService(str)), str);
    }

    public BinderInvocationProxy(Class<?> cls, String str) {
        this(new BinderInvocationStub(cls, getService(str)), str);
    }

    private static IBinder getService(String str) {
        return ServiceManager.getService.call(str);
    }

    public BinderInvocationProxy(BinderInvocationStub binderInvocationStub, String str) {
        super(binderInvocationStub);
        if (binderInvocationStub.getBaseInterface() == null) {
            VLog.m18993d("BinderInvocationProxy", "Unable to build HookDelegate: %s.", str);
        }
        this.mServiceName = str;
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        getInvocationStub().replaceService(this.mServiceName);
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        IBinder call = ServiceManager.getService.call(this.mServiceName);
        return (call == null || getInvocationStub() == call) ? false : true;
    }
}
