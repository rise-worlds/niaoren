package p110z1;

import android.content.pm.ApplicationInfo;
import java.util.ArrayList;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefObject;

/* renamed from: z1.cxw */
/* loaded from: classes3.dex */
public class RemoteViews {
    public static Class<?> TYPE = RefClass.load(RemoteViews.class, android.widget.RemoteViews.class);
    @MethodParams({ApplicationInfo.class, int.class})
    public static RefConstructor<android.widget.RemoteViews> ctor;
    public static RefObject<ArrayList<Object>> mActions;
    public static RefObject<ApplicationInfo> mApplication;
    public static RefObject<String> mPackage;
}
