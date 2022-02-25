package jonathanfinerty.once;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
class PersistedMap {

    /* renamed from: a */
    private static final String f14678a = ",";

    /* renamed from: b */
    private SharedPreferences f14679b;

    /* renamed from: c */
    private final Map<String, List<Long>> f14680c = new ConcurrentHashMap();

    /* renamed from: d */
    private final AsyncSharedPreferenceLoader f14681d;

    public PersistedMap(Context context, String str) {
        this.f14681d = new AsyncSharedPreferenceLoader(context, PersistedMap.class.getSimpleName() + str);
    }

    /* renamed from: a */
    private void m14879a() {
        List<Long> list;
        if (this.f14679b == null) {
            this.f14679b = this.f14681d.get();
            for (String str : this.f14679b.getAll().keySet()) {
                try {
                    list = m14876b(this.f14679b.getString(str, null));
                } catch (ClassCastException unused) {
                    list = m14878a(str);
                }
                this.f14680c.put(str, list);
            }
        }
    }

    /* renamed from: a */
    private List<Long> m14878a(String str) {
        long j = this.f14679b.getLong(str, -1L);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        this.f14679b.edit().putString(str, m14877a(arrayList)).apply();
        return arrayList;
    }

    @NonNull
    public List<Long> get(String str) {
        m14879a();
        List<Long> list = this.f14680c.get(str);
        return list == null ? Collections.emptyList() : list;
    }

    public void put(String str, long j) {
        m14879a();
        List<Long> list = this.f14680c.get(str);
        if (list == null) {
            list = new ArrayList<>(1);
        }
        list.add(Long.valueOf(j));
        this.f14680c.put(str, list);
        SharedPreferences.Editor edit = this.f14679b.edit();
        edit.putString(str, m14877a(list));
        edit.apply();
    }

    public void remove(String str) {
        m14879a();
        this.f14680c.remove(str);
        SharedPreferences.Editor edit = this.f14679b.edit();
        edit.remove(str);
        edit.apply();
    }

    public void clear() {
        m14879a();
        this.f14680c.clear();
        SharedPreferences.Editor edit = this.f14679b.edit();
        edit.clear();
        edit.apply();
    }

    /* renamed from: a */
    private String m14877a(List<Long> list) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (Long l : list) {
            sb.append(str);
            sb.append(l);
            str = f14678a;
        }
        return sb.toString();
    }

    /* renamed from: b */
    private List<Long> m14876b(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }
        String[] split = str.split(f14678a);
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            arrayList.add(Long.valueOf(Long.parseLong(str2)));
        }
        return arrayList;
    }
}
