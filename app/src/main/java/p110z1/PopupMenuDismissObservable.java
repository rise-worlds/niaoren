package p110z1;

import android.widget.PopupMenu;

/* renamed from: z1.aad */
/* loaded from: classes3.dex */
final class PopupMenuDismissObservable extends Observable<Object> {

    /* renamed from: a */
    private final PopupMenu f15061a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PopupMenuDismissObservable(PopupMenu popupMenu) {
        this.f15061a = popupMenu;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3301a aVar = new C3301a(this.f15061a, assVar);
            this.f15061a.setOnDismissListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* compiled from: PopupMenuDismissObservable.java */
    /* renamed from: z1.aad$a */
    /* loaded from: classes3.dex */
    static final class C3301a extends MainThreadDisposable implements PopupMenu.OnDismissListener {

        /* renamed from: a */
        private final PopupMenu f15062a;

        /* renamed from: b */
        private final Observer<? super Object> f15063b;

        C3301a(PopupMenu popupMenu, Observer<? super Object> assVar) {
            this.f15062a = popupMenu;
            this.f15063b = assVar;
        }

        @Override // android.widget.PopupMenu.OnDismissListener
        public void onDismiss(PopupMenu popupMenu) {
            if (!isDisposed()) {
                this.f15063b.onNext(EnumC5595xh.INSTANCE);
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15062a.setOnDismissListener(null);
        }
    }
}
