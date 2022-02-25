package org.apache.tools.ant.taskdefs.optional.jsp;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.taskdefs.optional.jsp.compilers.JspCompilerAdapter;
import org.apache.tools.ant.taskdefs.optional.jsp.compilers.JspCompilerAdapterFactory;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* loaded from: classes2.dex */
public class JspC extends MatchingTask {
    private static final String FAIL_MSG = "Compile failed, messages should have been provided.";
    private Path classpath;
    private Path compilerClasspath;
    private File destDir;
    private String iepluginid;
    private boolean mapped;
    private String packageName;
    private Path src;
    private File uriroot;
    protected WebAppParameter webApp;
    private File webinc;
    private File webxml;
    private String compilerName = "jasper";
    private int verbose = 0;
    protected Vector compileList = new Vector();
    Vector javaFiles = new Vector();
    protected boolean failOnError = true;

    public void setSrcDir(Path path) {
        Path path2 = this.src;
        if (path2 == null) {
            this.src = path;
        } else {
            path2.append(path);
        }
    }

    public Path getSrcDir() {
        return this.src;
    }

    public void setDestdir(File file) {
        this.destDir = file;
    }

    public File getDestdir() {
        return this.destDir;
    }

    public void setPackage(String str) {
        this.packageName = str;
    }

    public String getPackage() {
        return this.packageName;
    }

    public void setVerbose(int i) {
        this.verbose = i;
    }

    public int getVerbose() {
        return this.verbose;
    }

    public void setFailonerror(boolean z) {
        this.failOnError = z;
    }

    public boolean getFailonerror() {
        return this.failOnError;
    }

    public String getIeplugin() {
        return this.iepluginid;
    }

    public void setIeplugin(String str) {
        this.iepluginid = str;
    }

    public boolean isMapped() {
        return this.mapped;
    }

    public void setMapped(boolean z) {
        this.mapped = z;
    }

    public void setUribase(File file) {
        log("Uribase is currently an unused parameter", 1);
    }

    public File getUribase() {
        return this.uriroot;
    }

    public void setUriroot(File file) {
        this.uriroot = file;
    }

