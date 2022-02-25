package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class Available extends Task implements Condition {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private String classname;
    private Path classpath;
    private File file;
    private String filename;
    private Path filepath;
    private AntClassLoader loader;
    private String property;
    private String resource;
    private FileDir type;
    private Object value = "true";
    private boolean isTask = false;
    private boolean ignoreSystemclasses = false;
    private boolean searchParents = false;

    public void setSearchParents(boolean z) {
        this.searchParents = z;
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

    public void setFilepath(Path path) {
        createFilepath().append(path);
    }

    public Path createFilepath() {
        if (this.filepath == null) {
            this.filepath = new Path(getProject());
        }
        return this.filepath.createPath();
    }

    public void setProperty(String str) {
        this.property = str;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public void setValue(String str) {
        setValue((Object) str);
    }

    public void setClassname(String str) {
        if (!"".equals(str)) {
            this.classname = str;
        }
    }

    public void setFile(File file) {
        this.file = file;
        this.filename = FILE_UTILS.removeLeadingPath(getProject().getBaseDir(), file);
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public void setType(String str) {
        log("DEPRECATED - The setType(String) method has been deprecated. Use setType(Available.FileDir) instead.", 1);
        this.type = new FileDir();
        this.type.setValue(str);
    }

    public void setType(FileDir fileDir) {
        this.type = fileDir;
    }

    public void setIgnoresystemclasses(boolean z) {
        this.ignoreSystemclasses = z;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.property != null) {
            this.isTask = true;
            try {
                if (eval()) {
                    PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(getProject());
                    Object property = propertyHelper.getProperty(this.property);
                    if (property != null && !property.equals(this.value)) {
                        log("DEPRECATED - <available> used to override an existing property." + StringUtils.LINE_SEP + "  Build file should not reuse the same property name for different values.", 1);
                    }
                    propertyHelper.setProperty(this.property, this.value, true);
                }
            } finally {
                this.isTask = false;
            }
        } else {
            throw new BuildException("property attribute is required", getLocation());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.tools.ant.AntClassLoader, java.lang.String] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean eval() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Available.eval():boolean");
    }

    private boolean checkFile() {
        Path path = this.filepath;
        if (path == null) {
            return checkFile(this.file, this.filename);
        }
        String[] list = path.list();
        for (int i = 0; i < list.length; i++) {
            log("Searching " + list[i], 3);
            File file = new File(list[i]);
            if (!file.exists() || (!this.filename.equals(list[i]) && !this.filename.equals(file.getName()))) {
                File parentFile = file.getParentFile();
                if (parentFile == null || !parentFile.exists() || !this.filename.equals(parentFile.getAbsolutePath())) {
                    if (file.exists() && file.isDirectory()) {
                        File file2 = new File(file, this.filename);
                        if (checkFile(file2, this.filename + " in " + file)) {
                            return true;
                        }
                    }
                    while (this.searchParents && parentFile != null && parentFile.exists()) {
                        File file3 = new File(parentFile, this.filename);
                        if (checkFile(file3, this.filename + " in " + parentFile)) {
                            return true;
                        }
                        parentFile = parentFile.getParentFile();
                    }
                } else {
                    FileDir fileDir = this.type;
                    if (fileDir == null) {
                        log("Found: " + parentFile, 3);
                        return true;
                    } else if (!fileDir.isDir()) {
                        return false;
                    } else {
                        log("Found directory: " + parentFile, 3);
                        return true;
                    }
                }
            } else {
                FileDir fileDir2 = this.type;
                if (fileDir2 == null) {
                    log("Found: " + file, 3);
                    return true;
                } else if (fileDir2.isDir() && file.isDirectory()) {
                    log("Found directory: " + file, 3);
                    return true;
                } else if (!this.type.isFile() || !file.isFile()) {
                    return false;
                } else {
                    log("Found file: " + file, 3);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkFile(File file, String str) {
        FileDir fileDir = this.type;
        if (fileDir != null) {
            if (fileDir.isDir()) {
                if (file.isDirectory()) {
                    log("Found directory: " + str, 3);
                }
                return file.isDirectory();
            } else if (this.type.isFile()) {
                if (file.isFile()) {
                    log("Found file: " + str, 3);
                }
                return file.isFile();
            }
        }
        if (file.exists()) {
            log("Found: " + str, 3);
        }
        return file.exists();
    }

    private boolean checkResource(String str) {
        AntClassLoader antClassLoader = this.loader;
        if (antClassLoader != null) {
            return antClassLoader.getResourceAsStream(str) != null;
        }
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader != null ? classLoader.getResourceAsStream(str) != null : ClassLoader.getSystemResourceAsStream(str) != null;
    }

    private boolean checkClass(String str) {
        try {
            if (this.ignoreSystemclasses) {
                this.loader = getProject().createClassLoader(this.classpath);
                this.loader.setParentFirst(false);
                this.loader.addJavaLibraries();
                try {
                    this.loader.findClass(str);
                } catch (SecurityException unused) {
                    return true;
                }
            } else if (this.loader != null) {
                this.loader.loadClass(str);
            } else {
                ClassLoader classLoader = getClass().getClassLoader();
                if (classLoader != null) {
                    Class.forName(str, true, classLoader);
                } else {
                    Class.forName(str);
                }
            }
            return true;
        } catch (ClassNotFoundException unused2) {
            log("class \"" + str + "\" was not found", 4);
            return false;
        } catch (NoClassDefFoundError e) {
            log("Could not load dependent class \"" + e.getMessage() + "\" for class \"" + str + "\"", 4);
            return false;
        }
    }

    /* loaded from: classes2.dex */
    public static class FileDir extends EnumeratedAttribute {
        private static final String[] VALUES = {"file", "dir"};

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return VALUES;
        }

        public boolean isDir() {
            return "dir".equalsIgnoreCase(getValue());
        }

        public boolean isFile() {
            return "file".equalsIgnoreCase(getValue());
        }
    }
}
