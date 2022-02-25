package org.apache.tools.ant.types.resources;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class JavaConstantResource extends AbstractClasspathResource {
    @Override // org.apache.tools.ant.types.resources.AbstractClasspathResource
    protected InputStream openInputStream(ClassLoader classLoader) throws IOException {
        String name = getName();
        if (name != null) {
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                String substring = name.substring(0, lastIndexOf);
                String substring2 = name.substring(lastIndexOf + 1, name.length());
                try {
                    return new ByteArrayInputStream((classLoader != null ? Class.forName(substring, true, classLoader) : Class.forName(substring)).getField(substring2).get(null).toString().getBytes("UTF-8"));
                } catch (ClassNotFoundException unused) {
                    throw new IOException("Class not found:" + substring);
                } catch (IllegalAccessException unused2) {
                    throw new IOException("Illegal access to :" + substring2 + " in " + substring);
                } catch (NoSuchFieldException unused3) {
                    throw new IOException("Field not found:" + substring2 + " in " + substring);
                } catch (NullPointerException unused4) {
                    throw new IOException("Not a static field: " + substring2 + " in " + substring);
                }
            } else {
                throw new IOException("No class name in " + name);
            }
        } else {
            throw new IOException("Attribute 'name' must be set.");
        }
    }
}
