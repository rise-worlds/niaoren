package org.apache.tools.mail;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class SmtpResponseReader {
    protected BufferedReader reader;
    private StringBuffer result = new StringBuffer();

    public SmtpResponseReader(InputStream inputStream) {
        this.reader = null;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String getResponse() throws IOException {
        this.result.setLength(0);
        String readLine = this.reader.readLine();
        if (readLine != null && readLine.length() >= 3) {
            this.result.append(readLine.substring(0, 3));
            this.result.append(ExpandableTextView.f6958c);
        }
        while (readLine != null) {
            append(readLine);
            if (!hasMoreLines(readLine)) {
                break;
            }
            readLine = this.reader.readLine();
        }
        return this.result.toString().trim();
    }

    public void close() throws IOException {
        this.reader.close();
    }

    protected boolean hasMoreLines(String str) {
        return str.length() > 3 && str.charAt(3) == '-';
    }

    private void append(String str) {
        if (str.length() > 4) {
            this.result.append(str.substring(4));
            this.result.append(ExpandableTextView.f6958c);
        }
    }
}
