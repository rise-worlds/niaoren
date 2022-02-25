package com.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.tencent.smtt.sdk.TbsListener;

/* loaded from: classes.dex */
public class CircularProgressBar extends View {
    Paint paintBg;
    Paint paintTouch;
    int progress;
    RectF rectBg;
    RectF rectTouch;
    int bgColor = Color.rgb((int) TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK, (int) TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS, 247);
    int touchColor = Color.rgb(87, 137, 247);

    public CircularProgressBar(Context context) {
        super(context);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.paintBg = new Paint();
        this.paintBg.setAntiAlias(true);
        this.paintBg.setColor(this.bgColor);
        this.paintTouch = new Paint();
        this.paintTouch.setAntiAlias(true);
        this.paintTouch.setColor(this.touchColor);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int measuredWidth;
        super.onDraw(canvas);
        this.rectBg = new RectF();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        float f = measuredHeight;
        this.rectBg.set(getPaddingLeft(), 0.0f, (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), f);
        this.rectTouch = new RectF(getPaddingLeft(), 0.0f, ((measuredWidth * this.progress) / 100) + getPaddingLeft(), f);
        canvas.drawRoundRect(this.rectBg, f, f, this.paintBg);
        canvas.drawRoundRect(this.rectTouch, f, f, this.paintTouch);
    }

    public void setProgress(int i) {
        if (i <= 0) {
            i = 0;
        }
        if (i >= 100) {
            i = 100;
        }
        this.progress = i;
        invalidate();
    }
}
