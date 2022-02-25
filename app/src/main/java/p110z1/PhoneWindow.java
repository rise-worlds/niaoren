package p110z1;

import android.os.IInterface;
import mirror.RefClass;
import mirror.RefStaticObject;

/* renamed from: z1.cyi */
/* loaded from: classes3.dex */
public class PhoneWindow {
    public static Class<?> TYPE;
    public static RefStaticObject<IInterface> sWindowManager;

    static {
        TYPE = RefClass.load(PhoneWindow.class, "com.android.internal.policy.impl.PhoneWindow$WindowManagerHolder");
        if (TYPE == null) {
            TYPE = RefClass.load(PhoneWindow.class, "com.android.internal.policy.PhoneWindow$WindowManagerHolder");
        }
    }
}
