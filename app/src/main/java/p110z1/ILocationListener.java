package p110z1;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefStaticMethod;

/* renamed from: z1.cvj */
/* loaded from: classes3.dex */
public class ILocationListener {
    public static Class<?> TYPE = RefClass.load(ILocationListener.class, "android.location.ILocationListener");
    @MethodParams({Location.class})
    public static RefMethod<Void> onLocationChanged;

    /* compiled from: ILocationListener.java */
    /* renamed from: z1.cvj$a */
    /* loaded from: classes3.dex */
    public static class C5154a {
        public static Class<?> TYPE = RefClass.load(C5154a.class, "android.location.ILocationListener$Stub");
        @MethodParams({IBinder.class})
        public static RefStaticMethod<IInterface> asInterface;
    }
}
