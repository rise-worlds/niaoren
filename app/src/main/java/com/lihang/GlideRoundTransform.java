package com.lihang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import java.security.MessageDigest;

/* renamed from: com.lihang.b */
/* loaded from: classes.dex */
class GlideRoundTransform implements Transformation<Bitmap> {

    /* renamed from: a */
    private BitmapPool f10292a;

    /* renamed from: b */
    private float f10293b;

    /* renamed from: c */
    private float f10294c;

    /* renamed from: d */
    private float f10295d;

    /* renamed from: e */
    private float f10296e;

    /* renamed from: f */
    private boolean f10297f;

    /* renamed from: g */
    private boolean f10298g;

    /* renamed from: h */
    private boolean f10299h;

    /* renamed from: i */
    private boolean f10300i;

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }

    public GlideRoundTransform(Context context, float f, float f2, float f3, float f4) {
        this.f10292a = Glide.get(context).getBitmapPool();
        this.f10293b = f;
        if (f != 0.0f) {
            this.f10297f = true;
        }
        this.f10294c = f2;
        if (f2 != 0.0f) {
            this.f10299h = true;
        }
        this.f10295d = f3;
        if (f3 != 0.0f) {
            this.f10298g = true;
        }
        this.f10296e = f4;
        if (f4 != 0.0f) {
            this.f10300i = true;
        }
    }

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int i, int i2) {
        int i3;
        int i4;
        Bitmap bitmap = resource.get();
        if (i > i2) {
            float f = i2;
            float f2 = i;
            i3 = bitmap.getWidth();
            i4 = (int) (bitmap.getWidth() * (f / f2));
            if (i4 > bitmap.getHeight()) {
                i4 = bitmap.getHeight();
                i3 = (int) (bitmap.getHeight() * (f2 / f));
            }
        } else if (i < i2) {
            float f3 = i;
            float f4 = i2;
            int height = bitmap.getHeight();
            int height2 = (int) (bitmap.getHeight() * (f3 / f4));
            if (height2 > bitmap.getWidth()) {
                i3 = bitmap.getWidth();
                i4 = (int) (bitmap.getWidth() * (f4 / f3));
            } else {
                i3 = height2;
                i4 = height;
            }
        } else {
            i3 = bitmap.getHeight();
            i4 = i3;
        }
        float f5 = i4 / i2;
        this.f10293b *= f5;
        this.f10294c *= f5;
        this.f10295d *= f5;
        this.f10296e *= f5;
        Bitmap bitmap2 = this.f10292a.get(i3, i4, Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int width = (bitmap.getWidth() - i3) / 2;
        int height3 = (bitmap.getHeight() - i4) / 2;
        if (!(width == 0 && height3 == 0)) {
            Matrix matrix = new Matrix();
            matrix.setTranslate(-width, -height3);
            bitmapShader.setLocalMatrix(matrix);
        }
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        float f6 = this.f10293b;
        float f7 = this.f10295d;
        float f8 = this.f10296e;
        float f9 = this.f10294c;
        float[] fArr = {f6, f6, f7, f7, f8, f8, f9, f9};
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.drawPath(path, paint);
        return BitmapResource.obtain(bitmap2, this.f10292a);
    }
}
