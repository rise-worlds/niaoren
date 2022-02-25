package p110z1;

import android.support.annotation.NonNull;
import android.view.MenuItem;

/* renamed from: z1.xk */
/* loaded from: classes3.dex */
final class AutoValue_MenuItemActionViewCollapseEvent extends MenuItemActionViewCollapseEvent {

    /* renamed from: a */
    private final MenuItem f23670a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MenuItemActionViewCollapseEvent(MenuItem menuItem) {
        if (menuItem != null) {
            this.f23670a = menuItem;
            return;
        }
        throw new NullPointerException("Null menuItem");
    }

    @Override // p110z1.MenuItemActionViewEvent
    @NonNull
    /* renamed from: a */
    public MenuItem mo121a() {
        return this.f23670a;
    }

    public String toString() {
        return "MenuItemActionViewCollapseEvent{menuItem=" + this.f23670a + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MenuItemActionViewCollapseEvent) {
            return this.f23670a.equals(((MenuItemActionViewCollapseEvent) obj).mo121a());
        }
        return false;
    }

    public int hashCode() {
        return this.f23670a.hashCode() ^ 1000003;
    }
}
