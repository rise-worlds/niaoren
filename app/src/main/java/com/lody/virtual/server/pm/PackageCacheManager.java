package com.lody.virtual.server.pm;

import com.lody.virtual.helper.collection.ArrayMap;
import com.lody.virtual.server.pm.parser.PackageParserEx;
import com.lody.virtual.server.pm.parser.VPackage;

/* renamed from: com.lody.virtual.server.pm.PackageCacheManager */
/* loaded from: classes.dex */
public class PackageCacheManager {
    static final ArrayMap<String, VPackage> PACKAGE_CACHE = new ArrayMap<>();

    public static int size() {
        int size;
        synchronized (PACKAGE_CACHE) {
            size = PACKAGE_CACHE.size();
        }
        return size;
    }

    public static void put(VPackage vPackage, PackageSetting packageSetting) {
        synchronized (PackageCacheManager.class) {
            PackageParserEx.initApplicationInfoBase(packageSetting, vPackage);
            PACKAGE_CACHE.put(vPackage.packageName, vPackage);
            vPackage.mExtras = packageSetting;
            VPackageManagerService.get().analyzePackageLocked(vPackage);
        }
    }

    public static VPackage get(String str) {
        VPackage vPackage;
        synchronized (PackageCacheManager.class) {
            vPackage = PACKAGE_CACHE.get(str);
        }
        return vPackage;
    }

    public static PackageSetting getSetting(String str) {
        synchronized (PackageCacheManager.class) {
            VPackage vPackage = PACKAGE_CACHE.get(str);
            if (vPackage == null) {
                return null;
            }
            return (PackageSetting) vPackage.mExtras;
        }
    }

    public static VPackage remove(String str) {
        VPackage remove;
        synchronized (PackageCacheManager.class) {
            VPackageManagerService.get().deletePackageLocked(str);
            remove = PACKAGE_CACHE.remove(str);
        }
        return remove;
    }
}
