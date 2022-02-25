package p110z1;

import android.os.Environment;
import java.io.File;

/* compiled from: Constants.java */
/* renamed from: z1.acf */
/* loaded from: classes3.dex */
public class acf {

    /* renamed from: A */
    public static final String f15162A = "failure_update_must_notice_xposed";

    /* renamed from: B */
    public static final String f15163B = "failure_update_version_unzip";

    /* renamed from: C */
    public static final String f15164C = "no_update_version";

    /* renamed from: D */
    public static final String f15165D = "initVirtualMachine";

    /* renamed from: E */
    public static final String f15166E = "ro.arch";

    /* renamed from: F */
    public static final String f15167F = "ro.hardware";

    /* renamed from: G */
    public static final String f15168G = "ro.product.board";

    /* renamed from: H */
    public static final String f15169H = "ro.board.platform";

    /* renamed from: I */
    public static final String f15170I = "IS_LCD";

    /* renamed from: J */
    public static final String f15171J = "have_apk_update_version";

    /* renamed from: K */
    public static final String f15172K = "have_rom_update_version";

    /* renamed from: L */
    public static final String f15173L = "update_download_success";

    /* renamed from: M */
    public static final String f15174M = "Unpack";

    /* renamed from: N */
    public static final long f15175N = 300000;

    /* renamed from: a */
    public static final String f15176a = Environment.getExternalStorageDirectory().getPath() + File.separatorChar + "xnkj" + File.separatorChar;

    /* renamed from: b */
    public static final String f15177b;

    /* renamed from: c */
    public static final String f15178c = "root_file_path";

    /* renamed from: d */
    public static final String f15179d = "root_file_version";

    /* renamed from: e */
    public static final String f15180e = "root_patch_path";

    /* renamed from: f */
    public static final String f15181f = "root_patch_version";

    /* renamed from: g */
    public static final String f15182g = "lcd_display_param";

    /* renamed from: h */
    public static final String f15183h = "rom_start_overtime";

    /* renamed from: i */
    public static final String f15184i = "rom_start_success";

    /* renamed from: j */
    public static final String f15185j = "START_VIRTUAL";

    /* renamed from: k */
    public static final String f15186k = "isInitBoot";

    /* renamed from: l */
    public static final String f15187l = "Boot_Duration";

    /* renamed from: m */
    public static final String f15188m = "GET_INSTALLED_APP";

    /* renamed from: n */
    public static final String f15189n = "GET_ALL_APK";

    /* renamed from: o */
    public static final String f15190o = "ro.product.cpu.abilist64";

    /* renamed from: p */
    public static final String f15191p = "32";

    /* renamed from: q */
    public static final String f15192q = "64";

    /* renamed from: r */
    public static final int f15193r = 99;

    /* renamed from: s */
    public static final int f15194s = 97;

    /* renamed from: t */
    public static final int f15195t = 98;

    /* renamed from: u */
    public static final int f15196u = 95;

    /* renamed from: v */
    public static final int f15197v = 93;

    /* renamed from: w */
    public static final int f15198w = 94;

    /* renamed from: x */
    public static final int f15199x = 96;

    /* renamed from: y */
    public static final String f15200y = "failure_update_version";

    /* renamed from: z */
    public static final String f15201z = "failure_update_must_notice";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(f15176a);
        sb.append("apk");
        sb.append(File.separatorChar);
        f15177b = sb.toString();
    }
}
