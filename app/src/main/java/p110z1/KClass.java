package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collection;
import java.util.List;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005J\u0013\u0010<\u001a\u00020\f2\b\u0010=\u001a\u0004\u0018\u00010\u0002H¦\u0002J\b\u0010>\u001a\u00020?H&J\u0012\u0010@\u001a\u00020\f2\b\u0010A\u001a\u0004\u0018\u00010\u0002H'R\u001e\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0014\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0014\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0016\u0010\u000fR\u001a\u0010\u0018\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0018\u0010\u000fR\u001a\u0010\u001a\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001a\u0010\u000fR\u001c\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\nR\u001c\u0010\u001f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\nR\u0014\u0010!\u001a\u0004\u0018\u00018\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u0004\u0018\u00010%X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R(\u0010(\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00000)8&X§\u0004¢\u0006\f\u0012\u0004\b*\u0010\u000e\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u0004\u0018\u00010%X¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010'R \u0010/\u001a\b\u0012\u0004\u0012\u0002000)8&X§\u0004¢\u0006\f\u0012\u0004\b1\u0010\u000e\u001a\u0004\b2\u0010,R \u00103\u001a\b\u0012\u0004\u0012\u0002040)8&X§\u0004¢\u0006\f\u0012\u0004\b5\u0010\u000e\u001a\u0004\b6\u0010,R\u001c\u00107\u001a\u0004\u0018\u0001088&X§\u0004¢\u0006\f\u0012\u0004\b9\u0010\u000e\u001a\u0004\b:\u0010;¨\u0006B"}, m8860e = {"Lkotlin/reflect/KClass;", TessBaseAPI.f9204e, "", "Lkotlin/reflect/KDeclarationContainer;", "Lkotlin/reflect/KAnnotatedElement;", "Lkotlin/reflect/KClassifier;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "", "sealedSubclasses$annotations", "getSealedSubclasses", "()Ljava/util/List;", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "supertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "typeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "visibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "hashCode", "", "isInstance", SizeSelector.SIZE_KEY, "kotlin-stdlib"})
/* renamed from: z1.cmw */
/* loaded from: classes3.dex */
public interface KClass<T> extends KAnnotatedElement, KClassifier, KDeclarationContainer {

    /* compiled from: KClass.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.cmw$a */
    /* loaded from: classes3.dex */
    public static final class C4996a {
        @bwy(m8750a = "1.1")
        /* renamed from: a */
        public static /* synthetic */ void m4599a() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: b */
        public static /* synthetic */ void m4598b() {
        }

        @bwy(m8750a = "1.3")
        /* renamed from: c */
        public static /* synthetic */ void m4597c() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: d */
        public static /* synthetic */ void m4596d() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: e */
        public static /* synthetic */ void m4595e() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: f */
        public static /* synthetic */ void m4594f() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: g */
        public static /* synthetic */ void m4593g() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: h */
        public static /* synthetic */ void m4592h() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: i */
        public static /* synthetic */ void m4591i() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: j */
        public static /* synthetic */ void m4590j() {
        }

        @bwy(m8750a = "1.1")
        /* renamed from: k */
        public static /* synthetic */ void m4589k() {
        }
    }

    /* renamed from: A */
    boolean mo4616A();

    /* renamed from: C */
    boolean mo4615C();

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    boolean mo4614a(@dbs Object obj);

    @dbs
    /* renamed from: b */
    String mo4613b();

    @dbs
    /* renamed from: c */
    String mo4612c();

    @NotNull
    /* renamed from: d */
    Collection<KCallable<?>> mo4588d();

    @NotNull
    /* renamed from: e */
    Collection<KFunction<T>> mo4611e();

    boolean equals(@dbs Object obj);

    @NotNull
    /* renamed from: f */
    Collection<KClass<?>> mo4610f();

    @dbs
    /* renamed from: g */
    T mo4609g();

    int hashCode();

    @NotNull
    /* renamed from: i */
    List<KTypeParameter> mo4608i();

    @NotNull
    /* renamed from: k */
    List<KType> mo4607k();

    @NotNull
    /* renamed from: m */
    List<KClass<? extends T>> mo4606m();

    @dbs
    /* renamed from: o */
    KVisibility mo4605o();

    /* renamed from: q */
    boolean mo4604q();

    /* renamed from: s */
    boolean mo4603s();

    /* renamed from: u */
    boolean mo4602u();

    /* renamed from: w */
    boolean mo4601w();

    /* renamed from: y */
    boolean mo4600y();
}
