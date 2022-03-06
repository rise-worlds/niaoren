package com.lbd.xj.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/* renamed from: com.lbd.xj.ui.view.MyVideoView */
/* loaded from: classes.dex */
public class MyVideoView extends VideoView {
    public MyVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MyVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyVideoView(Context context) {
        super(context);
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(0, i);
        int defaultSize2 = getDefaultSize(0, i2);
        getHolder().setFixedSize(defaultSize, defaultSize2);
        setMeasuredDimension(defaultSize, defaultSize2);
    }
}
