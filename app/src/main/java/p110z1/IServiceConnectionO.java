package p110z1;

import android.content.ComponentName;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.csd */
/* loaded from: classes3.dex */
public class IServiceConnectionO {
    public static Class<?> TYPE = RefClass.load(IServiceConnectionO.class, "android.app.IServiceConnection");
    @MethodParams({ComponentName.class, IBinder.class, boolean.class})
    public static RefMethod<Void> connected;
}
