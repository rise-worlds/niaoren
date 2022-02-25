package p110z1;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.remote.InstalledAppInfo;
import com.nrzs.core.models.AppData;
import com.nrzs.core.models.AppInfo;
import com.nrzs.core.models.AppInfoLite;
import com.nrzs.core.models.MultiplePackageAppData;
import com.nrzs.core.models.PackageAppData;
import com.nrzs.p072va.AppInstallOptions;
import com.nrzs.p072va.AppInstallResult;
import com.nrzs.p072va.VirtualCoreProxy;
import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

/* renamed from: z1.ajf */
/* loaded from: classes3.dex */
public class AppRepository implements AppDataSource {

    /* renamed from: a */
    private static final Collator f16043a = Collator.getInstance(Locale.CHINA);

    /* renamed from: b */
    private static final List<String> f16044b = Arrays.asList(Consts.f23430h, "wandoujia/app", "tencent/tassistant/apk", "BaiduAsa9103056", "360Download", "pp/downloader", "pp/downloader/apk", "pp/downloader/silent/apk");

    /* renamed from: c */
    private Context f16045c;

    public AppRepository(Context context) {
        this.f16045c = context;
    }

    /* renamed from: a */
    private static boolean m12953a(PackageInfo packageInfo) {
        return packageInfo.applicationInfo.uid <= 10000 || (packageInfo.applicationInfo.flags & 1) != 0;
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public Promise<List<AppData>, Throwable, Void> mo12961a() {
        return VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$ajf$UtCp6Q_lPCUd6k1Ps9HXKw51R0k
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List b;
                b = AppRepository.this.m12949b();
                return b;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ List m12949b() throws Exception {
        int[] installedUsers;
        List<InstalledAppInfo> installedApps = VirtualCore.get().getInstalledApps(0);
        ArrayList arrayList = new ArrayList();
        for (InstalledAppInfo installedAppInfo : installedApps) {
            boolean z = !new File(installedAppInfo.getApkPath()).exists();
            VirtualCore.get().isPackageLaunchable(installedAppInfo.packageName);
            PackageAppData dVar = new PackageAppData(this.f16045c, installedAppInfo);
            if (VirtualCore.get().isAppInstalledAsUser(0, installedAppInfo.packageName)) {
                dVar.f10579j = installedAppInfo.getApkPath();
                dVar.f10578i = z;
                arrayList.add(dVar);
            }
            for (int i : installedAppInfo.getInstalledUsers()) {
                if (i != 0) {
                    MultiplePackageAppData cVar = new MultiplePackageAppData(dVar, i);
                    cVar.f10567j = installedAppInfo.getApkPath();
                    cVar.f10566i = z;
                    arrayList.add(cVar);
                }
            }
        }
        return arrayList;
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public Promise<AppData, Throwable, Void> mo12951a(final String str) {
        return VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$ajf$8UUFLAju7ZvyTq26yz_mO_Jx28U
            @Override // java.util.concurrent.Callable
            public final Object call() {
                AppData b;
                b = AppRepository.this.m12945b(str);
                return b;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ AppData m12945b(String str) throws Exception {
        int i = 0;
        InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(str, 0);
        MultiplePackageAppData cVar = null;
        if (installedAppInfo == null) {
            return null;
        }
        boolean z = !new File(installedAppInfo.getApkPath()).exists();
        PackageAppData dVar = new PackageAppData(this.f16045c, installedAppInfo);
        if (VirtualCore.get().isAppInstalledAsUser(0, installedAppInfo.packageName)) {
            dVar.f10581l = installedAppInfo.flag;
            dVar.f10579j = installedAppInfo.getApkPath();
            dVar.f10578i = z;
            cVar = dVar;
        }
        int[] installedUsers = installedAppInfo.getInstalledUsers();
        int length = installedUsers.length;
        MultiplePackageAppData cVar2 = cVar;
        while (i < length) {
            int i2 = installedUsers[i];
            if (i2 != 0) {
                cVar2 = new MultiplePackageAppData(dVar, i2);
                cVar2.f10567j = installedAppInfo.getApkPath();
                cVar2.f10566i = z;
                cVar2.f10569l = installedAppInfo.flag;
            }
            i++;
            cVar2 = cVar2;
        }
        return cVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ List m12948b(Context context) throws Exception {
        return m12954a(context, context.getPackageManager().getInstalledPackages(0), true);
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public Promise<List<AppInfo>, Throwable, Void> mo12960a(final Context context) {
        return VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$ajf$9tW4c3rOkC-Hus_10vj7lnQpfWo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List b;
                b = AppRepository.this.m12948b(context);
                return b;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public /* synthetic */ AppInfo m12942e(Context context, String str) throws Exception {
        return mo12955a(context, str, true);
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public Promise<AppInfo, Throwable, Void> mo12956a(final Context context, final String str) {
        return VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$ajf$YnLHKBZ6XYNEVZz2rhWT9vZUfTo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                AppInfo e;
                e = AppRepository.this.m12942e(context, str);
                return e;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ List m12947b(Context context, File file) throws Exception {
        return m12954a(context, m12957a(context, file, f16044b), false);
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public Promise<List<AppInfo>, Throwable, Void> mo12958a(final Context context, final File file) {
        return VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$ajf$Lmt914X_LC_FNF84SpLndZhhFx0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List b;
                b = AppRepository.this.m12947b(context, file);
                return b;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public /* synthetic */ AppInfo m12943d(Context context, String str) throws Exception {
        return m12959a(context, m12944c(context, str), false);
    }

    @Override // p110z1.AppDataSource
    /* renamed from: b */
    public Promise<AppInfo, Throwable, Void> mo12946b(final Context context, final String str) {
        return VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$ajf$dyJPLjylGMoiqudnRCaUUB6igC4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                AppInfo d;
                d = AppRepository.this.m12943d(context, str);
                return d;
            }
        });
    }

    /* renamed from: a */
    public AppInfo m12959a(Context context, PackageInfo packageInfo, boolean z) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null) {
            return null;
        }
        String str = applicationInfo.publicSourceDir != null ? applicationInfo.publicSourceDir : applicationInfo.sourceDir;
        AppInfo bVar = new AppInfo();
        bVar.f10550a = applicationInfo.packageName;
        bVar.f10552c = z;
        bVar.f10551b = str;
        bVar.f10553d = applicationInfo.loadIcon(packageManager);
        bVar.f10554e = applicationInfo.loadLabel(packageManager);
        bVar.f10552c = z;
        InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(packageInfo.packageName, 0);
        if (installedAppInfo != null) {
            bVar.f10555f = installedAppInfo.getInstalledUsers().length;
        }
        return bVar;
    }

    /* renamed from: c */
    private PackageInfo m12944c(Context context, String str) {
        File file = new File(str);
        PackageInfo packageInfo = null;
        try {
            if (!file.exists()) {
                return null;
            }
            packageInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
            packageInfo.applicationInfo.sourceDir = file.getAbsolutePath();
            packageInfo.applicationInfo.publicSourceDir = file.getAbsolutePath();
            return packageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return packageInfo;
        }
    }

    /* renamed from: a */
    private List<PackageInfo> m12957a(Context context, File file, List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (String str : list) {
            File[] listFiles = new File(file, str).listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.getName().toLowerCase().endsWith(".apk")) {
                        PackageInfo packageInfo = null;
                        try {
                            packageInfo = context.getPackageManager().getPackageArchiveInfo(file2.getAbsolutePath(), 0);
                            packageInfo.applicationInfo.sourceDir = file2.getAbsolutePath();
                            packageInfo.applicationInfo.publicSourceDir = file2.getAbsolutePath();
                        } catch (Exception unused) {
                        }
                        if (packageInfo != null) {
                            arrayList.add(packageInfo);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public AppInfo mo12955a(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (applicationInfo == null) {
                return null;
            }
            String str2 = applicationInfo.publicSourceDir != null ? applicationInfo.publicSourceDir : applicationInfo.sourceDir;
            AppInfo bVar = new AppInfo();
            bVar.f10550a = applicationInfo.packageName;
            bVar.f10552c = z;
            bVar.f10551b = str2;
            bVar.f10553d = applicationInfo.loadIcon(packageManager);
            bVar.f10554e = applicationInfo.loadLabel(packageManager);
            bVar.f10552c = z;
            return bVar;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private List<AppInfo> m12954a(Context context, List<PackageInfo> list, boolean z) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList(list.size());
        String hostPkg = VirtualCore.get().getHostPkg();
        for (PackageInfo packageInfo : list) {
            if (!hostPkg.equals(packageInfo.packageName) && !m12953a(packageInfo)) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                String str = applicationInfo.publicSourceDir != null ? applicationInfo.publicSourceDir : applicationInfo.sourceDir;
                if (str != null) {
                    AppInfo bVar = new AppInfo();
                    bVar.f10550a = packageInfo.packageName;
                    bVar.f10552c = z;
                    bVar.f10551b = str;
                    bVar.f10553d = applicationInfo.loadIcon(packageManager);
                    bVar.f10554e = applicationInfo.loadLabel(packageManager);
                    InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(packageInfo.packageName, 0);
                    if (installedAppInfo != null) {
                        bVar.f10555f = installedAppInfo.getInstalledUsers().length;
                    }
                    arrayList.add(bVar);
                }
            }
        }
        return arrayList;
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public AppInstallResult mo12952a(AppInfoLite appInfoLite) {
        return VirtualCoreProxy.installPackageSync(appInfoLite.f10534e, AppInstallOptions.makeOptions(appInfoLite.f10535f, false));
    }

    @Override // p110z1.AppDataSource
    /* renamed from: a */
    public boolean mo12950a(String str, int i) {
        return VirtualCore.get().uninstallPackageAsUser(str, i);
    }
}
