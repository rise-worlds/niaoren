package com.tendcloud.tenddata;

import org.slf4j.Marker;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.cz */
/* loaded from: classes2.dex */
public class C2789cz extends C2793dc implements AbstractC2787cx {

    /* renamed from: a */
    private String f13714a = Marker.ANY_MARKER;

    @Override // com.tendcloud.tenddata.AbstractC2787cx
    public void setResourceDescriptor(String str) {
        if (str != null) {
            this.f13714a = str;
            return;
        }
        throw new IllegalArgumentException("http resource descriptor must not be null");
    }

    @Override // com.tendcloud.tenddata.AbstractC2786cw
    /* renamed from: a */
    public String mo16134a() {
        return this.f13714a;
    }
}
