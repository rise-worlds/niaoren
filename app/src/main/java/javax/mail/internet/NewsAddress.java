package javax.mail.internet;

import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.Address;

/* loaded from: classes2.dex */
public class NewsAddress extends Address {
    private static final long serialVersionUID = -4203797299824684143L;
    protected String host;
    protected String newsgroup;

    @Override // javax.mail.Address
    public String getType() {
        return "news";
    }

    public NewsAddress() {
    }

    public NewsAddress(String str) {
        this(str, null);
    }

    public NewsAddress(String str, String str2) {
        this.newsgroup = str;
        this.host = str2;
    }

    public void setNewsgroup(String str) {
        this.newsgroup = str;
    }

    public String getNewsgroup() {
        return this.newsgroup;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getHost() {
        return this.host;
    }

    @Override // javax.mail.Address
    public String toString() {
        return this.newsgroup;
    }

    @Override // javax.mail.Address
    public boolean equals(Object obj) {
        String str;
        if (!(obj instanceof NewsAddress)) {
            return false;
        }
        NewsAddress newsAddress = (NewsAddress) obj;
        if (this.newsgroup.equals(newsAddress.newsgroup)) {
            if (this.host == null && newsAddress.host == null) {
                return true;
            }
            String str2 = this.host;
            if (str2 != null && (str = newsAddress.host) != null && str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.newsgroup;
        int i = 0;
        if (str != null) {
            i = 0 + str.hashCode();
        }
        String str2 = this.host;
        return str2 != null ? i + str2.toLowerCase(Locale.ENGLISH).hashCode() : i;
    }

    public static String toString(Address[] addressArr) {
        if (addressArr == null || addressArr.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(((NewsAddress) addressArr[0]).toString());
        for (int i = 1; i < addressArr.length; i++) {
            stringBuffer.append(",");
            stringBuffer.append(((NewsAddress) addressArr[i]).toString());
        }
        return stringBuffer.toString();
    }

    public static NewsAddress[] parse(String str) throws AddressException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        Vector vector = new Vector();
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(new NewsAddress(stringTokenizer.nextToken()));
        }
        int size = vector.size();
        NewsAddress[] newsAddressArr = new NewsAddress[size];
        if (size > 0) {
            vector.copyInto(newsAddressArr);
        }
        return newsAddressArr;
    }
}
