package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* loaded from: classes2.dex */
public final class PrefixLines extends BaseParamFilterReader implements ChainableReader {
    private static final String PREFIX_KEY = "prefix";
    private String prefix = null;
    private String queuedData = null;

    public PrefixLines() {
    }

    public PrefixLines(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        String str = this.queuedData;
        if (str != null && str.length() == 0) {
            this.queuedData = null;
        }
        String str2 = this.queuedData;
        if (str2 != null) {
            char charAt = str2.charAt(0);
            this.queuedData = this.queuedData.substring(1);
            if (this.queuedData.length() != 0) {
                return charAt;
            }
            this.queuedData = null;
            return charAt;
        }
        this.queuedData = readLine();
        if (this.queuedData == null) {
            return -1;
        }
        if (this.prefix != null) {
            this.queuedData = this.prefix + this.queuedData;
        }
        return read();
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    private String getPrefix() {
        return this.prefix;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        PrefixLines prefixLines = new PrefixLines(reader);
        prefixLines.setPrefix(getPrefix());
        prefixLines.setInitialized(true);
        return prefixLines;
    }

    private void initialize() {
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (PREFIX_KEY.equals(parameters[i].getName())) {
                    this.prefix = parameters[i].getValue();
                    return;
                }
            }
        }
    }
}
