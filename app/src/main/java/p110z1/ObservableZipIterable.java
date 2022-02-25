package p110z1;

import java.util.Iterator;

/* renamed from: z1.bnr */
/* loaded from: classes3.dex */
public final class ObservableZipIterable<T, U, V> extends Observable<V> {

    /* renamed from: a */
    final Observable<? extends T> f19572a;

    /* renamed from: b */
    final Iterable<U> f19573b;

    /* renamed from: c */
    final BiFunction<? super T, ? super U, ? extends V> f19574c;

    public ObservableZipIterable(Observable<? extends T> aslVar, Iterable<U> iterable, BiFunction<? super T, ? super U, ? extends V> auiVar) {
        this.f19572a = aslVar;
        this.f19573b = iterable;
        this.f19574c = auiVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super V> assVar) {
        try {
            Iterator it = (Iterator) ObjectHelper.m9873a(this.f19573b.iterator(), "The iterator returned by other is null");
            try {
                if (!it.hasNext()) {
                    EmptyDisposable.complete(assVar);
                } else {
                    this.f19572a.subscribe(new C4617a(assVar, it, this.f19574c));
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptyDisposable.error(th, assVar);
            }
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            EmptyDisposable.error(th2, assVar);
        }
    }

    /* compiled from: ObservableZipIterable.java */
    /* renamed from: z1.bnr$a */
    /* loaded from: classes3.dex */
    static final class C4617a<T, U, V> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super V> f19575a;

        /* renamed from: b */
        final Iterator<U> f19576b;

        /* renamed from: c */
        final BiFunction<? super T, ? super U, ? extends V> f19577c;

        /* renamed from: d */
        Disposable f19578d;

        /* renamed from: e */
        boolean f19579e;

        C4617a(Observer<? super V> assVar, Iterator<U> it, BiFunction<? super T, ? super U, ? extends V> auiVar) {
            this.f19575a = assVar;
            this.f19576b = it;
            this.f19577c = auiVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19578d, atrVar)) {
                this.f19578d = atrVar;
                this.f19575a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19578d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19578d.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19579e) {
                try {
                    try {
                        this.f19575a.onNext(ObjectHelper.m9873a(this.f19577c.apply(t, ObjectHelper.m9873a(this.f19576b.next(), "The iterator returned a null value")), "The zipper function returned a null value"));
                        try {
                            if (!this.f19576b.hasNext()) {
                                this.f19579e = true;
                                this.f19578d.dispose();
                                this.f19575a.onComplete();
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            m9556a(th);
                        }
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        m9556a(th2);
                    }
                } catch (Throwable th3) {
                    Exceptions.m9983b(th3);
                    m9556a(th3);
                }
            }
        }

        /* renamed from: a */
        void m9556a(Throwable th) {
            this.f19579e = true;
            this.f19578d.dispose();
            this.f19575a.onError(th);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19579e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19579e = true;
            this.f19575a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19579e) {
                this.f19579e = true;
                this.f19575a.onComplete();
            }
        }
    }
}
