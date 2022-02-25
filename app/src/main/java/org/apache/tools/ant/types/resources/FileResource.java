package org.apache.tools.ant.types.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceFactory;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class FileResource extends Resource implements ResourceFactory, Appendable, FileProvider, Touchable {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final int NULL_FILE = Resource.getMagicNumber("null file".getBytes());
    private File baseDir;
    private File file;

    public FileResource() {
    }

    public FileResource(File file, String str) {
        this.baseDir = file;
        this.file = FILE_UTILS.resolveFile(file, str);
    }

    public FileResource(File file) {
        setFile(file);
    }

    public FileResource(Project project, File file) {
        this(file);
        setProject(project);
    }

    public FileResource(Project project, String str) {
        this(project, project.resolveFile(str));
    }

    public void setFile(File file) {
        checkAttributesAllowed();
        this.file = file;
        if (file == null) {
            return;
        }
        if (getBaseDir() == null || !FILE_UTILS.isLeadingPath(getBaseDir(), file)) {
            setBaseDir(file.getParentFile());
        }
    }

    @Override // org.apache.tools.ant.types.resources.FileProvider
    public File getFile() {
        if (isReference()) {
            return ((FileResource) getCheckedRef()).getFile();
        }
        dieOnCircularReference();
        synchronized (this) {
            if (this.file == null) {
                File baseDir = getBaseDir();
                String name = super.getName();
                if (name != null) {
                    setFile(FILE_UTILS.resolveFile(baseDir, name));
                }
            }
        }
        return this.file;
    }

    public void setBaseDir(File file) {
        checkAttributesAllowed();
        this.baseDir = file;
    }

    public File getBaseDir() {
        if (isReference()) {
            return ((FileResource) getCheckedRef()).getBaseDir();
        }
        dieOnCircularReference();
        return this.baseDir;
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) {
        if (this.file == null && this.baseDir == null) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    @Override // org.apache.tools.ant.types.Resource
    public String getName() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getName();
        }
        File baseDir = getBaseDir();
        return baseDir == null ? getNotNullFile().getName() : FILE_UTILS.removeLeadingPath(baseDir, getNotNullFile());
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean isExists() {
        return isReference() ? ((Resource) getCheckedRef()).isExists() : getNotNullFile().exists();
    }

    @Override // org.apache.tools.ant.types.Resource
    public long getLastModified() {
        return isReference() ? ((Resource) getCheckedRef()).getLastModified() : getNotNullFile().lastModified();
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean isDirectory() {
        return isReference() ? ((Resource) getCheckedRef()).isDirectory() : getNotNullFile().isDirectory();
    }

    @Override // org.apache.tools.ant.types.Resource
    public long getSize() {
        return isReference() ? ((Resource) getCheckedRef()).getSize() : getNotNullFile().length();
    }

    @Override // org.apache.tools.ant.types.Resource
    public InputStream getInputStream() throws IOException {
        return isReference() ? ((Resource) getCheckedRef()).getInputStream() : new FileInputStream(getNotNullFile());
    }

    @Override // org.apache.tools.ant.types.Resource
    public OutputStream getOutputStream() throws IOException {
        if (isReference()) {
            return ((FileResource) getCheckedRef()).getOutputStream();
        }
        return getOutputStream(false);
    }

    @Override // org.apache.tools.ant.types.resources.Appendable
    public OutputStream getAppendOutputStream() throws IOException {
        if (isReference()) {
            return ((FileResource) getCheckedRef()).getAppendOutputStream();
        }
        return getOutputStream(true);
    }

    private OutputStream getOutputStream(boolean z) throws IOException {
        File notNullFile = getNotNullFile();
        if (!notNullFile.exists()) {
            File parentFile = notNullFile.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
        } else if (notNullFile.isFile() && !z) {
            notNullFile.delete();
        }
        return z ? new FileOutputStream(notNullFile.getAbsolutePath(), true) : new FileOutputStream(notNullFile);
    }

    @Override // org.apache.tools.ant.types.Resource
    public int compareTo(Resource resource) {
        if (isReference()) {
            return ((Resource) getCheckedRef()).compareTo(resource);
        }
        if (equals(resource)) {
            return 0;
        }
        FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
        if (fileProvider == null) {
            return super.compareTo(resource);
        }
        File file = getFile();
        if (file == null) {
            return -1;
        }
        File file2 = fileProvider.getFile();
        if (file2 == null) {
            return 1;
        }
        return file.compareTo(file2);
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (isReference()) {
            return getCheckedRef().equals(obj);
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        FileResource fileResource = (FileResource) obj;
        return getFile() == null ? fileResource.getFile() == null : getFile().equals(fileResource.getFile());
    }

    @Override // org.apache.tools.ant.types.Resource
    public int hashCode() {
        if (isReference()) {
            return getCheckedRef().hashCode();
        }
        return MAGIC * (getFile() == null ? NULL_FILE : getFile().hashCode());
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        }
        File file = this.file;
        if (file == null) {
            return "(unbound file resource)";
        }
        return FILE_UTILS.normalize(file.getAbsolutePath()).getAbsolutePath();
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        if (isReference()) {
            return ((FileResource) getCheckedRef()).isFilesystemOnly();
        }
        dieOnCircularReference();
        return true;
    }

    @Override // org.apache.tools.ant.types.resources.Touchable
    public void touch(long j) {
        if (isReference()) {
            ((FileResource) getCheckedRef()).touch(j);
        } else if (!getNotNullFile().setLastModified(j)) {
            log("Failed to change file modification time", 1);
        }
    }

    protected File getNotNullFile() {
        if (getFile() != null) {
            dieOnCircularReference();
            return getFile();
        }
        throw new BuildException("file attribute is null!");
    }

    @Override // org.apache.tools.ant.types.ResourceFactory
    public Resource getResource(String str) {
        File resolveFile = FILE_UTILS.resolveFile(getFile(), str);
        FileResource fileResource = new FileResource(resolveFile);
        if (FILE_UTILS.isLeadingPath(getBaseDir(), resolveFile)) {
            fileResource.setBaseDir(getBaseDir());
        }
        return fileResource;
    }
}
