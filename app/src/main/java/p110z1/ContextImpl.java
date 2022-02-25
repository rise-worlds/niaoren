package p110z1;

import android.content.Context;
import android.content.pm.PackageManager;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefObject;

/* renamed from: z1.cro */
/* loaded from: classes3.dex */
public class ContextImpl {
    public static Class<?> TYPE = RefClass.load(ContextImpl.class, "android.app.ContextImpl");
    public static RefMethod<Context> getReceiverRestrictedContext;
    @MethodParams({Context.class})
    public static RefObject<String> mBasePackageName;
    public static RefObject<Object> mPackageInfo;
    public static RefObject<PackageManager> mPackageManager;
    @MethodParams({Context.class})
    public static RefMethod<Void> setOuterContext;
}
