package com.lody.virtual.remote;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Problem implements Parcelable {
    public static final Parcelable.Creator<Problem> CREATOR = new Parcelable.Creator<Problem>() { // from class: com.lody.virtual.remote.Problem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Problem createFromParcel(Parcel parcel) {
            return new Problem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Problem[] newArray(int i) {
            return new Problem[i];
        }
    };

    /* renamed from: e */
    public Throwable f10501e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Problem(Throwable th) {
        this.f10501e = th;
    }

    protected Problem(Parcel parcel) {
        this.f10501e = (Throwable) parcel.readSerializable();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.f10501e);
    }
}
