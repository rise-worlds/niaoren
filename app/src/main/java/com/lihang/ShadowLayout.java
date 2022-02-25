package com.lihang;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;

/* loaded from: classes.dex */
public class ShadowLayout extends FrameLayout {

    /* renamed from: A */
    private float f10239A;

    /* renamed from: B */
    private float f10240B;

    /* renamed from: C */
    private float f10241C;

    /* renamed from: D */
    private float f10242D;

    /* renamed from: E */
    private Paint f10243E;

    /* renamed from: F */
    private float f10244F;

    /* renamed from: G */
    private int f10245G;

    /* renamed from: H */
    private int f10246H;

    /* renamed from: I */
    private boolean f10247I;

    /* renamed from: J */
    private int f10248J;

    /* renamed from: K */
    private int f10249K;

    /* renamed from: L */
    private int f10250L;

    /* renamed from: M */
    private int f10251M;

    /* renamed from: N */
    private int f10252N;

    /* renamed from: O */
    private TextView f10253O;

    /* renamed from: P */
    private int f10254P;

    /* renamed from: Q */
    private int f10255Q;

    /* renamed from: R */
    private String f10256R;

    /* renamed from: S */
    private String f10257S;

    /* renamed from: T */
    private View.OnClickListener f10258T;

    /* renamed from: a */
    private Drawable f10259a;

    /* renamed from: b */
    private int f10260b;

    /* renamed from: c */
    private Drawable f10261c;

    /* renamed from: d */
    private Drawable f10262d;

    /* renamed from: e */
    private View f10263e;

    /* renamed from: f */
    private int f10264f;

    /* renamed from: g */
    private int f10265g;

    /* renamed from: h */
    private int f10266h;

    /* renamed from: i */
    private float f10267i;

    /* renamed from: j */
    private float f10268j;

    /* renamed from: k */
    private float f10269k;

    /* renamed from: l */
    private float f10270l;

    /* renamed from: m */
    private boolean f10271m;

    /* renamed from: n */
    private boolean f10272n;

    /* renamed from: o */
    private boolean f10273o;

    /* renamed from: p */
    private boolean f10274p;

    /* renamed from: q */
    private Paint f10275q;

    /* renamed from: r */
    private Paint f10276r;

    /* renamed from: s */
    private int f10277s;

    /* renamed from: t */
    private int f10278t;

    /* renamed from: u */
    private int f10279u;

    /* renamed from: v */
    private int f10280v;

    /* renamed from: w */
    private RectF f10281w;

    /* renamed from: x */
    private int f10282x;

    /* renamed from: y */
    private boolean f10283y;

    /* renamed from: z */
    private boolean f10284z;

