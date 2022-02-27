package com.cyjh.mobileanjian.ipc.uip;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.cyjh.mqsdk.C1375R;

/* renamed from: com.cyjh.mobileanjian.ipc.uip.a */
/* loaded from: classes.dex */
public final class CircleIndicator extends LinearLayout {

    /* renamed from: a */
    private static final int f8637a = 8;

    /* renamed from: b */
    private ViewPager f8638b;

    /* renamed from: j */
    private Animator f8646j;

    /* renamed from: k */
    private Animator f8647k;

    /* renamed from: l */
    private Animator f8648l;

    /* renamed from: m */
    private Animator f8649m;

    /* renamed from: c */
    private int f8639c = -1;

    /* renamed from: d */
    private int f8640d = -1;

    /* renamed from: e */
    private int f8641e = -1;

    /* renamed from: f */
    private int f8642f = C1375R.animator.no_animator;

    /* renamed from: g */
    private int f8643g = 0;

    /* renamed from: h */
    private int f8644h = C1375R.C1376drawable.ci_blue_point;

    /* renamed from: i */
    private int f8645i = C1375R.C1376drawable.ci_gray_point;

    /* renamed from: n */
    private int f8650n = -1;

    /* renamed from: o */
    private final ViewPager.OnPageChangeListener f8651o = new ViewPager.OnPageChangeListener() { // from class: com.cyjh.mobileanjian.ipc.uip.a.1
        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public final void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public final void onPageScrolled(int i, float f, int i2) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public final void onPageSelected(int i) {
            CircleIndicator aVar;
            View childAt;
            if (CircleIndicator.this.f8638b.getAdapter() != null && CircleIndicator.this.f8638b.getAdapter().getCount() > 0) {
                if (CircleIndicator.this.f8647k.isRunning()) {
                    CircleIndicator.this.f8647k.end();
                    CircleIndicator.this.f8647k.cancel();
                }
                if (CircleIndicator.this.f8646j.isRunning()) {
                    CircleIndicator.this.f8646j.end();
                    CircleIndicator.this.f8646j.cancel();
                }
                if (CircleIndicator.this.f8650n >= 0 && (childAt = (aVar = CircleIndicator.this).getChildAt(aVar.f8650n)) != null) {
                    childAt.setBackgroundResource(CircleIndicator.this.f8645i);
                    CircleIndicator.this.f8647k.setTarget(childAt);
                    CircleIndicator.this.f8647k.start();
                }
                View childAt2 = CircleIndicator.this.getChildAt(i);
                if (childAt2 != null) {
                    childAt2.setBackgroundResource(CircleIndicator.this.f8644h);
                    CircleIndicator.this.f8646j.setTarget(childAt2);
                    CircleIndicator.this.f8646j.start();
                }
                CircleIndicator.this.f8650n = i;
            }
        }
    };

    /* renamed from: p */
    private DataSetObserver f8652p = new DataSetObserver() { // from class: com.cyjh.mobileanjian.ipc.uip.a.2
        @Override // android.database.DataSetObserver
        public final void onChanged() {
            int count;
            super.onChanged();
            if (CircleIndicator.this.f8638b != null && (count = CircleIndicator.this.f8638b.getAdapter().getCount()) != CircleIndicator.this.getChildCount()) {
                if (CircleIndicator.this.f8650n < count) {
                    CircleIndicator aVar = CircleIndicator.this;
                    aVar.f8650n = aVar.f8638b.getCurrentItem();
                } else {
                    CircleIndicator.this.f8650n = -1;
                }
                CircleIndicator.this.m20704a();
            }
        }
    };

    public CircleIndicator(Context context) {
        super(context);
        m20698a(context, (AttributeSet) null);
    }

