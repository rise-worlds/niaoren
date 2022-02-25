package p110z1;

import android.support.annotation.NonNull;
import android.view.MenuItem;

/* renamed from: z1.xl */
/* loaded from: classes3.dex */
final class AutoValue_MenuItemActionViewExpandEvent extends MenuItemActionViewExpandEvent {

    /* renamed from: a */
    private final MenuItem f23671a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MenuItemActionViewExpandEvent(MenuItem menuItem) {
        if (menuItem != null) {
            this.f23671a = menuItem;
            return;
        }
        throw new NullPointerException("Null menuItem");
    }

    @Override // p110z1.MenuItemActionViewEvent
    @NonNull
    /* renamed from: a */
    public MenuItem mo121a() {
        return this.f23671a;
    }

    public String toString() {
        return "MenuItemActionViewExpandEvent{menuItem=" + this.f23671a + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MenuItemActionViewExpandEvent) {
            return this.f23671a.equals(((MenuItemActionViewExpandEvent) obj).mo121a());
        }
        return false;
    }

    public int hashCode() {
        return this.f23671a.hashCode() ^ 1000003;
    }
}
