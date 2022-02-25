package p110z1;

import android.support.annotation.RequiresApi;
import android.view.MenuItem;
import android.widget.Toolbar;

@RequiresApi(21)
/* renamed from: z1.abr */
/* loaded from: classes3.dex */
final class ToolbarItemClickObservable extends Observable<MenuItem> {

    /* renamed from: a */
    private final Toolbar f15113a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolbarItemClickObservable(Toolbar toolbar) {
        this.f15113a = toolbar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super MenuItem> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3317a aVar = new C3317a(this.f15113a, assVar);
            assVar.onSubscribe(aVar);
            this.f15113a.setOnMenuItemClickListener(aVar);
        }
    }

    /* compiled from: ToolbarItemClickObservable.java */
    /* renamed from: z1.abr$a */
    /* loaded from: classes3.dex */
    static final class C3317a extends MainThreadDisposable implements Toolbar.OnMenuItemClickListener {

        /* renamed from: a */
        private final Toolbar f15114a;

        /* renamed from: b */
        private final Observer<? super MenuItem> f15115b;

        C3317a(Toolbar toolbar, Observer<? super MenuItem> assVar) {
            this.f15114a = toolbar;
            this.f15115b = assVar;
        }

        @Override // android.widget.Toolbar.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (isDisposed()) {
                return false;
            }
            this.f15115b.onNext(menuItem);
            return true;
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15114a.setOnMenuItemClickListener(null);
        }
    }
}
