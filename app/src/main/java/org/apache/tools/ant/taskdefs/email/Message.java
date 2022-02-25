package org.apache.tools.ant.taskdefs.email;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import org.apache.tools.ant.ProjectComponent;

/* loaded from: classes2.dex */
public class Message extends ProjectComponent {
    private StringBuffer buffer;
    private String charset;
    private String inputEncoding;
    private File messageSource;
    private String mimeType;
    private boolean specified;

    public Message() {
        this.messageSource = null;
        this.buffer = new StringBuffer();
        this.mimeType = "text/plain";
        this.specified = false;
        this.charset = null;
    }

    public Message(String str) {
        this.messageSource = null;
        this.buffer = new StringBuffer();
        this.mimeType = "text/plain";
        this.specified = false;
        this.charset = null;
        addText(str);
    }

    public Message(File file) {
        this.messageSource = null;
        this.buffer = new StringBuffer();
        this.mimeType = "text/plain";
        this.specified = false;
        this.charset = null;
        this.messageSource = file;
    }

    public void addText(String str) {
        this.buffer.append(str);
    }

    public void setSrc(File file) {
        this.messageSource = file;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
        this.specified = true;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void print(PrintStream printStream) throws IOException {
        BufferedWriter bufferedWriter = this.charset != null ? new BufferedWriter(new OutputStreamWriter(printStream, this.charset)) : new BufferedWriter(new OutputStreamWriter(printStream));
        if (this.messageSource != null) {
            Reader reader = getReader(this.messageSource);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                bufferedWriter.write(getProject().replaceProperties(readLine));
                bufferedWriter.newLine();
            }
            reader.close();
        } else {
            bufferedWriter.write(getProject().replaceProperties(this.buffer.substring(0)));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    public boolean isMimeTypeSpecified() {
        return this.specified;
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setInputEncoding(String str) {
        this.inputEncoding = str;
    }

    private Reader getReader(File file) throws IOException {
        if (this.inputEncoding == null) {
            return new FileReader(file);
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return new InputStreamReader(fileInputStream, this.inputEncoding);
        } catch (IOException e) {
            fileInputStream.close();
            throw e;
        }
    }
}
