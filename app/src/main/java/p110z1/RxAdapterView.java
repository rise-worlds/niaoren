package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.Adapter;
import android.widget.AdapterView;
import java.util.concurrent.Callable;

/* renamed from: z1.aal */
/* loaded from: classes3.dex */
public final class RxAdapterView {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static <T extends Adapter> InitialValueObservable<Integer> m14524a(@NonNull AdapterView<T> adapterView) {
        C5596xi.m126a(adapterView, "view == null");
        return new AdapterViewItemSelectionObservable(adapterView);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static <T extends Adapter> InitialValueObservable<AdapterViewSelectionEvent> m14521b(@NonNull AdapterView<T> adapterView) {
        C5596xi.m126a(adapterView, "view == null");
        return new AdapterViewSelectionObservable(adapterView);
    }

    @CheckResult
    @NonNull
    /* renamed from: c */
    public static <T extends Adapter> Observable<Integer> m14520c(@NonNull AdapterView<T> adapterView) {
        C5596xi.m126a(adapterView, "view == null");
        return new AdapterViewItemClickObservable(adapterView);
    }

    @CheckResult
    @NonNull
    /* renamed from: d */
    public static <T extends Adapter> Observable<AdapterViewItemClickEvent> m14519d(@NonNull AdapterView<T> adapterView) {
        C5596xi.m126a(adapterView, "view == null");
        return new AdapterViewItemClickEventObservable(adapterView);
    }

    @CheckResult
    @NonNull
    /* renamed from: e */
    public static <T extends Adapter> Observable<Integer> m14518e(@NonNull AdapterView<T> adapterView) {
        C5596xi.m126a(adapterView, "view == null");
        return m14523a(adapterView, C5593xf.f23666a);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static <T extends Adapter> Observable<Integer> m14523a(@NonNull AdapterView<T> adapterView, @NonNull Callable<Boolean> callable) {
        C5596xi.m126a(adapterView, "view == null");
        C5596xi.m126a(callable, "handled == null");
        return new AdapterViewItemLongClickObservable(adapterView, callable);
    }

    @CheckResult
    @NonNull
    /* renamed from: f */
    public static <T extends Adapter> Observable<AdapterViewItemLongClickEvent> m14517f(@NonNull AdapterView<T> adapterView) {
        C5596xi.m126a(adapterView, "view == null");
        return m14522a(adapterView, C5593xf.f23667b);
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public static <T extends Adapter> Observable<AdapterViewItemLongClickEvent> m14522a(@NonNull AdapterView<T> adapterView, @NonNull Predicate<? super AdapterViewItemLongClickEvent> auxVar) {
        C5596xi.m126a(adapterView, "view == null");
        C5596xi.m126a(auxVar, "handled == null");
        return new AdapterViewItemLongClickEventObservable(adapterView, auxVar);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: g */
    public static <T extends Adapter> Consumer<? super Integer> m14516g(@NonNull final AdapterView<T> adapterView) {
        C5596xi.m126a(adapterView, "view == null");
        adapterView.getClass();
        return new Consumer() { // from class: z1.-$$Lambda$fLL0nwB7ufeX0KFQzD0A_tPuu-o
            @Override // p110z1.Consumer
            public final void accept(Object obj) {
                adapterView.setSelection(((Integer) obj).intValue());
            }
        };
    }

    private RxAdapterView() {
        throw new AssertionError("No instances.");
    }
}
