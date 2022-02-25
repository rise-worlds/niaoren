package com.goldcoast.sdk.p052c;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.goldcoast.sdk.p050a.CPUInfo;
import com.goldcoast.sdk.p050a.Device;
import com.goldcoast.sdk.p050a.Phone;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import p110z1.CheckPhoneInfoTools;
import p110z1.acf;

/* compiled from: DeviceUtils.java */
/* renamed from: com.goldcoast.sdk.c.d */
/* loaded from: classes.dex */
public final class C1391d {

    /* renamed from: a */
    private static C1391d f9009a;

    /* renamed from: b */
    private static Context f9010b;

    private C1391d() {
    }

    /* renamed from: a */
    public static C1391d m20333a(Context context) {
        f9010b = context;
        if (f9009a == null) {
            f9009a = new C1391d();
        }
        return f9009a;
    }

    /* renamed from: a */
    public static Device m20334a() {
        Device bVar = new Device();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String str = "";
        String str2 = "";
        Phone c = m20331c();
        try {
            hashMap2.put(CheckPhoneInfoTools.f15426b, "");
            IOUtils.m20323a(new FileInputStream("/proc/meminfo"), hashMap2);
            str = (String) hashMap2.get(CheckPhoneInfoTools.f15426b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CPUInfo aVar = null;
        try {
            str2 = IOUtils.m20323a(new FileInputStream("/proc/version"), null).split(ExpandableTextView.f6958c)[2];
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            IOUtils.m20323a(new FileInputStream("/proc/cpuinfo"), hashMap);
            aVar = new CPUInfo(hashMap);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        bVar.m20388a(aVar);
        bVar.m20387a(c);
        bVar.m20383b(str2);
        bVar.m20385a(str);
        bVar.m20382c(m20330d());
        bVar.m20380e(m20332b());
        bVar.m20381d(m20329e());
        bVar.m20378g(CPUTypeUtil.m20343a());
        bVar.m20379f(String.valueOf(System.currentTimeMillis()));
        return bVar;
    }

    /* renamed from: d */
    private static String m20330d() {
        try {
            return ((TelephonyManager) f9010b.getSystemService(ShippingInfoWidget.f12563f)).getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultTypeConstant.f7213z;
        }
    }

    /* renamed from: b */
    public static String m20332b() {
        try {
            return Settings.Secure.getString(f9010b.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultTypeConstant.f7213z;
        }
    }

    /* renamed from: e */
    private static String m20329e() {
        try {
            return ((WifiManager) f9010b.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultTypeConstant.f7213z;
        }
    }

    /* renamed from: c */
    public static Phone m20331c() {
        HashMap hashMap = new HashMap();
        String[] strArr = {"ro.product.brand", "ro.product.name", "ro.product.model", "ro.build.fingerprint", "ro.build.version.sdk", "ro.build.version.release", "ro.build.date", "ro.build.date.utc", "ro.boot.cpuid", "ro.btconfig.vendor", "persist.sys.timezone", "persist.sys.country", "persist.sys.language", "persist.sys.dalvik.vm.lib", "ro.build.description", "ro.runtime.firstboot", "ro.serialno", acf.f15167F, acf.f15168G, "ro.product.locale.language", "ro.product.locale.region", "ro.product.cpu.abi", acf.f15169H, "ro.build.selinux", "ro.build.selinux.enforce"};
        for (int i = 0; i < 25; i++) {
            hashMap.put(strArr[i], "");
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            hashMap.put(entry.getKey(), C1393i.m20313a((String) entry.getKey()));
        }
        return new Phone(hashMap);
    }
}
