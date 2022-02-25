package p110z1;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.3")
@Unsigned
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0096\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, m8860e = {"Lkotlin/ULongArray;", "", "Lkotlin/ULong;", "size", "", "constructor-impl", "(I)[J", "storage", "", "([J)[J", "getSize-impl", "([J)I", "storage$annotations", "()V", "contains", "", "element", "contains-VKZWuLQ", "([JJ)Z", "containsAll", "elements", "containsAll-impl", "([JLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([JI)J", "hashCode", "isEmpty", "isEmpty-impl", "([J)Z", "iterator", "Lkotlin/collections/ULongIterator;", "iterator-impl", "([J)Lkotlin/collections/ULongIterator;", "set", "", SizeSelector.SIZE_KEY, "set-k8EXiF4", "([JIJ)V", "toString", "", "Iterator", "kotlin-stdlib"})
/* renamed from: z1.bxt */
/* loaded from: classes3.dex */
public final class ULongArray implements Collection<ULong>, KMarkers {
    @NotNull

    /* renamed from: a */
    private final long[] f20389a;

    /* renamed from: a */
    public static boolean m8497a(long[] jArr, @dbs Object obj) {
        return (obj instanceof ULongArray) && cji.m5184a(jArr, ((ULongArray) obj).m8489d());
    }

    /* renamed from: a */
    public static final boolean m8495a(@NotNull long[] jArr, @NotNull long[] jArr2) {
        cji.m5162f(jArr, "p1");
        cji.m5162f(jArr2, "p2");
        throw null;
    }

    @bwt
    /* renamed from: c */
    public static /* synthetic */ void m8491c() {
    }

    @bwt
    @NotNull
    /* renamed from: d */
    public static long[] m8488d(@NotNull long[] jArr) {
        cji.m5162f(jArr, "storage");
        return jArr;
    }

    @NotNull
    /* renamed from: f */
    public static String m8486f(long[] jArr) {
        return "ULongArray(storage=" + Arrays.toString(jArr) + ")";
    }

    /* renamed from: g */
    public static int m8485g(long[] jArr) {
        if (jArr != null) {
            return Arrays.hashCode(jArr);
        }
        return 0;
    }

    /* renamed from: a */
    public int m8504a() {
        return m8501a(this.f20389a);
    }

    /* renamed from: a */
    public boolean m8502a(long j) {
        return m8498a(this.f20389a, j);
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(ULong bxsVar) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends ULong> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: b */
    public cbm iterator() {
        return m8492b(this.f20389a);
    }

    /* renamed from: b */
    public boolean m8493b(long j) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m8496a(this.f20389a, (Collection<ULong>) collection);
    }

    @NotNull
    /* renamed from: d */
    public final /* synthetic */ long[] m8489d() {
        return this.f20389a;
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m8497a(this.f20389a, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m8485g(this.f20389a);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m8490c(this.f20389a);
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
        return m8486f(this.f20389a);
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return m8502a(((ULong) obj).m8549b());
        }
        return false;
    }

    @Override // java.util.Collection
    public final int size() {
        return m8504a();
    }

    @bwt
    private /* synthetic */ ULongArray(@NotNull long[] jArr) {
        cji.m5162f(jArr, "storage");
        this.f20389a = jArr;
    }

    @NotNull
    /* renamed from: a */
    public static long[] m8503a(int i) {
        return m8488d(new long[i]);
    }

    /* renamed from: a */
    public static final long m8500a(long[] jArr, int i) {
        return ULong.m8548b(jArr[i]);
    }

    /* renamed from: a */
    public static final void m8499a(long[] jArr, int i, long j) {
        jArr[i] = j;
    }

    /* renamed from: a */
    public static int m8501a(long[] jArr) {
        return jArr.length;
    }

    @NotNull
    /* renamed from: b */
    public static cbm m8492b(long[] jArr) {
        return new C4811a(jArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ULongArray.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"Lkotlin/ULongArray$Iterator;", "Lkotlin/collections/ULongIterator;", "array", "", "([J)V", "index", "", "hasNext", "", "nextULong", "Lkotlin/ULong;", "()J", "kotlin-stdlib"})
    /* renamed from: z1.bxt$a */
    /* loaded from: classes3.dex */
    public static final class C4811a extends cbm {

        /* renamed from: a */
        private int f20390a;

        /* renamed from: b */
        private final long[] f20391b;

        public C4811a(@NotNull long[] jArr) {
            cji.m5162f(jArr, "array");
            this.f20391b = jArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20390a < this.f20391b.length;
        }

        @Override // p110z1.cbm
        /* renamed from: a */
        public long mo4670a() {
            int i = this.f20390a;
            long[] jArr = this.f20391b;
            if (i < jArr.length) {
                this.f20390a = i + 1;
                return ULong.m8548b(jArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(i));
        }
    }

    /* renamed from: a */
    public static boolean m8498a(long[] jArr, long j) {
        return bzb.m7497b(jArr, j);
    }

    /* renamed from: a */
    public static boolean m8496a(long[] jArr, @NotNull Collection<ULong> collection) {
        boolean z;
        cji.m5162f(collection, "elements");
        Collection<ULong> collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        for (Object obj : collection2) {
            if (!(obj instanceof ULong) || !bzb.m7497b(jArr, ((ULong) obj).m8549b())) {
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
    public static boolean m8490c(long[] jArr) {
        return jArr.length == 0;
    }
}
