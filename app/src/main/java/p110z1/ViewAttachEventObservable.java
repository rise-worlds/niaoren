package p110z1;

import android.view.View;

/* renamed from: z1.yd */
/* loaded from: classes3.dex */
final class ViewAttachEventObservable extends Observable<ViewAttachEvent> {

    /* renamed from: a */
    private final View f23702a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewAttachEventObservable(View view) {
        this.f23702a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super ViewAttachEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnAttachStateChangeListenerC5600a aVar = new View$OnAttachStateChangeListenerC5600a(this.f23702a, assVar);
            assVar.onSubscribe(aVar);
            this.f23702a.addOnAttachStateChangeListener(aVar);
        }
    }

    /* compiled from: ViewAttachEventObservable.java */
    /* renamed from: z1.yd$a */
    /* loaded from: classes3.dex */
    static final class View$OnAttachStateChangeListenerC5600a extends MainThreadDisposable implements View.OnAttachStateChangeListener {

        /* renamed from: a */
        private final View f23703a;

        /* renamed from: b */
        private final Observer<? super ViewAttachEvent> f23704b;

        View$OnAttachStateChangeListenerC5600a(View view, Observer<? super ViewAttachEvent> assVar) {
            this.f23703a = view;
            this.f23704b = assVar;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (!isDisposed()) {
                this.f23704b.onNext(ViewAttachAttachedEvent.m71a(this.f23703a));
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (!isDisposed()) {
                this.f23704b.onNext(ViewAttachDetachedEvent.m70a(this.f23703a));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23703a.removeOnAttachStateChangeListener(this);
        }
    }
}
