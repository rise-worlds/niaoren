package p110z1;

import android.annotation.TargetApi;
import android.os.IInterface;
import mirror.RefClass;
import mirror.RefObject;

@TargetApi(24)
/* renamed from: z1.cwq */
/* loaded from: classes3.dex */
public class SystemHealthManager {
    public static Class<?> TYPE = RefClass.load(SystemHealthManager.class, android.os.health.SystemHealthManager.class);
    public static RefObject<IInterface> mBatteryStats;
}
