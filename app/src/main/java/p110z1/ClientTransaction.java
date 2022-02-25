package p110z1;

import android.os.IBinder;
import java.util.List;
import mirror.RefClass;
import mirror.RefObject;

/* renamed from: z1.csz */
/* loaded from: classes3.dex */
public class ClientTransaction {
    public static Class<?> TYPE = RefClass.load(ClientTransaction.class, "android.app.servertransaction.ClientTransaction");
    public static RefObject<List<Object>> mActivityCallbacks;
    public static RefObject<IBinder> mActivityToken;
    public static RefObject<Object> mLifecycleStateRequest;
}
