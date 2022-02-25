package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseArray;
import androidx.versionedparcelable.VersionedParcel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;
import org.apache.http.protocol.HTTP;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: androidx.versionedparcelable.g */
/* loaded from: classes.dex */
class VersionedParcelStream extends VersionedParcel {

    /* renamed from: a */
    private static final Charset f81a = Charset.forName(HTTP.UTF_16);

    /* renamed from: b */
    private static final int f82b = 0;

    /* renamed from: c */
    private static final int f83c = 1;

    /* renamed from: d */
    private static final int f84d = 2;

    /* renamed from: e */
    private static final int f85e = 3;

    /* renamed from: f */
    private static final int f86f = 4;

    /* renamed from: g */
    private static final int f87g = 5;

    /* renamed from: h */
    private static final int f88h = 6;

    /* renamed from: i */
    private static final int f89i = 7;

    /* renamed from: j */
    private static final int f90j = 8;

    /* renamed from: k */
    private static final int f91k = 9;

    /* renamed from: l */
    private static final int f92l = 10;

    /* renamed from: m */
    private static final int f93m = 11;

    /* renamed from: n */
    private static final int f94n = 12;

    /* renamed from: o */
    private static final int f95o = 13;

    /* renamed from: p */
    private static final int f96p = 14;

    /* renamed from: q */
    private final DataInputStream f97q;

    /* renamed from: r */
    private final DataOutputStream f98r;

    /* renamed from: s */
    private final SparseArray<C0578b> f99s = new SparseArray<>();

    /* renamed from: t */
    private DataInputStream f100t;

    /* renamed from: u */
    private DataOutputStream f101u;

    /* renamed from: v */
    private C0577a f102v;

