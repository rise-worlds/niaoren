package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;
import p110z1.CacheConstants;

/* renamed from: com.blankj.utilcode.util.g */
/* loaded from: classes.dex */
public final class CacheDiskUtils implements CacheConstants {

    /* renamed from: e */
    private static final long f6847e = Long.MAX_VALUE;

    /* renamed from: f */
    private static final int f6848f = Integer.MAX_VALUE;

    /* renamed from: g */
    private static final String f6849g = "cdu_";

    /* renamed from: h */
    private static final String f6850h = "by_";

    /* renamed from: i */
    private static final String f6851i = "st_";

    /* renamed from: j */
    private static final String f6852j = "jo_";

    /* renamed from: k */
    private static final String f6853k = "ja_";

    /* renamed from: l */
    private static final String f6854l = "bi_";

    /* renamed from: m */
    private static final String f6855m = "dr_";

    /* renamed from: n */
    private static final String f6856n = "pa_";

    /* renamed from: o */
    private static final String f6857o = "se_";

    /* renamed from: p */
    private static final Map<String, CacheDiskUtils> f6858p = new HashMap();

    /* renamed from: q */
    private final String f6859q;

    /* renamed from: r */
    private final File f6860r;

    /* renamed from: s */
    private final long f6861s;

    /* renamed from: t */
    private final int f6862t;

    /* renamed from: u */
    private C1016b f6863u;

