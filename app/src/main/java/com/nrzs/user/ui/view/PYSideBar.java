package com.nrzs.user.ui.view;

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

/* renamed from: com.nrzs.user.ui.view.PYSideBar */
/* loaded from: classes2.dex */
public class PYSideBar extends View {

    /* renamed from: a */
    public static String[] f11647a = {"A", "B", "C", "D", "E", TessBaseAPI.f9205f, "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", TessBaseAPI.f9204e, "U", "V", "W", "X", "Y", "Z", "#"};

    /* renamed from: b */
    private AbstractC2333a f11648b;

    /* renamed from: c */
    private int f11649c = -1;

    /* renamed from: d */
    private Paint f11650d = new Paint();

    /* renamed from: e */
    private TextView f11651e;

    /* renamed from: com.nrzs.user.ui.view.PYSideBar$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2333a {
        /* renamed from: a */
        void mo18187a(String str);
    }

    public void setTextView(TextView textView) {
        this.f11651e = textView;
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
        int length = height / f11647a.length;
        String simpleName = PYSideBar.class.getSimpleName();
        Log.d(simpleName, "width:" + width + ",height:" + height + ",singleHeight:" + length);
        for (int i = 0; i < f11647a.length; i++) {
            this.f11650d.setColor(Color.parseColor("#808080"));
            this.f11650d.setAntiAlias(true);
            this.f11650d.setTextSize(m18188a(12));
            if (i == this.f11649c) {
                this.f11650d.setColor(Color.parseColor("#007eff"));
                this.f11650d.setFakeBoldText(true);
            }
            canvas.drawText(f11647a[i], (width / 2) - (this.f11650d.measureText(f11647a[i]) / 2.0f), (length * i) + length, this.f11650d);
            this.f11650d.reset();
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.f11649c;
        AbstractC2333a aVar = this.f11648b;
        float height = y / getHeight();
        String[] strArr = f11647a;
        int length = (int) (height * strArr.length);
        if (action == 1) {
            setBackgroundDrawable(new ColorDrawable(0));
            this.f11649c = -1;
            invalidate();
            TextView textView = this.f11651e;
            if (textView != null) {
                textView.setVisibility(4);
            }
        } else if (i != length && length >= 0 && length < strArr.length) {
            if (aVar != null) {
                aVar.mo18187a(strArr[length]);
            }
            this.f11649c = length;
            invalidate();
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(AbstractC2333a aVar) {
        this.f11648b = aVar;
    }

    /* renamed from: a */
    private int m18188a(int i) {
        return (int) ((i * getContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
