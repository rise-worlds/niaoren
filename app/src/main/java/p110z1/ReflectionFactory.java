package p110z1;

import java.util.List;

/* renamed from: z1.cki */
/* loaded from: classes3.dex */
public class ReflectionFactory {

    /* renamed from: a */
    private static final String f20773a = "kotlin.jvm.functions.";

    /* renamed from: a */
    public KFunction m5098a(FunctionReference cjdVar) {
        return cjdVar;
    }

    /* renamed from: a */
    public cnb m5096a(MutablePropertyReference0 cjrVar) {
        return cjrVar;
    }

    /* renamed from: a */
    public cnc m5095a(MutablePropertyReference1 cjtVar) {
        return cjtVar;
    }

    /* renamed from: a */
    public cnd m5094a(MutablePropertyReference2 cjvVar) {
        return cjvVar;
    }

    /* renamed from: a */
    public cng m5093a(PropertyReference0 ckaVar) {
        return ckaVar;
    }

    /* renamed from: a */
    public cnh m5092a(PropertyReference1 ckcVar) {
        return ckcVar;
    }

    /* renamed from: a */
    public cni m5091a(PropertyReference2 ckeVar) {
        return ckeVar;
    }

    /* renamed from: a */
    public KClass m5101a(Class cls) {
        return new ClassReference(cls);
    }

    /* renamed from: a */
    public KClass m5100a(Class cls, String str) {
        return new ClassReference(cls);
    }

    /* renamed from: b */
    public KDeclarationContainer m5088b(Class cls, String str) {
        return new PackageReference(cls, str);
    }

    /* renamed from: b */
    public KClass m5089b(Class cls) {
        return new ClassReference(cls);
    }

    /* renamed from: c */
    public KClass m5087c(Class cls, String str) {
        return new ClassReference(cls);
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public String m5097a(Lambda cjjVar) {
        return m5099a((FunctionBase) cjjVar);
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public String m5099a(FunctionBase cjbVar) {
        String obj = cjbVar.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith(f20773a) ? obj.substring(21) : obj;
    }

    @bwy(m8750a = "1.4")
    /* renamed from: a */
    public KType m5090a(KClassifier cmxVar, List<cnl> list, boolean z) {
        return new TypeReference(cmxVar, list, z);
    }
}
