package p110z1;

import java.util.ArrayDeque;

/* renamed from: z1.bmt */
/* loaded from: classes3.dex */
public final class ObservableTakeLast<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final int f19418b;

    public ObservableTakeLast(ObservableSource<T> asqVar, int i) {
        super(asqVar);
        this.f19418b = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4568a(assVar, this.f19418b));
    }

    /* compiled from: ObservableTakeLast.java */
    /* renamed from: z1.bmt$a */
    /* loaded from: classes3.dex */
    static final class C4568a<T> extends ArrayDeque<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = 7240042530241604978L;
        volatile boolean cancelled;
        final int count;
        final Observer<? super T> downstream;
        Disposable upstream;

        C4568a(Observer<? super T> assVar, int i) {
            this.downstream = assVar;
            this.count = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            Observer<? super T> assVar = this.downstream;
            while (!this.cancelled) {
                Object obj = (T) poll();
                if (obj != null) {
                    assVar.onNext(obj);
                } else if (!this.cancelled) {
                    assVar.onComplete();
                    return;
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }
    }
}
