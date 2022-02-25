package org.apache.tools.ant;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.launch.Locator;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.CollectionUtils;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.apache.tools.ant.util.LoaderUtils;
import org.apache.tools.ant.util.ReflectUtil;
import org.apache.tools.ant.util.VectorSet;
import org.apache.tools.zip.ZipLong;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class AntClassLoader extends ClassLoader implements SubBuildListener {
    private static final int BUFFER_SIZE = 8192;
    private static final ZipLong EOCD_SIG;
    private static final int NUMBER_OF_STRINGS = 256;
    private static final ZipLong SINGLE_SEGMENT_SPLIT_MARKER;
    private static Class<?> subClassToLoad;
    private boolean ignoreBase;
    private boolean isContextLoaderSaved;
    private Hashtable<File, JarFile> jarFiles;
    private final Vector<String> loaderPackages;
    private ClassLoader parent;
    private boolean parentFirst;
    private final Vector<File> pathComponents;
    private Project project;
    private ClassLoader savedContextLoader;
    private final Vector<String> systemPackages;
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static Map<String, String> pathMap = Collections.synchronizedMap(new HashMap());
    private static final Class<?>[] CONSTRUCTOR_ARGS = {ClassLoader.class, Project.class, Path.class, Boolean.TYPE};

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildStarted(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent buildEvent) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent buildEvent) {
    }

    static {
        subClassToLoad = null;
        if (JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_1_5)) {
            try {
                subClassToLoad = Class.forName("org.apache.tools.ant.loader.AntClassLoader5");
            } catch (ClassNotFoundException unused) {
            }
        }
        EOCD_SIG = new ZipLong(101010256L);
        SINGLE_SEGMENT_SPLIT_MARKER = new ZipLong(808471376L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ResourceEnumeration implements Enumeration<URL> {
        private URL nextResource;
        private int pathElementsIndex = 0;
        private final String resourceName;

        ResourceEnumeration(String str) {
            this.resourceName = str;
            findNextResource();
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.nextResource != null;
        }

        @Override // java.util.Enumeration
        public URL nextElement() {
            URL url = this.nextResource;
            if (url != null) {
                findNextResource();
                return url;
            }
            throw new NoSuchElementException();
        }

        private void findNextResource() {
            URL url = null;
            while (this.pathElementsIndex < AntClassLoader.this.pathComponents.size() && url == null) {
                try {
                    url = AntClassLoader.this.getResourceURL((File) AntClassLoader.this.pathComponents.elementAt(this.pathElementsIndex), this.resourceName);
                    this.pathElementsIndex++;
                } catch (BuildException unused) {
                }
            }
            this.nextResource = url;
        }
    }

    public AntClassLoader(ClassLoader classLoader, Project project, Path path) {
        this.pathComponents = new VectorSet();
        this.parentFirst = true;
        this.systemPackages = new Vector<>();
        this.loaderPackages = new Vector<>();
        this.ignoreBase = false;
        this.parent = null;
        this.jarFiles = new Hashtable<>();
        this.savedContextLoader = null;
        this.isContextLoaderSaved = false;
        setParent(classLoader);
        setClassPath(path);
        setProject(project);
    }

    public AntClassLoader() {
        this.pathComponents = new VectorSet();
        this.parentFirst = true;
        this.systemPackages = new Vector<>();
        this.loaderPackages = new Vector<>();
        this.ignoreBase = false;
        this.parent = null;
        this.jarFiles = new Hashtable<>();
        this.savedContextLoader = null;
        this.isContextLoaderSaved = false;
        setParent(null);
    }

    public AntClassLoader(Project project, Path path) {
        this.pathComponents = new VectorSet();
        this.parentFirst = true;
        this.systemPackages = new Vector<>();
        this.loaderPackages = new Vector<>();
        this.ignoreBase = false;
        this.parent = null;
        this.jarFiles = new Hashtable<>();
        this.savedContextLoader = null;
        this.isContextLoaderSaved = false;
        setParent(null);
        setProject(project);
        setClassPath(path);
    }

    public AntClassLoader(ClassLoader classLoader, Project project, Path path, boolean z) {
        this(project, path);
        if (classLoader != null) {
            setParent(classLoader);
        }
        setParentFirst(z);
        addJavaLibraries();
    }

    public AntClassLoader(Project project, Path path, boolean z) {
        this(null, project, path, z);
    }

    public AntClassLoader(ClassLoader classLoader, boolean z) {
        this.pathComponents = new VectorSet();
        this.parentFirst = true;
        this.systemPackages = new Vector<>();
        this.loaderPackages = new Vector<>();
        this.ignoreBase = false;
        this.parent = null;
        this.jarFiles = new Hashtable<>();
        this.savedContextLoader = null;
        this.isContextLoaderSaved = false;
        setParent(classLoader);
        this.project = null;
        this.parentFirst = z;
    }

    public void setProject(Project project) {
        this.project = project;
        if (project != null) {
            project.addBuildListener(this);
        }
    }

    public void setClassPath(Path path) {
        this.pathComponents.removeAllElements();
        if (path != null) {
            for (String str : path.concatSystemClasspath(Definer.OnError.POLICY_IGNORE).list()) {
                try {
                    addPathElement(str);
                } catch (BuildException unused) {
                }
            }
        }
    }

    public void setParent(ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = AntClassLoader.class.getClassLoader();
        }
        this.parent = classLoader;
    }

    public void setParentFirst(boolean z) {
        this.parentFirst = z;
    }

    protected void log(String str, int i) {
        Project project = this.project;
        if (project != null) {
            project.log(str, i);
        }
    }

    public void setThreadContextLoader() {
        if (this.isContextLoaderSaved) {
            throw new BuildException("Context loader has not been reset");
        } else if (LoaderUtils.isContextLoaderAvailable()) {
            this.savedContextLoader = LoaderUtils.getContextClassLoader();
            Project project = this.project;
            LoaderUtils.setContextClassLoader((project == null || !"only".equals(project.getProperty(MagicNames.BUILD_SYSCLASSPATH))) ? this : getClass().getClassLoader());
            this.isContextLoaderSaved = true;
        }
    }

    public void resetThreadContextLoader() {
        if (LoaderUtils.isContextLoaderAvailable() && this.isContextLoaderSaved) {
            LoaderUtils.setContextClassLoader(this.savedContextLoader);
            this.savedContextLoader = null;
            this.isContextLoaderSaved = false;
        }
    }

    public void addPathElement(String str) throws BuildException {
        Project project = this.project;
        try {
            addPathFile(project != null ? project.resolveFile(str) : new File(str));
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    public void addPathComponent(File file) {
        if (!this.pathComponents.contains(file)) {
            this.pathComponents.addElement(file);
        }
    }

    protected void addPathFile(File file) throws IOException {
        Throwable th;
        if (!this.pathComponents.contains(file)) {
            this.pathComponents.addElement(file);
        }
        if (!file.isDirectory()) {
            String str = file.getAbsolutePath() + file.lastModified() + "-" + file.length();
            String str2 = pathMap.get(str);
            if (str2 == null) {
                JarFile jarFile = null;
                try {
                    jarFile = new JarFile(file);
                    try {
                        Manifest manifest = jarFile.getManifest();
                        if (manifest == null) {
                            jarFile.close();
                            return;
                        }
                        str2 = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH);
                        jarFile.close();
                        if (str2 == null) {
                            str2 = "";
                        }
                        pathMap.put(str, str2);
                    } catch (Throwable th2) {
                        th = th2;
                        if (jarFile != null) {
                            jarFile.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (!"".equals(str2)) {
                URL fileURL = FILE_UTILS.getFileURL(file);
                StringTokenizer stringTokenizer = new StringTokenizer(str2);
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    URL url = new URL(fileURL, nextToken);
                    if (!url.getProtocol().equals("file")) {
                        log("Skipping jar library " + nextToken + " since only relative URLs are supported by this loader", 3);
                    } else {
                        File file2 = new File(Locator.decodeUri(url.getFile()));
                        if (file2.exists() && !isInPath(file2)) {
                            addPathFile(file2);
                        }
                    }
                }
            }
        }
    }

    public String getClasspath() {
        StringBuilder sb = new StringBuilder();
        Enumeration<File> elements = this.pathComponents.elements();
        boolean z = true;
        while (elements.hasMoreElements()) {
            if (!z) {
                sb.append(System.getProperty("path.separator"));
            } else {
                z = false;
            }
            sb.append(elements.nextElement().getAbsolutePath());
        }
        return sb.toString();
    }

    public synchronized void setIsolated(boolean z) {
        this.ignoreBase = z;
    }

    @Deprecated
    public static void initializeClass(Class<?> cls) {
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        if (declaredConstructors != null && declaredConstructors.length > 0 && declaredConstructors[0] != null) {
            try {
                declaredConstructors[0].newInstance(new String[256]);
            } catch (Exception unused) {
            }
        }
    }

    public void addSystemPackageRoot(String str) {
        Vector<String> vector = this.systemPackages;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.endsWith(Consts.f23430h) ? "" : Consts.f23430h);
        vector.addElement(sb.toString());
    }

    public void addLoaderPackageRoot(String str) {
        Vector<String> vector = this.loaderPackages;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.endsWith(Consts.f23430h) ? "" : Consts.f23430h);
        vector.addElement(sb.toString());
    }

    public Class<?> forceLoadClass(String str) throws ClassNotFoundException {
        log("force loading " + str, 4);
        Class<?> findLoadedClass = findLoadedClass(str);
        return findLoadedClass == null ? findClass(str) : findLoadedClass;
    }

    public Class<?> forceLoadSystemClass(String str) throws ClassNotFoundException {
        log("force system loading " + str, 4);
        Class<?> findLoadedClass = findLoadedClass(str);
        return findLoadedClass == null ? findBaseClass(str) : findLoadedClass;
    }

    @Override // java.lang.ClassLoader
    public InputStream getResourceAsStream(String str) {
        InputStream inputStream = null;
        inputStream = isParentFirst(str) ? loadBaseResource(str) : null;
        if (inputStream != null) {
            log("ResourceStream for " + str + " loaded from parent loader", 4);
        } else {
            inputStream = loadResource(str);
            if (inputStream != null) {
                log("ResourceStream for " + str + " loaded from ant loader", 4);
            }
        }
        if (inputStream == null && !isParentFirst(str)) {
            if (!this.ignoreBase) {
                inputStream = loadBaseResource(str);
            } else if (getRootLoader() != null) {
                inputStream = getRootLoader().getResourceAsStream(str);
            }
            if (inputStream != null) {
                log("ResourceStream for " + str + " loaded from parent loader", 4);
            }
        }
        if (inputStream == null) {
            log("Couldn't load ResourceStream for " + str, 4);
        }
        return inputStream;
    }

    private InputStream loadResource(String str) {
        Enumeration<File> elements = this.pathComponents.elements();
        InputStream inputStream = null;
        while (elements.hasMoreElements() && inputStream == null) {
            inputStream = getResourceStream(elements.nextElement(), str);
        }
        return inputStream;
    }

    private InputStream loadBaseResource(String str) {
        ClassLoader classLoader = this.parent;
        return classLoader == null ? super.getResourceAsStream(str) : classLoader.getResourceAsStream(str);
    }

    private InputStream getResourceStream(File file, String str) {
        try {
            JarFile jarFile = this.jarFiles.get(file);
            if (jarFile != null || !file.isDirectory()) {
                if (jarFile == null) {
                    if (!file.exists()) {
                        return null;
                    }
                    this.jarFiles.put(file, new JarFile(file));
                    jarFile = this.jarFiles.get(file);
                }
                JarEntry jarEntry = jarFile.getJarEntry(str);
                if (jarEntry != null) {
                    return jarFile.getInputStream(jarEntry);
                }
            } else {
                File file2 = new File(file, str);
                if (file2.exists()) {
                    return new FileInputStream(file2);
                }
            }
        } catch (Exception e) {
            log("Ignoring Exception " + e.getClass().getName() + ": " + e.getMessage() + " reading resource " + str + " from " + file, 3);
        }
        return null;
    }

    private boolean isParentFirst(String str) {
        boolean z = this.parentFirst;
        Enumeration<String> elements = this.systemPackages.elements();
        while (true) {
            if (elements.hasMoreElements()) {
                if (str.startsWith(elements.nextElement())) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        Enumeration<String> elements2 = this.loaderPackages.elements();
        while (elements2.hasMoreElements()) {
            if (str.startsWith(elements2.nextElement())) {
                return false;
            }
        }
        return z;
    }

    private ClassLoader getRootLoader() {
        ClassLoader classLoader = getClass().getClassLoader();
        while (classLoader != null && classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
        }
        return classLoader;
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        URL url = null;
        if (isParentFirst(str)) {
            ClassLoader classLoader = this.parent;
            url = classLoader == null ? super.getResource(str) : classLoader.getResource(str);
        } else {
            url = null;
        }
        if (url != null) {
            log("Resource " + str + " loaded from parent loader", 4);
        } else {
            Enumeration<File> elements = this.pathComponents.elements();
            while (elements.hasMoreElements() && url == null) {
                url = getResourceURL(elements.nextElement(), str);
                if (url != null) {
                    log("Resource " + str + " loaded from ant loader", 4);
                }
            }
        }
        if (url == null && !isParentFirst(str)) {
            if (!this.ignoreBase) {
                ClassLoader classLoader2 = this.parent;
                url = classLoader2 == null ? super.getResource(str) : classLoader2.getResource(str);
            } else if (getRootLoader() != null) {
                url = getRootLoader().getResource(str);
            }
            if (url != null) {
                log("Resource " + str + " loaded from parent loader", 4);
            }
        }
        if (url == null) {
            log("Couldn't load Resource " + str, 4);
        }
        return url;
    }

    public Enumeration<URL> getNamedResources(String str) throws IOException {
        return findResources(str, false);
    }

    @Override // java.lang.ClassLoader
    protected Enumeration<URL> findResources(String str) throws IOException {
        return findResources(str, true);
    }

    protected Enumeration<URL> findResources(String str, boolean z) throws IOException {
        Enumeration<URL> enumeration;
        ResourceEnumeration resourceEnumeration = new ResourceEnumeration(str);
        ClassLoader classLoader = this.parent;
        if (classLoader == null || (z && classLoader == getParent())) {
            enumeration = new CollectionUtils.EmptyEnumeration<>();
        } else {
            enumeration = this.parent.getResources(str);
        }
        if (isParentFirst(str)) {
            return CollectionUtils.append(enumeration, resourceEnumeration);
        }
        if (this.ignoreBase) {
            return getRootLoader() == null ? resourceEnumeration : CollectionUtils.append(resourceEnumeration, getRootLoader().getResources(str));
        }
        return CollectionUtils.append(resourceEnumeration, enumeration);
    }

    protected URL getResourceURL(File file, String str) {
        try {
            JarFile jarFile = this.jarFiles.get(file);
            if (jarFile != null || !file.isDirectory()) {
                if (jarFile == null) {
                    if (!file.exists()) {
                        return null;
                    }
                    if (!isZip(file)) {
                        String str2 = "CLASSPATH element " + file + " is not a JAR.";
                        log(str2, 1);
                        System.err.println(str2);
                        return null;
                    }
                    this.jarFiles.put(file, new JarFile(file));
                    jarFile = this.jarFiles.get(file);
                }
                JarEntry jarEntry = jarFile.getJarEntry(str);
                if (jarEntry != null) {
                    try {
                        return new URL("jar:" + FILE_UTILS.getFileURL(file) + "!/" + jarEntry);
                    } catch (MalformedURLException unused) {
                        return null;
                    }
                }
            } else {
                File file2 = new File(file, str);
                if (file2.exists()) {
                    try {
                        return FILE_UTILS.getFileURL(file2);
                    } catch (MalformedURLException unused2) {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            String str3 = "Unable to obtain resource from " + file + ": ";
            log(str3 + e, 1);
            System.err.println(str3);
            e.printStackTrace();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0098 A[Catch: all -> 0x009e, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:7:0x0009, B:10:0x0010, B:11:0x0033, B:12:0x0056, B:14:0x0075, B:16:0x0079, B:18:0x0098, B:21:0x009d), top: B:28:0x0001, inners: #0, #1 }] */
    @Override // java.lang.ClassLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.Class<?> loadClass(java.lang.String r5, boolean r6) throws java.lang.ClassNotFoundException {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.Class r0 = r4.findLoadedClass(r5)     // Catch: all -> 0x009e
            if (r0 == 0) goto L_0x0009
            monitor-exit(r4)
            return r0
        L_0x0009:
            boolean r0 = r4.isParentFirst(r5)     // Catch: all -> 0x009e
            r1 = 4
            if (r0 == 0) goto L_0x0056
            java.lang.Class r0 = r4.findBaseClass(r5)     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            r2.<init>()     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            java.lang.String r3 = "Class "
            r2.append(r3)     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            r2.append(r5)     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            java.lang.String r3 = " loaded from parent loader "
            r2.append(r3)     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            java.lang.String r3 = "(parentFirst)"
            r2.append(r3)     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            java.lang.String r2 = r2.toString()     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            r4.log(r2, r1)     // Catch: ClassNotFoundException -> 0x0033, all -> 0x009e
            goto L_0x0096
        L_0x0033:
            java.lang.Class r0 = r4.findClass(r5)     // Catch: all -> 0x009e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x009e
            r2.<init>()     // Catch: all -> 0x009e
            java.lang.String r3 = "Class "
            r2.append(r3)     // Catch: all -> 0x009e
            r2.append(r5)     // Catch: all -> 0x009e
            java.lang.String r5 = " loaded from ant loader "
            r2.append(r5)     // Catch: all -> 0x009e
            java.lang.String r5 = "(parentFirst)"
            r2.append(r5)     // Catch: all -> 0x009e
            java.lang.String r5 = r2.toString()     // Catch: all -> 0x009e
            r4.log(r5, r1)     // Catch: all -> 0x009e
            goto L_0x0096
        L_0x0056:
            java.lang.Class r0 = r4.findClass(r5)     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            r2.<init>()     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            java.lang.String r3 = "Class "
            r2.append(r3)     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            r2.append(r5)     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            java.lang.String r3 = " loaded from ant loader"
            r2.append(r3)     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            java.lang.String r2 = r2.toString()     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            r4.log(r2, r1)     // Catch: ClassNotFoundException -> 0x0074, all -> 0x009e
            goto L_0x0096
        L_0x0074:
            r0 = move-exception
            boolean r2 = r4.ignoreBase     // Catch: all -> 0x009e
            if (r2 != 0) goto L_0x009d
            java.lang.Class r0 = r4.findBaseClass(r5)     // Catch: all -> 0x009e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x009e
            r2.<init>()     // Catch: all -> 0x009e
            java.lang.String r3 = "Class "
            r2.append(r3)     // Catch: all -> 0x009e
            r2.append(r5)     // Catch: all -> 0x009e
            java.lang.String r5 = " loaded from parent loader"
            r2.append(r5)     // Catch: all -> 0x009e
            java.lang.String r5 = r2.toString()     // Catch: all -> 0x009e
            r4.log(r5, r1)     // Catch: all -> 0x009e
        L_0x0096:
            if (r6 == 0) goto L_0x009b
            r4.resolveClass(r0)     // Catch: all -> 0x009e
        L_0x009b:
            monitor-exit(r4)
            return r0
        L_0x009d:
            throw r0     // Catch: all -> 0x009e
        L_0x009e:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.AntClassLoader.loadClass(java.lang.String, boolean):java.lang.Class");
    }

    private String getClassFilename(String str) {
        return str.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + ".class";
    }

    protected Class<?> defineClassFromData(File file, byte[] bArr, String str) throws IOException {
        definePackage(file, str);
        ProtectionDomain protectionDomain = Project.class.getProtectionDomain();
        return defineClass(str, bArr, 0, bArr.length, new ProtectionDomain(new CodeSource(FILE_UTILS.getFileURL(file), getCertificates(file, getClassFilename(str))), protectionDomain.getPermissions(), this, protectionDomain.getPrincipals()));
    }

    protected void definePackage(File file, String str) throws IOException {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            if (getPackage(substring) == null) {
                Manifest jarManifest = getJarManifest(file);
                if (jarManifest == null) {
                    definePackage(substring, null, null, null, null, null, null, null);
                } else {
                    definePackage(file, substring, jarManifest);
                }
            }
        }
    }

    private Manifest getJarManifest(File file) throws IOException {
        JarFile jarFile;
        if (!file.isDirectory() && (jarFile = this.jarFiles.get(file)) != null) {
            return jarFile.getManifest();
        }
        return null;
    }

    private Certificate[] getCertificates(File file, String str) throws IOException {
        JarFile jarFile;
        JarEntry jarEntry;
        if (file.isDirectory() || (jarFile = this.jarFiles.get(file)) == null || (jarEntry = jarFile.getJarEntry(str)) == null) {
            return null;
        }
        return jarEntry.getCertificates();
    }

    protected void definePackage(File file, String str, Manifest manifest) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        URL url;
        Attributes attributes = manifest.getAttributes(str.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + "/");
        if (attributes != null) {
            str7 = attributes.getValue(Attributes.Name.SPECIFICATION_TITLE);
            str6 = attributes.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            str5 = attributes.getValue(Attributes.Name.SPECIFICATION_VERSION);
            str4 = attributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            str3 = attributes.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            str2 = attributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            str8 = attributes.getValue(Attributes.Name.SEALED);
        } else {
            str8 = null;
            str7 = null;
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            str2 = null;
        }
        Attributes mainAttributes = manifest.getMainAttributes();
        if (mainAttributes != null) {
            if (str7 == null) {
                str7 = mainAttributes.getValue(Attributes.Name.SPECIFICATION_TITLE);
            }
            if (str6 == null) {
                str6 = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            }
            if (str5 == null) {
                str5 = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VERSION);
            }
            if (str4 == null) {
                str4 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            }
            if (str3 == null) {
                str3 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            }
            if (str2 == null) {
                str2 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            }
            if (str8 == null) {
                str8 = mainAttributes.getValue(Attributes.Name.SEALED);
                str9 = str3;
                str10 = str2;
                str12 = str6;
                str11 = str4;
                str13 = str5;
                str14 = str7;
            } else {
                str9 = str3;
                str10 = str2;
                str12 = str6;
                str11 = str4;
                str13 = str5;
                str14 = str7;
            }
        } else {
            str9 = str3;
            str10 = str2;
            str12 = str6;
            str11 = str4;
            str13 = str5;
            str14 = str7;
        }
        if (str8 != null && str8.equalsIgnoreCase("true")) {
            try {
                url = new URL(FileUtils.getFileUtils().toURI(file.getAbsolutePath()));
            } catch (MalformedURLException unused) {
            }
            definePackage(str, str14, str13, str12, str11, str10, str9, url);
        }
        url = null;
        definePackage(str, str14, str13, str12, str11, str10, str9, url);
    }

    private Class<?> getClassFromStream(InputStream inputStream, String str, File file) throws IOException, SecurityException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr, 0, 8192);
            if (read == -1) {
                return defineClassFromData(file, byteArrayOutputStream.toByteArray(), str);
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    @Override // java.lang.ClassLoader
    public Class<?> findClass(String str) throws ClassNotFoundException {
        log("Finding class " + str, 4);
        return findClassInComponents(str);
    }

    protected boolean isInPath(File file) {
        return this.pathComponents.contains(file);
    }

    private Class<?> findClassInComponents(String str) throws ClassNotFoundException {
        String classFilename = getClassFilename(str);
        Enumeration<File> elements = this.pathComponents.elements();
        while (elements.hasMoreElements()) {
            File nextElement = elements.nextElement();
            InputStream inputStream = null;
            try {
                try {
                    inputStream = getResourceStream(nextElement, classFilename);
                } catch (IOException e) {
                    log("Exception reading component " + nextElement + " (reason: " + e.getMessage() + ")", 3);
                } catch (SecurityException e2) {
                    throw e2;
                }
                if (inputStream != null) {
                    log("Loaded from " + nextElement + ExpandableTextView.f6958c + classFilename, 4);
                    return getClassFromStream(inputStream, str, nextElement);
                }
                continue;
            } finally {
                FileUtils.close(inputStream);
            }
        }
        throw new ClassNotFoundException(str);
    }

    private Class<?> findBaseClass(String str) throws ClassNotFoundException {
        ClassLoader classLoader = this.parent;
        return classLoader == null ? findSystemClass(str) : classLoader.loadClass(str);
    }

    public synchronized void cleanup() {
        Enumeration<JarFile> elements = this.jarFiles.elements();
        while (elements.hasMoreElements()) {
            try {
                elements.nextElement().close();
            } catch (IOException unused) {
            }
        }
        this.jarFiles = new Hashtable<>();
        if (this.project != null) {
            this.project.removeBuildListener(this);
        }
        this.project = null;
    }

    public ClassLoader getConfiguredParent() {
        return this.parent;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent buildEvent) {
        cleanup();
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildFinished(BuildEvent buildEvent) {
        if (buildEvent.getProject() == this.project) {
            cleanup();
        }
    }

    public void addJavaLibraries() {
        Enumeration<String> elements = JavaEnvUtils.getJrePackages().elements();
        while (elements.hasMoreElements()) {
            addSystemPackageRoot(elements.nextElement());
        }
    }

    public String toString() {
        return "AntClassLoader[" + getClasspath() + "]";
    }

    public static AntClassLoader newAntClassLoader(ClassLoader classLoader, Project project, Path path, boolean z) {
        Class<?> cls = subClassToLoad;
        return cls != null ? (AntClassLoader) ReflectUtil.newInstance(cls, CONSTRUCTOR_ARGS, new Object[]{classLoader, project, path, Boolean.valueOf(z)}) : new AntClassLoader(classLoader, project, path, z);
    }

    private static boolean isZip(File file) throws IOException {
        byte[] bArr = new byte[4];
        if (!readFully(file, bArr)) {
            return false;
        }
        ZipLong zipLong = new ZipLong(bArr);
        return ZipLong.LFH_SIG.equals(zipLong) || EOCD_SIG.equals(zipLong) || ZipLong.DD_SIG.equals(zipLong) || SINGLE_SEGMENT_SPLIT_MARKER.equals(zipLong);
    }

    private static boolean readFully(File file, byte[] bArr) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            int length = bArr.length;
            boolean z = false;
            int i = 0;
            while (i != length) {
                int read = fileInputStream.read(bArr, i, length - i);
                if (read == -1) {
                    break;
                }
                i += read;
            }
            if (i == length) {
                z = true;
            }
            return z;
        } finally {
            fileInputStream.close();
        }
    }
}
