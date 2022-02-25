package org.apache.tools.ant.types;

import java.io.File;
import java.util.Iterator;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;

/* loaded from: classes2.dex */
public abstract class ArchiveFileSet extends FileSet {
    private static final int BASE_OCTAL = 8;
    public static final int DEFAULT_DIR_MODE = 16877;
    public static final int DEFAULT_FILE_MODE = 33188;
    private static final String ERROR_DIR_AND_SRC_ATTRIBUTES = "Cannot set both dir and src attributes";
    private static final String ERROR_PATH_AND_PREFIX = "Cannot set both fullpath and prefix attributes";
    private int dirMode;
    private boolean dirModeHasBeenSet;
    private String encoding;
    private boolean errorOnMissingArchive;
    private int fileMode;
    private boolean fileModeHasBeenSet;
    private String fullpath;
    private boolean hasDir;
    private String prefix;
    private Resource src;

    protected abstract ArchiveScanner newArchiveScanner();

    public ArchiveFileSet() {
        this.src = null;
        this.prefix = "";
        this.fullpath = "";
        this.hasDir = false;
        this.fileMode = 33188;
        this.dirMode = 16877;
        this.fileModeHasBeenSet = false;
        this.dirModeHasBeenSet = false;
        this.errorOnMissingArchive = true;
        this.encoding = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArchiveFileSet(FileSet fileSet) {
        super(fileSet);
        this.src = null;
        this.prefix = "";
        this.fullpath = "";
        this.hasDir = false;
        this.fileMode = 33188;
        this.dirMode = 16877;
        this.fileModeHasBeenSet = false;
        this.dirModeHasBeenSet = false;
        this.errorOnMissingArchive = true;
        this.encoding = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArchiveFileSet(ArchiveFileSet archiveFileSet) {
        super(archiveFileSet);
        this.src = null;
        this.prefix = "";
        this.fullpath = "";
        this.hasDir = false;
        this.fileMode = 33188;
        this.dirMode = 16877;
        this.fileModeHasBeenSet = false;
        this.dirModeHasBeenSet = false;
        this.errorOnMissingArchive = true;
        this.encoding = null;
        this.src = archiveFileSet.src;
        this.prefix = archiveFileSet.prefix;
        this.fullpath = archiveFileSet.fullpath;
        this.hasDir = archiveFileSet.hasDir;
        this.fileMode = archiveFileSet.fileMode;
        this.dirMode = archiveFileSet.dirMode;
        this.fileModeHasBeenSet = archiveFileSet.fileModeHasBeenSet;
        this.dirModeHasBeenSet = archiveFileSet.dirModeHasBeenSet;
        this.errorOnMissingArchive = archiveFileSet.errorOnMissingArchive;
        this.encoding = archiveFileSet.encoding;
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    public void setDir(File file) throws BuildException {
        checkAttributesAllowed();
        if (this.src == null) {
            super.setDir(file);
            this.hasDir = true;
            return;
        }
        throw new BuildException(ERROR_DIR_AND_SRC_ATTRIBUTES);
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        checkChildrenAllowed();
        if (resourceCollection.size() == 1) {
            setSrcResource(resourceCollection.iterator().next());
            return;
        }
        throw new BuildException("only single argument resource collections are supported as archives");
    }

    public void setSrc(File file) {
        setSrcResource(new FileResource(file));
    }

    public void setSrcResource(Resource resource) {
        checkArchiveAttributesAllowed();
        if (!this.hasDir) {
            this.src = resource;
            setChecked(false);
            return;
        }
        throw new BuildException(ERROR_DIR_AND_SRC_ATTRIBUTES);
    }

    public File getSrc(Project project) {
        if (isReference()) {
            return ((ArchiveFileSet) getRef(project)).getSrc(project);
        }
        return getSrc();
    }

    public void setErrorOnMissingArchive(boolean z) {
        checkAttributesAllowed();
        this.errorOnMissingArchive = z;
    }

    public File getSrc() {
        FileProvider fileProvider;
        if (isReference()) {
            return ((ArchiveFileSet) getCheckedRef()).getSrc();
        }
        dieOnCircularReference();
        Resource resource = this.src;
        if (resource == null || (fileProvider = (FileProvider) resource.mo14823as(FileProvider.class)) == null) {
            return null;
        }
        return fileProvider.getFile();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public Object getCheckedRef(Project project) {
        return getRef(project);
    }

    public void setPrefix(String str) {
        checkArchiveAttributesAllowed();
        if ("".equals(str) || "".equals(this.fullpath)) {
            this.prefix = str;
            return;
        }
        throw new BuildException(ERROR_PATH_AND_PREFIX);
    }

    public String getPrefix(Project project) {
        if (isReference()) {
            return ((ArchiveFileSet) getRef(project)).getPrefix(project);
        }
        dieOnCircularReference(project);
        return this.prefix;
    }

    public void setFullpath(String str) {
        checkArchiveAttributesAllowed();
        if ("".equals(this.prefix) || "".equals(str)) {
            this.fullpath = str;
            return;
        }
        throw new BuildException(ERROR_PATH_AND_PREFIX);
    }

    public String getFullpath(Project project) {
        if (isReference()) {
            return ((ArchiveFileSet) getRef(project)).getFullpath(project);
        }
        dieOnCircularReference(project);
        return this.fullpath;
    }

    public void setEncoding(String str) {
        checkAttributesAllowed();
        this.encoding = str;
    }

    public String getEncoding() {
        if (!isReference()) {
            return this.encoding;
        }
        AbstractFileSet ref = getRef(getProject());
        if (ref instanceof ArchiveFileSet) {
            return ((ArchiveFileSet) ref).getEncoding();
        }
        return null;
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    public DirectoryScanner getDirectoryScanner(Project project) {
        if (isReference()) {
            return getRef(project).getDirectoryScanner(project);
        }
        dieOnCircularReference();
        Resource resource = this.src;
        if (resource == null) {
            return super.getDirectoryScanner(project);
        }
        if (!resource.isExists() && this.errorOnMissingArchive) {
            throw new BuildException("The archive " + this.src.getName() + " doesn't exist");
        } else if (!this.src.isDirectory()) {
            ArchiveScanner newArchiveScanner = newArchiveScanner();
            newArchiveScanner.setErrorOnMissingArchive(this.errorOnMissingArchive);
            newArchiveScanner.setSrc(this.src);
            super.setDir(project.getBaseDir());
            setupDirectoryScanner(newArchiveScanner, project);
            newArchiveScanner.init();
            return newArchiveScanner;
        } else {
            throw new BuildException("The archive " + this.src.getName() + " can't be a directory");
        }
    }

    @Override // org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public Iterator<Resource> iterator() {
        if (isReference()) {
            return ((ResourceCollection) getRef(getProject())).iterator();
        }
        if (this.src == null) {
            return super.iterator();
        }
        return ((ArchiveScanner) getDirectoryScanner(getProject())).getResourceFiles(getProject());
    }

    @Override // org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.ResourceCollection
    public int size() {
        if (isReference()) {
            return ((ResourceCollection) getRef(getProject())).size();
        }
        if (this.src == null) {
            return super.size();
        }
        return ((ArchiveScanner) getDirectoryScanner(getProject())).getIncludedFilesCount();
    }

    @Override // org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        if (isReference()) {
            return ((ArchiveFileSet) getCheckedRef()).isFilesystemOnly();
        }
        dieOnCircularReference();
        return this.src == null;
    }

    public void setFileMode(String str) {
        checkArchiveAttributesAllowed();
        integerSetFileMode(Integer.parseInt(str, 8));
    }

    public void integerSetFileMode(int i) {
        this.fileModeHasBeenSet = true;
        this.fileMode = i | 32768;
    }

    public int getFileMode(Project project) {
        if (isReference()) {
            return ((ArchiveFileSet) getRef(project)).getFileMode(project);
        }
        dieOnCircularReference();
        return this.fileMode;
    }

    public boolean hasFileModeBeenSet() {
        if (isReference()) {
            return ((ArchiveFileSet) getRef(getProject())).hasFileModeBeenSet();
        }
        dieOnCircularReference();
        return this.fileModeHasBeenSet;
    }

    public void setDirMode(String str) {
        checkArchiveAttributesAllowed();
        integerSetDirMode(Integer.parseInt(str, 8));
    }

    public void integerSetDirMode(int i) {
        this.dirModeHasBeenSet = true;
        this.dirMode = i | 16384;
    }

    public int getDirMode(Project project) {
        if (isReference()) {
            return ((ArchiveFileSet) getRef(project)).getDirMode(project);
        }
        dieOnCircularReference();
        return this.dirMode;
    }

    public boolean hasDirModeBeenSet() {
        if (isReference()) {
            return ((ArchiveFileSet) getRef(getProject())).hasDirModeBeenSet();
        }
        dieOnCircularReference();
        return this.dirModeHasBeenSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void configureFileSet(ArchiveFileSet archiveFileSet) {
        archiveFileSet.setPrefix(this.prefix);
        archiveFileSet.setFullpath(this.fullpath);
        archiveFileSet.fileModeHasBeenSet = this.fileModeHasBeenSet;
        archiveFileSet.fileMode = this.fileMode;
        archiveFileSet.dirModeHasBeenSet = this.dirModeHasBeenSet;
        archiveFileSet.dirMode = this.dirMode;
    }

    @Override // org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        if (isReference()) {
            return ((ArchiveFileSet) getCheckedRef(ArchiveFileSet.class, getDataTypeName(), getProject())).clone();
        }
        return super.clone();
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType
    public String toString() {
        if (this.hasDir && getProject() != null) {
            return super.toString();
        }
        Resource resource = this.src;
        if (resource == null) {
            return null;
        }
        return resource.getName();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getFullpath() {
        return this.fullpath;
    }

    public int getFileMode() {
        return this.fileMode;
    }

    public int getDirMode() {
        return this.dirMode;
    }

    private void checkArchiveAttributesAllowed() {
        if (getProject() == null || (isReference() && (getRefid().getReferencedObject(getProject()) instanceof ArchiveFileSet))) {
            checkAttributesAllowed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            super.dieOnCircularReference(stack, project);
            if (!isReference()) {
                if (this.src != null) {
                    pushAndInvokeCircularReferenceCheck(this.src, stack, project);
                }
                setChecked(true);
            }
        }
    }
}
