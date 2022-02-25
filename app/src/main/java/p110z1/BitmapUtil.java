package p110z1;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.view.View;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: z1.fb */
/* loaded from: classes3.dex */
public class BitmapUtil {
    /* renamed from: a */
    public static String m2972a(Bitmap bitmap) {
        return Base64.encodeToString(m2970a(bitmap, 100), 0);
    }

    /* renamed from: b */
    public static String m2954b(Bitmap bitmap) {
        return Base64.encodeToString(m2952b(bitmap, 50), 0);
    }

    /* renamed from: a */
    public static Bitmap m2973a(Context context, Uri uri, int i, int i2) {
        FileNotFoundException e;
        Bitmap bitmap;
        BitmapFactory.Options options;
        try {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        } catch (FileNotFoundException e2) {
            e = e2;
            bitmap = null;
        }
        try {
            int ceil = (int) Math.ceil(options.outHeight / i2);
            int ceil2 = (int) Math.ceil(options.outWidth / i);
            if (ceil > 1 || ceil2 > 1) {
                if (ceil > ceil2) {
                    options.inSampleSize = ceil;
                } else {
                    options.inSampleSize = ceil2;
                }
            }
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        } catch (FileNotFoundException e3) {
            e = e3;
            e.printStackTrace();
            return bitmap;
        }
    }

