package com.nrzs.moudleui.p070ui.state;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.moudleui.C2202R;

/* renamed from: com.nrzs.moudleui.ui.state.LoadingImageView */
/* loaded from: classes2.dex */
public class LoadingImageView extends AppCompatImageView {

    /* renamed from: a */
    private AnimationDrawable f11296a;

    public LoadingImageView(Context context) {
        super(context);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        setImageResource(C2202R.C2203drawable.bird_ani_common_loading);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtils.m23734c("HomeAdapter", "onAttachedToWindow");
        new Handler().post(new Runnable() { // from class: com.nrzs.moudleui.ui.state.LoadingImageView.1
            @Override // java.lang.Runnable
            public void run() {
                if (LoadingImageView.this.getDrawable() instanceof AnimationDrawable) {
                    LoadingImageView loadingImageView = LoadingImageView.this;
                    loadingImageView.f11296a = (AnimationDrawable) loadingImageView.getDrawable();
                    if (LoadingImageView.this.f11296a != null) {
                        LoadingImageView.this.f11296a.start();
                    }
                }
            }
        });
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtils.m23734c("HomeAdapter", "onDetachedFromWindow");
        new Handler().post(new Runnable() { // from class: com.nrzs.moudleui.ui.state.LoadingImageView.2
            @Override // java.lang.Runnable
            public void run() {
                if (LoadingImageView.this.f11296a != null) {
                    LoadingImageView.this.f11296a.stop();
                }
                LoadingImageView.this.clearAnimation();
            }
        });
    }
}
