package org.apache.tools.ant.taskdefs.email;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import org.apache.tools.ant.BuildException;
import org.apache.tools.mail.MailMessage;

/* loaded from: classes2.dex */
class PlainMailer extends Mailer {
    @Override // org.apache.tools.ant.taskdefs.email.Mailer
    public void send() {
        try {
            MailMessage mailMessage = new MailMessage(this.host, this.port);
            mailMessage.from(this.from.toString());
            boolean z = false;
            Enumeration<EmailAddress> elements = this.replyToList.elements();
            while (elements.hasMoreElements()) {
                mailMessage.replyto(elements.nextElement().toString());
            }
            Enumeration<EmailAddress> elements2 = this.toList.elements();
            while (elements2.hasMoreElements()) {
                String obj = elements2.nextElement().toString();
                try {
                    mailMessage.m14821to(obj);
                    z = true;
                } catch (IOException e) {
                    badRecipient(obj, e);
                }
            }
            Enumeration<EmailAddress> elements3 = this.ccList.elements();
            while (elements3.hasMoreElements()) {
                String obj2 = elements3.nextElement().toString();
                try {
                    mailMessage.m14822cc(obj2);
                    z = true;
                } catch (IOException e2) {
                    badRecipient(obj2, e2);
                }
            }
            Enumeration<EmailAddress> elements4 = this.bccList.elements();
            while (elements4.hasMoreElements()) {
                String obj3 = elements4.nextElement().toString();
                try {
                    mailMessage.bcc(obj3);
                    z = true;
                } catch (IOException e3) {
                    badRecipient(obj3, e3);
                }
            }
            if (z) {
                if (this.subject != null) {
                    mailMessage.setSubject(this.subject);
                }
                mailMessage.setHeader("Date", getDate());
                if (this.message.getCharset() != null) {
                    mailMessage.setHeader("Content-Type", this.message.getMimeType() + "; charset=\"" + this.message.getCharset() + "\"");
                } else {
                    mailMessage.setHeader("Content-Type", this.message.getMimeType());
                }
                if (this.headers != null) {
                    Enumeration<Header> elements5 = this.headers.elements();
                    while (elements5.hasMoreElements()) {
                        Header nextElement = elements5.nextElement();
                        mailMessage.setHeader(nextElement.getName(), nextElement.getValue());
                    }
                }
                PrintStream printStream = mailMessage.getPrintStream();
                this.message.print(printStream);
                Enumeration<File> elements6 = this.files.elements();
                while (elements6.hasMoreElements()) {
                    attach(elements6.nextElement(), printStream);
                }
                mailMessage.sendAndClose();
                return;
            }
            throw new BuildException("Couldn't reach any recipient");
        } catch (IOException e4) {
            throw new BuildException("IO error sending mail", e4);
        }
    }

    protected void attach(File file, PrintStream printStream) throws IOException {
        if (!file.exists() || !file.canRead()) {
            throw new BuildException("File \"" + file.getName() + "\" does not exist or is not readable.");
        }
        if (this.includeFileNames) {
            printStream.println();
            String name = file.getName();
            int length = name.length();
            printStream.println(name);
            for (int i = 0; i < length; i++) {
                printStream.print('=');
            }
            printStream.println();
        }
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, bArr.length);
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    printStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } finally {
            fileInputStream.close();
        }
    }

    private void badRecipient(String str, IOException iOException) {
        String str2 = "Failed to send mail to " + str;
        if (shouldIgnoreInvalidRecipients()) {
            String str3 = str2 + " because of :" + iOException.getMessage();
            if (this.task != null) {
                this.task.log(str3, 1);
            } else {
                System.err.println(str3);
            }
        } else {
            throw new BuildException(str2, iOException);
        }
    }
}
