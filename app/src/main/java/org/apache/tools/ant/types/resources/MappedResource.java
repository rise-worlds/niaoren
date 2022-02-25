package org.apache.tools.ant.types.resources;

import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.util.FileNameMapper;

/* loaded from: classes2.dex */
public class MappedResource extends ResourceDecorator {
    private final FileNameMapper mapper;

    public MappedResource(Resource resource, FileNameMapper fileNameMapper) {
        super(resource);
        this.mapper = fileNameMapper;
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource
    public String getName() {
        String name = getResource().getName();
        if (isReference()) {
            return name;
        }
        String[] mapFileName = this.mapper.mapFileName(name);
        if (mapFileName == null || mapFileName.length <= 0) {
            return null;
        }
        return mapFileName[0];
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) {
        if (this.mapper == null) {
            super.setRefid(reference);
            return;
        }
        throw noChildrenAllowed();
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource
    /* renamed from: as */
    public <T> T mo14823as(Class<T> cls) {
        if (FileProvider.class.isAssignableFrom(cls)) {
            return null;
        }
        return (T) getResource().mo14823as(cls);
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource
    public int hashCode() {
        String name = getName();
        return name == null ? super.hashCode() : name.hashCode();
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        MappedResource mappedResource = (MappedResource) obj;
        String name = getName();
        String name2 = mappedResource.getName();
        if (name == null) {
            if (name2 != null) {
                return false;
            }
        } else if (!name.equals(name2)) {
            return false;
        }
        return getResource().equals(mappedResource.getResource());
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        }
        return getName();
    }
}
