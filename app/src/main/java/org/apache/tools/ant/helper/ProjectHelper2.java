package org.apache.tools.ant.helper;

import com.liulishuo.filedownloader.model.ConnectionModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ExtensionPoint;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.launch.Locator;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.URLProvider;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JAXPUtils;
import org.apache.tools.zip.ZipFile;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class ProjectHelper2 extends ProjectHelper {
    private static final String REFID_CONTEXT = "ant.parsing.context";
    public static final String REFID_TARGETS = "ant.targets";
    private static AntHandler elementHandler = new ElementHandler();
    private static AntHandler targetHandler = new TargetHandler();
    private static AntHandler mainHandler = new MainHandler();
    private static AntHandler projectHandler = new ProjectHandler();
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();

    @Override // org.apache.tools.ant.ProjectHelper
    public boolean canParseAntlibDescriptor(Resource resource) {
        return true;
    }

    @Override // org.apache.tools.ant.ProjectHelper
    public UnknownElement parseAntlibDescriptor(Project project, Resource resource) {
        URLProvider uRLProvider = (URLProvider) resource.mo14823as(URLProvider.class);
        if (uRLProvider != null) {
            return parseUnknownElement(project, uRLProvider.getURL());
        }
        throw new BuildException("Unsupported resource type: " + resource);
    }

    public UnknownElement parseUnknownElement(Project project, URL url) throws BuildException {
        Target target = new Target();
        target.setProject(project);
        AntXMLContext antXMLContext = new AntXMLContext(project);
        antXMLContext.addTarget(target);
        antXMLContext.setImplicitTarget(target);
        parse(antXMLContext.getProject(), url, new RootHandler(antXMLContext, elementHandler));
        Task[] tasks = target.getTasks();
        if (tasks.length == 1) {
            return (UnknownElement) tasks[0];
        }
        throw new BuildException("No tasks defined");
    }

    @Override // org.apache.tools.ant.ProjectHelper
    public void parse(Project project, Object obj) throws BuildException {
        getImportStack().addElement(obj);
        AntXMLContext antXMLContext = (AntXMLContext) project.getReference(REFID_CONTEXT);
        if (antXMLContext == null) {
            antXMLContext = new AntXMLContext(project);
            project.addReference(REFID_CONTEXT, antXMLContext);
            project.addReference(REFID_TARGETS, antXMLContext.getTargets());
        }
        if (getImportStack().size() > 1) {
            antXMLContext.setIgnoreProjectTag(true);
            Target currentTarget = antXMLContext.getCurrentTarget();
            Target implicitTarget = antXMLContext.getImplicitTarget();
            Map<String, Target> currentTargets = antXMLContext.getCurrentTargets();
            try {
                Target target = new Target();
                target.setProject(project);
                target.setName("");
                antXMLContext.setCurrentTarget(target);
                antXMLContext.setCurrentTargets(new HashMap());
                antXMLContext.setImplicitTarget(target);
                parse(project, obj, new RootHandler(antXMLContext, mainHandler));
                target.execute();
            } finally {
                antXMLContext.setCurrentTarget(currentTarget);
                antXMLContext.setImplicitTarget(implicitTarget);
                antXMLContext.setCurrentTargets(currentTargets);
            }
        } else {
            antXMLContext.setCurrentTargets(new HashMap());
            parse(project, obj, new RootHandler(antXMLContext, mainHandler));
            antXMLContext.getImplicitTarget().execute();
            resolveExtensionOfAttributes(project);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v7 */
    public void parse(Project project, Object obj, RootHandler rootHandler) throws BuildException {
        ZipFile zipFile;
        File file;
        String str;
        Throwable th;
        SAXParseException e;
        SAXException e2;
        FileNotFoundException e3;
        UnsupportedEncodingException e4;
        IOException e5;
        ZipFile zipFile2;
        InputStream inputStream;
        String str2;
        int indexOf;
        AntXMLContext antXMLContext = rootHandler.context;
        if (obj instanceof File) {
            file = (File) obj;
            zipFile = 0;
        } else if (obj instanceof URL) {
            zipFile = (URL) obj;
            file = null;
        } else {
            if (obj instanceof Resource) {
                Resource resource = (Resource) obj;
                FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
                if (fileProvider != null) {
                    file = fileProvider.getFile();
                    zipFile = 0;
                } else {
                    URLProvider uRLProvider = (URLProvider) resource.mo14823as(URLProvider.class);
                    if (uRLProvider != null) {
                        zipFile = uRLProvider.getURL();
                        file = null;
                    }
                }
            }
            file = null;
            zipFile = 0;
        }
        if (file != null) {
            file = FILE_UTILS.normalize(file.getAbsolutePath());
            antXMLContext.setBuildFile(file);
            str = file.toString();
        } else if (zipFile != 0) {
            try {
                antXMLContext.setBuildFile((File) null);
                antXMLContext.setBuildFile((URL) zipFile);
                str = zipFile.toString();
            } catch (MalformedURLException e6) {
                throw new BuildException(e6);
            }
        } else {
            throw new BuildException("Source " + obj.getClass().getName() + " not supported by this plugin");
        }
        try {
            try {
                XMLReader namespaceXMLReader = JAXPUtils.getNamespaceXMLReader();
                if (file != null) {
                    str2 = FILE_UTILS.toURI(file.getAbsolutePath());
                    zipFile2 = null;
                    inputStream = new FileInputStream(file);
                } else {
                    str2 = zipFile.toString();
                    if (!str2.startsWith("jar:file") || (indexOf = str2.indexOf("!/")) <= -1) {
                        URLConnection openConnection = zipFile.openConnection();
                        openConnection.setUseCaches(false);
                        InputStream inputStream2 = openConnection.getInputStream();
                        zipFile2 = null;
                        inputStream = inputStream2;
                    } else {
                        zipFile2 = new ZipFile(Locator.fromJarURI(str2), "UTF-8");
                        try {
                            inputStream = zipFile2.getInputStream(zipFile2.getEntry(str2.substring(indexOf + 1)));
                        } catch (FileNotFoundException e7) {
                            e3 = e7;
                            throw new BuildException(e3);
                        } catch (UnsupportedEncodingException e8) {
                            e4 = e8;
                            throw new BuildException("Encoding of project file " + str + " is invalid.", e4);
                        } catch (IOException e9) {
                            e5 = e9;
                            throw new BuildException("Error reading project file " + str + ": " + e5.getMessage(), e5);
                        } catch (SAXParseException e10) {
                            e = e10;
                            Location location = new Location(e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
                            Exception exception = e.getException();
                            if (exception instanceof BuildException) {
                                BuildException buildException = (BuildException) exception;
                                if (buildException.getLocation() == Location.UNKNOWN_LOCATION) {
                                    buildException.setLocation(location);
                                }
                                throw buildException;
                            }
                            String message = e.getMessage();
                            if (exception != null) {
                                e = exception;
                            }
                            throw new BuildException(message, e, location);
                        } catch (SAXException e11) {
                            e2 = e11;
                            Exception exception2 = e2.getException();
                            if (!(exception2 instanceof BuildException)) {
                                String message2 = e2.getMessage();
                                if (exception2 != null) {
                                    e2 = exception2;
                                }
                                throw new BuildException(message2, e2);
                            }
                            throw ((BuildException) exception2);
                        }
                    }
                }
                InputSource inputSource = new InputSource(inputStream);
                if (str2 != null) {
                    inputSource.setSystemId(str2);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("parsing buildfile ");
                sb.append(str);
                sb.append(" with URI = ");
                sb.append(str2);
                sb.append(zipFile2 != null ? " from a zip file" : "");
                project.log(sb.toString(), 3);
                namespaceXMLReader.setContentHandler(rootHandler);
                namespaceXMLReader.setEntityResolver(rootHandler);
                namespaceXMLReader.setErrorHandler(rootHandler);
                namespaceXMLReader.setDTDHandler(rootHandler);
                namespaceXMLReader.parse(inputSource);
                FileUtils.close(inputStream);
                ZipFile.closeQuietly(zipFile2);
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close((InputStream) null);
                ZipFile.closeQuietly(zipFile);
                throw th;
            }
        } catch (FileNotFoundException e12) {
            e3 = e12;
        } catch (UnsupportedEncodingException e13) {
            e4 = e13;
        } catch (IOException e14) {
            e5 = e14;
        } catch (SAXParseException e15) {
            e = e15;
        } catch (SAXException e16) {
            e2 = e16;
        } catch (Throwable th3) {
            th = th3;
            zipFile = 0;
            FileUtils.close((InputStream) null);
            ZipFile.closeQuietly(zipFile);
            throw th;
        }
    }

    protected static AntHandler getMainHandler() {
        return mainHandler;
    }

    protected static void setMainHandler(AntHandler antHandler) {
        mainHandler = antHandler;
    }

    protected static AntHandler getProjectHandler() {
        return projectHandler;
    }

    protected static void setProjectHandler(AntHandler antHandler) {
        projectHandler = antHandler;
    }

    protected static AntHandler getTargetHandler() {
        return targetHandler;
    }

    protected static void setTargetHandler(AntHandler antHandler) {
        targetHandler = antHandler;
    }

    protected static AntHandler getElementHandler() {
        return elementHandler;
    }

    protected static void setElementHandler(AntHandler antHandler) {
        elementHandler = antHandler;
    }

    /* loaded from: classes2.dex */
    public static class AntHandler {
        protected void checkNamespace(String str) {
        }

        public void onEndChild(String str, String str2, String str3, AntXMLContext antXMLContext) throws SAXParseException {
        }

        public void onEndElement(String str, String str2, AntXMLContext antXMLContext) {
        }

        public void onStartElement(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
        }

        public AntHandler onStartChild(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            throw new SAXParseException("Unexpected element \"" + str3 + " \"", antXMLContext.getLocator());
        }

        public void characters(char[] cArr, int i, int i2, AntXMLContext antXMLContext) throws SAXParseException {
            String trim = new String(cArr, i, i2).trim();
            if (trim.length() > 0) {
                throw new SAXParseException("Unexpected text \"" + trim + "\"", antXMLContext.getLocator());
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class RootHandler extends DefaultHandler {
        private Stack<AntHandler> antHandlers = new Stack<>();
        private AntXMLContext context;
        private AntHandler currentHandler;

        public RootHandler(AntXMLContext antXMLContext, AntHandler antHandler) {
            this.currentHandler = null;
            this.currentHandler = antHandler;
            this.antHandlers.push(this.currentHandler);
            this.context = antXMLContext;
        }

        public AntHandler getCurrentAntHandler() {
            return this.currentHandler;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String str, String str2) {
            Project project = this.context.getProject();
            project.log("resolving systemId: " + str2, 3);
            if (str2.startsWith("file:")) {
                String fromURI = ProjectHelper2.FILE_UTILS.fromURI(str2);
                File file = new File(fromURI);
                if (!file.isAbsolute()) {
                    file = ProjectHelper2.FILE_UTILS.resolveFile(this.context.getBuildFileParent(), fromURI);
                    Project project2 = this.context.getProject();
                    project2.log("Warning: '" + str2 + "' in " + this.context.getBuildFile() + " should be expressed simply as '" + fromURI.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX) + "' for compliance with other XML tools", 1);
                }
                Project project3 = this.context.getProject();
                project3.log("file=" + file, 4);
                try {
                    InputSource inputSource = new InputSource(new FileInputStream(file));
                    inputSource.setSystemId(ProjectHelper2.FILE_UTILS.toURI(file.getAbsolutePath()));
                    return inputSource;
                } catch (FileNotFoundException unused) {
                    Project project4 = this.context.getProject();
                    project4.log(file.getAbsolutePath() + " could not be found", 1);
                }
            }
            this.context.getProject().log("could not resolve systemId", 4);
            return null;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXParseException {
            AntHandler onStartChild = this.currentHandler.onStartChild(str, str2, str3, attributes, this.context);
            this.antHandlers.push(this.currentHandler);
            this.currentHandler = onStartChild;
            this.currentHandler.onStartElement(str, str2, str3, attributes, this.context);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void setDocumentLocator(org.xml.sax.Locator locator) {
            this.context.setLocator(locator);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            this.currentHandler.onEndElement(str, str2, this.context);
            this.currentHandler = this.antHandlers.pop();
            AntHandler antHandler = this.currentHandler;
            if (antHandler != null) {
                antHandler.onEndChild(str, str2, str3, this.context);
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) throws SAXParseException {
            this.currentHandler.characters(cArr, i, i2, this.context);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startPrefixMapping(String str, String str2) {
            this.context.startPrefixMapping(str, str2);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endPrefixMapping(String str) {
            this.context.endPrefixMapping(str);
        }
    }

    /* loaded from: classes2.dex */
    public static class MainHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            if (str2.equals("project") && (str.equals("") || str.equals(ProjectHelper.ANT_CORE_URI))) {
                return ProjectHelper2.projectHandler;
            }
            if (str2.equals(str3)) {
                throw new SAXParseException("Unexpected element \"{" + str + C4963cj.f20747d + str2 + "\" {" + ProjectHelper.ANT_CORE_URI + C4963cj.f20747d + str2, antXMLContext.getLocator());
            }
            throw new SAXParseException("Unexpected element \"" + str3 + "\" " + str2, antXMLContext.getLocator());
        }
    }

    /* loaded from: classes2.dex */
    public static class ProjectHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onStartElement(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            Object url;
            Object buildFileURL;
            Project project = antXMLContext.getProject();
            antXMLContext.getImplicitTarget().setLocation(new Location(antXMLContext.getLocator()));
            String str4 = null;
            boolean z = false;
            for (int i = 0; i < attributes.getLength(); i++) {
                String uri = attributes.getURI(i);
                if (uri == null || uri.equals("") || uri.equals(str)) {
                    String localName = attributes.getLocalName(i);
                    String value = attributes.getValue(i);
                    if (localName.equals("default")) {
                        if (value != null && !value.equals("") && !antXMLContext.isIgnoringProjectTag()) {
                            project.setDefault(value);
                        }
                    } else if (localName.equals("name")) {
                        if (value != null) {
                            antXMLContext.setCurrentProjectName(value);
                            if (!antXMLContext.isIgnoringProjectTag()) {
                                project.setName(value);
                                project.addReference(value, project);
                            } else if (ProjectHelper.isInIncludeMode() && !"".equals(value) && ProjectHelper.getCurrentTargetPrefix() != null && ProjectHelper.getCurrentTargetPrefix().endsWith(ProjectHelper.USE_PROJECT_NAME_AS_TARGET_PREFIX)) {
                                ProjectHelper.setCurrentTargetPrefix(ProjectHelper.getCurrentTargetPrefix().replace(ProjectHelper.USE_PROJECT_NAME_AS_TARGET_PREFIX, value));
                            }
                            z = true;
                        }
                    } else if (localName.equals(ConnectionModel.f10389a)) {
                        if (value != null && !antXMLContext.isIgnoringProjectTag()) {
                            project.addReference(value, project);
                        }
                    } else if (!localName.equals(MagicNames.PROJECT_BASEDIR)) {
                        throw new SAXParseException("Unexpected attribute \"" + attributes.getQName(i) + "\"", antXMLContext.getLocator());
                    } else if (!antXMLContext.isIgnoringProjectTag()) {
                        str4 = value;
                    }
                }
            }
            String str5 = "ant.file." + antXMLContext.getCurrentProjectName();
            String property = project.getProperty(str5);
            String str6 = "ant.file.type." + antXMLContext.getCurrentProjectName();
            String property2 = project.getProperty(str6);
            if (property != null && z) {
                if ("url".equals(property2)) {
                    try {
                        url = new URL(property);
                        buildFileURL = antXMLContext.getBuildFileURL();
                    } catch (MalformedURLException e) {
                        throw new BuildException("failed to parse " + property + " as URL while looking at a duplicate project name.", e);
                    }
                } else {
                    url = new File(property);
                    buildFileURL = antXMLContext.getBuildFile();
                }
                if (antXMLContext.isIgnoringProjectTag() && !url.equals(buildFileURL)) {
                    project.log("Duplicated project name in import. Project " + antXMLContext.getCurrentProjectName() + " defined first in " + property + " and again in " + buildFileURL, 1);
                }
            }
            if (z) {
                if (antXMLContext.getBuildFile() != null) {
                    project.setUserProperty(str5, antXMLContext.getBuildFile().toString());
                    project.setUserProperty(str6, "file");
                } else if (antXMLContext.getBuildFileURL() != null) {
                    project.setUserProperty(str5, antXMLContext.getBuildFileURL().toString());
                    project.setUserProperty(str6, "url");
                }
            }
            if (!antXMLContext.isIgnoringProjectTag()) {
                if (project.getProperty(MagicNames.PROJECT_BASEDIR) != null) {
                    project.setBasedir(project.getProperty(MagicNames.PROJECT_BASEDIR));
                } else if (str4 == null) {
                    project.setBasedir(antXMLContext.getBuildFileParent().getAbsolutePath());
                } else if (new File(str4).isAbsolute()) {
                    project.setBasedir(str4);
                } else {
                    project.setBaseDir(ProjectHelper2.FILE_UTILS.resolveFile(antXMLContext.getBuildFileParent(), str4));
                }
                project.addTarget("", antXMLContext.getImplicitTarget());
                antXMLContext.setCurrentTarget(antXMLContext.getImplicitTarget());
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            return ((str2.equals("target") || str2.equals("extension-point")) && (str.equals("") || str.equals(ProjectHelper.ANT_CORE_URI))) ? ProjectHelper2.targetHandler : ProjectHelper2.elementHandler;
        }
    }

    /* loaded from: classes2.dex */
    public static class TargetHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onStartElement(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            String str4;
            boolean z;
            Target target;
            String str5 = "";
            Project project = antXMLContext.getProject();
            Target target2 = "target".equals(str2) ? new Target() : new ExtensionPoint();
            target2.setProject(project);
            target2.setLocation(new Location(antXMLContext.getLocator()));
            antXMLContext.addTarget(target2);
            String str6 = null;
            String str7 = null;
            ProjectHelper.OnMissingExtensionPoint onMissingExtensionPoint = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                String uri = attributes.getURI(i);
                if (uri != null && !uri.equals("")) {
                    if (!uri.equals(str)) {
                        continue;
                    }
                }
                String localName = attributes.getLocalName(i);
                String value = attributes.getValue(i);
                if (localName.equals("name")) {
                    if (!"".equals(value)) {
                        str6 = value;
                    } else {
                        throw new BuildException("name attribute must not be empty");
                    }
                } else if (localName.equals("depends")) {
                    str5 = value;
                } else if (localName.equals("if")) {
                    target2.setIf(value);
                } else if (localName.equals("unless")) {
                    target2.setUnless(value);
                } else if (localName.equals(ConnectionModel.f10389a)) {
                    if (value != null && !value.equals("")) {
                        antXMLContext.getProject().addReference(value, target2);
                    }
                } else if (localName.equals("description")) {
                    target2.setDescription(value);
                } else if (localName.equals("extensionOf")) {
                    str7 = value;
                } else if (localName.equals("onMissingExtensionPoint")) {
                    try {
                        onMissingExtensionPoint = ProjectHelper.OnMissingExtensionPoint.valueOf(value);
                    } catch (IllegalArgumentException unused) {
                        throw new BuildException("Invalid onMissingExtensionPoint " + value);
                    }
                } else {
                    throw new SAXParseException("Unexpected attribute \"" + localName + "\"", antXMLContext.getLocator());
                }
            }
            if (str6 != null) {
                boolean z2 = antXMLContext.isIgnoringProjectTag() && ProjectHelper.isInIncludeMode();
                String currentPrefixSeparator = ProjectHelper.getCurrentPrefixSeparator();
                if (z2) {
                    str4 = getTargetPrefix(antXMLContext);
                    if (str4 != null) {
                        str6 = str4 + currentPrefixSeparator + str6;
                    } else {
                        throw new BuildException("can't include build file " + antXMLContext.getBuildFileURL() + ", no as attribute has been given and the project tag doesn't specify a name attribute");
                    }
                } else {
                    str4 = null;
                }
                if (antXMLContext.getCurrentTargets().get(str6) == null) {
                    if (project.getTargets().containsKey(str6)) {
                        project.log("Already defined in main or a previous import, ignore " + str6, 3);
                        z = false;
                    } else {
                        target2.setName(str6);
                        antXMLContext.getCurrentTargets().put(str6, target2);
                        project.addOrReplaceTarget(str6, target2);
                        z = true;
                    }
                    if (str5.length() > 0) {
                        if (!z2) {
                            target2.setDepends(str5);
                        } else {
                            Iterator<String> it = Target.parseDepends(str5, str6, "depends").iterator();
                            while (it.hasNext()) {
                                target2.addDependency(str4 + currentPrefixSeparator + it.next());
                            }
                        }
                    }
                    if (!z2 && antXMLContext.isIgnoringProjectTag() && (str4 = getTargetPrefix(antXMLContext)) != null) {
                        String str8 = str4 + currentPrefixSeparator + str6;
                        if (z) {
                            target = "target".equals(str2) ? new Target(target2) : new ExtensionPoint(target2);
                        } else {
                            target = target2;
                        }
                        target.setName(str8);
                        antXMLContext.getCurrentTargets().put(str8, target);
                        project.addOrReplaceTarget(str8, target);
                    }
                    if (onMissingExtensionPoint != null && str7 == null) {
                        throw new BuildException("onMissingExtensionPoint attribute cannot be specified unless extensionOf is specified", target2.getLocation());
                    } else if (str7 != null) {
                        ProjectHelper projectHelper = (ProjectHelper) antXMLContext.getProject().getReference("ant.projectHelper");
                        for (String str9 : Target.parseDepends(str7, str6, "extensionOf")) {
                            if (onMissingExtensionPoint == null) {
                                onMissingExtensionPoint = ProjectHelper.OnMissingExtensionPoint.FAIL;
                            }
                            if (ProjectHelper.isInIncludeMode()) {
                                projectHelper.getExtensionStack().add(new String[]{str9, target2.getName(), onMissingExtensionPoint.name(), str4 + currentPrefixSeparator});
                            } else {
                                projectHelper.getExtensionStack().add(new String[]{str9, target2.getName(), onMissingExtensionPoint.name()});
                            }
                        }
                    }
                } else {
                    throw new BuildException("Duplicate target '" + str6 + "'", target2.getLocation());
                }
            } else {
                throw new SAXParseException("target element appears without a name attribute", antXMLContext.getLocator());
            }
        }

        private String getTargetPrefix(AntXMLContext antXMLContext) {
            String currentTargetPrefix = ProjectHelper.getCurrentTargetPrefix();
            if (currentTargetPrefix != null && currentTargetPrefix.length() == 0) {
                currentTargetPrefix = null;
            }
            if (currentTargetPrefix != null) {
                return currentTargetPrefix;
            }
            String currentProjectName = antXMLContext.getCurrentProjectName();
            if ("".equals(currentProjectName)) {
                return null;
            }
            return currentProjectName;
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            return ProjectHelper2.elementHandler;
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onEndElement(String str, String str2, AntXMLContext antXMLContext) {
            antXMLContext.setCurrentTarget(antXMLContext.getImplicitTarget());
        }
    }

    /* loaded from: classes2.dex */
    public static class ElementHandler extends AntHandler {
        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onStartElement(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            RuntimeConfigurable currentWrapper = antXMLContext.currentWrapper();
            Object proxy = currentWrapper != null ? currentWrapper.getProxy() : null;
            UnknownElement unknownElement = new UnknownElement(str2);
            unknownElement.setProject(antXMLContext.getProject());
            unknownElement.setNamespace(str);
            unknownElement.setQName(str3);
            unknownElement.setTaskType(ProjectHelper.genComponentName(unknownElement.getNamespace(), str2));
            unknownElement.setTaskName(str3);
            unknownElement.setLocation(new Location(antXMLContext.getLocator().getSystemId(), antXMLContext.getLocator().getLineNumber(), antXMLContext.getLocator().getColumnNumber()));
            unknownElement.setOwningTarget(antXMLContext.getCurrentTarget());
            if (proxy != null) {
                ((UnknownElement) proxy).addChild(unknownElement);
            } else {
                antXMLContext.getCurrentTarget().addTask(unknownElement);
            }
            antXMLContext.configureId(unknownElement, attributes);
            RuntimeConfigurable runtimeConfigurable = new RuntimeConfigurable(unknownElement, unknownElement.getTaskName());
            for (int i = 0; i < attributes.getLength(); i++) {
                String localName = attributes.getLocalName(i);
                String uri = attributes.getURI(i);
                if (uri != null && !uri.equals("") && !uri.equals(str)) {
                    localName = uri + ":" + attributes.getQName(i);
                }
                String value = attributes.getValue(i);
                if (ProjectHelper.ANT_TYPE.equals(localName) || (ProjectHelper.ANT_CORE_URI.equals(uri) && ProjectHelper.ANT_TYPE.equals(attributes.getLocalName(i)))) {
                    localName = ProjectHelper.ANT_TYPE;
                    int indexOf = value.indexOf(":");
                    if (indexOf < 0) {
                        continue;
                    } else {
                        String substring = value.substring(0, indexOf);
                        String prefixMapping = antXMLContext.getPrefixMapping(substring);
                        if (prefixMapping != null) {
                            value = ProjectHelper.genComponentName(prefixMapping, value.substring(indexOf + 1));
                        } else {
                            throw new BuildException("Unable to find XML NS prefix \"" + substring + "\"");
                        }
                    }
                }
                runtimeConfigurable.setAttribute(localName, value);
            }
            if (currentWrapper != null) {
                currentWrapper.addChild(runtimeConfigurable);
            }
            antXMLContext.pushWrapper(runtimeConfigurable);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void characters(char[] cArr, int i, int i2, AntXMLContext antXMLContext) throws SAXParseException {
            antXMLContext.currentWrapper().addText(cArr, i, i2);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public AntHandler onStartChild(String str, String str2, String str3, Attributes attributes, AntXMLContext antXMLContext) throws SAXParseException {
            return ProjectHelper2.elementHandler;
        }

        @Override // org.apache.tools.ant.helper.ProjectHelper2.AntHandler
        public void onEndElement(String str, String str2, AntXMLContext antXMLContext) {
            antXMLContext.popWrapper();
        }
    }
}
