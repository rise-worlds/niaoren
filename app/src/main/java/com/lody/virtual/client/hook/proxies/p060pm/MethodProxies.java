package com.lody.virtual.client.hook.proxies.p060pm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.content.p001pm.IPackageDataObserver;
import android.content.p001pm.IPackageDeleteObserver2;
import android.content.p001pm.IPackageInstallerCallback;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.fixer.ComponentFixer;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.ParceledListSliceCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.os.VBinder;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.server.IPackageInstaller;
import com.lody.virtual.server.pm.installer.SessionInfo;
import com.lody.virtual.server.pm.installer.SessionParams;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.ParceledListSlice;
import patch.MyFixer;

/* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies */
/* loaded from: classes.dex */
class MethodProxies {
    MethodProxies() {
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$FreeStorage */
    /* loaded from: classes.dex */
    static class FreeStorage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "freeStorage";
        }

        FreeStorage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IntentSender intentSender = (IntentSender) ArrayUtils.getFirst(objArr, IntentSender.class);
            if (intentSender != null) {
                intentSender.sendIntent(getHostContext(), 0, null, null, null);
            }
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$FreeStorageAndNotify */
    /* loaded from: classes.dex */
    static class FreeStorageAndNotify extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "freeStorageAndNotify";
        }

        FreeStorageAndNotify() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IPackageDataObserver iPackageDataObserver = (IPackageDataObserver) ArrayUtils.getFirst(objArr, IPackageDataObserver.class);
            if (iPackageDataObserver != null) {
                iPackageDataObserver.onRemoveCompleted(getAppPkg(), true);
            }
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$CheckPackageStartable */
    /* loaded from: classes.dex */
    static class CheckPackageStartable extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "checkPackageStartable";
        }

        CheckPackageStartable() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isAppPkg((String) objArr[0])) {
                return 0;
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetUidForSharedUser */
    /* loaded from: classes.dex */
    static class GetUidForSharedUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getUidForSharedUser";
        }

        GetUidForSharedUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Integer.valueOf(VirtualCore.get().getUidForSharedUser((String) objArr[0]));
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$CanForwardTo */
    /* loaded from: classes.dex */
    static class CanForwardTo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "canForwardTo";
        }

        CanForwardTo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Boolean.valueOf(((Integer) objArr[2]).intValue() == ((Integer) objArr[3]).intValue());
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$IsPackageAvailable */
    /* loaded from: classes.dex */
    static class IsPackageAvailable extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "isPackageAvailable";
        }

        IsPackageAvailable() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isAppPkg((String) objArr[0])) {
                return true;
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetInstallerPackageName */
    /* loaded from: classes.dex */
    static class GetInstallerPackageName extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return GmsSupport.VENDING_PKG;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getInstallerPackageName";
        }

        GetInstallerPackageName() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPreferredActivities */
    /* loaded from: classes.dex */
    static class GetPreferredActivities extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPreferredActivities";
        }

        GetPreferredActivities() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceLastAppPkg(objArr);
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetComponentEnabledSetting */
    /* loaded from: classes.dex */
    static class GetComponentEnabledSetting extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getComponentEnabledSetting";
        }

        GetComponentEnabledSetting() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Integer.valueOf(VPackageManager.get().getComponentEnabledSetting((ComponentName) objArr[0], getAppUserId()));
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$RemovePackageFromPreferred */
    /* loaded from: classes.dex */
    static class RemovePackageFromPreferred extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "removePackageFromPreferred";
        }

        RemovePackageFromPreferred() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetServiceInfo */
    /* loaded from: classes.dex */
    static class GetServiceInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getServiceInfo";
        }

        GetServiceInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[1]).intValue();
            int myUserId = VUserHandle.myUserId();
            ServiceInfo serviceInfo = VPackageManager.get().getServiceInfo((ComponentName) objArr[0], intValue, myUserId);
            if (serviceInfo != null) {
                return serviceInfo;
            }
            replaceLastUserId(objArr);
            ServiceInfo serviceInfo2 = (ServiceInfo) method.invoke(obj, objArr);
            if (serviceInfo2 == null || !isVisiblePackage(serviceInfo2.applicationInfo)) {
                return null;
            }
            ComponentFixer.fixOutsideComponentInfo(serviceInfo2);
            return serviceInfo2;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPackageUid */
    /* loaded from: classes.dex */
    static class GetPackageUid extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageUid";
        }

        GetPackageUid() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            replaceLastUserId(objArr);
            if (str.equals(getHostPkg())) {
                return method.invoke(obj, objArr);
            }
            if (isAppPkg(str)) {
                return Integer.valueOf(VUserHandle.getAppId(VPackageManager.get().getPackageUid(str, 0)));
            }
            if (isVisiblePackage(str)) {
                return method.invoke(obj, objArr);
            }
            return -1;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetActivityInfo */
    /* loaded from: classes.dex */
    static class GetActivityInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getActivityInfo";
        }

        GetActivityInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ComponentName componentName = (ComponentName) objArr[0];
            if (getHostPkg().equals(componentName.getPackageName())) {
                return method.invoke(obj, objArr);
            }
            int myUserId = VUserHandle.myUserId();
            ActivityInfo activityInfo = VPackageManager.get().getActivityInfo(componentName, ((Integer) objArr[1]).intValue(), myUserId);
            if (activityInfo == null) {
                replaceLastUserId(objArr);
                activityInfo = (ActivityInfo) method.invoke(obj, objArr);
                if (activityInfo == null || !isVisiblePackage(activityInfo.applicationInfo)) {
                    return null;
                }
                ComponentFixer.fixOutsideComponentInfo(activityInfo);
            }
            return activityInfo;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPackageUidEtc */
    /* loaded from: classes.dex */
    static class GetPackageUidEtc extends GetPackageUid {
        GetPackageUidEtc() {
        }

        @Override // com.lody.virtual.client.hook.proxies.p060pm.MethodProxies.GetPackageUid, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return super.getMethodName() + "Etc";
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPackageInstaller */
    /* loaded from: classes.dex */
    static class GetPackageInstaller extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageInstaller";
        }

        GetPackageInstaller() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IInterface iInterface = (IInterface) method.invoke(obj, objArr);
            final IPackageInstaller packageInstaller = VPackageManager.get().getPackageInstaller();
            return Proxy.newProxyInstance(iInterface.getClass().getClassLoader(), iInterface.getClass().getInterfaces(), new InvocationHandler() { // from class: com.lody.virtual.client.hook.proxies.pm.MethodProxies.GetPackageInstaller.1
                @TargetApi(21)
                private Object createSession(Object obj2, Method method2, Object[] objArr2) throws RemoteException {
                    return Integer.valueOf(packageInstaller.createSession(SessionParams.create((PackageInstaller.SessionParams) objArr2[0]), (String) objArr2[1], VUserHandle.myUserId()));
                }

                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj2, Method method2, Object[] objArr2) throws Throwable {
                    char c;
                    String name = method2.getName();
                    switch (name.hashCode()) {
                        case -1776922004:
                            if (name.equals("toString")) {
                                c = 11;
                                break;
                            }
                            c = 65535;
                            break;
                        case -663066834:
                            if (name.equals("getSessionInfo")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case -652885011:
                            if (name.equals("updateSessionAppIcon")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case -403218424:
                            if (name.equals("registerCallback")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case -93516191:
                            if (name.equals("abandonSession")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -63461894:
                            if (name.equals("createSession")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 938656808:
                            if (name.equals("getAllSessions")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1170196863:
                            if (name.equals("setPermissionsResult")) {
                                c = '\n';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1238099456:
                            if (name.equals("updateSessionAppLabel")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1568181855:
                            if (name.equals("getMySessions")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1738611873:
                            if (name.equals("unregisterCallback")) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1788161260:
                            if (name.equals("openSession")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            return createSession(obj2, method2, objArr2);
                        case 1:
                            packageInstaller.updateSessionAppIcon(((Integer) objArr2[0]).intValue(), (Bitmap) objArr2[1]);
                            return 0;
                        case 2:
                            packageInstaller.updateSessionAppLabel(((Integer) objArr2[0]).intValue(), (String) objArr2[1]);
                            return 0;
                        case 3:
                            packageInstaller.abandonSession(((Integer) objArr2[0]).intValue());
                            return 0;
                        case 4:
                            return packageInstaller.openSession(((Integer) objArr2[0]).intValue());
                        case 5:
                            SessionInfo sessionInfo = packageInstaller.getSessionInfo(((Integer) objArr2[0]).intValue());
                            if (sessionInfo != null) {
                                return sessionInfo.alloc();
                            }
                            return null;
                        case 6:
                            List<SessionInfo> list = packageInstaller.getAllSessions(((Integer) objArr2[0]).intValue()).getList();
                            ArrayList arrayList = new ArrayList(list.size());
                            for (SessionInfo sessionInfo2 : list) {
                                arrayList.add(sessionInfo2.alloc());
                            }
                            return ParceledListSliceCompat.create(arrayList);
                        case 7:
                            int intValue = ((Integer) objArr2[1]).intValue();
                            List<SessionInfo> list2 = packageInstaller.getMySessions((String) objArr2[0], intValue).getList();
                            ArrayList arrayList2 = new ArrayList(list2.size());
                            for (SessionInfo sessionInfo3 : list2) {
                                arrayList2.add(sessionInfo3.alloc());
                            }
                            return ParceledListSliceCompat.create(arrayList2);
                        case '\b':
                            packageInstaller.registerCallback((IPackageInstallerCallback) objArr2[0], VUserHandle.myUserId());
                            return 0;
                        case '\t':
                            packageInstaller.unregisterCallback((IPackageInstallerCallback) objArr2[0]);
                            return 0;
                        case '\n':
                            packageInstaller.setPermissionsResult(((Integer) objArr2[0]).intValue(), ((Boolean) objArr2[1]).booleanValue());
                            return 0;
                        case 11:
                            return "VPackageInstaller";
                        default:
                            throw new RuntimeException("Not support PackageInstaller method : " + method2.getName());
                    }
                }
            });
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPackageGids */
    /* loaded from: classes.dex */
    static class GetPackageGids extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageGids";
        }

        GetPackageGids() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$RevokeRuntimePermission */
    /* loaded from: classes.dex */
    static class RevokeRuntimePermission extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "revokeRuntimePermission";
        }

        RevokeRuntimePermission() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$ClearPackagePreferredActivities */
    /* loaded from: classes.dex */
    static class ClearPackagePreferredActivities extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "clearPackagePreferredActivities";
        }

        ClearPackagePreferredActivities() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$ResolveContentProvider */
    /* loaded from: classes.dex */
    static class ResolveContentProvider extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "resolveContentProvider";
        }

        ResolveContentProvider() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            if (str != null && str.equals("com.huawei.hms.dynamic.moduleFileProvider")) {
                return null;
            }
            ProviderInfo resolveContentProvider = VPackageManager.get().resolveContentProvider(str, ((Integer) objArr[1]).intValue(), VUserHandle.myUserId());
            if (resolveContentProvider == null) {
                replaceLastUserId(objArr);
                resolveContentProvider = (ProviderInfo) method.invoke(obj, objArr);
                if (resolveContentProvider != null && isVisiblePackage(resolveContentProvider.applicationInfo)) {
                    return resolveContentProvider;
                }
            } else if (!resolveContentProvider.enabled) {
                return null;
            }
            return resolveContentProvider;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$QueryIntentServices */
    /* loaded from: classes.dex */
    static class QueryIntentServices extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "queryIntentServices";
        }

        QueryIntentServices() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean isReturnParceledListSlice = ParceledListSliceCompat.isReturnParceledListSlice(method);
            List<ResolveInfo> queryIntentServices = VPackageManager.get().queryIntentServices((Intent) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue(), VUserHandle.myUserId());
            replaceLastUserId(objArr);
            Object invoke = method.invoke(obj, objArr);
            if (invoke != null) {
                if (isReturnParceledListSlice) {
                    invoke = ParceledListSlice.getList.call(invoke, new Object[0]);
                }
                List list = (List) invoke;
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ResolveInfo resolveInfo = (ResolveInfo) it.next();
                        if (resolveInfo == null || resolveInfo.serviceInfo == null || !isVisiblePackage(resolveInfo.serviceInfo.applicationInfo)) {
                            it.remove();
                        }
                    }
                    queryIntentServices.addAll(list);
                }
            }
            return isReturnParceledListSlice ? ParceledListSliceCompat.create(queryIntentServices) : queryIntentServices;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPermissions */
    /* loaded from: classes.dex */
    static class GetPermissions extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPermissions";
        }

        GetPermissions() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$IsPackageForzen */
    /* loaded from: classes.dex */
    static class IsPackageForzen extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "isPackageForzen";
        }

        IsPackageForzen() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return false;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPackageGidsEtc */
    /* loaded from: classes.dex */
    static class GetPackageGidsEtc extends GetPackageGids {
        GetPackageGidsEtc() {
        }

        @Override // com.lody.virtual.client.hook.proxies.p060pm.MethodProxies.GetPackageGids, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return super.getMethodName() + "Etc";
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$QueryIntentActivities */
    /* loaded from: classes.dex */
    static class QueryIntentActivities extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "queryIntentActivities";
        }

        QueryIntentActivities() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean isReturnParceledListSlice = ParceledListSliceCompat.isReturnParceledListSlice(method);
            List<ResolveInfo> queryIntentActivities = VPackageManager.get().queryIntentActivities((Intent) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue(), VUserHandle.myUserId());
            replaceLastUserId(objArr);
            Object invoke = method.invoke(obj, objArr);
            if (invoke != null) {
                if (isReturnParceledListSlice) {
                    invoke = ParceledListSlice.getList.call(invoke, new Object[0]);
                }
                List list = (List) invoke;
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ResolveInfo resolveInfo = (ResolveInfo) it.next();
                        if (resolveInfo == null || resolveInfo.activityInfo == null || !isVisiblePackage(resolveInfo.activityInfo.applicationInfo)) {
                            it.remove();
                        } else {
                            ComponentFixer.fixOutsideComponentInfo(resolveInfo.activityInfo);
                        }
                    }
                    queryIntentActivities.addAll(list);
                }
            }
            return isReturnParceledListSlice ? ParceledListSliceCompat.create(queryIntentActivities) : queryIntentActivities;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$ResolveService */
    /* loaded from: classes.dex */
    static class ResolveService extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "resolveService";
        }

        ResolveService() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[2]).intValue();
            int myUserId = VUserHandle.myUserId();
            ResolveInfo resolveService = VPackageManager.get().resolveService((Intent) objArr[0], (String) objArr[1], intValue, myUserId);
            if (resolveService != null) {
                return resolveService;
            }
            replaceLastUserId(objArr);
            ResolveInfo resolveInfo = (ResolveInfo) method.invoke(obj, objArr);
            if (resolveInfo == null || !isVisiblePackage(resolveInfo.serviceInfo.applicationInfo)) {
                return null;
            }
            ComponentFixer.fixOutsideComponentInfo(resolveInfo.serviceInfo);
            return resolveInfo;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$ClearPackagePersistentPreferredActivities */
    /* loaded from: classes.dex */
    static class ClearPackagePersistentPreferredActivities extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "clearPackagePersistentPreferredActivities";
        }

        ClearPackagePersistentPreferredActivities() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPermissionGroupInfo */
    /* loaded from: classes.dex */
    static class GetPermissionGroupInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPermissionGroupInfo";
        }

        GetPermissionGroupInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            PermissionGroupInfo permissionGroupInfo = VPackageManager.get().getPermissionGroupInfo((String) objArr[0], ((Integer) objArr[1]).intValue());
            return permissionGroupInfo != null ? permissionGroupInfo : super.call(obj, method, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPermissionInfo */
    /* loaded from: classes.dex */
    static class GetPermissionInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPermissionInfo";
        }

        GetPermissionInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            PermissionInfo permissionInfo = VPackageManager.get().getPermissionInfo((String) objArr[0], ((Integer) objArr[objArr.length - 1]).intValue());
            return permissionInfo != null ? permissionInfo : super.call(obj, method, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPackageInfo */
    /* loaded from: classes.dex */
    static final class GetPackageInfo extends MethodProxy {
        private static final int MATCH_ANY_USER = 4194304;
        private static final int MATCH_FACTORY_ONLY = 2097152;

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageInfo";
        }

        GetPackageInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean beforeCall(Object obj, Method method, Object... objArr) {
            return (objArr == null || objArr[0] == null) ? false : true;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            int intValue = ((Integer) objArr[1]).intValue();
            int myUserId = VUserHandle.myUserId();
            if ((4194304 & intValue) != 0) {
                intValue &= -4194305;
                objArr[1] = Integer.valueOf(intValue);
            }
            if ((2097152 & intValue) != 0) {
                replaceLastUserId(objArr);
                return method.invoke(obj, objArr);
            }
            PackageInfo packageInfo = VPackageManager.get().getPackageInfo(str, intValue, myUserId);
            if (packageInfo != null && "com.huawei.hwid".equals(str)) {
                try {
                    PackageInfo packageInfo2 = VirtualCore.get().getUnHookPackageManager().getPackageInfo(str, intValue);
                    if (packageInfo2 != null) {
                        packageInfo.signatures = packageInfo2.signatures;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (packageInfo != null) {
                if (BuildCompat.isPie() && (134217728 & intValue) != 0 && Reflect.m18998on(packageInfo).get("signingInfo") == null) {
                    Reflect.m18998on(packageInfo).set("signingInfo", Reflect.m18998on(VirtualCore.get().getUnHookPackageManager().getPackageInfo(str, intValue)).get("signingInfo"));
                }
                return packageInfo;
            }
            replaceLastUserId(objArr);
            PackageInfo packageInfo3 = (PackageInfo) method.invoke(obj, objArr);
            if (packageInfo3 == null || !isVisiblePackage(packageInfo3.applicationInfo)) {
                return null;
            }
            ComponentFixer.fixOutsideApplicationInfo(packageInfo3.applicationInfo);
            return packageInfo3;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$DeleteApplicationCacheFiles */
    /* loaded from: classes.dex */
    static class DeleteApplicationCacheFiles extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "deleteApplicationCacheFiles";
        }

        DeleteApplicationCacheFiles() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ApplicationInfo applicationInfo;
            String str = (String) objArr[0];
            IPackageDataObserver iPackageDataObserver = (IPackageDataObserver) objArr[1];
            if (!str.equals(getAppPkg()) || (applicationInfo = VPackageManager.get().getApplicationInfo(str, 0, getAppUserId())) == null) {
                return method.invoke(obj, objArr);
            }
            File file = new File(applicationInfo.dataDir);
            FileUtils.deleteDir(file);
            file.mkdirs();
            if (Build.VERSION.SDK_INT >= 24) {
                File file2 = new File(applicationInfo.deviceProtectedDataDir);
                FileUtils.deleteDir(file2);
                file2.mkdirs();
            }
            if (iPackageDataObserver != null) {
                iPackageDataObserver.onRemoveCompleted(str, true);
            }
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$SetApplicationBlockedSettingAsUser */
    /* loaded from: classes.dex */
    static class SetApplicationBlockedSettingAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setApplicationBlockedSettingAsUser";
        }

        SetApplicationBlockedSettingAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetApplicationEnabledSetting */
    /* loaded from: classes.dex */
    static class GetApplicationEnabledSetting extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getApplicationEnabledSetting";
        }

        GetApplicationEnabledSetting() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int index = MethodParameterUtils.getIndex(objArr, String.class);
            if (index < 0) {
                return false;
            }
            String str = (String) objArr[index];
            if (isAppPkg(str)) {
                return 1;
            }
            if (!isVisiblePackage(str)) {
                return 2;
            }
            objArr[1] = 0;
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$CanRequestPackageInstalls */
    /* loaded from: classes.dex */
    static class CanRequestPackageInstalls extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "canRequestPackageInstalls";
        }

        CanRequestPackageInstalls() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$AddPackageToPreferred */
    /* loaded from: classes.dex */
    static class AddPackageToPreferred extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "addPackageToPreferred";
        }

        AddPackageToPreferred() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return 0;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$CheckPermission */
    /* loaded from: classes.dex */
    static class CheckPermission extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "checkPermission";
        }

        CheckPermission() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int myUserId = VUserHandle.myUserId();
            return Integer.valueOf(VPackageManager.get().checkPermission((String) objArr[0], (String) objArr[1], myUserId));
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            return super.afterCall(obj, method, objArr, obj2);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPackagesForUid */
    /* loaded from: classes.dex */
    static class GetPackagesForUid extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackagesForUid";
        }

        GetPackagesForUid() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[0]).intValue();
            if (intValue == getRealUid()) {
                intValue = VBinder.getCallingUid();
            }
            String[] packagesForUid = VPackageManager.get().getPackagesForUid(intValue);
            return packagesForUid == null ? VirtualCore.get().getUnHookPackageManager().getPackagesForUid(intValue) : packagesForUid;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$QuerySliceContentProviders */
    /* loaded from: classes.dex */
    static class QuerySliceContentProviders extends QueryContentProviders {
        @Override // com.lody.virtual.client.hook.proxies.p060pm.MethodProxies.QueryContentProviders, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "querySliceContentProviders";
        }

        QuerySliceContentProviders() {
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPersistentApplications */
    /* loaded from: classes.dex */
    static class GetPersistentApplications extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPersistentApplications";
        }

        GetPersistentApplications() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (ParceledListSliceCompat.isReturnParceledListSlice(method)) {
                return ParceledListSliceCompat.create(new ArrayList(0));
            }
            return new ArrayList(0);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$QueryContentProviders */
    /* loaded from: classes.dex */
    static class QueryContentProviders extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "queryContentProviders";
        }

        QueryContentProviders() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean isReturnParceledListSlice = ParceledListSliceCompat.isReturnParceledListSlice(method);
            int intValue = ((Integer) objArr[1]).intValue();
            ((Integer) objArr[2]).intValue();
            List<ProviderInfo> queryContentProviders = VPackageManager.get().queryContentProviders((String) objArr[0], intValue, 0);
            Object invoke = method.invoke(obj, objArr);
            if (invoke != null) {
                if (isReturnParceledListSlice) {
                    invoke = ParceledListSlice.getList.call(invoke, new Object[0]);
                }
                List list = (List) invoke;
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ProviderInfo providerInfo = (ProviderInfo) it.next();
                    if (!isVisiblePackage(providerInfo.applicationInfo)) {
                        it.remove();
                    }
                    ComponentFixer.fixOutsideComponentInfo(providerInfo);
                }
                queryContentProviders.addAll(list);
            }
            return isReturnParceledListSlice ? ParceledListSliceCompat.create(queryContentProviders) : queryContentProviders;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$SetApplicationEnabledSetting */
    /* loaded from: classes.dex */
    static class SetApplicationEnabledSetting extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setApplicationEnabledSetting";
        }

        SetApplicationEnabledSetting() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$CheckSignatures */
    /* loaded from: classes.dex */
    static class CheckSignatures extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "checkSignatures";
        }

        CheckSignatures() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (objArr.length != 2 || !(objArr[0] instanceof String) || !(objArr[1] instanceof String)) {
                return method.invoke(obj, objArr);
            }
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            if (TextUtils.equals(str, str2)) {
                return 0;
            }
            return Integer.valueOf(VPackageManager.get().checkSignatures(str, str2));
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$checkUidSignatures */
    /* loaded from: classes.dex */
    static class checkUidSignatures extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "checkUidSignatures";
        }

        checkUidSignatures() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[0]).intValue();
            int intValue2 = ((Integer) objArr[1]).intValue();
            if (intValue == intValue2) {
                return 0;
            }
            if (intValue == 9999 || intValue2 == 9999) {
                return 0;
            }
            String[] packagesForUid = VirtualCore.getPM().getPackagesForUid(intValue);
            String[] packagesForUid2 = VirtualCore.getPM().getPackagesForUid(intValue2);
            if (packagesForUid == null || packagesForUid.length == 0) {
                return -4;
            }
            if (packagesForUid2 == null || packagesForUid2.length == 0) {
                return -4;
            }
            return Integer.valueOf(VPackageManager.get().checkSignatures(packagesForUid[0], packagesForUid2[0]));
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$getNameForUid */
    /* loaded from: classes.dex */
    static class getNameForUid extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getNameForUid";
        }

        getNameForUid() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[0]).intValue();
            if (intValue == 9999) {
                intValue = getVUid();
            }
            return VPackageManager.get().getNameForUid(intValue);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$DeletePackage */
    /* loaded from: classes.dex */
    static class DeletePackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "deletePackage";
        }

        DeletePackage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            try {
                VirtualCore.get().uninstallPackage(str);
                IPackageDeleteObserver2 iPackageDeleteObserver2 = (IPackageDeleteObserver2) objArr[1];
                if (iPackageDeleteObserver2 != null) {
                    iPackageDeleteObserver2.onPackageDeleted(str, 0, "done.");
                }
            } catch (Throwable unused) {
            }
            return 0;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$ActivitySupportsIntent */
    /* loaded from: classes.dex */
    static class ActivitySupportsIntent extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "activitySupportsIntent";
        }

        ActivitySupportsIntent() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Boolean.valueOf(VPackageManager.get().activitySupportsIntent((ComponentName) objArr[0], (Intent) objArr[1], (String) objArr[2]));
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$ResolveIntent */
    /* loaded from: classes.dex */
    static class ResolveIntent extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "resolveIntent";
        }

        ResolveIntent() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[2]).intValue();
            int myUserId = VUserHandle.myUserId();
            ResolveInfo resolveIntent = VPackageManager.get().resolveIntent((Intent) objArr[0], (String) objArr[1], intValue, myUserId);
            if (resolveIntent == null) {
                replaceLastUserId(objArr);
                ResolveInfo resolveInfo = (ResolveInfo) method.invoke(obj, objArr);
                if (resolveInfo != null && isVisiblePackage(resolveInfo.activityInfo.applicationInfo)) {
                    ComponentFixer.fixOutsideComponentInfo(resolveInfo.activityInfo);
                    return resolveInfo;
                }
            }
            return resolveIntent;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetApplicationInfo */
    /* loaded from: classes.dex */
    static class GetApplicationInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getApplicationInfo";
        }

        GetApplicationInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            int intValue = ((Integer) objArr[1]).intValue();
            if (getHostPkg().equals(str)) {
                replaceLastUserId(objArr);
                return method.invoke(obj, objArr);
            }
            ApplicationInfo applicationInfo = VPackageManager.get().getApplicationInfo(str, intValue, VUserHandle.myUserId());
            if (applicationInfo != null) {
                return applicationInfo;
            }
            replaceLastUserId(objArr);
            ApplicationInfo applicationInfo2 = (ApplicationInfo) method.invoke(obj, objArr);
            if (applicationInfo2 == null || !isVisiblePackage(applicationInfo2)) {
                return null;
            }
            ComponentFixer.fixOutsideApplicationInfo(applicationInfo2);
            return applicationInfo2;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetProviderInfo */
    /* loaded from: classes.dex */
    static class GetProviderInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getProviderInfo";
        }

        GetProviderInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ComponentName componentName = (ComponentName) objArr[0];
            int intValue = ((Integer) objArr[1]).intValue();
            if (getHostPkg().equals(componentName.getPackageName())) {
                replaceLastUserId(objArr);
                return method.invoke(obj, objArr);
            }
            ProviderInfo providerInfo = VPackageManager.get().getProviderInfo(componentName, intValue, VUserHandle.myUserId());
            if (providerInfo == null) {
                replaceLastUserId(objArr);
                providerInfo = (ProviderInfo) method.invoke(obj, objArr);
                if (providerInfo == null || !isVisiblePackage(providerInfo.applicationInfo)) {
                    return null;
                }
                ComponentFixer.fixOutsideComponentInfo(providerInfo);
            }
            return providerInfo;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$SetComponentEnabledSetting */
    /* loaded from: classes.dex */
    static class SetComponentEnabledSetting extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setComponentEnabledSetting";
        }

        SetComponentEnabledSetting() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[1]).intValue();
            int intValue2 = ((Integer) objArr[2]).intValue();
            VPackageManager.get().setComponentEnabledSetting((ComponentName) objArr[0], intValue, intValue2, getAppUserId());
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetInstalledApplications */
    /* loaded from: classes.dex */
    static class GetInstalledApplications extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getInstalledApplications";
        }

        GetInstalledApplications() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean isReturnParceledListSlice = ParceledListSliceCompat.isReturnParceledListSlice(method);
            List<ApplicationInfo> installedApplications = VPackageManager.get().getInstalledApplications(((Integer) objArr[0]).intValue(), VUserHandle.myUserId());
            Object invoke = method.invoke(obj, objArr);
            if (isReturnParceledListSlice) {
                invoke = ParceledListSlice.getList.call(invoke, new Object[0]);
            }
            List list = (List) invoke;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ApplicationInfo applicationInfo = (ApplicationInfo) it.next();
                if (VirtualCore.get().isAppInstalled(applicationInfo.packageName) || !isVisiblePackage(applicationInfo.packageName)) {
                    it.remove();
                }
                ComponentFixer.fixOutsideApplicationInfo(applicationInfo);
            }
            installedApplications.addAll(list);
            return isReturnParceledListSlice ? ParceledListSliceCompat.create(installedApplications) : installedApplications;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetInstalledPackages */
    /* loaded from: classes.dex */
    static class GetInstalledPackages extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getInstalledPackages";
        }

        GetInstalledPackages() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean isReturnParceledListSlice = ParceledListSliceCompat.isReturnParceledListSlice(method);
            List<PackageInfo> installedPackages = VPackageManager.get().getInstalledPackages(((Integer) objArr[0]).intValue(), VUserHandle.myUserId());
            replaceLastUserId(objArr);
            Object invoke = method.invoke(obj, objArr);
            if (isReturnParceledListSlice) {
                invoke = ParceledListSlice.getList.call(invoke, new Object[0]);
            }
            List list = (List) invoke;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                PackageInfo packageInfo = (PackageInfo) it.next();
                if (VirtualCore.get().isAppInstalled(packageInfo.packageName) || !isVisiblePackage(packageInfo.packageName)) {
                    it.remove();
                }
                ComponentFixer.fixOutsideApplicationInfo(packageInfo.applicationInfo);
            }
            installedPackages.addAll(list);
            MyFixer.m14541b(installedPackages);
            return ParceledListSliceCompat.isReturnParceledListSlice(method) ? ParceledListSliceCompat.create(installedPackages) : installedPackages;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$QueryIntentReceivers */
    /* loaded from: classes.dex */
    static class QueryIntentReceivers extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "queryIntentReceivers";
        }

        QueryIntentReceivers() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean isReturnParceledListSlice = ParceledListSliceCompat.isReturnParceledListSlice(method);
            List<ResolveInfo> queryIntentReceivers = VPackageManager.get().queryIntentReceivers((Intent) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue(), VUserHandle.myUserId());
            Object invoke = method.invoke(obj, objArr);
            if (isReturnParceledListSlice) {
                invoke = ParceledListSlice.getList.call(invoke, new Object[0]);
            }
            List list = (List) invoke;
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ResolveInfo resolveInfo = (ResolveInfo) it.next();
                    if (resolveInfo == null || resolveInfo.activityInfo == null || !isVisiblePackage(resolveInfo.activityInfo.applicationInfo)) {
                        it.remove();
                    } else {
                        ComponentFixer.fixOutsideComponentInfo(resolveInfo.activityInfo);
                    }
                }
                queryIntentReceivers.addAll(list);
            }
            return isReturnParceledListSlice ? ParceledListSliceCompat.create(queryIntentReceivers) : queryIntentReceivers;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetReceiverInfo */
    /* loaded from: classes.dex */
    static class GetReceiverInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getReceiverInfo";
        }

        GetReceiverInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ComponentName componentName = (ComponentName) objArr[0];
            if (getHostPkg().equals(componentName.getPackageName())) {
                return method.invoke(obj, objArr);
            }
            ActivityInfo receiverInfo = VPackageManager.get().getReceiverInfo(componentName, ((Integer) objArr[1]).intValue(), 0);
            if (receiverInfo == null) {
                replaceLastUserId(objArr);
                receiverInfo = (ActivityInfo) method.invoke(obj, objArr);
                if (receiverInfo == null || !isVisiblePackage(receiverInfo.applicationInfo)) {
                    return null;
                }
                ComponentFixer.fixOutsideComponentInfo(receiverInfo);
            }
            return receiverInfo;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    @TargetApi(17)
    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetPermissionFlags */
    /* loaded from: classes.dex */
    static class GetPermissionFlags extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPermissionFlags";
        }

        GetPermissionFlags() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[1];
            ((Integer) objArr[2]).intValue();
            if (VPackageManager.get().getPermissionInfo((String) objArr[0], 0) != null) {
                return 0;
            }
            objArr[2] = Integer.valueOf(getRealUserId());
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$SetPackageStoppedState */
    /* loaded from: classes.dex */
    static class SetPackageStoppedState extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setPackageStoppedState";
        }

        SetPackageStoppedState() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    @TargetApi(19)
    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$QueryIntentContentProviders */
    /* loaded from: classes.dex */
    static class QueryIntentContentProviders extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "queryIntentContentProviders";
        }

        QueryIntentContentProviders() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean isReturnParceledListSlice = ParceledListSliceCompat.isReturnParceledListSlice(method);
            List<ResolveInfo> queryIntentContentProviders = VPackageManager.get().queryIntentContentProviders((Intent) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue(), VUserHandle.myUserId());
            replaceLastUserId(objArr);
            Object invoke = method.invoke(obj, objArr);
            if (isReturnParceledListSlice) {
                invoke = ParceledListSlice.getList.call(invoke, new Object[0]);
            }
            List list = (List) invoke;
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ResolveInfo resolveInfo = (ResolveInfo) it.next();
                    if (resolveInfo == null || resolveInfo.providerInfo == null || !isVisiblePackage(resolveInfo.providerInfo.applicationInfo)) {
                        it.remove();
                    } else {
                        ComponentFixer.fixOutsideComponentInfo(resolveInfo.providerInfo);
                    }
                }
                queryIntentContentProviders.addAll(list);
            }
            return ParceledListSliceCompat.isReturnParceledListSlice(method) ? ParceledListSliceCompat.create(queryIntentContentProviders) : queryIntentContentProviders;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.pm.MethodProxies$GetApplicationBlockedSettingAsUser */
    /* loaded from: classes.dex */
    static class GetApplicationBlockedSettingAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getApplicationBlockedSettingAsUser";
        }

        GetApplicationBlockedSettingAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }
    }
}
