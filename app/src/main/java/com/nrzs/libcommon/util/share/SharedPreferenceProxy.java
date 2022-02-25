package com.nrzs.libcommon.util.share;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.nrzs.libcommon.util.share.c */
/* loaded from: classes2.dex */
public class SharedPreferenceProxy implements SharedPreferences {

    /* renamed from: a */
    private static Map<String, SharedPreferenceProxy> f11214a;

    /* renamed from: b */
    private static AtomicInteger f11215b = new AtomicInteger(0);

    /* renamed from: c */
    private Context f11216c;

    /* renamed from: d */
    private String f11217d;

    private SharedPreferenceProxy(Context context, String str) {
        this.f11216c = context.getApplicationContext();
        this.f11217d = str;
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException("Not support method getAll");
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public String getString(String str, @Nullable String str2) {
        OpEntry a = m18514a(OpEntry.m18522d(str).m18525c(str2));
        return a == null ? str2 : a.m18529b(str2);
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public Set<String> getStringSet(String str, @Nullable Set<String> set) {
        Set<String> d;
        OpEntry a = m18514a(OpEntry.m18522d(str).m18535a(set));
        return (a == null || (d = a.m18524d()) == null) ? set : d;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        OpEntry a = m18514a(OpEntry.m18522d(str).m18523d(i));
        return a == null ? i : a.m18526c(i);
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        OpEntry a = m18514a(OpEntry.m18522d(str).m18530b(j));
        return a == null ? j : a.m18537a(j);
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        OpEntry a = m18514a(OpEntry.m18522d(str).m18532b(f));
        return a == null ? f : a.m18539a(f);
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        OpEntry a = m18514a(OpEntry.m18522d(str).m18528b(z));
        return a == null ? z : a.m18534a(z);
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("key_key", str);
        try {
            return this.f11216c.getContentResolver().call(PreferenceUtil.f11209c, PreferenceUtil.f11207a, this.f11217d, bundle).getBoolean(PreferenceUtil.f11213g);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new SharedPreferences$EditorC2192a();
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    private OpEntry m18514a(@NonNull OpEntry aVar) {
        try {
            return new OpEntry(this.f11216c.getContentResolver().call(PreferenceUtil.f11209c, PreferenceUtil.f11210d, this.f11217d, aVar.m18521e()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* compiled from: SharedPreferenceProxy.java */
    /* renamed from: com.nrzs.libcommon.util.share.c$a */
    /* loaded from: classes2.dex */
    public class SharedPreferences$EditorC2192a implements SharedPreferences.Editor {

        /* renamed from: b */
        private ArrayList<OpEntry> f11219b = new ArrayList<>();

        public SharedPreferences$EditorC2192a() {
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, @Nullable String str2) {
            return m18508a(OpEntry.m18520e(str).m18525c(str2));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, @Nullable Set<String> set) {
            return m18508a(OpEntry.m18520e(str).m18535a(set));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            return m18508a(OpEntry.m18520e(str).m18523d(i));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            return m18508a(OpEntry.m18520e(str).m18530b(j));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            return m18508a(OpEntry.m18520e(str).m18532b(f));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            return m18508a(OpEntry.m18520e(str).m18528b(z));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            return m18508a(OpEntry.m18518f(str));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            return m18508a(OpEntry.m18519f());
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            Bundle bundle;
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList(PreferenceUtil.f11213g, m18509a());
            bundle2.putInt("key_op_type", 5);
            try {
                bundle = SharedPreferenceProxy.this.f11216c.getContentResolver().call(PreferenceUtil.f11209c, PreferenceUtil.f11211e, SharedPreferenceProxy.this.f11217d, bundle2);
            } catch (Exception e) {
                e.printStackTrace();
                bundle = null;
            }
            if (bundle == null) {
                return false;
            }
            return bundle.getBoolean(PreferenceUtil.f11213g, false);
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(PreferenceUtil.f11213g, m18509a());
            bundle.putInt("key_op_type", 6);
            try {
                SharedPreferenceProxy.this.f11216c.getContentResolver().call(PreferenceUtil.f11209c, PreferenceUtil.f11211e, SharedPreferenceProxy.this.f11217d, bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* renamed from: a */
        private SharedPreferences.Editor m18508a(OpEntry aVar) {
            synchronized (this) {
                this.f11219b.add(aVar);
            }
            return this;
        }

        /* renamed from: a */
        private ArrayList<Bundle> m18509a() {
            ArrayList<Bundle> arrayList;
            synchronized (this) {
                arrayList = new ArrayList<>(this.f11219b.size());
                Iterator<OpEntry> it = this.f11219b.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().m18521e());
                }
            }
            return arrayList;
        }
    }

    /* renamed from: a */
    public static SharedPreferences m18515a(@NonNull Context context, String str) {
        if (f11215b.get() == 0) {
            Bundle call = context.getContentResolver().call(PreferenceUtil.f11209c, PreferenceUtil.f11212f, "", (Bundle) null);
            int i = 0;
            if (call != null) {
                i = call.getInt(PreferenceUtil.f11213g);
            }
            if (i == 0) {
                return m18510c(context, str);
            }
            f11215b.set(Process.myPid() == i ? 1 : -1);
            return m18515a(context, str);
        } else if (f11215b.get() > 0) {
            return m18510c(context, str);
        } else {
            return m18512b(context, str);
        }
    }

    /* renamed from: b */
    private static SharedPreferences m18512b(@NonNull Context context, String str) {
        SharedPreferenceProxy cVar;
        synchronized (SharedPreferenceProxy.class) {
            if (f11214a == null) {
                f11214a = new ArrayMap();
            }
            cVar = f11214a.get(str);
            if (cVar == null) {
                cVar = new SharedPreferenceProxy(context.getApplicationContext(), str);
                f11214a.put(str, cVar);
            }
        }
        return cVar;
    }

    /* renamed from: c */
    private static SharedPreferences m18510c(@NonNull Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }
}
