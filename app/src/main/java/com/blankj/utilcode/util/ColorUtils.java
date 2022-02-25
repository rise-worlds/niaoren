package com.blankj.utilcode.util;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.p003v4.content.ContextCompat;
import android.support.p003v4.view.InputDeviceCompat;
import android.support.p003v4.view.ViewCompat;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;

/* renamed from: com.blankj.utilcode.util.p */
/* loaded from: classes.dex */
public final class ColorUtils {
    /* renamed from: a */
    public static int m22484a(@ColorInt int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((f * 255.0f) + 0.5f)) << 24);
    }

    /* renamed from: a */
    public static int m22483a(@ColorInt int i, @IntRange(from = 0, m25695to = 255) int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
    }

    /* renamed from: b */
    public static int m22479b(@ColorInt int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return (i & (-16711681)) | (((int) ((f * 255.0f) + 0.5f)) << 16);
    }

    /* renamed from: b */
    public static int m22478b(@ColorInt int i, @IntRange(from = 0, m25695to = 255) int i2) {
        return (i & (-16711681)) | (i2 << 16);
    }

    /* renamed from: c */
    public static int m22476c(@ColorInt int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return (i & (-65281)) | (((int) ((f * 255.0f) + 0.5f)) << 8);
    }

    /* renamed from: c */
    public static int m22475c(@ColorInt int i, @IntRange(from = 0, m25695to = 255) int i2) {
        return (i & (-65281)) | (i2 << 8);
    }

    /* renamed from: d */
    public static int m22474d(@ColorInt int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return (i & InputDeviceCompat.SOURCE_ANY) | ((int) ((f * 255.0f) + 0.5f));
    }

    /* renamed from: d */
    public static int m22473d(@ColorInt int i, @IntRange(from = 0, m25695to = 255) int i2) {
        return (i & InputDeviceCompat.SOURCE_ANY) | i2;
    }

    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static int m22485a(@ColorRes int i) {
        return ContextCompat.getColor(Utils.m24103a(), i);
    }

    /* renamed from: a */
    public static int m22482a(@NonNull String str) {
        if (str != null) {
            return Color.parseColor(str);
        }
        throw new NullPointerException("Argument 'colorString' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public static String m22480b(@ColorInt int i) {
        String hexString = Integer.toHexString(i & ViewCompat.MEASURED_SIZE_MASK);
        while (hexString.length() < 6) {
            hexString = ResultTypeConstant.f7213z + hexString;
        }
        return "#" + hexString;
    }

    /* renamed from: c */
    public static String m22477c(@ColorInt int i) {
        String hexString = Integer.toHexString(i);
        while (hexString.length() < 6) {
            hexString = ResultTypeConstant.f7213z + hexString;
        }
        while (hexString.length() < 8) {
            hexString = "f" + hexString;
        }
        return "#" + hexString;
    }

    /* renamed from: a */
    public static int m22486a() {
        return m22481a(true);
    }

    /* renamed from: a */
    public static int m22481a(boolean z) {
        return (z ? ((int) (Math.random() * 256.0d)) << 24 : -16777216) | ((int) (Math.random() * 1.6777216E7d));
    }
}
