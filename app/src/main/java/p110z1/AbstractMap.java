package p110z1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b'\u0018\u0000 )*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001)B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0016H\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\u0018\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\rH\u0016J#\u0010#\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020'H\u0016J\u0012\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u001fH\u0002J\u001c\u0010&\u001a\u00020'2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016H\bR\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bX\u0088\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006*"}, m8860e = {"Lkotlin/collections/AbstractMap;", "K", "V", "", "()V", "_keys", "", "_values", "", "keys", "getKeys", "()Ljava/util/Set;", "size", "", "getSize", "()I", "values", "getValues", "()Ljava/util/Collection;", "containsEntry", "", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "key", "(Ljava/lang/Object;)Z", "containsValue", SizeSelector.SIZE_KEY, "equals", "other", "", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "implFindEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", "isEmpty", "toString", "", "o", "Companion", "kotlin-stdlib"})
/* renamed from: z1.byu */
/* loaded from: classes3.dex */
public abstract class AbstractMap<K, V> implements Map<K, V>, KMarkers {

    /* renamed from: a */
    public static final C4821a f20431a = new C4821a(null);

    /* renamed from: b */
    private volatile Set<? extends K> f20432b;

    /* renamed from: c */
    private volatile Collection<? extends V> f20433c;

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: d */
    public abstract Set m8306d();

    @Override // java.util.Map
    public V put(K k, V v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* compiled from: AbstractMap.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010&\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0006\b\u0001\u0010\u0003 \u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, m8860e = {"<anonymous>", "", "K", "V", "it", "", "invoke"})
    /* renamed from: z1.byu$c */
    /* loaded from: classes3.dex */
    static final class C4824c extends Lambda implements chd<Map.Entry<? extends K, ? extends V>, String> {
        C4824c() {
            super(1);
        }

        @Override // p110z1.chd
        public /* bridge */ /* synthetic */ String invoke(Object obj) {
            return invoke((Map.Entry) ((Map.Entry) obj));
        }

        @NotNull
        public final String invoke(@NotNull Map.Entry<? extends K, ? extends V> entry) {
            cji.m5162f(entry, "it");
            return AbstractMap.this.m8308b((Map.Entry) entry);
        }
    }

    protected AbstractMap() {
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return m8306d();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return m8310b();
    }

