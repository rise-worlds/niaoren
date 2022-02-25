package p110z1;

import android.content.Intent;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.crs */
/* loaded from: classes3.dex */
public class IActivityManagerICS {
    public static Class<?> TYPE = RefClass.load(IActivityManagerICS.class, "android.app.IActivityManager");
    @MethodParams({IBinder.class, int.class, Intent.class})
    public static RefMethod<Boolean> finishActivity;
}
