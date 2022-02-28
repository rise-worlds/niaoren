package com.lody.virtual.client.hook.proxies.p059am;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.app.IServiceConnection;
import android.app.Notification;
import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.badger.BadgerManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.env.SpecialComponentList;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate;
import com.lody.virtual.client.hook.providers.ProviderHook;
import com.lody.virtual.client.hook.secondary.ServiceConnectionDelegate;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.ipc.VNotificationManager;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.client.stub.ChooserActivity;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.compat.ActivityManagerCompat;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.helper.compat.ParceledListSliceCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.BitmapUtils;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.helper.utils.DrawableUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.os.VUserInfo;
import com.lody.virtual.os.VUserManager;
import com.lody.virtual.remote.AppTaskInfo;
import com.lody.virtual.remote.BroadcastIntentData;
import com.lody.virtual.remote.ClientConfig;
import com.lody.virtual.remote.IntentSenderData;
import com.lody.virtual.remote.IntentSenderExtData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import p110z1.ContentProviderHolderOreo;
import p110z1.IActivityManager;
import p110z1.IIntentReceiverJB;
import p110z1.IntentSender;
import p110z1.LoadedApk;
import p110z1.ParceledListSlice;
import p110z1.UserInfo;
import patch.MyFixer;

/* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies */
/* loaded from: classes.dex */
public class MethodProxies {

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$FinishReceiver */
    /* loaded from: classes.dex */
    static class FinishReceiver extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "finishReceiver";
        }

        FinishReceiver() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return super.call(obj, method, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetRecentTasks */
    /* loaded from: classes.dex */
    static class GetRecentTasks extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getRecentTasks";
        }

        GetRecentTasks() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            replaceFirstUserId(objArr);
            Object invoke = method.invoke(obj, objArr);
            Iterator<?> it = (ParceledListSliceCompat.isReturnParceledListSlice(method) ? ParceledListSlice.getList.call(invoke, new Object[0]) : (List) invoke).iterator();
            while (it.hasNext()) {
                ActivityManager.RecentTaskInfo recentTaskInfo = (ActivityManager.RecentTaskInfo) it.next();
                AppTaskInfo taskInfo = VActivityManager.get().getTaskInfo(recentTaskInfo.id);
                if (taskInfo != null) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        try {
                            recentTaskInfo.topActivity = taskInfo.topActivity;
                            recentTaskInfo.baseActivity = taskInfo.baseActivity;
                        } catch (Throwable unused) {
                        }
                    }
                    try {
                        recentTaskInfo.origActivity = taskInfo.baseActivity;
                        recentTaskInfo.baseIntent = taskInfo.baseIntent;
                    } catch (Throwable unused2) {
                    }
                }
            }
            return invoke;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$ForceStopPackage */
    /* loaded from: classes.dex */
    static class ForceStopPackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "forceStopPackage";
        }

        ForceStopPackage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int myUserId = VUserHandle.myUserId();
            VActivityManager.get().killAppByPkg((String) objArr[0], myUserId);
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$CrashApplication */
    /* loaded from: classes.dex */
    static class CrashApplication extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "crashApplication";
        }

        CrashApplication() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$AddPackageDependency */
    /* loaded from: classes.dex */
    static class AddPackageDependency extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "addPackageDependency";
        }

        AddPackageDependency() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetPackageForToken */
    /* loaded from: classes.dex */
    static class GetPackageForToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageForToken";
        }

        GetPackageForToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String packageForToken = VActivityManager.get().getPackageForToken((IBinder) objArr[0]);
            return packageForToken != null ? packageForToken : super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$UnbindService */
    /* loaded from: classes.dex */
    static class UnbindService extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "unbindService";
        }

        UnbindService() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ServiceConnectionDelegate removeDelegate = ServiceConnectionDelegate.removeDelegate((IServiceConnection) objArr[0]);
            if (removeDelegate == null) {
                return method.invoke(obj, objArr);
            }
            return Boolean.valueOf(VActivityManager.get().unbindService(removeDelegate));
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetContentProviderExternal */
    /* loaded from: classes.dex */
    static class GetContentProviderExternal extends GetContentProvider {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.GetContentProvider, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getContentProviderExternal";
        }

        GetContentProviderExternal() {
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.GetContentProvider
        public int getProviderNameIndex() {
            return BuildCompat.isQ() ? 1 : 0;
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.GetContentProvider, com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartVoiceActivity */
    /* loaded from: classes.dex */
    static class StartVoiceActivity extends StartActivity {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startVoiceActivity";
        }

        StartVoiceActivity() {
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$UnstableProviderDied */
    /* loaded from: classes.dex */
    static class UnstableProviderDied extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "unstableProviderDied";
        }

        UnstableProviderDied() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (objArr[0] == null) {
                return 0;
            }
            return method.invoke(obj, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$PeekService */
    /* loaded from: classes.dex */
    static class PeekService extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "peekService";
        }

        PeekService() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return VActivityManager.get().peekService((Intent) objArr[0], (String) objArr[1]);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetPackageAskScreenCompat */
    /* loaded from: classes.dex */
    static class GetPackageAskScreenCompat extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageAskScreenCompat";
        }

        GetPackageAskScreenCompat() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (Build.VERSION.SDK_INT >= 15 && objArr.length > 0 && (objArr[0] instanceof String)) {
                objArr[0] = getHostPkg();
            }
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetIntentSender */
    /* loaded from: classes.dex */
    static class GetIntentSender extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getIntentSender";
        }

        protected int intentsStart() {
            return 5;
        }

        GetIntentSender() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[1];
            objArr[1] = getHostPkg();
            if (objArr[objArr.length - 1] instanceof Integer) {
                objArr[objArr.length - 1] = Integer.valueOf(getRealUserId());
            }
            String[] strArr = (String[]) objArr[intentsStart() + 1];
            ArrayUtils.indexOfFirst(objArr, IBinder.class);
            int intValue = ((Integer) objArr[0]).intValue();
            int intValue2 = ((Integer) objArr[intentsStart() + 2]).intValue();
            Intent[] intentArr = (Intent[]) objArr[intentsStart()];
            if (intentArr.length <= 0) {
                return method.invoke(obj, objArr);
            }
            Intent intent = new Intent(intentArr[intentArr.length - 1]);
            if (strArr != null && strArr.length >= intentArr.length) {
                intent.setDataAndType(intent.getData(), strArr[intentArr.length - 1]);
            }
            Intent redirectIntentSender = ComponentUtils.redirectIntentSender(intValue, str, intent);
            if (redirectIntentSender == null) {
                return null;
            }
            Intent[] intentArr2 = new Intent[1];
            intentArr2[0] = redirectIntentSender;
            objArr[5] = intentArr2;
            objArr[6] = new String[]{null};
            objArr[7] = Integer.valueOf(intValue2 & InputDeviceCompat.SOURCE_ANY);
            IInterface iInterface = (IInterface) method.invoke(obj, objArr);
            if (iInterface != null) {
                VActivityManager.get().addOrUpdateIntentSender(new IntentSenderData(str, iInterface.asBinder(), intent, intValue2, intValue, VUserHandle.myUserId()));
            }
            return iInterface;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetIntentSenderWithSourceToken */
    /* loaded from: classes.dex */
    static class GetIntentSenderWithSourceToken extends GetIntentSender {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.GetIntentSender, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getIntentSenderWithSourceToken";
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.GetIntentSender
        protected int intentsStart() {
            return 6;
        }

        GetIntentSenderWithSourceToken() {
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartActivity */
    /* loaded from: classes.dex */
    public static class StartActivity extends MethodProxy {
        private static final String SCHEME_CONTENT = "content";
        private static final String SCHEME_FILE = "file";
        private static final String SCHEME_PACKAGE = "package";

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startActivity";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int i;
            String str;
            int indexOfObject = ArrayUtils.indexOfObject(objArr, Intent.class, 1);
            if (indexOfObject < 0) {
                return Integer.valueOf(ActivityManagerCompat.START_INTENT_NOT_RESOLVED);
            }
            int indexOfObject2 = ArrayUtils.indexOfObject(objArr, IBinder.class, 2);
            Intent intent = (Intent) objArr[indexOfObject];
            intent.setDataAndType(intent.getData(), (String) objArr[indexOfObject + 1]);
            IBinder iBinder = indexOfObject2 >= 0 ? (IBinder) objArr[indexOfObject2] : null;
            Bundle bundle = (Bundle) ArrayUtils.getFirst(objArr, Bundle.class);
            if (iBinder != null) {
                str = (String) objArr[indexOfObject2 + 1];
                i = ((Integer) objArr[indexOfObject2 + 2]).intValue();
            } else {
                str = null;
                i = 0;
            }
            int myUserId = VUserHandle.myUserId();
            if ("android.intent.action.MAIN".equals(intent.getAction()) && intent.hasCategory("android.intent.category.HOME")) {
                Intent onHandleLauncherIntent = getConfig().onHandleLauncherIntent(intent);
                if (onHandleLauncherIntent != null) {
                    objArr[indexOfObject] = onHandleLauncherIntent;
                }
                return method.invoke(obj, objArr);
            } else if (isHostIntent(intent)) {
                return method.invoke(obj, objArr);
            } else {
                if ("android.intent.action.INSTALL_PACKAGE".equals(intent.getAction()) || ("android.intent.action.VIEW".equals(intent.getAction()) && "application/vnd.android.package-archive".equals(intent.getType()))) {
                    objArr[indexOfObject] = MyFixer.m14550a(intent, getHostContext().getCacheDir(), "content", getHostContext().getContentResolver());
                    return method.invoke(obj, objArr);
                } else if (("android.intent.action.UNINSTALL_PACKAGE".equals(intent.getAction()) || "android.intent.action.DELETE".equals(intent.getAction())) && "package".equals(intent.getScheme()) && handleUninstallRequest(intent)) {
                    return 0;
                } else {
                    String str2 = intent.getPackage();
                    if (str2 != null && !isAppPkg(str2) && !str2.equals("com.iqiyigame.sdk") && (intent.getComponent() == null || !"com.huawei.hwid".equals(intent.getComponent().getPackageName()))) {
                        return method.invoke(obj, objArr);
                    }
                    if (ChooserActivity.check(intent)) {
                        Intent processOutsideIntent = ComponentUtils.processOutsideIntent(myUserId, VirtualCore.get().is64BitEngine(), intent);
                        objArr[indexOfObject] = processOutsideIntent;
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt(Constants.EXTRA_USER_HANDLE, myUserId);
                        bundle2.putBundle(ChooserActivity.EXTRA_DATA, bundle);
                        bundle2.putString(ChooserActivity.EXTRA_WHO, str);
                        bundle2.putInt(ChooserActivity.EXTRA_REQUEST_CODE, i);
                        BundleCompat.putBinder(bundle2, ChooserActivity.EXTRA_RESULTTO, iBinder);
                        processOutsideIntent.setComponent(new ComponentName(StubManifest.PACKAGE_NAME, ChooserActivity.class.getName()));
                        processOutsideIntent.setAction(null);
                        processOutsideIntent.putExtras(bundle2);
                        return method.invoke(obj, objArr);
                    }
                    if (Build.VERSION.SDK_INT >= 18) {
                        objArr[indexOfObject - 1] = getHostPkg();
                    }
                    if (!(intent.getScheme() == null || !intent.getScheme().equals("package") || intent.getData() == null || intent.getAction() == null || !intent.getAction().startsWith("android.settings."))) {
                        intent.setData(Uri.parse("package:" + getHostPkg()));
                    }
                    ActivityInfo resolveActivityInfo = VirtualCore.get().resolveActivityInfo(intent, myUserId);
                    if (resolveActivityInfo != null) {
                        MyFixer.m14551a(intent);
                        int startActivity = VActivityManager.get().startActivity(intent, resolveActivityInfo, iBinder, bundle, str, i, VUserHandle.myUserId());
                        if (!(startActivity == 0 || iBinder == null || i <= 0)) {
                            VActivityManager.get().sendCancelActivityResult(iBinder, str, i);
                        }
                        return Integer.valueOf(startActivity);
                    } else if (intent.getPackage() != null && isAppPkg(intent.getPackage())) {
                        return Integer.valueOf(ActivityManagerCompat.START_INTENT_NOT_RESOLVED);
                    } else {
                        objArr[indexOfObject] = ComponentUtils.processOutsideIntent(myUserId, VirtualCore.get().is64BitEngine(), intent);
                        ResolveInfo resolveActivity = VirtualCore.get().getUnHookPackageManager().resolveActivity(intent, 0);
                        if (resolveActivity == null || resolveActivity.activityInfo == null) {
                            return Integer.valueOf(ActivityManagerCompat.START_INTENT_NOT_RESOLVED);
                        }
                        if ("android.intent.action.VIEW".equals(intent.getAction()) || isVisiblePackage(resolveActivity.activityInfo.applicationInfo)) {
                            return method.invoke(obj, objArr);
                        }
                        return Integer.valueOf(ActivityManagerCompat.START_INTENT_NOT_RESOLVED);
                    }
                }
            }
        }

        private boolean handleInstallRequest(Intent intent) {
            IOException e;
            InputStream inputStream;
            VirtualCore.AppRequestListener appRequestListener = VirtualCore.get().getAppRequestListener();
            if (appRequestListener != null) {
                Uri data = intent.getData();
                if ("file".equals(data.getScheme())) {
                    appRequestListener.onRequestInstall(NativeEngine.getRedirectedPath(new File(data.getPath()).getAbsolutePath()));
                    return true;
                } else if ("content".equals(data.getScheme())) {
                    File file = new File(getHostContext().getCacheDir(), data.getLastPathSegment());
                    InputStream inputStream2 = null;
                    try {
                        inputStream = getHostContext().getContentResolver().openInputStream(data);
                        try {
                            inputStream2 = new FileOutputStream(file);
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = inputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    inputStream2.write(bArr, 0, read);
                                }
                                inputStream2.flush();
                                FileUtils.closeQuietly(inputStream);
                            } catch (IOException e2) {
                                e = e2;
                                inputStream2 = inputStream;
                                try {
                                    e.printStackTrace();
                                    FileUtils.closeQuietly(inputStream2);
                                    FileUtils.closeQuietly(inputStream2);
                                    appRequestListener.onRequestInstall(file.getPath());
                                    file.delete();
                                    return true;
                                } catch (Throwable th) {
                                    th = th;
                                    inputStream = inputStream2;
                                    FileUtils.closeQuietly(inputStream);
                                    FileUtils.closeQuietly(inputStream2);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                FileUtils.closeQuietly(inputStream);
                                FileUtils.closeQuietly(inputStream2);
                                throw th;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            inputStream2 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            FileUtils.closeQuietly(inputStream);
                            FileUtils.closeQuietly(inputStream2);
                            throw th;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        inputStream2 = null;
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                    }
                    FileUtils.closeQuietly(inputStream2);
                    appRequestListener.onRequestInstall(file.getPath());
                    file.delete();
                    return true;
                }
            }
            return false;
        }

        private boolean handleUninstallRequest(Intent intent) {
            VirtualCore.AppRequestListener appRequestListener = VirtualCore.get().getAppRequestListener();
            if (appRequestListener == null) {
                return false;
            }
            Uri data = intent.getData();
            if (!"package".equals(data.getScheme())) {
                return false;
            }
            appRequestListener.onRequestUninstall(data.getSchemeSpecificPart());
            return true;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartActivities */
    /* loaded from: classes.dex */
    public static class StartActivities extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startActivities";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            new Exception().printStackTrace();
            Intent[] intentArr = (Intent[]) ArrayUtils.getFirst(objArr, Intent[].class);
            String[] strArr = (String[]) ArrayUtils.getFirst(objArr, String[].class);
            int indexOfObject = ArrayUtils.indexOfObject(objArr, IBinder.class, 2);
            return Integer.valueOf(VActivityManager.get().startActivities(intentArr, strArr, indexOfObject != -1 ? (IBinder) objArr[indexOfObject] : null, (Bundle) ArrayUtils.getFirst(objArr, Bundle.class), VUserHandle.myUserId()));
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$ShouldUpRecreateTask */
    /* loaded from: classes.dex */
    static class ShouldUpRecreateTask extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "shouldUpRecreateTask";
        }

        ShouldUpRecreateTask() {
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

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetCallingPackage */
    /* loaded from: classes.dex */
    public static class GetCallingPackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getCallingPackage";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return VActivityManager.get().getCallingPackage((IBinder) objArr[0]);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetPackageForIntentSender */
    /* loaded from: classes.dex */
    static class GetPackageForIntentSender extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageForIntentSender";
        }

        GetPackageForIntentSender() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IntentSenderData intentSender;
            IInterface iInterface = (IInterface) objArr[0];
            if (iInterface == null || (intentSender = VActivityManager.get().getIntentSender(iInterface.asBinder())) == null) {
                return super.call(obj, method, objArr);
            }
            return intentSender.creator;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$PublishContentProviders */
    /* loaded from: classes.dex */
    static class PublishContentProviders extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "publishContentProviders";
        }

        PublishContentProviders() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetServices */
    /* loaded from: classes.dex */
    static class GetServices extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getServices";
        }

        GetServices() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return VActivityManager.get().getServices(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue()).getList();
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GrantUriPermissionFromOwner */
    /* loaded from: classes.dex */
    static class GrantUriPermissionFromOwner extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "grantUriPermissionFromOwner";
        }

        GrantUriPermissionFromOwner() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$SetServiceForeground */
    /* loaded from: classes.dex */
    static class SetServiceForeground extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setServiceForeground";
        }

        SetServiceForeground() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!getConfig().isAllowServiceStartForeground()) {
                return 0;
            }
            ComponentName componentName = (ComponentName) objArr[0];
            IBinder iBinder = (IBinder) objArr[1];
            int intValue = ((Integer) objArr[2]).intValue();
            Notification notification = (Notification) objArr[3];
            if (objArr[4] instanceof Boolean) {
                ((Boolean) objArr[4]).booleanValue();
            } else if (Build.VERSION.SDK_INT < 24 || !(objArr[4] instanceof Integer)) {
                String simpleName = getClass().getSimpleName();
                VLog.m18992e(simpleName, "Unknown flag : " + objArr[4]);
            } else {
                int intValue2 = ((Integer) objArr[4]).intValue() & 1;
            }
            if (!VNotificationManager.get().dealNotification(intValue, notification, getAppPkg())) {
                notification = new Notification();
                notification.icon = getHostContext().getApplicationInfo().icon;
            }
            if (notification != null && Build.VERSION.SDK_INT >= 23 && (Build.BRAND.equalsIgnoreCase("samsung") || Build.MANUFACTURER.equalsIgnoreCase("samsung"))) {
                notification.icon = getHostContext().getApplicationInfo().icon;
                Reflect.m18998on(notification).call("setSmallIcon", Icon.createWithResource(getHostPkg(), notification.icon));
            }
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$UpdateDeviceOwner */
    /* loaded from: classes.dex */
    static class UpdateDeviceOwner extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "updateDeviceOwner";
        }

        UpdateDeviceOwner() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetIntentForIntentSender */
    /* loaded from: classes.dex */
    static class GetIntentForIntentSender extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getIntentForIntentSender";
        }

        GetIntentForIntentSender() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) {
            Intent selector;
            Intent intent;
            Intent intent2 = (Intent) obj2;
            return (intent2 == null || (selector = intent2.getSelector()) == null || (intent = (Intent) selector.getParcelableExtra("_VA_|_intent_")) == null) ? intent2 : intent;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$UnbindFinished */
    /* loaded from: classes.dex */
    static class UnbindFinished extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "unbindFinished";
        }

        UnbindFinished() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
            VActivityManager.get().unbindFinished((IBinder) objArr[0], (Intent) objArr[1], booleanValue);
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartActivityIntentSender */
    /* loaded from: classes.dex */
    public static class StartActivityIntentSender extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startActivityIntentSender";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            char c;
            char c2;
            IInterface iInterface;
            char c3 = '\t';
            char c4 = '\b';
            char c5 = 7;
            char c6 = 6;
            char c7 = 5;
            if (BuildCompat.isOreo()) {
                c2 = 3;
                c3 = '\n';
                c = '\t';
            } else {
                c2 = 2;
                c4 = 7;
                c5 = 6;
                c6 = 5;
                c7 = 4;
                c = '\b';
            }
            Object obj2 = objArr[1];
            Intent intent = (Intent) objArr[c2];
            IBinder iBinder = (IBinder) objArr[c7];
            String str = (String) objArr[c6];
            int intValue = ((Integer) objArr[c5]).intValue();
            Bundle bundle = (Bundle) objArr[c3];
            int intValue2 = ((Integer) objArr[c4]).intValue();
            int intValue3 = ((Integer) objArr[c]).intValue();
            Intent intent2 = new Intent();
            if (obj2 instanceof IInterface) {
                iInterface = (IInterface) obj2;
            } else {
                iInterface = IntentSender.mTarget.get(obj2);
            }
            intent2.putExtra("_VA_|_ext_", new IntentSenderExtData(iInterface.asBinder(), intent, iBinder, str, intValue, bundle, intValue2, intValue3));
            objArr[c2] = intent2;
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$SendIntentSender */
    /* loaded from: classes.dex */
    static class SendIntentSender extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "sendIntentSender";
        }

        SendIntentSender() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IInterface iInterface = (IInterface) objArr[0];
            int indexOfObject = ArrayUtils.indexOfObject(objArr, Integer.class, 1) + 1;
            Intent intent = (Intent) objArr[indexOfObject];
            Bundle bundle = (Bundle) objArr[objArr.length - 1];
            int length = objArr.length - 2;
            if (objArr[length] instanceof String) {
                objArr[length] = null;
            }
            IntentSenderExtData intentSenderExtData = new IntentSenderExtData(iInterface.asBinder(), intent, null, null, 0, bundle, 0, 0);
            Intent intent2 = new Intent();
            intent2.putExtra("_VA_|_ext_", intentSenderExtData);
            objArr[indexOfObject] = intent2;
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetAppTasks */
    /* loaded from: classes.dex */
    static class GetAppTasks extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAppTasks";
        }

        GetAppTasks() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$BindServiceQ */
    /* loaded from: classes.dex */
    static class BindServiceQ extends BindService {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.BindService, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "bindIsolatedService";
        }

        BindServiceQ() {
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.BindService, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            objArr[7] = VirtualCore.get().getHostPkg();
            replaceLastUserId(objArr);
            return super.call(obj, method, objArr);
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.BindService, com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$BindService */
    /* loaded from: classes.dex */
    static class BindService extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "bindService";
        }

        BindService() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IInterface iInterface = (IInterface) objArr[0];
            IBinder iBinder = (IBinder) objArr[1];
            Intent intent = (Intent) objArr[2];
            String str = (String) objArr[3];
            IServiceConnection iServiceConnection = (IServiceConnection) objArr[4];
            int intValue = ((Integer) objArr[5]).intValue();
            int intExtra = isServerProcess() ? intent.getIntExtra("_VA_|_user_id_", VUserHandle.USER_NULL) : VUserHandle.myUserId();
            if (intExtra == -10000) {
                replaceLastUserId(objArr);
                return method.invoke(obj, objArr);
            }
            ServiceInfo resolveServiceInfo = VirtualCore.get().resolveServiceInfo(intent, intExtra);
            if (resolveServiceInfo != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    intent.setComponent(new ComponentName(resolveServiceInfo.packageName, resolveServiceInfo.name));
                }
                return Integer.valueOf(VActivityManager.get().bindService(iInterface.asBinder(), iBinder, intent, str, ServiceConnectionDelegate.getDelegate(iServiceConnection), intValue, intExtra));
            }
            ResolveInfo resolveService = VirtualCore.get().getUnHookPackageManager().resolveService(intent, 0);
            if (resolveService == null || !isVisiblePackage(resolveService.serviceInfo.applicationInfo)) {
                return 0;
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartService */
    /* loaded from: classes.dex */
    static class StartService extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startService";
        }

        StartService() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Intent intent = (Intent) objArr[1];
            String str = (String) objArr[2];
            if (intent == null) {
                return null;
            }
            ComponentName component = intent.getComponent();
            String replace = getHostPkg().replace(".addon.arm64", "");
            if (component == null || !replace.equals(component.getPackageName())) {
                int myUserId = VUserHandle.myUserId();
                if (isServerProcess()) {
                    myUserId = intent.getIntExtra("_VA_|_user_id_", VUserHandle.USER_NULL);
                }
                intent.setDataAndType(intent.getData(), str);
                if (VirtualCore.get().resolveServiceInfo(intent, VUserHandle.myUserId()) != null) {
                    return VActivityManager.get().startService(intent, str, myUserId);
                }
                ResolveInfo resolveService = VirtualCore.get().getUnHookPackageManager().resolveService(intent, 0);
                if (resolveService == null || !isVisiblePackage(resolveService.serviceInfo.applicationInfo)) {
                    return null;
                }
                replaceLastUserId(objArr);
                return method.invoke(obj, objArr);
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartActivityAndWait */
    /* loaded from: classes.dex */
    public static class StartActivityAndWait extends StartActivity {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startActivityAndWait";
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$PublishService */
    /* loaded from: classes.dex */
    static class PublishService extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "publishService";
        }

        PublishService() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IBinder iBinder = (IBinder) objArr[0];
            if (!VActivityManager.get().isVAServiceToken(iBinder)) {
                return method.invoke(obj, objArr);
            }
            VActivityManager.get().publishService(iBinder, (Intent) objArr[1], (IBinder) objArr[2]);
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetRunningAppProcesses */
    /* loaded from: classes.dex */
    static class GetRunningAppProcesses extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getRunningAppProcesses";
        }

        GetRunningAppProcesses() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public synchronized Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!VClient.get().isEnvironmentPrepared()) {
                return method.invoke(obj, objArr);
            }
            List list = (List) method.invoke(obj, objArr);
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo) it.next();
                if (runningAppProcessInfo.uid == getRealUid()) {
                    if (VActivityManager.get().isAppPid(runningAppProcessInfo.pid)) {
                        int uidByPid = VActivityManager.get().getUidByPid(runningAppProcessInfo.pid);
                        if (VUserHandle.getUserId(uidByPid) != getAppUserId()) {
                            it.remove();
                        } else {
                            List<String> processPkgList = VActivityManager.get().getProcessPkgList(runningAppProcessInfo.pid);
                            String appProcessName = VActivityManager.get().getAppProcessName(runningAppProcessInfo.pid);
                            if (appProcessName != null) {
                                runningAppProcessInfo.importanceReasonCode = 0;
                                runningAppProcessInfo.importanceReasonPid = 0;
                                runningAppProcessInfo.importanceReasonComponent = null;
                                runningAppProcessInfo.processName = appProcessName;
                            }
                            runningAppProcessInfo.pkgList = (String[]) processPkgList.toArray(new String[0]);
                            runningAppProcessInfo.uid = uidByPid;
                        }
                    } else if (runningAppProcessInfo.processName.startsWith(getConfig().getHostPackageName()) || runningAppProcessInfo.processName.startsWith(getConfig().get64bitEnginePackageName())) {
                        it.remove();
                    }
                }
            }
            return MyFixer.m14545a(arrayList);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$SetPackageAskScreenCompat */
    /* loaded from: classes.dex */
    static class SetPackageAskScreenCompat extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setPackageAskScreenCompat";
        }

        SetPackageAskScreenCompat() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (Build.VERSION.SDK_INT >= 15 && objArr.length > 0 && (objArr[0] instanceof String)) {
                objArr[0] = getHostPkg();
            }
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetCallingActivity */
    /* loaded from: classes.dex */
    public static class GetCallingActivity extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getCallingActivity";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return VActivityManager.get().getCallingActivity((IBinder) objArr[0]);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetCurrentUser */
    /* loaded from: classes.dex */
    static class GetCurrentUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getCurrentUser";
        }

        GetCurrentUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            try {
                return UserInfo.ctor.newInstance(0, ServiceManagerNative.USER, 1);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$KillApplicationProcess */
    /* loaded from: classes.dex */
    static class KillApplicationProcess extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "killApplicationProcess";
        }

        KillApplicationProcess() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (objArr.length <= 1 || !(objArr[0] instanceof String) || !(objArr[1] instanceof Integer)) {
                return 0;
            }
            int intValue = ((Integer) objArr[1]).intValue();
            VActivityManager.get().killApplicationProcess((String) objArr[0], intValue);
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$KillBackgroundProcesses */
    /* loaded from: classes.dex */
    static class KillBackgroundProcesses extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "killBackgroundProcesses";
        }

        KillBackgroundProcesses() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (objArr[0] instanceof String) {
                VActivityManager.get().killAppByPkg((String) objArr[0], getAppUserId());
                return 0;
            }
            replaceLastUserId(objArr);
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartActivityAsUser */
    /* loaded from: classes.dex */
    public static class StartActivityAsUser extends StartActivity {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startActivityAsUser";
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            replaceLastUserId(objArr);
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$CheckPermission */
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
            int intValue = ((Integer) objArr[1]).intValue();
            int intValue2 = ((Integer) objArr[2]).intValue();
            return Integer.valueOf(VActivityManager.get().checkPermission((String) objArr[0], intValue, intValue2));
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$CheckPermissionWithToken */
    /* loaded from: classes.dex */
    static class CheckPermissionWithToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "checkPermissionWithToken";
        }

        CheckPermissionWithToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int intValue = ((Integer) objArr[1]).intValue();
            int intValue2 = ((Integer) objArr[2]).intValue();
            return Integer.valueOf(VActivityManager.get().checkPermission((String) objArr[0], intValue, intValue2));
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartActivityAsCaller */
    /* loaded from: classes.dex */
    static class StartActivityAsCaller extends StartActivity {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startActivityAsCaller";
        }

        StartActivityAsCaller() {
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$HandleIncomingUser */
    /* loaded from: classes.dex */
    static class HandleIncomingUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "handleIncomingUser";
        }

        HandleIncomingUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int length = objArr.length - 1;
            if (objArr[length] instanceof String) {
                objArr[length] = getHostPkg();
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetTasks */
    /* loaded from: classes.dex */
    public static class GetTasks extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getTasks";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            List<ActivityManager.RunningTaskInfo> list = (List) method.invoke(obj, objArr);
            for (ActivityManager.RunningTaskInfo runningTaskInfo : list) {
                AppTaskInfo taskInfo = VActivityManager.get().getTaskInfo(runningTaskInfo.id);
                if (taskInfo != null) {
                    runningTaskInfo.topActivity = taskInfo.topActivity;
                    runningTaskInfo.baseActivity = taskInfo.baseActivity;
                }
            }
            return list;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetPersistedUriPermissions */
    /* loaded from: classes.dex */
    static class GetPersistedUriPermissions extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPersistedUriPermissions";
        }

        GetPersistedUriPermissions() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$RegisterReceiver */
    /* loaded from: classes.dex */
    static class RegisterReceiver extends MethodProxy {
        private static final int IDX_IIntentReceiver;
        private static final int IDX_IntentFilter;
        private static final int IDX_RequiredPermission;
        private WeakHashMap<IBinder, IIntentReceiver> mProxyIIntentReceivers = new WeakHashMap<>();

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "registerReceiver";
        }

        RegisterReceiver() {
        }

        static {
            int i = 2;
            IDX_IIntentReceiver = Build.VERSION.SDK_INT >= 15 ? 2 : 1;
            IDX_RequiredPermission = Build.VERSION.SDK_INT >= 15 ? 4 : 3;
            if (Build.VERSION.SDK_INT >= 15) {
                i = 3;
            }
            IDX_IntentFilter = i;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            final IBinder asBinder;
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            replaceFirstUserId(objArr);
            objArr[IDX_RequiredPermission] = null;
            IntentFilter intentFilter = (IntentFilter) objArr[IDX_IntentFilter];
            if (intentFilter == null) {
                return method.invoke(obj, objArr);
            }
            IntentFilter intentFilter2 = new IntentFilter(intentFilter);
            if (intentFilter2.hasCategory("__VA__|_static_receiver_")) {
                p110z1.IntentFilter.mCategories.get(intentFilter2).remove("__VA__|_static_receiver_");
                return method.invoke(obj, objArr);
            }
            SpecialComponentList.protectIntentFilter(intentFilter2);
            objArr[IDX_IntentFilter] = intentFilter2;
            int length = objArr.length;
            int i = IDX_IIntentReceiver;
            if (length > i && (objArr[i] instanceof IIntentReceiver)) {
                IInterface iInterface = (IInterface) objArr[i];
                if (!(iInterface instanceof IIntentReceiverProxy) && (asBinder = iInterface.asBinder()) != null) {
                    asBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.lody.virtual.client.hook.proxies.am.MethodProxies.RegisterReceiver.1
                        @Override // android.os.IBinder.DeathRecipient
                        public void binderDied() {
                            asBinder.unlinkToDeath(this, 0);
                            RegisterReceiver.this.mProxyIIntentReceivers.remove(asBinder);
                        }
                    }, 0);
                    IIntentReceiver iIntentReceiver = this.mProxyIIntentReceivers.get(asBinder);
                    if (iIntentReceiver == null) {
                        iIntentReceiver = new IIntentReceiverProxy(iInterface, intentFilter2);
                        this.mProxyIIntentReceivers.put(asBinder, iIntentReceiver);
                    }
                    WeakReference weakReference = LoadedApk.C5120a.C5121a.mDispatcher.get(iInterface);
                    if (weakReference != null) {
                        LoadedApk.C5120a.mIIntentReceiver.set(weakReference.get(), iIntentReceiver);
                        objArr[IDX_IIntentReceiver] = iIntentReceiver;
                    }
                }
            }
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }

        /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$RegisterReceiver$IIntentReceiverProxy */
        /* loaded from: classes.dex */
        private static class IIntentReceiverProxy extends IIntentReceiver.Stub {
            IntentFilter mFilter;
            IInterface mOld;

            IIntentReceiverProxy(IInterface iInterface, IntentFilter intentFilter) {
                this.mOld = iInterface;
                this.mFilter = intentFilter;
            }

            @Override // android.content.IIntentReceiver
            public void performReceive(Intent intent, int i, String str, Bundle bundle, boolean z, boolean z2, int i2) {
                BroadcastIntentData broadcastIntentData;
                Intent intent2;
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    extras.setClassLoader(BroadcastIntentData.class.getClassLoader());
                    broadcastIntentData = (BroadcastIntentData) extras.getParcelable("_VA_|_data_");
                } else {
                    broadcastIntentData = null;
                }
                if (broadcastIntentData == null) {
                    SpecialComponentList.unprotectIntent(intent);
                    intent2 = intent;
                } else if (broadcastIntentData.userId == VUserHandle.myUserId()) {
                    intent2 = broadcastIntentData.intent;
                } else {
                    return;
                }
                if (Build.VERSION.SDK_INT > 16) {
                    IIntentReceiverJB.performReceive.call(this.mOld, intent2, Integer.valueOf(i), str, bundle, Boolean.valueOf(z), Boolean.valueOf(z2), Integer.valueOf(i2));
                } else {
                    p110z1.IIntentReceiver.performReceive.call(this.mOld, intent2, Integer.valueOf(i), str, bundle, Boolean.valueOf(z), Boolean.valueOf(z2));
                }
            }

            public void performReceive(Intent intent, int i, String str, Bundle bundle, boolean z, boolean z2) {
                performReceive(intent, i, str, bundle, z, z2, 0);
            }
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StopService */
    /* loaded from: classes.dex */
    static class StopService extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "stopService";
        }

        StopService() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ResolveInfo resolveService;
            IInterface iInterface = (IInterface) objArr[0];
            Intent intent = (Intent) objArr[1];
            String str = (String) objArr[2];
            intent.setDataAndType(intent.getData(), str);
            ComponentName component = intent.getComponent();
            PackageManager pm = VirtualCore.getPM();
            if (!(component != null || (resolveService = pm.resolveService(intent, 0)) == null || resolveService.serviceInfo == null)) {
                component = new ComponentName(resolveService.serviceInfo.packageName, resolveService.serviceInfo.name);
            }
            if (component != null && !getHostPkg().equals(component.getPackageName())) {
                return Integer.valueOf(VActivityManager.get().stopService(iInterface, intent, str));
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetContentProvider */
    /* loaded from: classes.dex */
    static class GetContentProvider extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getContentProvider";
        }

        GetContentProvider() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int providerNameIndex = getProviderNameIndex();
            String str = (String) objArr[providerNameIndex];
            if (str.equals("com.huawei.hms.runtimekit.kitcontainerservice.KitProcessAgentPersistProvider")) {
                return method.invoke(obj, objArr);
            }
            if (str.startsWith(StubManifest.STUB_CP_AUTHORITY) || str.startsWith(StubManifest.STUB_CP_AUTHORITY_64BIT) || str.equals(getConfig().getHelperAuthority()) || str.equals(getConfig().getBinderProviderAuthority())) {
                replaceLastUserId(objArr);
                return method.invoke(obj, objArr);
            }
            if (BuildCompat.isQ()) {
                int i = providerNameIndex - 1;
                if (objArr[i] instanceof String) {
                    objArr[i] = getHostPkg();
                }
            }
            int myUserId = VUserHandle.myUserId();
            ProviderInfo resolveContentProvider = VPackageManager.get().resolveContentProvider(str, 0, myUserId);
            if (resolveContentProvider != null && !resolveContentProvider.enabled) {
                return null;
            }
            boolean z = true;
            if (resolveContentProvider == null || !isAppPkg(resolveContentProvider.packageName)) {
                VLog.m18986w("ActivityManger", "getContentProvider:%s", str);
                replaceLastUserId(objArr);
                Object invoke = method.invoke(obj, objArr);
                if (invoke == null) {
                    return null;
                }
                if (BuildCompat.isOreo()) {
                    IInterface iInterface = ContentProviderHolderOreo.provider.get(invoke);
                    ProviderInfo providerInfo = ContentProviderHolderOreo.info.get(invoke);
                    if (iInterface != null) {
                        iInterface = ProviderHook.createProxy(true, providerInfo.authority, iInterface);
                    }
                    ContentProviderHolderOreo.provider.set(invoke, iInterface);
                } else {
                    IInterface iInterface2 = IActivityManager.C5112a.provider.get(invoke);
                    ProviderInfo providerInfo2 = IActivityManager.C5112a.info.get(invoke);
                    if (iInterface2 != null) {
                        iInterface2 = ProviderHook.createProxy(true, providerInfo2.authority, iInterface2);
                    }
                    IActivityManager.C5112a.provider.set(invoke, iInterface2);
                }
                return invoke;
            }
            ClientConfig initProcess = VActivityManager.get().initProcess(resolveContentProvider.packageName, resolveContentProvider.processName, myUserId);
            if (initProcess == null) {
                return null;
            }
            objArr[providerNameIndex] = StubManifest.getStubAuthority(initProcess.vpid, initProcess.is64Bit);
            replaceLastUserId(objArr);
            Object invoke2 = method.invoke(obj, objArr);
            if (invoke2 == null) {
                return null;
            }
            if (BuildCompat.isOreo()) {
                IInterface iInterface3 = ContentProviderHolderOreo.provider.get(invoke2);
                if (iInterface3 != null) {
                    iInterface3 = VActivityManager.get().acquireProviderClient(myUserId, resolveContentProvider);
                    z = false;
                }
                if (iInterface3 != null) {
                    ContentProviderHolderOreo.provider.set(invoke2, iInterface3);
                    ContentProviderHolderOreo.info.set(invoke2, resolveContentProvider);
                } else if (z) {
                    VLog.m18986w("VActivityManager", "Loading provider: " + resolveContentProvider.authority + "(" + resolveContentProvider.processName + ")", new Object[0]);
                    ContentProviderHolderOreo.info.set(invoke2, resolveContentProvider);
                    return invoke2;
                } else {
                    VLog.m18992e("VActivityManager", "acquireProviderClient fail: " + resolveContentProvider.authority + "(" + resolveContentProvider.processName + ")");
                    return null;
                }
            } else {
                IInterface iInterface4 = IActivityManager.C5112a.provider.get(invoke2);
                if (iInterface4 != null) {
                    iInterface4 = VActivityManager.get().acquireProviderClient(myUserId, resolveContentProvider);
                    z = false;
                }
                if (iInterface4 != null) {
                    IActivityManager.C5112a.provider.set(invoke2, iInterface4);
                    IActivityManager.C5112a.info.set(invoke2, resolveContentProvider);
                } else if (!z) {
                    VLog.m18992e("VActivityManager", "acquireProviderClient fail: " + resolveContentProvider.authority + "(" + resolveContentProvider.processName + ")");
                    return null;
                } else if (!BuildCompat.isMIUI() || !miuiProviderWaitingTargetProcess(invoke2)) {
                    return null;
                } else {
                    VLog.m18986w("VActivityManager", "miui provider waiting process: " + resolveContentProvider.authority + "(" + resolveContentProvider.processName + ")", new Object[0]);
                    return null;
                }
            }
            return invoke2;
        }

        public int getProviderNameIndex() {
            return BuildCompat.isQ() ? 2 : 1;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }

        private boolean miuiProviderWaitingTargetProcess(Object obj) {
            if (obj == null || IActivityManager.C5113b.waitProcessStart == null) {
                return false;
            }
            return IActivityManager.C5113b.waitProcessStart.get(obj);
        }
    }

    @TargetApi(21)
    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$SetTaskDescription */
    /* loaded from: classes.dex */
    public static class SetTaskDescription extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setTaskDescription";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Application currentApplication;
            Drawable loadIcon;
            ActivityManager.TaskDescription taskDescription = (ActivityManager.TaskDescription) objArr[1];
            String label = taskDescription.getLabel();
            Bitmap icon = taskDescription.getIcon();
            if ((label == null || icon == null) && (currentApplication = VClient.get().getCurrentApplication()) != null) {
                if (label == null) {
                    try {
                        label = currentApplication.getApplicationInfo().loadLabel(currentApplication.getPackageManager()).toString();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (icon == null && (loadIcon = currentApplication.getApplicationInfo().loadIcon(currentApplication.getPackageManager())) != null) {
                    icon = DrawableUtils.drawableToBitMap(loadIcon);
                }
                taskDescription = new ActivityManager.TaskDescription(label, icon, taskDescription.getPrimaryColor());
            }
            TaskDescriptionDelegate taskDescriptionDelegate = VirtualCore.get().getTaskDescriptionDelegate();
            if (taskDescriptionDelegate != null) {
                taskDescription = taskDescriptionDelegate.getTaskDescription(taskDescription);
            }
            objArr[1] = taskDescription;
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StopServiceToken */
    /* loaded from: classes.dex */
    static class StopServiceToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "stopServiceToken";
        }

        StopServiceToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ComponentName componentName = (ComponentName) objArr[0];
            IBinder iBinder = (IBinder) objArr[1];
            if (!VActivityManager.get().isVAServiceToken(iBinder)) {
                return method.invoke(obj, objArr);
            }
            int intValue = ((Integer) objArr[2]).intValue();
            if (componentName != null) {
                return Boolean.valueOf(VActivityManager.get().stopServiceToken(componentName, iBinder, intValue));
            }
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess() || isServerProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartActivityWithConfig */
    /* loaded from: classes.dex */
    public static class StartActivityWithConfig extends StartActivity {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startActivityWithConfig";
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return super.call(obj, method, objArr);
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$StartNextMatchingActivity */
    /* loaded from: classes.dex */
    static class StartNextMatchingActivity extends StartActivity {
        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startNextMatchingActivity";
        }

        StartNextMatchingActivity() {
        }

        @Override // com.lody.virtual.client.hook.proxies.p059am.MethodProxies.StartActivity, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return false;
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$BroadcastIntent */
    /* loaded from: classes.dex */
    static class BroadcastIntent extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "broadcastIntent";
        }

        BroadcastIntent() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Intent intent = (Intent) objArr[1];
            intent.setDataAndType(intent.getData(), (String) objArr[2]);
            Intent handleIntent = handleIntent(intent);
            if (handleIntent == null) {
                return 0;
            }
            objArr[1] = handleIntent;
            if ((objArr[7] instanceof String) || (objArr[7] instanceof String[])) {
                objArr[7] = null;
            }
            replaceLastUserId(objArr);
            return method.invoke(obj, objArr);
        }

        private Intent handleIntent(Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.CREATE_SHORTCUT".equals(action) || "com.android.launcher.action.INSTALL_SHORTCUT".equals(action) || "com.aliyun.homeshell.action.INSTALL_SHORTCUT".equals(action)) {
                if (getConfig().isAllowCreateShortcut()) {
                    return handleInstallShortcutIntent(intent);
                }
                return null;
            } else if ("com.android.launcher.action.UNINSTALL_SHORTCUT".equals(action) || "com.aliyun.homeshell.action.UNINSTALL_SHORTCUT".equals(action)) {
                handleUninstallShortcutIntent(intent);
                return intent;
            } else if ("android.intent.action.MEDIA_SCANNER_SCAN_FILE".equals(action)) {
                return handleMediaScannerIntent(intent);
            } else {
                if (BadgerManager.handleBadger(intent)) {
                    return null;
                }
                return ComponentUtils.redirectBroadcastIntent(intent, VUserHandle.myUserId());
            }
        }

        private Intent handleMediaScannerIntent(Intent intent) {
            String path;
            if (intent == null) {
                return null;
            }
            Uri data = intent.getData();
            if (data == null || !"file".equalsIgnoreCase(data.getScheme()) || (path = data.getPath()) == null) {
                return intent;
            }
            File file = new File(NativeEngine.getRedirectedPath(path));
            if (!file.exists()) {
                return intent;
            }
            intent.setData(Uri.fromFile(file));
            return intent;
        }

        private Intent handleInstallShortcutIntent(Intent intent) {
            ComponentName resolveActivity;
            Parcelable drawableToBitmap;
            Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
            if (!(intent2 == null || (resolveActivity = intent2.resolveActivity(VirtualCore.getPM())) == null)) {
                String packageName = resolveActivity.getPackageName();
                Intent intent3 = new Intent();
                intent3.addCategory("android.intent.category.DEFAULT");
                intent3.setAction(Constants.ACTION_SHORTCUT);
                intent3.setPackage(getHostPkg());
                intent3.putExtra("_VA_|_intent_", intent2);
                intent3.putExtra("_VA_|_uri_", intent2.toUri(0));
                intent3.putExtra("_VA_|_user_id_", VUserHandle.myUserId());
                intent.removeExtra("android.intent.extra.shortcut.INTENT");
                intent.putExtra("android.intent.extra.shortcut.INTENT", intent3);
                Intent.ShortcutIconResource shortcutIconResource = (Intent.ShortcutIconResource) intent.getParcelableExtra("android.intent.extra.shortcut.ICON_RESOURCE");
                if (shortcutIconResource != null && !TextUtils.equals(shortcutIconResource.packageName, getHostPkg())) {
                    try {
                        Resources resources = VirtualCore.get().getResources(packageName);
                        int identifier = resources.getIdentifier(shortcutIconResource.resourceName, "drawable", packageName);
                        if (identifier > 0 && (drawableToBitmap = BitmapUtils.drawableToBitmap(resources.getDrawable(identifier))) != null) {
                            intent.removeExtra("android.intent.extra.shortcut.ICON_RESOURCE");
                            intent.putExtra("android.intent.extra.shortcut.ICON", drawableToBitmap);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
            return intent;
        }

        private void handleUninstallShortcutIntent(Intent intent) {
            Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
            if (intent2 != null && intent2.resolveActivity(getPM()) != null) {
                Intent intent3 = new Intent();
                intent3.putExtra("_VA_|_uri_", intent2.toUri(0));
                intent3.setClassName(getHostPkg(), Constants.SHORTCUT_PROXY_ACTIVITY_NAME);
                intent3.removeExtra("android.intent.extra.shortcut.INTENT");
                intent.putExtra("android.intent.extra.shortcut.INTENT", intent3);
            }
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetActivityClassForToken */
    /* loaded from: classes.dex */
    static class GetActivityClassForToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getActivityClassForToken";
        }

        GetActivityClassForToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return VActivityManager.get().getActivityForToken((IBinder) objArr[0]);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GrantUriPermission */
    /* loaded from: classes.dex */
    static class GrantUriPermission extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "grantUriPermission";
        }

        GrantUriPermission() {
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

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$CheckGrantUriPermission */
    /* loaded from: classes.dex */
    static class CheckGrantUriPermission extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "checkGrantUriPermission";
        }

        CheckGrantUriPermission() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            return method.invoke(obj, objArr);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$ServiceDoneExecuting */
    /* loaded from: classes.dex */
    static class ServiceDoneExecuting extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "serviceDoneExecuting";
        }

        ServiceDoneExecuting() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IBinder iBinder = (IBinder) objArr[0];
            if (!VActivityManager.get().isVAServiceToken(iBinder)) {
                return method.invoke(obj, objArr);
            }
            VActivityManager.get().serviceDoneExecuting(iBinder, ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
            return 0;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$isUserRunning */
    /* loaded from: classes.dex */
    static class isUserRunning extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "isUserRunning";
        }

        isUserRunning() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) {
            int intValue = ((Integer) objArr[0]).intValue();
            for (VUserInfo vUserInfo : VUserManager.get().getUsers()) {
                if (vUserInfo.f10500id == intValue) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$GetPackageProcessState */
    /* loaded from: classes.dex */
    static class GetPackageProcessState extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackageProcessState";
        }

        GetPackageProcessState() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return 4;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    /* renamed from: com.lody.virtual.client.hook.proxies.am.MethodProxies$OverridePendingTransition */
    /* loaded from: classes.dex */
    public static class OverridePendingTransition extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "overridePendingTransition";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!VClient.get().isAppUseOutsideAPK()) {
                return 0;
            }
            return super.call(obj, method, objArr);
        }
    }
}
