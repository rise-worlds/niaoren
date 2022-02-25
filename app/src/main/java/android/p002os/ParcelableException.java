package android.p002os;

import android.annotation.TargetApi;
import android.os.Parcelable;

@TargetApi(26)
/* renamed from: android.os.ParcelableException */
/* loaded from: classes.dex */
public final class ParcelableException extends RuntimeException implements Parcelable {
    public static final Parcelable.Creator<ParcelableException> CREATOR = new Parcelable.Creator<ParcelableException>() { // from class: android.os.ParcelableException.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableException createFromParcel(Parcel parcel) {
            return new ParcelableException(ParcelableException.readFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableException[] newArray(int i) {
            return new ParcelableException[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableException(Throwable th) {
        super(th);
    }

    public <T extends Throwable> void maybeRethrow(Class<T> cls) throws Throwable {
        throw new RuntimeException("Stub!");
    }

    public static Throwable readFromParcel(Parcel parcel) {
        throw new RuntimeException("Stub!");
    }

    public static void writeToParcel(Parcel parcel, Throwable th) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
