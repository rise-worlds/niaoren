package org.apache.tools.ant.listener;

import com.github.kevinsawicki.http.HttpRequest;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.http.cookie.ClientCookie;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.email.EmailAddress;
import org.apache.tools.ant.taskdefs.email.Mailer;
import org.apache.tools.ant.taskdefs.email.Message;
import org.apache.tools.ant.util.ClasspathUtils;
import org.apache.tools.ant.util.DateUtils;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;
import org.apache.tools.mail.MailMessage;

/* loaded from: classes2.dex */
public class MailLogger extends DefaultLogger {
    private static final String DEFAULT_MIME_TYPE = "text/plain";
    private StringBuffer buffer = new StringBuffer();

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent buildEvent) {
        FileInputStream fileInputStream;
        Throwable th;
        super.buildFinished(buildEvent);
        Project project = buildEvent.getProject();
        Hashtable<String, Object> properties = project.getProperties();
        Properties properties2 = new Properties();
        String str = (String) properties.get("MailLogger.properties.file");
        FileInputStream fileInputStream2 = null;
        if (str != null) {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    properties2.load(fileInputStream);
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    FileUtils.close(fileInputStream2);
                    throw th;
                }
            } catch (IOException unused2) {
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
            }
            FileUtils.close(fileInputStream);
        }
        Enumeration keys = properties2.keys();
        while (keys.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            properties.put(str2, project.replaceProperties(properties2.getProperty(str2)));
        }
        boolean z = buildEvent.getException() == null;
        String str3 = z ? "success" : "failure";
        try {
            if (Project.toBoolean(getValue(properties, str3 + ".notify", "on"))) {
                Values subject = new Values().mailhost(getValue(properties, "mailhost", MailMessage.DEFAULT_HOST)).port(Integer.parseInt(getValue(properties, ClientCookie.PORT_ATTR, String.valueOf(25)))).user(getValue(properties, ServiceManagerNative.USER, "")).password(getValue(properties, "password", "")).ssl(Project.toBoolean(getValue(properties, "ssl", "off"))).starttls(Project.toBoolean(getValue(properties, "starttls.enable", "off"))).from(getValue(properties, "from", null)).replytoList(getValue(properties, "replyto", "")).toList(getValue(properties, str3 + ".to", null)).toCcList(getValue(properties, str3 + ".cc", "")).toBccList(getValue(properties, str3 + ".bcc", "")).mimeType(getValue(properties, "mimeType", "text/plain")).charset(getValue(properties, HttpRequest.PARAM_CHARSET, "")).body(getValue(properties, str3 + ".body", "")).subject(getValue(properties, str3 + ".subject", z ? "Build Success" : "Build Failure"));
                if (!subject.user().equals("") || !subject.password().equals("") || subject.ssl() || subject.starttls()) {
                    sendMimeMail(buildEvent.getProject(), subject, this.buffer.substring(0));
                } else {
                    sendMail(subject, this.buffer.substring(0));
                }
            }
        } catch (Exception e) {
            System.out.println("MailLogger failed to send e-mail!");
            e.printStackTrace(System.err);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Values {
        private String body;
        private String charset;
        private String from;
        private String mailhost;
        private String mimeType;
        private String password;
        private int port;
        private String replytoList;
        private boolean ssl;
        private boolean starttls;
        private String subject;
        private String toBccList;
        private String toCcList;
        private String toList;
        private String user;

        private Values() {
        }

        public String mailhost() {
            return this.mailhost;
        }

        public Values mailhost(String str) {
            this.mailhost = str;
            return this;
        }

        public int port() {
            return this.port;
        }

        public Values port(int i) {
            this.port = i;
            return this;
        }

        public String user() {
            return this.user;
        }

        public Values user(String str) {
            this.user = str;
            return this;
        }

        public String password() {
            return this.password;
        }

        public Values password(String str) {
            this.password = str;
            return this;
        }

        public boolean ssl() {
            return this.ssl;
        }

        public Values ssl(boolean z) {
            this.ssl = z;
            return this;
        }

        public String from() {
            return this.from;
        }

        public Values from(String str) {
            this.from = str;
            return this;
        }

        public String replytoList() {
            return this.replytoList;
        }

        public Values replytoList(String str) {
            this.replytoList = str;
            return this;
        }

        public String toList() {
            return this.toList;
        }

        public Values toList(String str) {
            this.toList = str;
            return this;
        }

        public String toCcList() {
            return this.toCcList;
        }

        public Values toCcList(String str) {
            this.toCcList = str;
            return this;
        }

        public String toBccList() {
            return this.toBccList;
        }

        public Values toBccList(String str) {
            this.toBccList = str;
            return this;
        }

        public String subject() {
            return this.subject;
        }

        public Values subject(String str) {
            this.subject = str;
            return this;
        }

        public String charset() {
            return this.charset;
        }

        public Values charset(String str) {
            this.charset = str;
            return this;
        }

        public String mimeType() {
            return this.mimeType;
        }

        public Values mimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public String body() {
            return this.body;
        }

        public Values body(String str) {
            this.body = str;
            return this;
        }

        public boolean starttls() {
            return this.starttls;
        }

        public Values starttls(boolean z) {
            this.starttls = z;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.DefaultLogger
    public void log(String str) {
        StringBuffer stringBuffer = this.buffer;
        stringBuffer.append(str);
        stringBuffer.append(StringUtils.LINE_SEP);
    }

    private String getValue(Hashtable<String, Object> hashtable, String str, String str2) throws Exception {
        String str3 = "MailLogger." + str;
        String str4 = (String) hashtable.get(str3);
        if (str4 == null) {
            str4 = str2;
        }
        if (str4 != null) {
            return str4;
        }
        throw new Exception("Missing required parameter: " + str3);
    }

    private void sendMail(Values values, String str) throws IOException {
        MailMessage mailMessage = new MailMessage(values.mailhost(), values.port());
        mailMessage.setHeader("Date", DateUtils.getDateForHeader());
        mailMessage.from(values.from());
        if (!values.replytoList().equals("")) {
            StringTokenizer stringTokenizer = new StringTokenizer(values.replytoList(), ", ", false);
            while (stringTokenizer.hasMoreTokens()) {
                mailMessage.replyto(stringTokenizer.nextToken());
            }
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(values.toList(), ", ", false);
        while (stringTokenizer2.hasMoreTokens()) {
            mailMessage.m14821to(stringTokenizer2.nextToken());
        }
        mailMessage.setSubject(values.subject());
        if (values.charset().length() > 0) {
            mailMessage.setHeader("Content-Type", values.mimeType() + "; charset=\"" + values.charset() + "\"");
        } else {
            mailMessage.setHeader("Content-Type", values.mimeType());
        }
        PrintStream printStream = mailMessage.getPrintStream();
        if (values.body().length() > 0) {
            str = values.body();
        }
        printStream.println(str);
        mailMessage.sendAndClose();
    }

    private void sendMimeMail(Project project, Values values, String str) {
        try {
            Mailer mailer = (Mailer) ClasspathUtils.newInstance("org.apache.tools.ant.taskdefs.email.MimeMailer", MailLogger.class.getClassLoader(), Mailer.class);
            Vector<EmailAddress> vectorizeEmailAddresses = vectorizeEmailAddresses(values.replytoList());
            mailer.setHost(values.mailhost());
            mailer.setPort(values.port());
            mailer.setUser(values.user());
            mailer.setPassword(values.password());
            mailer.setSSL(values.ssl());
            mailer.setEnableStartTLS(values.starttls());
            if (values.body().length() > 0) {
                str = values.body();
            }
            Message message = new Message(str);
            message.setProject(project);
            message.setMimeType(values.mimeType());
            if (values.charset().length() > 0) {
                message.setCharset(values.charset());
            }
            mailer.setMessage(message);
            mailer.setFrom(new EmailAddress(values.from()));
            mailer.setReplyToList(vectorizeEmailAddresses);
            mailer.setToList(vectorizeEmailAddresses(values.toList()));
            mailer.setCcList(vectorizeEmailAddresses(values.toCcList()));
            mailer.setBccList(vectorizeEmailAddresses(values.toBccList()));
            mailer.setFiles(new Vector<>());
            mailer.setSubject(values.subject());
            mailer.setHeaders(new Vector<>());
            mailer.send();
        } catch (BuildException e) {
            Throwable cause = e.getCause();
            Throwable th = e;
            if (cause != null) {
                th = e.getCause();
            }
            log("Failed to initialise MIME mail: " + th.getMessage());
        }
    }

    private Vector<EmailAddress> vectorizeEmailAddresses(String str) {
        Vector<EmailAddress> vector = new Vector<>();
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(new EmailAddress(stringTokenizer.nextToken()));
        }
        return vector;
    }
}
