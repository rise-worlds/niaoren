package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.p023c.HttpCodec;
import com.p018b.p029b.ForwardingSource;
import com.p018b.p029b.Source;

/* compiled from: Http2Codec.java */
/* renamed from: com.b.a.a.e.i */
/* loaded from: classes.dex */
final class C0873i extends ForwardingSource {

    /* renamed from: a */
    final /* synthetic */ Http2Codec f5943a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0873i(Http2Codec hVar, Source xVar) {
        super(xVar);
        this.f5943a = hVar;
    }

    @Override // com.p018b.p029b.ForwardingSource, com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f5943a.f5939a.m24778a(false, (HttpCodec) this.f5943a);
        super.close();
    }
}
