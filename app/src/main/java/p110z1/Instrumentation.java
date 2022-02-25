package p110z1;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.csf */
/* loaded from: classes3.dex */
public class Instrumentation {
    public static Class<?> TYPE = RefClass.load(Instrumentation.class, android.app.Instrumentation.class);
    @MethodParams({Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class})
    public static RefMethod<Instrumentation.ActivityResult> execStartActivity;
}
