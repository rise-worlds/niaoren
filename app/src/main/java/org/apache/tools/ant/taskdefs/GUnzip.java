package org.apache.tools.ant.taskdefs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class GUnzip extends Unpack {
    private static final int BUFFER_SIZE = 8192;
    private static final String DEFAULT_EXTENSION = ".gz";

    @Override // org.apache.tools.ant.taskdefs.Unpack
    protected String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override // org.apache.tools.ant.taskdefs.Unpack
    protected void extract() {
        GZIPInputStream gZIPInputStream;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        Throwable th;
        IOException e;
        if (this.source.lastModified() > this.dest.lastModified()) {
            log("Expanding " + this.source.getAbsolutePath() + " to " + this.dest.getAbsolutePath());
            try {
                fileOutputStream = new FileOutputStream(this.dest);
                try {
                    inputStream = this.srcResource.getInputStream();
                } catch (IOException e2) {
                    e = e2;
                    gZIPInputStream = null;
                    inputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    gZIPInputStream = null;
                    inputStream = null;
                }
            } catch (IOException e3) {
                e = e3;
                inputStream = null;
                gZIPInputStream = null;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                gZIPInputStream = null;
                fileOutputStream = null;
            }
            try {
                gZIPInputStream = new GZIPInputStream(inputStream);
                try {
                    try {
                        byte[] bArr = new byte[8192];
                        int i = 0;
                        do {
                            fileOutputStream.write(bArr, 0, i);
                            i = gZIPInputStream.read(bArr, 0, bArr.length);
                        } while (i != -1);
                        FileUtils.close(inputStream);
                        FileUtils.close(fileOutputStream);
                        FileUtils.close(gZIPInputStream);
                    } catch (IOException e4) {
                        e = e4;
                        throw new BuildException("Problem expanding gzip " + e.getMessage(), e, getLocation());
                    }
                } catch (Throwable th4) {
                    th = th4;
                    FileUtils.close(inputStream);
                    FileUtils.close(fileOutputStream);
                    FileUtils.close(gZIPInputStream);
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                gZIPInputStream = null;
            } catch (Throwable th5) {
                th = th5;
                gZIPInputStream = null;
                FileUtils.close(inputStream);
                FileUtils.close(fileOutputStream);
                FileUtils.close(gZIPInputStream);
                throw th;
            }
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Unpack
    protected boolean supportsNonFileResources() {
        return getClass().equals(GUnzip.class);
    }
}
