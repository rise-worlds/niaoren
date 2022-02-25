package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.axc */
/* loaded from: classes3.dex */
public final class CompletableConcatArray extends Completable {

    /* renamed from: a */
    final CompletableSource[] f17693a;

    public CompletableConcatArray(CompletableSource[] arsVarArr) {
        this.f17693a = arsVarArr;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        C3946a aVar = new C3946a(arpVar, this.f17693a);
        arpVar.onSubscribe(aVar.f17694sd);
        aVar.next();
    }

    /* compiled from: CompletableConcatArray.java */
    /* renamed from: z1.axc$a */
    /* loaded from: classes3.dex */
    static final class C3946a extends AtomicInteger implements CompletableObserver {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableObserver downstream;
        int index;

        /* renamed from: sd */
        final SequentialDisposable f17694sd = new SequentialDisposable();
        final CompletableSource[] sources;

        C3946a(CompletableObserver arpVar, CompletableSource[] arsVarArr) {
            this.downstream = arpVar;
            this.sources = arsVarArr;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17694sd.replace(atrVar);
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
            if (!this.f17694sd.isDisposed() && getAndIncrement() == 0) {
                CompletableSource[] arsVarArr = this.sources;
                while (!this.f17694sd.isDisposed()) {
                    int i = this.index;
                    this.index = i + 1;
                    if (i == arsVarArr.length) {
                        this.downstream.onComplete();
                        return;
                    }
                    arsVarArr[i].mo11309a(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
