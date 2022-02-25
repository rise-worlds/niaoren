package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.blankj.utilcode.util.f */
/* loaded from: classes.dex */
public final class CacheDiskStaticUtils {

    /* renamed from: a */
    private static CacheDiskUtils f6846a;

    /* renamed from: a */
    public static void m22829a(CacheDiskUtils gVar) {
        f6846a = gVar;
    }

    /* renamed from: a */
    public static void m22792a(@NonNull String str, byte[] bArr) {
        if (str != null) {
            m22789a(str, bArr, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22791a(@NonNull String str, byte[] bArr, int i) {
        if (str != null) {
            m22790a(str, bArr, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static byte[] m22828a(@NonNull String str) {
        if (str != null) {
            return m22811a(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static byte[] m22774b(@NonNull String str, byte[] bArr) {
        if (str != null) {
            return m22773b(str, bArr, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22804a(@NonNull String str, String str2) {
        if (str != null) {
            m22801a(str, str2, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22803a(@NonNull String str, String str2, int i) {
        if (str != null) {
            m22802a(str, str2, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m22786b(@NonNull String str) {
        if (str != null) {
            return m22781b(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m22780b(@NonNull String str, String str2) {
        if (str != null) {
            return m22779b(str, str2, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22796a(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            m22793a(str, jSONObject, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22795a(@NonNull String str, JSONObject jSONObject, int i) {
        if (str != null) {
            m22794a(str, jSONObject, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static JSONObject m22770c(@NonNull String str) {
        if (str != null) {
            return m22769c(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static JSONObject m22776b(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            return m22775b(str, jSONObject, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22800a(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            m22797a(str, jSONArray, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22799a(@NonNull String str, JSONArray jSONArray, int i) {
        if (str != null) {
            m22798a(str, jSONArray, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static JSONArray m22766d(@NonNull String str) {
        if (str != null) {
            return m22765d(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static JSONArray m22778b(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            return m22777b(str, jSONArray, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22827a(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            m22824a(str, bitmap, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22826a(@NonNull String str, Bitmap bitmap, int i) {
        if (str != null) {
            m22825a(str, bitmap, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static Bitmap m22764e(@NonNull String str) {
        if (str != null) {
            return m22763e(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static Bitmap m22785b(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            return m22784b(str, bitmap, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22823a(@NonNull String str, Drawable drawable) {
        if (str != null) {
            m22820a(str, drawable, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22822a(@NonNull String str, Drawable drawable, int i) {
        if (str != null) {
            m22821a(str, drawable, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public static Drawable m22762f(@NonNull String str) {
        if (str != null) {
            return m22761f(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static Drawable m22783b(@NonNull String str, Drawable drawable) {
        if (str != null) {
            return m22782b(str, drawable, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22815a(@NonNull String str, Parcelable parcelable) {
        if (str != null) {
            m22812a(str, parcelable, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22814a(@NonNull String str, Parcelable parcelable, int i) {
        if (str != null) {
            m22813a(str, parcelable, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static <T> T m22819a(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return (T) m22818a(str, (Parcelable.Creator<Object>) creator, m22768d());
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static <T> T m22817a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return (T) m22816a(str, creator, t, m22768d());
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22810a(@NonNull String str, Serializable serializable) {
        if (str != null) {
            m22807a(str, serializable, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22809a(@NonNull String str, Serializable serializable, int i) {
        if (str != null) {
            m22808a(str, serializable, i, m22768d());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public static Object m22760g(@NonNull String str) {
        if (str != null) {
            return m22759g(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Object m22806a(@NonNull String str, Object obj) {
        if (str != null) {
            return m22805a(str, obj, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static long m22830a() {
        return m22787b(m22768d());
    }

    /* renamed from: b */
    public static int m22788b() {
        return m22771c(m22768d());
    }

    /* renamed from: h */
    public static boolean m22758h(@NonNull String str) {
        if (str != null) {
            return m22757h(str, m22768d());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static boolean m22772c() {
        return m22767d(m22768d());
    }

    /* renamed from: a */
    public static void m22789a(@NonNull String str, byte[] bArr, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22728a(str, bArr);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22790a(@NonNull String str, byte[] bArr, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22727a(str, bArr, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static byte[] m22811a(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22718b(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static byte[] m22773b(@NonNull String str, byte[] bArr, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22712b(str, bArr);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22801a(@NonNull String str, String str2, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22734a(str, str2);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22802a(@NonNull String str, String str2, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22733a(str, str2, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static String m22781b(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22708c(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static String m22779b(@NonNull String str, String str2, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22715b(str, str2);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22793a(@NonNull String str, JSONObject jSONObject, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22730a(str, jSONObject);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22794a(@NonNull String str, JSONObject jSONObject, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22729a(str, jSONObject, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static JSONObject m22769c(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22704d(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static JSONObject m22775b(@NonNull String str, JSONObject jSONObject, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22713b(str, jSONObject);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22797a(@NonNull String str, JSONArray jSONArray, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22732a(str, jSONArray);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22798a(@NonNull String str, JSONArray jSONArray, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22731a(str, jSONArray, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: d */
    public static JSONArray m22765d(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22701e(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static JSONArray m22777b(@NonNull String str, JSONArray jSONArray, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22714b(str, jSONArray);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22824a(@NonNull String str, Bitmap bitmap, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22745a(str, bitmap);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22825a(@NonNull String str, Bitmap bitmap, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22744a(str, bitmap, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    public static Bitmap m22763e(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22699f(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static Bitmap m22784b(@NonNull String str, Bitmap bitmap, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22717b(str, bitmap);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22820a(@NonNull String str, Drawable drawable, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22743a(str, drawable);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22821a(@NonNull String str, Drawable drawable, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22742a(str, drawable, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: f */
    public static Drawable m22761f(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22697g(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static Drawable m22782b(@NonNull String str, Drawable drawable, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22716b(str, drawable);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22812a(@NonNull String str, Parcelable parcelable, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22739a(str, parcelable);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22813a(@NonNull String str, Parcelable parcelable, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22738a(str, parcelable, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static <T> T m22818a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return (T) gVar.m22741a(str, (Parcelable.Creator<Object>) creator);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static <T> T m22816a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return (T) gVar.m22740a(str, (Parcelable.Creator<Parcelable.Creator<T>>) creator, (Parcelable.Creator<T>) t);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22807a(@NonNull String str, Serializable serializable, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22737a(str, serializable);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22808a(@NonNull String str, Serializable serializable, int i, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            gVar.m22736a(str, serializable, i);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: g */
    public static Object m22759g(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22696h(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static Object m22805a(@NonNull String str, Object obj, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22735a(str, obj);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static long m22787b(@NonNull CacheDiskUtils gVar) {
        if (gVar != null) {
            return gVar.m22722b();
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static int m22771c(@NonNull CacheDiskUtils gVar) {
        if (gVar != null) {
            return gVar.m22709c();
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public static boolean m22757h(@NonNull String str, @NonNull CacheDiskUtils gVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            return gVar.m22695i(str);
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: d */
    public static boolean m22767d(@NonNull CacheDiskUtils gVar) {
        if (gVar != null) {
            return gVar.m22705d();
        }
        throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    private static CacheDiskUtils m22768d() {
        CacheDiskUtils gVar = f6846a;
        return gVar != null ? gVar : CacheDiskUtils.m22756a();
    }
}
