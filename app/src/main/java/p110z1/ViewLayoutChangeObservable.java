package p110z1;

import android.view.View;

/* renamed from: z1.yq */
/* loaded from: classes3.dex */
final class ViewLayoutChangeObservable extends Observable<Object> {

    /* renamed from: a */
    private final View f23737a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewLayoutChangeObservable(View view) {
        this.f23737a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnLayoutChangeListenerC5609a aVar = new View$OnLayoutChangeListenerC5609a(this.f23737a, assVar);
            assVar.onSubscribe(aVar);
            this.f23737a.addOnLayoutChangeListener(aVar);
        }
    }

    /* compiled from: ViewLayoutChangeObservable.java */
    /* renamed from: z1.yq$a */
    /* loaded from: classes3.dex */
    static final class View$OnLayoutChangeListenerC5609a extends MainThreadDisposable implements View.OnLayoutChangeListener {

        /* renamed from: a */
        private final View f23738a;

        /* renamed from: b */
        private final Observer<? super Object> f23739b;

        View$OnLayoutChangeListenerC5609a(View view, Observer<? super Object> assVar) {
            this.f23738a = view;
            this.f23739b = assVar;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (!isDisposed()) {
                this.f23739b.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23738a.removeOnLayoutChangeListener(this);
        }
    }
}
