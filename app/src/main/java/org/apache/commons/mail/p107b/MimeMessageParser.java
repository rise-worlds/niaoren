package org.apache.commons.mail.p107b;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;
import javax.mail.util.ByteArrayDataSource;

/* renamed from: org.apache.commons.mail.b.a */
/* loaded from: classes2.dex */
public final class MimeMessageParser {

    /* renamed from: a */
    private final MimeMessage f14716a;

    /* renamed from: b */
    private String f14717b;

    /* renamed from: c */
    private String f14718c;

    /* renamed from: d */
    private final List<DataSource> f14719d = new ArrayList();

    /* renamed from: e */
    private final Map<String, DataSource> f14720e = new HashMap();

    /* renamed from: f */
    private boolean f14721f = false;

    private MimeMessageParser(MimeMessage mimeMessage) {
        this.f14716a = mimeMessage;
    }

    /* renamed from: a */
    private MimeMessageParser m14853a() throws Exception {
        m14849a(this.f14716a);
        return this;
    }

    /* renamed from: b */
    private List<Address> m14847b() throws Exception {
        Address[] recipients = this.f14716a.getRecipients(Message.RecipientType.f14650TO);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    /* renamed from: c */
    private List<Address> m14844c() throws Exception {
        Address[] recipients = this.f14716a.getRecipients(Message.RecipientType.f14649CC);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    /* renamed from: d */
    private List<Address> m14842d() throws Exception {
        Address[] recipients = this.f14716a.getRecipients(Message.RecipientType.BCC);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    /* renamed from: e */
    private String m14840e() throws Exception {
        Address[] from = this.f14716a.getFrom();
        if (from == null || from.length == 0) {
            return null;
        }
        return ((InternetAddress) from[0]).getAddress();
    }

    /* renamed from: f */
    private String m14839f() throws Exception {
        Address[] replyTo = this.f14716a.getReplyTo();
        if (replyTo == null || replyTo.length == 0) {
            return null;
        }
        return ((InternetAddress) replyTo[0]).getAddress();
    }

    /* renamed from: g */
    private String m14838g() throws Exception {
        return this.f14716a.getSubject();
    }

    /* renamed from: a */
    private void m14849a(MimePart mimePart) throws MessagingException, IOException {
        if (m14848a(mimePart, "text/plain") && this.f14717b == null && !"attachment".equalsIgnoreCase(mimePart.getDisposition())) {
            this.f14717b = (String) mimePart.getContent();
        } else if (!m14848a(mimePart, "text/html") || this.f14718c != null || "attachment".equalsIgnoreCase(mimePart.getDisposition())) {
            if (m14848a(mimePart, "multipart/*")) {
                this.f14721f = true;
                Multipart multipart = (Multipart) mimePart.getContent();
                int count = multipart.getCount();
                for (int i = 0; i < count; i++) {
                    m14849a((MimeBodyPart) multipart.getBodyPart(i));
                }
                return;
            }
            String contentID = mimePart.getContentID();
            String str = null;
            String replaceAll = contentID == null ? null : contentID.trim().replaceAll("[\\<\\>]", "");
            DataSource dataSource = mimePart.getDataHandler().getDataSource();
            String contentType = dataSource.getContentType();
            int indexOf = contentType.indexOf(59);
            if (indexOf >= 0) {
                contentType = contentType.substring(0, indexOf);
            }
            InputStream inputStream = dataSource.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            while (true) {
                int read = bufferedInputStream.read();
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(read);
            }
            bufferedOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            bufferedOutputStream.close();
            ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray, contentType);
            String name = dataSource.getName();
            if (name == null || name.length() == 0) {
                name = mimePart.getFileName();
            }
            if (name != null && name.length() > 0) {
                str = MimeUtility.decodeText(name);
            }
            byteArrayDataSource.setName(str);
            if (replaceAll != null) {
                this.f14720e.put(replaceAll, byteArrayDataSource);
            }
            this.f14719d.add(byteArrayDataSource);
        } else {
            this.f14718c = (String) mimePart.getContent();
        }
    }

    /* renamed from: a */
    private static String m14851a(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("[\\<\\>]", "");
    }

    /* renamed from: a */
    private static boolean m14848a(MimePart mimePart, String str) throws MessagingException, IOException {
        try {
            return new ContentType(mimePart.getDataHandler().getContentType()).match(str);
        } catch (ParseException unused) {
            return mimePart.getContentType().equalsIgnoreCase(str);
        }
    }

    /* renamed from: b */
    private static DataSource m14845b(MimePart mimePart) throws MessagingException, IOException {
        DataSource dataSource = mimePart.getDataHandler().getDataSource();
        String contentType = dataSource.getContentType();
        int indexOf = contentType.indexOf(59);
        if (indexOf >= 0) {
            contentType = contentType.substring(0, indexOf);
        }
        InputStream inputStream = dataSource.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        while (true) {
            int read = bufferedInputStream.read();
            if (read == -1) {
                break;
            }
            bufferedOutputStream.write(read);
        }
        bufferedOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        bufferedOutputStream.close();
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray, contentType);
        String name = dataSource.getName();
        if (name == null || name.length() == 0) {
            name = mimePart.getFileName();
        }
        byteArrayDataSource.setName((name == null || name.length() <= 0) ? null : MimeUtility.decodeText(name));
        return byteArrayDataSource;
    }

    /* renamed from: h */
    private MimeMessage m14837h() {
        return this.f14716a;
    }

    /* renamed from: i */
    private boolean m14836i() {
        return this.f14721f;
    }

    /* renamed from: j */
    private String m14835j() {
        return this.f14717b;
    }

    /* renamed from: k */
    private List<DataSource> m14834k() {
        return this.f14719d;
    }

    /* renamed from: l */
    private Collection<String> m14833l() {
        return Collections.unmodifiableSet(this.f14720e.keySet());
    }

    /* renamed from: m */
    private String m14832m() {
        return this.f14718c;
    }

    /* renamed from: n */
    private boolean m14831n() {
        return this.f14717b != null;
    }

    /* renamed from: o */
    private boolean m14830o() {
        return this.f14718c != null;
    }

    /* renamed from: p */
    private boolean m14829p() {
        return this.f14719d.size() > 0;
    }

    /* renamed from: c */
    private DataSource m14843c(String str) {
        return this.f14720e.get(str);
    }

    /* renamed from: a */
    private static String m14850a(Part part, DataSource dataSource) throws MessagingException, UnsupportedEncodingException {
        String name = dataSource.getName();
        if (name == null || name.length() == 0) {
            name = part.getFileName();
        }
        if (name == null || name.length() <= 0) {
            return null;
        }
        return MimeUtility.decodeText(name);
    }

    /* renamed from: a */
    private static byte[] m14852a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        while (true) {
            int read = bufferedInputStream.read();
            if (read != -1) {
                bufferedOutputStream.write(read);
            } else {
                bufferedOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedOutputStream.close();
                return byteArray;
            }
        }
    }

    /* renamed from: d */
    private static String m14841d(String str) {
        int indexOf = str.indexOf(59);
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }

    /* renamed from: b */
    private DataSource m14846b(String str) {
        for (int i = 0; i < this.f14719d.size(); i++) {
            DataSource dataSource = this.f14719d.get(i);
            if (str.equalsIgnoreCase(dataSource.getName())) {
                return dataSource;
            }
        }
        return null;
    }
}
