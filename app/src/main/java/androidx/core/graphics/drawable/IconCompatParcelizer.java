package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.support.annotation.RestrictTo;
import android.support.p003v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel eVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.mType = eVar.m25598b(iconCompat.mType, 1);
        iconCompat.mData = eVar.m25582b(iconCompat.mData, 2);
        iconCompat.mParcelable = eVar.m25593b((VersionedParcel) iconCompat.mParcelable, 3);
        iconCompat.mInt1 = eVar.m25598b(iconCompat.mInt1, 4);
        iconCompat.mInt2 = eVar.m25598b(iconCompat.mInt2, 5);
        iconCompat.mTintList = (ColorStateList) eVar.m25593b((VersionedParcel) iconCompat.mTintList, 6);
        iconCompat.mTintModeStr = eVar.m25586b(iconCompat.mTintModeStr, 7);
        iconCompat.onPostParceling();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, VersionedParcel eVar) {
        eVar.mo25549a(true, true);
        iconCompat.onPreParceling(eVar.mo25562a());
        eVar.m25642a(iconCompat.mType, 1);
        eVar.m25616a(iconCompat.mData, 2);
        eVar.m25635a(iconCompat.mParcelable, 3);
        eVar.m25642a(iconCompat.mInt1, 4);
        eVar.m25642a(iconCompat.mInt2, 5);
        eVar.m25635a(iconCompat.mTintList, 6);
        eVar.m25623a(iconCompat.mTintModeStr, 7);
    }
}
