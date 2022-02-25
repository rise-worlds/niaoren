package org.apache.tools.ant.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class LayoutPreservingProperties extends Properties {
    private boolean removeComments;

    /* renamed from: LS */
    private String f14777LS = StringUtils.LINE_SEP;
    private ArrayList logicalLines = new ArrayList();
    private HashMap keyedPairLines = new HashMap();

    public LayoutPreservingProperties() {
    }

    public LayoutPreservingProperties(Properties properties) {
        super(properties);
    }

    public boolean isRemoveComments() {
        return this.removeComments;
    }

    public void setRemoveComments(boolean z) {
        this.removeComments = z;
    }

    @Override // java.util.Properties
    public void load(InputStream inputStream) throws IOException {
        super.load(new ByteArrayInputStream(readLines(inputStream).getBytes("ISO-8859-1")));
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public Object put(Object obj, Object obj2) throws NullPointerException {
        Object put = super.put(obj, obj2);
        innerSetProperty(obj.toString(), obj2.toString());
        return put;
    }

    @Override // java.util.Properties
    public Object setProperty(String str, String str2) throws NullPointerException {
        Object property = super.setProperty(str, str2);
        innerSetProperty(str, str2);
        return property;
    }

    private void innerSetProperty(String str, String str2) {
        String escapeValue = escapeValue(str2);
        if (this.keyedPairLines.containsKey(str)) {
            ((Pair) this.logicalLines.get(((Integer) this.keyedPairLines.get(str)).intValue())).setValue(escapeValue);
            return;
        }
        String escapeName = escapeName(str);
        Pair pair = new Pair(escapeName, escapeValue);
        pair.setNew(true);
        this.keyedPairLines.put(escapeName, new Integer(this.logicalLines.size()));
        this.logicalLines.add(pair);
    }

    @Override // java.util.Hashtable, java.util.Map
    public void clear() {
        super.clear();
        this.keyedPairLines.clear();
        this.logicalLines.clear();
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public Object remove(Object obj) {
        Object remove = super.remove(obj);
        Integer num = (Integer) this.keyedPairLines.remove(obj);
        if (num != null) {
            if (this.removeComments) {
                removeCommentsEndingAt(num.intValue());
            }
            this.logicalLines.set(num.intValue(), null);
        }
        return remove;
    }

    @Override // java.util.Hashtable
    public Object clone() {
        LayoutPreservingProperties layoutPreservingProperties = (LayoutPreservingProperties) super.clone();
        layoutPreservingProperties.keyedPairLines = (HashMap) this.keyedPairLines.clone();
        layoutPreservingProperties.logicalLines = (ArrayList) this.logicalLines.clone();
        int size = layoutPreservingProperties.logicalLines.size();
        for (int i = 0; i < size; i++) {
            LogicalLine logicalLine = (LogicalLine) layoutPreservingProperties.logicalLines.get(i);
            if (logicalLine instanceof Pair) {
                layoutPreservingProperties.logicalLines.set(i, ((Pair) logicalLine).clone());
            }
        }
        return layoutPreservingProperties;
    }

    public void listLines(PrintStream printStream) {
        printStream.println("-- logical lines --");
        Iterator it = this.logicalLines.iterator();
        while (it.hasNext()) {
            LogicalLine logicalLine = (LogicalLine) it.next();
            if (logicalLine instanceof Blank) {
                printStream.println("blank:   \"" + logicalLine + "\"");
            } else if (logicalLine instanceof Comment) {
                printStream.println("comment: \"" + logicalLine + "\"");
            } else if (logicalLine instanceof Pair) {
                printStream.println("pair:    \"" + logicalLine + "\"");
            }
        }
    }

    public void saveAs(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0099  */
    @Override // java.util.Properties
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void store(java.io.OutputStream r6, java.lang.String r7) throws java.io.IOException {
        /*
            r5 = this;
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter
            java.lang.String r1 = "ISO-8859-1"
            r0.<init>(r6, r1)
            java.util.ArrayList r6 = r5.logicalLines
            int r6 = r6.size()
            r1 = 0
            r2 = 1
            if (r7 == 0) goto L_0x004c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "#"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r4 = r5.f14777LS
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.write(r3)
            if (r6 <= 0) goto L_0x004c
            java.util.ArrayList r3 = r5.logicalLines
            java.lang.Object r3 = r3.get(r1)
            boolean r3 = r3 instanceof org.apache.tools.ant.util.LayoutPreservingProperties.Comment
            if (r3 == 0) goto L_0x004c
            java.util.ArrayList r3 = r5.logicalLines
            java.lang.Object r3 = r3.get(r1)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = r3.substring(r2)
            boolean r7 = r7.equals(r3)
            if (r7 == 0) goto L_0x004c
            r7 = 1
            goto L_0x004d
        L_0x004c:
            r7 = 0
        L_0x004d:
            if (r6 <= r7) goto L_0x006c
            java.util.ArrayList r3 = r5.logicalLines
            java.lang.Object r3 = r3.get(r7)
            boolean r3 = r3 instanceof org.apache.tools.ant.util.LayoutPreservingProperties.Comment
            if (r3 == 0) goto L_0x006c
            java.util.ArrayList r3 = r5.logicalLines     // Catch: ParseException -> 0x006c
            java.lang.Object r3 = r3.get(r7)     // Catch: ParseException -> 0x006c
            java.lang.String r3 = r3.toString()     // Catch: ParseException -> 0x006c
            java.lang.String r3 = r3.substring(r2)     // Catch: ParseException -> 0x006c
            org.apache.tools.ant.util.DateUtils.parseDateFromHeader(r3)     // Catch: ParseException -> 0x006c
            int r7 = r7 + 1
        L_0x006c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "#"
            r3.append(r4)
            java.lang.String r4 = org.apache.tools.ant.util.DateUtils.getDateForHeader()
            r3.append(r4)
            java.lang.String r4 = r5.f14777LS
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.write(r3)
            java.util.ArrayList r3 = r5.logicalLines
            java.util.List r6 = r3.subList(r7, r6)
            java.util.Iterator r6 = r6.iterator()
        L_0x0093:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00e8
            java.lang.Object r7 = r6.next()
            org.apache.tools.ant.util.LayoutPreservingProperties$LogicalLine r7 = (org.apache.tools.ant.util.LayoutPreservingProperties.LogicalLine) r7
            boolean r3 = r7 instanceof org.apache.tools.ant.util.LayoutPreservingProperties.Pair
            if (r3 == 0) goto L_0x00cd
            r3 = r7
            org.apache.tools.ant.util.LayoutPreservingProperties$Pair r3 = (org.apache.tools.ant.util.LayoutPreservingProperties.Pair) r3
            boolean r3 = r3.isNew()
            if (r3 == 0) goto L_0x00b4
            if (r1 != 0) goto L_0x00b4
            java.lang.String r1 = r5.f14777LS
            r0.write(r1)
            r1 = 1
        L_0x00b4:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r7 = r7.toString()
            r3.append(r7)
            java.lang.String r7 = r5.f14777LS
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r0.write(r7)
            goto L_0x0093
        L_0x00cd:
            if (r7 == 0) goto L_0x0093
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r7 = r7.toString()
            r3.append(r7)
            java.lang.String r7 = r5.f14777LS
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r0.write(r7)
            goto L_0x0093
        L_0x00e8:
            r0.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.util.LayoutPreservingProperties.store(java.io.OutputStream, java.lang.String):void");
    }

    private String readLines(InputStream inputStream) throws IOException {
        Object obj;
        PushbackReader pushbackReader = new PushbackReader(new InputStreamReader(inputStream, "ISO-8859-1"), 1);
        if (this.logicalLines.size() > 0) {
            this.logicalLines.add(new Blank());
        }
        String readFirstLine = readFirstLine(pushbackReader);
        BufferedReader bufferedReader = new BufferedReader(pushbackReader);
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        boolean z = false;
        boolean z2 = false;
        while (readFirstLine != null) {
            stringBuffer.append(readFirstLine);
            stringBuffer.append(this.f14777LS);
            if (z) {
                readFirstLine = "\n" + readFirstLine;
            } else {
                z2 = readFirstLine.matches("^( |\t|\f)*(#|!).*");
            }
            if (!z2) {
                z = requiresContinuation(readFirstLine);
            }
            stringBuffer2.append(readFirstLine);
            if (!z) {
                if (z2) {
                    obj = new Comment(stringBuffer2.toString());
                } else if (stringBuffer2.toString().trim().length() == 0) {
                    obj = new Blank();
                } else {
                    obj = new Pair(stringBuffer2.toString());
                    String unescape = unescape(((Pair) obj).getName());
                    if (this.keyedPairLines.containsKey(unescape)) {
                        remove(unescape);
                    }
                    this.keyedPairLines.put(unescape, new Integer(this.logicalLines.size()));
                }
                this.logicalLines.add(obj);
                stringBuffer2.setLength(0);
            }
            readFirstLine = bufferedReader.readLine();
        }
        return stringBuffer.toString();
    }

    private String readFirstLine(PushbackReader pushbackReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(80);
        int read = pushbackReader.read();
        this.f14777LS = StringUtils.LINE_SEP;
        boolean z = false;
        while (true) {
            if (read >= 0) {
                if (z && read != 10) {
                    pushbackReader.unread(read);
                    break;
                }
                if (read == 13) {
                    this.f14777LS = "\r";
                    z = true;
                } else if (read == 10) {
                    this.f14777LS = z ? "\r\n" : "\n";
                } else {
                    stringBuffer.append((char) read);
                }
                read = pushbackReader.read();
            } else {
                break;
            }
        }
        return stringBuffer.toString();
    }

    private boolean requiresContinuation(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length - 1;
        while (length > 0 && charArray[length] == '\\') {
            length--;
        }
        return ((charArray.length - length) - 1) % 2 == 1;
    }

    private String unescape(String str) {
        char c;
        char[] cArr = new char[str.length() + 1];
        int i = 0;
        str.getChars(0, str.length(), cArr, 0);
        cArr[str.length()] = '\n';
        StringBuffer stringBuffer = new StringBuffer(str.length());
        while (i < cArr.length && (c = cArr[i]) != '\n') {
            if (c == '\\') {
                i++;
                char c2 = cArr[i];
                if (c2 == 'n') {
                    stringBuffer.append('\n');
                } else if (c2 == 'r') {
                    stringBuffer.append('\r');
                } else if (c2 == 'f') {
                    stringBuffer.append('\f');
                } else if (c2 == 't') {
                    stringBuffer.append('\t');
                } else if (c2 == 'u') {
                    i += 4;
                    stringBuffer.append(unescapeUnicode(cArr, i + 1));
                } else {
                    stringBuffer.append(c2);
                }
            } else {
                stringBuffer.append(c);
            }
            i++;
        }
        return stringBuffer.toString();
    }

    private char unescapeUnicode(char[] cArr, int i) {
        return (char) Integer.parseInt(new String(cArr, i, 4), 16);
    }

    private String escapeValue(String str) {
        return escape(str, false);
    }

    private String escapeName(String str) {
        return escape(str, true);
    }

    private String escape(String str, boolean z) {
        if (str == null) {
            return null;
        }
        char[] cArr = new char[str.length()];
        str.getChars(0, str.length(), cArr, 0);
        StringBuffer stringBuffer = new StringBuffer(str.length());
        boolean z2 = true;
        for (char c : cArr) {
            if (c != ' ') {
                z2 = false;
            } else if (z || z2) {
                stringBuffer.append("\\");
            }
            int indexOf = "\t\f\r\n\\:=#!".indexOf(c);
            if (indexOf != -1) {
                stringBuffer.append("\\");
                stringBuffer.append("tfrn\\:=#!".substring(indexOf, indexOf + 1));
            } else if (c < ' ' || c > '~') {
                stringBuffer.append(escapeUnicode(c));
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }

    private String escapeUnicode(char c) {
        return "\\" + ((Object) UnicodeUtil.EscapeUnicode(c));
    }

    private void removeCommentsEndingAt(int i) {
        int i2 = i - 1;
        int i3 = i2;
        while (i3 > 0 && (this.logicalLines.get(i3) instanceof Blank)) {
            i3--;
        }
        if (this.logicalLines.get(i3) instanceof Comment) {
            while (i3 >= 0 && (this.logicalLines.get(i3) instanceof Comment)) {
                i3--;
            }
            while (true) {
                i3++;
                if (i3 <= i2) {
                    this.logicalLines.set(i3, null);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class LogicalLine {
        private String text;

        public LogicalLine(String str) {
            this.text = str;
        }

        public void setText(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Blank extends LogicalLine {
        public Blank() {
            super("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class Comment extends LogicalLine {
        public Comment(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Pair extends LogicalLine implements Cloneable {
        private boolean added;
        private String name;
        private String value;

        public Pair(String str) {
            super(str);
            parsePair(str);
        }

        public Pair(String str, String str2) {
            this(str + SimpleComparison.f23609c + str2);
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
            setText(this.name + SimpleComparison.f23609c + str);
        }

        public boolean isNew() {
            return this.added;
        }

        public void setNew(boolean z) {
            this.added = z;
        }

        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void parsePair(String str) {
            int findFirstSeparator = findFirstSeparator(str);
            if (findFirstSeparator == -1) {
                this.name = str;
                this.value = null;
            } else {
                this.name = str.substring(0, findFirstSeparator);
                this.value = str.substring(findFirstSeparator + 1, str.length());
            }
            this.name = stripStart(this.name, " \t\f");
        }

        private String stripStart(String str, String str2) {
            if (str == null) {
                return null;
            }
            int i = 0;
            while (i < str.length() && str2.indexOf(str.charAt(i)) != -1) {
                i++;
            }
            return i == str.length() ? "" : str.substring(i);
        }

        private int findFirstSeparator(String str) {
            return indexOfAny(str.replaceAll("\\\\\\\\", "__").replaceAll("\\\\=", "__").replaceAll("\\\\:", "__").replaceAll("\\\\ ", "__").replaceAll("\\\\t", "__"), " :=\t");
        }

        private int indexOfAny(String str, String str2) {
            if (str == null || str2 == null) {
                return -1;
            }
            int length = str.length() + 1;
            for (int i = 0; i < str2.length(); i++) {
                int indexOf = str.indexOf(str2.charAt(i));
                if (indexOf != -1 && indexOf < length) {
                    length = indexOf;
                }
            }
            if (length == str.length() + 1) {
                return -1;
            }
            return length;
        }
    }
}
