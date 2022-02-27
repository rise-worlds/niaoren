package com.blankj.utilcode.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/* renamed from: com.blankj.utilcode.util.ai */
/* loaded from: classes.dex */
public final class ObjectUtils {
    /* renamed from: b */
    public static <T> T m23590b(T t, T t2) {
        return t == null ? t2 : t;
    }

    private ObjectUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23604a(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if ((obj instanceof CharSequence) && obj.toString().length() == 0) {
            return true;
        }
        if ((obj instanceof Collection) && ((Collection) obj).isEmpty()) {
            return true;
        }
        if ((obj instanceof Map) && ((Map) obj).isEmpty()) {
            return true;
        }
        if ((obj instanceof SimpleArrayMap) && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }
        if ((obj instanceof SparseArray) && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if ((obj instanceof SparseBooleanArray) && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if ((obj instanceof SparseIntArray) && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 18 && (obj instanceof SparseLongArray) && ((SparseLongArray) obj).size() == 0) {
            return true;
        }
        if (!(obj instanceof LongSparseArray) || ((LongSparseArray) obj).size() != 0) {
            return Build.VERSION.SDK_INT >= 16 && (obj instanceof android.util.LongSparseArray) && ((android.util.LongSparseArray) obj).size() == 0;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m23605a(CharSequence charSequence) {
        return charSequence == null || charSequence.toString().length() == 0;
    }

    /* renamed from: a */
    public static boolean m23602a(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /* renamed from: a */
    public static boolean m23601a(Map map) {
        return map == null || map.isEmpty();
    }

    /* renamed from: a */
    public static boolean m23611a(SimpleArrayMap simpleArrayMap) {
        return simpleArrayMap == null || simpleArrayMap.isEmpty();
    }

    /* renamed from: a */
    public static boolean m23609a(SparseArray sparseArray) {
        return sparseArray == null || sparseArray.size() == 0;
    }

    /* renamed from: a */
    public static boolean m23608a(SparseBooleanArray sparseBooleanArray) {
        return sparseBooleanArray == null || sparseBooleanArray.size() == 0;
    }

    /* renamed from: a */
    public static boolean m23607a(SparseIntArray sparseIntArray) {
        return sparseIntArray == null || sparseIntArray.size() == 0;
    }

    /* renamed from: a */
    public static boolean m23612a(LongSparseArray longSparseArray) {
        return longSparseArray == null || longSparseArray.size() == 0;
    }

    @RequiresApi(api = 18)
    /* renamed from: a */
    public static boolean m23606a(SparseLongArray sparseLongArray) {
        return sparseLongArray == null || sparseLongArray.size() == 0;
    }

    @RequiresApi(api = 16)
    /* renamed from: a */
    public static boolean m23610a(android.util.LongSparseArray longSparseArray) {
        return longSparseArray == null || longSparseArray.size() == 0;
    }

    /* renamed from: b */
    public static boolean m23591b(Object obj) {
        return !m23604a(obj);
    }

    /* renamed from: b */
    public static boolean m23592b(CharSequence charSequence) {
        return !m23605a(charSequence);
    }

    /* renamed from: b */
    public static boolean m23589b(Collection collection) {
        return !m23602a(collection);
    }

    /* renamed from: b */
    public static boolean m23588b(Map map) {
        return !m23601a(map);
    }

    /* renamed from: b */
    public static boolean m23598b(SimpleArrayMap simpleArrayMap) {
        return !m23611a(simpleArrayMap);
    }

    /* renamed from: b */
    public static boolean m23596b(SparseArray sparseArray) {
        return !m23609a(sparseArray);
    }

    /* renamed from: b */
    public static boolean m23595b(SparseBooleanArray sparseBooleanArray) {
        return !m23608a(sparseBooleanArray);
    }

    /* renamed from: b */
    public static boolean m23594b(SparseIntArray sparseIntArray) {
        return !m23607a(sparseIntArray);
    }

    /* renamed from: b */
    public static boolean m23599b(LongSparseArray longSparseArray) {
        return !m23612a(longSparseArray);
    }

    @RequiresApi(api = 18)
    /* renamed from: b */
    public static boolean m23593b(SparseLongArray sparseLongArray) {
        return !m23606a(sparseLongArray);
    }

    @RequiresApi(api = 16)
    /* renamed from: b */
    public static boolean m23597b(android.util.LongSparseArray longSparseArray) {
        return !m23610a(longSparseArray);
    }

    /* renamed from: a */
    public static boolean m23603a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    public static void m23600a(Object... objArr) {
        if (objArr != null) {
            for (Object obj : objArr) {
                if (obj == null) {
                    throw new NullPointerException();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: c */
    public static int m23587c(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
