package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;

@Deprecated
/* renamed from: z1.xj */
/* loaded from: classes3.dex */
public final class RxMenuItemCompat {
    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: a */
    public static Observable<MenuItemActionViewEvent> m124a(@NonNull MenuItem menuItem) {
        C5596xi.m126a(menuItem, "menuItem == null");
        return RxMenuItem.m116b(menuItem);
    }

    @CheckResult
    @NonNull
    @Deprecated
    /* renamed from: a */
    public static Observable<MenuItemActionViewEvent> m123a(@NonNull MenuItem menuItem, @NonNull Predicate<? super MenuItemActionViewEvent> auxVar) {
        C5596xi.m126a(menuItem, "menuItem == null");
        C5596xi.m126a(auxVar, "handled == null");
        return RxMenuItem.m115b(menuItem, auxVar);
    }

    private RxMenuItemCompat() {
        throw new AssertionError("No instances.");
    }
}
