package p110z1;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: z1.oy */
/* loaded from: classes3.dex */
public final class GsonBuilder {

    /* renamed from: a */
    private Excluder f22767a;

    /* renamed from: b */
    private LongSerializationPolicy f22768b;

    /* renamed from: c */
    private FieldNamingStrategy f22769c;

    /* renamed from: d */
    private final Map<Type, InstanceCreator<?>> f22770d;

    /* renamed from: e */
    private final List<TypeAdapterFactory> f22771e;

    /* renamed from: f */
    private final List<TypeAdapterFactory> f22772f;

    /* renamed from: g */
    private boolean f22773g;

    /* renamed from: h */
    private String f22774h;

    /* renamed from: i */
    private int f22775i;

    /* renamed from: j */
    private int f22776j;

    /* renamed from: k */
    private boolean f22777k;

    /* renamed from: l */
    private boolean f22778l;

    /* renamed from: m */
    private boolean f22779m;

    /* renamed from: n */
    private boolean f22780n;

    /* renamed from: o */
    private boolean f22781o;

    /* renamed from: p */
    private boolean f22782p;

    public GsonBuilder() {
        this.f22767a = Excluder.f22841a;
        this.f22768b = LongSerializationPolicy.DEFAULT;
        this.f22769c = FieldNamingPolicy.IDENTITY;
        this.f22770d = new HashMap();
        this.f22771e = new ArrayList();
        this.f22772f = new ArrayList();
        this.f22773g = false;
        this.f22775i = 2;
        this.f22776j = 2;
        this.f22777k = false;
        this.f22778l = false;
        this.f22779m = true;
        this.f22780n = false;
        this.f22781o = false;
        this.f22782p = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GsonBuilder(Gson oxVar) {
        this.f22767a = Excluder.f22841a;
        this.f22768b = LongSerializationPolicy.DEFAULT;
        this.f22769c = FieldNamingPolicy.IDENTITY;
        this.f22770d = new HashMap();
        this.f22771e = new ArrayList();
        this.f22772f = new ArrayList();
        this.f22773g = false;
        this.f22775i = 2;
        this.f22776j = 2;
        this.f22777k = false;
        this.f22778l = false;
        this.f22779m = true;
        this.f22780n = false;
        this.f22781o = false;
        this.f22782p = false;
        this.f22767a = oxVar.f22746i;
        this.f22769c = oxVar.f22747j;
        this.f22770d.putAll(oxVar.f22748k);
        this.f22773g = oxVar.f22749l;
        this.f22777k = oxVar.f22750m;
        this.f22781o = oxVar.f22751n;
        this.f22779m = oxVar.f22752o;
        this.f22780n = oxVar.f22753p;
        this.f22782p = oxVar.f22754q;
        this.f22778l = oxVar.f22755r;
        this.f22768b = oxVar.f22759v;
        this.f22774h = oxVar.f22756s;
        this.f22775i = oxVar.f22757t;
        this.f22776j = oxVar.f22758u;
        this.f22771e.addAll(oxVar.f22760w);
        this.f22772f.addAll(oxVar.f22761x);
    }

    /* renamed from: a */
    public GsonBuilder m1556a(double d) {
        this.f22767a = this.f22767a.m1395a(d);
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1544a(int... iArr) {
        this.f22767a = this.f22767a.m1387a(iArr);
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1557a() {
        this.f22781o = true;
        return this;
    }

    /* renamed from: b */
    public GsonBuilder m1542b() {
        this.f22767a = this.f22767a.m1383c();
        return this;
    }

    /* renamed from: c */
    public GsonBuilder m1540c() {
        this.f22773g = true;
        return this;
    }

    /* renamed from: d */
    public GsonBuilder m1539d() {
        this.f22777k = true;
        return this;
    }

    /* renamed from: e */
    public GsonBuilder m1538e() {
        this.f22767a = this.f22767a.m1386b();
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1546a(LongSerializationPolicy poVar) {
        this.f22768b = poVar;
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1548a(FieldNamingPolicy ovVar) {
        this.f22769c = ovVar;
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1547a(FieldNamingStrategy owVar) {
        this.f22769c = owVar;
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1543a(ExclusionStrategy... otVarArr) {
        for (ExclusionStrategy otVar : otVarArr) {
            this.f22767a = this.f22767a.m1391a(otVar, true, true);
        }
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1549a(ExclusionStrategy otVar) {
        this.f22767a = this.f22767a.m1391a(otVar, true, false);
        return this;
    }

    /* renamed from: b */
    public GsonBuilder m1541b(ExclusionStrategy otVar) {
        this.f22767a = this.f22767a.m1391a(otVar, false, true);
        return this;
    }

    /* renamed from: f */
    public GsonBuilder m1537f() {
        this.f22780n = true;
        return this;
    }

    /* renamed from: g */
    public GsonBuilder m1536g() {
        this.f22782p = true;
        return this;
    }

    /* renamed from: h */
    public GsonBuilder m1535h() {
        this.f22779m = false;
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1552a(String str) {
        this.f22774h = str;
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1555a(int i) {
        this.f22775i = i;
        this.f22774h = null;
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1554a(int i, int i2) {
        this.f22775i = i;
        this.f22776j = i2;
        this.f22774h = null;
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1550a(Type type, Object obj) {
        boolean z = obj instanceof JsonSerializer;
        C$Gson$Preconditions.m1422a(z || (obj instanceof JsonDeserializer) || (obj instanceof InstanceCreator) || (obj instanceof TypeAdapter));
        if (obj instanceof InstanceCreator) {
            this.f22770d.put(type, (InstanceCreator) obj);
        }
        if (z || (obj instanceof JsonDeserializer)) {
            this.f22771e.add(TreeTypeAdapter.m1306b(TypeToken.get(type), obj));
        }
        if (obj instanceof TypeAdapter) {
            this.f22771e.add(TypeAdapters.m1299a(TypeToken.get(type), (TypeAdapter) obj));
        }
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1545a(TypeAdapterFactory pqVar) {
        this.f22771e.add(pqVar);
        return this;
    }

    /* renamed from: a */
    public GsonBuilder m1553a(Class<?> cls, Object obj) {
        boolean z = obj instanceof JsonSerializer;
        C$Gson$Preconditions.m1422a(z || (obj instanceof JsonDeserializer) || (obj instanceof TypeAdapter));
        if ((obj instanceof JsonDeserializer) || z) {
            this.f22772f.add(TreeTypeAdapter.m1309a(cls, obj));
        }
        if (obj instanceof TypeAdapter) {
            this.f22771e.add(TypeAdapters.m1297b(cls, (TypeAdapter) obj));
        }
        return this;
    }

    /* renamed from: i */
    public GsonBuilder m1534i() {
        this.f22778l = true;
        return this;
    }

    /* renamed from: j */
    public Gson m1533j() {
        List<TypeAdapterFactory> arrayList = new ArrayList<>(this.f22771e.size() + this.f22772f.size() + 3);
        arrayList.addAll(this.f22771e);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.f22772f);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        m1551a(this.f22774h, this.f22775i, this.f22776j, arrayList);
        return new Gson(this.f22767a, this.f22769c, this.f22770d, this.f22773g, this.f22777k, this.f22781o, this.f22779m, this.f22780n, this.f22782p, this.f22778l, this.f22768b, this.f22774h, this.f22775i, this.f22776j, this.f22771e, this.f22772f, arrayList);
    }

    /* renamed from: a */
    private void m1551a(String str, int i, int i2, List<TypeAdapterFactory> list) {
        DefaultDateTypeAdapter osVar;
        DefaultDateTypeAdapter osVar2;
        DefaultDateTypeAdapter osVar3;
        if (str != null && !"".equals(str.trim())) {
            DefaultDateTypeAdapter osVar4 = new DefaultDateTypeAdapter(Date.class, str);
            osVar = new DefaultDateTypeAdapter(Timestamp.class, str);
            osVar3 = new DefaultDateTypeAdapter(java.sql.Date.class, str);
            osVar2 = osVar4;
        } else if (i != 2 && i2 != 2) {
            osVar2 = new DefaultDateTypeAdapter(Date.class, i, i2);
            DefaultDateTypeAdapter osVar5 = new DefaultDateTypeAdapter(Timestamp.class, i, i2);
            DefaultDateTypeAdapter osVar6 = new DefaultDateTypeAdapter(java.sql.Date.class, i, i2);
            osVar = osVar5;
            osVar3 = osVar6;
        } else {
            return;
        }
        list.add(TypeAdapters.m1300a(Date.class, osVar2));
        list.add(TypeAdapters.m1300a(Timestamp.class, osVar));
        list.add(TypeAdapters.m1300a(java.sql.Date.class, osVar3));
    }
}
