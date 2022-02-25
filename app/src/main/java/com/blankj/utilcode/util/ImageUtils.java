package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.p003v4.content.ContextCompat;
import android.support.p003v4.view.MotionEventCompat;
import android.support.p003v4.view.ViewCompat;
import android.view.View;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

/* renamed from: com.blankj.utilcode.util.aa */
/* loaded from: classes.dex */
public final class ImageUtils {
    private ImageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static byte[] m23939a(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static Bitmap m23916a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static Bitmap m23927a(Drawable drawable) {
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
    public static Drawable m23962a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.m24103a().getResources(), bitmap);
    }

    /* renamed from: a */
    public static byte[] m23926a(Drawable drawable, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return null;
        }
        return m23939a(m23927a(drawable), compressFormat);
    }

    /* renamed from: b */
    public static Drawable m23900b(byte[] bArr) {
        return m23962a(m23916a(bArr));
    }

    /* renamed from: a */
    public static Bitmap m23925a(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m23924a(File file) {
        if (file == null) {
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    /* renamed from: a */
    public static Bitmap m23923a(File file, int i, int i2) {
        if (file == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = m23928a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    /* renamed from: a */
    public static Bitmap m23918a(String str) {
        if (m23865h(str)) {
            return null;
        }
        return BitmapFactory.decodeFile(str);
    }

    /* renamed from: a */
    public static Bitmap m23917a(String str, int i, int i2) {
        if (m23865h(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = m23928a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    /* renamed from: a */
    public static Bitmap m23920a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return BitmapFactory.decodeStream(inputStream);
    }

    /* renamed from: a */
    public static Bitmap m23919a(InputStream inputStream, int i, int i2) {
        if (inputStream == null) {
            return null;
        }
        return m23914a(m23891c(inputStream), 0, i, i2);
    }

    /* renamed from: a */
    public static Bitmap m23915a(byte[] bArr, int i) {
        if (bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length);
    }

    /* renamed from: a */
    public static Bitmap m23914a(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length == 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
        options.inSampleSize = m23928a(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, i, bArr.length, options);
    }

    /* renamed from: a */
    public static Bitmap m23964a(@DrawableRes int i) {
        Drawable drawable = ContextCompat.getDrawable(Utils.m24103a(), i);
        Canvas canvas = new Canvas();
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m23963a(@DrawableRes int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Resources resources = Utils.m24103a().getResources();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = m23928a(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    /* renamed from: a */
    public static Bitmap m23922a(FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) {
            return null;
        }
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    /* renamed from: a */
    public static Bitmap m23921a(FileDescriptor fileDescriptor, int i, int i2) {
        if (fileDescriptor == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = m23928a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    /* renamed from: a */
    public static Bitmap m23952a(@NonNull Bitmap bitmap, @ColorInt int i) {
        if (bitmap != null) {
            return m23942a(bitmap, i, false);
        }
        throw new NullPointerException("Argument 'src' of type Bitmap (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static Bitmap m23942a(@NonNull Bitmap bitmap, @ColorInt int i, boolean z) {
        if (bitmap == null) {
            throw new NullPointerException("Argument 'src' of type Bitmap (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (m23878e(bitmap)) {
            return null;
        } else {
            if (!z) {
                bitmap = bitmap.copy(bitmap.getConfig(), true);
            }
            new Canvas(bitmap).drawColor(i, PorterDuff.Mode.DARKEN);
            return bitmap;
        }
    }

    /* renamed from: a */
    public static Bitmap m23949a(Bitmap bitmap, int i, int i2) {
        return m23944a(bitmap, i, i2, false);
    }

    /* renamed from: a */
    public static Bitmap m23944a(Bitmap bitmap, int i, int i2, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
        if (z && !bitmap.isRecycled() && createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    /* renamed from: a */
    public static Bitmap m23960a(Bitmap bitmap, float f, float f2) {
        return m23957a(bitmap, f, f2, false);
    }

    /* renamed from: a */
    public static Bitmap m23957a(Bitmap bitmap, float f, float f2, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m23946a(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return m23945a(bitmap, i, i2, i3, i4, false);
    }

    /* renamed from: a */
    public static Bitmap m23945a(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i, i2, i3, i4);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: b */
    public static Bitmap m23911b(Bitmap bitmap, float f, float f2) {
        return m23958a(bitmap, f, f2, 0.0f, 0.0f, false);
    }

    /* renamed from: b */
    public static Bitmap m23910b(Bitmap bitmap, float f, float f2, boolean z) {
        return m23958a(bitmap, f, f2, 0.0f, 0.0f, z);
    }

    /* renamed from: a */
    public static Bitmap m23959a(Bitmap bitmap, float f, float f2, float f3, float f4) {
        return m23958a(bitmap, f, f2, f3, f4, false);
    }

    /* renamed from: a */
    public static Bitmap m23958a(Bitmap bitmap, float f, float f2, float f3, float f4, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setSkew(f, f2, f3, f4);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m23951a(Bitmap bitmap, int i, float f, float f2) {
        return m23950a(bitmap, i, f, f2, false);
    }

    /* renamed from: a */
    public static Bitmap m23950a(Bitmap bitmap, int i, float f, float f2, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        if (i == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i, f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: b */
    public static int m23901b(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: b */
    public static Bitmap m23913b(Bitmap bitmap) {
        return m23906b(bitmap, 0, 0, false);
    }

    /* renamed from: a */
    public static Bitmap m23929a(Bitmap bitmap, boolean z) {
        return m23906b(bitmap, 0, 0, z);
    }

    /* renamed from: b */
    public static Bitmap m23907b(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2) {
        return m23906b(bitmap, i, i2, false);
    }

    /* renamed from: b */
    public static Bitmap m23906b(Bitmap bitmap, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        float f = min;
        float f2 = f / 2.0f;
        float f3 = width;
        float f4 = height;
        RectF rectF = new RectF(0.0f, 0.0f, f3, f4);
        rectF.inset((width - min) / 2.0f, (height - min) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.setTranslate(rectF.left, rectF.top);
        if (width != height) {
            matrix.preScale(f / f3, f / f4);
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (i > 0) {
            paint.setShader(null);
            paint.setColor(i2);
            paint.setStyle(Paint.Style.STROKE);
            float f5 = i;
            paint.setStrokeWidth(f5);
            canvas.drawCircle(f3 / 2.0f, f4 / 2.0f, f2 - (f5 / 2.0f), paint);
        }
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m23961a(Bitmap bitmap, float f) {
        return m23954a(bitmap, f, 0, 0, false);
    }

    /* renamed from: a */
    public static Bitmap m23953a(Bitmap bitmap, float f, boolean z) {
        return m23954a(bitmap, f, 0, 0, z);
    }

    /* renamed from: a */
    public static Bitmap m23955a(Bitmap bitmap, float f, @IntRange(from = 0) int i, @ColorInt int i2) {
        return m23954a(bitmap, f, i, i2, false);
    }

    /* renamed from: a */
    public static Bitmap m23954a(Bitmap bitmap, float f, @IntRange(from = 0) int i, @ColorInt int i2, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        float f2 = i;
        float f3 = f2 / 2.0f;
        rectF.inset(f3, f3);
        canvas.drawRoundRect(rectF, f, f, paint);
        if (i > 0) {
            paint.setShader(null);
            paint.setColor(i2);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(f2);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawRoundRect(rectF, f, f, paint);
        }
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m23948a(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, @FloatRange(from = 0.0d) float f) {
        return m23943a(bitmap, i, i2, false, f, false);
    }

    /* renamed from: a */
    public static Bitmap m23947a(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, @FloatRange(from = 0.0d) float f, boolean z) {
        return m23943a(bitmap, i, i2, false, f, z);
    }

    /* renamed from: c */
    public static Bitmap m23895c(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2) {
        return m23943a(bitmap, i, i2, true, 0.0f, false);
    }

    /* renamed from: c */
    public static Bitmap m23894c(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, boolean z) {
        return m23943a(bitmap, i, i2, true, 0.0f, z);
    }

    /* renamed from: a */
    private static Bitmap m23943a(Bitmap bitmap, @IntRange(from = 1) int i, @ColorInt int i2, boolean z, float f, boolean z2) {
        int i3;
        if (m23878e(bitmap)) {
            return null;
        }
        if (!z2) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(1);
        paint.setColor(i2);
        paint.setStyle(Paint.Style.STROKE);
        float f2 = i;
        paint.setStrokeWidth(f2);
        if (z) {
            canvas.drawCircle(width / 2.0f, height / 2.0f, (Math.min(width, height) / 2.0f) - (f2 / 2.0f), paint);
        } else {
            float f3 = i >> 1;
            canvas.drawRoundRect(new RectF(f3, f3, width - i3, height - i3), f, f, paint);
        }
        return bitmap;
    }

    /* renamed from: b */
    public static Bitmap m23908b(Bitmap bitmap, int i) {
        return m23905b(bitmap, i, false);
    }

    /* renamed from: b */
    public static Bitmap m23905b(Bitmap bitmap, int i, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, height - i, width, i, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height + i, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        float f = height + 0;
        canvas.drawBitmap(createBitmap, 0.0f, f, (Paint) null);
        Paint paint = new Paint(1);
        paint.setShader(new LinearGradient(0.0f, height, 0.0f, createBitmap2.getHeight() + 0, 1895825407, (int) ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f, width, createBitmap2.getHeight(), paint);
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
        if (z && !bitmap.isRecycled() && createBitmap2 != bitmap) {
            bitmap.recycle();
        }
        return createBitmap2;
    }

    /* renamed from: a */
    public static Bitmap m23932a(Bitmap bitmap, String str, int i, @ColorInt int i2, float f, float f2) {
        return m23933a(bitmap, str, i, i2, f, f2, false);
    }

    /* renamed from: a */
    public static Bitmap m23933a(Bitmap bitmap, String str, float f, @ColorInt int i, float f2, float f3, boolean z) {
        if (m23878e(bitmap) || str == null) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        Paint paint = new Paint(1);
        Canvas canvas = new Canvas(copy);
        paint.setColor(i);
        paint.setTextSize(f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        canvas.drawText(str, f2, f3 + f, paint);
        if (z && !bitmap.isRecycled() && copy != bitmap) {
            bitmap.recycle();
        }
        return copy;
    }

    /* renamed from: a */
    public static Bitmap m23938a(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3) {
        return m23937a(bitmap, bitmap2, i, i2, i3, false);
    }

    /* renamed from: a */
    public static Bitmap m23937a(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (!m23878e(bitmap2)) {
            Paint paint = new Paint(1);
            Canvas canvas = new Canvas(copy);
            paint.setAlpha(i3);
            canvas.drawBitmap(bitmap2, i, i2, paint);
        }
        if (z && !bitmap.isRecycled() && copy != bitmap) {
            bitmap.recycle();
        }
        return copy;
    }

    /* renamed from: c */
    public static Bitmap m23899c(Bitmap bitmap) {
        return m23934a(bitmap, (Boolean) false);
    }

    /* renamed from: a */
    public static Bitmap m23934a(Bitmap bitmap, Boolean bool) {
        if (m23878e(bitmap)) {
            return null;
        }
        Bitmap extractAlpha = bitmap.extractAlpha();
        if (bool.booleanValue() && !bitmap.isRecycled() && extractAlpha != bitmap) {
            bitmap.recycle();
        }
        return extractAlpha;
    }

    /* renamed from: d */
    public static Bitmap m23888d(Bitmap bitmap) {
        return m23904b(bitmap, false);
    }

    /* renamed from: b */
    public static Bitmap m23904b(Bitmap bitmap, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* renamed from: c */
    public static Bitmap m23898c(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 25.0d) float f2) {
        return m23956a(bitmap, f, f2, false, false);
    }

    /* renamed from: c */
    public static Bitmap m23897c(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 25.0d) float f2, boolean z) {
        return m23956a(bitmap, f, f2, z, false);
    }

    /* renamed from: a */
    public static Bitmap m23956a(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 1.0d) float f, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 25.0d) float f2, boolean z, boolean z2) {
        Bitmap bitmap2;
        if (m23878e(bitmap)) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Paint paint = new Paint(3);
        Canvas canvas = new Canvas();
        paint.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP));
        canvas.scale(f, f);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        if (Build.VERSION.SDK_INT >= 17) {
            bitmap2 = m23909b(createBitmap, f2, z);
        } else {
            bitmap2 = m23893c(createBitmap, (int) f2, z);
        }
        if (f == 1.0f || z2) {
            if (z && !bitmap.isRecycled() && bitmap2 != bitmap) {
                bitmap.recycle();
            }
            return bitmap2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, width, height, true);
        if (!bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
        if (z && !bitmap.isRecycled() && createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    @RequiresApi(17)
    /* renamed from: b */
    public static Bitmap m23912b(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 25.0d) float f) {
        return m23909b(bitmap, f, false);
    }

    @RequiresApi(17)
    /* renamed from: b */
    public static Bitmap m23909b(Bitmap bitmap, @FloatRange(from = 0.0d, fromInclusive = false, m25696to = 25.0d) float f, boolean z) {
        if (!z) {
            bitmap = bitmap.copy(bitmap.getConfig(), true);
        }
        RenderScript renderScript = null;
        try {
            renderScript = RenderScript.create(Utils.m24103a());
            renderScript.setMessageHandler(new RenderScript.RSMessageHandler());
            Allocation createFromBitmap = Allocation.createFromBitmap(renderScript, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
            Allocation createTyped = Allocation.createTyped(renderScript, createFromBitmap.getType());
            ScriptIntrinsicBlur create = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            create.setInput(createFromBitmap);
            create.setRadius(f);
            create.forEach(createTyped);
            createTyped.copyTo(bitmap);
            return bitmap;
        } finally {
            if (renderScript != null) {
                renderScript.destroy();
            }
        }
    }

    /* renamed from: c */
    public static Bitmap m23896c(Bitmap bitmap, int i) {
        return m23893c(bitmap, i, false);
    }

    /* renamed from: c */
    public static Bitmap m23893c(Bitmap bitmap, int i, boolean z) {
        int i2;
        Bitmap copy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        int i3 = i < 1 ? 1 : i;
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i4 = width * height;
        int[] iArr = new int[i4];
        copy.getPixels(iArr, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int i7 = i3 + i3 + 1;
        int[] iArr2 = new int[i4];
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[Math.max(width, height)];
        int i8 = (i7 + 1) >> 1;
        int i9 = i8 * i8;
        int i10 = i9 * 256;
        int[] iArr6 = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            iArr6[i11] = i11 / i9;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(int.class, i7, 3);
        int i12 = i3 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            int i16 = -i3;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            while (i16 <= i3) {
                int i26 = iArr[i14 + Math.min(i5, Math.max(i16, 0))];
                int[] iArr8 = iArr7[i16 + i3];
                iArr8[0] = (i26 & 16711680) >> 16;
                iArr8[1] = (i26 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr8[2] = i26 & 255;
                int abs = i12 - Math.abs(i16);
                i17 += iArr8[0] * abs;
                i18 += iArr8[1] * abs;
                i19 += iArr8[2] * abs;
                if (i16 > 0) {
                    i23 += iArr8[0];
                    i24 += iArr8[1];
                    i25 += iArr8[2];
                } else {
                    i20 += iArr8[0];
                    i21 += iArr8[1];
                    i22 += iArr8[2];
                }
                i16++;
                height = height;
                i6 = i6;
            }
            int i27 = i3;
            for (int i28 = 0; i28 < width; i28++) {
                iArr2[i14] = iArr6[i17];
                iArr3[i14] = iArr6[i18];
                iArr4[i14] = iArr6[i19];
                int i29 = i17 - i20;
                int i30 = i18 - i21;
                int i31 = i19 - i22;
                int[] iArr9 = iArr7[((i27 - i3) + i7) % i7];
                int i32 = i20 - iArr9[0];
                int i33 = i21 - iArr9[1];
                int i34 = i22 - iArr9[2];
                if (i13 == 0) {
                    iArr6 = iArr6;
                    iArr5[i28] = Math.min(i28 + i3 + 1, i5);
                } else {
                    iArr6 = iArr6;
                }
                int i35 = iArr[i15 + iArr5[i28]];
                iArr9[0] = (i35 & 16711680) >> 16;
                iArr9[1] = (i35 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr9[2] = i35 & 255;
                int i36 = i23 + iArr9[0];
                int i37 = i24 + iArr9[1];
                int i38 = i25 + iArr9[2];
                i17 = i29 + i36;
                i18 = i30 + i37;
                i19 = i31 + i38;
                i27 = (i27 + 1) % i7;
                int[] iArr10 = iArr7[i27 % i7];
                i20 = i32 + iArr10[0];
                i21 = i33 + iArr10[1];
                i22 = i34 + iArr10[2];
                i23 = i36 - iArr10[0];
                i24 = i37 - iArr10[1];
                i25 = i38 - iArr10[2];
                i14++;
            }
            i15 += width;
            i13++;
            height = height;
            copy = copy;
            i6 = i6;
        }
        int i39 = i6;
        int i40 = height;
        int i41 = 0;
        while (i41 < width) {
            int i42 = -i3;
            int i43 = i42 * width;
            int i44 = 0;
            int i45 = 0;
            int i46 = 0;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            while (i42 <= i3) {
                int max = Math.max(0, i43) + i41;
                int[] iArr11 = iArr7[i42 + i3];
                iArr11[0] = iArr2[max];
                iArr11[1] = iArr3[max];
                iArr11[2] = iArr4[max];
                int abs2 = i12 - Math.abs(i42);
                i44 += iArr2[max] * abs2;
                i45 += iArr3[max] * abs2;
                i46 += iArr4[max] * abs2;
                if (i42 > 0) {
                    i50 += iArr11[0];
                    i51 += iArr11[1];
                    i52 += iArr11[2];
                    i2 = i39;
                } else {
                    i47 += iArr11[0];
                    i48 += iArr11[1];
                    i49 += iArr11[2];
                    i2 = i39;
                }
                if (i42 < i2) {
                    i43 += width;
                }
                i42++;
                i39 = i2;
                iArr5 = iArr5;
            }
            int i53 = i51;
            int i54 = i52;
            int i55 = i3;
            int i56 = i50;
            int i57 = i49;
            int i58 = i48;
            int i59 = i47;
            int i60 = i46;
            int i61 = i45;
            int i62 = i44;
            int i63 = i41;
            for (int i64 = 0; i64 < i40; i64++) {
                iArr[i63] = (iArr[i63] & (-16777216)) | (iArr6[i62] << 16) | (iArr6[i61] << 8) | iArr6[i60];
                int i65 = i62 - i59;
                int i66 = i61 - i58;
                int i67 = i60 - i57;
                int[] iArr12 = iArr7[((i55 - i3) + i7) % i7];
                int i68 = i59 - iArr12[0];
                int i69 = i58 - iArr12[1];
                int i70 = i57 - iArr12[2];
                if (i41 == 0) {
                    i3 = i3;
                    iArr5[i64] = Math.min(i64 + i12, i39) * width;
                } else {
                    i3 = i3;
                }
                int i71 = iArr5[i64] + i41;
                iArr12[0] = iArr2[i71];
                iArr12[1] = iArr3[i71];
                iArr12[2] = iArr4[i71];
                int i72 = i56 + iArr12[0];
                int i73 = i53 + iArr12[1];
                int i74 = i54 + iArr12[2];
                i62 = i65 + i72;
                i61 = i66 + i73;
                i60 = i67 + i74;
                i55 = (i55 + 1) % i7;
                int[] iArr13 = iArr7[i55];
                i59 = i68 + iArr13[0];
                i58 = i69 + iArr13[1];
                i57 = i70 + iArr13[2];
                i56 = i72 - iArr13[0];
                i53 = i73 - iArr13[1];
                i54 = i74 - iArr13[2];
                i63 += width;
            }
            i41++;
            i39 = i39;
            i40 = i40;
            iArr5 = iArr5;
        }
        copy.setPixels(iArr, 0, width, 0, 0, width, i40);
        return copy;
    }

    /* renamed from: a */
    public static boolean m23931a(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        return m23935a(bitmap, m23867g(str), compressFormat, false);
    }

    /* renamed from: a */
    public static boolean m23936a(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat) {
        return m23935a(bitmap, file, compressFormat, false);
    }

    /* renamed from: a */
    public static boolean m23930a(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat, boolean z) {
        return m23935a(bitmap, m23867g(str), compressFormat, z);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002f -> B:35:0x0044). Please submit an issue!!! */
    /* renamed from: a */
    public static boolean m23935a(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, boolean z) {
        IOException e;
        BufferedOutputStream bufferedOutputStream;
        boolean z2 = false;
        if (m23878e(bitmap) || !m23873e(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            z2 = bitmap.compress(compressFormat, 100, bufferedOutputStream);
            if (z && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            bufferedOutputStream.close();
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            return z2;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        return z2;
    }

    /* renamed from: b */
    public static boolean m23903b(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return m23890c(file.getPath());
    }

    /* renamed from: c */
    public static boolean m23890c(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(str, options);
            if (options.outWidth != -1) {
                if (options.outHeight != -1) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: d */
    public static String m23880d(String str) {
        return m23892c(m23867g(str));
    }

    /* renamed from: c */
    public static String m23892c(File file) {
        Throwable th;
        IOException e;
        FileInputStream fileInputStream;
        String b;
        if (file == null) {
            return "";
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            b = m23902b(fileInputStream);
        } catch (IOException e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return m23869f(file.getAbsolutePath()).toUpperCase();
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        if (b != null) {
            try {
                fileInputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            return b;
        }
        fileInputStream.close();
        return m23869f(file.getAbsolutePath()).toUpperCase();
    }

    /* renamed from: f */
    private static String m23869f(String str) {
        if (m23865h(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || str.lastIndexOf(File.separator) >= lastIndexOf) ? "" : str.substring(lastIndexOf + 1);
    }

    /* renamed from: b */
    private static String m23902b(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[8];
            if (inputStream.read(bArr, 0, 8) != -1) {
                return m23889c(bArr);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private static String m23889c(byte[] bArr) {
        if (m23879d(bArr)) {
            return "JPEG";
        }
        if (m23871e(bArr)) {
            return "GIF";
        }
        if (m23868f(bArr)) {
            return "PNG";
        }
        if (m23866g(bArr)) {
            return "BMP";
        }
        return null;
    }

    /* renamed from: d */
    private static boolean m23879d(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -40;
    }

    /* renamed from: e */
    private static boolean m23871e(byte[] bArr) {
        return bArr.length >= 6 && bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70 && bArr[3] == 56 && (bArr[4] == 55 || bArr[4] == 57) && bArr[5] == 97;
    }

    /* renamed from: f */
    private static boolean m23868f(byte[] bArr) {
        return bArr.length >= 8 && bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71 && bArr[4] == 13 && bArr[5] == 10 && bArr[6] == 26 && bArr[7] == 10;
    }

    /* renamed from: g */
    private static boolean m23866g(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == 66 && bArr[1] == 77;
    }

    /* renamed from: e */
    private static boolean m23878e(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    /* renamed from: d */
    public static Bitmap m23884d(Bitmap bitmap, int i, int i2) {
        return m23944a(bitmap, i, i2, false);
    }

    /* renamed from: d */
    public static Bitmap m23883d(Bitmap bitmap, int i, int i2, boolean z) {
        return m23944a(bitmap, i, i2, z);
    }

    /* renamed from: d */
    public static Bitmap m23887d(Bitmap bitmap, float f, float f2) {
        return m23957a(bitmap, f, f2, false);
    }

    /* renamed from: d */
    public static Bitmap m23886d(Bitmap bitmap, float f, float f2, boolean z) {
        return m23957a(bitmap, f, f2, z);
    }

    /* renamed from: d */
    public static Bitmap m23885d(Bitmap bitmap, @IntRange(from = 0, m25695to = 100) int i) {
        return m23882d(bitmap, i, false);
    }

    /* renamed from: d */
    public static Bitmap m23882d(Bitmap bitmap, @IntRange(from = 0, m25695to = 100) int i, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    /* renamed from: a */
    public static Bitmap m23941a(Bitmap bitmap, long j) {
        return m23940a(bitmap, j, false);
    }

    /* renamed from: a */
    public static Bitmap m23940a(Bitmap bitmap, long j, boolean z) {
        byte[] bArr;
        if (m23878e(bitmap) || j <= 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.size() <= j) {
            bArr = byteArrayOutputStream.toByteArray();
        } else {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
            if (byteArrayOutputStream.size() >= j) {
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                int i = 0;
                int i2 = 100;
                int i3 = 0;
                while (i < i2) {
                    i3 = (i + i2) / 2;
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                    int i4 = (byteArrayOutputStream.size() > j ? 1 : (byteArrayOutputStream.size() == j ? 0 : -1));
                    if (i4 == 0) {
                        break;
                    } else if (i4 > 0) {
                        i2 = i3 - 1;
                    } else {
                        i = i3 + 1;
                    }
                }
                if (i2 == i3 - 1) {
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                }
                bArr = byteArrayOutputStream.toByteArray();
            }
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    /* renamed from: e */
    public static Bitmap m23877e(Bitmap bitmap, int i) {
        return m23874e(bitmap, i, false);
    }

    /* renamed from: e */
    public static Bitmap m23874e(Bitmap bitmap, int i, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    /* renamed from: e */
    public static Bitmap m23876e(Bitmap bitmap, int i, int i2) {
        return m23875e(bitmap, i, i2, false);
    }

    /* renamed from: e */
    public static Bitmap m23875e(Bitmap bitmap, int i, int i2, boolean z) {
        if (m23878e(bitmap)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        options.inSampleSize = m23928a(options, i, i2);
        options.inJustDecodeBounds = false;
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    /* renamed from: e */
    public static int[] m23872e(String str) {
        return m23881d(m23867g(str));
    }

    /* renamed from: d */
    public static int[] m23881d(File file) {
        if (file == null) {
            return new int[]{0, 0};
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return new int[]{options.outWidth, options.outHeight};
    }

    /* renamed from: a */
    private static int m23928a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        while (true) {
            if (i3 <= i2 && i4 <= i) {
                return i5;
            }
            i3 >>= 1;
            i4 >>= 1;
            i5 <<= 1;
        }
    }

    /* renamed from: g */
    private static File m23867g(String str) {
        if (m23865h(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: e */
    private static boolean m23873e(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !m23870f(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: f */
    private static boolean m23870f(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* renamed from: h */
    private static boolean m23865h(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    private static byte[] m23891c(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return byteArray;
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }
}
