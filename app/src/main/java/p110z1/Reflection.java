package p110z1;

import java.util.Arrays;
import java.util.Collections;

/* renamed from: z1.ckh */
/* loaded from: classes3.dex */
public class Reflection {

    /* renamed from: a */
    static final String f20770a = " (Kotlin reflection is not available)";

    /* renamed from: b */
    private static final ReflectionFactory f20771b;

    /* renamed from: c */
    private static final KClass[] f20772c;

    static {
        ReflectionFactory ckiVar = null;
        try {
            ckiVar = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException unused) {
        } catch (ClassNotFoundException unused2) {
        } catch (IllegalAccessException unused3) {
        } catch (InstantiationException unused4) {
        }
        if (ckiVar == null) {
            ckiVar = new ReflectionFactory();
        }
        f20771b = ckiVar;
        f20772c = new KClass[0];
    }

    /* renamed from: a */
    public static KClass m5124a(Class cls) {
        return f20771b.m5101a(cls);
    }

    /* renamed from: a */
    public static KClass m5123a(Class cls, String str) {
        return f20771b.m5100a(cls, str);
    }

    /* renamed from: b */
    public static KDeclarationContainer m5108b(Class cls, String str) {
        return f20771b.m5088b(cls, str);
    }

    /* renamed from: b */
    public static KClass m5109b(Class cls) {
        return f20771b.m5089b(cls);
    }

    /* renamed from: c */
    public static KClass m5103c(Class cls, String str) {
        return f20771b.m5087c(cls, str);
    }

    /* renamed from: a */
    public static KClass[] m5110a(Class[] clsArr) {
        int length = clsArr.length;
        if (length == 0) {
            return f20772c;
        }
        KClass[] cmwVarArr = new KClass[length];
        for (int i = 0; i < length; i++) {
            cmwVarArr[i] = m5109b(clsArr[i]);
        }
        return cmwVarArr;
    }

    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static String m5117a(Lambda cjjVar) {
        return f20771b.m5097a(cjjVar);
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static String m5119a(FunctionBase cjbVar) {
        return f20771b.m5099a(cjbVar);
    }

    /* renamed from: a */
    public static KFunction m5118a(FunctionReference cjdVar) {
        return f20771b.m5098a(cjdVar);
    }

    /* renamed from: a */
    public static cng m5113a(PropertyReference0 ckaVar) {
        return f20771b.m5093a(ckaVar);
    }

    /* renamed from: a */
    public static cnb m5116a(MutablePropertyReference0 cjrVar) {
        return f20771b.m5096a(cjrVar);
    }

    /* renamed from: a */
    public static cnh m5112a(PropertyReference1 ckcVar) {
        return f20771b.m5092a(ckcVar);
    }

    /* renamed from: a */
    public static cnc m5115a(MutablePropertyReference1 cjtVar) {
        return f20771b.m5095a(cjtVar);
    }

    /* renamed from: a */
    public static cni m5111a(PropertyReference2 ckeVar) {
        return f20771b.m5091a(ckeVar);
    }

    /* renamed from: a */
    public static cnd m5114a(MutablePropertyReference2 cjvVar) {
        return f20771b.m5094a(cjvVar);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: c */
    public static KType m5104c(Class cls) {
        return f20771b.m5090a(m5109b(cls), Collections.emptyList(), false);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: a */
    public static KType m5122a(Class cls, cnl cnlVar) {
        return f20771b.m5090a(m5109b(cls), Collections.singletonList(cnlVar), false);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: a */
    public static KType m5121a(Class cls, cnl cnlVar, cnl cnlVar2) {
        return f20771b.m5090a(m5109b(cls), Arrays.asList(cnlVar, cnlVar2), false);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: a */
    public static KType m5120a(Class cls, cnl... cnlVarArr) {
        return f20771b.m5090a(m5109b(cls), bzb.m6941t(cnlVarArr), false);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: d */
    public static KType m5102d(Class cls) {
        return f20771b.m5090a(m5109b(cls), Collections.emptyList(), true);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: b */
    public static KType m5107b(Class cls, cnl cnlVar) {
        return f20771b.m5090a(m5109b(cls), Collections.singletonList(cnlVar), true);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: b */
    public static KType m5106b(Class cls, cnl cnlVar, cnl cnlVar2) {
        return f20771b.m5090a(m5109b(cls), Arrays.asList(cnlVar, cnlVar2), true);
    }

    @bwy(m8750a = "1.4")
    /* renamed from: b */
    public static KType m5105b(Class cls, cnl... cnlVarArr) {
        return f20771b.m5090a(m5109b(cls), bzb.m6941t(cnlVarArr), true);
    }
}
