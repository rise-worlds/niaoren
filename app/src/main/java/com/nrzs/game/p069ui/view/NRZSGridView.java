package com.nrzs.game.p069ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* renamed from: com.nrzs.game.ui.view.NRZSGridView */
/* loaded from: classes2.dex */
public class NRZSGridView extends GridView {
    public NRZSGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NRZSGridView(Context context) {
        super(context);
    }

    public NRZSGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
