package p110z1;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: z1.aei */
/* loaded from: classes3.dex */
public class GsonUtil {

    /* renamed from: a */
    private static Gson f15451a;

    static {
        if (f15451a == null) {
            f15451a = new Gson();
        }
    }

    /* renamed from: a */
    public static String m13969a(Object obj) {
        Gson oxVar = f15451a;
        if (oxVar != null) {
            return oxVar.m1575b(obj);
        }
        return null;
    }

    /* renamed from: a */
    public static <T> T m13967a(String str, Class<T> cls) {
        Gson oxVar = f15451a;
        if (oxVar != null) {
            return (T) oxVar.m1589a(str, (Class<Object>) cls);
        }
        return null;
    }

    /* renamed from: a */
    public static <T> List<Map<String, T>> m13968a(String str) {
        Gson oxVar = f15451a;
        if (oxVar != null) {
            return (List) oxVar.m1588a(str, new TypeToken<List<Map<String, T>>>() { // from class: z1.aei.1
            }.getType());
        }
        return null;
    }

    /* renamed from: b */
    public static <T> Map<String, T> m13963b(String str) {
        Gson oxVar = f15451a;
        if (oxVar != null) {
            return (Map) oxVar.m1588a(str, new TypeToken<Map<String, T>>() { // from class: z1.aei.2
            }.getType());
        }
        return null;
    }

    /* renamed from: b */
    public static <T> List<T> m13962b(String str, Class<T> cls) {
        Gson oxVar = new Gson();
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = new JsonParser().m1463a(str).m1484u().iterator();
        while (it.hasNext()) {
            arrayList.add(oxVar.m1585a(it.next(), (Class<Object>) cls));
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m13966a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("json�ַ���");
        } else if (!TextUtils.isEmpty(str2)) {
            JsonElement a = new JsonParser().m1463a(str);
            if (!a.m1486s()) {
                return a.m1485t().m1470c(str2).toString();
            }
            throw new RuntimeException("�õ���jsonElement����Ϊ��");
        } else {
            throw new RuntimeException("note��ǩ����Ϊ��");
        }
    }

    /* renamed from: a */
    public static <T> List<T> m13965a(String str, String str2, Class<T> cls) {
        return m13960c(m13966a(str, str2), cls);
    }

    /* renamed from: c */
    public static <T> List<T> m13960c(String str, Class<T> cls) {
        if (!TextUtils.isEmpty(str)) {
            JsonElement a = new JsonParser().m1463a(str);
            if (a.m1486s()) {
                throw new RuntimeException("�õ���jsonElement����Ϊ��");
            } else if (a.m1489p()) {
                JsonArray u = a.m1484u();
                ArrayList arrayList = new ArrayList();
                Iterator<JsonElement> it = u.iterator();
                while (it.hasNext()) {
                    arrayList.add(new Gson().m1585a(it.next(), (Class<Object>) cls));
                }
                return arrayList;
            } else {
                throw new RuntimeException("json�ַ�����һ��������\ufcbfa�");
            }
        } else {
            throw new RuntimeException("json�ַ���Ϊ��");
        }
    }

    /* renamed from: d */
    public static <T> T m13959d(String str, Class<T> cls) {
        if (!TextUtils.isEmpty(str)) {
            JsonElement a = new JsonParser().m1463a(str);
            if (a.m1486s()) {
                throw new RuntimeException("json�ַ���Ϊ��");
            } else if (a.m1488q()) {
                return (T) new Gson().m1585a(a, (Class<Object>) cls);
            } else {
                throw new RuntimeException("json����һ������");
            }
        } else {
            throw new RuntimeException("json�ַ���Ϊ��");
        }
    }

    /* renamed from: b */
    public static <T> T m13961b(String str, String str2, Class<T> cls) {
        return (T) m13959d(m13966a(str, str2), cls);
    }

    /* renamed from: b */
    public static String m13964b(Object obj) {
        if (obj != null) {
            return new Gson().m1575b(obj);
        }
        throw new RuntimeException("������Ϊ��");
    }
}
