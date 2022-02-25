package androidx.media;

import android.support.annotation.RestrictTo;
import android.support.p003v4.media.AudioAttributesCompat;
import android.support.p003v4.media.AudioAttributesImpl;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(VersionedParcel eVar) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        audioAttributesCompat.mImpl = (AudioAttributesImpl) eVar.m25588b((VersionedParcel) audioAttributesCompat.mImpl, 1);
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, VersionedParcel eVar) {
        eVar.mo25549a(false, false);
        eVar.m25630a(audioAttributesCompat.mImpl, 1);
    }
}
