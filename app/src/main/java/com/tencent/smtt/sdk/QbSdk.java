package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.Process;
import android.support.v4.provider.FontsContractCompat;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.p078a.MttLoader;
import com.tencent.smtt.sdk.p079b.p080a.TBSActivityPicker;
import com.tencent.smtt.sdk.p079b.p080a.TBSResources;
import com.tencent.smtt.utils.FileProvider;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.ReflectionUtils;
import com.tencent.smtt.utils.TbsCheckUtils;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import p110z1.C3894au;
import p110z1.Consts;

@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public class QbSdk {
    public static final int EXTENSION_INIT_FAILURE = -99999;
    public static final String FILERADER_MENUDATA = "menuData";
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int QBMODE = 2;
    public static final String SVNVERSION = "jnizz";
    public static final int TBSMODE = 1;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;

    /* renamed from: b */
    static boolean f12790b = false;

    /* renamed from: c */
    static boolean f12791c = true;

    /* renamed from: d */
    static String f12792d = null;

    /* renamed from: e */
    static boolean f12793e = false;

    /* renamed from: f */
    static long f12794f = 0;

    /* renamed from: g */
    static long f12795g = 0;

    /* renamed from: o */
    private static int f12803o = 0;

    /* renamed from: p */
    private static String f12804p = "";

    /* renamed from: q */
    private static Class<?> f12805q = null;

    /* renamed from: r */
    private static Object f12806r = null;

    /* renamed from: s */
    private static boolean f12807s = false;
    public static boolean sIsVersionPrinted = false;

    /* renamed from: t */
    private static String[] f12808t = null;

    /* renamed from: u */
    private static String f12809u = "NULL";

    /* renamed from: v */
    private static String f12810v = "UNKNOWN";

    /* renamed from: h */
    static Object f12796h = new Object();
    public static boolean isDefaultDialog = false;

    /* renamed from: w */
    private static boolean f12811w = false;

    /* renamed from: i */
    static boolean f12797i = true;

    /* renamed from: j */
    static boolean f12798j = true;

    /* renamed from: k */
    static boolean f12799k = false;

    /* renamed from: x */
    private static int f12812x = 0;

    /* renamed from: y */
    private static int f12813y = TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE;

    /* renamed from: z */
    private static String f12814z = null;

    /* renamed from: A */
    private static String f12782A = null;

    /* renamed from: a */
    static boolean f12789a = false;

    /* renamed from: l */
    static volatile boolean f12800l = f12789a;
    public static boolean mDisableUseHostBackupCore = false;

    /* renamed from: B */
    private static boolean f12783B = false;

    /* renamed from: C */
    private static boolean f12784C = true;

    /* renamed from: D */
    private static TbsListener f12785D = null;

    /* renamed from: E */
    private static TbsListener f12786E = null;

    /* renamed from: F */
    private static boolean f12787F = false;

    /* renamed from: G */
    private static boolean f12788G = false;

    /* renamed from: m */
    static TbsListener f12801m = new TbsListener() { // from class: com.tencent.smtt.sdk.QbSdk.7
        @Override // com.tencent.smtt.sdk.TbsListener
        public void onDownloadFinish(int i) {
            if (TbsDownloader.needDownloadDecoupleCore()) {
                TbsLog.m16530i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is true", true);
                TbsDownloader.f12902a = true;
                return;
            }
            TbsLog.m16530i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is false", true);
            TbsDownloader.f12902a = false;
            if (QbSdk.f12785D != null) {
                QbSdk.f12785D.onDownloadFinish(i);
            }
            if (QbSdk.f12786E != null) {
                QbSdk.f12786E.onDownloadFinish(i);
            }
        }

        @Override // com.tencent.smtt.sdk.TbsListener
        public void onInstallFinish(int i) {
            if (i != 200) {
            }
            QbSdk.setTBSInstallingStatus(false);
            TbsDownloader.f12902a = false;
            if (TbsDownloader.startDecoupleCoreIfNeeded()) {
                TbsDownloader.f12902a = true;
            } else {
                TbsDownloader.f12902a = false;
            }
            if (QbSdk.f12785D != null) {
                QbSdk.f12785D.onInstallFinish(i);
            }
            if (QbSdk.f12786E != null) {
                QbSdk.f12786E.onInstallFinish(i);
            }
        }

        @Override // com.tencent.smtt.sdk.TbsListener
        public void onDownloadProgress(int i) {
            if (QbSdk.f12786E != null) {
                QbSdk.f12786E.onDownloadProgress(i);
            }
            if (QbSdk.f12785D != null) {
                QbSdk.f12785D.onDownloadProgress(i);
            }
        }
    };
    public static String KEY_SET_SENDREQUEST_AND_UPLOAD = "SET_SENDREQUEST_AND_UPLOAD";

    /* renamed from: n */
    static Map<String, Object> f12802n = null;

    /* loaded from: classes2.dex */
    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    public static void clear(Context context) {
    }

    public static int getTbsSdkVersion() {
        return 43697;
    }

    public static boolean startQBToLoadurl(Context context, String str, int i, WebView webView) {
        X5CoreEngine a;
        Object invokeStaticMethod;
        IX5WebViewBase iX5WebViewBase;
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if (!((str2 != TbsConfig.APP_WX && str2 != TbsConfig.APP_QQ) || (a = X5CoreEngine.m16621a()) == null || !a.m16618b() || (invokeStaticMethod = a.m16616c().m16598b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0])) == null || (iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod) == null)) {
                    webView = (WebView) iX5WebViewBase.getView().getParent();
                }
            } catch (Exception unused) {
            }
        }
        return MttLoader.m16876a(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQBForVideo(Context context, String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        return MttLoader.m16878a(context, str, hashMap);
    }

    public static boolean startQBForDoc(Context context, String str, int i, int i2, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        return MttLoader.m16879a(context, str, i2, str2, hashMap, bundle);
    }

    public static boolean getIsSysWebViewForcedByOuter() {
        return f12790b;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private static boolean m17052a(Context context, boolean z) {
        File file;
        String str;
        int i;
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.m16531i("QbSdk", "svn revision: jnizz; SDK_VERSION_CODE: 43697; SDK_VERSION_NAME: 4.3.0.1148");
            sIsVersionPrinted = true;
        }
        if (f12789a && !z) {
            TbsLog.m16532e("QbSdk", "QbSdk init: " + f12810v, false);
            TbsCoreLoadStat.getInstance().m17035a(context, 414, new Throwable(f12810v));
            return false;
        } else if (f12790b) {
            TbsLog.m16532e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().m17035a(context, 402, new Throwable(f12809u));
            return false;
        } else {
            if (!f12784C) {
                m17044d(context);
            }
            try {
                File q = TbsInstaller.m16742a().m16678q(context);
                if (q == null) {
                    TbsLog.m16533e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().m17035a(context, 312, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    if (f12803o != 0) {
                        i = TbsInstaller.m16742a().m16722a(true, context);
                        if (f12803o != i) {
                            f12805q = null;
                            f12806r = null;
                            TbsLog.m16532e("QbSdk", "QbSdk init (false) not isThirdPartyApp tbsCoreInstalledVer=" + i, true);
                            TbsLog.m16532e("QbSdk", "QbSdk init (false) not isThirdPartyApp sTbsVersion=" + f12803o, true);
                            TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
                            instance.m17035a(context, 303, new Throwable("sTbsVersion: " + f12803o + "; tbsCoreInstalledVer: " + i));
                            return false;
                        }
                    } else {
                        i = 0;
                    }
                    f12803o = i;
                } else if (f12803o == 0 || f12803o == TbsShareManager.m16956d(context)) {
                    f12803o = TbsShareManager.m16956d(context);
                } else {
                    f12805q = null;
                    f12806r = null;
                    TbsLog.m16533e("QbSdk", "QbSdk init (false) ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY!");
                    TbsCoreLoadStat instance2 = TbsCoreLoadStat.getInstance();
                    instance2.m17035a(context, 302, new Throwable("sTbsVersion: " + f12803o + "; AvailableTbsCoreVersion: " + TbsShareManager.m16956d(context)));
                    return false;
                }
                if (f12805q != null && f12806r != null) {
                    return true;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    file = new File(TbsInstaller.m16742a().m16678q(context), "tbs_sdk_extension_dex.jar");
                } else if (TbsShareManager.m16949j(context)) {
                    file = new File(TbsShareManager.m16958c(context), "tbs_sdk_extension_dex.jar");
                } else {
                    TbsCoreLoadStat.getInstance().m17035a(context, 304, new Throwable("isShareTbsCoreAvailable false!"));
                    return false;
                }
                if (!file.exists()) {
                    TbsLog.m16533e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                    int i2 = TbsInstaller.m16742a().m16688i(context);
                    if (new File(file.getParentFile(), "tbs_jars_fusion_dex.jar").exists()) {
                        if (i2 > 0) {
                            TbsCoreLoadStat instance3 = TbsCoreLoadStat.getInstance();
                            instance3.m17035a(context, TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i2));
                        } else {
                            TbsCoreLoadStat instance4 = TbsCoreLoadStat.getInstance();
                            instance4.m17035a(context, TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + i2));
                        }
                    } else if (i2 > 0) {
                        TbsCoreLoadStat instance5 = TbsCoreLoadStat.getInstance();
                        instance5.m17035a(context, TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i2));
                    } else {
                        TbsCoreLoadStat instance6 = TbsCoreLoadStat.getInstance();
                        instance6.m17035a(context, TbsListener.ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + i2));
                    }
                    return false;
                }
                if (TbsShareManager.getHostCorePathAppDefined() != null) {
                    str = TbsShareManager.getHostCorePathAppDefined();
                } else {
                    str = q.getAbsolutePath();
                }
                TbsLog.m16531i("QbSdk", "QbSdk init optDirExtension #1 is " + str);
                TbsLog.m16531i("QbSdk", "new DexLoader #1 dexFile is " + file.getAbsolutePath());
                X5CoreEngine.m16621a().m16617b(context);
                TbsCheckUtils.m16393a(context);
                f12805q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, str, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                loadTBSSDKExtension(context, file.getParent());
                ReflectionUtils.m16404a(f12806r, "setClientVersion", new Class[]{Integer.TYPE}, 1);
                return true;
            } catch (Throwable th) {
                TbsLog.m16533e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().m17035a(context, 306, th);
                return false;
            }
        }
    }

    public static void loadTBSSDKExtension(Context context, String str) {
        Constructor<?> constructor;
        boolean z;
        if (f12806r == null) {
            synchronized (QbSdk.class) {
                if (f12806r == null) {
                    if (f12805q == null) {
                        TbsLog.m16531i("QbSdk", "QbSdk loadTBSSDKExtension sExtensionClass is null");
                    }
                    try {
                        constructor = f12805q.getConstructor(Context.class, Context.class, String.class, String.class, String.class);
                        z = true;
                    } catch (Throwable unused) {
                        constructor = null;
                        z = false;
                    }
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        Context e = TbsShareManager.m16954e(context);
                        if (e == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                            TbsLogReport.getInstance(context.getApplicationContext()).setLoadErrorCode(TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                        } else if (z) {
                            f12806r = constructor.newInstance(context, e, TbsShareManager.getHostCorePathAppDefined(), str, null);
                        } else if (e == null) {
                            f12806r = f12805q.getConstructor(Context.class, Context.class, String.class).newInstance(context, e, TbsShareManager.getHostCorePathAppDefined(), str, null);
                        } else {
                            f12806r = f12805q.getConstructor(Context.class, Context.class).newInstance(context, e);
                        }
                    } else if (!z) {
                        Constructor<?> constructor2 = f12805q.getConstructor(Context.class, Context.class);
                        if (context.getApplicationContext() != null) {
                            context = context.getApplicationContext();
                        }
                        f12806r = constructor2.newInstance(context, context);
                    } else {
                        String str2 = (!TbsConfig.APP_WX.equals(getCurrentProcessName(context)) || WebView.mWebViewCreated) ? null : "notLoadSo";
                        if (context.getApplicationContext() != null) {
                            context = context.getApplicationContext();
                        }
                        f12806r = constructor.newInstance(context, context, null, str, str2);
                    }
                }
            }
        }
    }

    public static void initForinitAndNotLoadSo(Context context) {
        if (f12805q == null) {
            File q = TbsInstaller.m16742a().m16678q(context);
            if (q == null) {
                Log.e("QbSdk", "QbSdk initForinitAndNotLoadSo optDir == null");
                return;
            }
            File file = new File(q, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                Log.e("QbSdk", "QbSdk initForinitAndNotLoadSo dexFile.exists()=false");
                return;
            }
            String absolutePath = q.getAbsolutePath();
            X5CoreEngine.m16621a().m16617b(context);
            TbsCheckUtils.m16393a(context);
            f12805q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, absolutePath, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
        }
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        String str;
        try {
            if (context.getApplicationInfo().packageName.contains("com.moji.mjweather") && Build.VERSION.SDK_INT == 19) {
                return true;
            }
            if (f12805q == null) {
                File q = TbsInstaller.m16742a().m16678q(context);
                if (q == null) {
                    TbsLog.m16533e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                File file = new File(TbsShareManager.m16958c(context), "tbs_sdk_extension_dex.jar");
                if (!file.exists()) {
                    TbsLog.m16532e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
                if (TbsShareManager.getHostCorePathAppDefined() != null) {
                    str = TbsShareManager.getHostCorePathAppDefined();
                } else {
                    str = q.getAbsolutePath();
                }
                TbsLog.m16531i("QbSdk", "QbSdk init optDirExtension #2 is " + str);
                TbsLog.m16531i("QbSdk", "new DexLoader #2 dexFile is " + file.getAbsolutePath());
                X5CoreEngine.m16621a().m16617b(context);
                TbsCheckUtils.m16393a(context);
                f12805q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, str, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                if (f12806r == null) {
                    if (TbsShareManager.m16954e(context) == null && TbsShareManager.getHostCorePathAppDefined() == null) {
                        TbsLogReport.getInstance(context.getApplicationContext()).setLoadErrorCode(TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, "host context is null!");
                        return false;
                    }
                    loadTBSSDKExtension(context, file.getParent());
                }
            }
            Object a = ReflectionUtils.m16404a(f12806r, "canLoadX5CoreForThirdApp", new Class[0], new Object[0]);
            if (a == null || !(a instanceof Boolean)) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        } catch (Throwable th) {
            TbsLog.m16533e("QbSdk", "canLoadX5FirstTimeThirdApp sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    /* renamed from: a */
    static boolean m17060a(Context context) {
        try {
            if (f12805q != null) {
                return true;
            }
            File q = TbsInstaller.m16742a().m16678q(context);
            if (q == null) {
                TbsLog.m16533e("QbSdk", "QbSdk initExtension (false) optDir == null");
                return false;
            }
            File file = new File(q, "tbs_sdk_extension_dex.jar");
            if (!file.exists()) {
                TbsLog.m16532e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
                return false;
            }
            TbsLog.m16531i("QbSdk", "new DexLoader #3 dexFile is " + file.getAbsolutePath());
            X5CoreEngine.m16621a().m16617b(context);
            TbsCheckUtils.m16393a(context);
            f12805q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, q.getAbsolutePath(), getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            loadTBSSDKExtension(context, file.getParent());
            return true;
        } catch (Throwable th) {
            TbsLog.m16533e("QbSdk", "initExtension sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m17046c(Context context) {
        File file;
        String str;
        try {
            if (f12805q != null) {
                return true;
            }
            File q = TbsInstaller.m16742a().m16678q(context);
            if (q == null) {
                TbsLog.m16533e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(TbsInstaller.m16742a().m16678q(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.m16949j(context)) {
                file = new File(TbsShareManager.m16958c(context), "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().m17036a(context, 304);
                return false;
            }
            if (!file.exists()) {
                TbsCoreLoadStat.getInstance().m17035a(context, 406, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
                return false;
            }
            if (TbsShareManager.getHostCorePathAppDefined() != null) {
                str = TbsShareManager.getHostCorePathAppDefined();
            } else {
                str = q.getAbsolutePath();
            }
            TbsLog.m16531i("QbSdk", "QbSdk init optDirExtension #3 is " + str);
            TbsLog.m16531i("QbSdk", "new DexLoader #4 dexFile is " + file.getAbsolutePath());
            X5CoreEngine.m16621a().m16617b(context);
            TbsCheckUtils.m16393a(context);
            f12805q = new DexLoader(file.getParent(), context, new String[]{file.getAbsolutePath()}, str, getSettings()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
            loadTBSSDKExtension(context, file.getParent());
            ReflectionUtils.m16404a(f12806r, "setClientVersion", new Class[]{Integer.TYPE}, 1);
            return true;
        } catch (Throwable th) {
            TbsLog.m16533e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static void setOnlyDownload(boolean z) {
        f12799k = z;
    }

    public static boolean getOnlyDownload() {
        return f12799k;
    }

    /* renamed from: b */
    static boolean m17048b(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null) {
            return false;
        }
        try {
            if (!context.getApplicationInfo().packageName.contains("com.tencent.portfolio")) {
                return true;
            }
            TbsLog.m16531i("QbSdk", "clearPluginConfigFile #1");
            String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null);
            String str = context.getPackageManager().getPackageInfo("com.tencent.portfolio", 0).versionName;
            TbsLog.m16531i("QbSdk", "clearPluginConfigFile oldAppVersionName is " + string + " newAppVersionName is " + str);
            if (string == null || string.contains(str) || (sharedPreferences = context.getSharedPreferences("plugin_setting", 0)) == null) {
                return true;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.commit();
            TbsLog.m16531i("QbSdk", "clearPluginConfigFile done");
            return true;
        } catch (Throwable th) {
            TbsLog.m16531i("QbSdk", "clearPluginConfigFile error is " + th.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Bundle m17057a(Context context, Bundle bundle) throws Exception {
        if (!m17060a(context)) {
            TbsLogReport.getInstance(context).setInstallErrorCode(216, "initForPatch return false!");
            return null;
        }
        Object a = ReflectionUtils.m16404a(f12806r, "incrUpdate", new Class[]{Context.class, Bundle.class}, context, bundle);
        if (a != null) {
            return (Bundle) a;
        }
        TbsLogReport.getInstance(context).setInstallErrorCode(216, "incrUpdate return null!");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17059a(Context context, int i) {
        return m17058a(context, i, (int) C3894au.f17525h);
    }

    /* renamed from: a */
    static boolean m17058a(Context context, int i, int i2) {
        Map<String, Object> map = f12802n;
        if (map == null || !map.containsKey(KEY_SET_SENDREQUEST_AND_UPLOAD) || !f12802n.get(KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            TbsInstaller.m16742a().m16712b(context, SDKEngine.f13137a == 0);
            if (!m17046c(context)) {
                return true;
            }
            Object a = ReflectionUtils.m16404a(f12806r, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i), 43697, Integer.valueOf(i2));
            if (a != null) {
                return ((Boolean) a).booleanValue();
            }
            Object a2 = ReflectionUtils.m16404a(f12806r, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(i), 43697);
            if (a2 != null) {
                return ((Boolean) a2).booleanValue();
            }
            return true;
        }
        TbsLog.m16531i("QbSdk", "[QbSdk.isX5Disabled] -- SET_SENDREQUEST_AND_UPLOAD is false");
        return true;
    }

    public static boolean canLoadX5(Context context) {
        return m17051a(context, false, false);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:38:0x00e8 in {1, 4, 10, 16, 18, 24, 26, 31, 36, 37, 39, 44, 45, 47, 49, 50, 52, 53, 55, 58, 61, 62, 64, 65, 66} preds:[]
        	at jadx.core.dex.visitors.blocks.BlockProcessor.calcImmediateDominators(BlockProcessor.java:279)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:231)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:50)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public static boolean canOpenWebPlus(android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.canOpenWebPlus(android.content.Context):boolean");
    }

    public static boolean isX5DisabledSync(Context context) {
        if (TbsCoreVerManager.m16764a(context).m16756c() == 2) {
            return false;
        }
        if (!m17046c(context)) {
            return true;
        }
        Object a = ReflectionUtils.m16404a(f12806r, "isX5DisabledSync", new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(TbsInstaller.m16742a().m16688i(context)), 43697);
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        SDKEngine a = SDKEngine.m16828a(true);
        a.m16830a(context, false, false);
        if (a == null || !a.m16827b()) {
            return false;
        }
        return a.m16832a().m16630a(context, str, str2, bundle);
    }

    public static boolean canUseVideoFeatrue(Context context, int i) {
        Object a = ReflectionUtils.m16404a(f12806r, "canUseVideoFeatrue", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        if (a == null || !(a instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    public static boolean canLoadVideo(Context context) {
        Object a = ReflectionUtils.m16404a(f12806r, "canLoadVideo", new Class[]{Integer.TYPE}, 0);
        if (a == null) {
            TbsCoreLoadStat.getInstance().m17036a(context, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL);
        } else if (!((Boolean) a).booleanValue()) {
            TbsCoreLoadStat.getInstance().m17036a(context, 313);
        }
        if (a == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0158 -> B:118:0x015c). Please submit an issue!!! */
    /* renamed from: a */
    public static boolean m17051a(Context context, boolean z, boolean z2) {
        int disabledCoreVersion = TbsPVConfig.getInstance(context).getDisabledCoreVersion();
        boolean z3 = false;
        if (disabledCoreVersion != 0 && disabledCoreVersion == TbsInstaller.m16742a().m16688i(context)) {
            TbsLog.m16533e("QbSdk", "force use sys by remote switch");
            return false;
        } else if (TbsShareManager.isThirdPartyApp(context) && !TbsShareManager.m16950i(context)) {
            TbsCoreLoadStat.getInstance().m17036a(context, 302);
            return false;
        } else if (!m17052a(context, z)) {
            TbsLog.m16533e("QbSdk", "QbSdk.init failure!");
            return false;
        } else {
            boolean z4 = true;
            Object a = ReflectionUtils.m16404a(f12806r, "canLoadX5Core", new Class[]{Integer.TYPE}, 43697);
            if (a == null) {
                Object a2 = ReflectionUtils.m16404a(f12806r, "canLoadX5", new Class[]{Integer.TYPE}, Integer.valueOf(DeviceInfo.m16904a()));
                if (a2 == null) {
                    TbsCoreLoadStat.getInstance().m17036a(context, 308);
                } else if ((a2 instanceof String) && ((String) a2).equalsIgnoreCase("AuthenticationFail")) {
                    return false;
                } else {
                    if (a2 instanceof Boolean) {
                        f12803o = SDKEngine.m16823d();
                        boolean a3 = m17059a(context, SDKEngine.m16823d());
                        Boolean bool = (Boolean) a2;
                        if (bool.booleanValue() && !a3) {
                            z3 = true;
                        }
                        if (!z3) {
                            TbsLog.m16533e(TbsListener.tag_load_error, "318");
                            TbsLog.m16527w(TbsListener.tag_load_error, "isX5Disable:" + a3);
                            TbsLog.m16527w(TbsListener.tag_load_error, "(Boolean) ret:" + bool);
                        }
                        return z3;
                    }
                }
            } else if ((a instanceof String) && ((String) a).equalsIgnoreCase("AuthenticationFail")) {
                return false;
            } else {
                if (!(a instanceof Bundle)) {
                    TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
                    instance.m17035a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, new Throwable("" + a));
                    TbsLog.m16533e(TbsListener.tag_load_error, "ret not instance of bundle");
                    return false;
                }
                Bundle bundle = (Bundle) a;
                if (bundle.isEmpty()) {
                    TbsCoreLoadStat instance2 = TbsCoreLoadStat.getInstance();
                    instance2.m17035a(context, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, new Throwable("" + a));
                    TbsLog.m16533e(TbsListener.tag_load_error, "empty bundle");
                    return false;
                }
                int i = -1;
                try {
                    i = bundle.getInt(FontsContractCompat.Columns.RESULT_CODE, -1);
                } catch (Exception e) {
                    TbsLog.m16533e("QbSdk", "bundle.getInt(KEY_RESULT_CODE) error : " + e.toString());
                }
                z3 = i == 0;
                if (TbsShareManager.isThirdPartyApp(context)) {
                    SDKEngine.m16831a(TbsShareManager.m16956d(context));
                    f12804p = String.valueOf(TbsShareManager.m16956d(context));
                    if (f12804p.length() == 5) {
                        f12804p = ResultTypeConstant.f7213z + f12804p;
                    }
                    if (f12804p.length() != 6) {
                        f12804p = "";
                    }
                } else {
                    try {
                        if (Build.VERSION.SDK_INT >= 12) {
                            f12804p = bundle.getString("tbs_core_version", ResultTypeConstant.f7213z);
                        } else {
                            f12804p = bundle.getString("tbs_core_version");
                            if (f12804p == null) {
                                f12804p = ResultTypeConstant.f7213z;
                            }
                        }
                    } catch (Exception unused) {
                        f12804p = ResultTypeConstant.f7213z;
                    }
                    try {
                        f12803o = Integer.parseInt(f12804p);
                    } catch (NumberFormatException unused2) {
                        f12803o = 0;
                    }
                    SDKEngine.m16831a(f12803o);
                    int i2 = f12803o;
                    if (i2 == 0) {
                        TbsCoreLoadStat.getInstance().m17035a(context, 307, new Throwable("sTbsVersion is 0"));
                        return false;
                    }
                    if ((i2 <= 0 || i2 > 25442) && f12803o != 25472) {
                        z4 = false;
                    }
                    if (z4) {
                        TbsLog.m16533e(TbsDownloader.LOGTAG, "is_obsolete --> delete old core:" + f12803o);
                        FileUtil.m16444b(TbsInstaller.m16742a().m16678q(context));
                        TbsCoreLoadStat instance3 = TbsCoreLoadStat.getInstance();
                        instance3.m17035a(context, 307, new Throwable("is_obsolete --> delete old core:" + f12803o));
                        return false;
                    }
                }
                try {
                    f12808t = bundle.getStringArray("tbs_jarfiles");
                    if (!(f12808t instanceof String[])) {
                        TbsCoreLoadStat instance4 = TbsCoreLoadStat.getInstance();
                        instance4.m17035a(context, 307, new Throwable("sJarFiles not instanceof String[]: " + f12808t));
                        return false;
                    }
                    try {
                        f12792d = bundle.getString("tbs_librarypath");
                        Object obj = null;
                        if (i != 0) {
                            try {
                                obj = ReflectionUtils.m16404a(f12806r, "getErrorCodeForLogReport", new Class[0], new Object[0]);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        switch (i) {
                            case -2:
                                if (!(obj instanceof Integer)) {
                                    TbsCoreLoadStat instance5 = TbsCoreLoadStat.getInstance();
                                    instance5.m17035a(context, 404, new Throwable("detail: " + obj));
                                    break;
                                } else {
                                    TbsCoreLoadStat instance6 = TbsCoreLoadStat.getInstance();
                                    int intValue = ((Integer) obj).intValue();
                                    instance6.m17035a(context, intValue, new Throwable("detail: " + obj));
                                    break;
                                }
                            case -1:
                                if (!(obj instanceof Integer)) {
                                    TbsCoreLoadStat instance7 = TbsCoreLoadStat.getInstance();
                                    instance7.m17035a(context, 307, new Throwable("detail: " + obj));
                                    break;
                                } else {
                                    TbsCoreLoadStat instance8 = TbsCoreLoadStat.getInstance();
                                    int intValue2 = ((Integer) obj).intValue();
                                    instance8.m17035a(context, intValue2, new Throwable("detail: " + obj));
                                    break;
                                }
                            case 0:
                                break;
                            default:
                                TbsCoreLoadStat instance9 = TbsCoreLoadStat.getInstance();
                                instance9.m17035a(context, 415, new Throwable("detail: " + obj + "errcode" + i));
                                break;
                        }
                    } catch (Exception unused3) {
                        return false;
                    }
                } catch (Throwable th) {
                    TbsCoreLoadStat.getInstance().m17035a(context, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, th);
                    return false;
                }
            }
            if (!z3) {
                TbsLog.m16533e(TbsListener.tag_load_error, "319");
            }
            return z3;
        }
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        return !m17052a(context, false) ? false : false;
    }

    public static void setCurrentID(String str) {
        if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
            String substring = str.substring(3);
            f12814z = "0000000000000000".substring(substring.length()) + substring;
        }
    }

    public static String getTID() {
        return f12814z;
    }

    public static String getTbsResourcesPath(Context context) {
        return TbsShareManager.m16952g(context);
    }

    public static void setQQBuildNumber(String str) {
        f12782A = str;
    }

    public static String getQQBuildNumber() {
        return f12782A;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized void m17056a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!f12789a) {
                f12789a = true;
                f12810v = "forceSysWebViewInner: " + str;
                TbsLog.m16533e("QbSdk", "QbSdk.SysWebViewForcedInner..." + f12810v);
                TbsCoreLoadStat.getInstance().m17035a(context, 401, new Throwable(f12810v));
            }
        }
    }

    public static void forceSysWebView() {
        f12790b = true;
        f12809u = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.m16533e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static void unForceSysWebView() {
        f12790b = false;
        TbsLog.m16533e("QbSdk", "sys WebView: unForceSysWebView called");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.tencent.smtt.sdk.QbSdk$1] */
    public static void canOpenFile(final Context context, final String str, final ValueCallback<Boolean> valueCallback) {
        new Thread() { // from class: com.tencent.smtt.sdk.QbSdk.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                X5CoreEngine a = X5CoreEngine.m16621a();
                a.m16620a(context);
                final boolean a2 = a.m16618b() ? a.m16616c().m16611a(context, str) : false;
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.smtt.sdk.QbSdk.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        valueCallback.onReceiveValue(Boolean.valueOf(a2));
                    }
                });
            }
        }.start();
    }

    public static void closeFileReader(Context context) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        a.m16620a(context);
        if (a.m16618b()) {
            a.m16616c().m16558p();
        }
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, null);
        }
    }

    public static void setDisableUseHostBackupCoreBySwitch(boolean z) {
        mDisableUseHostBackupCore = z;
        TbsLog.m16531i("QbSdk", "setDisableUseHostBackupCoreBySwitch -- mDisableUseHostBackupCore is " + mDisableUseHostBackupCore);
    }

    public static void setDisableUnpreinitBySwitch(boolean z) {
        f12783B = z;
        TbsLog.m16531i("QbSdk", "setDisableUnpreinitBySwitch -- mDisableUnpreinitBySwitch is " + f12783B);
    }

    public static synchronized boolean unPreInit(Context context) {
        synchronized (QbSdk.class) {
        }
        return true;
    }

    public static String getCurrentProcessName(Context context) {
        try {
            int myPid = Process.myPid();
            String str = "";
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    str = runningAppProcessInfo.processName;
                }
            }
            return str;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static synchronized void preInit(final Context context, final PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            TbsLog.m16531i("QbSdk", "preInit -- processName: " + getCurrentProcessName(context));
            TbsLog.m16531i("QbSdk", "preInit -- stack: " + Log.getStackTraceString(new Throwable("#")));
            f12800l = f12789a;
            if (!f12807s) {
                final Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.tencent.smtt.sdk.QbSdk.3
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        X5CoreWizard c;
                        switch (message.what) {
                            case 1:
                                boolean unused = QbSdk.f12783B = TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_UNPREINIT);
                                if (QbSdk.f12798j && (c = X5CoreEngine.m16621a().m16616c()) != null) {
                                    c.m16612a(context);
                                }
                                PreInitCallback preInitCallback2 = preInitCallback;
                                if (preInitCallback2 != null) {
                                    preInitCallback2.onViewInitFinished(true);
                                }
                                TbsLog.writeLogToDisk();
                                return;
                            case 2:
                                PreInitCallback preInitCallback3 = preInitCallback;
                                if (preInitCallback3 != null) {
                                    preInitCallback3.onViewInitFinished(false);
                                }
                                TbsLog.writeLogToDisk();
                                return;
                            case 3:
                                PreInitCallback preInitCallback4 = preInitCallback;
                                if (preInitCallback4 != null) {
                                    preInitCallback4.onCoreInitFinished();
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                };
                Thread thread = new Thread() { // from class: com.tencent.smtt.sdk.QbSdk.4
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        int a = TbsInstaller.m16742a().m16722a(true, context);
                        TbsDownloader.setAppContext(context);
                        TbsLog.m16531i("QbSdk", "QbSdk preinit ver is " + a);
                        if (a == 0) {
                            TbsInstaller.m16742a().m16712b(context, true);
                        }
                        TbsLog.m16531i("QbSdk", "preInit -- prepare initAndLoadSo");
                        SDKEngine.m16828a(true).m16830a(context, false, false);
                        X5CoreEngine a2 = X5CoreEngine.m16621a();
                        a2.m16620a(context);
                        boolean b = a2.m16618b();
                        handler.sendEmptyMessage(3);
                        if (!b) {
                            handler.sendEmptyMessage(2);
                        } else {
                            handler.sendEmptyMessage(1);
                        }
                    }
                };
                thread.setName("tbs_preinit");
                thread.setPriority(10);
                thread.start();
                f12807s = true;
            }
        }
    }

    public static void setUploadCode(Context context, int i) {
        if (i >= 130 && i <= 139) {
            TbsDownloadUpload instance = TbsDownloadUpload.getInstance(context);
            instance.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, Integer.valueOf(i));
            instance.commit();
        } else if (i >= 150 && i <= 159) {
            TbsDownloadUpload instance2 = TbsDownloadUpload.getInstance(context);
            instance2.f12894a.put(TbsDownloadUpload.TbsUploadKey.KEY_STARTDOWNLOAD_CODE, Integer.valueOf(i));
            instance2.commit();
        }
    }

    public static void checkTbsValidity(Context context) {
        if (context != null && !TbsCheckUtils.m16391b(context)) {
            TbsLog.m16533e("QbSdk", "sys WebView: SysWebViewForcedBy checkTbsValidity");
            TbsCoreLoadStat.getInstance().m17036a(context, 419);
            forceSysWebView();
        }
    }

    public static void disableAutoCreateX5Webview() {
        f12798j = false;
    }

    public static boolean isTbsCoreInited() {
        SDKEngine a = SDKEngine.m16828a(false);
        return a != null && a.m16820g();
    }

    public static void initX5Environment(final Context context, final PreInitCallback preInitCallback) {
        if (context == null) {
            TbsLog.m16533e("QbSdk", "initX5Environment,context=null");
            return;
        }
        m17048b(context);
        f12786E = new TbsListener() { // from class: com.tencent.smtt.sdk.QbSdk.5
            @Override // com.tencent.smtt.sdk.TbsListener
            public void onDownloadFinish(int i) {
            }

            @Override // com.tencent.smtt.sdk.TbsListener
            public void onDownloadProgress(int i) {
            }

            @Override // com.tencent.smtt.sdk.TbsListener
            public void onInstallFinish(int i) {
                QbSdk.preInit(context, preInitCallback);
            }
        };
        if (TbsShareManager.isThirdPartyApp(context)) {
            TbsInstaller.m16742a().m16712b(context, SDKEngine.f13137a == 0);
        }
        TbsDownloader.needDownload(context, false, false, true, new TbsDownloader.TbsDownloaderCallback() { // from class: com.tencent.smtt.sdk.QbSdk.6
            @Override // com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback
            public void onNeedDownloadFinish(boolean z, int i) {
                if (TbsShareManager.findCoreForThirdPartyApp(context) == 0 && !TbsShareManager.getCoreDisabled()) {
                    TbsShareManager.forceToLoadX5ForThirdApp(context, false);
                }
                if (QbSdk.f12797i && TbsShareManager.isThirdPartyApp(context)) {
                    TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
                }
                QbSdk.preInit(context, preInitCallback);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m17044d(android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.m17044d(android.content.Context):void");
    }

    public static int getTbsVersion(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.m16961a(context, false);
        }
        return TbsInstaller.m16742a().m16688i(context);
    }

    public static int getTbsVersionForCrash(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.m16961a(context, false);
        }
        int j = TbsInstaller.m16742a().m16686j(context);
        if (j == 0 && TbsCoreVerManager.m16764a(context).m16756c() == 3) {
            reset(context);
        }
        return j;
    }

    public static void continueLoadSo(Context context) {
        if (TbsConfig.APP_WX.equals(getCurrentProcessName(context)) && WebView.mWebViewCreated) {
            ReflectionUtils.m16404a(f12806r, "continueLoadSo", new Class[0], new Object[0]);
        }
    }

    public static boolean getJarFilesAndLibraryPath(Context context) {
        Object obj = f12806r;
        if (obj == null) {
            TbsLog.m16531i("QbSdk", "getJarFilesAndLibraryPath sExtensionObj is null");
            return false;
        }
        Bundle bundle = (Bundle) ReflectionUtils.m16404a(obj, "canLoadX5CoreAndNotLoadSo", new Class[]{Integer.TYPE}, 43697);
        if (bundle == null) {
            TbsLog.m16531i("QbSdk", "getJarFilesAndLibraryPath bundle is null and coreverison is " + TbsInstaller.m16742a().m16722a(true, context));
            return false;
        }
        f12808t = bundle.getStringArray("tbs_jarfiles");
        f12792d = bundle.getString("tbs_librarypath");
        return true;
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        String[] strArr = f12808t;
        if (strArr instanceof String[]) {
            int length = strArr.length;
            String[] strArr2 = new String[length];
            for (int i = 0; i < length; i++) {
                strArr2[i] = str + f12808t[i];
            }
            return strArr2;
        }
        Object a = ReflectionUtils.m16404a(f12806r, "getJarFiles", new Class[]{Context.class, Context.class, String.class}, context, context2, str);
        if (!(a instanceof String[])) {
            a = new String[]{""};
        }
        return (String[]) a;
    }

    public static boolean useSoftWare() {
        Object obj = f12806r;
        if (obj == null) {
            return false;
        }
        Object a = ReflectionUtils.m16404a(obj, "useSoftWare", new Class[0], new Object[0]);
        if (a == null) {
            a = ReflectionUtils.m16404a(f12806r, "useSoftWare", new Class[]{Integer.TYPE}, Integer.valueOf(DeviceInfo.m16904a()));
        }
        if (a == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    public static void setTbsListener(TbsListener tbsListener) {
        f12785D = tbsListener;
    }

    public static void setDownloadWithoutWifi(boolean z) {
        f12787F = z;
    }

    public static boolean getDownloadWithoutWifi() {
        return f12787F;
    }

    public static long getApkFileSize(Context context) {
        if (context != null) {
            return TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, 0L);
        }
        return 0L;
    }

    public static void setTBSInstallingStatus(boolean z) {
        f12788G = z;
    }

    public static boolean getTBSInstalling() {
        return f12788G;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m17061a() {
        return f12804p;
    }

    public static void reset(Context context) {
        reset(context, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005d A[Catch: Throwable -> 0x007d, TryCatch #0 {Throwable -> 0x007d, blocks: (B:3:0x0008, B:5:0x000e, B:7:0x0014, B:12:0x002e, B:14:0x005d, B:16:0x0062), top: B:20:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0062 A[Catch: Throwable -> 0x007d, TRY_LEAVE, TryCatch #0 {Throwable -> 0x007d, blocks: (B:3:0x0008, B:5:0x000e, B:7:0x0014, B:12:0x002e, B:14:0x005d, B:16:0x0062), top: B:20:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void reset(android.content.Context r4, boolean r5) {
        /*
            java.lang.String r0 = "QbSdk"
            java.lang.String r1 = "QbSdk reset!"
            r2 = 1
            com.tencent.smtt.utils.TbsLog.m16532e(r0, r1, r2)
            com.tencent.smtt.sdk.TbsDownloader.stopDownload()     // Catch: Throwable -> 0x007d
            r0 = 0
            if (r5 == 0) goto L_0x002d
            boolean r5 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r4)     // Catch: Throwable -> 0x007d
            if (r5 != 0) goto L_0x002d
            com.tencent.smtt.sdk.l r5 = com.tencent.smtt.sdk.TbsInstaller.m16742a()     // Catch: Throwable -> 0x007d
            int r5 = r5.m16690h(r4)     // Catch: Throwable -> 0x007d
            com.tencent.smtt.sdk.l r1 = com.tencent.smtt.sdk.TbsInstaller.m16742a()     // Catch: Throwable -> 0x007d
            int r1 = r1.m16688i(r4)     // Catch: Throwable -> 0x007d
            r3 = 43300(0xa924, float:6.0676E-41)
            if (r5 <= r3) goto L_0x002d
            if (r5 == r1) goto L_0x002d
            r5 = 1
            goto L_0x002e
        L_0x002d:
            r5 = 0
        L_0x002e:
            com.tencent.smtt.sdk.TbsDownloader.m17015c(r4)     // Catch: Throwable -> 0x007d
            java.lang.String r1 = "tbs"
            java.io.File r1 = r4.getDir(r1, r0)     // Catch: Throwable -> 0x007d
            java.lang.String r3 = "core_share_decouple"
            com.tencent.smtt.utils.FileUtil.m16454a(r1, r0, r3)     // Catch: Throwable -> 0x007d
            java.lang.String r1 = "QbSdk"
            java.lang.String r3 = "delete downloaded apk success"
            com.tencent.smtt.utils.TbsLog.m16530i(r1, r3, r2)     // Catch: Throwable -> 0x007d
            java.lang.ThreadLocal<java.lang.Integer> r1 = com.tencent.smtt.sdk.TbsInstaller.f13200a     // Catch: Throwable -> 0x007d
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch: Throwable -> 0x007d
            r1.set(r2)     // Catch: Throwable -> 0x007d
            java.io.File r1 = new java.io.File     // Catch: Throwable -> 0x007d
            java.io.File r2 = r4.getFilesDir()     // Catch: Throwable -> 0x007d
            java.lang.String r3 = "bugly_switch.txt"
            r1.<init>(r2, r3)     // Catch: Throwable -> 0x007d
            boolean r2 = r1.exists()     // Catch: Throwable -> 0x007d
            if (r2 == 0) goto L_0x0060
            r1.delete()     // Catch: Throwable -> 0x007d
        L_0x0060:
            if (r5 == 0) goto L_0x0098
            com.tencent.smtt.sdk.l r5 = com.tencent.smtt.sdk.TbsInstaller.m16742a()     // Catch: Throwable -> 0x007d
            java.io.File r5 = r5.m16679p(r4)     // Catch: Throwable -> 0x007d
            com.tencent.smtt.sdk.l r1 = com.tencent.smtt.sdk.TbsInstaller.m16742a()     // Catch: Throwable -> 0x007d
            java.io.File r0 = r1.m16695f(r4, r0)     // Catch: Throwable -> 0x007d
            com.tencent.smtt.utils.FileUtil.m16443b(r5, r0)     // Catch: Throwable -> 0x007d
            com.tencent.smtt.sdk.l r5 = com.tencent.smtt.sdk.TbsInstaller.m16742a()     // Catch: Throwable -> 0x007d
            r5.m16720b(r4)     // Catch: Throwable -> 0x007d
            goto L_0x0098
        L_0x007d:
            r4 = move-exception
            java.lang.String r5 = "QbSdk"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "QbSdk reset exception:"
            r0.append(r1)
            java.lang.String r4 = android.util.Log.getStackTraceString(r4)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            com.tencent.smtt.utils.TbsLog.m16533e(r5, r4)
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.reset(android.content.Context, boolean):void");
    }

    public static void resetDecoupleCore(Context context) {
        TbsLog.m16532e("QbSdk", "QbSdk resetDecoupleCore!", true);
        try {
            FileUtil.m16444b(TbsInstaller.m16742a().m16679p(context));
        } catch (Throwable th) {
            TbsLog.m16533e("QbSdk", "QbSdk resetDecoupleCore exception:" + Log.getStackTraceString(th));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void clearAllWebViewCache(android.content.Context r6, boolean r7) {
        /*
            java.lang.String r0 = "QbSdk"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "clearAllWebViewCache("
            r1.append(r2)
            r1.append(r6)
            java.lang.String r2 = ", "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.m16531i(r0, r1)
            r0 = 1
            r1 = 0
            com.tencent.smtt.sdk.WebView r2 = new com.tencent.smtt.sdk.WebView     // Catch: Throwable -> 0x0048
            r2.<init>(r6)     // Catch: Throwable -> 0x0048
            com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension r2 = r2.getWebViewClientExtension()     // Catch: Throwable -> 0x0048
            if (r2 == 0) goto L_0x0066
            com.tencent.smtt.sdk.t r1 = com.tencent.smtt.sdk.X5CoreEngine.m16621a()     // Catch: Throwable -> 0x0045
            if (r1 == 0) goto L_0x0043
            boolean r2 = r1.m16618b()     // Catch: Throwable -> 0x0045
            if (r2 == 0) goto L_0x0043
            com.tencent.smtt.sdk.u r1 = r1.m16616c()     // Catch: Throwable -> 0x0045
            r1.m16609a(r6, r7)     // Catch: Throwable -> 0x0045
        L_0x0043:
            r1 = 1
            goto L_0x0066
        L_0x0045:
            r1 = move-exception
            r2 = 1
            goto L_0x004b
        L_0x0048:
            r2 = move-exception
            r1 = r2
            r2 = 0
        L_0x004b:
            java.lang.String r3 = "QbSdk"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "clearAllWebViewCache exception 2 -- "
            r4.append(r5)
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.tencent.smtt.utils.TbsLog.m16533e(r3, r1)
            r1 = r2
        L_0x0066:
            if (r1 == 0) goto L_0x0070
            java.lang.String r6 = "QbSdk"
            java.lang.String r7 = "is_in_x5_mode --> no need to clear system webview!"
            com.tencent.smtt.utils.TbsLog.m16531i(r6, r7)
            return
        L_0x0070:
            android.webkit.WebView r1 = new android.webkit.WebView     // Catch: Throwable -> 0x00bd
            r1.<init>(r6)     // Catch: Throwable -> 0x00bd
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: Throwable -> 0x00bd
            r3 = 11
            if (r2 < r3) goto L_0x008a
            java.lang.String r2 = "searchBoxJavaBridge_"
            r1.removeJavascriptInterface(r2)     // Catch: Throwable -> 0x00bd
            java.lang.String r2 = "accessibility"
            r1.removeJavascriptInterface(r2)     // Catch: Throwable -> 0x00bd
            java.lang.String r2 = "accessibilityTraversal"
            r1.removeJavascriptInterface(r2)     // Catch: Throwable -> 0x00bd
        L_0x008a:
            r1.clearCache(r0)     // Catch: Throwable -> 0x00bd
            if (r7 == 0) goto L_0x0099
            android.webkit.CookieSyncManager.createInstance(r6)     // Catch: Throwable -> 0x00bd
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()     // Catch: Throwable -> 0x00bd
            r7.removeAllCookie()     // Catch: Throwable -> 0x00bd
        L_0x0099:
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch: Throwable -> 0x00bd
            r7.clearUsernamePassword()     // Catch: Throwable -> 0x00bd
            android.webkit.WebViewDatabase r7 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch: Throwable -> 0x00bd
            r7.clearHttpAuthUsernamePassword()     // Catch: Throwable -> 0x00bd
            android.webkit.WebViewDatabase r6 = android.webkit.WebViewDatabase.getInstance(r6)     // Catch: Throwable -> 0x00bd
            r6.clearFormData()     // Catch: Throwable -> 0x00bd
            android.webkit.WebStorage r6 = android.webkit.WebStorage.getInstance()     // Catch: Throwable -> 0x00bd
            r6.deleteAllData()     // Catch: Throwable -> 0x00bd
            android.webkit.WebIconDatabase r6 = android.webkit.WebIconDatabase.getInstance()     // Catch: Throwable -> 0x00bd
            r6.removeAllIcons()     // Catch: Throwable -> 0x00bd
            goto L_0x00d8
        L_0x00bd:
            r6 = move-exception
            java.lang.String r7 = "QbSdk"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "clearAllWebViewCache exception 1 -- "
            r0.append(r1)
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            com.tencent.smtt.utils.TbsLog.m16533e(r7, r6)
        L_0x00d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.QbSdk.clearAllWebViewCache(android.content.Context, boolean):void");
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().m17036a(context, 501);
        if (context == null) {
            return -100;
        }
        X5CoreEngine a = X5CoreEngine.m16621a();
        a.m16620a(context);
        if (!a.m16618b()) {
            TbsCoreLoadStat.getInstance().m17036a(context, 502);
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = -102");
            return -102;
        } else if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
            return -101;
        } else {
            int a2 = a.m16616c().m16610a(context, str, hashMap, null, valueCallback);
            if (a2 == 0) {
                TbsCoreLoadStat.getInstance().m17036a(context, 503);
            } else {
                TbsLogReport instance = TbsLogReport.getInstance(context);
                instance.setLoadErrorCode(504, "" + a2);
            }
            Log.e("QbSdk", "startMiniQBToLoadUrl  ret = " + a2);
            return a2;
        }
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        X5CoreEngine a = X5CoreEngine.m16621a();
        a.m16620a(context);
        if (hashMap != null && DdyConstants.APP_INSTALL_INSTALLING.equals(hashMap.get(LOGIN_TYPE_KEY_PARTNER_CALL_POS)) && a.m16618b()) {
            Bundle bundle = (Bundle) a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0]);
        }
        if (MttLoader.m16876a(context, str, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
            return a.m16618b() && (context == null || !context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") || getTbsVersion(context) >= 25487) && a.m16616c().m16610a(context, str, hashMap, null, valueCallback) == 0;
        }
        return true;
    }

    public static boolean startQBWithBrowserlist(Context context, String str, int i) {
        boolean startQBToLoadurl = startQBToLoadurl(context, str, i, null);
        if (!startQBToLoadurl) {
            openBrowserList(context, str, null);
        }
        return startQBToLoadurl;
    }

    public static int openFileReader(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().m17036a(context, 505);
        if (!checkContentProviderPrivilage(context)) {
            return -5;
        }
        if (str != null) {
            String substring = str.substring(str.lastIndexOf(Consts.f23430h) + 1, str.length());
            if ("apk".equalsIgnoreCase(substring)) {
                Intent intent = new Intent("android.intent.action.VIEW");
                if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                    intent.addFlags(1);
                }
                Uri a = FileProvider.m16546a(context, str);
                if (a == null) {
                    valueCallback.onReceiveValue("uri failed");
                    return -6;
                }
                intent.setDataAndType(a, "application/vnd.android.package-archive");
                context.startActivity(intent);
                TbsCoreLoadStat.getInstance().m17036a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_APKFILE);
                Log.e("QbSdk", "open openFileReader ret = 4");
                return 4;
            }
            if (!MttLoader.m16874b(context)) {
                Log.d("QbSdk", "openFileReader QQ browser not installed");
            } else if (!m17054a(context, str, substring)) {
                Log.e("QbSdk", "openFileReader open in QB isQBSupport: false  ret = 3");
                openFileReaderListWithQBDownload(context, str, valueCallback);
                TbsCoreLoadStat.getInstance().m17036a(context, 507);
                return 3;
            } else if (startQBForDoc(context, str, 4, 0, substring, m17053a(context, hashMap))) {
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("open QB");
                }
                TbsCoreLoadStat.getInstance().m17036a(context, 507);
                Log.e("QbSdk", "open openFileReader open QB ret = 1");
                return 1;
            } else {
                Log.d("QbSdk", "openFileReader startQBForDoc return false");
            }
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            hashMap.put("local", "true");
            TbsLog.setWriteLogJIT(true);
            int startMiniQBToLoadUrl = startMiniQBToLoadUrl(context, str, hashMap, valueCallback);
            if (startMiniQBToLoadUrl != 0) {
                openFileReaderListWithQBDownload(context, str, valueCallback);
                TbsLogReport instance = TbsLogReport.getInstance(context);
                instance.setLoadErrorCode(511, "" + startMiniQBToLoadUrl);
                return 3;
            }
            TbsCoreLoadStat.getInstance().m17036a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS);
            return 2;
        }
        if (valueCallback != null) {
            valueCallback.onReceiveValue("filepath error");
        }
        TbsCoreLoadStat.getInstance().m17036a(context, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL);
        Log.e("QbSdk", "open openFileReader filepath error ret -1");
        return -1;
    }

    public static boolean checkContentProviderPrivilage(Context context) {
        if (context == null || context.getApplicationInfo().targetSdkVersion < 24 || Build.VERSION.SDK_INT < 24 || TbsConfig.APP_QQ.equals(context.getApplicationInfo().packageName)) {
            return true;
        }
        try {
            if (!TextUtils.isEmpty(context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "android.support.v4.content.FileProvider"), 0).authority)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PackageManager packageManager = context.getPackageManager();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(context.getApplicationInfo().packageName + ".provider", 128);
        if (resolveContentProvider == null) {
            Log.e("QbSdk", "Must declare com.tencent.smtt.utils.FileProvider in AndroidManifest above Android 7.0,please view document in x5.tencent.com");
        }
        return resolveContentProvider != null;
    }

    /* renamed from: a */
    private static boolean m17054a(Context context, String str, String str2) {
        return isSuportOpenFile(str2, 2);
    }

    /* renamed from: a */
    private static Bundle m17053a(Context context, Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("style", map.get("style") == null ? ResultTypeConstant.f7213z : map.get("style"));
            try {
                bundle.putInt("topBarBgColor", Color.parseColor(map.get("topBarBgColor")));
            } catch (Exception unused) {
            }
            if (map != null && map.containsKey(FILERADER_MENUDATA)) {
                JSONObject jSONObject = new JSONObject(map.get(FILERADER_MENUDATA));
                JSONArray jSONArray = jSONObject.getJSONArray("menuItems");
                if (jSONArray != null) {
                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                    for (int i = 0; i < jSONArray.length() && i < 5; i++) {
                        try {
                            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                            arrayList.add(i, BitmapFactory.decodeResource(context.getResources(), jSONObject2.getInt("iconResId")));
                            jSONObject2.put("iconResId", i);
                        } catch (Exception unused2) {
                        }
                    }
                    bundle.putParcelableArrayList("resArray", arrayList);
                }
                bundle.putString(FILERADER_MENUDATA, jSONObject.toString());
            }
            return bundle;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMiniQBVersion(Context context) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        a.m16620a(context);
        if (a == null || !a.m16618b()) {
            return null;
        }
        return a.m16616c().m16582f();
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        X5CoreEngine a;
        if (context == null || TbsDownloader.getOverSea(context) || isMiniQBShortCutExist(context, str, str2) || (a = X5CoreEngine.m16621a()) == null || !a.m16618b()) {
            return false;
        }
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        DexLoader b = a.m16616c().m16598b();
        TbsLog.m16533e("QbSdk", "qbsdk createMiniQBShortCut");
        Object invokeStaticMethod = b.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, context, str, str2, bitmap);
        TbsLog.m16533e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
        return invokeStaticMethod != null;
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        X5CoreEngine a;
        Object invokeStaticMethod;
        if (context == null || TbsDownloader.getOverSea(context) || (a = X5CoreEngine.m16621a()) == null || !a.m16618b() || (invokeStaticMethod = a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, context, str)) == null) {
            return false;
        }
        Boolean bool = false;
        if (invokeStaticMethod instanceof Boolean) {
            bool = (Boolean) invokeStaticMethod;
        }
        return bool.booleanValue();
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        X5CoreEngine a;
        return (context == null || TbsDownloader.getOverSea(context) || (a = X5CoreEngine.m16621a()) == null || !a.m16618b() || a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, context, str, str2) == null) ? false : true;
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            String substring = indexOf > 0 ? str.substring(indexOf + 4) : null;
            HashMap hashMap = new HashMap();
            String str3 = "unknown";
            try {
                str3 = context.getApplicationInfo().packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, str3);
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, "14004");
            if (MttLoader.m16876a(context, "miniqb://home".equals(substring) ? "qb://navicard/addCard?cardId=168&cardName=168" : substring, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
                X5CoreEngine a = X5CoreEngine.m16621a();
                if (a != null && a.m16618b() && a.m16616c().m16610a(context, substring, null, str2, null) == 0) {
                    return true;
                }
                webView.loadUrl(substring);
            }
        } else {
            webView.loadUrl(str);
        }
        return false;
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, ValueCallback<String> valueCallback) {
        openFileReaderListWithQBDownload(context, str, null, valueCallback);
    }

    public static void openFileReaderListWithQBDownload(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        if (context != null && !context.getApplicationInfo().packageName.equals("com.tencent.qim") && !context.getApplicationInfo().packageName.equals("com.tencent.tim") && !context.getApplicationInfo().packageName.equals("com.tencent.androidqqmail")) {
            String str2 = "";
            if (bundle != null) {
                str2 = bundle.getString("ChannelId");
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            String c = TBSResources.m16837c(str);
            if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(1);
            }
            Uri a = FileProvider.m16546a(context, str);
            if (a == null) {
                valueCallback.onReceiveValue("uri failed");
                return;
            }
            intent.setDataAndType(a, c);
            isDefaultDialog = false;
            TBSActivityPicker cVar = new TBSActivityPicker(context, "选择其它应用打开", intent, valueCallback, c, str2);
            String a2 = cVar.m16850a();
            if (a2 != null && !TextUtils.isEmpty(a2) && checkApkExist(context, a2)) {
                if (TbsConfig.APP_QB.equals(a2)) {
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getPackageName());
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_CALL_POS, DdyConstants.APP_INSTALL_DOWNLOADING);
                }
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra("big_brother_source_key", str2);
                }
                intent.setPackage(a2);
                context.startActivity(intent);
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("default browser:" + a2);
                }
            } else if ("com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) && isDefaultDialog) {
                new AlertDialog.Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.tencent.smtt.sdk.QbSdk.8
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
            } else if (!isDefaultDialog) {
                try {
                    cVar.show();
                    cVar.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tencent.smtt.sdk.QbSdk.9
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            ValueCallback valueCallback2 = ValueCallback.this;
                            if (valueCallback2 != null) {
                                valueCallback2.onReceiveValue("TbsReaderDialogClosed");
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    valueCallback.onReceiveValue("TbsReaderDialogClosed");
                }
            } else if (valueCallback != null) {
                valueCallback.onReceiveValue("can not open");
            }
        }
    }

    public static boolean checkApkExist(Context context, String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isSuportOpenFile(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = {"rar", "zip", "tar", "bz2", "gz", "7z", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub", "chm", EmailConstants.TEXT_SUBTYPE_HTML, "htm", "xml", "mht", "url", "ini", "log", "bat", "php", "js", "lrc", "jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp", "mp3", "m4a", "aac", "amr", "wav", "ogg", "mid", "ra", "wma", "mpga", "ape", "flac", "RTSP", "RTP", "SDP", "RTMP", "mp4", "flv", "avi", "3gp", "3gpp", "webm", "ts", "ogv", "m3u8", "asf", "wmv", "rmvb", "rm", "f4v", "dat", "mov", "mpg", "mkv", "mpeg", "mpeg1", "mpeg2", "xvid", "dvd", "vcd", "vob", "divx"};
        String[] strArr2 = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "pdf", "epub"};
        switch (i) {
            case 1:
                return Arrays.asList(strArr2).contains(str.toLowerCase());
            case 2:
                return Arrays.asList(strArr).contains(str.toLowerCase());
            default:
                return false;
        }
    }

    public static void openBrowserList(Context context, String str, ValueCallback<String> valueCallback) {
        openBrowserList(context, str, null, valueCallback);
    }

    public static void openBrowserList(Context context, String str, Bundle bundle, final ValueCallback<String> valueCallback) {
        if (context != null) {
            String str2 = "";
            if (bundle != null) {
                str2 = bundle.getString("ChannelId");
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse(str));
            String c = TBSResources.m16837c(str);
            isDefaultDialog = false;
            TBSActivityPicker cVar = new TBSActivityPicker(context, "选择其它应用打开", intent, valueCallback, c, str2);
            String a = cVar.m16850a();
            if (a != null && !TextUtils.isEmpty(a)) {
                if (TbsConfig.APP_QB.equals(a)) {
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getPackageName());
                    intent.putExtra(LOGIN_TYPE_KEY_PARTNER_CALL_POS, DdyConstants.APP_INSTALL_DOWNLOADING);
                }
                intent.setPackage(a);
                intent.putExtra("big_brother_source_key", str2);
                context.startActivity(intent);
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("default browser:" + a);
                }
            } else if (isDefaultDialog) {
                new AlertDialog.Builder(context).setTitle("提示").setMessage("此文件无法打开").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.tencent.smtt.sdk.QbSdk.10
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("can not open");
                }
            } else {
                cVar.show();
                cVar.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tencent.smtt.sdk.QbSdk.2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        ValueCallback valueCallback2 = ValueCallback.this;
                        if (valueCallback2 != null) {
                            valueCallback2.onReceiveValue("TbsReaderDialogClosed");
                        }
                    }
                });
            }
        }
    }

    public static void initTbsSettings(Map<String, Object> map) {
        Map<String, Object> map2 = f12802n;
        if (map2 == null) {
            f12802n = map;
            return;
        }
        try {
            map2.putAll(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getSettings() {
        return f12802n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Object m17055a(Context context, String str, Bundle bundle) {
        if (!m17060a(context)) {
            return Integer.valueOf((int) EXTENSION_INIT_FAILURE);
        }
        Object a = ReflectionUtils.m16404a(f12806r, "miscCall", new Class[]{String.class, Bundle.class}, str, bundle);
        if (a != null) {
            return a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static String m17049b() {
        Object invokeStaticMethod;
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b() || (invokeStaticMethod = a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0])) == null || !(invokeStaticMethod instanceof String)) {
            return null;
        }
        return (String) invokeStaticMethod;
    }

    public static void fileInfoDetect(Context context, String str, ValueCallback<String> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a != null && a.m16618b()) {
            try {
                a.m16616c().m16598b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "fileInfoDetect", new Class[]{Context.class, String.class, ValueCallback.class}, context, str, valueCallback);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void disAllowThirdAppDownload() {
        f12791c = false;
    }

    public static void initBuglyAsync(boolean z) {
        f12797i = z;
    }

    public static void setNeedInitX5FirstTime(boolean z) {
        f12811w = z;
    }

    public static boolean isNeedInitX5FirstTime() {
        return f12811w;
    }

    public static int getTmpDirTbsVersion(Context context) {
        if (TbsCoreVerManager.m16764a(context).m16756c() == 2) {
            return TbsInstaller.m16742a().m16699e(context, 0);
        }
        if (TbsCoreVerManager.m16764a(context).m16757b("copy_status") == 1) {
            return TbsInstaller.m16742a().m16699e(context, 1);
        }
        return 0;
    }
}
