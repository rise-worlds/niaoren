package com.cyjh.p045mq.p047b;

/* renamed from: com.cyjh.mq.b.a */
/* loaded from: classes.dex */
public final class ClientInfo {

    /* renamed from: a */
    public final int f8792a;

    /* renamed from: b */
    public final int f8793b;

    /* renamed from: c */
    public final boolean f8794c;

    public /* synthetic */ ClientInfo(C1352a aVar, byte b) {
        this(aVar);
    }

    private ClientInfo(C1352a aVar) {
        this.f8792a = aVar.f8795a;
        this.f8793b = aVar.f8796b;
        this.f8794c = aVar.f8797c;
    }

    /* renamed from: a */
    private int m20556a() {
        return this.f8792a;
    }

    /* renamed from: b */
    private int m20555b() {
        return this.f8793b;
    }

    /* renamed from: c */
    private boolean m20554c() {
        return this.f8794c;
    }

    /* compiled from: ClientInfo.java */
    /* renamed from: com.cyjh.mq.b.a$a */
    /* loaded from: classes.dex */
    public static class C1352a {

        /* renamed from: a */
        public int f8795a;

        /* renamed from: b */
        public int f8796b;

        /* renamed from: c */
        public boolean f8797c;

        /* renamed from: a */
        private C1352a m20552a(int i) {
            this.f8795a = i;
            return this;
        }

        /* renamed from: b */
        private C1352a m20549b(int i) {
            this.f8796b = i;
            return this;
        }

        /* renamed from: a */
        private C1352a m20550a(boolean z) {
            this.f8797c = z;
            return this;
        }

        /* renamed from: a */
        private ClientInfo m20553a() {
            return new ClientInfo(this, (byte) 0);
        }
    }
}
