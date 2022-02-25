package com.cyjh.mobileanjian.ipc.utils;

import android.os.Build;
import java.io.File;
import java.io.FileInputStream;

/* loaded from: classes.dex */
public class RomUtils {

    /* renamed from: a */
    private static final BuildProperties f8676a = new BuildProperties();

    public static boolean isMIUI() {
        return f8676a.m20668a((Object) "ro.miui.ui.version.name");
    }

    public static int getMUIUVersion() {
        if (!isMIUI()) {
            return -1;
        }
        String a = f8676a.m20666a("ro.miui.ui.version.name", "V0");
        if (a.equals("V8")) {
            return 8;
        }
        if (a.equals("V7")) {
            return 7;
        }
        if (a.equals("V6")) {
            return 6;
        }
        return a.equals("V5") ? 5 : 0;
    }

    public static String getMUIUDetailVersion() {
        return !isMIUI() ? "" : String.format("MIUI %s %s", f8676a.m20667a("ro.miui.ui.version.name"), Build.VERSION.INCREMENTAL);
    }

    public static boolean isFlyme() {
        return f8676a.m20668a((Object) "ro.flyme.published");
    }

    public static String getFlymeVersion() {
        return Build.DISPLAY;
    }

    public static boolean isEmui() {
        return f8676a.m20668a((Object) "ro.build.version.emui");
    }

    public static String getEmuiVersion() {
        return f8676a.m20667a("ro.build.version.emui");
    }

    public static boolean isOppoR9S() {
        String a = f8676a.m20666a("ro.product.name", "");
        String a2 = f8676a.m20666a("ro.product.model", "");
        return a != null && a.contains("R9s") && a2 != null && a2.contains("R9s");
    }

    public static boolean isHaimawanVM() {
        return f8676a.m20668a((Object) "droid4x.inited") || f8676a.m20668a((Object) "ro.droid4x.host.mac") || f8676a.m20668a((Object) "droid4x.battery.status") || f8676a.m20668a((Object) "persist.droid4x.op_alpha");
    }

    public static boolean isTiantianVM() {
        return f8676a.m20668a((Object) "ttVM.inited") || f8676a.m20668a((Object) "ttvmd.battery.status") || f8676a.m20668a((Object) "ttVM.vbox_dpi") || f8676a.m20668a((Object) "ttvmd.battery.mode");
    }

    public static boolean isNoxVM() {
        return f8676a.m20668a((Object) "nox.inited") || f8676a.m20668a((Object) "nox.vbox_dpi") || f8676a.m20668a((Object) "ro.nox.host.mac") || f8676a.m20668a((Object) "nox.battery.status");
    }

    public static boolean isXiaoyaoVM() {
        return f8676a.m20668a((Object) "microvirt.inited") || f8676a.m20668a((Object) "microvirt.channel") || f8676a.m20668a((Object) "ro.microvirt.hmac") || f8676a.m20668a((Object) "microvirt.vbox_dpi");
    }

    public static boolean isXXZS() {
        return f8676a.m20668a((Object) "ro.xxzs.DeviceId") || f8676a.m20668a((Object) "ro.xxzs.origDeviceId");
    }

    public static boolean isEmulator() {
        return isHaimawanVM() || isTiantianVM() || isNoxVM() || isXiaoyaoVM() || isXXZS();
    }

    public static String getKnownRomVersion() {
        if (isMIUI()) {
            return getMUIUDetailVersion();
        }
        if (isFlyme()) {
            return getFlymeVersion();
        }
        if (isEmui()) {
            return getEmuiVersion();
        }
        return isHaimawanVM() ? "海马玩模拟器" : isTiantianVM() ? "天天模拟器" : isNoxVM() ? "夜神模拟器" : isXiaoyaoVM() ? "逍遥模拟器" : isXXZS() ? "猩猩助手" : "undefined";
    }

    public static boolean isARMCpu() {
        byte[] bArr = new byte[20];
        try {
            if (new FileInputStream(new File("/system/lib/libc.so")).read(bArr) != -1) {
                return bArr[18] != 3;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
