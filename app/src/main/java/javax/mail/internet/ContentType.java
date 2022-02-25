package javax.mail.internet;

import javax.mail.internet.HeaderTokenizer;
import org.apache.commons.p105io.IOUtils;

/* loaded from: classes2.dex */
public class ContentType {
    private ParameterList list;
    private String primaryType;
    private String subType;

    public ContentType() {
    }

    public ContentType(String str, String str2, ParameterList parameterList) {
        this.primaryType = str;
        this.subType = str2;
        this.list = parameterList;
    }

    public ContentType(String str) throws ParseException {
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(str, HeaderTokenizer.MIME);
        HeaderTokenizer.Token next = headerTokenizer.next();
        if (next.getType() == -1) {
            this.primaryType = next.getValue();
            if (((char) headerTokenizer.next().getType()) == '/') {
                HeaderTokenizer.Token next2 = headerTokenizer.next();
                if (next2.getType() == -1) {
                    this.subType = next2.getValue();
                    String remainder = headerTokenizer.getRemainder();
                    if (remainder != null) {
                        this.list = new ParameterList(remainder);
                        return;
                    }
                    return;
                }
                throw new ParseException();
            }
            throw new ParseException();
        }
        throw new ParseException();
    }

    public String getPrimaryType() {
        return this.primaryType;
    }

    public String getSubType() {
        return this.subType;
    }

    public String getBaseType() {
        return String.valueOf(this.primaryType) + IOUtils.DIR_SEPARATOR_UNIX + this.subType;
    }

    public String getParameter(String str) {
        ParameterList parameterList = this.list;
        if (parameterList == null) {
            return null;
        }
        return parameterList.get(str);
    }

    public ParameterList getParameterList() {
        return this.list;
    }

    public void setPrimaryType(String str) {
        this.primaryType = str;
    }

    public void setSubType(String str) {
        this.subType = str;
    }

    public void setParameter(String str, String str2) {
        if (this.list == null) {
            this.list = new ParameterList();
        }
        this.list.set(str, str2);
    }

    public void setParameterList(ParameterList parameterList) {
        this.list = parameterList;
    }

    public String toString() {
        if (this.primaryType == null || this.subType == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.primaryType);
        stringBuffer.append(IOUtils.DIR_SEPARATOR_UNIX);
        stringBuffer.append(this.subType);
        ParameterList parameterList = this.list;
        if (parameterList != null) {
            stringBuffer.append(parameterList.toString(stringBuffer.length() + 14));
        }
        return stringBuffer.toString();
    }

    public boolean match(ContentType contentType) {
        if (!this.primaryType.equalsIgnoreCase(contentType.getPrimaryType())) {
            return false;
        }
        String subType = contentType.getSubType();
        return this.subType.charAt(0) == '*' || subType.charAt(0) == '*' || this.subType.equalsIgnoreCase(subType);
    }

    public boolean match(String str) {
        try {
            return match(new ContentType(str));
        } catch (ParseException unused) {
            return false;
        }
    }
}
