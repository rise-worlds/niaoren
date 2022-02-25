package org.apache.tools.ant.filters;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public abstract class BaseFilterReader extends FilterReader {
    private static final int BUFFER_SIZE = 8192;
    private boolean initialized = false;
    private Project project = null;

    public BaseFilterReader() {
        super(new StringReader(""));
        FileUtils.close(this);
    }

    public BaseFilterReader(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read(char[] cArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            int read = read();
            if (read != -1) {
                cArr[i + i3] = (char) read;
            } else if (i3 == 0) {
                return -1;
            } else {
                return i3;
            }
        }
        return i2;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final long skip(long j) throws IOException, IllegalArgumentException {
        if (j >= 0) {
            for (long j2 = 0; j2 < j; j2++) {
                if (read() == -1) {
                    return j2;
                }
            }
            return j;
        }
        throw new IllegalArgumentException("skip value is negative");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setInitialized(boolean z) {
        this.initialized = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getInitialized() {
        return this.initialized;
    }

    public final void setProject(Project project) {
        this.project = project;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Project getProject() {
        return this.project;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String readLine() throws IOException {
        int read = this.in.read();
        if (read == -1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (read != -1) {
            stringBuffer.append((char) read);
            if (read == 10) {
                break;
            }
            read = this.in.read();
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String readFully() throws IOException {
        return FileUtils.readFully(this.in, 8192);
    }
}
