package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p003v4.util.ArraySet;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: androidx.versionedparcelable.e */
/* loaded from: classes.dex */
public abstract class VersionedParcel {

    /* renamed from: a */
    private static final String f57a = "VersionedParcel";

    /* renamed from: b */
    private static final int f58b = -1;

    /* renamed from: c */
    private static final int f59c = -2;

    /* renamed from: d */
    private static final int f60d = -3;

    /* renamed from: e */
    private static final int f61e = -4;

    /* renamed from: f */
    private static final int f62f = -5;

    /* renamed from: g */
    private static final int f63g = -6;

    /* renamed from: h */
    private static final int f64h = -7;

    /* renamed from: i */
    private static final int f65i = -9;

    /* renamed from: j */
    private static final int f66j = 1;

    /* renamed from: k */
    private static final int f67k = 2;

    /* renamed from: l */
    private static final int f68l = 3;

    /* renamed from: m */
    private static final int f69m = 4;

    /* renamed from: n */
    private static final int f70n = 5;

    /* renamed from: a */
    protected abstract void mo25561a(double d);

    /* renamed from: a */
    protected abstract void mo25560a(float f);

    /* renamed from: a */
    protected abstract void mo25559a(int i);

    /* renamed from: a */
    protected abstract void mo25557a(long j);

    /* renamed from: a */
    protected abstract void mo25556a(Bundle bundle);

    /* renamed from: a */
    protected abstract void mo25555a(IBinder iBinder);

    /* renamed from: a */
    protected abstract void mo25554a(IInterface iInterface);

    /* renamed from: a */
    protected abstract void mo25553a(Parcelable parcelable);

    /* renamed from: a */
    protected abstract void mo25551a(String str);

    /* renamed from: a */
    protected abstract void mo25550a(boolean z);

    /* renamed from: a */
    public void mo25549a(boolean z, boolean z2) {
    }

    /* renamed from: a */
    protected abstract void mo25548a(byte[] bArr);

    /* renamed from: a */
    protected abstract void mo25547a(byte[] bArr, int i, int i2);

    /* renamed from: a */
    public boolean mo25562a() {
        return false;
    }

    /* renamed from: b */
    protected abstract void mo25546b();

    /* renamed from: b */
    protected abstract boolean mo25545b(int i);

    /* renamed from: c */
    protected abstract VersionedParcel mo25544c();

    /* renamed from: c */
    protected abstract void mo25543c(int i);

    /* renamed from: d */
    protected abstract int mo25542d();

    /* renamed from: e */
    protected abstract long mo25541e();

    /* renamed from: f */
    protected abstract float mo25540f();

    /* renamed from: g */
    protected abstract double mo25539g();

    /* renamed from: h */
    protected abstract String mo25538h();

    /* renamed from: i */
    protected abstract IBinder mo25537i();

    /* renamed from: j */
    protected abstract byte[] mo25536j();

    /* renamed from: k */
    protected abstract <T extends Parcelable> T mo25535k();

    /* renamed from: l */
    protected abstract Bundle mo25534l();

    /* renamed from: m */
    protected abstract boolean mo25533m();

    /* renamed from: a */
    public void m25636a(IInterface iInterface, int i) {
        mo25543c(i);
        mo25554a(iInterface);
    }

    /* renamed from: a */
    public void m25638a(Bundle bundle, int i) {
        mo25543c(i);
        mo25556a(bundle);
    }

    /* renamed from: a */
    public void m25617a(boolean z, int i) {
        mo25543c(i);
        mo25550a(z);
    }

    /* renamed from: a */
    public void m25616a(byte[] bArr, int i) {
        mo25543c(i);
        mo25548a(bArr);
    }

    /* renamed from: a */
    public void m25615a(byte[] bArr, int i, int i2, int i3) {
        mo25543c(i3);
        mo25547a(bArr, i, i2);
    }

    /* renamed from: a */
    public void m25642a(int i, int i2) {
        mo25543c(i2);
        mo25559a(i);
    }

    /* renamed from: a */
    public void m25639a(long j, int i) {
        mo25543c(i);
        mo25557a(j);
    }

    /* renamed from: a */
    public void m25643a(float f, int i) {
        mo25543c(i);
        mo25560a(f);
    }

    /* renamed from: a */
    public void m25644a(double d, int i) {
        mo25543c(i);
        mo25561a(d);
    }

    /* renamed from: a */
    public void m25623a(String str, int i) {
        mo25543c(i);
        mo25551a(str);
    }

