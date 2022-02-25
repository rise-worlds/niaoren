package org.apache.tools.ant.types.resources;

import java.io.File;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public abstract class ArchiveResource extends Resource {
    private static final int NULL_ARCHIVE = Resource.getMagicNumber("null archive".getBytes());
    private Resource archive;
    private boolean haveEntry;
    private int mode;
    private boolean modeSet;

    protected abstract void fetchEntry();

    /* JADX INFO: Access modifiers changed from: protected */
    public ArchiveResource() {
        this.haveEntry = false;
        this.modeSet = false;
        this.mode = 0;
    }

    protected ArchiveResource(File file) {
        this(file, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArchiveResource(File file, boolean z) {
        this.haveEntry = false;
        this.modeSet = false;
        this.mode = 0;
        setArchive(file);
        this.haveEntry = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArchiveResource(Resource resource, boolean z) {
        this.haveEntry = false;
        this.modeSet = false;
        this.mode = 0;
        addConfigured(resource);
        this.haveEntry = z;
    }

    public void setArchive(File file) {
        checkAttributesAllowed();
        this.archive = new FileResource(file);
    }

    public void setMode(int i) {
        checkAttributesAllowed();
        this.mode = i;
        this.modeSet = true;
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        checkChildrenAllowed();
        if (this.archive != null) {
            throw new BuildException("you must not specify more than one archive");
        } else if (resourceCollection.size() == 1) {
            this.archive = resourceCollection.iterator().next();
        } else {
            throw new BuildException("only single argument resource collections are supported as archives");
        }
    }

    public Resource getArchive() {
        return isReference() ? ((ArchiveResource) getCheckedRef()).getArchive() : this.archive;
    }

    @Override // org.apache.tools.ant.types.Resource
    public long getLastModified() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getLastModified();
        }
        checkEntry();
        return super.getLastModified();
    }

    @Override // org.apache.tools.ant.types.Resource
    public long getSize() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getSize();
        }
        checkEntry();
        return super.getSize();
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean isDirectory() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).isDirectory();
        }
        checkEntry();
        return super.isDirectory();
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean isExists() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).isExists();
        }
        checkEntry();
        return super.isExists();
    }

    public int getMode() {
        if (isReference()) {
            return ((ArchiveResource) getCheckedRef()).getMode();
        }
        checkEntry();
        return this.mode;
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) {
        if (this.archive != null || this.modeSet) {
            throw tooManyAttributes();
        }
        super.setRefid(reference);
    }

    @Override // org.apache.tools.ant.types.Resource
    public int compareTo(Resource resource) {
        if (equals(resource)) {
            return 0;
        }
        return super.compareTo(resource);
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
        ArchiveResource archiveResource = (ArchiveResource) obj;
        return getArchive().equals(archiveResource.getArchive()) && getName().equals(archiveResource.getName());
    }

    @Override // org.apache.tools.ant.types.Resource
    public int hashCode() {
        return super.hashCode() * (getArchive() == null ? NULL_ARCHIVE : getArchive().hashCode());
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        }
        return getArchive().toString() + ':' + getName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized void checkEntry() throws BuildException {
        dieOnCircularReference();
        if (!this.haveEntry) {
            if (getName() != null) {
                Resource archive = getArchive();
                if (archive == null) {
                    throw new BuildException("archive attribute not set");
                } else if (!archive.isExists()) {
                    throw new BuildException(archive.toString() + DirectoryScanner.DOES_NOT_EXIST_POSTFIX);
                } else if (!archive.isDirectory()) {
                    fetchEntry();
                    this.haveEntry = true;
                } else {
                    throw new BuildException(archive + " denotes a directory.");
                }
            } else {
                throw new BuildException("entry name not set");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                if (this.archive != null) {
                    pushAndInvokeCircularReferenceCheck(this.archive, stack, project);
                }
                setChecked(true);
            }
        }
    }
}
