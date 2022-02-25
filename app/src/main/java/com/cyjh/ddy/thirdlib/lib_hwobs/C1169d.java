package com.cyjh.ddy.thirdlib.lib_hwobs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.blankj.utilcode.util.Utils;
import java.io.ByteArrayOutputStream;

/* renamed from: com.cyjh.ddy.thirdlib.lib_hwobs.d */
/* loaded from: classes.dex */
public final class C1169d {
    private C1169d() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static Bitmap m21340a(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /* renamed from: a */
    public static Bitmap m21338a(View view) {
        Bitmap bitmap;
        if (view == null) {
            return null;
        }
        boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
        boolean willNotCacheDrawing = view.willNotCacheDrawing();
        view.setDrawingCacheEnabled(true);
        view.setWillNotCacheDrawing(false);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache();
            bitmap = Bitmap.createBitmap(view.getDrawingCache());
        } else {
            bitmap = Bitmap.createBitmap(drawingCache);
        }
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCacheDrawing);
        view.setDrawingCacheEnabled(isDrawingCacheEnabled);
        return bitmap;
    }

    /* renamed from: a */
    public static Bitmap m21337a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static byte[] m21342a(Bitmap bitmap) {
        return m21341a(bitmap, Bitmap.CompressFormat.PNG, 100);
    }

    /* renamed from: a */
    public static byte[] m21341a(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static byte[] m21339a(Drawable drawable, Bitmap.CompressFormat compressFormat, int i) {
        if (drawable == null) {
            return null;
        }
        return m21341a(m21340a(drawable), compressFormat, i);
    }

    /* renamed from: b */
    public static Drawable m21336b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.m24103a().getResources(), bitmap);
    }

    /* renamed from: b */
    public static Drawable m21334b(byte[] bArr) {
        return m21336b(m21337a(bArr));
    }

    /* renamed from: b */
    public static byte[] m21335b(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return m21342a(m21340a(drawable));
    }
}
