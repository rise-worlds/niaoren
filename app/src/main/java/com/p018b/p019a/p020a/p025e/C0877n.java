package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.n */
/* loaded from: classes.dex */
public final class C0877n extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ int f5977a;

    /* renamed from: c */
    final /* synthetic */ List f5978c;

    /* renamed from: d */
    final /* synthetic */ Http2Connection f5979d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0877n(Http2Connection jVar, String str, Object[] objArr, int i, List list) {
        super(str, objArr);
        this.f5979d = jVar;
        this.f5977a = i;
        this.f5978c = list;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        this.f5979d.f5953i.mo24677a();
        try {
            this.f5979d.f5960p.m24695a(this.f5977a, ErrorCode.CANCEL);
            synchronized (this.f5979d) {
                this.f5979d.f5962r.remove(Integer.valueOf(this.f5977a));
            }
        } catch (IOException unused) {
        }
    }
}
