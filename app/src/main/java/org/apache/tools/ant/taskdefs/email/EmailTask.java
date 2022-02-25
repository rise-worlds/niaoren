package org.apache.tools.ant.taskdefs.email;

import java.io.File;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.mail.MailMessage;

/* loaded from: classes2.dex */
public class EmailTask extends Task {
    public static final String AUTO = "auto";
    public static final String MIME = "mime";
    public static final String PLAIN = "plain";
    private static final int SMTP_PORT = 25;

    /* renamed from: UU */
    public static final String f14763UU = "uu";
    private String messageFileInputEncoding;
    private String encoding = AUTO;
    private String host = MailMessage.DEFAULT_HOST;
    private Integer port = null;
    private String subject = null;
    private Message message = null;
    private boolean failOnError = true;
    private boolean includeFileNames = false;
    private String messageMimeType = null;
    private EmailAddress from = null;
    private Vector replyToList = new Vector();
    private Vector toList = new Vector();
    private Vector ccList = new Vector();
    private Vector bccList = new Vector();
    private Vector headers = new Vector();
    private Path attachments = null;
    private String charset = null;
    private String user = null;
    private String password = null;
    private boolean ssl = false;
    private boolean starttls = false;
    private boolean ignoreInvalidRecipients = false;

    /* loaded from: classes2.dex */
    public static class Encoding extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{EmailTask.AUTO, EmailTask.MIME, EmailTask.f14763UU, EmailTask.PLAIN};
        }
    }

    public void setUser(String str) {
        this.user = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setSSL(boolean z) {
        this.ssl = z;
    }

    public void setEnableStartTLS(boolean z) {
        this.starttls = z;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding.getValue();
    }

    public void setMailport(int i) {
        this.port = new Integer(i);
    }

    public void setMailhost(String str) {
        this.host = str;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public void setMessage(String str) {
        if (this.message == null) {
            this.message = new Message(str);
            this.message.setProject(getProject());
            return;
        }
        throw new BuildException("Only one message can be sent in an email");
    }

    public void setMessageFile(File file) {
        if (this.message == null) {
            this.message = new Message(file);
            this.message.setProject(getProject());
            return;
        }
        throw new BuildException("Only one message can be sent in an email");
    }

    public void setMessageMimeType(String str) {
        this.messageMimeType = str;
    }

    public void addMessage(Message message) throws BuildException {
        if (this.message == null) {
            this.message = message;
            return;
        }
        throw new BuildException("Only one message can be sent in an email");
    }

    public void addFrom(EmailAddress emailAddress) {
        if (this.from == null) {
            this.from = emailAddress;
            return;
        }
        throw new BuildException("Emails can only be from one address");
    }

    public void setFrom(String str) {
        if (this.from == null) {
            this.from = new EmailAddress(str);
            return;
        }
        throw new BuildException("Emails can only be from one address");
    }

    public void addReplyTo(EmailAddress emailAddress) {
        this.replyToList.add(emailAddress);
    }

    public void setReplyTo(String str) {
        this.replyToList.add(new EmailAddress(str));
    }

    public void addTo(EmailAddress emailAddress) {
        this.toList.addElement(emailAddress);
    }

    public void setToList(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.toList.addElement(new EmailAddress(stringTokenizer.nextToken()));
        }
    }

    public void addCc(EmailAddress emailAddress) {
        this.ccList.addElement(emailAddress);
    }

    public void setCcList(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.ccList.addElement(new EmailAddress(stringTokenizer.nextToken()));
        }
    }

    public void addBcc(EmailAddress emailAddress) {
        this.bccList.addElement(emailAddress);
    }

    public void setBccList(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.bccList.addElement(new EmailAddress(stringTokenizer.nextToken()));
        }
    }

    public void setFailOnError(boolean z) {
        this.failOnError = z;
    }

    public void setFiles(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ", ");
        while (stringTokenizer.hasMoreTokens()) {
            createAttachments().add(new FileResource(getProject().resolveFile(stringTokenizer.nextToken())));
        }
    }

    public void addFileset(FileSet fileSet) {
        createAttachments().add(fileSet);
    }

    public Path createAttachments() {
        if (this.attachments == null) {
            this.attachments = new Path(getProject());
        }
        return this.attachments.createPath();
    }

    public Header createHeader() {
        Header header = new Header();
        this.headers.add(header);
        return header;
    }

    public void setIncludefilenames(boolean z) {
        this.includeFileNames = z;
    }

    public boolean getIncludeFileNames() {
        return this.includeFileNames;
    }

    public void setIgnoreInvalidRecipients(boolean z) {
        this.ignoreInvalidRecipients = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0078, code lost:
        if (r9.ssl != false) goto L_0x007e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007c, code lost:
        if (r9.starttls == false) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0086, code lost:
        if (r9.encoding.equals(org.apache.tools.ant.taskdefs.email.EmailTask.f14763UU) != false) goto L_0x0093;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0090, code lost:
        if (r9.encoding.equals(org.apache.tools.ant.taskdefs.email.EmailTask.PLAIN) != false) goto L_0x0093;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x009a, code lost:
        throw new org.apache.tools.ant.BuildException("SSL and STARTTLS only possible with MIME mail");
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02c3 A[Catch: all -> 0x02dc, Exception -> 0x02de, BuildException -> 0x0302, TryCatch #0 {BuildException -> 0x0302, blocks: (B:3:0x0004, B:5:0x0010, B:15:0x0047, B:17:0x004f, B:19:0x0053, B:21:0x0057, B:23:0x0061, B:26:0x006c, B:27:0x0073, B:29:0x0076, B:31:0x007a, B:33:0x007e, B:35:0x0088, B:38:0x0093, B:39:0x009a, B:40:0x009b, B:42:0x00a5, B:51:0x00cf, B:52:0x00d4, B:54:0x00de, B:57:0x00ea, B:59:0x00f6, B:61:0x00fa, B:62:0x010a, B:64:0x010e, B:66:0x0116, B:68:0x011e, B:70:0x0126, B:73:0x012f, B:74:0x0136, B:75:0x0137, B:77:0x013b, B:79:0x0143, B:80:0x014b, B:81:0x0152, B:82:0x0153, B:84:0x0157, B:86:0x015f, B:87:0x0167, B:88:0x016e, B:89:0x016f, B:91:0x017f, B:92:0x0185, B:94:0x018b, B:95:0x01a1, B:97:0x022f, B:98:0x023c, B:99:0x0244, B:103:0x02b0, B:104:0x02bb, B:105:0x02c2, B:106:0x02c3, B:107:0x02db), top: B:123:0x0004, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00de A[Catch: all -> 0x02dc, Exception -> 0x02de, BuildException -> 0x0302, TryCatch #0 {BuildException -> 0x0302, blocks: (B:3:0x0004, B:5:0x0010, B:15:0x0047, B:17:0x004f, B:19:0x0053, B:21:0x0057, B:23:0x0061, B:26:0x006c, B:27:0x0073, B:29:0x0076, B:31:0x007a, B:33:0x007e, B:35:0x0088, B:38:0x0093, B:39:0x009a, B:40:0x009b, B:42:0x00a5, B:51:0x00cf, B:52:0x00d4, B:54:0x00de, B:57:0x00ea, B:59:0x00f6, B:61:0x00fa, B:62:0x010a, B:64:0x010e, B:66:0x0116, B:68:0x011e, B:70:0x0126, B:73:0x012f, B:74:0x0136, B:75:0x0137, B:77:0x013b, B:79:0x0143, B:80:0x014b, B:81:0x0152, B:82:0x0153, B:84:0x0157, B:86:0x015f, B:87:0x0167, B:88:0x016e, B:89:0x016f, B:91:0x017f, B:92:0x0185, B:94:0x018b, B:95:0x01a1, B:97:0x022f, B:98:0x023c, B:99:0x0244, B:103:0x02b0, B:104:0x02bb, B:105:0x02c2, B:106:0x02c3, B:107:0x02db), top: B:123:0x0004, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f6 A[Catch: all -> 0x02dc, Exception -> 0x02de, BuildException -> 0x0302, TryCatch #0 {BuildException -> 0x0302, blocks: (B:3:0x0004, B:5:0x0010, B:15:0x0047, B:17:0x004f, B:19:0x0053, B:21:0x0057, B:23:0x0061, B:26:0x006c, B:27:0x0073, B:29:0x0076, B:31:0x007a, B:33:0x007e, B:35:0x0088, B:38:0x0093, B:39:0x009a, B:40:0x009b, B:42:0x00a5, B:51:0x00cf, B:52:0x00d4, B:54:0x00de, B:57:0x00ea, B:59:0x00f6, B:61:0x00fa, B:62:0x010a, B:64:0x010e, B:66:0x0116, B:68:0x011e, B:70:0x0126, B:73:0x012f, B:74:0x0136, B:75:0x0137, B:77:0x013b, B:79:0x0143, B:80:0x014b, B:81:0x0152, B:82:0x0153, B:84:0x0157, B:86:0x015f, B:87:0x0167, B:88:0x016e, B:89:0x016f, B:91:0x017f, B:92:0x0185, B:94:0x018b, B:95:0x01a1, B:97:0x022f, B:98:0x023c, B:99:0x0244, B:103:0x02b0, B:104:0x02bb, B:105:0x02c2, B:106:0x02c3, B:107:0x02db), top: B:123:0x0004, outer: #6 }] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() {
        /*
            Method dump skipped, instructions count: 787
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.email.EmailTask.execute():void");
    }

    private void logBuildException(String str, BuildException buildException) {
        Throwable cause = buildException.getCause();
        Throwable th = buildException;
        if (cause != null) {
            th = buildException.getCause();
        }
        log(str + th.getMessage(), 1);
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setMessageFileInputEncoding(String str) {
        this.messageFileInputEncoding = str;
    }
}
