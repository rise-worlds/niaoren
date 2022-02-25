package com.lody.virtual.helper.compat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.stub.RequestPermissionsActivity;
import com.lody.virtual.server.IRequestPermissionsResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class PermissionCompat {
    public static Set<String> DANGEROUS_PERMISSION = new HashSet<String>() { // from class: com.lody.virtual.helper.compat.PermissionCompat.1
        {
            add("android.permission.READ_CALENDAR");
            add("android.permission.WRITE_CALENDAR");
            add("android.permission.CAMERA");
            add("android.permission.READ_CONTACTS");
            add("android.permission.WRITE_CONTACTS");
            add("android.permission.GET_ACCOUNTS");
            add("android.permission.ACCESS_FINE_LOCATION");
            add("android.permission.ACCESS_COARSE_LOCATION");
            add("android.permission.READ_PHONE_STATE");
            add("android.permission.CALL_PHONE");
            if (Build.VERSION.SDK_INT >= 16) {
                add("android.permission.READ_CALL_LOG");
                add("android.permission.WRITE_CALL_LOG");
            }
            add("com.android.voicemail.permission.ADD_VOICEMAIL");
            add("android.permission.USE_SIP");
            add("android.permission.PROCESS_OUTGOING_CALLS");
            add("android.permission.SEND_SMS");
            add("android.permission.RECEIVE_SMS");
            add("android.permission.READ_SMS");
            add("android.permission.RECEIVE_WAP_PUSH");
            add("android.permission.RECEIVE_MMS");
            add("android.permission.RECORD_AUDIO");
            add("android.permission.WRITE_EXTERNAL_STORAGE");
            if (Build.VERSION.SDK_INT >= 16) {
                add("android.permission.READ_EXTERNAL_STORAGE");
            }
            if (Build.VERSION.SDK_INT >= 20) {
                add("android.permission.BODY_SENSORS");
            }
        }
    };

    /* loaded from: classes.dex */
    public interface CallBack {
        boolean onResult(int i, String[] strArr, int[] iArr);
    }

    public static String[] findDangerousPermissions(List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (DANGEROUS_PERMISSION.contains(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static String[] findDangrousPermissions(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (DANGEROUS_PERMISSION.contains(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static boolean isCheckPermissionRequired(ApplicationInfo applicationInfo) {
        return Build.VERSION.SDK_INT >= 23 && VirtualCore.get().getTargetSdkVersion() >= 23 && applicationInfo.targetSdkVersion >= 23;
    }

    public static boolean checkPermissions(String[] strArr, boolean z) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (!VirtualCore.get().checkSelfPermission(str, z)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRequestGranted(int[] iArr) {
        for (int i : iArr) {
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    public static void startRequestPermissions(Context context, boolean z, String[] strArr, final CallBack callBack) {
        RequestPermissionsActivity.request(context, z, strArr, new IRequestPermissionsResult.Stub() { // from class: com.lody.virtual.helper.compat.PermissionCompat.2
            @Override // com.lody.virtual.server.IRequestPermissionsResult
            public boolean onResult(int i, String[] strArr2, int[] iArr) {
                CallBack callBack2 = CallBack.this;
                if (callBack2 != null) {
                    return callBack2.onResult(i, strArr2, iArr);
                }
                return false;
            }
        });
    }
}
