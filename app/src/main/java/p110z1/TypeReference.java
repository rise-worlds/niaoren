package p110z1;

import java.lang.annotation.Annotation;
import java.util.List;
import org.slf4j.Marker;

@bwy(m8750a = "1.4")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u0013\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\f\u0010\u0017\u001a\u00020\u0013*\u00020\u0006H\u0002R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, m8860e = {"Lkotlin/jvm/internal/TypeReference;", "Lkotlin/reflect/KType;", "classifier", "Lkotlin/reflect/KClassifier;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "isMarkedNullable", "", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Z)V", "annotations", "", "getAnnotations", "()Ljava/util/List;", "getArguments", "getClassifier", "()Lkotlin/reflect/KClassifier;", "()Z", "arrayClassName", "", "Ljava/lang/Class;", "getArrayClassName", "(Ljava/lang/Class;)Ljava/lang/String;", "asString", "equals", "other", "", "hashCode", "", "toString", "kotlin-stdlib"})
/* renamed from: z1.cko */
/* loaded from: classes3.dex */
public final class TypeReference implements KType {
    @NotNull

    /* renamed from: a */
    private final KClassifier f20782a;
    @NotNull

    /* renamed from: b */
    private final List<cnl> f20783b;

    /* renamed from: c */
    private final boolean f20784c;

    public TypeReference(@NotNull KClassifier cmxVar, @NotNull List<cnl> list, boolean z) {
        cji.m5162f(cmxVar, "classifier");
        cji.m5162f(list, "arguments");
        this.f20782a = cmxVar;
        this.f20783b = list;
        this.f20784c = z;
    }

    @Override // p110z1.KType
    @NotNull
    /* renamed from: a */
    public KClassifier mo4570a() {
        return this.f20782a;
    }

    @Override // p110z1.KType
    @NotNull
    /* renamed from: b */
    public List<cnl> mo4569b() {
        return this.f20783b;
    }

    @Override // p110z1.KType
    /* renamed from: c */
    public boolean mo4568c() {
        return this.f20784c;
    }

    @Override // p110z1.KAnnotatedElement
    @NotNull
    public List<Annotation> getAnnotations() {
        return bzk.m6816a();
    }

    public boolean equals(@dbs Object obj) {
        if (obj instanceof TypeReference) {
            TypeReference ckoVar = (TypeReference) obj;
            if (cji.m5184a(mo4570a(), ckoVar.mo4570a()) && cji.m5184a(mo4569b(), ckoVar.mo4569b()) && mo4568c() == ckoVar.mo4568c()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((mo4570a().hashCode() * 31) + mo4569b().hashCode()) * 31) + Boolean.valueOf(mo4568c()).hashCode();
    }

    @NotNull
    public String toString() {
        return m5035d() + " (Kotlin reflection is not available)";
    }

    /* renamed from: d */
    private final String m5035d() {
        String str;
        KClassifier a = mo4570a();
        Class<?> cls = null;
        if (!(a instanceof KClass)) {
            a = null;
        }
        KClass cmwVar = (KClass) a;
        if (cmwVar != null) {
            cls = JvmClassMapping.m5275b(cmwVar);
        }
        if (cls == null) {
            str = mo4570a().toString();
        } else {
            str = cls.isArray() ? m5038a(cls) : cls.getName();
        }
        return str + (mo4569b().isEmpty() ? "" : bzk.m6679a(mo4569b(), ", ", SimpleComparison.f23612f, SimpleComparison.f23610d, 0, null, new C4974a(), 24, null)) + (mo4568c() ? "?" : "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TypeReference.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m8860e = {"<anonymous>", "", "it", "Lkotlin/reflect/KTypeProjection;", "invoke"})
    /* renamed from: z1.cko$a */
    /* loaded from: classes3.dex */
    public static final class C4974a extends Lambda implements chd<cnl, String> {
        C4974a() {
            super(1);
        }

        @NotNull
        public final String invoke(@NotNull cnl cnlVar) {
            cji.m5162f(cnlVar, "it");
            return TypeReference.this.m5036a(cnlVar);
        }
    }

    /* renamed from: a */
    private final String m5038a(@NotNull Class<?> cls) {
        return cji.m5184a(cls, boolean[].class) ? "kotlin.BooleanArray" : cji.m5184a(cls, char[].class) ? "kotlin.CharArray" : cji.m5184a(cls, byte[].class) ? "kotlin.ByteArray" : cji.m5184a(cls, short[].class) ? "kotlin.ShortArray" : cji.m5184a(cls, int[].class) ? "kotlin.IntArray" : cji.m5184a(cls, float[].class) ? "kotlin.FloatArray" : cji.m5184a(cls, long[].class) ? "kotlin.LongArray" : cji.m5184a(cls, double[].class) ? "kotlin.DoubleArray" : "kotlin.Array";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public final String m5036a(@NotNull cnl cnlVar) {
        String str;
        if (cnlVar.m4561a() == null) {
            return Marker.ANY_MARKER;
        }
        KType b = cnlVar.m4558b();
        if (!(b instanceof TypeReference)) {
            b = null;
        }
        TypeReference ckoVar = (TypeReference) b;
        if (ckoVar == null || (str = ckoVar.m5035d()) == null) {
            str = String.valueOf(cnlVar.m4558b());
        }
        KVariance a = cnlVar.m4561a();
        if (a != null) {
            switch (a) {
                case INVARIANT:
                    return str;
                case IN:
                    return "in " + str;
                case OUT:
                    return "out " + str;
            }
        }
        throw new NoWhenBranchMatchedException();
    }
}
