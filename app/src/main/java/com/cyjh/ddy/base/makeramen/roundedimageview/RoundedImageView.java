package com.cyjh.ddy.base.makeramen.roundedimageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.R;

/* loaded from: classes.dex */
public class RoundedImageView extends ImageView {

    /* renamed from: a */
    public static final String f7055a = "RoundedImageView";

    /* renamed from: b */
    public static final float f7056b = 0.0f;

    /* renamed from: c */
    public static final float f7057c = 0.0f;

    /* renamed from: f */
    private static final int f7060f = -2;

    /* renamed from: g */
    private static final int f7061g = 0;

    /* renamed from: h */
    private static final int f7062h = 1;

    /* renamed from: i */
    private static final int f7063i = 2;

    /* renamed from: k */
    private final float[] f7065k;

    /* renamed from: l */
    private Drawable f7066l;

    /* renamed from: m */
    private ColorStateList f7067m;

    /* renamed from: n */
    private float f7068n;

    /* renamed from: o */
    private ColorFilter f7069o;

    /* renamed from: p */
    private boolean f7070p;

    /* renamed from: q */
    private Drawable f7071q;

    /* renamed from: r */
    private boolean f7072r;

    /* renamed from: s */
    private boolean f7073s;

    /* renamed from: t */
    private boolean f7074t;

    /* renamed from: u */
    private int f7075u;

    /* renamed from: v */
    private int f7076v;

    /* renamed from: w */
    private ImageView.ScaleType f7077w;

    /* renamed from: x */
    private Shader.TileMode f7078x;

    /* renamed from: y */
    private Shader.TileMode f7079y;

    /* renamed from: e */
    static final /* synthetic */ boolean f7059e = !RoundedImageView.class.desiredAssertionStatus();

    /* renamed from: d */
    public static final Shader.TileMode f7058d = Shader.TileMode.CLAMP;

