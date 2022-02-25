package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes2.dex */
public class TbsVideoUtils {

    /* renamed from: a */
    private static TbsVideoUtilsWizard f12991a;

    /* renamed from: a */
    private static void m16943a(Context context) {
        synchronized (TbsVideoUtils.class) {
            if (f12991a == null) {
                SDKEngine.m16828a(true).m16830a(context, false, false);
                TbsWizard a = SDKEngine.m16828a(true).m16832a();
                DexLoader dexLoader = null;
                if (a != null) {
                    dexLoader = a.m16629b();
                }
                if (dexLoader != null) {
                    f12991a = new TbsVideoUtilsWizard(dexLoader);
                }
            }
        }
    }

    public static void deleteVideoCache(Context context, String str) {
        m16943a(context);
        TbsVideoUtilsWizard pVar = f12991a;
        if (pVar != null) {
            pVar.m16641a(context, str);
        }
    }

    public static String getCurWDPDecodeType(Context context) {
        m16943a(context);
        TbsVideoUtilsWizard pVar = f12991a;
        return pVar != null ? pVar.m16642a(context) : "";
    }
}
