package com.blankj.utilcode.util;

import android.content.ContentResolver;
import android.provider.Settings;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

/* renamed from: com.blankj.utilcode.util.e */
/* loaded from: classes.dex */
public final class BrightnessUtils {
    private BrightnessUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m22836a() {
        try {
            return Settings.System.getInt(Utils.m24103a().getContentResolver(), "screen_brightness_mode") == 1;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m22832a(boolean z) {
        return Settings.System.putInt(Utils.m24103a().getContentResolver(), "screen_brightness_mode", z ? 1 : 0);
    }

    /* renamed from: b */
    public static int m22831b() {
        try {
            return Settings.System.getInt(Utils.m24103a().getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: a */
    public static boolean m22835a(@IntRange(from = 0, m25695to = 255) int i) {
        ContentResolver contentResolver = Utils.m24103a().getContentResolver();
        boolean putInt = Settings.System.putInt(contentResolver, "screen_brightness", i);
        contentResolver.notifyChange(Settings.System.getUriFor("screen_brightness"), null);
        return putInt;
    }

    /* renamed from: a */
    public static void m22833a(@NonNull Window window, @IntRange(from = 0, m25695to = 255) int i) {
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.screenBrightness = i / 255.0f;
            window.setAttributes(attributes);
            return;
        }
        throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static int m22834a(Window window) {
        float f = window.getAttributes().screenBrightness;
        return f < 0.0f ? m22831b() : (int) (f * 255.0f);
    }
}
