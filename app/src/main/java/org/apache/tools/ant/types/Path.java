package org.apache.tools.ant.types;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.PathTokenizer;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.taskdefs.optional.j2ee.HotDeploymentTool;
import org.apache.tools.ant.types.resources.FileResourceIterator;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class Path extends DataType implements Cloneable, ResourceCollection {
    private boolean cache;
    private Boolean preserveBC;
    private Union union;
    public static Path systemClasspath = new Path(null, System.getProperty("java.class.path"));
    public static Path systemBootClasspath = new Path(null, System.getProperty("sun.boot.class.path"));

    /* loaded from: classes2.dex */
    public class PathElement implements ResourceCollection {
        private String[] parts;

        @Override // org.apache.tools.ant.types.ResourceCollection
        public boolean isFilesystemOnly() {
            return true;
        }

        public PathElement() {
        }

        public void setLocation(File file) {
            this.parts = new String[]{Path.translateFile(file.getAbsolutePath())};
        }

        public void setPath(String str) {
            this.parts = Path.translatePath(Path.this.getProject(), str);
        }

        public String[] getParts() {
            return this.parts;
        }

        @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
        public Iterator<Resource> iterator() {
            return new FileResourceIterator(Path.this.getProject(), null, this.parts);
        }

        @Override // org.apache.tools.ant.types.ResourceCollection
        public int size() {
            String[] strArr = this.parts;
            if (strArr == null) {
                return 0;
            }
            return strArr.length;
        }
    }

    public Path(Project project, String str) {
        this(project);
        createPathElement().setPath(str);
    }

    public Path(Project project) {
        this.union = null;
        this.cache = false;
        setProject(project);
    }

    public void setLocation(File file) throws BuildException {
        checkAttributesAllowed();
        createPathElement().setLocation(file);
    }

    public void setPath(String str) throws BuildException {
        checkAttributesAllowed();
        createPathElement().setPath(str);
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.union == null) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    public PathElement createPathElement() throws BuildException {
        if (!isReference()) {
            PathElement pathElement = new PathElement();
            add(pathElement);
            return pathElement;
        }
        throw noChildrenAllowed();
    }

    public void addFileset(FileSet fileSet) throws BuildException {
        if (fileSet.getProject() == null) {
            fileSet.setProject(getProject());
        }
        add(fileSet);
    }

    public void addFilelist(FileList fileList) throws BuildException {
        if (fileList.getProject() == null) {
            fileList.setProject(getProject());
        }
        add(fileList);
    }

    public void addDirset(DirSet dirSet) throws BuildException {
        if (dirSet.getProject() == null) {
            dirSet.setProject(getProject());
        }
        add(dirSet);
    }

    public void add(Path path) throws BuildException {
        if (path != this) {
            if (path.getProject() == null) {
                path.setProject(getProject());
            }
            add((ResourceCollection) path);
            return;
        }
        throw circularReference();
    }

    public void add(ResourceCollection resourceCollection) {
        checkChildrenAllowed();
        if (resourceCollection != null) {
            if (this.union == null) {
                this.union = new Union();
                this.union.setProject(getProject());
                this.union.setCache(this.cache);
            }
            this.union.add(resourceCollection);
            setChecked(false);
        }
    }

    public Path createPath() throws BuildException {
        Path path = new Path(getProject());
        add(path);
        return path;
    }

    public void append(Path path) {
        if (path != null) {
            add(path);
        }
    }

    public void addExisting(Path path) {
        addExisting(path, false);
    }

    public void addExisting(Path path, boolean z) {
        String[] list = path.list();
        File file = z ? new File(System.getProperty("user.dir")) : null;
        for (int i = 0; i < list.length; i++) {
            File resolveFile = resolveFile(getProject(), list[i]);
            if (z && !resolveFile.exists()) {
                resolveFile = new File(file, list[i]);
            }
            if (resolveFile.exists()) {
                setLocation(resolveFile);
            } else if (resolveFile.getParentFile() == null || !resolveFile.getParentFile().exists() || !containsWildcards(resolveFile.getName())) {
                log("dropping " + resolveFile + " from path as it doesn't exist", 3);
            } else {
                setLocation(resolveFile);
                log("adding " + resolveFile + " which contains wildcards and may not do what you intend it to do depending on your OS or version of Java", 3);
            }
        }
    }

    public void setCache(boolean z) {
        checkAttributesAllowed();
        this.cache = z;
        Union union = this.union;
        if (union != null) {
            union.setCache(z);
        }
    }

    public String[] list() {
        if (isReference()) {
            return ((Path) getCheckedRef()).list();
        }
        return assertFilesystemOnly(this.union) == null ? new String[0] : this.union.list();
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        }
        Union union = this.union;
        return union == null ? "" : union.toString();
    }

    public static String[] translatePath(Project project, String str) {
        Vector vector = new Vector();
        if (str == null) {
            return new String[0];
        }
        PathTokenizer pathTokenizer = new PathTokenizer(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (pathTokenizer.hasMoreTokens()) {
            String nextToken = pathTokenizer.nextToken();
            try {
                stringBuffer.append(resolveFile(project, nextToken).getPath());
            } catch (BuildException unused) {
                project.log("Dropping path element " + nextToken + " as it is not valid relative to the project", 3);
            }
            for (int i = 0; i < stringBuffer.length(); i++) {
                translateFileSep(stringBuffer, i);
            }
            vector.addElement(stringBuffer.toString());
            stringBuffer = new StringBuffer();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    public static String translateFile(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = 0; i < stringBuffer.length(); i++) {
            translateFileSep(stringBuffer, i);
        }
        return stringBuffer.toString();
    }

    protected static boolean translateFileSep(StringBuffer stringBuffer, int i) {
        if (stringBuffer.charAt(i) != '/' && stringBuffer.charAt(i) != '\\') {
            return false;
        }
        stringBuffer.setCharAt(i, File.separatorChar);
        return true;
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        if (isReference()) {
            return ((Path) getCheckedRef()).size();
        }
        dieOnCircularReference();
        return this.union == null ? 0 : assertFilesystemOnly(this.union).size();
    }

    @Override // org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        try {
            Path path = (Path) super.clone();
            path.union = this.union == null ? this.union : (Union) this.union.clone();
            return path;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                if (this.union != null) {
                    pushAndInvokeCircularReferenceCheck(this.union, stack, project);
                }
                setChecked(true);
            }
        }
    }

    private static File resolveFile(Project project, String str) {
        return FileUtils.getFileUtils().resolveFile(project == null ? null : project.getBaseDir(), str);
    }

    public Path concatSystemClasspath() {
        return concatSystemClasspath("last");
    }

    public Path concatSystemClasspath(String str) {
        return concatSpecialPath(str, systemClasspath);
    }

    public Path concatSystemBootClasspath(String str) {
        return concatSpecialPath(str, systemBootClasspath);
    }

    private Path concatSpecialPath(String str, Path path) {
        Path path2 = new Path(getProject());
        String property = getProject() != null ? getProject().getProperty(MagicNames.BUILD_SYSCLASSPATH) : System.getProperty(MagicNames.BUILD_SYSCLASSPATH);
        if (property != null) {
            str = property;
        }
        if (str.equals("only")) {
            path2.addExisting(path, true);
        } else if (str.equals("first")) {
            path2.addExisting(path, true);
            path2.addExisting(this);
        } else if (str.equals(Definer.OnError.POLICY_IGNORE)) {
            path2.addExisting(this);
        } else {
            if (!str.equals("last")) {
                log("invalid value for build.sysclasspath: " + str, 1);
            }
            path2.addExisting(this);
            path2.addExisting(path, true);
        }
        return path2;
    }

    public void addJavaRuntime() {
        if (JavaEnvUtils.isKaffe()) {
            File file = new File(System.getProperty("java.home") + File.separator + "share" + File.separator + "kaffe");
            if (file.isDirectory()) {
                FileSet fileSet = new FileSet();
                fileSet.setDir(file);
                fileSet.setIncludes("*.jar");
                addFileset(fileSet);
            }
        } else if ("GNU libgcj".equals(System.getProperty("java.vm.name"))) {
            addExisting(systemBootClasspath);
        }
        if (System.getProperty("java.vendor").toLowerCase(Locale.ENGLISH).indexOf("microsoft") >= 0) {
            FileSet fileSet2 = new FileSet();
            fileSet2.setDir(new File(System.getProperty("java.home") + File.separator + "Packages"));
            fileSet2.setIncludes("*.ZIP");
            addFileset(fileSet2);
            return;
        }
        addExisting(new Path(null, System.getProperty("java.home") + File.separator + "lib" + File.separator + "rt.jar"));
        addExisting(new Path(null, System.getProperty("java.home") + File.separator + "jre" + File.separator + "lib" + File.separator + "rt.jar"));
        String[] strArr = {"jce", "jsse"};
        for (int i = 0; i < strArr.length; i++) {
            addExisting(new Path(null, System.getProperty("java.home") + File.separator + "lib" + File.separator + strArr[i] + ".jar"));
            addExisting(new Path(null, System.getProperty("java.home") + File.separator + ".." + File.separator + "Classes" + File.separator + strArr[i] + ".jar"));
        }
        String[] strArr2 = {"core", "graphics", "security", "server", "xml"};
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            addExisting(new Path(null, System.getProperty("java.home") + File.separator + "lib" + File.separator + strArr2[i2] + ".jar"));
        }
        addExisting(new Path(null, System.getProperty("java.home") + File.separator + ".." + File.separator + "Classes" + File.separator + "classes.jar"));
        addExisting(new Path(null, System.getProperty("java.home") + File.separator + ".." + File.separator + "Classes" + File.separator + "ui.jar"));
    }

    public void addExtdirs(Path path) {
        if (path == null) {
            String property = System.getProperty("java.ext.dirs");
            if (property != null) {
                path = new Path(getProject(), property);
            } else {
                return;
            }
        }
        for (String str : path.list()) {
            File resolveFile = resolveFile(getProject(), str);
            if (resolveFile.exists() && resolveFile.isDirectory()) {
                FileSet fileSet = new FileSet();
                fileSet.setDir(resolveFile);
                fileSet.setIncludes(Marker.ANY_MARKER);
                addFileset(fileSet);
            }
        }
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public final synchronized Iterator<Resource> iterator() {
        if (isReference()) {
            return ((Path) getCheckedRef()).iterator();
        }
        dieOnCircularReference();
        if (getPreserveBC()) {
            return new FileResourceIterator(getProject(), null, list());
        }
        return this.union == null ? Collections.emptySet().iterator() : assertFilesystemOnly(this.union).iterator();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized boolean isFilesystemOnly() {
        if (isReference()) {
            return ((Path) getCheckedRef()).isFilesystemOnly();
        }
        dieOnCircularReference();
        assertFilesystemOnly(this.union);
        return true;
    }

    protected ResourceCollection assertFilesystemOnly(ResourceCollection resourceCollection) {
        if (resourceCollection == null || resourceCollection.isFilesystemOnly()) {
            return resourceCollection;
        }
        throw new BuildException(getDataTypeName() + " allows only filesystem resources.");
    }

    protected boolean delegateIteratorToList() {
        if (getClass().equals(Path.class)) {
            return false;
        }
        try {
            return !getClass().getMethod(HotDeploymentTool.ACTION_LIST, null).getDeclaringClass().equals(Path.class);
        } catch (Exception unused) {
            return false;
        }
    }

    private synchronized boolean getPreserveBC() {
        if (this.preserveBC == null) {
            this.preserveBC = delegateIteratorToList() ? Boolean.TRUE : Boolean.FALSE;
        }
        return this.preserveBC.booleanValue();
    }

    private static boolean containsWildcards(String str) {
        return str != null && (str.indexOf(Marker.ANY_MARKER) > -1 || str.indexOf("?") > -1);
    }
}
