package jonathanfinerty.once;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
class PersistedSet {

    /* renamed from: a */
    private static final String f14682a = "PersistedSetValues";

    /* renamed from: b */
    private static final String f14683b = ",";

    /* renamed from: c */
    private SharedPreferences f14684c;

    /* renamed from: d */
    private Set<String> f14685d = new HashSet();

    /* renamed from: e */
    private final AsyncSharedPreferenceLoader f14686e;

    public PersistedSet(Context context, String str) {
        this.f14686e = new AsyncSharedPreferenceLoader(context, PersistedSet.class.getSimpleName() + str);
    }

    /* renamed from: a */
    private void m14875a() {
        if (this.f14684c == null) {
            this.f14684c = this.f14686e.get();
            if (Build.VERSION.SDK_INT >= 11) {
                this.f14685d = this.f14684c.getStringSet(f14682a, new HashSet());
            } else {
                this.f14685d = new HashSet(m14874a(this.f14684c.getString(f14682a, null)));
            }
        }
    }

    public void put(String str) {
        m14875a();
        this.f14685d.add(str);
        m14872b();
    }

    public boolean contains(String str) {
        m14875a();
        return this.f14685d.contains(str);
    }

    public void remove(String str) {
        m14875a();
        this.f14685d.remove(str);
        m14872b();
    }

    public void clear() {
        m14875a();
        this.f14685d.clear();
        m14872b();
    }

    /* renamed from: b */
    private void m14872b() {
        SharedPreferences.Editor edit = this.f14684c.edit();
        if (Build.VERSION.SDK_INT >= 11) {
            edit.putStringSet(f14682a, this.f14685d);
        } else {
            edit.putString(f14682a, m14873a(this.f14685d));
        }
        edit.apply();
    }

    /* renamed from: a */
    private String m14873a(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (String str2 : set) {
            sb.append(str);
            sb.append(str2);
            str = f14683b;
        }
        return sb.toString();
    }

    @NonNull
    /* renamed from: a */
    private Set<String> m14874a(@Nullable String str) {
        if (str == null) {
            return new HashSet();
        }
        return new HashSet(Arrays.asList(str.split(f14683b)));
    }
}
