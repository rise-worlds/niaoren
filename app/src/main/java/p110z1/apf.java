package p110z1;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.nrzs.libcommon.util.share.PreferenceUtil;
import com.tencent.mmkv.MMKV;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/* compiled from: SharepreferenceUtil.java */
/* renamed from: z1.apf */
/* loaded from: classes3.dex */
public class apf {
    /* renamed from: a */
    public static <T> List<T> m11839a(String str) {
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
    public static void m11844a(Context context, String str, String str2) {
        m11840a(context, str, false).edit().remove(str2).commit();
    }

    /* renamed from: b */
    private static MMKV m11835b(String str) {
        return MMKV.mmkvWithID(str, 2);
    }

    /* renamed from: a */
    public static SharedPreferences m11840a(Context context, String str, boolean z) {
        if (z) {
            return m11835b(str);
        }
        return PreferenceUtil.m18516a(context, str);
    }

    /* renamed from: a */
    public static void m11843a(Context context, String str, String str2, int i) {
        m11840a(context, str, false).edit().putInt(str2, i).commit();
    }

    /* renamed from: a */
    public static void m11841a(Context context, String str, String str2, boolean z) {
        m11840a(context, str, false).edit().putBoolean(str2, z).commit();
    }

    /* renamed from: a */
    public static void m11842a(Context context, String str, String str2, String str3) {
        m11840a(context, str, false).edit().putString(str2, str3).commit();
    }

    /* renamed from: b */
    public static String m11837b(Context context, String str, String str2, String str3) {
        return m11840a(context, str, false).getString(str2, str3);
    }

    /* renamed from: b */
    public static boolean m11836b(Context context, String str, String str2, boolean z) {
        return m11840a(context, str, false).getBoolean(str2, z);
    }

    /* renamed from: b */
    public static int m11838b(Context context, String str, String str2, int i) {
        return m11840a(context, str, false).getInt(str2, i);
    }

    /* renamed from: c */
    public static void m11834c(Context context, String str, String str2, int i) {
        m11840a(context, str, true).edit().putInt(str2, i).commit();
    }

    /* renamed from: c */
    public static void m11832c(Context context, String str, String str2, boolean z) {
        m11840a(context, str, true).edit().putBoolean(str2, z).commit();
    }

    /* renamed from: c */
    public static void m11833c(Context context, String str, String str2, String str3) {
        m11840a(context, str, true).edit().putString(str2, str3).commit();
    }

    /* renamed from: d */
    public static String m11830d(Context context, String str, String str2, String str3) {
        return m11840a(context, str, true).getString(str2, str3);
    }

    /* renamed from: d */
    public static boolean m11829d(Context context, String str, String str2, boolean z) {
        return m11840a(context, str, true).getBoolean(str2, z);
    }

    /* renamed from: d */
    public static int m11831d(Context context, String str, String str2, int i) {
        return m11840a(context, str, true).getInt(str2, i);
    }
}
