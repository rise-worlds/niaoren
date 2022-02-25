package javax.mail.internet;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class ParameterList {
    private static boolean applehack = false;
    private static boolean decodeParameters = false;
    private static boolean decodeParametersStrict = false;
    private static boolean encodeParameters = false;
    private static final char[] hex;
    private String lastName;
    private Map list;
    private Set multisegmentNames;
    private Map slist;

    static {
        try {
            String property = System.getProperty("mail.mime.encodeparameters");
            boolean z = true;
            encodeParameters = property != null && property.equalsIgnoreCase("true");
            String property2 = System.getProperty("mail.mime.decodeparameters");
            decodeParameters = property2 != null && property2.equalsIgnoreCase("true");
            String property3 = System.getProperty("mail.mime.decodeparameters.strict");
            decodeParametersStrict = property3 != null && property3.equalsIgnoreCase("true");
            String property4 = System.getProperty("mail.mime.applefilenames");
            if (property4 == null || !property4.equalsIgnoreCase("true")) {
                z = false;
            }
            applehack = z;
        } catch (SecurityException unused) {
        }
        hex = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Value {
        String charset;
        String encodedValue;
        String value;

        private Value() {
        }

        /* synthetic */ Value(Value value) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MultiValue extends ArrayList {
        String value;

        private MultiValue() {
        }

        /* synthetic */ MultiValue(MultiValue multiValue) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    private static class ParamEnum implements Enumeration {

        /* renamed from: it */
        private Iterator f14657it;

        ParamEnum(Iterator it) {
            this.f14657it = it;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f14657it.hasNext();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return this.f14657it.next();
        }
    }

    public ParameterList() {
        this.list = new LinkedHashMap();
        this.lastName = null;
        if (decodeParameters) {
            this.multisegmentNames = new HashSet();
            this.slist = new HashMap();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0122, code lost:
        if (javax.mail.internet.ParameterList.decodeParameters == false) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0124, code lost:
        combineMultisegmentNames(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0128, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ParameterList(java.lang.String r7) throws javax.mail.internet.ParseException {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.ParameterList.<init>(java.lang.String):void");
    }

    private void putEncodedName(String str, String str2) throws ParseException {
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            this.list.put(str, str2);
        } else if (indexOf == str.length() - 1) {
            this.list.put(str.substring(0, indexOf), decodeValue(str2));
        } else {
            String substring = str.substring(0, indexOf);
            this.multisegmentNames.add(substring);
            this.list.put(substring, "");
            if (str.endsWith(Marker.ANY_MARKER)) {
                Value value = new Value(null);
                Value value2 = value;
                value2.encodedValue = str2;
                value2.value = str2;
                str = str.substring(0, str.length() - 1);
                str2 = value;
            }
            this.slist.put(str, str2);
        }
    }

    private void combineMultisegmentNames(boolean z) throws ParseException {
        String str;
        NumberFormatException e;
        UnsupportedEncodingException e2;
        StringIndexOutOfBoundsException e3;
        Value decodeValue;
        String str2;
        try {
            for (String str3 : this.multisegmentNames) {
                StringBuffer stringBuffer = new StringBuffer();
                MultiValue multiValue = new MultiValue(null);
                int i = 0;
                String str4 = null;
                while (true) {
                    String str5 = String.valueOf(str3) + Marker.ANY_MARKER + i;
                    Object obj = this.slist.get(str5);
                    if (obj == null) {
                        break;
                    }
                    multiValue.add(obj);
                    if (obj instanceof Value) {
                        try {
                            Value value = (Value) obj;
                            str = value.encodedValue;
                            if (i == 0) {
                                try {
                                    decodeValue = decodeValue(str);
                                    str2 = decodeValue.charset;
                                    value.charset = str2;
                                } catch (UnsupportedEncodingException e4) {
                                    e2 = e4;
                                } catch (NumberFormatException e5) {
                                    e = e5;
                                } catch (StringIndexOutOfBoundsException e6) {
                                    e3 = e6;
                                }
                                try {
                                    String str6 = decodeValue.value;
                                    value.value = str6;
                                    str = str6;
                                    str4 = str2;
                                } catch (UnsupportedEncodingException e7) {
                                    e2 = e7;
                                    str4 = str2;
                                    if (!decodeParametersStrict) {
                                        stringBuffer.append(str);
                                        this.slist.remove(str5);
                                        i++;
                                    } else {
                                        throw new ParseException(e2.toString());
                                    }
                                } catch (NumberFormatException e8) {
                                    e = e8;
                                    str4 = str2;
                                    if (!decodeParametersStrict) {
                                        stringBuffer.append(str);
                                        this.slist.remove(str5);
                                        i++;
                                    } else {
                                        throw new ParseException(e.toString());
                                    }
                                } catch (StringIndexOutOfBoundsException e9) {
                                    e3 = e9;
                                    str4 = str2;
                                    if (!decodeParametersStrict) {
                                        stringBuffer.append(str);
                                        this.slist.remove(str5);
                                        i++;
                                    } else {
                                        throw new ParseException(e3.toString());
                                    }
                                }
                            } else if (str4 == null) {
                                this.multisegmentNames.remove(str3);
                                break;
                            } else {
                                String decodeBytes = decodeBytes(str, str4);
                                value.value = decodeBytes;
                                str = decodeBytes;
                            }
                        } catch (UnsupportedEncodingException e10) {
                            e2 = e10;
                            str = null;
                        } catch (NumberFormatException e11) {
                            e = e11;
                            str = null;
                        } catch (StringIndexOutOfBoundsException e12) {
                            e3 = e12;
                            str = null;
                        }
                    } else {
                        str = (String) obj;
                    }
                    stringBuffer.append(str);
                    this.slist.remove(str5);
                    i++;
                }
                if (i == 0) {
                    this.list.remove(str3);
                } else {
                    multiValue.value = stringBuffer.toString();
                    this.list.put(str3, multiValue);
                }
            }
            if (this.slist.size() > 0) {
                for (Object obj2 : this.slist.values()) {
                    if (obj2 instanceof Value) {
                        Value value2 = (Value) obj2;
                        Value decodeValue2 = decodeValue(value2.encodedValue);
                        value2.charset = decodeValue2.charset;
                        value2.value = decodeValue2.value;
                    }
                }
                this.list.putAll(this.slist);
            }
            this.multisegmentNames.clear();
            this.slist.clear();
        } catch (Throwable th) {
            if (z) {
                if (this.slist.size() > 0) {
                    for (Object obj3 : this.slist.values()) {
                        if (obj3 instanceof Value) {
                            Value value3 = (Value) obj3;
                            Value decodeValue3 = decodeValue(value3.encodedValue);
                            value3.charset = decodeValue3.charset;
                            value3.value = decodeValue3.value;
                        }
                    }
                    this.list.putAll(this.slist);
                }
                this.multisegmentNames.clear();
                this.slist.clear();
            }
            throw th;
        }
    }

    public int size() {
        return this.list.size();
    }

    public String get(String str) {
        Object obj = this.list.get(str.trim().toLowerCase(Locale.ENGLISH));
        if (obj instanceof MultiValue) {
            return ((MultiValue) obj).value;
        }
        if (obj instanceof Value) {
            return ((Value) obj).value;
        }
        return (String) obj;
    }

    public void set(String str, String str2) {
        if (str != null || str2 == null || !str2.equals("DONE")) {
            String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
            if (decodeParameters) {
                try {
                    putEncodedName(lowerCase, str2);
                } catch (ParseException unused) {
                    this.list.put(lowerCase, str2);
                }
            } else {
                this.list.put(lowerCase, str2);
            }
        } else if (decodeParameters && this.multisegmentNames.size() > 0) {
            try {
                combineMultisegmentNames(true);
            } catch (ParseException unused2) {
            }
        }
    }

    public void set(String str, String str2, String str3) {
        if (encodeParameters) {
            Value encodeValue = encodeValue(str2, str3);
            if (encodeValue != null) {
                this.list.put(str.trim().toLowerCase(Locale.ENGLISH), encodeValue);
            } else {
                set(str, str2);
            }
        } else {
            set(str, str2);
        }
    }

    public void remove(String str) {
        this.list.remove(str.trim().toLowerCase(Locale.ENGLISH));
    }

    public Enumeration getNames() {
        return new ParamEnum(this.list.keySet().iterator());
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int i) {
        ToStringBuffer toStringBuffer = new ToStringBuffer(i);
        for (String str : this.list.keySet()) {
            Object obj = this.list.get(str);
            if (obj instanceof MultiValue) {
                MultiValue multiValue = (MultiValue) obj;
                String str2 = String.valueOf(str) + Marker.ANY_MARKER;
                for (int i2 = 0; i2 < multiValue.size(); i2++) {
                    Object obj2 = multiValue.get(i2);
                    if (obj2 instanceof Value) {
                        toStringBuffer.addNV(String.valueOf(str2) + i2 + Marker.ANY_MARKER, ((Value) obj2).encodedValue);
                    } else {
                        toStringBuffer.addNV(String.valueOf(str2) + i2, (String) obj2);
                    }
                }
            } else if (obj instanceof Value) {
                toStringBuffer.addNV(String.valueOf(str) + Marker.ANY_MARKER, ((Value) obj).encodedValue);
            } else {
                toStringBuffer.addNV(str, (String) obj);
            }
        }
        return toStringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ToStringBuffer {

        /* renamed from: sb */
        private StringBuffer f14658sb = new StringBuffer();
        private int used;

        public ToStringBuffer(int i) {
            this.used = i;
        }

        public void addNV(String str, String str2) {
            int lastIndexOf;
            String quote = ParameterList.quote(str2);
            this.f14658sb.append("; ");
            this.used += 2;
            if (this.used + str.length() + quote.length() + 1 > 76) {
                this.f14658sb.append("\r\n\t");
                this.used = 8;
            }
            StringBuffer stringBuffer = this.f14658sb;
            stringBuffer.append(str);
            stringBuffer.append('=');
            this.used += str.length() + 1;
            if (this.used + quote.length() > 76) {
                String fold = MimeUtility.fold(this.used, quote);
                this.f14658sb.append(fold);
                if (fold.lastIndexOf(10) >= 0) {
                    this.used += (fold.length() - lastIndexOf) - 1;
                } else {
                    this.used += fold.length();
                }
            } else {
                this.f14658sb.append(quote);
                this.used += quote.length();
            }
        }

        public String toString() {
            return this.f14658sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String quote(String str) {
        return MimeUtility.quote(str, HeaderTokenizer.MIME);
    }

    private static Value encodeValue(String str, String str2) {
        if (MimeUtility.checkAscii(str) == 1) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(MimeUtility.javaCharset(str2));
            StringBuffer stringBuffer = new StringBuffer(bytes.length + str2.length() + 2);
            stringBuffer.append(str2);
            stringBuffer.append("''");
            for (byte b : bytes) {
                char c = (char) (b & 255);
                if (c <= ' ' || c >= 127 || c == '*' || c == '\'' || c == '%' || HeaderTokenizer.MIME.indexOf(c) >= 0) {
                    stringBuffer.append('%');
                    stringBuffer.append(hex[c >> 4]);
                    stringBuffer.append(hex[c & 15]);
                } else {
                    stringBuffer.append(c);
                }
            }
            Value value = new Value(null);
            value.charset = str2;
            value.value = str;
            value.encodedValue = stringBuffer.toString();
            return value;
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    private static Value decodeValue(String str) throws ParseException {
        int indexOf;
        Value value = new Value(null);
        value.encodedValue = str;
        value.value = str;
        try {
            indexOf = str.indexOf(39);
        } catch (UnsupportedEncodingException e) {
            if (decodeParametersStrict) {
                throw new ParseException(e.toString());
            }
        } catch (NumberFormatException e2) {
            if (decodeParametersStrict) {
                throw new ParseException(e2.toString());
            }
        } catch (StringIndexOutOfBoundsException e3) {
            if (decodeParametersStrict) {
                throw new ParseException(e3.toString());
            }
        }
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(39, i);
            if (indexOf2 >= 0) {
                str.substring(i, indexOf2);
                String substring2 = str.substring(indexOf2 + 1);
                value.charset = substring;
                value.value = decodeBytes(substring2, substring);
                return value;
            } else if (!decodeParametersStrict) {
                return value;
            } else {
                throw new ParseException("Missing language in encoded value: " + str);
            }
        } else if (!decodeParametersStrict) {
            return value;
        } else {
            throw new ParseException("Missing charset in encoded value: " + str);
        }
    }

    private static String decodeBytes(String str, String str2) throws UnsupportedEncodingException {
        byte[] bArr = new byte[str.length()];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == '%') {
                charAt = (char) Integer.parseInt(str.substring(i + 1, i + 3), 16);
                i += 2;
            }
            i2++;
            bArr[i2] = (byte) charAt;
            i++;
        }
        return new String(bArr, 0, i2, MimeUtility.javaCharset(str2));
    }
}
