package de.hdodenhof.circleimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;

/* loaded from: classes2.dex */
public class CircleImageView extends ImageView {

    /* renamed from: a */
    private static final ImageView.ScaleType f14607a = ImageView.ScaleType.CENTER_CROP;

    /* renamed from: b */
    private static final Bitmap.Config f14608b = Bitmap.Config.ARGB_8888;

    /* renamed from: c */
    private static final int f14609c = 2;

    /* renamed from: d */
    private static final int f14610d = 0;

    /* renamed from: e */
    private static final int f14611e = -16777216;

    /* renamed from: f */
    private static final int f14612f = 0;

    /* renamed from: g */
    private static final boolean f14613g = false;

    /* renamed from: A */
    private boolean f14614A;

    /* renamed from: h */
    private final RectF f14615h;

    /* renamed from: i */
    private final RectF f14616i;

    /* renamed from: j */
    private final Matrix f14617j;

    /* renamed from: k */
    private final Paint f14618k;

    /* renamed from: l */
    private final Paint f14619l;

    /* renamed from: m */
    private final Paint f14620m;

    /* renamed from: n */
    private int f14621n;

    /* renamed from: o */
    private int f14622o;

    /* renamed from: p */
    private int f14623p;

    /* renamed from: q */
    private Bitmap f14624q;

    /* renamed from: r */
    private BitmapShader f14625r;

    /* renamed from: s */
    private int f14626s;

    /* renamed from: t */
    private int f14627t;

    /* renamed from: u */
    private float f14628u;

    /* renamed from: v */
    private float f14629v;

    /* renamed from: w */
    private ColorFilter f14630w;

    /* renamed from: x */
    private boolean f14631x;

    /* renamed from: y */
    private boolean f14632y;

    /* renamed from: z */
    private boolean f14633z;

