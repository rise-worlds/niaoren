package p110z1;

import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.csq */
/* loaded from: classes3.dex */
public class Service {
    public static Class<?> TYPE = RefClass.load(Service.class, android.app.Service.class);
    @MethodReflectParams({"android.content.Context", "android.app.ActivityThread", "java.lang.String", "android.os.IBinder", "android.app.Application", "java.lang.Object"})
    public static RefMethod<Void> attach;
}
