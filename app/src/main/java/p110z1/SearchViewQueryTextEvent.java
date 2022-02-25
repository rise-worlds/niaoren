package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SearchView;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.aba */
/* loaded from: classes3.dex */
public abstract class SearchViewQueryTextEvent {
    @NonNull
    /* renamed from: a */
    public abstract SearchView mo12a();

    @NonNull
    /* renamed from: b */
    public abstract CharSequence mo11b();

    /* renamed from: c */
    public abstract boolean mo10c();

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static SearchViewQueryTextEvent m14458a(@NonNull SearchView searchView, @NonNull CharSequence charSequence, boolean z) {
        return new AutoValue_SearchViewQueryTextEvent(searchView, charSequence, z);
    }
}
