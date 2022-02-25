package com.nrzs.libcommon;

import android.view.View;

/* renamed from: com.nrzs.libcommon.c */
/* loaded from: classes2.dex */
public abstract class NRZSViewClickListener implements View.OnClickListener {

    /* renamed from: a */
    private long f11179a;

    /* renamed from: b */
    private long f11180b;

    /* renamed from: a */
    protected abstract void mo18275a(View view);

    /* renamed from: b */
    protected abstract void mo18274b(View view);

    public NRZSViewClickListener() {
        this.f11180b = 800L;
    }

    public NRZSViewClickListener(long j) {
        this.f11180b = 800L;
        this.f11180b = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f11179a > this.f11180b) {
            mo18275a(view);
            this.f11179a = currentTimeMillis;
            return;
        }
        mo18274b(view);
    }
}
