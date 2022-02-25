package org.apache.tools.ant.taskdefs.optional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.DTDLocation;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JAXPUtils;
import org.apache.tools.ant.util.XmlConstants;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.ParserAdapter;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class XMLValidateTask extends Task {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    protected static final String INIT_FAILED_MSG = "Could not start xml validation: ";
    public static final String MESSAGE_FILES_VALIDATED = " file(s) have been successfully validated.";
    protected Path classpath;
    protected boolean failOnError = true;
    protected boolean warn = true;
    protected boolean lenient = false;
    protected String readerClassName = null;
    protected File file = null;
    protected Vector filesets = new Vector();
    protected XMLReader xmlReader = null;
    protected ValidatorErrorHandler errorHandler = new ValidatorErrorHandler();
    private Vector attributeList = new Vector();
    private final Vector propertyList = new Vector();
    private XMLCatalog xmlCatalog = new XMLCatalog();
    private AntClassLoader readerLoader = null;

    public void setFailOnError(boolean z) {
        this.failOnError = z;
    }

    public void setWarn(boolean z) {
        this.warn = z;
    }

    public void setLenient(boolean z) {
        this.lenient = z;
    }

    public void setClassName(String str) {
        this.readerClassName = str;
    }

    public void setClasspath(Path path) {
        Path path2 = this.classpath;
        if (path2 == null) {
            this.classpath = path;
        } else {
            path2.append(path);
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference reference) {
        createClasspath().setRefid(reference);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void addConfiguredXMLCatalog(XMLCatalog xMLCatalog) {
        this.xmlCatalog.addConfiguredXMLCatalog(xMLCatalog);
    }

    public void addFileset(FileSet fileSet) {
        this.filesets.addElement(fileSet);
    }

    public Attribute createAttribute() {
        Attribute attribute = new Attribute();
        this.attributeList.addElement(attribute);
        return attribute;
    }

    public Property createProperty() {
        Property property = new Property();
        this.propertyList.addElement(property);
        return property;
    }

    @Override // org.apache.tools.ant.Task
    public void init() throws BuildException {
        super.init();
        this.xmlCatalog.setProject(getProject());
    }

    public DTDLocation createDTD() {
        DTDLocation dTDLocation = new DTDLocation();
        this.xmlCatalog.addDTD(dTDLocation);
        return dTDLocation;
    }

    protected EntityResolver getEntityResolver() {
        return this.xmlCatalog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMLReader getXmlReader() {
        return this.xmlReader;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a A[Catch: all -> 0x00a5, TryCatch #0 {all -> 0x00a5, blocks: (B:2:0x0000, B:4:0x0004, B:7:0x000d, B:8:0x0014, B:9:0x0015, B:11:0x001a, B:13:0x0022, B:15:0x002a, B:17:0x0032, B:18:0x0039, B:20:0x0055, B:21:0x0059, B:22:0x005e, B:24:0x0060, B:26:0x006a, B:27:0x0080, B:29:0x0083, B:30:0x009a, B:31:0x009e), top: B:37:0x0000 }] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            r10 = this;
            java.io.File r0 = r10.file     // Catch: all -> 0x00a5
            if (r0 != 0) goto L_0x0015
            java.util.Vector r0 = r10.filesets     // Catch: all -> 0x00a5
            int r0 = r0.size()     // Catch: all -> 0x00a5
            if (r0 == 0) goto L_0x000d
            goto L_0x0015
        L_0x000d:
            org.apache.tools.ant.BuildException r0 = new org.apache.tools.ant.BuildException     // Catch: all -> 0x00a5
            java.lang.String r1 = "Specify at least one source - a file or a fileset."
            r0.<init>(r1)     // Catch: all -> 0x00a5
            throw r0     // Catch: all -> 0x00a5
        L_0x0015:
            java.io.File r0 = r10.file     // Catch: all -> 0x00a5
            r1 = 0
            if (r0 == 0) goto L_0x005f
            java.io.File r0 = r10.file     // Catch: all -> 0x00a5
            boolean r0 = r0.exists()     // Catch: all -> 0x00a5
            if (r0 == 0) goto L_0x0039
            java.io.File r0 = r10.file     // Catch: all -> 0x00a5
            boolean r0 = r0.canRead()     // Catch: all -> 0x00a5
            if (r0 == 0) goto L_0x0039
            java.io.File r0 = r10.file     // Catch: all -> 0x00a5
            boolean r0 = r0.isFile()     // Catch: all -> 0x00a5
            if (r0 == 0) goto L_0x0039
            java.io.File r0 = r10.file     // Catch: all -> 0x00a5
            r10.doValidate(r0)     // Catch: all -> 0x00a5
            r0 = 1
            goto L_0x0060
        L_0x0039:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: all -> 0x00a5
            r0.<init>()     // Catch: all -> 0x00a5
            java.lang.String r2 = "File "
            r0.append(r2)     // Catch: all -> 0x00a5
            java.io.File r2 = r10.file     // Catch: all -> 0x00a5
            r0.append(r2)     // Catch: all -> 0x00a5
            java.lang.String r2 = " cannot be read"
            r0.append(r2)     // Catch: all -> 0x00a5
            java.lang.String r0 = r0.toString()     // Catch: all -> 0x00a5
            boolean r2 = r10.failOnError     // Catch: all -> 0x00a5
            if (r2 != 0) goto L_0x0059
            r10.log(r0, r1)     // Catch: all -> 0x00a5
            goto L_0x005f
        L_0x0059:
            org.apache.tools.ant.BuildException r1 = new org.apache.tools.ant.BuildException     // Catch: all -> 0x00a5
            r1.<init>(r0)     // Catch: all -> 0x00a5
            throw r1     // Catch: all -> 0x00a5
        L_0x005f:
            r0 = 0
        L_0x0060:
            java.util.Vector r2 = r10.filesets     // Catch: all -> 0x00a5
            int r2 = r2.size()     // Catch: all -> 0x00a5
            r3 = r0
            r0 = 0
        L_0x0068:
            if (r0 >= r2) goto L_0x009e
            java.util.Vector r4 = r10.filesets     // Catch: all -> 0x00a5
            java.lang.Object r4 = r4.elementAt(r0)     // Catch: all -> 0x00a5
            org.apache.tools.ant.types.FileSet r4 = (org.apache.tools.ant.types.FileSet) r4     // Catch: all -> 0x00a5
            org.apache.tools.ant.Project r5 = r10.getProject()     // Catch: all -> 0x00a5
            org.apache.tools.ant.DirectoryScanner r5 = r4.getDirectoryScanner(r5)     // Catch: all -> 0x00a5
            java.lang.String[] r5 = r5.getIncludedFiles()     // Catch: all -> 0x00a5
            r6 = r3
            r3 = 0
        L_0x0080:
            int r7 = r5.length     // Catch: all -> 0x00a5
            if (r3 >= r7) goto L_0x009a
            java.io.File r7 = new java.io.File     // Catch: all -> 0x00a5
            org.apache.tools.ant.Project r8 = r10.getProject()     // Catch: all -> 0x00a5
            java.io.File r8 = r4.getDir(r8)     // Catch: all -> 0x00a5
            r9 = r5[r3]     // Catch: all -> 0x00a5
            r7.<init>(r8, r9)     // Catch: all -> 0x00a5
            r10.doValidate(r7)     // Catch: all -> 0x00a5
            int r6 = r6 + 1
            int r3 = r3 + 1
            goto L_0x0080
        L_0x009a:
            int r0 = r0 + 1
            r3 = r6
            goto L_0x0068
        L_0x009e:
            r10.onSuccessfulValidation(r3)     // Catch: all -> 0x00a5
            r10.cleanup()
            return
        L_0x00a5:
            r0 = move-exception
            r10.cleanup()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.optional.XMLValidateTask.execute():void");
    }

    protected void onSuccessfulValidation(int i) {
        log(i + MESSAGE_FILES_VALIDATED);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initValidator() {
        this.xmlReader = createXmlReader();
        this.xmlReader.setEntityResolver(getEntityResolver());
        this.xmlReader.setErrorHandler(this.errorHandler);
        if (!isSax1Parser()) {
            if (!this.lenient) {
                setFeature(XmlConstants.FEATURE_VALIDATION, true);
            }
            int size = this.attributeList.size();
            for (int i = 0; i < size; i++) {
                Attribute attribute = (Attribute) this.attributeList.elementAt(i);
                setFeature(attribute.getName(), attribute.getValue());
            }
            int size2 = this.propertyList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Property property = (Property) this.propertyList.elementAt(i2);
                setProperty(property.getName(), property.getValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSax1Parser() {
        return this.xmlReader instanceof ParserAdapter;
    }

    protected XMLReader createXmlReader() {
        Class<?> cls;
        Object newInstance;
        String str = this.readerClassName;
        if (str == null) {
            newInstance = createDefaultReaderOrParser();
        } else {
            try {
                if (this.classpath != null) {
                    this.readerLoader = getProject().createClassLoader(this.classpath);
                    cls = Class.forName(this.readerClassName, true, this.readerLoader);
                } else {
                    cls = Class.forName(str);
                }
                newInstance = cls.newInstance();
            } catch (ClassNotFoundException e) {
                throw new BuildException(INIT_FAILED_MSG + this.readerClassName, e);
            } catch (IllegalAccessException e2) {
                throw new BuildException(INIT_FAILED_MSG + this.readerClassName, e2);
            } catch (InstantiationException e3) {
                throw new BuildException(INIT_FAILED_MSG + this.readerClassName, e3);
            }
        }
        if (newInstance instanceof XMLReader) {
            XMLReader xMLReader = (XMLReader) newInstance;
            log("Using SAX2 reader " + newInstance.getClass().getName(), 3);
            return xMLReader;
        } else if (newInstance instanceof Parser) {
            ParserAdapter parserAdapter = new ParserAdapter((Parser) newInstance);
            log("Using SAX1 parser " + newInstance.getClass().getName(), 3);
            return parserAdapter;
        } else {
            throw new BuildException(INIT_FAILED_MSG + newInstance.getClass().getName() + " implements nor SAX1 Parser nor SAX2 XMLReader.");
        }
    }

    protected void cleanup() {
        AntClassLoader antClassLoader = this.readerLoader;
        if (antClassLoader != null) {
            antClassLoader.cleanup();
            this.readerLoader = null;
        }
    }

    private Object createDefaultReaderOrParser() {
        try {
            return createDefaultReader();
        } catch (BuildException unused) {
            return JAXPUtils.getParser();
        }
    }

    protected XMLReader createDefaultReader() {
        return JAXPUtils.getXMLReader();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFeature(String str, boolean z) throws BuildException {
        log("Setting feature " + str + SimpleComparison.f23609c + z, 4);
        try {
            this.xmlReader.setFeature(str, z);
        } catch (SAXNotRecognizedException e) {
            throw new BuildException("Parser " + this.xmlReader.getClass().getName() + " doesn't recognize feature " + str, e, getLocation());
        } catch (SAXNotSupportedException e2) {
            throw new BuildException("Parser " + this.xmlReader.getClass().getName() + " doesn't support feature " + str, e2, getLocation());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setProperty(String str, String str2) throws BuildException {
        if (str == null || str2 == null) {
            throw new BuildException("Property name and value must be specified.");
        }
        try {
            this.xmlReader.setProperty(str, str2);
        } catch (SAXNotRecognizedException e) {
            throw new BuildException("Parser " + this.xmlReader.getClass().getName() + " doesn't recognize property " + str, e, getLocation());
        } catch (SAXNotSupportedException e2) {
            throw new BuildException("Parser " + this.xmlReader.getClass().getName() + " doesn't support property " + str, e2, getLocation());
        }
    }

    protected boolean doValidate(File file) {
        boolean z;
        initValidator();
        try {
            log("Validating " + file.getName() + "... ", 3);
            this.errorHandler.init(file);
            InputSource inputSource = new InputSource(new FileInputStream(file));
            inputSource.setSystemId(FILE_UTILS.toURI(file.getAbsolutePath()));
            this.xmlReader.parse(inputSource);
            z = true;
        } catch (IOException e) {
            throw new BuildException("Could not validate document " + file, e);
        } catch (SAXException e2) {
            log("Caught when validating: " + e2.toString(), 4);
            if (!this.failOnError) {
                log("Could not validate document " + file + ": " + e2.toString());
                z = false;
            } else {
                throw new BuildException("Could not validate document " + file);
            }
        }
        if (!this.errorHandler.getFailure()) {
            return z;
        }
        if (!this.failOnError) {
            log(file + " is not a valid XML document", 0);
            return false;
        }
        throw new BuildException(file + " is not a valid XML document.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public class ValidatorErrorHandler implements ErrorHandler {
        protected File currentFile = null;
        protected String lastErrorMessage = null;
        protected boolean failed = false;

        protected ValidatorErrorHandler() {
        }

        public void init(File file) {
            this.currentFile = file;
            this.failed = false;
        }

        public boolean getFailure() {
            return this.failed;
        }

        @Override // org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException sAXParseException) {
            this.failed = true;
            doLog(sAXParseException, 0);
        }

        @Override // org.xml.sax.ErrorHandler
        public void error(SAXParseException sAXParseException) {
            this.failed = true;
            doLog(sAXParseException, 0);
        }

        @Override // org.xml.sax.ErrorHandler
        public void warning(SAXParseException sAXParseException) {
            if (XMLValidateTask.this.warn) {
                doLog(sAXParseException, 1);
            }
        }

        private void doLog(SAXParseException sAXParseException, int i) {
            XMLValidateTask.this.log(getMessage(sAXParseException), i);
        }

        private String getMessage(SAXParseException sAXParseException) {
            String str;
            String str2;
            String systemId = sAXParseException.getSystemId();
            if (systemId == null) {
                return sAXParseException.getMessage();
            }
            if (systemId.startsWith("file:")) {
                try {
                    systemId = XMLValidateTask.FILE_UTILS.fromURI(systemId);
                } catch (Exception unused) {
                }
            }
            int lineNumber = sAXParseException.getLineNumber();
            int columnNumber = sAXParseException.getColumnNumber();
            StringBuilder sb = new StringBuilder();
            sb.append(systemId);
            if (lineNumber == -1) {
                str = "";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(":");
                sb2.append(lineNumber);
                if (columnNumber == -1) {
                    str2 = "";
                } else {
                    str2 = ":" + columnNumber;
                }
                sb2.append(str2);
                str = sb2.toString();
            }
            sb.append(str);
            sb.append(": ");
            sb.append(sAXParseException.getMessage());
            return sb.toString();
        }
    }

    /* loaded from: classes2.dex */
    public static class Attribute {
        private String attributeName = null;
        private boolean attributeValue;

        public void setName(String str) {
            this.attributeName = str;
        }

        public void setValue(boolean z) {
            this.attributeValue = z;
        }

        public String getName() {
            return this.attributeName;
        }

        public boolean getValue() {
            return this.attributeValue;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Property {
        private String name;
        private String value;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }
}
