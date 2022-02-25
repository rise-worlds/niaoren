package androidx.media;

import android.support.annotation.RestrictTo;
import android.support.p003v4.media.AudioAttributesImplBase;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(VersionedParcel eVar) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.mUsage = eVar.m25598b(audioAttributesImplBase.mUsage, 1);
        audioAttributesImplBase.mContentType = eVar.m25598b(audioAttributesImplBase.mContentType, 2);
        audioAttributesImplBase.mFlags = eVar.m25598b(audioAttributesImplBase.mFlags, 3);
        audioAttributesImplBase.mLegacyStream = eVar.m25598b(audioAttributesImplBase.mLegacyStream, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel eVar) {
        eVar.mo25549a(false, false);
        eVar.m25642a(audioAttributesImplBase.mUsage, 1);
        eVar.m25642a(audioAttributesImplBase.mContentType, 2);
        eVar.m25642a(audioAttributesImplBase.mFlags, 3);
        eVar.m25642a(audioAttributesImplBase.mLegacyStream, 4);
    }
}
