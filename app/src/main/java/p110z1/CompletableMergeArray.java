package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.axz */
/* loaded from: classes3.dex */
public final class CompletableMergeArray extends Completable {

    /* renamed from: a */
    final CompletableSource[] f17742a;

    public CompletableMergeArray(CompletableSource[] arsVarArr) {
        this.f17742a = arsVarArr;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        CompletableSource[] arsVarArr;
        CompositeDisposable atqVar = new CompositeDisposable();
        C3960a aVar = new C3960a(arpVar, new AtomicBoolean(), atqVar, this.f17742a.length + 1);
        arpVar.onSubscribe(atqVar);
        for (CompletableSource arsVar : this.f17742a) {
            if (!atqVar.isDisposed()) {
                if (arsVar == null) {
                    atqVar.dispose();
                    aVar.onError(new NullPointerException("A completable source is null"));
                    return;
                }
                arsVar.mo11309a(aVar);
            } else {
                return;
            }
        }
        aVar.onComplete();
    }

    /* compiled from: CompletableMergeArray.java */
    /* renamed from: z1.axz$a */
    /* loaded from: classes3.dex */
    static final class C3960a extends AtomicInteger implements CompletableObserver {
        private static final long serialVersionUID = -8360547806504310570L;
        final CompletableObserver downstream;
        final AtomicBoolean once;
        final CompositeDisposable set;

        C3960a(CompletableObserver arpVar, AtomicBoolean atomicBoolean, CompositeDisposable atqVar, int i) {
            this.downstream = arpVar;
            this.once = atomicBoolean;
            this.set = atqVar;
            lazySet(i);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.set.mo9939a(atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.set.dispose();
            if (this.once.compareAndSet(false, true)) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (decrementAndGet() == 0 && this.once.compareAndSet(false, true)) {
                this.downstream.onComplete();
            }
        }
    }
}
