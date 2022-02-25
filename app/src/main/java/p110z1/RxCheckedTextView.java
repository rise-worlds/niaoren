package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.CheckedTextView;

/* renamed from: z1.aan */
/* loaded from: classes3.dex */
public final class RxCheckedTextView {
    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: a */
    public static Consumer<? super Boolean> m14512a(@NonNull final CheckedTextView checkedTextView) {
        C5596xi.m126a(checkedTextView, "view == null");
        checkedTextView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$CarsBXYjrmgLLW7EkHTRbEpgQJA
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                checkedTextView.setChecked(((Boolean) obj).booleanValue());
            }
        };
    }

    private RxCheckedTextView() {
        throw new AssertionError("No instances.");
    }
}