    /* renamed from: a */
    public void m25637a(IBinder iBinder, int i) {
        mo25543c(i);
        mo25555a(iBinder);
    }

    /* renamed from: a */
    public void m25635a(Parcelable parcelable, int i) {
        mo25543c(i);
        mo25553a(parcelable);
    }

    /* renamed from: b */
    public boolean m25583b(boolean z, int i) {
        return !mo25545b(i) ? z : mo25533m();
    }

    /* renamed from: b */
    public int m25598b(int i, int i2) {
        return !mo25545b(i2) ? i : mo25542d();
    }

    /* renamed from: b */
    public long m25596b(long j, int i) {
        return !mo25545b(i) ? j : mo25541e();
    }

    /* renamed from: b */
    public float m25599b(float f, int i) {
        return !mo25545b(i) ? f : mo25540f();
    }

    /* renamed from: b */
    public double m25600b(double d, int i) {
        return !mo25545b(i) ? d : mo25539g();
    }

    /* renamed from: b */
    public String m25586b(String str, int i) {
        return !mo25545b(i) ? str : mo25538h();
    }

    /* renamed from: b */
    public IBinder m25594b(IBinder iBinder, int i) {
        return !mo25545b(i) ? iBinder : mo25537i();
    }

    /* renamed from: b */
    public byte[] m25582b(byte[] bArr, int i) {
        return !mo25545b(i) ? bArr : mo25536j();
    }

    /* renamed from: b */
    public <T extends Parcelable> T m25593b(T t, int i) {
        return !mo25545b(i) ? t : (T) mo25535k();
    }

    /* renamed from: b */
    public Bundle m25595b(Bundle bundle, int i) {
        return !mo25545b(i) ? bundle : mo25534l();
    }

    /* renamed from: a */
    public void m25645a(byte b, int i) {
        mo25543c(i);
        mo25559a((int) b);
    }

    @RequiresApi(api = 21)
    /* renamed from: a */
    public void m25634a(Size size, int i) {
        mo25543c(i);
        mo25550a(size != null);
        if (size != null) {
            mo25559a(size.getWidth());
            mo25559a(size.getHeight());
        }
    }

    @RequiresApi(api = 21)
    /* renamed from: a */
    public void m25633a(SizeF sizeF, int i) {
        mo25543c(i);
        mo25550a(sizeF != null);
        if (sizeF != null) {
            mo25560a(sizeF.getWidth());
            mo25560a(sizeF.getHeight());
        }
    }

    /* renamed from: a */
    public void m25632a(SparseBooleanArray sparseBooleanArray, int i) {
        mo25543c(i);
        if (sparseBooleanArray == null) {
            mo25559a(-1);
            return;
        }
        int size = sparseBooleanArray.size();
        mo25559a(size);
        for (int i2 = 0; i2 < size; i2++) {
            mo25559a(sparseBooleanArray.keyAt(i2));
            mo25550a(sparseBooleanArray.valueAt(i2));
        }
    }

