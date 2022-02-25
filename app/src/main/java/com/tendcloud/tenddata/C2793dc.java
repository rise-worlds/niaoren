package com.tendcloud.tenddata;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dc */
/* loaded from: classes2.dex */
public class C2793dc implements AbstractC2788cy {

    /* renamed from: a */
    private byte[] f13717a;

    /* renamed from: b */
    private TreeMap f13718b = new TreeMap(String.CASE_INSENSITIVE_ORDER);

    @Override // com.tendcloud.tenddata.AbstractC2792db
    /* renamed from: c */
    public Iterator mo16130c() {
        return Collections.unmodifiableSet(this.f13718b.keySet()).iterator();
    }

    @Override // com.tendcloud.tenddata.AbstractC2792db
    /* renamed from: a */
    public String mo16133a(String str) {
        String str2 = (String) this.f13718b.get(str);
        return str2 == null ? "" : str2;
    }

    @Override // com.tendcloud.tenddata.AbstractC2792db
    /* renamed from: d */
    public byte[] mo16129d() {
        return this.f13717a;
    }

    @Override // com.tendcloud.tenddata.AbstractC2788cy
    public void setContent(byte[] bArr) {
        this.f13717a = bArr;
    }

    @Override // com.tendcloud.tenddata.AbstractC2788cy
    /* renamed from: a */
    public void mo16132a(String str, String str2) {
        this.f13718b.put(str, str2);
    }

    @Override // com.tendcloud.tenddata.AbstractC2792db
    /* renamed from: b */
    public boolean mo16131b(String str) {
        return this.f13718b.containsKey(str);
    }
}
