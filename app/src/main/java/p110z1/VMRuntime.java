package p110z1;

import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;
import mirror.RefStaticMethod;

/* renamed from: z1.cyu */
/* loaded from: classes3.dex */
public class VMRuntime {
    public static Class<?> TYPE = RefClass.load(VMRuntime.class, "dalvik.system.VMRuntime");
    public static RefStaticMethod<String> getCurrentInstructionSet;
    public static RefStaticMethod<Object> getRuntime;
    public static RefMethod<Boolean> is64Bit;
    @MethodParams({String.class})
    public static RefStaticMethod<Boolean> is64BitAbi;
    public static RefMethod<Boolean> isJavaDebuggable;
    @MethodParams({int.class})
    public static RefMethod<Void> setTargetSdkVersion;
}
