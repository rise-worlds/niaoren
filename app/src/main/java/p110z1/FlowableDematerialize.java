package p110z1;

/* renamed from: z1.bab */
/* loaded from: classes3.dex */
public final class FlowableDematerialize<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super T, ? extends Notification<R>> f18010c;

    public FlowableDematerialize(Flowable<T> arvVar, Function<? super T, ? extends Notification<R>> aunVar) {
        super(arvVar);
        this.f18010c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4047a(dbxVar, this.f18010c));
    }

    /* compiled from: FlowableDematerialize.java */
    /* renamed from: z1.bab$a */
    /* loaded from: classes3.dex */
    static final class C4047a<T, R> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super R> f18011a;

        /* renamed from: b */
        final Function<? super T, ? extends Notification<R>> f18012b;

        /* renamed from: c */
        boolean f18013c;

        /* renamed from: d */
        dby f18014d;

        C4047a(Subscriber<? super R> dbxVar, Function<? super T, ? extends Notification<R>> aunVar) {
            this.f18011a = dbxVar;
            this.f18012b = aunVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18014d, dbyVar)) {
                this.f18014d = dbyVar;
                this.f18011a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18013c) {
                try {
                    Notification askVar = (Notification) ObjectHelper.m9873a(this.f18012b.apply(t), "The selector returned a null Notification");
                    if (askVar.m10641b()) {
                        this.f18014d.cancel();
                        onError(askVar.m10638e());
                    } else if (askVar.m10644a()) {
                        this.f18014d.cancel();
                        onComplete();
                    } else {
                        this.f18011a.onNext((Object) askVar.m10639d());
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18014d.cancel();
                    onError(th);
                }
            } else if (t instanceof Notification) {
                Notification askVar2 = (Notification) t;
                if (askVar2.m10641b()) {
                    RxJavaPlugins.m9212a(askVar2.m10638e());
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18013c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18013c = true;
            this.f18011a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18013c) {
                this.f18013c = true;
                this.f18011a.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18014d.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18014d.cancel();
        }
    }
}
