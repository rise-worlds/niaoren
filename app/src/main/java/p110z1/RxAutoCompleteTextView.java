package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AutoCompleteTextView;

/* renamed from: z1.aam */
/* loaded from: classes3.dex */
public final class RxAutoCompleteTextView {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<AdapterViewItemClickEvent> m14515a(@NonNull AutoCompleteTextView autoCompleteTextView) {
        C5596xi.m126a(autoCompleteTextView, "view == null");
        return new AutoCompleteTextViewItemClickEventObservable(autoCompleteTextView);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: b */
    public static Consumer<? super CharSequence> m14514b(@NonNull final AutoCompleteTextView autoCompleteTextView) {
        C5596xi.m126a(autoCompleteTextView, "view == null");
        autoCompleteTextView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$DHntJaQ9o9vEOBzptgshqL6QFqg
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                autoCompleteTextView.setCompletionHint((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: c */
    public static Consumer<? super Integer> m14513c(@NonNull final AutoCompleteTextView autoCompleteTextView) {
        C5596xi.m126a(autoCompleteTextView, "view == null");
        autoCompleteTextView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$sbYHy9SP1SuhzNZaThkp8vG57jQ
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                autoCompleteTextView.setThreshold(((Integer) obj).intValue());
            }
        };
    }

    private RxAutoCompleteTextView() {
        throw new AssertionError("No instances.");
    }
}
