package com.noober.background.view;

import android.content.Context;
import android.support.p006v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.noober.background.BackgroundFactory;

/* loaded from: classes.dex */
public class BLTextView extends AppCompatTextView {
    public BLTextView(Context context) {
        super(context);
    }

    public BLTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public BLTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        BackgroundFactory.setViewBackground(context, attributeSet, this);
    }
}
