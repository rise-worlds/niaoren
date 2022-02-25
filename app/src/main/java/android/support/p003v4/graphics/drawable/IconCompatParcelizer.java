package android.support.p003v4.graphics.drawable;

import android.support.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: android.support.v4.graphics.drawable.IconCompatParcelizer */
/* loaded from: classes.dex */
public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer {
    public static IconCompat read(VersionedParcel eVar) {
        return androidx.core.graphics.drawable.IconCompatParcelizer.read(eVar);
    }

    public static void write(IconCompat iconCompat, VersionedParcel eVar) {
        androidx.core.graphics.drawable.IconCompatParcelizer.write(iconCompat, eVar);
    }
}
