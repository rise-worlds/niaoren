package com.p018b.p029b;

import java.util.concurrent.TimeUnit;

/* renamed from: com.b.b.k */
/* loaded from: classes.dex */
public final class ForwardingTimeout extends Timeout {

    /* renamed from: a */
    private Timeout f6429a;

    public ForwardingTimeout(Timeout yVar) {
        if (yVar != null) {
            this.f6429a = yVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    /* renamed from: a */
    public final Timeout m24311a() {
        return this.f6429a;
    }

    /* renamed from: a */
    public final ForwardingTimeout m24310a(Timeout yVar) {
        if (yVar != null) {
            this.f6429a = yVar;
            return this;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: a */
    public final Timeout mo24242a(long j, TimeUnit timeUnit) {
        return this.f6429a.mo24242a(j, timeUnit);
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: b_ */
    public final long mo24248b_() {
        return this.f6429a.mo24248b_();
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: c_ */
    public final boolean mo24247c_() {
        return this.f6429a.mo24247c_();
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: d */
    public final long mo24246d() {
        return this.f6429a.mo24246d();
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: a */
    public final Timeout mo24243a(long j) {
        return this.f6429a.mo24243a(j);
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: d_ */
    public final Timeout mo24245d_() {
        return this.f6429a.mo24245d_();
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: e_ */
    public final Timeout mo24244e_() {
        return this.f6429a.mo24244e_();
    }

    @Override // com.p018b.p029b.Timeout
    /* renamed from: f_ */
    public final void mo24241f_() {
        this.f6429a.mo24241f_();
    }
}
