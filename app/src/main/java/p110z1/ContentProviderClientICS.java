package p110z1;

import android.content.ContentProviderClient;
import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefConstructor;

/* renamed from: z1.ctk */
/* loaded from: classes3.dex */
public class ContentProviderClientICS {
    public static Class TYPE = RefClass.load(ContentProviderClientICS.class, ContentProviderClient.class);
    @MethodReflectParams({"android.content.ContentResolver", "android.content.IContentProvider"})
    public static RefConstructor<ContentProviderClient> ctor;
}
