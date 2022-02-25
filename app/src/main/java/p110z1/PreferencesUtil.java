package p110z1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import com.common.utils.log.LogUtils;
import com.tencent.mmkv.MMKV;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* renamed from: z1.aem */
/* loaded from: classes3.dex */
public class PreferencesUtil {

    /* renamed from: a */
    public static PreferencesUtil f15458a;

    /* renamed from: c */
    private Object f15460c;

    /* renamed from: b */
    private SharedPreferences.Editor f15459b = null;

    /* renamed from: d */
    private MMKV f15461d = null;

    /* renamed from: a */
    public static PreferencesUtil m13937a() {
        if (f15458a == null) {
            synchronized (PreferencesUtil.class) {
                if (f15458a == null) {
                    f15458a = new PreferencesUtil();
                }
            }
        }
        return f15458a;
    }

    /* renamed from: a */
    public void m13936a(Context context) {
        this.f15461d = MMKV.mmkvWithID("mdata");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        this.f15461d.importFromSharedPreferences(defaultSharedPreferences);
        defaultSharedPreferences.edit().clear().commit();
    }

    private PreferencesUtil() {
    }

    /* renamed from: a */
    public synchronized void m13931a(String str, Object obj) {
        if (this.f15459b == null) {
            this.f15459b = this.f15461d.edit();
        }
        String simpleName = obj.getClass().getSimpleName();
        if ("String".equals(simpleName)) {
            this.f15459b.putString(str, (String) obj);
        } else if ("Integer".equals(simpleName)) {
            this.f15459b.putInt(str, ((Integer) obj).intValue());
        } else if ("Boolean".equals(simpleName)) {
            this.f15459b.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if ("Float".equals(simpleName)) {
            this.f15459b.putFloat(str, ((Float) obj).floatValue());
        } else if ("Long".equals(simpleName)) {
            this.f15459b.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Serializable) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
                this.f15459b.putString(str, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                LogUtils.m22038d(getClass().getSimpleName(), "save object success");
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.m22036e(getClass().getSimpleName(), "save object error");
            }
        } else {
            throw new IllegalArgumentException(obj.getClass().getName() + " 必须实现Serializable接口!");
        }
    }

    /* renamed from: a */
    public synchronized void m13935a(String str) {
        if (this.f15459b == null) {
            this.f15459b = this.f15461d.edit();
        }
        this.f15459b.remove(str);
    }

    /* renamed from: b */
    public Object m13927b(String str, Object obj) {
        if (obj == null) {
            return m13928b(str);
        }
        String simpleName = obj.getClass().getSimpleName();
        if ("String".equals(simpleName)) {
            return this.f15461d.getString(str, (String) obj);
        }
        if ("Integer".equals(simpleName)) {
            return Integer.valueOf(this.f15461d.getInt(str, ((Integer) obj).intValue()));
        }
        if ("Boolean".equals(simpleName)) {
            return Boolean.valueOf(this.f15461d.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if ("Float".equals(simpleName)) {
            return Float.valueOf(this.f15461d.getFloat(str, ((Float) obj).floatValue()));
        }
        return "Long".equals(simpleName) ? Long.valueOf(this.f15461d.getLong(str, ((Long) obj).longValue())) : m13928b(str);
    }

    /* renamed from: a */
    public String m13930a(String str, String str2) {
        return this.f15461d.getString(str, str2);
    }

    /* renamed from: a */
    public int m13933a(String str, int i) {
        return this.f15461d.getInt(str, i);
    }

    /* renamed from: a */
    public float m13934a(String str, float f) {
        return this.f15461d.getFloat(str, f);
    }

    /* renamed from: a */
    public long m13932a(String str, long j) {
        return this.f15461d.getLong(str, j);
    }

    /* renamed from: a */
    public boolean m13929a(String str, boolean z) {
        return this.f15461d.getBoolean(str, z);
    }

    /* renamed from: b */
    public Object m13928b(String str) {
        try {
            this.f15460c = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(this.f15461d.getString(str, "").getBytes(), 0))).readObject();
            LogUtils.m22038d(getClass().getSimpleName(), "Get object success");
            return this.f15460c;
        } catch (Exception unused) {
            LogUtils.m22036e(getClass().getSimpleName(), "Get object is error");
            return null;
        }
    }
}
