package p110z1;

import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Toolbar;

@RequiresApi(21)
/* renamed from: z1.abs */
/* loaded from: classes3.dex */
final class ToolbarNavigationClickObservable extends Observable<Object> {

    /* renamed from: a */
    private final Toolbar f15116a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolbarNavigationClickObservable(Toolbar toolbar) {
        this.f15116a = toolbar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            View$OnClickListenerC3318a aVar = new View$OnClickListenerC3318a(this.f15116a, assVar);
            assVar.onSubscribe(aVar);
            this.f15116a.setNavigationOnClickListener(aVar);
        }
    }

    /* compiled from: ToolbarNavigationClickObservable.java */
    /* renamed from: z1.abs$a */
    /* loaded from: classes3.dex */
    static final class View$OnClickListenerC3318a extends MainThreadDisposable implements View.OnClickListener {

        /* renamed from: a */
        private final Toolbar f15117a;

        /* renamed from: b */
        private final Observer<? super Object> f15118b;

        View$OnClickListenerC3318a(Toolbar toolbar, Observer<? super Object> assVar) {
            this.f15117a = toolbar;
            this.f15118b = assVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!isDisposed()) {
                this.f15118b.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15117a.setNavigationOnClickListener(null);
        }
    }
}
