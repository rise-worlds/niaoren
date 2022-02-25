package com.king.sdk;

import android.accounts.Account;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;

/* compiled from: I2FABB9840C76199A1E170A7C19698595.java */
/* renamed from: com.king.sdk.d */
/* loaded from: classes.dex */
final class C1463d implements I2FABB9840C76199A1E170A7C19698595 {

    /* renamed from: a */
    private IBinder f9409a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1463d(IBinder iBinder) {
        this.f9409a = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f9409a;
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final String mo19819a(Account account) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (account != null) {
                obtain.writeInt(1);
                account.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f9409a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final int mo19818a(Account account, String str, String str2, String str3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (account != null) {
                obtain.writeInt(1);
                account.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            this.f9409a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: b */
    public final int mo19804b(Account account, String str, String str2, String str3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (account != null) {
                obtain.writeInt(1);
                account.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            this.f9409a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final int mo19822a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f9409a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19810a(String str, String str2, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeInt(i);
            this.f9409a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19812a(String str, IBinder iBinder, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeStrongBinder(iBinder);
            obtain.writeInt(i);
            this.f9409a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19814a(String str, int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.f9409a.transact(7, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19817a(ComponentName componentName, int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (componentName != null) {
                obtain.writeInt(1);
                componentName.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.f9409a.transact(8, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19807a(String str, boolean z) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeInt(z ? 1 : 0);
            this.f9409a.transact(9, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: b */
    public final byte[] mo19805b() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f9409a.transact(10, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createByteArray();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19821a(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeInt(i);
            this.f9409a.transact(11, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final Intent mo19815a(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            this.f9409a.transact(12, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19820a(int i, int i2, String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeInt(i);
            obtain.writeInt(i2);
            obtain.writeString(str);
            this.f9409a.transact(13, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: b */
    public final IBinder mo19802b(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            this.f9409a.transact(14, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: c */
    public final void mo19800c(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            this.f9409a.transact(15, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: c */
    public final String mo19801c() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f9409a.transact(16, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: d */
    public final String mo19799d() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f9409a.transact(17, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19806a(String[] strArr, int i, String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeStringArray(strArr);
            obtain.writeInt(i);
            obtain.writeString(str);
            obtain.writeString(str2);
            this.f9409a.transact(18, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: e */
    public final int mo19797e() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f9409a.transact(19, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: f */
    public final String mo19795f() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f9409a.transact(20, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: d */
    public final boolean mo19798d(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            boolean z = false;
            this.f9409a.transact(21, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19816a(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeStrongBinder(idfee16b42c8b2890d8ff2860af5562b1 != null ? idfee16b42c8b2890d8ff2860af5562b1.asBinder() : null);
            this.f9409a.transact(22, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: b */
    public final void mo19803b(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeStrongBinder(idfee16b42c8b2890d8ff2860af5562b1 != null ? idfee16b42c8b2890d8ff2860af5562b1.asBinder() : null);
            this.f9409a.transact(23, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final void mo19813a(String str, int i, String str2, Notification notification) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeInt(i);
            obtain.writeString(str2);
            if (notification != null) {
                obtain.writeInt(1);
                notification.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f9409a.transact(24, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: g */
    public final String mo19794g() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f9409a.transact(25, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: e */
    public final void mo19796e(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            this.f9409a.transact(26, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final boolean mo19808a(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            boolean z = true;
            if (pendingIntent != null) {
                obtain.writeInt(1);
                pendingIntent.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (pendingIntent2 != null) {
                obtain.writeInt(1);
                pendingIntent2.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f9409a.transact(27, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                z = false;
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final boolean mo19811a(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeString(str2);
            boolean z = false;
            this.f9409a.transact(28, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.king.sdk.I2FABB9840C76199A1E170A7C19698595
    /* renamed from: a */
    public final boolean mo19809a(String str, String str2, int i, byte[] bArr, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeInt(i);
            obtain.writeByteArray(bArr);
            boolean z = true;
            if (pendingIntent != null) {
                obtain.writeInt(1);
                pendingIntent.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (pendingIntent2 != null) {
                obtain.writeInt(1);
                pendingIntent2.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f9409a.transact(29, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                z = false;
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
