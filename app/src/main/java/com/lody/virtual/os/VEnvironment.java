package com.lody.virtual.os;

import android.content.Context;
import android.os.Build;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.utils.EncodeUtils;
import com.lody.virtual.helper.utils.FileUtils;
import java.io.File;
import p110z1.SocketConstants;

/* renamed from: com.lody.virtual.os.VEnvironment */
/* loaded from: classes.dex */
public class VEnvironment {
    private static final File DALVIK_CACHE_DIRECTORY;
    private static final File DALVIK_CACHE_DIRECTORY64;
    private static final File DATA_DIRECTORY;
    private static final File DATA_DIRECTORY64;
    private static final File ROOT;
    private static final File ROOT64;
    private static final String TAG = "VEnvironment";
    private static final File USER_DE_DIRECTORY;
    private static final File USER_DE_DIRECTORY64;
    private static final File USER_DIRECTORY;
    private static final File USER_DIRECTORY64;

    static {
        File file = new File(getContext().getApplicationInfo().dataDir);
        ROOT = ensureCreated(new File(file, SocketConstants.f15239b));
        DATA_DIRECTORY = ensureCreated(new File(ROOT, "data"));
        USER_DIRECTORY = ensureCreated(new File(DATA_DIRECTORY, ServiceManagerNative.USER));
        USER_DE_DIRECTORY = ensureCreated(new File(DATA_DIRECTORY, "user_de"));
        DALVIK_CACHE_DIRECTORY = ensureCreated(new File(ROOT, "opt"));
        ROOT64 = ensureCreated(new File(new File(file.getParent() + "/" + StubManifest.PACKAGE_NAME_64BIT), SocketConstants.f15239b));
        DATA_DIRECTORY64 = ensureCreated(new File(ROOT64, "data"));
        USER_DIRECTORY64 = ensureCreated(new File(DATA_DIRECTORY64, ServiceManagerNative.USER));
        USER_DE_DIRECTORY64 = ensureCreated(new File(DATA_DIRECTORY64, "user_de"));
        DALVIK_CACHE_DIRECTORY64 = ensureCreated(new File(ROOT64, "opt"));
    }

