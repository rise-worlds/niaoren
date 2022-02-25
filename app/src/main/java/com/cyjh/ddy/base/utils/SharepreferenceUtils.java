package com.cyjh.ddy.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.blankj.utilcode.util.Utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/* renamed from: com.cyjh.ddy.base.utils.q */
/* loaded from: classes.dex */
public class SharepreferenceUtils {
    /* renamed from: a */
    public static <T> void m21757a(Context context, String str, String str2, T t) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            String str3 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            MySharepreferenceUtil.m21788a(context, str, str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static <T> T m21756a(String str) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str.getBytes(), 0)));
            T t = (T) objectInputStream.readObject();
            objectInputStream.close();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m21758a(Context context, String str, String str2) {
        try {
            String b = MySharepreferenceUtil.m21782b(context, str, str2, "");
            if (b.equals("")) {
                return null;
            }
            return (T) m21756a(b);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static void m21750b(String str) {
        SharedPreferences.Editor edit = m21759a().edit();
        edit.remove(str);
        edit.commit();
    }

    /* renamed from: a */
    public static SharedPreferences m21759a() {
        return Utils.m24103a().getSharedPreferences(Utils.m24103a().getResources().getString(R.string.app_name), 0);
    }

    /* renamed from: a */
    public static void m21753a(String str, String str2) {
        SharedPreferences.Editor edit = m21759a().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    /* renamed from: a */
    public static void m21754a(String str, long j) {
        SharedPreferences.Editor edit = m21759a().edit();
        edit.putLong(str, j);
        edit.commit();
    }

    /* renamed from: a */
    public static void m21751a(String str, boolean z) {
        SharedPreferences.Editor edit = m21759a().edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    /* renamed from: a */
    public static void m21755a(String str, int i) {
        SharedPreferences.Editor edit = m21759a().edit();
        edit.putInt(str, i);
        edit.commit();
    }

    /* renamed from: b */
    public static int m21749b(String str, int i) {
        return m21759a().getInt(str, i);
    }

    /* renamed from: b */
    public static String m21747b(String str, String str2) {
        return m21759a().getString(str, str2);
    }

    /* renamed from: b */
    public static long m21748b(String str, long j) {
        return m21759a().getLong(str, j);
    }

    /* renamed from: b */
    public static boolean m21746b(String str, boolean z) {
        return m21759a().getBoolean(str, z);
    }

    /* renamed from: a */
    public static boolean m21752a(String str, ArrayList<String> arrayList) {
        SharedPreferences.Editor edit = m21759a().edit();
        edit.putInt(str + "_size", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            edit.remove(str + "_" + i);
            edit.putString(str + "_" + i, arrayList.get(i));
        }
        return edit.commit();
    }

    /* renamed from: c */
    public static ArrayList<String> m21745c(String str) {
        SharedPreferences a = m21759a();
        ArrayList<String> arrayList = new ArrayList<>();
        int i = a.getInt(str + "_size", 0);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(a.getString(str + "_" + i2, null));
        }
        return arrayList;
    }
}
