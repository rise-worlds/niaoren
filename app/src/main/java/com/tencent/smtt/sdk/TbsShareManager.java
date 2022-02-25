package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.ApkUtil;
import com.tencent.smtt.utils.AppUtil;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes2.dex */
public class TbsShareManager {

    /* renamed from: a */
    private static Context f12972a = null;

    /* renamed from: b */
    private static boolean f12973b = false;

    /* renamed from: c */
    private static String f12974c = null;

    /* renamed from: d */
    private static String f12975d = null;

    /* renamed from: e */
    private static int f12976e = 0;

    /* renamed from: f */
    private static String f12977f = null;

    /* renamed from: g */
    private static boolean f12978g = false;

    /* renamed from: h */
    private static boolean f12979h = false;

    /* renamed from: i */
    private static boolean f12980i = false;

    /* renamed from: j */
    private static String f12981j = null;

    /* renamed from: k */
    private static boolean f12982k = false;

    /* renamed from: l */
    private static boolean f12983l = false;
    public static boolean mHasQueryed = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16964a(Context context) {
        TbsLog.m16531i("TbsShareManager", "shareTbsCore #1");
        try {
            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(context);
            m16962a(context, tbsLinuxToolsJni, TbsInstaller.m16742a().m16678q(context));
            File r = TbsInstaller.m16742a().m16677r(context);
            TbsLog.m16531i("TbsShareManager", "shareTbsCore tbsShareDir is " + r.getAbsolutePath());
            tbsLinuxToolsJni.m17007a(r.getAbsolutePath(), "755");
        } catch (Throwable th) {
            TbsLog.m16531i("TbsShareManager", "shareTbsCore tbsShareDir error is " + th.getMessage() + " ## " + th.getCause());
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m16960b(Context context) {
        try {
            m16962a(context, new TbsLinuxToolsJni(context), TbsInstaller.m16742a().m16679p(context));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m16962a(Context context, TbsLinuxToolsJni tbsLinuxToolsJni, File file) {
        File[] listFiles;
        TbsLog.m16531i("TbsShareManager", "shareAllDirsAndFiles #1");
        if (file != null && file.exists() && file.isDirectory()) {
            TbsLog.m16531i("TbsShareManager", "shareAllDirsAndFiles dir is " + file.getAbsolutePath());
            tbsLinuxToolsJni.m17007a(file.getAbsolutePath(), "755");
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    if (file2.getAbsolutePath().indexOf(".so") > 0) {
                        tbsLinuxToolsJni.m17007a(file2.getAbsolutePath(), "755");
                    } else {
                        tbsLinuxToolsJni.m17007a(file2.getAbsolutePath(), "644");
                    }
                } else if (file2.isDirectory()) {
                    m16962a(context, tbsLinuxToolsJni, file2);
                } else {
                    TbsLog.m16532e("TbsShareManager", "unknown file type.", true);
                }
            }
        }
    }

    public static void setHostCorePathAppDefined(String str) {
        f12974c = str;
    }

    public static String getHostCorePathAppDefined() {
        return f12974c;
    }

    public static boolean isThirdPartyApp(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f12972a != null && f12972a.equals(context.getApplicationContext())) {
            return f12973b;
        }
        f12972a = context.getApplicationContext();
        String packageName = f12972a.getPackageName();
        for (String str : getCoreProviderAppList()) {
            if (packageName.equals(str)) {
                f12973b = false;
                return false;
            }
        }
        f12973b = true;
        return true;
    }

    public static String[] getCoreProviderAppList() {
        return new String[]{TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE};
    }

    public static long getHostCoreVersions(Context context) {
        String[] coreProviderAppList;
        long j = 0;
        for (String str : getCoreProviderAppList()) {
            if (str.equalsIgnoreCase(TbsConfig.APP_WX)) {
                j += getSharedTbsCoreVersion(context, str) * 10000000000L;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QQ)) {
                j += getSharedTbsCoreVersion(context, str) * 100000;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QZONE)) {
                j += getSharedTbsCoreVersion(context, str);
            }
        }
        return j;
    }

    public static int getSharedTbsCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return TbsInstaller.m16742a().m16688i(packageContext);
        }
        return 0;
    }

    public static int getCoreShareDecoupleCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return TbsInstaller.m16742a().m16690h(packageContext);
        }
        return 0;
    }

    public static int getBackupCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.m16465a(getPackageContext(context, str, false), 4)), "x5.tbs.org");
            if (file.exists()) {
                return ApkUtil.m16513b(file);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static int getBackupDecoupleCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.m16465a(getPackageContext(context, str, false), 4)), "x5.tbs.decouple");
            if (file.exists()) {
                return ApkUtil.m16513b(file);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static File getBackupCoreFile(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.m16465a(getPackageContext(context, str, false), 4)), "x5.tbs.org");
            if (file.exists()) {
                return file;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static File getBackupDecoupleCoreFile(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.m16465a(getPackageContext(context, str, true), 4)), "x5.tbs.decouple");
            if (file.exists()) {
                return file;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean getCoreDisabled() {
        return f12978g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m16958c(Context context) {
        m16949j(context);
        return f12975d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m16965a() {
        return f12975d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static int m16956d(Context context) {
        return m16961a(context, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m16961a(Context context, boolean z) {
        m16959b(context, z);
        return f12976e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static Context m16954e(Context context) {
        m16949j(context);
        String str = f12977f;
        Context context2 = null;
        if (str != null) {
            Context packageContext = getPackageContext(context, str, true);
            if (TbsInstaller.m16742a().m16696f(packageContext)) {
                context2 = packageContext;
            }
        }
        return f12974c != null ? f12972a : context2;
    }

    /* renamed from: k */
    private static boolean m16948k(Context context) {
        String str = f12977f;
        if (str == null) {
            return false;
        }
        return f12976e == getSharedTbsCoreVersion(context, str) || f12976e == getCoreShareDecoupleCoreVersion(context, f12977f);
    }

    /* renamed from: l */
    private static boolean m16947l(Context context) {
        if (QbSdk.getOnlyDownload()) {
            return false;
        }
        String[] coreProviderAppList = getCoreProviderAppList();
        for (String str : coreProviderAppList) {
            int i = f12976e;
            if (i > 0 && i == getSharedTbsCoreVersion(context, str)) {
                Context packageContext = getPackageContext(context, str, true);
                if (TbsInstaller.m16742a().m16696f(context)) {
                    f12975d = TbsInstaller.m16742a().m16718b(context, packageContext).getAbsolutePath();
                    f12977f = str;
                    return true;
                }
            }
        }
        for (String str2 : coreProviderAppList) {
            int i2 = f12976e;
            if (i2 > 0 && i2 == getCoreShareDecoupleCoreVersion(context, str2)) {
                Context packageContext2 = getPackageContext(context, str2, true);
                if (TbsInstaller.m16742a().m16696f(context)) {
                    f12975d = TbsInstaller.m16742a().m16709c(context, packageContext2).getAbsolutePath();
                    f12977f = str2;
                    return true;
                }
            }
        }
        return false;
    }

    public static int findCoreForThirdPartyApp(Context context) {
        m16945n(context);
        TbsLog.m16531i("TbsShareManager", "core_info mAvailableCoreVersion is " + f12976e + " mAvailableCorePath is " + f12975d + " mSrcPackageName is " + f12977f);
        if (f12977f == null) {
            TbsLog.m16533e("TbsShareManager", "mSrcPackageName is null !!!");
        }
        String str = f12977f;
        if (str == null || !str.equals("AppDefined")) {
            if (!m16948k(context) && !m16947l(context)) {
                f12976e = 0;
                f12975d = null;
                f12977f = null;
                TbsLog.m16531i("TbsShareManager", "core_info error checkCoreInfo is false and checkCoreInOthers is false ");
            }
        } else if (f12976e != TbsInstaller.m16742a().m16723a(f12974c)) {
            f12976e = 0;
            f12975d = null;
            f12977f = null;
            TbsLog.m16531i("TbsShareManager", "check AppDefined core is error src is " + f12976e + " dest is " + TbsInstaller.m16742a().m16723a(f12974c));
        }
        if (f12976e > 0) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if ((!("com.tencent.android.qqdownloader".equals(applicationInfo.packageName) || "com.jd.jrapp".equals(applicationInfo.packageName)) ? QbSdk.m17059a(context, f12976e) : false) || f12978g) {
                f12976e = 0;
                f12975d = null;
                f12977f = null;
                TbsLog.m16531i("TbsShareManager", "core_info error QbSdk.isX5Disabled ");
            }
        }
        return f12976e;
    }

    /* renamed from: m */
    private static boolean m16946m(Context context) {
        if (context == null) {
            return false;
        }
        writeProperties(context, Integer.toString(0), "", "", Integer.toString(0));
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:14:0x0067
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: c */
    private static void m16957c(android.content.Context r6, boolean r7) {
        /*
            r0 = 0
            java.lang.String r1 = "core_info"
            java.io.File r1 = getTbsShareFile(r6, r1)     // Catch: Throwable -> 0x0074
            if (r1 != 0) goto L_0x000a
            return
        L_0x000a:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: Throwable -> 0x0074
            r2.<init>(r1)     // Catch: Throwable -> 0x0074
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x0074
            r3.<init>(r2)     // Catch: Throwable -> 0x0074
            java.util.Properties r2 = new java.util.Properties     // Catch: Throwable -> 0x006d
            r2.<init>()     // Catch: Throwable -> 0x006d
            r2.load(r3)     // Catch: Throwable -> 0x006d
            java.lang.String r4 = "core_disabled"
            r5 = 0
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: Throwable -> 0x006d
            r2.setProperty(r4, r5)     // Catch: Throwable -> 0x006d
            if (r7 == 0) goto L_0x0053
            com.tencent.smtt.sdk.l r7 = com.tencent.smtt.sdk.TbsInstaller.m16742a()     // Catch: Throwable -> 0x006d
            java.io.File r7 = r7.m16678q(r6)     // Catch: Throwable -> 0x006d
            java.lang.String r7 = r7.getAbsolutePath()     // Catch: Throwable -> 0x006d
            android.content.Context r4 = r6.getApplicationContext()     // Catch: Throwable -> 0x006d
            java.lang.String r4 = r4.getPackageName()     // Catch: Throwable -> 0x006d
            int r6 = com.tencent.smtt.utils.AppUtil.m16501b(r6)     // Catch: Throwable -> 0x006d
            java.lang.String r5 = "core_packagename"
            r2.setProperty(r5, r4)     // Catch: Throwable -> 0x006d
            java.lang.String r4 = "core_path"
            r2.setProperty(r4, r7)     // Catch: Throwable -> 0x006d
            java.lang.String r7 = "app_version"
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: Throwable -> 0x006d
            r2.setProperty(r7, r6)     // Catch: Throwable -> 0x006d
        L_0x0053:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: Throwable -> 0x006d
            r6.<init>(r1)     // Catch: Throwable -> 0x006d
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch: Throwable -> 0x006d
            r7.<init>(r6)     // Catch: Throwable -> 0x006d
            r2.store(r7, r0)     // Catch: Throwable -> 0x0069
            r3.close()     // Catch: Exception -> 0x0063
        L_0x0063:
            r7.close()     // Catch: Exception -> 0x0081
            goto L_0x0081
        L_0x0067:
            r6 = move-exception
            goto L_0x0084
        L_0x0069:
            r6 = move-exception
            goto L_0x006f
        L_0x006b:
            r6 = move-exception
            goto L_0x0085
        L_0x006d:
            r6 = move-exception
            r7 = r0
        L_0x006f:
            r0 = r3
            goto L_0x0076
        L_0x0071:
            r6 = move-exception
            r3 = r0
            goto L_0x0085
        L_0x0074:
            r6 = move-exception
            r7 = r0
        L_0x0076:
            r6.printStackTrace()     // Catch: all -> 0x0082
            if (r0 == 0) goto L_0x007e
            r0.close()     // Catch: Exception -> 0x007e
        L_0x007e:
            if (r7 == 0) goto L_0x0081
            goto L_0x0063
        L_0x0081:
            return
        L_0x0082:
            r6 = move-exception
            r3 = r0
        L_0x0084:
            r0 = r7
        L_0x0085:
            if (r3 == 0) goto L_0x008a
            r3.close()     // Catch: Exception -> 0x008a
        L_0x008a:
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch: Exception -> 0x008f
        L_0x008f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.m16957c(android.content.Context, boolean):void");
    }

    public static boolean forceLoadX5FromTBSDemo(Context context) {
        int sharedTbsCoreVersion;
        if (context == null || TbsInstaller.m16742a().m16728a(context, (File[]) null) || (sharedTbsCoreVersion = getSharedTbsCoreVersion(context, TbsConfig.APP_DEMO)) <= 0) {
            return false;
        }
        writeProperties(context, Integer.toString(sharedTbsCoreVersion), TbsConfig.APP_DEMO, TbsInstaller.m16742a().m16678q(getPackageContext(context, TbsConfig.APP_DEMO, true)).getAbsolutePath(), "1");
        return true;
    }

    public static synchronized void writeCoreInfoForThirdPartyApp(Context context, int i, boolean z) {
        synchronized (TbsShareManager.class) {
            TbsLog.m16531i("TbsShareManager", "writeCoreInfoForThirdPartyApp coreVersion is " + i);
            if (i == 0) {
                m16946m(context);
                TbsDownloadConfig.getInstance(f12972a).setDownloadInterruptCode(-401);
                return;
            }
            int h = m16951h(context);
            TbsLog.m16531i("TbsShareManager", "writeCoreInfoForThirdPartyApp coreVersionFromConfig is " + h);
            if (h < 0) {
                TbsDownloadConfig.getInstance(f12972a).setDownloadInterruptCode(-402);
            } else if (i == h) {
                if (m16956d(context) == 0 && !z) {
                    m16963a(context, i);
                }
                m16957c(context, z);
                TbsDownloadConfig.getInstance(f12972a).setDownloadInterruptCode(-403);
            } else if (i < h) {
                m16946m(context);
                TbsDownloadConfig.getInstance(f12972a).setDownloadInterruptCode(-404);
            } else {
                int i2 = TbsInstaller.m16742a().m16688i(context);
                TbsLog.m16531i("TbsShareManager", "writeCoreInfoForThirdPartyApp coreVersionFromCoreShare is " + i2);
                if (i < i2) {
                    m16946m(context);
                    TbsDownloadConfig.getInstance(f12972a).setDownloadInterruptCode(-404);
                    return;
                }
                String[] d = m16955d(context, z);
                boolean z2 = true;
                if (f12974c != null) {
                    if (i == TbsInstaller.m16742a().m16723a(f12974c)) {
                        writeProperties(context, Integer.toString(i), "AppDefined", f12974c, Integer.toString(1));
                        File tbsShareFile = getTbsShareFile(context, "core_info");
                        if (!f12980i && tbsShareFile != null) {
                            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(f12972a);
                            tbsLinuxToolsJni.m17007a(tbsShareFile.getAbsolutePath(), "644");
                            tbsLinuxToolsJni.m17007a(TbsInstaller.m16742a().m16677r(context).getAbsolutePath(), "755");
                            f12980i = true;
                        }
                        return;
                    } else if (i > TbsInstaller.m16742a().m16723a(f12974c)) {
                        for (String str : d) {
                            if (i == getSharedTbsCoreVersion(context, str)) {
                                Context packageContext = getPackageContext(context, str, true);
                                String absolutePath = TbsInstaller.m16742a().m16678q(packageContext).getAbsolutePath();
                                AppUtil.m16501b(context);
                                if (TbsInstaller.m16742a().m16696f(packageContext)) {
                                    FileUtil.m16457a(new File(absolutePath), new File(f12974c), new FileFilter() { // from class: com.tencent.smtt.sdk.TbsShareManager.1
                                        @Override // java.io.FileFilter
                                        public boolean accept(File file) {
                                            return !file.getName().endsWith(".dex");
                                        }
                                    });
                                    writeProperties(context, Integer.toString(i), "AppDefined", f12974c, Integer.toString(1));
                                    File tbsShareFile2 = getTbsShareFile(context, "core_info");
                                    if (!f12980i && tbsShareFile2 != null) {
                                        TbsLinuxToolsJni tbsLinuxToolsJni2 = new TbsLinuxToolsJni(f12972a);
                                        tbsLinuxToolsJni2.m17007a(tbsShareFile2.getAbsolutePath(), "644");
                                        tbsLinuxToolsJni2.m17007a(TbsInstaller.m16742a().m16677r(context).getAbsolutePath(), "755");
                                        f12980i = true;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
                int length = d.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        z2 = false;
                        break;
                    }
                    String str2 = d[i3];
                    if (i == getSharedTbsCoreVersion(context, str2)) {
                        Context packageContext2 = getPackageContext(context, str2, true);
                        String absolutePath2 = TbsInstaller.m16742a().m16678q(packageContext2).getAbsolutePath();
                        int b = AppUtil.m16501b(context);
                        if (!TbsInstaller.m16742a().m16696f(packageContext2)) {
                            i3++;
                        } else {
                            if (!str2.equals(context.getApplicationContext().getPackageName())) {
                                TbsLog.m16531i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + i);
                                TbsCoreVerManager.m16764a(f12972a).m16762a("remove_old_core", 1);
                            }
                            writeProperties(context, Integer.toString(i), str2, absolutePath2, Integer.toString(b));
                            File tbsShareFile3 = getTbsShareFile(context, "core_info");
                            if (!f12980i && tbsShareFile3 != null) {
                                TbsLinuxToolsJni tbsLinuxToolsJni3 = new TbsLinuxToolsJni(f12972a);
                                tbsLinuxToolsJni3.m17007a(tbsShareFile3.getAbsolutePath(), "644");
                                tbsLinuxToolsJni3.m17007a(TbsInstaller.m16742a().m16677r(context).getAbsolutePath(), "755");
                                f12980i = true;
                            }
                        }
                    } else {
                        if (i == getCoreShareDecoupleCoreVersion(context, str2)) {
                            Context packageContext3 = getPackageContext(context, str2, true);
                            String absolutePath3 = TbsInstaller.m16742a().m16679p(packageContext3).getAbsolutePath();
                            int b2 = AppUtil.m16501b(context);
                            if (TbsInstaller.m16742a().m16696f(packageContext3)) {
                                if (!str2.equals(context.getApplicationContext().getPackageName())) {
                                    TbsLog.m16531i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + i);
                                    FileUtil.m16444b(TbsInstaller.m16742a().m16678q(context));
                                    TbsLog.m16531i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
                                }
                                writeProperties(context, Integer.toString(i), str2, absolutePath3, Integer.toString(b2));
                                File tbsShareFile4 = getTbsShareFile(context, "core_info");
                                if (!f12980i && tbsShareFile4 != null) {
                                    TbsLinuxToolsJni tbsLinuxToolsJni4 = new TbsLinuxToolsJni(f12972a);
                                    tbsLinuxToolsJni4.m17007a(tbsShareFile4.getAbsolutePath(), "644");
                                    tbsLinuxToolsJni4.m17007a(TbsInstaller.m16742a().m16677r(context).getAbsolutePath(), "755");
                                    f12980i = true;
                                }
                            }
                        } else {
                            continue;
                        }
                        i3++;
                    }
                }
                if (!z2 && !z) {
                    m16963a(context, i);
                }
            }
        }
    }

    /* renamed from: d */
    private static String[] m16955d(Context context, boolean z) {
        if (QbSdk.getOnlyDownload()) {
            return new String[]{context.getApplicationContext().getPackageName()};
        }
        return z ? new String[]{context.getApplicationContext().getPackageName()} : getCoreProviderAppList();
    }

    /* renamed from: a */
    private static void m16963a(Context context, int i) {
        if (!TbsPVConfig.getInstance(f12972a).isDisableHostBackupCore() && TbsInstaller.m16742a().m16675t(context)) {
            String[] strArr = {TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE, context.getPackageName()};
            TbsLog.m16531i("TbsShareManager", "find host backup core to unzip #1" + Log.getStackTraceString(new Throwable()));
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str = strArr[i2];
                if (i == getBackupCoreVersion(context, str)) {
                    if (TbsInstaller.m16742a().m16696f(getPackageContext(context, str, false))) {
                        File backupCoreFile = getBackupCoreFile(context, str);
                        if (ApkUtil.m16518a(context, backupCoreFile, 0L, i)) {
                            TbsLog.m16531i("TbsShareManager", "find host backup core to unzip normal coreVersion is " + i + " packageName is " + str);
                            TbsInstaller.m16742a().m16715b(context, backupCoreFile, i);
                            break;
                        }
                    } else {
                        continue;
                    }
                    i2++;
                } else {
                    if (i == getBackupDecoupleCoreVersion(context, str) && TbsInstaller.m16742a().m16696f(getPackageContext(context, str, false))) {
                        File backupDecoupleCoreFile = getBackupDecoupleCoreFile(context, str);
                        if (ApkUtil.m16518a(context, backupDecoupleCoreFile, 0L, i)) {
                            TbsLog.m16531i("TbsShareManager", "find host backup core to unzip decouple coreVersion is " + i + " packageName is " + str);
                            TbsInstaller.m16742a().m16715b(context, backupDecoupleCoreFile, i);
                            break;
                        }
                    }
                    i2++;
                }
            }
            TbsInstaller.m16742a().m16721b();
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:21:0x00c5
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public static void writeProperties(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeProperties(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static synchronized String m16953f(Context context) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        synchronized (TbsShareManager.class) {
            try {
                File tbsShareFile = getTbsShareFile(context, "core_info");
                if (tbsShareFile == null) {
                    return null;
                }
                bufferedInputStream = new BufferedInputStream(new FileInputStream(tbsShareFile));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream);
                    String property = properties.getProperty("core_packagename", "");
                    if (!"".equals(property)) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception unused) {
                        }
                        return property;
                    }
                    try {
                        bufferedInputStream.close();
                    } catch (Exception unused2) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception unused3) {
                        }
                    }
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public static String m16952g(Context context) {
        try {
            m16945n(context);
            if (f12975d != null && !TextUtils.isEmpty(f12975d)) {
                return f12975d + File.separator + "res.apk";
            }
            return null;
        } catch (Throwable th) {
            Log.e("", "getTbsResourcesPath exception: " + Log.getStackTraceString(th));
            return null;
        }
    }

    /* renamed from: h */
    static synchronized int m16951h(Context context) {
        Throwable th;
        synchronized (TbsShareManager.class) {
            TbsLog.m16531i("TbsShareManager", "readCoreVersionFromConfig #1");
            BufferedInputStream bufferedInputStream = null;
            try {
                File tbsShareFile = getTbsShareFile(context, "core_info");
                if (tbsShareFile == null) {
                    TbsLog.m16531i("TbsShareManager", "readCoreVersionFromConfig #2");
                    return 0;
                }
                bufferedInputStream = new BufferedInputStream(new FileInputStream(tbsShareFile));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream);
                    String property = properties.getProperty("core_version", "");
                    if (!"".equals(property)) {
                        TbsLog.m16531i("TbsShareManager", "readCoreVersionFromConfig #3");
                        int max = Math.max(Integer.parseInt(property), 0);
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return max;
                    }
                    TbsLog.m16531i("TbsShareManager", "readCoreVersionFromConfig #4");
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    TbsLog.m16531i("TbsShareManager", "readCoreVersionFromConfig #5");
                    return -2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public static boolean getCoreFormOwn() {
        return f12982k;
    }

    /* renamed from: n */
    private static void m16945n(Context context) {
        Throwable th;
        File tbsShareFile;
        if (!f12983l) {
            synchronized (TbsShareManager.class) {
                if (!f12983l) {
                    BufferedInputStream bufferedInputStream = null;
                    try {
                        tbsShareFile = getTbsShareFile(context, "core_info");
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (tbsShareFile != null) {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(tbsShareFile));
                        try {
                            Properties properties = new Properties();
                            properties.load(bufferedInputStream);
                            String property = properties.getProperty("core_version", "");
                            if (!"".equals(property)) {
                                f12976e = Math.max(Integer.parseInt(property), 0);
                                TbsLog.m16531i("TbsShareManager", "loadProperties -- mAvailableCoreVersion: " + f12976e + ExpandableTextView.f6958c + Log.getStackTraceString(new Throwable("#")));
                            }
                            String property2 = properties.getProperty("core_packagename", "");
                            if (!"".equals(property2)) {
                                f12977f = property2;
                            }
                            if (!(f12977f == null || f12972a == null)) {
                                if (f12977f.equals(f12972a.getPackageName())) {
                                    f12982k = true;
                                } else {
                                    f12982k = false;
                                }
                            }
                            String property3 = properties.getProperty("core_path", "");
                            if (!"".equals(property3)) {
                                f12975d = property3;
                            }
                            String property4 = properties.getProperty("app_version", "");
                            if (!"".equals(property4)) {
                                f12981j = property4;
                            }
                            f12978g = Boolean.parseBoolean(properties.getProperty("core_disabled", "false"));
                            f12983l = true;
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e) {
                                    e = e;
                                    e.printStackTrace();
                                }
                            }
                        }
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void forceToLoadX5ForThirdApp(Context context, boolean z) {
        File r;
        int a;
        try {
            if (!QbSdk.isNeedInitX5FirstTime() || !isThirdPartyApp(context) || QbSdk.getOnlyDownload() || (r = TbsInstaller.m16742a().m16677r(context)) == null) {
                return;
            }
            if (z && new File(r, "core_info").exists()) {
                return;
            }
            if (f12974c == null || (a = TbsInstaller.m16742a().m16723a(f12974c)) <= 0) {
                TbsLog.m16531i("TbsShareManager", "forceToLoadX5ForThirdApp #1");
                int h = m16951h(context);
                int i = TbsInstaller.m16742a().m16688i(context);
                TbsLog.m16531i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromConfig is " + h);
                TbsLog.m16531i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromCoreShare is " + i);
                String[] coreProviderAppList = getCoreProviderAppList();
                for (String str : coreProviderAppList) {
                    int coreShareDecoupleCoreVersion = getCoreShareDecoupleCoreVersion(context, str);
                    if (coreShareDecoupleCoreVersion >= h && coreShareDecoupleCoreVersion >= i && coreShareDecoupleCoreVersion > 0) {
                        f12975d = TbsInstaller.m16742a().m16709c(context, getPackageContext(context, str, true)).getAbsolutePath();
                        f12977f = str;
                        f12976e = coreShareDecoupleCoreVersion;
                        TbsLog.m16531i("TbsShareManager", "forceToLoadX5ForThirdApp #2 -- mAvailableCoreVersion: " + f12976e + ExpandableTextView.f6958c + Log.getStackTraceString(new Throwable("#")));
                        if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                            int b = AppUtil.m16501b(context);
                            TbsLog.m16531i("TbsShareManager", "forceToLoadX5ForThirdApp #2");
                            writeProperties(context, Integer.toString(f12976e), f12977f, f12975d, Integer.toString(b));
                            return;
                        }
                        f12976e = 0;
                        f12975d = null;
                        f12977f = null;
                    }
                }
                for (String str2 : coreProviderAppList) {
                    int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str2);
                    if (sharedTbsCoreVersion >= h && sharedTbsCoreVersion >= i && sharedTbsCoreVersion > 0) {
                        f12975d = TbsInstaller.m16742a().m16718b(context, getPackageContext(context, str2, true)).getAbsolutePath();
                        f12977f = str2;
                        f12976e = sharedTbsCoreVersion;
                        TbsLog.m16531i("TbsShareManager", "forceToLoadX5ForThirdApp #3 -- mAvailableCoreVersion: " + f12976e + ExpandableTextView.f6958c + Log.getStackTraceString(new Throwable("#")));
                        if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                            writeProperties(context, Integer.toString(f12976e), f12977f, f12975d, Integer.toString(AppUtil.m16501b(context)));
                            return;
                        }
                        f12976e = 0;
                        f12975d = null;
                        f12977f = null;
                    }
                }
                if (TbsPVConfig.getInstance(f12972a).isDisableHostBackupCore()) {
                    return;
                }
                if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                    for (String str3 : coreProviderAppList) {
                        int backupCoreVersion = getBackupCoreVersion(context, str3);
                        if (backupCoreVersion >= h && backupCoreVersion >= i && backupCoreVersion > 0) {
                            TbsLog.m16531i("TbsShareManager", "find host backup core to unzip forceload coreVersion is " + backupCoreVersion + " packageName is " + str3);
                            TbsInstaller.m16742a().m16733a(context, getBackupCoreFile(context, str3), backupCoreVersion);
                            TbsLog.m16531i("TbsShareManager", "find host backup core to unzip forceload after unzip ");
                            return;
                        }
                        int backupDecoupleCoreVersion = getBackupDecoupleCoreVersion(context, str3);
                        if (backupDecoupleCoreVersion >= h && backupDecoupleCoreVersion >= i && backupDecoupleCoreVersion > 0) {
                            TbsLog.m16531i("TbsShareManager", "find host backup core to unzip forceload decouple coreVersion is " + backupDecoupleCoreVersion + " packageName is " + str3);
                            TbsInstaller.m16742a().m16733a(context, getBackupCoreFile(context, str3), backupDecoupleCoreVersion);
                            TbsLog.m16531i("TbsShareManager", "find host backup decouple core to unzip forceload after unzip ");
                            return;
                        }
                    }
                    return;
                }
                TbsLog.m16531i("TbsShareManager", "in mainthread so do not find host backup core to install ");
                return;
            }
            f12975d = f12974c;
            f12977f = "AppDefined";
            f12976e = a;
            TbsLog.m16531i("TbsShareManager", "forceToLoadX5ForThirdApp #1 -- mAvailableCoreVersion: " + f12976e + ExpandableTextView.f6958c + Log.getStackTraceString(new Throwable("#")));
            writeProperties(context, Integer.toString(f12976e), f12977f, f12975d, Integer.toString(1));
        } catch (Exception unused) {
        }
    }

    public static File getTbsShareFile(Context context, String str) {
        File r = TbsInstaller.m16742a().m16677r(context);
        if (r == null) {
            return null;
        }
        File file = new File(r, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public static boolean m16950i(Context context) {
        try {
            if (f12976e == 0) {
                findCoreForThirdPartyApp(context);
            }
            if (f12976e == 0) {
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE, null, new Object[0]);
                return false;
            }
            if (f12974c == null) {
                if (f12976e != 0 && getSharedTbsCoreVersion(context, f12977f) == f12976e) {
                    return true;
                }
            } else if (f12976e != 0 && TbsInstaller.m16742a().m16723a(f12974c) == f12976e) {
                return true;
            }
            if (m16947l(context)) {
                return true;
            }
            TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
            instance.m17035a(context, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, new Throwable("mAvailableCoreVersion=" + f12976e + "; mSrcPackageName=" + f12977f + "; getSharedTbsCoreVersion(ctx, mSrcPackageName) is " + getSharedTbsCoreVersion(context, f12977f) + "; getHostCoreVersions is " + getHostCoreVersions(context)));
            f12975d = null;
            f12976e = 0;
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, null, new Object[0]);
            QbSdk.m17056a(context, "TbsShareManager::isShareTbsCoreAvailableInner forceSysWebViewInner!");
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE, null, new Object[0]);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public static boolean m16949j(Context context) {
        return m16959b(context, true);
    }

    /* renamed from: b */
    static boolean m16959b(Context context, boolean z) {
        if (m16950i(context)) {
            return true;
        }
        if (!z) {
            return false;
        }
        QbSdk.m17056a(context, "TbsShareManager::isShareTbsCoreAvailable forceSysWebViewInner!");
        return false;
    }

    public static Context getPackageContext(Context context, String str, boolean z) {
        if (z) {
            try {
                if (!context.getPackageName().equals(str) && TbsPVConfig.getInstance(context).isEnableNoCoreGray()) {
                    TbsLog.m16531i("TbsShareManager", "gray no core app,skip get context");
                    return null;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return context.createPackageContext(str, 2);
    }
}
