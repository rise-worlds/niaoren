package javax.mail.internet;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import javax.mail.Address;
import javax.mail.Session;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.p105io.IOUtils;
import p110z1.C4963cj;
import p110z1.SimpleComparison;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class InternetAddress extends Address implements Cloneable {
    private static final String rfc822phrase = HeaderTokenizer.RFC822.replace(' ', (char) 0).replace('\t', (char) 0);
    private static final long serialVersionUID = -7507595530758302903L;
    private static final String specialsNoDot = "()<>,;:\\\"[]@";
    private static final String specialsNoDotNoAt = "()<>,;:\\\"[]";
    protected String address;
    protected String encodedPersonal;
    protected String personal;

    @Override // javax.mail.Address
    public String getType() {
        return "rfc822";
    }

    public InternetAddress() {
    }

    public InternetAddress(String str) throws AddressException {
        InternetAddress[] parse = parse(str, true);
        if (parse.length == 1) {
            this.address = parse[0].address;
            this.personal = parse[0].personal;
            this.encodedPersonal = parse[0].encodedPersonal;
            return;
        }
        throw new AddressException("Illegal address", str);
    }

    public InternetAddress(String str, boolean z) throws AddressException {
        this(str);
        if (z) {
            checkAddress(this.address, true, true);
        }
    }

    public InternetAddress(String str, String str2) throws UnsupportedEncodingException {
        this(str, str2, null);
    }

    public InternetAddress(String str, String str2, String str3) throws UnsupportedEncodingException {
        this.address = str;
        setPersonal(str2, str3);
    }

    public Object clone() {
        try {
            return (InternetAddress) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setPersonal(String str, String str2) throws UnsupportedEncodingException {
        this.personal = str;
        if (str != null) {
            this.encodedPersonal = MimeUtility.encodeWord(str, str2, null);
        } else {
            this.encodedPersonal = null;
        }
    }

    public void setPersonal(String str) throws UnsupportedEncodingException {
        this.personal = str;
        if (str != null) {
            this.encodedPersonal = MimeUtility.encodeWord(str);
        } else {
            this.encodedPersonal = null;
        }
    }

    public String getAddress() {
        return this.address;
    }

    public String getPersonal() {
        String str = this.personal;
        if (str != null) {
            return str;
        }
        String str2 = this.encodedPersonal;
        if (str2 == null) {
            return null;
        }
        try {
            this.personal = MimeUtility.decodeText(str2);
            return this.personal;
        } catch (Exception unused) {
            return this.encodedPersonal;
        }
    }

    @Override // javax.mail.Address
    public String toString() {
        String str;
        if (this.encodedPersonal == null && (str = this.personal) != null) {
            try {
                this.encodedPersonal = MimeUtility.encodeWord(str);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        String str2 = this.encodedPersonal;
        if (str2 != null) {
            return String.valueOf(quotePhrase(str2)) + " <" + this.address + SimpleComparison.f23610d;
        } else if (isGroup() || isSimple()) {
            return this.address;
        } else {
            return SimpleComparison.f23612f + this.address + SimpleComparison.f23610d;
        }
    }

    public String toUnicodeString() {
        String personal = getPersonal();
        if (personal != null) {
            return String.valueOf(quotePhrase(personal)) + " <" + this.address + SimpleComparison.f23610d;
        } else if (isGroup() || isSimple()) {
            return this.address;
        } else {
            return SimpleComparison.f23612f + this.address + SimpleComparison.f23610d;
        }
    }

    private static String quotePhrase(String str) {
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"' || charAt == '\\') {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append(Typography.f21049a);
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '\"' || charAt2 == '\\') {
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    }
                    stringBuffer.append(charAt2);
                }
                stringBuffer.append(Typography.f21049a);
                return stringBuffer.toString();
            }
            if (!(charAt >= ' ' || charAt == '\r' || charAt == '\n' || charAt == '\t') || charAt >= 127 || rfc822phrase.indexOf(charAt) >= 0) {
                z = true;
            }
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

    private static String unquote(String str) {
        if (!str.startsWith("\"") || !str.endsWith("\"")) {
            return str;
        }
        String substring = str.substring(1, str.length() - 1);
        if (substring.indexOf(92) < 0) {
            return substring;
        }
        StringBuffer stringBuffer = new StringBuffer(substring.length());
        int i = 0;
        while (i < substring.length()) {
            char charAt = substring.charAt(i);
            if (charAt == '\\' && i < substring.length() - 1) {
                i++;
                charAt = substring.charAt(i);
            }
            stringBuffer.append(charAt);
            i++;
        }
        return stringBuffer.toString();
    }

    @Override // javax.mail.Address
    public boolean equals(Object obj) {
        if (!(obj instanceof InternetAddress)) {
            return false;
        }
        String address = ((InternetAddress) obj).getAddress();
        String str = this.address;
        if (address == str) {
            return true;
        }
        return str != null && str.equalsIgnoreCase(address);
    }

    public int hashCode() {
        String str = this.address;
        if (str == null) {
            return 0;
        }
        return str.toLowerCase(Locale.ENGLISH).hashCode();
    }

    public static String toString(Address[] addressArr) {
        return toString(addressArr, 0);
    }

    public static String toString(Address[] addressArr, int i) {
        if (addressArr == null || addressArr.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < addressArr.length; i2++) {
            if (i2 != 0) {
                stringBuffer.append(", ");
                i += 2;
            }
            String address = addressArr[i2].toString();
            if (lengthOfFirstSegment(address) + i > 76) {
                stringBuffer.append("\r\n\t");
                i = 8;
            }
            stringBuffer.append(address);
            i = lengthOfLastSegment(address, i);
        }
        return stringBuffer.toString();
    }

    private static int lengthOfFirstSegment(String str) {
        int indexOf = str.indexOf("\r\n");
        return indexOf != -1 ? indexOf : str.length();
    }

    private static int lengthOfLastSegment(String str, int i) {
        int lastIndexOf = str.lastIndexOf("\r\n");
        if (lastIndexOf != -1) {
            return (str.length() - lastIndexOf) - 2;
        }
        return str.length() + i;
    }

    public static InternetAddress getLocalAddress(Session session) {
        String str;
        String str2;
        String str3;
        InetAddress localHost;
        try {
            if (session == null) {
                String property = System.getProperty("user.name");
                str = InetAddress.getLocalHost().getHostName();
                str2 = property;
                str3 = null;
            } else {
                str3 = session.getProperty(EmailConstants.MAIL_FROM);
                if (str3 == null) {
                    str2 = session.getProperty("mail.user");
                    if (str2 == null || str2.length() == 0) {
                        str2 = session.getProperty("user.name");
                    }
                    if (str2 == null || str2.length() == 0) {
                        str2 = System.getProperty("user.name");
                    }
                    str = session.getProperty("mail.host");
                    if ((str == null || str.length() == 0) && (localHost = InetAddress.getLocalHost()) != null) {
                        str = localHost.getHostName();
                    }
                } else {
                    str = null;
                    str2 = null;
                }
            }
            if (!(str3 != null || str2 == null || str2.length() == 0 || str == null || str.length() == 0)) {
                str3 = String.valueOf(str2) + "@" + str;
            }
            if (str3 != null) {
                return new InternetAddress(str3);
            }
        } catch (SecurityException | UnknownHostException | AddressException unused) {
        }
        return null;
    }

    public static InternetAddress[] parse(String str) throws AddressException {
        return parse(str, true);
    }

    public static InternetAddress[] parse(String str, boolean z) throws AddressException {
        return parse(str, z, false);
    }

    public static InternetAddress[] parseHeader(String str, boolean z) throws AddressException {
        return parse(str, z, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01f9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static javax.mail.internet.InternetAddress[] parse(java.lang.String r17, boolean r18, boolean r19) throws javax.mail.internet.AddressException {
        /*
            Method dump skipped, instructions count: 588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.InternetAddress.parse(java.lang.String, boolean, boolean):javax.mail.internet.InternetAddress[]");
    }

    public void validate() throws AddressException {
        checkAddress(getAddress(), true, true);
    }

    private static void checkAddress(String str, boolean z, boolean z2) throws AddressException {
        String str2;
        String str3;
        if (str.indexOf(34) < 0) {
            int i = 0;
            if (z) {
                while (true) {
                    int indexOfAny = indexOfAny(str, ",:", i);
                    if (indexOfAny < 0) {
                        break;
                    } else if (str.charAt(i) != '@') {
                        throw new AddressException("Illegal route-addr", str);
                    } else if (str.charAt(indexOfAny) == ':') {
                        i = indexOfAny + 1;
                        break;
                    } else {
                        i = indexOfAny + 1;
                    }
                }
            }
            int indexOf = str.indexOf(64, i);
            if (indexOf >= 0) {
                if (indexOf == i) {
                    throw new AddressException("Missing local name", str);
                } else if (indexOf != str.length() - 1) {
                    str2 = str.substring(i, indexOf);
                    str3 = str.substring(indexOf + 1);
                } else {
                    throw new AddressException("Missing domain", str);
                }
            } else if (!z2) {
                str3 = null;
                str2 = str;
            } else {
                throw new AddressException("Missing final '@domain'", str);
            }
            if (indexOfAny(str, " \t\n\r") >= 0) {
                throw new AddressException("Illegal whitespace in address", str);
            } else if (indexOfAny(str2, specialsNoDot) >= 0) {
                throw new AddressException("Illegal character in local name", str);
            } else if (str3 != null && str3.indexOf(91) < 0 && indexOfAny(str3, specialsNoDot) >= 0) {
                throw new AddressException("Illegal character in domain", str);
            }
        }
    }

    private boolean isSimple() {
        String str = this.address;
        return str == null || indexOfAny(str, specialsNoDotNoAt) < 0;
    }

    public boolean isGroup() {
        String str = this.address;
        return str != null && str.endsWith(C4963cj.f20745b) && this.address.indexOf(58) > 0;
    }

    public InternetAddress[] getGroup(boolean z) throws AddressException {
        int indexOf;
        String address = getAddress();
        if (address.endsWith(C4963cj.f20745b) && (indexOf = address.indexOf(58)) >= 0) {
            return parseHeader(address.substring(indexOf + 1, address.length() - 1), z);
        }
        return null;
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
}
