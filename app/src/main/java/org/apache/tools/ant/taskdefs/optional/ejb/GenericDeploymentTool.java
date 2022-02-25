package org.apache.tools.ant.taskdefs.optional.ejb;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import javax.xml.parsers.SAXParser;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.optional.ejb.EjbJar;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.depend.DependencyAnalyzer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes2.dex */
public class GenericDeploymentTool implements EJBDeploymentTool {
    public static final String ANALYZER_CLASS_FULL = "org.apache.tools.ant.util.depend.bcel.FullAnalyzer";
    public static final String ANALYZER_CLASS_SUPER = "org.apache.tools.ant.util.depend.bcel.AncestorAnalyzer";
    public static final String ANALYZER_FULL = "full";
    public static final String ANALYZER_NONE = "none";
    public static final String ANALYZER_SUPER = "super";
    public static final String DEFAULT_ANALYZER = "super";
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    protected static final String EJB_DD = "ejb-jar.xml";
    public static final int JAR_COMPRESS_LEVEL = 9;
    protected static final String MANIFEST = "META-INF/MANIFEST.MF";
    protected static final String META_DIR = "META-INF/";
    private Set addedfiles;
    private Path classpath;
    private EjbJar.Config config;
    private DependencyAnalyzer dependencyAnalyzer;
    private File destDir;
    private DescriptorHandler handler;
    private Task task;
    private String genericJarSuffix = "-generic.jar";
    private ClassLoader classpathLoader = null;

    protected void addVendorFiles(Hashtable hashtable, String str) {
    }

    protected void checkConfiguration(String str, SAXParser sAXParser) throws BuildException {
    }

    protected void registerKnownDTDs(DescriptorHandler descriptorHandler) {
    }

