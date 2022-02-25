package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.CompoundButton;

/* renamed from: z1.aao */
/* loaded from: classes3.dex */
public final class RxCompoundButton {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static InitialValueObservable<Boolean> m14511a(@NonNull CompoundButton compoundButton) {
        C5596xi.m126a(compoundButton, "view == null");
        return new CompoundButtonCheckedChangeObservable(compoundButton);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: b */
    public static Consumer<? super Boolean> m14509b(@NonNull final CompoundButton compoundButton) {
        C5596xi.m126a(compoundButton, "view == null");
        compoundButton.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$Zz_yXAEGGFe4SK220O39cjahnGQ
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                compoundButton.setChecked(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    /* renamed from: c */
    public static Consumer<? super Object> m14508c(@NonNull final CompoundButton compoundButton) {
        C5596xi.m126a(compoundButton, "view == null");
        return new Consumer() { // from class: z1.-$$Lambda$aao$H1JB60gn3tvyj843QB5ZnSN82Iw
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                compoundButton.toggle();
            }
        };
    }

    private RxCompoundButton() {
        throw new AssertionError("No instances.");
    }
}
