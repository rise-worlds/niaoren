package androidx.versionedparcelable;

import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import java.io.InputStream;
import java.io.OutputStream;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: androidx.versionedparcelable.c */
/* loaded from: classes.dex */
public class ParcelUtils {
    private ParcelUtils() {
    }

    /* renamed from: a */
    public static Parcelable m25648a(VersionedParcelable hVar) {
        return new ParcelImpl(hVar);
    }

    /* renamed from: a */
    public static <T extends VersionedParcelable> T m25649a(Parcelable parcelable) {
        if (parcelable instanceof ParcelImpl) {
            return (T) ((ParcelImpl) parcelable).m25653a();
        }
        throw new IllegalArgumentException("Invalid parcel");
    }

    /* renamed from: a */
    public static void m25647a(VersionedParcelable hVar, OutputStream outputStream) {
        VersionedParcelStream gVar = new VersionedParcelStream(null, outputStream);
        gVar.m25631a(hVar);
        gVar.mo25546b();
    }

    /* renamed from: a */
    public static <T extends VersionedParcelable> T m25646a(InputStream inputStream) {
        return (T) new VersionedParcelStream(inputStream, null).m25566t();
    }
}
