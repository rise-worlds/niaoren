package javax.mail.internet;

import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.LineOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessageAware;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.MultipartDataSource;

/* loaded from: classes2.dex */
public class MimeMultipart extends Multipart {
    private static boolean bmparse = true;
    private static boolean ignoreMissingBoundaryParameter = true;
    private static boolean ignoreMissingEndBoundary = true;
    private boolean complete;

    /* renamed from: ds */
    protected DataSource f14656ds;
    protected boolean parsed;
    private String preamble;

    static {
        try {
            String property = System.getProperty("mail.mime.multipart.ignoremissingendboundary");
            boolean z = false;
            ignoreMissingEndBoundary = property == null || !property.equalsIgnoreCase("false");
            String property2 = System.getProperty("mail.mime.multipart.ignoremissingboundaryparameter");
            ignoreMissingBoundaryParameter = property2 == null || !property2.equalsIgnoreCase("false");
            String property3 = System.getProperty("mail.mime.multipart.bmparse");
            if (property3 == null || !property3.equalsIgnoreCase("false")) {
                z = true;
            }
            bmparse = z;
        } catch (SecurityException unused) {
        }
    }

    public MimeMultipart() {
        this("mixed");
    }

    public MimeMultipart(String str) {
        this.f14656ds = null;
        this.parsed = true;
        this.complete = true;
        this.preamble = null;
        String uniqueBoundaryValue = UniqueValue.getUniqueBoundaryValue();
        ContentType contentType = new ContentType("multipart", str, null);
        contentType.setParameter("boundary", uniqueBoundaryValue);
        this.contentType = contentType.toString();
    }

    public MimeMultipart(DataSource dataSource) throws MessagingException {
        this.f14656ds = null;
        this.parsed = true;
        this.complete = true;
        this.preamble = null;
        if (dataSource instanceof MessageAware) {
            setParent(((MessageAware) dataSource).getMessageContext().getPart());
        }
        if (dataSource instanceof MultipartDataSource) {
            setMultipartDataSource((MultipartDataSource) dataSource);
            return;
        }
        this.parsed = false;
        this.f14656ds = dataSource;
        this.contentType = dataSource.getContentType();
    }

    public synchronized void setSubType(String str) throws MessagingException {
        ContentType contentType = new ContentType(this.contentType);
        contentType.setSubType(str);
        this.contentType = contentType.toString();
    }

    @Override // javax.mail.Multipart
    public synchronized int getCount() throws MessagingException {
        parse();
        return super.getCount();
    }

    @Override // javax.mail.Multipart
    public synchronized BodyPart getBodyPart(int i) throws MessagingException {
        parse();
        return super.getBodyPart(i);
    }

    public synchronized BodyPart getBodyPart(String str) throws MessagingException {
        parse();
        int count = getCount();
        for (int i = 0; i < count; i++) {
            MimeBodyPart mimeBodyPart = (MimeBodyPart) getBodyPart(i);
            String contentID = mimeBodyPart.getContentID();
            if (contentID != null && contentID.equals(str)) {
                return mimeBodyPart;
            }
        }
        return null;
    }

    @Override // javax.mail.Multipart
    public boolean removeBodyPart(BodyPart bodyPart) throws MessagingException {
        parse();
        return super.removeBodyPart(bodyPart);
    }

    @Override // javax.mail.Multipart
    public void removeBodyPart(int i) throws MessagingException {
        parse();
        super.removeBodyPart(i);
    }

    @Override // javax.mail.Multipart
    public synchronized void addBodyPart(BodyPart bodyPart) throws MessagingException {
        parse();
        super.addBodyPart(bodyPart);
    }

    @Override // javax.mail.Multipart
    public synchronized void addBodyPart(BodyPart bodyPart, int i) throws MessagingException {
        parse();
        super.addBodyPart(bodyPart, i);
    }

    public synchronized boolean isComplete() throws MessagingException {
        parse();
        return this.complete;
    }

    public synchronized String getPreamble() throws MessagingException {
        parse();
        return this.preamble;
    }

