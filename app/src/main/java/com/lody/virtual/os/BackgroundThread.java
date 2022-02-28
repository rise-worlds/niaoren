package com.lody.virtual.os;

import android.os.Handler;
import android.os.HandlerThread;

/* renamed from: com.lody.virtual.os.BackgroundThread */
/* loaded from: classes.dex */
public final class BackgroundThread extends HandlerThread {
    private static Handler sHandler;
    private static BackgroundThread sInstance;

    private BackgroundThread() {
        super("va.android.bg", 10);
    }

    private static void ensureThreadLocked() {
        if (sInstance == null) {
            sInstance = new BackgroundThread();
            sInstance.start();
            sHandler = new Handler(sInstance.getLooper());
        }
    }

    public static BackgroundThread get() {
        BackgroundThread backgroundThread;
        synchronized (BackgroundThread.class) {
            ensureThreadLocked();
            backgroundThread = sInstance;
        }
        return backgroundThread;
    }

    public static Handler getHandler() {
        Handler handler;
        synchronized (BackgroundThread.class) {
            ensureThreadLocked();
            handler = sHandler;
        }
        return handler;
    }
}
