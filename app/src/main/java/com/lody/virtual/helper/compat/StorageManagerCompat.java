package com.lody.virtual.helper.compat;

import android.content.Context;
import android.os.storage.StorageManager;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class StorageManagerCompat {
    private StorageManagerCompat() {
    }

    public static String[] getAllPoints(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            return (String[]) storageManager.getClass().getMethod("getVolumePaths", new Class[0]).invoke(storageManager, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isMounted(Context context, String str) {
        if (str == null) {
            return false;
        }
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            return "mounted".equals((String) storageManager.getClass().getMethod("getVolumeState", String.class).invoke(storageManager, str));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<String> getMountedPoints(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String[] strArr = (String[]) storageManager.getClass().getMethod("getVolumePaths", new Class[0]).invoke(storageManager, new Object[0]);
            if (strArr == null || strArr.length <= 0) {
                return null;
            }
            Method method = storageManager.getClass().getMethod("getVolumeState", String.class);
            for (String str : strArr) {
                if ("mounted".equals((String) method.invoke(storageManager, str))) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
