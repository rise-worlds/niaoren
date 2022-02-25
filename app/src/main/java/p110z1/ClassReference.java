package p110z1;

import com.stripe.android.PaymentResultListener;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0011\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010B\u001a\u00020\u00122\b\u0010C\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010D\u001a\u00020EH\u0002J\b\u0010F\u001a\u00020GH\u0016J\u0012\u0010H\u001a\u00020\u00122\b\u0010I\u001a\u0004\u0018\u00010\u0002H\u0017J\b\u0010J\u001a\u00020-H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0018\u0010\u0015R\u001a\u0010\u001a\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001a\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001e\u0010\u0015R\u001a\u0010 \u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0014\u001a\u0004\b \u0010\u0015R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001e\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030%0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0010R\u001e\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0010R\u0016\u0010)\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0016\u0010,\u001a\u0004\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R(\u00100\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b1\u0010\u0014\u001a\u0004\b2\u0010\u000bR\u0016\u00103\u001a\u0004\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010/R \u00105\u001a\b\u0012\u0004\u0012\u0002060\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b7\u0010\u0014\u001a\u0004\b8\u0010\u000bR \u00109\u001a\b\u0012\u0004\u0012\u00020:0\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b;\u0010\u0014\u001a\u0004\b<\u0010\u000bR\u001c\u0010=\u001a\u0004\u0018\u00010>8VX\u0097\u0004¢\u0006\f\u0012\u0004\b?\u0010\u0014\u001a\u0004\b@\u0010A¨\u0006K"}, m8860e = {"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "getJClass", "()Ljava/lang/Class;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "sealedSubclasses$annotations", "getSealedSubclasses", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "supertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "typeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "visibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", PaymentResultListener.f11903c, "", "hashCode", "", "isInstance", SizeSelector.SIZE_KEY, "toString", "kotlin-stdlib"})
/* renamed from: z1.cit */
/* loaded from: classes3.dex */
public final class ClassReference implements ClassBasedDeclarationContainer, KClass<Object> {
    @NotNull

    /* renamed from: a */
    private final Class<?> f20727a;

    @bwy(m8750a = "1.1")
    /* renamed from: B */
    public static /* synthetic */ void m5239B() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: h */
    public static /* synthetic */ void m5237h() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: j */
    public static /* synthetic */ void m5236j() {
    }

    @bwy(m8750a = "1.3")
    /* renamed from: l */
    public static /* synthetic */ void m5235l() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: n */
    public static /* synthetic */ void m5234n() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: p */
    public static /* synthetic */ void m5233p() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: r */
    public static /* synthetic */ void m5232r() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: t */
    public static /* synthetic */ void m5231t() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: v */
    public static /* synthetic */ void m5230v() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: x */
    public static /* synthetic */ void m5229x() {
    }

    @bwy(m8750a = "1.1")
    /* renamed from: z */
    public static /* synthetic */ void m5228z() {
    }

    public ClassReference(@NotNull Class<?> cls) {
        cji.m5162f(cls, "jClass");
        this.f20727a = cls;
    }

    @Override // p110z1.ClassBasedDeclarationContainer
    @NotNull
    /* renamed from: a */
    public Class<?> mo5136a() {
        return this.f20727a;
    }

    @Override // p110z1.KClass
    @dbs
    /* renamed from: b */
    public String mo4613b() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @dbs
    /* renamed from: c */
    public String mo4612c() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KDeclarationContainer, p110z1.KClass
    @NotNull
    /* renamed from: d */
    public Collection<KCallable<?>> mo4588d() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @NotNull
    /* renamed from: e */
    public Collection<KFunction<Object>> mo4611e() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @NotNull
    /* renamed from: f */
    public Collection<KClass<?>> mo4610f() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KAnnotatedElement
    @NotNull
    public List<Annotation> getAnnotations() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @dbs
    /* renamed from: g */
    public Object mo4609g() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public boolean mo4614a(@dbs Object obj) {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @NotNull
    /* renamed from: i */
    public List<KTypeParameter> mo4608i() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @NotNull
    /* renamed from: k */
    public List<KType> mo4607k() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @NotNull
    /* renamed from: m */
    public List<KClass<? extends Object>> mo4606m() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    @dbs
    /* renamed from: o */
    public KVisibility mo4605o() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    /* renamed from: q */
    public boolean mo4604q() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    /* renamed from: s */
    public boolean mo4603s() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    /* renamed from: u */
    public boolean mo4602u() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    /* renamed from: w */
    public boolean mo4601w() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    /* renamed from: y */
    public boolean mo4600y() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    /* renamed from: A */
    public boolean mo4616A() {
        m5238D();
        throw null;
    }

    @Override // p110z1.KClass
    /* renamed from: C */
    public boolean mo4615C() {
        m5238D();
        throw null;
    }

    /* renamed from: D */
    private final Void m5238D() {
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // p110z1.KClass
    public boolean equals(@dbs Object obj) {
        return (obj instanceof ClassReference) && cji.m5184a(JvmClassMapping.m5273d(this), JvmClassMapping.m5273d((KClass) obj));
    }

    @Override // p110z1.KClass
    public int hashCode() {
        return JvmClassMapping.m5273d(this).hashCode();
    }

    @NotNull
    public String toString() {
        return mo5136a().toString() + " (Kotlin reflection is not available)";
    }
}
