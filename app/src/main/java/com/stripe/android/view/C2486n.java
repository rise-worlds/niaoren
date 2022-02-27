package com.stripe.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import com.stripe.android.model.Card;

/* compiled from: ViewUtils.java */
/* renamed from: com.stripe.android.view.n */
/* loaded from: classes2.dex */
class C2486n {
    C2486n() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static TypedValue m17215a(Context context) {
        int identifier = Build.VERSION.SDK_INT >= 21 ? 16843829 : context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(identifier, typedValue, true);
        return typedValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static TypedValue m17209b(Context context) {
        int identifier = Build.VERSION.SDK_INT >= 21 ? 16843817 : context.getResources().getIdentifier("colorControlNormal", "attr", context.getPackageName());
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(identifier, typedValue, true);
        return typedValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static TypedValue m17207c(Context context) {
        int i = Build.VERSION.SDK_INT >= 21 ? 16842808 : 17170439;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static TypedValue m17206d(Context context) {
        int i = Build.VERSION.SDK_INT >= 21 ? 16842806 : 17170435;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue;
    }

    /* renamed from: a */
    static Drawable m17213a(@NonNull Context context, @DrawableRes int i, @ColorRes int i2) {
        int i3;
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 23) {
            i3 = context.getResources().getColor(i2, context.getTheme());
            drawable = context.getResources().getDrawable(i, context.getTheme());
        } else {
            i3 = context.getResources().getColor(i2);
            drawable = context.getResources().getDrawable(i);
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap.mutate(), i3);
        return wrap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Drawable m17212a(@NonNull Context context, @NonNull Resources.Theme theme, @AttrRes int i, @DrawableRes int i2) {
        Drawable drawable;
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        int i3 = typedValue.data;
        if (Build.VERSION.SDK_INT > 23) {
            drawable = context.getResources().getDrawable(i2, theme);
        } else {
            drawable = context.getResources().getDrawable(i2);
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap.mutate(), i3);
        return wrap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17216a(@ColorInt int i) {
        return Color.alpha(i) < 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m17210b(@ColorInt int i) {
        return (((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d)) / 255.0d <= 0.5d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m17211a(@NonNull String str, @Nullable String str2) {
        if (str2 == null) {
            return false;
        }
        return Card.f11999a.equals(str) ? str2.trim().length() == 4 : str2.trim().length() == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: b */
    public static String[] m17208b(@NonNull String str, @NonNull String str2) {
        String[] strArr;
        int i;
        int i2 = 0;
        if (str.length() > 16) {
            str = str.substring(0, 16);
        }
        if (str2.equals(Card.f11999a)) {
            strArr = new String[3];
            int length = str.length();
            if (length > 4) {
                strArr[0] = str.substring(0, 4);
                i = 4;
            } else {
                i = 0;
            }
            if (length > 10) {
                strArr[1] = str.substring(4, 10);
                i = 10;
            }
            while (true) {
                if (i2 < 3) {
                    if (strArr[i2] == null) {
                        strArr[i2] = str.substring(i);
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
        } else {
            strArr = new String[4];
            int i3 = 0;
            while (true) {
                int i4 = i2 + 1;
                int i5 = i4 * 4;
                if (i5 >= str.length()) {
                    break;
                }
                strArr[i2] = str.substring(i3, i5);
                i2 = i4;
                i3 = i5;
            }
            strArr[i2] = str.substring(i3);
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m17214a(Context context, int i) {
        return (int) (i * context.getResources().getDisplayMetrics().density);
    }
}
