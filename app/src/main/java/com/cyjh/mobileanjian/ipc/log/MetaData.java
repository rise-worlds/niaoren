package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.utils.RomUtils;
import com.cyjh.p045mq.p049d.PackageUtils;
import com.stripe.android.model.Card;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.p105io.FileUtils;

/* renamed from: com.cyjh.mobileanjian.ipc.log.b */
/* loaded from: classes.dex */
public final class MetaData {

    /* renamed from: n */
    private static MetaData f8285n = null;

    /* renamed from: o */
    private static final File f8286o = new File(Environment.getExternalStorageDirectory(), ".oldmarkuser");

    /* renamed from: p */
    private static final String f8287p = "com.cyjh.mobileanjian";

    /* renamed from: a */
    public final String f8288a;

    /* renamed from: b */
    public final String f8289b = Build.VERSION.RELEASE;

    /* renamed from: c */
    public final String f8290c;

    /* renamed from: d */
    public final String f8291d;

    /* renamed from: e */
    public final String f8292e;

    /* renamed from: f */
    public final String f8293f;

    /* renamed from: g */
    public final String f8294g;

    /* renamed from: h */
    public final String f8295h;

    /* renamed from: i */
    public final boolean f8296i;

    /* renamed from: j */
    public final String f8297j;

    /* renamed from: k */
    public final String f8298k;

    /* renamed from: l */
    public final boolean f8299l;

    /* renamed from: m */
    public final boolean f8300m;

    /* renamed from: a */
    private static void m20984a(Context context, boolean z, String str, String str2) {
        if (f8285n == null) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            MetaData bVar = new MetaData(context, z, str, str2);
            f8285n = bVar;
            bVar.toString();
        }
    }

    /* renamed from: a */
    public static MetaData m20986a() {
        return f8285n;
    }

    private MetaData(Context context, boolean z, String str, String str2) {
        String str3;
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            str3 = "UNKNOWN";
        } else {
            str3 = connectionInfo.getMacAddress() == null ? "UNKNOWN" : connectionInfo.getMacAddress();
        }
        this.f8290c = m20983a(str3);
        this.f8291d = RomUtils.getKnownRomVersion();
        if (RomUtils.isEmulator()) {
            this.f8288a = this.f8291d;
        } else {
            this.f8288a = String.format("%s %s", Build.BRAND, Build.MODEL);
        }
        this.f8294g = PackageUtils.m20443a(context);
        this.f8298k = PackageUtils.m20442b(context);
        this.f8293f = context.getPackageName();
        this.f8300m = "com.cyjh.mobileanjian".equals(this.f8293f);
        this.f8296i = z;
        this.f8295h = str;
        this.f8297j = str2;
        boolean z2 = !f8286o.exists();
        this.f8299l = z2;
        if (z2) {
            try {
                f8286o.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str4 = null;
        try {
            File file = new File("/proc/version");
            if (file.exists() && file.canRead()) {
                str4 = FileUtils.readFileToString(file);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.f8292e = TextUtils.isEmpty(str4) ? Card.f12006h : str4;
    }

    /* renamed from: a */
    private static String m20985a(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return (connectionInfo == null || connectionInfo.getMacAddress() == null) ? "UNKNOWN" : connectionInfo.getMacAddress();
    }

    /* renamed from: a */
    private static final String m20983a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = ResultTypeConstant.f7213z + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("手机型号: ");
        sb.append(this.f8288a);
        sb.append("\nLinux: ");
        sb.append(this.f8292e);
        sb.append("\nMAC的MD5: ");
        sb.append(this.f8290c);
        sb.append("\n安卓版本: ");
        sb.append(this.f8289b);
        sb.append("\n定制安卓: ");
        sb.append(this.f8291d);
        sb.append("\n应用名称: ");
        sb.append(this.f8294g);
        sb.append("\n应用包名: ");
        sb.append(this.f8293f);
        sb.append("\n应用版本: ");
        sb.append(this.f8298k);
        sb.append("\n唯一标识: ");
        sb.append(this.f8295h);
        sb.append("\n精灵版本: ");
        sb.append(this.f8297j);
        sb.append("\n产品形态: UNKNOWN\n是否付费: ");
        sb.append(this.f8296i ? "免费" : "收费");
        sb.append("\n");
        return sb.toString();
    }
}
