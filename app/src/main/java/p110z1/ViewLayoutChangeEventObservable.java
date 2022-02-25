package p110z1;

import android.view.View;

/* renamed from: z1.yp */
/* loaded from: classes3.dex */
final class ViewLayoutChangeEventObservable extends Observable<ViewLayoutChangeEvent> {

    /* renamed from: a */
    private final View f23734a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewLayoutChangeEventObservable(View view) {
        this.f23734a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super ViewLayoutChangeEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnLayoutChangeListenerC5608a aVar = new View$OnLayoutChangeListenerC5608a(this.f23734a, assVar);
            assVar.onSubscribe(aVar);
            this.f23734a.addOnLayoutChangeListener(aVar);
        }
    }

    /* compiled from: ViewLayoutChangeEventObservable.java */
    /* renamed from: z1.yp$a */
    /* loaded from: classes3.dex */
    static final class View$OnLayoutChangeListenerC5608a extends MainThreadDisposable implements View.OnLayoutChangeListener {

        /* renamed from: a */
        private final View f23735a;

        /* renamed from: b */
        private final Observer<? super ViewLayoutChangeEvent> f23736b;

        View$OnLayoutChangeListenerC5608a(View view, Observer<? super ViewLayoutChangeEvent> assVar) {
            this.f23735a = view;
            this.f23736b = assVar;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (!isDisposed()) {
                this.f23736b.onNext(ViewLayoutChangeEvent.m62a(view, i, i2, i3, i4, i5, i6, i7, i8));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23735a.removeOnLayoutChangeListener(this);
        }
    }
}
