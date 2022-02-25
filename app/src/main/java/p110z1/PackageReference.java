package p110z1;

import java.util.Collection;

@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0016R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m8860e = {"Lkotlin/jvm/internal/PackageReference;", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "moduleName", "", "(Ljava/lang/Class;Ljava/lang/String;)V", "getJClass", "()Ljava/lang/Class;", "members", "", "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "equals", "", "other", "", "hashCode", "", "toString", "kotlin-stdlib"})
/* renamed from: z1.cjx */
/* loaded from: classes3.dex */
public final class PackageReference implements ClassBasedDeclarationContainer {
    @NotNull

    /* renamed from: a */
    private final Class<?> f20764a;

    /* renamed from: b */
    private final String f20765b;

    public PackageReference(@NotNull Class<?> cls, @NotNull String str) {
        cji.m5162f(cls, "jClass");
        cji.m5162f(str, "moduleName");
        this.f20764a = cls;
        this.f20765b = str;
    }

    @Override // p110z1.ClassBasedDeclarationContainer
    @NotNull
    /* renamed from: a */
    public Class<?> mo5136a() {
        return this.f20764a;
    }

    @Override // p110z1.KDeclarationContainer, p110z1.KClass
    @NotNull
    /* renamed from: d */
    public Collection<KCallable<?>> mo4588d() {
        throw new KotlinReflectionNotSupportedError();
    }

    public boolean equals(@dbs Object obj) {
        return (obj instanceof PackageReference) && cji.m5184a(mo5136a(), ((PackageReference) obj).mo5136a());
    }

    public int hashCode() {
        return mo5136a().hashCode();
    }

    @NotNull
    public String toString() {
        return mo5136a().toString() + " (Kotlin reflection is not available)";
    }
}
