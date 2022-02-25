package p110z1;

import java.lang.reflect.Method;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DebugMetadata.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m8860e = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever;", "", "()V", "cache", "Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "notOnJava9", "buildCache", "continuation", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getModuleName", "", "Cache", "kotlin-stdlib"})
/* renamed from: z1.cek */
/* loaded from: classes3.dex */
public final class cek {
    @JvmPlatformAnnotations
    @dbs

    /* renamed from: a */
    public static C4942a f20626a;

    /* renamed from: b */
    public static final cek f20627b = new cek();

    /* renamed from: c */
    private static final C4942a f20628c = new C4942a(null, null, null);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DebugMetadata.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m8860e = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "", "getModuleMethod", "Ljava/lang/reflect/Method;", "getDescriptorMethod", "nameMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "kotlin-stdlib"})
    /* renamed from: z1.cek$a */
    /* loaded from: classes3.dex */
    public static final class C4942a {
        @JvmPlatformAnnotations
        @dbs

        /* renamed from: a */
        public final Method f20629a;
        @JvmPlatformAnnotations
        @dbs

        /* renamed from: b */
        public final Method f20630b;
        @JvmPlatformAnnotations
        @dbs

        /* renamed from: c */
        public final Method f20631c;

        public C4942a(@dbs Method method, @dbs Method method2, @dbs Method method3) {
            this.f20629a = method;
            this.f20630b = method2;
            this.f20631c = method3;
        }
    }

    private cek() {
    }

    @dbs
    /* renamed from: a */
    public final String m5502a(@NotNull ContinuationImpl cecVar) {
        Method method;
        Object invoke;
        Method method2;
        Object invoke2;
        cji.m5162f(cecVar, "continuation");
        C4942a aVar = f20626a;
        if (aVar == null) {
            aVar = m5501b(cecVar);
        }
        if (aVar == f20628c || (method = aVar.f20629a) == null || (invoke = method.invoke(cecVar.getClass(), new Object[0])) == null || (method2 = aVar.f20630b) == null || (invoke2 = method2.invoke(invoke, new Object[0])) == null) {
            return null;
        }
        Method method3 = aVar.f20631c;
        Object invoke3 = method3 != null ? method3.invoke(invoke2, new Object[0]) : null;
        if (!(invoke3 instanceof String)) {
            invoke3 = null;
        }
        return (String) invoke3;
    }

    /* renamed from: b */
    private final C4942a m5501b(ContinuationImpl cecVar) {
        try {
            C4942a aVar = new C4942a(Class.class.getDeclaredMethod("getModule", new Class[0]), cecVar.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), cecVar.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
            f20626a = aVar;
            return aVar;
        } catch (Exception unused) {
            C4942a aVar2 = f20628c;
            f20626a = aVar2;
            return aVar2;
        }
    }
}
