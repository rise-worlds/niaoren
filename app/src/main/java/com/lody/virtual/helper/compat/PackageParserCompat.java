package com.lody.virtual.helper.compat;

import android.content.p001pm.PackageParser;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Process;
import android.util.DisplayMetrics;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.p061os.VUserHandle;
import java.io.File;
import p110z1.PackageParserJellyBean;
import p110z1.PackageParserJellyBean17;
import p110z1.PackageParserLollipop;
import p110z1.PackageParserLollipop22;
import p110z1.PackageParserMarshmallow;
import p110z1.PackageParserNougat;
import p110z1.PackageParserPie;
import p110z1.PackageUserState;

/* loaded from: classes.dex */
public class PackageParserCompat {
    private static final Object sUserState;
    public static final int[] GIDS = VirtualCore.get().getGids();
    private static final int API_LEVEL = Build.VERSION.SDK_INT;
    private static final int myUserId = VUserHandle.getUserId(Process.myUid());

    static {
        sUserState = API_LEVEL >= 17 ? PackageUserState.ctor.newInstance() : null;
    }

    public static PackageParser createParser(File file) {
        int i = API_LEVEL;
        if (i >= 23) {
            return PackageParserMarshmallow.ctor.newInstance();
        }
        if (i >= 22) {
            return PackageParserLollipop22.ctor.newInstance();
        }
        if (i >= 21) {
            return PackageParserLollipop.ctor.newInstance();
        }
        return i >= 17 ? PackageParserJellyBean17.ctor.newInstance(file.getAbsolutePath()) : i >= 16 ? PackageParserJellyBean.ctor.newInstance(file.getAbsolutePath()) : p110z1.PackageParser.ctor.newInstance(file.getAbsolutePath());
    }

    public static PackageParser.Package parsePackage(PackageParser packageParser, File file, int i) throws Throwable {
        int i2 = API_LEVEL;
        if (i2 >= 23) {
            return PackageParserMarshmallow.parsePackage.callWithException(packageParser, file, Integer.valueOf(i));
        }
        if (i2 >= 22) {
            return PackageParserLollipop22.parsePackage.callWithException(packageParser, file, Integer.valueOf(i));
        }
        if (i2 >= 21) {
            return PackageParserLollipop.parsePackage.callWithException(packageParser, file, Integer.valueOf(i));
        }
        if (i2 >= 17) {
            return PackageParserJellyBean17.parsePackage.callWithException(packageParser, file, null, new DisplayMetrics(), Integer.valueOf(i));
        }
        if (i2 >= 16) {
            return PackageParserJellyBean.parsePackage.callWithException(packageParser, file, null, new DisplayMetrics(), Integer.valueOf(i));
        }
        return p110z1.PackageParser.parsePackage.callWithException(packageParser, file, null, new DisplayMetrics(), Integer.valueOf(i));
    }

