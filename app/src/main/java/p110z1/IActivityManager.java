package p110z1;

import android.content.pm.ProviderInfo;
import android.os.IBinder;
import android.os.IInterface;
import mirror.MethodParams;
import mirror.RefBoolean;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefObject;

/* renamed from: z1.crr */
/* loaded from: classes3.dex */
public class IActivityManager {
    public static Class<?> TYPE = RefClass.load(IActivityManager.class, "android.app.IActivityManager");
    @MethodParams({IBinder.class, boolean.class})
    public static RefMethod<Integer> getTaskForActivity;
    @MethodParams({IBinder.class, String.class, int.class, int.class})
    public static RefMethod<Void> overridePendingTransition;
    @MethodParams({IBinder.class, int.class})
    public static RefMethod<Void> setRequestedOrientation;
    public static RefMethod<Integer> startActivities;
    public static RefMethod<Integer> startActivity;

    /* compiled from: IActivityManager.java */
    /* renamed from: z1.crr$a */
    /* loaded from: classes3.dex */
    public static class C5112a {
        public static Class<?> TYPE = RefClass.load(C5112a.class, "android.app.IActivityManager$ContentProviderHolder");
        public static RefObject<ProviderInfo> info;
        public static RefBoolean noReleaseNeeded;
        public static RefObject<IInterface> provider;
    }

    /* compiled from: IActivityManager.java */
    /* renamed from: z1.crr$b */
    /* loaded from: classes3.dex */
    public static class C5113b {
        public static Class<?> TYPE = RefClass.load(C5112a.class, "android.app.IActivityManager$ContentProviderHolder");
        public static RefObject<ProviderInfo> info;
        public static RefBoolean noReleaseNeeded;
        public static RefObject<IInterface> provider;
        public static RefBoolean waitProcessStart;
    }
}
