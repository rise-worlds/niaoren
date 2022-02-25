package p110z1;

import android.text.TextUtils;
import java.util.List;

/* compiled from: JsonUtil.java */
/* renamed from: z1.apa */
/* loaded from: classes3.dex */
public class apa {
    /* renamed from: a */
    public static Object m11878a(String str, Class cls) {
        try {
            return new Gson().m1589a(str, (Class<Object>) cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m11877a(String str, TypeToken<T> rdVar) {
        try {
            return (T) new Gson().m1588a(str, rdVar.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static <T> T m11876b(String str, Class<T> cls) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return (T) new Gson().m1589a(str, (Class<Object>) cls);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static <T> List<T> m11875c(String str, Class<T> cls) {
        return (List) new Gson().m1588a(str, new TypeToken<List<T>>() { // from class: z1.apa.1
        }.getType());
    }

    /* renamed from: a */
    public static String m11879a(Object obj) {
        try {
            return new Gson().m1575b(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
