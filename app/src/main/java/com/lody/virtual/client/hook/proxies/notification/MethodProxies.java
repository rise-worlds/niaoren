package com.lody.virtual.client.hook.proxies.notification;

import android.app.Notification;
import android.os.Build;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.client.ipc.VNotificationManager;
import com.lody.virtual.helper.utils.ArrayUtils;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class MethodProxies {
    MethodProxies() {
    }

    /* loaded from: classes.dex */
    static class EnqueueNotification extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "enqueueNotification";
        }

        EnqueueNotification() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            replaceLastUserId(objArr);
            if (getHostPkg().equals(str)) {
                return method.invoke(obj, objArr);
            }
            int indexOfFirst = ArrayUtils.indexOfFirst(objArr, Notification.class);
            int indexOfFirst2 = ArrayUtils.indexOfFirst(objArr, Integer.class);
            int dealNotificationId = VNotificationManager.get().dealNotificationId(((Integer) objArr[indexOfFirst2]).intValue(), str, null, getAppUserId());
            objArr[indexOfFirst2] = Integer.valueOf(dealNotificationId);
            if (!VNotificationManager.get().dealNotification(dealNotificationId, (Notification) objArr[indexOfFirst], str)) {
                return 0;
            }
            VNotificationManager.get().addNotification(dealNotificationId, null, str, getAppUserId());
            objArr[0] = getHostPkg();
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class EnqueueNotificationWithTag extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "enqueueNotificationWithTag";
        }

        EnqueueNotificationWithTag() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            replaceLastUserId(objArr);
            if (getHostPkg().equals(str)) {
                return method.invoke(obj, objArr);
            }
            int indexOfFirst = ArrayUtils.indexOfFirst(objArr, Notification.class);
            int indexOfFirst2 = ArrayUtils.indexOfFirst(objArr, Integer.class);
            char c = Build.VERSION.SDK_INT >= 18 ? (char) 2 : (char) 1;
            int intValue = ((Integer) objArr[indexOfFirst2]).intValue();
            String str2 = (String) objArr[c];
            int dealNotificationId = VNotificationManager.get().dealNotificationId(intValue, str, str2, getAppUserId());
            String dealNotificationTag = VNotificationManager.get().dealNotificationTag(dealNotificationId, str, str2, getAppUserId());
            objArr[indexOfFirst2] = Integer.valueOf(dealNotificationId);
            objArr[c] = dealNotificationTag;
            if (!VNotificationManager.get().dealNotification(dealNotificationId, (Notification) objArr[indexOfFirst], str)) {
                return 0;
            }
            VNotificationManager.get().addNotification(dealNotificationId, dealNotificationTag, str, getAppUserId());
            objArr[0] = getHostPkg();
            if (Build.VERSION.SDK_INT >= 18 && (objArr[1] instanceof String)) {
                objArr[1] = getHostPkg();
            }
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class EnqueueNotificationWithTagPriority extends EnqueueNotificationWithTag {
        @Override // com.lody.virtual.client.hook.proxies.notification.MethodProxies.EnqueueNotificationWithTag, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "enqueueNotificationWithTagPriority";
        }

        EnqueueNotificationWithTagPriority() {
        }
    }

    /* loaded from: classes.dex */
    static class CancelNotificationWithTag extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "cancelNotificationWithTag";
        }

        CancelNotificationWithTag() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String replaceFirstAppPkg = MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            if (getHostPkg().equals(replaceFirstAppPkg)) {
                return method.invoke(obj, objArr);
            }
            String str = (String) objArr[1];
            int dealNotificationId = VNotificationManager.get().dealNotificationId(((Integer) objArr[2]).intValue(), replaceFirstAppPkg, str, getAppUserId());
            objArr[1] = VNotificationManager.get().dealNotificationTag(dealNotificationId, replaceFirstAppPkg, str, getAppUserId());
            objArr[2] = Integer.valueOf(dealNotificationId);
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class CancelAllNotifications extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "cancelAllNotifications";
        }

        CancelAllNotifications() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String replaceFirstAppPkg = MethodParameterUtils.replaceFirstAppPkg(objArr);
            if (VirtualCore.get().isAppInstalled(replaceFirstAppPkg)) {
                VNotificationManager.get().cancelAllNotification(replaceFirstAppPkg, getAppUserId());
                return 0;
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class AreNotificationsEnabledForPackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "areNotificationsEnabledForPackage";
        }

        AreNotificationsEnabledForPackage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            if (getHostPkg().equals(str)) {
                return method.invoke(obj, objArr);
            }
            return Boolean.valueOf(VNotificationManager.get().areNotificationsEnabledForPackage(str, getAppUserId()));
        }
    }

    /* loaded from: classes.dex */
    static class SetNotificationsEnabledForPackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setNotificationsEnabledForPackage";
        }

        SetNotificationsEnabledForPackage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            if (getHostPkg().equals(str)) {
                return method.invoke(obj, objArr);
            }
            VNotificationManager.get().setNotificationsEnabledForPackage(str, ((Boolean) objArr[ArrayUtils.indexOfFirst(objArr, Boolean.class)]).booleanValue(), getAppUserId());
            return 0;
        }
    }

    /* loaded from: classes.dex */
    static class GetAppActiveNotifications extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAppActiveNotifications";
        }

        GetAppActiveNotifications() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            objArr[0] = getHostPkg();
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }
    }
}
