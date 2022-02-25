package javax.mail.internet;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.BEncoderStream;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.QDecoderStream;
import com.sun.mail.util.QEncoderStream;
import com.sun.mail.util.QPDecoderStream;
import com.sun.mail.util.QPEncoderStream;
import com.sun.mail.util.UUDecoderStream;
import com.sun.mail.util.UUEncoderStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.p105io.IOUtils;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class MimeUtility {
    public static final int ALL = -1;
    static final int ALL_ASCII = 1;
    static final int MOSTLY_ASCII = 2;
    static final int MOSTLY_NONASCII = 3;
    private static boolean decodeStrict = true;
    private static String defaultJavaCharset = null;
    private static String defaultMIMECharset = null;
    private static boolean encodeEolStrict = false;
    private static boolean foldEncodedWords = false;
    private static boolean foldText = true;
    private static Hashtable java2mime;
    private static Hashtable mime2java;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean nonascii(int i) {
        if (i < 127) {
            return (i >= 32 || i == 13 || i == 10 || i == 9) ? false : true;
        }
        return true;
    }

    private MimeUtility() {
    }

    static {
        LineInputStream lineInputStream;
        Throwable th;
        try {
            String property = System.getProperty("mail.mime.decodetext.strict");
            boolean z = false;
            decodeStrict = property == null || !property.equalsIgnoreCase("false");
            String property2 = System.getProperty("mail.mime.encodeeol.strict");
            encodeEolStrict = property2 != null && property2.equalsIgnoreCase("true");
            String property3 = System.getProperty("mail.mime.foldencodedwords");
            foldEncodedWords = property3 != null && property3.equalsIgnoreCase("true");
            String property4 = System.getProperty("mail.mime.foldtext");
            if (property4 == null || !property4.equalsIgnoreCase("false")) {
                z = true;
            }
            foldText = z;
        } catch (SecurityException unused) {
        }
        java2mime = new Hashtable(40);
        mime2java = new Hashtable(10);
        try {
            InputStream resourceAsStream = MimeUtility.class.getResourceAsStream("/META-INF/javamail.charset.map");
            if (resourceAsStream != null) {
                try {
                    lineInputStream = new LineInputStream(resourceAsStream);
                    try {
                        loadMappings(lineInputStream, java2mime);
                        loadMappings(lineInputStream, mime2java);
                        try {
                            lineInputStream.close();
                        } catch (Exception unused2) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            lineInputStream.close();
                        } catch (Exception unused3) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    lineInputStream = resourceAsStream;
                }
            }
        } catch (Exception unused4) {
        }
        if (java2mime.isEmpty()) {
            java2mime.put("8859_1", "ISO-8859-1");
            java2mime.put("iso8859_1", "ISO-8859-1");
            java2mime.put("iso8859-1", "ISO-8859-1");
            java2mime.put("8859_2", "ISO-8859-2");
            java2mime.put("iso8859_2", "ISO-8859-2");
            java2mime.put("iso8859-2", "ISO-8859-2");
            java2mime.put("8859_3", "ISO-8859-3");
            java2mime.put("iso8859_3", "ISO-8859-3");
            java2mime.put("iso8859-3", "ISO-8859-3");
            java2mime.put("8859_4", "ISO-8859-4");
            java2mime.put("iso8859_4", "ISO-8859-4");
            java2mime.put("iso8859-4", "ISO-8859-4");
            java2mime.put("8859_5", "ISO-8859-5");
            java2mime.put("iso8859_5", "ISO-8859-5");
            java2mime.put("iso8859-5", "ISO-8859-5");
            java2mime.put("8859_6", "ISO-8859-6");
            java2mime.put("iso8859_6", "ISO-8859-6");
            java2mime.put("iso8859-6", "ISO-8859-6");
            java2mime.put("8859_7", "ISO-8859-7");
            java2mime.put("iso8859_7", "ISO-8859-7");
            java2mime.put("iso8859-7", "ISO-8859-7");
            java2mime.put("8859_8", "ISO-8859-8");
            java2mime.put("iso8859_8", "ISO-8859-8");
            java2mime.put("iso8859-8", "ISO-8859-8");
            java2mime.put("8859_9", "ISO-8859-9");
            java2mime.put("iso8859_9", "ISO-8859-9");
            java2mime.put("iso8859-9", "ISO-8859-9");
            java2mime.put("sjis", "Shift_JIS");
            java2mime.put("jis", "ISO-2022-JP");
            java2mime.put("iso2022jp", "ISO-2022-JP");
            java2mime.put("euc_jp", "euc-jp");
            java2mime.put("koi8_r", "koi8-r");
            java2mime.put("euc_cn", "euc-cn");
            java2mime.put("euc_tw", "euc-tw");
            java2mime.put("euc_kr", "euc-kr");
        }
        if (mime2java.isEmpty()) {
            mime2java.put("iso-2022-cn", "ISO2022CN");
            mime2java.put("iso-2022-kr", "ISO2022KR");
            mime2java.put(EmailConstants.UTF_8, "UTF8");
            mime2java.put("utf8", "UTF8");
            mime2java.put("ja_jp.iso2022-7", "ISO2022JP");
            mime2java.put("ja_jp.eucjp", "EUCJIS");
            mime2java.put("euc-kr", "KSC5601");
            mime2java.put("euckr", "KSC5601");
            mime2java.put("us-ascii", "ISO-8859-1");
            mime2java.put("x-us-ascii", "ISO-8859-1");
        }
    }

    public static String getEncoding(DataSource dataSource) {
        String str;
        try {
            ContentType contentType = new ContentType(dataSource.getContentType());
            InputStream inputStream = dataSource.getInputStream();
            switch (checkAscii(inputStream, -1, !contentType.match("text/*"))) {
                case 1:
                    str = "7bit";
                    break;
                case 2:
                    str = "quoted-printable";
                    break;
                default:
                    str = "base64";
                    break;
            }
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            return str;
        } catch (Exception unused2) {
            return "base64";
        }
    }

    public static String getEncoding(DataHandler dataHandler) {
        if (dataHandler.getName() != null) {
            return getEncoding(dataHandler.getDataSource());
        }
        try {
            if (new ContentType(dataHandler.getContentType()).match("text/*")) {
                AsciiOutputStream asciiOutputStream = new AsciiOutputStream(false, false);
                try {
                    dataHandler.writeTo(asciiOutputStream);
                } catch (IOException unused) {
                }
                switch (asciiOutputStream.getAscii()) {
                    case 1:
                        return "7bit";
                    case 2:
                        return "quoted-printable";
                    default:
                        return "base64";
                }
            } else {
                AsciiOutputStream asciiOutputStream2 = new AsciiOutputStream(true, encodeEolStrict);
                try {
                    dataHandler.writeTo(asciiOutputStream2);
                } catch (IOException unused2) {
                }
                return asciiOutputStream2.getAscii() == 1 ? "7bit" : "base64";
            }
        } catch (Exception unused3) {
            return "base64";
        }
    }

    public static InputStream decode(InputStream inputStream, String str) throws MessagingException {
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64DecoderStream(inputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPDecoderStream(inputStream);
        }
        if (str.equalsIgnoreCase("uuencode") || str.equalsIgnoreCase("x-uuencode") || str.equalsIgnoreCase("x-uue")) {
            return new UUDecoderStream(inputStream);
        }
        if (str.equalsIgnoreCase("binary") || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase("8bit")) {
            return inputStream;
        }
        throw new MessagingException("Unknown encoding: " + str);
    }

    public static OutputStream encode(OutputStream outputStream, String str) throws MessagingException {
        if (str == null) {
            return outputStream;
        }
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64EncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPEncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("uuencode") || str.equalsIgnoreCase("x-uuencode") || str.equalsIgnoreCase("x-uue")) {
            return new UUEncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("binary") || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase("8bit")) {
            return outputStream;
        }
        throw new MessagingException("Unknown encoding: " + str);
    }

    public static OutputStream encode(OutputStream outputStream, String str, String str2) throws MessagingException {
        if (str == null) {
            return outputStream;
        }
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64EncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPEncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("uuencode") || str.equalsIgnoreCase("x-uuencode") || str.equalsIgnoreCase("x-uue")) {
            return new UUEncoderStream(outputStream, str2);
        }
        if (str.equalsIgnoreCase("binary") || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase("8bit")) {
            return outputStream;
        }
        throw new MessagingException("Unknown encoding: " + str);
    }

    public static String encodeText(String str) throws UnsupportedEncodingException {
        return encodeText(str, null, null);
    }

    public static String encodeText(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, false);
    }

    public static String decodeText(String str) throws UnsupportedEncodingException {
        if (str.indexOf("=?") == -1) {
            return str;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \t\n\r", true);
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            char charAt = nextToken.charAt(0);
            if (charAt == ' ' || charAt == '\t' || charAt == '\r' || charAt == '\n') {
                stringBuffer2.append(charAt);
            } else {
                try {
                    String decodeWord = decodeWord(nextToken);
                    if (!z && stringBuffer2.length() > 0) {
                        stringBuffer.append(stringBuffer2);
                    }
                    nextToken = decodeWord;
                    z = true;
                } catch (ParseException unused) {
                    if (!decodeStrict) {
                        String decodeInnerWords = decodeInnerWords(nextToken);
                        if (decodeInnerWords != nextToken) {
                            if ((!z || !nextToken.startsWith("=?")) && stringBuffer2.length() > 0) {
                                stringBuffer.append(stringBuffer2);
                            }
                            z = nextToken.endsWith("?=");
                            nextToken = decodeInnerWords;
                        } else {
                            if (stringBuffer2.length() > 0) {
                                stringBuffer.append(stringBuffer2);
                            }
                            z = false;
                        }
                    } else {
                        if (stringBuffer2.length() > 0) {
                            stringBuffer.append(stringBuffer2);
                        }
                        z = false;
                    }
                }
                stringBuffer.append(nextToken);
                stringBuffer2.setLength(0);
            }
        }
        stringBuffer.append(stringBuffer2);
        return stringBuffer.toString();
    }

    public static String encodeWord(String str) throws UnsupportedEncodingException {
        return encodeWord(str, null, null);
    }

    public static String encodeWord(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, true);
    }

    private static String encodeWord(String str, String str2, String str3, boolean z) throws UnsupportedEncodingException {
        String str4;
        boolean z2;
        int checkAscii = checkAscii(str);
        if (checkAscii == 1) {
            return str;
        }
        if (str2 == null) {
            str4 = getDefaultJavaCharset();
            str2 = getDefaultMIMECharset();
        } else {
            str4 = javaCharset(str2);
        }
        if (str3 == null) {
            str3 = checkAscii != 3 ? "Q" : "B";
        }
        if (str3.equalsIgnoreCase("B")) {
            z2 = true;
        } else if (str3.equalsIgnoreCase("Q")) {
            z2 = false;
        } else {
            throw new UnsupportedEncodingException("Unknown transfer encoding: " + str3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = 68 - str2.length();
        doEncode(str, z2, str4, length, "=?" + str2 + "?" + str3 + "?", true, z, stringBuffer);
        return stringBuffer.toString();
    }

    private static void doEncode(String str, boolean z, String str2, int i, String str3, boolean z2, boolean z3, StringBuffer stringBuffer) throws UnsupportedEncodingException {
        byte[] bytes;
        int i2;
        int i3;
        OutputStream outputStream;
        String str4;
        int length;
        String str5 = str;
        boolean z4 = z2;
        while (true) {
            bytes = str5.getBytes(str2);
            if (z) {
                i3 = BEncoderStream.encodedLength(bytes);
                i2 = i;
            } else {
                i3 = QEncoderStream.encodedLength(bytes, z3);
                i2 = i;
            }
            if (i3 <= i2 || (length = str5.length()) <= 1) {
                break;
            }
            int i4 = length / 2;
            doEncode(str5.substring(0, i4), z, str2, i, str3, z4, z3, stringBuffer);
            str5 = str5.substring(i4, length);
            z4 = false;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (z) {
            outputStream = new BEncoderStream(byteArrayOutputStream);
        } else {
            outputStream = new QEncoderStream(byteArrayOutputStream, z3);
        }
        try {
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException unused) {
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z4) {
            str4 = str3;
        } else if (foldEncodedWords) {
            stringBuffer.append("\r\n ");
            str4 = str3;
        } else {
            stringBuffer.append(ExpandableTextView.f6958c);
            str4 = str3;
        }
        stringBuffer.append(str4);
        for (byte b : byteArray) {
            stringBuffer.append((char) b);
        }
        stringBuffer.append("?=");
    }

    public static String decodeWord(String str) throws ParseException, UnsupportedEncodingException {
        String str2;
        InputStream inputStream;
        if (str.startsWith("=?")) {
            int indexOf = str.indexOf(63, 2);
            if (indexOf != -1) {
                String javaCharset = javaCharset(str.substring(2, indexOf));
                int i = indexOf + 1;
                int indexOf2 = str.indexOf(63, i);
                if (indexOf2 != -1) {
                    String substring = str.substring(i, indexOf2);
                    int i2 = indexOf2 + 1;
                    int indexOf3 = str.indexOf("?=", i2);
                    if (indexOf3 != -1) {
                        String substring2 = str.substring(i2, indexOf3);
                        try {
                            if (substring2.length() > 0) {
                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ASCIIUtility.getBytes(substring2));
                                if (substring.equalsIgnoreCase("B")) {
                                    inputStream = new BASE64DecoderStream(byteArrayInputStream);
                                } else if (substring.equalsIgnoreCase("Q")) {
                                    inputStream = new QDecoderStream(byteArrayInputStream);
                                } else {
                                    throw new UnsupportedEncodingException("unknown encoding: " + substring);
                                }
                                int available = byteArrayInputStream.available();
                                byte[] bArr = new byte[available];
                                int read = inputStream.read(bArr, 0, available);
                                str2 = read <= 0 ? "" : new String(bArr, 0, read, javaCharset);
                            } else {
                                str2 = "";
                            }
                            int i3 = indexOf3 + 2;
                            if (i3 >= str.length()) {
                                return str2;
                            }
                            String substring3 = str.substring(i3);
                            if (!decodeStrict) {
                                substring3 = decodeInnerWords(substring3);
                            }
                            return String.valueOf(str2) + substring3;
                        } catch (UnsupportedEncodingException e) {
                            throw e;
                        } catch (IOException e2) {
                            throw new ParseException(e2.toString());
                        } catch (IllegalArgumentException unused) {
                            throw new UnsupportedEncodingException(javaCharset);
                        }
                    } else {
                        throw new ParseException("encoded word does not end with \"?=\": " + str);
                    }
                } else {
                    throw new ParseException("encoded word does not include encoding: " + str);
                }
            } else {
                throw new ParseException("encoded word does not include charset: " + str);
            }
        } else {
            throw new ParseException("encoded word does not start with \"=?\": " + str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String decodeInnerWords(java.lang.String r5) throws java.io.UnsupportedEncodingException {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
        L_0x0006:
            java.lang.String r2 = "=?"
            int r2 = r5.indexOf(r2, r1)
            if (r2 >= 0) goto L_0x000f
            goto L_0x0040
        L_0x000f:
            java.lang.String r3 = r5.substring(r1, r2)
            r0.append(r3)
            int r3 = r2 + 2
            r4 = 63
            int r3 = r5.indexOf(r4, r3)
            if (r3 < 0) goto L_0x0040
            int r3 = r3 + 1
            int r3 = r5.indexOf(r4, r3)
            if (r3 < 0) goto L_0x0040
            java.lang.String r4 = "?="
            int r3 = r3 + 1
            int r3 = r5.indexOf(r4, r3)
            if (r3 < 0) goto L_0x0040
            int r1 = r3 + 2
            java.lang.String r2 = r5.substring(r2, r1)
            java.lang.String r2 = decodeWord(r2)     // Catch: ParseException -> 0x003c
        L_0x003c:
            r0.append(r2)
            goto L_0x0006
        L_0x0040:
            if (r1 != 0) goto L_0x0043
            return r5
        L_0x0043:
            int r2 = r5.length()
            if (r1 >= r2) goto L_0x0050
            java.lang.String r5 = r5.substring(r1)
            r0.append(r5)
        L_0x0050:
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.decodeInnerWords(java.lang.String):java.lang.String");
    }

    public static String quote(String str, String str2) {
        int length = str.length();
        char c = 0;
        int i = 0;
        boolean z = false;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '\"' || charAt == '\\' || charAt == '\r' || charAt == '\n') {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append(Typography.f21049a);
                stringBuffer.append(str.substring(0, i));
                while (i < length) {
                    char charAt2 = str.charAt(i);
                    if ((charAt2 == '\"' || charAt2 == '\\' || charAt2 == '\r' || charAt2 == '\n') && !(charAt2 == '\n' && c == '\r')) {
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    }
                    stringBuffer.append(charAt2);
                    i++;
                    c = charAt2;
                }
                stringBuffer.append(Typography.f21049a);
                return stringBuffer.toString();
            }
            if (charAt < ' ' || charAt >= 127 || str2.indexOf(charAt) >= 0) {
                z = true;
            }
            i++;
        }
        if (!z) {
            return str;
        }
        StringBuffer stringBuffer2 = new StringBuffer(length + 2);
        stringBuffer2.append(Typography.f21049a);
        stringBuffer2.append(str);
        stringBuffer2.append(Typography.f21049a);
        return stringBuffer2.toString();
    }

    public static String fold(int i, String str) {
        char charAt;
        if (!foldText) {
            return str;
        }
        int length = str.length() - 1;
        while (length >= 0 && ((charAt = str.charAt(length)) == ' ' || charAt == '\t' || charAt == '\r' || charAt == '\n')) {
            length--;
        }
        if (length != str.length() - 1) {
            str = str.substring(0, length + 1);
        }
        if (str.length() + i <= 76) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 4);
        int i2 = i;
        String str2 = str;
        char c = 0;
        while (true) {
            if (str2.length() + i2 <= 76) {
                break;
            }
            int i3 = 0;
            int i4 = -1;
            while (i3 < str2.length() && (i4 == -1 || i2 + i3 <= 76)) {
                char charAt2 = str2.charAt(i3);
                if (!((charAt2 != ' ' && charAt2 != '\t') || c == ' ' || c == '\t')) {
                    i4 = i3;
                }
                i3++;
                c = charAt2;
            }
            if (i4 == -1) {
                stringBuffer.append(str2);
                str2 = "";
                break;
            }
            stringBuffer.append(str2.substring(0, i4));
            stringBuffer.append("\r\n");
            c = str2.charAt(i4);
            stringBuffer.append(c);
            str2 = str2.substring(i4 + 1);
            i2 = 1;
        }
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static String unfold(String str) {
        char charAt;
        if (!foldText) {
            return str;
        }
        StringBuffer stringBuffer = null;
        while (true) {
            int indexOfAny = indexOfAny(str, "\r\n");
            if (indexOfAny < 0) {
                break;
            }
            int length = str.length();
            int i = indexOfAny + 1;
            if (i < length && str.charAt(i - 1) == '\r' && str.charAt(i) == '\n') {
                i++;
            }
            if (indexOfAny != 0) {
                int i2 = indexOfAny - 1;
                if (str.charAt(i2) == '\\') {
                    if (stringBuffer == null) {
                        stringBuffer = new StringBuffer(str.length());
                    }
                    stringBuffer.append(str.substring(0, i2));
                    stringBuffer.append(str.substring(indexOfAny, i));
                    str = str.substring(i);
                }
            }
            if (i >= length || !((charAt = str.charAt(i)) == ' ' || charAt == '\t')) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length());
                }
                stringBuffer.append(str.substring(0, i));
                str = str.substring(i);
            } else {
                int i3 = i + 1;
                while (i3 < length) {
                    char charAt2 = str.charAt(i3);
                    if (charAt2 != ' ' && charAt2 != '\t') {
                        break;
                    }
                    i3++;
                }
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length());
                }
                if (indexOfAny != 0) {
                    stringBuffer.append(str.substring(0, indexOfAny));
                    stringBuffer.append(' ');
                }
                str = str.substring(i3);
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    private static int indexOfAny(String str, String str2) {
        return indexOfAny(str, str2, 0);
    }

    private static int indexOfAny(String str, String str2, int i) {
        try {
            int length = str.length();
            while (i < length) {
                if (str2.indexOf(str.charAt(i)) >= 0) {
                    return i;
                }
                i++;
            }
            return -1;
        } catch (StringIndexOutOfBoundsException unused) {
            return -1;
        }
    }

    public static String javaCharset(String str) {
        String str2;
        Hashtable hashtable = mime2java;
        return (hashtable == null || str == null || (str2 = (String) hashtable.get(str.toLowerCase(Locale.ENGLISH))) == null) ? str : str2;
    }

    public static String mimeCharset(String str) {
        String str2;
        Hashtable hashtable = java2mime;
        return (hashtable == null || str == null || (str2 = (String) hashtable.get(str.toLowerCase(Locale.ENGLISH))) == null) ? str : str2;
    }

    public static String getDefaultJavaCharset() {
        if (defaultJavaCharset == null) {
            String str = null;
            try {
                str = System.getProperty(EmailConstants.MAIL_MIME_CHARSET);
            } catch (SecurityException unused) {
            }
            if (str == null || str.length() <= 0) {
                try {
                    defaultJavaCharset = System.getProperty("file.encoding", "8859_1");
                } catch (SecurityException unused2) {
                    String encoding = new InputStreamReader(new InputStream() { // from class: javax.mail.internet.MimeUtility.1NullInputStream
                        @Override // java.io.InputStream
                        public int read() {
                            return 0;
                        }
                    }).getEncoding();
                    defaultJavaCharset = encoding;
                    if (encoding == null) {
                        defaultJavaCharset = "8859_1";
                    }
                }
            } else {
                String javaCharset = javaCharset(str);
                defaultJavaCharset = javaCharset;
                return javaCharset;
            }
        }
        return defaultJavaCharset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDefaultMIMECharset() {
        if (defaultMIMECharset == null) {
            try {
                defaultMIMECharset = System.getProperty(EmailConstants.MAIL_MIME_CHARSET);
            } catch (SecurityException unused) {
            }
        }
        if (defaultMIMECharset == null) {
            defaultMIMECharset = mimeCharset(getDefaultJavaCharset());
        }
        return defaultMIMECharset;
    }

    private static void loadMappings(LineInputStream lineInputStream, Hashtable hashtable) {
        while (true) {
            try {
                String readLine = lineInputStream.readLine();
                if (readLine == null) {
                    return;
                }
                if (readLine.startsWith("--") && readLine.endsWith("--")) {
                    return;
                }
                if (readLine.trim().length() != 0 && !readLine.startsWith("#")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, " \t");
                    try {
                        String nextToken = stringTokenizer.nextToken();
                        hashtable.put(nextToken.toLowerCase(Locale.ENGLISH), stringTokenizer.nextToken());
                    } catch (NoSuchElementException unused) {
                    }
                }
            } catch (IOException unused2) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkAscii(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (nonascii(str.charAt(i3))) {
                i++;
            } else {
                i2++;
            }
        }
        if (i == 0) {
            return 1;
        }
        return i2 > i ? 2 : 3;
    }

    static int checkAscii(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        for (byte b : bArr) {
            if (nonascii(b & 255)) {
                i++;
            } else {
                i2++;
            }
        }
        if (i == 0) {
            return 1;
        }
        return i2 > i ? 2 : 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static int checkAscii(java.io.InputStream r17, int r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 164
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.checkAscii(java.io.InputStream, int, boolean):int");
    }
}
