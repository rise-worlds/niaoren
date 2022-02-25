package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.filters.ChainableReader;
import org.apache.tools.ant.types.RedirectorElement;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileProvider;

/* loaded from: classes2.dex */
public class VerifyJar extends AbstractJarSignerTask {
    public static final String ERROR_NO_FILE = "Not found :";
    public static final String ERROR_NO_VERIFY = "Failed to verify ";
    private static final String VERIFIED_TEXT = "jar verified.";
    private boolean certificates = false;
    private BufferingOutputFilter outputCache = new BufferingOutputFilter();

    public void setCertificates(boolean z) {
        this.certificates = z;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if ((this.jar != null) || hasResources()) {
            beginExecution();
            RedirectorElement redirector = getRedirector();
            redirector.setAlwaysLog(true);
            redirector.createOutputFilterChain().add(this.outputCache);
            try {
                Iterator<Resource> it = createUnifiedSourcePath().iterator();
                while (it.hasNext()) {
                    verifyOneJar(((FileProvider) it.next().mo14823as(FileProvider.class)).getFile());
                }
            } finally {
                endExecution();
            }
        } else {
            throw new BuildException(AbstractJarSignerTask.ERROR_NO_SOURCE);
        }
    }

    private void verifyOneJar(File file) {
        if (file.exists()) {
            ExecTask createJarSigner = createJarSigner();
            setCommonOptions(createJarSigner);
            bindToKeystore(createJarSigner);
            addValue(createJarSigner, "-verify");
            if (this.certificates) {
                addValue(createJarSigner, "-certs");
            }
            addValue(createJarSigner, file.getPath());
            log("Verifying JAR: " + file.getAbsolutePath());
            this.outputCache.clear();
            BuildException e = null;
            try {
                createJarSigner.execute();
            } catch (BuildException e2) {
                e = e2;
            }
            String bufferingOutputFilter = this.outputCache.toString();
            if (e != null) {
                if (bufferingOutputFilter.indexOf("zip file closed") >= 0) {
                    log("You are running jarsigner against a JVM with a known bug that manifests as an IllegalStateException.", 1);
                } else {
                    throw e;
                }
            }
            if (bufferingOutputFilter.indexOf(VERIFIED_TEXT) < 0) {
                throw new BuildException(ERROR_NO_VERIFY + file);
            }
            return;
        }
        throw new BuildException(ERROR_NO_FILE + file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BufferingOutputFilter implements ChainableReader {
        private BufferingOutputFilterReader buffer;

        private BufferingOutputFilter() {
        }

        @Override // org.apache.tools.ant.filters.ChainableReader
        public Reader chain(Reader reader) {
            this.buffer = new BufferingOutputFilterReader(reader);
            return this.buffer;
        }

        public String toString() {
            return this.buffer.toString();
        }

        public void clear() {
            BufferingOutputFilterReader bufferingOutputFilterReader = this.buffer;
            if (bufferingOutputFilterReader != null) {
                bufferingOutputFilterReader.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BufferingOutputFilterReader extends Reader {
        private StringBuffer buffer = new StringBuffer();
        private Reader next;

        public BufferingOutputFilterReader(Reader reader) {
            this.next = reader;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            int read = this.next.read(cArr, i, i2);
            this.buffer.append(cArr, i, i2);
            return read;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.next.close();
        }

        public String toString() {
            return this.buffer.toString();
        }

        public void clear() {
            this.buffer = new StringBuffer();
        }
    }
}
