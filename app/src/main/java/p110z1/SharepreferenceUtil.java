package p110z1;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Base64;
import com.tencent.mmkv.MMKV;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/* renamed from: z1.aep */
/* loaded from: classes3.dex */
public class SharepreferenceUtil {

    /* renamed from: a */
    public static final String f15462a = "share_preference";

    /* renamed from: a */
    public static <T> List<T> m13895a(String str) {
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

    /* renamed from: b */
    public static SharedPreferences m13884b(String str) {
        return m13877c(str);
    }

    /* renamed from: a */
    public static void m13890a(String str, String str2, int i) {
        m13884b(str).edit().putInt(str2, i);
    }

    /* renamed from: a */
    public static void m13894a(String str, int i) {
        m13890a(f15462a, str, i);
    }

    /* renamed from: a */
    public static void m13886a(String str, String str2, boolean z) {
        m13884b(str).edit().putBoolean(str2, z);
    }

    /* renamed from: a */
    public static void m13885a(String str, boolean z) {
        m13886a(f15462a, str, z);
    }

    /* renamed from: a */
    public static void m13887a(String str, String str2, String str3) {
        m13884b(str).edit().putString(str2, str3);
    }

    /* renamed from: a */
    public static void m13891a(String str, String str2) {
        m13887a(f15462a, str, str2);
    }

    /* renamed from: b */
    public static String m13880b(String str, String str2, String str3) {
        return m13884b(str).getString(str2, str3);
    }

    /* renamed from: b */
    public static String m13882b(String str, String str2) {
        return m13880b(f15462a, str, str2);
    }

    /* renamed from: b */
    public static boolean m13879b(String str, String str2, boolean z) {
        return m13884b(str).getBoolean(str2, z);
    }

    /* renamed from: b */
    public static boolean m13878b(String str, boolean z) {
        return m13879b(f15462a, str, z);
    }

    /* renamed from: b */
    public static int m13881b(String str, String str2, int i) {
        return m13884b(str).getInt(str2, i);
    }

    /* renamed from: b */
    public static int m13883b(String str, int i) {
        return m13881b(f15462a, str, i);
    }

    /* renamed from: a */
    public static <T extends Parcelable> void m13893a(String str, T t) {
        m13889a(f15462a, str, t);
    }

    /* renamed from: a */
    public static <T extends Parcelable> void m13889a(String str, String str2, T t) {
        m13877c(str).encode(str2, t);
    }

    /* renamed from: a */
    public static <T extends Parcelable> T m13888a(String str, String str2, Class<T> cls) {
        return (T) m13877c(str).decodeParcelable(str2, cls);
    }

    /* renamed from: a */
    public static <T extends Parcelable> T m13892a(String str, Class<T> cls) {
        return (T) m13888a(f15462a, str, cls);
    }

    /* renamed from: c */
    private static MMKV m13877c(String str) {
        return MMKV.mmkvWithID(str, 2);
    }
}
