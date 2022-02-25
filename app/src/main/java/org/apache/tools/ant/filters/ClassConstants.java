package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public final class ClassConstants extends BaseFilterReader implements ChainableReader {
    private static final String JAVA_CLASS_HELPER = "org.apache.tools.ant.filters.util.JavaClassHelper";
    private String queuedData = null;

    public ClassConstants() {
    }

    public ClassConstants(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        String str = this.queuedData;
        if (str != null && str.length() == 0) {
            this.queuedData = null;
        }
        String str2 = this.queuedData;
        char c = 65535;
        if (str2 != null) {
            c = str2.charAt(0);
            this.queuedData = this.queuedData.substring(1);
            if (this.queuedData.length() == 0) {
                this.queuedData = null;
            }
        } else {
            String readFully = readFully();
            if (!(readFully == null || readFully.length() == 0)) {
                byte[] bytes = readFully.getBytes("ISO-8859-1");
                try {
                    Class<?> cls = Class.forName(JAVA_CLASS_HELPER);
                    if (cls != null) {
                        StringBuffer stringBuffer = (StringBuffer) cls.getMethod("getConstants", byte[].class).invoke(null, bytes);
                        if (stringBuffer.length() > 0) {
                            this.queuedData = stringBuffer.toString();
                            return read();
                        }
                    }
                } catch (InvocationTargetException e) {
                    Throwable targetException = e.getTargetException();
                    if (targetException instanceof NoClassDefFoundError) {
                        throw ((NoClassDefFoundError) targetException);
                    } else if (targetException instanceof RuntimeException) {
                        throw ((RuntimeException) targetException);
                    } else {
                        throw new BuildException(targetException);
                    }
                } catch (Exception e2) {
                    throw new BuildException(e2);
                } catch (NoClassDefFoundError e3) {
                    throw e3;
                } catch (RuntimeException e4) {
                    throw e4;
                }
            }
        }
        return c;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        return new ClassConstants(reader);
    }
}
