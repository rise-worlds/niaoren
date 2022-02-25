package com.sun.mail.smtp;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

/* loaded from: classes2.dex */
public class SMTPAddressSucceededException extends MessagingException {
    private static final long serialVersionUID = -1168335848623096749L;
    protected InternetAddress addr;
    protected String cmd;

    /* renamed from: rc */
    protected int f12664rc;

    public SMTPAddressSucceededException(InternetAddress internetAddress, String str, int i, String str2) {
        super(str2);
        this.addr = internetAddress;
        this.cmd = str;
        this.f12664rc = i;
    }

    public InternetAddress getAddress() {
        return this.addr;
    }

    public String getCommand() {
        return this.cmd;
    }

    public int getReturnCode() {
        return this.f12664rc;
    }
}
