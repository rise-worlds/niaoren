package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.MenuItem;
import android.widget.Toolbar;

@RequiresApi(21)
/* renamed from: z1.aax */
/* loaded from: classes3.dex */
public final class RxToolbar {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<MenuItem> m14466a(@NonNull Toolbar toolbar) {
        C5596xi.m126a(toolbar, "view == null");
        return new ToolbarItemClickObservable(toolbar);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<Object> m14465b(@NonNull Toolbar toolbar) {
        C5596xi.m126a(toolbar, "view == null");
        return new ToolbarNavigationClickObservable(toolbar);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: c */
    public static Consumer<? super CharSequence> m14464c(@NonNull final Toolbar toolbar) {
        C5596xi.m126a(toolbar, "view == null");
        toolbar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$i-N9iiWyAx6XfmBohozRHRDgTWA
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                toolbar.setTitle((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: d */
    public static Consumer<? super Integer> m14463d(@NonNull final Toolbar toolbar) {
        C5596xi.m126a(toolbar, "view == null");
        toolbar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$8PNrSvYcbS4jEQAOINpkAQHo9b0
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                toolbar.setTitle(((Integer) obj).intValue());
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: e */
    public static Consumer<? super CharSequence> m14462e(@NonNull final Toolbar toolbar) {
        C5596xi.m126a(toolbar, "view == null");
        toolbar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$7QpwxlkqegRLQaLOG0LoPNWGbxM
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                toolbar.setSubtitle((CharSequence) obj);
            }
        };
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: f */
    public static Consumer<? super Integer> m14461f(@NonNull final Toolbar toolbar) {
        C5596xi.m126a(toolbar, "view == null");
        toolbar.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$QVTsip_srfiMa3VJrxZf8pIyt-E
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                toolbar.setSubtitle(((Integer) obj).intValue());
            }
        };
    }

    private RxToolbar() {
        throw new AssertionError("No instances.");
    }
}
