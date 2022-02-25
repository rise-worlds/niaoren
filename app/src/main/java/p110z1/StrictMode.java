package p110z1;

import mirror.RefClass;
import mirror.RefStaticInt;
import mirror.RefStaticMethod;

/* renamed from: z1.cwo */
/* loaded from: classes3.dex */
public class StrictMode {
    public static RefStaticInt DETECT_VM_FILE_URI_EXPOSURE;
    public static RefStaticInt PENALTY_DEATH_ON_FILE_URI_EXPOSURE;
    public static Class<?> TYPE = RefClass.load(StrictMode.class, "android.os.StrictMode");
    public static RefStaticMethod<Void> disableDeathOnFileUriExposure;
    public static RefStaticInt sVmPolicyMask;
}
