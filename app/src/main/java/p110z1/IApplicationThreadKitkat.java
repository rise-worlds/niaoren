package p110z1;

import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.csa */
/* loaded from: classes3.dex */
public class IApplicationThreadKitkat {
    public static Class<?> TYPE = RefClass.load(IApplicationThreadKitkat.class, "android.app.IApplicationThread");
    @MethodParams({IBinder.class, Intent.class, boolean.class, int.class})
    public static RefMethod<Void> scheduleBindService;
    @MethodParams({IBinder.class, ServiceInfo.class, CompatibilityInfo.class, int.class})
    public static RefMethod<Void> scheduleCreateService;
    @MethodReflectParams({"android.content.Intent", "android.content.pm.ActivityInfo", "android.content.res.CompatibilityInfo", "int", "java.lang.String", "android.os.Bundle", "boolean", "int", "int"})
    public static RefMethod<Void> scheduleReceiver;
}
