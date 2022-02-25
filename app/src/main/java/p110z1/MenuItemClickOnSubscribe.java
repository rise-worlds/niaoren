package p110z1;

import android.view.MenuItem;

/* renamed from: z1.xw */
/* loaded from: classes3.dex */
final class MenuItemClickOnSubscribe extends Observable<Object> {

    /* renamed from: a */
    private final MenuItem f23697a;

    /* renamed from: b */
    private final Predicate<? super MenuItem> f23698b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MenuItemClickOnSubscribe(MenuItem menuItem, Predicate<? super MenuItem> auxVar) {
        this.f23697a = menuItem;
        this.f23698b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        if (C5596xi.m125a(assVar)) {
            MenuItem$OnMenuItemClickListenerC5598a aVar = new MenuItem$OnMenuItemClickListenerC5598a(this.f23697a, this.f23698b, assVar);
            assVar.onSubscribe(aVar);
            this.f23697a.setOnMenuItemClickListener(aVar);
        }
    }

    /* compiled from: MenuItemClickOnSubscribe.java */
    /* renamed from: z1.xw$a */
    /* loaded from: classes3.dex */
    static final class MenuItem$OnMenuItemClickListenerC5598a extends MainThreadDisposable implements MenuItem.OnMenuItemClickListener {

        /* renamed from: a */
        private final MenuItem f23699a;

        /* renamed from: b */
        private final Predicate<? super MenuItem> f23700b;

        /* renamed from: c */
        private final Observer<? super Object> f23701c;

        MenuItem$OnMenuItemClickListenerC5598a(MenuItem menuItem, Predicate<? super MenuItem> auxVar, Observer<? super Object> assVar) {
            this.f23699a = menuItem;
            this.f23700b = auxVar;
            this.f23701c = assVar;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23700b.test(this.f23699a)) {
                    return false;
                }
                this.f23701c.onNext(EnumC5595xh.INSTANCE);
                return true;
            } catch (Exception e) {
                this.f23701c.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23699a.setOnMenuItemClickListener(null);
        }
    }
}
