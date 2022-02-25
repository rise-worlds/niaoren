package p110z1;

import mirror.RefClass;
import mirror.RefLong;

/* renamed from: z1.cty */
/* loaded from: classes3.dex */
public class PeriodicSync {
    public static Class<?> TYPE = RefClass.load(PeriodicSync.class, android.content.PeriodicSync.class);
    public static RefLong flexTime;

    public static android.content.PeriodicSync clone(android.content.PeriodicSync periodicSync) {
        android.content.PeriodicSync periodicSync2 = new android.content.PeriodicSync(periodicSync.account, periodicSync.authority, periodicSync.extras, periodicSync.period);
        RefLong gVar = flexTime;
        gVar.set(periodicSync2, gVar.get(periodicSync));
        return periodicSync2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean syncExtrasEquals(android.os.Bundle r5, android.os.Bundle r6) {
        /*
            int r0 = r5.size()
            int r1 = r6.size()
            r2 = 0
            if (r0 == r1) goto L_0x000c
            return r2
        L_0x000c:
            boolean r0 = r5.isEmpty()
            r1 = 1
            if (r0 == 0) goto L_0x0014
            return r1
        L_0x0014:
            java.util.Set r0 = r5.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x001c:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x003e
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = r6.containsKey(r3)
            if (r4 != 0) goto L_0x002f
            return r2
        L_0x002f:
            java.lang.Object r4 = r5.get(r3)
            java.lang.Object r3 = r6.get(r3)
            boolean r3 = com.lody.virtual.helper.compat.ObjectsCompat.equals(r4, r3)
            if (r3 != 0) goto L_0x001c
            return r2
        L_0x003e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PeriodicSync.syncExtrasEquals(android.os.Bundle, android.os.Bundle):boolean");
    }
}
