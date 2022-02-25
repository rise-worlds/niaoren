package p110z1;

import android.view.View;

/* renamed from: z1.yf */
/* loaded from: classes3.dex */
final class ViewClickObservable extends Observable<Object> {

    /* renamed from: a */
    private final View f23710a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewClickObservable(View view) {
        this.f23710a = view;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnClickListenerC5602a aVar = new View$OnClickListenerC5602a(this.f23710a, assVar);
            assVar.onSubscribe(aVar);
            this.f23710a.setOnClickListener(aVar);
        }
    }

    /* compiled from: ViewClickObservable.java */
    /* renamed from: z1.yf$a */
    /* loaded from: classes3.dex */
    static final class View$OnClickListenerC5602a extends MainThreadDisposable implements View.OnClickListener {

        /* renamed from: a */
        private final View f23711a;

        /* renamed from: b */
        private final Observer<? super Object> f23712b;

        View$OnClickListenerC5602a(View view, Observer<? super Object> assVar) {
            this.f23711a = view;
            this.f23712b = assVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!isDisposed()) {
                this.f23712b.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23711a.setOnClickListener(null);
        }
    }
}