    /* renamed from: j */
    private static final ImageView.ScaleType[] f7064j = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};

    public RoundedImageView(Context context) {
        super(context);
        this.f7065k = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.f7067m = ColorStateList.valueOf(-16777216);
        this.f7068n = 0.0f;
        this.f7069o = null;
        this.f7070p = false;
        this.f7072r = false;
        this.f7073s = false;
        this.f7074t = false;
        Shader.TileMode tileMode = f7058d;
        this.f7078x = tileMode;
        this.f7079y = tileMode;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7065k = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.f7067m = ColorStateList.valueOf(-16777216);
        this.f7068n = 0.0f;
        this.f7069o = null;
        this.f7070p = false;
        this.f7072r = false;
        this.f7073s = false;
        this.f7074t = false;
        Shader.TileMode tileMode = f7058d;
        this.f7078x = tileMode;
        this.f7079y = tileMode;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_android_scaleType, -1);
        if (i2 >= 0) {
            m21957a(f7064j[i2]);
        } else {
            m21957a(ImageView.ScaleType.FIT_CENTER);
        }
        float dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius, -1);
        this.f7065k[0] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_left, -1);
        this.f7065k[1] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_right, -1);
        this.f7065k[2] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_right, -1);
        this.f7065k[3] = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_left, -1);
        int length = this.f7065k.length;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            float[] fArr = this.f7065k;
            if (fArr[i3] < 0.0f) {
                fArr[i3] = 0.0f;
            } else {
                z = true;
            }
        }
        if (!z) {
            dimensionPixelSize = dimensionPixelSize < 0.0f ? 0.0f : dimensionPixelSize;
            int length2 = this.f7065k.length;
            for (int i4 = 0; i4 < length2; i4++) {
                this.f7065k[i4] = dimensionPixelSize;
            }
        }
        this.f7068n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_riv_border_width, -1);
        if (this.f7068n < 0.0f) {
            this.f7068n = 0.0f;
        }
        this.f7067m = obtainStyledAttributes.getColorStateList(R.styleable.RoundedImageView_riv_border_color);
        if (this.f7067m == null) {
            this.f7067m = ColorStateList.valueOf(-16777216);
        }
        this.f7074t = obtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_riv_mutate_background, false);
        this.f7073s = obtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_riv_oval, false);
        int i5 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_riv_tile_mode, -2);
        if (i5 != -2) {
            m21961a(m21936h(i5));
            m21952b(m21936h(i5));
        }
        int i6 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_riv_tile_mode_x, -2);
        if (i6 != -2) {
            m21961a(m21936h(i6));
        }
        int i7 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_riv_tile_mode_y, -2);
        if (i7 != -2) {
            m21952b(m21936h(i7));
        }
        m21930n();
        m21946c(true);
        if (this.f7074t) {
            super.setBackgroundDrawable(this.f7066l);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: h */
    private static Shader.TileMode m21936h(int i) {
        switch (i) {
            case 0:
                return Shader.TileMode.CLAMP;
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return null;
        }
    }

    /* renamed from: a */
    protected void m21970a() {
        super.drawableStateChanged();
        invalidate();
    }

    /* renamed from: b */
    public ImageView.ScaleType m21955b() {
        return this.f7077w;
    }

    /* renamed from: a */
    public void m21957a(ImageView.ScaleType scaleType) {
        if (!f7059e && scaleType == null) {
            throw new AssertionError();
        } else if (this.f7077w != scaleType) {
            this.f7077w = scaleType;
            switch (C11201.f7080a[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            m21930n();
            m21946c(false);
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cyjh.ddy.base.makeramen.roundedimageview.RoundedImageView$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C11201 {

        /* renamed from: a */
        static final /* synthetic */ int[] f7080a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                f7080a[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7080a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7080a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7080a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7080a[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7080a[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7080a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* renamed from: a */
    public void m21960a(Drawable drawable) {
        this.f7075u = 0;
        this.f7071q = RoundedDrawable.m21916a(drawable);
        m21930n();
        super.setImageDrawable(this.f7071q);
    }

    /* renamed from: a */
    public void m21963a(Bitmap bitmap) {
        this.f7075u = 0;
        this.f7071q = RoundedDrawable.m21921a(bitmap);
        m21930n();
        super.setImageDrawable(this.f7071q);
    }

    /* renamed from: a */
    public void m21967a(int i) {
        if (this.f7075u != i) {
            this.f7075u = i;
            this.f7071q = m21932l();
            m21930n();
            super.setImageDrawable(this.f7071q);
        }
    }

    /* renamed from: a */
    public void m21958a(Uri uri) {
        super.setImageURI(uri);
        m21960a(getDrawable());
    }

    /* renamed from: l */
    private Drawable m21932l() {
        Resources resources = getResources();
        Drawable drawable = null;
        if (resources == null) {
            return null;
        }
        int i = this.f7075u;
        if (i != 0) {
            try {
                drawable = resources.getDrawable(i);
            } catch (Exception e) {
                CLog.m21879w(f7055a, "Unable to find resource: " + this.f7075u, e);
                this.f7075u = 0;
            }
        }
        return RoundedDrawable.m21916a(drawable);
    }

    /* renamed from: b */
    public void m21951b(Drawable drawable) {
        m21947c(drawable);
    }

    /* renamed from: b */
    public void m21953b(int i) {
        if (this.f7076v != i) {
            this.f7076v = i;
            this.f7066l = m21931m();
            m21947c(this.f7066l);
        }
    }

    /* renamed from: c */
    public void m21948c(int i) {
        this.f7066l = new ColorDrawable(i);
        m21947c(this.f7066l);
    }

    /* renamed from: m */
    private Drawable m21931m() {
        Resources resources = getResources();
        Drawable drawable = null;
        if (resources == null) {
            return null;
        }
        int i = this.f7076v;
        if (i != 0) {
            try {
                drawable = resources.getDrawable(i);
            } catch (Exception e) {
                CLog.m21879w(f7055a, "Unable to find resource: " + this.f7076v, e);
                this.f7076v = 0;
            }
        }
        return RoundedDrawable.m21916a(drawable);
    }

    /* renamed from: n */
    private void m21930n() {
        m21959a(this.f7071q, this.f7077w);
    }

    /* renamed from: c */
    private void m21946c(boolean z) {
        if (this.f7074t) {
            if (z) {
                this.f7066l = RoundedDrawable.m21916a(this.f7066l);
            }
            m21959a(this.f7066l, ImageView.ScaleType.FIT_XY);
        }
    }

    /* renamed from: a */
    public void m21962a(ColorFilter colorFilter) {
        if (this.f7069o != colorFilter) {
            this.f7069o = colorFilter;
            this.f7072r = true;
            this.f7070p = true;
            m21929o();
            invalidate();
        }
    }

    /* renamed from: o */
    private void m21929o() {
        Drawable drawable = this.f7071q;
        if (drawable != null && this.f7070p) {
            this.f7071q = drawable.mutate();
            if (this.f7072r) {
                this.f7071q.setColorFilter(this.f7069o);
            }
        }
    }

    /* renamed from: a */
    private void m21959a(Drawable drawable, ImageView.ScaleType scaleType) {
        if (drawable != null) {
            if (drawable instanceof RoundedDrawable) {
                RoundedDrawable bVar = (RoundedDrawable) drawable;
                bVar.m21915a(scaleType).m21910b(this.f7068n).m21922a(this.f7067m).m21900c(this.f7073s).m21917a(this.f7078x).m21907b(this.f7079y);
                float[] fArr = this.f7065k;
                if (fArr != null) {
                    bVar.m21926a(fArr[0], fArr[1], fArr[2], fArr[3]);
                }
                m21929o();
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    m21959a(layerDrawable.getDrawable(i), scaleType);
                }
            }
        }
    }

    @Deprecated
    /* renamed from: c */
    public void m21947c(Drawable drawable) {
        this.f7066l = drawable;
        m21946c(true);
        super.setBackgroundDrawable(this.f7066l);
    }

    /* renamed from: c */
    public float m21949c() {
        return m21945d();
    }

    /* renamed from: d */
    public float m21945d() {
        float f = 0.0f;
        for (float f2 : this.f7065k) {
            f = Math.max(f2, f);
        }
        return f;
    }

    /* renamed from: d */
    public float m21944d(int i) {
        return this.f7065k[i];
    }

    /* renamed from: e */
    public void m21942e(int i) {
        float dimension = getResources().getDimension(i);
        m21968a(dimension, dimension, dimension, dimension);
    }

    /* renamed from: a */
    public void m21965a(int i, int i2) {
        m21966a(i, getResources().getDimensionPixelSize(i2));
    }

    /* renamed from: a */
    public void m21969a(float f) {
        m21968a(f, f, f, f);
    }

    /* renamed from: a */
    public void m21966a(int i, float f) {
        float[] fArr = this.f7065k;
        if (fArr[i] != f) {
            fArr[i] = f;
            m21930n();
            m21946c(false);
            invalidate();
        }
    }

    /* renamed from: a */
    public void m21968a(float f, float f2, float f3, float f4) {
        float[] fArr = this.f7065k;
        if (fArr[0] != f || fArr[1] != f2 || fArr[2] != f4 || fArr[3] != f3) {
            float[] fArr2 = this.f7065k;
            fArr2[0] = f;
            fArr2[1] = f2;
            fArr2[3] = f3;
            fArr2[2] = f4;
            m21930n();
            m21946c(false);
            invalidate();
        }
    }

    /* renamed from: e */
    public float m21943e() {
        return this.f7068n;
    }

    /* renamed from: f */
    public void m21940f(int i) {
        m21954b(getResources().getDimension(i));
    }

    /* renamed from: b */
    public void m21954b(float f) {
        if (this.f7068n != f) {
            this.f7068n = f;
            m21930n();
            m21946c(false);
            invalidate();
        }
    }

    /* renamed from: f */
    public int m21941f() {
        return this.f7067m.getDefaultColor();
    }

    /* renamed from: g */
    public void m21938g(int i) {
        m21964a(ColorStateList.valueOf(i));
    }

    /* renamed from: g */
    public ColorStateList m21939g() {
        return this.f7067m;
    }

    /* renamed from: a */
    public void m21964a(ColorStateList colorStateList) {
        if (!this.f7067m.equals(colorStateList)) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(-16777216);
            }
            this.f7067m = colorStateList;
            m21930n();
            m21946c(false);
            if (this.f7068n > 0.0f) {
                invalidate();
            }
        }
    }

    /* renamed from: h */
    public boolean m21937h() {
        return this.f7073s;
    }

    /* renamed from: a */
    public void m21956a(boolean z) {
        this.f7073s = z;
        m21930n();
        m21946c(false);
        invalidate();
    }

    /* renamed from: i */
    public Shader.TileMode m21935i() {
        return this.f7078x;
    }

    /* renamed from: a */
    public void m21961a(Shader.TileMode tileMode) {
        if (this.f7078x != tileMode) {
            this.f7078x = tileMode;
            m21930n();
            m21946c(false);
            invalidate();
        }
    }

    /* renamed from: j */
    public Shader.TileMode m21934j() {
        return this.f7079y;
    }

    /* renamed from: b */
    public void m21952b(Shader.TileMode tileMode) {
        if (this.f7079y != tileMode) {
            this.f7079y = tileMode;
            m21930n();
            m21946c(false);
            invalidate();
        }
    }

    /* renamed from: k */
    public boolean m21933k() {
        return this.f7074t;
    }

    /* renamed from: b */
    public void m21950b(boolean z) {
        if (this.f7074t != z) {
            this.f7074t = z;
            m21946c(true);
            invalidate();
        }
    }
}
