package com.lbd.xj.manager.launch;

import android.view.InputEvent;
import android.view.Surface;
import com.lbd.xj.app.AppConfig;

/* renamed from: com.lbd.xj.manager.launch.Nativebridge */
/* loaded from: classes.dex */
public class Nativebridge {

    /* renamed from: a */
    static Nativebridge f9478a;

    public static native int setNativeWindow(int i, int i2, int i3);

    public static native int setSurface(Surface surface);

    public native void sendeventkey(int i);

    public native void setInputTouchEvent(int i, int i2, InputEvent inputEvent);

    public native void setRotation(float f);

    static {
        System.loadLibrary("native-lib");
        if (AppConfig.f9445l) {
            System.loadLibrary("vmtools");
        } else {
            System.loadLibrary("native-activity");
        }
        f9478a = null;
    }

    /* renamed from: a */
    public static Nativebridge m19712a() {
        if (f9478a == null) {
            f9478a = new Nativebridge();
        }
        return f9478a;
    }
}
