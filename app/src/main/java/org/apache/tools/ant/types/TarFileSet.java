package org.apache.tools.ant.types;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public class TarFileSet extends ArchiveFileSet {
    private int gid;
    private boolean groupIdSet;
    private boolean groupNameSet;
    private int uid;
    private boolean userIdSet;
    private boolean userNameSet;
    private String userName = "";
    private String groupName = "";

    public TarFileSet() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TarFileSet(FileSet fileSet) {
        super(fileSet);
    }

    protected TarFileSet(TarFileSet tarFileSet) {
        super((ArchiveFileSet) tarFileSet);
    }

    public void setUserName(String str) {
        checkTarFileSetAttributesAllowed();
        this.userNameSet = true;
        this.userName = str;
    }

    public String getUserName() {
        if (isReference()) {
            return ((TarFileSet) getCheckedRef()).getUserName();
        }
        return this.userName;
    }

    public boolean hasUserNameBeenSet() {
        return this.userNameSet;
    }

    public void setUid(int i) {
        checkTarFileSetAttributesAllowed();
        this.userIdSet = true;
        this.uid = i;
    }

    public int getUid() {
        if (isReference()) {
            return ((TarFileSet) getCheckedRef()).getUid();
        }
        return this.uid;
    }

    public boolean hasUserIdBeenSet() {
        return this.userIdSet;
    }

    public void setGroup(String str) {
        checkTarFileSetAttributesAllowed();
        this.groupNameSet = true;
        this.groupName = str;
    }

    public String getGroup() {
        if (isReference()) {
            return ((TarFileSet) getCheckedRef()).getGroup();
        }
        return this.groupName;
    }

    public boolean hasGroupBeenSet() {
        return this.groupNameSet;
    }

    public void setGid(int i) {
        checkTarFileSetAttributesAllowed();
        this.groupIdSet = true;
        this.gid = i;
    }

    public int getGid() {
        if (isReference()) {
            return ((TarFileSet) getCheckedRef()).getGid();
        }
        return this.gid;
    }

    public boolean hasGroupIdBeenSet() {
        return this.groupIdSet;
    }

    @Override // org.apache.tools.ant.types.ArchiveFileSet
    protected ArchiveScanner newArchiveScanner() {
        TarScanner tarScanner = new TarScanner();
        tarScanner.setEncoding(getEncoding());
        return tarScanner;
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.userNameSet || this.userIdSet || this.groupNameSet || this.groupIdSet) {
            throw tooManyAttributes();
        }
        super.setRefid(reference);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.AbstractFileSet
    public AbstractFileSet getRef(Project project) {
        dieOnCircularReference(project);
        Object referencedObject = getRefid().getReferencedObject(project);
        if (referencedObject instanceof TarFileSet) {
            return (AbstractFileSet) referencedObject;
        }
        if (referencedObject instanceof FileSet) {
            TarFileSet tarFileSet = new TarFileSet((FileSet) referencedObject);
            configureFileSet(tarFileSet);
            return tarFileSet;
        }
        throw new BuildException(getRefid().getRefId() + " doesn't denote a tarfileset or a fileset");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.ArchiveFileSet
    public void configureFileSet(ArchiveFileSet archiveFileSet) {
        super.configureFileSet(archiveFileSet);
        if (archiveFileSet instanceof TarFileSet) {
            TarFileSet tarFileSet = (TarFileSet) archiveFileSet;
            tarFileSet.setUserName(this.userName);
            tarFileSet.setGroup(this.groupName);
            tarFileSet.setUid(this.uid);
            tarFileSet.setGid(this.gid);
        }
    }

    @Override // org.apache.tools.ant.types.ArchiveFileSet, org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.AbstractFileSet, org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        if (isReference()) {
            return ((TarFileSet) getRef(getProject())).clone();
        }
        return super.clone();
    }

    private void checkTarFileSetAttributesAllowed() {
        if (getProject() == null || (isReference() && (getRefid().getReferencedObject(getProject()) instanceof TarFileSet))) {
            checkAttributesAllowed();
        }
    }
}
