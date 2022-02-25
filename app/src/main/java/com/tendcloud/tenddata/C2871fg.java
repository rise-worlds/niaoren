package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fg */
/* loaded from: classes2.dex */
public class C2871fg {

    /* renamed from: a */
    private String f13957a;

    /* renamed from: b */
    private String f13958b;

    /* renamed from: c */
    private byte f13959c;

    /* renamed from: d */
    private byte f13960d;

    /* renamed from: e */
    private byte f13961e;

    public C2871fg() {
        this.f13957a = "";
        this.f13958b = "00:00:00:00:00:00";
        this.f13959c = (byte) -127;
        this.f13960d = (byte) 1;
        this.f13961e = (byte) 1;
    }

    public C2871fg(String str, String str2, byte b, byte b2, byte b3) {
        this.f13957a = str;
        this.f13958b = str2;
        this.f13959c = b;
        this.f13960d = b2;
        this.f13961e = b3;
    }

    /* renamed from: a */
    public String m15738a() {
        return this.f13957a;
    }

    public void setSsid(String str) {
        this.f13957a = str;
    }

    /* renamed from: b */
    public String m15737b() {
        return this.f13958b;
    }

    public void setBssid(String str) {
        this.f13958b = str;
    }

    /* renamed from: c */
    public byte m15736c() {
        return this.f13959c;
    }

    public void setRssi(byte b) {
        this.f13959c = b;
    }

    /* renamed from: d */
    public byte m15735d() {
        return this.f13960d;
    }

    public void setBand(byte b) {
        this.f13960d = b;
    }

    /* renamed from: e */
    public byte m15734e() {
        return this.f13961e;
    }

    public void setChannel(byte b) {
        this.f13961e = b;
    }

    /* renamed from: f */
    public C2871fg m15733f() {
        return new C2871fg(this.f13957a, this.f13958b, this.f13959c, this.f13960d, this.f13961e);
    }
}
