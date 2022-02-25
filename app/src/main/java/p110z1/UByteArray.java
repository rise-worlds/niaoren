package p110z1;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0096\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, m8860e = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "size", "", "constructor-impl", "(I)[B", "storage", "", "([B)[B", "getSize-impl", "([B)I", "storage$annotations", "()V", "contains", "", "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([BI)B", "hashCode", "isEmpty", "isEmpty-impl", "([B)Z", "iterator", "Lkotlin/collections/UByteIterator;", "iterator-impl", "([B)Lkotlin/collections/UByteIterator;", "set", "", SizeSelector.SIZE_KEY, "set-VurrAj0", "([BIB)V", "toString", "", "Iterator", "kotlin-stdlib"})
/* renamed from: z1.bxl */
/* loaded from: classes3.dex */
public final class UByteArray implements Collection<UByte>, KMarkers {
    @NotNull

    /* renamed from: a */
    private final byte[] f20371a;

    /* renamed from: a */
    public static boolean m8655a(byte[] bArr, @dbs Object obj) {
        return (obj instanceof UByteArray) && cji.m5184a(bArr, ((UByteArray) obj).m8647d());
    }

    /* renamed from: a */
    public static final boolean m8653a(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        cji.m5162f(bArr, "p1");
        cji.m5162f(bArr2, "p2");
        throw null;
    }

    @bwt
    /* renamed from: c */
    public static /* synthetic */ void m8649c() {
    }

    @bwt
    @NotNull
    /* renamed from: d */
    public static byte[] m8646d(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "storage");
        return bArr;
    }

    @NotNull
    /* renamed from: f */
    public static String m8644f(byte[] bArr) {
        return "UByteArray(storage=" + Arrays.toString(bArr) + ")";
    }

    /* renamed from: g */
    public static int m8643g(byte[] bArr) {
        if (bArr != null) {
            return Arrays.hashCode(bArr);
        }
        return 0;
    }

    /* renamed from: a */
    public int m8662a() {
        return m8659a(this.f20371a);
    }

    /* renamed from: a */
    public boolean m8661a(byte b) {
        return m8658a(this.f20371a, b);
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(UByte bxkVar) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UByte> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public UIterators iterator() {
        return m8650b(this.f20371a);
    }

    /* renamed from: b */
    public boolean m8651b(byte b) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m8654a(this.f20371a, (Collection<UByte>) collection);
    }

    @NotNull
    /* renamed from: d */
    public final /* synthetic */ byte[] m8647d() {
        return this.f20371a;
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m8655a(this.f20371a, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m8643g(this.f20371a);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m8648c(this.f20371a);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.m5227a(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.m5225a(this, tArr);
    }

    public String toString() {
        return m8644f(this.f20371a);
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return m8661a(((UByte) obj).m8705b());
        }
        return false;
    }

    @Override // java.util.Collection
    public final int size() {
        return m8662a();
    }

    @bwt
    private /* synthetic */ UByteArray(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "storage");
        this.f20371a = bArr;
    }

    @NotNull
    /* renamed from: a */
    public static byte[] m8660a(int i) {
        return m8646d(new byte[i]);
    }

    /* renamed from: a */
    public static final byte m8657a(byte[] bArr, int i) {
        return UByte.m8704b(bArr[i]);
    }

    /* renamed from: a */
    public static final void m8656a(byte[] bArr, int i, byte b) {
        bArr[i] = b;
    }

    /* renamed from: a */
    public static int m8659a(byte[] bArr) {
        return bArr.length;
    }

    @NotNull
    /* renamed from: b */
    public static UIterators m8650b(byte[] bArr) {
        return new C4807a(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UByteArray.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"Lkotlin/UByteArray$Iterator;", "Lkotlin/collections/UByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextUByte", "Lkotlin/UByte;", "()B", "kotlin-stdlib"})
    /* renamed from: z1.bxl$a */
    /* loaded from: classes3.dex */
    public static final class C4807a extends UIterators {

        /* renamed from: a */
        private int f20372a;

        /* renamed from: b */
        private final byte[] f20373b;

        public C4807a(@NotNull byte[] bArr) {
            cji.m5162f(bArr, "array");
            this.f20373b = bArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20372a < this.f20373b.length;
        }

        @Override // p110z1.UIterators
        /* renamed from: a */
        public byte mo6292a() {
            int i = this.f20372a;
            byte[] bArr = this.f20373b;
            if (i < bArr.length) {
                this.f20372a = i + 1;
                return UByte.m8704b(bArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(i));
        }
    }

    /* renamed from: a */
    public static boolean m8658a(byte[] bArr, byte b) {
        return bzb.m7588b(bArr, b);
    }

    /* renamed from: a */
    public static boolean m8654a(byte[] bArr, @NotNull Collection<UByte> collection) {
        boolean z;
        cji.m5162f(collection, "elements");
        Collection<UByte> collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        for (Object obj : collection2) {
            if (!(obj instanceof UByte) || !bzb.m7588b(bArr, ((UByte) obj).m8705b())) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public static boolean m8648c(byte[] bArr) {
        return bArr.length == 0;
    }
}
