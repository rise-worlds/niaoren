package p110z1;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: z1.pz */
/* loaded from: classes3.dex */
public final class Excluder implements Cloneable, TypeAdapterFactory {

    /* renamed from: a */
    public static final Excluder f22841a = new Excluder();

    /* renamed from: b */
    private static final double f22842b = -1.0d;

    /* renamed from: f */
    private boolean f22846f;

    /* renamed from: c */
    private double f22843c = f22842b;

    /* renamed from: d */
    private int f22844d = 136;

    /* renamed from: e */
    private boolean f22845e = true;

    /* renamed from: g */
    private List<ExclusionStrategy> f22847g = Collections.emptyList();

    /* renamed from: h */
    private List<ExclusionStrategy> f22848h = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Excluder clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public Excluder m1395a(double d) {
        Excluder a = clone();
        a.f22843c = d;
        return a;
    }

    /* renamed from: a */
    public Excluder m1387a(int... iArr) {
        Excluder a = clone();
        a.f22844d = 0;
        for (int i : iArr) {
            a.f22844d = i | a.f22844d;
        }
        return a;
    }

    /* renamed from: b */
    public Excluder m1386b() {
        Excluder a = clone();
        a.f22845e = false;
        return a;
    }

    /* renamed from: c */
    public Excluder m1383c() {
        Excluder a = clone();
        a.f22846f = true;
        return a;
    }

    /* renamed from: a */
    public Excluder m1391a(ExclusionStrategy otVar, boolean z, boolean z2) {
        Excluder a = clone();
        if (z) {
            a.f22847g = new ArrayList(this.f22847g);
            a.f22847g.add(otVar);
        }
        if (z2) {
            a.f22848h = new ArrayList(this.f22848h);
            a.f22848h.add(otVar);
        }
        return a;
    }

    @Override // p110z1.TypeAdapterFactory
    /* renamed from: a */
    public <T> TypeAdapter<T> mo1264a(final Gson oxVar, final TypeToken<T> rdVar) {
        Class<? super T> rawType = rdVar.getRawType();
        boolean a = m1394a(rawType);
        final boolean z = a || m1384b(rawType, true);
        final boolean z2 = a || m1384b(rawType, false);
        if (z || z2) {
            return new TypeAdapter<T>() { // from class: z1.pz.1

                /* renamed from: f */
                private TypeAdapter<T> f22854f;

                /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
                @Override // p110z1.TypeAdapter
                /* renamed from: b */
                public T mo1234b(JsonReader reVar) throws IOException {
                    if (!z2) {
                        return m1380b().mo1234b(reVar);
                    }
                    reVar.mo1197n();
                    return null;
                }

                @Override // p110z1.TypeAdapter
                /* renamed from: a */
                public void mo1235a(JsonWriter rhVar, T t) throws IOException {
                    if (z) {
                        rhVar.mo1161f();
                    } else {
                        m1380b().mo1235a(rhVar, (JsonWriter) t);
                    }
                }

                /* renamed from: b */
                private TypeAdapter<T> m1380b() {
                    TypeAdapter<T> ppVar = this.f22854f;
                    if (ppVar != 0) {
                        return ppVar;
                    }
                    TypeAdapter<T> a2 = oxVar.m1580a(Excluder.this, rdVar);
                    this.f22854f = a2;
                    return a2;
                }
            };
        }
        return null;
    }

    /* renamed from: a */
    public boolean m1392a(Field field, boolean z) {
        Expose prVar;
        if ((this.f22844d & field.getModifiers()) != 0) {
            return true;
        }
        if (!(this.f22843c == f22842b || m1389a((Since) field.getAnnotation(Since.class), (Until) field.getAnnotation(Until.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f22846f && ((prVar = (Expose) field.getAnnotation(Expose.class)) == null || (!z ? !prVar.m1430b() : !prVar.m1431a()))) {
            return true;
        }
        if ((!this.f22845e && m1382c(field.getType())) || m1385b(field.getType())) {
            return true;
        }
        List<ExclusionStrategy> list = z ? this.f22847g : this.f22848h;
        if (list.isEmpty()) {
            return false;
        }
        FieldAttributes ouVar = new FieldAttributes(field);
        for (ExclusionStrategy otVar : list) {
            if (otVar.m1612a(ouVar)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private boolean m1394a(Class<?> cls) {
        if (this.f22843c == f22842b || m1389a((Since) cls.getAnnotation(Since.class), (Until) cls.getAnnotation(Until.class))) {
            return (!this.f22845e && m1382c(cls)) || m1385b(cls);
        }
        return true;
    }

    /* renamed from: a */
    public boolean m1393a(Class<?> cls, boolean z) {
        return m1394a(cls) || m1384b(cls, z);
    }

    /* renamed from: b */
    private boolean m1384b(Class<?> cls, boolean z) {
        for (ExclusionStrategy otVar : z ? this.f22847g : this.f22848h) {
            if (otVar.m1613a(cls)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private boolean m1385b(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    /* renamed from: c */
    private boolean m1382c(Class<?> cls) {
        return cls.isMemberClass() && !m1381d(cls);
    }

    /* renamed from: d */
    private boolean m1381d(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    /* renamed from: a */
    private boolean m1389a(Since puVar, Until pvVar) {
        return m1390a(puVar) && m1388a(pvVar);
    }

    /* renamed from: a */
    private boolean m1390a(Since puVar) {
        return puVar == null || puVar.m1425a() <= this.f22843c;
    }

    /* renamed from: a */
    private boolean m1388a(Until pvVar) {
        return pvVar == null || pvVar.m1424a() > this.f22843c;
    }
}
