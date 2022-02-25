package com.goldcoast.sdk.p050a;

import org.json.JSONException;
import org.json.JSONObject;
import p110z1.CheckPhoneInfoTools;

/* renamed from: com.goldcoast.sdk.a.b */
/* loaded from: classes.dex */
public final class Device extends Entity<Device> {

    /* renamed from: a */
    private String f8941a;

    /* renamed from: b */
    private String f8942b;

    /* renamed from: c */
    private CPUInfo f8943c;

    /* renamed from: d */
    private Phone f8944d;

    /* renamed from: e */
    private String f8945e;

    /* renamed from: f */
    private String f8946f;

    /* renamed from: g */
    private String f8947g;

    /* renamed from: h */
    private PropInfo f8948h;

    /* renamed from: i */
    private String f8949i;

    /* renamed from: j */
    private String f8950j;

    /* renamed from: a */
    public final JSONObject m20389a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cpuinfo", this.f8943c.m20391a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jSONObject.put("prop", this.f8944d.m20369a());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            jSONObject.put("propInfo", this.f8948h.m20364b());
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        try {
            jSONObject.put(CheckPhoneInfoTools.f15426b, this.f8941a);
            jSONObject.put("linuxversion", this.f8942b);
            jSONObject.put("imei", this.f8945e);
            jSONObject.put("macAddr", this.f8946f);
            jSONObject.put("androidId", this.f8947g);
            jSONObject.put("currentTime", this.f8949i);
            jSONObject.put("cpuType", this.f8950j);
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: a */
    public final void m20385a(String str) {
        this.f8941a = str;
    }

    /* renamed from: b */
    public final void m20383b(String str) {
        this.f8942b = str;
    }

    /* renamed from: a */
    public final void m20388a(CPUInfo aVar) {
        this.f8943c = aVar;
    }

    /* renamed from: b */
    public final Phone m20384b() {
        return this.f8944d;
    }

    /* renamed from: a */
    public final void m20387a(Phone eVar) {
        this.f8944d = eVar;
    }

    /* renamed from: c */
    public final void m20382c(String str) {
        this.f8945e = str;
    }

    /* renamed from: d */
    public final void m20381d(String str) {
        this.f8946f = str;
    }

    /* renamed from: e */
    public final void m20380e(String str) {
        this.f8947g = str;
    }

    /* renamed from: a */
    public final void m20386a(PropInfo fVar) {
        this.f8948h = fVar;
    }

    /* renamed from: f */
    public final void m20379f(String str) {
        this.f8949i = str;
    }

    /* renamed from: g */
    public final void m20378g(String str) {
        this.f8950j = str;
    }
}
