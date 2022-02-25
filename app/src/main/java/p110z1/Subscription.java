package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.czt */
/* loaded from: classes3.dex */
public final class Subscription {

    /* renamed from: a */
    final Object f21200a;

    /* renamed from: b */
    final SubscriberMethod f21201b;

    /* renamed from: c */
    volatile boolean f21202c = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Subscription(Object obj, SubscriberMethod czrVar) {
        this.f21200a = obj;
        this.f21201b = czrVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription cztVar = (Subscription) obj;
        return this.f21200a == cztVar.f21200a && this.f21201b.equals(cztVar.f21201b);
    }

    public int hashCode() {
        return this.f21200a.hashCode() + this.f21201b.f21182f.hashCode();
    }
}
