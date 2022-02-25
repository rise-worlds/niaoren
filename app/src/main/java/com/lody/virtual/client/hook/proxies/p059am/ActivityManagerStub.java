package com.lody.virtual.client.hook.proxies.p059am;

import android.os.IBinder;
import android.os.IInterface;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.annotations.LogInvocation;
import com.lody.virtual.client.hook.base.BinderInvocationStub;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.compat.BuildCompat;
import java.lang.reflect.Method;
import p110z1.ActivityManagerNative;
import p110z1.ActivityManagerOreo;
import p110z1.IActivityManager;
import p110z1.ServiceManager;
import p110z1.Singleton;

@Inject(MethodProxies.class)
@LogInvocation
/* renamed from: com.lody.virtual.client.hook.proxies.am.ActivityManagerStub */
/* loaded from: classes.dex */
public class ActivityManagerStub extends MethodInvocationProxy<MethodInvocationStub<IInterface>> {
    public ActivityManagerStub() {
        super(new MethodInvocationStub(ActivityManagerNative.getDefault.call(new Object[0])));
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() {
        if (BuildCompat.isOreo()) {
            Singleton.mInstance.set(ActivityManagerOreo.IActivityManagerSingleton.get(), getInvocationStub().getProxyInterface());
        } else if (ActivityManagerNative.gDefault.type() == IActivityManager.TYPE) {
            ActivityManagerNative.gDefault.set(getInvocationStub().getProxyInterface());
        } else if (ActivityManagerNative.gDefault.type() == Singleton.TYPE) {
            Singleton.mInstance.set(ActivityManagerNative.gDefault.get(), getInvocationStub().getProxyInterface());
        }
        BinderInvocationStub binderInvocationStub = new BinderInvocationStub(getInvocationStub().getBaseInterface());
        binderInvocationStub.copyMethodProxies(getInvocationStub());
        ServiceManager.sCache.get().put(ServiceManagerNative.ACTIVITY, binderInvocationStub);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        if (VirtualCore.get().isVAppProcess()) {
            addMethodProxy(new StaticMethodProxy("setRequestedOrientation") { // from class: com.lody.virtual.client.hook.proxies.am.ActivityManagerStub.1
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    try {
                        return super.call(obj, method, objArr);
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return 0;
                    }
                }
            });
            addMethodProxy(new ResultStaticMethodProxy("registerUidObserver", 0));
            addMethodProxy(new ResultStaticMethodProxy("unregisterUidObserver", 0));
            addMethodProxy(new ReplaceLastPkgMethodProxy("getAppStartMode"));
            addMethodProxy(new ResultStaticMethodProxy("updateConfiguration", 0));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("setAppLockedVerifying"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("reportJunkFromApp"));
            addMethodProxy(new StaticMethodProxy("activityResumed") { // from class: com.lody.virtual.client.hook.proxies.am.ActivityManagerStub.2
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    VActivityManager.get().onActivityResumed((IBinder) objArr[0]);
                    return super.call(obj, method, objArr);
                }
            });
            addMethodProxy(new StaticMethodProxy("activityDestroyed") { // from class: com.lody.virtual.client.hook.proxies.am.ActivityManagerStub.3
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    VActivityManager.get().onActivityDestroy((IBinder) objArr[0]);
                    return super.call(obj, method, objArr);
                }
            });
            addMethodProxy(new StaticMethodProxy("checkUriPermission") { // from class: com.lody.virtual.client.hook.proxies.am.ActivityManagerStub.4
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    return 0;
                }
            });
            addMethodProxy(new StaticMethodProxy("finishActivity") { // from class: com.lody.virtual.client.hook.proxies.am.ActivityManagerStub.5
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    VActivityManager.get().onFinishActivity((IBinder) objArr[0]);
                    return super.call(obj, method, objArr);
                }

                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public boolean isEnable() {
                    return isAppProcess();
                }
            });
            addMethodProxy(new StaticMethodProxy("finishActivityAffinity") { // from class: com.lody.virtual.client.hook.proxies.am.ActivityManagerStub.6
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) {
                    return Boolean.valueOf(VActivityManager.get().finishActivityAffinity(getAppUserId(), (IBinder) objArr[0]));
                }

                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public boolean isEnable() {
                    return isAppProcess();
                }
            });
        }
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return ActivityManagerNative.getDefault.call(new Object[0]) != getInvocationStub().getProxyInterface();
    }
}
