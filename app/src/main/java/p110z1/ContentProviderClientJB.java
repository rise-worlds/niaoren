package p110z1;

import android.content.ContentProviderClient;
import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefConstructor;

/* renamed from: z1.ctl */
/* loaded from: classes3.dex */
public class ContentProviderClientJB {
    public static Class TYPE = RefClass.load(ContentProviderClientJB.class, ContentProviderClient.class);
    @MethodReflectParams({"android.content.ContentResolver", "android.content.IContentProvider", "boolean"})
    public static RefConstructor<ContentProviderClient> ctor;
}
