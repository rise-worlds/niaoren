package p110z1;

import android.content.Intent;
import mirror.MethodParams;
import mirror.RefBoolean;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefInt;
import mirror.RefObject;

/* renamed from: z1.csr */
/* loaded from: classes3.dex */
public class ServiceStartArgs {
    public static Class<?> TYPE = RefClass.load(ServiceStartArgs.class, "android.app.ServiceStartArgs");
    public static RefObject<Intent> args;
    @MethodParams({boolean.class, int.class, int.class, Intent.class})
    public static RefConstructor<Object> ctor;
    public static RefInt flags;
    public static RefInt startId;
    public static RefBoolean taskRemoved;
}
