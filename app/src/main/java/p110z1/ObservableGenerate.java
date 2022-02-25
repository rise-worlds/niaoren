package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bkn */
/* loaded from: classes3.dex */
public final class ObservableGenerate<T, S> extends Observable<T> {

    /* renamed from: a */
    final Callable<S> f19146a;

    /* renamed from: b */
    final BiFunction<S, Emitter<T>, S> f19147b;

    /* renamed from: c */
    final Consumer<? super S> f19148c;

    public ObservableGenerate(Callable<S> callable, BiFunction<S, Emitter<T>, S> auiVar, Consumer<? super S> aumVar) {
        this.f19146a = callable;
        this.f19147b = auiVar;
        this.f19148c = aumVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        try {
            C4458a aVar = new C4458a(assVar, this.f19147b, this.f19148c, this.f19146a.call());
            assVar.onSubscribe(aVar);
            aVar.m9625a();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservableGenerate.java */
    /* renamed from: z1.bkn$a */
    /* loaded from: classes3.dex */
    static final class C4458a<T, S> implements Emitter<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19149a;

        /* renamed from: b */
        final BiFunction<S, ? super Emitter<T>, S> f19150b;

        /* renamed from: c */
        final Consumer<? super S> f19151c;

        /* renamed from: d */
        S f19152d;

        /* renamed from: e */
        volatile boolean f19153e;

        /* renamed from: f */
        boolean f19154f;

        /* renamed from: g */
        boolean f19155g;

        C4458a(Observer<? super T> assVar, BiFunction<S, ? super Emitter<T>, S> auiVar, Consumer<? super S> aumVar, S s) {
            this.f19149a = assVar;
            this.f19150b = auiVar;
            this.f19151c = aumVar;
            this.f19152d = s;
        }

        /* renamed from: a */
        public void m9625a() {
            S s = this.f19152d;
            if (this.f19153e) {
                this.f19152d = null;
                m9624a(s);
                return;
            }
            BiFunction<S, ? super Emitter<T>, S> auiVar = this.f19150b;
            while (!this.f19153e) {
                this.f19155g = false;
                try {
                    s = auiVar.apply(s, this);
                    if (this.f19154f) {
                        this.f19153e = true;
                        this.f19152d = null;
                        m9624a(s);
                        return;
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19152d = null;
                    this.f19153e = true;
                    onError(th);
                    m9624a(s);
                    return;
                }
            }
            this.f19152d = null;
            m9624a(s);
        }

        /* renamed from: a */
        private void m9624a(S s) {
            try {
                this.f19151c.accept(s);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19153e = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19153e;
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            if (this.f19154f) {
                return;
            }
            if (this.f19155g) {
                onError(new IllegalStateException("onNext already called in this generate turn"));
            } else if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.f19155g = true;
                this.f19149a.onNext(t);
            }
        }

        @Override // p110z1.Emitter
        public void onError(Throwable th) {
            if (this.f19154f) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.f19154f = true;
            this.f19149a.onError(th);
        }

        @Override // p110z1.Emitter
        public void onComplete() {
            if (!this.f19154f) {
                this.f19154f = true;
                this.f19149a.onComplete();
            }
        }
    }
}
