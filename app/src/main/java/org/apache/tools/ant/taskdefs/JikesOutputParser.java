package org.apache.tools.ant.taskdefs;

import com.stripe.android.PaymentResultListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class JikesOutputParser implements ExecuteStreamHandler {

    /* renamed from: br */
    protected BufferedReader f14752br;
    protected boolean emacsMode;
    protected int errors;
    protected Task task;
    protected int warnings;
    protected boolean errorFlag = false;
    protected boolean error = false;

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessErrorStream(InputStream inputStream) {
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessInputStream(OutputStream outputStream) {
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void stop() {
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessOutputStream(InputStream inputStream) throws IOException {
        this.f14752br = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void start() throws IOException {
        parseOutput(this.f14752br);
    }

    protected JikesOutputParser(Task task, boolean z) {
        System.err.println("As of Ant 1.2 released in October 2000, the JikesOutputParser class");
        System.err.println("is considered to be dead code by the Ant developers and is unmaintained.");
        System.err.println("Don't use it!");
        this.task = task;
        this.emacsMode = z;
    }

    protected void parseOutput(BufferedReader bufferedReader) throws IOException {
        if (this.emacsMode) {
            parseEmacsOutput(bufferedReader);
        } else {
            parseStandardOutput(bufferedReader);
        }
    }

    private void parseStandardOutput(BufferedReader bufferedReader) throws IOException {
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                String lowerCase = readLine.toLowerCase();
                if (!readLine.trim().equals("")) {
                    if (lowerCase.indexOf(PaymentResultListener.f11903c) != -1) {
                        setError(true);
                    } else if (lowerCase.indexOf("warning") != -1) {
                        setError(false);
                    } else if (this.emacsMode) {
                        setError(true);
                    }
                    log(readLine);
                }
            } else {
                return;
            }
        }
    }

    private void parseEmacsOutput(BufferedReader bufferedReader) throws IOException {
        parseStandardOutput(bufferedReader);
    }

    private void setError(boolean z) {
        this.error = z;
        if (this.error) {
            this.errorFlag = true;
        }
    }

    private void log(String str) {
        if (!this.emacsMode) {
            this.task.log("", !this.error ? 1 : 0);
        }
        this.task.log(str, !this.error ? 1 : 0);
    }

    protected boolean getErrorFlag() {
        return this.errorFlag;
    }
}
