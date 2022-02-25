package org.apache.tools.ant.taskdefs.optional.depend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.FileUtils;
import p110z1.cjm;

/* loaded from: classes2.dex */
public class Depend extends MatchingTask {
    private static final String CACHE_FILE_NAME = "dependencies.txt";
    private static final String CLASSNAME_PREPEND = "||:";
    private static final int ONE_SECOND = 1000;
    private Hashtable affectedClassMap;
    private File cache;
    private Hashtable classFileInfoMap;
    private Hashtable classpathDependencies;
    private Path dependClasspath;
    private Path destPath;
    private Hashtable outOfDateClasses;
    private Path srcPath;
    private String[] srcPathList;
    private boolean closure = false;
    private boolean warnOnRmiStubs = true;
    private boolean dump = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ClassFileInfo {
        private File absoluteFile;
        private String className;
        private boolean isUserWarned;
        private File sourceFile;

        private ClassFileInfo() {
            this.isUserWarned = false;
        }
    }

    public void setClasspath(Path path) {
        Path path2 = this.dependClasspath;
        if (path2 == null) {
            this.dependClasspath = path;
        } else {
            path2.append(path);
        }
    }

    public Path getClasspath() {
        return this.dependClasspath;
    }

    public Path createClasspath() {
        if (this.dependClasspath == null) {
            this.dependClasspath = new Path(getProject());
        }
        return this.dependClasspath.createPath();
    }

    public void setClasspathRef(Reference reference) {
        createClasspath().setRefid(reference);
    }

    public void setWarnOnRmiStubs(boolean z) {
        this.warnOnRmiStubs = z;
    }

