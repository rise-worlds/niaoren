package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.aya */
/* loaded from: classes3.dex */
public final class CompletableMergeDelayErrorArray extends Completable {

    /* renamed from: a */
    final CompletableSource[] f17753a;

    public CompletableMergeDelayErrorArray(CompletableSource[] arsVarArr) {
        this.f17753a = arsVarArr;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        CompletableSource[] arsVarArr;
        CompositeDisposable atqVar = new CompositeDisposable();
        AtomicInteger atomicInteger = new AtomicInteger(this.f17753a.length + 1);
        AtomicThrowable bsnVar = new AtomicThrowable();
        arpVar.onSubscribe(atqVar);
        for (CompletableSource arsVar : this.f17753a) {
            if (!atqVar.isDisposed()) {
                if (arsVar == null) {
                    bsnVar.addThrowable(new NullPointerException("A completable source is null"));
                    atomicInteger.decrementAndGet();
                } else {
                    arsVar.mo11309a(new C3962a(arpVar, atqVar, bsnVar, atomicInteger));
                }
            } else {
                return;
            }
        }
        if (atomicInteger.decrementAndGet() == 0) {
            Throwable terminate = bsnVar.terminate();
            if (terminate == null) {
                arpVar.onComplete();
            } else {
                arpVar.onError(terminate);
            }
        }
    }

    /* compiled from: CompletableMergeDelayErrorArray.java */
    /* renamed from: z1.aya$a */
    /* loaded from: classes3.dex */
    static final class C3962a implements CompletableObserver {

        /* renamed from: a */
        final CompletableObserver f17754a;

        /* renamed from: b */
        final CompositeDisposable f17755b;

        /* renamed from: c */
        final AtomicThrowable f17756c;

        /* renamed from: d */
        final AtomicInteger f17757d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C3962a(CompletableObserver arpVar, CompositeDisposable atqVar, AtomicThrowable bsnVar, AtomicInteger atomicInteger) {
            this.f17754a = arpVar;
            this.f17755b = atqVar;
            this.f17756c = bsnVar;
            this.f17757d = atomicInteger;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17755b.mo9939a(atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f17756c.addThrowable(th)) {
                m9831a();
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            m9831a();
        }

        /* renamed from: a */
        void m9831a() {
            if (this.f17757d.decrementAndGet() == 0) {
                Throwable terminate = this.f17756c.terminate();
                if (terminate == null) {
                    this.f17754a.onComplete();
                } else {
                    this.f17754a.onError(terminate);
                }
            }
        }
    }
}
