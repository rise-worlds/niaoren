package org.apache.tools.ant.taskdefs;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Main;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.Resources;
import org.apache.tools.ant.types.resources.URLProvider;
import org.apache.tools.ant.types.resources.URLResource;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class Get extends Task {
    private static final int BIG_BUFFER_SIZE = 102400;
    private static final String DEFAULT_AGENT_PREFIX = "Apache Ant";
    private static final int DOTS_PER_LINE = 50;
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final String GZIP_CONTENT_ENCODING = "gzip";
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static final int HTTP_MOVED_TEMP = 307;
    private static final int NUMBER_RETRIES = 3;
    private static final int REDIRECT_LIMIT = 25;
    private File destination;
    private final Resources sources = new Resources();
    private boolean verbose = false;
    private boolean quiet = false;
    private boolean useTimestamp = false;
    private boolean ignoreErrors = false;
    private String uname = null;
    private String pword = null;
    private long maxTime = 0;
    private int numberRetries = 3;
    private boolean skipExisting = false;
    private boolean httpUseCaches = true;
    private boolean tryGzipEncoding = false;
    private Mapper mapperElement = null;
    private String userAgent = System.getProperty(MagicNames.HTTP_AGENT_PROPERTY, "Apache Ant/" + Main.getShortAntVersion());

    /* loaded from: classes2.dex */
    public interface DownloadProgress {
        void beginDownload();

        void endDownload();

        void onTick();
    }

    /* loaded from: classes2.dex */
    public static class NullProgress implements DownloadProgress {
        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void beginDownload() {
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void endDownload() {
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void onTick() {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:5|(1:40)(2:7|(6:41|9|(1:11)|12|(1:14)|15)(3:16|(3:44|18|50)(3:39|19|(3:46|21|51)(3:43|22|(3:47|24|52)(2:45|25)))|49))|26|(1:28)|29|37|30|53|49|3) */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00e0, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00e1, code lost:
        log("Error getting " + r2 + " to " + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ff, code lost:
        if (r7.ignoreErrors != false) goto L_0x0009;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x010c, code lost:
        throw new org.apache.tools.ant.BuildException(r1, getLocation());
     */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Get.execute():void");
    }

    @Deprecated
    public boolean doGet(int i, DownloadProgress downloadProgress) throws IOException {
        checkAttributes();
        Iterator<Resource> it = this.sources.iterator();
        if (it.hasNext()) {
            return doGet(((URLProvider) it.next().mo14823as(URLProvider.class)).getURL(), this.destination, i, downloadProgress);
        }
        return false;
    }

    public boolean doGet(URL url, File file, int i, DownloadProgress downloadProgress) throws IOException {
        boolean z;
        if (!file.exists() || !this.skipExisting) {
            NullProgress nullProgress = downloadProgress == null ? new NullProgress() : downloadProgress;
            log("Getting: " + url, i);
            log("To: " + file.getAbsolutePath(), i);
            long j = 0;
            if (!this.useTimestamp || !file.exists()) {
                z = false;
            } else {
                long lastModified = file.lastModified();
                if (this.verbose) {
                    Date date = new Date(lastModified);
                    log("local file date : " + date.toString(), i);
                }
                j = lastModified;
                z = true;
            }
            GetThread getThread = new GetThread(url, file, z, j, nullProgress, i, this.userAgent);
            getThread.setDaemon(true);
            getProject().registerThreadTask(getThread, this);
            getThread.start();
            try {
                getThread.join(this.maxTime * 1000);
            } catch (InterruptedException unused) {
                log("interrupted waiting for GET to finish", 3);
            }
            if (!getThread.isAlive()) {
                return getThread.wasSuccessful();
            }
            String str = "The GET operation took longer than " + this.maxTime + " seconds, stopping it.";
            if (this.ignoreErrors) {
                log(str);
            }
            getThread.closeStreams();
            if (this.ignoreErrors) {
                return false;
            }
            throw new BuildException(str);
        }
        log("Destination already exists (skipping): " + file.getAbsolutePath(), i);
        return true;
    }

    @Override // org.apache.tools.ant.Task, org.apache.tools.ant.ProjectComponent
    public void log(String str, int i) {
        if (!this.quiet || i >= 0) {
            super.log(str, i);
        }
    }

    private void checkAttributes() {
        String str = this.userAgent;
        if (str == null || str.trim().length() == 0) {
            throw new BuildException("userAgent may not be null or empty");
        } else if (this.sources.size() != 0) {
            Iterator<Resource> it = this.sources.iterator();
            while (it.hasNext()) {
                if (((URLProvider) it.next().mo14823as(URLProvider.class)) == null) {
                    throw new BuildException("Only URLProvider resources are supported", getLocation());
                }
            }
            File file = this.destination;
            if (file == null) {
                throw new BuildException("dest attribute is required", getLocation());
            } else if (file.exists() && this.sources.size() > 1 && !this.destination.isDirectory()) {
                throw new BuildException("The specified destination is not a directory", getLocation());
            } else if (this.destination.exists() && !this.destination.canWrite()) {
                throw new BuildException("Can't write to " + this.destination.getAbsolutePath(), getLocation());
            } else if (this.sources.size() > 1 && !this.destination.exists()) {
                this.destination.mkdirs();
            }
        } else {
            throw new BuildException("at least one source is required", getLocation());
        }
    }

    public void setSrc(URL url) {
        add(new URLResource(url));
    }

    public void add(ResourceCollection resourceCollection) {
        this.sources.add(resourceCollection);
    }

    public void setDest(File file) {
        this.destination = file;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void setQuiet(boolean z) {
        this.quiet = z;
    }

    public void setIgnoreErrors(boolean z) {
        this.ignoreErrors = z;
    }

    public void setUseTimestamp(boolean z) {
        this.useTimestamp = z;
    }

    public void setUsername(String str) {
        this.uname = str;
    }

    public void setPassword(String str) {
        this.pword = str;
    }

    public void setMaxTime(long j) {
        this.maxTime = j;
    }

    public void setRetries(int i) {
        this.numberRetries = i;
    }

    public void setSkipExisting(boolean z) {
        this.skipExisting = z;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    public void setHttpUseCaches(boolean z) {
        this.httpUseCaches = z;
    }

    public void setTryGzipEncoding(boolean z) {
        this.tryGzipEncoding = z;
    }

    public Mapper createMapper() throws BuildException {
        if (this.mapperElement == null) {
            this.mapperElement = new Mapper(getProject());
            return this.mapperElement;
        }
        throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS, getLocation());
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class Base64Converter extends org.apache.tools.ant.util.Base64Converter {
        protected Base64Converter() {
        }
    }

    /* loaded from: classes2.dex */
    public static class VerboseProgress implements DownloadProgress {
        private int dots = 0;
        PrintStream out;

        public VerboseProgress(PrintStream printStream) {
            this.out = printStream;
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void beginDownload() {
            this.dots = 0;
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void onTick() {
            this.out.print(Consts.f23430h);
            int i = this.dots;
            this.dots = i + 1;
            if (i > 50) {
                this.out.flush();
                this.dots = 0;
            }
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void endDownload() {
            this.out.println();
            this.out.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class GetThread extends Thread {
        private URLConnection connection;
        private final File dest;
        private final boolean hasTimestamp;
        private final int logLevel;
        private final DownloadProgress progress;
        private final URL source;
        private final long timestamp;
        private String userAgent;
        private boolean success = false;
        private IOException ioexception = null;
        private BuildException exception = null;

        /* renamed from: is */
        private InputStream f14750is = null;

        /* renamed from: os */
        private OutputStream f14751os = null;
        private int redirections = 0;

        private boolean isMoved(int i) {
            return i == 301 || i == 302 || i == 303 || i == 307;
        }

        GetThread(URL url, File file, boolean z, long j, DownloadProgress downloadProgress, int i, String str) {
            this.userAgent = null;
            this.source = url;
            this.dest = file;
            this.hasTimestamp = z;
            this.timestamp = j;
            this.progress = downloadProgress;
            this.logLevel = i;
            this.userAgent = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                this.success = get();
            } catch (IOException e) {
                this.ioexception = e;
            } catch (BuildException e2) {
                this.exception = e2;
            }
        }

        private boolean get() throws IOException, BuildException {
            this.connection = openConnection(this.source);
            if (this.connection == null) {
                return false;
            }
            boolean downloadFile = downloadFile();
            if (downloadFile && Get.this.useTimestamp) {
                updateTimeStamp();
            }
            return downloadFile;
        }

        private boolean redirectionAllowed(URL url, URL url2) {
            if (url.getProtocol().equals(url2.getProtocol()) || ("http".equals(url.getProtocol()) && "https".equals(url2.getProtocol()))) {
                this.redirections++;
                if (this.redirections <= 25) {
                    return true;
                }
                if (Get.this.ignoreErrors) {
                    Get.this.log("More than 25 times redirected, giving up", this.logLevel);
                    return false;
                }
                throw new BuildException("More than 25 times redirected, giving up");
            }
            String str = "Redirection detected from " + url.getProtocol() + " to " + url2.getProtocol() + ". Protocol switch unsafe, not allowed.";
            if (Get.this.ignoreErrors) {
                Get.this.log(str, this.logLevel);
                return false;
            }
            throw new BuildException(str);
        }

        private URLConnection openConnection(URL url) throws IOException {
            URLConnection openConnection = url.openConnection();
            if (this.hasTimestamp) {
                openConnection.setIfModifiedSince(this.timestamp);
            }
            openConnection.addRequestProperty("User-Agent", this.userAgent);
            if (!(Get.this.uname == null && Get.this.pword == null)) {
                String encode = new Base64Converter().encode((Get.this.uname + ":" + Get.this.pword).getBytes());
                StringBuilder sb = new StringBuilder();
                sb.append("Basic ");
                sb.append(encode);
                openConnection.setRequestProperty("Authorization", sb.toString());
            }
            if (Get.this.tryGzipEncoding) {
                openConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, "gzip");
            }
            boolean z = openConnection instanceof HttpURLConnection;
            if (z) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setUseCaches(Get.this.httpUseCaches);
            }
            try {
                openConnection.connect();
                if (z) {
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                    int responseCode = httpURLConnection2.getResponseCode();
                    if (isMoved(responseCode)) {
                        String headerField = httpURLConnection2.getHeaderField(HttpRequest.HEADER_LOCATION);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(url);
                        sb2.append(responseCode == 301 ? " permanently" : "");
                        sb2.append(" moved to ");
                        sb2.append(headerField);
                        Get.this.log(sb2.toString(), this.logLevel);
                        URL url2 = new URL(url, headerField);
                        if (!redirectionAllowed(url, url2)) {
                            return null;
                        }
                        return openConnection(url2);
                    }
                    long lastModified = httpURLConnection2.getLastModified();
                    if (responseCode == 304 || (lastModified != 0 && this.hasTimestamp && this.timestamp >= lastModified)) {
                        Get.this.log("Not modified - so not downloaded", this.logLevel);
                        return null;
                    } else if (responseCode == 401) {
                        if (Get.this.ignoreErrors) {
                            Get.this.log("HTTP Authorization failure", this.logLevel);
                            return null;
                        }
                        throw new BuildException("HTTP Authorization failure");
                    }
                }
                return openConnection;
            } catch (NullPointerException e) {
                throw new BuildException("Failed to parse " + this.source.toString(), e);
            }
        }

        private boolean downloadFile() throws FileNotFoundException, IOException {
            int read;
            for (int i = 0; i < Get.this.numberRetries; i++) {
                try {
                    this.f14750is = this.connection.getInputStream();
                    break;
                } catch (IOException e) {
                    Get.this.log("Error opening connection " + e, this.logLevel);
                }
            }
            if (this.f14750is == null) {
                Get.this.log("Can't get " + this.source + " to " + this.dest, this.logLevel);
                if (Get.this.ignoreErrors) {
                    return false;
                }
                throw new BuildException("Can't get " + this.source + " to " + this.dest, Get.this.getLocation());
            }
            if (Get.this.tryGzipEncoding && "gzip".equals(this.connection.getContentEncoding())) {
                this.f14750is = new GZIPInputStream(this.f14750is);
            }
            this.f14751os = new FileOutputStream(this.dest);
            this.progress.beginDownload();
            try {
                byte[] bArr = new byte[102400];
                while (!isInterrupted() && (read = this.f14750is.read(bArr)) >= 0) {
                    this.f14751os.write(bArr, 0, read);
                    this.progress.onTick();
                }
                boolean z = !isInterrupted();
                FileUtils.close(this.f14751os);
                FileUtils.close(this.f14750is);
                if (!z) {
                    this.dest.delete();
                }
                this.progress.endDownload();
                return true;
            } catch (Throwable th) {
                FileUtils.close(this.f14751os);
                FileUtils.close(this.f14750is);
                this.dest.delete();
                throw th;
            }
        }

        private void updateTimeStamp() {
            long lastModified = this.connection.getLastModified();
            if (Get.this.verbose) {
                Date date = new Date(lastModified);
                Get get = Get.this;
                StringBuilder sb = new StringBuilder();
                sb.append("last modified = ");
                sb.append(date.toString());
                sb.append(lastModified == 0 ? " - using current time instead" : "");
                get.log(sb.toString(), this.logLevel);
            }
            if (lastModified != 0) {
                Get.FILE_UTILS.setFileLastModified(this.dest, lastModified);
            }
        }

        boolean wasSuccessful() throws IOException, BuildException {
            IOException iOException = this.ioexception;
            if (iOException == null) {
                BuildException buildException = this.exception;
                if (buildException == null) {
                    return this.success;
                }
                throw buildException;
            }
            throw iOException;
        }

        void closeStreams() {
            interrupt();
            FileUtils.close(this.f14751os);
            FileUtils.close(this.f14750is);
            if (!this.success && this.dest.exists()) {
                this.dest.delete();
            }
        }
    }
}
