package com.lody.virtual.client.hook.proxies.notification;

import android.os.Build;
import android.os.IInterface;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgAndLastUserIdMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastUserIdMethodProxy;
import com.lody.virtual.helper.compat.BuildCompat;
import java.lang.reflect.Method;
import p110z1.NotificationManager;
import p110z1.Toast;

@Inject(MethodProxies.class)
/* loaded from: classes.dex */
public class NotificationManagerStub extends MethodInvocationProxy<MethodInvocationStub<IInterface>> {
    public NotificationManagerStub() {
        super(new MethodInvocationStub(NotificationManager.getService.call(new Object[0])));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("enqueueToast"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("enqueueToastEx"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("cancelToast"));
        addMethodProxy(new ReplaceLastUserIdMethodProxy("cancelAllNotifications"));
        if (Build.VERSION.SDK_INT >= 24) {
            addMethodProxy(new ReplaceCallingPkgMethodProxy("removeAutomaticZenRules"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("getImportance"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("areNotificationsEnabled"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("setNotificationPolicy"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("getNotificationPolicy"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("setNotificationPolicyAccessGranted"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("isNotificationPolicyAccessGranted"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("isNotificationPolicyAccessGrantedForPackage"));
        }
        if ("samsung".equalsIgnoreCase(Build.BRAND) || "samsung".equalsIgnoreCase(Build.MANUFACTURER)) {
            addMethodProxy(new ReplaceCallingPkgMethodProxy("removeEdgeNotification"));
        }
        if (BuildCompat.isOreo()) {
            addMethodProxy(new ReplaceCallingPkgMethodProxy("createNotificationChannelGroups"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("getNotificationChannelGroups"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("deleteNotificationChannelGroup"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("createNotificationChannels"));
            if (BuildCompat.isQ()) {
                addMethodProxy(new MethodProxy() { // from class: com.lody.virtual.client.hook.proxies.notification.NotificationManagerStub.1
                    @Override // com.lody.virtual.client.hook.base.MethodProxy
                    public String getMethodName() {
                        return "getNotificationChannels";
                    }

                    @Override // com.lody.virtual.client.hook.base.MethodProxy
                    public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                        objArr[0] = VirtualCore.get().getHostPkg();
                        objArr[1] = VirtualCore.get().getHostPkg();
                        replaceLastUserId(objArr);
                        return super.call(obj, method, objArr);
                    }
                });
            } else {
                addMethodProxy(new ReplaceCallingPkgAndLastUserIdMethodProxy("getNotificationChannels"));
            }
            if (BuildCompat.isQ()) {
                addMethodProxy(new MethodProxy() { // from class: com.lody.virtual.client.hook.proxies.notification.NotificationManagerStub.2
                    @Override // com.lody.virtual.client.hook.base.MethodProxy
                    public String getMethodName() {
                        return "getNotificationChannel";
                    }

                    @Override // com.lody.virtual.client.hook.base.MethodProxy
                    public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                        objArr[0] = VirtualCore.get().getHostPkg();
                        objArr[2] = VirtualCore.get().getHostPkg();
                        replaceLastUserId(objArr);
                        return super.call(obj, method, objArr);
                    }
                });
            } else {
                addMethodProxy(new ReplaceCallingPkgAndLastUserIdMethodProxy("getNotificationChannel"));
            }
            addMethodProxy(new ReplaceCallingPkgMethodProxy("deleteNotificationChannel"));
        }
        if (BuildCompat.isPie()) {
            addMethodProxy(new ReplaceCallingPkgMethodProxy("getNotificationChannelGroup"));
        }
        addMethodProxy(new ReplaceCallingPkgMethodProxy("setInterruptionFilter"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getPackageImportance"));
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        NotificationManager.sService.set(getInvocationStub().getProxyInterface());
        Toast.sService.set(getInvocationStub().getProxyInterface());
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return NotificationManager.getService.call(new Object[0]) != getInvocationStub().getProxyInterface();
    }
}
