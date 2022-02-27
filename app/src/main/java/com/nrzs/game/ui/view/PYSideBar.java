package com.nrzs.game.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.googlecode.tesseract.android.TessBaseAPI;

/* renamed from: com.nrzs.game.ui.view.PYSideBar */
/* loaded from: classes2.dex */
public class PYSideBar extends View {

    /* renamed from: a */
    public static String[] f11122a = {"A", "B", "C", "D", "E", TessBaseAPI.f9205f, "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", TessBaseAPI.f9204e, "U", "V", "W", "X", "Y", "Z", "#"};

    /* renamed from: b */
    private AbstractC2158a f11123b;

    /* renamed from: c */
    private int f11124c = -1;

    /* renamed from: d */
    private Paint f11125d = new Paint();

    /* renamed from: e */
    private TextView f11126e;

    /* renamed from: com.nrzs.game.ui.view.PYSideBar$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2158a {
        /* renamed from: a */
        void mo18590a(String str);
    }

    public void setTextView(TextView textView) {
        this.f11126e = textView;
    }

    public PYSideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PYSideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PYSideBar(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int length = height / f11122a.length;
        String simpleName = PYSideBar.class.getSimpleName();
        Log.d(simpleName, "width:" + width + ",height:" + height + ",singleHeight:" + length);
        for (int i = 0; i < f11122a.length; i++) {
            this.f11125d.setColor(Color.parseColor("#808080"));
            this.f11125d.setAntiAlias(true);
            this.f11125d.setTextSize(m18591a(12));
            if (i == this.f11124c) {
                this.f11125d.setColor(Color.parseColor("#007eff"));
                this.f11125d.setFakeBoldText(true);
            }
            canvas.drawText(f11122a[i], (width / 2) - (this.f11125d.measureText(f11122a[i]) / 2.0f), (length * i) + length, this.f11125d);
            this.f11125d.reset();
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.f11124c;
        AbstractC2158a aVar = this.f11123b;
        float height = y / getHeight();
        String[] strArr = f11122a;
        int length = (int) (height * strArr.length);
        if (action == 1) {
            setBackgroundDrawable(new ColorDrawable(0));
            this.f11124c = -1;
            invalidate();
            TextView textView = this.f11126e;
            if (textView != null) {
                textView.setVisibility(4);
            }
        } else if (i != length && length >= 0 && length < strArr.length) {
            if (aVar != null) {
                aVar.mo18590a(strArr[length]);
            }
            this.f11124c = length;
            invalidate();
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(AbstractC2158a aVar) {
        this.f11123b = aVar;
    }

    /* renamed from: a */
    private int m18591a(int i) {
        return (int) ((i * getContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
