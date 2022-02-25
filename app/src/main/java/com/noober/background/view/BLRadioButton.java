package com.noober.background.view;

import android.content.Context;
import android.support.p006v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import com.noober.background.BackgroundFactory;

/* loaded from: classes.dex */
public class BLRadioButton extends AppCompatRadioButton {
    public BLRadioButton(Context context) {
        super(context);
    }

    public BLRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public BLRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        BackgroundFactory.setViewBackground(context, attributeSet, this);
    }
}
