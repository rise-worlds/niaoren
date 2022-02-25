package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.util.UnicodeUtil;

/* loaded from: classes2.dex */
public class EscapeUnicode extends BaseParamFilterReader implements ChainableReader {
    private StringBuffer unicodeBuf = new StringBuffer();

    private void initialize() {
    }

    public EscapeUnicode() {
    }

    public EscapeUnicode(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        char c;
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        if (this.unicodeBuf.length() == 0) {
            int read = this.in.read();
            if (read == -1 || (c = (char) read) < 128) {
                return read;
            }
            this.unicodeBuf = UnicodeUtil.EscapeUnicode(c);
            return 92;
        }
        char charAt = this.unicodeBuf.charAt(0);
        this.unicodeBuf.deleteCharAt(0);
        return charAt;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader reader) {
        EscapeUnicode escapeUnicode = new EscapeUnicode(reader);
        escapeUnicode.setInitialized(true);
        return escapeUnicode;
    }
}
