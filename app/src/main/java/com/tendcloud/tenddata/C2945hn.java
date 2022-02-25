package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hn */
/* loaded from: classes2.dex */
public class C2945hn {

    /* renamed from: a */
    public AbstractC2790d f14178a = null;

    /* renamed from: b */
    public EnumC2946a f14179b = EnumC2946a.IMMEDIATELY;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.hn$a */
    /* loaded from: classes2.dex */
    public enum EnumC2946a {
        IMMEDIATELY(0),
        HIGH(1);
        
        private final int indexNum;

        EnumC2946a(int i) {
            this.indexNum = i;
        }

        public int index() {
            return this.indexNum;
        }
    }
}
