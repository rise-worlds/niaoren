package org.apache.tools.ant.helper;

import com.liulishuo.filedownloader.model.ConnectionModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.TypeAdapter;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.util.FileUtils;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* loaded from: classes2.dex */
public class ProjectHelperImpl extends ProjectHelper {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private File buildFile;
    private File buildFileParent;
    private Target implicitTarget = new Target();
    private Locator locator;
    private Parser parser;
    private Project project;

    public ProjectHelperImpl() {
        this.implicitTarget.setName("");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00df A[Catch: all -> 0x0028, TryCatch #5 {all -> 0x0028, blocks: (B:5:0x0021, B:12:0x003a, B:13:0x0045, B:29:0x00ae, B:30:0x00c8, B:31:0x00c9, B:32:0x00d0, B:33:0x00d1, B:34:0x00d6, B:35:0x00d7, B:37:0x00df, B:38:0x00e1, B:39:0x00e2, B:40:0x00eb, B:41:0x00ec, B:43:0x0105, B:45:0x010f, B:46:0x0112, B:47:0x0113, B:48:0x011c), top: B:55:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e2 A[Catch: all -> 0x0028, TryCatch #5 {all -> 0x0028, blocks: (B:5:0x0021, B:12:0x003a, B:13:0x0045, B:29:0x00ae, B:30:0x00c8, B:31:0x00c9, B:32:0x00d0, B:33:0x00d1, B:34:0x00d6, B:35:0x00d7, B:37:0x00df, B:38:0x00e1, B:39:0x00e2, B:40:0x00eb, B:41:0x00ec, B:43:0x0105, B:45:0x010f, B:46:0x0112, B:47:0x0113, B:48:0x011c), top: B:55:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0105 A[Catch: all -> 0x0028, TryCatch #5 {all -> 0x0028, blocks: (B:5:0x0021, B:12:0x003a, B:13:0x0045, B:29:0x00ae, B:30:0x00c8, B:31:0x00c9, B:32:0x00d0, B:33:0x00d1, B:34:0x00d6, B:35:0x00d7, B:37:0x00df, B:38:0x00e1, B:39:0x00e2, B:40:0x00eb, B:41:0x00ec, B:43:0x0105, B:45:0x010f, B:46:0x0112, B:47:0x0113, B:48:0x011c), top: B:55:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0113 A[Catch: all -> 0x0028, TryCatch #5 {all -> 0x0028, blocks: (B:5:0x0021, B:12:0x003a, B:13:0x0045, B:29:0x00ae, B:30:0x00c8, B:31:0x00c9, B:32:0x00d0, B:33:0x00d1, B:34:0x00d6, B:35:0x00d7, B:37:0x00df, B:38:0x00e1, B:39:0x00e2, B:40:0x00eb, B:41:0x00ec, B:43:0x0105, B:45:0x010f, B:46:0x0112, B:47:0x0113, B:48:0x011c), top: B:55:0x0021 }] */
    @Override // org.apache.tools.ant.ProjectHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parse(org.apache.tools.ant.Project r6, java.lang.Object r7) throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.helper.ProjectHelperImpl.parse(org.apache.tools.ant.Project, java.lang.Object):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class AbstractHandler extends HandlerBase {
        ProjectHelperImpl helperImpl;
        protected DocumentHandler parentHandler;

        public AbstractHandler(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler) {
            this.parentHandler = documentHandler;
            this.helperImpl = projectHelperImpl;
            projectHelperImpl.parser.setDocumentHandler(this);
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String str, AttributeList attributeList) throws SAXParseException {
            throw new SAXParseException("Unexpected element \"" + str + "\"", this.helperImpl.locator);
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] cArr, int i, int i2) throws SAXParseException {
            String trim = new String(cArr, i, i2).trim();
            if (trim.length() > 0) {
                throw new SAXParseException("Unexpected text \"" + trim + "\"", this.helperImpl.locator);
            }
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void endElement(String str) throws SAXException {
            this.helperImpl.parser.setDocumentHandler(this.parentHandler);
        }
    }

    /* loaded from: classes2.dex */
    static class RootHandler extends HandlerBase {
        ProjectHelperImpl helperImpl;

        public RootHandler(ProjectHelperImpl projectHelperImpl) {
            this.helperImpl = projectHelperImpl;
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String str, String str2) {
            Project project = this.helperImpl.project;
            project.log("resolving systemId: " + str2, 3);
            if (!str2.startsWith("file:")) {
                return null;
            }
            String fromURI = ProjectHelperImpl.FILE_UTILS.fromURI(str2);
            File file = new File(fromURI);
            if (!file.isAbsolute()) {
                file = ProjectHelperImpl.FILE_UTILS.resolveFile(this.helperImpl.buildFileParent, fromURI);
                Project project2 = this.helperImpl.project;
                project2.log("Warning: '" + str2 + "' in " + this.helperImpl.buildFile + " should be expressed simply as '" + fromURI.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX) + "' for compliance with other XML tools", 1);
            }
            try {
                InputSource inputSource = new InputSource(new FileInputStream(file));
                inputSource.setSystemId(ProjectHelperImpl.FILE_UTILS.toURI(file.getAbsolutePath()));
                return inputSource;
            } catch (FileNotFoundException unused) {
                Project project3 = this.helperImpl.project;
                project3.log(file.getAbsolutePath() + " could not be found", 1);
                return null;
            }
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String str, AttributeList attributeList) throws SAXParseException {
            if (str.equals("project")) {
                new ProjectHandler(this.helperImpl, this).init(str, attributeList);
                return;
            }
            throw new SAXParseException("Config file is not of expected XML type", this.helperImpl.locator);
        }

        @Override // org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void setDocumentLocator(Locator locator) {
            this.helperImpl.locator = locator;
        }
    }

    /* loaded from: classes2.dex */
    static class ProjectHandler extends AbstractHandler {
        public ProjectHandler(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler) {
            super(projectHelperImpl, documentHandler);
        }

        public void init(String str, AttributeList attributeList) throws SAXParseException {
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            for (int i = 0; i < attributeList.getLength(); i++) {
                String name = attributeList.getName(i);
                String value = attributeList.getValue(i);
                if (name.equals("default")) {
                    str2 = value;
                } else if (name.equals("name")) {
                    str3 = value;
                } else if (name.equals(ConnectionModel.f10389a)) {
                    str4 = value;
                } else if (name.equals(MagicNames.PROJECT_BASEDIR)) {
                    str5 = value;
                } else {
                    throw new SAXParseException("Unexpected attribute \"" + attributeList.getName(i) + "\"", this.helperImpl.locator);
                }
            }
            if (str2 == null || str2.equals("")) {
                throw new BuildException("The default attribute is required");
            }
            this.helperImpl.project.setDefault(str2);
            if (str3 != null) {
                this.helperImpl.project.setName(str3);
                this.helperImpl.project.addReference(str3, this.helperImpl.project);
            }
            if (str4 != null) {
                this.helperImpl.project.addReference(str4, this.helperImpl.project);
            }
            if (this.helperImpl.project.getProperty(MagicNames.PROJECT_BASEDIR) != null) {
                this.helperImpl.project.setBasedir(this.helperImpl.project.getProperty(MagicNames.PROJECT_BASEDIR));
            } else if (str5 == null) {
                this.helperImpl.project.setBasedir(this.helperImpl.buildFileParent.getAbsolutePath());
            } else if (new File(str5).isAbsolute()) {
                this.helperImpl.project.setBasedir(str5);
            } else {
                this.helperImpl.project.setBaseDir(ProjectHelperImpl.FILE_UTILS.resolveFile(this.helperImpl.buildFileParent, str5));
            }
            this.helperImpl.project.addTarget("", this.helperImpl.implicitTarget);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String str, AttributeList attributeList) throws SAXParseException {
            if (str.equals("target")) {
                handleTarget(str, attributeList);
            } else {
                ProjectHelperImpl.handleElement(this.helperImpl, this, this.helperImpl.implicitTarget, str, attributeList);
            }
        }

        private void handleTarget(String str, AttributeList attributeList) throws SAXParseException {
            new TargetHandler(this.helperImpl, this).init(str, attributeList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TargetHandler extends AbstractHandler {
        private Target target;

        public TargetHandler(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler) {
            super(projectHelperImpl, documentHandler);
        }

        public void init(String str, AttributeList attributeList) throws SAXParseException {
            String str2 = null;
            String str3 = "";
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            for (int i = 0; i < attributeList.getLength(); i++) {
                String name = attributeList.getName(i);
                String value = attributeList.getValue(i);
                if (name.equals("name")) {
                    if (!value.equals("")) {
                        str2 = value;
                    } else {
                        throw new BuildException("name attribute must not be empty", new Location(this.helperImpl.locator));
                    }
                } else if (name.equals("depends")) {
                    str3 = value;
                } else if (name.equals("if")) {
                    str4 = value;
                } else if (name.equals("unless")) {
                    str5 = value;
                } else if (name.equals(ConnectionModel.f10389a)) {
                    str7 = value;
                } else if (name.equals("description")) {
                    str6 = value;
                } else {
                    throw new SAXParseException("Unexpected attribute \"" + name + "\"", this.helperImpl.locator);
                }
            }
            if (str2 != null) {
                this.target = new Target();
                this.target.addDependency("");
                this.target.setName(str2);
                this.target.setIf(str4);
                this.target.setUnless(str5);
                this.target.setDescription(str6);
                this.helperImpl.project.addTarget(str2, this.target);
                if (str7 != null && !str7.equals("")) {
                    this.helperImpl.project.addReference(str7, this.target);
                }
                if (str3.length() > 0) {
                    this.target.setDepends(str3);
                    return;
                }
                return;
            }
            throw new SAXParseException("target element appears without a name attribute", this.helperImpl.locator);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String str, AttributeList attributeList) throws SAXParseException {
            ProjectHelperImpl.handleElement(this.helperImpl, this, this.target, str, attributeList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleElement(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler, Target target, String str, AttributeList attributeList) throws SAXParseException {
        if (str.equals("description")) {
            new DescriptionHandler(projectHelperImpl, documentHandler);
        } else if (projectHelperImpl.project.getDataTypeDefinitions().get(str) != null) {
            new DataTypeHandler(projectHelperImpl, documentHandler, target).init(str, attributeList);
        } else {
            new TaskHandler(projectHelperImpl, documentHandler, target, null, target).init(str, attributeList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class DescriptionHandler extends AbstractHandler {
        public DescriptionHandler(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler) {
            super(projectHelperImpl, documentHandler);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] cArr, int i, int i2) {
            String str = new String(cArr, i, i2);
            String description = this.helperImpl.project.getDescription();
            if (description == null) {
                this.helperImpl.project.setDescription(str);
                return;
            }
            Project project = this.helperImpl.project;
            project.setDescription(description + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TaskHandler extends AbstractHandler {
        private TaskContainer container;
        private RuntimeConfigurable parentWrapper;
        private Target target;
        private Task task;
        private RuntimeConfigurable wrapper = null;

        public TaskHandler(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler, TaskContainer taskContainer, RuntimeConfigurable runtimeConfigurable, Target target) {
            super(projectHelperImpl, documentHandler);
            this.container = taskContainer;
            this.parentWrapper = runtimeConfigurable;
            this.target = target;
        }

        public void init(String str, AttributeList attributeList) throws SAXParseException {
            try {
                this.task = this.helperImpl.project.createTask(str);
            } catch (BuildException unused) {
            }
            if (this.task == null) {
                this.task = new UnknownElement(str);
                this.task.setProject(this.helperImpl.project);
                this.task.setTaskName(str);
            }
            this.task.setLocation(new Location(this.helperImpl.locator));
            this.helperImpl.configureId(this.task, attributeList);
            this.task.setOwningTarget(this.target);
            this.container.addTask(this.task);
            this.task.init();
            this.wrapper = this.task.getRuntimeConfigurableWrapper();
            this.wrapper.setAttributes(attributeList);
            RuntimeConfigurable runtimeConfigurable = this.parentWrapper;
            if (runtimeConfigurable != null) {
                runtimeConfigurable.addChild(this.wrapper);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] cArr, int i, int i2) {
            this.wrapper.addText(cArr, i, i2);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String str, AttributeList attributeList) throws SAXParseException {
            if (this.task instanceof TaskContainer) {
                new TaskHandler(this.helperImpl, this, (TaskContainer) this.task, this.wrapper, this.target).init(str, attributeList);
            } else {
                new NestedElementHandler(this.helperImpl, this, this.task, this.wrapper, this.target).init(str, attributeList);
            }
        }
    }

    /* loaded from: classes2.dex */
    static class NestedElementHandler extends AbstractHandler {
        private Object child;
        private RuntimeConfigurable childWrapper = null;
        private Object parent;
        private RuntimeConfigurable parentWrapper;
        private Target target;

        public NestedElementHandler(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler, Object obj, RuntimeConfigurable runtimeConfigurable, Target target) {
            super(projectHelperImpl, documentHandler);
            if (obj instanceof TypeAdapter) {
                this.parent = ((TypeAdapter) obj).getProxy();
            } else {
                this.parent = obj;
            }
            this.parentWrapper = runtimeConfigurable;
            this.target = target;
        }

        public void init(String str, AttributeList attributeList) throws SAXParseException {
            IntrospectionHelper helper = IntrospectionHelper.getHelper(this.helperImpl.project, this.parent.getClass());
            try {
                String lowerCase = str.toLowerCase(Locale.ENGLISH);
                if (this.parent instanceof UnknownElement) {
                    UnknownElement unknownElement = new UnknownElement(lowerCase);
                    unknownElement.setProject(this.helperImpl.project);
                    ((UnknownElement) this.parent).addChild(unknownElement);
                    this.child = unknownElement;
                } else {
                    this.child = helper.createElement(this.helperImpl.project, this.parent, lowerCase);
                }
                this.helperImpl.configureId(this.child, attributeList);
                this.childWrapper = new RuntimeConfigurable(this.child, str);
                this.childWrapper.setAttributes(attributeList);
                this.parentWrapper.addChild(this.childWrapper);
            } catch (BuildException e) {
                throw new SAXParseException(e.getMessage(), this.helperImpl.locator, e);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] cArr, int i, int i2) {
            this.childWrapper.addText(cArr, i, i2);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String str, AttributeList attributeList) throws SAXParseException {
            if (this.child instanceof TaskContainer) {
                new TaskHandler(this.helperImpl, this, (TaskContainer) this.child, this.childWrapper, this.target).init(str, attributeList);
            } else {
                new NestedElementHandler(this.helperImpl, this, this.child, this.childWrapper, this.target).init(str, attributeList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class DataTypeHandler extends AbstractHandler {
        private Object element;
        private Target target;
        private RuntimeConfigurable wrapper = null;

        public DataTypeHandler(ProjectHelperImpl projectHelperImpl, DocumentHandler documentHandler, Target target) {
            super(projectHelperImpl, documentHandler);
            this.target = target;
        }

        public void init(String str, AttributeList attributeList) throws SAXParseException {
            try {
                this.element = this.helperImpl.project.createDataType(str);
                if (this.element != null) {
                    this.wrapper = new RuntimeConfigurable(this.element, str);
                    this.wrapper.setAttributes(attributeList);
                    this.target.addDataType(this.wrapper);
                    return;
                }
                throw new BuildException("Unknown data type " + str);
            } catch (BuildException e) {
                throw new SAXParseException(e.getMessage(), this.helperImpl.locator, e);
            }
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void characters(char[] cArr, int i, int i2) {
            this.wrapper.addText(cArr, i, i2);
        }

        @Override // org.apache.tools.ant.helper.ProjectHelperImpl.AbstractHandler, org.xml.sax.HandlerBase, org.xml.sax.DocumentHandler
        public void startElement(String str, AttributeList attributeList) throws SAXParseException {
            new NestedElementHandler(this.helperImpl, this, this.element, this.wrapper, this.target).init(str, attributeList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configureId(Object obj, AttributeList attributeList) {
        String value = attributeList.getValue(ConnectionModel.f10389a);
        if (value != null) {
            this.project.addReference(value, obj);
        }
    }
}
