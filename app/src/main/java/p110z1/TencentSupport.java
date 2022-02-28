package p110z1;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.remote.InstalledAppInfo;
import com.nrzs.va.AppInstallOptions;
import com.nrzs.va.VirtualCoreProxy;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/* renamed from: z1.ait */
/* loaded from: classes3.dex */
public class TencentSupport {

    /* renamed from: a */
    public static final List<String> f16013a = Arrays.asList("com.tencent.mobileqqi", "com.tencent.minihd.qq", "com.tencent.qqlite", "com.huawei.gamebox", "com.huawei.hwid", "com.xiaomi.gamecenter.sdk.service", "com.meizu.gamecenter.service");

    /* renamed from: b */
    public static final List<String> f16014b = Arrays.asList(TbsConfig.APP_WX, TbsConfig.APP_QQ, "com.tencent.mobileqqi", "com.tencent.minihd.qq", "com.tencent.qqlite", "com.huawei.gamebox", "com.huawei.hwid", "com.xiaomi.gamecenter.sdk.service", "com.meizu.gamecenter.service", "com.sheep.jiuyan.samllsheep");

    /* renamed from: a */
    private static void m12995a(List<String> list, int i) {
        VirtualCore virtualCore = VirtualCore.get();
        for (String str : list) {
            boolean z = false;
            InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(str, 0);
            if (installedAppInfo != null && !new File(installedAppInfo.getApkPath()).exists()) {
                z = true;
            }
            m12996a(virtualCore, str, i, z);
        }
    }

    /* renamed from: a */
    public static void m12996a(VirtualCore virtualCore, String str, int i, boolean z) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = VirtualCoreProxy.getUnHookPackageManager().getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        if (applicationInfo != null && applicationInfo.sourceDir != null) {
            if (i != 0) {
                virtualCore.installPackageAsUser(i, str);
            } else if (z) {
                VirtualCoreProxy.installPackageSync(applicationInfo.sourceDir, AppInstallOptions.makeOptions(true, false));
            } else {
                if ("64bit".equals(VirtualCoreProxy.installPackageSync(applicationInfo.sourceDir, AppInstallOptions.makeOptions(true, false)).error)) {
                    App64 aiwVar = new App64();
                    aiwVar.m12972b(applicationInfo.name);
                    aiwVar.m12974a(applicationInfo.packageName);
                    App64Manager.INSTANCE.put(aiwVar);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m12997a(int i) {
        m12995a(f16013a, i);
    }
}
