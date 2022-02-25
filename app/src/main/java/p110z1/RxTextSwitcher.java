package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextSwitcher;

/* renamed from: z1.aav */
/* loaded from: classes3.dex */
public final class RxTextSwitcher {
    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: a */
    public static Consumer<? super CharSequence> m14484a(@NonNull final TextSwitcher textSwitcher) {
        C5596xi.m126a(textSwitcher, "view == null");
        textSwitcher.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$oM86W5RVzzfxiJZ48J10Y2-DsrI
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textSwitcher.setText((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: b */
    public static Consumer<? super CharSequence> m14483b(@NonNull final TextSwitcher textSwitcher) {
        C5596xi.m126a(textSwitcher, "view == null");
        textSwitcher.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$gpEKSGkHWgaHTYX9Nd8zFH7JO3w
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                textSwitcher.setCurrentText((CharSequence) obj);
            }
        };
    }

    private RxTextSwitcher() {
        throw new AssertionError("No instances.");
    }
}
