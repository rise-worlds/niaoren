package p110z1;

import android.content.Intent;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.crt */
/* loaded from: classes3.dex */
public class IActivityManagerL {
    public static Class<?> TYPE = RefClass.load(IActivityManagerL.class, "android.app.IActivityManager");
    @MethodParams({IBinder.class, int.class, Intent.class, boolean.class})
    public static RefMethod<Boolean> finishActivity;
}
