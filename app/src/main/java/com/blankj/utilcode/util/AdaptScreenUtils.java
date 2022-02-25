package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.b */
/* loaded from: classes.dex */
public final class AdaptScreenUtils {

    /* renamed from: a */
    private static List<Field> f6806a;

    /* renamed from: a */
    public static Resources m23139a(Resources resources, int i) {
        m23140a(resources, (resources.getDisplayMetrics().widthPixels * 72.0f) / i);
        return resources;
    }

    /* renamed from: b */
    public static Resources m23133b(Resources resources, int i) {
        return m23138a(resources, i, false);
    }

    /* renamed from: a */
    public static Resources m23138a(Resources resources, int i, boolean z) {
        m23140a(resources, ((resources.getDisplayMetrics().heightPixels + (z ? m23135b(resources) : 0)) * 72.0f) / i);
        return resources;
    }

    /* renamed from: b */
    private static int m23135b(Resources resources) {
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: a */
    public static Resources m23141a(Resources resources) {
        m23140a(resources, Resources.getSystem().getDisplayMetrics().density * 72.0f);
        return resources;
    }

    /* renamed from: a */
    public static int m23142a(float f) {
        return (int) (((f * Utils.m24103a().getResources().getDisplayMetrics().xdpi) / 72.0f) + 0.5d);
    }

    /* renamed from: b */
    public static int m23136b(float f) {
        return (int) (((f * 72.0f) / Utils.m24103a().getResources().getDisplayMetrics().xdpi) + 0.5d);
    }

    /* renamed from: a */
    private static void m23140a(Resources resources, float f) {
        resources.getDisplayMetrics().xdpi = f;
        Utils.m24103a().getResources().getDisplayMetrics().xdpi = f;
        m23134b(resources, f);
    }

    /* renamed from: b */
    private static void m23134b(Resources resources, float f) {
        if (f6806a == null) {
            f6806a = new ArrayList();
            Class<?> cls = resources.getClass();
            Field[] declaredFields = cls.getDeclaredFields();
            while (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.getType().isAssignableFrom(DisplayMetrics.class)) {
                        field.setAccessible(true);
                        DisplayMetrics a = m23137a(resources, field);
                        if (a != null) {
                            f6806a.add(field);
                            a.xdpi = f;
                        }
                    }
                }
                cls = cls.getSuperclass();
                if (cls != null) {
                    declaredFields = cls.getDeclaredFields();
                } else {
                    return;
                }
            }
            return;
        }
        m23132c(resources, f);
    }

    /* renamed from: c */
    private static void m23132c(Resources resources, float f) {
        for (Field field : f6806a) {
            try {
                DisplayMetrics displayMetrics = (DisplayMetrics) field.get(resources);
                if (displayMetrics != null) {
                    displayMetrics.xdpi = f;
                }
            } catch (Exception e) {
                Log.e("AdaptScreenUtils", "applyMetricsFields: " + e);
            }
        }
    }

    /* renamed from: a */
    private static DisplayMetrics m23137a(Resources resources, Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception e) {
            Log.e("AdaptScreenUtils", "getMetricsFromField: " + e);
            return null;
        }
    }
}
