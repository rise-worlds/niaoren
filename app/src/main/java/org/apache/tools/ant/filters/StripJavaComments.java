package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;

/* loaded from: classes2.dex */
public final class StripJavaComments extends BaseFilterReader implements ChainableReader {
    private int readAheadCh = -1;
    private boolean inString = false;
    private boolean quoted = false;

    public StripJavaComments() {
    }

    public StripJavaComments(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        int i = this.readAheadCh;
        if (i != -1) {
            this.readAheadCh = -1;
            return i;
        }
        int read = this.in.read();
        if (read == 34 && !this.quoted) {
            this.inString = !this.inString;
            this.quoted = false;
            return read;
        } else if (read == 92) {
            this.quoted = !this.quoted;
            return read;
        } else {
            this.quoted = false;
            if (this.inString || read != 47) {
                return read;
            }
            int read2 = this.in.read();
            if (read2 == 47) {
                while (read2 != 10 && read2 != -1 && read2 != 13) {
                    read2 = this.in.read();
                }
                return read2;
            } else if (read2 == 42) {
                while (read2 != -1) {
                    read2 = this.in.read();
                    if (read2 == 42) {
                        read2 = this.in.read();
                        while (read2 == 42) {
                            read2 = this.in.read();
                        }
                        if (read2 == 47) {
                            return read();
                        }
                    }
                }
                return read2;
            } else {
                this.readAheadCh = read2;
                return 47;
            }
        }
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        return new StripJavaComments(reader);
    }
}
