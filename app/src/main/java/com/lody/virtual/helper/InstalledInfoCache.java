package com.lody.virtual.helper;

import android.graphics.drawable.Drawable;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.ACache;
import java.io.Serializable;

/* loaded from: classes.dex */
public class InstalledInfoCache {
    private static ACache diskCache = ACache.get(VirtualCore.get().getContext(), "AppInfoCache");

    public static void save(CacheItem cacheItem) {
        ACache aCache = diskCache;
        aCache.put(CacheItem.INFO_CACHE_PREFIX + cacheItem.packageName, cacheItem);
        cacheItem.saveIcon();
    }

    public static CacheItem get(String str) {
        ACache aCache = diskCache;
        return (CacheItem) aCache.getAsObject(CacheItem.INFO_CACHE_PREFIX + str);
    }

    @Keep
    /* loaded from: classes.dex */
    public static class CacheItem implements Serializable {
        public static final transient String ICON_CACHE_PREFIX = "va_installed_icon_cache@";
        public static final transient String INFO_CACHE_PREFIX = "va_installed_info_cache@";
        private static final long serialVersionUID = 1;
        public transient Drawable icon;
        public String label;
        public String packageName;

        public CacheItem(String str, String str2, Drawable drawable) {
            this.packageName = str;
            this.label = str2;
            this.icon = drawable;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public String getLabel() {
            return this.label;
        }

        public synchronized void saveIcon() {
            if (this.icon != null) {
                ACache aCache = InstalledInfoCache.diskCache;
                aCache.put(ICON_CACHE_PREFIX + this.packageName, this.icon);
            }
        }

        public synchronized Drawable getIcon() {
            if (this.icon == null) {
                ACache aCache = InstalledInfoCache.diskCache;
                this.icon = aCache.getAsDrawable(ICON_CACHE_PREFIX + this.packageName);
            }
            return this.icon;
        }
    }
}
