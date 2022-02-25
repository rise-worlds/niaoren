package p110z1;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0096\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, m8860e = {"Lkotlin/UIntArray;", "", "Lkotlin/UInt;", "size", "", "constructor-impl", "(I)[I", "storage", "", "([I)[I", "getSize-impl", "([I)I", "storage$annotations", "()V", "contains", "", "element", "contains-WZ4Q5Ns", "([II)Z", "containsAll", "elements", "containsAll-impl", "([ILjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([II)I", "hashCode", "isEmpty", "isEmpty-impl", "([I)Z", "iterator", "Lkotlin/collections/UIntIterator;", "iterator-impl", "([I)Lkotlin/collections/UIntIterator;", "set", "", SizeSelector.SIZE_KEY, "set-VXSXFK8", "([III)V", "toString", "", "Iterator", "kotlin-stdlib"})
/* renamed from: z1.bxp */
/* loaded from: classes3.dex */
public final class UIntArray implements Collection<UInt>, KMarkers {
    @NotNull

    /* renamed from: a */
    private final int[] f20380a;

    /* renamed from: a */
    public static boolean m8579a(int[] iArr, @dbs Object obj) {
        return (obj instanceof UIntArray) && cji.m5184a(iArr, ((UIntArray) obj).m8569d());
    }

    /* renamed from: a */
    public static final boolean m8577a(@NotNull int[] iArr, @NotNull int[] iArr2) {
        cji.m5162f(iArr, "p1");
        cji.m5162f(iArr2, "p2");
        throw null;
    }

    @bwt
    /* renamed from: c */
    public static /* synthetic */ void m8572c() {
    }

    @bwt
    @NotNull
    /* renamed from: d */
    public static int[] m8568d(@NotNull int[] iArr) {
        cji.m5162f(iArr, "storage");
        return iArr;
    }

    @NotNull
    /* renamed from: f */
    public static String m8566f(int[] iArr) {
        return "UIntArray(storage=" + Arrays.toString(iArr) + ")";
    }

    /* renamed from: g */
    public static int m8565g(int[] iArr) {
        if (iArr != null) {
            return Arrays.hashCode(iArr);
        }
        return 0;
    }

    /* renamed from: a */
    public int m8584a() {
        return m8582a(this.f20380a);
    }

    /* renamed from: a */
    public boolean m8583a(int i) {
        return m8573b(this.f20380a, i);
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(UInt bxoVar) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UInt> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public cbl iterator() {
        return m8574b(this.f20380a);
    }

    /* renamed from: c */
    public boolean m8571c(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m8578a(this.f20380a, (Collection<UInt>) collection);
    }

    @NotNull
    /* renamed from: d */
    public final /* synthetic */ int[] m8569d() {
        return this.f20380a;
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m8579a(this.f20380a, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m8565g(this.f20380a);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m8570c(this.f20380a);
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
        return m8566f(this.f20380a);
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return m8583a(((UInt) obj).m8629b());
        }
        return false;
    }

    @Override // java.util.Collection
    public final int size() {
        return m8584a();
    }

    @bwt
    private /* synthetic */ UIntArray(@NotNull int[] iArr) {
        cji.m5162f(iArr, "storage");
        this.f20380a = iArr;
    }

    @NotNull
    /* renamed from: b */
    public static int[] m8575b(int i) {
        return m8568d(new int[i]);
    }

    /* renamed from: a */
    public static final int m8581a(int[] iArr, int i) {
        return UInt.m8628b(iArr[i]);
    }

    /* renamed from: a */
    public static final void m8580a(int[] iArr, int i, int i2) {
        iArr[i] = i2;
    }

    /* renamed from: a */
    public static int m8582a(int[] iArr) {
        return iArr.length;
    }

    @NotNull
    /* renamed from: b */
    public static cbl m8574b(int[] iArr) {
        return new C4809a(iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UIntArray.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"Lkotlin/UIntArray$Iterator;", "Lkotlin/collections/UIntIterator;", "array", "", "([I)V", "index", "", "hasNext", "", "nextUInt", "Lkotlin/UInt;", "()I", "kotlin-stdlib"})
    /* renamed from: z1.bxp$a */
    /* loaded from: classes3.dex */
    public static final class C4809a extends cbl {

        /* renamed from: a */
        private int f20381a;

        /* renamed from: b */
        private final int[] f20382b;

        public C4809a(@NotNull int[] iArr) {
            cji.m5162f(iArr, "array");
            this.f20382b = iArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20381a < this.f20382b.length;
        }

        @Override // p110z1.cbl
        /* renamed from: a */
        public int mo4681a() {
            int i = this.f20381a;
            int[] iArr = this.f20382b;
            if (i < iArr.length) {
                this.f20381a = i + 1;
                return UInt.m8628b(iArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(i));
        }
    }

    /* renamed from: b */
    public static boolean m8573b(int[] iArr, int i) {
        return bzb.m7516b(iArr, i);
    }

    /* renamed from: a */
    public static boolean m8578a(int[] iArr, @NotNull Collection<UInt> collection) {
        boolean z;
        cji.m5162f(collection, "elements");
        Collection<UInt> collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        for (Object obj : collection2) {
            if (!(obj instanceof UInt) || !bzb.m7516b(iArr, ((UInt) obj).m8629b())) {
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
    public static boolean m8570c(int[] iArr) {
        return iArr.length == 0;
    }
}
