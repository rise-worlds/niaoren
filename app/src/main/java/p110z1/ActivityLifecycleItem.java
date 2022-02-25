package p110z1;

import mirror.RefClass;
import mirror.RefMethod;

/* renamed from: z1.csy */
/* loaded from: classes3.dex */
public class ActivityLifecycleItem {
    public static final int ON_CREATE = 1;
    public static final int ON_DESTROY = 6;
    public static final int ON_PAUSE = 4;
    public static final int ON_RESTART = 7;
    public static final int ON_RESUME = 3;
    public static final int ON_START = 2;
    public static final int ON_STOP = 5;
    public static final int PRE_ON_CREATE = 0;
    public static Class<?> TYPE = RefClass.load(ActivityLifecycleItem.class, "android.app.servertransaction.ActivityLifecycleItem");
    public static final int UNDEFINED = -1;
    public static RefMethod<Integer> getTargetState;
}
