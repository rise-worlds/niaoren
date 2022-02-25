package com.lidroid.xutils.http.callback;

/* loaded from: classes.dex */
public class FileDownloadHandler {
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0077, code lost:
        r14.flush();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007a, code lost:
        if (r17 == null) goto L_0x0083;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007c, code lost:
        r17.updateProgress(r11, r9, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
        com.lidroid.xutils.util.IOUtils.closeQuietly(r13);
        com.lidroid.xutils.util.IOUtils.closeQuietly(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008d, code lost:
        if (r8.exists() == false) goto L_0x00ca;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0093, code lost:
        if (android.text.TextUtils.isEmpty(r20) != false) goto L_0x00ca;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0095, code lost:
        r0 = new java.io.File(r8.getParent(), r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a2, code lost:
        if (r0.exists() != false) goto L_0x00ac;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a8, code lost:
        if (r8.renameTo(r0) == false) goto L_0x00ab;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00aa, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ab, code lost:
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ac, code lost:
        r0 = new java.io.File(r8.getParent(), java.lang.String.valueOf(java.lang.System.currentTimeMillis()) + r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ca, code lost:
        return r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.io.File handleEntity(org.apache.http.HttpEntity r16, com.lidroid.xutils.http.callback.RequestCallBackHandler r17, java.lang.String r18, boolean r19, java.lang.String r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.http.callback.FileDownloadHandler.handleEntity(org.apache.http.HttpEntity, com.lidroid.xutils.http.callback.RequestCallBackHandler, java.lang.String, boolean, java.lang.String):java.io.File");
    }
}
