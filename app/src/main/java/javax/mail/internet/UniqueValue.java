package javax.mail.internet;

import javax.mail.Session;
import org.apache.commons.p105io.FilenameUtils;

/* loaded from: classes2.dex */
class UniqueValue {

    /* renamed from: id */
    private static int f14659id;

    UniqueValue() {
    }

    public static String getUniqueBoundaryValue() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("----=_Part_");
        stringBuffer.append(getUniqueId());
        stringBuffer.append("_");
        stringBuffer.append(stringBuffer.hashCode());
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(System.currentTimeMillis());
        return stringBuffer.toString();
    }

    public static String getUniqueMessageIDValue(Session session) {
        InternetAddress localAddress = InternetAddress.getLocalAddress(session);
        String address = localAddress != null ? localAddress.getAddress() : "javamailuser@localhost";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringBuffer.hashCode());
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(getUniqueId());
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append("JavaMail.");
        stringBuffer.append(address);
        return stringBuffer.toString();
    }

    private static synchronized int getUniqueId() {
        int i;
        synchronized (UniqueValue.class) {
            i = f14659id;
            f14659id = i + 1;
        }
        return i;
    }
}
