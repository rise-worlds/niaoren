package org.apache.tools.ant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
public class AntTypeDefinition {
    private Class<?> adaptToClass;
    private Class<?> adapterClass;
    private ClassLoader classLoader;
    private String className;
    private Class<?> clazz;
    private String name;
    private boolean restrict = false;

    public void setRestrict(boolean z) {
        this.restrict = z;
    }

    public boolean isRestrict() {
        return this.restrict;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setClass(Class<?> cls) {
        this.clazz = cls;
        if (cls != null) {
            ClassLoader classLoader = this.classLoader;
            if (classLoader == null) {
                classLoader = cls.getClassLoader();
            }
            this.classLoader = classLoader;
            String str = this.className;
            if (str == null) {
                str = cls.getName();
            }
            this.className = str;
        }
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public String getClassName() {
        return this.className;
    }

    public void setAdapterClass(Class<?> cls) {
        this.adapterClass = cls;
    }

    public void setAdaptToClass(Class<?> cls) {
        this.adaptToClass = cls;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Class<?> getExposedClass(Project project) {
        Class<?> typeClass;
        if (this.adaptToClass != null && ((typeClass = getTypeClass(project)) == null || this.adaptToClass.isAssignableFrom(typeClass))) {
            return typeClass;
        }
        Class<?> cls = this.adapterClass;
        return cls == null ? getTypeClass(project) : cls;
    }

    public Class<?> getTypeClass(Project project) {
        try {
            return innerGetTypeClass();
        } catch (ClassNotFoundException unused) {
            project.log("Could not load class (" + this.className + ") for type " + this.name, 4);
            return null;
        } catch (NoClassDefFoundError e) {
            project.log("Could not load a dependent class (" + e.getMessage() + ") for type " + this.name, 4);
            return null;
        }
    }

    public Class<?> innerGetTypeClass() throws ClassNotFoundException {
        Class<?> cls = this.clazz;
        if (cls != null) {
            return cls;
        }
        ClassLoader classLoader = this.classLoader;
        if (classLoader == null) {
            this.clazz = Class.forName(this.className);
        } else {
            this.clazz = classLoader.loadClass(this.className);
        }
        return this.clazz;
    }

    public Object create(Project project) {
        return icreate(project);
    }

    private Object icreate(Project project) {
        Class<?> typeClass = getTypeClass(project);
        if (typeClass == null) {
            return null;
        }
        Object createAndSet = createAndSet(project, typeClass);
        if (createAndSet == null || this.adapterClass == null) {
            return createAndSet;
        }
        Class<?> cls = this.adaptToClass;
        if (cls != null && cls.isAssignableFrom(createAndSet.getClass())) {
            return createAndSet;
        }
        TypeAdapter typeAdapter = (TypeAdapter) createAndSet(project, this.adapterClass);
        if (typeAdapter == null) {
            return null;
        }
        typeAdapter.setProxy(createAndSet);
        return typeAdapter;
    }

    public void checkClass(Project project) {
        if (this.clazz == null) {
            this.clazz = getTypeClass(project);
            if (this.clazz == null) {
                throw new BuildException("Unable to create class for " + getName());
            }
        }
        if (this.adapterClass != null) {
            Class<?> cls = this.adaptToClass;
            if (cls == null || !cls.isAssignableFrom(this.clazz)) {
                TypeAdapter typeAdapter = (TypeAdapter) createAndSet(project, this.adapterClass);
                if (typeAdapter != null) {
                    typeAdapter.checkProxyClass(this.clazz);
                    return;
                }
                throw new BuildException("Unable to create adapter object");
            }
        }
    }

    private Object createAndSet(Project project, Class<?> cls) {
        try {
            return innerCreateAndSet(cls, project);
        } catch (IllegalAccessException unused) {
            throw new BuildException("Could not create type " + this.name + " as the constructor " + cls + " is not accessible");
        } catch (InstantiationException unused2) {
            throw new BuildException("Could not create type " + this.name + " as the class " + cls + " is abstract");
        } catch (NoClassDefFoundError e) {
            throw new BuildException("Type " + this.name + ": A class needed by class " + cls + " cannot be found: " + e.getMessage(), e);
        } catch (NoSuchMethodException unused3) {
            throw new BuildException("Could not create type " + this.name + " as the class " + cls + " has no compatible constructor");
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            throw new BuildException("Could not create type " + this.name + " due to " + targetException, targetException);
        } catch (Throwable th) {
            throw new BuildException("Could not create type " + this.name + " due to " + th, th);
        }
    }

    public <T> T innerCreateAndSet(Class<T> cls, Project project) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<T> constructor;
        boolean z;
        try {
            constructor = cls.getConstructor(new Class[0]);
            z = true;
        } catch (NoSuchMethodException unused) {
            constructor = cls.getConstructor(Project.class);
            z = false;
        }
        T newInstance = constructor.newInstance(z ? new Object[0] : new Object[]{project});
        project.setProjectReference(newInstance);
        return newInstance;
    }

    public boolean sameDefinition(AntTypeDefinition antTypeDefinition, Project project) {
        return antTypeDefinition != null && antTypeDefinition.getClass() == getClass() && antTypeDefinition.getTypeClass(project).equals(getTypeClass(project)) && antTypeDefinition.getExposedClass(project).equals(getExposedClass(project)) && antTypeDefinition.restrict == this.restrict && antTypeDefinition.adapterClass == this.adapterClass && antTypeDefinition.adaptToClass == this.adaptToClass;
    }

    public boolean similarDefinition(AntTypeDefinition antTypeDefinition, Project project) {
        if (antTypeDefinition == null || getClass() != antTypeDefinition.getClass() || !getClassName().equals(antTypeDefinition.getClassName()) || !extractClassname(this.adapterClass).equals(extractClassname(antTypeDefinition.adapterClass)) || !extractClassname(this.adaptToClass).equals(extractClassname(antTypeDefinition.adaptToClass)) || this.restrict != antTypeDefinition.restrict) {
            return false;
        }
        ClassLoader classLoader = antTypeDefinition.getClassLoader();
        ClassLoader classLoader2 = getClassLoader();
        return classLoader == classLoader2 || ((classLoader instanceof AntClassLoader) && (classLoader2 instanceof AntClassLoader) && ((AntClassLoader) classLoader).getClasspath().equals(((AntClassLoader) classLoader2).getClasspath()));
    }

    private String extractClassname(Class<?> cls) {
        return cls == null ? "<null>" : cls.getClass().getName();
    }
}
