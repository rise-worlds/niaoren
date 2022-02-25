package com.nrzs.moudleui.p070ui.state;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.nrzs.moudleui.C2202R;

/* renamed from: com.nrzs.moudleui.ui.state.LoadingView */
/* loaded from: classes2.dex */
public class LoadingView extends RelativeLayout {
    public LoadingView(Context context) {
        super(context);
        m18423a();
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m18423a();
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m18423a();
    }

    /* renamed from: a */
    private void m18423a() {
        LayoutInflater.from(getContext()).inflate(C2202R.layout.layout_loading, (ViewGroup) this, true);
    }
}
