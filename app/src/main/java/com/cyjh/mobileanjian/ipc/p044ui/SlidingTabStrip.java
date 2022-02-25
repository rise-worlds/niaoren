package com.cyjh.mobileanjian.ipc.p044ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import com.cyjh.mobileanjian.ipc.p044ui.SlidingTabLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.cyjh.mobileanjian.ipc.ui.d */
/* loaded from: classes.dex */
public final class SlidingTabStrip extends LinearLayout {

    /* renamed from: c */
    private static final int f8472c = 0;

    /* renamed from: d */
    private static final byte f8473d = 38;

    /* renamed from: e */
    private static final int f8474e = 3;

    /* renamed from: f */
    private static final int f8475f = -13388315;

    /* renamed from: a */
    SlidingTabLayout.AbstractC1296c f8476a;

    /* renamed from: b */
    final C1304a f8477b;

    /* renamed from: g */
    private final int f8478g;

    /* renamed from: h */
    private final Paint f8479h;

    /* renamed from: i */
    private final int f8480i;

    /* renamed from: j */
    private final Paint f8481j;

    /* renamed from: k */
    private int f8482k;

    /* renamed from: l */
    private float f8483l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SlidingTabStrip(Context context) {
        this(context, (byte) 0);
    }

    private SlidingTabStrip(Context context, byte b) {
        super(context, null);
        setWillNotDraw(false);
        float f = getResources().getDisplayMetrics().density;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842800, typedValue, true);
        int i = typedValue.data;
        int argb = Color.argb(38, Color.red(i), Color.green(i), Color.blue(i));
        this.f8477b = new C1304a((byte) 0);
        this.f8477b.f8484a = new int[]{f8475f};
        this.f8478g = (int) (0.0f * f);
        this.f8479h = new Paint();
        this.f8479h.setColor(argb);
        this.f8480i = (int) (f * 3.0f);
        this.f8481j = new Paint();
    }

    /* renamed from: a */
    private void m20891a(SlidingTabLayout.AbstractC1296c cVar) {
        this.f8476a = cVar;
        invalidate();
    }

    /* renamed from: a */
    private void m20890a(int... iArr) {
        this.f8476a = null;
        this.f8477b.f8484a = iArr;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m20893a(int i, float f) {
        this.f8482k = i;
        this.f8483l = f;
        invalidate();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onDraw(Canvas canvas) {
        int height = getHeight();
        int childCount = getChildCount();
        SlidingTabLayout.AbstractC1296c cVar = this.f8476a;
        if (cVar == null) {
            cVar = this.f8477b;
        }
        if (childCount > 0) {
            View childAt = getChildAt(this.f8482k);
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int a = cVar.mo20889a(this.f8482k);
            if (this.f8483l > 0.0f && this.f8482k < getChildCount() - 1) {
                int a2 = cVar.mo20889a(this.f8482k + 1);
                if (a != a2) {
                    float f = this.f8483l;
                    float f2 = 1.0f - f;
                    a = Color.rgb((int) ((Color.red(a2) * f) + (Color.red(a) * f2)), (int) ((Color.green(a2) * f) + (Color.green(a) * f2)), (int) ((Color.blue(a2) * f) + (Color.blue(a) * f2)));
                }
                View childAt2 = getChildAt(this.f8482k + 1);
                float left2 = this.f8483l * childAt2.getLeft();
                float f3 = this.f8483l;
                left = (int) (left2 + ((1.0f - f3) * left));
                right = (int) ((f3 * childAt2.getRight()) + ((1.0f - this.f8483l) * right));
            }
            this.f8481j.setColor(a);
            canvas.drawRect(left, height - this.f8480i, right, height, this.f8481j);
        }
        canvas.drawRect(0.0f, height - this.f8478g, getWidth(), height, this.f8479h);
    }

    /* renamed from: a */
    private static int m20894a(int i) {
        return Color.argb(38, Color.red(i), Color.green(i), Color.blue(i));
    }

    /* renamed from: a */
    private static int m20892a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.rgb((int) ((Color.red(i) * f) + (Color.red(i2) * f2)), (int) ((Color.green(i) * f) + (Color.green(i2) * f2)), (int) ((Color.blue(i) * f) + (Color.blue(i2) * f2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SlidingTabStrip.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.d$a */
    /* loaded from: classes.dex */
    public static class C1304a implements SlidingTabLayout.AbstractC1296c {

        /* renamed from: a */
        int[] f8484a;

        private C1304a() {
        }

        /* synthetic */ C1304a(byte b) {
            this();
        }

        @Override // com.cyjh.mobileanjian.ipc.p044ui.SlidingTabLayout.AbstractC1296c
        /* renamed from: a */
        public final int mo20889a(int i) {
            int[] iArr = this.f8484a;
            return iArr[i % iArr.length];
        }

        /* renamed from: a */
        private void m20888a(int... iArr) {
            this.f8484a = iArr;
        }
    }
}
