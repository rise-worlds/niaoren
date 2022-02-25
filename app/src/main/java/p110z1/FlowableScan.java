package p110z1;

/* renamed from: z1.bda */
/* loaded from: classes3.dex */
public final class FlowableScan<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final BiFunction<T, T, T> f18313c;

    public FlowableScan(Flowable<T> arvVar, BiFunction<T, T, T> auiVar) {
        super(arvVar);
        this.f18313c = auiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4188a(dbxVar, this.f18313c));
    }

    /* compiled from: FlowableScan.java */
    /* renamed from: z1.bda$a */
    /* loaded from: classes3.dex */
    static final class C4188a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18314a;

        /* renamed from: b */
        final BiFunction<T, T, T> f18315b;

        /* renamed from: c */
        dby f18316c;

        /* renamed from: d */
        T f18317d;

        /* renamed from: e */
        boolean f18318e;

        C4188a(Subscriber<? super T> dbxVar, BiFunction<T, T, T> auiVar) {
            this.f18314a = dbxVar;
            this.f18315b = auiVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18316c, dbyVar)) {
                this.f18316c = dbyVar;
                this.f18314a.onSubscribe(this);
            }
        }

        /* JADX WARN: Type inference failed for: r4v3, types: [T, java.lang.Object] */
        /* JADX WARN: Unknown variable types count: 1 */
        @Override // p110z1.Subscriber
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onNext(T r4) {
            /*
                r3 = this;
                boolean r0 = r3.f18318e
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                z1.dbx<? super T> r0 = r3.f18314a
                T r1 = r3.f18317d
                if (r1 != 0) goto L_0x0011
                r3.f18317d = r4
                r0.onNext(r4)
                goto L_0x0022
            L_0x0011:
                z1.aui<T, T, T> r2 = r3.f18315b     // Catch: Throwable -> 0x0023
                java.lang.Object r4 = r2.apply(r1, r4)     // Catch: Throwable -> 0x0023
                java.lang.String r1 = "The value returned by the accumulator is null"
                java.lang.Object r4 = p110z1.ObjectHelper.m9873a(r4, r1)     // Catch: Throwable -> 0x0023
                r3.f18317d = r4
                r0.onNext(r4)
            L_0x0022:
                return
            L_0x0023:
                r4 = move-exception
                p110z1.Exceptions.m9983b(r4)
                z1.dby r0 = r3.f18316c
                r0.cancel()
                r3.onError(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.FlowableScan.C4188a.onNext(java.lang.Object):void");
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18318e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18318e = true;
            this.f18314a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18318e) {
                this.f18318e = true;
                this.f18314a.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18316c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18316c.cancel();
        }
    }
}