    public void setDestdir(File file) {
        this.destDir = file;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public File getDestDir() {
        return this.destDir;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void setTask(Task task) {
        this.task = task;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Task getTask() {
        return this.task;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EjbJar.Config getConfig() {
        return this.config;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean usingBaseJarName() {
        return this.config.baseJarName != null;
    }

    public void setGenericJarSuffix(String str) {
        this.genericJarSuffix = str;
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(this.task.getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspath(Path path) {
        this.classpath = path;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Path getCombinedClasspath() {
        Path path = this.classpath;
        if (this.config.classpath == null) {
            return path;
        }
        if (path == null) {
            return this.config.classpath;
        }
        path.append(this.config.classpath);
        return path;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void log(String str, int i) {
        getTask().log(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Location getLocation() {
        return getTask().getLocation();
    }

    private void createAnalyzer() {
        String str = this.config.analyzer;
        if (str == null) {
            str = "super";
        }
        if (!str.equals("none")) {
            if (str.equals("super")) {
                str = ANALYZER_CLASS_SUPER;
            } else if (str.equals(ANALYZER_FULL)) {
                str = "org.apache.tools.ant.util.depend.bcel.FullAnalyzer";
            }
            try {
                this.dependencyAnalyzer = (DependencyAnalyzer) Class.forName(str).newInstance();
                this.dependencyAnalyzer.addClassPath(new Path(this.task.getProject(), this.config.srcDir.getPath()));
                this.dependencyAnalyzer.addClassPath(this.config.classpath);
            } catch (Exception e) {
                this.dependencyAnalyzer = null;
                Task task = this.task;
                task.log("Unable to load dependency analyzer: " + str + " - exception: " + e.getMessage(), 1);
            } catch (NoClassDefFoundError e2) {
                this.dependencyAnalyzer = null;
                Task task2 = this.task;
                task2.log("Unable to load dependency analyzer: " + str + " - dependent class not found: " + e2.getMessage(), 1);
            }
        }
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void configure(EjbJar.Config config) {
        this.config = config;
        createAnalyzer();
        this.classpathLoader = null;
    }

    protected void addFileToJar(JarOutputStream jarOutputStream, File file, String str) throws BuildException {
        Throwable th;
        IOException e;
        FileInputStream fileInputStream = null;
        try {
            try {
                if (!this.addedfiles.contains(str)) {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        jarOutputStream.putNextEntry(new ZipEntry(str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX)));
                        byte[] bArr = new byte[2048];
                        int i = 0;
                        do {
                            jarOutputStream.write(bArr, 0, i);
                            i = fileInputStream2.read(bArr, 0, bArr.length);
                        } while (i != -1);
                        this.addedfiles.add(str);
                        fileInputStream = fileInputStream2;
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        log("WARNING: IOException while adding entry " + str + " to jarfile from " + file.getPath() + ExpandableTextView.f6958c + e.getClass().getName() + "-" + e.getMessage(), 1);
                        if (fileInputStream == null) {
                            return;
                        }
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                }
                if (fileInputStream == null) {
                    return;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e3) {
            e = e3;
        }
        try {
            fileInputStream.close();
        } catch (IOException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DescriptorHandler getDescriptorHandler(File file) {
        DescriptorHandler descriptorHandler = new DescriptorHandler(getTask(), file);
        registerKnownDTDs(descriptorHandler);
        Iterator it = getConfig().dtdLocations.iterator();
        while (it.hasNext()) {
            EjbJar.DTDLocation dTDLocation = (EjbJar.DTDLocation) it.next();
            descriptorHandler.registerDTD(dTDLocation.getPublicId(), dTDLocation.getLocation());
        }
        return descriptorHandler;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void processDescriptor(String str, SAXParser sAXParser) {
        checkConfiguration(str, sAXParser);
        try {
            this.handler = getDescriptorHandler(this.config.srcDir);
            Hashtable parseEjbFiles = parseEjbFiles(str, sAXParser);
            addSupportClasses(parseEjbFiles);
            String jarBaseName = getJarBaseName(str);
            String vendorDDPrefix = getVendorDDPrefix(jarBaseName, str);
            File manifestFile = getManifestFile(vendorDDPrefix);
            if (manifestFile != null) {
                parseEjbFiles.put(MANIFEST, manifestFile);
            }
            parseEjbFiles.put("META-INF/ejb-jar.xml", new File(this.config.descriptorDir, str));
            addVendorFiles(parseEjbFiles, vendorDDPrefix);
            checkAndAddDependants(parseEjbFiles);
            if (this.config.flatDestDir && jarBaseName.length() != 0) {
                int lastIndexOf = jarBaseName.lastIndexOf(File.separator);
                if (lastIndexOf == -1) {
                    lastIndexOf = 0;
                }
                jarBaseName = jarBaseName.substring(lastIndexOf, jarBaseName.length());
            }
            File vendorOutputJarFile = getVendorOutputJarFile(jarBaseName);
            if (needToRebuild(parseEjbFiles, vendorOutputJarFile)) {
                log("building " + vendorOutputJarFile.getName() + " with " + String.valueOf(parseEjbFiles.size()) + " files", 2);
                writeJar(jarBaseName, vendorOutputJarFile, parseEjbFiles, getPublicId());
                return;
            }
            log(vendorOutputJarFile.toString() + " is up to date.", 3);
        } catch (IOException e) {
            throw new BuildException("IOException while parsing'" + str + "'.  This probably indicates that the descriptor doesn't exist. Details: " + e.getMessage(), e);
        } catch (SAXException e2) {
            throw new BuildException("SAXException while parsing '" + str + "'. This probably indicates badly-formed XML.  Details: " + e2.getMessage(), e2);
        }
    }

    protected Hashtable parseEjbFiles(String str, SAXParser sAXParser) throws IOException, SAXException {
        Throwable th;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(new File(this.config.descriptorDir, str));
            try {
                sAXParser.parse(new InputSource(fileInputStream2), this.handler);
                Hashtable files = this.handler.getFiles();
                try {
                    fileInputStream2.close();
                } catch (IOException unused) {
                }
                return files;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    protected void addSupportClasses(Hashtable hashtable) {
        Project project = this.task.getProject();
        for (FileSet fileSet : this.config.supportFileSets) {
            File dir = fileSet.getDir(project);
            DirectoryScanner directoryScanner = fileSet.getDirectoryScanner(project);
            directoryScanner.scan();
            String[] includedFiles = directoryScanner.getIncludedFiles();
            for (int i = 0; i < includedFiles.length; i++) {
                hashtable.put(includedFiles[i], new File(dir, includedFiles[i]));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getJarBaseName(String str) {
        int i;
        String str2 = "";
        if (this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.BASEJARNAME)) {
            int lastIndexOf = str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX).lastIndexOf(47);
            if (lastIndexOf != -1) {
                str2 = str.substring(0, lastIndexOf + 1);
            }
            return str2 + this.config.baseJarName;
        } else if (this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.DESCRIPTOR)) {
            int lastIndexOf2 = str.lastIndexOf(File.separator);
            if (lastIndexOf2 != -1) {
                i = str.indexOf(this.config.baseNameTerminator, lastIndexOf2);
            } else {
                i = str.indexOf(this.config.baseNameTerminator);
            }
            if (i != -1) {
                return str.substring(0, i);
            }
            throw new BuildException("Unable to determine jar name from descriptor \"" + str + "\"");
        } else if (!this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.DIRECTORY)) {
            return this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.EJB_NAME) ? this.handler.getEjbName() : str2;
        } else {
            String absolutePath = new File(this.config.descriptorDir, str).getAbsolutePath();
            int lastIndexOf3 = absolutePath.lastIndexOf(File.separator);
            if (lastIndexOf3 != -1) {
                String substring = absolutePath.substring(0, lastIndexOf3);
                int lastIndexOf4 = substring.lastIndexOf(File.separator);
                return lastIndexOf4 != -1 ? substring.substring(lastIndexOf4 + 1) : substring;
            }
            throw new BuildException("Unable to determine directory name holding descriptor");
        }
    }

    public String getVendorDDPrefix(String str, String str2) {
        if (this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.DESCRIPTOR)) {
            return str + this.config.baseNameTerminator;
        } else if (!this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.BASEJARNAME) && !this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.EJB_NAME) && !this.config.namingScheme.getValue().equals(EjbJar.NamingScheme.DIRECTORY)) {
            return null;
        } else {
            int lastIndexOf = str2.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX).lastIndexOf(47);
            return lastIndexOf == -1 ? "" : str2.substring(0, lastIndexOf + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File getVendorOutputJarFile(String str) {
        File file = this.destDir;
        return new File(file, str + this.genericJarSuffix);
    }

    protected boolean needToRebuild(Hashtable hashtable, File file) {
        if (!file.exists()) {
            return true;
        }
        long lastModified = file.lastModified();
        for (File file2 : hashtable.values()) {
            if (lastModified < file2.lastModified()) {
                log("Build needed because " + file2.getPath() + " is out of date", 3);
                return true;
            }
        }
        return false;
    }

    protected String getPublicId() {
        return this.handler.getPublicId();
    }

    protected File getManifestFile(String str) {
        File file = getConfig().descriptorDir;
        File file2 = new File(file, str + "manifest.mf");
        if (file2.exists()) {
            return file2;
        }
        if (this.config.manifest != null) {
            return this.config.manifest;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeJar(String str, File file, Hashtable hashtable, String str2) throws BuildException {
        IOException e;
        Throwable th;
        IOException e2;
        InputStream inputStream;
        JarOutputStream jarOutputStream;
        int lastIndexOf;
        InputStream inputStream2 = null;
        try {
            try {
                if (this.addedfiles == null) {
                    this.addedfiles = new HashSet();
                } else {
                    this.addedfiles.clear();
                }
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    File file2 = (File) hashtable.get(MANIFEST);
                    try {
                        if (file2 == null || !file2.exists()) {
                            inputStream = getClass().getResourceAsStream("/org/apache/tools/ant/defaultManifest.mf");
                            if (inputStream == null) {
                                throw new BuildException("Could not find default manifest: /org/apache/tools/ant/defaultManifest.mf");
                            }
                        } else {
                            inputStream = new FileInputStream(file2);
                        }
                        Manifest manifest = new Manifest(inputStream);
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        jarOutputStream = new JarOutputStream(new FileOutputStream(file), manifest);
                        try {
                            jarOutputStream.setMethod(8);
                            for (String str3 : hashtable.keySet()) {
                                if (!str3.equals(MANIFEST)) {
                                    File file3 = (File) hashtable.get(str3);
                                    log("adding file '" + str3 + "'", 3);
                                    addFileToJar(jarOutputStream, file3, str3);
                                    String[] list = file3.getParentFile().list(new InnerClassFilenameFilter(file3.getName()));
                                    if (list != null) {
                                        int length = list.length;
                                        File file4 = file3;
                                        String str4 = str3;
                                        for (int i = 0; i < length; i++) {
                                            str4 = str4.lastIndexOf(file4.getName()) - 1 < 0 ? list[i] : str4.substring(0, lastIndexOf) + File.separatorChar + list[i];
                                            file4 = new File(this.config.srcDir, str4);
                                            log("adding innerclass file '" + str4 + "'", 3);
                                            addFileToJar(jarOutputStream, file4, str4);
                                        }
                                    }
                                }
                            }
                            try {
                                jarOutputStream.close();
                            } catch (IOException unused) {
                            }
                        } catch (IOException e3) {
                            e = e3;
                            throw new BuildException("IOException while processing ejb-jar file '" + file.toString() + "'. Details: " + e.getMessage(), e);
                        } catch (Throwable th3) {
                            th = th3;
                            if (jarOutputStream != null) {
                                try {
                                    jarOutputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e4) {
                        e2 = e4;
                        throw new BuildException("Unable to read manifest", e2, getLocation());
                    }
                } catch (IOException e5) {
                    e2 = e5;
                } catch (Throwable th4) {
                    th = th4;
                    if (0 != 0) {
                        inputStream2.close();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th5) {
            th = th5;
            jarOutputStream = null;
        }
    }

    protected void checkAndAddDependants(Hashtable hashtable) throws BuildException {
        DependencyAnalyzer dependencyAnalyzer = this.dependencyAnalyzer;
        if (dependencyAnalyzer != null) {
            dependencyAnalyzer.reset();
            for (String str : hashtable.keySet()) {
                if (str.endsWith(".class")) {
                    this.dependencyAnalyzer.addRootClass(str.substring(0, str.length() - 6).replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX).replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR));
                }
            }
            Enumeration<String> classDependencies = this.dependencyAnalyzer.getClassDependencies();
            while (classDependencies.hasMoreElements()) {
                String nextElement = classDependencies.nextElement();
                String str2 = nextElement.replace(FilenameUtils.EXTENSION_SEPARATOR, File.separatorChar) + ".class";
                File file = new File(this.config.srcDir, str2);
                if (file.exists()) {
                    hashtable.put(str2, file);
                    log("dependent class: " + nextElement + " - " + file, 3);
                }
            }
        }
    }

    protected ClassLoader getClassLoaderForBuild() {
        ClassLoader classLoader = this.classpathLoader;
        if (classLoader != null) {
            return classLoader;
        }
        Path combinedClasspath = getCombinedClasspath();
        if (combinedClasspath == null) {
            this.classpathLoader = getClass().getClassLoader();
        } else {
            this.classpathLoader = getTask().getProject().createClassLoader(combinedClasspath);
        }
        return this.classpathLoader;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.ejb.EJBDeploymentTool
    public void validateConfigured() throws BuildException {
        File file = this.destDir;
        if (file == null || !file.isDirectory()) {
            throw new BuildException("A valid destination directory must be specified using the \"destdir\" attribute.", getLocation());
        }
    }
}
