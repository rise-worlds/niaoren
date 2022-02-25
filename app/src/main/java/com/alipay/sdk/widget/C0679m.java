package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.C0675j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.widget.m */
/* loaded from: classes.dex */
public class C0679m extends C0675j.AbstractanimationAnimation$AnimationListenerC0676a {

    /* renamed from: b */
    final /* synthetic */ C0682p f392b;

    /* renamed from: c */
    final /* synthetic */ String f393c;

    /* renamed from: d */
    final /* synthetic */ C0675j f394d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0679m(C0675j jVar, C0682p pVar, String str) {
        super(jVar, null);
        this.f394d = jVar;
        this.f392b = pVar;
        this.f393c = str;
    }

    @Override // com.alipay.sdk.widget.C0675j.AbstractanimationAnimation$AnimationListenerC0676a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        C0682p pVar;
        this.f394d.removeView(this.f392b);
        pVar = this.f394d.f386x;
        pVar.m25235a(this.f393c);
        this.f394d.f384v = false;
    }
}
