package org.apache.commons.mail;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes2.dex */
public class EmailException extends Exception {
    private static final long serialVersionUID = 5550674499282474616L;

    public EmailException() {
    }

    public EmailException(String str) {
        super(str);
    }

    public EmailException(Throwable th) {
        super(th);
    }

    public EmailException(String str, Throwable th) {
        super(str, th);
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        synchronized (printStream) {
            PrintWriter printWriter = new PrintWriter((OutputStream) printStream, false);
            printStackTrace(printWriter);
            printWriter.flush();
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        synchronized (printWriter) {
            super.printStackTrace(printWriter);
        }
    }
}
