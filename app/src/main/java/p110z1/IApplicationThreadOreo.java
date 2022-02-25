package p110z1;

import android.os.IBinder;
import android.os.IInterface;
import mirror.MethodParams;
import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefStaticMethod;

/* renamed from: z1.csb */
/* loaded from: classes3.dex */
public class IApplicationThreadOreo {
    public static Class<?> TYPE = RefClass.load(IApplicationThreadOreo.class, "android.app.IApplicationThread");
    @MethodReflectParams({"android.os.IBinder", "android.content.pm.ParceledListSlice"})
    public static RefMethod<Void> scheduleServiceArgs;

    /* compiled from: IApplicationThreadOreo.java */
    /* renamed from: z1.csb$a */
    /* loaded from: classes3.dex */
    public static final class C5117a {
        public static Class<?> TYPE = RefClass.load(C5117a.class, "android.app.IApplicationThread$Stub");
        @MethodParams({IBinder.class})
        public static RefStaticMethod<IInterface> asInterface;
    }
}
