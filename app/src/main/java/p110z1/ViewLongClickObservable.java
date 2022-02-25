package p110z1;

import android.view.View;
import java.util.concurrent.Callable;

/* renamed from: z1.yr */
/* loaded from: classes3.dex */
final class ViewLongClickObservable extends Observable<Object> {

    /* renamed from: a */
    private final View f23740a;

    /* renamed from: b */
    private final Callable<Boolean> f23741b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewLongClickObservable(View view, Callable<Boolean> callable) {
        this.f23740a = view;
        this.f23741b = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnLongClickListenerC5610a aVar = new View$OnLongClickListenerC5610a(this.f23740a, this.f23741b, assVar);
            assVar.onSubscribe(aVar);
            this.f23740a.setOnLongClickListener(aVar);
        }
    }

    /* compiled from: ViewLongClickObservable.java */
    /* renamed from: z1.yr$a */
    /* loaded from: classes3.dex */
    static final class View$OnLongClickListenerC5610a extends MainThreadDisposable implements View.OnLongClickListener {

        /* renamed from: a */
        private final View f23742a;

        /* renamed from: b */
        private final Observer<? super Object> f23743b;

        /* renamed from: c */
        private final Callable<Boolean> f23744c;

        View$OnLongClickListenerC5610a(View view, Callable<Boolean> callable, Observer<? super Object> assVar) {
            this.f23742a = view;
            this.f23743b = assVar;
            this.f23744c = callable;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23744c.call().booleanValue()) {
                    return false;
                }
                this.f23743b.onNext(EnumC5595xh.INSTANCE);
                return true;
            } catch (Exception e) {
                this.f23743b.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23742a.setOnLongClickListener(null);
        }
    }
}
