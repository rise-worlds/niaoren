package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.CollectionUtils;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class Manifest {
    public static final String ATTRIBUTE_SIGNATURE_VERSION = "Signature-Version";
    public static final String DEFAULT_MANIFEST_VERSION = "1.0";
    public static final String EOL = "\r\n";
    public static final String ERROR_FROM_FORBIDDEN = "Manifest attributes should not start with \"From\" in \"";
    public static final String JAR_ENCODING = "UTF-8";
    public static final int MAX_LINE_LENGTH = 72;
    public static final int MAX_SECTION_LENGTH = 70;
    private String manifestVersion;
    public static final String ATTRIBUTE_MANIFEST_VERSION = "Manifest-Version";
    private static final String ATTRIBUTE_MANIFEST_VERSION_LC = ATTRIBUTE_MANIFEST_VERSION.toLowerCase(Locale.ENGLISH);
    public static final String ATTRIBUTE_NAME = "Name";
    private static final String ATTRIBUTE_NAME_LC = ATTRIBUTE_NAME.toLowerCase(Locale.ENGLISH);
    public static final String ATTRIBUTE_FROM = "From";
    private static final String ATTRIBUTE_FROM_LC = ATTRIBUTE_FROM.toLowerCase(Locale.ENGLISH);
    public static final String ATTRIBUTE_CLASSPATH = "Class-Path";
    private static final String ATTRIBUTE_CLASSPATH_LC = ATTRIBUTE_CLASSPATH.toLowerCase(Locale.ENGLISH);
    private Section mainSection = new Section();
    private Map<String, Section> sections = new LinkedHashMap();

    /* loaded from: classes2.dex */
    public static class Attribute {
        private static final int MAX_NAME_LENGTH = 70;
        private static final int MAX_NAME_VALUE_LENGTH = 68;
        private int currentIndex;
        private String name;
        private Vector<String> values;

        public Attribute() {
            this.name = null;
            this.values = new Vector<>();
            this.currentIndex = 0;
        }

        public Attribute(String str) throws ManifestException {
            this.name = null;
            this.values = new Vector<>();
            this.currentIndex = 0;
            parse(str);
        }

        public Attribute(String str, String str2) {
            this.name = null;
            this.values = new Vector<>();
            this.currentIndex = 0;
            this.name = str;
            setValue(str2);
        }

        public int hashCode() {
            int i = 0;
            if (this.name != null) {
                i = 0 + getKey().hashCode();
            }
            return i + this.values.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            Attribute attribute = (Attribute) obj;
            String key = getKey();
            String key2 = attribute.getKey();
            if ((key != null || key2 == null) && (key == null || key.equals(key2))) {
                return this.values.equals(attribute.values);
            }
            return false;
        }

        public void parse(String str) throws ManifestException {
            int indexOf = str.indexOf(": ");
            if (indexOf != -1) {
                this.name = str.substring(0, indexOf);
                setValue(str.substring(indexOf + 2));
                return;
            }
            throw new ManifestException("Manifest line \"" + str + "\" is not valid as it does not contain a name and a value separated by ': ' ");
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public String getKey() {
            String str = this.name;
            if (str == null) {
                return null;
            }
            return str.toLowerCase(Locale.ENGLISH);
        }

        public void setValue(String str) {
            if (this.currentIndex >= this.values.size()) {
                this.values.addElement(str);
                this.currentIndex = this.values.size() - 1;
                return;
            }
            this.values.setElementAt(str, this.currentIndex);
        }

        public String getValue() {
            Enumeration<String> values;
            if (this.values.size() == 0) {
                return null;
            }
            String str = "";
            while (getValues().hasMoreElements()) {
                str = str + values.nextElement() + ExpandableTextView.f6958c;
            }
            return str.trim();
        }

        public void addValue(String str) {
            this.currentIndex++;
            setValue(str);
        }

        public Enumeration<String> getValues() {
            return this.values.elements();
        }

        public void addContinuation(String str) {
            setValue(this.values.elementAt(this.currentIndex) + str.substring(1));
        }

        public void write(PrintWriter printWriter) throws IOException {
            write(printWriter, false);
        }

        public void write(PrintWriter printWriter, boolean z) throws IOException {
            if (!z) {
                Enumeration<String> values = getValues();
                while (values.hasMoreElements()) {
                    writeValue(printWriter, values.nextElement());
                }
                return;
            }
            writeValue(printWriter, getValue());
        }

        private void writeValue(PrintWriter printWriter, String str) throws IOException {
            String str2;
            int length = this.name.getBytes("UTF-8").length;
            if (length <= 68) {
                str2 = this.name + ": " + str;
            } else if (length <= 70) {
                printWriter.print(this.name + ": \r\n");
                StringBuilder sb = new StringBuilder();
                sb.append(ExpandableTextView.f6958c);
                sb.append(str);
                str2 = sb.toString();
            } else {
                throw new IOException("Unable to write manifest line " + this.name + ": " + str);
            }
            while (str2.getBytes("UTF-8").length > 70) {
                int length2 = 70 >= str2.length() ? str2.length() - 1 : 70;
                String substring = str2.substring(0, length2);
                while (substring.getBytes("UTF-8").length > 70 && length2 > 0) {
                    length2--;
                    substring = str2.substring(0, length2);
                }
                if (length2 != 0) {
                    printWriter.print(substring + "\r\n");
                    str2 = ExpandableTextView.f6958c + str2.substring(length2);
                } else {
                    throw new IOException("Unable to write manifest line " + this.name + ": " + str);
                }
            }
            printWriter.print(str2 + "\r\n");
        }
    }

    /* loaded from: classes2.dex */
    public static class Section {
        private Vector<String> warnings = new Vector<>();
        private String name = null;
        private Map<String, Attribute> attributes = new LinkedHashMap();

        public void setName(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0066, code lost:
            return null;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String read(java.io.BufferedReader r6) throws org.apache.tools.ant.taskdefs.ManifestException, java.io.IOException {
            /*
                r5 = this;
                r0 = 0
                r1 = r0
            L_0x0002:
                java.lang.String r2 = r6.readLine()
                if (r2 == 0) goto L_0x0066
                int r3 = r2.length()
                if (r3 != 0) goto L_0x000f
                goto L_0x0066
            L_0x000f:
                r3 = 0
                char r3 = r2.charAt(r3)
                r4 = 32
                if (r3 != r4) goto L_0x0052
                if (r1 != 0) goto L_0x004e
                java.lang.String r3 = r5.name
                if (r3 == 0) goto L_0x0037
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = r5.name
                r3.append(r4)
                r4 = 1
                java.lang.String r2 = r2.substring(r4)
                r3.append(r2)
                java.lang.String r2 = r3.toString()
                r5.name = r2
                goto L_0x0002
            L_0x0037:
                org.apache.tools.ant.taskdefs.ManifestException r6 = new org.apache.tools.ant.taskdefs.ManifestException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Can't start an attribute with a continuation line "
                r0.append(r1)
                r0.append(r2)
                java.lang.String r0 = r0.toString()
                r6.<init>(r0)
                throw r6
            L_0x004e:
                r1.addContinuation(r2)
                goto L_0x0002
            L_0x0052:
                org.apache.tools.ant.taskdefs.Manifest$Attribute r1 = new org.apache.tools.ant.taskdefs.Manifest$Attribute
                r1.<init>(r2)
                java.lang.String r2 = r5.addAttributeAndCheck(r1)
                java.lang.String r1 = r1.getKey()
                org.apache.tools.ant.taskdefs.Manifest$Attribute r1 = r5.getAttribute(r1)
                if (r2 == 0) goto L_0x0002
                return r2
            L_0x0066:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Manifest.Section.read(java.io.BufferedReader):java.lang.String");
        }

        public void merge(Section section) throws ManifestException {
            merge(section, false);
        }

        public void merge(Section section, boolean z) throws ManifestException {
            Attribute attribute;
            if ((this.name != null || section.getName() == null) && (this.name == null || section.getName() == null || this.name.toLowerCase(Locale.ENGLISH).equals(section.getName().toLowerCase(Locale.ENGLISH)))) {
                Enumeration<String> attributeKeys = section.getAttributeKeys();
                Attribute attribute2 = null;
                while (attributeKeys.hasMoreElements()) {
                    String nextElement = attributeKeys.nextElement();
                    Attribute attribute3 = section.getAttribute(nextElement);
                    if (nextElement.equalsIgnoreCase(Manifest.ATTRIBUTE_CLASSPATH)) {
                        if (attribute2 == null) {
                            attribute2 = new Attribute();
                            attribute2.setName(Manifest.ATTRIBUTE_CLASSPATH);
                        }
                        Enumeration<String> values = attribute3.getValues();
                        while (values.hasMoreElements()) {
                            attribute2.addValue(values.nextElement());
                        }
                    } else {
                        storeAttribute(attribute3);
                    }
                }
                if (attribute2 != null) {
                    if (z && (attribute = getAttribute(Manifest.ATTRIBUTE_CLASSPATH)) != null) {
                        Enumeration<String> values2 = attribute.getValues();
                        while (values2.hasMoreElements()) {
                            attribute2.addValue(values2.nextElement());
                        }
                    }
                    storeAttribute(attribute2);
                }
                Enumeration<String> elements = section.warnings.elements();
                while (elements.hasMoreElements()) {
                    this.warnings.addElement(elements.nextElement());
                }
                return;
            }
            throw new ManifestException("Unable to merge sections with different names");
        }

        public void write(PrintWriter printWriter) throws IOException {
            write(printWriter, false);
        }

        public void write(PrintWriter printWriter, boolean z) throws IOException {
            String str = this.name;
            if (str != null) {
                new Attribute(Manifest.ATTRIBUTE_NAME, str).write(printWriter);
            }
            Enumeration<String> attributeKeys = getAttributeKeys();
            while (attributeKeys.hasMoreElements()) {
                getAttribute(attributeKeys.nextElement()).write(printWriter, z);
            }
            printWriter.print("\r\n");
        }

        public Attribute getAttribute(String str) {
            return this.attributes.get(str.toLowerCase(Locale.ENGLISH));
        }

        public Enumeration<String> getAttributeKeys() {
            return CollectionUtils.asEnumeration(this.attributes.keySet().iterator());
        }

        public String getAttributeValue(String str) {
            Attribute attribute = getAttribute(str.toLowerCase(Locale.ENGLISH));
            if (attribute == null) {
                return null;
            }
            return attribute.getValue();
        }

        public void removeAttribute(String str) {
            this.attributes.remove(str.toLowerCase(Locale.ENGLISH));
        }

        public void addConfiguredAttribute(Attribute attribute) throws ManifestException {
            if (addAttributeAndCheck(attribute) != null) {
                throw new BuildException("Specify the section name using the \"name\" attribute of the <section> element rather than using a \"Name\" manifest attribute");
            }
        }

        public String addAttributeAndCheck(Attribute attribute) throws ManifestException {
            if (attribute.getName() == null || attribute.getValue() == null) {
                throw new BuildException("Attributes must have name and value");
            }
            String key = attribute.getKey();
            if (key.equals(Manifest.ATTRIBUTE_NAME_LC)) {
                Vector<String> vector = this.warnings;
                vector.addElement("\"Name\" attributes should not occur in the main section and must be the first element in all other sections: \"" + attribute.getName() + ": " + attribute.getValue() + "\"");
                return attribute.getValue();
            } else if (key.startsWith(Manifest.ATTRIBUTE_FROM_LC)) {
                Vector<String> vector2 = this.warnings;
                vector2.addElement(Manifest.ERROR_FROM_FORBIDDEN + attribute.getName() + ": " + attribute.getValue() + "\"");
                return null;
            } else if (key.equals(Manifest.ATTRIBUTE_CLASSPATH_LC)) {
                Attribute attribute2 = this.attributes.get(key);
                if (attribute2 == null) {
                    storeAttribute(attribute);
                    return null;
                }
                this.warnings.addElement("Multiple Class-Path attributes are supported but violate the Jar specification and may not be correctly processed in all environments");
                Enumeration<String> values = attribute.getValues();
                while (values.hasMoreElements()) {
                    attribute2.addValue(values.nextElement());
                }
                return null;
            } else if (!this.attributes.containsKey(key)) {
                storeAttribute(attribute);
                return null;
            } else {
                throw new ManifestException("The attribute \"" + attribute.getName() + "\" may not occur more than once in the same section");
            }
        }

        public Object clone() {
            Section section = new Section();
            section.setName(this.name);
            Enumeration<String> attributeKeys = getAttributeKeys();
            while (attributeKeys.hasMoreElements()) {
                Attribute attribute = getAttribute(attributeKeys.nextElement());
                section.storeAttribute(new Attribute(attribute.getName(), attribute.getValue()));
            }
            return section;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void storeAttribute(Attribute attribute) {
            if (attribute != null) {
                this.attributes.put(attribute.getKey(), attribute);
            }
        }

        public Enumeration<String> getWarnings() {
            return this.warnings.elements();
        }

        public int hashCode() {
            return this.attributes.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            return this.attributes.equals(((Section) obj).attributes);
        }
    }

    public static Manifest getDefaultManifest() throws BuildException {
        InputStream inputStream;
        Reader reader;
        Throwable th;
        ManifestException e;
        IOException e2;
        InputStreamReader inputStreamReader;
        Manifest manifest;
        try {
            try {
                inputStream = Manifest.class.getResourceAsStream("/org/apache/tools/ant/defaultManifest.mf");
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e2 = e3;
        } catch (ManifestException e4) {
            e = e4;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            reader = null;
        }
        try {
            if (inputStream != null) {
                try {
                    inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                }
                try {
                    try {
                        manifest = new Manifest(inputStreamReader);
                        String property = System.getProperty("java.runtime.version");
                        if (property == null) {
                            property = System.getProperty("java.vm.version");
                        }
                        manifest.getMainSection().storeAttribute(new Attribute("Created-By", property + " (" + System.getProperty("java.vm.vendor") + ")"));
                    } catch (UnsupportedEncodingException unused2) {
                        inputStreamReader = new InputStreamReader(inputStream);
                        manifest = new Manifest(inputStreamReader);
                        FileUtils.close(inputStreamReader);
                        FileUtils.close(inputStream);
                        return manifest;
                    }
                    FileUtils.close(inputStreamReader);
                    FileUtils.close(inputStream);
                    return manifest;
                } catch (IOException e5) {
                    e2 = e5;
                    throw new BuildException("Unable to read default manifest", e2);
                } catch (ManifestException e6) {
                    e = e6;
                    throw new BuildException("Default manifest is invalid !!", e);
                }
            } else {
                throw new BuildException("Could not find default manifest: /org/apache/tools/ant/defaultManifest.mf");
            }
        } catch (IOException e7) {
            e2 = e7;
        } catch (ManifestException e8) {
            e = e8;
        } catch (Throwable th4) {
            th = th4;
            reader = null;
            FileUtils.close(reader);
            FileUtils.close(inputStream);
            throw th;
        }
    }

    public Manifest() {
        this.manifestVersion = "1.0";
        this.manifestVersion = null;
    }

    public Manifest(Reader reader) throws ManifestException, IOException {
        this.manifestVersion = "1.0";
        BufferedReader bufferedReader = new BufferedReader(reader);
        String read = this.mainSection.read(bufferedReader);
        String attributeValue = this.mainSection.getAttributeValue(ATTRIBUTE_MANIFEST_VERSION);
        if (attributeValue != null) {
            this.manifestVersion = attributeValue;
            this.mainSection.removeAttribute(ATTRIBUTE_MANIFEST_VERSION);
        }
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return;
            }
            if (readLine.length() != 0) {
                Section section = new Section();
                if (read == null) {
                    Attribute attribute = new Attribute(readLine);
                    if (attribute.getName().equalsIgnoreCase(ATTRIBUTE_NAME)) {
                        read = attribute.getValue();
                    } else {
                        throw new ManifestException("Manifest sections should start with a \"Name\" attribute and not \"" + attribute.getName() + "\"");
                    }
                } else {
                    section.addAttributeAndCheck(new Attribute(readLine));
                }
                section.setName(read);
                read = section.read(bufferedReader);
                addConfiguredSection(section);
            }
        }
    }

    public void addConfiguredSection(Section section) throws ManifestException {
        String name = section.getName();
        if (name != null) {
            this.sections.put(name, section);
            return;
        }
        throw new BuildException("Sections must have a name");
    }

    public void addConfiguredAttribute(Attribute attribute) throws ManifestException {
        if (attribute.getKey() == null || attribute.getValue() == null) {
            throw new BuildException("Attributes must have name and value");
        } else if (attribute.getKey().equals(ATTRIBUTE_MANIFEST_VERSION_LC)) {
            this.manifestVersion = attribute.getValue();
        } else {
            this.mainSection.addConfiguredAttribute(attribute);
        }
    }

    public void merge(Manifest manifest) throws ManifestException {
        merge(manifest, false);
    }

    public void merge(Manifest manifest, boolean z) throws ManifestException {
        merge(manifest, z, false);
    }

    public void merge(Manifest manifest, boolean z, boolean z2) throws ManifestException {
        if (manifest != null) {
            if (z) {
                this.mainSection = (Section) manifest.mainSection.clone();
            } else {
                this.mainSection.merge(manifest.mainSection, z2);
            }
            String str = manifest.manifestVersion;
            if (str != null) {
                this.manifestVersion = str;
            }
            Enumeration<String> sectionNames = manifest.getSectionNames();
            while (sectionNames.hasMoreElements()) {
                String nextElement = sectionNames.nextElement();
                Section section = this.sections.get(nextElement);
                Section section2 = manifest.sections.get(nextElement);
                if (section != null) {
                    section.merge(section2, z2);
                } else if (section2 != null) {
                    addConfiguredSection((Section) section2.clone());
                }
            }
        }
    }

    public void write(PrintWriter printWriter) throws IOException {
        write(printWriter, false);
    }

    public void write(PrintWriter printWriter, boolean z) throws IOException {
        printWriter.print("Manifest-Version: " + this.manifestVersion + "\r\n");
        String attributeValue = this.mainSection.getAttributeValue(ATTRIBUTE_SIGNATURE_VERSION);
        if (attributeValue != null) {
            printWriter.print("Signature-Version: " + attributeValue + "\r\n");
            this.mainSection.removeAttribute(ATTRIBUTE_SIGNATURE_VERSION);
        }
        this.mainSection.write(printWriter, z);
        if (attributeValue != null) {
            try {
                this.mainSection.addConfiguredAttribute(new Attribute(ATTRIBUTE_SIGNATURE_VERSION, attributeValue));
            } catch (ManifestException unused) {
            }
        }
        for (String str : this.sections.keySet()) {
            getSection(str).write(printWriter, z);
        }
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try {
            write(new PrintWriter(stringWriter));
            return stringWriter.toString();
        } catch (IOException unused) {
            return null;
        }
    }

    public Enumeration<String> getWarnings() {
        Vector vector = new Vector();
        Enumeration<String> warnings = this.mainSection.getWarnings();
        while (warnings.hasMoreElements()) {
            vector.addElement(warnings.nextElement());
        }
        for (Section section : this.sections.values()) {
            Enumeration<String> warnings2 = section.getWarnings();
            while (warnings2.hasMoreElements()) {
                vector.addElement(warnings2.nextElement());
            }
        }
        return vector.elements();
    }

    public int hashCode() {
        String str = this.manifestVersion;
        int i = 0;
        if (str != null) {
            i = 0 + str.hashCode();
        }
        return i + this.mainSection.hashCode() + this.sections.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Manifest manifest = (Manifest) obj;
        String str = this.manifestVersion;
        if (str == null) {
            if (manifest.manifestVersion != null) {
                return false;
            }
        } else if (!str.equals(manifest.manifestVersion)) {
            return false;
        }
        if (!this.mainSection.equals(manifest.mainSection)) {
            return false;
        }
        return this.sections.equals(manifest.sections);
    }

    public String getManifestVersion() {
        return this.manifestVersion;
    }

    public Section getMainSection() {
        return this.mainSection;
    }

    public Section getSection(String str) {
        return this.sections.get(str);
    }

    public Enumeration<String> getSectionNames() {
        return CollectionUtils.asEnumeration(this.sections.keySet().iterator());
    }
}
