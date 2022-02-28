package com.lody.virtual.server.bit64;

import android.app.ActivityManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Process;
import com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.ProviderCall;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.DexOptimizer;
import com.lody.virtual.helper.compat.NativeLibraryHelperCompat;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.os.VUserInfo;
import com.lody.virtual.os.VUserManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class V64BitHelper extends ContentProvider {
    private static final String[] METHODS = {"getRunningAppProcess", "getRunningTasks", "getRecentTasks", "forceStop", "copyPackage", "uninstallPackage", "cleanPackageData"};

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private static String getAuthority() {
        return VirtualCore.getConfig().getHelperAuthority();
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (METHODS[0].equals(str)) {
            return getRunningAppProcess64(bundle);
        }
        if (METHODS[1].equals(str)) {
            return getRunningTasks64(bundle);
        }
        if (METHODS[2].equals(str)) {
            return getRecentTasks64(bundle);
        }
        if (METHODS[3].equals(str)) {
            return forceStop64(bundle);
        }
        if (METHODS[4].equals(str)) {
            return copyPackage64(bundle);
        }
        if (METHODS[5].equals(str)) {
            return uninstallPackage64(bundle);
        }
        if (METHODS[5].equals(str)) {
            return cleanPackageData64(bundle);
        }
        return null;
    }

    private Bundle cleanPackageData64(Bundle bundle) {
        int[] intArray = bundle.getIntArray("user_ids");
        String string = bundle.getString(HWYunManager.f7540a);
        if (string == null || intArray == null) {
            return null;
        }
        for (int i : intArray) {
            FileUtils.deleteDir(VEnvironment.getDataUserPackageDirectory64(i, string));
        }
        return null;
    }

    private Bundle uninstallPackage64(Bundle bundle) {
        int[] intArray = bundle.getIntArray("user_ids");
        String string = bundle.getString(HWYunManager.f7540a);
        boolean z = bundle.getBoolean("full_remove", false);
        if (string == null || intArray == null) {
            return null;
        }
        if (z) {
            VEnvironment.getPackageResourcePath64(string).delete();
            FileUtils.deleteDir(VEnvironment.getDataAppPackageDirectory64(string));
            VEnvironment.getOdexFile64(string).delete();
        }
        for (int i : intArray) {
            FileUtils.deleteDir(VEnvironment.getDataUserPackageDirectory64(i, string));
        }
        return null;
    }

    private Bundle getRunningAppProcess64(Bundle bundle) {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(((ActivityManager) getContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses());
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("running_processes", arrayList);
        return bundle2;
    }

    private Bundle getRunningTasks64(Bundle bundle) {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(((ActivityManager) getContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(bundle.getInt("max_num", Integer.MAX_VALUE)));
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("running_tasks", arrayList);
        return bundle2;
    }

    private Bundle getRecentTasks64(Bundle bundle) {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(((ActivityManager) getContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRecentTasks(bundle.getInt("max_num", Integer.MAX_VALUE), bundle.getInt("flags", 0)));
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("recent_tasks", arrayList);
        return bundle2;
    }

    private Bundle forceStop64(Bundle bundle) {
        Object obj = bundle.get("target");
        if (obj instanceof Integer) {
            Process.killProcess(((Integer) obj).intValue());
            return null;
        } else if (!(obj instanceof int[])) {
            return null;
        } else {
            for (int i : (int[]) obj) {
                Process.killProcess(i);
            }
            return null;
        }
    }

    private Bundle copyPackage64(Bundle bundle) {
        boolean z;
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable("fd");
        String string = bundle.getString(HWYunManager.f7540a);
        if (!(parcelFileDescriptor == null || string == null)) {
            File packageResourcePath64 = VEnvironment.getPackageResourcePath64(string);
            try {
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
                FileUtils.writeToFile(fileInputStream, packageResourcePath64);
                FileUtils.closeQuietly(fileInputStream);
                VEnvironment.chmodPackageDictionary(packageResourcePath64);
                NativeLibraryHelperCompat.copyNativeBinaries(packageResourcePath64, VEnvironment.getAppLibDirectory64(string));
                try {
                    DexOptimizer.optimizeDex(packageResourcePath64.getPath(), VEnvironment.getOdexFile64(string).getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                z = true;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("res", z);
            return bundle2;
        }
        z = false;
        Bundle bundle22 = new Bundle();
        bundle22.putBoolean("res", z);
        return bundle22;
    }

    public static boolean has64BitEngineStartPermission() {
        try {
            new ProviderCall.Builder(VirtualCore.get().getContext(), getAuthority()).methodName("@").retry(1).call();
            return true;
        } catch (IllegalAccessException unused) {
            return false;
        }
    }

    private static ProviderCall.Builder getHelper() {
        return new ProviderCall.Builder(VirtualCore.get().getContext(), getAuthority()).retry(1);
    }

    public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcess64() {
        Bundle callSafely;
        if (!VirtualCore.get().is64BitEngineInstalled() || (callSafely = getHelper().methodName(METHODS[0]).callSafely()) == null) {
            return Collections.emptyList();
        }
        return callSafely.getParcelableArrayList("running_processes");
    }

    public static List<ActivityManager.RunningTaskInfo> getRunningTasks64(int i) {
        Bundle callSafely;
        if (!VirtualCore.get().is64BitEngineInstalled() || (callSafely = getHelper().methodName(METHODS[1]).addArg("max_num", Integer.valueOf(i)).retry(1).callSafely()) == null) {
            return Collections.emptyList();
        }
        return callSafely.getParcelableArrayList("running_tasks");
    }

    public static List<ActivityManager.RecentTaskInfo> getRecentTasks64(int i, int i2) {
        Bundle callSafely;
        if (!VirtualCore.get().is64BitEngineInstalled() || (callSafely = getHelper().methodName(METHODS[2]).addArg("max_num", Integer.valueOf(i)).addArg("flags", Integer.valueOf(i2)).callSafely()) == null) {
            return Collections.emptyList();
        }
        return callSafely.getParcelableArrayList("recent_tasks");
    }

    public static void forceStop64(int i) {
        if (VirtualCore.get().is64BitEngineInstalled()) {
            getHelper().methodName(METHODS[3]).addArg("target", Integer.valueOf(i)).retry(1).callSafely();
        }
    }

    public static void forceStop64(int[] iArr) {
        if (VirtualCore.get().is64BitEngineInstalled()) {
            getHelper().methodName(METHODS[3]).addArg("target", iArr).retry(1).callSafely();
        }
    }

    public static void uninstallPackage64(int i, String str) {
        int[] iArr;
        if (VirtualCore.get().is64BitEngineInstalled()) {
            boolean z = i == -1;
            if (z) {
                List<VUserInfo> users = VUserManager.get().getUsers();
                iArr = new int[users.size()];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    iArr[i2] = users.get(i2).f10500id;
                }
            } else {
                iArr = new int[]{i};
            }
            getHelper().methodName(METHODS[5]).addArg("user_ids", iArr).addArg("full_remove", Boolean.valueOf(z)).addArg(HWYunManager.f7540a, str).callSafely();
        }
    }

    public static void cleanPackageData64(int i, String str) {
        int[] iArr;
        if (VirtualCore.get().is64BitEngineInstalled()) {
            if (i == -1) {
                List<VUserInfo> users = VUserManager.get().getUsers();
                iArr = new int[users.size()];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    iArr[i2] = users.get(i2).f10500id;
                }
            } else {
                iArr = new int[]{i};
            }
            getHelper().methodName(METHODS[6]).addArg("user_ids", iArr).addArg(HWYunManager.f7540a, str).callSafely();
        }
    }

    public static boolean copyPackage64(String str, String str2) {
        if (VirtualCore.get().is64BitEngineInstalled()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] byteArray = FileUtils.toByteArray(fileInputStream);
                FileUtils.closeQuietly(fileInputStream);
                MemoryFile memoryFile = new MemoryFile("file_" + str2, byteArray.length);
                memoryFile.allowPurging(false);
                memoryFile.getOutputStream().write(byteArray);
                Bundle callSafely = getHelper().methodName(METHODS[4]).addArg("fd", ParcelFileDescriptor.dup(p110z1.MemoryFile.getFileDescriptor.call(memoryFile, new Object[0]))).addArg(HWYunManager.f7540a, str2).callSafely();
                memoryFile.close();
                if (callSafely != null) {
                    return callSafely.getBoolean("res");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
