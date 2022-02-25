package org.apache.tools.ant.types.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.ant.util.MergingMapper;

/* loaded from: classes2.dex */
public class MappedResourceCollection extends DataType implements Cloneable, ResourceCollection {
    private ResourceCollection nested = null;
    private Mapper mapper = null;
    private boolean enableMultipleMappings = false;
    private boolean cache = false;
    private Collection<Resource> cachedColl = null;

    public synchronized void add(ResourceCollection resourceCollection) throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (this.nested == null) {
            setChecked(false);
            this.cachedColl = null;
            this.nested = resourceCollection;
        } else {
            throw new BuildException("Only one resource collection can be nested into mappedresources", getLocation());
        }
    }

    public Mapper createMapper() throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (this.mapper == null) {
            setChecked(false);
            this.mapper = new Mapper(getProject());
            this.cachedColl = null;
            return this.mapper;
        } else {
            throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS, getLocation());
        }
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    public void setEnableMultipleMappings(boolean z) {
        this.enableMultipleMappings = z;
    }

    public void setCache(boolean z) {
        this.cache = z;
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        if (isReference()) {
            return ((MappedResourceCollection) getCheckedRef()).isFilesystemOnly();
        }
        checkInitialized();
        return false;
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public int size() {
        if (isReference()) {
            return ((MappedResourceCollection) getCheckedRef()).size();
        }
        checkInitialized();
        return cacheCollection().size();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public Iterator<Resource> iterator() {
        if (isReference()) {
            return ((MappedResourceCollection) getCheckedRef()).iterator();
        }
        checkInitialized();
        return cacheCollection().iterator();
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) {
        if (this.nested == null && this.mapper == null) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    @Override // org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        try {
            MappedResourceCollection mappedResourceCollection = (MappedResourceCollection) super.clone();
            mappedResourceCollection.nested = this.nested;
            mappedResourceCollection.mapper = this.mapper;
            mappedResourceCollection.cachedColl = null;
            return mappedResourceCollection;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                checkInitialized();
                if (this.mapper != null) {
                    pushAndInvokeCircularReferenceCheck(this.mapper, stack, project);
                }
                if (this.nested instanceof DataType) {
                    pushAndInvokeCircularReferenceCheck((DataType) this.nested, stack, project);
                }
                setChecked(true);
            }
        }
    }

    private void checkInitialized() {
        if (this.nested != null) {
            dieOnCircularReference();
            return;
        }
        throw new BuildException("A nested resource collection element is required", getLocation());
    }

    private synchronized Collection<Resource> cacheCollection() {
        if (this.cachedColl == null || !this.cache) {
            this.cachedColl = getCollection();
        }
        return this.cachedColl;
    }

    private Collection<Resource> getCollection() {
        ArrayList arrayList = new ArrayList();
        Mapper mapper = this.mapper;
        FileNameMapper implementation = mapper != null ? mapper.getImplementation() : new IdentityMapper();
        for (Resource resource : this.nested) {
            if (this.enableMultipleMappings) {
                String[] mapFileName = implementation.mapFileName(resource.getName());
                if (mapFileName != null) {
                    for (String str : mapFileName) {
                        arrayList.add(new MappedResource(resource, new MergingMapper(str)));
                    }
                }
            } else {
                arrayList.add(new MappedResource(resource, implementation));
            }
        }
        return arrayList;
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        }
        Iterator<Resource> it = iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (it.hasNext()) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(File.pathSeparatorChar);
            }
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }
}
