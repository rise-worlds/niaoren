package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.xv */
/* loaded from: classes3.dex */
public abstract class MenuItemActionViewExpandEvent extends MenuItemActionViewEvent {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static MenuItemActionViewExpandEvent m119a(@NonNull MenuItem menuItem) {
        return new AutoValue_MenuItemActionViewExpandEvent(menuItem);
    }
}
