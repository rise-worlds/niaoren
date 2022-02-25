package p110z1;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* renamed from: z1.qe */
/* loaded from: classes3.dex */
public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() { // from class: z1.qe.1
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> comparator;
    private LinkedHashTreeMap<K, V>.C5464c entrySet;
    final C5469f<K, V> header;
    private LinkedHashTreeMap<K, V>.C5466d keySet;
    int modCount;
    int size;
    C5469f<K, V>[] table;
    int threshold;

    private static int secondaryHash(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    public LinkedHashTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator == null ? NATURAL_ORDER : comparator;
        this.header = new C5469f<>();
        this.table = new C5469f[16];
        C5469f<K, V>[] fVarArr = this.table;
        this.threshold = (fVarArr.length / 2) + (fVarArr.length / 4);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        C5469f<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            return findByObject.f22878h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k != null) {
            C5469f<K, V> find = find(k, true);
            V v2 = find.f22878h;
            find.f22878h = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.table, (Object) null);
        this.size = 0;
        this.modCount++;
        C5469f<K, V> fVar = this.header;
        C5469f<K, V> fVar2 = fVar.f22874d;
        while (fVar2 != fVar) {
            C5469f<K, V> fVar3 = fVar2.f22874d;
            fVar2.f22875e = null;
            fVar2.f22874d = null;
            fVar2 = fVar3;
        }
        fVar.f22875e = fVar;
        fVar.f22874d = fVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        C5469f<K, V> removeInternalByKey = removeInternalByKey(obj);
        if (removeInternalByKey != null) {
            return removeInternalByKey.f22878h;
        }
        return null;
    }

    C5469f<K, V> find(K k, boolean z) {
        int i;
        C5469f<K, V> fVar;
        C5469f<K, V> fVar2;
        int i2;
        Comparator<? super K> comparator = this.comparator;
        C5469f<K, V>[] fVarArr = this.table;
        int secondaryHash = secondaryHash(k.hashCode());
        int length = (fVarArr.length - 1) & secondaryHash;
        C5469f<K, V> fVar3 = fVarArr[length];
        if (fVar3 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i2 = comparable.compareTo(fVar3.f22876f);
                } else {
                    i2 = comparator.compare(k, (K) fVar3.f22876f);
                }
                if (i2 == 0) {
                    return fVar3;
                }
                C5469f<K, V> fVar4 = i2 < 0 ? fVar3.f22872b : fVar3.f22873c;
                if (fVar4 == null) {
                    fVar = fVar3;
                    i = i2;
                    break;
                }
                fVar3 = fVar4;
            }
        } else {
            fVar = fVar3;
            i = 0;
        }
        if (!z) {
            return null;
        }
        C5469f<K, V> fVar5 = this.header;
        if (fVar != null) {
            fVar2 = new C5469f<>(fVar, k, secondaryHash, fVar5, fVar5.f22875e);
            if (i < 0) {
                fVar.f22872b = fVar2;
            } else {
                fVar.f22873c = fVar2;
            }
            rebalance(fVar, true);
        } else if (comparator != NATURAL_ORDER || (k instanceof Comparable)) {
            fVar2 = new C5469f<>(fVar, k, secondaryHash, fVar5, fVar5.f22875e);
            fVarArr[length] = fVar2;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        int i3 = this.size;
        this.size = i3 + 1;
        if (i3 > this.threshold) {
            doubleCapacity();
        }
        this.modCount++;
        return fVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    C5469f<K, V> findByObject(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return find(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    C5469f<K, V> findByEntry(Map.Entry<?, ?> entry) {
        C5469f<K, V> findByObject = findByObject(entry.getKey());
        if (findByObject != null && equal(findByObject.f22878h, entry.getValue())) {
            return findByObject;
        }
        return null;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    void removeInternal(C5469f<K, V> fVar, boolean z) {
        int i;
        if (z) {
            fVar.f22875e.f22874d = fVar.f22874d;
            fVar.f22874d.f22875e = fVar.f22875e;
            fVar.f22875e = null;
            fVar.f22874d = null;
        }
        C5469f<K, V> fVar2 = fVar.f22872b;
        C5469f<K, V> fVar3 = fVar.f22873c;
        C5469f<K, V> fVar4 = fVar.f22871a;
        int i2 = 0;
        if (fVar2 == null || fVar3 == null) {
            if (fVar2 != null) {
                replaceInParent(fVar, fVar2);
                fVar.f22872b = null;
            } else if (fVar3 != null) {
                replaceInParent(fVar, fVar3);
                fVar.f22873c = null;
            } else {
                replaceInParent(fVar, null);
            }
            rebalance(fVar4, false);
            this.size--;
            this.modCount++;
            return;
        }
        C5469f<K, V> b = fVar2.f22879i > fVar3.f22879i ? fVar2.m1363b() : fVar3.m1364a();
        removeInternal(b, false);
        C5469f<K, V> fVar5 = fVar.f22872b;
        if (fVar5 != null) {
            i = fVar5.f22879i;
            b.f22872b = fVar5;
            fVar5.f22871a = b;
            fVar.f22872b = null;
        } else {
            i = 0;
        }
        C5469f<K, V> fVar6 = fVar.f22873c;
        if (fVar6 != null) {
            i2 = fVar6.f22879i;
            b.f22873c = fVar6;
            fVar6.f22871a = b;
            fVar.f22873c = null;
        }
        b.f22879i = Math.max(i, i2) + 1;
        replaceInParent(fVar, b);
    }

    C5469f<K, V> removeInternalByKey(Object obj) {
        C5469f<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    private void replaceInParent(C5469f<K, V> fVar, C5469f<K, V> fVar2) {
        C5469f<K, V> fVar3 = fVar.f22871a;
        fVar.f22871a = null;
        if (fVar2 != null) {
            fVar2.f22871a = fVar3;
        }
        if (fVar3 == null) {
            int i = fVar.f22877g;
            C5469f<K, V>[] fVarArr = this.table;
            fVarArr[i & (fVarArr.length - 1)] = fVar2;
        } else if (fVar3.f22872b == fVar) {
            fVar3.f22872b = fVar2;
        } else {
            fVar3.f22873c = fVar2;
        }
    }

    private void rebalance(C5469f<K, V> fVar, boolean z) {
        while (fVar != null) {
            C5469f<K, V> fVar2 = fVar.f22872b;
            C5469f<K, V> fVar3 = fVar.f22873c;
            int i = 0;
            int i2 = fVar2 != null ? fVar2.f22879i : 0;
            int i3 = fVar3 != null ? fVar3.f22879i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                C5469f<K, V> fVar4 = fVar3.f22872b;
                C5469f<K, V> fVar5 = fVar3.f22873c;
                int i5 = fVar5 != null ? fVar5.f22879i : 0;
                if (fVar4 != null) {
                    i = fVar4.f22879i;
                }
                int i6 = i - i5;
                if (i6 == -1 || (i6 == 0 && !z)) {
                    rotateLeft(fVar);
                } else {
                    rotateRight(fVar3);
                    rotateLeft(fVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                C5469f<K, V> fVar6 = fVar2.f22872b;
                C5469f<K, V> fVar7 = fVar2.f22873c;
                int i7 = fVar7 != null ? fVar7.f22879i : 0;
                if (fVar6 != null) {
                    i = fVar6.f22879i;
                }
                int i8 = i - i7;
                if (i8 == 1 || (i8 == 0 && !z)) {
                    rotateRight(fVar);
                } else {
                    rotateLeft(fVar2);
                    rotateRight(fVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                fVar.f22879i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                fVar.f22879i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            fVar = fVar.f22871a;
        }
    }

    private void rotateLeft(C5469f<K, V> fVar) {
        C5469f<K, V> fVar2 = fVar.f22872b;
        C5469f<K, V> fVar3 = fVar.f22873c;
        C5469f<K, V> fVar4 = fVar3.f22872b;
        C5469f<K, V> fVar5 = fVar3.f22873c;
        fVar.f22873c = fVar4;
        if (fVar4 != null) {
            fVar4.f22871a = fVar;
        }
        replaceInParent(fVar, fVar3);
        fVar3.f22872b = fVar;
        fVar.f22871a = fVar3;
        int i = 0;
        fVar.f22879i = Math.max(fVar2 != null ? fVar2.f22879i : 0, fVar4 != null ? fVar4.f22879i : 0) + 1;
        int i2 = fVar.f22879i;
        if (fVar5 != null) {
            i = fVar5.f22879i;
        }
        fVar3.f22879i = Math.max(i2, i) + 1;
    }

    private void rotateRight(C5469f<K, V> fVar) {
        C5469f<K, V> fVar2 = fVar.f22872b;
        C5469f<K, V> fVar3 = fVar.f22873c;
        C5469f<K, V> fVar4 = fVar2.f22872b;
        C5469f<K, V> fVar5 = fVar2.f22873c;
        fVar.f22872b = fVar5;
        if (fVar5 != null) {
            fVar5.f22871a = fVar;
        }
        replaceInParent(fVar, fVar2);
        fVar2.f22873c = fVar;
        fVar.f22871a = fVar2;
        int i = 0;
        fVar.f22879i = Math.max(fVar3 != null ? fVar3.f22879i : 0, fVar5 != null ? fVar5.f22879i : 0) + 1;
        int i2 = fVar.f22879i;
        if (fVar4 != null) {
            i = fVar4.f22879i;
        }
        fVar2.f22879i = Math.max(i2, i) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.C5464c cVar = this.entrySet;
        if (cVar != null) {
            return cVar;
        }
        LinkedHashTreeMap<K, V>.C5464c cVar2 = new C5464c();
        this.entrySet = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.C5466d dVar = this.keySet;
        if (dVar != null) {
            return dVar;
        }
        LinkedHashTreeMap<K, V>.C5466d dVar2 = new C5466d();
        this.keySet = dVar2;
        return dVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LinkedHashTreeMap.java */
    /* renamed from: z1.qe$f */
    /* loaded from: classes3.dex */
    public static final class C5469f<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        C5469f<K, V> f22871a;

        /* renamed from: b */
        C5469f<K, V> f22872b;

        /* renamed from: c */
        C5469f<K, V> f22873c;

        /* renamed from: d */
        C5469f<K, V> f22874d;

        /* renamed from: e */
        C5469f<K, V> f22875e;

        /* renamed from: f */
        final K f22876f;

        /* renamed from: g */
        final int f22877g;

        /* renamed from: h */
        V f22878h;

        /* renamed from: i */
        int f22879i;

        C5469f() {
            this.f22876f = null;
            this.f22877g = -1;
            this.f22875e = this;
            this.f22874d = this;
        }

        C5469f(C5469f<K, V> fVar, K k, int i, C5469f<K, V> fVar2, C5469f<K, V> fVar3) {
            this.f22871a = fVar;
            this.f22876f = k;
            this.f22877g = i;
            this.f22879i = 1;
            this.f22874d = fVar2;
            this.f22875e = fVar3;
            fVar3.f22874d = this;
            fVar2.f22875e = this;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f22876f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f22878h;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f22878h;
            this.f22878h = v;
            return v2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k = this.f22876f;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.f22878h;
            if (v == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!v.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f22876f;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.f22878h;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.f22876f + SimpleComparison.f23609c + this.f22878h;
        }

        /* renamed from: a */
        public C5469f<K, V> m1364a() {
            C5469f<K, V> fVar = this;
            for (C5469f<K, V> fVar2 = this.f22872b; fVar2 != null; fVar2 = fVar2.f22872b) {
                fVar = fVar2;
            }
            return fVar;
        }

        /* renamed from: b */
        public C5469f<K, V> m1363b() {
            C5469f<K, V> fVar = this;
            for (C5469f<K, V> fVar2 = this.f22873c; fVar2 != null; fVar2 = fVar2.f22873c) {
                fVar = fVar2;
            }
            return fVar;
        }
    }

    private void doubleCapacity() {
        this.table = doubleCapacity(this.table);
        C5469f<K, V>[] fVarArr = this.table;
        this.threshold = (fVarArr.length / 2) + (fVarArr.length / 4);
    }

    static <K, V> C5469f<K, V>[] doubleCapacity(C5469f<K, V>[] fVarArr) {
        int length = fVarArr.length;
        C5469f<K, V>[] fVarArr2 = new C5469f[length * 2];
        C5463b bVar = new C5463b();
        C5462a aVar = new C5462a();
        C5462a aVar2 = new C5462a();
        for (int i = 0; i < length; i++) {
            C5469f<K, V> fVar = fVarArr[i];
            if (fVar != null) {
                bVar.m1367a(fVar);
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    C5469f<K, V> a = bVar.m1368a();
                    if (a == null) {
                        break;
                    } else if ((a.f22877g & length) == 0) {
                        i2++;
                    } else {
                        i3++;
                    }
                }
                aVar.m1370a(i2);
                aVar2.m1370a(i3);
                bVar.m1367a(fVar);
                while (true) {
                    C5469f<K, V> a2 = bVar.m1368a();
                    if (a2 == null) {
                        break;
                    } else if ((a2.f22877g & length) == 0) {
                        aVar.m1369a(a2);
                    } else {
                        aVar2.m1369a(a2);
                    }
                }
                C5469f<K, V> fVar2 = null;
                fVarArr2[i] = i2 > 0 ? aVar.m1371a() : null;
                int i4 = i + length;
                if (i3 > 0) {
                    fVar2 = aVar2.m1371a();
                }
                fVarArr2[i4] = fVar2;
            }
        }
        return fVarArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LinkedHashTreeMap.java */
    /* renamed from: z1.qe$b */
    /* loaded from: classes3.dex */
    public static class C5463b<K, V> {

        /* renamed from: a */
        private C5469f<K, V> f22862a;

        C5463b() {
        }

        /* renamed from: a */
        void m1367a(C5469f<K, V> fVar) {
            fVar = null;
            while (fVar != null) {
                fVar.f22871a = fVar;
                fVar = fVar.f22872b;
            }
            this.f22862a = fVar;
        }

        /* renamed from: a */
        public C5469f<K, V> m1368a() {
            C5469f<K, V> fVar = this.f22862a;
            if (fVar == null) {
                return null;
            }
            C5469f<K, V> fVar2 = fVar.f22871a;
            fVar.f22871a = null;
            fVar2 = fVar.f22873c;
            while (fVar2 != null) {
                fVar2.f22871a = fVar2;
                fVar2 = fVar2.f22872b;
            }
            this.f22862a = fVar2;
            return fVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LinkedHashTreeMap.java */
    /* renamed from: z1.qe$a */
    /* loaded from: classes3.dex */
    public static final class C5462a<K, V> {

        /* renamed from: a */
        private C5469f<K, V> f22858a;

        /* renamed from: b */
        private int f22859b;

        /* renamed from: c */
        private int f22860c;

        /* renamed from: d */
        private int f22861d;

        C5462a() {
        }

        /* renamed from: a */
        void m1370a(int i) {
            this.f22859b = ((Integer.highestOneBit(i) * 2) - 1) - i;
            this.f22861d = 0;
            this.f22860c = 0;
            this.f22858a = null;
        }

        /* renamed from: a */
        void m1369a(C5469f<K, V> fVar) {
            fVar.f22873c = null;
            fVar.f22871a = null;
            fVar.f22872b = null;
            fVar.f22879i = 1;
            int i = this.f22859b;
            if (i > 0) {
                int i2 = this.f22861d;
                if ((i2 & 1) == 0) {
                    this.f22861d = i2 + 1;
                    this.f22859b = i - 1;
                    this.f22860c++;
                }
            }
            fVar.f22871a = this.f22858a;
            this.f22858a = fVar;
            this.f22861d++;
            int i3 = this.f22859b;
            if (i3 > 0) {
                int i4 = this.f22861d;
                if ((i4 & 1) == 0) {
                    this.f22861d = i4 + 1;
                    this.f22859b = i3 - 1;
                    this.f22860c++;
                }
            }
            int i5 = 4;
            while (true) {
                int i6 = i5 - 1;
                if ((this.f22861d & i6) == i6) {
                    int i7 = this.f22860c;
                    if (i7 == 0) {
                        C5469f<K, V> fVar2 = this.f22858a;
                        C5469f<K, V> fVar3 = fVar2.f22871a;
                        C5469f<K, V> fVar4 = fVar3.f22871a;
                        fVar3.f22871a = fVar4.f22871a;
                        this.f22858a = fVar3;
                        fVar3.f22872b = fVar4;
                        fVar3.f22873c = fVar2;
                        fVar3.f22879i = fVar2.f22879i + 1;
                        fVar4.f22871a = fVar3;
                        fVar2.f22871a = fVar3;
                    } else if (i7 == 1) {
                        C5469f<K, V> fVar5 = this.f22858a;
                        C5469f<K, V> fVar6 = fVar5.f22871a;
                        this.f22858a = fVar6;
                        fVar6.f22873c = fVar5;
                        fVar6.f22879i = fVar5.f22879i + 1;
                        fVar5.f22871a = fVar6;
                        this.f22860c = 0;
                    } else if (i7 == 2) {
                        this.f22860c = 0;
                    }
                    i5 *= 2;
                } else {
                    return;
                }
            }
        }

        /* renamed from: a */
        C5469f<K, V> m1371a() {
            C5469f<K, V> fVar = this.f22858a;
            if (fVar.f22871a == null) {
                return fVar;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LinkedHashTreeMap.java */
    /* renamed from: z1.qe$e */
    /* loaded from: classes3.dex */
    public abstract class AbstractC5468e<T> implements Iterator<T> {

        /* renamed from: b */
        C5469f<K, V> f22867b;

        /* renamed from: c */
        C5469f<K, V> f22868c = null;

        /* renamed from: d */
        int f22869d;

        AbstractC5468e() {
            this.f22867b = LinkedHashTreeMap.this.header.f22874d;
            this.f22869d = LinkedHashTreeMap.this.modCount;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f22867b != LinkedHashTreeMap.this.header;
        }

        /* renamed from: b */
        final C5469f<K, V> m1365b() {
            C5469f<K, V> fVar = this.f22867b;
            if (fVar == LinkedHashTreeMap.this.header) {
                throw new NoSuchElementException();
            } else if (LinkedHashTreeMap.this.modCount == this.f22869d) {
                this.f22867b = fVar.f22874d;
                this.f22868c = fVar;
                return fVar;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public final void remove() {
            C5469f<K, V> fVar = this.f22868c;
            if (fVar != null) {
                LinkedHashTreeMap.this.removeInternal(fVar, true);
                this.f22868c = null;
                this.f22869d = LinkedHashTreeMap.this.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* compiled from: LinkedHashTreeMap.java */
    /* renamed from: z1.qe$c */
    /* loaded from: classes3.dex */
    final class C5464c extends AbstractSet<Map.Entry<K, V>> {
        C5464c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedHashTreeMap<K, V>.AbstractC5468e<Map.Entry<K, V>>() { // from class: z1.qe.c.1
                {
                    LinkedHashTreeMap qeVar = LinkedHashTreeMap.this;
                }

                /* renamed from: a */
                public Map.Entry<K, V> next() {
                    return m1365b();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.findByEntry((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            C5469f<K, V> findByEntry;
            if (!(obj instanceof Map.Entry) || (findByEntry = LinkedHashTreeMap.this.findByEntry((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedHashTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    /* compiled from: LinkedHashTreeMap.java */
    /* renamed from: z1.qe$d */
    /* loaded from: classes3.dex */
    final class C5466d extends AbstractSet<K> {
        C5466d() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedHashTreeMap<K, V>.AbstractC5468e<K>() { // from class: z1.qe.d.1
                {
                    LinkedHashTreeMap qeVar = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return m1365b().f22876f;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.removeInternalByKey(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
