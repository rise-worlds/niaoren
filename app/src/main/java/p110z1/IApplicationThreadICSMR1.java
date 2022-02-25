package p110z1;

import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.cry */
/* loaded from: classes3.dex */
public class IApplicationThreadICSMR1 {
    public static Class<?> TYPE = RefClass.load(IApplicationThreadICSMR1.class, "android.app.IApplicationThread");
    @MethodParams({IBinder.class, ServiceInfo.class, CompatibilityInfo.class})
    public static RefMethod<Void> scheduleCreateService;
    @MethodReflectParams({"android.content.Intent", "android.content.pm.ActivityInfo", "android.content.res.CompatibilityInfo", "int", "java.lang.String", "android.os.Bundle", "boolean"})
    public static RefMethod<Void> scheduleReceiver;
    @MethodParams({IBinder.class, boolean.class, int.class, int.class, Intent.class})
    public static RefMethod<Void> scheduleServiceArgs;
}
