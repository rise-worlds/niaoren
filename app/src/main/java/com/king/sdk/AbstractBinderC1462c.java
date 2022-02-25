package com.king.sdk;

import android.accounts.Account;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: I2FABB9840C76199A1E170A7C19698595.java */
/* renamed from: com.king.sdk.c */
/* loaded from: classes.dex */
public abstract class AbstractBinderC1462c extends Binder implements I2FABB9840C76199A1E170A7C19698595 {
    /* renamed from: a */
    public static I2FABB9840C76199A1E170A7C19698595 m19823a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof I2FABB9840C76199A1E170A7C19698595)) {
            return new C1463d(iBinder);
        }
        return (I2FABB9840C76199A1E170A7C19698595) queryLocalInterface;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1598968902) {
            boolean z = false;
            Account account = null;
            Notification notification = null;
            ComponentName componentName = null;
            Account account2 = null;
            Account account3 = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    if (parcel.readInt() != 0) {
                        account = (Account) Account.CREATOR.createFromParcel(parcel);
                    }
                    String a = mo19819a(account);
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    if (parcel.readInt() != 0) {
                        account3 = (Account) Account.CREATOR.createFromParcel(parcel);
                    }
                    int a2 = mo19818a(account3, parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a2);
                    return true;
                case 3:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    if (parcel.readInt() != 0) {
                        account2 = (Account) Account.CREATOR.createFromParcel(parcel);
                    }
                    int b = mo19804b(account2, parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(b);
                    return true;
                case 4:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    int a3 = mo19822a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a3);
                    return true;
                case 5:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19810a(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19812a(parcel.readString(), parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19814a(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    if (parcel.readInt() != 0) {
                        componentName = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                    }
                    mo19817a(componentName, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    String readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    mo19807a(readString, z);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    byte[] b2 = mo19805b();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(b2);
                    return true;
                case 11:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19821a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    Intent a4 = mo19815a(parcel.readString());
                    parcel2.writeNoException();
                    if (a4 != null) {
                        parcel2.writeInt(1);
                        a4.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19820a(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    IBinder b3 = mo19802b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(b3);
                    return true;
                case 15:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19800c(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    String c = mo19801c();
                    parcel2.writeNoException();
                    parcel2.writeString(c);
                    return true;
                case 17:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    String d = mo19799d();
                    parcel2.writeNoException();
                    parcel2.writeString(d);
                    return true;
                case 18:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19806a(parcel.createStringArray(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    int e = mo19797e();
                    parcel2.writeNoException();
                    parcel2.writeInt(e);
                    return true;
                case 20:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    String f = mo19795f();
                    parcel2.writeNoException();
                    parcel2.writeString(f);
                    return true;
                case 21:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    boolean d2 = mo19798d(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(d2 ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19816a(AbstractBinderC1465g.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19803b(AbstractBinderC1465g.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    String readString2 = parcel.readString();
                    int readInt = parcel.readInt();
                    String readString3 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        notification = (Notification) Notification.CREATOR.createFromParcel(parcel);
                    }
                    mo19813a(readString2, readInt, readString3, notification);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    String g = mo19794g();
                    parcel2.writeNoException();
                    parcel2.writeString(g);
                    return true;
                case 26:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    mo19796e(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    boolean a5 = mo19808a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(a5 ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    boolean a6 = mo19811a(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a6 ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                    boolean a7 = mo19809a(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.createByteArray(), parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(a7 ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        } else {
            parcel2.writeString("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            return true;
        }
    }
}
