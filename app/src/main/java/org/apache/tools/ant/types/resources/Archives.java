package org.apache.tools.ant.types.resources;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.ArchiveFileSet;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.TarFileSet;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.ant.util.CollectionUtils;

/* loaded from: classes2.dex */
public class Archives extends DataType implements Cloneable, ResourceCollection {
    private Union zips = new Union();
    private Union tars = new Union();

    public Union createZips() {
        if (!isReference()) {
            setChecked(false);
            return this.zips;
        }
        throw noChildrenAllowed();
    }

    public Union createTars() {
        if (!isReference()) {
            setChecked(false);
            return this.tars;
        }
        throw noChildrenAllowed();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public int size() {
        if (isReference()) {
            return ((Archives) getCheckedRef()).size();
        }
        dieOnCircularReference();
        int i = 0;
        Iterator<ArchiveFileSet> grabArchives = grabArchives();
        while (grabArchives.hasNext()) {
            i += grabArchives.next().size();
        }
        return i;
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public Iterator<Resource> iterator() {
        if (isReference()) {
            return ((Archives) getCheckedRef()).iterator();
        }
        dieOnCircularReference();
        LinkedList linkedList = new LinkedList();
        Iterator<ArchiveFileSet> grabArchives = grabArchives();
        while (grabArchives.hasNext()) {
            linkedList.addAll(CollectionUtils.asCollection(grabArchives.next().iterator()));
        }
        return linkedList.iterator();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        if (isReference()) {
            return ((Archives) getCheckedRef()).isFilesystemOnly();
        }
        dieOnCircularReference();
        return false;
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) {
        if (this.zips.getResourceCollections().size() > 0 || this.tars.getResourceCollections().size() > 0) {
            throw tooManyAttributes();
        }
        super.setRefid(reference);
    }

    @Override // org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        try {
            Archives archives = (Archives) super.clone();
            archives.zips = (Union) this.zips.clone();
            archives.tars = (Union) this.tars.clone();
            return archives;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    protected Iterator<ArchiveFileSet> grabArchives() {
        LinkedList linkedList = new LinkedList();
        Iterator<Resource> it = this.zips.iterator();
        while (it.hasNext()) {
            linkedList.add(configureArchive(new ZipFileSet(), it.next()));
        }
        Iterator<Resource> it2 = this.tars.iterator();
        while (it2.hasNext()) {
            linkedList.add(configureArchive(new TarFileSet(), it2.next()));
        }
        return linkedList.iterator();
    }

    protected ArchiveFileSet configureArchive(ArchiveFileSet archiveFileSet, Resource resource) {
        archiveFileSet.setProject(getProject());
        archiveFileSet.setSrcResource(resource);
        return archiveFileSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                pushAndInvokeCircularReferenceCheck(this.zips, stack, project);
                pushAndInvokeCircularReferenceCheck(this.tars, stack, project);
                setChecked(true);
            }
        }
    }
}
