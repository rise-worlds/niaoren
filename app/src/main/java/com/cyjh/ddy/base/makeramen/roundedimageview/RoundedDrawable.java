package com.cyjh.ddy.base.makeramen.roundedimageview;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;
import com.cyjh.ddy.base.utils.CLog;
import java.util.HashSet;

/* renamed from: com.cyjh.ddy.base.makeramen.roundedimageview.b */
/* loaded from: classes.dex */
public class RoundedDrawable extends Drawable {

    /* renamed from: a */
    public static final String f7085a = "RoundedDrawable";

    /* renamed from: b */
    public static final int f7086b = -16777216;

    /* renamed from: f */
    private final Bitmap f7090f;

    /* renamed from: h */
    private final int f7092h;

    /* renamed from: i */
    private final int f7093i;

    /* renamed from: c */
    private final RectF f7087c = new RectF();

    /* renamed from: d */
    private final RectF f7088d = new RectF();

    /* renamed from: e */
    private final RectF f7089e = new RectF();

    /* renamed from: j */
    private final RectF f7094j = new RectF();

    /* renamed from: l */
    private final Matrix f7096l = new Matrix();

    /* renamed from: m */
    private final RectF f7097m = new RectF();

    /* renamed from: n */
    private Shader.TileMode f7098n = Shader.TileMode.CLAMP;

    /* renamed from: o */
    private Shader.TileMode f7099o = Shader.TileMode.CLAMP;

    /* renamed from: p */
    private boolean f7100p = true;

    /* renamed from: q */
    private float f7101q = 0.0f;

    /* renamed from: r */
    private final boolean[] f7102r = {true, true, true, true};

    /* renamed from: s */
    private boolean f7103s = false;

    /* renamed from: t */
    private float f7104t = 0.0f;

    /* renamed from: u */
    private ColorStateList f7105u = ColorStateList.valueOf(-16777216);

    /* renamed from: v */
    private ImageView.ScaleType f7106v = ImageView.ScaleType.FIT_CENTER;

    /* renamed from: g */
    private final Paint f7091g = new Paint();

    /* renamed from: k */
    private final Paint f7095k = new Paint();

    /* renamed from: c */
    public int m21903c() {
        return -3;
    }

    public RoundedDrawable(Bitmap bitmap) {
        this.f7090f = bitmap;
        this.f7092h = bitmap.getWidth();
        this.f7093i = bitmap.getHeight();
        this.f7089e.set(0.0f, 0.0f, this.f7092h, this.f7093i);
        this.f7091g.setStyle(Paint.Style.FILL);
        this.f7091g.setAntiAlias(true);
        this.f7095k.setStyle(Paint.Style.STROKE);
        this.f7095k.setAntiAlias(true);
        this.f7095k.setColor(this.f7105u.getColorForState(getState(), -16777216));
        this.f7095k.setStrokeWidth(this.f7104t);
    }

    /* renamed from: a */
    public static RoundedDrawable m21921a(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }

