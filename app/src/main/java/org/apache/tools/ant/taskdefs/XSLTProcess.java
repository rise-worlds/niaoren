package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathVariableResolver;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DynamicConfigurator;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.taskdefs.optional.TraXLiaison;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PropertySet;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Resources;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.types.selectors.SizeSelector;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.ResourceUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class XSLTProcess extends MatchingTask implements XSLTLogger {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    public static final String PROCESSOR_TRAX = "trax";
    private XSLTLiaison liaison;
    private String processor;
    private TraceConfiguration traceConfiguration;
    private XPath xpath;
    private XPathFactory xpathFactory;
    private File destDir = null;
    private File baseDir = null;
    private String xslFile = null;
    private Resource xslResource = null;
    private String targetExtension = ".html";
    private String fileNameParameter = null;
    private String fileDirParameter = null;
    private final List<Param> params = new ArrayList();
    private File inFile = null;
    private File outFile = null;
    private Path classpath = null;
    private boolean stylesheetLoaded = false;
    private boolean force = false;
    private final Vector outputProperties = new Vector();
    private final XMLCatalog xmlCatalog = new XMLCatalog();
    private boolean performDirectoryScan = true;
    private Factory factory = null;
    private boolean reuseLoadedStylesheet = true;
    private AntClassLoader loader = null;
    private Mapper mapperElement = null;
    private final Union resources = new Union();
    private boolean useImplicitFileset = true;
    private boolean suppressWarnings = false;
    private boolean failOnTransformationError = true;
    private boolean failOnError = true;
    private boolean failOnNoResources = true;
    private final CommandlineJava.SysProperties sysProperties = new CommandlineJava.SysProperties();

    public void setScanIncludedDirectories(boolean z) {
        this.performDirectoryScan = z;
    }

    public void setReloadStylesheet(boolean z) {
        this.reuseLoadedStylesheet = !z;
    }

    public void addMapper(Mapper mapper) {
        if (this.mapperElement != null) {
            handleError(Expand.ERROR_MULTIPLE_MAPPERS);
        } else {
            this.mapperElement = mapper;
        }
    }

    public void add(ResourceCollection resourceCollection) {
        this.resources.add(resourceCollection);
    }

    public void addConfiguredStyle(Resources resources) {
        if (resources.size() != 1) {
            handleError("The style element must be specified with exactly one nested resource.");
        } else {
            setXslResource(resources.iterator().next());
        }
    }

    public void setXslResource(Resource resource) {
        this.xslResource = resource;
    }

    public void add(FileNameMapper fileNameMapper) throws BuildException {
        Mapper mapper = new Mapper(getProject());
        mapper.add(fileNameMapper);
        addMapper(mapper);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x011c, code lost:
        if (r11.sysProperties.size() > 0) goto L_0x011e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x011e, code lost:
        r11.sysProperties.restoreSystem();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0123, code lost:
        r11.liaison = null;
        r11.stylesheetLoaded = false;
        r11.baseDir = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0129, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x014d, code lost:
        if (r11.sysProperties.size() > 0) goto L_0x011e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01ef, code lost:
        if (r11.sysProperties.size() > 0) goto L_0x011e;
     */
    /* JADX WARN: Finally extract failed */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 571
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.XSLTProcess.execute():void");
    }

    public void setForce(boolean z) {
        this.force = z;
    }

    public void setBasedir(File file) {
        this.baseDir = file;
    }

    public void setDestdir(File file) {
        this.destDir = file;
    }

    public void setExtension(String str) {
        this.targetExtension = str;
    }

    public void setStyle(String str) {
        this.xslFile = str;
    }

    public void setClasspath(Path path) {
        createClasspath().append(path);
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

    public void setProcessor(String str) {
        this.processor = str;
    }

    public void setUseImplicitFileset(boolean z) {
        this.useImplicitFileset = z;
    }

    public void addConfiguredXMLCatalog(XMLCatalog xMLCatalog) {
        this.xmlCatalog.addConfiguredXMLCatalog(xMLCatalog);
    }

    public void setFileNameParameter(String str) {
        this.fileNameParameter = str;
    }

    public void setFileDirParameter(String str) {
        this.fileDirParameter = str;
    }

    public void setSuppressWarnings(boolean z) {
        this.suppressWarnings = z;
    }

    public boolean getSuppressWarnings() {
        return this.suppressWarnings;
    }

    public void setFailOnTransformationError(boolean z) {
        this.failOnTransformationError = z;
    }

    public void setFailOnError(boolean z) {
        this.failOnError = z;
    }

    public void setFailOnNoResources(boolean z) {
        this.failOnNoResources = z;
    }

    public void addSysproperty(Environment.Variable variable) {
        this.sysProperties.addVariable(variable);
    }

    public void addSyspropertyset(PropertySet propertySet) {
        this.sysProperties.addSyspropertyset(propertySet);
    }

    public TraceConfiguration createTrace() {
        if (this.traceConfiguration == null) {
            this.traceConfiguration = new TraceConfiguration();
            return this.traceConfiguration;
        }
        throw new BuildException("can't have more than one trace configuration");
    }

    public TraceConfiguration getTraceConfiguration() {
        return this.traceConfiguration;
    }

    private void resolveProcessor(String str) throws Exception {
        if (str.equals(PROCESSOR_TRAX)) {
            this.liaison = new TraXLiaison();
        } else {
            this.liaison = (XSLTLiaison) loadClass(str).newInstance();
        }
    }

    private Class loadClass(String str) throws Exception {
        setupLoader();
        AntClassLoader antClassLoader = this.loader;
        if (antClassLoader == null) {
            return Class.forName(str);
        }
        return Class.forName(str, true, antClassLoader);
    }

    private void setupLoader() {
        if (this.classpath != null && this.loader == null) {
            this.loader = getProject().createClassLoader(this.classpath);
            this.loader.setThreadContextLoader();
        }
    }

    public void setOut(File file) {
        this.outFile = file;
    }

    public void setIn(File file) {
        this.inFile = file;
    }

    private void checkDest() {
        if (this.destDir == null) {
            handleError("destdir attributes must be set!");
        }
    }

    private void processResources(Resource resource) {
        FileResource asFileResource;
        Iterator<Resource> it = this.resources.iterator();
        while (it.hasNext()) {
            Resource next = it.next();
            if (next.isExists()) {
                File file = this.baseDir;
                String name = next.getName();
                FileProvider fileProvider = (FileProvider) next.mo14823as(FileProvider.class);
                if (fileProvider != null && (file = (asFileResource = ResourceUtils.asFileResource(fileProvider)).getBaseDir()) == null) {
                    name = asFileResource.getFile().getAbsolutePath();
                }
                process(file, name, this.destDir, resource);
            }
        }
    }

    private void process(File file, String str, File file2, Resource resource) throws BuildException {
        Exception e;
        FileNameMapper fileNameMapper;
        File file3 = null;
        try {
            long lastModified = resource.getLastModified();
            File file4 = new File(file, str);
            if (file4.isDirectory()) {
                log("Skipping " + file4 + " it is a directory.", 3);
                return;
            }
            if (this.mapperElement != null) {
                fileNameMapper = this.mapperElement.getImplementation();
            } else {
                fileNameMapper = new StyleMapper();
            }
            String[] mapFileName = fileNameMapper.mapFileName(str);
            if (!(mapFileName == null || mapFileName.length == 0)) {
                if (mapFileName != null && mapFileName.length <= 1) {
                    File file5 = new File(file2, mapFileName[0]);
                    try {
                        if (this.force || file4.lastModified() > file5.lastModified() || lastModified > file5.lastModified()) {
                            ensureDirectoryFor(file5);
                            log("Processing " + file4 + " to " + file5);
                            configureLiaison(resource);
                            setLiaisonDynamicFileParameters(this.liaison, file4);
                            this.liaison.transform(file4, file5);
                            return;
                        }
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        file3 = file5;
                        log("Failed to process " + this.inFile, 2);
                        if (file3 != null) {
                            file3.delete();
                        }
                        handleTransformationError(e);
                        return;
                    }
                }
                log("Skipping " + this.inFile + " its mapping is ambiguos.", 3);
                return;
            }
            log("Skipping " + this.inFile + " it cannot get mapped to output.", 3);
        } catch (Exception e3) {
            e = e3;
        }
    }

    private void process(File file, File file2, Resource resource) throws BuildException {
        try {
            long lastModified = resource.getLastModified();
            log("In file " + file + " time: " + file.lastModified(), 4);
            log("Out file " + file2 + " time: " + file2.lastModified(), 4);
            log("Style file " + this.xslFile + " time: " + lastModified, 4);
            if (!this.force && file.lastModified() < file2.lastModified() && lastModified < file2.lastModified()) {
                log("Skipping input file " + file + " because it is older than output file " + file2 + " and so is the stylesheet " + resource, 4);
            }
            ensureDirectoryFor(file2);
            log("Processing " + file + " to " + file2, 2);
            configureLiaison(resource);
            setLiaisonDynamicFileParameters(this.liaison, file);
            this.liaison.transform(file, file2);
        } catch (Exception e) {
            log("Failed to process " + file, 2);
            if (file2 != null) {
                file2.delete();
            }
            handleTransformationError(e);
        }
    }

    private void ensureDirectoryFor(File file) throws BuildException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs() && !parentFile.isDirectory()) {
            handleError("Unable to create directory: " + parentFile.getAbsolutePath());
        }
    }

    public Factory getFactory() {
        return this.factory;
    }

    public XMLCatalog getXMLCatalog() {
        this.xmlCatalog.setProject(getProject());
        return this.xmlCatalog;
    }

    public Enumeration getOutputProperties() {
        return this.outputProperties.elements();
    }

    protected XSLTLiaison getLiaison() {
        if (this.liaison == null) {
            String str = this.processor;
            if (str != null) {
                try {
                    resolveProcessor(str);
                } catch (Exception e) {
                    handleError(e);
                }
            } else {
                try {
                    resolveProcessor(PROCESSOR_TRAX);
                } catch (Throwable th) {
                    th.printStackTrace();
                    handleError(th);
                }
            }
        }
        return this.liaison;
    }

    public Param createParam() {
        Param param = new Param();
        this.params.add(param);
        return param;
    }

    /* loaded from: classes2.dex */
    public static class Param {
        private Object ifCond;
        private Project project;
        private String type;
        private Object unlessCond;
        private String name = null;
        private String expression = null;

        public void setProject(Project project) {
            this.project = project;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setExpression(String str) {
            this.expression = str;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getName() throws BuildException {
            String str = this.name;
            if (str != null) {
                return str;
            }
            throw new BuildException("Name attribute is missing.");
        }

        public String getExpression() throws BuildException {
            String str = this.expression;
            if (str != null) {
                return str;
            }
            throw new BuildException("Expression attribute is missing.");
        }

        public String getType() {
            return this.type;
        }

        public void setIf(Object obj) {
            this.ifCond = obj;
        }

        public void setIf(String str) {
            setIf((Object) str);
        }

        public void setUnless(Object obj) {
            this.unlessCond = obj;
        }

        public void setUnless(String str) {
            setUnless((Object) str);
        }

        public boolean shouldUse() {
            PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(this.project);
            return propertyHelper.testIfCondition(this.ifCond) && propertyHelper.testUnlessCondition(this.unlessCond);
        }
    }

    /* loaded from: classes2.dex */
    public enum ParamType {
        STRING,
        BOOLEAN,
        INT,
        LONG,
        DOUBLE,
        XPATH_STRING,
        XPATH_BOOLEAN,
        XPATH_NUMBER,
        XPATH_NODE,
        XPATH_NODESET;
        
        public static final Map<ParamType, QName> XPATH_TYPES;

        static {
            EnumMap enumMap = new EnumMap(ParamType.class);
            enumMap.put((EnumMap) XPATH_STRING, (ParamType) XPathConstants.STRING);
            enumMap.put((EnumMap) XPATH_BOOLEAN, (ParamType) XPathConstants.BOOLEAN);
            enumMap.put((EnumMap) XPATH_NUMBER, (ParamType) XPathConstants.NUMBER);
            enumMap.put((EnumMap) XPATH_NODE, (ParamType) XPathConstants.NODE);
            enumMap.put((EnumMap) XPATH_NODESET, (ParamType) XPathConstants.NODESET);
            XPATH_TYPES = Collections.unmodifiableMap(enumMap);
        }
    }

    public OutputProperty createOutputProperty() {
        OutputProperty outputProperty = new OutputProperty();
        this.outputProperties.addElement(outputProperty);
        return outputProperty;
    }

    /* loaded from: classes2.dex */
    public static class OutputProperty {
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

    @Override // org.apache.tools.ant.Task
    public void init() throws BuildException {
        super.init();
        this.xmlCatalog.setProject(getProject());
        this.xpathFactory = XPathFactory.newInstance();
        this.xpath = this.xpathFactory.newXPath();
        this.xpath.setXPathVariableResolver(new XPathVariableResolver() { // from class: org.apache.tools.ant.taskdefs.XSLTProcess.1
            @Override // javax.xml.xpath.XPathVariableResolver
            public Object resolveVariable(QName qName) {
                return XSLTProcess.this.getProject().getProperty(qName.toString());
            }
        });
    }

    @Deprecated
    protected void configureLiaison(File file) throws BuildException {
        FileResource fileResource = new FileResource();
        fileResource.setProject(getProject());
        fileResource.setFile(file);
        configureLiaison(fileResource);
    }

    protected void configureLiaison(Resource resource) throws BuildException {
        if (!this.stylesheetLoaded || !this.reuseLoadedStylesheet) {
            this.stylesheetLoaded = true;
            try {
                log("Loading stylesheet " + resource, 2);
                if (this.liaison instanceof XSLTLiaison2) {
                    ((XSLTLiaison2) this.liaison).configure(this);
                }
                if (this.liaison instanceof XSLTLiaison3) {
                    ((XSLTLiaison3) this.liaison).setStylesheet(resource);
                } else {
                    FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
                    if (fileProvider != null) {
                        this.liaison.setStylesheet(fileProvider.getFile());
                    } else {
                        handleError(this.liaison.getClass().toString() + " accepts the stylesheet only as a file");
                        return;
                    }
                }
                for (Param param : this.params) {
                    if (param.shouldUse()) {
                        Object evaluateParam = evaluateParam(param);
                        if (this.liaison instanceof XSLTLiaison4) {
                            ((XSLTLiaison4) this.liaison).addParam(param.getName(), evaluateParam);
                        } else {
                            if (evaluateParam != null && !(evaluateParam instanceof String)) {
                                log("XSLTLiaison '" + this.liaison.getClass().getName() + "' supports only String parameters. Converting parameter '" + param.getName() + "' to its String value '" + evaluateParam, 1);
                                this.liaison.addParam(param.getName(), String.valueOf(evaluateParam));
                            }
                            this.liaison.addParam(param.getName(), (String) evaluateParam);
                        }
                    }
                }
            } catch (Exception e) {
                log("Failed to transform using stylesheet " + resource, 2);
                handleTransformationError(e);
            }
        }
    }

    private Object evaluateParam(Param param) throws XPathExpressionException {
        ParamType paramType;
        String type = param.getType();
        String expression = param.getExpression();
        if (type == null || "".equals(type)) {
            paramType = ParamType.STRING;
        } else {
            try {
                paramType = ParamType.valueOf(type);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid XSLT parameter type: " + type, e);
            }
        }
        switch (paramType) {
            case STRING:
                return expression;
            case BOOLEAN:
                return Boolean.valueOf(Boolean.parseBoolean(expression));
            case DOUBLE:
                return Double.valueOf(Double.parseDouble(expression));
            case INT:
                return Integer.valueOf(Integer.parseInt(expression));
            case LONG:
                return Long.valueOf(Long.parseLong(expression));
            default:
                QName qName = ParamType.XPATH_TYPES.get(paramType);
                if (qName != null) {
                    return this.xpath.compile(expression).evaluate((Object) null, qName);
                }
                throw new IllegalArgumentException("Invalid XSLT parameter type: " + type);
        }
    }

    private void setLiaisonDynamicFileParameters(XSLTLiaison xSLTLiaison, File file) throws Exception {
        String str = this.fileNameParameter;
        if (str != null) {
            xSLTLiaison.addParam(str, file.getName());
        }
        if (this.fileDirParameter != null) {
            File file2 = new File(FileUtils.getRelativePath(this.baseDir, file));
            xSLTLiaison.addParam(this.fileDirParameter, file2.getParent() != null ? file2.getParent().replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX) : Consts.f23430h);
        }
    }

    public Factory createFactory() throws BuildException {
        if (this.factory != null) {
            handleError("'factory' element must be unique");
        } else {
            this.factory = new Factory();
        }
        return this.factory;
    }

    protected void handleError(String str) {
        if (!this.failOnError) {
            log(str, 1);
            return;
        }
        throw new BuildException(str, getLocation());
    }

    protected void handleError(Throwable th) {
        if (!this.failOnError) {
            log("Caught an exception: " + th, 1);
            return;
        }
        throw new BuildException(th);
    }

    protected void handleTransformationError(Exception exc) {
        if (!this.failOnError || !this.failOnTransformationError) {
            log("Caught an error during transformation: " + exc, 1);
            return;
        }
        throw new BuildException(exc);
    }

    /* loaded from: classes2.dex */
    public static class Factory {
        private final Vector attributes = new Vector();
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void addAttribute(Attribute attribute) {
            this.attributes.addElement(attribute);
        }

        public Enumeration getAttributes() {
            return this.attributes.elements();
        }

        /* loaded from: classes2.dex */
        public static class Attribute implements DynamicConfigurator {
            private String name;
            private Object value;

            @Override // org.apache.tools.ant.DynamicElement
            public Object createDynamicElement(String str) throws BuildException {
                return null;
            }

            public String getName() {
                return this.name;
            }

            public Object getValue() {
                return this.value;
            }

            @Override // org.apache.tools.ant.DynamicAttribute
            public void setDynamicAttribute(String str, String str2) throws BuildException {
                if ("name".equalsIgnoreCase(str)) {
                    this.name = str2;
                } else if (!SizeSelector.SIZE_KEY.equalsIgnoreCase(str)) {
                    throw new BuildException("Unsupported attribute: " + str);
                } else if ("true".equalsIgnoreCase(str2)) {
                    this.value = Boolean.TRUE;
                } else if ("false".equalsIgnoreCase(str2)) {
                    this.value = Boolean.FALSE;
                } else {
                    try {
                        this.value = new Integer(str2);
                    } catch (NumberFormatException unused) {
                        this.value = str2;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class StyleMapper implements FileNameMapper {
        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setFrom(String str) {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setTo(String str) {
        }

        private StyleMapper() {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public String[] mapFileName(String str) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf > 0) {
                str = str.substring(0, lastIndexOf);
            }
            return new String[]{str + XSLTProcess.this.targetExtension};
        }
    }

    /* loaded from: classes2.dex */
    public final class TraceConfiguration {
        private boolean elements;
        private boolean extension;
        private boolean generation;
        private boolean selection;
        private boolean templates;

        public TraceConfiguration() {
        }

        public void setElements(boolean z) {
            this.elements = z;
        }

        public boolean getElements() {
            return this.elements;
        }

        public void setExtension(boolean z) {
            this.extension = z;
        }

        public boolean getExtension() {
            return this.extension;
        }

        public void setGeneration(boolean z) {
            this.generation = z;
        }

        public boolean getGeneration() {
            return this.generation;
        }

        public void setSelection(boolean z) {
            this.selection = z;
        }

        public boolean getSelection() {
            return this.selection;
        }

        public void setTemplates(boolean z) {
            this.templates = z;
        }

        public boolean getTemplates() {
            return this.templates;
        }

        public OutputStream getOutputStream() {
            return new LogOutputStream(XSLTProcess.this);
        }
    }
}
