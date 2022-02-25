package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.util.LineTokenizer;

/* loaded from: classes2.dex */
public final class HeadFilter extends BaseParamFilterReader implements ChainableReader {
    private static final int DEFAULT_NUM_LINES = 10;
    private static final String LINES_KEY = "lines";
    private static final String SKIP_KEY = "skip";
    private boolean eof;
    private String line;
    private int linePos;
    private LineTokenizer lineTokenizer;
    private long lines;
    private long linesRead;
    private long skip;

    public HeadFilter() {
        this.linesRead = 0L;
        this.lines = 10L;
        this.skip = 0L;
        this.lineTokenizer = null;
        this.line = null;
        this.linePos = 0;
    }

    public HeadFilter(Reader reader) {
        super(reader);
        this.linesRead = 0L;
        this.lines = 10L;
        this.skip = 0L;
        this.lineTokenizer = null;
        this.line = null;
        this.linePos = 0;
        this.lineTokenizer = new LineTokenizer();
        this.lineTokenizer.setIncludeDelims(true);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        while (true) {
            String str = this.line;
            if (str == null || str.length() == 0) {
                this.line = this.lineTokenizer.getToken(this.in);
                String str2 = this.line;
                if (str2 == null) {
                    return -1;
                }
                this.line = headFilter(str2);
                if (this.eof) {
                    return -1;
                }
                this.linePos = 0;
            } else {
                char charAt = this.line.charAt(this.linePos);
                this.linePos++;
                if (this.linePos == this.line.length()) {
                    this.line = null;
                }
                return charAt;
            }
        }
    }

    public void setLines(long j) {
        this.lines = j;
    }

    private long getLines() {
        return this.lines;
    }

    public void setSkip(long j) {
        this.skip = j;
    }

    private long getSkip() {
        return this.skip;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        HeadFilter headFilter = new HeadFilter(reader);
        headFilter.setLines(getLines());
        headFilter.setSkip(getSkip());
        headFilter.setInitialized(true);
        return headFilter;
    }

    private void initialize() {
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (LINES_KEY.equals(parameters[i].getName())) {
                    this.lines = Long.parseLong(parameters[i].getValue());
                } else if ("skip".equals(parameters[i].getName())) {
                    this.skip = Long.parseLong(parameters[i].getValue());
                }
            }
        }
    }

    private String headFilter(String str) {
        this.linesRead++;
        long j = this.skip;
        if (j > 0 && this.linesRead - 1 < j) {
            return null;
        }
        long j2 = this.lines;
        if (j2 <= 0 || this.linesRead <= j2 + this.skip) {
            return str;
        }
        this.eof = true;
        return null;
    }
}
