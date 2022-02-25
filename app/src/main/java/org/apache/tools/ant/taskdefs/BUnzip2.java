package org.apache.tools.ant.taskdefs;

/* loaded from: classes2.dex */
public class BUnzip2 extends Unpack {
    private static final int BUFFER_SIZE = 8192;
    private static final String DEFAULT_EXTENSION = ".bz2";

    @Override // org.apache.tools.ant.taskdefs.Unpack
    protected String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v17, types: [org.apache.tools.bzip2.CBZip2InputStream, java.io.InputStream] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // org.apache.tools.ant.taskdefs.Unpack
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void extract() {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.BUnzip2.extract():void");
    }

    @Override // org.apache.tools.ant.taskdefs.Unpack
    protected boolean supportsNonFileResources() {
        return getClass().equals(BUnzip2.class);
    }
}
