package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.Headers;
import com.p018b.p019a.MediaType;
import com.p018b.p019a.ResponseBody;
import com.p018b.p029b.BufferedSource;

/* renamed from: com.b.a.a.c.i */
/* loaded from: classes.dex */
public final class RealResponseBody extends ResponseBody {

    /* renamed from: a */
    private final Headers f5806a;

    /* renamed from: b */
    private final BufferedSource f5807b;

    public RealResponseBody(Headers aaVar, BufferedSource hVar) {
        this.f5806a = aaVar;
        this.f5807b = hVar;
    }

    @Override // com.p018b.p019a.ResponseBody
    /* renamed from: a */
    public final MediaType mo24427a() {
        String a = this.f5806a.m24557a("Content-Type");
        if (a != null) {
            return MediaType.m24510a(a);
        }
        return null;
    }

    @Override // com.p018b.p019a.ResponseBody
    /* renamed from: b */
    public final long mo24426b() {
        return HttpHeaders.m24743a(this.f5806a);
    }

    @Override // com.p018b.p019a.ResponseBody
    /* renamed from: c */
    public final BufferedSource mo24425c() {
        return this.f5807b;
    }
}
