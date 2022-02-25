package p110z1;

import android.view.MenuItem;
import android.widget.PopupMenu;

/* renamed from: z1.aae */
/* loaded from: classes3.dex */
final class PopupMenuItemClickObservable extends Observable<MenuItem> {

    /* renamed from: a */
    private final PopupMenu f15064a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PopupMenuItemClickObservable(PopupMenu popupMenu) {
        this.f15064a = popupMenu;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super MenuItem> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3302a aVar = new C3302a(this.f15064a, assVar);
            this.f15064a.setOnMenuItemClickListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* compiled from: PopupMenuItemClickObservable.java */
    /* renamed from: z1.aae$a */
    /* loaded from: classes3.dex */
    static final class C3302a extends MainThreadDisposable implements PopupMenu.OnMenuItemClickListener {

        /* renamed from: a */
        private final PopupMenu f15065a;

        /* renamed from: b */
        private final Observer<? super MenuItem> f15066b;

        C3302a(PopupMenu popupMenu, Observer<? super MenuItem> assVar) {
            this.f15065a = popupMenu;
            this.f15066b = assVar;
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (isDisposed()) {
                return false;
            }
            this.f15066b.onNext(menuItem);
            return true;
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15065a.setOnMenuItemClickListener(null);
        }
    }
}
