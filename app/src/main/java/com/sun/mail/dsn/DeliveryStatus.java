package com.sun.mail.dsn;

import com.sun.mail.util.LineOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Vector;
import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;

/* loaded from: classes2.dex */
public class DeliveryStatus {
    private static boolean debug = false;
    protected InternetHeaders messageDSN;
    protected InternetHeaders[] recipientDSN;

    static {
        try {
            String property = System.getProperty("mail.dsn.debug");
            debug = property != null && !property.equalsIgnoreCase("false");
        } catch (SecurityException unused) {
        }
    }

    public DeliveryStatus() throws MessagingException {
        this.messageDSN = new InternetHeaders();
        this.recipientDSN = new InternetHeaders[0];
    }

    public DeliveryStatus(InputStream inputStream) throws MessagingException, IOException {
        this.messageDSN = new InternetHeaders(inputStream);
        if (debug) {
            System.out.println("DSN: got messageDSN");
        }
        Vector vector = new Vector();
        while (inputStream.available() > 0) {
            try {
                InternetHeaders internetHeaders = new InternetHeaders(inputStream);
                if (debug) {
                    System.out.println("DSN: got recipientDSN");
                }
                vector.addElement(internetHeaders);
            } catch (EOFException unused) {
                if (debug) {
                    System.out.println("DSN: got EOFException");
                }
            }
        }
        if (debug) {
            PrintStream printStream = System.out;
            printStream.println("DSN: recipientDSN size " + vector.size());
        }
        this.recipientDSN = new InternetHeaders[vector.size()];
        vector.copyInto(this.recipientDSN);
    }

    public InternetHeaders getMessageDSN() {
        return this.messageDSN;
    }

    public void setMessageDSN(InternetHeaders internetHeaders) {
        this.messageDSN = internetHeaders;
    }

    public int getRecipientDSNCount() {
        return this.recipientDSN.length;
    }

    public InternetHeaders getRecipientDSN(int i) {
        return this.recipientDSN[i];
    }

    public void addRecipientDSN(InternetHeaders internetHeaders) {
        InternetHeaders[] internetHeadersArr = this.recipientDSN;
        InternetHeaders[] internetHeadersArr2 = new InternetHeaders[internetHeadersArr.length + 1];
        System.arraycopy(internetHeadersArr, 0, internetHeadersArr2, 0, internetHeadersArr.length);
        this.recipientDSN = internetHeadersArr2;
        InternetHeaders[] internetHeadersArr3 = this.recipientDSN;
        internetHeadersArr3[internetHeadersArr3.length - 1] = internetHeaders;
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        LineOutputStream lineOutputStream;
        if (outputStream instanceof LineOutputStream) {
            lineOutputStream = (LineOutputStream) outputStream;
        } else {
            lineOutputStream = new LineOutputStream(outputStream);
        }
        writeInternetHeaders(this.messageDSN, lineOutputStream);
        lineOutputStream.writeln();
        int i = 0;
        while (true) {
            InternetHeaders[] internetHeadersArr = this.recipientDSN;
            if (i < internetHeadersArr.length) {
                writeInternetHeaders(internetHeadersArr[i], lineOutputStream);
                lineOutputStream.writeln();
                i++;
            } else {
                return;
            }
        }
    }

    private static void writeInternetHeaders(InternetHeaders internetHeaders, LineOutputStream lineOutputStream) throws IOException {
        Enumeration allHeaderLines = internetHeaders.getAllHeaderLines();
        while (allHeaderLines.hasMoreElements()) {
            try {
                lineOutputStream.writeln((String) allHeaderLines.nextElement());
            } catch (MessagingException e) {
                Exception nextException = e.getNextException();
                if (nextException instanceof IOException) {
                    throw ((IOException) nextException);
                }
                throw new IOException("Exception writing headers: " + e);
            }
        }
    }

    public String toString() {
        return "DeliveryStatus: Reporting-MTA=" + this.messageDSN.getHeader("Reporting-MTA", null) + ", #Recipients=" + this.recipientDSN.length;
    }
}
