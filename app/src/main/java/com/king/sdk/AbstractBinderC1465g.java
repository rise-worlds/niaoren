package com.king.sdk;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: IDFEE16B42C8B2890D8FF2860AF5562B1.java */
/* renamed from: com.king.sdk.g */
/* loaded from: classes.dex */
public abstract class AbstractBinderC1465g extends Binder implements IDFEE16B42C8B2890D8FF2860AF5562B1 {
    private static final String DESCRIPTOR = "com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1";
    static final int TRANSACTION_on088201B3F7D8B2CA1DF5D6AD12CB0C1F = 4;
    static final int TRANSACTION_on0D923A4B87763BA40F853B570EF75D94 = 6;
    static final int TRANSACTION_on19C45B0F9674B131C4BF67981B1CC455 = 7;
    static final int TRANSACTION_on1DC022CD78BE5580705D060873AAEB16 = 9;
    static final int TRANSACTION_on369D7B24CF0870337D9962B9BB11E924 = 3;
    static final int TRANSACTION_on46FE18C9ADFE18C2BC0D43B003F41A66 = 2;
    static final int TRANSACTION_on96EC25C9716D6EFFB8DD7648F16E4FA2 = 1;
    static final int TRANSACTION_onACF4B25418AE4E2D8376A85BD6F0FC17 = 8;
    static final int TRANSACTION_onB9504A9B1263E0D14DFE18C2167427BC = 5;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    public AbstractBinderC1465g() {
        attachInterface(this, DESCRIPTOR);
    }

    public static IDFEE16B42C8B2890D8FF2860AF5562B1 asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
        if (queryLocalInterface == null || !(queryLocalInterface instanceof IDFEE16B42C8B2890D8FF2860AF5562B1)) {
            return new C1466h(iBinder);
        }
        return (IDFEE16B42C8B2890D8FF2860AF5562B1) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1598968902) {
            Intent intent = null;
            boolean z = false;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                    }
                    boolean on96EC25C9716D6EFFB8DD7648F16E4FA2 = on96EC25C9716D6EFFB8DD7648F16E4FA2(intent);
                    parcel2.writeNoException();
                    parcel2.writeInt(on96EC25C9716D6EFFB8DD7648F16E4FA2 ? 1 : 0);
                    if (intent != null) {
                        parcel2.writeInt(1);
                        intent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    on46FE18C9ADFE18C2BC0D43B003F41A66(readString, z);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    on369D7B24CF0870337D9962B9BB11E924();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    on088201B3F7D8B2CA1DF5D6AD12CB0C1F(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onB9504A9B1263E0D14DFE18C2167427BC(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    on0D923A4B87763BA40F853B570EF75D94(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    on19C45B0F9674B131C4BF67981B1CC455(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onACF4B25418AE4E2D8376A85BD6F0FC17(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                    }
                    boolean on1DC022CD78BE5580705D060873AAEB16 = on1DC022CD78BE5580705D060873AAEB16(intent);
                    parcel2.writeNoException();
                    parcel2.writeInt(on1DC022CD78BE5580705D060873AAEB16 ? 1 : 0);
                    if (intent != null) {
                        parcel2.writeInt(1);
                        intent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        } else {
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }
}
