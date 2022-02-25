package org.apache.commons.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class HtmlEmail extends MultiPartEmail {
    public static final int CID_LENGTH = 10;
    private static final String HTML_MESSAGE_END = "</pre></body></html>";
    private static final String HTML_MESSAGE_START = "<html><body><pre>";
    protected String html;
    protected Map<String, InlineImage> inlineEmbeds = new HashMap();
    @Deprecated
    protected List<InlineImage> inlineImages;
    protected String text;

    public HtmlEmail setTextMsg(String str) throws EmailException {
        if (!EmailUtils.isEmpty(str)) {
            this.text = str;
            return this;
        }
        throw new EmailException("Invalid message supplied");
    }

    public HtmlEmail setHtmlMsg(String str) throws EmailException {
        if (!EmailUtils.isEmpty(str)) {
            this.html = str;
            return this;
        }
        throw new EmailException("Invalid message supplied");
    }

    @Override // org.apache.commons.mail.MultiPartEmail, org.apache.commons.mail.Email
    public Email setMsg(String str) throws EmailException {
        if (!EmailUtils.isEmpty(str)) {
            setTextMsg(str);
            StringBuffer stringBuffer = new StringBuffer(str.length() + 17 + 20);
            stringBuffer.append(HTML_MESSAGE_START);
            stringBuffer.append(str);
            stringBuffer.append(HTML_MESSAGE_END);
            setHtmlMsg(stringBuffer.toString());
            return this;
        }
        throw new EmailException("Invalid message supplied");
    }

    public String embed(String str, String str2) throws EmailException {
        try {
            return embed(new URL(str), str2);
        } catch (MalformedURLException e) {
            throw new EmailException("Invalid URL", e);
        }
    }

    public String embed(URL url, String str) throws EmailException {
        if (EmailUtils.isEmpty(str)) {
            throw new EmailException("name cannot be null or empty");
        } else if (this.inlineEmbeds.containsKey(str)) {
            InlineImage inlineImage = this.inlineEmbeds.get(str);
            URLDataSource uRLDataSource = (URLDataSource) inlineImage.getDataSource();
            if (url.toExternalForm().equals(uRLDataSource.getURL().toExternalForm())) {
                return inlineImage.getCid();
            }
            throw new EmailException("embedded name '" + str + "' is already bound to URL " + uRLDataSource.getURL() + "; existing names cannot be rebound");
        } else {
            try {
                InputStream openStream = url.openStream();
                if (openStream != null) {
                    try {
                        openStream.close();
                    } catch (IOException unused) {
                    }
                }
                return embed(new URLDataSource(url), str);
            } catch (IOException e) {
                throw new EmailException("Invalid URL", e);
            }
        }
    }

    public String embed(File file) throws EmailException {
        return embed(file, EmailUtils.randomAlphabetic(10).toLowerCase(Locale.ENGLISH));
    }

    public String embed(File file, String str) throws EmailException {
        if (!EmailUtils.isEmpty(file.getName())) {
            try {
                String canonicalPath = file.getCanonicalPath();
                if (this.inlineEmbeds.containsKey(file.getName())) {
                    InlineImage inlineImage = this.inlineEmbeds.get(file.getName());
                    FileDataSource fileDataSource = (FileDataSource) inlineImage.getDataSource();
                    try {
                        String canonicalPath2 = fileDataSource.getFile().getCanonicalPath();
                        if (canonicalPath.equals(canonicalPath2)) {
                            return inlineImage.getCid();
                        }
                        throw new EmailException("embedded name '" + file.getName() + "' is already bound to file " + canonicalPath2 + "; existing names cannot be rebound");
                    } catch (IOException e) {
                        throw new EmailException("couldn't get canonical path for file " + fileDataSource.getFile().getName() + "which has already been embedded", e);
                    }
                } else if (!file.exists()) {
                    throw new EmailException("file " + canonicalPath + " doesn't exist");
                } else if (!file.isFile()) {
                    throw new EmailException("file " + canonicalPath + " isn't a normal file");
                } else if (file.canRead()) {
                    return embed(new FileDataSource(file), file.getName(), str);
                } else {
                    throw new EmailException("file " + canonicalPath + " isn't readable");
                }
            } catch (IOException e2) {
                throw new EmailException("couldn't get canonical path for " + file.getName(), e2);
            }
        } else {
            throw new EmailException("file name cannot be null or empty");
        }
    }

    public String embed(DataSource dataSource, String str) throws EmailException {
        if (!this.inlineEmbeds.containsKey(str)) {
            return embed(dataSource, str, EmailUtils.randomAlphabetic(10).toLowerCase());
        }
        InlineImage inlineImage = this.inlineEmbeds.get(str);
        if (dataSource.equals(inlineImage.getDataSource())) {
            return inlineImage.getCid();
        }
        throw new EmailException("embedded DataSource '" + str + "' is already bound to name " + inlineImage.getDataSource().toString() + "; existing names cannot be rebound");
    }

    public String embed(DataSource dataSource, String str, String str2) throws EmailException {
        if (!EmailUtils.isEmpty(str)) {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            try {
                String encodeUrl = EmailUtils.encodeUrl(str2);
                mimeBodyPart.setDataHandler(new DataHandler(dataSource));
                mimeBodyPart.setFileName(str);
                mimeBodyPart.setDisposition("inline");
                mimeBodyPart.setContentID(SimpleComparison.f23612f + encodeUrl + SimpleComparison.f23610d);
                this.inlineEmbeds.put(str, new InlineImage(encodeUrl, dataSource, mimeBodyPart));
                return encodeUrl;
            } catch (UnsupportedEncodingException e) {
                throw new EmailException(e);
            } catch (MessagingException e2) {
                throw new EmailException(e2);
            }
        } else {
            throw new EmailException("name cannot be null or empty");
        }
    }

    @Override // org.apache.commons.mail.MultiPartEmail, org.apache.commons.mail.Email
    public void buildMimeMessage() throws EmailException {
        try {
            build();
            super.buildMimeMessage();
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    private void build() throws MessagingException, EmailException {
        MimeMultipart mimeMultipart;
        MimeMultipart container = getContainer();
        container.setSubType("mixed");
        if (!EmailUtils.isNotEmpty(this.html) || this.inlineEmbeds.size() <= 0) {
            if (EmailUtils.isNotEmpty(this.text) && EmailUtils.isNotEmpty(this.html)) {
                if (this.inlineEmbeds.size() > 0 || isBoolHasAttachments()) {
                    MimeMultipart mimeMultipart2 = new MimeMultipart("alternative");
                    addPart(mimeMultipart2, 0);
                    mimeMultipart = container;
                    container = mimeMultipart2;
                } else {
                    container.setSubType("alternative");
                }
            }
            mimeMultipart = container;
        } else {
            container = new MimeMultipart("related");
            addPart(container, 0);
            if (EmailUtils.isNotEmpty(this.text)) {
                MimeMultipart mimeMultipart3 = new MimeMultipart("alternative");
                BodyPart createBodyPart = createBodyPart();
                try {
                    createBodyPart.setContent(mimeMultipart3);
                    container.addBodyPart(createBodyPart, 0);
                    mimeMultipart = container;
                    container = mimeMultipart3;
                } catch (MessagingException e) {
                    throw new EmailException(e);
                }
            } else {
                mimeMultipart = container;
            }
        }
        if (EmailUtils.isNotEmpty(this.html)) {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            container.addBodyPart(mimeBodyPart, 0);
            mimeBodyPart.setText(this.html, this.charset, EmailConstants.TEXT_SUBTYPE_HTML);
            String contentType = mimeBodyPart.getContentType();
            if (contentType == null || !contentType.equals("text/html")) {
                if (EmailUtils.isNotEmpty(this.charset)) {
                    String str = this.html;
                    mimeBodyPart.setContent(str, "text/html; charset=" + this.charset);
                } else {
                    mimeBodyPart.setContent(this.html, "text/html");
                }
            }
            for (InlineImage inlineImage : this.inlineEmbeds.values()) {
                mimeMultipart.addBodyPart(inlineImage.getMbp());
            }
        }
        if (EmailUtils.isNotEmpty(this.text)) {
            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            container.addBodyPart(mimeBodyPart2, 0);
            mimeBodyPart2.setText(this.text, this.charset);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class InlineImage {
        private final String cid;
        private final DataSource dataSource;
        private final MimeBodyPart mbp;

        public InlineImage(String str, DataSource dataSource, MimeBodyPart mimeBodyPart) {
            this.cid = str;
            this.dataSource = dataSource;
            this.mbp = mimeBodyPart;
        }

        public String getCid() {
            return this.cid;
        }

        public DataSource getDataSource() {
            return this.dataSource;
        }

        public MimeBodyPart getMbp() {
            return this.mbp;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InlineImage)) {
                return false;
            }
            return this.cid.equals(((InlineImage) obj).cid);
        }

        public int hashCode() {
            return this.cid.hashCode();
        }
    }
}
