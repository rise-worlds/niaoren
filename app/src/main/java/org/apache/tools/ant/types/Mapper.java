package org.apache.tools.ant.types;

import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.util.Properties;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.CompositeMapper;
import org.apache.tools.ant.util.ContainerMapper;
import org.apache.tools.ant.util.FileNameMapper;

/* loaded from: classes2.dex */
public class Mapper extends DataType implements Cloneable {
    protected MapperType type = null;
    protected String classname = null;
    protected Path classpath = null;
    protected String from = null;

    /* renamed from: to */
    protected String f14764to = null;
    private ContainerMapper container = null;

    public Mapper(Project project) {
        setProject(project);
    }

    public void setType(MapperType mapperType) {
        if (!isReference()) {
            this.type = mapperType;
            return;
        }
        throw tooManyAttributes();
    }

    public void addConfigured(FileNameMapper fileNameMapper) {
        add(fileNameMapper);
    }

    public void add(FileNameMapper fileNameMapper) {
        if (!isReference()) {
            if (this.container == null) {
                if (this.type == null && this.classname == null) {
                    this.container = new CompositeMapper();
                } else {
                    FileNameMapper implementation = getImplementation();
                    if (implementation instanceof ContainerMapper) {
                        this.container = (ContainerMapper) implementation;
                    } else {
                        throw new BuildException(String.valueOf(implementation) + " mapper implementation does not support nested mappers!");
                    }
                }
            }
            this.container.add(fileNameMapper);
            setChecked(false);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addConfiguredMapper(Mapper mapper) {
        add(mapper.getImplementation());
    }

    public void setClassname(String str) {
        if (!isReference()) {
            this.classname = str;
            return;
        }
        throw tooManyAttributes();
    }

    public void setClasspath(Path path) {
        if (!isReference()) {
            Path path2 = this.classpath;
            if (path2 == null) {
                this.classpath = path;
            } else {
                path2.append(path);
            }
        } else {
            throw tooManyAttributes();
        }
    }

    public Path createClasspath() {
        if (!isReference()) {
            if (this.classpath == null) {
                this.classpath = new Path(getProject());
            }
            setChecked(false);
            return this.classpath.createPath();
        }
        throw noChildrenAllowed();
    }

    public void setClasspathRef(Reference reference) {
        if (!isReference()) {
            createClasspath().setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    public void setFrom(String str) {
        if (!isReference()) {
            this.from = str;
            return;
        }
        throw tooManyAttributes();
    }

    public void setTo(String str) {
        if (!isReference()) {
            this.f14764to = str;
            return;
        }
        throw tooManyAttributes();
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.type == null && this.from == null && this.f14764to == null) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    public FileNameMapper getImplementation() throws BuildException {
        if (isReference()) {
            dieOnCircularReference();
            Reference refid = getRefid();
            Object referencedObject = refid.getReferencedObject(getProject());
            if (referencedObject instanceof FileNameMapper) {
                return (FileNameMapper) referencedObject;
            }
            if (referencedObject instanceof Mapper) {
                return ((Mapper) referencedObject).getImplementation();
            }
            String name = referencedObject == null ? "null" : referencedObject.getClass().getName();
            throw new BuildException(name + " at reference '" + refid.getRefId() + "' is not a valid mapper reference.");
        } else if (this.type == null && this.classname == null && this.container == null) {
            throw new BuildException("nested mapper or one of the attributes type or classname is required");
        } else {
            ContainerMapper containerMapper = this.container;
            if (containerMapper != null) {
                return containerMapper;
            }
            if (this.type == null || this.classname == null) {
                try {
                    FileNameMapper fileNameMapper = (FileNameMapper) getImplementationClass().newInstance();
                    Project project = getProject();
                    if (project != null) {
                        project.setProjectReference(fileNameMapper);
                    }
                    fileNameMapper.setFrom(this.from);
                    fileNameMapper.setTo(this.f14764to);
                    return fileNameMapper;
                } catch (BuildException e) {
                    throw e;
                } catch (Throwable th) {
                    throw new BuildException(th);
                }
            } else {
                throw new BuildException("must not specify both type and classname attribute");
            }
        }
    }

    protected Class<? extends FileNameMapper> getImplementationClass() throws ClassNotFoundException {
        String str = this.classname;
        MapperType mapperType = this.type;
        if (mapperType != null) {
            str = mapperType.getImplementation();
        }
        return Class.forName(str, true, this.classpath == null ? getClass().getClassLoader() : getProject().createClassLoader(this.classpath)).asSubclass(FileNameMapper.class);
    }

    protected Mapper getRef() {
        return (Mapper) getCheckedRef(Mapper.class, getDataTypeName());
    }

    /* loaded from: classes2.dex */
    public static class MapperType extends EnumeratedAttribute {
        private Properties implementations = new Properties();

        public MapperType() {
            this.implementations.put(HTTP.IDENTITY_CODING, "org.apache.tools.ant.util.IdentityMapper");
            this.implementations.put("flatten", "org.apache.tools.ant.util.FlatFileNameMapper");
            this.implementations.put("glob", "org.apache.tools.ant.util.GlobPatternMapper");
            this.implementations.put("merge", "org.apache.tools.ant.util.MergingMapper");
            this.implementations.put(RegularExpression.DATA_TYPE_NAME, "org.apache.tools.ant.util.RegexpPatternMapper");
            this.implementations.put(ServiceManagerNative.PACKAGE, "org.apache.tools.ant.util.PackageNameMapper");
            this.implementations.put("unpackage", "org.apache.tools.ant.util.UnPackageNameMapper");
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{HTTP.IDENTITY_CODING, "flatten", "glob", "merge", RegularExpression.DATA_TYPE_NAME, ServiceManagerNative.PACKAGE, "unpackage"};
        }

        public String getImplementation() {
            return this.implementations.getProperty(getValue());
        }
    }
}
