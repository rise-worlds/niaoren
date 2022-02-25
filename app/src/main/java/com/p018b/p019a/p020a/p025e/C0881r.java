package com.p018b.p019a.p020a.p025e;

import com.p018b.p029b.BufferedSink;
import com.p018b.p029b.BufferedSource;
import java.net.Socket;

/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.r */
/* loaded from: classes.dex */
public final class C0881r {

    /* renamed from: a */
    Socket f5992a;

    /* renamed from: b */
    String f5993b;

    /* renamed from: c */
    BufferedSource f5994c;

    /* renamed from: d */
    BufferedSink f5995d;

    /* renamed from: e */
    AbstractC0882s f5996e = AbstractC0882s.f5999f;

    /* renamed from: f */
    PushObserver f5997f = PushObserver.f5884a;

    /* renamed from: g */
    boolean f5998g = true;

    /* renamed from: a */
    public final C0881r m24615a(Socket socket, String str, BufferedSource hVar, BufferedSink gVar) {
        this.f5992a = socket;
        this.f5993b = str;
        this.f5994c = hVar;
        this.f5995d = gVar;
        return this;
    }

    /* renamed from: a */
    public final C0881r m24616a(AbstractC0882s sVar) {
        this.f5996e = sVar;
        return this;
    }

    /* renamed from: a */
    public final Http2Connection m24617a() {
        return new Http2Connection(this);
    }
}