    @Override // java.util.Map
    public final int size() {
        return m8314a();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return m8307c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return m8309b((AbstractMap<K, V>) obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        Set<Map.Entry<K, V>> entrySet = entrySet();
        if ((entrySet instanceof Collection) && entrySet.isEmpty()) {
            return false;
        }
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            if (cji.m5184a(((Map.Entry) it.next()).getValue(), obj)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final boolean m8312a(@dbs Map.Entry<?, ?> entry) {
        if (!(entry instanceof Map.Entry)) {
            return false;
        }
        Object key = entry.getKey();
        Object value = entry.getValue();
        AbstractMap<K, V> byuVar = this;
        V v = byuVar.get(key);
        if (!cji.m5184a(value, v)) {
            return false;
        }
        return v != null || byuVar.containsKey(key);
    }

    @Override // java.util.Map
    public boolean equals(@dbs Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        if ((entrySet instanceof Collection) && entrySet.isEmpty()) {
            return true;
        }
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            if (!m8312a((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @dbs
    public V get(Object obj) {
        Map.Entry<K, V> b = m8309b((AbstractMap<K, V>) obj);
        if (b != null) {
            return b.getValue();
        }
        return null;
    }

    @Override // java.util.Map
    public int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    /* renamed from: a */
    public int m8314a() {
        return entrySet().size();
    }

    /* compiled from: AbstractMap.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\tJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, m8860e = {"kotlin/collections/AbstractMap$keys$1", "Lkotlin/collections/AbstractSet;", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.byu$b */
    /* loaded from: classes3.dex */
    public static final class C4822b extends AbstractSet<K> {
        C4822b() {
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return AbstractMap.this.containsKey(obj);
        }

        /* compiled from: AbstractMap.kt */
        @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m8860e = {"kotlin/collections/AbstractMap$keys$1$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
        /* renamed from: z1.byu$b$a */
        /* loaded from: classes3.dex */
        public static final class C4823a implements Iterator<K>, KMarkers {

            /* renamed from: a */
            final /* synthetic */ Iterator f20435a;

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            C4823a(Iterator it) {
                this.f20435a = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f20435a.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                return (K) ((Map.Entry) this.f20435a.next()).getKey();
            }
        }

        @Override // p110z1.AbstractSet, p110z1.AbstractCollection, java.util.Collection, java.lang.Iterable
        @NotNull
        public Iterator<K> iterator() {
            return new C4823a(AbstractMap.this.entrySet().iterator());
        }

        @Override // p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return AbstractMap.this.size();
        }
    }

    @NotNull
    /* renamed from: b */
    public Set<K> m8310b() {
        if (this.f20432b == null) {
            this.f20432b = new C4822b();
        }
        Set set = (Set<? extends K>) this.f20432b;
        if (set == null) {
            cji.m5196a();
        }
        return set;
    }

    @NotNull
    public String toString() {
        return bzk.m6679a(entrySet(), ", ", "{", C4963cj.f20747d, 0, null, new C4824c(), 24, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public final String m8308b(Map.Entry<? extends K, ? extends V> entry) {
        return m8313a(entry.getKey()) + SimpleComparison.f23609c + m8313a(entry.getValue());
    }

    /* renamed from: a */
    private final String m8313a(Object obj) {
        return obj == this ? "(this Map)" : String.valueOf(obj);
    }

    /* compiled from: AbstractMap.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\tJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, m8860e = {"kotlin/collections/AbstractMap$values$1", "Lkotlin/collections/AbstractCollection;", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.byu$d */
    /* loaded from: classes3.dex */
    public static final class C4825d extends AbstractCollection<V> {
        C4825d() {
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return AbstractMap.this.containsValue(obj);
        }

        /* compiled from: AbstractMap.kt */
        @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m8860e = {"kotlin/collections/AbstractMap$values$1$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
        /* renamed from: z1.byu$d$a */
        /* loaded from: classes3.dex */
        public static final class C4826a implements Iterator<V>, KMarkers {

            /* renamed from: a */
            final /* synthetic */ Iterator f20437a;

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            C4826a(Iterator it) {
                this.f20437a = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f20437a.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return (V) ((Map.Entry) this.f20437a.next()).getValue();
            }
        }

        @Override // p110z1.AbstractCollection, java.util.Collection, java.lang.Iterable
        @NotNull
        public Iterator<V> iterator() {
            return new C4826a(AbstractMap.this.entrySet().iterator());
        }

        @Override // p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return AbstractMap.this.size();
        }
    }

    @NotNull
    /* renamed from: c */
    public Collection<V> m8307c() {
        if (this.f20433c == null) {
            this.f20433c = new C4825d();
        }
        Collection collection = (Collection<? extends V>) this.f20433c;
        if (collection == null) {
            cji.m5196a();
        }
        return collection;
    }

    /* renamed from: b */
    private final Map.Entry<K, V> m8309b(K k) {
        Object obj;
        Iterator<T> it = entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (cji.m5184a(((Map.Entry) obj).getKey(), k)) {
                break;
            }
        }
        return (Map.Entry) obj;
    }

    /* compiled from: AbstractMap.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b\bJ\u001d\u0010\t\u001a\u00020\n2\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000e¨\u0006\u000f"}, m8860e = {"Lkotlin/collections/AbstractMap$Companion;", "", "()V", "entryEquals", "", "e", "", "other", "entryEquals$kotlin_stdlib", "entryHashCode", "", "entryHashCode$kotlin_stdlib", "entryToString", "", "entryToString$kotlin_stdlib", "kotlin-stdlib"})
    /* renamed from: z1.byu$a */
    /* loaded from: classes3.dex */
    public static final class C4821a {
        private C4821a() {
        }

        public /* synthetic */ C4821a(DefaultConstructorMarker civVar) {
            this();
        }

        /* renamed from: a */
        public final int m8305a(@NotNull Map.Entry<?, ?> entry) {
            cji.m5162f(entry, "e");
            Object key = entry.getKey();
            int i = 0;
            int hashCode = key != null ? key.hashCode() : 0;
            Object value = entry.getValue();
            if (value != null) {
                i = value.hashCode();
            }
            return hashCode ^ i;
        }

        @NotNull
        /* renamed from: b */
        public final String m8303b(@NotNull Map.Entry<?, ?> entry) {
            cji.m5162f(entry, "e");
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            return sb.toString();
        }

        /* renamed from: a */
        public final boolean m8304a(@NotNull Map.Entry<?, ?> entry, @dbs Object obj) {
            cji.m5162f(entry, "e");
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry2 = (Map.Entry) obj;
            return cji.m5184a(entry.getKey(), entry2.getKey()) && cji.m5184a(entry.getValue(), entry2.getValue());
        }
    }
}
