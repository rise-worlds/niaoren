package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.ByteString;
import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.u */
/* loaded from: classes.dex */
public final class C0884u extends NamedRunnable implements Http2Reader {

    /* renamed from: a */
    final C0888y f6000a;

    /* renamed from: c */
    final /* synthetic */ Http2Connection f6001c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0884u(Http2Connection jVar, C0888y yVar) {
        super("OkHttp %s", jVar.f5949e);
        this.f6001c = jVar;
        this.f6000a = yVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        Http2Connection jVar;
        ErrorCode bVar = ErrorCode.INTERNAL_ERROR;
        ErrorCode bVar2 = ErrorCode.INTERNAL_ERROR;
        try {
            try {
                try {
                    this.f6000a.m24601a(this);
                    do {
                    } while (this.f6000a.m24599a(false, this));
                    bVar = ErrorCode.NO_ERROR;
                    bVar2 = ErrorCode.CANCEL;
                    jVar = this.f6001c;
                } catch (IOException unused) {
                    bVar = ErrorCode.PROTOCOL_ERROR;
                    bVar2 = ErrorCode.PROTOCOL_ERROR;
                    jVar = this.f6001c;
                }
                jVar.m24627a(bVar, bVar2);
            } catch (Throwable th) {
                try {
                    this.f6001c.m24627a(bVar, bVar2);
                } catch (IOException unused2) {
                }
                Util.m24764a(this.f6000a);
                throw th;
            }
        } catch (IOException unused3) {
        }
        Util.m24764a(this.f6000a);
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24606a(boolean z, int i, BufferedSource hVar, int i2) {
        if (Http2Connection.m24618d(i)) {
            this.f6001c.m24631a(i, hVar, i2, z);
            return;
        }
        Http2Stream a = this.f6001c.m24634a(i);
        if (a == null) {
            this.f6001c.m24632a(i, ErrorCode.PROTOCOL_ERROR);
            hVar.mo24278f(i2);
            return;
        }
        a.m24715a(hVar, i2);
        if (z) {
            a.m24706f();
        }
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24605a(boolean z, int i, List<Header> list) {
        if (Http2Connection.m24618d(i)) {
            this.f6001c.m24629a(i, list, z);
            return;
        }
        synchronized (this.f6001c) {
            if (!this.f6001c.f5952h) {
                Http2Stream a = this.f6001c.m24634a(i);
                if (a != null) {
                    a.m24714a(list);
                    if (z) {
                        a.m24706f();
                    }
                } else if (i > this.f6001c.f5950f) {
                    if (i % 2 != this.f6001c.f5951g % 2) {
                        Http2Stream abVar = new Http2Stream(i, this.f6001c, false, z, list);
                        this.f6001c.f5950f = i;
                        this.f6001c.f5948d.put(Integer.valueOf(i), abVar);
                        Http2Connection.f5944a.execute(new C0885v(this, "OkHttp %s stream %d", new Object[]{this.f6001c.f5949e, Integer.valueOf(i)}, abVar));
                    }
                }
            }
        }
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24611a(int i, ErrorCode bVar) {
        if (Http2Connection.m24618d(i)) {
            this.f6001c.m24619c(i, bVar);
            return;
        }
        Http2Stream b = this.f6001c.m24624b(i);
        if (b != null) {
            b.m24710c(bVar);
        }
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24608a(Settings alVar) {
        int i;
        Http2Stream[] abVarArr;
        long j;
        synchronized (this.f6001c) {
            int d = this.f6001c.f5957m.m24666d();
            Settings alVar2 = this.f6001c.f5957m;
            for (int i2 = 0; i2 < 10; i2++) {
                if (alVar.m24672a(i2)) {
                    alVar2.m24671a(i2, alVar.m24669b(i2));
                }
            }
            Http2Connection.f5944a.execute(new C0887x(this, "OkHttp %s ACK Settings", new Object[]{this.f6001c.f5949e}, alVar));
            int d2 = this.f6001c.f5957m.m24666d();
            abVarArr = null;
            if (d2 == -1 || d2 == d) {
                j = 0;
            } else {
                j = d2 - d;
                if (!this.f6001c.f5958n) {
                    Http2Connection jVar = this.f6001c;
                    jVar.f5955k += j;
                    if (j > 0) {
                        jVar.notifyAll();
                    }
                    this.f6001c.f5958n = true;
                }
                if (!this.f6001c.f5948d.isEmpty()) {
                    abVarArr = (Http2Stream[]) this.f6001c.f5948d.values().toArray(new Http2Stream[this.f6001c.f5948d.size()]);
                }
            }
            Http2Connection.f5944a.execute(new C0886w(this, "OkHttp %s settings", this.f6001c.f5949e));
        }
        if (!(abVarArr == null || j == 0)) {
            for (Http2Stream abVar : abVarArr) {
                synchronized (abVar) {
                    abVar.m24717a(j);
                }
            }
        }
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24607a(boolean z, int i, int i2) {
        if (z) {
            Ping c = this.f6001c.m24620c(i);
            if (c != null) {
                c.m24679b();
                return;
            }
            return;
        }
        Http2Connection jVar = this.f6001c;
        Http2Connection.f5944a.execute(new C0876m(jVar, "OkHttp %s ping %08x%08x", new Object[]{jVar.f5949e, Integer.valueOf(i), Integer.valueOf(i2)}, i, i2));
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24610a(int i, ByteString iVar) {
        Http2Stream[] abVarArr;
        iVar.mo24255g();
        synchronized (this.f6001c) {
            abVarArr = (Http2Stream[]) this.f6001c.f5948d.values().toArray(new Http2Stream[this.f6001c.f5948d.size()]);
            this.f6001c.f5952h = true;
        }
        for (Http2Stream abVar : abVarArr) {
            if (abVar.f5844c > i && abVar.m24713b()) {
                abVar.m24710c(ErrorCode.REFUSED_STREAM);
                this.f6001c.m24624b(abVar.f5844c);
            }
        }
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24612a(int i, long j) {
        if (i == 0) {
            synchronized (this.f6001c) {
                this.f6001c.f5955k += j;
                this.f6001c.notifyAll();
            }
            return;
        }
        Http2Stream a = this.f6001c.m24634a(i);
        if (a != null) {
            synchronized (a) {
                a.m24717a(j);
            }
        }
    }

    @Override // com.p018b.p019a.p020a.p025e.Http2Reader
    /* renamed from: a */
    public final void mo24609a(int i, List<Header> list) {
        this.f6001c.m24630a(i, list);
    }
}