    public synchronized void setPreamble(String str) throws MessagingException {
        this.preamble = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateHeaders() throws MessagingException {
        for (int i = 0; i < this.parts.size(); i++) {
            ((MimeBodyPart) this.parts.elementAt(i)).updateHeaders();
        }
    }

    @Override // javax.mail.Multipart
    public synchronized void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        parse();
        String str = "--" + new ContentType(this.contentType).getParameter("boundary");
        LineOutputStream lineOutputStream = new LineOutputStream(outputStream);
        if (this.preamble != null) {
            byte[] bytes = ASCIIUtility.getBytes(this.preamble);
            lineOutputStream.write(bytes);
            if (!(bytes.length <= 0 || bytes[bytes.length - 1] == 13 || bytes[bytes.length - 1] == 10)) {
                lineOutputStream.writeln();
            }
        }
        for (int i = 0; i < this.parts.size(); i++) {
            lineOutputStream.writeln(str);
            ((MimeBodyPart) this.parts.elementAt(i)).writeTo(outputStream);
            lineOutputStream.writeln();
        }
        lineOutputStream.writeln(String.valueOf(str) + "--");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00c7, code lost:
        r2.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x021d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x005f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x017e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void parse() throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 622
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeMultipart.parse():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x017f, code lost:
        r19 = r14;
        r5 = r15;
        r25 = (r0.getPosition() - r6) - (r12 == true ? 1 : 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x02b6, code lost:
        if (r3 > 0) goto L_0x02b9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x02b9, code lost:
        r3 = r3 - 1;
        r8[r3] = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d4, code lost:
        r2.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0198 A[Catch: all -> 0x033f, IOException -> 0x0341, TryCatch #1 {IOException -> 0x0341, blocks: (B:26:0x004f, B:27:0x0056, B:30:0x0063, B:33:0x006b, B:37:0x0075, B:39:0x007d, B:43:0x0088, B:48:0x0095, B:49:0x009b, B:51:0x00a7, B:53:0x00ac, B:57:0x00bc, B:58:0x00c0, B:60:0x00c6, B:63:0x00ce, B:65:0x00d2, B:70:0x00db, B:71:0x00e2, B:73:0x00e7, B:74:0x00ef, B:77:0x00f7, B:78:0x00ff, B:79:0x0105, B:80:0x010e, B:82:0x011b, B:85:0x0121, B:86:0x0125, B:87:0x0131, B:88:0x0138, B:92:0x0140, B:94:0x0148, B:98:0x0154, B:107:0x0166, B:117:0x017f, B:119:0x0190, B:121:0x0198, B:123:0x01a0, B:133:0x01ba, B:135:0x01c4, B:139:0x01d4, B:140:0x01e3, B:142:0x01ea, B:144:0x01f7, B:147:0x01fd, B:148:0x0201, B:149:0x0209, B:150:0x0222, B:152:0x023e, B:157:0x0255, B:158:0x025b, B:160:0x0265, B:161:0x0274, B:165:0x0282, B:167:0x0288, B:169:0x02a4, B:170:0x02ab, B:171:0x02ac, B:175:0x02b9, B:176:0x02be, B:178:0x02c6, B:179:0x02cd, B:180:0x02db, B:181:0x02f3, B:182:0x02fa, B:184:0x02fd, B:187:0x0305, B:191:0x0314, B:192:0x0320), top: B:209:0x004f, outer: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x032a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0303 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x01d0 A[EDGE_INSN: B:241:0x01d0->B:137:0x01d0 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v20 */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v29 */
    /* JADX WARN: Type inference failed for: r12v30 */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void parsebm() throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 866
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeMultipart.parsebm():void");
    }

    private static int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        while (i2 > 0) {
            int read = inputStream.read(bArr, i, i2);
            if (read <= 0) {
                break;
            }
            i += read;
            i3 += read;
            i2 -= read;
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    private void skipFully(InputStream inputStream, long j) throws IOException {
        while (j > 0) {
            long skip = inputStream.skip(j);
            if (skip > 0) {
                j -= skip;
            } else {
                throw new EOFException("can't skip");
            }
        }
    }

    protected InternetHeaders createInternetHeaders(InputStream inputStream) throws MessagingException {
        return new InternetHeaders(inputStream);
    }

    protected MimeBodyPart createMimeBodyPart(InternetHeaders internetHeaders, byte[] bArr) throws MessagingException {
        return new MimeBodyPart(internetHeaders, bArr);
    }

    protected MimeBodyPart createMimeBodyPart(InputStream inputStream) throws MessagingException {
        return new MimeBodyPart(inputStream);
    }
}
