package p110z1;

import android.support.annotation.NonNull;
import android.widget.SearchView;

/* renamed from: z1.zu */
/* loaded from: classes3.dex */
final class AutoValue_SearchViewQueryTextEvent extends SearchViewQueryTextEvent {

    /* renamed from: a */
    private final SearchView f23823a;

    /* renamed from: b */
    private final CharSequence f23824b;

    /* renamed from: c */
    private final boolean f23825c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SearchViewQueryTextEvent(SearchView searchView, CharSequence charSequence, boolean z) {
        if (searchView != null) {
            this.f23823a = searchView;
            if (charSequence != null) {
                this.f23824b = charSequence;
                this.f23825c = z;
                return;
            }
            throw new NullPointerException("Null queryText");
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.SearchViewQueryTextEvent
    @NonNull
    /* renamed from: a */
    public SearchView mo12a() {
        return this.f23823a;
    }

    @Override // p110z1.SearchViewQueryTextEvent
    @NonNull
    /* renamed from: b */
    public CharSequence mo11b() {
        return this.f23824b;
    }

    @Override // p110z1.SearchViewQueryTextEvent
    /* renamed from: c */
    public boolean mo10c() {
        return this.f23825c;
    }

    public String toString() {
        return "SearchViewQueryTextEvent{view=" + this.f23823a + ", queryText=" + ((Object) this.f23824b) + ", isSubmitted=" + this.f23825c + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchViewQueryTextEvent)) {
            return false;
        }
        SearchViewQueryTextEvent abaVar = (SearchViewQueryTextEvent) obj;
        return this.f23823a.equals(abaVar.mo12a()) && this.f23824b.equals(abaVar.mo11b()) && this.f23825c == abaVar.mo10c();
    }

    public int hashCode() {
        return ((((this.f23823a.hashCode() ^ 1000003) * 1000003) ^ this.f23824b.hashCode()) * 1000003) ^ (this.f23825c ? 1231 : 1237);
    }
}
