package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.blankj.utilcode.util.h */
/* loaded from: classes.dex */
public final class CacheDoubleStaticUtils {

    /* renamed from: a */
    private static CacheDoubleUtils f6876a;

    /* renamed from: a */
    public static void m22658a(CacheDoubleUtils iVar) {
        f6876a = iVar;
    }

    /* renamed from: a */
    public static void m22621a(@NonNull String str, byte[] bArr) {
        if (str != null) {
            m22618a(str, bArr, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22620a(@NonNull String str, byte[] bArr, int i) {
        if (str != null) {
            m22619a(str, bArr, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static byte[] m22657a(@NonNull String str) {
        if (str != null) {
            return m22640a(str, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static byte[] m22603b(@NonNull String str, byte[] bArr) {
        if (str != null) {
            return m22602b(str, bArr, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22633a(@NonNull String str, String str2) {
        if (str != null) {
            m22630a(str, str2, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22632a(@NonNull String str, String str2, int i) {
        if (str != null) {
            m22631a(str, str2, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m22615b(@NonNull String str) {
        if (str != null) {
            return m22610b(str, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m22609b(@NonNull String str, String str2) {
        if (str != null) {
            return m22608b(str, str2, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22625a(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            m22622a(str, jSONObject, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22624a(@NonNull String str, JSONObject jSONObject, int i) {
        if (str != null) {
            m22623a(str, jSONObject, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static JSONObject m22599c(@NonNull String str) {
        if (str != null) {
            return m22598c(str, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static JSONObject m22605b(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            return m22604b(str, jSONObject, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22629a(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            m22626a(str, jSONArray, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22628a(@NonNull String str, JSONArray jSONArray, int i) {
        if (str != null) {
            m22627a(str, jSONArray, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static JSONArray m22595d(@NonNull String str) {
        if (str != null) {
            return m22594d(str, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static JSONArray m22607b(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            return m22606b(str, jSONArray, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22656a(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            m22653a(str, bitmap, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22655a(@NonNull String str, Bitmap bitmap, int i) {
        if (str != null) {
            m22654a(str, bitmap, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static Bitmap m22591e(@NonNull String str) {
        if (str != null) {
            return m22590e(str, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static Bitmap m22614b(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            return m22613b(str, bitmap, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22652a(@NonNull String str, Drawable drawable) {
        if (str != null) {
            m22649a(str, drawable, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22651a(@NonNull String str, Drawable drawable, int i) {
        if (str != null) {
            m22650a(str, drawable, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public static Drawable m22589f(@NonNull String str) {
        if (str != null) {
            return m22588f(str, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static Drawable m22612b(@NonNull String str, Drawable drawable) {
        if (str != null) {
            return m22611b(str, drawable, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22644a(@NonNull String str, Parcelable parcelable) {
        if (str != null) {
            m22641a(str, parcelable, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22643a(@NonNull String str, Parcelable parcelable, int i) {
        if (str != null) {
            m22642a(str, parcelable, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static <T> T m22648a(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return (T) m22647a(str, (Parcelable.Creator<Object>) creator, m22593e());
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static <T> T m22646a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return (T) m22645a(str, creator, t, m22593e());
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22639a(@NonNull String str, Serializable serializable) {
        if (str != null) {
            m22636a(str, serializable, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22638a(@NonNull String str, Serializable serializable, int i) {
        if (str != null) {
            m22637a(str, serializable, i, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public static Object m22587g(@NonNull String str) {
        if (str != null) {
            return m22586g(str, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Object m22635a(@NonNull String str, Object obj) {
        if (str != null) {
            return m22634a(str, obj, m22593e());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static long m22659a() {
        return m22616b(m22593e());
    }

    /* renamed from: b */
    public static int m22617b() {
        return m22600c(m22593e());
    }

    /* renamed from: c */
    public static int m22601c() {
        return m22596d(m22593e());
    }

    /* renamed from: h */
    public static void m22585h(@NonNull String str) {
        if (str != null) {
            m22584h(str, m22593e());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static void m22597d() {
        m22592e(m22593e());
    }

    /* renamed from: a */
    public static void m22618a(@NonNull String str, byte[] bArr, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22563a(str, bArr);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22619a(@NonNull String str, byte[] bArr, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22562a(str, bArr, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static byte[] m22640a(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22581a(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static byte[] m22602b(@NonNull String str, byte[] bArr, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22554b(str, bArr);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22630a(@NonNull String str, String str2, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22569a(str, str2);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22631a(@NonNull String str, String str2, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22568a(str, str2, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static String m22610b(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22560b(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static String m22608b(@NonNull String str, String str2, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22557b(str, str2);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22622a(@NonNull String str, JSONObject jSONObject, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22565a(str, jSONObject);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22623a(@NonNull String str, JSONObject jSONObject, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22564a(str, jSONObject, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static JSONObject m22598c(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22552c(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static JSONObject m22604b(@NonNull String str, JSONObject jSONObject, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22555b(str, jSONObject);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22626a(@NonNull String str, JSONArray jSONArray, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22567a(str, jSONArray);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22627a(@NonNull String str, JSONArray jSONArray, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22566a(str, jSONArray, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: d */
    public static JSONArray m22594d(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22550d(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static JSONArray m22606b(@NonNull String str, JSONArray jSONArray, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22556b(str, jSONArray);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22653a(@NonNull String str, Bitmap bitmap, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22580a(str, bitmap);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22654a(@NonNull String str, Bitmap bitmap, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22579a(str, bitmap, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    public static Bitmap m22590e(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22548e(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static Bitmap m22613b(@NonNull String str, Bitmap bitmap, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22559b(str, bitmap);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22649a(@NonNull String str, Drawable drawable, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22578a(str, drawable);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22650a(@NonNull String str, Drawable drawable, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22577a(str, drawable, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: f */
    public static Drawable m22588f(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22547f(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static Drawable m22611b(@NonNull String str, Drawable drawable, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22558b(str, drawable);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22641a(@NonNull String str, Parcelable parcelable, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22574a(str, parcelable);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22642a(@NonNull String str, Parcelable parcelable, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22573a(str, parcelable, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static <T> T m22647a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return (T) iVar.m22576a(str, (Parcelable.Creator<Object>) creator);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static <T> T m22645a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return (T) iVar.m22575a(str, (Parcelable.Creator<Parcelable.Creator<T>>) creator, (Parcelable.Creator<T>) t);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22636a(@NonNull String str, Serializable serializable, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22572a(str, serializable);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22637a(@NonNull String str, Serializable serializable, int i, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22571a(str, serializable, i);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: g */
    public static Object m22586g(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22546g(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static Object m22634a(@NonNull String str, Object obj, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            return iVar.m22570a(str, obj);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static long m22616b(@NonNull CacheDoubleUtils iVar) {
        if (iVar != null) {
            return iVar.m22561b();
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static int m22600c(@NonNull CacheDoubleUtils iVar) {
        if (iVar != null) {
            return iVar.m22553c();
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static int m22596d(@NonNull CacheDoubleUtils iVar) {
        if (iVar != null) {
            return iVar.m22551d();
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public static void m22584h(@NonNull String str, @NonNull CacheDoubleUtils iVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (iVar != null) {
            iVar.m22545h(str);
        } else {
            throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    public static void m22592e(@NonNull CacheDoubleUtils iVar) {
        if (iVar != null) {
            iVar.m22549e();
            return;
        }
        throw new NullPointerException("Argument 'cacheDoubleUtils' of type CacheDoubleUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    private static CacheDoubleUtils m22593e() {
        CacheDoubleUtils iVar = f6876a;
        return iVar != null ? iVar : CacheDoubleUtils.m22583a();
    }
}
