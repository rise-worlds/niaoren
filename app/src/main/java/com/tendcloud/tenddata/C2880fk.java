package com.tendcloud.tenddata;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fk */
/* loaded from: classes2.dex */
public class C2880fk {

    /* renamed from: a */
    private int f13976a;

    /* renamed from: b */
    private long f13977b;

    /* renamed from: c */
    private List f13978c;

    /* renamed from: d */
    private Map f13979d;

    /* renamed from: a */
    public int m15720a() {
        return this.f13976a;
    }

    public void setTimestamp(int i) {
        this.f13976a = i;
    }

    /* renamed from: b */
    public long m15718b() {
        return this.f13977b;
    }

    public void setPoiId(long j) {
        this.f13977b = j;
    }

    /* renamed from: c */
    public List m15717c() {
        return this.f13978c;
    }

    public void setBsslist(List list) {
        this.f13978c = list;
    }

    /* renamed from: a */
    public Map m15719a(boolean z) {
        if (this.f13979d == null || z) {
            this.f13979d = new HashMap();
            for (C2871fg fgVar : this.f13978c) {
                this.f13979d.put(fgVar.m15737b(), fgVar);
            }
        }
        return this.f13979d;
    }

    /* renamed from: d */
    public C2880fk m15716d() {
        C2880fk fkVar = new C2880fk();
        fkVar.setTimestamp(this.f13976a);
        fkVar.setPoiId(this.f13977b);
        LinkedList linkedList = new LinkedList();
        for (C2871fg fgVar : this.f13978c) {
            linkedList.add(fgVar.m15733f());
        }
        fkVar.setBsslist(linkedList);
        return fkVar;
    }
}
