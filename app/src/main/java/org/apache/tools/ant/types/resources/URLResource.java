package org.apache.tools.ant.types.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class URLResource extends Resource implements URLProvider {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final int NULL_URL = Resource.getMagicNumber("null URL".getBytes());
    private URL baseURL;
    private URLConnection conn;
    private String relPath;
    private URL url;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface ConnectionUser {
        long useConnection(URLConnection uRLConnection);
    }

    public URLResource() {
    }

    public URLResource(URL url) {
        setURL(url);
    }

    public URLResource(URLProvider uRLProvider) {
        setURL(uRLProvider.getURL());
    }

    public URLResource(File file) {
        setFile(file);
    }

    public URLResource(String str) {
        this(newURL(str));
    }

    public synchronized void setURL(URL url) {
        checkAttributesAllowed();
        this.url = url;
    }

    public synchronized void setFile(File file) {
        try {
            setURL(FILE_UTILS.getFileURL(file));
        } catch (MalformedURLException e) {
            throw new BuildException(e);
        }
    }

    public synchronized void setBaseURL(URL url) {
        checkAttributesAllowed();
        if (this.url == null) {
            this.baseURL = url;
        } else {
            throw new BuildException("can't define URL and baseURL attribute");
        }
    }

    public synchronized void setRelativePath(String str) {
        checkAttributesAllowed();
        if (this.url == null) {
            this.relPath = str;
        } else {
            throw new BuildException("can't define URL and relativePath attribute");
        }
    }

    @Override // org.apache.tools.ant.types.resources.URLProvider
    public synchronized URL getURL() {
        if (isReference()) {
            return ((URLResource) getCheckedRef()).getURL();
        }
        if (this.url == null && this.baseURL != null) {
            if (this.relPath != null) {
                try {
                    this.url = new URL(this.baseURL, this.relPath);
                } catch (MalformedURLException e) {
                    throw new BuildException(e);
                }
            } else {
                throw new BuildException("must provide relativePath attribute when using baseURL.");
            }
        }
        return this.url;
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public synchronized void setRefid(Reference reference) {
        if (this.url == null && this.baseURL == null && this.relPath == null) {
            super.setRefid(reference);
        } else {
            throw tooManyAttributes();
        }
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized String getName() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getName();
        }
        String file = getURL().getFile();
        if (!"".equals(file)) {
            file = file.substring(1);
        }
        return file;
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public synchronized String toString() {
        return isReference() ? getCheckedRef().toString() : String.valueOf(getURL());
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized boolean isExists() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).isExists();
        }
        return isExists(false);
    }

    private synchronized boolean isExists(boolean z) {
        Throwable th;
        boolean z2 = false;
        if (getURL() == null) {
            return false;
        }
        try {
            connect(3);
            if (this.conn instanceof HttpURLConnection) {
                if (((HttpURLConnection) this.conn).getResponseCode() < 400) {
                    z2 = true;
                }
                if (z) {
                    close();
                }
                return z2;
            }
            if (this.url.getProtocol().startsWith("ftp")) {
                try {
                    FileUtils.close(this.conn.getInputStream());
                    z = true;
                } catch (IOException unused) {
                    z = true;
                    if (z) {
                        close();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    z = true;
                    if (z) {
                        close();
                    }
                    throw th;
                }
            }
            if (z) {
                close();
            }
            return true;
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized long getLastModified() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getLastModified();
        } else if (!isExists(false)) {
            return 0L;
        } else {
            return withConnection(new ConnectionUser() { // from class: org.apache.tools.ant.types.resources.URLResource.1
                @Override // org.apache.tools.ant.types.resources.URLResource.ConnectionUser
                public long useConnection(URLConnection uRLConnection) {
                    return URLResource.this.conn.getLastModified();
                }
            }, 0L);
        }
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized boolean isDirectory() {
        return isReference() ? ((Resource) getCheckedRef()).isDirectory() : getName().endsWith("/");
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized long getSize() {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getSize();
        } else if (!isExists(false)) {
            return 0L;
        } else {
            return withConnection(new ConnectionUser() { // from class: org.apache.tools.ant.types.resources.URLResource.2
                @Override // org.apache.tools.ant.types.resources.URLResource.ConnectionUser
                public long useConnection(URLConnection uRLConnection) {
                    return URLResource.this.conn.getContentLength();
                }
            }, -1L);
        }
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (isReference()) {
            return getCheckedRef().equals(obj);
        }
        if (obj != null && obj.getClass().equals(getClass())) {
            URLResource uRLResource = (URLResource) obj;
            if (getURL() != null) {
                z = getURL().equals(uRLResource.getURL());
            } else if (uRLResource.getURL() != null) {
                z = false;
            }
            return z;
        }
        return false;
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized int hashCode() {
        if (isReference()) {
            return getCheckedRef().hashCode();
        }
        return MAGIC * (getURL() == null ? NULL_URL : getURL().hashCode());
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized InputStream getInputStream() throws IOException {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getInputStream();
        }
        connect();
        InputStream inputStream = this.conn.getInputStream();
        this.conn = null;
        return inputStream;
    }

    @Override // org.apache.tools.ant.types.Resource
    public synchronized OutputStream getOutputStream() throws IOException {
        if (isReference()) {
            return ((Resource) getCheckedRef()).getOutputStream();
        }
        connect();
        OutputStream outputStream = this.conn.getOutputStream();
        this.conn = null;
        return outputStream;
    }

    protected void connect() throws IOException {
        connect(0);
    }

    protected synchronized void connect(int i) throws IOException {
        URL url = getURL();
        if (url == null) {
            throw new BuildException("URL not set");
        } else if (this.conn == null) {
            try {
                this.conn = url.openConnection();
                this.conn.connect();
            } catch (IOException e) {
                log(e.toString(), i);
                this.conn = null;
                throw e;
            }
        }
    }

    private synchronized void close() {
        FileUtils.close(this.conn);
        this.conn = null;
    }

    private static URL newURL(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new BuildException(e);
        }
    }

    private long withConnection(ConnectionUser connectionUser, long j) {
        try {
            if (this.conn != null) {
                return connectionUser.useConnection(this.conn);
            }
            connect();
            long useConnection = connectionUser.useConnection(this.conn);
            close();
            return useConnection;
        } catch (IOException unused) {
            return j;
        }
    }
}
