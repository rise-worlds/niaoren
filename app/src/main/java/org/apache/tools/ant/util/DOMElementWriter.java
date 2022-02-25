package org.apache.tools.ant.util;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import p110z1.C4963cj;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class DOMElementWriter {
    private static final int HEX = 16;

    /* renamed from: NS */
    private static final String f14776NS = "ns";
    private static final String[] WS_ENTITIES = new String[5];
    private static String lSep;
    protected String[] knownEntities;
    private XmlNamespacePolicy namespacePolicy;
    private int nextPrefix;
    private HashMap nsPrefixMap;
    private HashMap nsURIByElement;
    private boolean xmlDeclaration;

    public boolean isLegalCharacter(char c) {
        if (c == '\t' || c == '\n' || c == '\r') {
            return true;
        }
        if (c < ' ') {
            return false;
        }
        if (c <= 55295) {
            return true;
        }
        return c >= 57344 && c <= 65533;
    }

    static {
        for (int i = 9; i < 14; i++) {
            WS_ENTITIES[i - 9] = "&#x" + Integer.toHexString(i) + C4963cj.f20745b;
        }
        lSep = System.getProperty("line.separator");
    }

    /* loaded from: classes2.dex */
    public static class XmlNamespacePolicy {
        public static final XmlNamespacePolicy IGNORE = new XmlNamespacePolicy(false, false);
        public static final XmlNamespacePolicy ONLY_QUALIFY_ELEMENTS = new XmlNamespacePolicy(true, false);
        public static final XmlNamespacePolicy QUALIFY_ALL = new XmlNamespacePolicy(true, true);
        private boolean qualifyAttributes;
        private boolean qualifyElements;

        public XmlNamespacePolicy(boolean z, boolean z2) {
            this.qualifyElements = z;
            this.qualifyAttributes = z2;
        }
    }

    public DOMElementWriter() {
        this.xmlDeclaration = true;
        this.namespacePolicy = XmlNamespacePolicy.IGNORE;
        this.nsPrefixMap = new HashMap();
        this.nextPrefix = 0;
        this.nsURIByElement = new HashMap();
        this.knownEntities = new String[]{"gt", "amp", "lt", "apos", "quot"};
    }

    public DOMElementWriter(boolean z) {
        this(z, XmlNamespacePolicy.IGNORE);
    }

    public DOMElementWriter(boolean z, XmlNamespacePolicy xmlNamespacePolicy) {
        this.xmlDeclaration = true;
        this.namespacePolicy = XmlNamespacePolicy.IGNORE;
        this.nsPrefixMap = new HashMap();
        this.nextPrefix = 0;
        this.nsURIByElement = new HashMap();
        this.knownEntities = new String[]{"gt", "amp", "lt", "apos", "quot"};
        this.xmlDeclaration = z;
        this.namespacePolicy = xmlNamespacePolicy;
    }

    public void write(Element element, OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF8");
        writeXMLDeclaration(outputStreamWriter);
        write(element, outputStreamWriter, 0, "  ");
        outputStreamWriter.flush();
    }

    public void writeXMLDeclaration(Writer writer) throws IOException {
        if (this.xmlDeclaration) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        }
    }

    public void write(Element element, Writer writer, int i, String str) throws IOException {
        NodeList childNodes = element.getChildNodes();
        boolean z = childNodes.getLength() > 0;
        openElement(element, writer, i, str, z);
        if (z) {
            boolean z2 = false;
            for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                Node item = childNodes.item(i2);
                switch (item.getNodeType()) {
                    case 1:
                        if (i2 == 0) {
                            writer.write(lSep);
                        }
                        write((Element) item, writer, i + 1, str);
                        z2 = true;
                        break;
                    case 3:
                        writer.write(encode(item.getNodeValue()));
                        break;
                    case 4:
                        writer.write("<![CDATA[");
                        encodedata(writer, ((Text) item).getData());
                        writer.write("]]>");
                        break;
                    case 5:
                        writer.write(38);
                        writer.write(item.getNodeName());
                        writer.write(59);
                        break;
                    case 7:
                        writer.write("<?");
                        writer.write(item.getNodeName());
                        String nodeValue = item.getNodeValue();
                        if (nodeValue != null && nodeValue.length() > 0) {
                            writer.write(32);
                            writer.write(nodeValue);
                        }
                        writer.write("?>");
                        break;
                    case 8:
                        writer.write("<!--");
                        writer.write(encode(item.getNodeValue()));
                        writer.write("-->");
                        break;
                }
            }
            closeElement(element, writer, i, str, z2);
        }
    }

    public void openElement(Element element, Writer writer, int i, String str) throws IOException {
        openElement(element, writer, i, str, true);
    }

    public void openElement(Element element, Writer writer, int i, String str, boolean z) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(str);
        }
        writer.write(SimpleComparison.f23612f);
        if (this.namespacePolicy.qualifyElements) {
            String namespaceURI = getNamespaceURI(element);
            String str2 = (String) this.nsPrefixMap.get(namespaceURI);
            if (str2 == null) {
                if (this.nsPrefixMap.isEmpty()) {
                    str2 = "";
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(f14776NS);
                    int i3 = this.nextPrefix;
                    this.nextPrefix = i3 + 1;
                    sb.append(i3);
                    str2 = sb.toString();
                }
                this.nsPrefixMap.put(namespaceURI, str2);
                addNSDefinition(element, namespaceURI);
            }
            if (!"".equals(str2)) {
                writer.write(str2);
                writer.write(":");
            }
        }
        writer.write(element.getTagName());
        NamedNodeMap attributes = element.getAttributes();
        for (int i4 = 0; i4 < attributes.getLength(); i4++) {
            Attr attr = (Attr) attributes.item(i4);
            writer.write(ExpandableTextView.f6958c);
            if (this.namespacePolicy.qualifyAttributes) {
                String namespaceURI2 = getNamespaceURI(attr);
                String str3 = (String) this.nsPrefixMap.get(namespaceURI2);
                if (str3 == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(f14776NS);
                    int i5 = this.nextPrefix;
                    this.nextPrefix = i5 + 1;
                    sb2.append(i5);
                    str3 = sb2.toString();
                    this.nsPrefixMap.put(namespaceURI2, str3);
                    addNSDefinition(element, namespaceURI2);
                }
                writer.write(str3);
                writer.write(":");
            }
            writer.write(attr.getName());
            writer.write("=\"");
            writer.write(encodeAttributeValue(attr.getValue()));
            writer.write("\"");
        }
        ArrayList arrayList = (ArrayList) this.nsURIByElement.get(element);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                String str5 = (String) this.nsPrefixMap.get(str4);
                writer.write(" xmlns");
                if (!"".equals(str5)) {
                    writer.write(":");
                    writer.write(str5);
                }
                writer.write("=\"");
                writer.write(str4);
                writer.write("\"");
            }
        }
        if (z) {
            writer.write(SimpleComparison.f23610d);
            return;
        }
        removeNSDefinitions(element);
        writer.write(" />");
        writer.write(lSep);
        writer.flush();
    }

    public void closeElement(Element element, Writer writer, int i, String str, boolean z) throws IOException {
        if (z) {
            for (int i2 = 0; i2 < i; i2++) {
                writer.write(str);
            }
        }
        writer.write("</");
        if (this.namespacePolicy.qualifyElements) {
            String str2 = (String) this.nsPrefixMap.get(getNamespaceURI(element));
            if (str2 != null && !"".equals(str2)) {
                writer.write(str2);
                writer.write(":");
            }
            removeNSDefinitions(element);
        }
        writer.write(element.getTagName());
        writer.write(SimpleComparison.f23610d);
        writer.write(lSep);
        writer.flush();
    }

    public String encode(String str) {
        return encode(str, false);
    }

    public String encodeAttributeValue(String str) {
        return encode(str, true);
    }

    private String encode(String str, boolean z) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\t':
                case '\n':
                case '\r':
                    if (z) {
                        stringBuffer.append(WS_ENTITIES[charAt - '\t']);
                        break;
                    } else {
                        stringBuffer.append(charAt);
                        break;
                    }
                case '\"':
                    stringBuffer.append("&quot;");
                    break;
                case '&':
                    stringBuffer.append("&amp;");
                    break;
                case '\'':
                    stringBuffer.append("&apos;");
                    break;
                case '<':
                    stringBuffer.append("&lt;");
                    break;
                case '>':
                    stringBuffer.append("&gt;");
                    break;
                default:
                    if (isLegalCharacter(charAt)) {
                        stringBuffer.append(charAt);
                        break;
                    } else {
                        break;
                    }
            }
        }
        return stringBuffer.substring(0);
    }

    public String encodedata(String str) {
        StringWriter stringWriter = new StringWriter();
        try {
            encodedata(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void encodedata(Writer writer, String str) throws IOException {
        int length = str.length();
        int indexOf = str.indexOf("]]>");
        int i = 0;
        while (i < length) {
            int i2 = indexOf < 0 ? length : indexOf;
            while (i < i2) {
                int i3 = i;
                while (i3 < i2 && isLegalCharacter(str.charAt(i3))) {
                    i3++;
                }
                writer.write(str, i, i3 - i);
                i = i3 + 1;
            }
            if (indexOf >= 0) {
                writer.write("]]]]><![CDATA[>");
                int i4 = indexOf + 3;
                i = i4;
                indexOf = str.indexOf("]]>", i4);
            } else {
                i = i2;
            }
        }
    }

    public boolean isReference(String str) {
        if (str.charAt(0) != '&' || !str.endsWith(C4963cj.f20745b)) {
            return false;
        }
        if (str.charAt(1) != '#') {
            String substring = str.substring(1, str.length() - 1);
            int i = 0;
            while (true) {
                String[] strArr = this.knownEntities;
                if (i >= strArr.length) {
                    return false;
                }
                if (substring.equals(strArr[i])) {
                    return true;
                }
                i++;
            }
        } else if (str.charAt(2) == 'x') {
            try {
                Integer.parseInt(str.substring(3, str.length() - 1), 16);
                return true;
            } catch (NumberFormatException unused) {
                return false;
            }
        } else {
            try {
                Integer.parseInt(str.substring(2, str.length() - 1));
                return true;
            } catch (NumberFormatException unused2) {
                return false;
            }
        }
    }

    private void removeNSDefinitions(Element element) {
        ArrayList arrayList = (ArrayList) this.nsURIByElement.get(element);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.nsPrefixMap.remove(it.next());
            }
            this.nsURIByElement.remove(element);
        }
    }

    private void addNSDefinition(Element element, String str) {
        ArrayList arrayList = (ArrayList) this.nsURIByElement.get(element);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.nsURIByElement.put(element, arrayList);
        }
        arrayList.add(str);
    }

    private static String getNamespaceURI(Node node) {
        String namespaceURI = node.getNamespaceURI();
        return namespaceURI == null ? "" : namespaceURI;
    }
}
