package org.apache.tools.ant.taskdefs;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.bzip2.CBZip2OutputStream;

/* loaded from: classes2.dex */
public class BZip2 extends Pack {
    @Override // org.apache.tools.ant.taskdefs.Pack
    protected void pack() {
        Throwable th;
        IOException e;
        CBZip2OutputStream cBZip2OutputStream;
        CBZip2OutputStream cBZip2OutputStream2 = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.zipFile));
                bufferedOutputStream.write(66);
                bufferedOutputStream.write(90);
                cBZip2OutputStream = new CBZip2OutputStream(bufferedOutputStream);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            zipResource(getSrcResource(), cBZip2OutputStream);
            FileUtils.close(cBZip2OutputStream);
        } catch (IOException e3) {
            e = e3;
            cBZip2OutputStream2 = cBZip2OutputStream;
            throw new BuildException("Problem creating bzip2 " + e.getMessage(), e, getLocation());
        } catch (Throwable th3) {
            th = th3;
            cBZip2OutputStream2 = cBZip2OutputStream;
            FileUtils.close(cBZip2OutputStream2);
            throw th;
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Pack
    protected boolean supportsNonFileResources() {
        return getClass().equals(BZip2.class);
    }
}