    private CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m20698a(context, attributeSet);
    }

    private CircleIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m20698a(context, attributeSet);
    }

    @TargetApi(21)
    private CircleIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m20698a(context, attributeSet);
    }

    /* renamed from: b */
    private void m20694b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1375R.styleable.CircleIndicator);
            this.f8640d = obtainStyledAttributes.getDimensionPixelSize(C1375R.styleable.CircleIndicator_ci_width, -1);
            this.f8641e = obtainStyledAttributes.getDimensionPixelSize(C1375R.styleable.CircleIndicator_ci_height, -1);
            this.f8639c = obtainStyledAttributes.getDimensionPixelSize(C1375R.styleable.CircleIndicator_ci_margin, -1);
            this.f8642f = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_animator, C1375R.animator.scale_with_alpha);
            int i = 0;
            this.f8643g = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_animator_reverse, 0);
            this.f8644h = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_drawable, C1375R.C1376drawable.white_radius);
            this.f8645i = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_drawable_unselected, this.f8644h);
            if (obtainStyledAttributes.getInt(C1375R.styleable.CircleIndicator_ci_orientation, -1) == 1) {
                i = 1;
            }
            setOrientation(i);
            int i2 = obtainStyledAttributes.getInt(C1375R.styleable.CircleIndicator_ci_gravity, -1);
            if (i2 < 0) {
                i2 = 17;
            }
            setGravity(i2);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: a */
    private void m20702a(int i, int i2, int i3) {
        int i4 = C1375R.animator.scale_with_alpha;
        int i5 = C1375R.C1376drawable.white_radius;
        int i6 = C1375R.C1376drawable.white_radius;
        this.f8640d = i;
        this.f8641e = i2;
        this.f8639c = i3;
        this.f8642f = i4;
        this.f8643g = 0;
        this.f8644h = i5;
        this.f8645i = i6;
        m20699a(getContext());
    }

    /* renamed from: a */
    private void m20701a(int i, int i2, int i3, @AnimatorRes int i4, @DrawableRes int i5, @DrawableRes int i6) {
        this.f8640d = i;
        this.f8641e = i2;
        this.f8639c = i3;
        this.f8642f = i4;
        this.f8643g = 0;
        this.f8644h = i5;
        this.f8645i = i6;
        m20699a(getContext());
    }

    /* renamed from: a */
    private void m20699a(Context context) {
        int i = this.f8640d;
        if (i < 0) {
            i = m20703a(8.0f);
        }
        this.f8640d = i;
        int i2 = this.f8641e;
        if (i2 < 0) {
            i2 = m20703a(8.0f);
        }
        this.f8641e = i2;
        int i3 = this.f8639c;
        if (i3 < 0) {
            i3 = m20703a(8.0f);
        }
        this.f8639c = i3;
        int i4 = this.f8642f;
        if (i4 == 0) {
            i4 = C1375R.animator.scale_with_alpha;
        }
        this.f8642f = i4;
        this.f8646j = m20695b(context);
        this.f8648l = m20695b(context);
        this.f8648l.setDuration(0L);
        this.f8647k = m20692c(context);
        this.f8649m = m20692c(context);
        this.f8649m.setDuration(0L);
        int i5 = this.f8644h;
        if (i5 == 0) {
            i5 = C1375R.C1376drawable.white_radius;
        }
        this.f8644h = i5;
        int i6 = this.f8645i;
        if (i6 == 0) {
            i6 = this.f8644h;
        }
        this.f8645i = i6;
    }

    /* renamed from: b */
    private Animator m20695b(Context context) {
        return AnimatorInflater.loadAnimator(context, this.f8642f);
    }

    /* renamed from: c */
    private Animator m20692c(Context context) {
        int i = this.f8643g;
        if (i != 0) {
            return AnimatorInflater.loadAnimator(context, i);
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, this.f8642f);
        loadAnimator.setInterpolator(new animationInterpolatorC1331a(this, (byte) 0));
        return loadAnimator;
    }

    public final void setViewPager(ViewPager viewPager) {
        this.f8638b = viewPager;
        ViewPager viewPager2 = this.f8638b;
        if (viewPager2 != null && viewPager2.getAdapter() != null) {
            this.f8650n = -1;
            m20704a();
            this.f8638b.removeOnPageChangeListener(this.f8651o);
            this.f8638b.addOnPageChangeListener(this.f8651o);
            this.f8651o.onPageSelected(this.f8638b.getCurrentItem());
        }
    }

    public final DataSetObserver getDataSetObserver() {
        return this.f8652p;
    }

    @Deprecated
    public final void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        ViewPager viewPager = this.f8638b;
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(onPageChangeListener);
            this.f8638b.addOnPageChangeListener(onPageChangeListener);
            return;
        }
        throw new NullPointerException("can not find Viewpager , setViewPager first");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m20704a() {
        removeAllViews();
        int count = this.f8638b.getAdapter().getCount();
        if (count > 0) {
            int currentItem = this.f8638b.getCurrentItem();
            int orientation = getOrientation();
            for (int i = 0; i < count; i++) {
                if (currentItem == i) {
                    m20700a(orientation, this.f8644h, this.f8648l);
                } else {
                    m20700a(orientation, this.f8645i, this.f8649m);
                }
            }
        }
    }

    /* renamed from: a */
    private void m20700a(int i, @DrawableRes int i2, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View view = new View(getContext());
        view.setBackgroundResource(i2);
        addView(view, this.f8640d, this.f8641e);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (i == 0) {
            int i3 = this.f8639c;
            layoutParams.leftMargin = i3;
            layoutParams.rightMargin = i3;
        } else {
            int i4 = this.f8639c;
            layoutParams.topMargin = i4;
            layoutParams.bottomMargin = i4;
        }
        view.setLayoutParams(layoutParams);
        animator.setTarget(view);
        animator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CircleIndicator.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.uip.a$a  reason: invalid class name */
    /* loaded from: classes.dex */
    public class animationInterpolatorC1331a implements Interpolator {
        private animationInterpolatorC1331a() {
        }

        /* synthetic */ animationInterpolatorC1331a(CircleIndicator aVar, byte b) {
            this();
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            return Math.abs(1.0f - f);
        }
    }

    /* renamed from: a */
    public final int m20703a(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: a */
    private void m20698a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1375R.styleable.CircleIndicator);
            this.f8640d = obtainStyledAttributes.getDimensionPixelSize(C1375R.styleable.CircleIndicator_ci_width, -1);
            this.f8641e = obtainStyledAttributes.getDimensionPixelSize(C1375R.styleable.CircleIndicator_ci_height, -1);
            this.f8639c = obtainStyledAttributes.getDimensionPixelSize(C1375R.styleable.CircleIndicator_ci_margin, -1);
            this.f8642f = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_animator, C1375R.animator.scale_with_alpha);
            int i = 0;
            this.f8643g = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_animator_reverse, 0);
            this.f8644h = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_drawable, C1375R.C1376drawable.white_radius);
            this.f8645i = obtainStyledAttributes.getResourceId(C1375R.styleable.CircleIndicator_ci_drawable_unselected, this.f8644h);
            if (obtainStyledAttributes.getInt(C1375R.styleable.CircleIndicator_ci_orientation, -1) == 1) {
                i = 1;
            }
            setOrientation(i);
            int i2 = obtainStyledAttributes.getInt(C1375R.styleable.CircleIndicator_ci_gravity, -1);
            if (i2 < 0) {
                i2 = 17;
            }
            setGravity(i2);
            obtainStyledAttributes.recycle();
        }
        m20699a(context);
    }
}
