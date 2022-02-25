package com.lbd.p054xj.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;
import com.common.utils.SharedPreferencesUtil;
import com.common.utils.log.LogUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tencent.mmkv.MMKV;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p110z1.CommonUtil;
import p110z1.PreferencesUtil;
import p110z1.acf;

/* renamed from: com.lbd.xj.app.XJApp */
/* loaded from: classes.dex */
public class XJApp {
    private static final List<String> SCAN_PATH_LIST;
    public static String TAG;
    public static boolean click_return_phone_in_floatview;
    private static volatile XJApp instance;
    public static int versionCode;
    private Context mContext;
    public boolean isNewUser = true;
    public boolean systemIsLive = false;

    public native int killuid(int i);

    static {
        if (!CommonUtil.m11897b()) {
            System.loadLibrary("native-lib");
        }
        TAG = "SHENG";
        click_return_phone_in_floatview = false;
        SCAN_PATH_LIST = Arrays.asList("/wandoujia/app", "/tencent/tassistant/apk", "/BaiduAsa9103056", "/360Download", "/pp/downloader", "/pp/downloader/apk", "/pp/downloader/silent/apk", "/tencent/QQfile_recv");
    }

    public static XJApp getInstance() {
        if (instance == null) {
            synchronized (XJApp.class) {
                if (instance == null) {
                    instance = new XJApp();
                }
            }
        }
        return instance;
    }

    public Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    public ApplicationInfo getApplicationInfo() {
        return this.mContext.getApplicationInfo();
    }

    public void init(Context context) {
        this.mContext = context;
        if (getCurProcessName(context).equals(this.mContext.getPackageName()) && !CommonUtil.m11897b()) {
            killuid(Process.myUid());
        }
        MMKV.initialize(this.mContext);
        Utils.m24101a(this.mContext);
        PreferencesUtil.m13937a().m13936a(this.mContext);
        SharedPreferencesUtil.getInstance().init(this.mContext);
        deleteOtherVersionFile();
        scannedExternalFile();
    }

    String getCurProcessName(Context context) {
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private void deleteOtherVersionFile() {
        int a = PreferencesUtil.m13937a().m13933a("versionCode", 0);
        if (a == 0) {
            PreferencesUtil.m13937a().m13931a("versionCode", (Object) 135);
        } else if (a != 135) {
            new Thread(new Runnable() { // from class: com.lbd.xj.app.XJApp.1
                @Override // java.lang.Runnable
                public void run() {
                    File file = new File(acf.f15177b);
                    LogUtils.m22039d("file:" + file.getAbsolutePath());
                    FileUtils.m22202j(file);
                    PreferencesUtil.m13937a().m13931a("versionCode", (Object) 135);
                }
            }).start();
        }
    }

    private void scannedExternalFile() {
        Iterator<String> it = SCAN_PATH_LIST.iterator();
        while (it.hasNext()) {
            Context applicationContext = getInstance().getApplicationContext();
            scanFile(applicationContext, Environment.getExternalStorageDirectory().getAbsolutePath() + it.next());
        }
    }

    public void scanFile(Context context, String str) {
        try {
            MediaScannerConnection.scanFile(context, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.lbd.xj.app.XJApp.2
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str2, Uri uri) {
                    LogUtils.m22034i("*******", "Scanned " + str2 + ":");
                    StringBuilder sb = new StringBuilder();
                    sb.append("-> uri=");
                    sb.append(uri);
                    LogUtils.m22034i("*******", sb.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AssetManager getAssets() {
        return this.mContext.getAssets();
    }

    public String getPackageCodePath() {
        return this.mContext.getPackageCodePath();
    }
}
