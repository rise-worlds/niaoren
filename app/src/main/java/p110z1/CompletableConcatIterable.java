package p110z1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.axd */
/* loaded from: classes3.dex */
public final class CompletableConcatIterable extends Completable {

    /* renamed from: a */
    final Iterable<? extends CompletableSource> f17695a;

    public CompletableConcatIterable(Iterable<? extends CompletableSource> iterable) {
        this.f17695a = iterable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        try {
            C3947a aVar = new C3947a(arpVar, (Iterator) ObjectHelper.m9873a(this.f17695a.iterator(), "The iterator returned is null"));
            arpVar.onSubscribe(aVar.f17696sd);
            aVar.next();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, arpVar);
        }
    }

    /* compiled from: CompletableConcatIterable.java */
    /* renamed from: z1.axd$a */
    /* loaded from: classes3.dex */
    static final class C3947a extends AtomicInteger implements CompletableObserver {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableObserver downstream;

        /* renamed from: sd */
        final SequentialDisposable f17696sd = new SequentialDisposable();
        final Iterator<? extends CompletableSource> sources;

        C3947a(CompletableObserver arpVar, Iterator<? extends CompletableSource> it) {
            this.downstream = arpVar;
            this.sources = it;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17696sd.replace(atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            next();
        }

        void next() {
            if (!this.f17696sd.isDisposed() && getAndIncrement() == 0) {
                Iterator<? extends CompletableSource> it = this.sources;
                while (!this.f17696sd.isDisposed()) {
                    try {
                        if (!it.hasNext()) {
                            this.downstream.onComplete();
                            return;
                        }
                        try {
                            ((CompletableSource) ObjectHelper.m9873a(it.next(), "The CompletableSource returned is null")).mo11309a(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            this.downstream.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        this.downstream.onError(th2);
                        return;
                    }
                }
            }
        }
    }
}
