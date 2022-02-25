package p110z1;

import android.widget.SearchView;

/* renamed from: z1.aay */
/* loaded from: classes3.dex */
final class SearchViewQueryTextChangeEventsObservable extends InitialValueObservable<SearchViewQueryTextEvent> {

    /* renamed from: a */
    private final SearchView f15077a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchViewQueryTextChangeEventsObservable(SearchView searchView) {
        this.f15077a = searchView;
    }

    @Override // p110z1.InitialValueObservable
    /* renamed from: b */
    protected void mo36b(Observer<? super SearchViewQueryTextEvent> assVar) {
        if (C5596xi.m125a(assVar)) {
            C3306a aVar = new C3306a(this.f15077a, assVar);
            this.f15077a.setOnQueryTextListener(aVar);
            assVar.onSubscribe(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public SearchViewQueryTextEvent mo37a() {
        SearchView searchView = this.f15077a;
        return SearchViewQueryTextEvent.m14458a(searchView, searchView.getQuery(), false);
    }

    /* compiled from: SearchViewQueryTextChangeEventsObservable.java */
    /* renamed from: z1.aay$a */
    /* loaded from: classes3.dex */
    static final class C3306a extends MainThreadDisposable implements SearchView.OnQueryTextListener {

        /* renamed from: a */
        private final SearchView f15078a;

        /* renamed from: b */
        private final Observer<? super SearchViewQueryTextEvent> f15079b;

        C3306a(SearchView searchView, Observer<? super SearchViewQueryTextEvent> assVar) {
            this.f15078a = searchView;
            this.f15079b = assVar;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            if (isDisposed()) {
                return false;
            }
            this.f15079b.onNext(SearchViewQueryTextEvent.m14458a(this.f15078a, str, false));
            return true;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            if (isDisposed()) {
                return false;
            }
            this.f15079b.onNext(SearchViewQueryTextEvent.m14458a(this.f15078a, str, true));
            return true;
        }

        @Override // p110z1.MainThreadDisposable
        /* renamed from: a */
        protected void mo33a() {
            this.f15078a.setOnQueryTextListener(null);
        }
    }
}
