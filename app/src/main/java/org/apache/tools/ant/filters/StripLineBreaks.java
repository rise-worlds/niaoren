package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* loaded from: classes2.dex */
public final class StripLineBreaks extends BaseParamFilterReader implements ChainableReader {
    private static final String DEFAULT_LINE_BREAKS = "\r\n";
    private static final String LINE_BREAKS_KEY = "linebreaks";
    private String lineBreaks = "\r\n";

    public StripLineBreaks() {
    }

    public StripLineBreaks(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        int read = this.in.read();
        while (read != -1 && this.lineBreaks.indexOf(read) != -1) {
            read = this.in.read();
        }
        return read;
    }

    public void setLineBreaks(String str) {
        this.lineBreaks = str;
    }

    private String getLineBreaks() {
        return this.lineBreaks;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        StripLineBreaks stripLineBreaks = new StripLineBreaks(reader);
        stripLineBreaks.setLineBreaks(getLineBreaks());
        stripLineBreaks.setInitialized(true);
        return stripLineBreaks;
    }

    private void initialize() {
        String str;
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (LINE_BREAKS_KEY.equals(parameters[i].getName())) {
                    str = parameters[i].getValue();
                    break;
                }
            }
        }
        str = null;
        if (str != null) {
            this.lineBreaks = str;
        }
    }
}
