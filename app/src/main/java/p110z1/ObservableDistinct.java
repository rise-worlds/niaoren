package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.bjp */
/* loaded from: classes3.dex */
public final class ObservableDistinct<T, K> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super T, K> f19031b;

    /* renamed from: c */
    final Callable<? extends Collection<? super K>> f19032c;

    public ObservableDistinct(ObservableSource<T> asqVar, Function<? super T, K> aunVar, Callable<? extends Collection<? super K>> callable) {
        super(asqVar);
        this.f19031b = aunVar;
        this.f19032c = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        try {
            this.f18807a.subscribe(new C4434a(assVar, this.f19031b, (Collection) ObjectHelper.m9873a(this.f19032c.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservableDistinct.java */
    /* renamed from: z1.bjp$a */
    /* loaded from: classes3.dex */
    static final class C4434a<T, K> extends BasicFuseableObserver<T, T> {

        /* renamed from: k */
        final Collection<? super K> f19033k;

        /* renamed from: l */
        final Function<? super T, K> f19034l;

        C4434a(Observer<? super T> assVar, Function<? super T, K> aunVar, Collection<? super K> collection) {
            super(assVar);
            this.f19034l = aunVar;
            this.f19033k = collection;
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f17605i) {
                if (this.f17606j == 0) {
                    try {
                        if (this.f19033k.add(ObjectHelper.m9873a(this.f19034l.apply(t), "The keySelector returned a null key"))) {
                            this.f17602f.onNext(t);
                        }
                    } catch (Throwable th) {
                        m9868a(th);
                    }
                } else {
                    this.f17602f.onNext(null);
                }
            }
        }

        @Override // p110z1.BasicFuseableObserver, p110z1.Observer
        public void onError(Throwable th) {
            if (this.f17605i) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17605i = true;
            this.f19033k.clear();
            this.f17602f.onError(th);
        }

        @Override // p110z1.BasicFuseableObserver, p110z1.Observer
        public void onComplete() {
            if (!this.f17605i) {
                this.f17605i = true;
                this.f19033k.clear();
                this.f17602f.onComplete();
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9869a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll;
            do {
                poll = this.f17604h.poll();
                if (poll == null) {
                    break;
                }
            } while (!this.f19033k.add((Object) ObjectHelper.m9873a(this.f19034l.apply(poll), "The keySelector returned a null key")));
            return poll;
        }

        @Override // p110z1.BasicFuseableObserver, p110z1.SimpleQueue
        public void clear() {
            this.f19033k.clear();
            super.clear();
        }
    }
}
