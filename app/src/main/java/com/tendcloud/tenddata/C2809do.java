package com.tendcloud.tenddata;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.do */
/* loaded from: classes2.dex */
final class C2809do implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public C2808dn createFromParcel(Parcel parcel) {
        try {
            return new C2808dn(parcel);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // android.os.Parcelable.Creator
    public C2808dn[] newArray(int i) {
        try {
            return new C2808dn[i];
        } catch (Throwable unused) {
            return null;
        }
    }
}
