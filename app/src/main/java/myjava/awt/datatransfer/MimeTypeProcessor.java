package myjava.awt.datatransfer;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import p110z1.Typography;

/* loaded from: classes2.dex */
final class MimeTypeProcessor {
    private static MimeTypeProcessor instance;

    private static boolean isMeaningfulChar(char c) {
        return c >= '!' && c <= '~';
    }

    private static boolean isTSpecialChar(char c) {
        return c == '(' || c == ')' || c == '[' || c == ']' || c == '<' || c == '>' || c == '@' || c == ',' || c == ';' || c == ':' || c == '\\' || c == '\"' || c == '/' || c == '?' || c == '=';
    }

    private MimeTypeProcessor() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MimeType parse(String str) {
        if (instance == null) {
            instance = new MimeTypeProcessor();
        }
        MimeType mimeType = new MimeType();
        if (str != null) {
            StringPosition stringPosition = new StringPosition(null);
            retrieveType(str, mimeType, stringPosition);
            retrieveParams(str, mimeType, stringPosition);
        }
        return mimeType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String assemble(MimeType mimeType) {
        StringBuilder sb = new StringBuilder();
        sb.append(mimeType.getFullType());
        Enumeration keys = mimeType.parameters.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            sb.append("; ");
            sb.append(str);
            sb.append("=\"");
            sb.append((String) mimeType.parameters.get(str));
            sb.append(Typography.f21049a);
        }
        return sb.toString();
    }

    private static void retrieveType(String str, MimeType mimeType, StringPosition stringPosition) {
        mimeType.primaryType = retrieveToken(str, stringPosition).toLowerCase();
        stringPosition.f14702i = getNextMeaningfulIndex(str, stringPosition.f14702i);
        if (stringPosition.f14702i >= str.length() || str.charAt(stringPosition.f14702i) != '/') {
            throw new IllegalArgumentException();
        }
        stringPosition.f14702i++;
        mimeType.subType = retrieveToken(str, stringPosition).toLowerCase();
    }

    private static void retrieveParams(String str, MimeType mimeType, StringPosition stringPosition) {
        mimeType.parameters = new Hashtable();
        mimeType.systemParameters = new Hashtable();
        while (true) {
            stringPosition.f14702i = getNextMeaningfulIndex(str, stringPosition.f14702i);
            if (stringPosition.f14702i < str.length()) {
                if (str.charAt(stringPosition.f14702i) == ';') {
                    stringPosition.f14702i++;
                    retrieveParam(str, mimeType, stringPosition);
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                return;
            }
        }
    }

    private static void retrieveParam(String str, MimeType mimeType, StringPosition stringPosition) {
        String str2;
        String lowerCase = retrieveToken(str, stringPosition).toLowerCase();
        stringPosition.f14702i = getNextMeaningfulIndex(str, stringPosition.f14702i);
        if (stringPosition.f14702i >= str.length() || str.charAt(stringPosition.f14702i) != '=') {
            throw new IllegalArgumentException();
        }
        stringPosition.f14702i++;
        stringPosition.f14702i = getNextMeaningfulIndex(str, stringPosition.f14702i);
        if (stringPosition.f14702i < str.length()) {
            if (str.charAt(stringPosition.f14702i) == '\"') {
                str2 = retrieveQuoted(str, stringPosition);
            } else {
                str2 = retrieveToken(str, stringPosition);
            }
            mimeType.parameters.put(lowerCase, str2);
            return;
        }
        throw new IllegalArgumentException();
    }

    private static String retrieveQuoted(String str, StringPosition stringPosition) {
        StringBuilder sb = new StringBuilder();
        stringPosition.f14702i++;
        boolean z = true;
        do {
            if (str.charAt(stringPosition.f14702i) != '\"' || !z) {
                int i = stringPosition.f14702i;
                stringPosition.f14702i = i + 1;
                char charAt = str.charAt(i);
                if (!z) {
                    z = true;
                } else if (charAt == '\\') {
                    z = false;
                }
                if (z) {
                    sb.append(charAt);
                }
            } else {
                stringPosition.f14702i++;
                return sb.toString();
            }
        } while (stringPosition.f14702i != str.length());
        throw new IllegalArgumentException();
    }

    private static String retrieveToken(String str, StringPosition stringPosition) {
        StringBuilder sb = new StringBuilder();
        stringPosition.f14702i = getNextMeaningfulIndex(str, stringPosition.f14702i);
        if (stringPosition.f14702i >= str.length() || isTSpecialChar(str.charAt(stringPosition.f14702i))) {
            throw new IllegalArgumentException();
        }
        do {
            int i = stringPosition.f14702i;
            stringPosition.f14702i = i + 1;
            sb.append(str.charAt(i));
            if (stringPosition.f14702i >= str.length() || !isMeaningfulChar(str.charAt(stringPosition.f14702i))) {
                break;
            }
        } while (!isTSpecialChar(str.charAt(stringPosition.f14702i)));
        return sb.toString();
    }

    private static int getNextMeaningfulIndex(String str, int i) {
        while (i < str.length() && !isMeaningfulChar(str.charAt(i))) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class StringPosition {

        /* renamed from: i */
        int f14702i;

        private StringPosition() {
            this.f14702i = 0;
        }

        /* synthetic */ StringPosition(StringPosition stringPosition) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class MimeType implements Serializable, Cloneable {
        private static final long serialVersionUID = -6693571907475992044L;
        private Hashtable<String, String> parameters;
        private String primaryType;
        private String subType;
        private Hashtable<String, Object> systemParameters;

        MimeType() {
            this.primaryType = null;
            this.subType = null;
            this.parameters = null;
            this.systemParameters = null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public MimeType(String str, String str2) {
            this.primaryType = str;
            this.subType = str2;
            this.parameters = new Hashtable<>();
            this.systemParameters = new Hashtable<>();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean equals(MimeType mimeType) {
            if (mimeType == null) {
                return false;
            }
            return getFullType().equals(mimeType.getFullType());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getPrimaryType() {
            return this.primaryType;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getSubType() {
            return this.subType;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getFullType() {
            return String.valueOf(this.primaryType) + "/" + this.subType;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getParameter(String str) {
            return this.parameters.get(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void addParameter(String str, String str2) {
            if (str2 != null) {
                if (str2.charAt(0) == '\"' && str2.charAt(str2.length() - 1) == '\"') {
                    str2 = str2.substring(1, str2.length() - 2);
                }
                if (str2.length() != 0) {
                    this.parameters.put(str, str2);
                }
            }
        }

        final void removeParameter(String str) {
            this.parameters.remove(str);
        }

        final Object getSystemParameter(String str) {
            return this.systemParameters.get(str);
        }

        final void addSystemParameter(String str, Object obj) {
            this.systemParameters.put(str, obj);
        }

        public final Object clone() {
            MimeType mimeType = new MimeType(this.primaryType, this.subType);
            mimeType.parameters = (Hashtable) this.parameters.clone();
            mimeType.systemParameters = (Hashtable) this.systemParameters.clone();
            return mimeType;
        }
    }
}
