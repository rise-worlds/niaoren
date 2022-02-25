package p110z1;

import java.io.File;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefStaticMethod;

/* renamed from: z1.cxp */
/* loaded from: classes3.dex */
public class RenderScript {
    public static Class<?> TYPE = RefClass.load(RenderScript.class, android.renderscript.RenderScript.class);
    @MethodParams({File.class})
    public static RefStaticMethod<Void> setupDiskCache;
}
