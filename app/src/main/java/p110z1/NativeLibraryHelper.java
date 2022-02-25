package p110z1;

import java.io.File;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefStaticMethod;

/* renamed from: z1.cyd */
/* loaded from: classes3.dex */
public class NativeLibraryHelper {
    public static Class<?> TYPE = RefClass.load(NativeLibraryHelper.class, "com.android.internal.content.NativeLibraryHelper");
    @MethodParams({C5198a.class, File.class, String.class})
    public static RefStaticMethod<Integer> copyNativeBinaries;
    @MethodParams({C5198a.class, String[].class})
    public static RefStaticMethod<Integer> findSupportedAbi;

    /* compiled from: NativeLibraryHelper.java */
    /* renamed from: z1.cyd$a */
    /* loaded from: classes3.dex */
    public static class C5198a {
        public static Class<?> TYPE = RefClass.load(C5198a.class, "com.android.internal.content.NativeLibraryHelper$Handle");
        @MethodParams({File.class})
        public static RefStaticMethod<Object> create;
    }
}
