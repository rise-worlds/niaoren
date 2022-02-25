package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: androidx.versionedparcelable.f */
/* loaded from: classes.dex */
class VersionedParcelParcel extends VersionedParcel {

    /* renamed from: a */
    private static final boolean f72a = false;

    /* renamed from: b */
    private static final String f73b = "VersionedParcelParcel";

    /* renamed from: c */
    private final SparseIntArray f74c;

    /* renamed from: d */
    private final Parcel f75d;

    /* renamed from: e */
    private final int f76e;

    /* renamed from: f */
    private final int f77f;

    /* renamed from: g */
    private final String f78g;

    /* renamed from: h */
    private int f79h;

    /* renamed from: i */
    private int f80i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VersionedParcelParcel(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    VersionedParcelParcel(Parcel parcel, int i, int i2, String str) {
        this.f74c = new SparseIntArray();
        this.f79h = -1;
        this.f80i = 0;
        this.f75d = parcel;
        this.f76e = i;
        this.f77f = i2;
        this.f80i = this.f76e;
        this.f78g = str;
    }

    /* renamed from: d */
    private int m25563d(int i) {
        int readInt;
        do {
            int i2 = this.f80i;
            if (i2 >= this.f77f) {
                return -1;
            }
            this.f75d.setDataPosition(i2);
            int readInt2 = this.f75d.readInt();
            readInt = this.f75d.readInt();
            this.f80i += readInt2;
        } while (readInt != i);
        return this.f75d.dataPosition();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: b */
    public boolean mo25545b(int i) {
        int d = m25563d(i);
        if (d == -1) {
            return false;
        }
        this.f75d.setDataPosition(d);
        return true;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: c */
    public void mo25543c(int i) {
        mo25546b();
        this.f79h = i;
        this.f74c.put(i, this.f75d.dataPosition());
        mo25559a(0);
        mo25559a(i);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: b */
    public void mo25546b() {
        int i = this.f79h;
        if (i >= 0) {
            int i2 = this.f74c.get(i);
            int dataPosition = this.f75d.dataPosition();
            this.f75d.setDataPosition(i2);
            this.f75d.writeInt(dataPosition - i2);
            this.f75d.setDataPosition(dataPosition);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: c */
    protected VersionedParcel mo25544c() {
        Parcel parcel = this.f75d;
        int dataPosition = parcel.dataPosition();
        int i = this.f80i;
        if (i == this.f76e) {
            i = this.f77f;
        }
        return new VersionedParcelParcel(parcel, dataPosition, i, this.f78g + "  ");
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25548a(byte[] bArr) {
        if (bArr != null) {
            this.f75d.writeInt(bArr.length);
            this.f75d.writeByteArray(bArr);
            return;
        }
        this.f75d.writeInt(-1);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25547a(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            this.f75d.writeInt(bArr.length);
            this.f75d.writeByteArray(bArr, i, i2);
            return;
        }
        this.f75d.writeInt(-1);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25559a(int i) {
        this.f75d.writeInt(i);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25557a(long j) {
        this.f75d.writeLong(j);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25560a(float f) {
        this.f75d.writeFloat(f);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25561a(double d) {
        this.f75d.writeDouble(d);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25551a(String str) {
        this.f75d.writeString(str);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25555a(IBinder iBinder) {
        this.f75d.writeStrongBinder(iBinder);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25553a(Parcelable parcelable) {
        this.f75d.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25550a(boolean z) {
        this.f75d.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25554a(IInterface iInterface) {
        this.f75d.writeStrongInterface(iInterface);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25556a(Bundle bundle) {
        this.f75d.writeBundle(bundle);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: d */
    public int mo25542d() {
        return this.f75d.readInt();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: e */
    public long mo25541e() {
        return this.f75d.readLong();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: f */
    public float mo25540f() {
        return this.f75d.readFloat();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: g */
    public double mo25539g() {
        return this.f75d.readDouble();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: h */
    public String mo25538h() {
        return this.f75d.readString();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: i */
    public IBinder mo25537i() {
        return this.f75d.readStrongBinder();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: j */
    public byte[] mo25536j() {
        int readInt = this.f75d.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.f75d.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: k */
    public <T extends Parcelable> T mo25535k() {
        return (T) this.f75d.readParcelable(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: l */
    public Bundle mo25534l() {
        return this.f75d.readBundle(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: m */
    public boolean mo25533m() {
        return this.f75d.readInt() != 0;
    }
}