    /* renamed from: a */
    public static CacheDiskUtils m22756a() {
        return m22746a("", Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static CacheDiskUtils m22747a(String str) {
        return m22746a(str, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static CacheDiskUtils m22755a(long j, int i) {
        return m22746a("", j, i);
    }

    /* renamed from: a */
    public static CacheDiskUtils m22746a(String str, long j, int i) {
        if (m22692l(str)) {
            str = "cacheUtils";
        }
        return m22750a(new File(Utils.m24103a().getCacheDir(), str), j, i);
    }

    /* renamed from: a */
    public static CacheDiskUtils m22751a(@NonNull File file) {
        if (file != null) {
            return m22750a(file, Long.MAX_VALUE, Integer.MAX_VALUE);
        }
        throw new NullPointerException("Argument 'cacheDir' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static CacheDiskUtils m22750a(@NonNull File file, long j, int i) {
        if (file != null) {
            String str = file.getAbsoluteFile() + "_" + j + "_" + i;
            CacheDiskUtils gVar = f6858p.get(str);
            if (gVar == null) {
                synchronized (CacheDiskUtils.class) {
                    gVar = f6858p.get(str);
                    if (gVar == null) {
                        gVar = new CacheDiskUtils(str, file, j, i);
                        f6858p.put(str, gVar);
                    }
                }
            }
            return gVar;
        }
        throw new NullPointerException("Argument 'cacheDir' of type File (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private CacheDiskUtils(String str, File file, long j, int i) {
        this.f6859q = str;
        this.f6860r = file;
        this.f6861s = j;
        this.f6862t = i;
    }

    /* renamed from: e */
    private C1016b m22702e() {
        if (this.f6860r.exists()) {
            if (this.f6863u == null) {
                this.f6863u = new C1016b(this.f6860r, this.f6861s, this.f6862t);
            }
        } else if (this.f6860r.mkdirs()) {
            this.f6863u = new C1016b(this.f6860r, this.f6861s, this.f6862t);
        } else {
            Log.e("CacheDiskUtils", "can't make dirs in " + this.f6860r.getAbsolutePath());
        }
        return this.f6863u;
    }

    public String toString() {
        return this.f6859q + "@" + Integer.toHexString(hashCode());
    }

    /* renamed from: a */
    public void m22728a(@NonNull String str, byte[] bArr) {
        if (str != null) {
            m22727a(str, bArr, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22727a(@NonNull String str, byte[] bArr, int i) {
        if (str != null) {
            m22711b(f6850h + str, bArr, i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    private void m22711b(String str, byte[] bArr, int i) {
        C1016b e;
        if (bArr != null && (e = m22702e()) != null) {
            if (i >= 0) {
                bArr = C1015a.m22687b(i, bArr);
            }
            File a = e.m22676a(str);
            m22749a(a, bArr);
            e.m22671b(a);
            e.m22677a(a);
        }
    }

    /* renamed from: b */
    public byte[] m22718b(@NonNull String str) {
        if (str != null) {
            return m22712b(str, (byte[]) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public byte[] m22712b(@NonNull String str, byte[] bArr) {
        if (str != null) {
            return m22707c(f6850h + str, bArr);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: j */
    private byte[] m22694j(@NonNull String str) {
        if (str != null) {
            return m22707c(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    private byte[] m22707c(@NonNull String str, byte[] bArr) {
        File b;
        if (str != null) {
            C1016b e = m22702e();
            if (e == null || (b = e.m22670b(str)) == null) {
                return bArr;
            }
            byte[] b2 = m22719b(b);
            if (C1015a.m22685c(b2)) {
                e.m22663d(str);
                return bArr;
            }
            e.m22671b(b);
            return C1015a.m22683e(b2);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22734a(@NonNull String str, String str2) {
        if (str != null) {
            m22733a(str, str2, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22733a(@NonNull String str, String str2, int i) {
        if (str != null) {
            m22711b(f6851i + str, m22693k(str2), i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: c */
    public String m22708c(@NonNull String str) {
        if (str != null) {
            return m22715b(str, (String) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public String m22715b(@NonNull String str, String str2) {
        if (str != null) {
            byte[] j = m22694j(f6851i + str);
            return j == null ? str2 : m22724a(j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22730a(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            m22729a(str, jSONObject, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22729a(@NonNull String str, JSONObject jSONObject, int i) {
        if (str != null) {
            m22711b(f6852j + str, m22725a(jSONObject), i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public JSONObject m22704d(@NonNull String str) {
        if (str != null) {
            return m22713b(str, (JSONObject) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public JSONObject m22713b(@NonNull String str, JSONObject jSONObject) {
        if (str != null) {
            byte[] j = m22694j(f6852j + str);
            return j == null ? jSONObject : m22710b(j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22732a(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            m22731a(str, jSONArray, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22731a(@NonNull String str, JSONArray jSONArray, int i) {
        if (str != null) {
            m22711b(f6853k + str, m22726a(jSONArray), i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: e */
    public JSONArray m22701e(@NonNull String str) {
        if (str != null) {
            return m22714b(str, (JSONArray) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public JSONArray m22714b(@NonNull String str, JSONArray jSONArray) {
        if (str != null) {
            byte[] j = m22694j(f6853k + str);
            return j == null ? jSONArray : m22706c(j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22745a(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            m22744a(str, bitmap, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22744a(@NonNull String str, Bitmap bitmap, int i) {
        if (str != null) {
            m22711b(f6854l + str, m22754a(bitmap), i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: f */
    public Bitmap m22699f(@NonNull String str) {
        if (str != null) {
            return m22717b(str, (Bitmap) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public Bitmap m22717b(@NonNull String str, Bitmap bitmap) {
        if (str != null) {
            byte[] j = m22694j(f6854l + str);
            return j == null ? bitmap : m22700e(j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22743a(@NonNull String str, Drawable drawable) {
        if (str != null) {
            m22742a(str, drawable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22742a(@NonNull String str, Drawable drawable, int i) {
        if (str != null) {
            m22711b(f6855m + str, m22753a(drawable), i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: g */
    public Drawable m22697g(@NonNull String str) {
        if (str != null) {
            return m22716b(str, (Drawable) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public Drawable m22716b(@NonNull String str, Drawable drawable) {
        if (str != null) {
            byte[] j = m22694j(f6855m + str);
            return j == null ? drawable : m22698f(j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22739a(@NonNull String str, Parcelable parcelable) {
        if (str != null) {
            m22738a(str, parcelable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22738a(@NonNull String str, Parcelable parcelable, int i) {
        if (str != null) {
            m22711b(f6856n + str, m22752a(parcelable), i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public <T> T m22741a(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return (T) m22740a(str, (Parcelable.Creator<Parcelable.Creator<T>>) creator, (Parcelable.Creator<T>) null);
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public <T> T m22740a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            byte[] j = m22694j(f6856n + str);
            return j == null ? t : (T) m22723a(j, creator);
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public void m22737a(@NonNull String str, Serializable serializable) {
        if (str != null) {
            m22736a(str, serializable, -1);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public void m22736a(@NonNull String str, Serializable serializable, int i) {
        if (str != null) {
            m22711b(f6857o + str, m22748a(serializable), i);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public Object m22696h(@NonNull String str) {
        if (str != null) {
            return m22735a(str, (Object) null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public Object m22735a(@NonNull String str, Object obj) {
        if (str != null) {
            byte[] j = m22694j(f6857o + str);
            return j == null ? obj : m22703d(j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public long m22722b() {
        C1016b e = m22702e();
        if (e == null) {
            return 0L;
        }
        return e.m22681a();
    }

    /* renamed from: c */
    public int m22709c() {
        C1016b e = m22702e();
        if (e == null) {
            return 0;
        }
        return e.m22675b();
    }

    /* renamed from: i */
    public boolean m22695i(@NonNull String str) {
        if (str != null) {
            C1016b e = m22702e();
            if (e == null) {
                return true;
            }
            if (e.m22663d(f6850h + str)) {
                if (e.m22663d(f6851i + str)) {
                    if (e.m22663d(f6852j + str)) {
                        if (e.m22663d(f6853k + str)) {
                            if (e.m22663d(f6854l + str)) {
                                if (e.m22663d(f6855m + str)) {
                                    if (e.m22663d(f6856n + str)) {
                                        if (e.m22663d(f6857o + str)) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public boolean m22705d() {
        C1016b e = m22702e();
        if (e == null) {
            return true;
        }
        return e.m22665d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CacheDiskUtils.java */
    /* renamed from: com.blankj.utilcode.util.g$b */
    /* loaded from: classes.dex */
    public static final class C1016b {

        /* renamed from: a */
        private final AtomicLong f6865a;

        /* renamed from: b */
        private final AtomicInteger f6866b;

        /* renamed from: c */
        private final long f6867c;

        /* renamed from: d */
        private final int f6868d;

        /* renamed from: e */
        private final Map<File, Long> f6869e;

        /* renamed from: f */
        private final File f6870f;

        /* renamed from: g */
        private final Thread f6871g;

        private C1016b(final File file, long j, int i) {
            this.f6869e = Collections.synchronizedMap(new HashMap());
            this.f6870f = file;
            this.f6867c = j;
            this.f6868d = i;
            this.f6865a = new AtomicLong();
            this.f6866b = new AtomicInteger();
            this.f6871g = new Thread(new Runnable() { // from class: com.blankj.utilcode.util.g.b.1
                @Override // java.lang.Runnable
                public void run() {
                    File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.blankj.utilcode.util.g.b.1.1
                        @Override // java.io.FilenameFilter
                        public boolean accept(File file2, String str) {
                            return str.startsWith(CacheDiskUtils.f6849g);
                        }
                    });
                    if (listFiles != null) {
                        int i2 = 0;
                        int i3 = 0;
                        for (File file2 : listFiles) {
                            i2 = (int) (i2 + file2.length());
                            i3++;
                            C1016b.this.f6869e.put(file2, Long.valueOf(file2.lastModified()));
                        }
                        C1016b.this.f6865a.getAndAdd(i2);
                        C1016b.this.f6866b.getAndAdd(i3);
                    }
                }
            });
            this.f6871g.start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public long m22681a() {
            m22669c();
            return this.f6865a.get();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public int m22675b() {
            m22669c();
            return this.f6866b.get();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public File m22676a(String str) {
            m22669c();
            File file = new File(this.f6870f, m22666c(str));
            if (file.exists()) {
                this.f6866b.addAndGet(-1);
                this.f6865a.addAndGet(-file.length());
            }
            return file;
        }

        /* renamed from: c */
        private void m22669c() {
            try {
                this.f6871g.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public File m22670b(String str) {
            File file = new File(this.f6870f, m22666c(str));
            if (!file.exists()) {
                return null;
            }
            return file;
        }

        /* renamed from: c */
        private String m22666c(String str) {
            return CacheDiskUtils.f6849g + str.substring(0, 3) + str.substring(3).hashCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m22677a(File file) {
            this.f6866b.addAndGet(1);
            this.f6865a.addAndGet(file.length());
            while (true) {
                if (this.f6866b.get() > this.f6868d || this.f6865a.get() > this.f6867c) {
                    this.f6865a.addAndGet(-m22662e());
                    this.f6866b.addAndGet(-1);
                } else {
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m22671b(File file) {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.f6869e.put(file, valueOf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public boolean m22663d(String str) {
            File b = m22670b(str);
            if (b == null) {
                return true;
            }
            if (!b.delete()) {
                return false;
            }
            this.f6865a.addAndGet(-b.length());
            this.f6866b.addAndGet(-1);
            this.f6869e.remove(b);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public boolean m22665d() {
            File[] listFiles = this.f6870f.listFiles(new FilenameFilter() { // from class: com.blankj.utilcode.util.g.b.2
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return str.startsWith(CacheDiskUtils.f6849g);
                }
            });
            if (listFiles == null || listFiles.length <= 0) {
                return true;
            }
            boolean z = true;
            for (File file : listFiles) {
                if (!file.delete()) {
                    z = false;
                } else {
                    this.f6865a.addAndGet(-file.length());
                    this.f6866b.addAndGet(-1);
                    this.f6869e.remove(file);
                }
            }
            if (z) {
                this.f6869e.clear();
                this.f6865a.set(0L);
                this.f6866b.set(0);
            }
            return z;
        }

        /* renamed from: e */
        private long m22662e() {
            if (this.f6869e.isEmpty()) {
                return 0L;
            }
            Long l = Long.MAX_VALUE;
            File file = null;
            Set<Map.Entry<File, Long>> entrySet = this.f6869e.entrySet();
            synchronized (this.f6869e) {
                for (Map.Entry<File, Long> entry : entrySet) {
                    Long value = entry.getValue();
                    if (value.longValue() < l.longValue()) {
                        file = entry.getKey();
                        l = value;
                    }
                }
            }
            if (file == null) {
                return 0L;
            }
            long length = file.length();
            if (!file.delete()) {
                return 0L;
            }
            this.f6869e.remove(file);
            return length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CacheDiskUtils.java */
    /* renamed from: com.blankj.utilcode.util.g$a */
    /* loaded from: classes.dex */
    public static final class C1015a {

        /* renamed from: a */
        static final int f6864a = 14;

        private C1015a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public static byte[] m22687b(int i, byte[] bArr) {
            byte[] bytes = m22691a(i).getBytes();
            byte[] bArr2 = new byte[bytes.length + bArr.length];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        /* renamed from: a */
        private static String m22691a(int i) {
            return String.format(Locale.getDefault(), "_$%010d$_", Long.valueOf((System.currentTimeMillis() / 1000) + i));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public static boolean m22685c(byte[] bArr) {
            long d = m22684d(bArr);
            return d != -1 && System.currentTimeMillis() > d;
        }

        /* renamed from: d */
        private static long m22684d(byte[] bArr) {
            if (!m22682f(bArr)) {
                return -1L;
            }
            try {
                return Long.parseLong(new String(m22688a(bArr, 2, 12))) * 1000;
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public static byte[] m22683e(byte[] bArr) {
            return m22682f(bArr) ? m22688a(bArr, 14, bArr.length) : bArr;
        }

        /* renamed from: a */
        private static byte[] m22688a(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        /* renamed from: f */
        private static boolean m22682f(byte[] bArr) {
            return bArr != null && bArr.length >= 14 && bArr[0] == 95 && bArr[1] == 36 && bArr[12] == 36 && bArr[13] == 95;
        }
    }

    /* renamed from: k */
    private static byte[] m22693k(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }

    /* renamed from: a */
    private static String m22724a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr);
    }

    /* renamed from: a */
    private static byte[] m22725a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString().getBytes();
    }

    /* renamed from: b */
    private static JSONObject m22710b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new JSONObject(new String(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m22726a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        return jSONArray.toString().getBytes();
    }

    /* renamed from: c */
    private static JSONArray m22706c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new JSONArray(new String(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m22752a(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* renamed from: a */
    private static <T> T m22723a(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T createFromParcel = creator.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0033: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:22:0x0033 */
    /* renamed from: a */
    private static byte[] m22748a(Serializable serializable) {
        Throwable th;
        Exception e;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            if (serializable == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream.writeObject(serializable);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        objectOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return byteArray;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Exception e5) {
                e = e5;
                objectOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object m22703d(byte[] r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: all -> 0x001d, Exception -> 0x001f
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: all -> 0x001d, Exception -> 0x001f
            r2.<init>(r3)     // Catch: all -> 0x001d, Exception -> 0x001f
            r1.<init>(r2)     // Catch: all -> 0x001d, Exception -> 0x001f
            java.lang.Object r3 = r1.readObject()     // Catch: Exception -> 0x001b, all -> 0x002f
            r1.close()     // Catch: IOException -> 0x0016
            goto L_0x001a
        L_0x0016:
            r0 = move-exception
            r0.printStackTrace()
        L_0x001a:
            return r3
        L_0x001b:
            r3 = move-exception
            goto L_0x0021
        L_0x001d:
            r3 = move-exception
            goto L_0x0031
        L_0x001f:
            r3 = move-exception
            r1 = r0
        L_0x0021:
            r3.printStackTrace()     // Catch: all -> 0x002f
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch: IOException -> 0x002a
            goto L_0x002e
        L_0x002a:
            r3 = move-exception
            r3.printStackTrace()
        L_0x002e:
            return r0
        L_0x002f:
            r3 = move-exception
            r0 = r1
        L_0x0031:
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch: IOException -> 0x0037
            goto L_0x003b
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.CacheDiskUtils.m22703d(byte[]):java.lang.Object");
    }

    /* renamed from: a */
    private static byte[] m22754a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: e */
    private static Bitmap m22700e(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    private static byte[] m22753a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return m22754a(m22720b(drawable));
    }

    /* renamed from: f */
    private static Drawable m22698f(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return m22721b(m22700e(bArr));
    }

    /* renamed from: b */
    private static Bitmap m22720b(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /* renamed from: b */
    private static Drawable m22721b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.m24103a().getResources(), bitmap);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0029 -> B:19:0x002c). Please submit an issue!!! */
    /* renamed from: a */
    private static void m22749a(File file, byte[] bArr) {
        FileChannel fileChannel = null;
        try {
            try {
                try {
                    fileChannel = new FileOutputStream(file, false).getChannel();
                    fileChannel.write(ByteBuffer.wrap(bArr));
                    fileChannel.force(true);
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                if (fileChannel != null) {
                    fileChannel.close();
                }
            }
        } catch (Throwable th) {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] m22719b(java.io.File r10) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: all -> 0x0032, IOException -> 0x0037
            java.lang.String r2 = "r"
            r1.<init>(r10, r2)     // Catch: all -> 0x0032, IOException -> 0x0037
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch: all -> 0x0032, IOException -> 0x0037
            long r1 = r10.size()     // Catch: IOException -> 0x0030, all -> 0x0047
            int r1 = (int) r1     // Catch: IOException -> 0x0030, all -> 0x0047
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: IOException -> 0x0030, all -> 0x0047
            r5 = 0
            long r7 = (long) r1     // Catch: IOException -> 0x0030, all -> 0x0047
            r3 = r10
            java.nio.MappedByteBuffer r2 = r3.map(r4, r5, r7)     // Catch: IOException -> 0x0030, all -> 0x0047
            java.nio.MappedByteBuffer r2 = r2.load()     // Catch: IOException -> 0x0030, all -> 0x0047
            byte[] r3 = new byte[r1]     // Catch: IOException -> 0x0030, all -> 0x0047
            r4 = 0
            r2.get(r3, r4, r1)     // Catch: IOException -> 0x0030, all -> 0x0047
            if (r10 == 0) goto L_0x002f
            r10.close()     // Catch: IOException -> 0x002b
            goto L_0x002f
        L_0x002b:
            r10 = move-exception
            r10.printStackTrace()
        L_0x002f:
            return r3
        L_0x0030:
            r1 = move-exception
            goto L_0x0039
        L_0x0032:
            r10 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x0048
        L_0x0037:
            r1 = move-exception
            r10 = r0
        L_0x0039:
            r1.printStackTrace()     // Catch: all -> 0x0047
            if (r10 == 0) goto L_0x0046
            r10.close()     // Catch: IOException -> 0x0042
            goto L_0x0046
        L_0x0042:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0046:
            return r0
        L_0x0047:
            r0 = move-exception
        L_0x0048:
            if (r10 == 0) goto L_0x0052
            r10.close()     // Catch: IOException -> 0x004e
            goto L_0x0052
        L_0x004e:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0052:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.CacheDiskUtils.m22719b(java.io.File):byte[]");
    }

    /* renamed from: l */
    private static boolean m22692l(String str) {
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
