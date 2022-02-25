package org.apache.tools.ant.util;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import p110z1.Typography;

/* loaded from: classes2.dex */
public final class SplitClassLoader extends AntClassLoader {
    private final String[] splitClasses;

    public SplitClassLoader(ClassLoader classLoader, Path path, Project project, String[] strArr) {
        super(classLoader, project, path, true);
        this.splitClasses = strArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.AntClassLoader, java.lang.ClassLoader
    public synchronized Class loadClass(String str, boolean z) throws ClassNotFoundException {
        Class findLoadedClass = findLoadedClass(str);
        if (findLoadedClass != null) {
            return findLoadedClass;
        }
        if (isSplit(str)) {
            Class<?> findClass = findClass(str);
            if (z) {
                resolveClass(findClass);
            }
            return findClass;
        }
        return super.loadClass(str, z);
    }

    private boolean isSplit(String str) {
        String substring = str.substring(str.lastIndexOf(46) + 1);
        int i = 0;
        while (true) {
            String[] strArr = this.splitClasses;
            if (i < strArr.length) {
                if (substring.equals(strArr[i])) {
                    break;
                }
                if (substring.startsWith(this.splitClasses[i] + Typography.f21050b)) {
                    break;
                }
                i++;
            } else {
                return false;
            }
        }
        return true;
    }
}
