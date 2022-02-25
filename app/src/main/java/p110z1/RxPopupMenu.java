package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.PopupMenu;

/* renamed from: z1.aap */
/* loaded from: classes3.dex */
public final class RxPopupMenu {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static Observable<MenuItem> m14507a(@NonNull PopupMenu popupMenu) {
        C5596xi.m126a(popupMenu, "view == null");
        return new PopupMenuItemClickObservable(popupMenu);
    }

    @CheckResult
    @NonNull
    /* renamed from: b */
    public static Observable<Object> m14506b(@NonNull PopupMenu popupMenu) {
        C5596xi.m126a(popupMenu, "view == null");
        return new PopupMenuDismissObservable(popupMenu);
    }

    private RxPopupMenu() {
        throw new AssertionError("No instances.");
    }
}
