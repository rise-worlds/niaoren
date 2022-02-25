package com.xuhao.didi.socket.client.sdk.client.connection;

/* loaded from: classes2.dex */
public class ReConnectMgr {

    /* renamed from: a */
    private boolean f14485a;

    /* renamed from: b */
    private boolean f14486b;

    /* renamed from: c */
    private boolean f14487c;

    /* renamed from: a */
    public int m14996a() {
        return 1;
    }

    /* renamed from: c */
    public long m14993c() {
        return 3000L;
    }

    private ReConnectMgr() {
        this.f14485a = false;
        this.f14486b = false;
        this.f14487c = false;
    }

    /* renamed from: b */
    public boolean m14994b() {
        return this.f14485a;
    }

    /* renamed from: a */
    public void m14995a(boolean z) {
        this.f14485a = z;
    }

    /* renamed from: d */
    public void m14992d() {
        this.f14486b = true;
    }

    /* renamed from: e */
    public boolean m14991e() {
        return this.f14486b;
    }

    /* renamed from: f */
    public void m14990f() {
        this.f14486b = false;
    }

    /* renamed from: g */
    public void m14989g() {
        this.f14487c = true;
    }

    /* renamed from: h */
    public void m14988h() {
        this.f14487c = false;
    }

    /* renamed from: i */
    public boolean m14987i() {
        return this.f14485a && this.f14486b && !this.f14487c;
    }

    /* loaded from: classes2.dex */
    private static class LazyHolder {

        /* renamed from: a */
        private static final ReConnectMgr f14488a = new ReConnectMgr();

        private LazyHolder() {
        }
    }

    /* renamed from: j */
    public static ReConnectMgr m14986j() {
        return LazyHolder.f14488a;
    }
}
