package com.lidroid.xutils.http;

import android.text.TextUtils;
import com.lidroid.xutils.cache.LruMemoryCache;
import com.lidroid.xutils.http.client.HttpRequest;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class HttpCache {
    private static final int DEFAULT_CACHE_SIZE = 102400;
    private static final long DEFAULT_EXPIRY_TIME = 60000;
    private static long defaultExpiryTime = 60000;
    private static final ConcurrentHashMap<String, Boolean> httpMethod_enabled_map;
    private int cacheSize;
    private final LruMemoryCache<String, String> mMemoryCache;

    public HttpCache() {
        this(102400, 60000L);
    }

    public HttpCache(int i, long j) {
        this.cacheSize = 102400;
        this.cacheSize = i;
        defaultExpiryTime = j;
        this.mMemoryCache = new LruMemoryCache<String, String>(this.cacheSize) { // from class: com.lidroid.xutils.http.HttpCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            public int sizeOf(String str, String str2) {
                if (str2 == null) {
                    return 0;
                }
                return str2.length();
            }
        };
    }

    public void setCacheSize(int i) {
        this.mMemoryCache.setMaxSize(i);
    }

    public static void setDefaultExpiryTime(long j) {
        defaultExpiryTime = j;
    }

    public static long getDefaultExpiryTime() {
        return defaultExpiryTime;
    }

    public void put(String str, String str2) {
        put(str, str2, defaultExpiryTime);
    }

    public void put(String str, String str2, long j) {
        if (str != null && str2 != null && j >= 1) {
            this.mMemoryCache.put(str, str2, System.currentTimeMillis() + j);
        }
    }

    public String get(String str) {
        if (str != null) {
            return this.mMemoryCache.get(str);
        }
        return null;
    }

    public void clear() {
        this.mMemoryCache.evictAll();
    }

    public boolean isEnabled(HttpRequest.HttpMethod httpMethod) {
        Boolean bool;
        if (httpMethod == null || (bool = httpMethod_enabled_map.get(httpMethod.toString())) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isEnabled(String str) {
        Boolean bool;
        if (!TextUtils.isEmpty(str) && (bool = httpMethod_enabled_map.get(str.toUpperCase())) != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public void setEnabled(HttpRequest.HttpMethod httpMethod, boolean z) {
        httpMethod_enabled_map.put(httpMethod.toString(), Boolean.valueOf(z));
    }

    static {
        ConcurrentHashMap<String, Boolean> concurrentHashMap = new ConcurrentHashMap<>(10);
        httpMethod_enabled_map = concurrentHashMap;
        concurrentHashMap.put(HttpRequest.HttpMethod.GET.toString(), true);
    }
}
