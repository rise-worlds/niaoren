package org.apache.tools.ant.taskdefs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.bzip2.CBZip2InputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

/* loaded from: classes2.dex */
public class Untar extends Expand {
    private UntarCompressionMethod compression = new UntarCompressionMethod();

    public Untar() {
        super(null);
    }

    public void setCompression(UntarCompressionMethod untarCompressionMethod) {
        this.compression = untarCompressionMethod;
    }

    @Override // org.apache.tools.ant.taskdefs.Expand
    public void setScanForUnicodeExtraFields(boolean z) {
        throw new BuildException("The " + getTaskName() + " task doesn't support the encoding attribute", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.Expand
    protected void expandFile(FileUtils fileUtils, File file, File file2) {
        Throwable th;
        FileInputStream fileInputStream;
        IOException e;
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        expandStream(file.getPath(), fileInputStream, file2);
                        FileUtils.close(fileInputStream);
                    } catch (IOException e2) {
                        e = e2;
                        throw new BuildException("Error while expanding " + file.getPath() + "\n" + e.toString(), e, getLocation());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.close(fileInputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                FileUtils.close(fileInputStream);
                throw th;
            }
        } else {
            throw new BuildException("Unable to untar " + file + " as the file does not exist", getLocation());
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Expand
    protected void expandResource(Resource resource, File file) {
        if (resource.isExists()) {
            InputStream inputStream = null;
            try {
                try {
                    inputStream = resource.getInputStream();
                    expandStream(resource.getName(), inputStream, file);
                } catch (IOException e) {
                    throw new BuildException("Error while expanding " + resource.getName(), e, getLocation());
                }
            } finally {
                FileUtils.close(inputStream);
            }
        } else {
            throw new BuildException("Unable to untar " + resource.getName() + " as the it does not exist", getLocation());
        }
    }

    private void expandStream(String str, InputStream inputStream, File file) throws IOException {
        Throwable th;
        TarInputStream tarInputStream;
        try {
            tarInputStream = new TarInputStream(this.compression.decompress(str, new BufferedInputStream(inputStream)), getEncoding());
            try {
                log("Expanding: " + str + " into " + file, 2);
                boolean z = true;
                FileNameMapper mapper = getMapper();
                while (true) {
                    TarEntry nextEntry = tarInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    z = false;
                    extractFile(FileUtils.getFileUtils(), null, file, tarInputStream, nextEntry.getName(), nextEntry.getModTime(), nextEntry.isDirectory(), mapper);
                }
                if (z && getFailOnEmptyArchive()) {
                    throw new BuildException("archive '" + str + "' is empty");
                }
                log("expand complete", 3);
                FileUtils.close(tarInputStream);
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close(tarInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            tarInputStream = null;
        }
    }

    /* loaded from: classes2.dex */
    public static final class UntarCompressionMethod extends EnumeratedAttribute {
        private static final String BZIP2 = "bzip2";
        private static final String GZIP = "gzip";
        private static final String NONE = "none";

        public UntarCompressionMethod() {
            setValue("none");
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"none", "gzip", BZIP2};
        }

        public InputStream decompress(String str, InputStream inputStream) throws IOException, BuildException {
            String value = getValue();
            if ("gzip".equals(value)) {
                return new GZIPInputStream(inputStream);
            }
            if (!BZIP2.equals(value)) {
                return inputStream;
            }
            for (char c : new char[]{'B', 'Z'}) {
                if (inputStream.read() != c) {
                    throw new BuildException("Invalid bz2 file." + str);
                }
            }
            return new CBZip2InputStream(inputStream);
        }
    }
}
