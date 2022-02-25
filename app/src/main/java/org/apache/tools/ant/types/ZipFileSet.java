package org.apache.tools.ant.types;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public class ZipFileSet extends ArchiveFileSet {
    public ZipFileSet() {
    }

    protected ZipFileSet(FileSet fileSet) {
        super(fileSet);
    }

    protected ZipFileSet(ZipFileSet zipFileSet) {
        super((ArchiveFileSet) zipFileSet);
    }

    @Override // org.apache.tools.ant.types.ArchiveFileSet
    protected ArchiveScanner newArchiveScanner() {
        ZipScanner zipScanner = new ZipScanner();
        zipScanner.setEncoding(getEncoding());
        return zipScanner;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.AbstractFileSet
    public AbstractFileSet getRef(Project project) {
        dieOnCircularReference(project);
        Object referencedObject = getRefid().getReferencedObject(project);
        if (referencedObject instanceof ZipFileSet) {
            return (AbstractFileSet) referencedObject;
        }
        if (referencedObject instanceof FileSet) {
            ZipFileSet zipFileSet = new ZipFileSet((FileSet) referencedObject);
            configureFileSet(zipFileSet);
            return zipFileSet;
        }
        throw new BuildException(getRefid().getRefId() + " doesn't denote a zipfileset or a fileset");
    }

    @Override // org.apache.tools.ant.types.ArchiveFileSet, org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        if (isReference()) {
            return ((ZipFileSet) getRef(getProject())).clone();
        }
        return super.clone();
    }

    private void checkZipFileSetAttributesAllowed() {
        if (getProject() == null || (isReference() && (getRefid().getReferencedObject(getProject()) instanceof ZipFileSet))) {
            checkAttributesAllowed();
        }
    }
}