    public File getUriroot() {
        return this.uriroot;
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

    public Path getClasspath() {
        return this.classpath;
    }

    public void setCompilerclasspath(Path path) {
        Path path2 = this.compilerClasspath;
        if (path2 == null) {
            this.compilerClasspath = path;
        } else {
            path2.append(path);
        }
    }

    public Path getCompilerclasspath() {
        return this.compilerClasspath;
    }

    public Path createCompilerclasspath() {
        if (this.compilerClasspath == null) {
            this.compilerClasspath = new Path(getProject());
        }
        return this.compilerClasspath.createPath();
    }

    public void setWebxml(File file) {
        this.webxml = file;
    }

    public File getWebxml() {
        return this.webxml;
    }

    public void setWebinc(File file) {
        this.webinc = file;
    }

    public File getWebinc() {
        return this.webinc;
    }

    public void addWebApp(WebAppParameter webAppParameter) throws BuildException {
        if (this.webApp == null) {
            this.webApp = webAppParameter;
            return;
        }
        throw new BuildException("Only one webapp can be specified");
    }

    public WebAppParameter getWebApp() {
        return this.webApp;
    }

    public void setCompiler(String str) {
        this.compilerName = str;
    }

    public Vector getCompileList() {
        return this.compileList;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File file = this.destDir;
        if (file == null) {
            throw new BuildException("destdir attribute must be set!", getLocation());
        } else if (file.isDirectory()) {
            File actualDestDir = getActualDestDir();
            AntClassLoader antClassLoader = null;
            try {
                String str = this.compilerName;
                antClassLoader = getProject().createClassLoader(this.compilerClasspath);
                JspCompilerAdapter compiler = JspCompilerAdapterFactory.getCompiler(str, this, antClassLoader);
                if (this.webApp != null) {
                    doCompilation(compiler);
                } else if (this.src != null) {
                    String[] list = this.src.list();
                    if (list.length == 0) {
                        throw new BuildException("srcdir attribute must be set!", getLocation());
                    } else if (compiler.implementsOwnDependencyChecking()) {
                        doCompilation(compiler);
                        if (antClassLoader != null) {
                            antClassLoader.cleanup();
                        }
                    } else {
                        JspMangler createMangler = compiler.createMangler();
                        resetFileLists();
                        int i = 0;
                        int i2 = 0;
                        while (i < list.length) {
                            File resolveFile = getProject().resolveFile(list[i]);
                            if (resolveFile.exists()) {
                                String[] includedFiles = getDirectoryScanner(resolveFile).getIncludedFiles();
                                int length = includedFiles.length;
                                scanDir(resolveFile, actualDestDir, createMangler, includedFiles);
                                i++;
                                i2 = length;
                            } else {
                                throw new BuildException("srcdir \"" + resolveFile.getPath() + "\" does not exist!", getLocation());
                            }
                        }
                        log("compiling " + this.compileList.size() + " files", 3);
                        if (this.compileList.size() > 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Compiling ");
                            sb.append(this.compileList.size());
                            sb.append(" source file");
                            sb.append(this.compileList.size() == 1 ? "" : "s");
                            sb.append(" to ");
                            sb.append(actualDestDir);
                            log(sb.toString());
                            doCompilation(compiler);
                        } else if (i2 == 0) {
                            log("there were no files to compile", 2);
                        } else {
                            log("all files are up to date", 3);
                        }
                        if (antClassLoader != null) {
                            antClassLoader.cleanup();
                        }
                    }
                } else {
                    throw new BuildException("srcdir attribute must be set!", getLocation());
                }
            } finally {
                if (antClassLoader != null) {
                    antClassLoader.cleanup();
                }
            }
        } else {
            throw new BuildException("destination directory \"" + this.destDir + "\" does not exist or is not a directory", getLocation());
        }
    }

    private File getActualDestDir() {
        if (this.packageName == null) {
            return this.destDir;
        }
        return new File(this.destDir.getPath() + File.separatorChar + this.packageName.replace(FilenameUtils.EXTENSION_SEPARATOR, File.separatorChar));
    }

    private void doCompilation(JspCompilerAdapter jspCompilerAdapter) throws BuildException {
        jspCompilerAdapter.setJspc(this);
        if (jspCompilerAdapter.execute()) {
            return;
        }
        if (!this.failOnError) {
            log(FAIL_MSG, 0);
            return;
        }
        throw new BuildException(FAIL_MSG, getLocation());
    }

    protected void resetFileLists() {
        this.compileList.removeAllElements();
    }

    protected void scanDir(File file, File file2, JspMangler jspMangler, String[] strArr) {
        long time = new Date().getTime();
        for (String str : strArr) {
            File file3 = new File(file, str);
            File mapToJavaFile = mapToJavaFile(jspMangler, file3, file, file2);
            if (mapToJavaFile != null) {
                if (file3.lastModified() > time) {
                    log("Warning: file modified in the future: " + str, 1);
                }
                if (isCompileNeeded(file3, mapToJavaFile)) {
                    this.compileList.addElement(file3.getAbsolutePath());
                    this.javaFiles.addElement(mapToJavaFile);
                }
            }
        }
    }

    private boolean isCompileNeeded(File file, File file2) {
        if (!file2.exists()) {
            log("Compiling " + file.getPath() + " because java file " + file2.getPath() + " does not exist", 3);
            return true;
        } else if (file.lastModified() > file2.lastModified()) {
            log("Compiling " + file.getPath() + " because it is out of date with respect to " + file2.getPath(), 3);
            return true;
        } else if (file2.length() != 0) {
            return false;
        } else {
            log("Compiling " + file.getPath() + " because java file " + file2.getPath() + " is empty", 3);
            return true;
        }
    }

    protected File mapToJavaFile(JspMangler jspMangler, File file, File file2, File file3) {
        if (!file.getName().endsWith(".jsp")) {
            return null;
        }
        return new File(file3, jspMangler.mapJspToJavaName(file));
    }

    public void deleteEmptyJavaFiles() {
        Vector vector = this.javaFiles;
        if (vector != null) {
            Enumeration elements = vector.elements();
            while (elements.hasMoreElements()) {
                File file = (File) elements.nextElement();
                if (file.exists() && file.length() == 0) {
                    log("deleting empty output file " + file);
                    file.delete();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class WebAppParameter {
        private File directory;

        public File getDirectory() {
            return this.directory;
        }

        public void setBaseDir(File file) {
            this.directory = file;
        }
    }
}
