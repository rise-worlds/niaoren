package com.blankj.utilcode.util;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* renamed from: com.blankj.utilcode.util.ax */
/* loaded from: classes.dex */
public final class SnackbarUtils {

    /* renamed from: a */
    public static final int f6753a = -2;

    /* renamed from: b */
    public static final int f6754b = -1;

    /* renamed from: c */
    public static final int f6755c = 0;

    /* renamed from: d */
    private static final int f6756d = -16777217;

    /* renamed from: e */
    private static final int f6757e = -13912576;

    /* renamed from: f */
    private static final int f6758f = -16128;

    /* renamed from: g */
    private static final int f6759g = -65536;

    /* renamed from: h */
    private static final int f6760h = -1;

    /* renamed from: i */
    private static WeakReference<Snackbar> f6761i;

    /* renamed from: j */
    private View f6762j;

    /* renamed from: k */
    private CharSequence f6763k;

    /* renamed from: l */
    private int f6764l;

    /* renamed from: m */
    private int f6765m;

    /* renamed from: n */
    private int f6766n;

    /* renamed from: o */
    private int f6767o;

    /* renamed from: p */
    private CharSequence f6768p;

    /* renamed from: q */
    private int f6769q;

    /* renamed from: r */
    private View.OnClickListener f6770r;

    /* renamed from: s */
    private int f6771s;

    /* compiled from: SnackbarUtils.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.blankj.utilcode.util.ax$a */
    /* loaded from: classes.dex */
    public @interface AbstractC0988a {
    }

    private SnackbarUtils(View view) {
        m23235g();
        this.f6762j = view;
    }

    /* renamed from: g */
    private void m23235g() {
        this.f6763k = "";
        this.f6764l = f6756d;
        this.f6765m = f6756d;
        this.f6766n = -1;
        this.f6767o = -1;
        this.f6768p = "";
        this.f6769q = f6756d;
        this.f6771s = 0;
    }

    /* renamed from: a */
    public static SnackbarUtils m23249a(@NonNull View view) {
        if (view != null) {
            return new SnackbarUtils(view);
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SnackbarUtils m23247a(@NonNull CharSequence charSequence) {
        if (charSequence != null) {
            this.f6763k = charSequence;
            return this;
        }
        throw new NullPointerException("Argument 'msg' of type CharSequence (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public SnackbarUtils m23251a(@ColorInt int i) {
        this.f6764l = i;
        return this;
    }

    /* renamed from: b */
    public SnackbarUtils m23243b(@ColorInt int i) {
        this.f6765m = i;
        return this;
    }

    /* renamed from: c */
    public SnackbarUtils m23241c(@DrawableRes int i) {
        this.f6766n = i;
        return this;
    }

    /* renamed from: d */
    public SnackbarUtils m23239d(int i) {
        this.f6767o = i;
        return this;
    }

    /* renamed from: a */
    public SnackbarUtils m23245a(@NonNull CharSequence charSequence, @NonNull View.OnClickListener onClickListener) {
        if (charSequence == null) {
            throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (onClickListener != null) {
            return m23246a(charSequence, f6756d, onClickListener);
        } else {
            throw new NullPointerException("Argument 'listener' of type View.OnClickListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public SnackbarUtils m23246a(@NonNull CharSequence charSequence, @ColorInt int i, @NonNull View.OnClickListener onClickListener) {
        if (charSequence == null) {
            throw new NullPointerException("Argument 'text' of type CharSequence (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (onClickListener != null) {
            this.f6768p = charSequence;
            this.f6769q = i;
            this.f6770r = onClickListener;
            return this;
        } else {
            throw new NullPointerException("Argument 'listener' of type View.OnClickListener (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: e */
    public SnackbarUtils m23237e(@IntRange(from = 1) int i) {
        this.f6771s = i;
        return this;
    }

    /* renamed from: a */
    public Snackbar m23252a() {
        View view = this.f6762j;
        if (view == null) {
            return null;
        }
        if (this.f6764l != f6756d) {
            SpannableString spannableString = new SpannableString(this.f6763k);
            spannableString.setSpan(new ForegroundColorSpan(this.f6764l), 0, spannableString.length(), 33);
            f6761i = new WeakReference<>(Snackbar.make(view, spannableString, this.f6767o));
        } else {
            f6761i = new WeakReference<>(Snackbar.make(view, this.f6763k, this.f6767o));
        }
        Snackbar snackbar = f6761i.get();
        View view2 = snackbar.getView();
        int i = this.f6766n;
        if (i != -1) {
            view2.setBackgroundResource(i);
        } else {
            int i2 = this.f6765m;
            if (i2 != f6756d) {
                view2.setBackgroundColor(i2);
            }
        }
        if (this.f6771s != 0) {
            ((ViewGroup.MarginLayoutParams) view2.getLayoutParams()).bottomMargin = this.f6771s;
        }
        if (this.f6768p.length() > 0 && this.f6770r != null) {
            int i3 = this.f6769q;
            if (i3 != f6756d) {
                snackbar.setActionTextColor(i3);
            }
            snackbar.setAction(this.f6768p, this.f6770r);
        }
        snackbar.show();
        return snackbar;
    }

    /* renamed from: b */
    public void m23244b() {
        this.f6765m = f6757e;
        this.f6764l = -1;
        this.f6769q = -1;
        m23252a();
    }

    /* renamed from: c */
    public void m23242c() {
        this.f6765m = f6758f;
        this.f6764l = -1;
        this.f6769q = -1;
        m23252a();
    }

    /* renamed from: d */
    public void m23240d() {
        this.f6765m = -65536;
        this.f6764l = -1;
        this.f6769q = -1;
        m23252a();
    }

    /* renamed from: e */
    public static void m23238e() {
        WeakReference<Snackbar> weakReference = f6761i;
        if (weakReference != null && weakReference.get() != null) {
            f6761i.get().dismiss();
            f6761i = null;
        }
    }

    /* renamed from: f */
    public static View m23236f() {
        Snackbar snackbar = f6761i.get();
        if (snackbar == null) {
            return null;
        }
        return snackbar.getView();
    }

    /* renamed from: a */
    public static void m23250a(@LayoutRes int i, @NonNull ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            View f = m23236f();
            if (f != null) {
                f.setPadding(0, 0, 0, 0);
                ((Snackbar.SnackbarLayout) f).addView(LayoutInflater.from(f.getContext()).inflate(i, (ViewGroup) null), -1, layoutParams);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'params' of type ViewGroup.LayoutParams (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m23248a(@NonNull View view, @NonNull ViewGroup.LayoutParams layoutParams) {
        if (view == null) {
            throw new NullPointerException("Argument 'child' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (layoutParams != null) {
            View f = m23236f();
            if (f != null) {
                f.setPadding(0, 0, 0, 0);
                ((Snackbar.SnackbarLayout) f).addView(view, layoutParams);
            }
        } else {
            throw new NullPointerException("Argument 'params' of type ViewGroup.LayoutParams (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }
}
