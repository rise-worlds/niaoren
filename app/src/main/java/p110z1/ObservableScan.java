package p110z1;

/* renamed from: z1.bmd */
/* loaded from: classes3.dex */
public final class ObservableScan<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final BiFunction<T, T, T> f19329b;

    public ObservableScan(ObservableSource<T> asqVar, BiFunction<T, T, T> auiVar) {
        super(asqVar);
        this.f19329b = auiVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4548a(assVar, this.f19329b));
    }

    /* compiled from: ObservableScan.java */
    /* renamed from: z1.bmd$a */
    /* loaded from: classes3.dex */
    static final class C4548a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19330a;

        /* renamed from: b */
        final BiFunction<T, T, T> f19331b;

        /* renamed from: c */
        Disposable f19332c;

        /* renamed from: d */
        T f19333d;

        /* renamed from: e */
        boolean f19334e;

        C4548a(Observer<? super T> assVar, BiFunction<T, T, T> auiVar) {
            this.f19330a = assVar;
            this.f19331b = auiVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19332c, atrVar)) {
                this.f19332c = atrVar;
                this.f19330a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19332c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19332c.isDisposed();
        }

        /* JADX WARN: Type inference failed for: r4v3, types: [T, java.lang.Object] */
        /* JADX WARN: Unknown variable types count: 1 */
        @Override // p110z1.Observer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onNext(T r4) {
            /*
                r3 = this;
                boolean r0 = r3.f19334e
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                z1.ass<? super T> r0 = r3.f19330a
                T r1 = r3.f19333d
                if (r1 != 0) goto L_0x0011
                r3.f19333d = r4
                r0.onNext(r4)
                goto L_0x0022
            L_0x0011:
                z1.aui<T, T, T> r2 = r3.f19331b     // Catch: Throwable -> 0x0023
                java.lang.Object r4 = r2.apply(r1, r4)     // Catch: Throwable -> 0x0023
                java.lang.String r1 = "The value returned by the accumulator is null"
                java.lang.Object r4 = p110z1.ObjectHelper.m9873a(r4, r1)     // Catch: Throwable -> 0x0023
                r3.f19333d = r4
                r0.onNext(r4)
            L_0x0022:
                return
            L_0x0023:
                r4 = move-exception
                p110z1.Exceptions.m9983b(r4)
                z1.atr r0 = r3.f19332c
                r0.dispose()
                r3.onError(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ObservableScan.C4548a.onNext(java.lang.Object):void");
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19334e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19334e = true;
            this.f19330a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19334e) {
                this.f19334e = true;
                this.f19330a.onComplete();
            }
        }
    }
}
