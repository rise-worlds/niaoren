package com.lbd.xj.utils.vmlog;

import com.lbd.xj.app.AppConfig;
import java.io.File;

/* renamed from: com.lbd.xj.utils.vmlog.b */
/* loaded from: classes.dex */
public class VMLogUtils {

    /* renamed from: c */
    private static VMLogUtils f9974c;

    /* renamed from: b */
    private String f9976b = "/data/data/" + AppConfig.f9441h + "/osimg/socket/logfile";

    /* renamed from: a */
    private String f9975a = "/data/data/" + AppConfig.f9441h + "/osimg/socket/log";

    /* renamed from: c */
    public void m19276c() {
    }

    /* renamed from: a */
    public void m19278a() {
        File file = new File(this.f9975a);
        if (file.exists()) {
            file.delete();
        }
    }

    /* renamed from: b */
    public static VMLogUtils m19277b() {
        if (f9974c == null) {
            f9974c = new VMLogUtils();
        }
        return f9974c;
    }
}
