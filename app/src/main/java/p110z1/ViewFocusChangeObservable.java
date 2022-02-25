package p110z1;

import android.view.View;

/* renamed from: z1.yh */
/* loaded from: classes3.dex */
final class ViewFocusChangeObservable extends InitialValueObservable<Boolean> {

    /* renamed from: a */
    private final View f23718a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewFocusChangeObservable(View view) {
        this.f23718a = view;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super Boolean> assVar) {
        View$OnFocusChangeListenerC5604a aVar = new View$OnFocusChangeListenerC5604a(this.f23718a, assVar);
        assVar.onSubscribe(aVar);
        this.f23718a.setOnFocusChangeListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public Boolean mo37a() {
        return Boolean.valueOf(this.f23718a.hasFocus());
    }

    /* compiled from: ViewFocusChangeObservable.java */
    /* renamed from: z1.yh$a */
    /* loaded from: classes3.dex */
    static final class View$OnFocusChangeListenerC5604a extends MainThreadDisposable implements View.OnFocusChangeListener {

        /* renamed from: a */
        private final View f23719a;

        /* renamed from: b */
        private final Observer<? super Boolean> f23720b;

        View$OnFocusChangeListenerC5604a(View view, Observer<? super Boolean> assVar) {
            this.f23719a = view;
            this.f23720b = assVar;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (!isDisposed()) {
                this.f23720b.onNext(Boolean.valueOf(z));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23719a.setOnFocusChangeListener(null);
        }
    }
}
