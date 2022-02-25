package p110z1;

/* renamed from: z1.bjn */
/* loaded from: classes3.dex */
public final class ObservableDematerialize<T, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends Notification<R>> f19024b;

    public ObservableDematerialize(ObservableSource<T> asqVar, Function<? super T, ? extends Notification<R>> aunVar) {
        super(asqVar);
        this.f19024b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super R> assVar) {
        this.f18807a.subscribe(new C4432a(assVar, this.f19024b));
    }

    /* compiled from: ObservableDematerialize.java */
    /* renamed from: z1.bjn$a */
    /* loaded from: classes3.dex */
    static final class C4432a<T, R> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super R> f19025a;

        /* renamed from: b */
        final Function<? super T, ? extends Notification<R>> f19026b;

        /* renamed from: c */
        boolean f19027c;

        /* renamed from: d */
        Disposable f19028d;

        C4432a(Observer<? super R> assVar, Function<? super T, ? extends Notification<R>> aunVar) {
            this.f19025a = assVar;
            this.f19026b = aunVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19028d, atrVar)) {
                this.f19028d = atrVar;
                this.f19025a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19028d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19028d.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19027c) {
                try {
                    Notification askVar = (Notification) ObjectHelper.m9873a(this.f19026b.apply(t), "The selector returned a null Notification");
                    if (askVar.m10641b()) {
                        this.f19028d.dispose();
                        onError(askVar.m10638e());
                    } else if (askVar.m10644a()) {
                        this.f19028d.dispose();
                        onComplete();
                    } else {
                        this.f19025a.onNext((Object) askVar.m10639d());
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19028d.dispose();
                    onError(th);
                }
            } else if (t instanceof Notification) {
                Notification askVar2 = (Notification) t;
                if (askVar2.m10641b()) {
                    RxJavaPlugins.m9212a(askVar2.m10638e());
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19027c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19027c = true;
            this.f19025a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19027c) {
                this.f19027c = true;
                this.f19025a.onComplete();
            }
        }
    }
}
