package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* loaded from: classes2.dex */
public final class SuffixLines extends BaseParamFilterReader implements ChainableReader {
    private static final String SUFFIX_KEY = "suffix";
    private String suffix = null;
    private String queuedData = null;

    public SuffixLines() {
    }

    public SuffixLines(Reader reader) {
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
        String str3 = this.queuedData;
        if (str3 == null) {
            return -1;
        }
        if (this.suffix != null) {
            String str4 = "";
            if (str3.endsWith("\r\n")) {
                str4 = "\r\n";
            } else if (this.queuedData.endsWith("\n")) {
                str4 = "\n";
            }
            StringBuilder sb = new StringBuilder();
            String str5 = this.queuedData;
            sb.append(str5.substring(0, str5.length() - str4.length()));
            sb.append(this.suffix);
            sb.append(str4);
            this.queuedData = sb.toString();
        }
        return read();
    }

    public void setSuffix(String str) {
        this.suffix = str;
    }

    private String getSuffix() {
        return this.suffix;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        SuffixLines suffixLines = new SuffixLines(reader);
        suffixLines.setSuffix(getSuffix());
        suffixLines.setInitialized(true);
        return suffixLines;
    }

    private void initialize() {
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (SUFFIX_KEY.equals(parameters[i].getName())) {
                    this.suffix = parameters[i].getValue();
                    return;
                }
            }
        }
    }
}
