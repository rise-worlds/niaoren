package p110z1;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: MapWithDefault.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B<\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001fJ\u0015\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001fJ\u0013\u0010\"\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0096\u0002J\u0018\u0010%\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010&J\u0015\u0010'\u001a\u00028\u00012\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J\b\u0010(\u001a\u00020\u0016H\u0016J\b\u0010)\u001a\u00020\u001eH\u0016J\b\u0010*\u001a\u00020+H\u0016R)\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006,"}, m8860e = {"Lkotlin/collections/MapWithDefaultImpl;", "K", "V", "Lkotlin/collections/MapWithDefault;", "map", "", "default", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "key", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "getMap", "()Ljava/util/Map;", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "(Ljava/lang/Object;)Z", "containsValue", SizeSelector.SIZE_KEY, "equals", "other", "", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrImplicitDefault", "hashCode", "isEmpty", "toString", "", "kotlin-stdlib"})
/* renamed from: z1.cam */
/* loaded from: classes3.dex */
final class cam<K, V> implements MapWithDefault<K, V> {
    @NotNull

    /* renamed from: a */
    private final Map<K, V> f20491a;

    /* renamed from: b */
    private final chd<K, V> f20492b;

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

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

    /* JADX WARN: Multi-variable type inference failed */
    public cam(@NotNull Map<K, ? extends V> map, @NotNull chd<? super K, ? extends V> chdVar) {
        cji.m5162f(map, "map");
        cji.m5162f(chdVar, "default");
        this.f20491a = map;
        this.f20492b = chdVar;
    }

    @Override // p110z1.MapWithDefault
    @NotNull
    /* renamed from: a */
    public Map<K, V> mo6377a() {
        return this.f20491a;
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return m6482e();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return m6484c();
    }

    @Override // java.util.Map
    public final int size() {
        return m6485b();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return m6483d();
    }

    @Override // java.util.Map
    public boolean equals(@dbs Object obj) {
        return mo6377a().equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return mo6377a().hashCode();
    }

    @NotNull
    public String toString() {
        return mo6377a().toString();
    }

    /* renamed from: b */
    public int m6485b() {
        return mo6377a().size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return mo6377a().isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return mo6377a().containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return mo6377a().containsValue(obj);
    }

    @Override // java.util.Map
    @dbs
    public V get(Object obj) {
        return mo6377a().get(obj);
    }

    @NotNull
    /* renamed from: c */
    public Set<K> m6484c() {
        return mo6377a().keySet();
    }

    @NotNull
    /* renamed from: d */
    public Collection<V> m6483d() {
        return mo6377a().values();
    }

    @NotNull
    /* renamed from: e */
    public Set<Map.Entry<K, V>> m6482e() {
        return mo6377a().entrySet();
    }

    @Override // p110z1.MapWithDefault
    /* renamed from: a */
    public V mo6376a(K k) {
        Map<K, V> a = mo6377a();
        V v = a.get(k);
        return (v != null || a.containsKey(k)) ? v : this.f20492b.invoke(k);
    }
}
