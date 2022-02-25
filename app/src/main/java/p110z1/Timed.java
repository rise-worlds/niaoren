package p110z1;

import java.util.concurrent.TimeUnit;

/* renamed from: z1.buq */
/* loaded from: classes3.dex */
public final class Timed<T> {

    /* renamed from: a */
    final T f20264a;

    /* renamed from: b */
    final long f20265b;

    /* renamed from: c */
    final TimeUnit f20266c;

    public Timed(@AbstractC3889atm T t, long j, @AbstractC3889atm TimeUnit timeUnit) {
        this.f20264a = t;
        this.f20265b = j;
        this.f20266c = (TimeUnit) ObjectHelper.m9873a(timeUnit, "unit is null");
    }

    @AbstractC3889atm
    /* renamed from: a */
    public T m9027a() {
        return this.f20264a;
    }

    @AbstractC3889atm
    /* renamed from: b */
    public TimeUnit m9025b() {
        return this.f20266c;
    }

    /* renamed from: c */
    public long m9024c() {
        return this.f20265b;
    }

    /* renamed from: a */
    public long m9026a(@AbstractC3889atm TimeUnit timeUnit) {
        return timeUnit.convert(this.f20265b, this.f20266c);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Timed)) {
            return false;
        }
        Timed buqVar = (Timed) obj;
        return ObjectHelper.m9874a(this.f20264a, buqVar.f20264a) && this.f20265b == buqVar.f20265b && ObjectHelper.m9874a(this.f20266c, buqVar.f20266c);
    }

    public int hashCode() {
        T t = this.f20264a;
        int hashCode = t != null ? t.hashCode() : 0;
        long j = this.f20265b;
        return (((hashCode * 31) + ((int) (j ^ (j >>> 31)))) * 31) + this.f20266c.hashCode();
    }

    public String toString() {
        return "Timed[time=" + this.f20265b + ", unit=" + this.f20266c + ", value=" + this.f20264a + "]";
    }
}
