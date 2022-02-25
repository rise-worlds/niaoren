package p110z1;

/* renamed from: z1.axu */
/* loaded from: classes3.dex */
public final class CompletableFromUnsafeSource extends Completable {

    /* renamed from: a */
    final CompletableSource f17732a;

    public CompletableFromUnsafeSource(CompletableSource arsVar) {
        this.f17732a = arsVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17732a.mo11309a(arpVar);
    }
}
