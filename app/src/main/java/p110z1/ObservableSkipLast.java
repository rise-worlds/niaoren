package p110z1;

import java.util.ArrayDeque;

/* renamed from: z1.bml */
/* loaded from: classes3.dex */
public final class ObservableSkipLast<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final int f19380b;

    public ObservableSkipLast(ObservableSource<T> asqVar, int i) {
        super(asqVar);
        this.f19380b = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4557a(assVar, this.f19380b));
    }

    /* compiled from: ObservableSkipLast.java */
    /* renamed from: z1.bml$a */
    /* loaded from: classes3.dex */
    static final class C4557a<T> extends ArrayDeque<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3807491841935125653L;
        final Observer<? super T> downstream;
        final int skip;
        Disposable upstream;

        C4557a(Observer<? super T> assVar, int i) {
            super(i);
            this.downstream = assVar;
            this.skip = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.skip == size()) {
                this.downstream.onNext((T) poll());
            }
            offer(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
