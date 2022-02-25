package org.apache.tools.ant.types.optional.depend;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.depend.DependencyAnalyzer;

/* loaded from: classes2.dex */
public class DependScanner extends DirectoryScanner {
    public static final String DEFAULT_ANALYZER_CLASS = "org.apache.tools.ant.util.depend.bcel.FullAnalyzer";
    private Vector<File> additionalBaseDirs = new Vector<>();
    private Vector<String> included;
    private DirectoryScanner parentScanner;
    private Vector<String> rootClasses;

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public void addDefaultExcludes() {
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getExcludedDirectories() {
        return null;
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getExcludedFiles() {
        return null;
    }

    @Override // org.apache.tools.ant.DirectoryScanner
    public int getIncludedDirsCount() {
        return 0;
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getNotIncludedDirectories() {
        return null;
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getNotIncludedFiles() {
        return null;
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public void setCaseSensitive(boolean z) {
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public void setExcludes(String[] strArr) {
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public void setIncludes(String[] strArr) {
    }

    public DependScanner(DirectoryScanner directoryScanner) {
        this.parentScanner = directoryScanner;
    }

    public synchronized void setRootClasses(Vector<String> vector) {
        this.rootClasses = vector;
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getIncludedFiles() {
        String[] strArr = new String[getIncludedFilesCount()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = this.included.elementAt(i);
        }
        return strArr;
    }

    @Override // org.apache.tools.ant.DirectoryScanner
    public synchronized int getIncludedFilesCount() {
        if (this.included != null) {
        } else {
            throw new IllegalStateException();
        }
        return this.included.size();
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public synchronized void scan() throws IllegalStateException {
        this.included = new Vector<>();
        try {
            DependencyAnalyzer dependencyAnalyzer = (DependencyAnalyzer) Class.forName("org.apache.tools.ant.util.depend.bcel.FullAnalyzer").asSubclass(DependencyAnalyzer.class).newInstance();
            dependencyAnalyzer.addClassPath(new Path(null, this.basedir.getPath()));
            Enumeration<File> elements = this.additionalBaseDirs.elements();
            while (elements.hasMoreElements()) {
                dependencyAnalyzer.addClassPath(new Path(null, elements.nextElement().getPath()));
            }
            Enumeration<String> elements2 = this.rootClasses.elements();
            while (elements2.hasMoreElements()) {
                dependencyAnalyzer.addRootClass(elements2.nextElement());
            }
            Enumeration<String> classDependencies = dependencyAnalyzer.getClassDependencies();
            String[] includedFiles = this.parentScanner.getIncludedFiles();
            Hashtable hashtable = new Hashtable();
            for (int i = 0; i < includedFiles.length; i++) {
                hashtable.put(includedFiles[i], includedFiles[i]);
            }
            while (classDependencies.hasMoreElements()) {
                String str = classDependencies.nextElement().replace(FilenameUtils.EXTENSION_SEPARATOR, File.separatorChar) + ".class";
                if (new File(this.basedir, str).exists() && hashtable.containsKey(str)) {
                    this.included.addElement(str);
                }
            }
        } catch (Exception e) {
            throw new BuildException("Unable to load dependency analyzer: org.apache.tools.ant.util.depend.bcel.FullAnalyzer", e);
        }
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getIncludedDirectories() {
        return new String[0];
    }

    public void addBasedir(File file) {
        this.additionalBaseDirs.addElement(file);
    }
}
