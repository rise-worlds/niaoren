package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.lody.virtual.server.pm.installer.PackageHelper;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.ApkUtil;
import com.tencent.smtt.utils.AppUtil;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.apache.tools.tar.TarConstants;
import org.json.JSONArray;

/* loaded from: classes2.dex */
public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";
    public static final String TBS_METADATA = "com.tencent.mm.BuildInfo.CLIENT_VERSION";

    /* renamed from: a */
    static boolean f12902a;

    /* renamed from: b */
    private static String f12903b;

    /* renamed from: c */
    private static Context f12904c;

    /* renamed from: d */
    private static Handler f12905d;

    /* renamed from: e */
    private static String f12906e;

    /* renamed from: g */
    private static TbsApkDownloader f12908g;

    /* renamed from: h */
    private static HandlerThread f12909h;

    /* renamed from: f */
    private static Object f12907f = new byte[0];

    /* renamed from: i */
    private static boolean f12910i = false;

    /* renamed from: j */
    private static boolean f12911j = false;

    /* renamed from: k */
    private static boolean f12912k = false;

    /* renamed from: l */
    private static long f12913l = -1;

    /* loaded from: classes2.dex */
    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i);
    }

    /* renamed from: a */
    private static String m17028a(String str) {
        return str == null ? "" : str;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return f12909h;
    }

    /* renamed from: c */
    private static boolean m17016c() {
        try {
            for (String str : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(f12904c, str) > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setAppContext(Context context) {
        if (context != null && context.getApplicationContext() != null) {
            f12904c = context.getApplicationContext();
        }
    }

    public static boolean needSendRequest(Context context, boolean z) {
        f12904c = context.getApplicationContext();
        TbsLog.initIfNeed(context);
        if (!m17030a(f12904c, z)) {
            return false;
        }
        int m = TbsInstaller.m16742a().m16682m(context);
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needSendRequest] localTbsVersion=" + m);
        if (m > 0) {
            return false;
        }
        boolean z2 = true;
        if (m17029a(f12904c, false, true)) {
            return true;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(f12904c);
        boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needSendRequest] hasNeedDownloadKey=" + contains);
        boolean z3 = !contains ? true : instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needSendRequest] needDownload=" + z3);
        if (!z3 || !m17009h()) {
            z2 = false;
        }
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needSendRequest] ret=" + z2);
        return z2;
    }

    /* renamed from: a */
    private static boolean m17029a(Context context, boolean z, boolean z2) {
        boolean z3;
        String str;
        long j;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (!z) {
            String string = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null);
            int i = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0);
            String string2 = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, null);
            String a = AppUtil.m16511a(f12904c);
            int b = AppUtil.m16501b(f12904c);
            String a2 = AppUtil.m16509a(f12904c, TBS_METADATA);
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needSendQueryRequest] appVersionName=" + a + " oldAppVersionName=" + string + " appVersionCode=" + b + " oldAppVersionCode=" + i + " appMetadata=" + a2 + " oldAppVersionMetadata=" + string2);
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK, 0L);
            StringBuilder sb = new StringBuilder();
            sb.append("[TbsDownloader.needSendQueryRequest] timeLastCheck=");
            sb.append(j2);
            sb.append(" timeNow=");
            sb.append(currentTimeMillis);
            TbsLog.m16531i(LOGTAG, sb.toString());
            if (z2) {
                boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK);
                TbsLog.m16531i(LOGTAG, "[TbsDownloader.needSendQueryRequest] hasLaskCheckKey=" + contains);
                if (contains) {
                    j = 0;
                    if (j2 == 0) {
                        j2 = currentTimeMillis;
                    }
                } else {
                    j = 0;
                }
            } else {
                j = 0;
            }
            long j3 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_REQUEST_SUCCESS, j);
            long j4 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, j);
            long retryInterval = instance.getRetryInterval();
            TbsLog.m16531i(LOGTAG, "retryInterval = " + retryInterval + " s");
            TbsPVConfig.releaseInstance();
            int emergentCoreVersion = TbsPVConfig.getInstance(f12904c).getEmergentCoreVersion();
            int i2 = TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            long j5 = currentTimeMillis - j2;
            long j6 = retryInterval * 1000;
            if (j5 > j6) {
                str = null;
                z3 = true;
            } else if (emergentCoreVersion > TbsInstaller.m16742a().m16688i(f12904c) && emergentCoreVersion > i2) {
                str = null;
                z3 = true;
            } else if (TbsShareManager.isThirdPartyApp(f12904c) && j3 > 0 && currentTimeMillis - j3 > j6 && j4 < 11) {
                str = null;
                z3 = true;
            } else if (TbsShareManager.isThirdPartyApp(f12904c) && TbsShareManager.findCoreForThirdPartyApp(f12904c) == 0 && !m17012e()) {
                TbsInstaller.m16742a().m16704d(f12904c);
                str = null;
                z3 = true;
            } else if (a == null || b == 0 || a2 == null) {
                if (TbsShareManager.isThirdPartyApp(f12904c)) {
                    str = "timeNow - timeLastCheck is " + j5 + " TbsShareManager.findCoreForThirdPartyApp(sAppContext) is " + TbsShareManager.findCoreForThirdPartyApp(f12904c) + " sendRequestWithSameHostCoreVersion() is " + m17012e() + " appVersionName is " + a + " appVersionCode is " + b + " appMetadata is " + a2 + " oldAppVersionName is " + string + " oldAppVersionCode is " + i + " oldAppVersionMetadata is " + string2;
                    z3 = false;
                }
                str = null;
                z3 = false;
            } else {
                if (!a.equals(string) || b != i || !a2.equals(string2)) {
                    str = null;
                    z3 = true;
                }
                str = null;
                z3 = false;
            }
        } else {
            str = null;
            z3 = true;
        }
        if (!z3 && TbsShareManager.isThirdPartyApp(f12904c)) {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(f12904c).tbsLogInfo();
            tbsLogInfo.setErrorCode(-119);
            tbsLogInfo.setFailDetail(str);
            TbsLogReport.getInstance(f12904c).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
        }
        return z3;
    }

    /* renamed from: a */
    private static boolean m17030a(Context context, boolean z) {
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (Build.VERSION.SDK_INT < 8) {
            instance.setDownloadInterruptCode(-102);
            return false;
        } else if (!QbSdk.f12791c && TbsShareManager.isThirdPartyApp(f12904c) && !m17016c()) {
            return false;
        } else {
            if (!instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                if (z && !TbsConfig.APP_WX.equals(context.getApplicationInfo().packageName)) {
                    TbsLog.m16531i(LOGTAG, "needDownload-oversea is true, but not WX");
                    z = false;
                }
                instance.f12891a.put(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
                instance.commit();
                f12911j = z;
                TbsLog.m16531i(LOGTAG, "needDownload-first-called--isoversea = " + z);
            }
            if (!getOverSea(context) || Build.VERSION.SDK_INT == 16 || Build.VERSION.SDK_INT == 17 || Build.VERSION.SDK_INT == 18) {
                Matcher matcher = null;
                f12906e = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, null);
                if (TextUtils.isEmpty(f12906e)) {
                    return true;
                }
                try {
                    matcher = Pattern.compile("i686|mips|x86_64").matcher(f12906e);
                } catch (Exception unused) {
                }
                if (matcher == null || !matcher.find()) {
                    return true;
                }
                TbsLog.m16533e(LOGTAG, "can not support x86 devices!!");
                instance.setDownloadInterruptCode(PackageHelper.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES);
                return false;
            }
            TbsLog.m16531i(LOGTAG, "needDownload- return false,  because of  version is " + Build.VERSION.SDK_INT + ", and overea");
            instance.setDownloadInterruptCode(PackageHelper.INSTALL_PARSE_FAILED_NO_CERTIFICATES);
            return false;
        }
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, true, null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        return needDownload(context, z, z2, true, tbsDownloaderCallback);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, boolean z3, TbsDownloaderCallback tbsDownloaderCallback) {
        boolean z4;
        boolean z5;
        TbsLog.m16531i(LOGTAG, "needDownload,process=" + QbSdk.getCurrentProcessName(context) + "stack=" + Log.getStackTraceString(new Throwable()));
        TbsDownloadUpload.clear();
        TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
        instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_1));
        instance.commit();
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload] oversea=" + z + ",isDownloadForeground=" + z2);
        TbsLog.initIfNeed(context);
        if (TbsInstaller.f13201b) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload]#1,return false");
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_1));
            instance.commit();
            return false;
        }
        TbsLog.app_extra(LOGTAG, context);
        f12904c = context.getApplicationContext();
        TbsDownloadConfig instance2 = TbsDownloadConfig.getInstance(f12904c);
        instance2.setDownloadInterruptCode(-100);
        if (!m17030a(f12904c, z)) {
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload]#2,return false");
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_2));
            instance.commit();
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2));
            instance.commit();
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        m17013d();
        if (f12910i) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            instance2.setDownloadInterruptCode(PackageHelper.INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING);
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload]#3,return false");
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_3));
            instance.commit();
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3));
            instance.commit();
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        boolean a = m17029a(f12904c, z2, false);
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload],needSendRequest=" + a);
        if (a) {
            m17024a(z2, tbsDownloaderCallback, z3);
            instance2.setDownloadInterruptCode(PackageHelper.NO_NATIVE_LIBRARIES);
        } else {
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.NEEDDOWNLOAD_4));
            instance.commit();
        }
        f12905d.removeMessages(102);
        Message.obtain(f12905d, 102).sendToTarget();
        if (QbSdk.f12791c || !TbsShareManager.isThirdPartyApp(context)) {
            z4 = instance2.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + z4);
            z5 = (z4 || TbsShareManager.isThirdPartyApp(context)) ? instance2.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
        } else {
            z5 = false;
            z4 = false;
        }
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload]#4,needDownload=" + z5 + ",hasNeedDownloadKey=" + z4);
        if (!z5) {
            int m = TbsInstaller.m16742a().m16682m(f12904c);
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload]#7,tbsLocalVersion=" + m + ",needSendRequest=" + a);
            if (a || m <= 0) {
                f12905d.removeMessages(103);
                if (m > 0 || a) {
                    Message.obtain(f12905d, 103, 1, 0, f12904c).sendToTarget();
                } else {
                    Message.obtain(f12905d, 103, 0, 0, f12904c).sendToTarget();
                }
                instance2.setDownloadInterruptCode(-121);
            } else {
                instance2.setDownloadInterruptCode(-119);
            }
        } else if (!m17009h()) {
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload]#5,set needDownload = false");
            z5 = false;
        } else {
            instance2.setDownloadInterruptCode(-118);
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload]#6");
        }
        if (!a && tbsDownloaderCallback != null) {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
        }
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.needDownload] needDownload=" + z5);
        instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, Integer.valueOf(z5 ? TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE : TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4));
        instance.commit();
        return z5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17031a(Context context) {
        return TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        synchronized (TbsDownloader.class) {
            TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.STARTDOWNLOAD_1));
            instance.commit();
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + f12904c);
            if (TbsInstaller.f13201b) {
                instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.STARTDOWNLOAD_2));
                instance.commit();
                return;
            }
            int i = 1;
            f12902a = true;
            f12904c = context.getApplicationContext();
            TbsDownloadConfig.getInstance(f12904c).setDownloadInterruptCode(-200);
            if (Build.VERSION.SDK_INT < 8) {
                QbSdk.f12801m.onDownloadFinish(110);
                TbsDownloadConfig.getInstance(f12904c).setDownloadInterruptCode(-201);
                instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.STARTDOWNLOAD_3));
                instance.commit();
                return;
            }
            m17013d();
            if (f12910i) {
                QbSdk.f12801m.onDownloadFinish(TbsListener.ErrorCode.THREAD_INIT_ERROR);
                TbsDownloadConfig.getInstance(f12904c).setDownloadInterruptCode(-202);
                instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf((int) TbsListener.ErrorCode.STARTDOWNLOAD_4));
                instance.commit();
                return;
            }
            if (z) {
                stopDownload();
            }
            f12905d.removeMessages(101);
            f12905d.removeMessages(100);
            Message obtain = Message.obtain(f12905d, 101, QbSdk.f12801m);
            if (!z) {
                i = 0;
            }
            obtain.arg1 = i;
            obtain.sendToTarget();
        }
    }

    public static int getCoreShareDecoupleCoreVersionByContext(Context context) {
        return TbsInstaller.m16742a().m16690h(context);
    }

    public static int getCoreShareDecoupleCoreVersion() {
        return TbsInstaller.m16742a().m16690h(f12904c);
    }

    public static boolean needDownloadDecoupleCore() {
        int i;
        if (TbsShareManager.isThirdPartyApp(f12904c) || m17031a(f12904c)) {
            return false;
        }
        return System.currentTimeMillis() - TbsDownloadConfig.getInstance(f12904c).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0L) >= TbsDownloadConfig.getInstance(f12904c).getRetryInterval() * 1000 && (i = TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0)) > 0 && i != TbsInstaller.m16742a().m16690h(f12904c) && TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i;
    }

    public static boolean startDecoupleCoreIfNeeded() {
        TbsLog.m16531i(LOGTAG, "startDecoupleCoreIfNeeded ");
        if (TbsShareManager.isThirdPartyApp(f12904c)) {
            return false;
        }
        TbsLog.m16531i(LOGTAG, "startDecoupleCoreIfNeeded #1");
        if (m17031a(f12904c) || f12905d == null) {
            return false;
        }
        TbsLog.m16531i(LOGTAG, "startDecoupleCoreIfNeeded #2");
        long j = TbsDownloadConfig.getInstance(f12904c).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0L);
        if (System.currentTimeMillis() - j < TbsDownloadConfig.getInstance(f12904c).getRetryInterval() * 1000) {
            return false;
        }
        TbsLog.m16531i(LOGTAG, "startDecoupleCoreIfNeeded #3");
        int i = TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
        if (i <= 0 || i == TbsInstaller.m16742a().m16690h(f12904c)) {
            TbsLog.m16531i(LOGTAG, "startDecoupleCoreIfNeeded no need, deCoupleCoreVersion is " + i + " getTbsCoreShareDecoupleCoreVersion is " + TbsInstaller.m16742a().m16690h(f12904c));
        } else if (TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i || TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1) {
            TbsLog.m16531i(LOGTAG, "startDecoupleCoreIfNeeded #4");
            f12902a = true;
            f12905d.removeMessages(108);
            Message obtain = Message.obtain(f12905d, 108, QbSdk.f12801m);
            obtain.arg1 = 0;
            obtain.sendToTarget();
            TbsDownloadConfig.getInstance(f12904c).f12891a.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, Long.valueOf(System.currentTimeMillis()));
            return true;
        } else {
            TbsLog.m16531i(LOGTAG, "startDecoupleCoreIfNeeded no need, KEY_TBS_DOWNLOAD_V is " + TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) + " deCoupleCoreVersion is " + i + " KEY_TBS_DOWNLOAD_V_TYPE is " + TbsDownloadConfig.getInstance(f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0));
        }
        return false;
    }

    public static void stopDownload() {
        if (!f12910i) {
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.stopDownload]");
            TbsApkDownloader iVar = f12908g;
            if (iVar != null) {
                iVar.m16788b();
            }
            Handler handler = f12905d;
            if (handler != null) {
                handler.removeMessages(100);
                f12905d.removeMessages(101);
                f12905d.removeMessages(108);
            }
        }
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.m16531i(LOGTAG, "[TbsDownloader.isDownloading] is " + f12902a);
            z = f12902a;
        }
        return z;
    }

    public static boolean isDownloadForeground() {
        TbsApkDownloader iVar = f12908g;
        return iVar != null && iVar.m16779d();
    }

    /* renamed from: d */
    private static synchronized void m17013d() {
        synchronized (TbsDownloader.class) {
            if (f12909h == null) {
                f12909h = TbsHandlerThread.m16748a();
                try {
                    f12908g = new TbsApkDownloader(f12904c);
                    f12905d = new Handler(f12909h.getLooper()) { // from class: com.tencent.smtt.sdk.TbsDownloader.1
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            FileOutputStream fileOutputStream;
                            int i = message.what;
                            boolean z = true;
                            if (i != 108) {
                                switch (i) {
                                    case 100:
                                        boolean z2 = message.arg1 == 1;
                                        boolean b = TbsDownloader.m17017b(true, false, false, message.arg2 == 1);
                                        if (message.obj != null && (message.obj instanceof TbsDownloaderCallback)) {
                                            TbsLog.m16531i(TbsDownloader.LOGTAG, "needDownload-onNeedDownloadFinish needStartDownload=" + b);
                                            String str = "";
                                            if (!(TbsDownloader.f12904c == null || TbsDownloader.f12904c.getApplicationContext() == null || TbsDownloader.f12904c.getApplicationContext().getApplicationInfo() == null)) {
                                                str = TbsDownloader.f12904c.getApplicationContext().getApplicationInfo().packageName;
                                            }
                                            if (!b || z2) {
                                                ((TbsDownloaderCallback) message.obj).onNeedDownloadFinish(b, TbsDownloadConfig.getInstance(TbsDownloader.f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                            } else if (TbsConfig.APP_WX.equals(str) || TbsConfig.APP_QQ.equals(str)) {
                                                TbsLog.m16531i(TbsDownloader.LOGTAG, "needDownload-onNeedDownloadFinish in mm or QQ callback needStartDownload = " + b);
                                                ((TbsDownloaderCallback) message.obj).onNeedDownloadFinish(b, TbsDownloadConfig.getInstance(TbsDownloader.f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                            }
                                        }
                                        if (TbsShareManager.isThirdPartyApp(TbsDownloader.f12904c) && b) {
                                            TbsDownloader.startDownload(TbsDownloader.f12904c);
                                            return;
                                        }
                                        return;
                                    case 101:
                                        break;
                                    case 102:
                                        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_REPORT_DOWNLOAD_STAT");
                                        int a = TbsShareManager.isThirdPartyApp(TbsDownloader.f12904c) ? TbsShareManager.m16961a(TbsDownloader.f12904c, false) : TbsInstaller.m16742a().m16682m(TbsDownloader.f12904c);
                                        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] localTbsVersion=" + a);
                                        TbsDownloader.f12908g.m16804a(a);
                                        TbsLogReport.getInstance(TbsDownloader.f12904c).dailyReport();
                                        return;
                                    case 103:
                                        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE");
                                        if (message.arg1 == 0) {
                                            TbsInstaller.m16742a().m16729a((Context) message.obj, true);
                                            return;
                                        } else {
                                            TbsInstaller.m16742a().m16729a((Context) message.obj, false);
                                            return;
                                        }
                                    case 104:
                                        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_UPLOAD_TBSLOG");
                                        TbsLogReport.getInstance(TbsDownloader.f12904c).reportTbsLog();
                                        return;
                                    default:
                                        return;
                                }
                            }
                            FileLock fileLock = null;
                            if (!TbsShareManager.isThirdPartyApp(TbsDownloader.f12904c)) {
                                fileOutputStream = FileUtil.m16445b(TbsDownloader.f12904c, false, "tbs_download_lock_file" + TbsDownloadConfig.getInstance(TbsDownloader.f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) + ".txt");
                                if (fileOutputStream != null) {
                                    fileLock = FileUtil.m16464a(TbsDownloader.f12904c, fileOutputStream);
                                    if (fileLock == null) {
                                        QbSdk.f12801m.onDownloadFinish(TbsListener.ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING);
                                        TbsLog.m16531i(TbsDownloader.LOGTAG, "file lock locked,wx or qq is downloading");
                                        TbsDownloadConfig.getInstance(TbsDownloader.f12904c).setDownloadInterruptCode(-203);
                                        TbsLog.m16531i(TbsDownloader.LOGTAG, "MSG_START_DOWNLOAD_DECOUPLECORE return #1");
                                        return;
                                    }
                                } else if (FileUtil.m16466a(TbsDownloader.f12904c)) {
                                    TbsDownloadConfig.getInstance(TbsDownloader.f12904c).setDownloadInterruptCode(-204);
                                    TbsLog.m16531i(TbsDownloader.LOGTAG, "MSG_START_DOWNLOAD_DECOUPLECORE return #2");
                                    return;
                                }
                            } else {
                                fileOutputStream = null;
                            }
                            boolean z3 = message.arg1 == 1;
                            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(TbsDownloader.f12904c);
                            if (!TbsDownloader.m17017b(false, z3, 108 == message.what, true)) {
                                QbSdk.f12801m.onDownloadFinish(110);
                            } else if (z3 && TbsInstaller.m16742a().m16719b(TbsDownloader.f12904c, TbsDownloadConfig.getInstance(TbsDownloader.f12904c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0))) {
                                QbSdk.f12801m.onDownloadFinish(TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR);
                                instance.setDownloadInterruptCode(-213);
                            } else if (instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false)) {
                                TbsDownloadConfig.getInstance(TbsDownloader.f12904c).setDownloadInterruptCode(-215);
                                TbsApkDownloader iVar = TbsDownloader.f12908g;
                                if (108 != message.what) {
                                    z = false;
                                }
                                iVar.m16784b(z3, z);
                            } else {
                                QbSdk.f12801m.onDownloadFinish(110);
                            }
                            TbsLog.m16531i(TbsDownloader.LOGTAG, "------freeFileLock called :");
                            FileUtil.m16448a(fileLock, fileOutputStream);
                        }
                    };
                } catch (Exception unused) {
                    f12910i = true;
                    TbsLog.m16533e(LOGTAG, "TbsApkDownloader init has Exception");
                }
            }
        }
    }

    /* renamed from: a */
    private static void m17024a(boolean z, TbsDownloaderCallback tbsDownloaderCallback, boolean z2) {
        TbsLog.m16531i(LOGTAG, "[TbsDownloader.queryConfig]");
        f12905d.removeMessages(100);
        Message obtain = Message.obtain(f12905d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        obtain.arg1 = z ? 1 : 0;
        obtain.arg2 = z2 ? 1 : 0;
        obtain.sendToTarget();
    }

    /* renamed from: e */
    private static boolean m17012e() {
        try {
            return TbsDownloadConfig.getInstance(f12904c).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, "").equals(m17010g().toString());
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: f */
    private static String[] m17011f() {
        if (QbSdk.getOnlyDownload()) {
            return new String[]{f12904c.getApplicationContext().getPackageName()};
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        String packageName = f12904c.getApplicationContext().getPackageName();
        if (!packageName.equals(TbsShareManager.m16953f(f12904c))) {
            return coreProviderAppList;
        }
        int length = coreProviderAppList.length;
        String[] strArr = new String[length + 1];
        System.arraycopy(coreProviderAppList, 0, strArr, 0, length);
        strArr[length] = packageName;
        return strArr;
    }

    /* renamed from: a */
    private static void m17026a(JSONArray jSONArray) {
        String[] f;
        boolean z;
        String[] f2 = m17011f();
        int length = f2.length;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= length) {
                break;
            }
            String str = f2[i];
            int sharedTbsCoreVersion = TbsShareManager.getSharedTbsCoreVersion(f12904c, str);
            if (sharedTbsCoreVersion > 0) {
                Context packageContext = TbsShareManager.getPackageContext(f12904c, str, true);
                if (packageContext == null || TbsInstaller.m16742a().m16696f(packageContext)) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (jSONArray.optInt(i2) == sharedTbsCoreVersion) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(sharedTbsCoreVersion);
                    }
                } else {
                    TbsLog.m16533e(LOGTAG, "host check failed,packageName = " + str);
                }
            }
            i++;
        }
        for (String str2 : m17011f()) {
            int coreShareDecoupleCoreVersion = TbsShareManager.getCoreShareDecoupleCoreVersion(f12904c, str2);
            if (coreShareDecoupleCoreVersion > 0) {
                Context packageContext2 = TbsShareManager.getPackageContext(f12904c, str2, true);
                if (packageContext2 == null || TbsInstaller.m16742a().m16696f(packageContext2)) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (jSONArray.optInt(i3) == coreShareDecoupleCoreVersion) {
                            z = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(coreShareDecoupleCoreVersion);
                    }
                } else {
                    TbsLog.m16533e(LOGTAG, "host check failed,packageName = " + str2);
                }
            }
        }
    }

    /* renamed from: b */
    private static void m17018b(JSONArray jSONArray) {
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            int a = TbsInstaller.m16742a().m16723a(TbsShareManager.getHostCorePathAppDefined());
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= jSONArray.length()) {
                    break;
                } else if (jSONArray.optInt(i) == a) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                jSONArray.put(a);
            }
        }
    }

    /* renamed from: g */
    private static JSONArray m17010g() {
        if (!TbsShareManager.isThirdPartyApp(f12904c)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        m17026a(jSONArray);
        m17014c(jSONArray);
        m17018b(jSONArray);
        return jSONArray;
    }

    /* renamed from: c */
    private static void m17014c(JSONArray jSONArray) {
        String[] f;
        boolean z;
        if (!TbsPVConfig.getInstance(f12904c).isDisableHostBackupCore()) {
            for (String str : m17011f()) {
                int backupCoreVersion = TbsShareManager.getBackupCoreVersion(f12904c, str);
                boolean z2 = true;
                if (backupCoreVersion > 0) {
                    Context packageContext = TbsShareManager.getPackageContext(f12904c, str, false);
                    if (packageContext == null || TbsInstaller.m16742a().m16696f(packageContext)) {
                        int i = 0;
                        while (true) {
                            if (i >= jSONArray.length()) {
                                z = false;
                                break;
                            } else if (jSONArray.optInt(i) == backupCoreVersion) {
                                z = true;
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (!z) {
                            jSONArray.put(backupCoreVersion);
                        }
                    } else {
                        TbsLog.m16533e(LOGTAG, "host check failed,packageName = " + str);
                    }
                }
                int backupDecoupleCoreVersion = TbsShareManager.getBackupDecoupleCoreVersion(f12904c, str);
                if (backupDecoupleCoreVersion > 0) {
                    Context packageContext2 = TbsShareManager.getPackageContext(f12904c, str, false);
                    if (packageContext2 == null || TbsInstaller.m16742a().m16696f(packageContext2)) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= jSONArray.length()) {
                                z2 = false;
                                break;
                            } else if (jSONArray.optInt(i2) == backupDecoupleCoreVersion) {
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z2) {
                            jSONArray.put(backupDecoupleCoreVersion);
                        }
                    } else {
                        TbsLog.m16533e(LOGTAG, "host check failed,packageName = " + str);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x014f, code lost:
        r7.put("FUNCTION", 2);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.json.JSONObject m17023a(boolean r13, boolean r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 715
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.m17023a(boolean, boolean, boolean):org.json.JSONObject");
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!f12912k) {
                f12912k = true;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
                if (instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                    f12911j = instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.m16531i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + f12911j);
                }
                TbsLog.m16531i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + f12911j);
            }
            z = f12911j;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(29:27|(1:29)(1:30)|31|(1:33)(1:34)|35|(1:37)(1:38)|39|(1:41)(1:42)|43|(2:45|(1:47)(2:48|(1:50)(2:51|(1:53))))|54|(1:56)|57|(4:59|113|60|(8:64|(3:66|(1:68)(1:69)|70)(1:(1:72)(1:73))|74|76|117|77|(4:81|(3:83|(1:85)|86)|87|(2:(1:90)(1:(1:92))|93))|(5:115|100|(1:102)(1:(1:104))|105|123)(1:(2:97|119)(1:(2:99|121)(1:120)))))|75|76|117|77|(0)|81|(0)|87|(0)|(0)|115|100|(0)(0)|105|123) */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0237, code lost:
        r8 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0341 A[Catch: Throwable -> 0x03b2, TryCatch #1 {Throwable -> 0x03b2, blocks: (B:100:0x031f, B:102:0x0341, B:104:0x036d, B:105:0x0396), top: B:115:0x031f }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02b4  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m17017b(final boolean r20, boolean r21, boolean r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 964
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.m17017b(boolean, boolean, boolean, boolean):boolean");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(24:16|(2:301|17)|19|(1:35)(6:299|25|26|(1:28)(1:29)|295|30)|(10:314|37|(1:39)(1:40)|41|(1:43)(1:44)|45|(1:47)(1:48)|49|(1:51)(1:52)|53)|56|289|57|293|58|285|59|287|60|(3:62|(1:64)(1:65)|66)(1:67)|(9:305|68|(1:70)|71|(3:73|(1:75)(1:76)|77)(1:78)|303|79|(2:81|(1:83)(1:84))(1:85)|86)|297|95|97|(2:307|98)|100|29e|111|(5:(1:114)(1:115)|116|(1:118)(1:119)|120|121)(19:(1:123)|(1:125)(1:126)|(1:128)|(1:130)|131|(3:133|(1:135)(1:136)|137)(1:139)|140|141|(1:143)|144|311|(1:146)(2:147|148)|291|150|(1:156)|157|(1:(1:(2:309|166))(1:164))|168|(2:177|(6:179|(1:181)(1:182)|183|(1:185)|186|187)(5:188|(1:190)|191|(3:193|(1:195)(1:196)|197)(1:198)|(4:205|(2:207|(1:209)(1:(1:211)(1:(1:213)(1:214))))(3:215|(1:217)(1:(1:219)(1:(1:221)))|222)|223|224)(11:225|(1:227)|228|(3:230|(1:232)(1:233)|234)|235|(1:237)(1:238)|(4:247|(4:250|(1:255)(1:254)|256|(3:258|(1:260)(1:261)|262))|263|(2:(1:273)|274)(3:267|(1:269)(1:270)|271))(2:(1:244)(1:245)|246)|275|(1:277)|278|279)))(4:172|(1:174)|175|176))) */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x034a, code lost:
        if (r0 > r7) goto L_0x0350;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0291, code lost:
        r13 = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x012a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x020f A[Catch: Exception -> 0x026b, TRY_LEAVE, TryCatch #1 {Exception -> 0x026b, blocks: (B:60:0x0207, B:62:0x020f), top: B:287:0x0207 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0228 A[Catch: Exception -> 0x027d, TryCatch #10 {Exception -> 0x027d, blocks: (B:68:0x0220, B:70:0x0228, B:71:0x0230, B:73:0x0238), top: B:305:0x0220 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0238 A[Catch: Exception -> 0x027d, TRY_LEAVE, TryCatch #10 {Exception -> 0x027d, blocks: (B:68:0x0220, B:70:0x0228, B:71:0x0230, B:73:0x0238), top: B:305:0x0220 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0250 A[Catch: Exception -> 0x027f, TRY_LEAVE, TryCatch #9 {Exception -> 0x027f, blocks: (B:79:0x0248, B:81:0x0250), top: B:303:0x0248 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x025c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:149:0x0382 -> B:291:0x0383). Please submit an issue!!! */
    @android.annotation.TargetApi(11)
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m17027a(java.lang.String r30, int r31, boolean r32, boolean r33, boolean r34) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 2050
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.m17027a(java.lang.String, int, boolean, boolean, boolean):boolean");
    }

    public static void setRetryIntervalInSeconds(Context context, long j) {
        if (context != null) {
            if (context.getApplicationInfo().packageName.equals("com.tencent.qqlive")) {
                f12913l = j;
            }
            TbsLog.m16531i(LOGTAG, "mRetryIntervalInSeconds is " + f12913l);
        }
    }

    public static long getRetryIntervalInSeconds() {
        return f12913l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m17019b(Context context) {
        if (!TextUtils.isEmpty(f12903b)) {
            return f12903b;
        }
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str = Build.VERSION.RELEASE;
        try {
            str = new String(str.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception unused) {
        }
        if (str == null) {
            stringBuffer.append("1.0");
        } else if (str.length() > 0) {
            stringBuffer.append(str);
        } else {
            stringBuffer.append("1.0");
        }
        stringBuffer.append("; ");
        String language = locale.getLanguage();
        if (language != null) {
            stringBuffer.append(language.toLowerCase());
            String country = locale.getCountry();
            if (country != null) {
                stringBuffer.append("-");
                stringBuffer.append(country.toLowerCase());
            }
        } else {
            stringBuffer.append("en");
        }
        if ("REL".equals(Build.VERSION.CODENAME)) {
            String str2 = Build.MODEL;
            try {
                str2 = new String(str2.getBytes("UTF-8"), "ISO8859-1");
            } catch (Exception unused2) {
            }
            if (str2 == null) {
                stringBuffer.append("; ");
            } else if (str2.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str2);
            }
        }
        String replaceAll = (Build.ID == null ? "" : Build.ID).replaceAll("[一-龥]", "");
        if (replaceAll == null) {
            stringBuffer.append(" Build/");
            stringBuffer.append(TarConstants.VERSION_POSIX);
        } else if (replaceAll.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(replaceAll);
        }
        String format = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", stringBuffer);
        f12903b = format;
        return format;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(11)
    /* renamed from: c */
    public static void m17015c(Context context) {
        SharedPreferences sharedPreferences;
        SharedPreferences sharedPreferences2;
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.getInstance(context).clear();
        TbsApkDownloader.m16782c(context);
        if (Build.VERSION.SDK_INT >= 11) {
            sharedPreferences = context.getSharedPreferences("tbs_extension_config", 4);
        } else {
            sharedPreferences = context.getSharedPreferences("tbs_extension_config", 0);
        }
        sharedPreferences.edit().clear().commit();
        if (Build.VERSION.SDK_INT >= 11) {
            sharedPreferences2 = context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4);
        } else {
            sharedPreferences2 = context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0);
        }
        sharedPreferences2.edit().clear().commit();
    }

    /* renamed from: h */
    private static boolean m17009h() {
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(f12904c);
        if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= instance.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.m16530i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            instance.setDownloadInterruptCode(-115);
            return false;
        } else if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= instance.getDownloadFailedMaxRetrytimes()) {
            TbsLog.m16530i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            instance.setDownloadInterruptCode(PackageHelper.INSTALL_FAILED_EPHEMERAL_INVALID);
            return false;
        } else if (!FileUtil.m16447b(f12904c)) {
            TbsLog.m16530i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            instance.setDownloadInterruptCode(-117);
            return false;
        } else {
            if (System.currentTimeMillis() - instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0L) <= WaitFor.ONE_DAY) {
                long j = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0L);
                TbsLog.m16531i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j);
                if (j >= instance.getDownloadMaxflow()) {
                    TbsLog.m16530i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    instance.setDownloadInterruptCode(-120);
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static File m17032a(int i) {
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str = coreProviderAppList[i2];
            if (!str.equals(f12904c.getApplicationInfo().packageName)) {
                file = new File(FileUtil.m16462a(f12904c, str, 4, false), getOverSea(f12904c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                if (!file.exists()) {
                    TbsLog.m16531i(LOGTAG, "can not find local backup core file");
                } else if (ApkUtil.m16519a(f12904c, file) == i) {
                    TbsLog.m16531i(LOGTAG, "local tbs version fond,path = " + file.getAbsolutePath());
                    break;
                } else {
                    TbsLog.m16531i(LOGTAG, "version is not match");
                }
            }
            i2++;
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static File m17020b(int i) {
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i2 = 0;
        while (i2 < length) {
            String str = coreProviderAppList[i2];
            File file2 = new File(FileUtil.m16462a(f12904c, str, 4, false), getOverSea(f12904c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (!file2.exists() || ApkUtil.m16519a(f12904c, file2) != i) {
                File file3 = new File(FileUtil.m16462a(f12904c, str, 4, false), "x5.tbs.decouple");
                if (!file3.exists() || ApkUtil.m16519a(f12904c, file3) != i) {
                    i2++;
                    file = file3;
                } else {
                    TbsLog.m16531i(LOGTAG, "local tbs version fond,path = " + file3.getAbsolutePath());
                    return file3;
                }
            } else {
                TbsLog.m16531i(LOGTAG, "local tbs version fond,path = " + file2.getAbsolutePath());
                return file2;
            }
        }
        return file;
    }

    /* renamed from: a */
    private static JSONArray m17025a(boolean z) {
        String[] coreProviderAppList;
        File file;
        boolean z2;
        JSONArray jSONArray = new JSONArray();
        for (String str : TbsShareManager.getCoreProviderAppList()) {
            if (z) {
                file = new File(FileUtil.m16462a(f12904c, str, 4, false), getOverSea(f12904c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            } else {
                file = new File(FileUtil.m16462a(f12904c, str, 4, false), "x5.tbs.decouple");
            }
            if (file.exists()) {
                long a = ApkUtil.m16519a(f12904c, file);
                if (a > 0) {
                    int i = 0;
                    while (true) {
                        if (i >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (jSONArray.optInt(i) == a) {
                            z2 = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(a);
                    }
                }
            }
        }
        return jSONArray;
    }
}
