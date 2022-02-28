package com.lody.virtual.server.am;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.SparseArray;
import com.lody.virtual.client.core.VirtualCore;
import java.util.HashMap;
import java.util.WeakHashMap;

/* renamed from: com.lody.virtual.server.am.AttributeCache */
/* loaded from: classes.dex */
public final class AttributeCache {
    private static final AttributeCache sInstance = new AttributeCache();
    private final WeakHashMap<String, Package> mPackages = new WeakHashMap<>();
    private final Configuration mConfiguration = new Configuration();

    private AttributeCache() {
    }

    public static AttributeCache instance() {
        return sInstance;
    }

    public void removePackage(String str) {
        synchronized (this) {
            this.mPackages.remove(str);
        }
    }

    public void updateConfiguration(Configuration configuration) {
        synchronized (this) {
            if ((this.mConfiguration.updateFrom(configuration) & (-1073741985)) != 0) {
                this.mPackages.clear();
            }
        }
    }

    public Entry get(String str, int i, int[] iArr) {
        HashMap hashMap;
        Entry entry;
        synchronized (this) {
            Package r0 = this.mPackages.get(str);
            if (r0 != null) {
                hashMap = (HashMap) r0.mMap.get(i);
                if (!(hashMap == null || (entry = (Entry) hashMap.get(iArr)) == null)) {
                    return entry;
                }
            } else {
                Package r2 = new Package(VirtualCore.get().getResources(str));
                this.mPackages.put(str, r2);
                hashMap = null;
                r0 = r2;
            }
            if (hashMap == null) {
                hashMap = new HashMap();
                r0.mMap.put(i, hashMap);
            }
            try {
                Entry entry2 = new Entry(r0.resources, r0.resources.newTheme().obtainStyledAttributes(i, iArr));
                hashMap.put(iArr, entry2);
                return entry2;
            } catch (Resources.NotFoundException unused) {
                return null;
            }
        }
    }

    /* renamed from: com.lody.virtual.server.am.AttributeCache$Package */
    /* loaded from: classes.dex */
    public static final class Package {
        private final SparseArray<HashMap<int[], Entry>> mMap = new SparseArray<>();
        public final Resources resources;

        public Package(Resources resources) {
            this.resources = resources;
        }
    }

    /* renamed from: com.lody.virtual.server.am.AttributeCache$Entry */
    /* loaded from: classes.dex */
    public static final class Entry {
        public final TypedArray array;
        public final Resources resource;

        public Entry(Resources resources, TypedArray typedArray) {
            this.resource = resources;
            this.array = typedArray;
        }
    }
}
