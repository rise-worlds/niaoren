package p110z1;

import android.content.pm.ApplicationInfo;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefObject;

/* renamed from: z1.cug */
/* loaded from: classes3.dex */
public class ApplicationInfoP {
    public static Class<?> TYPE = RefClass.load(ApplicationInfoP.class, ApplicationInfo.class);
    @MethodParams({int.class})
    public static RefMethod<Void> setHiddenApiEnforcementPolicy;
    public static RefObject<String[]> splitClassLoaderNames;
}
