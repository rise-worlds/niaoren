package org.apache.commons.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/* loaded from: classes2.dex */
public class DefaultAuthenticator extends Authenticator {
    private final PasswordAuthentication authentication;

    public DefaultAuthenticator(String str, String str2) {
        this.authentication = new PasswordAuthentication(str, str2);
    }

    @Override // javax.mail.Authenticator
    protected PasswordAuthentication getPasswordAuthentication() {
        return this.authentication;
    }
}
