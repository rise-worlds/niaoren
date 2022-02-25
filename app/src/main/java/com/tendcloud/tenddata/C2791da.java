package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.da */
/* loaded from: classes2.dex */
public class C2791da extends C2793dc implements AbstractC2795de {

    /* renamed from: a */
    private short f13715a;

    /* renamed from: b */
    private String f13716b;

    @Override // com.tendcloud.tenddata.AbstractC2794dd
    /* renamed from: a */
    public String mo16128a() {
        return this.f13716b;
    }

    @Override // com.tendcloud.tenddata.AbstractC2794dd
    /* renamed from: b */
    public short mo16127b() {
        return this.f13715a;
    }

    @Override // com.tendcloud.tenddata.AbstractC2795de
    public void setHttpStatusMessage(String str) {
        this.f13716b = str;
    }

    @Override // com.tendcloud.tenddata.AbstractC2795de
    public void setHttpStatus(short s) {
        this.f13715a = s;
    }
}
