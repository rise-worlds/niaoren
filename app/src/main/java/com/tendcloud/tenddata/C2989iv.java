package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.iv */
/* loaded from: classes2.dex */
public class C2989iv extends AbstractC2984iq {

    /* renamed from: a */
    public static C2988iu f14280a = null;

    /* renamed from: c */
    private static final String f14281c = "type";

    /* renamed from: d */
    private static final String f14282d = "deviceId";

    /* renamed from: e */
    private static final String f14283e = "runtimeConfig";

    /* renamed from: f */
    private static final String f14284f = "hardwareConfig";

    /* renamed from: g */
    private static final String f14285g = "softwareConfig";

    /* renamed from: h */
    private C2990iw f14286h = new C2990iw();

    /* renamed from: i */
    private C2987it f14287i = new C2987it();

    public C2989iv() {
        m15398d();
    }

    /* renamed from: d */
    private void m15398d() {
        m15410a("type", "mobile");
        f14280a = new C2988iu();
        m15410a(f14282d, f14280a.mo15408a_());
        m15410a(f14283e, new C2991ix().mo15408a_());
        m15410a(f14284f, this.f14287i.mo15408a_());
        m15410a(f14285g, this.f14286h.mo15408a_());
    }

    /* renamed from: b */
    public C2990iw m15400b() {
        return this.f14286h;
    }

    /* renamed from: c */
    public C2987it m15399c() {
        return this.f14287i;
    }
}
