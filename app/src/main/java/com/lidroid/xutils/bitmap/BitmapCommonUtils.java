package com.lidroid.xutils.bitmap;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class BitmapCommonUtils {
    private static BitmapSize screenSize;

    private BitmapCommonUtils() {
    }

    public static BitmapSize getScreenSize(Context context) {
        if (screenSize == null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            screenSize = new BitmapSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        return screenSize;
    }

    public static BitmapSize optimizeMaxSizeByView(View view, int i, int i2) {
        if (i > 0 && i2 > 0) {
            return new BitmapSize(i, i2);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            if (layoutParams.width > 0) {
                i = layoutParams.width;
            } else if (layoutParams.width != -2) {
                i = view.getWidth();
            }
            if (layoutParams.height > 0) {
                i2 = layoutParams.height;
            } else if (layoutParams.height != -2) {
                i2 = view.getHeight();
            }
        }
        if (i <= 0) {
            i = getImageViewFieldValue(view, "mMaxWidth");
        }
        if (i2 <= 0) {
            i2 = getImageViewFieldValue(view, "mMaxHeight");
        }
        BitmapSize screenSize2 = getScreenSize(view.getContext());
        if (i <= 0) {
            i = screenSize2.getWidth();
        }
        if (i2 <= 0) {
            i2 = screenSize2.getHeight();
        }
        return new BitmapSize(i, i2);
    }

    private static int getImageViewFieldValue(Object obj, String str) {
        if (obj instanceof ImageView) {
            try {
                Field declaredField = ImageView.class.getDeclaredField(str);
                declaredField.setAccessible(true);
                int intValue = ((Integer) declaredField.get(obj)).intValue();
                if (intValue > 0 && intValue < Integer.MAX_VALUE) {
                    return intValue;
                }
            } catch (Throwable unused) {
            }
        }
        return 0;
    }
}
