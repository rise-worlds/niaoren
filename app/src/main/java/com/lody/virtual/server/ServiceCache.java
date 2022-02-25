package com.lody.virtual.server;

import android.os.IBinder;
import com.lody.virtual.helper.collection.ArrayMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ServiceCache {
    private static final Map<String, IBinder> sCache = new ArrayMap(5);

    public static void addService(String str, IBinder iBinder) {
        sCache.put(str, iBinder);
    }

    public static IBinder removeService(String str) {
        return sCache.remove(str);
    }

    public static IBinder getService(String str) {
        return sCache.get(str);
    }
}
