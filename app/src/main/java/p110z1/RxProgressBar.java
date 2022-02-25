package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;

/* renamed from: z1.aaq */
/* loaded from: classes3.dex */
public final class RxProgressBar {
    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: a */
    public static Consumer<? super Integer> m14505a(@NonNull final ProgressBar progressBar) {
        C5596xi.m126a(progressBar, "view == null");
        progressBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$u4CtHC665mX4AweqGsFzk5UXibw
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                progressBar.incrementProgressBy(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: b */
    public static Consumer<? super Integer> m14504b(@NonNull final ProgressBar progressBar) {
        C5596xi.m126a(progressBar, "view == null");
        progressBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$IBx5uFGiivxn-adU5SNRHvkOAz8
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                progressBar.incrementSecondaryProgressBy(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: c */
    public static Consumer<? super Boolean> m14503c(@NonNull final ProgressBar progressBar) {
        C5596xi.m126a(progressBar, "view == null");
        progressBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$xrc7TFsarPcTqLUSt69SrEpTmQA
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                progressBar.setIndeterminate(((Boolean) obj).booleanValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: d */
    public static Consumer<? super Integer> m14502d(@NonNull final ProgressBar progressBar) {
        C5596xi.m126a(progressBar, "view == null");
        progressBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$K4Ob9w2n1wBb9btgfjm0yg1GJOg
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                progressBar.setMax(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: e */
    public static Consumer<? super Integer> m14501e(@NonNull final ProgressBar progressBar) {
        C5596xi.m126a(progressBar, "view == null");
        progressBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$dyosDKZb969alqbr387JKfG73Tg
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                progressBar.setProgress(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: f */
    public static Consumer<? super Integer> m14500f(@NonNull final ProgressBar progressBar) {
        C5596xi.m126a(progressBar, "view == null");
        progressBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$IJKEI3yMrqO4KFc_13uUbOA0ubI
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                progressBar.setSecondaryProgress(((Integer) obj).intValue());
            }
        };
    }

    private RxProgressBar() {
        throw new AssertionError("No instances.");
    }
}