    /* renamed from: b */
    public static Bitmap m2955b(Context context, Uri uri, int i, int i2) throws Exception {
        int i3;
        if (uri == null) {
            return null;
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            if (uri.toString().indexOf(ServiceManagerNative.CONTENT) != -1) {
                BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, options);
            } else {
                BitmapFactory.decodeFile(uri.toString(), options);
            }
            int i4 = options.outWidth;
            int i5 = options.outHeight;
            if (i4 > i5) {
                i3 = Math.round(i4 / i);
            } else {
                i3 = Math.round(i5 / i2);
            }
            if (i3 == 0) {
                i3 = 1;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i3;
            if (uri.toString().indexOf(ServiceManagerNative.CONTENT) != -1) {
                return BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, options);
            }
            return BitmapFactory.decodeStream(new FileInputStream(new File(uri.toString())), null, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Bitmap m2969a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /* renamed from: a */
    public static Bitmap m2971a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /* renamed from: b */
    public static Bitmap m2951b(Bitmap bitmap, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i7 = (i * height) / i2;
        if (i7 > width) {
            int i8 = (i2 * width) / i;
            i5 = (bitmap.getHeight() - i8) / 2;
            i4 = width;
            i3 = i8;
            i6 = 0;
        } else {
            i6 = (bitmap.getWidth() - i7) / 2;
            i3 = height;
            i4 = i7;
            i5 = 0;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(i / i4, i2 / i3);
        return Bitmap.createBitmap(bitmap, i6, i5, i4, i3, matrix, true);
    }

    /* renamed from: c */
    public static Bitmap m2944c(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = (bitmap.getHeight() * i) / i2;
        int i3 = (i2 * width) / i;
        int height2 = (bitmap.getHeight() - i3) / 2;
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / i3);
        return Bitmap.createBitmap(bitmap, 0, height2, width, i3, matrix, true);
    }

    /* renamed from: a */
    public static byte[] m2970a(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    public static byte[] m2952b(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP, i, byteArrayOutputStream);
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: c */
    public static InputStream m2945c(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, i, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    /* renamed from: a */
    public static Bitmap m2961a(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    /* renamed from: a */
    public static InputStream m2964a(Drawable drawable) {
        return m2945c(m2943c(drawable), 100);
    }

    /* renamed from: a */
    public static Drawable m2960a(InputStream inputStream, Resources resources) {
        return m2968a(m2961a(inputStream), resources);
    }

    /* renamed from: a */
    public static Drawable m2968a(Bitmap bitmap, Resources resources) {
        return new BitmapDrawable(resources, bitmap);
    }

    /* renamed from: c */
    public static byte[] m2946c(Bitmap bitmap) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    /* renamed from: b */
    public static byte[] m2949b(Drawable drawable) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                if (drawable instanceof BitmapDrawable) {
                    ((BitmapDrawable) drawable).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    /* renamed from: a */
    public static Bitmap m2958a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    /* renamed from: c */
    public static Bitmap m2943c(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static int[] m2959a(String str) {
        int[] iArr = {-1, -1};
        if (str == null) {
            return iArr;
        }
        File file = new File(str);
        if (file.exists() && !file.isDirectory()) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(new FileInputStream(str), null, options);
                iArr[0] = options.outWidth;
                iArr[1] = options.outHeight;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return iArr;
    }

    /* renamed from: b */
    public static int[] m2947b(InputStream inputStream) {
        int[] iArr = {-1, -1};
        if (inputStream == null) {
            return iArr;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            iArr[0] = options.outWidth;
            iArr[1] = options.outHeight;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return iArr;
    }

    /* renamed from: a */
    public static Rect m2979a(float f, float f2, float f3, float f4) {
        int round = Math.round(f - (f3 / 2.0f));
        int round2 = Math.round(f2 - (f4 / 2.0f));
        return new Rect(round, round2, Math.round(round + f3), Math.round(round2 + f4));
    }

    /* renamed from: d */
    public static Bitmap m2942d(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        Canvas canvas = new Canvas();
        canvas.setBitmap(createScaledBitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createScaledBitmap;
    }

    /* renamed from: a */
    public static Bitmap m2975a(Context context, Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        float f2 = NRZSScreenUtil.m11863e(context).density;
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawColor(-16777216, PorterDuff.Mode.SRC_OUT);
        float f3 = f2 * 2.0f;
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(f3, BlurMaskFilter.Blur.NORMAL);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setMaskFilter(blurMaskFilter);
        int[] iArr = new int[2];
        Bitmap extractAlpha = bitmap.extractAlpha(paint, iArr);
        Paint paint2 = new Paint();
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        paint2.setFilterBitmap(true);
        paint2.setAntiAlias(true);
        canvas.setBitmap(extractAlpha);
        canvas.drawBitmap(bitmap, -iArr[0], -iArr[1], paint2);
        canvas.drawRect(0.0f, 0.0f, -iArr[0], extractAlpha.getHeight(), paint2);
        canvas.drawRect(0.0f, 0.0f, extractAlpha.getWidth(), -iArr[1], paint2);
        Paint paint3 = new Paint();
        paint3.setFilterBitmap(true);
        paint3.setAntiAlias(true);
        paint3.setAlpha(150);
        canvas.setBitmap(bitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        int parseColor = Color.parseColor("#33b5e5");
        paint3.setColor(parseColor);
        canvas.drawBitmap(extractAlpha, iArr[0], iArr[1], paint3);
        extractAlpha.recycle();
        paint.setMaskFilter(new BlurMaskFilter(f3, BlurMaskFilter.Blur.OUTER));
        int[] iArr2 = new int[2];
        Bitmap extractAlpha2 = bitmap.extractAlpha(paint, iArr2);
        canvas.drawBitmap(extractAlpha2, iArr2[0], iArr2[1], paint3);
        extractAlpha2.recycle();
        paint3.setColor(parseColor);
        paint.setMaskFilter(new BlurMaskFilter(f2 * 1.0f, BlurMaskFilter.Blur.OUTER));
        int[] iArr3 = new int[2];
        Bitmap extractAlpha3 = bitmap.extractAlpha(paint, iArr3);
        canvas.drawBitmap(extractAlpha3, iArr3[0], iArr3[1], paint3);
        extractAlpha3.recycle();
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m2977a(Context context, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = i2;
        return BitmapFactory.decodeStream(context.getResources().openRawResource(i), null, options);
    }

    /* renamed from: a */
    public static Bitmap m2976a(Context context, int i, int i2, int i3) {
        int i4;
        int[] b = m2947b(context.getResources().openRawResource(i));
        if (b == null) {
            return null;
        }
        if (b[0] > b[1]) {
            i4 = Math.round(b[0] / i2);
        } else {
            i4 = Math.round(b[1] / i3);
        }
        if (i4 == 0) {
            i4 = 1;
        }
        return m2977a(context, i, i4);
    }

    /* renamed from: a */
    public static boolean m2963a(Drawable drawable, String str) {
        return m2965a(m2943c(drawable), str, Bitmap.CompressFormat.PNG);
    }

    /* renamed from: a */
    public static boolean m2966a(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        if (fileOutputStream == null) {
            return false;
        }
        return bitmap.compress(compressFormat, 100, fileOutputStream);
    }

    /* renamed from: b */
    public static boolean m2950b(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        if (fileOutputStream == null) {
            return false;
        }
        return bitmap.compress(compressFormat, 100, fileOutputStream);
    }

    /* renamed from: a */
    public static boolean m2965a(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fileOutputStream == null) {
            return false;
        }
        return bitmap.compress(compressFormat, 100, fileOutputStream);
    }

    /* renamed from: d */
    public static void m2940d(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    /* renamed from: e */
    public static void m2939e(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    /* renamed from: e */
    public static void m2937e(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                m2939e(((BitmapDrawable) drawable).getBitmap());
            }
            drawable.setCallback(null);
        }
    }

    /* renamed from: a */
    public static Bitmap m2967a(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        return createBitmap;
    }

    /* renamed from: b */
    public static Bitmap m2956b(Context context, int i, int i2, int i3) {
        Drawable drawable = context.getResources().getDrawable(i3);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i, i2);
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m2978a(Context context, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(context.getResources().openRawResource(i), null, options);
    }

    /* renamed from: a */
    public static Bitmap m2962a(View view) {
        view.clearFocus();
        view.setPressed(false);
        boolean willNotCacheDrawing = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);
        int drawingCacheBackgroundColor = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(0);
        if (drawingCacheBackgroundColor != 0) {
            view.destroyDrawingCache();
        }
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap = null;
        if (drawingCache == null) {
            return null;
        }
        try {
            bitmap = Bitmap.createBitmap(drawingCache);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCacheDrawing);
        view.setDrawingCacheBackgroundColor(drawingCacheBackgroundColor);
        return bitmap;
    }

    /* renamed from: b */
    public static Bitmap m2948b(View view) {
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache != null) {
            return drawingCache;
        }
        view.clearFocus();
        view.setPressed(false);
        view.setWillNotCacheDrawing(false);
        view.setDrawingCacheEnabled(true);
        if (view.getDrawingCacheBackgroundColor() != 0) {
            view.destroyDrawingCache();
            view.setDrawingCacheBackgroundColor(0);
        }
        view.buildDrawingCache();
        Bitmap drawingCache2 = view.getDrawingCache();
        if (drawingCache2 == null) {
            return null;
        }
        return drawingCache2;
    }

    /* renamed from: a */
    public Bitmap m2974a(Context context, Bitmap bitmap, int i, int i2) {
        int i3;
        int i4;
        Canvas canvas;
        Paint paint = new Paint();
        Bitmap b = m2957b(context, i);
        Bitmap b2 = m2957b(context, i2);
        int width = b2.getWidth();
        int height = b2.getHeight();
        int width2 = b.getWidth();
        int height2 = b.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888);
        Bitmap b3 = m2951b(bitmap, width, height);
        Canvas canvas2 = new Canvas(createBitmap);
        canvas2.drawBitmap(b, 0.0f, 0.0f, paint);
        int i5 = (width2 / 2) - (width / 2);
        int i6 = (height2 / 2) - (height / 2);
        if (Build.VERSION.SDK_INT >= 21) {
            canvas2.saveLayer(i5, i6, width + i5, height + i6, null);
            canvas = canvas2;
        } else {
            float f = height + i6;
            canvas = canvas2;
            canvas2.saveLayer(i5, i6, width + i5, f, null, 31);
        }
        canvas.drawBitmap(b2, i5, i6, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(b3, i5 + (i3 - (b3.getWidth() / 2)), i6 + (i4 - (b3.getHeight() / 2)), paint);
        paint.setXfermode(null);
        canvas.restore();
        return createBitmap;
    }

    /* renamed from: b */
    public static Bitmap m2957b(Context context, int i) {
        return BitmapFactory.decodeResource(context.getResources(), i);
    }

    /* renamed from: d */
    public static Bitmap m2941d(Bitmap bitmap, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        float f = i;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    /* renamed from: b */
    public static Bitmap m2953b(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    /* renamed from: e */
    public Bitmap m2938e(Bitmap bitmap, int i) {
        int i2;
        if (bitmap == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i3 = 16777215 & i;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            int i5 = iArr[i4];
            if (!(i5 == 0 || (i2 = i5 & (-16777216)) == 0)) {
                iArr[i4] = i2 | i3;
            }
        }
        return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
    }
}