    /* renamed from: w */
    private boolean f103w;

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public boolean mo25562a() {
        return true;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: i */
    public IBinder mo25537i() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: k */
    public <T extends Parcelable> T mo25535k() {
        return null;
    }

    public VersionedParcelStream(InputStream inputStream, OutputStream outputStream) {
        DataOutputStream dataOutputStream = null;
        this.f97q = inputStream != null ? new DataInputStream(inputStream) : null;
        this.f98r = outputStream != null ? new DataOutputStream(outputStream) : dataOutputStream;
        this.f100t = this.f97q;
        this.f101u = this.f98r;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25549a(boolean z, boolean z2) {
        if (z) {
            this.f103w = z2;
            return;
        }
        throw new RuntimeException("Serialization of this object is not allowed");
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: b */
    public void mo25546b() {
        C0577a aVar = this.f102v;
        if (aVar != null) {
            try {
                if (aVar.f104a.size() != 0) {
                    this.f102v.m25532a();
                }
                this.f102v = null;
            } catch (IOException e) {
                throw new VersionedParcel.C0576a(e);
            }
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: c */
    protected VersionedParcel mo25544c() {
        return new VersionedParcelStream(this.f100t, this.f101u);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: b */
    public boolean mo25545b(int i) {
        C0578b bVar = this.f99s.get(i);
        if (bVar != null) {
            this.f99s.remove(i);
            this.f100t = bVar.f108a;
            return true;
        }
        while (true) {
            try {
                int readInt = this.f97q.readInt();
                int i2 = readInt & 65535;
                if (i2 == 65535) {
                    i2 = this.f97q.readInt();
                }
                C0578b bVar2 = new C0578b((readInt >> 16) & 65535, i2, this.f97q);
                if (bVar2.f109b == i) {
                    this.f100t = bVar2.f108a;
                    return true;
                }
                this.f99s.put(bVar2.f109b, bVar2);
            } catch (IOException unused) {
                return false;
            }
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: c */
    public void mo25543c(int i) {
        mo25546b();
        this.f102v = new C0577a(i, this.f98r);
        this.f101u = this.f102v.f105b;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25548a(byte[] bArr) {
        try {
            if (bArr != null) {
                this.f101u.writeInt(bArr.length);
                this.f101u.write(bArr);
                return;
            }
            this.f101u.writeInt(-1);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25547a(byte[] bArr, int i, int i2) {
        try {
            if (bArr != null) {
                this.f101u.writeInt(i2);
                this.f101u.write(bArr, i, i2);
                return;
            }
            this.f101u.writeInt(-1);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25559a(int i) {
        try {
            this.f101u.writeInt(i);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25557a(long j) {
        try {
            this.f101u.writeLong(j);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25560a(float f) {
        try {
            this.f101u.writeFloat(f);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25561a(double d) {
        try {
            this.f101u.writeDouble(d);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25551a(String str) {
        try {
            if (str != null) {
                byte[] bytes = str.getBytes(f81a);
                this.f101u.writeInt(bytes.length);
                this.f101u.write(bytes);
                return;
            }
            this.f101u.writeInt(-1);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25550a(boolean z) {
        try {
            this.f101u.writeBoolean(z);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25555a(IBinder iBinder) {
        if (!this.f103w) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25553a(Parcelable parcelable) {
        if (!this.f103w) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25554a(IInterface iInterface) {
        if (!this.f103w) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: d */
    public int mo25542d() {
        try {
            return this.f100t.readInt();
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: e */
    public long mo25541e() {
        try {
            return this.f100t.readLong();
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: f */
    public float mo25540f() {
        try {
            return this.f100t.readFloat();
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: g */
    public double mo25539g() {
        try {
            return this.f100t.readDouble();
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: h */
    public String mo25538h() {
        try {
            int readInt = this.f100t.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.f100t.readFully(bArr);
            return new String(bArr, f81a);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: j */
    public byte[] mo25536j() {
        try {
            int readInt = this.f100t.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.f100t.readFully(bArr);
            return bArr;
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: m */
    public boolean mo25533m() {
        try {
            return this.f100t.readBoolean();
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo25556a(Bundle bundle) {
        try {
            if (bundle != null) {
                Set<String> keySet = bundle.keySet();
                this.f101u.writeInt(keySet.size());
                for (String str : keySet) {
                    mo25551a(str);
                    m25552a(bundle.get(str));
                }
                return;
            }
            this.f101u.writeInt(-1);
        } catch (IOException e) {
            throw new VersionedParcel.C0576a(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: l */
    public Bundle mo25534l() {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (int i = 0; i < d; i++) {
            m25558a(mo25542d(), mo25538h(), bundle);
        }
        return bundle;
    }

    /* renamed from: a */
    private void m25552a(Object obj) {
        if (obj == null) {
            mo25559a(0);
        } else if (obj instanceof Bundle) {
            mo25559a(1);
            mo25556a((Bundle) obj);
        } else if (obj instanceof String) {
            mo25559a(3);
            mo25551a((String) obj);
        } else if (obj instanceof String[]) {
            mo25559a(4);
            m25605a((Object[]) ((String[]) obj));
        } else if (obj instanceof Boolean) {
            mo25559a(5);
            mo25550a(((Boolean) obj).booleanValue());
        } else if (obj instanceof boolean[]) {
            mo25559a(6);
            m25603a((boolean[]) obj);
        } else if (obj instanceof Double) {
            mo25559a(7);
            mo25561a(((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            mo25559a(8);
            m25613a((double[]) obj);
        } else if (obj instanceof Integer) {
            mo25559a(9);
            mo25559a(((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            mo25559a(10);
            m25609a((int[]) obj);
        } else if (obj instanceof Long) {
            mo25559a(11);
            mo25557a(((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            mo25559a(12);
            m25607a((long[]) obj);
        } else if (obj instanceof Float) {
            mo25559a(13);
            mo25560a(((Float) obj).floatValue());
        } else if (obj instanceof float[]) {
            mo25559a(14);
            m25611a((float[]) obj);
        } else {
            throw new IllegalArgumentException("Unsupported type " + obj.getClass());
        }
    }

    /* renamed from: a */
    private void m25558a(int i, String str, Bundle bundle) {
        switch (i) {
            case 0:
                bundle.putParcelable(str, null);
                return;
            case 1:
                bundle.putBundle(str, mo25534l());
                return;
            case 2:
                bundle.putBundle(str, mo25534l());
                return;
            case 3:
                bundle.putString(str, mo25538h());
                return;
            case 4:
                bundle.putStringArray(str, (String[]) m25576b(new String[0]));
                return;
            case 5:
                bundle.putBoolean(str, mo25533m());
                return;
            case 6:
                bundle.putBooleanArray(str, m25572n());
                return;
            case 7:
                bundle.putDouble(str, mo25539g());
                return;
            case 8:
                bundle.putDoubleArray(str, m25568r());
                return;
            case 9:
                bundle.putInt(str, mo25542d());
                return;
            case 10:
                bundle.putIntArray(str, m25571o());
                return;
            case 11:
                bundle.putLong(str, mo25541e());
                return;
            case 12:
                bundle.putLongArray(str, m25570p());
                return;
            case 13:
                bundle.putFloat(str, mo25540f());
                return;
            case 14:
                bundle.putFloatArray(str, m25569q());
                return;
            default:
                throw new RuntimeException("Unknown type " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: VersionedParcelStream.java */
    /* renamed from: androidx.versionedparcelable.g$a */
    /* loaded from: classes.dex */
    public static class C0577a {

        /* renamed from: a */
        final ByteArrayOutputStream f104a = new ByteArrayOutputStream();

        /* renamed from: b */
        final DataOutputStream f105b = new DataOutputStream(this.f104a);

        /* renamed from: c */
        private final int f106c;

        /* renamed from: d */
        private final DataOutputStream f107d;

        C0577a(int i, DataOutputStream dataOutputStream) {
            this.f106c = i;
            this.f107d = dataOutputStream;
        }

        /* renamed from: a */
        void m25532a() throws IOException {
            this.f105b.flush();
            int size = this.f104a.size();
            this.f107d.writeInt((this.f106c << 16) | (size >= 65535 ? 65535 : size));
            if (size >= 65535) {
                this.f107d.writeInt(size);
            }
            this.f104a.writeTo(this.f107d);
        }
    }

    /* compiled from: VersionedParcelStream.java */
    /* renamed from: androidx.versionedparcelable.g$b */
    /* loaded from: classes.dex */
    private static class C0578b {

        /* renamed from: a */
        final DataInputStream f108a;

        /* renamed from: b */
        final int f109b;

        /* renamed from: c */
        private final int f110c;

        C0578b(int i, int i2, DataInputStream dataInputStream) throws IOException {
            this.f110c = i2;
            this.f109b = i;
            byte[] bArr = new byte[this.f110c];
            dataInputStream.readFully(bArr);
            this.f108a = new DataInputStream(new ByteArrayInputStream(bArr));
        }
    }
}
