package com.tendcloud.tenddata;

import java.io.PrintStream;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ga */
/* loaded from: classes2.dex */
public class C2900ga {

    /* renamed from: a */
    private final double f14046a;

    /* renamed from: b */
    private final double f14047b;

    public C2900ga(double d, double d2) {
        this.f14046a = d;
        this.f14047b = d2;
    }

    public String toString() {
        double d = this.f14047b;
        if (d == 0.0d) {
            return this.f14046a + "";
        } else if (this.f14046a == 0.0d) {
            return this.f14047b + "i";
        } else if (d < 0.0d) {
            return this.f14046a + " - " + (-this.f14047b) + "i";
        } else {
            return this.f14046a + " + " + this.f14047b + "i";
        }
    }

    /* renamed from: a */
    public double m15655a() {
        return Math.hypot(this.f14046a, this.f14047b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C2900ga m15653a(C2900ga gaVar) {
        return new C2900ga(this.f14046a + gaVar.f14046a, this.f14047b + gaVar.f14047b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public C2900ga m15650b(C2900ga gaVar) {
        return new C2900ga(this.f14046a - gaVar.f14046a, this.f14047b - gaVar.f14047b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public C2900ga m15648c(C2900ga gaVar) {
        double d = this.f14046a;
        double d2 = gaVar.f14046a;
        double d3 = this.f14047b;
        double d4 = gaVar.f14047b;
        return new C2900ga((d * d2) - (d3 * d4), (d * d4) + (d3 * d2));
    }

    /* renamed from: a */
    C2900ga m15654a(double d) {
        return new C2900ga(this.f14046a * d, d * this.f14047b);
    }

    /* renamed from: b */
    C2900ga m15651b() {
        return new C2900ga(this.f14046a, -this.f14047b);
    }

    /* renamed from: c */
    private C2900ga m15649c() {
        double d = this.f14046a;
        double d2 = this.f14047b;
        double d3 = (d * d) + (d2 * d2);
        return new C2900ga(d / d3, (-d2) / d3);
    }

    /* renamed from: d */
    private double m15647d() {
        return this.f14046a;
    }

    /* renamed from: e */
    private double m15645e() {
        return this.f14047b;
    }

    /* renamed from: d */
    private C2900ga m15646d(C2900ga gaVar) {
        return m15648c(gaVar.m15649c());
    }

    /* renamed from: f */
    private C2900ga m15644f() {
        return new C2900ga(Math.sin(this.f14046a) * Math.cosh(this.f14047b), Math.cos(this.f14046a) * Math.sinh(this.f14047b));
    }

    /* renamed from: g */
    private C2900ga m15643g() {
        return new C2900ga(Math.cos(this.f14046a) * Math.cosh(this.f14047b), (-Math.sin(this.f14046a)) * Math.sinh(this.f14047b));
    }

    /* renamed from: h */
    private C2900ga m15642h() {
        return m15644f().m15646d(m15643g());
    }

    /* renamed from: a */
    static void m15652a(String[] strArr) {
        C2900ga gaVar = new C2900ga(5.0d, 6.0d);
        C2900ga gaVar2 = new C2900ga(-3.0d, 4.0d);
        PrintStream printStream = System.out;
        printStream.println("a            = " + gaVar);
        PrintStream printStream2 = System.out;
        printStream2.println("b            = " + gaVar2);
        PrintStream printStream3 = System.out;
        printStream3.println("Re(a)        = " + gaVar.m15647d());
        PrintStream printStream4 = System.out;
        printStream4.println("Im(a)        = " + gaVar.m15645e());
        PrintStream printStream5 = System.out;
        printStream5.println("b + a        = " + gaVar2.m15653a(gaVar));
        PrintStream printStream6 = System.out;
        printStream6.println("a - b        = " + gaVar.m15650b(gaVar2));
        PrintStream printStream7 = System.out;
        printStream7.println("a * b        = " + gaVar.m15648c(gaVar2));
        PrintStream printStream8 = System.out;
        printStream8.println("b * a        = " + gaVar2.m15648c(gaVar));
        PrintStream printStream9 = System.out;
        printStream9.println("a / b        = " + gaVar.m15646d(gaVar2));
        PrintStream printStream10 = System.out;
        printStream10.println("(a / b) * b  = " + gaVar.m15646d(gaVar2).m15648c(gaVar2));
        PrintStream printStream11 = System.out;
        printStream11.println("conj(a)      = " + gaVar.m15651b());
        PrintStream printStream12 = System.out;
        printStream12.println("|a|          = " + gaVar.m15655a());
        PrintStream printStream13 = System.out;
        printStream13.println("tan(a)       = " + gaVar.m15642h());
    }
}