    /* renamed from: a */
    public void m25602a(boolean[] zArr, int i) {
        mo25543c(i);
        m25603a(zArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m25603a(boolean[] zArr) {
        if (zArr != null) {
            mo25559a(zArr.length);
            for (boolean z : zArr) {
                mo25559a(z ? 1 : 0);
            }
            return;
        }
        mo25559a(-1);
    }

    /* renamed from: b */
    public boolean[] m25574b(boolean[] zArr, int i) {
        return !mo25545b(i) ? zArr : m25572n();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: n */
    public boolean[] m25572n() {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        boolean[] zArr = new boolean[d];
        for (int i = 0; i < d; i++) {
            zArr[i] = mo25542d() != 0;
        }
        return zArr;
    }

    /* renamed from: a */
    public void m25614a(char[] cArr, int i) {
        mo25543c(i);
        if (cArr != null) {
            mo25559a(cArr.length);
            for (char c : cArr) {
                mo25559a((int) c);
            }
            return;
        }
        mo25559a(-1);
    }

    /* renamed from: b */
    public char[] m25581b(char[] cArr, int i) {
        if (!mo25545b(i)) {
            return cArr;
        }
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        char[] cArr2 = new char[d];
        for (int i2 = 0; i2 < d; i2++) {
            cArr2[i2] = (char) mo25542d();
        }
        return cArr2;
    }

    /* renamed from: a */
    public void m25608a(int[] iArr, int i) {
        mo25543c(i);
        m25609a(iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m25609a(int[] iArr) {
        if (iArr != null) {
            mo25559a(iArr.length);
            for (int i : iArr) {
                mo25559a(i);
            }
            return;
        }
        mo25559a(-1);
    }

    /* renamed from: b */
    public int[] m25578b(int[] iArr, int i) {
        return !mo25545b(i) ? iArr : m25571o();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: o */
    public int[] m25571o() {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        int[] iArr = new int[d];
        for (int i = 0; i < d; i++) {
            iArr[i] = mo25542d();
        }
        return iArr;
    }

    /* renamed from: a */
    public void m25606a(long[] jArr, int i) {
        mo25543c(i);
        m25607a(jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m25607a(long[] jArr) {
        if (jArr != null) {
            mo25559a(jArr.length);
            for (long j : jArr) {
                mo25557a(j);
            }
            return;
        }
        mo25559a(-1);
    }

    /* renamed from: b */
    public long[] m25577b(long[] jArr, int i) {
        return !mo25545b(i) ? jArr : m25570p();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: p */
    public long[] m25570p() {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        long[] jArr = new long[d];
        for (int i = 0; i < d; i++) {
            jArr[i] = mo25541e();
        }
        return jArr;
    }

    /* renamed from: a */
    public void m25610a(float[] fArr, int i) {
        mo25543c(i);
        m25611a(fArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m25611a(float[] fArr) {
        if (fArr != null) {
            mo25559a(fArr.length);
            for (float f : fArr) {
                mo25560a(f);
            }
            return;
        }
        mo25559a(-1);
    }

    /* renamed from: b */
    public float[] m25579b(float[] fArr, int i) {
        return !mo25545b(i) ? fArr : m25569q();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: q */
    public float[] m25569q() {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        float[] fArr = new float[d];
        for (int i = 0; i < d; i++) {
            fArr[i] = mo25540f();
        }
        return fArr;
    }

    /* renamed from: a */
    public void m25612a(double[] dArr, int i) {
        mo25543c(i);
        m25613a(dArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m25613a(double[] dArr) {
        if (dArr != null) {
            mo25559a(dArr.length);
            for (double d : dArr) {
                mo25561a(d);
            }
            return;
        }
        mo25559a(-1);
    }

    /* renamed from: b */
    public double[] m25580b(double[] dArr, int i) {
        return !mo25545b(i) ? dArr : m25568r();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: r */
    public double[] m25568r() {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        double[] dArr = new double[d];
        for (int i = 0; i < d; i++) {
            dArr[i] = mo25539g();
        }
        return dArr;
    }

    /* renamed from: a */
    public <T> void m25618a(Set<T> set, int i) {
        m25620a((Collection) set, i);
    }

    /* renamed from: a */
    public <T> void m25619a(List<T> list, int i) {
        m25620a((Collection) list, i);
    }

    /* renamed from: a */
    private <T> void m25620a(Collection<T> collection, int i) {
        mo25543c(i);
        if (collection == null) {
            mo25559a(-1);
            return;
        }
        int size = collection.size();
        mo25559a(size);
        if (size > 0) {
            int a = m25624a((VersionedParcel) collection.iterator().next());
            mo25559a(a);
            switch (a) {
                case 1:
                    for (T t : collection) {
                        m25631a(t);
                    }
                    return;
                case 2:
                    for (T t2 : collection) {
                        mo25553a(t2);
                    }
                    return;
                case 3:
                    for (T t3 : collection) {
                        m25628a(t3);
                    }
                    return;
                case 4:
                    for (T t4 : collection) {
                        mo25551a(t4);
                    }
                    return;
                case 5:
                    for (T t5 : collection) {
                        mo25555a(t5);
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public <T> void m25604a(T[] tArr, int i) {
        mo25543c(i);
        m25605a((Object[]) tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public <T> void m25605a(T[] tArr) {
        if (tArr == null) {
            mo25559a(-1);
            return;
        }
        int length = tArr.length;
        mo25559a(length);
        if (length > 0) {
            int i = 0;
            int a = m25624a((VersionedParcel) tArr[0]);
            mo25559a(a);
            switch (a) {
                case 1:
                    while (i < length) {
                        m25631a((VersionedParcelable) tArr[i]);
                        i++;
                    }
                    return;
                case 2:
                    while (i < length) {
                        mo25553a((Parcelable) tArr[i]);
                        i++;
                    }
                    return;
                case 3:
                    while (i < length) {
                        m25628a((Serializable) tArr[i]);
                        i++;
                    }
                    return;
                case 4:
                    while (i < length) {
                        mo25551a((String) tArr[i]);
                        i++;
                    }
                    return;
                case 5:
                    while (i < length) {
                        mo25555a((IBinder) tArr[i]);
                        i++;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private <T> int m25624a(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof VersionedParcelable) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        throw new IllegalArgumentException(t.getClass().getName() + " cannot be VersionedParcelled");
    }

    /* renamed from: a */
    public void m25630a(VersionedParcelable hVar, int i) {
        mo25543c(i);
        m25631a(hVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m25631a(VersionedParcelable hVar) {
        if (hVar == null) {
            mo25551a((String) null);
            return;
        }
        m25589b(hVar);
        VersionedParcel c = mo25544c();
        m25629a(hVar, c);
        c.mo25546b();
    }

    /* renamed from: b */
    private void m25589b(VersionedParcelable hVar) {
        try {
            mo25551a(m25626a((Class<? extends VersionedParcelable>) hVar.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(hVar.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    /* renamed from: a */
    public void m25627a(Serializable serializable, int i) {
        mo25543c(i);
        m25628a(serializable);
    }

    /* renamed from: a */
    private void m25628a(Serializable serializable) {
        if (serializable == null) {
            mo25551a((String) null);
            return;
        }
        String name = serializable.getClass().getName();
        mo25551a(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            mo25548a(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + name + ")", e);
        }
    }

    /* renamed from: a */
    public void m25625a(Exception exc, int i) {
        mo25543c(i);
        if (exc == null) {
            m25567s();
            return;
        }
        int i2 = 0;
        if ((exc instanceof Parcelable) && exc.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            i2 = -9;
        } else if (exc instanceof SecurityException) {
            i2 = -1;
        } else if (exc instanceof BadParcelableException) {
            i2 = -2;
        } else if (exc instanceof IllegalArgumentException) {
            i2 = -3;
        } else if (exc instanceof NullPointerException) {
            i2 = -4;
        } else if (exc instanceof IllegalStateException) {
            i2 = -5;
        } else if (exc instanceof NetworkOnMainThreadException) {
            i2 = -6;
        } else if (exc instanceof UnsupportedOperationException) {
            i2 = -7;
        }
        mo25559a(i2);
        if (i2 != 0) {
            mo25551a(exc.getMessage());
            if (i2 == -9) {
                mo25553a((Parcelable) exc);
            }
        } else if (exc instanceof RuntimeException) {
            throw ((RuntimeException) exc);
        } else {
            throw new RuntimeException(exc);
        }
    }

    /* renamed from: s */
    protected void m25567s() {
        mo25559a(0);
    }

    /* renamed from: b */
    public Exception m25587b(Exception exc, int i) {
        int v;
        return (mo25545b(i) && (v = m25564v()) != 0) ? m25641a(v, mo25538h()) : exc;
    }

    /* renamed from: v */
    private int m25564v() {
        return mo25542d();
    }

    /* renamed from: a */
    private Exception m25641a(int i, String str) {
        return m25597b(i, str);
    }

    @NonNull
    /* renamed from: a */
    protected static Throwable m25621a(@NonNull Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    /* renamed from: b */
    private Exception m25597b(int i, String str) {
        switch (i) {
            case -9:
                return (Exception) mo25535k();
            case -8:
            default:
                return new RuntimeException("Unknown exception code: " + i + " msg " + str);
            case -7:
                return new UnsupportedOperationException(str);
            case -6:
                return new NetworkOnMainThreadException();
            case -5:
                return new IllegalStateException(str);
            case -4:
                return new NullPointerException(str);
            case -3:
                return new IllegalArgumentException(str);
            case -2:
                return new BadParcelableException(str);
            case -1:
                return new SecurityException(str);
        }
    }

    /* renamed from: b */
    public byte m25601b(byte b, int i) {
        return !mo25545b(i) ? b : (byte) (mo25542d() & 255);
    }

    @RequiresApi(api = 21)
    /* renamed from: b */
    public Size m25592b(Size size, int i) {
        if (!mo25545b(i)) {
            return size;
        }
        if (mo25533m()) {
            return new Size(mo25542d(), mo25542d());
        }
        return null;
    }

    @RequiresApi(api = 21)
    /* renamed from: b */
    public SizeF m25591b(SizeF sizeF, int i) {
        if (!mo25545b(i)) {
            return sizeF;
        }
        if (mo25533m()) {
            return new SizeF(mo25540f(), mo25540f());
        }
        return null;
    }

    /* renamed from: b */
    public SparseBooleanArray m25590b(SparseBooleanArray sparseBooleanArray, int i) {
        if (!mo25545b(i)) {
            return sparseBooleanArray;
        }
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray(d);
        for (int i2 = 0; i2 < d; i2++) {
            sparseBooleanArray2.put(mo25542d(), mo25533m());
        }
        return sparseBooleanArray2;
    }

    /* renamed from: b */
    public <T> Set<T> m25584b(Set<T> set, int i) {
        return !mo25545b(i) ? set : (Set) m25640a(i, (int) new ArraySet());
    }

    /* renamed from: b */
    public <T> List<T> m25585b(List<T> list, int i) {
        return !mo25545b(i) ? list : (List) m25640a(i, (int) new ArrayList());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    private <T, S extends Collection<T>> S m25640a(int i, S s) {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        if (d != 0) {
            int d2 = mo25542d();
            if (d >= 0) {
                switch (d2) {
                    case 1:
                        while (d > 0) {
                            s.add(m25566t());
                            d--;
                        }
                        break;
                    case 2:
                        while (d > 0) {
                            s.add(mo25535k());
                            d--;
                        }
                        break;
                    case 3:
                        while (d > 0) {
                            s.add(m25565u());
                            d--;
                        }
                        break;
                    case 4:
                        while (d > 0) {
                            s.add(mo25538h());
                            d--;
                        }
                        break;
                    case 5:
                        while (d > 0) {
                            s.add(mo25537i());
                            d--;
                        }
                        break;
                }
            } else {
                return null;
            }
        }
        return s;
    }

    /* renamed from: b */
    public <T> T[] m25575b(T[] tArr, int i) {
        return !mo25545b(i) ? tArr : (T[]) m25576b(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: b */
    public <T> T[] m25576b(T[] tArr) {
        int d = mo25542d();
        if (d < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d);
        if (d != 0) {
            int d2 = mo25542d();
            if (d >= 0) {
                switch (d2) {
                    case 1:
                        while (d > 0) {
                            arrayList.add(m25566t());
                            d--;
                        }
                        break;
                    case 2:
                        while (d > 0) {
                            arrayList.add(mo25535k());
                            d--;
                        }
                        break;
                    case 3:
                        while (d > 0) {
                            arrayList.add(m25565u());
                            d--;
                        }
                        break;
                    case 4:
                        while (d > 0) {
                            arrayList.add(mo25538h());
                            d--;
                        }
                        break;
                    case 5:
                        while (d > 0) {
                            arrayList.add(mo25537i());
                            d--;
                        }
                        break;
                }
            } else {
                return null;
            }
        }
        return (T[]) arrayList.toArray(tArr);
    }

    /* renamed from: b */
    public <T extends VersionedParcelable> T m25588b(T t, int i) {
        return !mo25545b(i) ? t : (T) m25566t();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: t */
    public <T extends VersionedParcelable> T m25566t() {
        String h = mo25538h();
        if (h == null) {
            return null;
        }
        return (T) m25622a(h, mo25544c());
    }

    /* renamed from: u */
    protected Serializable m25565u() {
        String h = mo25538h();
        if (h == null) {
            return null;
        }
        try {
            return (Serializable) new ObjectInputStream(new ByteArrayInputStream(mo25536j())) { // from class: androidx.versionedparcelable.e.1
                @Override // java.io.ObjectInputStream
                protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                    Class<?> cls = Class.forName(objectStreamClass.getName(), false, getClass().getClassLoader());
                    return cls != null ? cls : super.resolveClass(objectStreamClass);
                }
            }.readObject();
        } catch (IOException e) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + h + ")", e);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + h + ")", e2);
        }
    }

    /* renamed from: a */
    protected static <T extends VersionedParcelable> T m25622a(String str, VersionedParcel eVar) {
        try {
            return (T) ((VersionedParcelable) Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class).invoke(null, eVar));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    /* renamed from: a */
    protected static <T extends VersionedParcelable> void m25629a(T t, VersionedParcel eVar) {
        try {
            m25573c(t).getDeclaredMethod("write", t.getClass(), VersionedParcel.class).invoke(null, t, eVar);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    /* renamed from: c */
    private static <T extends VersionedParcelable> Class m25573c(T t) throws ClassNotFoundException {
        return m25626a((Class<? extends VersionedParcelable>) t.getClass());
    }

    /* renamed from: a */
    private static Class m25626a(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        return Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
    }

    /* compiled from: VersionedParcel.java */
    /* renamed from: androidx.versionedparcelable.e$a */
    /* loaded from: classes.dex */
    public static class C0576a extends RuntimeException {
        public C0576a(Throwable th) {
            super(th);
        }
    }
}
