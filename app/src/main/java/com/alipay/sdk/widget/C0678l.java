package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.C0675j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.widget.l */
/* loaded from: classes.dex */
public class C0678l extends C0675j.AbstractanimationAnimation$AnimationListenerC0676a {

    /* renamed from: b */
    final /* synthetic */ C0682p f390b;

    /* renamed from: c */
    final /* synthetic */ C0675j f391c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0678l(C0675j jVar, C0682p pVar) {
        super(jVar, null);
        this.f391c = jVar;
        this.f390b = pVar;
    }

    @Override // com.alipay.sdk.widget.C0675j.AbstractanimationAnimation$AnimationListenerC0676a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.f390b.m25240a();
        this.f391c.f384v = false;
    }
}
