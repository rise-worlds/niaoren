package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.AppUtil;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.l */
/* loaded from: classes2.dex */
public class TbsInstaller {

    /* renamed from: d */
    private static TbsInstaller f13203d;

    /* renamed from: f */
    private FileLock f13212f;

    /* renamed from: g */
    private FileOutputStream f13213g;

    /* renamed from: i */
    private static final ReentrantLock f13204i = new ReentrantLock();

    /* renamed from: j */
    private static final Lock f13205j = new ReentrantLock();

    /* renamed from: l */
    private static FileLock f13206l = null;

    /* renamed from: a */
    public static ThreadLocal<Integer> f13200a = new ThreadLocal<Integer>() { // from class: com.tencent.smtt.sdk.l.1
        /* renamed from: a */
        public Integer initialValue() {
            return 0;
        }
    };

    /* renamed from: m */
    private static Handler f13207m = null;

    /* renamed from: n */
    private static final Long[][] f13208n = {new Long[]{44006L, 39094008L}, new Long[]{44005L, 39094008L}, new Long[]{43910L, 38917816L}, new Long[]{44027L, 39094008L}, new Long[]{44028L, 39094008L}, new Long[]{44029L, 39094008L}, new Long[]{44030L, 39094008L}, new Long[]{44032L, 39094008L}, new Long[]{44033L, 39094008L}, new Long[]{44034L, 39094008L}, new Long[]{43909L, 38917816L}};

    /* renamed from: b */
    static boolean f13201b = false;

