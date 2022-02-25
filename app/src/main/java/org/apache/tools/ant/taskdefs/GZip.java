package org.apache.tools.ant.taskdefs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class GZip extends Pack {
    @Override // org.apache.tools.ant.taskdefs.Pack
    protected void pack() {
        Throwable th;
        IOException e;
        GZIPOutputStream gZIPOutputStream;
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(new FileOutputStream(this.zipFile));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            zipResource(getSrcResource(), gZIPOutputStream);
            FileUtils.close(gZIPOutputStream);
        } catch (IOException e3) {
            e = e3;
            throw new BuildException("Problem creating gzip " + e.getMessage(), e, getLocation());
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream2 = gZIPOutputStream;
            FileUtils.close(gZIPOutputStream2);
            throw th;
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Pack
    protected boolean supportsNonFileResources() {
        return getClass().equals(GZip.class);
    }
}
