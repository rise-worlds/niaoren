package org.apache.tools.ant.util;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.ProjectComponent;

/* loaded from: classes2.dex */
public class LineTokenizer extends ProjectComponent implements Tokenizer {
    private static final int NOT_A_CHAR = -2;
    private String lineEnd = "";
    private int pushed = -2;
    private boolean includeDelims = false;

    public void setIncludeDelims(boolean z) {
        this.includeDelims = z;
    }

    @Override // org.apache.tools.ant.util.Tokenizer
    public String getToken(Reader reader) throws IOException {
        int i = this.pushed;
        if (i != -2) {
            this.pushed = -2;
        } else {
            i = reader.read();
        }
        if (i == -1) {
            return null;
        }
        this.lineEnd = "";
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (i == -1) {
                break;
            } else if (!z2) {
                if (i == 13) {
                    z2 = true;
                } else if (i == 10) {
                    this.lineEnd = "\n";
                    break;
                } else {
                    stringBuffer.append((char) i);
                }
                i = reader.read();
            } else if (i == 10) {
                this.lineEnd = "\r\n";
            } else {
                this.pushed = i;
                this.lineEnd = "\r";
            }
        }
        z = z2;
        if (i == -1 && z) {
            this.lineEnd = "\r";
        }
        if (this.includeDelims) {
            stringBuffer.append(this.lineEnd);
        }
        return stringBuffer.toString();
    }

    @Override // org.apache.tools.ant.util.Tokenizer
    public String getPostToken() {
        return this.includeDelims ? "" : this.lineEnd;
    }
}
