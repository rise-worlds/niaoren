package com.nrzs.ft.ui.view;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.nrzs.ft.FloatApp;

/* renamed from: com.nrzs.ft.ui.view.NRZSScreenChangeLayout */
/* loaded from: classes2.dex */
public class NRZSScreenChangeLayout extends LinearLayout {

    /* renamed from: a */
    private AbstractC2027a f10794a;

    /* renamed from: com.nrzs.ft.ui.view.NRZSScreenChangeLayout$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2027a {
        /* renamed from: a */
        void m18838a(boolean z);
    }

    public NRZSScreenChangeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setiChangeCallback(AbstractC2027a aVar) {
        this.f10794a = aVar;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
        if (ScreenUtils.m23290h()) {
            LogUtils.m23734c("convert", "convert - 竖屏");
            layoutParams.width = -1;
            layoutParams.height = FloatApp.m18899b().f10711a;
        } else {
            LogUtils.m23734c("convert", "convert - 横屏");
            layoutParams.width = FloatApp.m18899b().f10711a;
            layoutParams.height = -1;
        }
        setLayoutParams(layoutParams);
    }
}
