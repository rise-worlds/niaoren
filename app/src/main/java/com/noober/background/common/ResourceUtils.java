package com.noober.background.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

/* loaded from: classes.dex */
public class ResourceUtils {
    @Nullable
    public static Drawable getDrawable(Context context, String str) {
        Resources resources = context.getResources();
        if (str.startsWith("#")) {
            return new ColorDrawable(Color.parseColor(str));
        }
        int identifier = resources.getIdentifier(str, "drawable", context.getPackageName());
        if (identifier == 0) {
            identifier = resources.getIdentifier(str, "mipmap", context.getPackageName());
        }
        if (identifier == 0) {
            identifier = resources.getIdentifier(str, "color", context.getPackageName());
        }
        if (identifier == 0) {
            return null;
        }
        return ContextCompat.getDrawable(context, identifier);
    }

    public static int getColor(Context context, String str) {
        Resources resources = context.getResources();
        if (str.startsWith("#")) {
            return Color.parseColor(str);
        }
        int identifier = resources.getIdentifier(str, "color", context.getPackageName());
        if (identifier == 0) {
            return -1;
        }
        return ContextCompat.getColor(context, identifier);
    }
}
