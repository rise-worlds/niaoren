package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TbsLinuxToolsJni {

    /* renamed from: a */
    private static boolean f12918a = false;

    /* renamed from: b */
    private static boolean f12919b = false;

    private native int ChmodInner(String str, String str2);

    /* renamed from: a */
    public int m17007a(String str, String str2) {
        if (f12918a) {
            return ChmodInner(str, str2);
        }
        TbsLog.m16532e("TbsLinuxToolsJni", "jni not loaded!", true);
        return -1;
    }

    public TbsLinuxToolsJni(Context context) {
        m17008a(context);
    }

    /* renamed from: a */
    private void m17008a(Context context) {
        File file;
        synchronized (TbsLinuxToolsJni.class) {
            TbsLog.m16531i("TbsLinuxToolsJni", "TbsLinuxToolsJni init mbIsInited is " + f12919b);
            if (!f12919b) {
                f12919b = true;
                if (TbsShareManager.isThirdPartyApp(context)) {
                    String a = TbsShareManager.m16965a();
                    if (a == null) {
                        a = TbsShareManager.m16958c(context);
                    }
                    file = new File(a);
                } else {
                    file = TbsInstaller.m16742a().m16678q(context);
                }
                if (file != null) {
                    if (!new File(file.getAbsolutePath() + File.separator + "liblinuxtoolsfortbssdk_jni.so").exists() && !TbsShareManager.isThirdPartyApp(context)) {
                        file = TbsInstaller.m16742a().m16679p(context);
                    }
                    if (file != null) {
                        TbsLog.m16531i("TbsLinuxToolsJni", "TbsLinuxToolsJni init tbsSharePath is " + file.getAbsolutePath());
                        System.load(file.getAbsolutePath() + File.separator + "liblinuxtoolsfortbssdk_jni.so");
                        f12918a = true;
                    }
                }
                ChmodInner("/checkChmodeExists", "700");
            }
        }
    }
}
