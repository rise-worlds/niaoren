package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bip */
/* loaded from: classes3.dex */
public final class ObservableAutoConnect<T> extends Observable<T> {

    /* renamed from: a */
    final ConnectableObservable<? extends T> f18856a;

    /* renamed from: b */
    final int f18857b;

    /* renamed from: c */
    final Consumer<? super Disposable> f18858c;

    /* renamed from: d */
    final AtomicInteger f18859d = new AtomicInteger();

    public ObservableAutoConnect(ConnectableObservable<? extends T> btkVar, int i, Consumer<? super Disposable> aumVar) {
        this.f18856a = btkVar;
        this.f18857b = i;
        this.f18858c = aumVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18856a.subscribe(assVar);
        if (this.f18859d.incrementAndGet() == this.f18857b) {
            this.f18856a.mo9358k(this.f18858c);
        }
    }
}