    public CircleImageView(Context context) {
        super(context);
        this.f14615h = new RectF();
        this.f14616i = new RectF();
        this.f14617j = new Matrix();
        this.f14618k = new Paint();
        this.f14619l = new Paint();
        this.f14620m = new Paint();
        this.f14621n = -16777216;
        this.f14622o = 0;
        this.f14623p = 0;
        m14888c();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f14615h = new RectF();
        this.f14616i = new RectF();
        this.f14617j = new Matrix();
        this.f14618k = new Paint();
        this.f14619l = new Paint();
        this.f14620m = new Paint();
        this.f14621n = -16777216;
        this.f14622o = 0;
        this.f14623p = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3056R.styleable.CircleImageView, i, 0);
        this.f14622o = obtainStyledAttributes.getDimensionPixelSize(C3056R.styleable.CircleImageView_civ_border_width, 0);
        this.f14621n = obtainStyledAttributes.getColor(C3056R.styleable.CircleImageView_civ_border_color, -16777216);
        this.f14633z = obtainStyledAttributes.getBoolean(C3056R.styleable.CircleImageView_civ_border_overlay, false);
        this.f14623p = obtainStyledAttributes.getColor(C3056R.styleable.CircleImageView_civ_circle_background_color, 0);
        obtainStyledAttributes.recycle();
        m14888c();
    }

    /* renamed from: c */
    private void m14888c() {
        super.setScaleType(f14607a);
        this.f14631x = true;
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new C3055a());
        }
        if (this.f14632y) {
            m14885f();
            this.f14632y = false;
        }
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return f14607a;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != f14607a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override // android.widget.ImageView
    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.f14614A) {
            super.onDraw(canvas);
        } else if (this.f14624q != null) {
            if (this.f14623p != 0) {
                canvas.drawCircle(this.f14615h.centerX(), this.f14615h.centerY(), this.f14628u, this.f14620m);
            }
            canvas.drawCircle(this.f14615h.centerX(), this.f14615h.centerY(), this.f14628u, this.f14618k);
            if (this.f14622o > 0) {
                canvas.drawCircle(this.f14616i.centerX(), this.f14616i.centerY(), this.f14629v, this.f14619l);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m14885f();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        m14885f();
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        m14885f();
    }

    public int getBorderColor() {
        return this.f14621n;
    }

    public void setBorderColor(@ColorInt int i) {
        if (i != this.f14621n) {
            this.f14621n = i;
            this.f14619l.setColor(this.f14621n);
            invalidate();
        }
    }

    public int getCircleBackgroundColor() {
        return this.f14623p;
    }

    public void setCircleBackgroundColor(@ColorInt int i) {
        if (i != this.f14623p) {
            this.f14623p = i;
            this.f14620m.setColor(i);
            invalidate();
        }
    }

    public void setCircleBackgroundColorResource(@ColorRes int i) {
        setCircleBackgroundColor(getContext().getResources().getColor(i));
    }

    public int getBorderWidth() {
        return this.f14622o;
    }

    public void setBorderWidth(int i) {
        if (i != this.f14622o) {
            this.f14622o = i;
            m14885f();
        }
    }

    /* renamed from: a */
    public boolean m14894a() {
        return this.f14633z;
    }

    public void setBorderOverlay(boolean z) {
        if (z != this.f14633z) {
            this.f14633z = z;
            m14885f();
        }
    }

    /* renamed from: b */
    public boolean m14890b() {
        return this.f14614A;
    }

    public void setDisableCircularTransformation(boolean z) {
        if (this.f14614A != z) {
            this.f14614A = z;
            m14886e();
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        m14886e();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        m14886e();
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i) {
        super.setImageResource(i);
        m14886e();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        m14886e();
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.f14630w) {
            this.f14630w = colorFilter;
            m14887d();
            invalidate();
        }
    }

    @Override // android.widget.ImageView
    public ColorFilter getColorFilter() {
        return this.f14630w;
    }

    /* renamed from: d */
    private void m14887d() {
        Paint paint = this.f14618k;
        if (paint != null) {
            paint.setColorFilter(this.f14630w);
        }
    }

    /* renamed from: a */
    private Bitmap m14892a(Drawable drawable) {
        Bitmap bitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(2, 2, f14608b);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), f14608b);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    private void m14886e() {
        if (this.f14614A) {
            this.f14624q = null;
        } else {
            this.f14624q = m14892a(getDrawable());
        }
        m14885f();
    }

    /* renamed from: f */
    private void m14885f() {
        int i;
        if (!this.f14631x) {
            this.f14632y = true;
        } else if (getWidth() != 0 || getHeight() != 0) {
            Bitmap bitmap = this.f14624q;
            if (bitmap == null) {
                invalidate();
                return;
            }
            this.f14625r = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.f14618k.setAntiAlias(true);
            this.f14618k.setDither(true);
            this.f14618k.setFilterBitmap(true);
            this.f14618k.setShader(this.f14625r);
            this.f14619l.setStyle(Paint.Style.STROKE);
            this.f14619l.setAntiAlias(true);
            this.f14619l.setColor(this.f14621n);
            this.f14619l.setStrokeWidth(this.f14622o);
            this.f14620m.setStyle(Paint.Style.FILL);
            this.f14620m.setAntiAlias(true);
            this.f14620m.setColor(this.f14623p);
            this.f14627t = this.f14624q.getHeight();
            this.f14626s = this.f14624q.getWidth();
            this.f14616i.set(m14884g());
            this.f14629v = Math.min((this.f14616i.height() - this.f14622o) / 2.0f, (this.f14616i.width() - this.f14622o) / 2.0f);
            this.f14615h.set(this.f14616i);
            if (!this.f14633z && (i = this.f14622o) > 0) {
                this.f14615h.inset(i - 1.0f, i - 1.0f);
            }
            this.f14628u = Math.min(this.f14615h.height() / 2.0f, this.f14615h.width() / 2.0f);
            m14887d();
            m14883h();
            invalidate();
        }
    }

    /* renamed from: g */
    private RectF m14884g() {
        int width;
        int height;
        int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        float paddingLeft = getPaddingLeft() + ((width - min) / 2.0f);
        float paddingTop = getPaddingTop() + ((height - min) / 2.0f);
        float f = min;
        return new RectF(paddingLeft, paddingTop, paddingLeft + f, f + paddingTop);
    }

    /* renamed from: h */
    private void m14883h() {
        float f;
        float f2;
        this.f14617j.set(null);
        float f3 = 0.0f;
        if (this.f14626s * this.f14615h.height() > this.f14615h.width() * this.f14627t) {
            f2 = this.f14615h.height() / this.f14627t;
            f = (this.f14615h.width() - (this.f14626s * f2)) * 0.5f;
        } else {
            f2 = this.f14615h.width() / this.f14626s;
            f3 = (this.f14615h.height() - (this.f14627t * f2)) * 0.5f;
            f = 0.0f;
        }
        this.f14617j.setScale(f2, f2);
        this.f14617j.postTranslate(((int) (f + 0.5f)) + this.f14615h.left, ((int) (f3 + 0.5f)) + this.f14615h.top);
        this.f14625r.setLocalMatrix(this.f14617j);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f14614A) {
            return super.onTouchEvent(motionEvent);
        }
        return m14893a(motionEvent.getX(), motionEvent.getY()) && super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private boolean m14893a(float f, float f2) {
        return this.f14616i.isEmpty() || Math.pow((double) (f - this.f14616i.centerX()), 2.0d) + Math.pow((double) (f2 - this.f14616i.centerY()), 2.0d) <= Math.pow((double) this.f14629v, 2.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(m25682b = 21)
    /* renamed from: de.hdodenhof.circleimageview.CircleImageView$a */
    /* loaded from: classes2.dex */
    public class C3055a extends ViewOutlineProvider {
        private C3055a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (CircleImageView.this.f14614A) {
                ViewOutlineProvider.BACKGROUND.getOutline(view, outline);
                return;
            }
            Rect rect = new Rect();
            CircleImageView.this.f14616i.roundOut(rect);
            outline.setRoundRect(rect, rect.width() / 2.0f);
        }
    }
}
