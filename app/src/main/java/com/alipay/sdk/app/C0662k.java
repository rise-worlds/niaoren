package com.alipay.sdk.app;

import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import p110z1.C3876ar;
import p110z1.C3894au;

/* renamed from: com.alipay.sdk.app.k */
/* loaded from: classes.dex */
public class C0662k {

    /* renamed from: b */
    private static String f329b = "";

    /* renamed from: c */
    private static final C3894au.C3895a f330c = new C3894au.C3895a("com.eg.android.AlipayGphone", 73, C3876ar.f17433j);

    /* renamed from: d */
    private static final C3894au.C3895a f331d = new C3894au.C3895a(PayResultActivity.f273c, 40, "e6b1bdcb890370f2f2419fe06d0fdf7628ad0083d52da1ecfe991164711bbf9297e75353de96f1740695d07610567b1240549af9cbd87d06919ac31c859ad37ab6907c311b4756e1e208775989a4f691bff4bbbc58174d2a96b1d0d970a05114d7ee57dfc33b1bafaf6e0d820e838427018b6435f903df04ba7fd34d73f843df9434b164e0220baabb10c8978c3f4c6b7da79d8220a968356d15090dea07df9606f665cbec14d218dd3d691cce2866a58840971b6a57b76af88b1a65fdffd2c080281a6ab20be5879e0330eb7ff70871ce684e7174ada5dc3159c461375a0796b17ce7beca83cf34f65976d237aee993db48d34a4e344f4d8b7e99119168bdd7");

    /* renamed from: a */
    public static List<C3894au.C3895a> f328a = Collections.singletonList(f330c);

    /* renamed from: a */
    public static void m25289a(String str) {
        f329b = str;
        if (((str.hashCode() == 3331 && str.equals("hk")) ? (char) 0 : (char) 65535) != 0) {
            f328a = Collections.singletonList(f330c);
        } else {
            f328a = Collections.singletonList(f331d);
        }
    }

    /* renamed from: a */
    public static String m25290a() {
        return f329b;
    }

    /* renamed from: b */
    public static boolean m25288b() {
        return TextUtils.isEmpty(f329b) || TextUtils.equals("cn", f329b);
    }
}
