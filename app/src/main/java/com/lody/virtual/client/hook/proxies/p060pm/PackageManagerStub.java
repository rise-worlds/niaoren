package com.lody.virtual.client.hook.proxies.p060pm;

import android.content.Context;
import android.os.IInterface;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationStub;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.Reflect;
import p110z1.ActivityThread;

@Inject(MethodProxies.class)
/* renamed from: com.lody.virtual.client.hook.proxies.pm.PackageManagerStub */
/* loaded from: classes.dex */
public final class PackageManagerStub extends MethodInvocationProxy<MethodInvocationStub<IInterface>> {
    public PackageManagerStub() {
        super(new MethodInvocationStub(ActivityThread.sPackageManager.get()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ResultStaticMethodProxy("addPermissionAsync", true));
        addMethodProxy(new ResultStaticMethodProxy("addPermission", true));
        addMethodProxy(new ResultStaticMethodProxy("performDexOpt", true));
        addMethodProxy(new ResultStaticMethodProxy("performDexOptIfNeeded", false));
        addMethodProxy(new ResultStaticMethodProxy("performDexOptSecondary", true));
        addMethodProxy(new ResultStaticMethodProxy("addOnPermissionsChangeListener", 0));
        addMethodProxy(new ResultStaticMethodProxy("removeOnPermissionsChangeListener", 0));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("shouldShowRequestPermissionRationale"));
        if (BuildCompat.isOreo()) {
            addMethodProxy(new ResultStaticMethodProxy("notifyDexLoad", 0));
            addMethodProxy(new ResultStaticMethodProxy("notifyPackageUse", 0));
            addMethodProxy(new ResultStaticMethodProxy("setInstantAppCookie", false));
            addMethodProxy(new ResultStaticMethodProxy("isInstantApp", false));
        }
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        IInterface proxyInterface = getInvocationStub().getProxyInterface();
        ActivityThread.sPackageManager.set(proxyInterface);
        BinderInvocationStub binderInvocationStub = new BinderInvocationStub(getInvocationStub().getBaseInterface());
        binderInvocationStub.copyMethodProxies(getInvocationStub());
        binderInvocationStub.replaceService(ServiceManagerNative.PACKAGE);
        try {
            Context context = (Context) Reflect.m18998on(VirtualCore.mainThread()).call("getSystemContext").get();
            if (Reflect.m18998on(context).field("mPackageManager").get() != null) {
                Reflect.m18998on(context).field("mPackageManager").set("mPM", proxyInterface);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return getInvocationStub().getProxyInterface() != ActivityThread.sPackageManager.get();
    }
}