    public static ServiceInfo generateServiceInfo(PackageParser.Service service, int i) {
        int i2 = API_LEVEL;
        return i2 >= 23 ? PackageParserMarshmallow.generateServiceInfo.call(service, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 22 ? PackageParserLollipop22.generateServiceInfo.call(service, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 21 ? PackageParserLollipop.generateServiceInfo.call(service, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 17 ? PackageParserJellyBean17.generateServiceInfo.call(service, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 16 ? PackageParserJellyBean.generateServiceInfo.call(service, Integer.valueOf(i), false, 1, Integer.valueOf(myUserId)) : p110z1.PackageParser.generateServiceInfo.call(service, Integer.valueOf(i));
    }

    public static ApplicationInfo generateApplicationInfo(PackageParser.Package r6, int i) {
        int i2 = API_LEVEL;
        return i2 >= 23 ? PackageParserMarshmallow.generateApplicationInfo.call(r6, Integer.valueOf(i), sUserState) : i2 >= 22 ? PackageParserLollipop22.generateApplicationInfo.call(r6, Integer.valueOf(i), sUserState) : i2 >= 21 ? PackageParserLollipop.generateApplicationInfo.call(r6, Integer.valueOf(i), sUserState) : i2 >= 17 ? PackageParserJellyBean17.generateApplicationInfo.call(r6, Integer.valueOf(i), sUserState) : i2 >= 16 ? PackageParserJellyBean.generateApplicationInfo.call(r6, Integer.valueOf(i), false, 1) : p110z1.PackageParser.generateApplicationInfo.call(r6, Integer.valueOf(i));
    }

    public static ActivityInfo generateActivityInfo(PackageParser.Activity activity, int i) {
        int i2 = API_LEVEL;
        return i2 >= 23 ? PackageParserMarshmallow.generateActivityInfo.call(activity, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 22 ? PackageParserLollipop22.generateActivityInfo.call(activity, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 21 ? PackageParserLollipop.generateActivityInfo.call(activity, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 17 ? PackageParserJellyBean17.generateActivityInfo.call(activity, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 16 ? PackageParserJellyBean.generateActivityInfo.call(activity, Integer.valueOf(i), false, 1, Integer.valueOf(myUserId)) : p110z1.PackageParser.generateActivityInfo.call(activity, Integer.valueOf(i));
    }

    public static ProviderInfo generateProviderInfo(PackageParser.Provider provider, int i) {
        int i2 = API_LEVEL;
        return i2 >= 23 ? PackageParserMarshmallow.generateProviderInfo.call(provider, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 22 ? PackageParserLollipop22.generateProviderInfo.call(provider, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 21 ? PackageParserLollipop.generateProviderInfo.call(provider, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 17 ? PackageParserJellyBean17.generateProviderInfo.call(provider, Integer.valueOf(i), sUserState, Integer.valueOf(myUserId)) : i2 >= 16 ? PackageParserJellyBean.generateProviderInfo.call(provider, Integer.valueOf(i), false, 1, Integer.valueOf(myUserId)) : p110z1.PackageParser.generateProviderInfo.call(provider, Integer.valueOf(i));
    }

    public static PackageInfo generatePackageInfo(PackageParser.Package r11, int i, long j, long j2) {
        int i2 = API_LEVEL;
        return i2 >= 23 ? PackageParserMarshmallow.generatePackageInfo.call(r11, GIDS, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), null, sUserState) : i2 >= 21 ? PackageParserLollipop22.generatePackageInfo != null ? PackageParserLollipop22.generatePackageInfo.call(r11, GIDS, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), null, sUserState) : PackageParserLollipop.generatePackageInfo.call(r11, GIDS, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), null, sUserState) : i2 >= 17 ? PackageParserJellyBean17.generatePackageInfo.call(r11, GIDS, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), null, sUserState) : i2 >= 16 ? PackageParserJellyBean.generatePackageInfo.call(r11, GIDS, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), null) : p110z1.PackageParser.generatePackageInfo.call(r11, GIDS, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2));
    }

    public static void collectCertificates(PackageParser packageParser, PackageParser.Package r6, int i) throws Throwable {
        if (BuildCompat.isPie()) {
            PackageParserPie.collectCertificates.callWithException(r6, true);
            return;
        }
        int i2 = API_LEVEL;
        if (i2 >= 24) {
            PackageParserNougat.collectCertificates.callWithException(r6, Integer.valueOf(i));
        } else if (i2 >= 23) {
            PackageParserMarshmallow.collectCertificates.callWithException(packageParser, r6, Integer.valueOf(i));
        } else if (i2 >= 22) {
            PackageParserLollipop22.collectCertificates.callWithException(packageParser, r6, Integer.valueOf(i));
        } else if (i2 >= 21) {
            PackageParserLollipop.collectCertificates.callWithException(packageParser, r6, Integer.valueOf(i));
        } else if (i2 >= 17) {
            PackageParserJellyBean17.collectCertificates.callWithException(packageParser, r6, Integer.valueOf(i));
        } else if (i2 >= 16) {
            PackageParserJellyBean.collectCertificates.callWithException(packageParser, r6, Integer.valueOf(i));
        } else {
            p110z1.PackageParser.collectCertificates.call(packageParser, r6, Integer.valueOf(i));
        }
    }
}
