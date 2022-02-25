package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SearchView;

/* renamed from: z1.aat */
/* loaded from: classes3.dex */
public final class RxSearchView {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static InitialValueObservable<SearchViewQueryTextEvent> m14492a(@NonNull SearchView searchView) {
        C5596xi.m126a(searchView, "view == null");
        return new SearchViewQueryTextChangeEventsObservable(searchView);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static InitialValueObservable<CharSequence> m14489b(@NonNull SearchView searchView) {
        C5596xi.m126a(searchView, "view == null");
        return new SearchViewQueryTextChangesObservable(searchView);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Consumer<? super CharSequence> m14491a(@NonNull final SearchView searchView, final boolean z) {
        C5596xi.m126a(searchView, "view == null");
        return new Consumer() { // from class: z1.-$$Lambda$aat$XO3XMFbGNhoCpzvf172y_US7gXY
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                searchView.setQuery((CharSequence) obj, z);
            }
        };
    }

    private RxSearchView() {
        throw new AssertionError("No instances.");
    }
}
