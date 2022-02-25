package org.apache.tools.ant;

/* loaded from: classes2.dex */
public interface TypeAdapter {
    void checkProxyClass(Class<?> cls);

    Project getProject();

    Object getProxy();

    void setProject(Project project);

    void setProxy(Object obj);
}
