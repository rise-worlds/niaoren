package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressLint({"ApplySharedPref"})
/* renamed from: com.blankj.utilcode.util.as */
/* loaded from: classes.dex */
public final class SPUtils {

    /* renamed from: a */
    private static final Map<String, SPUtils> f6742a = new HashMap();

    /* renamed from: b */
    private SharedPreferences f6743b;

    /* renamed from: a */
    public static SPUtils m23341a() {
        return m23336a("", 0);
    }

    /* renamed from: a */
    public static SPUtils m23340a(int i) {
        return m23336a("", i);
    }

    /* renamed from: a */
    public static SPUtils m23339a(String str) {
        return m23336a(str, 0);
    }

    /* renamed from: a */
    public static SPUtils m23336a(String str, int i) {
        if (m23307j(str)) {
            str = "spUtils";
        }
        SPUtils asVar = f6742a.get(str);
        if (asVar == null) {
            synchronized (SPUtils.class) {
                asVar = f6742a.get(str);
                if (asVar == null) {
                    asVar = new SPUtils(str, i);
                    f6742a.put(str, asVar);
                }
            }
        }
        return asVar;
    }

    private SPUtils(String str) {
        this.f6743b = Utils.m24103a().getSharedPreferences(str, 0);
    }

    private SPUtils(String str, int i) {
        this.f6743b = Utils.m24103a().getSharedPreferences(str, i);
    }

    /* renamed from: a */
    public void m23332a(@NonNull String str, String str2) {
        if (str != null) {
            m23331a(str, str2, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23331a(@NonNull String str, String str2, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            this.f6743b.edit().putString(str, str2).commit();
        } else {
            this.f6743b.edit().putString(str, str2).apply();
        }
    }

    /* renamed from: b */
    public String m23324b(@NonNull String str) {
        if (str != null) {
            return m23320b(str, "");
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public String m23320b(@NonNull String str, String str2) {
        if (str != null) {
            return this.f6743b.getString(str, str2);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public void m23322b(@NonNull String str, int i) {
        if (str != null) {
            m23335a(str, i, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23335a(@NonNull String str, int i, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            this.f6743b.edit().putInt(str, i).commit();
        } else {
            this.f6743b.edit().putInt(str, i).apply();
        }
    }

    /* renamed from: c */
    public int m23316c(@NonNull String str) {
        if (str != null) {
            return m23315c(str, -1);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public int m23315c(@NonNull String str, int i) {
        if (str != null) {
            return this.f6743b.getInt(str, i);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23334a(@NonNull String str, long j) {
        if (str != null) {
            m23333a(str, j, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23333a(@NonNull String str, long j, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            this.f6743b.edit().putLong(str, j).commit();
        } else {
            this.f6743b.edit().putLong(str, j).apply();
        }
    }

    /* renamed from: d */
    public long m23313d(@NonNull String str) {
        if (str != null) {
            return m23321b(str, -1L);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public long m23321b(@NonNull String str, long j) {
        if (str != null) {
            return this.f6743b.getLong(str, j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23338a(@NonNull String str, float f) {
        if (str != null) {
            m23337a(str, f, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23337a(@NonNull String str, float f, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            this.f6743b.edit().putFloat(str, f).commit();
        } else {
            this.f6743b.edit().putFloat(str, f).apply();
        }
    }

    /* renamed from: e */
    public float m23312e(@NonNull String str) {
        if (str != null) {
            return m23323b(str, -1.0f);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public float m23323b(@NonNull String str, float f) {
        if (str != null) {
            return this.f6743b.getFloat(str, f);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23328a(@NonNull String str, boolean z) {
        if (str != null) {
            m23327a(str, z, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23327a(@NonNull String str, boolean z, boolean z2) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z2) {
            this.f6743b.edit().putBoolean(str, z).commit();
        } else {
            this.f6743b.edit().putBoolean(str, z).apply();
        }
    }

    /* renamed from: f */
    public boolean m23311f(@NonNull String str) {
        if (str != null) {
            return m23318b(str, false);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public boolean m23318b(@NonNull String str, boolean z) {
        if (str != null) {
            return this.f6743b.getBoolean(str, z);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23330a(@NonNull String str, Set<String> set) {
        if (str != null) {
            m23329a(str, set, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m23329a(@NonNull String str, Set<String> set, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            this.f6743b.edit().putStringSet(str, set).commit();
        } else {
            this.f6743b.edit().putStringSet(str, set).apply();
        }
    }

    /* renamed from: g */
    public Set<String> m23310g(@NonNull String str) {
        if (str != null) {
            return m23319b(str, Collections.emptySet());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public Set<String> m23319b(@NonNull String str, Set<String> set) {
        if (str != null) {
            return this.f6743b.getStringSet(str, set);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public Map<String, ?> m23325b() {
        return this.f6743b.getAll();
    }

    /* renamed from: h */
    public boolean m23309h(@NonNull String str) {
        if (str != null) {
            return this.f6743b.contains(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: i */
    public void m23308i(@NonNull String str) {
        if (str != null) {
            m23314c(str, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public void m23314c(@NonNull String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (z) {
            this.f6743b.edit().remove(str).commit();
        } else {
            this.f6743b.edit().remove(str).apply();
        }
    }

    /* renamed from: c */
    public void m23317c() {
        m23326a(false);
    }

    /* renamed from: a */
    public void m23326a(boolean z) {
        if (z) {
            this.f6743b.edit().clear().commit();
        } else {
            this.f6743b.edit().clear().apply();
        }
    }

    /* renamed from: j */
    private static boolean m23307j(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
