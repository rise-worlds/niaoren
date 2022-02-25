package p110z1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.ayc */
/* loaded from: classes3.dex */
public final class CompletableMergeIterable extends Completable {

    /* renamed from: a */
    final Iterable<? extends CompletableSource> f17759a;

    public CompletableMergeIterable(Iterable<? extends CompletableSource> iterable) {
        this.f17759a = iterable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        CompositeDisposable atqVar = new CompositeDisposable();
        arpVar.onSubscribe(atqVar);
        try {
            Iterator it = (Iterator) ObjectHelper.m9873a(this.f17759a.iterator(), "The source iterator returned is null");
            AtomicInteger atomicInteger = new AtomicInteger(1);
            C3963a aVar = new C3963a(arpVar, atqVar, atomicInteger);
            while (!atqVar.isDisposed()) {
                try {
                    if (!it.hasNext()) {
                        aVar.onComplete();
                        return;
                    } else if (!atqVar.isDisposed()) {
                        try {
                            CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(it.next(), "The iterator returned a null CompletableSource");
                            if (!atqVar.isDisposed()) {
                                atomicInteger.getAndIncrement();
                                arsVar.mo11309a(aVar);
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            atqVar.dispose();
                            aVar.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    atqVar.dispose();
                    aVar.onError(th2);
                    return;
                }
            }
        } catch (Throwable th3) {
            Exceptions.m9983b(th3);
            arpVar.onError(th3);
        }
    }

    /* compiled from: CompletableMergeIterable.java */
    /* renamed from: z1.ayc$a */
    /* loaded from: classes3.dex */
    static final class C3963a extends AtomicBoolean implements CompletableObserver {
        private static final long serialVersionUID = -7730517613164279224L;
        final CompletableObserver downstream;
        final CompositeDisposable set;
        final AtomicInteger wip;

        C3963a(CompletableObserver arpVar, CompositeDisposable atqVar, AtomicInteger atomicInteger) {
            this.downstream = arpVar;
            this.set = atqVar;
            this.wip = atomicInteger;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.set.mo9939a(atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.set.dispose();
            if (compareAndSet(false, true)) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (this.wip.decrementAndGet() == 0 && compareAndSet(false, true)) {
                this.downstream.onComplete();
            }
        }
    }
}
