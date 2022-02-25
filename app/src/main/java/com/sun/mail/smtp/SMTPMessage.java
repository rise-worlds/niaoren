package com.sun.mail.smtp;

import java.io.InputStream;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import p110z1.C5271dq;

/* loaded from: classes2.dex */
public class SMTPMessage extends MimeMessage {
    public static final int NOTIFY_DELAY = 4;
    public static final int NOTIFY_FAILURE = 2;
    public static final int NOTIFY_NEVER = -1;
    public static final int NOTIFY_SUCCESS = 1;
    public static final int RETURN_FULL = 1;
    public static final int RETURN_HDRS = 2;
    private static final String[] returnOptionString;
    private String envelopeFrom;
    private int notifyOptions = 0;
    private int returnOption = 0;
    private boolean sendPartial = false;
    private boolean allow8bitMIME = false;
    private String submitter = null;
    private String extension = null;

    static {
        String[] strArr = new String[3];
        strArr[1] = "FULL";
        strArr[2] = "HDRS";
        returnOptionString = strArr;
    }

    public SMTPMessage(Session session) {
        super(session);
    }

    public SMTPMessage(Session session, InputStream inputStream) throws MessagingException {
        super(session, inputStream);
    }

    public SMTPMessage(MimeMessage mimeMessage) throws MessagingException {
        super(mimeMessage);
    }

    public void setEnvelopeFrom(String str) {
        this.envelopeFrom = str;
    }

    public String getEnvelopeFrom() {
        return this.envelopeFrom;
    }

    public void setNotifyOptions(int i) {
        if (i < -1 || i >= 8) {
            throw new IllegalArgumentException("Bad return option");
        }
        this.notifyOptions = i;
    }

    public int getNotifyOptions() {
        return this.notifyOptions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getDSNNotify() {
        int i = this.notifyOptions;
        if (i == 0) {
            return null;
        }
        if (i == -1) {
            return "NEVER";
        }
        StringBuffer stringBuffer = new StringBuffer();
        if ((this.notifyOptions & 1) != 0) {
            stringBuffer.append(C5271dq.f21322g);
        }
        if ((this.notifyOptions & 2) != 0) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append("FAILURE");
        }
        if ((this.notifyOptions & 4) != 0) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append("DELAY");
        }
        return stringBuffer.toString();
    }

    public void setReturnOption(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Bad return option");
        }
        this.returnOption = i;
    }

    public int getReturnOption() {
        return this.returnOption;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getDSNRet() {
        return returnOptionString[this.returnOption];
    }

    public void setAllow8bitMIME(boolean z) {
        this.allow8bitMIME = z;
    }

    public boolean getAllow8bitMIME() {
        return this.allow8bitMIME;
    }

    public void setSendPartial(boolean z) {
        this.sendPartial = z;
    }

    public boolean getSendPartial() {
        return this.sendPartial;
    }

    public String getSubmitter() {
        return this.submitter;
    }

    public void setSubmitter(String str) {
        this.submitter = str;
    }

    public String getMailExtension() {
        return this.extension;
    }

    public void setMailExtension(String str) {
        this.extension = str;
    }
}
