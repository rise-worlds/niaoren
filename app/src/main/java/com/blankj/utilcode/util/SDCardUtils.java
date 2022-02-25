package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.aq */
/* loaded from: classes.dex */
public final class SDCardUtils {
    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23409a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* renamed from: b */
    public static String m23408b() {
        return "mounted".equals(Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "";
    }

    /* renamed from: c */
    public static List<C0983a> m23407c() {
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) Utils.m24103a().getSystemService("storage");
        if (storageManager == null) {
            return arrayList;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            try {
                Method method = StorageVolume.class.getMethod("getPath", new Class[0]);
                for (StorageVolume storageVolume : storageVolumes) {
                    arrayList.add(new C0983a((String) method.invoke(storageVolume, new Object[0]), storageVolume.getState(), storageVolume.isRemovable()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
            return arrayList;
        }
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Method method4 = StorageManager.class.getMethod("getVolumeState", String.class);
            Object invoke = StorageManager.class.getMethod("getVolumeList", new Class[0]).invoke(storageManager, new Object[0]);
            int length = Array.getLength(invoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(invoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                arrayList.add(new C0983a(str, (String) method4.invoke(storageManager, str), ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()));
            }
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
        } catch (NoSuchMethodException e6) {
            e6.printStackTrace();
        } catch (InvocationTargetException e7) {
            e7.printStackTrace();
        }
        return arrayList;
    }

    /* compiled from: SDCardUtils.java */
    /* renamed from: com.blankj.utilcode.util.aq$a */
    /* loaded from: classes.dex */
    public static class C0983a {

        /* renamed from: a */
        private String f6738a;

        /* renamed from: b */
        private String f6739b;

        /* renamed from: c */
        private boolean f6740c;

        C0983a(String str, String str2, boolean z) {
            this.f6738a = str;
            this.f6739b = str2;
            this.f6740c = z;
        }

        /* renamed from: a */
        public String m23406a() {
            return this.f6738a;
        }

        /* renamed from: b */
        public String m23405b() {
            return this.f6739b;
        }

        /* renamed from: c */
        public boolean m23404c() {
            return this.f6740c;
        }

        public String toString() {
            return "SDCardInfo {path = " + this.f6738a + ", state = " + this.f6739b + ", isRemovable = " + this.f6740c + '}';
        }
    }
}
