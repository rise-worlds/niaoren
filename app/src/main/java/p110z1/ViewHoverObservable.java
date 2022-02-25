package p110z1;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: z1.ym */
/* loaded from: classes3.dex */
final class ViewHoverObservable extends Observable<MotionEvent> {

    /* renamed from: a */
    private final View f23724a;

    /* renamed from: b */
    private final Predicate<? super MotionEvent> f23725b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewHoverObservable(View view, Predicate<? super MotionEvent> auxVar) {
        this.f23724a = view;
        this.f23725b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super MotionEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnHoverListenerC5606a aVar = new View$OnHoverListenerC5606a(this.f23724a, this.f23725b, assVar);
            assVar.onSubscribe(aVar);
            this.f23724a.setOnHoverListener(aVar);
        }
    }

    /* compiled from: ViewHoverObservable.java */
    /* renamed from: z1.ym$a */
    /* loaded from: classes3.dex */
    static final class View$OnHoverListenerC5606a extends MainThreadDisposable implements View.OnHoverListener {

        /* renamed from: a */
        private final View f23726a;

        /* renamed from: b */
        private final Predicate<? super MotionEvent> f23727b;

        /* renamed from: c */
        private final Observer<? super MotionEvent> f23728c;

        View$OnHoverListenerC5606a(View view, Predicate<? super MotionEvent> auxVar, Observer<? super MotionEvent> assVar) {
            this.f23726a = view;
            this.f23727b = auxVar;
            this.f23728c = assVar;
        }

        @Override // android.view.View.OnHoverListener
        public boolean onHover(View view, MotionEvent motionEvent) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23727b.test(motionEvent)) {
                    return false;
                }
                this.f23728c.onNext(motionEvent);
                return true;
            } catch (Exception e) {
                this.f23728c.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23726a.setOnHoverListener(null);
        }
    }
}
