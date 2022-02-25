package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSink;
import java.util.List;

/* renamed from: com.b.a.x */
/* loaded from: classes.dex */
public final class FormBody extends RequestBody {

    /* renamed from: a */
    private static final MediaType f6398a = MediaType.m24510a("application/x-www-form-urlencoded");

    /* renamed from: b */
    private final List<String> f6399b;

    /* renamed from: c */
    private final List<String> f6400c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormBody(List<String> list, List<String> list2) {
        this.f6399b = Util.m24753a(list);
        this.f6400c = Util.m24753a(list2);
    }

    @Override // com.p018b.p019a.RequestBody
    /* renamed from: a */
    public final MediaType mo24366a() {
        return f6398a;
    }

    @Override // com.p018b.p019a.RequestBody
    /* renamed from: b */
    public final long mo24363b() {
        return m24364a(null, true);
    }

    @Override // com.p018b.p019a.RequestBody
    /* renamed from: a */
    public final void mo24365a(BufferedSink gVar) {
        m24364a(gVar, false);
    }

    /* renamed from: a */
    private long m24364a(BufferedSink gVar, boolean z) {
        Buffer fVar;
        if (z) {
            fVar = new Buffer();
        } else {
            fVar = gVar.mo24284c();
        }
        int size = this.f6399b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                fVar.mo24293h(38);
            }
            fVar.mo24298b(this.f6399b.get(i));
            fVar.mo24293h(61);
            fVar.mo24298b(this.f6400c.get(i));
        }
        if (!z) {
            return 0L;
        }
        long b = fVar.m24331b();
        fVar.m24317o();
        return b;
    }
}
