package com.cyjh.mobileanjian.ipc.uip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import com.cyjh.mqsdk.C1375R;
import java.util.ArrayList;
import java.util.List;
import p110z1.MemoryConstants;

@TargetApi(14)
/* renamed from: com.cyjh.mobileanjian.ipc.uip.b */
/* loaded from: classes.dex */
public final class FlowLayout extends ViewGroup {

    /* renamed from: a */
    private int f8656a;

    /* renamed from: b */
    private final List<List<View>> f8657b;

    /* renamed from: c */
    private final List<Integer> f8658c;

    /* renamed from: d */
    private final List<Integer> f8659d;

    public FlowLayout(Context context) {
        this(context, (byte) 0);
    }

    private FlowLayout(Context context, byte b) {
        this(context, (char) 0);
    }

    private FlowLayout(Context context, char c) {
        super(context, null, 0);
        this.f8656a = (m20683b() ? GravityCompat.START : 3) | 48;
        this.f8657b = new ArrayList();
        this.f8658c = new ArrayList();
        this.f8659d = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, C1375R.styleable.FlowLayout, 0, 0);
        try {
            int i = obtainStyledAttributes.getInt(C1375R.styleable.FlowLayout_android_gravity, -1);
            if (i > 0) {
                setGravity(i);
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        super.onMeasure(i, i2);
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childCount = getChildCount();
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i10 < childCount) {
            View childAt = getChildAt(i10);
            boolean z = i10 == childCount + (-1);
            if (childAt.getVisibility() != 8) {
                size2 = size2;
                measureChildWithMargins(childAt, i, i9, i2, paddingTop);
                C1332a aVar = (C1332a) childAt.getLayoutParams();
                if (aVar.width == -1) {
                    i3 = size - (aVar.leftMargin + aVar.rightMargin);
                    i4 = MemoryConstants.f21646d;
                } else if (aVar.width >= 0) {
                    i3 = aVar.width;
                    i4 = MemoryConstants.f21646d;
                } else {
                    i3 = size;
                    i4 = Integer.MIN_VALUE;
                }
                if (aVar.height >= 0) {
                    i6 = aVar.height;
                    i5 = MemoryConstants.f21646d;
                } else if (mode2 == 0) {
                    i6 = 0;
                    i5 = 0;
                } else {
                    i6 = size2;
                    i5 = Integer.MIN_VALUE;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, i4), View.MeasureSpec.makeMeasureSpec(i6, i5));
                int measuredWidth = childAt.getMeasuredWidth() + aVar.leftMargin + aVar.rightMargin;
                i9 += measuredWidth;
                if (i9 > size) {
                    i11 = Math.max(i11, i9);
                    paddingTop += i8;
                    i7 = childAt.getMeasuredHeight() + aVar.topMargin + aVar.bottomMargin;
                    i9 = measuredWidth;
                } else {
                    i7 = Math.max(i8, childAt.getMeasuredHeight() + aVar.topMargin + aVar.bottomMargin);
                }
                if (z) {
                    paddingTop += i7;
                    i11 = Math.max(i11, i9);
                    i8 = i7;
                } else {
                    i8 = i7;
                }
            } else if (z) {
                paddingTop += i8;
                i11 = Math.max(i11, i9);
                size2 = size2;
            } else {
                size2 = size2;
            }
            i10++;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight() + i11;
        if (mode == 1073741824) {
            paddingLeft = size;
        }
        if (mode2 == 1073741824) {
            paddingTop = size2;
        }
        setMeasuredDimension(paddingLeft, paddingTop);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        FlowLayout bVar = this;
        bVar.f8657b.clear();
        bVar.f8658c.clear();
        bVar.f8659d.clear();
        int width = getWidth();
        int height = getHeight();
        int paddingTop = getPaddingTop();
        ArrayList arrayList = new ArrayList();
        int i10 = bVar.f8656a & 7;
        float f = i10 != 1 ? i10 != 5 ? 0.0f : 1.0f : 0.5f;
        int i11 = paddingTop;
        ArrayList arrayList2 = arrayList;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            i5 = 8;
            if (i12 >= getChildCount()) {
                break;
            }
            View childAt = bVar.getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                C1332a aVar = (C1332a) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth() + aVar.leftMargin + aVar.rightMargin;
                int measuredHeight = childAt.getMeasuredHeight() + aVar.bottomMargin + aVar.topMargin;
                if (i14 + measuredWidth > width) {
                    bVar.f8658c.add(Integer.valueOf(i13));
                    bVar.f8657b.add(arrayList2);
                    bVar.f8659d.add(Integer.valueOf(((int) ((width - i14) * f)) + getPaddingLeft()));
                    i11 += i13;
                    arrayList2 = new ArrayList();
                    i13 = 0;
                    i14 = 0;
                }
                i14 += measuredWidth;
                i13 = Math.max(i13, measuredHeight);
                arrayList2.add(childAt);
            }
            i12++;
        }
        bVar.f8658c.add(Integer.valueOf(i13));
        bVar.f8657b.add(arrayList2);
        bVar.f8659d.add(Integer.valueOf(((int) ((width - i14) * f)) + getPaddingLeft()));
        int i15 = i11 + i13;
        int i16 = bVar.f8656a & 112;
        if (i16 != 16) {
            i6 = i16 != 80 ? 0 : height - i15;
        } else {
            i6 = (height - i15) / 2;
        }
        int size = bVar.f8657b.size();
        int paddingTop2 = getPaddingTop();
        int i17 = 0;
        while (i17 < size) {
            int intValue = bVar.f8658c.get(i17).intValue();
            List<View> list = bVar.f8657b.get(i17);
            int intValue2 = bVar.f8659d.get(i17).intValue();
            int size2 = list.size();
            int i18 = 0;
            while (i18 < size2) {
                View view = list.get(i18);
                if (view.getVisibility() != i5) {
                    C1332a aVar2 = (C1332a) view.getLayoutParams();
                    if (aVar2.height == -1) {
                        if (aVar2.width == -1) {
                            i9 = i14;
                            i8 = MemoryConstants.f21646d;
                        } else if (aVar2.width >= 0) {
                            i9 = aVar2.width;
                            i8 = MemoryConstants.f21646d;
                        } else {
                            i9 = i14;
                            i8 = Integer.MIN_VALUE;
                        }
                        view.measure(View.MeasureSpec.makeMeasureSpec(i9, i8), View.MeasureSpec.makeMeasureSpec((intValue - aVar2.topMargin) - aVar2.bottomMargin, MemoryConstants.f21646d));
                    }
                    int measuredWidth2 = view.getMeasuredWidth();
                    int measuredHeight2 = view.getMeasuredHeight();
                    if (Gravity.isVertical(aVar2.f8660a)) {
                        int i19 = aVar2.f8660a;
                        if (i19 != 80) {
                            switch (i19) {
                                case 16:
                                case 17:
                                    i7 = (((intValue - measuredHeight2) - aVar2.topMargin) - aVar2.bottomMargin) / 2;
                                    break;
                            }
                        } else {
                            i7 = ((intValue - measuredHeight2) - aVar2.topMargin) - aVar2.bottomMargin;
                        }
                        size = size;
                        i14 = i14;
                        list = list;
                        view.layout(aVar2.leftMargin + intValue2, aVar2.topMargin + paddingTop2 + i7 + i6, intValue2 + measuredWidth2 + aVar2.leftMargin, measuredHeight2 + paddingTop2 + aVar2.topMargin + i7 + i6);
                        intValue2 += measuredWidth2 + aVar2.leftMargin + aVar2.rightMargin;
                    }
                    i7 = 0;
                    size = size;
                    i14 = i14;
                    list = list;
                    view.layout(aVar2.leftMargin + intValue2, aVar2.topMargin + paddingTop2 + i7 + i6, intValue2 + measuredWidth2 + aVar2.leftMargin, measuredHeight2 + paddingTop2 + aVar2.topMargin + i7 + i6);
                    intValue2 += measuredWidth2 + aVar2.leftMargin + aVar2.rightMargin;
                } else {
                    size = size;
                    i14 = i14;
                    list = list;
                }
                i18++;
                i5 = 8;
            }
            paddingTop2 += intValue;
            i17++;
            bVar = this;
            i5 = 8;
        }
    }

    /* renamed from: a */
    private static C1332a m20684a(ViewGroup.LayoutParams layoutParams) {
        return new C1332a(layoutParams);
    }

    /* renamed from: a */
    private C1332a m20685a(AttributeSet attributeSet) {
        return new C1332a(getContext(), attributeSet);
    }

    /* renamed from: a */
    private static C1332a m20686a() {
        return new C1332a(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof C1332a);
    }

    @TargetApi(14)
    public final void setGravity(int i) {
        if (this.f8656a != i) {
            if ((8388615 & i) == 0) {
                i |= m20683b() ? GravityCompat.START : 3;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f8656a = i;
            requestLayout();
        }
    }

    public final int getGravity() {
        return this.f8656a;
    }

    /* renamed from: b */
    private static boolean m20683b() {
        return Build.VERSION.SDK_INT >= 14;
    }

    /* compiled from: FlowLayout.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.uip.b$a */
    /* loaded from: classes.dex */
    public static class C1332a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public int f8660a;

        public C1332a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f8660a = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1375R.styleable.FlowLayout_Layout);
            try {
                this.f8660a = obtainStyledAttributes.getInt(C1375R.styleable.FlowLayout_Layout_android_layout_gravity, -1);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        public C1332a(int i, int i2) {
            super(i, i2);
            this.f8660a = -1;
        }

        public C1332a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f8660a = -1;
        }
    }

    @Override // android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C1332a(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C1332a(layoutParams);
    }

    @Override // android.view.ViewGroup
    public final /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C1332a(getContext(), attributeSet);
    }
}
