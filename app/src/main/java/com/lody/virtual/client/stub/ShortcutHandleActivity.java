package com.lody.virtual.client.stub;

import android.app.Activity;

/* loaded from: classes.dex */
public class ShortcutHandleActivity extends Activity {
    /* JADX WARN: Removed duplicated region for block: B:17:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x002f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            r5.finish()
            android.content.Intent r6 = r5.getIntent()
            if (r6 != 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.String r0 = "_VA_|_user_id_"
            r1 = 0
            int r0 = r6.getIntExtra(r0, r1)
            java.lang.String r2 = "_VA_|_splash_"
            java.lang.String r2 = r6.getStringExtra(r2)
            java.lang.String r3 = "_VA_|_uri_"
            java.lang.String r6 = r6.getStringExtra(r3)
            r3 = 0
            if (r2 == 0) goto L_0x002c
            android.content.Intent r2 = android.content.Intent.parseUri(r2, r1)     // Catch: URISyntaxException -> 0x0028
            goto L_0x002d
        L_0x0028:
            r2 = move-exception
            r2.printStackTrace()
        L_0x002c:
            r2 = r3
        L_0x002d:
            if (r6 == 0) goto L_0x0038
            android.content.Intent r6 = android.content.Intent.parseUri(r6, r1)     // Catch: URISyntaxException -> 0x0034
            goto L_0x0039
        L_0x0034:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0038:
            r6 = r3
        L_0x0039:
            if (r6 != 0) goto L_0x003c
            return
        L_0x003c:
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 15
            if (r1 < r4) goto L_0x0045
            r6.setSelector(r3)
        L_0x0045:
            if (r2 != 0) goto L_0x0054
            com.lody.virtual.client.ipc.VActivityManager r1 = com.lody.virtual.client.ipc.VActivityManager.get()     // Catch: Throwable -> 0x004f
            r1.startActivity(r6, r0)     // Catch: Throwable -> 0x004f
            goto L_0x0061
        L_0x004f:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0061
        L_0x0054:
            java.lang.String r1 = "android.intent.extra.INTENT"
            r2.putExtra(r1, r6)
            java.lang.String r6 = "android.intent.extra.CC"
            r2.putExtra(r6, r0)
            r5.startActivity(r2)
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.client.stub.ShortcutHandleActivity.onCreate(android.os.Bundle):void");
    }
}