    /* renamed from: a */
    public static Drawable m21916a(Drawable drawable) {
        if (drawable == null || (drawable instanceof RoundedDrawable)) {
            return drawable;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), m21916a(layerDrawable.getDrawable(i)));
            }
            return layerDrawable;
        }
        Bitmap b = m21906b(drawable);
        if (b != null) {
            return new RoundedDrawable(b);
        }
        return drawable;
    }

    /* renamed from: b */
    public static Bitmap m21906b(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            CLog.m21880w(f7085a, "Failed to create bitmap from drawable!");
            return null;
        }
    }

    /* renamed from: a */
    public Bitmap m21928a() {
        return this.f7090f;
    }

    /* renamed from: b */
    public boolean m21911b() {
        return this.f7105u.isStateful();
    }

    /* renamed from: a */
    protected boolean m21913a(int[] iArr) {
        int colorForState = this.f7105u.getColorForState(iArr, 0);
        if (this.f7095k.getColor() == colorForState) {
            return super.onStateChange(iArr);
        }
        this.f7095k.setColor(colorForState);
        return true;
    }

    /* renamed from: q */
    private void m21886q() {
        float f;
        float f2;
        switch (RoundedDrawable$1.f7054a[this.f7106v.ordinal()]) {
            case 1:
                this.f7094j.set(this.f7087c);
                RectF rectF = this.f7094j;
                float f3 = this.f7104t;
                rectF.inset(f3 / 2.0f, f3 / 2.0f);
                this.f7096l.reset();
                this.f7096l.setTranslate((int) (((this.f7094j.width() - this.f7092h) * 0.5f) + 0.5f), (int) (((this.f7094j.height() - this.f7093i) * 0.5f) + 0.5f));
                break;
            case 2:
                this.f7094j.set(this.f7087c);
                RectF rectF2 = this.f7094j;
                float f4 = this.f7104t;
                rectF2.inset(f4 / 2.0f, f4 / 2.0f);
                this.f7096l.reset();
                float f5 = 0.0f;
                if (this.f7092h * this.f7094j.height() > this.f7094j.width() * this.f7093i) {
                    f2 = this.f7094j.height() / this.f7093i;
                    f = (this.f7094j.width() - (this.f7092h * f2)) * 0.5f;
                } else {
                    f2 = this.f7094j.width() / this.f7092h;
                    f5 = (this.f7094j.height() - (this.f7093i * f2)) * 0.5f;
                    f = 0.0f;
                }
                this.f7096l.setScale(f2, f2);
                Matrix matrix = this.f7096l;
                float f6 = this.f7104t;
                matrix.postTranslate(((int) (f + 0.5f)) + (f6 / 2.0f), ((int) (f5 + 0.5f)) + (f6 / 2.0f));
                break;
            case 3:
                this.f7096l.reset();
                float min = (((float) this.f7092h) > this.f7087c.width() || ((float) this.f7093i) > this.f7087c.height()) ? Math.min(this.f7087c.width() / this.f7092h, this.f7087c.height() / this.f7093i) : 1.0f;
                this.f7096l.setScale(min, min);
                this.f7096l.postTranslate((int) (((this.f7087c.width() - (this.f7092h * min)) * 0.5f) + 0.5f), (int) (((this.f7087c.height() - (this.f7093i * min)) * 0.5f) + 0.5f));
                this.f7094j.set(this.f7089e);
                this.f7096l.mapRect(this.f7094j);
                RectF rectF3 = this.f7094j;
                float f7 = this.f7104t;
                rectF3.inset(f7 / 2.0f, f7 / 2.0f);
                this.f7096l.setRectToRect(this.f7089e, this.f7094j, Matrix.ScaleToFit.FILL);
                break;
            case 4:
            default:
                this.f7094j.set(this.f7089e);
                this.f7096l.setRectToRect(this.f7089e, this.f7087c, Matrix.ScaleToFit.CENTER);
                this.f7096l.mapRect(this.f7094j);
                RectF rectF4 = this.f7094j;
                float f8 = this.f7104t;
                rectF4.inset(f8 / 2.0f, f8 / 2.0f);
                this.f7096l.setRectToRect(this.f7089e, this.f7094j, Matrix.ScaleToFit.FILL);
                break;
            case 5:
                this.f7094j.set(this.f7089e);
                this.f7096l.setRectToRect(this.f7089e, this.f7087c, Matrix.ScaleToFit.END);
                this.f7096l.mapRect(this.f7094j);
                RectF rectF5 = this.f7094j;
                float f9 = this.f7104t;
                rectF5.inset(f9 / 2.0f, f9 / 2.0f);
                this.f7096l.setRectToRect(this.f7089e, this.f7094j, Matrix.ScaleToFit.FILL);
                break;
            case 6:
                this.f7094j.set(this.f7089e);
                this.f7096l.setRectToRect(this.f7089e, this.f7087c, Matrix.ScaleToFit.START);
                this.f7096l.mapRect(this.f7094j);
                RectF rectF6 = this.f7094j;
                float f10 = this.f7104t;
                rectF6.inset(f10 / 2.0f, f10 / 2.0f);
                this.f7096l.setRectToRect(this.f7089e, this.f7094j, Matrix.ScaleToFit.FILL);
                break;
            case 7:
                this.f7094j.set(this.f7087c);
                RectF rectF7 = this.f7094j;
                float f11 = this.f7104t;
                rectF7.inset(f11 / 2.0f, f11 / 2.0f);
                this.f7096l.reset();
                this.f7096l.setRectToRect(this.f7089e, this.f7094j, Matrix.ScaleToFit.FILL);
                break;
        }
        this.f7088d.set(this.f7094j);
    }

    /* renamed from: a */
    protected void m21918a(Rect rect) {
        super.onBoundsChange(rect);
        this.f7087c.set(rect);
        m21886q();
    }

    /* renamed from: a */
    public void m21920a(Canvas canvas) {
        if (this.f7100p) {
            BitmapShader bitmapShader = new BitmapShader(this.f7090f, this.f7098n, this.f7099o);
            if (this.f7098n == Shader.TileMode.CLAMP && this.f7099o == Shader.TileMode.CLAMP) {
                bitmapShader.setLocalMatrix(this.f7096l);
            }
            this.f7091g.setShader(bitmapShader);
            this.f7100p = false;
        }
        if (this.f7103s) {
            if (this.f7104t > 0.0f) {
                canvas.drawOval(this.f7088d, this.f7091g);
                canvas.drawOval(this.f7094j, this.f7095k);
                return;
            }
            canvas.drawOval(this.f7088d, this.f7091g);
        } else if (m21912a(this.f7102r)) {
            float f = this.f7101q;
            if (this.f7104t > 0.0f) {
                canvas.drawRoundRect(this.f7088d, f, f, this.f7091g);
                canvas.drawRoundRect(this.f7094j, f, f, this.f7095k);
                m21908b(canvas);
                m21901c(canvas);
                return;
            }
            canvas.drawRoundRect(this.f7088d, f, f, this.f7091g);
            m21908b(canvas);
        } else {
            canvas.drawRect(this.f7088d, this.f7091g);
            if (this.f7104t > 0.0f) {
                canvas.drawRect(this.f7094j, this.f7095k);
            }
        }
    }

    /* renamed from: b */
    private void m21908b(Canvas canvas) {
        if (!m21904b(this.f7102r) && this.f7101q != 0.0f) {
            float f = this.f7088d.left;
            float f2 = this.f7088d.top;
            float width = this.f7088d.width() + f;
            float height = this.f7088d.height() + f2;
            float f3 = this.f7101q;
            if (!this.f7102r[0]) {
                this.f7097m.set(f, f2, f + f3, f2 + f3);
                canvas.drawRect(this.f7097m, this.f7091g);
            }
            if (!this.f7102r[1]) {
                this.f7097m.set(width - f3, f2, width, f3);
                canvas.drawRect(this.f7097m, this.f7091g);
            }
            if (!this.f7102r[2]) {
                this.f7097m.set(width - f3, height - f3, width, height);
                canvas.drawRect(this.f7097m, this.f7091g);
            }
            if (!this.f7102r[3]) {
                this.f7097m.set(f, height - f3, f3 + f, height);
                canvas.drawRect(this.f7097m, this.f7091g);
            }
        }
    }

    /* renamed from: c */
    private void m21901c(Canvas canvas) {
        if (!m21904b(this.f7102r) && this.f7101q != 0.0f) {
            float f = this.f7088d.left;
            float f2 = this.f7088d.top;
            float width = f + this.f7088d.width();
            float height = f2 + this.f7088d.height();
            float f3 = this.f7101q;
            float f4 = this.f7104t / 2.0f;
            if (!this.f7102r[0]) {
                canvas.drawLine(f - f4, f2, f + f3, f2, this.f7095k);
                canvas.drawLine(f, f2 - f4, f, f2 + f3, this.f7095k);
            }
            if (!this.f7102r[1]) {
                canvas.drawLine((width - f3) - f4, f2, width, f2, this.f7095k);
                canvas.drawLine(width, f2 - f4, width, f2 + f3, this.f7095k);
            }
            if (!this.f7102r[2]) {
                canvas.drawLine((width - f3) - f4, height, width + f4, height, this.f7095k);
                canvas.drawLine(width, height - f3, width, height, this.f7095k);
            }
            if (!this.f7102r[3]) {
                canvas.drawLine(f - f4, height, f + f3, height, this.f7095k);
                canvas.drawLine(f, height - f3, f, height, this.f7095k);
            }
        }
    }

    /* renamed from: d */
    public int m21899d() {
        return this.f7091g.getAlpha();
    }

    /* renamed from: a */
    public void m21925a(int i) {
        this.f7091g.setAlpha(i);
        invalidateSelf();
    }

    /* renamed from: e */
    public ColorFilter m21898e() {
        return this.f7091g.getColorFilter();
    }

    /* renamed from: a */
    public void m21919a(ColorFilter colorFilter) {
        this.f7091g.setColorFilter(colorFilter);
        invalidateSelf();
    }

    /* renamed from: a */
    public void m21914a(boolean z) {
        this.f7091g.setDither(z);
        invalidateSelf();
    }

    /* renamed from: b */
    public void m21905b(boolean z) {
        this.f7091g.setFilterBitmap(z);
        invalidateSelf();
    }

    /* renamed from: f */
    public int m21897f() {
        return this.f7092h;
    }

    /* renamed from: g */
    public int m21896g() {
        return this.f7093i;
    }

    /* renamed from: h */
    public float m21895h() {
        return this.f7101q;
    }

    /* renamed from: b */
    public float m21909b(int i) {
        if (this.f7102r[i]) {
            return this.f7101q;
        }
        return 0.0f;
    }

    /* renamed from: a */
    public RoundedDrawable m21927a(float f) {
        m21926a(f, f, f, f);
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable m21924a(int i, float f) {
        int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i2 != 0) {
            float f2 = this.f7101q;
            if (!(f2 == 0.0f || f2 == f)) {
                throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
            }
        }
        if (i2 == 0) {
            if (m21923a(i, this.f7102r)) {
                this.f7101q = 0.0f;
            }
            this.f7102r[i] = false;
        } else {
            if (this.f7101q == 0.0f) {
                this.f7101q = f;
            }
            this.f7102r[i] = true;
        }
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable m21926a(float f, float f2, float f3, float f4) {
        HashSet hashSet = new HashSet(4);
        hashSet.add(Float.valueOf(f));
        hashSet.add(Float.valueOf(f2));
        hashSet.add(Float.valueOf(f3));
        hashSet.add(Float.valueOf(f4));
        hashSet.remove(Float.valueOf(0.0f));
        if (hashSet.size() <= 1) {
            if (!hashSet.isEmpty()) {
                float floatValue = ((Float) hashSet.iterator().next()).floatValue();
                if (Float.isInfinite(floatValue) || Float.isNaN(floatValue) || floatValue < 0.0f) {
                    throw new IllegalArgumentException("Invalid radius value: " + floatValue);
                }
                this.f7101q = floatValue;
            } else {
                this.f7101q = 0.0f;
            }
            boolean z = false;
            this.f7102r[0] = f > 0.0f;
            this.f7102r[1] = f2 > 0.0f;
            this.f7102r[2] = f3 > 0.0f;
            boolean[] zArr = this.f7102r;
            if (f4 > 0.0f) {
                z = true;
            }
            zArr[3] = z;
            return this;
        }
        throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
    }

    /* renamed from: i */
    public float m21894i() {
        return this.f7104t;
    }

    /* renamed from: b */
    public RoundedDrawable m21910b(float f) {
        this.f7104t = f;
        this.f7095k.setStrokeWidth(this.f7104t);
        return this;
    }

    /* renamed from: j */
    public int m21893j() {
        return this.f7105u.getDefaultColor();
    }

    /* renamed from: c */
    public RoundedDrawable m21902c(int i) {
        return m21922a(ColorStateList.valueOf(i));
    }

    /* renamed from: k */
    public ColorStateList m21892k() {
        return this.f7105u;
    }

    /* renamed from: a */
    public RoundedDrawable m21922a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f7105u = colorStateList;
        this.f7095k.setColor(this.f7105u.getColorForState(getState(), -16777216));
        return this;
    }

    /* renamed from: l */
    public boolean m21891l() {
        return this.f7103s;
    }

    /* renamed from: c */
    public RoundedDrawable m21900c(boolean z) {
        this.f7103s = z;
        return this;
    }

    /* renamed from: m */
    public ImageView.ScaleType m21890m() {
        return this.f7106v;
    }

    /* renamed from: a */
    public RoundedDrawable m21915a(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.f7106v != scaleType) {
            this.f7106v = scaleType;
            m21886q();
        }
        return this;
    }

    /* renamed from: n */
    public Shader.TileMode m21889n() {
        return this.f7098n;
    }

    /* renamed from: a */
    public RoundedDrawable m21917a(Shader.TileMode tileMode) {
        if (this.f7098n != tileMode) {
            this.f7098n = tileMode;
            this.f7100p = true;
            invalidateSelf();
        }
        return this;
    }

    /* renamed from: o */
    public Shader.TileMode m21888o() {
        return this.f7099o;
    }

    /* renamed from: b */
    public RoundedDrawable m21907b(Shader.TileMode tileMode) {
        if (this.f7099o != tileMode) {
            this.f7099o = tileMode;
            this.f7100p = true;
            invalidateSelf();
        }
        return this;
    }

    /* renamed from: a */
    private static boolean m21923a(int i, boolean[] zArr) {
        int length = zArr.length;
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= length) {
                return true;
            }
            boolean z2 = zArr[i2];
            if (i2 != i) {
                z = false;
            }
            if (z2 != z) {
                return false;
            }
            i2++;
        }
    }

    /* renamed from: a */
    private static boolean m21912a(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private static boolean m21904b(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: p */
    public Bitmap m21887p() {
        return m21906b(this);
    }
}
