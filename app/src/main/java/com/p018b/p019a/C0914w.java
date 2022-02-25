package com.p018b.p019a;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* compiled from: Dns.java */
/* renamed from: com.b.a.w */
/* loaded from: classes.dex */
final class C0914w implements Dns {
    @Override // com.p018b.p019a.Dns
    /* renamed from: a */
    public final List<InetAddress> mo24367a(String str) {
        if (str != null) {
            return Arrays.asList(InetAddress.getAllByName(str));
        }
        throw new UnknownHostException("hostname == null");
    }
}
