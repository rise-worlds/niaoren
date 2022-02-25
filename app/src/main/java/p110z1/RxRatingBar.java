package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RatingBar;

/* renamed from: z1.aas */
/* loaded from: classes3.dex */
public final class RxRatingBar {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static InitialValueObservable<Float> m14496a(@NonNull RatingBar ratingBar) {
        C5596xi.m126a(ratingBar, "view == null");
        return new RatingBarRatingChangeObservable(ratingBar);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static InitialValueObservable<RatingBarChangeEvent> m14495b(@NonNull RatingBar ratingBar) {
        C5596xi.m126a(ratingBar, "view == null");
        return new RatingBarRatingChangeEventObservable(ratingBar);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: c */
    public static Consumer<? super Float> m14494c(@NonNull final RatingBar ratingBar) {
        C5596xi.m126a(ratingBar, "view == null");
        ratingBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$bz-YzAsncGXSUnANnQp4ENblxEM
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                ratingBar.setRating(((Float) obj).floatValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: d */
    public static Consumer<? super Boolean> m14493d(@NonNull final RatingBar ratingBar) {
        C5596xi.m126a(ratingBar, "view == null");
        ratingBar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$gPBsa7ZK_ihn22QacRbrFY4BG4o
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                ratingBar.setIsIndicator(((Boolean) obj).booleanValue());
            }
        };
    }

    private RxRatingBar() {
        throw new AssertionError("No instances.");
    }
}
