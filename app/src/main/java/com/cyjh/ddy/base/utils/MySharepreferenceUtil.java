package com.cyjh.ddy.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/* renamed from: com.cyjh.ddy.base.utils.l */
/* loaded from: classes.dex */
public class MySharepreferenceUtil {
    /* renamed from: a */
    public static <T> void m21787a(Context context, String str, String str2, List<T> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            String str3 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            m21788a(context, str, str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static <T> List<T> m21785a(String str) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str.getBytes(), 0)));
            List<T> list = (List) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static SharedPreferences m21791a(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    /* renamed from: a */
    public static void m21790a(Context context, String str, String str2, int i) {
        m21791a(context, str).edit().putInt(str2, i).commit();
    }

    /* renamed from: a */
    public static void m21786a(Context context, String str, String str2, boolean z) {
        m21791a(context, str).edit().putBoolean(str2, z).commit();
    }

    /* renamed from: a */
    public static void m21788a(Context context, String str, String str2, String str3) {
        m21791a(context, str).edit().putString(str2, str3).commit();
    }

    /* renamed from: a */
    public static void m21789a(Context context, String str, String str2, long j) {
        m21791a(context, str).edit().putLong(str2, j).commit();
    }

    /* renamed from: b */
    public static String m21782b(Context context, String str, String str2, String str3) {
        return m21791a(context, str).getString(str2, str3);
    }

    /* renamed from: b */
    public static boolean m21781b(Context context, String str, String str2, boolean z) {
        return m21791a(context, str).getBoolean(str2, z);
    }

    /* renamed from: b */
    public static int m21784b(Context context, String str, String str2, int i) {
        return m21791a(context, str).getInt(str2, i);
    }

    /* renamed from: b */
    public static long m21783b(Context context, String str, String str2, long j) {
        return m21791a(context, str).getLong(str2, j);
    }
}
