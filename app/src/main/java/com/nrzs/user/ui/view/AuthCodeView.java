package com.nrzs.user.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.nrzs.user.C2222R;
import java.util.Random;

/* renamed from: com.nrzs.user.ui.view.AuthCodeView */
/* loaded from: classes2.dex */
public class AuthCodeView extends View {

    /* renamed from: a */
    public static final int f11637a = 100;

    /* renamed from: b */
    public static final int f11638b = 2;

    /* renamed from: c */
    String[] f11639c;

    /* renamed from: d */
    Random f11640d;

    /* renamed from: e */
    private String f11641e;

    /* renamed from: f */
    private int f11642f;

    /* renamed from: g */
    private int f11643g;

    /* renamed from: h */
    private Rect f11644h;

    /* renamed from: i */
    private Paint f11645i;

    public AuthCodeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AuthCodeView(Context context) {
        this(context, null);
    }

    public AuthCodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11639c = new String[4];
        this.f11640d = new Random();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2222R.styleable.AuthCodeView, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == C2222R.styleable.AuthCodeView_userTitleText) {
                this.f11641e = obtainStyledAttributes.getString(index);
            } else if (index == C2222R.styleable.AuthCodeView_userTitleTextColor) {
                this.f11642f = obtainStyledAttributes.getColor(index, -16777216);
            } else if (index == C2222R.styleable.AuthCodeView_userTitleTextSize) {
                this.f11643g = obtainStyledAttributes.getDimensionPixelSize(index, (int) TypedValue.applyDimension(2, 16.0f, getResources().getDisplayMetrics()));
            }
        }
        obtainStyledAttributes.recycle();
        this.f11641e = m18190b();
        this.f11645i = new Paint();
        this.f11645i.setTextSize(this.f11643g);
        this.f11644h = new Rect();
        Paint paint = this.f11645i;
        String str = this.f11641e;
        paint.getTextBounds(str, 0, str.length(), this.f11644h);
        setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.view.AuthCodeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AuthCodeView.this.m18193a();
            }
        });
    }

    /* renamed from: b */
    private String m18190b() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int nextInt = this.f11640d.nextInt(10);
            String[] strArr = this.f11639c;
            stringBuffer2.append(nextInt);
            strArr[i] = stringBuffer2.toString();
            stringBuffer.append(nextInt);
        }
        return stringBuffer.toString();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int i4 = 0;
        if (mode != Integer.MIN_VALUE) {
            i3 = mode != 1073741824 ? 0 : size + getPaddingLeft() + getPaddingRight();
        } else {
            i3 = getPaddingLeft() + getPaddingRight() + this.f11644h.width();
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == Integer.MIN_VALUE) {
            i4 = getPaddingTop() + getPaddingBottom() + this.f11644h.height();
        } else if (mode2 == 1073741824) {
            i4 = getPaddingTop() + getPaddingBottom() + size2;
        }
        setMeasuredDimension(i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.f11645i.setColor(-1);
        canvas.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.f11645i);
        this.f11645i.setColor(this.f11642f);
        for (int i = 0; i < 2; i++) {
            this.f11645i.setStrokeWidth(5.0f);
            int[] b = m18189b(getMeasuredHeight(), getMeasuredWidth());
            canvas.drawLine(b[0], b[1], b[2], b[3], this.f11645i);
        }
        this.f11645i.setColor(-16711936);
        for (int i2 = 0; i2 < 100; i2++) {
            int nextInt = this.f11640d.nextInt(5);
            int[] a = m18191a(getMeasuredHeight(), getMeasuredWidth());
            canvas.drawCircle(a[0], a[1], nextInt, this.f11645i);
        }
        this.f11645i.setColor(this.f11642f);
        int i3 = 20;
        for (int i4 = 0; i4 < 4; i4++) {
            canvas.drawText("" + this.f11639c[i4], i3 - 5, ((getHeight() / 2) + m18192a(this.f11644h.height() / 2)) - 5, this.f11645i);
            i3 += ((getWidth() / 2) - (this.f11644h.width() / 2)) + (i4 / 5) + 20;
        }
    }

    /* renamed from: a */
    private int m18192a(int i) {
        int random = (int) (Math.random() * i);
        return random < 20 ? random + 20 : random;
    }

    /* renamed from: a */
    public static int[] m18191a(int i, int i2) {
        int[] iArr = {0, 0, 0, 0};
        iArr[0] = (int) (Math.random() * i2);
        iArr[1] = (int) (Math.random() * i);
        return iArr;
    }

    /* renamed from: b */
    public static int[] m18189b(int i, int i2) {
        int[] iArr = {0, 0, 0, 0};
        for (int i3 = 0; i3 < 4; i3 += 2) {
            iArr[i3] = (int) (Math.random() * i2);
            iArr[i3 + 1] = (int) (Math.random() * i);
        }
        return iArr;
    }

    public String getAuthCode() {
        return this.f11641e.trim();
    }

    /* renamed from: a */
    public void m18193a() {
        this.f11641e = m18190b();
        postInvalidate();
    }
}
