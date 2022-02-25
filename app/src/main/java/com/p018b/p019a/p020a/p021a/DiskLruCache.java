package com.p018b.p019a.p020a.p021a;

import com.p018b.p019a.p020a.p026f.FileSystem;
import com.p018b.p029b.BufferedSink;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/* renamed from: com.b.a.a.a.f */
/* loaded from: classes.dex */
public final class DiskLruCache implements Closeable, Flushable {

    /* renamed from: b */
    final FileSystem f5712b;

    /* renamed from: c */
    final int f5713c;

    /* renamed from: d */
    BufferedSink f5714d;

    /* renamed from: e */
    final LinkedHashMap<String, C0856h> f5715e;

    /* renamed from: f */
    int f5716f;

    /* renamed from: g */
    boolean f5717g;

    /* renamed from: h */
    boolean f5718h;

    /* renamed from: i */
    boolean f5719i;

    /* renamed from: k */
    private long f5720k;

    /* renamed from: l */
    private long f5721l;

    /* renamed from: m */
    private final Executor f5722m;

    /* renamed from: n */
    private final Runnable f5723n;

    /* renamed from: j */
    static final /* synthetic */ boolean f5711j = !DiskLruCache.class.desiredAssertionStatus();

    /* renamed from: a */
    static final Pattern f5710a = Pattern.compile("[a-z0-9_-]{1,120}");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m24816a(C0855g gVar) {
        C0856h hVar = gVar.f5724a;
        if (hVar.f5732f == gVar) {
            for (int i = 0; i < this.f5713c; i++) {
                this.f5712b.mo24598a(hVar.f5730d[i]);
            }
            this.f5716f++;
            hVar.f5732f = null;
            if (false || hVar.f5731e) {
                hVar.f5731e = true;
                this.f5714d.mo24298b("CLEAN").mo24293h(32);
                this.f5714d.mo24298b(hVar.f5727a);
                hVar.m24810a(this.f5714d);
                this.f5714d.mo24293h(10);
            } else {
                this.f5715e.remove(hVar.f5727a);
                this.f5714d.mo24298b("REMOVE").mo24293h(32);
                this.f5714d.mo24298b(hVar.f5727a);
                this.f5714d.mo24293h(10);
            }
            this.f5714d.flush();
            if (this.f5721l > this.f5720k || m24817a()) {
                this.f5722m.execute(this.f5723n);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    private boolean m24817a() {
        int i = this.f5716f;
        return i >= 2000 && i >= this.f5715e.size();
    }

    /* renamed from: a */
    private boolean m24815a(C0856h hVar) {
        if (hVar.f5732f != null) {
            C0855g gVar = hVar.f5732f;
            if (gVar.f5724a.f5732f == gVar) {
                for (int i = 0; i < gVar.f5725b.f5713c; i++) {
                    try {
                        gVar.f5725b.f5712b.mo24598a(gVar.f5724a.f5730d[i]);
                    } catch (IOException unused) {
                    }
                }
                gVar.f5724a.f5732f = null;
            }
        }
        for (int i2 = 0; i2 < this.f5713c; i2++) {
            this.f5712b.mo24598a(hVar.f5729c[i2]);
            this.f5721l -= hVar.f5728b[i2];
            hVar.f5728b[i2] = 0;
        }
        this.f5716f++;
        this.f5714d.mo24298b("REMOVE").mo24293h(32).mo24298b(hVar.f5727a).mo24293h(10);
        this.f5715e.remove(hVar.f5727a);
        if (m24817a()) {
            this.f5722m.execute(this.f5723n);
        }
        return true;
    }

    /* renamed from: b */
    private synchronized boolean m24814b() {
        return this.f5718h;
    }

    /* renamed from: c */
    private synchronized void m24813c() {
        if (m24814b()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() {
        if (this.f5717g) {
            m24813c();
            m24812d();
            this.f5714d.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        C0856h[] hVarArr;
        if (this.f5717g && !this.f5718h) {
            for (C0856h hVar : (C0856h[]) this.f5715e.values().toArray(new C0856h[this.f5715e.size()])) {
                if (hVar.f5732f != null) {
                    hVar.f5732f.m24811a();
                }
            }
            m24812d();
            this.f5714d.close();
            this.f5714d = null;
            this.f5718h = true;
            return;
        }
        this.f5718h = true;
    }

    /* renamed from: d */
    private void m24812d() {
        while (this.f5721l > this.f5720k) {
            m24815a(this.f5715e.values().iterator().next());
        }
        this.f5719i = false;
    }
}
