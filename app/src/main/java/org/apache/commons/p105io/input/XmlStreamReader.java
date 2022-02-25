package org.apache.commons.p105io.input;

import com.tencent.smtt.sdk.TbsListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.p105io.ByteOrderMark;
import p110z1.C4963cj;

/* renamed from: org.apache.commons.io.input.XmlStreamReader */
/* loaded from: classes2.dex */
public class XmlStreamReader extends Reader {
    private static final int BUFFER_SIZE = 4096;
    private static final String HTTP_EX_1 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be NULL";
    private static final String HTTP_EX_2 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch";
    private static final String HTTP_EX_3 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], Invalid MIME";
    private static final String RAW_EX_1 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch";
    private static final String RAW_EX_2 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] unknown BOM";
    private static final String US_ASCII = "US-ASCII";
    private static final String UTF_16 = "UTF-16";
    private static final String UTF_32 = "UTF-32";
    private static final String UTF_8 = "UTF-8";
    private final String defaultEncoding;
    private final String encoding;
    private final Reader reader;
    private static final ByteOrderMark[] BOMS = {ByteOrderMark.UTF_8, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_32BE, ByteOrderMark.UTF_32LE};
    private static final String UTF_16BE = "UTF-16BE";
    private static final String UTF_16LE = "UTF-16LE";
    private static final String UTF_32BE = "UTF-32BE";
    private static final String UTF_32LE = "UTF-32LE";
    private static final String EBCDIC = "CP1047";
    private static final ByteOrderMark[] XML_GUESS_BYTES = {new ByteOrderMark("UTF-8", 60, 63, 120, 109), new ByteOrderMark(UTF_16BE, 0, 60, 0, 63), new ByteOrderMark(UTF_16LE, 60, 0, 63, 0), new ByteOrderMark(UTF_32BE, 0, 0, 0, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, 109), new ByteOrderMark(UTF_32LE, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, 109, 0, 0, 0), new ByteOrderMark(EBCDIC, 76, 111, TbsListener.ErrorCode.STARTDOWNLOAD_8, TbsListener.ErrorCode.NEEDDOWNLOAD_9)};
    private static final Pattern CHARSET_PATTERN = Pattern.compile("charset=[\"']?([.[^; \"']]*)[\"']?");
    public static final Pattern ENCODING_PATTERN = Pattern.compile("<\\?xml.*encoding[\\s]*=[\\s]*((?:\".[^\"]*\")|(?:'.[^']*'))", 8);

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public XmlStreamReader(File file) throws IOException {
        this(new FileInputStream(file));
    }

    public XmlStreamReader(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    public XmlStreamReader(InputStream inputStream, boolean z) throws IOException {
        this(inputStream, z, (String) null);
    }

    public XmlStreamReader(InputStream inputStream, boolean z, String str) throws IOException {
        this.defaultEncoding = str;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(inputStream, 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        this.encoding = doRawStream(bOMInputStream, bOMInputStream2, z);
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    public XmlStreamReader(URL url) throws IOException {
        this(url.openConnection(), (String) null);
    }

    public XmlStreamReader(URLConnection uRLConnection, String str) throws IOException {
        this.defaultEncoding = str;
        String contentType = uRLConnection.getContentType();
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(uRLConnection.getInputStream(), 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        if ((uRLConnection instanceof HttpURLConnection) || contentType != null) {
            this.encoding = doHttpStream(bOMInputStream, bOMInputStream2, contentType, true);
        } else {
            this.encoding = doRawStream(bOMInputStream, bOMInputStream2, true);
        }
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    public XmlStreamReader(InputStream inputStream, String str) throws IOException {
        this(inputStream, str, true);
    }

    public XmlStreamReader(InputStream inputStream, String str, boolean z, String str2) throws IOException {
        this.defaultEncoding = str2;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream(inputStream, 4096), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        this.encoding = doHttpStream(bOMInputStream, bOMInputStream2, str, z);
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    public XmlStreamReader(InputStream inputStream, String str, boolean z) throws IOException {
        this(inputStream, str, z, null);
    }

    public String getEncoding() {
        return this.encoding;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        return this.reader.read(cArr, i, i2);
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    private String doRawStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, boolean z) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateRawEncoding(bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2));
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(null, e);
            }
            throw e;
        }
    }

    private String doHttpStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, String str, boolean z) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateHttpEncoding(str, bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2), z);
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(str, e);
            }
            throw e;
        }
    }

    private String doLenientDetection(String str, XmlStreamReaderException e) throws IOException {
        if (str != null && str.startsWith("text/html")) {
            String substring = str.substring(9);
            try {
                return calculateHttpEncoding("text/xml" + substring, e.getBomEncoding(), e.getXmlGuessEncoding(), e.getXmlEncoding(), true);
            } catch (XmlStreamReaderException e2) {
                e = e2;
            }
        }
        String xmlEncoding = e.getXmlEncoding();
        if (xmlEncoding == null) {
            xmlEncoding = e.getContentTypeEncoding();
        }
        if (xmlEncoding != null) {
            return xmlEncoding;
        }
        String str2 = this.defaultEncoding;
        return str2 == null ? "UTF-8" : str2;
    }

    String calculateRawEncoding(String str, String str2, String str3) throws IOException {
        if (str == null) {
            if (str2 != null && str3 != null) {
                return (!str3.equals("UTF-16") || (!str2.equals(UTF_16BE) && !str2.equals(UTF_16LE))) ? str3 : str2;
            }
            String str4 = this.defaultEncoding;
            return str4 == null ? "UTF-8" : str4;
        } else if (str.equals("UTF-8")) {
            if (str2 != null && !str2.equals("UTF-8")) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            } else if (str3 == null || str3.equals("UTF-8")) {
                return str;
            } else {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
        } else if (str.equals(UTF_16BE) || str.equals(UTF_16LE)) {
            if (str2 != null && !str2.equals(str)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            } else if (str3 == null || str3.equals("UTF-16") || str3.equals(str)) {
                return str;
            } else {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
        } else if (!str.equals(UTF_32BE) && !str.equals(UTF_32LE)) {
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_2, str, str2, str3), str, str2, str3);
        } else if (str2 != null && !str2.equals(str)) {
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        } else if (str3 == null || str3.equals(UTF_32) || str3.equals(str)) {
            return str;
        } else {
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        }
    }

    String calculateHttpEncoding(String str, String str2, String str3, String str4, boolean z) throws IOException {
        if (z && str4 != null) {
            return str4;
        }
        String contentTypeMime = getContentTypeMime(str);
        String contentTypeEncoding = getContentTypeEncoding(str);
        boolean isAppXml = isAppXml(contentTypeMime);
        boolean isTextXml = isTextXml(contentTypeMime);
        if (!isAppXml && !isTextXml) {
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_3, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        } else if (contentTypeEncoding == null) {
            if (isAppXml) {
                return calculateRawEncoding(str2, str3, str4);
            }
            String str5 = this.defaultEncoding;
            return str5 == null ? "US-ASCII" : str5;
        } else if (contentTypeEncoding.equals(UTF_16BE) || contentTypeEncoding.equals(UTF_16LE)) {
            if (str2 == null) {
                return contentTypeEncoding;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_1, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        } else if (contentTypeEncoding.equals("UTF-16")) {
            if (str2 != null && str2.startsWith("UTF-16")) {
                return str2;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_2, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        } else if (contentTypeEncoding.equals(UTF_32BE) || contentTypeEncoding.equals(UTF_32LE)) {
            if (str2 == null) {
                return contentTypeEncoding;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_1, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        } else if (!contentTypeEncoding.equals(UTF_32)) {
            return contentTypeEncoding;
        } else {
            if (str2 != null && str2.startsWith(UTF_32)) {
                return str2;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_2, contentTypeMime, contentTypeEncoding, str2, str3, str4), contentTypeMime, contentTypeEncoding, str2, str3, str4);
        }
    }

    static String getContentTypeMime(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(C4963cj.f20745b);
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        return str.trim();
    }

    static String getContentTypeEncoding(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(C4963cj.f20745b)) < 0) {
            return null;
        }
        Matcher matcher = CHARSET_PATTERN.matcher(str.substring(indexOf + 1));
        String group = matcher.find() ? matcher.group(1) : null;
        if (group != null) {
            return group.toUpperCase(Locale.US);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
        throw new java.io.IOException("Unexpected end of XML stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0052, code lost:
        throw new java.io.IOException("XML prolog or ROOT element not found on first " + r6 + " bytes");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0030, code lost:
        if (r3 != (-1)) goto L_0x003a;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getXmlProlog(java.io.InputStream r9, java.lang.String r10) throws java.io.IOException {
        /*
            if (r10 == 0) goto L_0x0099
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r0]
            r9.mark(r0)
            r2 = 0
            int r3 = r9.read(r1, r2, r0)
            java.lang.String r4 = ""
            r5 = -1
            r7 = r4
            r4 = -1
            r6 = 0
            r8 = 4096(0x1000, float:5.74E-42)
        L_0x0016:
            if (r3 == r5) goto L_0x002e
            if (r4 != r5) goto L_0x002e
            if (r6 >= r0) goto L_0x002e
            int r6 = r6 + r3
            int r8 = r8 - r3
            int r3 = r9.read(r1, r6, r8)
            java.lang.String r7 = new java.lang.String
            r7.<init>(r1, r2, r6, r10)
            r4 = 62
            int r4 = r7.indexOf(r4)
            goto L_0x0016
        L_0x002e:
            if (r4 != r5) goto L_0x0053
            if (r3 != r5) goto L_0x003a
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Unexpected end of XML stream"
            r9.<init>(r10)
            throw r9
        L_0x003a:
            java.io.IOException r9 = new java.io.IOException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "XML prolog or ROOT element not found on first "
            r10.<init>(r0)
            r10.append(r6)
            java.lang.String r0 = " bytes"
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x0053:
            if (r6 <= 0) goto L_0x0099
            r9.reset()
            java.io.BufferedReader r9 = new java.io.BufferedReader
            java.io.StringReader r10 = new java.io.StringReader
            r0 = 1
            int r4 = r4 + r0
            java.lang.String r1 = r7.substring(r2, r4)
            r10.<init>(r1)
            r9.<init>(r10)
            java.lang.StringBuffer r10 = new java.lang.StringBuffer
            r10.<init>()
            java.lang.String r1 = r9.readLine()
        L_0x0071:
            if (r1 == 0) goto L_0x007b
            r10.append(r1)
            java.lang.String r1 = r9.readLine()
            goto L_0x0071
        L_0x007b:
            java.util.regex.Pattern r9 = org.apache.commons.p105io.input.XmlStreamReader.ENCODING_PATTERN
            java.util.regex.Matcher r9 = r9.matcher(r10)
            boolean r10 = r9.find()
            if (r10 == 0) goto L_0x0099
            java.lang.String r9 = r9.group(r0)
            java.lang.String r9 = r9.toUpperCase()
            int r10 = r9.length()
            int r10 = r10 - r0
            java.lang.String r9 = r9.substring(r0, r10)
            goto L_0x009a
        L_0x0099:
            r9 = 0
        L_0x009a:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.p105io.input.XmlStreamReader.getXmlProlog(java.io.InputStream, java.lang.String):java.lang.String");
    }

    static boolean isAppXml(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("application/xml") || str.equals("application/xml-dtd") || str.equals("application/xml-external-parsed-entity")) {
            return true;
        }
        return str.startsWith("application/") && str.endsWith("+xml");
    }

    static boolean isTextXml(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("text/xml") || str.equals("text/xml-external-parsed-entity")) {
            return true;
        }
        return str.startsWith("text/") && str.endsWith("+xml");
    }
}
