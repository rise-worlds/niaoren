package p110z1;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0096\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, m8860e = {"Lkotlin/UShortArray;", "", "Lkotlin/UShort;", "size", "", "constructor-impl", "(I)[S", "storage", "", "([S)[S", "getSize-impl", "([S)I", "storage$annotations", "()V", "contains", "", "element", "contains-xj2QHRw", "([SS)Z", "containsAll", "elements", "containsAll-impl", "([SLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([SI)S", "hashCode", "isEmpty", "isEmpty-impl", "([S)Z", "iterator", "Lkotlin/collections/UShortIterator;", "iterator-impl", "([S)Lkotlin/collections/UShortIterator;", "set", "", SizeSelector.SIZE_KEY, "set-01HTLdE", "([SIS)V", "toString", "", "Iterator", "kotlin-stdlib"})
/* renamed from: z1.bxz */
/* loaded from: classes3.dex */
public final class UShortArray implements Collection<UShort>, KMarkers {
    @NotNull

    /* renamed from: a */
    private final short[] f20399a;

    /* renamed from: a */
    public static boolean m8392a(short[] sArr, @dbs Object obj) {
        return (obj instanceof UShortArray) && cji.m5184a(sArr, ((UShortArray) obj).m8383d());
    }

    /* renamed from: a */
    public static final boolean m8389a(@NotNull short[] sArr, @NotNull short[] sArr2) {
        cji.m5162f(sArr, "p1");
        cji.m5162f(sArr2, "p2");
        throw null;
    }

    @bwt
    /* renamed from: c */
    public static /* synthetic */ void m8385c() {
    }

    @bwt
    @NotNull
    /* renamed from: d */
    public static short[] m8382d(@NotNull short[] sArr) {
        cji.m5162f(sArr, "storage");
        return sArr;
    }

    @NotNull
    /* renamed from: f */
    public static String m8380f(short[] sArr) {
        return "UShortArray(storage=" + Arrays.toString(sArr) + ")";
    }

    /* renamed from: g */
    public static int m8379g(short[] sArr) {
        if (sArr != null) {
            return Arrays.hashCode(sArr);
        }
        return 0;
    }

    /* renamed from: a */
    public int m8398a() {
        return m8395a(this.f20399a);
    }

    /* renamed from: a */
    public boolean m8396a(short s) {
        return m8390a(this.f20399a, s);
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(UShort bxyVar) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UShort> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public cbn iterator() {
        return m8386b(this.f20399a);
    }

    /* renamed from: b */
    public boolean m8387b(short s) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m8391a(this.f20399a, (Collection<UShort>) collection);
    }

    @NotNull
    /* renamed from: d */
    public final /* synthetic */ short[] m8383d() {
        return this.f20399a;
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m8392a(this.f20399a, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m8379g(this.f20399a);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m8384c(this.f20399a);
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
        return m8380f(this.f20399a);
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return m8396a(((UShort) obj).m8441b());
        }
        return false;
    }

    @Override // java.util.Collection
    public final int size() {
        return m8398a();
    }

    @bwt
    private /* synthetic */ UShortArray(@NotNull short[] sArr) {
        cji.m5162f(sArr, "storage");
        this.f20399a = sArr;
    }

    @NotNull
    /* renamed from: a */
    public static short[] m8397a(int i) {
        return m8382d(new short[i]);
    }

    /* renamed from: a */
    public static final short m8394a(short[] sArr, int i) {
        return UShort.m8440b(sArr[i]);
    }

    /* renamed from: a */
    public static final void m8393a(short[] sArr, int i, short s) {
        sArr[i] = s;
    }

    /* renamed from: a */
    public static int m8395a(short[] sArr) {
        return sArr.length;
    }

    @NotNull
    /* renamed from: b */
    public static cbn m8386b(short[] sArr) {
        return new C4813a(sArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UShortArray.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"Lkotlin/UShortArray$Iterator;", "Lkotlin/collections/UShortIterator;", "array", "", "([S)V", "index", "", "hasNext", "", "nextUShort", "Lkotlin/UShort;", "()S", "kotlin-stdlib"})
    /* renamed from: z1.bxz$a */
    /* loaded from: classes3.dex */
    public static final class C4813a extends cbn {

        /* renamed from: a */
        private int f20400a;

        /* renamed from: b */
        private final short[] f20401b;

        public C4813a(@NotNull short[] sArr) {
            cji.m5162f(sArr, "array");
            this.f20401b = sArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20400a < this.f20401b.length;
        }

        @Override // p110z1.cbn
        /* renamed from: a */
        public short mo6280a() {
            int i = this.f20400a;
            short[] sArr = this.f20401b;
            if (i < sArr.length) {
                this.f20400a = i + 1;
                return UShort.m8440b(sArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(i));
        }
    }

    /* renamed from: a */
    public static boolean m8390a(short[] sArr, short s) {
        return bzb.m7442b(sArr, s);
    }

    /* renamed from: a */
    public static boolean m8391a(short[] sArr, @NotNull Collection<UShort> collection) {
        boolean z;
        cji.m5162f(collection, "elements");
        Collection<UShort> collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        for (Object obj : collection2) {
            if (!(obj instanceof UShort) || !bzb.m7442b(sArr, ((UShort) obj).m8441b())) {
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
    public static boolean m8384c(short[] sArr) {
        return sArr.length == 0;
    }
}