    private Hashtable readCachedDependencies(File file) throws IOException {
        Throwable th;
        BufferedReader bufferedReader;
        Hashtable hashtable = new Hashtable();
        Vector vector = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        FileUtils.close(bufferedReader);
                        return hashtable;
                    } else if (readLine.startsWith(CLASSNAME_PREPEND)) {
                        vector = new Vector();
                        hashtable.put(readLine.substring(3), vector);
                    } else {
                        vector.addElement(readLine);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.close(bufferedReader);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    private void writeCachedDependencies(Hashtable hashtable) throws IOException {
        Throwable th;
        File file = this.cache;
        if (file != null) {
            BufferedWriter bufferedWriter = null;
            try {
                file.mkdirs();
                bufferedWriter = new BufferedWriter(new FileWriter(new File(this.cache, CACHE_FILE_NAME)));
                try {
                    Enumeration keys = hashtable.keys();
                    while (keys.hasMoreElements()) {
                        String str = (String) keys.nextElement();
                        bufferedWriter.write(CLASSNAME_PREPEND + str);
                        bufferedWriter.newLine();
                        Vector vector = (Vector) hashtable.get(str);
                        int size = vector.size();
                        for (int i = 0; i < size; i++) {
                            bufferedWriter.write(String.valueOf(vector.elementAt(i)));
                            bufferedWriter.newLine();
                        }
                    }
                    FileUtils.close(bufferedWriter);
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.close(bufferedWriter);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    private Path getCheckClassPath() {
        String[] list;
        Path path = null;
        if (this.dependClasspath == null) {
            return null;
        }
        String[] list2 = this.destPath.list();
        String str = "";
        for (String str2 : this.dependClasspath.list()) {
            boolean z = false;
            for (int i = 0; i < list2.length && !z; i++) {
                z = list2[i].equals(str2);
            }
            if (!z) {
                str = str.length() == 0 ? str2 : str + ":" + str2;
            }
        }
        if (str.length() > 0) {
            path = new Path(getProject(), str);
        }
        log("Classpath without dest dir is " + path, 4);
        return path;
    }

    private void determineDependencies() throws IOException {
        long j;
        boolean z;
        AntClassLoader antClassLoader;
        this.affectedClassMap = new Hashtable();
        this.classFileInfoMap = new Hashtable();
        Hashtable hashtable = new Hashtable();
        File file = this.cache;
        if (file != null) {
            File file2 = new File(file, CACHE_FILE_NAME);
            z = file2.exists();
            j = file2.lastModified();
            if (z) {
                hashtable = readCachedDependencies(file2);
            }
        } else {
            j = cjm.f20759b;
            z = true;
        }
        Enumeration elements = getClassFiles(this.destPath).elements();
        boolean z2 = false;
        while (true) {
            antClassLoader = null;
            r9 = null;
            r9 = null;
            Vector vector = null;
            if (!elements.hasMoreElements()) {
                break;
            }
            ClassFileInfo classFileInfo = (ClassFileInfo) elements.nextElement();
            log("Adding class info for " + classFileInfo.className, 4);
            this.classFileInfoMap.put(classFileInfo.className, classFileInfo);
            if (this.cache != null && z && j > classFileInfo.absoluteFile.lastModified()) {
                vector = (Vector) hashtable.get(classFileInfo.className);
            }
            if (vector == null) {
                AntAnalyzer antAnalyzer = new AntAnalyzer();
                antAnalyzer.addRootClass(classFileInfo.className);
                antAnalyzer.addClassPath(this.destPath);
                antAnalyzer.setClosure(false);
                vector = new Vector();
                Enumeration<String> classDependencies = antAnalyzer.getClassDependencies();
                while (classDependencies.hasMoreElements()) {
                    String nextElement = classDependencies.nextElement();
                    vector.addElement(nextElement);
                    log("Class " + classFileInfo.className + " depends on " + ((Object) nextElement), 4);
                }
                hashtable.put(classFileInfo.className, vector);
                z2 = true;
            }
            Enumeration elements2 = vector.elements();
            while (elements2.hasMoreElements()) {
                String str = (String) elements2.nextElement();
                Hashtable hashtable2 = (Hashtable) this.affectedClassMap.get(str);
                if (hashtable2 == null) {
                    hashtable2 = new Hashtable();
                    this.affectedClassMap.put(str, hashtable2);
                }
                hashtable2.put(classFileInfo.className, classFileInfo);
                log(str + " affects " + classFileInfo.className, 4);
            }
        }
        this.classpathDependencies = null;
        Path checkClassPath = getCheckClassPath();
        if (checkClassPath != null) {
            this.classpathDependencies = new Hashtable();
            try {
                antClassLoader = getProject().createClassLoader(checkClassPath);
                Hashtable hashtable3 = new Hashtable();
                Object obj = new Object();
                Enumeration keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    String str2 = (String) keys.nextElement();
                    log("Determining classpath dependencies for " + str2, 4);
                    Hashtable hashtable4 = new Hashtable();
                    this.classpathDependencies.put(str2, hashtable4);
                    Enumeration elements3 = ((Vector) hashtable.get(str2)).elements();
                    while (elements3.hasMoreElements()) {
                        String str3 = (String) elements3.nextElement();
                        log("Looking for " + str3, 4);
                        Object obj2 = hashtable3.get(str3);
                        if (obj2 == null) {
                            if (str3.startsWith("java.") || str3.startsWith("javax.")) {
                                log("Ignoring base classlib dependency " + str3, 4);
                                obj2 = obj;
                            } else {
                                URL resource = antClassLoader.getResource(str3.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + ".class");
                                log("URL is " + resource, 4);
                                if (resource != null) {
                                    if (resource.getProtocol().equals("jar")) {
                                        String file3 = resource.getFile();
                                        String substring = file3.substring(0, file3.indexOf(33));
                                        if (substring.startsWith("file:")) {
                                            obj2 = new File(FileUtils.getFileUtils().fromURI(substring));
                                        } else {
                                            throw new IOException("Bizarre nested path in jar: protocol: " + substring);
                                        }
                                    } else {
                                        obj2 = resource.getProtocol().equals("file") ? new File(FileUtils.getFileUtils().fromURI(resource.toExternalForm())) : obj;
                                    }
                                    log("Class " + str2 + " depends on " + obj2 + " due to " + str3, 4);
                                } else {
                                    obj2 = obj;
                                }
                            }
                            hashtable3.put(str3, obj2);
                        }
                        if (obj2 != obj) {
                            File file4 = (File) obj2;
                            log("Adding a classpath dependency on " + file4, 4);
                            hashtable4.put(file4, file4);
                        }
                    }
                }
            } finally {
                if (antClassLoader != null) {
                    antClassLoader.cleanup();
                }
            }
        } else {
            log("No classpath to check", 4);
        }
        if (this.cache != null && z2) {
            writeCachedDependencies(hashtable);
        }
    }

    private int deleteAllAffectedFiles() {
        Enumeration elements = this.outOfDateClasses.elements();
        int i = 0;
        while (elements.hasMoreElements()) {
            String str = (String) elements.nextElement();
            i += deleteAffectedFiles(str);
            ClassFileInfo classFileInfo = (ClassFileInfo) this.classFileInfoMap.get(str);
            if (classFileInfo != null && classFileInfo.absoluteFile.exists()) {
                if (classFileInfo.sourceFile == null) {
                    warnOutOfDateButNotDeleted(classFileInfo, str, str);
                } else {
                    classFileInfo.absoluteFile.delete();
                    i++;
                }
            }
        }
        return i;
    }

    private int deleteAffectedFiles(String str) {
        Hashtable hashtable = (Hashtable) this.affectedClassMap.get(str);
        if (hashtable == null) {
            return 0;
        }
        Enumeration keys = hashtable.keys();
        int i = 0;
        while (keys.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            ClassFileInfo classFileInfo = (ClassFileInfo) hashtable.get(str2);
            if (classFileInfo.absoluteFile.exists()) {
                if (classFileInfo.sourceFile == null) {
                    warnOutOfDateButNotDeleted(classFileInfo, str2, str);
                } else {
                    log("Deleting file " + classFileInfo.absoluteFile.getPath() + " since " + str + " out of date", 3);
                    classFileInfo.absoluteFile.delete();
                    i++;
                    if (this.closure) {
                        i += deleteAffectedFiles(str2);
                    } else if (str2.indexOf("$") != -1) {
                        String substring = str2.substring(0, str2.indexOf("$"));
                        log("Top level class = " + substring, 3);
                        ClassFileInfo classFileInfo2 = (ClassFileInfo) this.classFileInfoMap.get(substring);
                        if (classFileInfo2 != null && classFileInfo2.absoluteFile.exists()) {
                            log("Deleting file " + classFileInfo2.absoluteFile.getPath() + " since one of its inner classes was removed", 3);
                            classFileInfo2.absoluteFile.delete();
                            i++;
                            if (this.closure) {
                                i += deleteAffectedFiles(substring);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    private void warnOutOfDateButNotDeleted(ClassFileInfo classFileInfo, String str, String str2) {
        if (!classFileInfo.isUserWarned) {
            int i = (this.warnOnRmiStubs || !isRmiStub(str, str2)) ? 1 : 3;
            log("The class " + str + " in file " + classFileInfo.absoluteFile.getPath() + " is out of date due to " + str2 + " but has not been deleted because its source file could not be determined", i);
            classFileInfo.isUserWarned = true;
        }
    }

    private boolean isRmiStub(String str, String str2) {
        return isStub(str, str2, DefaultRmicAdapter.RMI_STUB_SUFFIX) || isStub(str, str2, DefaultRmicAdapter.RMI_SKEL_SUFFIX) || isStub(str, str2, DefaultRmicAdapter.RMI_STUB_SUFFIX) || isStub(str, str2, DefaultRmicAdapter.RMI_SKEL_SUFFIX);
    }

    private boolean isStub(String str, String str2, String str3) {
        return (str2 + str3).equals(str);
    }

    private void dumpDependencies() {
        log("Reverse Dependency Dump for " + this.affectedClassMap.size() + " classes:", 4);
        Enumeration keys = this.affectedClassMap.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            log(" Class " + str + " affects:", 4);
            Hashtable hashtable = (Hashtable) this.affectedClassMap.get(str);
            Enumeration keys2 = hashtable.keys();
            while (keys2.hasMoreElements()) {
                String str2 = (String) keys2.nextElement();
                log("    " + str2 + " in " + ((ClassFileInfo) hashtable.get(str2)).absoluteFile.getPath(), 4);
            }
        }
        if (this.classpathDependencies != null) {
            log("Classpath file dependencies (Forward):", 4);
            Enumeration keys3 = this.classpathDependencies.keys();
            while (keys3.hasMoreElements()) {
                String str3 = (String) keys3.nextElement();
                log(" Class " + str3 + " depends on:", 4);
                Enumeration elements = ((Hashtable) this.classpathDependencies.get(str3)).elements();
                while (elements.hasMoreElements()) {
                    log("    " + ((File) elements.nextElement()).getPath(), 4);
                }
            }
        }
    }

    private void determineOutOfDateClasses() {
        ClassFileInfo classFileInfo;
        this.outOfDateClasses = new Hashtable();
        for (int i = 0; i < this.srcPathList.length; i++) {
            File resolveFile = getProject().resolveFile(this.srcPathList[i]);
            if (resolveFile.exists()) {
                scanDir(resolveFile, getDirectoryScanner(resolveFile).getIncludedFiles());
            }
        }
        Hashtable hashtable = this.classpathDependencies;
        if (hashtable != null) {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                String str = (String) keys.nextElement();
                if (!this.outOfDateClasses.containsKey(str) && (classFileInfo = (ClassFileInfo) this.classFileInfoMap.get(str)) != null) {
                    Enumeration elements = ((Hashtable) this.classpathDependencies.get(str)).elements();
                    while (true) {
                        if (elements.hasMoreElements()) {
                            File file = (File) elements.nextElement();
                            if (file.lastModified() > classFileInfo.absoluteFile.lastModified()) {
                                log("Class " + str + " is out of date with respect to " + file, 4);
                                this.outOfDateClasses.put(str, str);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.srcPath != null) {
                this.srcPathList = this.srcPath.list();
                if (this.srcPathList.length != 0) {
                    if (this.destPath == null) {
                        this.destPath = this.srcPath;
                    }
                    if (this.cache != null && this.cache.exists() && !this.cache.isDirectory()) {
                        throw new BuildException("The cache, if specified, must point to a directory");
                    }
                    if (this.cache != null && !this.cache.exists()) {
                        this.cache.mkdirs();
                    }
                    determineDependencies();
                    if (this.dump) {
                        dumpDependencies();
                    }
                    determineOutOfDateClasses();
                    int deleteAllAffectedFiles = deleteAllAffectedFiles();
                    long currentTimeMillis2 = (System.currentTimeMillis() - currentTimeMillis) / 1000;
                    int i = deleteAllAffectedFiles > 0 ? 2 : 4;
                    log("Deleted " + deleteAllAffectedFiles + " out of date files in " + currentTimeMillis2 + " seconds", i);
                    return;
                }
                throw new BuildException("srcdir attribute must be non-empty", getLocation());
            }
            throw new BuildException("srcdir attribute must be set", getLocation());
        } catch (Exception e) {
            throw new BuildException(e);
        }
    }

    protected void scanDir(File file, String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            File file2 = new File(file, strArr[i]);
            if (strArr[i].endsWith(".java")) {
                String path = file2.getPath();
                String convertSlashName = ClassFileUtils.convertSlashName(path.substring(file.getPath().length() + 1, path.length() - 5));
                ClassFileInfo classFileInfo = (ClassFileInfo) this.classFileInfoMap.get(convertSlashName);
                if (classFileInfo == null) {
                    this.outOfDateClasses.put(convertSlashName, convertSlashName);
                } else if (file2.lastModified() > classFileInfo.absoluteFile.lastModified()) {
                    this.outOfDateClasses.put(convertSlashName, convertSlashName);
                }
            }
        }
    }

    private Vector getClassFiles(Path path) {
        String[] list = path.list();
        Vector vector = new Vector();
        for (String str : list) {
            File file = new File(str);
            if (file.isDirectory()) {
                addClassFiles(vector, file, file);
            }
        }
        return vector;
    }

    private File findSourceFile(String str, File file) {
        int indexOf;
        String str2;
        File file2;
        int i = 0;
        if (str.indexOf("$") != -1) {
            str2 = str.substring(0, indexOf) + ".java";
        } else {
            str2 = str + ".java";
        }
        while (true) {
            String[] strArr = this.srcPathList;
            if (i >= strArr.length) {
                return null;
            }
            file2 = new File(strArr[i], str2);
            if (file2.equals(file) || file2.exists()) {
                break;
            }
            i++;
        }
        return file2;
    }

    private void addClassFiles(Vector vector, File file, File file2) {
        String[] list = file.list();
        if (list != null) {
            int length = list.length;
            int length2 = file2.getPath().length();
            File file3 = null;
            for (int i = 0; i < length; i++) {
                File file4 = new File(file, list[i]);
                if (list[i].endsWith(".class")) {
                    ClassFileInfo classFileInfo = new ClassFileInfo();
                    classFileInfo.absoluteFile = file4;
                    String substring = file4.getPath().substring(length2 + 1, file4.getPath().length() - 6);
                    classFileInfo.className = ClassFileUtils.convertSlashName(substring);
                    file3 = findSourceFile(substring, file3);
                    classFileInfo.sourceFile = file3;
                    vector.addElement(classFileInfo);
                } else {
                    addClassFiles(vector, file4, file2);
                }
            }
        }
    }

    public void setSrcdir(Path path) {
        this.srcPath = path;
    }

    public void setDestDir(Path path) {
        this.destPath = path;
    }

    public void setCache(File file) {
        this.cache = file;
    }

    public void setClosure(boolean z) {
        this.closure = z;
    }

    public void setDump(boolean z) {
        this.dump = z;
    }
}
