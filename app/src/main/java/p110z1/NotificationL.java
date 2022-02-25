package p110z1;

import android.app.Notification;
import android.content.Context;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefStaticMethod;

/* renamed from: z1.csl */
/* loaded from: classes3.dex */
public class NotificationL {
    public static Class<?> TYPE = RefClass.load(NotificationL.class, Notification.class);

    /* compiled from: NotificationL.java */
    /* renamed from: z1.csl$a */
    /* loaded from: classes3.dex */
    public static class C5124a {
        public static Class<?> TYPE = RefClass.load(C5124a.class, Notification.Builder.class);
        @MethodParams({Context.class, Notification.class})
        public static RefStaticMethod<Notification> rebuild;
    }
}
