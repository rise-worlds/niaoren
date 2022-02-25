package com.blankj.utilcode.util;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import java.io.IOException;

/* renamed from: com.blankj.utilcode.util.x */
/* loaded from: classes.dex */
public final class FlashlightUtils {

    /* renamed from: a */
    private static Camera f6913a;

    /* renamed from: b */
    private static SurfaceTexture f6914b;

    private FlashlightUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m22168a() {
        return Utils.m24103a().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    /* renamed from: b */
    public static boolean m22166b() {
        if (!m22164d()) {
            return false;
        }
        return "torch".equals(f6913a.getParameters().getFlashMode());
    }

    /* renamed from: a */
    public static void m22167a(boolean z) {
        if (m22164d()) {
            Camera.Parameters parameters = f6913a.getParameters();
            if (z) {
                if (!"torch".equals(parameters.getFlashMode())) {
                    try {
                        f6913a.setPreviewTexture(f6914b);
                        f6913a.startPreview();
                        parameters.setFlashMode("torch");
                        f6913a.setParameters(parameters);
                    } catch (IOException e) {
                        Log.e("FlashlightUtils", "setFlashlightStatusOn: ", e);
                    }
                }
            } else if (!"off".equals(parameters.getFlashMode())) {
                parameters.setFlashMode("off");
                f6913a.setParameters(parameters);
            }
        }
    }

    /* renamed from: c */
    public static void m22165c() {
        Camera camera = f6913a;
        if (camera != null) {
            camera.release();
            f6914b = null;
            f6913a = null;
        }
    }

    /* renamed from: d */
    private static boolean m22164d() {
        if (f6913a == null) {
            try {
                f6913a = Camera.open(0);
                f6914b = new SurfaceTexture(0);
            } catch (Throwable th) {
                Log.e("FlashlightUtils", "init failed: ", th);
                return false;
            }
        }
        if (f6913a != null) {
            return true;
        }
        Log.e("FlashlightUtils", "init failed.");
        return false;
    }
}
