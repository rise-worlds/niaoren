package com.blankj.utilcode.util;

import android.support.annotation.NonNull;
import java.util.Map;
import java.util.Set;

/* renamed from: com.blankj.utilcode.util.ar */
/* loaded from: classes.dex */
public final class SPStaticUtils {

    /* renamed from: a */
    private static SPUtils f6741a;

    /* renamed from: a */
    public static void m23402a(SPUtils asVar) {
        f6741a = asVar;
    }

    /* renamed from: a */
    public static void m23387a(@NonNull String str, String str2) {
        if (str != null) {
            m23386a(str, str2, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23385a(@NonNull String str, String str2, boolean z) {
        if (str != null) {
            m23384a(str, str2, z, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static String m23401a(@NonNull String str) {
        if (str != null) {
            return m23388a(str, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m23363b(@NonNull String str, String str2) {
        if (str != null) {
            return m23362b(str, str2, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23396a(@NonNull String str, int i) {
        if (str != null) {
            m23395a(str, i, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23394a(@NonNull String str, int i, boolean z) {
        if (str != null) {
            m23393a(str, i, z, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static int m23371b(@NonNull String str) {
        if (str != null) {
            return m23364b(str, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static int m23368b(@NonNull String str, int i) {
        if (str != null) {
            return m23367b(str, i, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23392a(@NonNull String str, long j) {
        if (str != null) {
            m23391a(str, j, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23390a(@NonNull String str, long j, boolean z) {
        if (str != null) {
            m23389a(str, j, z, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static long m23355c(@NonNull String str) {
        if (str != null) {
            return m23354c(str, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static long m23366b(@NonNull String str, long j) {
        if (str != null) {
            return m23365b(str, j, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23400a(@NonNull String str, float f) {
        if (str != null) {
            m23399a(str, f, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23398a(@NonNull String str, float f, boolean z) {
        if (str != null) {
            m23397a(str, f, z, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static float m23351d(@NonNull String str) {
        if (str != null) {
            return m23350d(str, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static float m23370b(@NonNull String str, float f) {
        if (str != null) {
            return m23369b(str, f, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23379a(@NonNull String str, boolean z) {
        if (str != null) {
            m23378a(str, z, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23377a(@NonNull String str, boolean z, boolean z2) {
        if (str != null) {
            m23376a(str, z, z2, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public static boolean m23349e(@NonNull String str) {
        if (str != null) {
            return m23348e(str, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static boolean m23359b(@NonNull String str, boolean z) {
        if (str != null) {
            return m23358b(str, z, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23383a(@NonNull String str, Set<String> set) {
        if (str != null) {
            m23382a(str, set, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23381a(@NonNull String str, Set<String> set, boolean z) {
        if (str != null) {
            m23380a(str, set, z, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public static Set<String> m23347f(@NonNull String str) {
        if (str != null) {
            return m23346f(str, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static Set<String> m23361b(@NonNull String str, Set<String> set) {
        if (str != null) {
            return m23360b(str, set, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Map<String, ?> m23403a() {
        return m23372b(m23357c());
    }

    /* renamed from: g */
    public static boolean m23345g(@NonNull String str) {
        if (str != null) {
            return m23344g(str, m23357c());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public static void m23343h(@NonNull String str) {
        if (str != null) {
            m23342h(str, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public static void m23353c(@NonNull String str, boolean z) {
        if (str != null) {
            m23352c(str, z, m23357c());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static void m23373b() {
        m23356c(m23357c());
    }

    /* renamed from: a */
    public static void m23375a(boolean z) {
        m23374a(z, m23357c());
    }

    /* renamed from: a */
    public static void m23386a(@NonNull String str, String str2, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23332a(str, str2);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23384a(@NonNull String str, String str2, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23331a(str, str2, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static String m23388a(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23324b(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static String m23362b(@NonNull String str, String str2, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23320b(str, str2);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23395a(@NonNull String str, int i, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23322b(str, i);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23393a(@NonNull String str, int i, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23335a(str, i, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static int m23364b(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23316c(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static int m23367b(@NonNull String str, int i, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23315c(str, i);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23391a(@NonNull String str, long j, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23334a(str, j);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23389a(@NonNull String str, long j, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23333a(str, j, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static long m23354c(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23313d(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static long m23365b(@NonNull String str, long j, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23321b(str, j);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23399a(@NonNull String str, float f, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23338a(str, f);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23397a(@NonNull String str, float f, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23337a(str, f, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: d */
    public static float m23350d(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23312e(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static float m23369b(@NonNull String str, float f, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23323b(str, f);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23378a(@NonNull String str, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23328a(str, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23376a(@NonNull String str, boolean z, boolean z2, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23327a(str, z, z2);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    public static boolean m23348e(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23311f(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static boolean m23358b(@NonNull String str, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23318b(str, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23382a(@NonNull String str, Set<String> set, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23330a(str, set);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m23380a(@NonNull String str, Set<String> set, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23329a(str, set, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: f */
    public static Set<String> m23346f(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23310g(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static Set<String> m23360b(@NonNull String str, Set<String> set, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23319b(str, set);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: b */
    public static Map<String, ?> m23372b(@NonNull SPUtils asVar) {
        if (asVar != null) {
            return asVar.m23325b();
        }
        throw new NullPointerException("Argument 'spUtils' of type SPUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public static boolean m23344g(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            return asVar.m23309h(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: h */
    public static void m23342h(@NonNull String str, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23308i(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static void m23352c(@NonNull String str, boolean z, @NonNull SPUtils asVar) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (asVar != null) {
            asVar.m23314c(str, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: c */
    public static void m23356c(@NonNull SPUtils asVar) {
        if (asVar != null) {
            asVar.m23317c();
            return;
        }
        throw new NullPointerException("Argument 'spUtils' of type SPUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23374a(boolean z, @NonNull SPUtils asVar) {
        if (asVar != null) {
            asVar.m23326a(z);
            return;
        }
        throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    private static SPUtils m23357c() {
        SPUtils asVar = f6741a;
        return asVar != null ? asVar : SPUtils.m23341a();
    }
}
