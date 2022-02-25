package com.cyjh.ddy.base.utils;

import android.text.TextUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.Gson;
import p110z1.GsonBuilder;
import p110z1.JsonElement;
import p110z1.JsonParser;
import p110z1.TypeToken;

/* renamed from: com.cyjh.ddy.base.utils.i */
/* loaded from: classes.dex */
public class JsonUtil {

    /* renamed from: a */
    public static final String f7130a = "yyyy-MM-dd HH:mm:ss";

    /* renamed from: a */
    public static <T> T m21803a(String str, Class<T> cls) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return (T) m21805a().m1589a(str, (Class<Object>) cls);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static Object m21799b(String str, Class cls) {
        try {
            return m21805a().m1589a(str, (Class<Object>) cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Object m21801a(String str, TypeToken rdVar) {
        try {
            return m21805a().m1588a(str, rdVar.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Object m21802a(String str, Type type) {
        try {
            return m21805a().m1588a(str, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m21804a(Object obj) {
        try {
            return m21805a().m1575b(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static <T> Object m21797c(String str, Class cls) {
        try {
            return m21805a().m1588a(str, new TypeToken<List<T>>() { // from class: com.cyjh.ddy.base.utils.JsonUtil$1
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Gson m21805a() {
        return new GsonBuilder().m1540c().m1552a("yyyy-MM-dd HH:mm:ss").m1535h().m1533j();
    }

    /* renamed from: d */
    public static <T> List<T> m21795d(String str, Class<T> cls) {
        return (List) m21805a().m1588a(str, new TypeToken<List<T>>() { // from class: com.cyjh.ddy.base.utils.JsonUtil$2
        }.getType());
    }

    /* renamed from: e */
    public static <T> List<T> m21794e(String str, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        try {
            Gson a = m21805a();
            Iterator<JsonElement> it = new JsonParser().m1463a(str).m1484u().iterator();
            while (it.hasNext()) {
                arrayList.add(a.m1585a(it.next(), (Class<Object>) cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: b */
    public static Object m21800b(Object obj) {
        try {
            return m21805a().m1575b(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static String m21798c(Object obj) {
        try {
            return m21805a().m1575b(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public static String m21796d(Object obj) {
        try {
            return new GsonBuilder().m1535h().m1533j().m1575b(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
