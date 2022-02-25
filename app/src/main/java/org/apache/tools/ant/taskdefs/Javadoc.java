package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.taskdefs.optional.sos.SOSCmd;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.apache.tools.ant.util.StringUtils;
import org.slf4j.Marker;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class Javadoc extends Task {
    private static final String LOAD_FRAME = "function loadFrames() {";
    private String noqualifier;
    private static final boolean JAVADOC_5 = !JavaEnvUtils.isJavaVersion("1.4");
    private static final int LOAD_FRAME_LEN = 23;
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    static final String[] SCOPE_ELEMENTS = {"overview", "packages", "types", "constructors", "methods", "fields"};
    private final Commandline cmd = new Commandline();
    private boolean failOnError = false;
    private boolean failOnWarning = false;
    private Path sourcePath = null;
    private File destDir = null;
    private final Vector<SourceFile> sourceFiles = new Vector<>();
    private final Vector<PackageName> packageNames = new Vector<>();
    private final Vector<PackageName> excludePackageNames = new Vector<>(1);
    private boolean author = true;
    private boolean version = true;
    private DocletInfo doclet = null;
    private Path classpath = null;
    private Path bootclasspath = null;
    private String group = null;
    private String packageList = null;
    private final Vector<LinkArgument> links = new Vector<>();
    private final Vector<GroupArgument> groups = new Vector<>();
    private final Vector<Object> tags = new Vector<>();
    private boolean useDefaultExcludes = true;
    private Html doctitle = null;
    private Html header = null;
    private Html footer = null;
    private Html bottom = null;
    private boolean useExternalFile = false;
    private String source = null;
    private boolean linksource = false;
    private boolean breakiterator = false;
    private boolean includeNoSourcePackages = false;
    private String executable = null;
    private boolean docFilesSubDirs = false;
    private String excludeDocFilesSubDir = null;
    private String docEncoding = null;
    private boolean postProcessGeneratedJavadocs = true;
    private final ResourceCollectionContainer nestedSourceFiles = new ResourceCollectionContainer();
    private final Vector<DirSet> packageSets = new Vector<>();

    /* loaded from: classes2.dex */
    public class DocletParam {
        private String name;
        private String value;

        public DocletParam() {
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public static class ExtensionInfo extends ProjectComponent {
        private String name;
        private Path path;

        public void setName(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public void setPath(Path path) {
            Path path2 = this.path;
            if (path2 == null) {
                this.path = path;
            } else {
                path2.append(path);
            }
        }

        public Path getPath() {
            return this.path;
        }

        public Path createPath() {
            if (this.path == null) {
                this.path = new Path(getProject());
            }
            return this.path.createPath();
        }

        public void setPathRef(Reference reference) {
            createPath().setRefid(reference);
        }
    }

    /* loaded from: classes2.dex */
    public class DocletInfo extends ExtensionInfo {
        private final Vector<DocletParam> params = new Vector<>();

        public DocletInfo() {
        }

        public DocletParam createParam() {
            DocletParam docletParam = new DocletParam();
            this.params.addElement(docletParam);
            return docletParam;
        }

        public Enumeration<DocletParam> getParams() {
            return this.params.elements();
        }
    }

    /* loaded from: classes2.dex */
    public static class PackageName {
        private String name;

        public void setName(String str) {
            this.name = str.trim();
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return getName();
        }
    }

    /* loaded from: classes2.dex */
    public static class SourceFile {
        private File file;

        public SourceFile() {
        }

        public SourceFile(File file) {
            this.file = file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public File getFile() {
            return this.file;
        }
    }

    /* loaded from: classes2.dex */
    public static class Html {
        private final StringBuffer text = new StringBuffer();

        public void addText(String str) {
            this.text.append(str);
        }

        public String getText() {
            return this.text.substring(0);
        }
    }

    /* loaded from: classes2.dex */
    public static class AccessType extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"protected", "public", ServiceManagerNative.PACKAGE, "private"};
        }
    }

    /* loaded from: classes2.dex */
    public class ResourceCollectionContainer {
        private final ArrayList<ResourceCollection> rcs = new ArrayList<>();

        public ResourceCollectionContainer() {
        }

        public void add(ResourceCollection resourceCollection) {
            this.rcs.add(resourceCollection);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Iterator<ResourceCollection> iterator() {
            return this.rcs.iterator();
        }
    }

    private void addArgIf(boolean z, String str) {
        if (z) {
            this.cmd.createArgument().setValue(str);
        }
    }

    private void addArgIfNotEmpty(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            log("Warning: Leaving out empty argument '" + str + "'", 1);
            return;
        }
        this.cmd.createArgument().setValue(str);
        this.cmd.createArgument().setValue(str2);
    }

    public void setUseExternalFile(boolean z) {
        this.useExternalFile = z;
    }

    public void setDefaultexcludes(boolean z) {
        this.useDefaultExcludes = z;
    }

    public void setMaxmemory(String str) {
        Commandline.Argument createArgument = this.cmd.createArgument();
        createArgument.setValue("-J-Xmx" + str);
    }

    public void setAdditionalparam(String str) {
        this.cmd.createArgument().setLine(str);
    }

    public Commandline.Argument createArg() {
        return this.cmd.createArgument();
    }

    public void setSourcepath(Path path) {
        Path path2 = this.sourcePath;
        if (path2 == null) {
            this.sourcePath = path;
        } else {
            path2.append(path);
        }
    }

    public Path createSourcepath() {
        if (this.sourcePath == null) {
            this.sourcePath = new Path(getProject());
        }
        return this.sourcePath.createPath();
    }

    public void setSourcepathRef(Reference reference) {
        createSourcepath().setRefid(reference);
    }

    public void setDestdir(File file) {
        this.destDir = file;
        this.cmd.createArgument().setValue("-d");
        this.cmd.createArgument().setFile(this.destDir);
    }

    public void setSourcefiles(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            SourceFile sourceFile = new SourceFile();
            sourceFile.setFile(getProject().resolveFile(nextToken.trim()));
            addSource(sourceFile);
        }
    }

    public void addSource(SourceFile sourceFile) {
        this.sourceFiles.addElement(sourceFile);
    }

    public void setPackagenames(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            PackageName packageName = new PackageName();
            packageName.setName(nextToken);
            addPackage(packageName);
        }
    }

    public void addPackage(PackageName packageName) {
        this.packageNames.addElement(packageName);
    }

    public void setExcludePackageNames(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            PackageName packageName = new PackageName();
            packageName.setName(nextToken);
            addExcludePackage(packageName);
        }
    }

    public void addExcludePackage(PackageName packageName) {
        this.excludePackageNames.addElement(packageName);
    }

    public void setOverview(File file) {
        this.cmd.createArgument().setValue("-overview");
        this.cmd.createArgument().setFile(file);
    }

    public void setPublic(boolean z) {
        addArgIf(z, "-public");
    }

    public void setProtected(boolean z) {
        addArgIf(z, "-protected");
    }

    public void setPackage(boolean z) {
        addArgIf(z, "-package");
    }

    public void setPrivate(boolean z) {
        addArgIf(z, "-private");
    }

    public void setAccess(AccessType accessType) {
        Commandline.Argument createArgument = this.cmd.createArgument();
        createArgument.setValue("-" + accessType.getValue());
    }

    public void setDoclet(String str) {
        if (this.doclet == null) {
            this.doclet = new DocletInfo();
            this.doclet.setProject(getProject());
        }
        this.doclet.setName(str);
    }

    public void setDocletPath(Path path) {
        if (this.doclet == null) {
            this.doclet = new DocletInfo();
            this.doclet.setProject(getProject());
        }
        this.doclet.setPath(path);
    }

    public void setDocletPathRef(Reference reference) {
        if (this.doclet == null) {
            this.doclet = new DocletInfo();
            this.doclet.setProject(getProject());
        }
        this.doclet.createPath().setRefid(reference);
    }

    public DocletInfo createDoclet() {
        if (this.doclet == null) {
            this.doclet = new DocletInfo();
        }
        return this.doclet;
    }

    public void addTaglet(ExtensionInfo extensionInfo) {
        this.tags.addElement(extensionInfo);
    }

    public void setOld(boolean z) {
        log("Javadoc 1.4 doesn't support the -1.1 switch anymore", 1);
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

    public void setBootclasspath(Path path) {
        Path path2 = this.bootclasspath;
        if (path2 == null) {
            this.bootclasspath = path;
        } else {
            path2.append(path);
        }
    }

    public Path createBootclasspath() {
        if (this.bootclasspath == null) {
            this.bootclasspath = new Path(getProject());
        }
        return this.bootclasspath.createPath();
    }

    public void setBootClasspathRef(Reference reference) {
        createBootclasspath().setRefid(reference);
    }

    @Deprecated
    public void setExtdirs(String str) {
        this.cmd.createArgument().setValue("-extdirs");
        this.cmd.createArgument().setValue(str);
    }

    public void setExtdirs(Path path) {
        this.cmd.createArgument().setValue("-extdirs");
        this.cmd.createArgument().setPath(path);
    }

    public void setVerbose(boolean z) {
        addArgIf(z, SOSCmd.FLAG_VERBOSE);
    }

    public void setLocale(String str) {
        this.cmd.createArgument(true).setValue(str);
        this.cmd.createArgument(true).setValue("-locale");
    }

    public void setEncoding(String str) {
        this.cmd.createArgument().setValue("-encoding");
        this.cmd.createArgument().setValue(str);
    }

    public void setVersion(boolean z) {
        this.version = z;
    }

    public void setUse(boolean z) {
        addArgIf(z, "-use");
    }

    public void setAuthor(boolean z) {
        this.author = z;
    }

    public void setSplitindex(boolean z) {
        addArgIf(z, "-splitindex");
    }

    public void setWindowtitle(String str) {
        addArgIfNotEmpty("-windowtitle", str);
    }

    public void setDoctitle(String str) {
        Html html = new Html();
        html.addText(str);
        addDoctitle(html);
    }

    public void addDoctitle(Html html) {
        this.doctitle = html;
    }

    public void setHeader(String str) {
        Html html = new Html();
        html.addText(str);
        addHeader(html);
    }

    public void addHeader(Html html) {
        this.header = html;
    }

    public void setFooter(String str) {
        Html html = new Html();
        html.addText(str);
        addFooter(html);
    }

    public void addFooter(Html html) {
        this.footer = html;
    }

    public void setBottom(String str) {
        Html html = new Html();
        html.addText(str);
        addBottom(html);
    }

    public void addBottom(Html html) {
        this.bottom = html;
    }

    public void setLinkoffline(String str) {
        LinkArgument createLink = createLink();
        createLink.setOffline(true);
        if (str.trim().length() != 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ExpandableTextView.f6958c, false);
            createLink.setHref(stringTokenizer.nextToken());
            if (stringTokenizer.hasMoreTokens()) {
                createLink.setPackagelistLoc(getProject().resolveFile(stringTokenizer.nextToken()));
                return;
            }
            throw new BuildException("The linkoffline attribute must include a URL and a package-list file location separated by a space");
        }
        throw new BuildException("The linkoffline attribute must include a URL and a package-list file location separated by a space");
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public void setLink(String str) {
        createLink().setHref(str);
    }

    public void setNodeprecated(boolean z) {
        addArgIf(z, "-nodeprecated");
    }

    public void setNodeprecatedlist(boolean z) {
        addArgIf(z, "-nodeprecatedlist");
    }

    public void setNotree(boolean z) {
        addArgIf(z, "-notree");
    }

    public void setNoindex(boolean z) {
        addArgIf(z, "-noindex");
    }

    public void setNohelp(boolean z) {
        addArgIf(z, "-nohelp");
    }

    public void setNonavbar(boolean z) {
        addArgIf(z, "-nonavbar");
    }

    public void setSerialwarn(boolean z) {
        addArgIf(z, "-serialwarn");
    }

    public void setStylesheetfile(File file) {
        this.cmd.createArgument().setValue("-stylesheetfile");
        this.cmd.createArgument().setFile(file);
    }

    public void setHelpfile(File file) {
        this.cmd.createArgument().setValue("-helpfile");
        this.cmd.createArgument().setFile(file);
    }

    public void setDocencoding(String str) {
        this.cmd.createArgument().setValue("-docencoding");
        this.cmd.createArgument().setValue(str);
        this.docEncoding = str;
    }

    public void setPackageList(String str) {
        this.packageList = str;
    }

    public LinkArgument createLink() {
        LinkArgument linkArgument = new LinkArgument();
        this.links.addElement(linkArgument);
        return linkArgument;
    }

    /* loaded from: classes2.dex */
    public class LinkArgument {
        private String href;
        private File packagelistLoc;
        private URL packagelistURL;
        private boolean offline = false;
        private boolean resolveLink = false;

        public LinkArgument() {
        }

        public void setHref(String str) {
            this.href = str;
        }

        public String getHref() {
            return this.href;
        }

        public void setPackagelistLoc(File file) {
            this.packagelistLoc = file;
        }

        public File getPackagelistLoc() {
            return this.packagelistLoc;
        }

        public void setPackagelistURL(URL url) {
            this.packagelistURL = url;
        }

        public URL getPackagelistURL() {
            return this.packagelistURL;
        }

        public void setOffline(boolean z) {
            this.offline = z;
        }

        public boolean isLinkOffline() {
            return this.offline;
        }

        public void setResolveLink(boolean z) {
            this.resolveLink = z;
        }

        public boolean shouldResolveLink() {
            return this.resolveLink;
        }
    }

    public TagArgument createTag() {
        TagArgument tagArgument = new TagArgument();
        this.tags.addElement(tagArgument);
        return tagArgument;
    }

    /* loaded from: classes2.dex */
    public class TagArgument extends FileSet {
        private String name = null;
        private boolean enabled = true;
        private String scope = "a";

        public TagArgument() {
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setScope(String str) throws BuildException {
            String lowerCase = str.toLowerCase(Locale.ENGLISH);
            boolean[] zArr = new boolean[Javadoc.SCOPE_ELEMENTS.length];
            StringTokenizer stringTokenizer = new StringTokenizer(lowerCase, ",");
            boolean z = false;
            boolean z2 = false;
            while (stringTokenizer.hasMoreTokens()) {
                String trim = stringTokenizer.nextToken().trim();
                if (trim.equals("all")) {
                    if (z2) {
                        getProject().log("Repeated tag scope element: all", 3);
                    }
                    z2 = true;
                } else {
                    int i = 0;
                    while (i < Javadoc.SCOPE_ELEMENTS.length && !trim.equals(Javadoc.SCOPE_ELEMENTS[i])) {
                        i++;
                    }
                    if (i != Javadoc.SCOPE_ELEMENTS.length) {
                        if (zArr[i]) {
                            Project project = getProject();
                            project.log("Repeated tag scope element: " + trim, 3);
                        }
                        zArr[i] = true;
                        z = true;
                    } else {
                        throw new BuildException("Unrecognised scope element: " + trim);
                    }
                }
            }
            if (z && z2) {
                throw new BuildException("Mixture of \"all\" and other scope elements in tag parameter.");
            } else if (!z && !z2) {
                throw new BuildException("No scope elements specified in tag parameter.");
            } else if (z2) {
                this.scope = "a";
            } else {
                StringBuffer stringBuffer = new StringBuffer(zArr.length);
                for (int i2 = 0; i2 < zArr.length; i2++) {
                    if (zArr[i2]) {
                        stringBuffer.append(Javadoc.SCOPE_ELEMENTS[i2].charAt(0));
                    }
                }
                this.scope = stringBuffer.toString();
            }
        }

        public void setEnabled(boolean z) {
            this.enabled = z;
        }

        public String getParameter() throws BuildException {
            String str = this.name;
            if (str == null || str.equals("")) {
                throw new BuildException("No name specified for custom tag.");
            } else if (getDescription() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.name);
                sb.append(":");
                sb.append(this.enabled ? "" : "X");
                sb.append(this.scope);
                sb.append(":");
                sb.append(getDescription());
                return sb.toString();
            } else if (this.enabled && "a".equals(this.scope)) {
                return this.name;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.name);
                sb2.append(":");
                sb2.append(this.enabled ? "" : "X");
                sb2.append(this.scope);
                return sb2.toString();
            }
        }
    }

    public GroupArgument createGroup() {
        GroupArgument groupArgument = new GroupArgument();
        this.groups.addElement(groupArgument);
        return groupArgument;
    }

    /* loaded from: classes2.dex */
    public class GroupArgument {
        private final Vector<PackageName> packages = new Vector<>();
        private Html title;

        public GroupArgument() {
        }

        public void setTitle(String str) {
            Html html = new Html();
            html.addText(str);
            addTitle(html);
        }

        public void addTitle(Html html) {
            this.title = html;
        }

        public String getTitle() {
            Html html = this.title;
            if (html != null) {
                return html.getText();
            }
            return null;
        }

        public void setPackages(String str) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                PackageName packageName = new PackageName();
                packageName.setName(nextToken);
                addPackage(packageName);
            }
        }

        public void addPackage(PackageName packageName) {
            this.packages.addElement(packageName);
        }

        public String getPackages() {
            StringBuffer stringBuffer = new StringBuffer();
            int size = this.packages.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    stringBuffer.append(":");
                }
                stringBuffer.append(this.packages.elementAt(i).toString());
            }
            return stringBuffer.toString();
        }
    }

    public void setCharset(String str) {
        addArgIfNotEmpty("-charset", str);
    }

    public void setFailonerror(boolean z) {
        this.failOnError = z;
    }

    public void setFailonwarning(boolean z) {
        this.failOnWarning = z;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setExecutable(String str) {
        this.executable = str;
    }

    public void addPackageset(DirSet dirSet) {
        this.packageSets.addElement(dirSet);
    }

    public void addFileset(FileSet fileSet) {
        createSourceFiles().add(fileSet);
    }

    public ResourceCollectionContainer createSourceFiles() {
        return this.nestedSourceFiles;
    }

    public void setLinksource(boolean z) {
        this.linksource = z;
    }

    public void setBreakiterator(boolean z) {
        this.breakiterator = z;
    }

    public void setNoqualifier(String str) {
        this.noqualifier = str;
    }

    public void setIncludeNoSourcePackages(boolean z) {
        this.includeNoSourcePackages = z;
    }

    public void setDocFilesSubDirs(boolean z) {
        this.docFilesSubDirs = z;
    }

    public void setExcludeDocFilesSubDir(String str) {
        this.excludeDocFilesSubDir = str;
    }

    public void setPostProcessGeneratedJavadocs(boolean z) {
        this.postProcessGeneratedJavadocs = z;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Throwable th;
        File file;
        IOException e;
        FileWriter fileWriter;
        File file2;
        BufferedWriter bufferedWriter;
        FileWriter fileWriter2;
        checkTaskName();
        Vector<String> vector = new Vector<>();
        Path path = new Path(getProject());
        checkPackageAndSourcePath();
        Path path2 = this.sourcePath;
        if (path2 != null) {
            path.addExisting(path2);
        }
        parsePackages(vector, path);
        checkPackages(vector, path);
        Vector<SourceFile> vector2 = (Vector) this.sourceFiles.clone();
        addSourceFiles(vector2);
        checkPackagesToDoc(vector, vector2);
        log("Generating Javadoc", 2);
        Commandline commandline = (Commandline) this.cmd.clone();
        String str = this.executable;
        if (str != null) {
            commandline.setExecutable(str);
        } else {
            commandline.setExecutable(JavaEnvUtils.getJdkExecutable("javadoc"));
        }
        generalJavadocArguments(commandline);
        doSourcePath(commandline, path);
        doDoclet(commandline);
        doBootPath(commandline);
        doLinks(commandline);
        doGroup(commandline);
        doGroups(commandline);
        doDocFilesSubDirs(commandline);
        doJava14(commandline);
        if (this.breakiterator && (this.doclet == null || JAVADOC_5)) {
            commandline.createArgument().setValue("-breakiterator");
        }
        if (this.useExternalFile) {
            writeExternalArgs(commandline);
        }
        FileWriter fileWriter3 = null;
        try {
            try {
                if (this.useExternalFile) {
                    file = FILE_UTILS.createTempFile("javadoc", "", null, true, true);
                    try {
                        commandline.createArgument().setValue("@" + file.getAbsolutePath());
                        fileWriter2 = new FileWriter(file.getAbsolutePath(), true);
                    } catch (IOException e2) {
                        e = e2;
                    }
                    try {
                        bufferedWriter = new BufferedWriter(fileWriter2);
                        file2 = file;
                        fileWriter = fileWriter2;
                    } catch (IOException e3) {
                        e = e3;
                        fileWriter3 = fileWriter2;
                        file.delete();
                        throw new BuildException("Error creating temporary file", e, getLocation());
                    } catch (Throwable th2) {
                        th = th2;
                        fileWriter3 = fileWriter2;
                        FileUtils.close(fileWriter3);
                        throw th;
                    }
                } else {
                    bufferedWriter = null;
                    file2 = null;
                    fileWriter = null;
                }
            } catch (IOException e4) {
                e = e4;
                file = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            doSourceAndPackageNames(commandline, vector, vector2, this.useExternalFile, file2, bufferedWriter);
            if (this.useExternalFile) {
                bufferedWriter.flush();
            }
            FileUtils.close(fileWriter);
            if (this.packageList != null) {
                commandline.createArgument().setValue("@" + this.packageList);
            }
            log(commandline.describeCommand(), 3);
            log("Javadoc execution", 2);
            JavadocOutputStream javadocOutputStream = new JavadocOutputStream(2);
            JavadocOutputStream javadocOutputStream2 = new JavadocOutputStream(1);
            Execute execute = new Execute(new PumpStreamHandler(javadocOutputStream, javadocOutputStream2));
            execute.setAntRun(getProject());
            execute.setWorkingDirectory(null);
            try {
                try {
                    execute.setCommandline(commandline.getCommandline());
                    int execute2 = execute.execute();
                    if (execute2 != 0 && this.failOnError) {
                        throw new BuildException("Javadoc returned " + execute2, getLocation());
                    }
                    if (javadocOutputStream.sawWarnings() && this.failOnWarning) {
                        throw new BuildException("Javadoc issued warnings.", getLocation());
                    }
                    postProcessGeneratedJavadocs();
                } finally {
                    if (file2 != null) {
                        file2.delete();
                    }
                    javadocOutputStream.logFlush();
                    javadocOutputStream2.logFlush();
                    try {
                        javadocOutputStream.close();
                        javadocOutputStream2.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException e5) {
                throw new BuildException("Javadoc failed: " + e5, e5, getLocation());
            }
        } catch (IOException e6) {
            e = e6;
            file = file2;
            fileWriter3 = fileWriter;
            file.delete();
            throw new BuildException("Error creating temporary file", e, getLocation());
        } catch (Throwable th4) {
            th = th4;
            fileWriter3 = fileWriter;
            FileUtils.close(fileWriter3);
            throw th;
        }
    }

    private void checkTaskName() {
        if ("javadoc2".equals(getTaskType())) {
            log("Warning: the task name <javadoc2> is deprecated. Use <javadoc> instead.", 1);
        }
    }

    private void checkPackageAndSourcePath() {
        if (this.packageList != null && this.sourcePath == null) {
            throw new BuildException("sourcePath attribute must be set when specifying packagelist.");
        }
    }

    private void checkPackages(Vector<String> vector, Path path) {
        if (vector.size() != 0 && path.size() == 0) {
            throw new BuildException("sourcePath attribute must be set when specifying package names.");
        }
    }

    private void checkPackagesToDoc(Vector<String> vector, Vector<SourceFile> vector2) {
        if (this.packageList == null && vector.size() == 0 && vector2.size() == 0) {
            throw new BuildException("No source files and no packages have been specified.");
        }
    }

    private void doSourcePath(Commandline commandline, Path path) {
        if (path.size() > 0) {
            commandline.createArgument().setValue("-sourcepath");
            commandline.createArgument().setPath(path);
        }
    }

    private void generalJavadocArguments(Commandline commandline) {
        if (this.doctitle != null) {
            commandline.createArgument().setValue("-doctitle");
            commandline.createArgument().setValue(expand(this.doctitle.getText()));
        }
        if (this.header != null) {
            commandline.createArgument().setValue("-header");
            commandline.createArgument().setValue(expand(this.header.getText()));
        }
        if (this.footer != null) {
            commandline.createArgument().setValue("-footer");
            commandline.createArgument().setValue(expand(this.footer.getText()));
        }
        if (this.bottom != null) {
            commandline.createArgument().setValue("-bottom");
            commandline.createArgument().setValue(expand(this.bottom.getText()));
        }
        Path path = this.classpath;
        if (path == null) {
            this.classpath = new Path(getProject()).concatSystemClasspath("last");
        } else {
            this.classpath = path.concatSystemClasspath(Definer.OnError.POLICY_IGNORE);
        }
        if (this.classpath.size() > 0) {
            commandline.createArgument().setValue("-classpath");
            commandline.createArgument().setPath(this.classpath);
        }
        if (this.version && this.doclet == null) {
            commandline.createArgument().setValue("-version");
        }
        if (this.author && this.doclet == null) {
            commandline.createArgument().setValue("-author");
        }
        if (this.doclet == null && this.destDir == null) {
            throw new BuildException("destdir attribute must be set!");
        }
    }

    private void doDoclet(Commandline commandline) {
        DocletInfo docletInfo = this.doclet;
        if (docletInfo == null) {
            return;
        }
        if (docletInfo.getName() != null) {
            commandline.createArgument().setValue("-doclet");
            commandline.createArgument().setValue(this.doclet.getName());
            if (this.doclet.getPath() != null) {
                Path concatSystemClasspath = this.doclet.getPath().concatSystemClasspath(Definer.OnError.POLICY_IGNORE);
                if (concatSystemClasspath.size() != 0) {
                    commandline.createArgument().setValue("-docletpath");
                    commandline.createArgument().setPath(concatSystemClasspath);
                }
            }
            Enumeration<DocletParam> params = this.doclet.getParams();
            while (params.hasMoreElements()) {
                DocletParam nextElement = params.nextElement();
                if (nextElement.getName() != null) {
                    commandline.createArgument().setValue(nextElement.getName());
                    if (nextElement.getValue() != null) {
                        commandline.createArgument().setValue(nextElement.getValue());
                    }
                } else {
                    throw new BuildException("Doclet parameters must have a name");
                }
            }
            return;
        }
        throw new BuildException("The doclet name must be specified.", getLocation());
    }

    private void writeExternalArgs(Commandline commandline) {
        Throwable th;
        IOException e;
        File file;
        BufferedWriter bufferedWriter = null;
        try {
            try {
                file = FILE_UTILS.createTempFile("javadocOptions", "", null, true, true);
                try {
                    String[] arguments = commandline.getArguments();
                    commandline.clearArgs();
                    commandline.createArgument().setValue("@" + file.getAbsolutePath());
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
                    for (String str : arguments) {
                        try {
                            if (str.startsWith("-J-")) {
                                commandline.createArgument().setValue(str);
                            } else if (str.startsWith("-")) {
                                bufferedWriter2.write(str);
                                bufferedWriter2.write(ExpandableTextView.f6958c);
                            } else {
                                bufferedWriter2.write(quoteString(str));
                                bufferedWriter2.newLine();
                            }
                        } catch (IOException e2) {
                            e = e2;
                            if (file != null) {
                                file.delete();
                            }
                            throw new BuildException("Error creating or writing temporary file for javadoc options", e, getLocation());
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedWriter = bufferedWriter2;
                            FileUtils.close(bufferedWriter);
                            throw th;
                        }
                    }
                    bufferedWriter2.close();
                    FileUtils.close(bufferedWriter2);
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e4) {
            e = e4;
            file = null;
        }
    }

    private void doBootPath(Commandline commandline) {
        Path path = new Path(getProject());
        Path path2 = this.bootclasspath;
        if (path2 != null) {
            path.append(path2);
        }
        Path concatSystemBootClasspath = path.concatSystemBootClasspath(Definer.OnError.POLICY_IGNORE);
        if (concatSystemBootClasspath.size() > 0) {
            commandline.createArgument().setValue("-bootclasspath");
            commandline.createArgument().setPath(concatSystemBootClasspath);
        }
    }

    private void doLinks(Commandline commandline) {
        if (this.links.size() != 0) {
            Enumeration<LinkArgument> elements = this.links.elements();
            while (elements.hasMoreElements()) {
                LinkArgument nextElement = elements.nextElement();
                if (nextElement.getHref() == null || nextElement.getHref().length() == 0) {
                    log("No href was given for the link - skipping", 3);
                } else {
                    String str = null;
                    if (nextElement.shouldResolveLink()) {
                        File resolveFile = getProject().resolveFile(nextElement.getHref());
                        if (resolveFile.exists()) {
                            try {
                                str = FILE_UTILS.getFileURL(resolveFile).toExternalForm();
                            } catch (MalformedURLException unused) {
                                log("Warning: link location was invalid " + resolveFile, 1);
                            }
                        }
                    }
                    if (str == null) {
                        try {
                            new URL(new URL("file://."), nextElement.getHref());
                            str = nextElement.getHref();
                        } catch (MalformedURLException unused2) {
                            log("Link href \"" + nextElement.getHref() + "\" is not a valid url - skipping link", 1);
                        }
                    }
                    if (nextElement.isLinkOffline()) {
                        File packagelistLoc = nextElement.getPackagelistLoc();
                        URL packagelistURL = nextElement.getPackagelistURL();
                        if (packagelistLoc == null && packagelistURL == null) {
                            throw new BuildException("The package list location for link " + nextElement.getHref() + " must be provided because the link is offline");
                        }
                        if (packagelistLoc != null) {
                            if (new File(packagelistLoc, "package-list").exists()) {
                                try {
                                    packagelistURL = FILE_UTILS.getFileURL(packagelistLoc);
                                } catch (MalformedURLException unused3) {
                                    log("Warning: Package list location was invalid " + packagelistLoc, 1);
                                }
                            } else {
                                log("Warning: No package list was found at " + packagelistLoc, 3);
                            }
                        }
                        if (packagelistURL != null) {
                            commandline.createArgument().setValue("-linkoffline");
                            commandline.createArgument().setValue(str);
                            commandline.createArgument().setValue(packagelistURL.toExternalForm());
                        }
                    } else {
                        commandline.createArgument().setValue("-link");
                        commandline.createArgument().setValue(str);
                    }
                }
            }
        }
    }

    private void doGroup(Commandline commandline) {
        String str = this.group;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",", false);
            while (stringTokenizer.hasMoreTokens()) {
                String trim = stringTokenizer.nextToken().trim();
                int indexOf = trim.indexOf(ExpandableTextView.f6958c);
                if (indexOf > 0) {
                    String substring = trim.substring(0, indexOf);
                    String substring2 = trim.substring(indexOf + 1);
                    commandline.createArgument().setValue("-group");
                    commandline.createArgument().setValue(substring);
                    commandline.createArgument().setValue(substring2);
                }
            }
        }
    }

    private void doGroups(Commandline commandline) {
        if (this.groups.size() != 0) {
            Enumeration<GroupArgument> elements = this.groups.elements();
            while (elements.hasMoreElements()) {
                GroupArgument nextElement = elements.nextElement();
                String title = nextElement.getTitle();
                String packages = nextElement.getPackages();
                if (title == null || packages == null) {
                    throw new BuildException("The title and packages must be specified for group elements.");
                }
                commandline.createArgument().setValue("-group");
                commandline.createArgument().setValue(expand(title));
                commandline.createArgument().setValue(packages);
            }
        }
    }

    private void doJava14(Commandline commandline) {
        File file;
        Enumeration<Object> elements = this.tags.elements();
        while (elements.hasMoreElements()) {
            Object nextElement = elements.nextElement();
            if (nextElement instanceof TagArgument) {
                TagArgument tagArgument = (TagArgument) nextElement;
                File dir = tagArgument.getDir(getProject());
                if (dir == null) {
                    commandline.createArgument().setValue("-tag");
                    commandline.createArgument().setValue(tagArgument.getParameter());
                } else {
                    for (String str : tagArgument.getDirectoryScanner(getProject()).getIncludedFiles()) {
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(dir, str)));
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                commandline.createArgument().setValue("-tag");
                                commandline.createArgument().setValue(readLine);
                            }
                            bufferedReader.close();
                        } catch (IOException e) {
                            throw new BuildException("Couldn't read  tag file from " + file.getAbsolutePath(), e);
                        }
                    }
                    continue;
                }
            } else {
                ExtensionInfo extensionInfo = (ExtensionInfo) nextElement;
                commandline.createArgument().setValue("-taglet");
                commandline.createArgument().setValue(extensionInfo.getName());
                if (extensionInfo.getPath() != null) {
                    Path concatSystemClasspath = extensionInfo.getPath().concatSystemClasspath(Definer.OnError.POLICY_IGNORE);
                    if (concatSystemClasspath.size() != 0) {
                        commandline.createArgument().setValue("-tagletpath");
                        commandline.createArgument().setPath(concatSystemClasspath);
                    }
                }
            }
        }
        String str2 = this.source;
        if (str2 == null) {
            str2 = getProject().getProperty(MagicNames.BUILD_JAVAC_SOURCE);
        }
        if (str2 != null) {
            commandline.createArgument().setValue("-source");
            commandline.createArgument().setValue(str2);
        }
        if (this.linksource && this.doclet == null) {
            commandline.createArgument().setValue("-linksource");
        }
        if (this.noqualifier != null && this.doclet == null) {
            commandline.createArgument().setValue("-noqualifier");
            commandline.createArgument().setValue(this.noqualifier);
        }
    }

    private void doDocFilesSubDirs(Commandline commandline) {
        if (this.docFilesSubDirs) {
            commandline.createArgument().setValue("-docfilessubdirs");
            String str = this.excludeDocFilesSubDir;
            if (str != null && str.trim().length() > 0) {
                commandline.createArgument().setValue("-excludedocfilessubdir");
                commandline.createArgument().setValue(this.excludeDocFilesSubDir);
            }
        }
    }

    private void doSourceAndPackageNames(Commandline commandline, Vector<String> vector, Vector<SourceFile> vector2, boolean z, File file, BufferedWriter bufferedWriter) throws IOException {
        Iterator<String> it = vector.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (z) {
                bufferedWriter.write(next);
                bufferedWriter.newLine();
            } else {
                commandline.createArgument().setValue(next);
            }
        }
        Iterator<SourceFile> it2 = vector2.iterator();
        while (it2.hasNext()) {
            String absolutePath = it2.next().getFile().getAbsolutePath();
            if (z) {
                if (absolutePath.indexOf(ExpandableTextView.f6958c) > -1) {
                    if (File.separatorChar == '\\') {
                        absolutePath = absolutePath.replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX);
                    }
                    bufferedWriter.write("\"" + absolutePath + "\"");
                } else {
                    bufferedWriter.write(absolutePath);
                }
                bufferedWriter.newLine();
            } else {
                commandline.createArgument().setValue(absolutePath);
            }
        }
    }

    private String quoteString(String str) {
        if (!containsWhitespace(str) && str.indexOf(39) == -1 && str.indexOf(34) == -1) {
            return str;
        }
        if (str.indexOf(39) == -1) {
            return quoteString(str, '\'');
        }
        return quoteString(str, Typography.f21049a);
    }

    private boolean containsWhitespace(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private String quoteString(String str, char c) {
        StringBuffer stringBuffer = new StringBuffer(str.length() * 2);
        stringBuffer.append(c);
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == c) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append(charAt);
                z = false;
            } else if (charAt == '\n') {
                if (!z) {
                    stringBuffer.append("\\\n");
                } else {
                    stringBuffer.append("\n");
                }
                z = false;
            } else if (charAt == '\r') {
                stringBuffer.append("\\\r");
                z = true;
            } else if (charAt != '\\') {
                stringBuffer.append(charAt);
                z = false;
            } else {
                stringBuffer.append("\\\\");
                z = false;
            }
        }
        stringBuffer.append(c);
        return stringBuffer.toString();
    }

    private void addSourceFiles(Vector<SourceFile> vector) {
        Iterator it = this.nestedSourceFiles.iterator();
        while (it.hasNext()) {
            ResourceCollection resourceCollection = (ResourceCollection) it.next();
            if (resourceCollection.isFilesystemOnly()) {
                boolean z = resourceCollection instanceof FileSet;
                ResourceCollection<Resource> resourceCollection2 = resourceCollection;
                if (z) {
                    FileSet fileSet = (FileSet) resourceCollection;
                    resourceCollection2 = resourceCollection;
                    if (!fileSet.hasPatterns()) {
                        resourceCollection2 = resourceCollection;
                        if (!fileSet.hasSelectors()) {
                            FileSet fileSet2 = (FileSet) fileSet.clone();
                            fileSet2.createInclude().setName("**/*.java");
                            resourceCollection2 = fileSet2;
                            if (this.includeNoSourcePackages) {
                                fileSet2.createInclude().setName("**/package.html");
                                resourceCollection2 = fileSet2;
                            }
                        }
                    }
                }
                for (Resource resource : resourceCollection2) {
                    vector.addElement(new SourceFile(((FileProvider) resource.mo14823as(FileProvider.class)).getFile()));
                }
            } else {
                throw new BuildException("only file system based resources are supported by javadoc");
            }
        }
    }

    private void parsePackages(Vector<String> vector, Path path) {
        HashSet hashSet = new HashSet();
        Vector vector2 = (Vector) this.packageSets.clone();
        if (this.sourcePath != null) {
            PatternSet patternSet = new PatternSet();
            patternSet.setProject(getProject());
            if (this.packageNames.size() > 0) {
                Enumeration<PackageName> elements = this.packageNames.elements();
                while (elements.hasMoreElements()) {
                    String replace = elements.nextElement().getName().replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX);
                    if (replace.endsWith(Marker.ANY_MARKER)) {
                        replace = replace + Marker.ANY_MARKER;
                    }
                    patternSet.createInclude().setName(replace);
                }
            } else {
                patternSet.createInclude().setName(SelectorUtils.DEEP_TREE_MATCH);
            }
            Enumeration<PackageName> elements2 = this.excludePackageNames.elements();
            while (elements2.hasMoreElements()) {
                String replace2 = elements2.nextElement().getName().replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX);
                if (replace2.endsWith(Marker.ANY_MARKER)) {
                    replace2 = replace2 + Marker.ANY_MARKER;
                }
                patternSet.createExclude().setName(replace2);
            }
            String[] list = this.sourcePath.list();
            for (int i = 0; i < list.length; i++) {
                File file = new File(list[i]);
                if (file.isDirectory()) {
                    DirSet dirSet = new DirSet();
                    dirSet.setProject(getProject());
                    dirSet.setDefaultexcludes(this.useDefaultExcludes);
                    dirSet.setDir(file);
                    dirSet.createPatternSet().addConfiguredPatternset(patternSet);
                    vector2.addElement(dirSet);
                } else {
                    log("Skipping " + list[i] + " since it is no directory.", 1);
                }
            }
        }
        Enumeration elements3 = vector2.elements();
        while (elements3.hasMoreElements()) {
            DirSet dirSet2 = (DirSet) elements3.nextElement();
            File dir = dirSet2.getDir(getProject());
            log("scanning " + dir + " for packages.", 4);
            String[] includedDirectories = dirSet2.getDirectoryScanner(getProject()).getIncludedDirectories();
            boolean z = false;
            for (int i2 = 0; i2 < includedDirectories.length; i2++) {
                if (new File(dir, includedDirectories[i2]).list(new FilenameFilter() { // from class: org.apache.tools.ant.taskdefs.Javadoc.1
                    @Override // java.io.FilenameFilter
                    public boolean accept(File file2, String str) {
                        return str.endsWith(".java") || (Javadoc.this.includeNoSourcePackages && str.equals("package.html"));
                    }
                }).length > 0) {
                    if ("".equals(includedDirectories[i2])) {
                        log(dir + " contains source files in the default package, you must specify them as source files not packages.", 1);
                    } else {
                        String replace3 = includedDirectories[i2].replace(File.separatorChar, FilenameUtils.EXTENSION_SEPARATOR);
                        if (!hashSet.contains(replace3)) {
                            hashSet.add(replace3);
                            vector.addElement(replace3);
                        }
                        z = true;
                    }
                }
            }
            if (z) {
                path.createPathElement().setLocation(dir);
            } else {
                log(dir + " doesn't contain any packages, dropping it.", 3);
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    private void postProcessGeneratedJavadocs() throws IOException {
        if (this.postProcessGeneratedJavadocs) {
            File file = this.destDir;
            if (file == null || file.isDirectory()) {
                InputStream resourceAsStream = Javadoc.class.getResourceAsStream("javadoc-frame-injections-fix.txt");
                if (resourceAsStream != null) {
                    try {
                        String trim = fixLineFeeds(FileUtils.readFully(new InputStreamReader(resourceAsStream, "US-ASCII"))).trim();
                        FileUtils.close(resourceAsStream);
                        DirectoryScanner directoryScanner = new DirectoryScanner();
                        directoryScanner.setBasedir(this.destDir);
                        directoryScanner.setCaseSensitive(false);
                        directoryScanner.setIncludes(new String[]{"**/index.html", "**/index.htm", "**/toc.html", "**/toc.htm"});
                        directoryScanner.addDefaultExcludes();
                        directoryScanner.scan();
                        int i = 0;
                        for (String str : directoryScanner.getIncludedFiles()) {
                            i += postProcess(new File(this.destDir, str), trim);
                        }
                        if (i > 0) {
                            log("Patched " + i + " link injection vulnerable javadocs", 2);
                        }
                    } catch (Throwable th) {
                        FileUtils.close(resourceAsStream);
                        throw th;
                    }
                } else {
                    throw new FileNotFoundException("Missing resource 'javadoc-frame-injections-fix.txt' in classpath.");
                }
            } else {
                log("No javadoc created, no need to post-process anything", 3);
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    private int postProcess(File file, String str) throws IOException {
        String str2 = this.docEncoding;
        if (str2 == null) {
            str2 = FILE_UTILS.getDefaultEncoding();
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            String fixLineFeeds = fixLineFeeds(FileUtils.safeReadFully(new InputStreamReader(fileInputStream, str2)));
            FileUtils.close(fileInputStream);
            if (fixLineFeeds.indexOf("function validURL(url) {") >= 0) {
                return 0;
            }
            String patchContent = patchContent(fixLineFeeds, str);
            if (patchContent.equals(fixLineFeeds)) {
                return 0;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, str2);
                outputStreamWriter.write(patchContent);
                outputStreamWriter.close();
                return 1;
            } finally {
                FileUtils.close(fileOutputStream);
            }
        } catch (Throwable th) {
            FileUtils.close(fileInputStream);
            throw th;
        }
    }

    private String fixLineFeeds(String str) {
        return str.replace("\r\n", "\n").replace("\n", StringUtils.LINE_SEP);
    }

    private String patchContent(String str, String str2) {
        int indexOf = str.indexOf(LOAD_FRAME);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(0, indexOf) + str2 + str.substring(indexOf + LOAD_FRAME_LEN);
    }

    /* loaded from: classes2.dex */
    private class JavadocOutputStream extends LogOutputStream {
        private String queuedLine = null;
        private boolean sawWarnings = false;

        JavadocOutputStream(int i) {
            super((Task) Javadoc.this, i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.tools.ant.taskdefs.LogOutputStream
        public void processLine(String str, int i) {
            if (str.contains("warning")) {
                this.sawWarnings = true;
            }
            if (i != 2 || !str.startsWith("Generating ")) {
                if (this.queuedLine != null) {
                    if (str.startsWith("Building ")) {
                        super.processLine(this.queuedLine, 3);
                    } else {
                        super.processLine(this.queuedLine, 2);
                    }
                    this.queuedLine = null;
                }
                super.processLine(str, i);
                return;
            }
            String str2 = this.queuedLine;
            if (str2 != null) {
                super.processLine(str2, 3);
            }
            this.queuedLine = str;
        }

        protected void logFlush() {
            String str = this.queuedLine;
            if (str != null) {
                super.processLine(str, 3);
                this.queuedLine = null;
            }
        }

        public boolean sawWarnings() {
            return this.sawWarnings;
        }
    }

    protected String expand(String str) {
        return getProject().replaceProperties(str);
    }
}
