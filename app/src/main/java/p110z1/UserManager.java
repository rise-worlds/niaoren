package p110z1;

import android.os.IBinder;
import android.os.IInterface;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefStaticMethod;

/* renamed from: z1.cyh */
/* loaded from: classes3.dex */
public class UserManager {
    public static Class<?> TYPE = RefClass.load(UserManager.class, "android.os.UserManager");

    /* compiled from: UserManager.java */
    /* renamed from: z1.cyh$a */
    /* loaded from: classes3.dex */
    public static class C5201a {
        public static Class<?> TYPE = RefClass.load(C5201a.class, "android.os.IUserManager$Stub");
        @MethodParams({IBinder.class})
        public static RefStaticMethod<IInterface> asInterface;
    }
}