    public static void systemReady() {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                FileUtils.chmod(ROOT.getAbsolutePath(), 493);
                FileUtils.chmod(DATA_DIRECTORY.getAbsolutePath(), 493);
                FileUtils.chmod(getDataAppDirectory().getAbsolutePath(), 493);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Context getContext() {
        return VirtualCore.get().getContext();
    }

    private static File ensureCreated(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void chmodPackageDictionary(File file) {
        try {
            if (Build.VERSION.SDK_INT >= 21 && !FileUtils.isSymlink(file)) {
                FileUtils.chmod(file.getParentFile().getAbsolutePath(), 493);
                FileUtils.chmod(file.getAbsolutePath(), 493);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getProcDirectory() {
        return ensureCreated(new File(ROOT, "proc"));
    }

    public static File getProcDirectory64() {
        return ensureCreated(new File(ROOT64, "proc"));
    }

    public static File getDataUserPackageDirectory(int i, String str) {
        return ensureCreated(new File(getUserDataDirectory(i), str));
    }

    public static File getDataUserPackageDirectory64(int i, String str) {
        return ensureCreated(new File(getUserDataDirectory64(i), str));
    }

    public static File getDeDataUserPackageDirectory(int i, String str) {
        return ensureCreated(new File(getUserDeDataDirectory(i), str));
    }

    public static File getDeDataUserPackageDirectory64(int i, String str) {
        return ensureCreated(new File(getUserDeDataDirectory64(i), str));
    }

    public static File getPackageResourcePath(String str) {
        return new File(getDataAppPackageDirectory(str), EncodeUtils.decodeBase64("YmFzZS5hcGs="));
    }

    public static File getPackageResourcePath64(String str) {
        return new File(getDataAppPackageDirectory64(str), EncodeUtils.decodeBase64("YmFzZS5hcGs="));
    }

    public static File getDataAppDirectory() {
        return ensureCreated(new File(getDataDirectory(), "app"));
    }

    public static File getDataAppDirectory64() {
        return ensureCreated(new File(getDataDirectory64(), "app"));
    }

    public static File getUidListFile() {
        return new File(getSystemSecureDirectory(), "uid-list.ini");
    }

    public static File getBakUidListFile() {
        return new File(getSystemSecureDirectory(), "uid-list.ini.bak");
    }

    public static File getAccountConfigFile() {
        return new File(getSystemSecureDirectory(), "account-list.ini");
    }

    public static File getComponentStateFile() {
        return new File(getSystemSecureDirectory(), "component-state.ini");
    }

    public static File getSyncDirectory() {
        return ensureCreated(new File(getSystemSecureDirectory(), "sync"));
    }

    public static File getAccountVisibilityConfigFile() {
        return new File(getSystemSecureDirectory(), "account-visibility-list.ini");
    }

    public static File getVirtualLocationFile() {
        return new File(getSystemSecureDirectory(), "virtual-loc.ini");
    }

    public static File getDeviceInfoFile() {
        return new File(getSystemSecureDirectory(), "device-config.ini");
    }

    public static File getSettingRuleFile() {
        return new File(getSystemSecureDirectory(), "app-setting.ini");
    }

    public static File getBuildInfoFile() {
        return new File(getSystemSecureDirectory(), "device-build.ini");
    }

    public static File getPackageListFile() {
        return new File(getSystemSecureDirectory(), "packages.ini");
    }

    public static File getVSConfigFile() {
        return new File(getSystemSecureDirectory(), "vss.ini");
    }

    public static File getBakPackageListFile() {
        return new File(getSystemSecureDirectory(), "packages.ini.bak");
    }

    public static File getJobConfigFile() {
        return new File(getSystemSecureDirectory(), "job-list.ini");
    }

    public static File getDalvikCacheDirectory() {
        return DALVIK_CACHE_DIRECTORY;
    }

    public static File getDalvikCacheDirectory64() {
        return DALVIK_CACHE_DIRECTORY64;
    }

    public static File getOdexFile(String str) {
        File file = DALVIK_CACHE_DIRECTORY;
        return new File(file, "data@app@" + str + "-1@base.apk@classes.dex");
    }

    public static File getOdexFile64(String str) {
        File file = DALVIK_CACHE_DIRECTORY64;
        return new File(file, "data@app@" + str + "-1@base.apk@classes.dex");
    }

    public static File getDataAppPackageDirectory(String str) {
        return ensureCreated(new File(getDataAppDirectory(), str));
    }

    public static File getDataAppPackageDirectory64(String str) {
        return ensureCreated(new File(getDataAppDirectory64(), str));
    }

    public static File getAppLibDirectory(String str) {
        return ensureCreated(new File(getDataAppPackageDirectory(str), "lib"));
    }

    public static File getAppLibDirectory64(String str) {
        return ensureCreated(new File(getDataAppPackageDirectory64(str), "lib"));
    }

    public static File getUserAppLibDirectory(int i, String str) {
        return new File(getDataUserPackageDirectory(i, str), "lib");
    }

    public static File getUserAppLibDirectory64(int i, String str) {
        return new File(getDataUserPackageDirectory64(i, str), "lib");
    }

    public static File getPackageCacheFile(String str) {
        return new File(getDataAppPackageDirectory(str), "package.ini");
    }

    public static File getSignatureFile(String str) {
        return new File(getDataAppPackageDirectory(str), "signature.ini");
    }

    public static File getFrameworkDirectory32() {
        return ensureCreated(new File(ROOT, "framework"));
    }

    public static File getFrameworkDirectory32(String str) {
        return ensureCreated(new File(getFrameworkDirectory32(), str));
    }

    public static File getOptimizedFrameworkFile32(String str) {
        return new File(getFrameworkDirectory32(str), "classes.dex");
    }

    public static File getFrameworkFile32(String str) {
        return new File(getFrameworkDirectory32(str), "extracted.jar");
    }

    public static File getUserSystemDirectory() {
        return USER_DIRECTORY;
    }

    public static File getUserDeSystemDirectory() {
        return USER_DE_DIRECTORY;
    }

    public static File getUserSystemDirectory(int i) {
        return getUserDataDirectory(i);
    }

    public static File getUserDataDirectory(int i) {
        return ensureCreated(new File(USER_DIRECTORY, String.valueOf(i)));
    }

    public static File getUserDeDataDirectory(int i) {
        return ensureCreated(new File(USER_DE_DIRECTORY, String.valueOf(i)));
    }

    public static File getUserDataDirectory64(int i) {
        return ensureCreated(new File(USER_DIRECTORY64, String.valueOf(i)));
    }

    public static File getUserDeDataDirectory64(int i) {
        return ensureCreated(new File(USER_DE_DIRECTORY64, String.valueOf(i)));
    }

    public static File getSystemDirectory(int i) {
        return ensureCreated(new File(getUserDataDirectory(i), "system"));
    }

    public static File getSystemDirectory64(int i) {
        return ensureCreated(new File(getUserDataDirectory64(i), "system"));
    }

    public static File getSystemBuildFile(int i) {
        return new File(getSystemDirectory(i), "build.prop");
    }

    public static File getAppBuildFile(String str, int i) {
        File systemDirectory = getSystemDirectory(i);
        return new File(systemDirectory, str + "_build.prop");
    }

    public static File getWifiMacFile(int i, boolean z) {
        if (z) {
            return new File(getSystemDirectory64(i), "wifiMacAddress");
        }
        return new File(getSystemDirectory(i), "wifiMacAddress");
    }

    public static File getDataDirectory() {
        return DATA_DIRECTORY;
    }

    public static File getDataDirectory64() {
        return DATA_DIRECTORY64;
    }

    public static File getSystemSecureDirectory() {
        return ensureCreated(new File(getDataAppDirectory(), "system"));
    }

    public static File getPackageInstallerStageDir() {
        return ensureCreated(new File(getSystemSecureDirectory(), ".session_dir"));
    }

    public static File getNativeCacheDir(boolean z) {
        return ensureCreated(new File(z ? ROOT64 : ROOT, ".native"));
    }
}
