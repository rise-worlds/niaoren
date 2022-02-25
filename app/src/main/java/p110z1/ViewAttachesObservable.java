package p110z1;

import android.view.View;

/* renamed from: z1.ye */
/* loaded from: classes3.dex */
final class ViewAttachesObservable extends Observable<Object> {

    /* renamed from: a */
    private final boolean f23705a;

    /* renamed from: b */
    private final View f23706b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewAttachesObservable(View view, boolean z) {
        this.f23706b = view;
        this.f23705a = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnAttachStateChangeListenerC5601a aVar = new View$OnAttachStateChangeListenerC5601a(this.f23706b, this.f23705a, assVar);
            assVar.onSubscribe(aVar);
            this.f23706b.addOnAttachStateChangeListener(aVar);
        }
    }

    /* compiled from: ViewAttachesObservable.java */
    /* renamed from: z1.ye$a */
    /* loaded from: classes3.dex */
    static final class View$OnAttachStateChangeListenerC5601a extends MainThreadDisposable implements View.OnAttachStateChangeListener {

        /* renamed from: a */
        private final View f23707a;

        /* renamed from: b */
        private final boolean f23708b;

        /* renamed from: c */
        private final Observer<? super Object> f23709c;

        View$OnAttachStateChangeListenerC5601a(View view, boolean z, Observer<? super Object> assVar) {
            this.f23707a = view;
            this.f23708b = z;
            this.f23709c = assVar;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (this.f23708b && !isDisposed()) {
                this.f23709c.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (!this.f23708b && !isDisposed()) {
                this.f23709c.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23707a.removeOnAttachStateChangeListener(this);
        }
    }
}
