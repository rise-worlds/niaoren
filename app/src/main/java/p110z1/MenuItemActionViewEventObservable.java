package p110z1;

import android.view.MenuItem;

/* renamed from: z1.xu */
/* loaded from: classes3.dex */
final class MenuItemActionViewEventObservable extends Observable<MenuItemActionViewEvent> {

    /* renamed from: a */
    private final MenuItem f23692a;

    /* renamed from: b */
    private final Predicate<? super MenuItemActionViewEvent> f23693b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MenuItemActionViewEventObservable(MenuItem menuItem, Predicate<? super MenuItemActionViewEvent> auxVar) {
        this.f23692a = menuItem;
        this.f23693b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super MenuItemActionViewEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            MenuItem$OnActionExpandListenerC5597a aVar = new MenuItem$OnActionExpandListenerC5597a(this.f23692a, this.f23693b, assVar);
            assVar.onSubscribe(aVar);
            this.f23692a.setOnActionExpandListener(aVar);
        }
    }

    /* compiled from: MenuItemActionViewEventObservable.java */
    /* renamed from: z1.xu$a */
    /* loaded from: classes3.dex */
    static final class MenuItem$OnActionExpandListenerC5597a extends MainThreadDisposable implements MenuItem.OnActionExpandListener {

        /* renamed from: a */
        private final MenuItem f23694a;

        /* renamed from: b */
        private final Predicate<? super MenuItemActionViewEvent> f23695b;

        /* renamed from: c */
        private final Observer<? super MenuItemActionViewEvent> f23696c;

        MenuItem$OnActionExpandListenerC5597a(MenuItem menuItem, Predicate<? super MenuItemActionViewEvent> auxVar, Observer<? super MenuItemActionViewEvent> assVar) {
            this.f23694a = menuItem;
            this.f23695b = auxVar;
            this.f23696c = assVar;
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return m120a(MenuItemActionViewExpandEvent.m119a(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return m120a(MenuItemActionViewCollapseEvent.m122a(menuItem));
        }

        /* renamed from: a */
        private boolean m120a(MenuItemActionViewEvent xtVar) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.f23695b.test(xtVar)) {
                    return false;
                }
                this.f23696c.onNext(xtVar);
                return true;
            } catch (Exception e) {
                this.f23696c.onError(e);
                dispose();
                return false;
            }
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f23694a.setOnActionExpandListener(null);
        }
    }
}
