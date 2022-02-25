package com.king.sdk;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;

/* compiled from: IDFEE16B42C8B2890D8FF2860AF5562B1.java */
/* renamed from: com.king.sdk.h */
/* loaded from: classes.dex */
final class C1466h implements IDFEE16B42C8B2890D8FF2860AF5562B1 {

    /* renamed from: a */
    private IBinder f9410a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1466h(IBinder iBinder) {
        this.f9410a = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f9410a;
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final boolean on96EC25C9716D6EFFB8DD7648F16E4FA2(Intent intent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            boolean z = true;
            if (intent != null) {
                obtain.writeInt(1);
                intent.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f9410a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                z = false;
            }
            if (obtain2.readInt() != 0) {
                intent.readFromParcel(obtain2);
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final void on46FE18C9ADFE18C2BC0D43B003F41A66(String str, boolean z) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            obtain.writeString(str);
            obtain.writeInt(z ? 1 : 0);
            this.f9410a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final void on369D7B24CF0870337D9962B9BB11E924() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            this.f9410a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final void on088201B3F7D8B2CA1DF5D6AD12CB0C1F(String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            obtain.writeString(str);
            obtain.writeInt(i);
            this.f9410a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final void onB9504A9B1263E0D14DFE18C2167427BC(String str, String str2, int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.f9410a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final void on0D923A4B87763BA40F853B570EF75D94(int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.f9410a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final void on19C45B0F9674B131C4BF67981B1CC455(String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            obtain.writeString(str);
            obtain.writeInt(i);
            this.f9410a.transact(7, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final void onACF4B25418AE4E2D8376A85BD6F0FC17(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            obtain.writeString(str);
            obtain.writeString(str2);
            this.f9410a.transact(8, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1
    public final boolean on1DC022CD78BE5580705D060873AAEB16(Intent intent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1");
            boolean z = true;
            if (intent != null) {
                obtain.writeInt(1);
                intent.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f9410a.transact(9, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                z = false;
            }
            if (obtain2.readInt() != 0) {
                intent.readFromParcel(obtain2);
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
