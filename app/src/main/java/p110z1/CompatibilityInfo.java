package p110z1;

import android.content.pm.ApplicationInfo;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefStaticObject;

/* renamed from: z1.cuy */
/* loaded from: classes3.dex */
public class CompatibilityInfo {
    public static RefStaticObject<Object> DEFAULT_COMPATIBILITY_INFO;
    public static Class<?> TYPE = RefClass.load(CompatibilityInfo.class, "android.content.res.CompatibilityInfo");
    @MethodParams({ApplicationInfo.class, int.class, int.class, boolean.class})
    public static RefConstructor ctor;
    @MethodParams({ApplicationInfo.class, int.class, int.class, boolean.class, int.class})
    public static RefConstructor ctorLG;
}
