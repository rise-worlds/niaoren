package com.lody.virtual.helper;

/* loaded from: classes.dex */
public class ComposeClassLoader extends ClassLoader {
    private final ClassLoader mAppClassLoader;

    public ComposeClassLoader(ClassLoader classLoader, ClassLoader classLoader2) {
        super(classLoader);
        this.mAppClassLoader = classLoader2;
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> cls;
        try {
            cls = this.mAppClassLoader.loadClass(str);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null) {
            cls = super.loadClass(str, z);
        }
        if (cls != null) {
            return cls;
        }
        throw new ClassNotFoundException();
    }
}
