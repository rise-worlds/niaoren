package com.sun.mail.imap.protocol;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.tendcloud.tenddata.C2771ci;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Vector;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.sasl.RealmCallback;
import javax.security.sasl.RealmChoiceCallback;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

/* loaded from: classes2.dex */
public class IMAPSaslAuthenticator implements SaslAuthenticator {
    private boolean debug;
    private String host;
    private String name;
    private PrintStream out;

    /* renamed from: pr */
    private IMAPProtocol f12660pr;
    private Properties props;

    public IMAPSaslAuthenticator(IMAPProtocol iMAPProtocol, String str, Properties properties, boolean z, PrintStream printStream, String str2) {
        this.f12660pr = iMAPProtocol;
        this.name = str;
        this.props = properties;
        this.debug = z;
        this.out = printStream;
        this.host = str2;
    }

    @Override // com.sun.mail.imap.protocol.SaslAuthenticator
    public boolean authenticate(String[] strArr, final String str, String str2, final String str3, final String str4) throws ProtocolException {
        String str5;
        byte[] bArr;
        synchronized (this.f12660pr) {
            Vector vector = new Vector();
            if (this.debug) {
                this.out.print("IMAP SASL DEBUG: Mechanisms:");
                for (int i = 0; i < strArr.length; i++) {
                    this.out.print(ExpandableTextView.f6958c + strArr[i]);
                }
                this.out.println();
            }
            try {
                SaslClient createSaslClient = Sasl.createSaslClient(strArr, str2, this.name, this.host, this.props, new CallbackHandler() { // from class: com.sun.mail.imap.protocol.IMAPSaslAuthenticator.1
                    @Override // javax.security.auth.callback.CallbackHandler
                    public void handle(Callback[] callbackArr) {
                        if (IMAPSaslAuthenticator.this.debug) {
                            PrintStream printStream = IMAPSaslAuthenticator.this.out;
                            printStream.println("IMAP SASL DEBUG: callback length: " + callbackArr.length);
                        }
                        for (int i2 = 0; i2 < callbackArr.length; i2++) {
                            if (IMAPSaslAuthenticator.this.debug) {
                                PrintStream printStream2 = IMAPSaslAuthenticator.this.out;
                                printStream2.println("IMAP SASL DEBUG: callback " + i2 + ": " + callbackArr[i2]);
                            }
                            if (callbackArr[i2] instanceof NameCallback) {
                                ((NameCallback) callbackArr[i2]).setName(str3);
                            } else if (callbackArr[i2] instanceof PasswordCallback) {
                                ((PasswordCallback) callbackArr[i2]).setPassword(str4.toCharArray());
                            } else if (callbackArr[i2] instanceof RealmCallback) {
                                RealmCallback realmCallback = (RealmCallback) callbackArr[i2];
                                String str6 = str;
                                if (str6 == null) {
                                    str6 = realmCallback.getDefaultText();
                                }
                                realmCallback.setText(str6);
                            } else if (callbackArr[i2] instanceof RealmChoiceCallback) {
                                RealmChoiceCallback realmChoiceCallback = (RealmChoiceCallback) callbackArr[i2];
                                if (str == null) {
                                    realmChoiceCallback.setSelectedIndex(realmChoiceCallback.getDefaultChoice());
                                } else {
                                    String[] choices = realmChoiceCallback.getChoices();
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 < choices.length) {
                                            if (choices[i3].equals(str)) {
                                                realmChoiceCallback.setSelectedIndex(i3);
                                                break;
                                            }
                                            i3++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                if (createSaslClient == null) {
                    if (this.debug) {
                        this.out.println("IMAP SASL DEBUG: No SASL support");
                    }
                    return false;
                }
                if (this.debug) {
                    this.out.println("IMAP SASL DEBUG: SASL client " + createSaslClient.getMechanismName());
                }
                try {
                    String writeCommand = this.f12660pr.writeCommand("AUTHENTICATE " + createSaslClient.getMechanismName(), null);
                    OutputStream iMAPOutputStream = this.f12660pr.getIMAPOutputStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr2 = {C2771ci.f13672f, 10};
                    boolean equals = createSaslClient.getMechanismName().equals("XGWTRUSTEDAPP");
                    boolean z = true;
                    Response response = null;
                    boolean z2 = false;
                    while (!z2) {
                        try {
                            response = this.f12660pr.readResponse();
                        } catch (Exception e) {
                            if (this.debug) {
                                e.printStackTrace();
                            }
                            response = Response.byeResponse(e);
                            z2 = true;
                            z = true;
                        }
                        if (response.isContinuation()) {
                            if (!createSaslClient.isComplete()) {
                                byte[] newBytes = response.readByteArray().getNewBytes();
                                if (newBytes.length > 0) {
                                    newBytes = BASE64DecoderStream.decode(newBytes);
                                }
                                if (this.debug) {
                                    this.out.println("IMAP SASL DEBUG: challenge: " + ASCIIUtility.toString(newBytes, 0, newBytes.length) + " :");
                                }
                                bArr = createSaslClient.evaluateChallenge(newBytes);
                            } else {
                                bArr = null;
                            }
                            if (bArr == null) {
                                if (this.debug) {
                                    this.out.println("IMAP SASL DEBUG: no response");
                                }
                                iMAPOutputStream.write(bArr2);
                                iMAPOutputStream.flush();
                                byteArrayOutputStream.reset();
                            } else {
                                if (this.debug) {
                                    this.out.println("IMAP SASL DEBUG: response: " + ASCIIUtility.toString(bArr, 0, bArr.length) + " :");
                                }
                                byte[] encode = BASE64EncoderStream.encode(bArr);
                                if (equals) {
                                    byteArrayOutputStream.write("XGWTRUSTEDAPP ".getBytes());
                                }
                                byteArrayOutputStream.write(encode);
                                byteArrayOutputStream.write(bArr2);
                                iMAPOutputStream.write(byteArrayOutputStream.toByteArray());
                                iMAPOutputStream.flush();
                                byteArrayOutputStream.reset();
                            }
                        } else if (response.isTagged() && response.getTag().equals(writeCommand)) {
                            z2 = true;
                            z = true;
                        } else if (response.isBYE()) {
                            z2 = true;
                            z = true;
                        } else {
                            vector.addElement(response);
                        }
                        z = true;
                    }
                    if (!createSaslClient.isComplete() || (str5 = (String) createSaslClient.getNegotiatedProperty("javax.security.sasl.qop")) == null || (!str5.equalsIgnoreCase("auth-int") && !str5.equalsIgnoreCase("auth-conf"))) {
                        Response[] responseArr = new Response[vector.size()];
                        vector.copyInto(responseArr);
                        this.f12660pr.notifyResponseHandlers(responseArr);
                        this.f12660pr.handleResult(response);
                        this.f12660pr.setCapabilities(response);
                        return z;
                    }
                    if (this.debug) {
                        this.out.println("IMAP SASL DEBUG: Mechanism requires integrity or confidentiality");
                    }
                    return false;
                } catch (Exception e2) {
                    if (this.debug) {
                        this.out.println("IMAP SASL DEBUG: AUTHENTICATE Exception: " + e2);
                    }
                    return false;
                }
            } catch (SaslException e3) {
                if (this.debug) {
                    this.out.println("IMAP SASL DEBUG: Failed to create SASL client: " + e3);
                }
                return false;
            }
        }
    }
}
