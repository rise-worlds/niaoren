package p110z1;

import android.os.IBinder;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.cwb */
/* loaded from: classes3.dex */
public class Bundle {
    public static Class<?> TYPE = RefClass.load(Bundle.class, android.os.Bundle.class);
    @MethodParams({String.class})
    public static RefMethod<IBinder> getIBinder;
    @MethodParams({String.class, IBinder.class})
    public static RefMethod<Void> putIBinder;
}
