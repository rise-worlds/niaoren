package p110z1;

/* renamed from: z1.bbu */
/* loaded from: classes3.dex */
public final class FlowableMaterialize<T> extends AbstractFlowableWithUpstream<T, Notification<T>> {
    public FlowableMaterialize(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Notification<T>> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4120a(dbxVar));
    }

    /* compiled from: FlowableMaterialize.java */
    /* renamed from: z1.bbu$a */
    /* loaded from: classes3.dex */
    static final class C4120a<T> extends SinglePostCompleteSubscriber<T, Notification<T>> {
        private static final long serialVersionUID = -3740826063558713822L;

        @Override // p110z1.SinglePostCompleteSubscriber
        protected /* bridge */ /* synthetic */ void onDrop(Object obj) {
            onDrop((Notification) ((Notification) obj));
        }

        C4120a(Subscriber<? super Notification<T>> dbxVar) {
            super(dbxVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(Notification.m10643a(t));
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            complete(Notification.m10642a(th));
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            complete(Notification.m10637f());
        }

        protected void onDrop(Notification<T> askVar) {
            if (askVar.m10641b()) {
                RxJavaPlugins.m9212a(askVar.m10638e());
            }
        }
    }
}
