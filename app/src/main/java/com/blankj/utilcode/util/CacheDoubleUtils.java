package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import p110z1.CacheConstants;

/* renamed from: com.blankj.utilcode.util.i */
/* loaded from: classes.dex */
public final class CacheDoubleUtils implements CacheConstants {

    /* renamed from: e */
    private static final Map<String, CacheDoubleUtils> f6877e = new HashMap();

    /* renamed from: f */
    private final CacheMemoryUtils f6878f;

    /* renamed from: g */
    private final CacheDiskUtils f6879g;

    /* renamed from: a */
    public static CacheDoubleUtils m22583a() {
        return m22582a(CacheMemoryUtils.m22528a(), CacheDiskUtils.m22756a());
    }

    /* renamed from: a */
    public static CacheDoubleUtils m22582a(@NonNull CacheMemoryUtils kVar, @NonNull CacheDiskUtils gVar) {
        if (kVar == null) {
            throw new NullPointerException("Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (gVar != null) {
            String str = gVar.toString() + "_" + kVar.toString();
            CacheDoubleUtils iVar = f6877e.get(str);
            if (iVar == null) {
                synchronized (CacheDoubleUtils.class) {
                    iVar = f6877e.get(str);
                    if (iVar == null) {
                        iVar = new CacheDoubleUtils(kVar, gVar);
                        f6877e.put(str, iVar);
                    }
                }
            }
            return iVar;
        } else {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    private CacheDoubleUtils(CacheMemoryUtils kVar, CacheDiskUtils gVar) {
        this.f6878f = kVar;
        this.f6879g = gVar;
    }

    /* renamed from: a */
    public void m22563a(@NonNull String str, byte[] bArr) {
        if (str != null) {
            m22562a(str, bArr, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22562a(@NonNull String str, byte[] bArr, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, bArr, i);
            this.f6879g.m22727a(str, bArr, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public byte[] m22581a(@NonNull String str) {
        if (str != null) {
            return m22554b(str, (byte[]) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public byte[] m22554b(@NonNull String str, byte[] bArr) {
        if (str != null) {
            byte[] bArr2 = (byte[]) this.f6878f.m22526a(str);
            return bArr2 != null ? bArr2 : this.f6879g.m22712b(str, bArr);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22569a(@NonNull String str, String str2) {
        if (str != null) {
            m22568a(str, str2, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22568a(@NonNull String str, String str2, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, str2, i);
            this.f6879g.m22733a(str, str2, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public String m22560b(@NonNull String str) {
        if (str != null) {
            return m22557b(str, (String) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public String m22557b(@NonNull String str, String str2) {
        if (str != null) {
            String str3 = (String) this.f6878f.m22526a(str);
            return str3 != null ? str3 : this.f6879g.m22715b(str, str2);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22565a(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            m22564a(str, jSONObject, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22564a(@NonNull String str, JSONObject jSONObject, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, jSONObject, i);
            this.f6879g.m22729a(str, jSONObject, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public JSONObject m22552c(@NonNull String str) {
        if (str != null) {
            return m22555b(str, (JSONObject) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public JSONObject m22555b(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            JSONObject jSONObject2 = (JSONObject) this.f6878f.m22526a(str);
            return jSONObject2 != null ? jSONObject2 : this.f6879g.m22713b(str, jSONObject);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22567a(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            m22566a(str, jSONArray, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22566a(@NonNull String str, JSONArray jSONArray, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, jSONArray, i);
            this.f6879g.m22731a(str, jSONArray, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public JSONArray m22550d(@NonNull String str) {
        if (str != null) {
            return m22556b(str, (JSONArray) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public JSONArray m22556b(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            JSONArray jSONArray2 = (JSONArray) this.f6878f.m22526a(str);
            return jSONArray2 != null ? jSONArray2 : this.f6879g.m22714b(str, jSONArray);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22580a(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            m22579a(str, bitmap, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22579a(@NonNull String str, Bitmap bitmap, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, bitmap, i);
            this.f6879g.m22744a(str, bitmap, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public Bitmap m22548e(@NonNull String str) {
        if (str != null) {
            return m22559b(str, (Bitmap) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public Bitmap m22559b(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            Bitmap bitmap2 = (Bitmap) this.f6878f.m22526a(str);
            return bitmap2 != null ? bitmap2 : this.f6879g.m22717b(str, bitmap);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22578a(@NonNull String str, Drawable drawable) {
        if (str != null) {
            m22577a(str, drawable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22577a(@NonNull String str, Drawable drawable, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, drawable, i);
            this.f6879g.m22742a(str, drawable, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public Drawable m22547f(@NonNull String str) {
        if (str != null) {
            return m22558b(str, (Drawable) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public Drawable m22558b(@NonNull String str, Drawable drawable) {
        if (str != null) {
            Drawable drawable2 = (Drawable) this.f6878f.m22526a(str);
            return drawable2 != null ? drawable2 : this.f6879g.m22716b(str, drawable);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22574a(@NonNull String str, Parcelable parcelable) {
        if (str != null) {
            m22573a(str, parcelable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22573a(@NonNull String str, Parcelable parcelable, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, parcelable, i);
            this.f6879g.m22738a(str, parcelable, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public <T> T m22576a(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return (T) m22575a(str, (Parcelable.Creator<Parcelable.Creator<T>>) creator, (Parcelable.Creator<T>) null);
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public <T> T m22575a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            T t2 = (T) this.f6878f.m22526a(str);
            return t2 != null ? t2 : (T) this.f6879g.m22740a(str, (Parcelable.Creator<Parcelable.Creator<T>>) creator, (Parcelable.Creator<T>) t);
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public void m22572a(@NonNull String str, Serializable serializable) {
        if (str != null) {
            m22571a(str, serializable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22571a(@NonNull String str, Serializable serializable, int i) {
        if (str != null) {
            this.f6878f.m22523a(str, serializable, i);
            this.f6879g.m22736a(str, serializable, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public Object m22546g(@NonNull String str) {
        if (str != null) {
            return m22570a(str, (Object) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public Object m22570a(@NonNull String str, Object obj) {
        if (str != null) {
            Object a = this.f6878f.m22526a(str);
            return a != null ? a : this.f6879g.m22735a(str, obj);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public long m22561b() {
        return this.f6879g.m22722b();
    }

    /* renamed from: c */
    public int m22553c() {
        return this.f6879g.m22709c();
    }

    /* renamed from: d */
    public int m22551d() {
        return this.f6878f.m22522b();
    }

    /* renamed from: h */
    public void m22545h(@NonNull String str) {
        if (str != null) {
            this.f6878f.m22521b(str);
            this.f6879g.m22695i(str);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public void m22549e() {
        this.f6878f.m22519c();
        this.f6879g.m22705d();
    }
}
