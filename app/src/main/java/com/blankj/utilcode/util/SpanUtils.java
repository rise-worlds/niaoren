package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.widget.TextView;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class SpanUtils {

    /* renamed from: a */
    public static final int f6513a = 0;

    /* renamed from: b */
    public static final int f6514b = 1;

    /* renamed from: c */
    public static final int f6515c = 2;

    /* renamed from: d */
    public static final int f6516d = 3;

    /* renamed from: e */
    private static final int f6517e = -16777217;

    /* renamed from: f */
    private static final String f6518f = System.getProperty("line.separator");

    /* renamed from: A */
    private boolean f6519A;

    /* renamed from: B */
    private boolean f6520B;

    /* renamed from: C */
    private boolean f6521C;

    /* renamed from: D */
    private boolean f6522D;

    /* renamed from: E */
    private boolean f6523E;

    /* renamed from: F */
    private boolean f6524F;

    /* renamed from: G */
    private String f6525G;

    /* renamed from: H */
    private Typeface f6526H;

    /* renamed from: I */
    private Layout.Alignment f6527I;

    /* renamed from: J */
    private int f6528J;

    /* renamed from: K */
    private ClickableSpan f6529K;

    /* renamed from: L */
    private String f6530L;

    /* renamed from: M */
    private float f6531M;

    /* renamed from: N */
    private BlurMaskFilter.Blur f6532N;

    /* renamed from: O */
    private Shader f6533O;

    /* renamed from: P */
    private float f6534P;

    /* renamed from: Q */
    private float f6535Q;

    /* renamed from: R */
    private float f6536R;

    /* renamed from: S */
    private int f6537S;

    /* renamed from: T */
    private Object[] f6538T;

    /* renamed from: U */
    private Bitmap f6539U;

    /* renamed from: V */
    private Drawable f6540V;

    /* renamed from: W */
    private Uri f6541W;

    /* renamed from: X */
    private int f6542X;

    /* renamed from: Y */
    private int f6543Y;

    /* renamed from: Z */
    private int f6544Z;

    /* renamed from: aa */
    private int f6545aa;

    /* renamed from: ab */
    private C0947g f6546ab;

    /* renamed from: ac */
    private int f6547ac;

    /* renamed from: ad */
    private final int f6548ad;

    /* renamed from: ae */
    private final int f6549ae;

    /* renamed from: af */
    private final int f6550af;

    /* renamed from: g */
    private TextView f6551g;

    /* renamed from: h */
    private CharSequence f6552h;

    /* renamed from: i */
    private int f6553i;

    /* renamed from: j */
    private int f6554j;

    /* renamed from: k */
    private int f6555k;

    /* renamed from: l */
    private int f6556l;

    /* renamed from: m */
    private int f6557m;

    /* renamed from: n */
    private int f6558n;

    /* renamed from: o */
    private int f6559o;

    /* renamed from: p */
    private int f6560p;

    /* renamed from: q */
    private int f6561q;

    /* renamed from: r */
    private int f6562r;

    /* renamed from: s */
    private int f6563s;

    /* renamed from: t */
    private int f6564t;

    /* renamed from: u */
    private int f6565u;

    /* renamed from: v */
    private int f6566v;

    /* renamed from: w */
    private boolean f6567w;

    /* renamed from: x */
    private float f6568x;

    /* renamed from: y */
    private float f6569y;

    /* renamed from: z */
    private boolean f6570z;

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.blankj.utilcode.util.SpanUtils$a */
    /* loaded from: classes.dex */
    public @interface AbstractC0941a {
    }

    private SpanUtils(TextView textView) {
        this();
        this.f6551g = textView;
    }

    public SpanUtils() {
        this.f6548ad = 0;
        this.f6549ae = 1;
        this.f6550af = 2;
        this.f6546ab = new C0947g();
        this.f6552h = "";
        this.f6547ac = -1;
        m24112k();
    }

    /* renamed from: k */
    private void m24112k() {
        this.f6553i = 33;
        this.f6554j = f6517e;
        this.f6555k = f6517e;
        this.f6556l = -1;
        this.f6558n = f6517e;
        this.f6561q = -1;
        this.f6563s = f6517e;
        this.f6566v = -1;
        this.f6568x = -1.0f;
        this.f6569y = -1.0f;
        this.f6570z = false;
        this.f6519A = false;
        this.f6520B = false;
        this.f6521C = false;
        this.f6522D = false;
        this.f6523E = false;
        this.f6524F = false;
        this.f6525G = null;
        this.f6526H = null;
        this.f6527I = null;
        this.f6528J = -1;
        this.f6529K = null;
        this.f6530L = null;
        this.f6531M = -1.0f;
        this.f6533O = null;
        this.f6534P = -1.0f;
        this.f6538T = null;
        this.f6539U = null;
        this.f6540V = null;
        this.f6541W = null;
        this.f6542X = -1;
        this.f6544Z = -1;
    }

    /* renamed from: a */
    public SpanUtils m24155a(int i) {
        this.f6553i = i;
        return this;
    }

    /* renamed from: b */
    public SpanUtils m24135b(@ColorInt int i) {
        this.f6554j = i;
        return this;
    }

    /* renamed from: c */
    public SpanUtils m24129c(@ColorInt int i) {
        this.f6555k = i;
        return this;
    }

    /* renamed from: d */
    public SpanUtils m24126d(@IntRange(from = 0) int i) {
        return m24154a(i, 2);
    }

    /* renamed from: a */
    public SpanUtils m24154a(@IntRange(from = 0) int i, int i2) {
        this.f6556l = i;
        this.f6557m = i2;
        return this;
    }

    /* renamed from: e */
    public SpanUtils m24123e(@ColorInt int i) {
        return m24153a(i, 2, 2);
    }

    /* renamed from: a */
    public SpanUtils m24153a(@ColorInt int i, @IntRange(from = 1) int i2, @IntRange(from = 0) int i3) {
        this.f6558n = i;
        this.f6559o = i2;
        this.f6560p = i3;
        return this;
    }

    /* renamed from: b */
    public SpanUtils m24134b(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        this.f6561q = i;
        this.f6562r = i2;
        return this;
    }

    /* renamed from: f */
    public SpanUtils m24121f(@IntRange(from = 0) int i) {
        return m24133b(0, 3, i);
    }

    /* renamed from: b */
    public SpanUtils m24133b(@ColorInt int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        this.f6563s = i;
        this.f6564t = i2;
        this.f6565u = i3;
        return this;
    }

    /* renamed from: g */
    public SpanUtils m24119g(@IntRange(from = 0) int i) {
        return m24152a(i, false);
    }

    /* renamed from: a */
    public SpanUtils m24152a(@IntRange(from = 0) int i, boolean z) {
        this.f6566v = i;
        this.f6567w = z;
        return this;
    }

    /* renamed from: a */
    public SpanUtils m24158a(float f) {
        this.f6568x = f;
        return this;
    }

    /* renamed from: b */
    public SpanUtils m24136b(float f) {
        this.f6569y = f;
        return this;
    }

    /* renamed from: a */
    public SpanUtils m24159a() {
        this.f6570z = true;
        return this;
    }

    /* renamed from: b */
    public SpanUtils m24137b() {
        this.f6519A = true;
        return this;
    }

    /* renamed from: c */
    public SpanUtils m24130c() {
        this.f6520B = true;
        return this;
    }

    /* renamed from: d */
    public SpanUtils m24127d() {
        this.f6521C = true;
        return this;
    }

    /* renamed from: e */
    public SpanUtils m24124e() {
        this.f6522D = true;
        return this;
    }

    /* renamed from: f */
    public SpanUtils m24122f() {
        this.f6523E = true;
        return this;
    }

    /* renamed from: g */
    public SpanUtils m24120g() {
        this.f6524F = true;
        return this;
    }

    /* renamed from: a */
    public SpanUtils m24139a(@NonNull String str) {
        if (str != null) {
            this.f6525G = str;
            return this;
        }
        throw new NullPointerException("Argument 'fontFamily' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24148a(@NonNull Typeface typeface) {
        if (typeface != null) {
            this.f6526H = typeface;
            return this;
        }
        throw new NullPointerException("Argument 'typeface' of type Typeface (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24143a(@NonNull Layout.Alignment alignment) {
        if (alignment != null) {
            this.f6527I = alignment;
            return this;
        }
        throw new NullPointerException("Argument 'alignment' of type Alignment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public SpanUtils m24117h(int i) {
        this.f6528J = i;
        return this;
    }

    /* renamed from: a */
    public SpanUtils m24142a(@NonNull ClickableSpan clickableSpan) {
        if (clickableSpan != null) {
            TextView textView = this.f6551g;
            if (textView != null && textView.getMovementMethod() == null) {
                this.f6551g.setMovementMethod(LinkMovementMethod.getInstance());
            }
            this.f6529K = clickableSpan;
            return this;
        }
        throw new NullPointerException("Argument 'clickSpan' of type ClickableSpan (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: b */
    public SpanUtils m24131b(@NonNull String str) {
        if (str != null) {
            TextView textView = this.f6551g;
            if (textView != null && textView.getMovementMethod() == null) {
                this.f6551g.setMovementMethod(LinkMovementMethod.getInstance());
            }
            this.f6530L = str;
            return this;
        }
        throw new NullPointerException("Argument 'url' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24156a(@FloatRange(from = 0.0d, fromInclusive = false) float f, BlurMaskFilter.Blur blur) {
        this.f6531M = f;
        this.f6532N = blur;
        return this;
    }

    /* renamed from: a */
    public SpanUtils m24149a(@NonNull Shader shader) {
        if (shader != null) {
            this.f6533O = shader;
            return this;
        }
        throw new NullPointerException("Argument 'shader' of type Shader (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24157a(@FloatRange(from = 0.0d, fromInclusive = false) float f, float f2, float f3, int i) {
        this.f6534P = f;
        this.f6535Q = f2;
        this.f6536R = f3;
        this.f6537S = i;
        return this;
    }

    /* renamed from: a */
    public SpanUtils m24138a(@NonNull Object... objArr) {
        if (objArr != null) {
            if (objArr.length > 0) {
                this.f6538T = objArr;
            }
            return this;
        }
        throw new NullPointerException("Argument 'spans' of type Object[] (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24140a(@NonNull CharSequence charSequence) {
        if (charSequence != null) {
            m24111k(0);
            this.f6552h = charSequence;
            return this;
        }
        throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: h */
    public SpanUtils m24118h() {
        m24111k(0);
        this.f6552h = f6518f;
        return this;
    }

    /* renamed from: b */
    public SpanUtils m24132b(@NonNull CharSequence charSequence) {
        if (charSequence != null) {
            m24111k(0);
            this.f6552h = ((Object) charSequence) + f6518f;
            return this;
        }
        throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24151a(@NonNull Bitmap bitmap) {
        if (bitmap != null) {
            return m24150a(bitmap, 0);
        }
        throw new NullPointerException("Argument 'bitmap' of type Bitmap (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24150a(@NonNull Bitmap bitmap, int i) {
        if (bitmap != null) {
            m24111k(1);
            this.f6539U = bitmap;
            this.f6543Y = i;
            return this;
        }
        throw new NullPointerException("Argument 'bitmap' of type Bitmap (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24147a(@NonNull Drawable drawable) {
        if (drawable != null) {
            return m24146a(drawable, 0);
        }
        throw new NullPointerException("Argument 'drawable' of type Drawable (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24146a(@NonNull Drawable drawable, int i) {
        if (drawable != null) {
            m24111k(1);
            this.f6540V = drawable;
            this.f6543Y = i;
            return this;
        }
        throw new NullPointerException("Argument 'drawable' of type Drawable (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24145a(@NonNull Uri uri) {
        if (uri != null) {
            return m24144a(uri, 0);
        }
        throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SpanUtils m24144a(@NonNull Uri uri, int i) {
        if (uri != null) {
            m24111k(1);
            this.f6541W = uri;
            this.f6543Y = i;
            return this;
        }
        throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: i */
    public SpanUtils m24115i(@DrawableRes int i) {
        return m24128c(i, 0);
    }

    /* renamed from: c */
    public SpanUtils m24128c(@DrawableRes int i, int i2) {
        m24111k(1);
        this.f6542X = i;
        this.f6543Y = i2;
        return this;
    }

    /* renamed from: j */
    public SpanUtils m24113j(@IntRange(from = 0) int i) {
        return m24125d(i, 0);
    }

    /* renamed from: d */
    public SpanUtils m24125d(@IntRange(from = 0) int i, @ColorInt int i2) {
        m24111k(2);
        this.f6544Z = i;
        this.f6545aa = i2;
        return this;
    }

    /* renamed from: k */
    private void m24111k(int i) {
        m24110l();
        this.f6547ac = i;
    }

    /* renamed from: i */
    public SpannableStringBuilder m24116i() {
        return this.f6546ab;
    }

    /* renamed from: j */
    public SpannableStringBuilder m24114j() {
        m24110l();
        TextView textView = this.f6551g;
        if (textView != null) {
            textView.setText(this.f6546ab);
        }
        return this.f6546ab;
    }

    /* renamed from: l */
    private void m24110l() {
        int i = this.f6547ac;
        if (i == 0) {
            m24109m();
        } else if (i == 1) {
            m24108n();
        } else if (i == 2) {
            m24107o();
        }
        m24112k();
    }

    /* renamed from: m */
    private void m24109m() {
        if (this.f6552h.length() != 0) {
            int length = this.f6546ab.length();
            if (length == 0 && this.f6556l != -1) {
                this.f6546ab.append((CharSequence) Character.toString((char) 2)).append((CharSequence) "\n").setSpan(new AbsoluteSizeSpan(0), 0, 2, 33);
                length = 2;
            }
            this.f6546ab.append(this.f6552h);
            int length2 = this.f6546ab.length();
            int i = this.f6528J;
            if (i != -1) {
                this.f6546ab.setSpan(new C0951k(i), length, length2, this.f6553i);
            }
            int i2 = this.f6554j;
            if (i2 != f6517e) {
                this.f6546ab.setSpan(new ForegroundColorSpan(i2), length, length2, this.f6553i);
            }
            int i3 = this.f6555k;
            if (i3 != f6517e) {
                this.f6546ab.setSpan(new BackgroundColorSpan(i3), length, length2, this.f6553i);
            }
            int i4 = this.f6561q;
            if (i4 != -1) {
                this.f6546ab.setSpan(new LeadingMarginSpan.Standard(i4, this.f6562r), length, length2, this.f6553i);
            }
            int i5 = this.f6558n;
            if (i5 != f6517e) {
                this.f6546ab.setSpan(new C0946f(i5, this.f6559o, this.f6560p), length, length2, this.f6553i);
            }
            int i6 = this.f6563s;
            if (i6 != f6517e) {
                this.f6546ab.setSpan(new C0942b(i6, this.f6564t, this.f6565u), length, length2, this.f6553i);
            }
            int i7 = this.f6566v;
            if (i7 != -1) {
                this.f6546ab.setSpan(new AbsoluteSizeSpan(i7, this.f6567w), length, length2, this.f6553i);
            }
            float f = this.f6568x;
            if (f != -1.0f) {
                this.f6546ab.setSpan(new RelativeSizeSpan(f), length, length2, this.f6553i);
            }
            float f2 = this.f6569y;
            if (f2 != -1.0f) {
                this.f6546ab.setSpan(new ScaleXSpan(f2), length, length2, this.f6553i);
            }
            int i8 = this.f6556l;
            if (i8 != -1) {
                this.f6546ab.setSpan(new C0945e(i8, this.f6557m), length, length2, this.f6553i);
            }
            if (this.f6570z) {
                this.f6546ab.setSpan(new StrikethroughSpan(), length, length2, this.f6553i);
            }
            if (this.f6519A) {
                this.f6546ab.setSpan(new UnderlineSpan(), length, length2, this.f6553i);
            }
            if (this.f6520B) {
                this.f6546ab.setSpan(new SuperscriptSpan(), length, length2, this.f6553i);
            }
            if (this.f6521C) {
                this.f6546ab.setSpan(new SubscriptSpan(), length, length2, this.f6553i);
            }
            if (this.f6522D) {
                this.f6546ab.setSpan(new StyleSpan(1), length, length2, this.f6553i);
            }
            if (this.f6523E) {
                this.f6546ab.setSpan(new StyleSpan(2), length, length2, this.f6553i);
            }
            if (this.f6524F) {
                this.f6546ab.setSpan(new StyleSpan(3), length, length2, this.f6553i);
            }
            String str = this.f6525G;
            if (str != null) {
                this.f6546ab.setSpan(new TypefaceSpan(str), length, length2, this.f6553i);
            }
            Typeface typeface = this.f6526H;
            if (typeface != null) {
                this.f6546ab.setSpan(new CustomTypefaceSpan(typeface), length, length2, this.f6553i);
            }
            Layout.Alignment alignment = this.f6527I;
            if (alignment != null) {
                this.f6546ab.setSpan(new AlignmentSpan.Standard(alignment), length, length2, this.f6553i);
            }
            ClickableSpan clickableSpan = this.f6529K;
            if (clickableSpan != null) {
                this.f6546ab.setSpan(clickableSpan, length, length2, this.f6553i);
            }
            String str2 = this.f6530L;
            if (str2 != null) {
                this.f6546ab.setSpan(new URLSpan(str2), length, length2, this.f6553i);
            }
            float f3 = this.f6531M;
            if (f3 != -1.0f) {
                this.f6546ab.setSpan(new MaskFilterSpan(new BlurMaskFilter(f3, this.f6532N)), length, length2, this.f6553i);
            }
            Shader shader = this.f6533O;
            if (shader != null) {
                this.f6546ab.setSpan(new C0948h(shader), length, length2, this.f6553i);
            }
            float f4 = this.f6534P;
            if (f4 != -1.0f) {
                this.f6546ab.setSpan(new C0949i(f4, this.f6535Q, this.f6536R, this.f6537S), length, length2, this.f6553i);
            }
            Object[] objArr = this.f6538T;
            if (objArr != null) {
                for (Object obj : objArr) {
                    this.f6546ab.setSpan(obj, length, length2, this.f6553i);
                }
            }
        }
    }

    /* renamed from: n */
    private void m24108n() {
        int length = this.f6546ab.length();
        if (length == 0) {
            this.f6546ab.append((CharSequence) Character.toString((char) 2));
            length = 1;
        }
        this.f6546ab.append((CharSequence) "<img>");
        int i = length + 5;
        Bitmap bitmap = this.f6539U;
        if (bitmap != null) {
            this.f6546ab.setSpan(new C0944d(bitmap, this.f6543Y), length, i, this.f6553i);
            return;
        }
        Drawable drawable = this.f6540V;
        if (drawable != null) {
            this.f6546ab.setSpan(new C0944d(drawable, this.f6543Y), length, i, this.f6553i);
            return;
        }
        Uri uri = this.f6541W;
        if (uri != null) {
            this.f6546ab.setSpan(new C0944d(uri, this.f6543Y), length, i, this.f6553i);
            return;
        }
        int i2 = this.f6542X;
        if (i2 != -1) {
            this.f6546ab.setSpan(new C0944d(i2, this.f6543Y), length, i, this.f6553i);
        }
    }

    /* renamed from: o */
    private void m24107o() {
        int length = this.f6546ab.length();
        this.f6546ab.append((CharSequence) "< >");
        this.f6546ab.setSpan(new C0950j(this.f6544Z, this.f6545aa), length, length + 3, this.f6553i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$k */
    /* loaded from: classes.dex */
    public static class C0951k extends ReplacementSpan {

        /* renamed from: a */
        static final int f6600a = 2;

        /* renamed from: b */
        static final int f6601b = 3;

        /* renamed from: c */
        final int f6602c;

        C0951k(int i) {
            this.f6602c = i;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            if (paint != null) {
                return (int) paint.measureText(charSequence.subSequence(i, i2).toString());
            }
            throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else if (paint != null) {
                CharSequence subSequence = charSequence.subSequence(i, i2);
                Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
                canvas.drawText(subSequence.toString(), f, i4 - (((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - ((i5 + i3) / 2)), paint);
            } else {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$e */
    /* loaded from: classes.dex */
    public static class C0945e implements LineHeightSpan {

        /* renamed from: a */
        static final int f6585a = 2;

        /* renamed from: b */
        static final int f6586b = 3;

        /* renamed from: d */
        static Paint.FontMetricsInt f6587d;

        /* renamed from: c */
        final int f6588c;

        /* renamed from: e */
        private final int f6589e;

        C0945e(int i, int i2) {
            this.f6589e = i;
            this.f6588c = i2;
        }

        @Override // android.text.style.LineHeightSpan
        public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
            LogUtils.m23720e(fontMetricsInt, f6587d);
            Paint.FontMetricsInt fontMetricsInt2 = f6587d;
            if (fontMetricsInt2 == null) {
                f6587d = new Paint.FontMetricsInt();
                f6587d.top = fontMetricsInt.top;
                f6587d.ascent = fontMetricsInt.ascent;
                f6587d.descent = fontMetricsInt.descent;
                f6587d.bottom = fontMetricsInt.bottom;
                f6587d.leading = fontMetricsInt.leading;
            } else {
                fontMetricsInt.top = fontMetricsInt2.top;
                fontMetricsInt.ascent = f6587d.ascent;
                fontMetricsInt.descent = f6587d.descent;
                fontMetricsInt.bottom = f6587d.bottom;
                fontMetricsInt.leading = f6587d.leading;
            }
            int i5 = this.f6589e - (((fontMetricsInt.descent + i4) - fontMetricsInt.ascent) - i3);
            if (i5 > 0) {
                int i6 = this.f6588c;
                if (i6 == 3) {
                    fontMetricsInt.descent += i5;
                } else if (i6 == 2) {
                    int i7 = i5 / 2;
                    fontMetricsInt.descent += i7;
                    fontMetricsInt.ascent -= i7;
                } else {
                    fontMetricsInt.ascent -= i5;
                }
            }
            int i8 = this.f6589e - (((i4 + fontMetricsInt.bottom) - fontMetricsInt.top) - i3);
            if (i8 > 0) {
                int i9 = this.f6588c;
                if (i9 == 3) {
                    fontMetricsInt.bottom += i8;
                } else if (i9 == 2) {
                    int i10 = i8 / 2;
                    fontMetricsInt.bottom += i10;
                    fontMetricsInt.top -= i10;
                } else {
                    fontMetricsInt.top -= i8;
                }
            }
            if (i2 == ((Spanned) charSequence).getSpanEnd(this)) {
                f6587d = null;
            }
            LogUtils.m23720e(fontMetricsInt, f6587d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$j */
    /* loaded from: classes.dex */
    public static class C0950j extends ReplacementSpan {

        /* renamed from: a */
        private final int f6598a;

        /* renamed from: b */
        private final Paint f6599b;

        private C0950j(int i) {
            this(i, 0);
        }

        private C0950j(int i, int i2) {
            this.f6599b = new Paint();
            this.f6598a = i;
            this.f6599b.setColor(i2);
            this.f6599b.setStyle(Paint.Style.FILL);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            if (paint != null) {
                return this.f6598a;
            }
            throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else if (paint != null) {
                canvas.drawRect(f, i3, f + this.f6598a, i5, this.f6599b);
            } else {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$f */
    /* loaded from: classes.dex */
    public static class C0946f implements LeadingMarginSpan {

        /* renamed from: a */
        private final int f6590a;

        /* renamed from: b */
        private final int f6591b;

        /* renamed from: c */
        private final int f6592c;

        private C0946f(int i, int i2, int i3) {
            this.f6590a = i;
            this.f6591b = i2;
            this.f6592c = i3;
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(boolean z) {
            return this.f6591b + this.f6592c;
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.f6590a);
            canvas.drawRect(i, i3, i + (this.f6591b * i2), i5, paint);
            paint.setStyle(style);
            paint.setColor(color);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$b */
    /* loaded from: classes.dex */
    public static class C0942b implements LeadingMarginSpan {

        /* renamed from: a */
        private final int f6572a;

        /* renamed from: b */
        private final int f6573b;

        /* renamed from: c */
        private final int f6574c;

        /* renamed from: d */
        private Path f6575d;

        private C0942b(int i, int i2, int i3) {
            this.f6575d = null;
            this.f6572a = i;
            this.f6573b = i2;
            this.f6574c = i3;
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(boolean z) {
            return (this.f6573b * 2) + this.f6574c;
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            if (((Spanned) charSequence).getSpanStart(this) == i6) {
                Paint.Style style = paint.getStyle();
                int color = paint.getColor();
                paint.setColor(this.f6572a);
                paint.setStyle(Paint.Style.FILL);
                if (canvas.isHardwareAccelerated()) {
                    if (this.f6575d == null) {
                        this.f6575d = new Path();
                        this.f6575d.addCircle(0.0f, 0.0f, this.f6573b, Path.Direction.CW);
                    }
                    canvas.save();
                    canvas.translate(i + (i2 * this.f6573b), (i3 + i5) / 2.0f);
                    canvas.drawPath(this.f6575d, paint);
                    canvas.restore();
                } else {
                    int i8 = this.f6573b;
                    canvas.drawCircle(i + (i2 * i8), (i3 + i5) / 2.0f, i8, paint);
                }
                paint.setColor(color);
                paint.setStyle(style);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"ParcelCreator"})
    /* loaded from: classes.dex */
    public static class CustomTypefaceSpan extends TypefaceSpan {

        /* renamed from: a */
        private final Typeface f6571a;

        private CustomTypefaceSpan(Typeface typeface) {
            super("");
            this.f6571a = typeface;
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            m24106a(textPaint, this.f6571a);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint textPaint) {
            m24106a(textPaint, this.f6571a);
        }

        /* renamed from: a */
        private void m24106a(Paint paint, Typeface typeface) {
            Typeface typeface2 = paint.getTypeface();
            int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (~typeface.getStyle());
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.getShader();
            paint.setTypeface(typeface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$d */
    /* loaded from: classes.dex */
    public static class C0944d extends AbstractC0943c {

        /* renamed from: f */
        private Drawable f6582f;

        /* renamed from: g */
        private Uri f6583g;

        /* renamed from: h */
        private int f6584h;

        private C0944d(Bitmap bitmap, int i) {
            super(i);
            this.f6582f = new BitmapDrawable(Utils.m24103a().getResources(), bitmap);
            Drawable drawable = this.f6582f;
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.f6582f.getIntrinsicHeight());
        }

        private C0944d(Drawable drawable, int i) {
            super(i);
            this.f6582f = drawable;
            Drawable drawable2 = this.f6582f;
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.f6582f.getIntrinsicHeight());
        }

        private C0944d(Uri uri, int i) {
            super(i);
            this.f6583g = uri;
        }

        private C0944d(@DrawableRes int i, int i2) {
            super(i2);
            this.f6584h = i;
        }

        @Override // com.blankj.utilcode.util.SpanUtils.AbstractC0943c
        /* renamed from: a */
        public Drawable mo24104a() {
            Drawable drawable;
            Exception e;
            InputStream openInputStream;
            BitmapDrawable bitmapDrawable;
            Drawable drawable2 = this.f6582f;
            if (drawable2 != null) {
                return drawable2;
            }
            BitmapDrawable bitmapDrawable2 = null;
            if (this.f6583g != null) {
                try {
                    openInputStream = Utils.m24103a().getContentResolver().openInputStream(this.f6583g);
                    bitmapDrawable = new BitmapDrawable(Utils.m24103a().getResources(), BitmapFactory.decodeStream(openInputStream));
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    return bitmapDrawable;
                } catch (Exception e3) {
                    e = e3;
                    bitmapDrawable2 = bitmapDrawable;
                    Log.e("sms", "Failed to loaded content " + this.f6583g, e);
                    return bitmapDrawable2;
                }
            } else {
                try {
                    drawable = ContextCompat.getDrawable(Utils.m24103a(), this.f6584h);
                    try {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    } catch (Exception unused) {
                        Log.e("sms", "Unable to find resource: " + this.f6584h);
                        return drawable;
                    }
                } catch (Exception unused2) {
                    drawable = null;
                }
            }
        }
    }

    /* renamed from: com.blankj.utilcode.util.SpanUtils$c */
    /* loaded from: classes.dex */
    static abstract class AbstractC0943c extends ReplacementSpan {

        /* renamed from: a */
        static final int f6576a = 0;

        /* renamed from: b */
        static final int f6577b = 1;

        /* renamed from: c */
        static final int f6578c = 2;

        /* renamed from: d */
        static final int f6579d = 3;

        /* renamed from: e */
        final int f6580e;

        /* renamed from: f */
        private WeakReference<Drawable> f6581f;

        /* renamed from: a */
        public abstract Drawable mo24104a();

        private AbstractC0943c() {
            this.f6580e = 0;
        }

        private AbstractC0943c(int i) {
            this.f6580e = i;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            int i3;
            if (paint != null) {
                Rect bounds = m24105b().getBounds();
                if (fontMetricsInt != null && (i3 = fontMetricsInt.bottom - fontMetricsInt.top) < bounds.height()) {
                    int i4 = this.f6580e;
                    if (i4 == 3) {
                        fontMetricsInt.top = fontMetricsInt.top;
                        fontMetricsInt.bottom = bounds.height() + fontMetricsInt.top;
                    } else if (i4 == 2) {
                        int i5 = i3 / 4;
                        fontMetricsInt.top = ((-bounds.height()) / 2) - i5;
                        fontMetricsInt.bottom = (bounds.height() / 2) - i5;
                    } else {
                        fontMetricsInt.top = (-bounds.height()) + fontMetricsInt.bottom;
                        fontMetricsInt.bottom = fontMetricsInt.bottom;
                    }
                    fontMetricsInt.ascent = fontMetricsInt.top;
                    fontMetricsInt.descent = fontMetricsInt.bottom;
                }
                return bounds.right;
            }
            throw new NullPointerException("Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            float f2;
            if (canvas == null) {
                throw new NullPointerException("Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            } else if (paint != null) {
                Drawable b = m24105b();
                Rect bounds = b.getBounds();
                canvas.save();
                if (bounds.height() < i5 - i3) {
                    int i6 = this.f6580e;
                    if (i6 == 3) {
                        f2 = i3;
                    } else if (i6 == 2) {
                        f2 = ((i5 + i3) - bounds.height()) / 2;
                    } else if (i6 == 1) {
                        f2 = i4 - bounds.height();
                    } else {
                        f2 = i5 - bounds.height();
                    }
                    canvas.translate(f, f2);
                } else {
                    canvas.translate(f, i3);
                }
                b.draw(canvas);
                canvas.restore();
            } else {
                throw new NullPointerException("Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        /* renamed from: b */
        private Drawable m24105b() {
            WeakReference<Drawable> weakReference = this.f6581f;
            Drawable drawable = weakReference != null ? weakReference.get() : null;
            if (drawable != null) {
                return drawable;
            }
            Drawable a = mo24104a();
            this.f6581f = new WeakReference<>(a);
            return a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$h */
    /* loaded from: classes.dex */
    public static class C0948h extends CharacterStyle implements UpdateAppearance {

        /* renamed from: a */
        private Shader f6593a;

        private C0948h(Shader shader) {
            this.f6593a = shader;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShader(this.f6593a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$i */
    /* loaded from: classes.dex */
    public static class C0949i extends CharacterStyle implements UpdateAppearance {

        /* renamed from: a */
        private float f6594a;

        /* renamed from: b */
        private float f6595b;

        /* renamed from: c */
        private float f6596c;

        /* renamed from: d */
        private int f6597d;

        private C0949i(float f, float f2, float f3, int i) {
            this.f6594a = f;
            this.f6595b = f2;
            this.f6596c = f3;
            this.f6597d = i;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShadowLayer(this.f6594a, this.f6595b, this.f6596c, this.f6597d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.blankj.utilcode.util.SpanUtils$g */
    /* loaded from: classes.dex */
    public static class C0947g extends SpannableStringBuilder implements Serializable {
        private static final long serialVersionUID = 4909567650765875771L;

        private C0947g() {
        }
    }

    /* renamed from: a */
    public static SpanUtils m24141a(TextView textView) {
        return new SpanUtils(textView);
    }
}
