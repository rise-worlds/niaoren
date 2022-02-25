package p110z1;

import android.view.KeyEvent;
import android.view.View;

/* renamed from: z1.yn */
/* loaded from: classes3.dex */
final class ViewKeyObservable extends Observable<KeyEvent> {

    /* renamed from: a */
    private final View f23729a;

    /* renamed from: b */
    private final Predicate<? super KeyEvent> f23730b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewKeyObservable(View view, Predicate<? super KeyEvent> auxVar) {
        this.f23729a = view;
        this.f23730b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super KeyEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnKeyListenerC5607a aVar = new View$OnKeyListenerC5607a(this.f23729a, this.f23730b, assVar);
            assVar.onSubscribe(aVar);
            this.f23729a.setOnKeyListener(aVar);
        }
    }

    /* compiled from: ViewKeyObservable.java */
    /* renamed from: z1.yn$a */
    /* loaded from: classes3.dex */
    static final class View$OnKeyListenerC5607a extends MainThreadDisposable implements View.OnKeyListener {

        /* renamed from: a */
        private final View f23731a;

        /* renamed from: b */
        private final Predicate<? super KeyEvent> f23732b;

        /* renamed from: c */
        private final Observer<? super KeyEvent> f23733c;

        View$OnKeyListenerC5607a(View view, Predicate<? super KeyEvent> auxVar, Observer<? super KeyEvent> assVar) {
            this.f23731a = view;
            this.f23732b = auxVar;
            this.f23733c = assVar;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23732b.test(keyEvent)) {
                    return false;
                }
                this.f23733c.onNext(keyEvent);
                return true;
            } catch (Exception e) {
                this.f23733c.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23731a.setOnKeyListener(null);
        }
    }
}
