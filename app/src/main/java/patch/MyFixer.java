package patch;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.kaopu.tiantian.Global.ViewUtil;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.helper.collection.ArraySet;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.remote.InstalledAppInfo;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.p105io.FilenameUtils;
import p110z1.SocketConstants;

/* renamed from: patch.b */
/* loaded from: classes2.dex */
public class MyFixer {

    /* renamed from: a */
    public static final List<String> f15014a = Arrays.asList(TbsConfig.APP_WX, TbsConfig.APP_QQ, "com.tencent.mobileqqi", "com.tencent.minihd.qq", "com.tencent.qqlite");

    /* renamed from: a */
    public static void m14554a(Activity activity) {
        try {
            PatchedResources dVar = new PatchedResources(activity.getAssets(), activity.getResources().getDisplayMetrics(), activity.getResources().getConfiguration(), 0);
            Field declaredField = ContextThemeWrapper.class.getDeclaredField("mResources");
            declaredField.setAccessible(true);
            declaredField.set(activity, dVar);
            Field declaredField2 = Resources.class.getDeclaredField("mSystem");
            declaredField2.setAccessible(true);
            declaredField2.set(null, dVar);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    public static List<ActivityManager.RunningAppProcessInfo> m14545a(List<ActivityManager.RunningAppProcessInfo> list) {
        if (VirtualCore.get().isVAppProcess()) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().processName.contains(VirtualCore.get().getHostPkg())) {
                    it.remove();
                }
            }
        }
        return list;
    }

    /* renamed from: a */
    public static void m14548a(InstalledAppInfo installedAppInfo) {
        String str = new String(new char[]{'l', 'i', 'b', 'N', 'e', 't', 'H', 'T', 'P', 'r', 'o', 't', 'e', 'c', 't', FilenameUtils.EXTENSION_SEPARATOR, 's', 'o'});
        String absolutePath = VEnvironment.getAppLibDirectory(installedAppInfo.packageName).getAbsolutePath();
        new File(absolutePath + "/" + str).exists();
    }

    /* renamed from: a */
    public static void m14551a(Intent intent) {
        Intent intent2 = new Intent(intent);
        ComponentName component = intent2.getComponent();
        if (component != null && component.getClassName().contains(".wxapi.WXEntryActivity") && intent2.hasExtra("_wxapi_baseresp_errcode") && intent2.getIntExtra("_wxapi_baseresp_errcode", 0) == -1 && component.getClassName().contains(".wxapi.WXEntryActivity")) {
            String str = VirtualCore.get().getContext().getFilesDir().getParent() + "/virtual/data/user/0/com.tencent.mm/";
            File file = new File(str);
            if (file.exists() && file.listFiles() != null && file.listFiles().length != 0) {
                m14542b(str);
                new Thread(new Runnable() { // from class: patch.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Thread.sleep(1000L);
                        } catch (Exception unused) {
                        }
                        VirtualRuntime.exit();
                    }
                }).start();
            }
        }
    }

    /* renamed from: b */
    private static void m14542b(String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && !file.getName().contains("lib")) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    if (!file2.getName().contains("lib")) {
                        m14542b(file2.getAbsolutePath());
                    }
                }
                file2.delete();
            }
        }
    }

    /* renamed from: a */
    public static void m14546a(String str) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) VirtualCore.get().getContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.contains(str)) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    /* renamed from: c */
    private static void m14539c(String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && !file.getName().contains("lib")) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory() && !file2.getName().contains("lib")) {
                    m14539c(file2.getAbsolutePath());
                }
                file2.delete();
            }
        }
    }

    /* renamed from: a */
    public static void m14553a(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.xiaomi.game.plugin.stat.MiGamePluginStat");
            Field declaredField = loadClass.getDeclaredField("b");
            declaredField.setAccessible(true);
            Boolean.valueOf(declaredField.getBoolean(loadClass));
            Boolean bool = false;
            declaredField.setBoolean(null, bool.booleanValue());
            Boolean.valueOf(declaredField.getBoolean(loadClass));
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public static void m14544b(Activity activity) {
        if (Names.f15015a.contains(activity.getComponentName().getClassName()) && DevicesInfo.m14555b()) {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealMetrics(displayMetrics);
            }
            try {
                SurfaceView surfaceView = (SurfaceView) ((ViewGroup) ((FrameLayout) activity.getWindow().getDecorView().findViewById(16908290)).getChildAt(0)).getChildAt(0);
                ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
                layoutParams.height = 1080;
                layoutParams.width = 1920;
                surfaceView.setLayoutParams(layoutParams);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static Object m14547a(Object obj, Method method, Object[] objArr) {
        int intValue = ((Integer) objArr[0]).intValue();
        if ("com.huawei.hwid".equals(VirtualRuntime.getProcessName())) {
            String[] strArr = new String[10];
            ArraySet arraySet = new ArraySet(10);
            int i = 0;
            for (InstalledAppInfo installedAppInfo : VirtualCore.get().getInstalledApps(0)) {
                if (VirtualCore.get().isAppRunning(installedAppInfo.packageName, 0, true) && !f15014a.contains(installedAppInfo.packageName)) {
                    i++;
                    strArr[i] = installedAppInfo.packageName;
                }
            }
            if (strArr.length > 0) {
                arraySet.addAll(Arrays.asList(strArr));
            }
            return arraySet.toArray(new String[arraySet.size()]);
        }
        if (intValue == VirtualCore.get().getContext().getApplicationInfo().uid) {
            int callingPid = Binder.getCallingPid();
            if (callingPid == -1 || callingPid == Process.myPid()) {
                intValue = Process.myUid();
            } else {
                intValue = Binder.getCallingUid();
            }
        }
        ArraySet arraySet2 = new ArraySet(2);
        String[] packagesForUid = VPackageManager.get().getPackagesForUid(intValue);
        if (packagesForUid != null && packagesForUid.length > 0) {
            arraySet2.addAll(Arrays.asList(packagesForUid));
        }
        return arraySet2.toArray(new String[arraySet2.size()]);
    }

    /* renamed from: b */
    public static int m14543b(Context context) {
        return m14534g(context).widthPixels;
    }

    /* renamed from: c */
    public static int m14540c(Context context) {
        return m14534g(context).heightPixels;
    }

    /* renamed from: g */
    private static DisplayMetrics m14534g(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /* renamed from: a */
    public static boolean m14552a(Context context, String str) {
        if (!"com.cashtoutiao".equals(str)) {
            return true;
        }
        try {
            Field declaredField = context.getClassLoader().loadClass("com.cashtoutiao.common.AppEnvironment").getDeclaredField("ADVIEW_DOWNLOAD_PATH");
            declaredField.setAccessible(true);
            declaredField.get(null);
            declaredField.set(null, "/data/data/com.cashtoutiao/files");
            declaredField.get(null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static void m14541b(List<PackageInfo> list) {
        Iterator<PackageInfo> it = list.iterator();
        while (it.hasNext()) {
            PackageInfo next = it.next();
            if (next.packageName.contains("com.cyjh.gundam") || next.packageName.contains("com.angel.nrzs")) {
                it.remove();
            }
        }
    }

    /* renamed from: c */
    public static List<ApplicationInfo> m14538c(List<ApplicationInfo> list) {
        Iterator<ApplicationInfo> it = list.iterator();
        while (it.hasNext()) {
            ApplicationInfo next = it.next();
            if (next.processName.contains("com.cyjh.gundam") || next.packageName.contains("com.angel.nrzs")) {
                it.remove();
            }
        }
        return list;
    }

    /* renamed from: d */
    public static void m14537d(Context context) {
        if (context.getPackageName().equals("com.tencent.crossgate")) {
            VLog.m18993d("sunya", "是魔力宝贝 开始判断刘海", new Object[0]);
            VLog.m18993d("sunya", "before moliliuhai=" + ((Boolean) Reflect.m18996on("com.example.crossgate.MainActivity", context.getClassLoader()).get("m_IsInitTopLiuHai")) + ExpandableTextView.f6958c + ((Boolean) Reflect.m18996on("com.example.crossgate.MainActivity", context.getClassLoader()).get("m_IsTopLiuHai")), new Object[0]);
            Reflect.m18996on("com.example.crossgate.MainActivity", context.getClassLoader()).set("m_IsInitTopLiuHai", true);
            Reflect.m18996on("com.example.crossgate.MainActivity", context.getClassLoader()).set("m_IsTopLiuHai", false);
        }
    }

    /* renamed from: a */
    public static int[] m14549a(Window window) {
        return ViewUtil.caculatePadding(window);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent m14550a(android.content.Intent r5, java.io.File r6, java.lang.String r7, android.content.ContentResolver r8) {
        /*
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            android.content.ComponentName r1 = new android.content.ComponentName
            com.lody.virtual.client.core.VirtualCore r2 = com.lody.virtual.client.core.VirtualCore.get()
            java.lang.String r2 = r2.getHostPkg()
            java.lang.String r3 = "com.nrzs.game.ui.activity.PXKJInnerInstallCallDialog"
            r1.<init>(r2, r3)
            r0.setComponent(r1)
            java.lang.String r1 = r5.getScheme()
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x007e
            android.net.Uri r5 = r5.getData()
            if (r5 == 0) goto L_0x0091
            java.io.File r7 = new java.io.File
            java.lang.String r1 = r5.getLastPathSegment()
            r7.<init>(r6, r1)
            r6 = 0
            java.io.InputStream r5 = r8.openInputStream(r5)     // Catch: all -> 0x005a, IOException -> 0x005f
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch: all -> 0x0051, IOException -> 0x0055
            r8.<init>(r7)     // Catch: all -> 0x0051, IOException -> 0x0055
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch: IOException -> 0x004f, all -> 0x0076
            if (r5 == 0) goto L_0x004b
        L_0x0040:
            int r1 = r5.read(r6)     // Catch: IOException -> 0x004f, all -> 0x0076
            if (r1 <= 0) goto L_0x004b
            r2 = 0
            r8.write(r6, r2, r1)     // Catch: IOException -> 0x004f, all -> 0x0076
            goto L_0x0040
        L_0x004b:
            r8.flush()     // Catch: IOException -> 0x004f, all -> 0x0076
            goto L_0x0066
        L_0x004f:
            r6 = move-exception
            goto L_0x0063
        L_0x0051:
            r7 = move-exception
            r8 = r6
            r6 = r7
            goto L_0x0077
        L_0x0055:
            r8 = move-exception
            r4 = r8
            r8 = r6
            r6 = r4
            goto L_0x0063
        L_0x005a:
            r5 = move-exception
            r8 = r6
            r6 = r5
            r5 = r8
            goto L_0x0077
        L_0x005f:
            r5 = move-exception
            r8 = r6
            r6 = r5
            r5 = r8
        L_0x0063:
            r6.printStackTrace()     // Catch: all -> 0x0076
        L_0x0066:
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r5)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r8)
            java.lang.String r5 = "url"
            java.lang.String r6 = r7.getPath()
            r0.putExtra(r5, r6)
            goto L_0x0091
        L_0x0076:
            r6 = move-exception
        L_0x0077:
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r5)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r8)
            throw r6
        L_0x007e:
            android.net.Uri r6 = r5.getData()
            if (r6 == 0) goto L_0x0091
            java.lang.String r6 = "url"
            android.net.Uri r5 = r5.getData()
            java.lang.String r5 = r5.getPath()
            r0.putExtra(r6, r5)
        L_0x0091:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: patch.MyFixer.m14550a(android.content.Intent, java.io.File, java.lang.String, android.content.ContentResolver):android.content.Intent");
    }

    /* renamed from: e */
    public static void m14536e(Context context) {
        int indexOf;
        ComponentName componentName;
        if (context != null) {
            if (!(context instanceof Activity) || (componentName = ((Activity) context).getComponentName()) == null || (!componentName.getClassName().contains("excelliance") && !componentName.getClassName().contains("Plugin"))) {
                File filesDir = context.getFilesDir();
                Context context2 = (Context) Reflect.m18998on(context).get("mBase");
                String absolutePath = filesDir.getAbsolutePath();
                if (!absolutePath.contains("xxajh") && (indexOf = absolutePath.indexOf(SocketConstants.f15239b)) >= 0) {
                    Reflect.m18998on(context2).set("mFilesDir", new File(absolutePath.substring(indexOf + 7)));
                }
            }
        }
    }

    /* renamed from: f */
    public static void m14535f(Context context) {
        File cacheDir;
        if (context != null && (cacheDir = context.getCacheDir()) != null) {
            Context context2 = (Context) Reflect.m18998on(context).get("mBase");
            String absolutePath = cacheDir.getAbsolutePath();
            int indexOf = absolutePath.indexOf(SocketConstants.f15239b);
            if (indexOf >= 0) {
                Reflect.m18998on(context2).set("mCacheDir", new File(absolutePath.substring(indexOf + 7)));
            }
        }
    }
}
