package p110z1;

import android.content.Intent;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.cru */
/* loaded from: classes3.dex */
public class IActivityManagerN {
    public static Class<?> TYPE = RefClass.load(IActivityManagerN.class, "android.app.IActivityManager");
    @MethodParams({IBinder.class, int.class, Intent.class, int.class})
    public static RefMethod<Boolean> finishActivity;
}
