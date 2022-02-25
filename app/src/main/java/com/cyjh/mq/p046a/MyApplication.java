package com.cyjh.mq.p046a;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.RootProgressListener;
import com.cyjh.mq.service.IpcService;
import java.io.File;

/* renamed from: com.cyjh.mq.a.a */
/* loaded from: classes.dex */
public final class MyApplication {

    /* renamed from: a */
    public static final String f8782a = "start_eventsrv";

    /* renamed from: b */
    public static final String f8783b = "start_eventsrvR";

    /* renamed from: c */
    public static final String f8784c = ".event.localserver";

    /* renamed from: d */
    public static final String f8785d = "com.cyjh.mobileanjian.ipc.ClientService";

    /* renamed from: e */
    public static final String f8786e = "DaemonClient.zip";

    /* renamed from: f */
    public static final String f8787f = "libmqm.so";

    /* renamed from: g */
    public static Application f8788g;

    /* renamed from: h */
    public static OnKeyEventListener f8789h;

    /* renamed from: i */
    public static RootProgressListener f8790i;

    /* renamed from: j */
    public static OnEngineStartCallback f8791j;

    /* renamed from: a */
    private static void m20564a(Application application, OnKeyEventListener onKeyEventListener, RootProgressListener rootProgressListener, OnEngineStartCallback onEngineStartCallback) {
        if (f8788g == null) {
            f8788g = application;
            f8789h = onKeyEventListener;
            f8790i = rootProgressListener;
            f8791j = onEngineStartCallback;
            application.startService(new Intent(application, IpcService.class));
        }
    }

    /* renamed from: b */
    private static void m20563b() {
        Application application = f8788g;
        if (application != null) {
            f8788g.stopService(new Intent(application, IpcService.class));
        }
    }

    /* renamed from: c */
    private static Application m20562c() {
        return f8788g;
    }

    /* renamed from: d */
    private static Context m20561d() {
        return f8788g.getApplicationContext();
    }

    /* renamed from: e */
    private static File m20560e() {
        return new File(f8788g.getFilesDir(), f8782a);
    }

    /* renamed from: a */
    public static File m20565a() {
        return new File(f8788g.getFilesDir(), f8783b);
    }

    /* renamed from: f */
    private static OnKeyEventListener m20559f() {
        return f8789h;
    }

    /* renamed from: g */
    private static RootProgressListener m20558g() {
        return f8790i;
    }

    /* renamed from: h */
    private static OnEngineStartCallback m20557h() {
        return f8791j;
    }
}
