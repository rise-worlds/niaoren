package p110z1;

import android.view.View;

/* renamed from: z1.yu */
/* loaded from: classes3.dex */
final class ViewSystemUiVisibilityChangeObservable extends Observable<Integer> {

    /* renamed from: a */
    private final View f23748a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewSystemUiVisibilityChangeObservable(View view) {
        this.f23748a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Integer> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnSystemUiVisibilityChangeListenerC5612a aVar = new View$OnSystemUiVisibilityChangeListenerC5612a(this.f23748a, assVar);
            assVar.onSubscribe(aVar);
            this.f23748a.setOnSystemUiVisibilityChangeListener(aVar);
        }
    }

    /* compiled from: ViewSystemUiVisibilityChangeObservable.java */
    /* renamed from: z1.yu$a */
    /* loaded from: classes3.dex */
    static final class View$OnSystemUiVisibilityChangeListenerC5612a extends MainThreadDisposable implements View.OnSystemUiVisibilityChangeListener {

        /* renamed from: a */
        private final View f23749a;

        /* renamed from: b */
        private final Observer<? super Integer> f23750b;

        View$OnSystemUiVisibilityChangeListenerC5612a(View view, Observer<? super Integer> assVar) {
            this.f23749a = view;
            this.f23750b = assVar;
        }

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int i) {
            if (!isDisposed()) {
                this.f23750b.onNext(Integer.valueOf(i));
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23749a.setOnSystemUiVisibilityChangeListener(null);
        }
    }
}
