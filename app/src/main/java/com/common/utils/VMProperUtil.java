package com.common.utils;

import android.support.annotation.NonNull;
import android.util.Log;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.AppConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes.dex */
public class VMProperUtil {
    public static final String APPACCESSTOKEN = "AccessToken";
    public static final String APPBASEURLAPI = "baseUrl_api";
    public static final String APPCHANNEL = "app_channel";
    public static final String APPCLIENTID = "client_id";
    public static final String APPENCRYPTKEY = "EncryptKey";
    public static final String APPISTESTMODEL = "isOpenTestModel";
    public static final String APPISTESTREQUEST = "isTestRequset";
    public static final String APPSIGNKEY = "sign_key";
    public static final String APPVERSIONCODE = "app_version_code";
    public static final String APPVERSIONNNAME = "app_version_name";
    private static String FileName = "vm_config";
    public static final String ISBOOTED = "is_booted";
    public static final String ISNEEDCLEANLAUNCHER = "is_need_clean_launcher";
    public static final String ROMVERSION = "rom_version";
    public static final String SCREEN_HEIGHT = "screen_height";
    public static final String SCREEN_WIDTH = "screen_width";
    private static final String TAG = "vmproper";
    public static final String VMUUID = "uuid";
    private static String FilePath = "/data/data/" + AppConfig.f9441h + "/osimg/socket/config/";
    private static String blackListFileName = ".pmslist";
    private static String blackListParent = "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/etc/";
    private static String preloadAppFileName = "vm_preload_list";
    private static String preloadIconPath = FilePath + "vm_preload/";

    public static String getConfigProperties(String str) {
        File file = new File(FilePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!new File(FilePath + FileName).exists()) {
            return "";
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(FilePath + FileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        String property = properties.getProperty(str);
        return property == null ? "" : property;
    }

    public static File getPreloadAppIconFile(@NonNull String str) {
        File file = new File(preloadIconPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String MD5 = StringUtil.MD5(str);
        if (StringUtil.isEmpty(MD5)) {
            LogUtils.m22036e(TAG, "md5img is null");
            return new File(preloadIconPath + "default");
        }
        return new File(preloadIconPath + MD5);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00ca A[Catch: IOException -> 0x00c6, TRY_LEAVE, TryCatch #9 {IOException -> 0x00c6, blocks: (B:56:0x00c2, B:60:0x00ca), top: B:71:0x00c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getPreloadAppListString() {
        /*
            Method dump skipped, instructions count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.common.utils.VMProperUtil.getPreloadAppListString():java.lang.String");
    }

    public static boolean isNeedUpdatePreloadAppIconFile(@NonNull String str) {
        if (StringUtil.isEmpty(str)) {
            Log.d(TAG, "preload app icon is null");
            return false;
        }
        return !new File(preloadIconPath + StringUtil.MD5(str)).exists();
    }

    public static synchronized void writeBlackListToFile(@NonNull String str) {
        synchronized (VMProperUtil.class) {
            File file = new File(blackListParent);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(blackListParent + blackListFileName);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    LogUtils.m22037e("writeBlackListToFile e=" + e.getMessage());
                }
            }
            writeStringToFile(file2, str);
        }
    }

    public static void writeDateToLocalFile(String str, String str2) {
        File file = new File(FilePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(FilePath + FileName);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                LogUtils.m22037e("writeDateToLocalFile e=" + e.getMessage());
            }
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(FilePath + FileName));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        properties.put(str, str2);
        try {
            properties.store(new FileOutputStream(FilePath + FileName), (String) null);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static synchronized void writePreloadAppListToFile(@NonNull String str) {
        synchronized (VMProperUtil.class) {
            File file = new File(FilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(FilePath + preloadAppFileName);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    LogUtils.m22037e("writePreloadAppListToFile e=" + e.getMessage());
                }
            }
            writeStringToFile(file2, str);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.common.utils.VMProperUtil$1] */
    private static void writeStringToFile(File file, String str) {
        new Thread() { // from class: com.common.utils.VMProperUtil.1
        }.start();
    }
}