    /* renamed from: c */
    static final FileFilter f13202c = new FileFilter() { // from class: com.tencent.smtt.sdk.l.2
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (name == null || name.endsWith(".jar_is_first_load_dex_flag_file")) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 21 && name.endsWith(".dex")) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 26 || !name.endsWith(".prof")) {
                return Build.VERSION.SDK_INT < 26 || !name.equals("oat");
            }
            return false;
        }
    };

    /* renamed from: o */
    private static int f13209o = 0;

    /* renamed from: p */
    private static boolean f13210p = false;

    /* renamed from: e */
    private int f13211e = 0;

    /* renamed from: h */
    private boolean f13214h = false;

    /* renamed from: k */
    private boolean f13215k = false;

    /* renamed from: a */
    public boolean m16728a(Context context, File[] fileArr) {
        return false;
    }

    private TbsInstaller() {
        if (f13207m == null) {
            f13207m = new Handler(TbsHandlerThread.m16748a().getLooper()) { // from class: com.tencent.smtt.sdk.l.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    QbSdk.setTBSInstallingStatus(true);
                    switch (message.what) {
                        case 1:
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE");
                            Object[] objArr = (Object[]) message.obj;
                            TbsInstaller.this.m16713b((Context) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue());
                            return;
                        case 2:
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--handleMessage--MSG_COPY_TBS_CORE");
                            Object[] objArr2 = (Object[]) message.obj;
                            TbsInstaller.this.m16736a((Context) objArr2[0], (Context) objArr2[1], ((Integer) objArr2[2]).intValue());
                            return;
                        case 3:
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE_EX");
                            Object[] objArr3 = (Object[]) message.obj;
                            TbsInstaller.this.m16717b((Context) objArr3[0], (Bundle) objArr3[1]);
                            return;
                        case 4:
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--handleMessage--MSG_UNZIP_TBS_CORE");
                            Object[] objArr4 = (Object[]) message.obj;
                            TbsInstaller.this.m16715b((Context) objArr4[0], (File) objArr4[1], ((Integer) objArr4[2]).intValue());
                            QbSdk.setTBSInstallingStatus(false);
                            super.handleMessage(message);
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized TbsInstaller m16742a() {
        TbsInstaller lVar;
        synchronized (TbsInstaller.class) {
            if (f13203d == null) {
                synchronized (TbsInstaller.class) {
                    if (f13203d == null) {
                        f13203d = new TbsInstaller();
                    }
                }
            }
            lVar = f13203d;
        }
        return lVar;
    }

    /* renamed from: a */
    public int m16722a(boolean z, Context context) {
        if (z || f13200a.get().intValue() <= 0) {
            f13200a.set(Integer.valueOf(m16688i(context)));
        }
        return f13200a.get().intValue();
    }

    /* JADX WARN: Finally extract failed */
    /* renamed from: c */
    private synchronized boolean m16705c(Context context, boolean z) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch");
        boolean z2 = false;
        if (!m16675t(context)) {
            return false;
        }
        boolean tryLock = f13204i.tryLock();
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch Locked =" + tryLock);
        if (tryLock) {
            try {
                int b = TbsCoreVerManager.m16764a(context).m16757b("tpatch_status");
                int a = m16722a(false, context);
                TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch copyStatus =" + b);
                TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer =" + a);
                if (b == 1) {
                    if (a == 0) {
                        TbsLog.m16530i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer = 0", true);
                        m16670y(context);
                        z2 = true;
                    } else if (z) {
                        TbsLog.m16530i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer != 0", true);
                        m16670y(context);
                        z2 = true;
                    }
                }
                f13204i.unlock();
            } catch (Throwable th) {
                f13204i.unlock();
                throw th;
            }
        }
        m16721b();
        return z2;
    }

    /* renamed from: d */
    private synchronized boolean m16701d(Context context, boolean z) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy");
        boolean z2 = false;
        try {
        } catch (Throwable th) {
            TbsLogReport.getInstance(context).setInstallErrorCode(215, th.toString());
            QbSdk.m17056a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
        }
        if (!m16675t(context)) {
            return false;
        }
        boolean tryLock = f13204i.tryLock();
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy Locked =" + tryLock);
        if (tryLock) {
            int b = TbsCoreVerManager.m16764a(context).m16757b("copy_status");
            int a = m16722a(false, context);
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy copyStatus =" + b);
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer =" + a);
            if (b == 1) {
                if (a == 0) {
                    TbsLog.m16530i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer = 0", true);
                    m16669z(context);
                    z2 = true;
                } else if (z) {
                    TbsLog.m16530i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer != 0", true);
                    m16669z(context);
                    z2 = true;
                }
            }
            f13204i.unlock();
        }
        m16721b();
        return z2;
    }

    /* JADX WARN: Finally extract failed */
    /* renamed from: e */
    private synchronized boolean m16697e(Context context, boolean z) {
        if (context != null) {
            if (TbsConfig.APP_WX.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
                TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.INSTALL_FROM_UNZIP, ExpandableTextView.f6958c);
            }
        }
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip canRenameTmpDir =" + z);
        TbsLog.m16531i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #1 ");
        boolean z2 = false;
        try {
        } catch (Exception e) {
            QbSdk.m17056a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + e);
            e.printStackTrace();
        }
        if (!m16675t(context)) {
            return false;
        }
        TbsLog.m16531i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #2 ");
        boolean tryLock = f13204i.tryLock();
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip locked=" + tryLock);
        if (tryLock) {
            try {
                int c = TbsCoreVerManager.m16764a(context).m16756c();
                TbsLog.m16531i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c);
                int a = m16722a(false, context);
                if (c == 2) {
                    TbsLog.m16531i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #4 ");
                    if (a == 0) {
                        TbsLog.m16530i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                        m16671x(context);
                        z2 = true;
                    } else if (z) {
                        TbsLog.m16530i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                        m16671x(context);
                        z2 = true;
                    }
                }
                f13204i.unlock();
            } catch (Throwable th) {
                f13204i.unlock();
                throw th;
            }
        }
        m16721b();
        return z2;
    }

    /* renamed from: f */
    private synchronized boolean m16694f(Context context, boolean z) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16729a(Context context, boolean z) {
        int c;
        int b;
        int c2;
        int b2;
        boolean z2 = true;
        if (z) {
            this.f13215k = true;
        }
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (m16675t(context)) {
            String d = null;
            if (f13204i.tryLock()) {
                try {
                    c = TbsCoreVerManager.m16764a(context).m16756c();
                    b = TbsCoreVerManager.m16764a(context).m16760b();
                    d = TbsCoreVerManager.m16764a(context).m16750d("install_apk_path");
                    c2 = TbsCoreVerManager.m16764a(context).m16753c("copy_core_ver");
                    b2 = TbsCoreVerManager.m16764a(context).m16757b("copy_status");
                } finally {
                    f13204i.unlock();
                }
            } else {
                c = -1;
                b = 0;
                c2 = 0;
                b2 = -1;
            }
            m16721b();
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + c);
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + b);
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + d);
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + c2);
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + b2);
            if (TbsShareManager.isThirdPartyApp(context)) {
                m16710c(context, TbsShareManager.m16961a(context, false));
                return;
            }
            int i = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
            if (!(i == 1 || i == 2 || i == 4)) {
                z2 = false;
            }
            if (!(z2 || i == 0 || i == 5)) {
                Bundle bundle = new Bundle();
                bundle.putInt("operation", 10001);
                m16735a(context, bundle);
            }
            if (c > -1 && c < 2) {
                m16730a(context, d, b);
            }
            if (b2 == 0) {
                m16719b(context, c2);
            }
        }
    }

    /* renamed from: a */
    public static void m16740a(Context context) {
        if (m16673v(context)) {
            return;
        }
        if (m16731a(context, "core_unzip_tmp")) {
            TbsCoreLoadStat.getInstance().m17035a(context, 417, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
            TbsLog.m16533e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME");
        } else if (m16731a(context, "core_share_backup_tmp")) {
            TbsCoreLoadStat.getInstance().m17035a(context, 417, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
            TbsLog.m16533e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME");
        } else if (m16731a(context, "core_copy_tmp")) {
            TbsCoreLoadStat.getInstance().m17035a(context, 417, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
            TbsLog.m16533e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m16712b(Context context, boolean z) {
        if (!QbSdk.f12790b) {
            if (Build.VERSION.SDK_INT < 8) {
                TbsLog.m16532e("TbsInstaller", "android version < 2.1 no need install X5 core", true);
                return;
            }
            TbsLog.m16531i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #1 ");
            if (TbsShareManager.isThirdPartyApp(context) && TbsCoreVerManager.m16764a(context).m16757b("remove_old_core") == 1 && z) {
                try {
                    FileUtil.m16444b(m16742a().m16678q(context));
                    TbsLog.m16531i("TbsInstaller", "thirdAPP success--> delete old core_share Directory");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                TbsCoreVerManager.m16764a(context).m16762a("remove_old_core", 0);
            }
            if (m16673v(context)) {
                TbsLog.m16531i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #2 ");
                if (m16731a(context, "core_unzip_tmp") && m16697e(context, z)) {
                    TbsLog.m16530i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip!!", true);
                } else if (m16731a(context, "core_share_backup_tmp") && m16694f(context, z)) {
                    TbsLog.m16530i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromBackup!!", true);
                } else if (m16731a(context, "core_copy_tmp") && m16701d(context, z)) {
                    TbsLog.m16530i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromCopy!!", true);
                } else if (m16731a(context, "tpatch_tmp") && m16705c(context, z)) {
                    TbsLog.m16530i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromTpatch!!", true);
                } else {
                    TbsLog.m16530i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, error !!", true);
                }
            }
        }
    }

    /* renamed from: a */
    static boolean m16731a(Context context, String str) {
        File file = new File(context.getDir("tbs", 0), str);
        if (!file.exists()) {
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #1");
            return false;
        } else if (!new File(file, "tbs.conf").exists()) {
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #2");
            return false;
        } else {
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #3");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16730a(Context context, String str, int i) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installTbsCore tbsApkPath=" + str);
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installTbsCore tbsCoreTargetVer=" + i);
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installTbsCore currentProcessId=" + Process.myPid());
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installTbsCore currentThreadName=" + Thread.currentThread().getName());
        Object[] objArr = {context, str, Integer.valueOf(i)};
        Message message = new Message();
        message.what = 1;
        message.obj = objArr;
        f13207m.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:228:0x04df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.TargetApi(11)
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m16713b(android.content.Context r17, java.lang.String r18, int r19) {
        /*
            Method dump skipped, instructions count: 1753
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsInstaller.m16713b(android.content.Context, java.lang.String, int):void");
    }

    /* renamed from: u */
    private int m16674u(Context context) {
        boolean z = true;
        if (TbsCoreVerManager.m16764a(context).m16752d() != 1) {
            z = false;
        }
        boolean a = TbsDownloader.m17031a(context);
        if (z) {
            return a ? TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS : TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS;
        }
        if (a) {
            return TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS;
        }
        return 200;
    }

    /* renamed from: b */
    public void m16720b(Context context) {
        m16691g(context, true);
        TbsCoreVerManager.m16764a(context).m16754c(m16690h(context), 2);
    }

    /* renamed from: a */
    public void m16739a(Context context, int i) {
        m16691g(context, true);
        TbsCoreVerManager.m16764a(context).m16754c(i, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0086 A[Catch: IOException -> 0x006f, TRY_ENTER, TRY_LEAVE, TryCatch #1 {IOException -> 0x006f, blocks: (B:15:0x006b, B:28:0x0086), top: B:38:0x0019 }] */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m16711c(android.content.Context r10) {
        /*
            r9 = this;
            java.io.File r10 = r9.m16678q(r10)
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "tbs.conf"
            r0.<init>(r10, r1)
            boolean r10 = r0.exists()
            r1 = 0
            if (r10 != 0) goto L_0x0013
            return r1
        L_0x0013:
            java.util.Properties r10 = new java.util.Properties
            r10.<init>()
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: Throwable -> 0x007f
            r3.<init>(r0)     // Catch: Throwable -> 0x007f
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x007f
            r4.<init>(r3)     // Catch: Throwable -> 0x007f
            r10.load(r4)     // Catch: Throwable -> 0x0079
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r3 = "false"
            java.lang.String r10 = r10.getProperty(r2, r3)     // Catch: Throwable -> 0x0079
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch: Throwable -> 0x0079
            boolean r10 = r10.booleanValue()     // Catch: Throwable -> 0x0079
            r2 = 1
            if (r10 == 0) goto L_0x004a
            long r5 = java.lang.System.currentTimeMillis()     // Catch: Throwable -> 0x0074
            long r7 = r0.lastModified()     // Catch: Throwable -> 0x0074
            long r5 = r5 - r7
            r7 = 259200000(0xf731400, double:1.280618154E-315)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x004a
            r1 = 1
        L_0x004a:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0074
            r3.<init>()     // Catch: Throwable -> 0x0074
            java.lang.String r5 = "TBS_LOCAL_INSTALLATION is:"
            r3.append(r5)     // Catch: Throwable -> 0x0074
            r3.append(r10)     // Catch: Throwable -> 0x0074
            java.lang.String r5 = " expired="
            r3.append(r5)     // Catch: Throwable -> 0x0074
            r3.append(r1)     // Catch: Throwable -> 0x0074
            java.lang.String r3 = r3.toString()     // Catch: Throwable -> 0x0074
            com.tencent.smtt.utils.TbsLog.m16531i(r0, r3)     // Catch: Throwable -> 0x0074
            r0 = r1 ^ 1
            r10 = r10 & r0
            r4.close()     // Catch: IOException -> 0x006f
            goto L_0x0089
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0089
        L_0x0074:
            r0 = move-exception
            r2 = r4
            goto L_0x0081
        L_0x0077:
            r10 = move-exception
            goto L_0x008a
        L_0x0079:
            r0 = move-exception
            r2 = r4
            goto L_0x0080
        L_0x007c:
            r10 = move-exception
            r4 = r2
            goto L_0x008a
        L_0x007f:
            r0 = move-exception
        L_0x0080:
            r10 = 0
        L_0x0081:
            r0.printStackTrace()     // Catch: all -> 0x007c
            if (r2 == 0) goto L_0x0089
            r2.close()     // Catch: IOException -> 0x006f
        L_0x0089:
            return r10
        L_0x008a:
            if (r4 == 0) goto L_0x0094
            r4.close()     // Catch: IOException -> 0x0090
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0094:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsInstaller.m16711c(android.content.Context):boolean");
    }

    /* renamed from: v */
    private static boolean m16673v(Context context) {
        if (context == null) {
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #1");
            return true;
        }
        try {
            if (new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf").exists()) {
                TbsLog.m16531i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #2");
                return true;
            }
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #3");
            return false;
        } catch (Exception unused) {
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #4");
            return true;
        }
    }

    /* renamed from: g */
    private void m16691g(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead context is null");
            return;
        }
        try {
            File file = new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf");
            if (!z) {
                FileUtil.m16444b(file);
            } else if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode(TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead Exception message is " + e.getMessage() + " Exception cause is " + e.getCause());
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:9:0x0039
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: d */
    public void m16704d(android.content.Context r6) {
        /*
            r5 = this;
            java.io.File r6 = r5.m16678q(r6)     // Catch: Throwable | IOException -> 0x005c
            java.io.File r0 = new java.io.File     // Catch: Throwable | IOException -> 0x005c
            java.lang.String r1 = "tbs.conf"
            r0.<init>(r6, r1)     // Catch: Throwable | IOException -> 0x005c
            java.util.Properties r6 = new java.util.Properties     // Catch: Throwable | IOException -> 0x005c
            r6.<init>()     // Catch: Throwable | IOException -> 0x005c
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: Throwable -> 0x0051
            r2.<init>(r0)     // Catch: Throwable -> 0x0051
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x0051
            r3.<init>(r2)     // Catch: Throwable -> 0x0051
            r6.load(r3)     // Catch: Throwable -> 0x0040
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: Throwable -> 0x0040
            r2.<init>(r0)     // Catch: Throwable -> 0x0040
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch: Throwable -> 0x0040
            r0.<init>(r2)     // Catch: Throwable -> 0x0040
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r4 = "false"
            r6.setProperty(r2, r4)     // Catch: Throwable -> 0x003c
            r6.store(r0, r1)     // Catch: Throwable -> 0x003c
            r0.close()     // Catch: IOException -> 0x0035, Throwable | IOException -> 0x005c
        L_0x0035:
            r3.close()     // Catch: Throwable | IOException -> 0x005c
            goto L_0x005c
        L_0x0039:
            r6 = move-exception
            r1 = r0
            goto L_0x0044
        L_0x003c:
            r1 = r0
            goto L_0x0052
        L_0x003e:
            r6 = move-exception
            goto L_0x0044
        L_0x0040:
            goto L_0x0052
        L_0x0042:
            r6 = move-exception
            r3 = r1
        L_0x0044:
            if (r1 == 0) goto L_0x004b
            r1.close()     // Catch: IOException -> 0x004a
            goto L_0x004b
        L_0x004a:
        L_0x004b:
            if (r3 == 0) goto L_0x0050
            r3.close()     // Catch: IOException -> 0x0050
        L_0x0050:
            throw r6     // Catch: Throwable | IOException -> 0x005c
        L_0x0051:
            r3 = r1
        L_0x0052:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch: IOException -> 0x0058
            goto L_0x0059
        L_0x0058:
        L_0x0059:
            if (r3 == 0) goto L_0x005c
            goto L_0x0035
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsInstaller.m16704d(android.content.Context):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:12:0x0079
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private void m16741a(int r5, java.lang.String r6, android.content.Context r7) {
        /*
            r4 = this;
            java.io.File r5 = new java.io.File
            r5.<init>(r6)
            r5.delete()
            java.lang.String r5 = "TbsInstaller"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Local tbs apk("
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = ") is deleted!"
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r0 = 1
            com.tencent.smtt.utils.TbsLog.m16530i(r5, r6, r0)
            java.lang.String r5 = "tbs"
            r6 = 0
            java.io.File r5 = r7.getDir(r5, r6)
            java.io.File r6 = new java.io.File
            java.lang.String r7 = "core_unzip_tmp"
            r6.<init>(r5, r7)
            boolean r5 = r6.canRead()
            if (r5 == 0) goto L_0x00b4
            java.io.File r5 = new java.io.File
            java.lang.String r7 = "tbs.conf"
            r5.<init>(r6, r7)
            java.util.Properties r6 = new java.util.Properties
            r6.<init>()
            r7 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: Throwable -> 0x0084
            r1.<init>(r5)     // Catch: Throwable -> 0x0084
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x0084
            r2.<init>(r1)     // Catch: Throwable -> 0x0084
            r6.load(r2)     // Catch: Throwable -> 0x007f
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: Throwable -> 0x007f
            r1.<init>(r5)     // Catch: Throwable -> 0x007f
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch: Throwable -> 0x007f
            r5.<init>(r1)     // Catch: Throwable -> 0x007f
            java.lang.String r1 = "tbs_local_installation"
            java.lang.String r3 = "true"
            r6.setProperty(r1, r3)     // Catch: Throwable -> 0x007c
            r6.store(r5, r7)     // Catch: Throwable -> 0x007c
            java.lang.String r6 = "TbsInstaller"
            java.lang.String r7 = "TBS_LOCAL_INSTALLATION is set!"
            com.tencent.smtt.utils.TbsLog.m16530i(r6, r7, r0)     // Catch: Throwable -> 0x007c
            r5.close()     // Catch: IOException -> 0x0071
            goto L_0x0075
        L_0x0071:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0075:
            r2.close()     // Catch: IOException -> 0x0099
            goto L_0x00b4
        L_0x0079:
            r6 = move-exception
            r7 = r5
            goto L_0x009f
        L_0x007c:
            r6 = move-exception
            r7 = r5
            goto L_0x0086
        L_0x007f:
            r6 = move-exception
            goto L_0x0086
        L_0x0081:
            r6 = move-exception
            r2 = r7
            goto L_0x009f
        L_0x0084:
            r6 = move-exception
            r2 = r7
        L_0x0086:
            r6.printStackTrace()     // Catch: all -> 0x009e
            if (r7 == 0) goto L_0x0093
            r7.close()     // Catch: IOException -> 0x008f
            goto L_0x0093
        L_0x008f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0093:
            if (r2 == 0) goto L_0x00b4
            r2.close()     // Catch: IOException -> 0x0099
            goto L_0x00b4
        L_0x0099:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x00b4
        L_0x009e:
            r6 = move-exception
        L_0x009f:
            if (r7 == 0) goto L_0x00a9
            r7.close()     // Catch: IOException -> 0x00a5
            goto L_0x00a9
        L_0x00a5:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00a9:
            if (r2 == 0) goto L_0x00b3
            r2.close()     // Catch: IOException -> 0x00af
            goto L_0x00b3
        L_0x00af:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00b3:
            throw r6
        L_0x00b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsInstaller.m16741a(int, java.lang.String, android.content.Context):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m16719b(Context context, int i) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i);
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        Context d = m16703d(context, i);
        if (d != null) {
            Object[] objArr = {d, context, Integer.valueOf(i)};
            Message message = new Message();
            message.what = 2;
            message.obj = objArr;
            f13207m.sendMessage(message);
            return true;
        }
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
        return false;
    }

    /* renamed from: a */
    void m16735a(Context context, Bundle bundle) {
        if (bundle != null && context != null) {
            Object[] objArr = {context, bundle};
            Message message = new Message();
            message.what = 3;
            message.obj = objArr;
            f13207m.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16733a(Context context, File file, int i) {
        TbsLog.m16531i("TbsInstaller", "unzipTbsCoreToThirdAppTmp,ctx=" + context + "File=" + file + "coreVersion=" + i);
        if (file != null && context != null) {
            Object[] objArr = {context, file, Integer.valueOf(i)};
            Message message = new Message();
            message.what = 4;
            message.obj = objArr;
            f13207m.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:136:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01eb A[Catch: all -> 0x03d3, Exception -> 0x03d7, TryCatch #7 {Exception -> 0x03d7, all -> 0x03d3, blocks: (B:20:0x00d1, B:22:0x00d6, B:45:0x01be, B:47:0x01c4, B:59:0x01df, B:61:0x01eb, B:63:0x020a, B:64:0x0212, B:66:0x0218, B:67:0x0220, B:69:0x0226, B:70:0x022e, B:77:0x0268, B:79:0x0278, B:81:0x0285, B:88:0x02ba, B:110:0x03b7), top: B:161:0x00d1 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0268 A[Catch: all -> 0x03d3, Exception -> 0x03d7, TRY_ENTER, TryCatch #7 {Exception -> 0x03d7, all -> 0x03d3, blocks: (B:20:0x00d1, B:22:0x00d6, B:45:0x01be, B:47:0x01c4, B:59:0x01df, B:61:0x01eb, B:63:0x020a, B:64:0x0212, B:66:0x0218, B:67:0x0220, B:69:0x0226, B:70:0x022e, B:77:0x0268, B:79:0x0278, B:81:0x0285, B:88:0x02ba, B:110:0x03b7), top: B:161:0x00d1 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02fb  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m16717b(android.content.Context r17, android.os.Bundle r18) {
        /*
            Method dump skipped, instructions count: 1351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsInstaller.m16717b(android.content.Context, android.os.Bundle):void");
    }

    /* renamed from: h */
    private void m16689h(Context context, int i) {
        TbsLog.m16531i("TbsInstaller", "proceedTpatchStatus,result=" + i);
        switch (i) {
            case 0:
                if (!TbsDownloader.m17031a(context)) {
                    m16691g(context, true);
                    TbsCoreVerManager.m16764a(context).m16758b(TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0), 1);
                    break;
                } else {
                    m16687i(context, 6);
                    break;
                }
        }
        QbSdk.setTBSInstallingStatus(false);
    }

    /* renamed from: c */
    private int m16708c(Context context, Bundle bundle) {
        try {
            Bundle a = QbSdk.m17057a(context, bundle);
            TbsLog.m16531i("TbsInstaller", "tpatch finished,ret is" + a);
            int i = a.getInt("patch_result");
            if (i == 0) {
                String string = bundle.getString("new_apk_location");
                int i2 = bundle.getInt("new_core_ver");
                int a2 = m16725a(new File(string));
                if (i2 != a2) {
                    TbsLog.m16531i("TbsInstaller", "version not equals!!!" + i2 + "patchVersion:" + a2);
                    TbsLogReport instance = TbsLogReport.getInstance(context);
                    instance.setInstallErrorCode(TbsListener.ErrorCode.TPATCH_VERSION_FAILED, "version=" + i2 + ",patchVersion=" + a2);
                    return 1;
                }
                File file = new File(bundle.getString("backup_apk"));
                String a3 = AppUtil.m16508a(context, true, file);
                if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(a3)) {
                    TbsLog.m16531i("TbsInstaller", "tpatch sig not equals!!!" + file + "signature:" + a3);
                    TbsLogReport instance2 = TbsLogReport.getInstance(context);
                    instance2.setInstallErrorCode(TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID, "version=" + i2 + ",patchVersion=" + a2);
                    FileUtil.m16444b(file);
                    return 0;
                }
                if (TbsDownloader.m17031a(context)) {
                    TbsLog.m16531i("TbsInstaller", "Tpatch decouple success!");
                    TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS, "");
                } else {
                    TbsLog.m16531i("TbsInstaller", "Tpatch success!");
                    TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS, "");
                }
                return 0;
            }
            String string2 = bundle.getString("new_apk_location");
            if (!TextUtils.isEmpty(string2)) {
                FileUtil.m16444b(new File(string2));
            }
            TbsLogReport instance3 = TbsLogReport.getInstance(context);
            instance3.setInstallErrorCode(i, "tpatch fail,patch error_code=" + i);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport instance4 = TbsLogReport.getInstance(context);
            instance4.setInstallErrorCode(TbsListener.ErrorCode.DECOUPLE_TPATCH_FAIL, "patch exception" + Log.getStackTraceString(e));
            return 1;
        }
    }

    /* renamed from: c */
    void m16710c(Context context, int i) {
        int i2;
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i > 0 && (i2 = m16688i(context)) != i) {
            Context e = TbsShareManager.m16954e(context);
            if (e != null || TbsShareManager.getHostCorePathAppDefined() != null) {
                TbsLog.m16531i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                m16737a(context, e);
            } else if (i2 <= 0) {
                TbsLog.m16531i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                QbSdk.m17056a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02cf A[Catch: Exception -> 0x04c5, all -> 0x04df, TryCatch #10 {Exception -> 0x04c5, blocks: (B:69:0x0203, B:71:0x022b, B:72:0x022e, B:74:0x024c, B:76:0x0255, B:89:0x02a6, B:92:0x02ac, B:102:0x02c3, B:105:0x02c9, B:108:0x02cf, B:109:0x02dd, B:111:0x02e0, B:113:0x02ee, B:115:0x02fa, B:117:0x0306, B:119:0x030c, B:122:0x0319, B:124:0x032f, B:126:0x0335, B:127:0x0355, B:128:0x0386, B:131:0x038d, B:134:0x03a7, B:135:0x03c5, B:137:0x03d5, B:139:0x03db, B:143:0x03e8, B:144:0x03ee, B:146:0x03f9, B:147:0x0403, B:148:0x040c, B:150:0x0431, B:151:0x043a, B:155:0x045a, B:156:0x0474, B:160:0x047d, B:163:0x0483, B:164:0x0486, B:165:0x0487, B:167:0x04b2, B:171:0x04ca), top: B:201:0x01ff }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03a5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x03d5 A[Catch: Exception -> 0x04c5, all -> 0x04df, TryCatch #10 {Exception -> 0x04c5, blocks: (B:69:0x0203, B:71:0x022b, B:72:0x022e, B:74:0x024c, B:76:0x0255, B:89:0x02a6, B:92:0x02ac, B:102:0x02c3, B:105:0x02c9, B:108:0x02cf, B:109:0x02dd, B:111:0x02e0, B:113:0x02ee, B:115:0x02fa, B:117:0x0306, B:119:0x030c, B:122:0x0319, B:124:0x032f, B:126:0x0335, B:127:0x0355, B:128:0x0386, B:131:0x038d, B:134:0x03a7, B:135:0x03c5, B:137:0x03d5, B:139:0x03db, B:143:0x03e8, B:144:0x03ee, B:146:0x03f9, B:147:0x0403, B:148:0x040c, B:150:0x0431, B:151:0x043a, B:155:0x045a, B:156:0x0474, B:160:0x047d, B:163:0x0483, B:164:0x0486, B:165:0x0487, B:167:0x04b2, B:171:0x04ca), top: B:201:0x01ff }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x03f9 A[Catch: Exception -> 0x04c5, all -> 0x04df, TryCatch #10 {Exception -> 0x04c5, blocks: (B:69:0x0203, B:71:0x022b, B:72:0x022e, B:74:0x024c, B:76:0x0255, B:89:0x02a6, B:92:0x02ac, B:102:0x02c3, B:105:0x02c9, B:108:0x02cf, B:109:0x02dd, B:111:0x02e0, B:113:0x02ee, B:115:0x02fa, B:117:0x0306, B:119:0x030c, B:122:0x0319, B:124:0x032f, B:126:0x0335, B:127:0x0355, B:128:0x0386, B:131:0x038d, B:134:0x03a7, B:135:0x03c5, B:137:0x03d5, B:139:0x03db, B:143:0x03e8, B:144:0x03ee, B:146:0x03f9, B:147:0x0403, B:148:0x040c, B:150:0x0431, B:151:0x043a, B:155:0x045a, B:156:0x0474, B:160:0x047d, B:163:0x0483, B:164:0x0486, B:165:0x0487, B:167:0x04b2, B:171:0x04ca), top: B:201:0x01ff }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0403 A[Catch: Exception -> 0x04c5, all -> 0x04df, TryCatch #10 {Exception -> 0x04c5, blocks: (B:69:0x0203, B:71:0x022b, B:72:0x022e, B:74:0x024c, B:76:0x0255, B:89:0x02a6, B:92:0x02ac, B:102:0x02c3, B:105:0x02c9, B:108:0x02cf, B:109:0x02dd, B:111:0x02e0, B:113:0x02ee, B:115:0x02fa, B:117:0x0306, B:119:0x030c, B:122:0x0319, B:124:0x032f, B:126:0x0335, B:127:0x0355, B:128:0x0386, B:131:0x038d, B:134:0x03a7, B:135:0x03c5, B:137:0x03d5, B:139:0x03db, B:143:0x03e8, B:144:0x03ee, B:146:0x03f9, B:147:0x0403, B:148:0x040c, B:150:0x0431, B:151:0x043a, B:155:0x045a, B:156:0x0474, B:160:0x047d, B:163:0x0483, B:164:0x0486, B:165:0x0487, B:167:0x04b2, B:171:0x04ca), top: B:201:0x01ff }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0431 A[Catch: Exception -> 0x04c5, all -> 0x04df, TryCatch #10 {Exception -> 0x04c5, blocks: (B:69:0x0203, B:71:0x022b, B:72:0x022e, B:74:0x024c, B:76:0x0255, B:89:0x02a6, B:92:0x02ac, B:102:0x02c3, B:105:0x02c9, B:108:0x02cf, B:109:0x02dd, B:111:0x02e0, B:113:0x02ee, B:115:0x02fa, B:117:0x0306, B:119:0x030c, B:122:0x0319, B:124:0x032f, B:126:0x0335, B:127:0x0355, B:128:0x0386, B:131:0x038d, B:134:0x03a7, B:135:0x03c5, B:137:0x03d5, B:139:0x03db, B:143:0x03e8, B:144:0x03ee, B:146:0x03f9, B:147:0x0403, B:148:0x040c, B:150:0x0431, B:151:0x043a, B:155:0x045a, B:156:0x0474, B:160:0x047d, B:163:0x0483, B:164:0x0486, B:165:0x0487, B:167:0x04b2, B:171:0x04ca), top: B:201:0x01ff }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x043a A[Catch: Exception -> 0x04c5, all -> 0x04df, TRY_LEAVE, TryCatch #10 {Exception -> 0x04c5, blocks: (B:69:0x0203, B:71:0x022b, B:72:0x022e, B:74:0x024c, B:76:0x0255, B:89:0x02a6, B:92:0x02ac, B:102:0x02c3, B:105:0x02c9, B:108:0x02cf, B:109:0x02dd, B:111:0x02e0, B:113:0x02ee, B:115:0x02fa, B:117:0x0306, B:119:0x030c, B:122:0x0319, B:124:0x032f, B:126:0x0335, B:127:0x0355, B:128:0x0386, B:131:0x038d, B:134:0x03a7, B:135:0x03c5, B:137:0x03d5, B:139:0x03db, B:143:0x03e8, B:144:0x03ee, B:146:0x03f9, B:147:0x0403, B:148:0x040c, B:150:0x0431, B:151:0x043a, B:155:0x045a, B:156:0x0474, B:160:0x047d, B:163:0x0483, B:164:0x0486, B:165:0x0487, B:167:0x04b2, B:171:0x04ca), top: B:201:0x01ff }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x047d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.TargetApi(11)
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m16736a(android.content.Context r22, android.content.Context r23, int r24) {
        /*
            Method dump skipped, instructions count: 1369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsInstaller.m16736a(android.content.Context, android.content.Context, int):void");
    }

    /* renamed from: e */
    private boolean m16698e(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    /* renamed from: b */
    Context m16714b(Context context, String str) {
        try {
            if (context.getPackageName() == str || !TbsPVConfig.getInstance(context).isEnableNoCoreGray()) {
                return context.createPackageContext(str, 2);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public boolean m16715b(Context context, File file, int i) {
        TbsLog.m16531i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread #1");
        boolean a = m16732a(context, file, false);
        TbsLog.m16531i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread result is " + a);
        if (a) {
            m16742a().m16739a(context, i);
        }
        return a;
    }

    /* renamed from: a */
    private boolean m16734a(Context context, File file) {
        return m16732a(context, file, false);
    }

    /* renamed from: a */
    private boolean m16732a(Context context, File file, boolean z) {
        File file2;
        String str;
        StringBuilder sb;
        File file3;
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-unzipTbs start");
        if (!FileUtil.m16439c(file)) {
            TbsLogReport.getInstance(context).setInstallErrorCode(204, "apk is invalid!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-520);
            return false;
        }
        try {
            File dir = context.getDir("tbs", 0);
            if (z) {
                file3 = new File(dir, "core_share_decouple");
            } else {
                file3 = new File(dir, "core_unzip_tmp");
            }
            if (file3.exists() && !TbsDownloader.m17031a(context)) {
                FileUtil.m16444b(file3);
            }
        } catch (Throwable th) {
            TbsLog.m16533e("TbsInstaller", "TbsInstaller-unzipTbs -- delete unzip folder if exists exception" + Log.getStackTraceString(th));
        }
        if (z) {
            file2 = m16695f(context, 2);
        } else {
            file2 = m16695f(context, 0);
        }
        if (file2 == null) {
            TbsLogReport.getInstance(context).setInstallErrorCode(205, "tmp unzip dir is null!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-521);
            return false;
        }
        boolean z2 = true;
        try {
            try {
                try {
                    FileUtil.m16459a(file2);
                    if (z) {
                        FileUtil.m16455a(file2, true);
                    }
                    boolean a = FileUtil.m16458a(file, file2);
                    if (a) {
                        a = m16724a(file2, context);
                    }
                    if (z) {
                        for (String str2 : file2.list()) {
                            File file4 = new File(file2, str2);
                            if (file4.getName().endsWith(".dex")) {
                                file4.delete();
                            }
                        }
                        try {
                            new File(m16676s(context), "x5.tbs").delete();
                        } catch (Exception unused) {
                        }
                    }
                    if (!a) {
                        FileUtil.m16444b(file2);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-522);
                        TbsLog.m16533e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:" + file2.exists());
                    } else {
                        m16691g(context, true);
                        if (z) {
                            File p = m16679p(context);
                            FileUtil.m16455a(p, true);
                            file2.renameTo(p);
                            TbsShareManager.m16960b(context);
                        }
                    }
                    return a;
                } catch (IOException e) {
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                    TbsLogReport.getInstance(context).setInstallErrorCode(206, e);
                    if (file2 == null || !file2.exists()) {
                        z2 = false;
                    }
                    if (z2 && file2 != null) {
                        try {
                            FileUtil.m16444b(file2);
                            TbsLog.m16533e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + file2.exists());
                        } catch (Throwable th2) {
                            th = th2;
                            str = "TbsInstaller";
                            sb = new StringBuilder();
                            sb.append("copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:");
                            sb.append(Log.getStackTraceString(th));
                            TbsLog.m16533e(str, sb.toString());
                            return false;
                        }
                    }
                    return false;
                }
            } finally {
                TbsLog.m16531i("TbsInstaller", "TbsInstaller-unzipTbs done");
            }
        } catch (Exception e2) {
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
            TbsLogReport.getInstance(context).setInstallErrorCode(207, e2);
            if (file2 == null || !file2.exists()) {
                z2 = false;
            }
            if (z2 && file2 != null) {
                try {
                    FileUtil.m16444b(file2);
                    TbsLog.m16533e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + file2.exists());
                } catch (Throwable th3) {
                    th = th3;
                    str = "TbsInstaller";
                    sb = new StringBuilder();
                    sb.append("copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:");
                    sb.append(Log.getStackTraceString(th));
                    TbsLog.m16533e(str, sb.toString());
                    return false;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0139  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m16724a(java.io.File r10, android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsInstaller.m16724a(java.io.File, android.content.Context):boolean");
    }

    /* renamed from: e */
    public boolean m16700e(Context context) {
        try {
            File file = new File(FileUtil.m16465a(context, 4), "x5.tbs.decouple");
            File f = m16742a().m16695f(context, 2);
            FileUtil.m16459a(f);
            FileUtil.m16455a(f, true);
            FileUtil.m16458a(file, f);
            for (String str : f.list()) {
                File file2 = new File(f, str);
                if (file2.getName().endsWith(".dex")) {
                    file2.delete();
                }
            }
            m16687i(context, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: i */
    private void m16687i(Context context, int i) {
        File f = m16742a().m16695f(context, i);
        m16742a().m16691g(context, true);
        File p = m16679p(context);
        FileUtil.m16455a(p, true);
        f.renameTo(p);
        TbsShareManager.m16960b(context);
    }

    /* renamed from: j */
    private boolean m16685j(Context context, int i) {
        File file;
        boolean z;
        TbsLog.m16531i("TbsInstaller", "TbsInstaller-doTbsDexOpt start - dirMode: " + i);
        boolean z2 = false;
        try {
            switch (i) {
                case 0:
                    if (!TbsDownloader.m17031a(context)) {
                        file = m16695f(context, 0);
                        break;
                    } else {
                        return true;
                    }
                case 1:
                    file = m16695f(context, 1);
                    break;
                case 2:
                    file = m16678q(context);
                    break;
                default:
                    TbsLog.m16533e("TbsInstaller", "doDexoptOrDexoat mode error: " + i);
                    return false;
            }
            String property = System.getProperty("java.vm.version");
            z = property != null && property.startsWith("2");
            boolean z3 = Build.VERSION.SDK_INT == 23;
            boolean z4 = TbsDownloadConfig.getInstance(context).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_STOP_PRE_OAT, false);
            if (z && z3 && !z4) {
                z2 = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(209, e.toString());
        }
        if (z2 && m16707c(context, file)) {
            TbsLog.m16531i("TbsInstaller", "doTbsDexOpt -- doDexoatForArtVm");
            return true;
        } else if (z) {
            TbsLog.m16531i("TbsInstaller", "doTbsDexOpt -- is ART mode, skip!");
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return true;
        } else {
            TbsLog.m16531i("TbsInstaller", "doTbsDexOpt -- doDexoptForDavlikVM");
            return m16716b(context, file);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.tencent.smtt.sdk.l$4] */
    /* renamed from: a */
    public synchronized boolean m16737a(final Context context, final Context context2) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (f13210p) {
            return true;
        }
        f13210p = true;
        new Thread() { // from class: com.tencent.smtt.sdk.l.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                File file;
                TbsLog.m16531i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread start");
                try {
                    if (context2 == null) {
                        file = new File(TbsShareManager.getHostCorePathAppDefined());
                    } else if (!TbsShareManager.isThirdPartyApp(context)) {
                        file = TbsInstaller.this.m16678q(context2);
                    } else if (TbsShareManager.m16958c(context) == null || !TbsShareManager.m16958c(context).contains("decouple")) {
                        file = TbsInstaller.this.m16678q(context2);
                    } else {
                        file = TbsInstaller.this.m16679p(context2);
                    }
                    File q = TbsInstaller.this.m16678q(context);
                    int i = Build.VERSION.SDK_INT;
                    if (i != 19 && i < 21) {
                        FileUtil.m16457a(file, q, new FileFilter() { // from class: com.tencent.smtt.sdk.l.4.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                return file2.getName().endsWith(".dex");
                            }
                        });
                    }
                    FileUtil.m16457a(file, q, new FileFilter() { // from class: com.tencent.smtt.sdk.l.4.2
                        @Override // java.io.FileFilter
                        public boolean accept(File file2) {
                            return file2.getName().endsWith("tbs.conf");
                        }
                    });
                    TbsLog.m16531i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean m16696f(Context context) {
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            return true;
        }
        try {
            Signature signature = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0];
            if (context.getPackageName().equals(TbsConfig.APP_QB)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_WX)) {
                if (!signature.toCharsString().equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_QQ)) {
                if (!signature.toCharsString().equals("30820253308201bca00302010202044bbb0361300d06092a864886f70d0101050500306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b30090603550403130251513020170d3130303430363039343831375a180f32323834303132303039343831375a306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b300906035504031302515130819f300d06092a864886f70d010101050003818d0030818902818100a15e9756216f694c5915e0b529095254367c4e64faeff07ae13488d946615a58ddc31a415f717d019edc6d30b9603d3e2a7b3de0ab7e0cf52dfee39373bc472fa997027d798d59f81d525a69ecf156e885fd1e2790924386b2230cc90e3b7adc95603ddcf4c40bdc72f22db0f216a99c371d3bf89cba6578c60699e8a0d536950203010001300d06092a864886f70d01010505000381810094a9b80e80691645dd42d6611775a855f71bcd4d77cb60a8e29404035a5e00b21bcc5d4a562482126bd91b6b0e50709377ceb9ef8c2efd12cc8b16afd9a159f350bb270b14204ff065d843832720702e28b41491fbc3a205f5f2f42526d67f17614d8a974de6487b2c866efede3b4e49a0f916baa3c1336fd2ee1b1629652049")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_DEMO)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_QZONE)) {
                if (!signature.toCharsString().equals("308202ad30820216a00302010202044c26cea2300d06092a864886f70d010105050030819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d301e170d3130303632373034303830325a170d3335303632313034303830325a30819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d30819f300d06092a864886f70d010101050003818d003081890281810082d6aca037a9843fbbe88b6dd19f36e9c24ce174c1b398f3a529e2a7fe02de99c27539602c026edf96ad8d43df32a85458bca1e6fbf11958658a7d6751a1d9b782bf43a8c19bd1c06bdbfd94c0516326ae3cf638ac42bb470580e340c46e6f306a772c1ef98f10a559edf867f3f31fe492808776b7bd953b2cba2d2b2d66a44f0203010001300d06092a864886f70d0101050500038181006003b04a8a8c5be9650f350cda6896e57dd13e6e83e7f891fc70f6a3c2eaf75cfa4fc998365deabbd1b9092159edf4b90df5702a0d101f8840b5d4586eb92a1c3cd19d95fbc1c2ac956309eda8eef3944baf08c4a49d3b9b3ffb06bc13dab94ecb5b8eb74e8789aa0ba21cb567f538bbc59c2a11e6919924a24272eb79251677")) {
                    return false;
                }
            } else if (context.getPackageName().equals("com.tencent.qqpimsecure") && !signature.toCharsString().equals("30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8")) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-installLocalTbsCore getPackageInfo fail");
            return false;
        }
    }

    /* renamed from: d */
    public Context m16703d(Context context, int i) {
        Context b;
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreHostContext tbsCoreTargetVer=" + i);
        if (i <= 0) {
            return null;
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        for (int i2 = 0; i2 < coreProviderAppList.length; i2++) {
            if (!context.getPackageName().equalsIgnoreCase(coreProviderAppList[i2]) && m16698e(context, coreProviderAppList[i2]) && (b = m16714b(context, coreProviderAppList[i2])) != null) {
                if (!m16696f(b)) {
                    TbsLog.m16533e("TbsInstaller", "TbsInstaller--getTbsCoreHostContext " + coreProviderAppList[i2] + " illegal signature go on next");
                } else {
                    int i3 = m16688i(b);
                    TbsLog.m16531i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext hostTbsCoreVer=" + i3);
                    if (i3 != 0 && i3 == i) {
                        TbsLog.m16531i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext targetApp=" + coreProviderAppList[i2]);
                        return b;
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: c */
    int m16706c(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.versionCode;
        }
        return 0;
    }

    /* renamed from: g */
    public void m16693g(Context context) {
        boolean z;
        FileLock fileLock;
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        if (z && (fileLock = f13206l) != null) {
            FileUtil.m16461a(context, fileLock);
        }
    }

    /* renamed from: w */
    private boolean m16672w(Context context) {
        boolean z;
        TbsLog.m16531i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #1 ");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        TbsLog.m16531i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #2  enabled is " + z);
        if (!z) {
            f13206l = X5CoreEngine.m16621a().m16617b(context);
        } else {
            f13206l = FileUtil.m16435f(context);
        }
        if (f13206l == null) {
            TbsLog.m16531i("TbsInstaller", "getTbsCoreRenameFileLock## failed!");
            return false;
        }
        TbsLog.m16531i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock true ");
        return true;
    }

    /* renamed from: x */
    private void m16671x(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (!m16672w(context)) {
            TbsLog.m16531i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            m16747A(context);
            m16746B(context);
            TbsLog.m16531i("TbsInstaller", "after renameTbsCoreShareDir");
            if (!TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.m16531i("TbsInstaller", "prepare to shareTbsCore");
                TbsShareManager.m16964a(context);
            } else {
                TbsLog.m16531i("TbsInstaller", "is thirdapp and not chmod");
            }
            TbsCoreVerManager.m16764a(context).m16766a(0);
            TbsCoreVerManager.m16764a(context).m16759b(0);
            TbsCoreVerManager.m16764a(context).m16751d(0);
            TbsCoreVerManager.m16764a(context).m16762a("incrupdate_retry_num", 0);
            TbsCoreVerManager.m16764a(context).m16754c(0, 3);
            TbsCoreVerManager.m16764a(context).m16763a("");
            TbsCoreVerManager.m16764a(context).m16762a("tpatch_num", 0);
            TbsCoreVerManager.m16764a(context).m16755c(-1);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i <= 0 || i == m16742a().m16690h(context) || i != m16742a().m16688i(context)) {
                    TbsLog.m16531i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip #1 deCoupleCoreVersion is " + i + " getTbsCoreShareDecoupleCoreVersion is " + m16742a().m16690h(context) + " getTbsCoreInstalledVerInNolock is " + m16742a().m16688i(context));
                } else {
                    m16681n(context);
                }
            }
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, m16682m(context), true);
            }
            f13200a.set(0);
            f13209o = 0;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode(TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from unzip:" + th.toString());
            TbsLog.m16532e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
        m16693g(context);
    }

    /* renamed from: y */
    private void m16670y(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromTpatch");
        if (!m16672w(context)) {
            TbsLog.m16531i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            m16747A(context);
            m16744D(context);
            TbsShareManager.m16964a(context);
            TbsCoreVerManager.m16764a(context).m16758b(0, -1);
            TbsCoreVerManager.m16764a(context).m16762a("tpatch_num", 0);
            f13200a.set(0);
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode(TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, "exception when renameing from tpatch:" + e.toString());
        }
        m16693g(context);
    }

    /* renamed from: z */
    private void m16669z(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy");
        if (!m16672w(context)) {
            TbsLog.m16531i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            m16747A(context);
            m16745C(context);
            TbsShareManager.m16964a(context);
            TbsCoreVerManager.m16764a(context).m16765a(0, 3);
            TbsCoreVerManager.m16764a(context).m16762a("tpatch_num", 0);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i <= 0 || i == m16742a().m16690h(context) || i != m16742a().m16688i(context)) {
                    TbsLog.m16531i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy #1 deCoupleCoreVersion is " + i + " getTbsCoreShareDecoupleCoreVersion is " + m16742a().m16690h(context) + " getTbsCoreInstalledVerInNolock is " + m16742a().m16688i(context));
                } else {
                    m16681n(context);
                }
            }
            f13200a.set(0);
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            instance.setInstallErrorCode(TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from copy:" + e.toString());
        }
        m16693g(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m16723a(String str) {
        Throwable th;
        if (str == null) {
            return 0;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            File file = new File(new File(str), "tbs.conf");
            if (!file.exists()) {
                return 0;
            }
            Properties properties = new Properties();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream2);
                bufferedInputStream2.close();
                String property = properties.getProperty("tbs_core_version");
                if (property == null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return 0;
                }
                int parseInt = Integer.parseInt(property);
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
                return parseInt;
            } catch (Exception unused3) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return 0;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        } catch (Exception unused6) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m16699e(Context context, int i) {
        return m16725a(m16695f(context, i));
    }

    /* renamed from: a */
    int m16725a(File file) {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsVersion  tbsShareDir is " + file);
            File file2 = new File(file, "tbs.conf");
            if (!file2.exists()) {
                return 0;
            }
            Properties properties = new Properties();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file2));
            try {
                properties.load(bufferedInputStream2);
                bufferedInputStream2.close();
                String property = properties.getProperty("tbs_core_version");
                if (property == null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return 0;
                }
                int parseInt = Integer.parseInt(property);
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
                return parseInt;
            } catch (Exception unused3) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return 0;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        } catch (Exception unused6) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: d */
    public String m16702d(Context context, String str) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            File file = new File(m16678q(context), "tbs.conf");
            if (!file.exists()) {
                return null;
            }
            Properties properties = new Properties();
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream);
                bufferedInputStream.close();
                String property = properties.getProperty(str);
                try {
                    bufferedInputStream.close();
                } catch (IOException unused) {
                }
                return property;
            } catch (Exception unused2) {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            bufferedInputStream = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public int m16690h(Context context) {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            File file = new File(m16679p(context), "tbs.conf");
            if (!file.exists()) {
                return 0;
            }
            Properties properties = new Properties();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream2);
                bufferedInputStream2.close();
                String property = properties.getProperty("tbs_core_version");
                if (property == null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return 0;
                }
                int parseInt = Integer.parseInt(property);
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
                return parseInt;
            } catch (Exception unused3) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return 0;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        } catch (Exception unused6) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public int m16688i(Context context) {
        Throwable th;
        Exception e;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                File file = new File(m16678q(context), "tbs.conf");
                if (!file.exists()) {
                    return 0;
                }
                Properties properties = new Properties();
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    properties.load(bufferedInputStream2);
                    bufferedInputStream2.close();
                    String property = properties.getProperty("tbs_core_version");
                    if (property == null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e2) {
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e2.toString());
                        }
                        return 0;
                    }
                    int parseInt = Integer.parseInt(property);
                    if (f13209o == 0) {
                        f13209o = parseInt;
                    }
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e3) {
                        TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e3.toString());
                    }
                    return parseInt;
                } catch (Exception e4) {
                    e = e4;
                    bufferedInputStream = bufferedInputStream2;
                    TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock Exception=" + e.toString());
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e5.toString());
                        }
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e6) {
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e6.toString());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e7) {
            e = e7;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public int m16686j(Context context) {
        int i = f13209o;
        return i != 0 ? i : m16688i(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: k */
    public void m16684k(Context context) {
        if (f13209o == 0) {
            f13209o = m16688i(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: l */
    public boolean m16683l(Context context) {
        return new File(m16678q(context), "tbs.conf").exists();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: m */
    public int m16682m(Context context) {
        Throwable th;
        Exception e;
        if (!m16675t(context)) {
            return -1;
        }
        boolean tryLock = f13204i.tryLock();
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock locked=" + tryLock);
        if (tryLock) {
            BufferedInputStream bufferedInputStream = null;
            try {
                try {
                    File file = new File(m16678q(context), "tbs.conf");
                    if (!file.exists()) {
                        try {
                            if (f13204i.isHeldByCurrentThread()) {
                                f13204i.unlock();
                            }
                        } catch (Throwable th2) {
                            TbsLog.m16533e("TbsInstaller", "TbsRenameLock.unlock exception: " + th2);
                        }
                        m16721b();
                        return 0;
                    }
                    Properties properties = new Properties();
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                    try {
                        properties.load(bufferedInputStream2);
                        bufferedInputStream2.close();
                        String property = properties.getProperty("tbs_core_version");
                        if (property == null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e2) {
                                TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e2.toString());
                            }
                            try {
                                if (f13204i.isHeldByCurrentThread()) {
                                    f13204i.unlock();
                                }
                            } catch (Throwable th3) {
                                TbsLog.m16533e("TbsInstaller", "TbsRenameLock.unlock exception: " + th3);
                            }
                            m16721b();
                            return 0;
                        }
                        f13200a.set(Integer.valueOf(Integer.parseInt(property)));
                        int intValue = f13200a.get().intValue();
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e3) {
                            TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e3.toString());
                        }
                        try {
                            if (f13204i.isHeldByCurrentThread()) {
                                f13204i.unlock();
                            }
                        } catch (Throwable th4) {
                            TbsLog.m16533e("TbsInstaller", "TbsRenameLock.unlock exception: " + th4);
                        }
                        m16721b();
                        return intValue;
                    } catch (Exception e4) {
                        e = e4;
                        bufferedInputStream = bufferedInputStream2;
                        TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock Exception=" + e.toString());
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                                TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e5.toString());
                            }
                        }
                        try {
                            if (f13204i.isHeldByCurrentThread()) {
                                f13204i.unlock();
                            }
                        } catch (Throwable th5) {
                            TbsLog.m16533e("TbsInstaller", "TbsRenameLock.unlock exception: " + th5);
                        }
                        m16721b();
                        return 0;
                    } catch (Throwable th6) {
                        th = th6;
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e6) {
                                TbsLog.m16531i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e6.toString());
                            }
                        }
                        try {
                            if (f13204i.isHeldByCurrentThread()) {
                                f13204i.unlock();
                            }
                        } catch (Throwable th7) {
                            TbsLog.m16533e("TbsInstaller", "TbsRenameLock.unlock exception: " + th7);
                        }
                        m16721b();
                        throw th;
                    }
                } catch (Exception e7) {
                    e = e7;
                }
            } catch (Throwable th8) {
                th = th8;
            }
        } else {
            m16721b();
            return 0;
        }
    }

    /* renamed from: A */
    private void m16747A(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--deleteOldCore");
        FileUtil.m16455a(m16678q(context), false);
    }

    /* renamed from: B */
    private void m16746B(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--renameShareDir");
        File f = m16695f(context, 0);
        File q = m16678q(context);
        if (f == null || q == null) {
            TbsLog.m16531i("TbsInstaller", "renameTbsCoreShareDir return,tmpTbsCoreUnzipDir=" + f + "tbsSharePath=" + q);
            return;
        }
        boolean renameTo = f.renameTo(q);
        TbsLog.m16531i("TbsInstaller", "renameTbsCoreShareDir rename success=" + renameTo);
        if (context != null && TbsConfig.APP_WX.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
            if (renameTo) {
                TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.RENAME_SUCCESS, ExpandableTextView.f6958c);
            } else {
                TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.RENAME_FAIL, ExpandableTextView.f6958c);
            }
        }
        m16691g(context, false);
    }

    /* renamed from: n */
    public boolean m16681n(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple #0");
        File q = m16678q(context);
        File p = m16679p(context);
        try {
            FileUtil.m16455a(p, true);
            FileUtil.m16457a(q, p, new FileFilter() { // from class: com.tencent.smtt.sdk.l.5
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return !file.getName().endsWith(".dex") && !file.getName().endsWith(".jar_is_first_load_dex_flag_file");
                }
            });
            TbsShareManager.m16960b(context);
            TbsLog.m16531i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple success!!!");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: C */
    private void m16745C(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--renameTbsCoreCopyDir");
        File f = m16695f(context, 1);
        File q = m16678q(context);
        if (f != null && q != null) {
            f.renameTo(q);
            m16691g(context, false);
        }
    }

    /* renamed from: D */
    private void m16744D(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--renameTbsTpatchCoreDir");
        File f = m16695f(context, 5);
        File q = m16678q(context);
        if (f != null && q != null) {
            f.renameTo(q);
            m16691g(context, false);
        }
    }

    /* renamed from: E */
    private void m16743E(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File f = m16695f(context, 0);
        if (f != null) {
            FileUtil.m16455a(f, false);
        }
        TbsCoreVerManager.m16764a(context).m16754c(0, 5);
        TbsCoreVerManager.m16764a(context).m16755c(-1);
        QbSdk.m17056a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: o */
    public void m16680o(Context context) {
        TbsLog.m16531i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        TbsCoreVerManager.m16764a(context).m16766a(0);
        TbsCoreVerManager.m16764a(context).m16759b(0);
        TbsCoreVerManager.m16764a(context).m16751d(0);
        TbsCoreVerManager.m16764a(context).m16762a("incrupdate_retry_num", 0);
        if (!TbsDownloader.m17031a(context)) {
            TbsCoreVerManager.m16764a(context).m16754c(0, -1);
            TbsCoreVerManager.m16764a(context).m16763a("");
            TbsCoreVerManager.m16764a(context).m16762a("copy_retry_num", 0);
            TbsCoreVerManager.m16764a(context).m16755c(-1);
            TbsCoreVerManager.m16764a(context).m16765a(0, -1);
            FileUtil.m16455a(m16695f(context, 0), true);
            FileUtil.m16455a(m16695f(context, 1), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public File m16718b(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        TbsLog.m16531i("TbsInstaller", "getTbsCoreShareDir,mkdir false");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: p */
    public File m16679p(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_share_decouple");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public File m16709c(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share_decouple");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: q */
    public File m16678q(Context context) {
        return m16718b((Context) null, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: r */
    public File m16677r(Context context) {
        File file = new File(context.getDir("tbs", 0), "share");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: s */
    public static File m16676s(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public File m16695f(Context context, int i) {
        return m16738a(context, i, true);
    }

    /* renamed from: a */
    File m16738a(Context context, int i, boolean z) {
        File dir = context.getDir("tbs", 0);
        String str = "";
        switch (i) {
            case 0:
                str = "core_unzip_tmp";
                break;
            case 1:
                str = "core_copy_tmp";
                break;
            case 2:
                str = "core_unzip_tmp_decouple";
                break;
            case 3:
                str = "core_share_backup";
                break;
            case 4:
                str = "core_share_backup_tmp";
                break;
            case 5:
                str = "tpatch_tmp";
                break;
            case 6:
                str = "tpatch_decouple_tmp";
                break;
        }
        TbsLog.m16531i("TbsInstaller", "type=" + i + "needMakeDir=" + z + "folder=" + str);
        File file = new File(dir, str);
        if (!file.isDirectory()) {
            if (!z) {
                TbsLog.m16531i("TbsInstaller", "getCoreDir,no need mkdir");
                return null;
            } else if (!file.mkdir()) {
                TbsLog.m16531i("TbsInstaller", "getCoreDir,mkdir false");
                return null;
            }
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean m16692g(Context context, int i) {
        File file;
        try {
            boolean isThirdPartyApp = TbsShareManager.isThirdPartyApp(context);
            if (!isThirdPartyApp) {
                file = m16678q(context);
            } else if (TbsShareManager.m16949j(context)) {
                file = new File(TbsShareManager.m16958c(context));
                if (file.getAbsolutePath().contains(TbsConfig.APP_DEMO)) {
                    return true;
                }
            } else {
                TbsLog.m16533e("TbsInstaller", "321");
                return false;
            }
            if (file != null) {
                Long[][] lArr = f13208n;
                int length = lArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    Long[] lArr2 = lArr[i2];
                    if (i == lArr2[0].intValue()) {
                        File file2 = new File(file, "libmttwebview.so");
                        if (!file2.exists() || file2.length() != lArr2[1].longValue()) {
                            if (!isThirdPartyApp) {
                                FileUtil.m16444b(context.getDir("tbs", 0));
                            }
                            f13200a.set(0);
                            TbsLog.m16533e("TbsInstaller", "322");
                            return false;
                        }
                        TbsLog.m16535d("TbsInstaller", "check so success: " + i + "; file: " + file2);
                    } else {
                        i2++;
                    }
                }
                return true;
            }
            TbsLog.m16533e("TbsInstaller", "323");
            return false;
        } catch (Throwable th) {
            TbsLog.m16533e("TbsInstaller", "ISTBSCORELEGAL exception getMessage is " + th.getMessage());
            TbsLog.m16533e("TbsInstaller", "ISTBSCORELEGAL exception getCause is " + th.getCause());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: t */
    public synchronized boolean m16675t(Context context) {
        if (this.f13211e > 0) {
            TbsLog.m16531i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
            this.f13211e++;
            return true;
        }
        this.f13213g = FileUtil.m16445b(context, true, "tbslock.txt");
        if (this.f13213g != null) {
            this.f13212f = FileUtil.m16464a(context, this.f13213g);
            if (this.f13212f == null) {
                TbsLog.m16531i("TbsInstaller", "getTbsInstallingFileLock tbsFileLockFileLock == null");
                return false;
            }
            TbsLog.m16531i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
            this.f13211e++;
            return true;
        }
        TbsLog.m16531i("TbsInstaller", "getTbsInstallingFileLock get install fos failed");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void m16721b() {
        if (this.f13211e <= 0) {
            TbsLog.m16531i("TbsInstaller", "releaseTbsInstallingFileLock currentTbsFileLockStackCount=" + this.f13211e + "call stack:" + Log.getStackTraceString(new Throwable()));
        } else if (this.f13211e > 1) {
            TbsLog.m16531i("TbsInstaller", "releaseTbsInstallingFileLock with skip");
            this.f13211e--;
        } else {
            if (this.f13211e == 1) {
                TbsLog.m16531i("TbsInstaller", "releaseTbsInstallingFileLock without skip");
                FileUtil.m16448a(this.f13212f, this.f13213g);
                this.f13211e = 0;
            }
        }
    }

    /* renamed from: b */
    private boolean m16716b(Context context, File file) {
        try {
            File[] listFiles = file.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.l.6
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    return file2.getName().endsWith(".jar");
                }
            });
            int length = listFiles.length;
            if (Build.VERSION.SDK_INT < 16 && context.getPackageName() != null && context.getPackageName().equalsIgnoreCase(TbsConfig.APP_DEMO)) {
                try {
                    Thread.sleep(5000L);
                } catch (Exception unused) {
                }
            }
            ClassLoader classLoader = context.getClassLoader();
            for (int i = 0; i < length; i++) {
                TbsLog.m16531i("TbsInstaller", "jarFile: " + listFiles[i].getAbsolutePath());
                new DexClassLoader(listFiles[i].getAbsolutePath(), file.getAbsolutePath(), null, classLoader);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(209, e.toString());
            TbsLog.m16531i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return false;
        }
    }

    /* renamed from: c */
    private boolean m16707c(Context context, File file) {
        File[] listFiles;
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
            String a = OatHelper.m16835a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a)) {
                TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.DEXOAT_EXCEPTION, "can not find oat command");
                return false;
            }
            for (File file4 : file.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.l.7
                @Override // java.io.FileFilter
                public boolean accept(File file5) {
                    return file5.getName().endsWith(".jar");
                }
            })) {
                String substring = file4.getName().substring(0, file4.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + m16742a().m16678q(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.DEXOAT_EXCEPTION, e);
            return false;
        }
    }
}
