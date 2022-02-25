package p110z1;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.google.auto.value.AutoValue;

@AutoValue
/* renamed from: z1.yl */
/* loaded from: classes3.dex */
public abstract class ViewGroupHierarchyChildViewRemoveEvent extends ViewGroupHierarchyChangeEvent {
    @CheckResult
    @NonNull
    /* renamed from: a */
    public static ViewGroupHierarchyChildViewRemoveEvent m64a(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return new AutoValue_ViewGroupHierarchyChildViewRemoveEvent(viewGroup, view);
    }
}
