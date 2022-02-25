package p110z1;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* renamed from: z1.qf */
/* loaded from: classes3.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() { // from class: z1.qf.1
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> comparator;
    private LinkedTreeMap<K, V>.C5471a entrySet;
    final C5476d<K, V> header;
    private LinkedTreeMap<K, V>.C5473b keySet;
    int modCount;
    C5476d<K, V> root;
    int size;

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.header = new C5476d<>();
        this.comparator = comparator == null ? NATURAL_ORDER : comparator;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        C5476d<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            return findByObject.f22894g;
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
            C5476d<K, V> find = find(k, true);
            V v2 = find.f22894g;
            find.f22894g = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        C5476d<K, V> dVar = this.header;
        dVar.f22892e = dVar;
        dVar.f22891d = dVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        C5476d<K, V> removeInternalByKey = removeInternalByKey(obj);
        if (removeInternalByKey != null) {
            return removeInternalByKey.f22894g;
        }
        return null;
    }

    C5476d<K, V> find(K k, boolean z) {
        int i;
        C5476d<K, V> dVar;
        Comparator<? super K> comparator = this.comparator;
        C5476d<K, V> dVar2 = this.root;
        if (dVar2 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(dVar2.f22893f);
                } else {
                    i = comparator.compare(k, (K) dVar2.f22893f);
                }
                if (i == 0) {
                    return dVar2;
                }
                C5476d<K, V> dVar3 = i < 0 ? dVar2.f22889b : dVar2.f22890c;
                if (dVar3 == null) {
                    break;
                }
                dVar2 = dVar3;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        C5476d<K, V> dVar4 = this.header;
        if (dVar2 != null) {
            dVar = new C5476d<>(dVar2, k, dVar4, dVar4.f22892e);
            if (i < 0) {
                dVar2.f22889b = dVar;
            } else {
                dVar2.f22890c = dVar;
            }
            rebalance(dVar2, true);
        } else if (comparator != NATURAL_ORDER || (k instanceof Comparable)) {
            dVar = new C5476d<>(dVar2, k, dVar4, dVar4.f22892e);
            this.root = dVar;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        this.size++;
        this.modCount++;
        return dVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    C5476d<K, V> findByObject(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return find(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    C5476d<K, V> findByEntry(Map.Entry<?, ?> entry) {
        C5476d<K, V> findByObject = findByObject(entry.getKey());
        if (findByObject != null && equal(findByObject.f22894g, entry.getValue())) {
            return findByObject;
        }
        return null;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    void removeInternal(C5476d<K, V> dVar, boolean z) {
        int i;
        if (z) {
            dVar.f22892e.f22891d = dVar.f22891d;
            dVar.f22891d.f22892e = dVar.f22892e;
        }
        C5476d<K, V> dVar2 = dVar.f22889b;
        C5476d<K, V> dVar3 = dVar.f22890c;
        C5476d<K, V> dVar4 = dVar.f22888a;
        int i2 = 0;
        if (dVar2 == null || dVar3 == null) {
            if (dVar2 != null) {
                replaceInParent(dVar, dVar2);
                dVar.f22889b = null;
            } else if (dVar3 != null) {
                replaceInParent(dVar, dVar3);
                dVar.f22890c = null;
            } else {
                replaceInParent(dVar, null);
            }
            rebalance(dVar4, false);
            this.size--;
            this.modCount++;
            return;
        }
        C5476d<K, V> b = dVar2.f22895h > dVar3.f22895h ? dVar2.m1358b() : dVar3.m1359a();
        removeInternal(b, false);
        C5476d<K, V> dVar5 = dVar.f22889b;
        if (dVar5 != null) {
            i = dVar5.f22895h;
            b.f22889b = dVar5;
            dVar5.f22888a = b;
            dVar.f22889b = null;
        } else {
            i = 0;
        }
        C5476d<K, V> dVar6 = dVar.f22890c;
        if (dVar6 != null) {
            i2 = dVar6.f22895h;
            b.f22890c = dVar6;
            dVar6.f22888a = b;
            dVar.f22890c = null;
        }
        b.f22895h = Math.max(i, i2) + 1;
        replaceInParent(dVar, b);
    }

    C5476d<K, V> removeInternalByKey(Object obj) {
        C5476d<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    private void replaceInParent(C5476d<K, V> dVar, C5476d<K, V> dVar2) {
        C5476d<K, V> dVar3 = dVar.f22888a;
        dVar.f22888a = null;
        if (dVar2 != null) {
            dVar2.f22888a = dVar3;
        }
        if (dVar3 == null) {
            this.root = dVar2;
        } else if (dVar3.f22889b == dVar) {
            dVar3.f22889b = dVar2;
        } else {
            dVar3.f22890c = dVar2;
        }
    }

    private void rebalance(C5476d<K, V> dVar, boolean z) {
        while (dVar != null) {
            C5476d<K, V> dVar2 = dVar.f22889b;
            C5476d<K, V> dVar3 = dVar.f22890c;
            int i = 0;
            int i2 = dVar2 != null ? dVar2.f22895h : 0;
            int i3 = dVar3 != null ? dVar3.f22895h : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                C5476d<K, V> dVar4 = dVar3.f22889b;
                C5476d<K, V> dVar5 = dVar3.f22890c;
                int i5 = dVar5 != null ? dVar5.f22895h : 0;
                if (dVar4 != null) {
                    i = dVar4.f22895h;
                }
                int i6 = i - i5;
                if (i6 == -1 || (i6 == 0 && !z)) {
                    rotateLeft(dVar);
                } else {
                    rotateRight(dVar3);
                    rotateLeft(dVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                C5476d<K, V> dVar6 = dVar2.f22889b;
                C5476d<K, V> dVar7 = dVar2.f22890c;
                int i7 = dVar7 != null ? dVar7.f22895h : 0;
                if (dVar6 != null) {
                    i = dVar6.f22895h;
                }
                int i8 = i - i7;
                if (i8 == 1 || (i8 == 0 && !z)) {
                    rotateRight(dVar);
                } else {
                    rotateLeft(dVar2);
                    rotateRight(dVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                dVar.f22895h = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                dVar.f22895h = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            dVar = dVar.f22888a;
        }
    }

    private void rotateLeft(C5476d<K, V> dVar) {
        C5476d<K, V> dVar2 = dVar.f22889b;
        C5476d<K, V> dVar3 = dVar.f22890c;
        C5476d<K, V> dVar4 = dVar3.f22889b;
        C5476d<K, V> dVar5 = dVar3.f22890c;
        dVar.f22890c = dVar4;
        if (dVar4 != null) {
            dVar4.f22888a = dVar;
        }
        replaceInParent(dVar, dVar3);
        dVar3.f22889b = dVar;
        dVar.f22888a = dVar3;
        int i = 0;
        dVar.f22895h = Math.max(dVar2 != null ? dVar2.f22895h : 0, dVar4 != null ? dVar4.f22895h : 0) + 1;
        int i2 = dVar.f22895h;
        if (dVar5 != null) {
            i = dVar5.f22895h;
        }
        dVar3.f22895h = Math.max(i2, i) + 1;
    }

    private void rotateRight(C5476d<K, V> dVar) {
        C5476d<K, V> dVar2 = dVar.f22889b;
        C5476d<K, V> dVar3 = dVar.f22890c;
        C5476d<K, V> dVar4 = dVar2.f22889b;
        C5476d<K, V> dVar5 = dVar2.f22890c;
        dVar.f22889b = dVar5;
        if (dVar5 != null) {
            dVar5.f22888a = dVar;
        }
        replaceInParent(dVar, dVar2);
        dVar2.f22890c = dVar;
        dVar.f22888a = dVar2;
        int i = 0;
        dVar.f22895h = Math.max(dVar3 != null ? dVar3.f22895h : 0, dVar5 != null ? dVar5.f22895h : 0) + 1;
        int i2 = dVar.f22895h;
        if (dVar4 != null) {
            i = dVar4.f22895h;
        }
        dVar2.f22895h = Math.max(i2, i) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.C5471a aVar = this.entrySet;
        if (aVar != null) {
            return aVar;
        }
        LinkedTreeMap<K, V>.C5471a aVar2 = new C5471a();
        this.entrySet = aVar2;
        return aVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedTreeMap<K, V>.C5473b bVar = this.keySet;
        if (bVar != null) {
            return bVar;
        }
        LinkedTreeMap<K, V>.C5473b bVar2 = new C5473b();
        this.keySet = bVar2;
        return bVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LinkedTreeMap.java */
    /* renamed from: z1.qf$d */
    /* loaded from: classes3.dex */
    public static final class C5476d<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        C5476d<K, V> f22888a;

        /* renamed from: b */
        C5476d<K, V> f22889b;

        /* renamed from: c */
        C5476d<K, V> f22890c;

        /* renamed from: d */
        C5476d<K, V> f22891d;

        /* renamed from: e */
        C5476d<K, V> f22892e;

        /* renamed from: f */
        final K f22893f;

        /* renamed from: g */
        V f22894g;

        /* renamed from: h */
        int f22895h;

        C5476d() {
            this.f22893f = null;
            this.f22892e = this;
            this.f22891d = this;
        }

        C5476d(C5476d<K, V> dVar, K k, C5476d<K, V> dVar2, C5476d<K, V> dVar3) {
            this.f22888a = dVar;
            this.f22893f = k;
            this.f22895h = 1;
            this.f22891d = dVar2;
            this.f22892e = dVar3;
            dVar3.f22891d = this;
            dVar2.f22892e = this;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f22893f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f22894g;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f22894g;
            this.f22894g = v;
            return v2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k = this.f22893f;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.f22894g;
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
            K k = this.f22893f;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.f22894g;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.f22893f + SimpleComparison.f23609c + this.f22894g;
        }

        /* renamed from: a */
        public C5476d<K, V> m1359a() {
            C5476d<K, V> dVar = this;
            for (C5476d<K, V> dVar2 = this.f22889b; dVar2 != null; dVar2 = dVar2.f22889b) {
                dVar = dVar2;
            }
            return dVar;
        }

        /* renamed from: b */
        public C5476d<K, V> m1358b() {
            C5476d<K, V> dVar = this;
            for (C5476d<K, V> dVar2 = this.f22890c; dVar2 != null; dVar2 = dVar2.f22890c) {
                dVar = dVar2;
            }
            return dVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LinkedTreeMap.java */
    /* renamed from: z1.qf$c */
    /* loaded from: classes3.dex */
    public abstract class AbstractC5475c<T> implements Iterator<T> {

        /* renamed from: b */
        C5476d<K, V> f22884b;

        /* renamed from: c */
        C5476d<K, V> f22885c = null;

        /* renamed from: d */
        int f22886d;

        AbstractC5475c() {
            this.f22884b = LinkedTreeMap.this.header.f22891d;
            this.f22886d = LinkedTreeMap.this.modCount;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f22884b != LinkedTreeMap.this.header;
        }

        /* renamed from: b */
        final C5476d<K, V> m1360b() {
            C5476d<K, V> dVar = this.f22884b;
            if (dVar == LinkedTreeMap.this.header) {
                throw new NoSuchElementException();
            } else if (LinkedTreeMap.this.modCount == this.f22886d) {
                this.f22884b = dVar.f22891d;
                this.f22885c = dVar;
                return dVar;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public final void remove() {
            C5476d<K, V> dVar = this.f22885c;
            if (dVar != null) {
                LinkedTreeMap.this.removeInternal(dVar, true);
                this.f22885c = null;
                this.f22886d = LinkedTreeMap.this.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* compiled from: LinkedTreeMap.java */
    /* renamed from: z1.qf$a */
    /* loaded from: classes3.dex */
    class C5471a extends AbstractSet<Map.Entry<K, V>> {
        C5471a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedTreeMap<K, V>.AbstractC5475c<Map.Entry<K, V>>() { // from class: z1.qf.a.1
                {
                    LinkedTreeMap qfVar = LinkedTreeMap.this;
                }

                /* renamed from: a */
                public Map.Entry<K, V> next() {
                    return m1360b();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedTreeMap.this.findByEntry((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            C5476d<K, V> findByEntry;
            if (!(obj instanceof Map.Entry) || (findByEntry = LinkedTreeMap.this.findByEntry((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    /* compiled from: LinkedTreeMap.java */
    /* renamed from: z1.qf$b */
    /* loaded from: classes3.dex */
    final class C5473b extends AbstractSet<K> {
        C5473b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedTreeMap<K, V>.AbstractC5475c<K>() { // from class: z1.qf.b.1
                {
                    LinkedTreeMap qfVar = LinkedTreeMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return m1360b().f22893f;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedTreeMap.this.removeInternalByKey(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
