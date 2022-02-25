package com.lody.virtual.client.hook.proxies.atm;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.IBinder;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.annotations.LogInvocation;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.p061os.VUserHandle;
import java.lang.reflect.Method;
import p110z1.IActivityTaskManager;

@Inject(MethodProxies.class)
@TargetApi(29)
@LogInvocation
/* loaded from: classes.dex */
public class ActivityTaskManagerStub extends BinderInvocationProxy {
    public ActivityTaskManagerStub() {
        super(IActivityTaskManager.C5114a.asInterface, "activity_task");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new StaticMethodProxy("activityDestroyed") { // from class: com.lody.virtual.client.hook.proxies.atm.ActivityTaskManagerStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                VActivityManager.get().onActivityDestroy((IBinder) objArr[0]);
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new StaticMethodProxy("activityResumed") { // from class: com.lody.virtual.client.hook.proxies.atm.ActivityTaskManagerStub.2
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                VActivityManager.get().onActivityResumed((IBinder) objArr[0]);
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new StaticMethodProxy("finishActivity") { // from class: com.lody.virtual.client.hook.proxies.atm.ActivityTaskManagerStub.3
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                IBinder iBinder = (IBinder) objArr[0];
                Intent intent = (Intent) objArr[2];
                if (intent != null) {
                    objArr[2] = ComponentUtils.processOutsideIntent(VUserHandle.myUserId(), VirtualCore.get().is64BitEngine(), intent);
                }
                VActivityManager.get().onFinishActivity(iBinder);
                return super.call(obj, method, objArr);
            }

            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public boolean isEnable() {
                return isAppProcess();
            }
        });
        addMethodProxy(new StaticMethodProxy("finishActivityAffinity") { // from class: com.lody.virtual.client.hook.proxies.atm.ActivityTaskManagerStub.4
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
