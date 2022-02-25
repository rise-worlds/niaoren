package com.xuhao.didi.socket.client.sdk.client;

import java.io.Serializable;

/* renamed from: com.xuhao.didi.socket.client.sdk.client.a */
/* loaded from: classes2.dex */
public final class ConnectionInfo implements Serializable, Cloneable {

    /* renamed from: a */
    private String f14464a;

    /* renamed from: b */
    private int f14465b;

    /* renamed from: c */
    private ConnectionInfo f14466c;

    public ConnectionInfo(String str, int i) {
        this.f14464a = str;
        this.f14465b = i;
    }

    /* renamed from: a */
    public String m15021a() {
        return this.f14464a;
    }

    /* renamed from: b */
    public int m15018b() {
        return this.f14465b;
    }

    /* renamed from: c */
    public ConnectionInfo m15017c() {
        return this.f14466c;
    }

    /* renamed from: a */
    public void m15020a(ConnectionInfo aVar) {
        this.f14466c = aVar;
    }

    /* renamed from: d */
    public ConnectionInfo m15013g() {
        ConnectionInfo aVar = new ConnectionInfo(this.f14464a, this.f14465b);
        ConnectionInfo aVar2 = this.f14466c;
        if (aVar2 != null) {
            aVar.m15020a(aVar2.m15013g());
        }
        return aVar;
    }

    /* renamed from: a */
    public boolean m15019a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConnectionInfo)) {
            return false;
        }
        ConnectionInfo aVar = (ConnectionInfo) obj;
        if (this.f14465b != aVar.f14465b) {
            return false;
        }
        return this.f14464a.equals(aVar.f14464a);
    }

    /* renamed from: e */
    public int m15015e() {
        return (this.f14464a.hashCode() * 31) + this.f14465b;
    }

    /* renamed from: f */
    public String m15014f() {
        return String.format("%s %d", this.f14464a, Integer.valueOf(this.f14465b));
    }
}
