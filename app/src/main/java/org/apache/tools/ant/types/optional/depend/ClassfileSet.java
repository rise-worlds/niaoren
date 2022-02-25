package org.apache.tools.ant.types.optional.depend;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class ClassfileSet extends FileSet {
    private List<String> rootClasses = new ArrayList();
    private List<FileSet> rootFileSets = new ArrayList();

    /* loaded from: classes2.dex */
    public static class ClassRoot {
        private String rootClass;

        public void setClassname(String str) {
            this.rootClass = str;
        }

        public String getClassname() {
            return this.rootClass;
        }
    }

    public ClassfileSet() {
    }

    public void addRootFileset(FileSet fileSet) {
        this.rootFileSets.add(fileSet);
        setChecked(false);
    }

    protected ClassfileSet(ClassfileSet classfileSet) {
        super(classfileSet);
        this.rootClasses.addAll(classfileSet.rootClasses);
    }

    public void setRootClass(String str) {
        this.rootClasses.add(str);
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    public DirectoryScanner getDirectoryScanner(Project project) {
        if (isReference()) {
            return getRef(project).getDirectoryScanner(project);
        }
        dieOnCircularReference(project);
        DependScanner dependScanner = new DependScanner(super.getDirectoryScanner(project));
        Vector<String> vector = new Vector<>(this.rootClasses);
        for (FileSet fileSet : this.rootFileSets) {
            String[] includedFiles = fileSet.getDirectoryScanner(project).getIncludedFiles();
            for (int i = 0; i < includedFiles.length; i++) {
                if (includedFiles[i].endsWith(".class")) {
                    vector.addElement(StringUtils.removeSuffix(includedFiles[i], ".class").replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR).replace(IOUtils.DIR_SEPARATOR_WINDOWS, FilenameUtils.EXTENSION_SEPARATOR));
                }
            }
            dependScanner.addBasedir(fileSet.getDir(project));
        }
        dependScanner.setBasedir(getDir(project));
        dependScanner.setRootClasses(vector);
        dependScanner.scan();
        return dependScanner;
    }

    public void addConfiguredRoot(ClassRoot classRoot) {
        this.rootClasses.add(classRoot.getClassname());
    }

    @Override // org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        return new ClassfileSet(isReference() ? (ClassfileSet) getRef(getProject()) : this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) {
        if (!isChecked()) {
            super.dieOnCircularReference(stack, project);
            if (!isReference()) {
                for (FileSet fileSet : this.rootFileSets) {
                    pushAndInvokeCircularReferenceCheck(fileSet, stack, project);
                }
                setChecked(true);
            }
        }
    }
}
