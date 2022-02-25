package com.cyjh.p045mq.sdk;

import android.app.Application;
import android.content.Intent;
import com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.RootProgressListener;
import com.cyjh.p045mq.p046a.MyApplication;
import com.cyjh.p045mq.service.IpcService;

/* renamed from: com.cyjh.mq.sdk.MqBridge */
/* loaded from: classes.dex */
public class MqBridge {
    public static void init(Application application, OnKeyEventListener onKeyEventListener, RootProgressListener rootProgressListener, OnEngineStartCallback onEngineStartCallback) {
        if (MyApplication.f8788g == null) {
            MyApplication.f8788g = application;
            MyApplication.f8789h = onKeyEventListener;
            MyApplication.f8790i = rootProgressListener;
            MyApplication.f8791j = onEngineStartCallback;
            application.startService(new Intent(application, IpcService.class));
        }
    }

    public static void exit() {
        if (MyApplication.f8788g != null) {
            MyApplication.f8788g.stopService(new Intent(MyApplication.f8788g, IpcService.class));
        }
    }
}