    public ShadowLayout(Context context) {
        this(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShadowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10260b = -101;
        this.f10265g = -101;
        this.f10281w = new RectF();
        this.f10282x = 1;
        this.f10283y = true;
        this.f10252N = -1;
        m19193a(context, attributeSet);
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        this.f10247I = z;
        m19199a();
        if (this.f10247I) {
            super.setOnClickListener(this.f10258T);
        }
        Paint paint = this.f10276r;
        if (paint != null && this.f10248J != -101 && this.f10250L != -101) {
            m19191a(paint);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.f10258T = onClickListener;
        if (this.f10247I) {
            super.setOnClickListener(onClickListener);
        }
    }

    /* renamed from: a */
    public void m19199a() {
        View view;
        if (this.f10282x == 1 && (view = this.f10263e) != null) {
            if (this.f10247I) {
                Drawable drawable = this.f10261c;
                if (drawable != null) {
                    setmBackGround(drawable);
                } else if (view.getBackground() != null) {
                    this.f10263e.getBackground().setAlpha(0);
                }
                this.f10276r.setColor(this.f10264f);
                postInvalidate();
            } else if (this.f10260b != -101) {
                if (this.f10261c != null) {
                    view.getBackground().setAlpha(0);
                }
                this.f10276r.setColor(this.f10260b);
                postInvalidate();
            } else {
                Drawable drawable2 = this.f10259a;
                if (drawable2 != null) {
                    setmBackGround(drawable2);
                    this.f10276r.setColor(Color.parseColor("#00000000"));
                    postInvalidate();
                }
            }
        }
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        if (getWidth() == 0) {
            addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.lihang.ShadowLayout.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    ShadowLayout.this.removeOnLayoutChangeListener(this);
                    ShadowLayout shadowLayout = ShadowLayout.this;
                    shadowLayout.setSelected(shadowLayout.isSelected());
                }
            });
        } else if (this.f10282x == 2) {
            if (z) {
                int i = this.f10265g;
                if (i != -101) {
                    this.f10276r.setColor(i);
                }
                this.f10276r.setShader(null);
                int i2 = this.f10246H;
                if (i2 != -101) {
                    this.f10243E.setColor(i2);
                }
                Drawable drawable = this.f10262d;
                if (drawable != null) {
                    setmBackGround(drawable);
                }
                TextView textView = this.f10253O;
                if (textView != null) {
                    textView.setTextColor(this.f10255Q);
                    if (!TextUtils.isEmpty(this.f10257S)) {
                        this.f10253O.setText(this.f10257S);
                    }
                }
            } else {
                this.f10276r.setColor(this.f10264f);
                if (this.f10248J != -101) {
                    m19191a(this.f10276r);
                }
                int i3 = this.f10245G;
                if (i3 != -101) {
                    this.f10243E.setColor(i3);
                }
                Drawable drawable2 = this.f10261c;
                if (drawable2 != null) {
                    setmBackGround(drawable2);
                }
                TextView textView2 = this.f10253O;
                if (textView2 != null) {
                    textView2.setTextColor(this.f10254P);
                    if (!TextUtils.isEmpty(this.f10256R)) {
                        this.f10253O.setText(this.f10256R);
                    }
                }
            }
            postInvalidate();
        }
    }

    public void setShadowHidden(boolean z) {
        this.f10283y = !z;
        if (getWidth() != 0 && getHeight() != 0) {
            m19185b(getWidth(), getHeight());
        }
    }

    public void setShadowOffsetX(float f) {
        if (this.f10283y) {
            float abs = Math.abs(f);
            float f2 = this.f10267i;
            if (abs <= f2) {
                this.f10269k = f;
            } else if (f > 0.0f) {
                this.f10269k = f2;
            } else {
                this.f10269k = -f2;
            }
            m19187b();
        }
    }

    public void setShadowOffsetY(float f) {
        if (this.f10283y) {
            float abs = Math.abs(f);
            float f2 = this.f10267i;
            if (abs <= f2) {
                this.f10270l = f;
            } else if (f > 0.0f) {
                this.f10270l = f2;
            } else {
                this.f10270l = -f2;
            }
            m19187b();
        }
    }

    public float getCornerRadius() {
        return this.f10268j;
    }

    public void setCornerRadius(int i) {
        this.f10268j = i;
        if (getWidth() != 0 && getHeight() != 0) {
            m19185b(getWidth(), getHeight());
        }
    }

    public float getShadowLimit() {
        return this.f10267i;
    }

    public void setShadowLimit(int i) {
        if (this.f10283y) {
            int dimension = (int) getContext().getResources().getDimension(C1672R.dimen.dp_5);
            if (i >= dimension) {
                this.f10267i = i;
            } else {
                this.f10267i = dimension;
            }
            m19187b();
        }
    }

    public void setShadowColor(int i) {
        this.f10266h = i;
        if (getWidth() != 0 && getHeight() != 0) {
            m19185b(getWidth(), getHeight());
        }
    }

    /* renamed from: a */
    public void m19194a(int i, int i2, int i3, int i4) {
        this.f10239A = i;
        this.f10240B = i2;
        this.f10241C = i3;
        this.f10242D = i4;
        if (getWidth() != 0 && getHeight() != 0) {
            m19185b(getWidth(), getHeight());
        }
    }

    public void setShadowHiddenTop(boolean z) {
        this.f10273o = !z;
        m19187b();
    }

    public void setShadowHiddenBottom(boolean z) {
        this.f10274p = !z;
        m19187b();
    }

    public void setShadowHiddenRight(boolean z) {
        this.f10272n = !z;
        m19187b();
    }

    public void setShadowHiddenLeft(boolean z) {
        this.f10271m = !z;
        m19187b();
    }

    public void setLayoutBackground(int i) {
        if (this.f10262d == null) {
            this.f10264f = i;
            if (this.f10282x != 2) {
                this.f10276r.setColor(this.f10264f);
            } else if (!isSelected()) {
                this.f10276r.setColor(this.f10264f);
            }
            postInvalidate();
            return;
        }
        throw new UnsupportedOperationException("使用了ShadowLayout_hl_layoutBackground_true属性，要与ShadowLayout_hl_layoutBackground属性统一为颜色");
    }

    public void setLayoutBackgroundTrue(int i) {
        if (this.f10261c == null) {
            this.f10265g = i;
            if (this.f10282x == 2 && isSelected()) {
                this.f10276r.setColor(this.f10265g);
            }
            postInvalidate();
            return;
        }
        throw new UnsupportedOperationException("使用了ShadowLayout_hl_layoutBackground属性，要与ShadowLayout_hl_layoutBackground_true属性统一为颜色");
    }

    public void setStrokeColor(int i) {
        this.f10245G = i;
        if (this.f10282x != 2) {
            this.f10243E.setColor(this.f10245G);
        } else if (!isSelected()) {
            this.f10243E.setColor(this.f10245G);
        }
        postInvalidate();
    }

    public void setStrokeColorTrue(int i) {
        this.f10246H = i;
        if (this.f10282x == 2 && isSelected()) {
            this.f10243E.setColor(this.f10246H);
        }
        postInvalidate();
    }

    public void setStrokeWidth(int i) {
        this.f10244F = i;
        if (this.f10244F > m19198a(7.0f)) {
            this.f10244F = m19198a(5.0f);
        }
        this.f10243E.setStrokeWidth(this.f10244F);
        postInvalidate();
    }

    @Override // android.view.View
    @RequiresApi(api = 16)
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = this.f10252N;
        if (i != -1) {
            this.f10253O = (TextView) findViewById(i);
            TextView textView = this.f10253O;
            if (textView != null) {
                if (this.f10254P == -101) {
                    this.f10254P = textView.getCurrentTextColor();
                }
                if (this.f10255Q == -101) {
                    this.f10255Q = this.f10253O.getCurrentTextColor();
                }
                this.f10253O.setTextColor(this.f10254P);
                if (!TextUtils.isEmpty(this.f10256R)) {
                    this.f10253O.setText(this.f10256R);
                }
            } else {
                throw new NullPointerException("ShadowLayout找不到hl_bindTextView，请确保绑定的资源id在ShadowLayout内");
            }
        }
        this.f10263e = getChildAt(0);
        if (this.f10263e == null) {
            this.f10263e = this;
            this.f10283y = false;
        }
        if (this.f10263e != null && this.f10282x != 2) {
            if (this.f10247I) {
                setmBackGround(this.f10261c);
                return;
            }
            setmBackGround(this.f10259a);
            int i2 = this.f10260b;
            if (i2 != -101) {
                this.f10276r.setColor(i2);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            m19185b(i, i2);
            if (this.f10248J != -101) {
                m19191a(this.f10276r);
            }
        }
    }

    /* renamed from: a */
    private void m19193a(Context context, AttributeSet attributeSet) {
        m19190a(attributeSet);
        this.f10275q = new Paint();
        this.f10275q.setAntiAlias(true);
        this.f10275q.setStyle(Paint.Style.FILL);
        this.f10243E = new Paint();
        this.f10243E.setAntiAlias(true);
        this.f10243E.setStyle(Paint.Style.STROKE);
        this.f10243E.setStrokeWidth(this.f10244F);
        int i = this.f10245G;
        if (i != -101) {
            this.f10243E.setColor(i);
        }
        this.f10276r = new Paint(1);
        this.f10276r.setStyle(Paint.Style.FILL);
        this.f10276r.setColor(this.f10264f);
        m19187b();
    }

    /* renamed from: a */
    public void m19191a(Paint paint) {
        if (!this.f10247I) {
            paint.setShader(null);
            return;
        }
        int i = this.f10249K;
        int[] iArr = i == -101 ? new int[]{this.f10248J, this.f10250L} : new int[]{this.f10248J, i, this.f10250L};
        int i2 = this.f10251M;
        if (i2 < 0) {
            this.f10251M = (i2 % 360) + 360;
        }
        switch ((this.f10251M % 360) / 45) {
            case 0:
                paint.setShader(new LinearGradient(this.f10277s, this.f10278t, getWidth() - this.f10279u, this.f10278t, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            case 1:
                paint.setShader(new LinearGradient(this.f10277s, getHeight() - this.f10280v, getWidth() - this.f10279u, this.f10278t, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            case 2:
                int width = getWidth() - this.f10279u;
                int i3 = this.f10277s;
                float f = ((width - i3) / 2) + i3;
                paint.setShader(new LinearGradient(f, getHeight() - this.f10280v, f, this.f10278t, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            case 3:
                paint.setShader(new LinearGradient(getWidth() - this.f10279u, getHeight() - this.f10280v, this.f10277s, this.f10278t, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            case 4:
                float width2 = getWidth() - this.f10279u;
                int i4 = this.f10278t;
                paint.setShader(new LinearGradient(width2, i4, this.f10277s, i4, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            case 5:
                paint.setShader(new LinearGradient(getWidth() - this.f10279u, this.f10278t, this.f10277s, getHeight() - this.f10280v, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            case 6:
                int width3 = getWidth() - this.f10279u;
                int i5 = this.f10277s;
                float f2 = ((width3 - i5) / 2) + i5;
                paint.setShader(new LinearGradient(f2, this.f10278t, f2, getHeight() - this.f10280v, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            case 7:
                paint.setShader(new LinearGradient(this.f10277s, this.f10278t, getWidth() - this.f10279u, getHeight() - this.f10280v, iArr, (float[]) null, Shader.TileMode.CLAMP));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public int m19198a(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: b */
    public void m19187b() {
        if (this.f10283y) {
            float f = this.f10267i;
            if (f > 0.0f) {
                if (this.f10284z) {
                    int abs = (int) (f + Math.abs(this.f10269k));
                    int abs2 = (int) (this.f10267i + Math.abs(this.f10270l));
                    if (this.f10271m) {
                        this.f10277s = abs;
                    } else {
                        this.f10277s = 0;
                    }
                    if (this.f10273o) {
                        this.f10278t = abs2;
                    } else {
                        this.f10278t = 0;
                    }
                    if (this.f10272n) {
                        this.f10279u = abs;
                    } else {
                        this.f10279u = 0;
                    }
                    if (this.f10274p) {
                        this.f10280v = abs2;
                    } else {
                        this.f10280v = 0;
                    }
                } else {
                    float abs3 = Math.abs(this.f10270l);
                    float f2 = this.f10267i;
                    if (abs3 > f2) {
                        if (this.f10270l > 0.0f) {
                            this.f10270l = f2;
                        } else {
                            this.f10270l = 0.0f - f2;
                        }
                    }
                    float abs4 = Math.abs(this.f10269k);
                    float f3 = this.f10267i;
                    if (abs4 > f3) {
                        if (this.f10269k > 0.0f) {
                            this.f10269k = f3;
                        } else {
                            this.f10269k = 0.0f - f3;
                        }
                    }
                    if (this.f10273o) {
                        this.f10278t = (int) (this.f10267i - this.f10270l);
                    } else {
                        this.f10278t = 0;
                    }
                    if (this.f10274p) {
                        this.f10280v = (int) (this.f10267i + this.f10270l);
                    } else {
                        this.f10280v = 0;
                    }
                    if (this.f10272n) {
                        this.f10279u = (int) (this.f10267i - this.f10269k);
                    } else {
                        this.f10279u = 0;
                    }
                    if (this.f10271m) {
                        this.f10277s = (int) (this.f10267i + this.f10269k);
                    } else {
                        this.f10277s = 0;
                    }
                }
                setPadding(this.f10277s, this.f10278t, this.f10279u, this.f10280v);
            }
        }
    }

    /* renamed from: b */
    private void m19185b(int i, int i2) {
        if (this.f10283y) {
            m19186b(this.f10266h);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(m19195a(i, i2, this.f10268j, this.f10267i, this.f10269k, this.f10270l, this.f10266h, 0));
            if (Build.VERSION.SDK_INT <= 16) {
                setBackgroundDrawable(bitmapDrawable);
            } else {
                setBackground(bitmapDrawable);
            }
        } else if (getChildAt(0) == null) {
            Drawable drawable = this.f10261c;
            if (drawable != null) {
                this.f10263e = this;
                if (this.f10247I) {
                    setmBackGround(drawable);
                } else {
                    m19199a();
                }
            } else {
                setBackgroundColor(Color.parseColor("#00000000"));
            }
        } else {
            setBackgroundColor(Color.parseColor("#00000000"));
        }
    }

    /* renamed from: a */
    private void m19190a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C1672R.styleable.ShadowLayout);
        if (obtainStyledAttributes != null) {
            try {
                this.f10283y = !obtainStyledAttributes.getBoolean(C1672R.styleable.ShadowLayout_hl_shadowHidden, false);
                this.f10271m = !obtainStyledAttributes.getBoolean(C1672R.styleable.ShadowLayout_hl_shadowHiddenLeft, false);
                this.f10272n = !obtainStyledAttributes.getBoolean(C1672R.styleable.ShadowLayout_hl_shadowHiddenRight, false);
                this.f10274p = !obtainStyledAttributes.getBoolean(C1672R.styleable.ShadowLayout_hl_shadowHiddenBottom, false);
                this.f10273o = !obtainStyledAttributes.getBoolean(C1672R.styleable.ShadowLayout_hl_shadowHiddenTop, false);
                this.f10268j = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_cornerRadius, getResources().getDimension(C1672R.dimen.dp_0));
                this.f10239A = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_cornerRadius_leftTop, -1.0f);
                this.f10241C = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_cornerRadius_leftBottom, -1.0f);
                this.f10240B = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_cornerRadius_rightTop, -1.0f);
                this.f10242D = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_cornerRadius_rightBottom, -1.0f);
                this.f10267i = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_shadowLimit, 0.0f);
                if (this.f10267i == 0.0f) {
                    this.f10283y = false;
                } else {
                    float dimension = (int) getContext().getResources().getDimension(C1672R.dimen.dp_5);
                    if (this.f10267i < dimension) {
                        this.f10267i = dimension;
                    }
                }
                this.f10269k = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_shadowOffsetX, 0.0f);
                this.f10270l = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_shadowOffsetY, 0.0f);
                this.f10266h = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_shadowColor, getResources().getColor(C1672R.color.default_shadow_color));
                this.f10282x = obtainStyledAttributes.getInt(C1672R.styleable.ShadowLayout_hl_shapeMode, 1);
                this.f10284z = obtainStyledAttributes.getBoolean(C1672R.styleable.ShadowLayout_hl_shadowSymmetry, true);
                this.f10264f = getResources().getColor(C1672R.color.default_shadowback_color);
                Drawable drawable = obtainStyledAttributes.getDrawable(C1672R.styleable.ShadowLayout_hl_layoutBackground);
                if (drawable != null) {
                    if (drawable instanceof ColorDrawable) {
                        this.f10264f = ((ColorDrawable) drawable).getColor();
                    } else {
                        this.f10261c = drawable;
                    }
                }
                Drawable drawable2 = obtainStyledAttributes.getDrawable(C1672R.styleable.ShadowLayout_hl_layoutBackground_true);
                if (drawable2 != null) {
                    if (drawable2 instanceof ColorDrawable) {
                        this.f10265g = ((ColorDrawable) drawable2).getColor();
                    } else {
                        this.f10262d = drawable2;
                    }
                }
                if (!(this.f10265g == -101 || this.f10261c == null)) {
                    throw new UnsupportedOperationException("使用了ShadowLayout_hl_layoutBackground_true属性，必须先设置ShadowLayout_hl_layoutBackground属性。且设置颜色时，必须保持都为颜色");
                }
                if (this.f10261c == null && this.f10262d != null) {
                    throw new UnsupportedOperationException("使用了ShadowLayout_hl_layoutBackground_true属性，必须先设置ShadowLayout_hl_layoutBackground属性。且设置图片时，必须保持都为图片");
                }
                this.f10245G = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_strokeColor, -101);
                this.f10246H = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_strokeColor_true, -101);
                if (this.f10245G == -101 && this.f10246H != -101) {
                    throw new UnsupportedOperationException("使用了ShadowLayout_hl_strokeColor_true属性，必须先设置ShadowLayout_hl_strokeColor属性");
                }
                this.f10244F = obtainStyledAttributes.getDimension(C1672R.styleable.ShadowLayout_hl_strokeWith, m19198a(1.0f));
                if (this.f10244F > m19198a(7.0f)) {
                    this.f10244F = m19198a(5.0f);
                }
                Drawable drawable3 = obtainStyledAttributes.getDrawable(C1672R.styleable.ShadowLayout_hl_layoutBackground_clickFalse);
                if (drawable3 != null) {
                    if (drawable3 instanceof ColorDrawable) {
                        this.f10260b = ((ColorDrawable) drawable3).getColor();
                    } else {
                        this.f10259a = drawable3;
                    }
                }
                this.f10248J = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_startColor, -101);
                this.f10249K = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_centerColor, -101);
                this.f10250L = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_endColor, -101);
                if (this.f10248J != -101 && this.f10250L == -101) {
                    throw new UnsupportedOperationException("使用了ShadowLayout_hl_startColor渐变起始色，必须搭配终止色ShadowLayout_hl_endColor");
                }
                this.f10251M = obtainStyledAttributes.getInt(C1672R.styleable.ShadowLayout_hl_angle, 0);
                if (this.f10251M % 45 == 0) {
                    if (this.f10282x == 3) {
                        if (this.f10264f == -101 || this.f10265g == -101) {
                            throw new NullPointerException("使用了ShadowLayout的水波纹，必须设置使用了ShadowLayout_hl_layoutBackground和使用了ShadowLayout_hl_layoutBackground_true属性，且为颜色值");
                        } else if (this.f10261c != null) {
                            this.f10282x = 1;
                        }
                    }
                    this.f10252N = obtainStyledAttributes.getResourceId(C1672R.styleable.ShadowLayout_hl_bindTextView, -1);
                    this.f10254P = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_textColor, -101);
                    this.f10255Q = obtainStyledAttributes.getColor(C1672R.styleable.ShadowLayout_hl_textColor_true, -101);
                    this.f10256R = obtainStyledAttributes.getString(C1672R.styleable.ShadowLayout_hl_text);
                    this.f10257S = obtainStyledAttributes.getString(C1672R.styleable.ShadowLayout_hl_text_true);
                    this.f10247I = obtainStyledAttributes.getBoolean(C1672R.styleable.ShadowLayout_clickable, true);
                    setClickable(this.f10247I);
                    return;
                }
                throw new IllegalArgumentException("Linear gradient requires 'angle' attribute to be a multiple of 45");
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* renamed from: a */
    private Bitmap m19195a(int i, int i2, float f, float f2, float f3, float f4, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        float f5 = f3 / 4.0f;
        float f6 = f4 / 4.0f;
        int i9 = i / 4;
        if (i9 == 0) {
            i9 = 1;
        }
        int i10 = i2 / 4;
        if (i10 == 0) {
            i10 = 1;
        }
        float f7 = f / 4.0f;
        float f8 = f2 / 4.0f;
        Bitmap createBitmap = Bitmap.createBitmap(i9, i10, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(this.f10271m ? f8 : Math.max(Math.max(f7, this.f10239A), Math.max(f7, this.f10241C)), this.f10273o ? f8 : Math.max(Math.max(f7, this.f10239A), Math.max(f7, this.f10240B)), this.f10272n ? i9 - f8 : i9 - Math.max(Math.max(f7, this.f10240B), Math.max(f7, this.f10242D)), this.f10274p ? i10 - f8 : i10 - Math.max(Math.max(f7, this.f10241C), Math.max(f7, this.f10242D)));
        if (this.f10284z) {
            if (f6 > 0.0f) {
                rectF.top += f6;
                rectF.bottom -= f6;
            } else if (f6 < 0.0f) {
                rectF.top += Math.abs(f6);
                rectF.bottom -= Math.abs(f6);
            }
            if (f5 > 0.0f) {
                rectF.left += f5;
                rectF.right -= f5;
            } else if (f5 < 0.0f) {
                rectF.left += Math.abs(f5);
                rectF.right -= Math.abs(f5);
            }
        } else {
            rectF.top -= f6;
            rectF.bottom -= f6;
            rectF.right -= f5;
            rectF.left -= f5;
        }
        this.f10275q.setColor(i4);
        if (!isInEditMode()) {
            this.f10275q.setShadowLayer(f8 / 2.0f, f5, f6, i3);
        }
        if (this.f10241C == -1.0f && this.f10239A == -1.0f && this.f10240B == -1.0f && this.f10242D == -1.0f) {
            canvas.drawRoundRect(rectF, f7, f7, this.f10275q);
        } else {
            RectF rectF2 = this.f10281w;
            rectF2.left = this.f10277s;
            rectF2.top = this.f10278t;
            rectF2.right = getWidth() - this.f10279u;
            this.f10281w.bottom = getHeight() - this.f10280v;
            this.f10275q.setAntiAlias(true);
            float f9 = this.f10239A;
            if (f9 == -1.0f) {
                i5 = ((int) this.f10268j) / 4;
            } else {
                i5 = ((int) f9) / 4;
            }
            float f10 = this.f10241C;
            if (f10 == -1.0f) {
                i6 = ((int) this.f10268j) / 4;
            } else {
                i6 = ((int) f10) / 4;
            }
            float f11 = this.f10240B;
            if (f11 == -1.0f) {
                i7 = ((int) this.f10268j) / 4;
            } else {
                i7 = ((int) f11) / 4;
            }
            float f12 = this.f10242D;
            if (f12 == -1.0f) {
                i8 = ((int) this.f10268j) / 4;
            } else {
                i8 = ((int) f12) / 4;
            }
            float f13 = i5;
            float f14 = i7;
            float f15 = i8;
            float f16 = i6;
            float[] fArr = {f13, f13, f14, f14, f15, f15, f16, f16};
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            canvas.drawPath(path, this.f10275q);
        }
        return createBitmap;
    }

    @Override // android.view.ViewGroup, android.view.View
    @RequiresApi(api = 21)
    protected void dispatchDraw(Canvas canvas) {
        int i = (int) (this.f10281w.bottom - this.f10281w.top);
        if (getChildAt(0) != null) {
            if (this.f10239A == -1.0f && this.f10241C == -1.0f && this.f10240B == -1.0f && this.f10242D == -1.0f) {
                float f = i / 2;
                if (this.f10268j > f) {
                    Path path = new Path();
                    path.addRoundRect(this.f10281w, f, f, Path.Direction.CW);
                    canvas.clipPath(path);
                } else {
                    Path path2 = new Path();
                    RectF rectF = this.f10281w;
                    float f2 = this.f10268j;
                    path2.addRoundRect(rectF, f2, f2, Path.Direction.CW);
                    canvas.clipPath(path2);
                }
            } else {
                float[] a = m19197a(i);
                Path path3 = new Path();
                path3.addRoundRect(this.f10277s, this.f10278t, getWidth() - this.f10279u, getHeight() - this.f10280v, a, Path.Direction.CW);
                canvas.clipPath(path3);
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.f10281w;
        rectF.left = this.f10277s;
        rectF.top = this.f10278t;
        rectF.right = getWidth() - this.f10279u;
        this.f10281w.bottom = getHeight() - this.f10280v;
        int i = (int) (this.f10281w.bottom - this.f10281w.top);
        if (getChildAt(0) == null) {
            return;
        }
        if (this.f10239A == -1.0f && this.f10241C == -1.0f && this.f10240B == -1.0f && this.f10242D == -1.0f) {
            float f = this.f10268j;
            float f2 = i / 2;
            if (f > f2) {
                if (this.f10282x != 3) {
                    if (this.f10261c == null && this.f10262d == null) {
                        canvas.drawRoundRect(this.f10281w, f2, f2, this.f10276r);
                        if (this.f10245G != -101) {
                            float f3 = ((i * ((int) (((this.f10281w.bottom - (this.f10244F / 2.0f)) - this.f10281w.top) - (this.f10244F / 2.0f)))) / 2) / ((int) (this.f10281w.bottom - this.f10281w.top));
                            canvas.drawRoundRect(new RectF(this.f10281w.left + (this.f10244F / 2.0f), this.f10281w.top + (this.f10244F / 2.0f), this.f10281w.right - (this.f10244F / 2.0f), this.f10281w.bottom - (this.f10244F / 2.0f)), f3, f3, this.f10243E);
                        }
                    }
                } else if (Build.VERSION.SDK_INT >= 21) {
                    m19188a(m19197a(i));
                }
            } else if (this.f10282x != 3) {
                if (this.f10261c == null && this.f10262d == null) {
                    canvas.drawRoundRect(this.f10281w, f, f, this.f10276r);
                    if (this.f10245G != -101) {
                        float f4 = (int) ((this.f10268j * ((int) (((this.f10281w.bottom - (this.f10244F / 2.0f)) - this.f10281w.top) - (this.f10244F / 2.0f)))) / ((int) (this.f10281w.bottom - this.f10281w.top)));
                        canvas.drawRoundRect(new RectF(this.f10281w.left + (this.f10244F / 2.0f), this.f10281w.top + (this.f10244F / 2.0f), this.f10281w.right - (this.f10244F / 2.0f), this.f10281w.bottom - (this.f10244F / 2.0f)), f4, f4, this.f10243E);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 21) {
                m19188a(m19197a(i));
            }
        } else if (this.f10261c == null && this.f10262d == null) {
            m19192a(canvas, i);
        }
    }

    /* renamed from: a */
    public float[] m19197a(int i) {
        float f = this.f10239A;
        int i2 = f == -1.0f ? (int) this.f10268j : (int) f;
        int i3 = i / 2;
        if (i2 > i3) {
            i2 = i3;
        }
        float f2 = this.f10240B;
        int i4 = f2 == -1.0f ? (int) this.f10268j : (int) f2;
        if (i4 > i3) {
            i4 = i3;
        }
        float f3 = this.f10242D;
        int i5 = f3 == -1.0f ? (int) this.f10268j : (int) f3;
        if (i5 > i3) {
            i5 = i3;
        }
        float f4 = this.f10241C;
        int i6 = f4 == -1.0f ? (int) this.f10268j : (int) f4;
        if (i6 <= i3) {
            i3 = i6;
        }
        float f5 = i2;
        float f6 = i4;
        float f7 = i5;
        float f8 = i3;
        return new float[]{f5, f5, f6, f6, f7, f7, f8, f8};
    }

    /* renamed from: a */
    public float[] m19196a(int i, int i2) {
        int i3 = i - (i2 * 2);
        float f = this.f10239A;
        int i4 = f == -1.0f ? (int) this.f10268j : (int) f;
        int i5 = i3 / 2;
        if (i4 > i5) {
            i4 = i5;
        }
        float f2 = this.f10240B;
        int i6 = f2 == -1.0f ? (int) this.f10268j : (int) f2;
        if (i6 > i5) {
            i6 = i5;
        }
        float f3 = this.f10242D;
        int i7 = f3 == -1.0f ? (int) this.f10268j : (int) f3;
        if (i7 > i5) {
            i7 = i5;
        }
        float f4 = this.f10241C;
        int i8 = f4 == -1.0f ? (int) this.f10268j : (int) f4;
        if (i8 <= i5) {
            i5 = i8;
        }
        float f5 = i4 - i2;
        float f6 = i6 - i2;
        float f7 = i7 - i2;
        float f8 = i5 - i2;
        return new float[]{f5, f5, f6, f6, f7, f7, f8, f8};
    }

    /* renamed from: a */
    private void m19192a(Canvas canvas, int i) {
        float[] a = m19197a(i);
        if (this.f10245G != -101) {
            if (this.f10282x != 3) {
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(a, null, null));
                if (this.f10276r.getShader() != null) {
                    shapeDrawable.getPaint().setShader(this.f10276r.getShader());
                } else {
                    shapeDrawable.getPaint().setColor(this.f10276r.getColor());
                }
                shapeDrawable.setBounds(this.f10277s, this.f10278t, getWidth() - this.f10279u, getHeight() - this.f10280v);
                shapeDrawable.draw(canvas);
                ShapeDrawable shapeDrawable2 = new ShapeDrawable(new RoundRectShape(m19196a(i, (int) this.f10244F), null, null));
                shapeDrawable2.getPaint().setColor(this.f10243E.getColor());
                shapeDrawable2.getPaint().setStyle(Paint.Style.STROKE);
                shapeDrawable2.getPaint().setStrokeWidth(this.f10244F);
                float f = this.f10244F;
                shapeDrawable2.setBounds((int) (this.f10277s + (f / 2.0f)), (int) (this.f10278t + (f / 2.0f)), (int) ((getWidth() - this.f10279u) - (this.f10244F / 2.0f)), (int) ((getHeight() - this.f10280v) - (this.f10244F / 2.0f)));
                shapeDrawable2.draw(canvas);
            } else if (Build.VERSION.SDK_INT >= 21) {
                m19188a(a);
            }
        } else if (this.f10282x != 3) {
            ShapeDrawable shapeDrawable3 = new ShapeDrawable(new RoundRectShape(a, null, null));
            if (this.f10276r.getShader() != null) {
                shapeDrawable3.getPaint().setShader(this.f10276r.getShader());
            } else {
                shapeDrawable3.getPaint().setColor(this.f10276r.getColor());
            }
            shapeDrawable3.setBounds(this.f10277s, this.f10278t, getWidth() - this.f10279u, getHeight() - this.f10280v);
            shapeDrawable3.draw(canvas);
        } else if (Build.VERSION.SDK_INT >= 21) {
            m19188a(a);
        }
    }

    @RequiresApi(api = 21)
    /* renamed from: a */
    private void m19188a(float[] fArr) {
        int i = this.f10264f;
        int i2 = this.f10265g;
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842908}, new int[]{16843518}, new int[0]}, new int[]{i2, i2, i2, i});
        RoundRectShape roundRectShape = new RoundRectShape(fArr, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(roundRectShape);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        if (this.f10248J != -101) {
            m19191a(shapeDrawable.getPaint());
        } else {
            shapeDrawable.getPaint().setColor(i);
        }
        ShapeDrawable shapeDrawable2 = new ShapeDrawable();
        shapeDrawable2.setShape(roundRectShape);
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
        if (this.f10248J != -101) {
            m19191a(shapeDrawable2.getPaint());
        } else {
            shapeDrawable2.getPaint().setColor(i);
        }
        this.f10263e.setBackground(new RippleDrawable(colorStateList, shapeDrawable2, shapeDrawable));
    }

    /* renamed from: b */
    public void m19186b(int i) {
        if (Color.alpha(i) == 255) {
            String hexString = Integer.toHexString(Color.red(i));
            String hexString2 = Integer.toHexString(Color.green(i));
            String hexString3 = Integer.toHexString(Color.blue(i));
            if (hexString.length() == 1) {
                hexString = ResultTypeConstant.f7213z + hexString;
            }
            if (hexString2.length() == 1) {
                hexString2 = ResultTypeConstant.f7213z + hexString2;
            }
            if (hexString3.length() == 1) {
                hexString3 = ResultTypeConstant.f7213z + hexString3;
            }
            this.f10266h = m19189a("#2a" + hexString + hexString2 + hexString3);
        }
    }

    /* renamed from: a */
    public static int m19189a(String str) throws IllegalArgumentException {
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        return Color.parseColor(str);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        TextView textView;
        TextView textView2;
        if (this.f10282x == 3) {
            if (this.f10247I) {
                int action = motionEvent.getAction();
                if (action != 3) {
                    switch (action) {
                        case 0:
                            if (this.f10282x == 3 && (textView2 = this.f10253O) != null) {
                                textView2.setTextColor(this.f10255Q);
                                if (!TextUtils.isEmpty(this.f10257S)) {
                                    this.f10253O.setText(this.f10257S);
                                    break;
                                }
                            }
                            break;
                    }
                }
                if (this.f10282x == 3 && (textView = this.f10253O) != null) {
                    textView.setTextColor(this.f10254P);
                    if (!TextUtils.isEmpty(this.f10256R)) {
                        this.f10253O.setText(this.f10256R);
                    }
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        if (!(this.f10265g == -101 && this.f10246H == -101 && this.f10262d == null) && this.f10247I) {
            int action2 = motionEvent.getAction();
            if (action2 != 3) {
                switch (action2) {
                    case 0:
                        if (this.f10282x == 1) {
                            int i = this.f10265g;
                            if (i != -101) {
                                this.f10276r.setColor(i);
                                this.f10276r.setShader(null);
                            }
                            int i2 = this.f10246H;
                            if (i2 != -101) {
                                this.f10243E.setColor(i2);
                            }
                            Drawable drawable = this.f10262d;
                            if (drawable != null) {
                                setmBackGround(drawable);
                            }
                            postInvalidate();
                            TextView textView3 = this.f10253O;
                            if (textView3 != null) {
                                textView3.setTextColor(this.f10255Q);
                                if (!TextUtils.isEmpty(this.f10257S)) {
                                    this.f10253O.setText(this.f10257S);
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
            if (this.f10282x == 1) {
                this.f10276r.setColor(this.f10264f);
                if (this.f10248J != -101) {
                    m19191a(this.f10276r);
                }
                int i3 = this.f10245G;
                if (i3 != -101) {
                    this.f10243E.setColor(i3);
                }
                Drawable drawable2 = this.f10261c;
                if (drawable2 != null) {
                    setmBackGround(drawable2);
                }
                postInvalidate();
                TextView textView4 = this.f10253O;
                if (textView4 != null) {
                    textView4.setTextColor(this.f10254P);
                    if (!TextUtils.isEmpty(this.f10256R)) {
                        this.f10253O.setText(this.f10256R);
                    }
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setmBackGround(Drawable drawable) {
        View view = this.f10263e;
        if (view != null && drawable != null) {
            if (this.f10239A == -1.0f && this.f10241C == -1.0f && this.f10240B == -1.0f && this.f10242D == -1.0f) {
                GlideRoundUtils.m19184a(view, drawable, this.f10268j);
                return;
            }
            float f = this.f10239A;
            int i = f == -1.0f ? (int) this.f10268j : (int) f;
            float f2 = this.f10241C;
            int i2 = f2 == -1.0f ? (int) this.f10268j : (int) f2;
            float f3 = this.f10240B;
            int i3 = f3 == -1.0f ? (int) this.f10268j : (int) f3;
            float f4 = this.f10242D;
            GlideRoundUtils.m19183a(this.f10263e, drawable, i, i2, i3, f4 == -1.0f ? (int) this.f10268j : (int) f4);
        }
    }
}
