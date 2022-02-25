package org.apache.tools.ant.types.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public abstract class ContentTransformingResource extends ResourceDecorator {
    private static final int BUFFER_SIZE = 8192;

    protected boolean isAppendSupported() {
        return false;
    }

    protected abstract InputStream wrapStream(InputStream inputStream) throws IOException;

    protected abstract OutputStream wrapStream(OutputStream outputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public ContentTransformingResource() {
    }

    protected ContentTransformingResource(ResourceCollection resourceCollection) {
        super(resourceCollection);
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource
    public long getSize() {
        if (!isExists()) {
            return 0L;
        }
        InputStream inputStream = null;
        try {
            try {
                inputStream = getInputStream();
                byte[] bArr = new byte[8192];
                int i = 0;
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read > 0) {
                        i += read;
                    } else {
                        return i;
                    }
                }
            } catch (IOException e) {
                throw new BuildException("caught exception while reading " + getName(), e);
            }
        } finally {
            FileUtils.close(inputStream);
        }
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = getResource().getInputStream();
        return inputStream != null ? wrapStream(inputStream) : inputStream;
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource
    public OutputStream getOutputStream() throws IOException {
        OutputStream outputStream = getResource().getOutputStream();
        return outputStream != null ? wrapStream(outputStream) : outputStream;
    }

    @Override // org.apache.tools.ant.types.resources.ResourceDecorator, org.apache.tools.ant.types.Resource
    /* renamed from: as */
    public <T> T mo14823as(Class<T> cls) {
        final Appendable appendable;
        if (Appendable.class.isAssignableFrom(cls)) {
            if (!isAppendSupported() || (appendable = (Appendable) getResource().mo14823as(Appendable.class)) == null) {
                return null;
            }
            return cls.cast(new Appendable() { // from class: org.apache.tools.ant.types.resources.ContentTransformingResource.1
                @Override // org.apache.tools.ant.types.resources.Appendable
                public OutputStream getAppendOutputStream() throws IOException {
                    OutputStream appendOutputStream = appendable.getAppendOutputStream();
                    return appendOutputStream != null ? ContentTransformingResource.this.wrapStream(appendOutputStream) : appendOutputStream;
                }
            });
        } else if (FileProvider.class.isAssignableFrom(cls)) {
            return null;
        } else {
            return (T) getResource().mo14823as(cls);
        }
    }
}
