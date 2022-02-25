package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.util.ConcatFileInputStream;
import org.apache.tools.ant.util.KeepAliveOutputStream;
import org.apache.tools.ant.util.LazyFileOutputStream;
import org.apache.tools.ant.util.LeadPipeInputStream;
import org.apache.tools.ant.util.LineOrientedOutputStreamRedirector;
import org.apache.tools.ant.util.OutputStreamFunneler;
import org.apache.tools.ant.util.ReaderInputStream;
import org.apache.tools.ant.util.StringUtils;
import org.apache.tools.ant.util.TeeOutputStream;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class Redirector {
    private static final String DEFAULT_ENCODING = System.getProperty("file.encoding");
    private static final int STREAMPUMPER_WAIT_INTERVAL = 1000;
    private boolean alwaysLogErr;
    private boolean alwaysLogOut;
    private boolean appendErr;
    private boolean appendOut;
    private boolean appendProperties;
    private PropertyOutputStream baos;
    private boolean createEmptyFilesErr;
    private boolean createEmptyFilesOut;
    private final Object errMutex;
    private File[] error;
    private PropertyOutputStream errorBaos;
    private String errorEncoding;
    private Vector<FilterChain> errorFilterChains;
    private PrintStream errorPrintStream;
    private String errorProperty;
    private OutputStream errorStream;
    private final Object inMutex;
    private File[] input;
    private String inputEncoding;
    private Vector<FilterChain> inputFilterChains;
    private InputStream inputStream;
    private String inputString;
    private boolean logError;
    private boolean logInputString;
    private final ProjectComponent managingTask;
    private File[] out;
    private final Object outMutex;
    private PrintStream outPrintStream;
    private String outputEncoding;
    private Vector<FilterChain> outputFilterChains;
    private boolean outputIsBinary;
    private String outputProperty;
    private OutputStream outputStream;
    private final ThreadGroup threadGroup;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class PropertyOutputStream extends ByteArrayOutputStream {
        private boolean closed = false;
        private final String property;

        PropertyOutputStream(String str) {
            this.property = str;
        }

        @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Redirector.this.outMutex) {
                if (!this.closed && (!Redirector.this.appendOut || !Redirector.this.appendProperties)) {
                    Redirector.this.setPropertyFromBAOS(this, this.property);
                    this.closed = true;
                }
            }
        }
    }

    public Redirector(Task task) {
        this((ProjectComponent) task);
    }

    public Redirector(ProjectComponent projectComponent) {
        this.logError = false;
        this.baos = null;
        this.errorBaos = null;
        this.appendOut = false;
        this.appendErr = false;
        this.alwaysLogOut = false;
        this.alwaysLogErr = false;
        this.createEmptyFilesOut = true;
        this.createEmptyFilesErr = true;
        this.outputStream = null;
        this.errorStream = null;
        this.inputStream = null;
        this.outPrintStream = null;
        this.errorPrintStream = null;
        String str = DEFAULT_ENCODING;
        this.outputEncoding = str;
        this.errorEncoding = str;
        this.inputEncoding = str;
        this.appendProperties = true;
        this.threadGroup = new ThreadGroup("redirector");
        this.logInputString = true;
        this.inMutex = new Object();
        this.outMutex = new Object();
        this.errMutex = new Object();
        this.outputIsBinary = false;
        this.managingTask = projectComponent;
    }

    public void setInput(File file) {
        setInput(file == null ? null : new File[]{file});
    }

    public void setInput(File[] fileArr) {
        synchronized (this.inMutex) {
            if (fileArr == null) {
                this.input = null;
            } else {
                this.input = (File[]) fileArr.clone();
            }
        }
    }

    public void setInputString(String str) {
        synchronized (this.inMutex) {
            this.inputString = str;
        }
    }

    public void setLogInputString(boolean z) {
        this.logInputString = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInputStream(InputStream inputStream) {
        synchronized (this.inMutex) {
            this.inputStream = inputStream;
        }
    }

    public void setOutput(File file) {
        setOutput(file == null ? null : new File[]{file});
    }

    public void setOutput(File[] fileArr) {
        synchronized (this.outMutex) {
            if (fileArr == null) {
                this.out = null;
            } else {
                this.out = (File[]) fileArr.clone();
            }
        }
    }

    public void setOutputEncoding(String str) {
        if (str != null) {
            synchronized (this.outMutex) {
                this.outputEncoding = str;
            }
            return;
        }
        throw new IllegalArgumentException("outputEncoding must not be null");
    }

    public void setErrorEncoding(String str) {
        if (str != null) {
            synchronized (this.errMutex) {
                this.errorEncoding = str;
            }
            return;
        }
        throw new IllegalArgumentException("errorEncoding must not be null");
    }

    public void setInputEncoding(String str) {
        if (str != null) {
            synchronized (this.inMutex) {
                this.inputEncoding = str;
            }
            return;
        }
        throw new IllegalArgumentException("inputEncoding must not be null");
    }

    public void setLogError(boolean z) {
        synchronized (this.errMutex) {
            this.logError = z;
        }
    }

    public void setAppendProperties(boolean z) {
        synchronized (this.outMutex) {
            this.appendProperties = z;
        }
    }

    public void setError(File file) {
        setError(file == null ? null : new File[]{file});
    }

    public void setError(File[] fileArr) {
        synchronized (this.errMutex) {
            if (fileArr == null) {
                this.error = null;
            } else {
                this.error = (File[]) fileArr.clone();
            }
        }
    }

    public void setOutputProperty(String str) {
        if (str == null || !str.equals(this.outputProperty)) {
            synchronized (this.outMutex) {
                this.outputProperty = str;
                this.baos = null;
            }
        }
    }

    public void setAppend(boolean z) {
        synchronized (this.outMutex) {
            this.appendOut = z;
        }
        synchronized (this.errMutex) {
            this.appendErr = z;
        }
    }

    public void setAlwaysLog(boolean z) {
        synchronized (this.outMutex) {
            this.alwaysLogOut = z;
        }
        synchronized (this.errMutex) {
            this.alwaysLogErr = z;
        }
    }

    public void setCreateEmptyFiles(boolean z) {
        synchronized (this.outMutex) {
            this.createEmptyFilesOut = z;
        }
        synchronized (this.outMutex) {
            this.createEmptyFilesErr = z;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000b, code lost:
        if (r3.equals(r2.errorProperty) == false) goto L_0x000d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setErrorProperty(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.errMutex
            monitor-enter(r0)
            if (r3 == 0) goto L_0x000d
            java.lang.String r1 = r2.errorProperty     // Catch: all -> 0x0014
            boolean r1 = r3.equals(r1)     // Catch: all -> 0x0014
            if (r1 != 0) goto L_0x0012
        L_0x000d:
            r2.errorProperty = r3     // Catch: all -> 0x0014
            r3 = 0
            r2.errorBaos = r3     // Catch: all -> 0x0014
        L_0x0012:
            monitor-exit(r0)     // Catch: all -> 0x0014
            return
        L_0x0014:
            r3 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x0014
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Redirector.setErrorProperty(java.lang.String):void");
    }

    public void setInputFilterChains(Vector<FilterChain> vector) {
        synchronized (this.inMutex) {
            this.inputFilterChains = vector;
        }
    }

    public void setOutputFilterChains(Vector<FilterChain> vector) {
        synchronized (this.outMutex) {
            this.outputFilterChains = vector;
        }
    }

    public void setErrorFilterChains(Vector<FilterChain> vector) {
        synchronized (this.errMutex) {
            this.errorFilterChains = vector;
        }
    }

    public void setBinaryOutput(boolean z) {
        this.outputIsBinary = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPropertyFromBAOS(ByteArrayOutputStream byteArrayOutputStream, String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(Execute.toString(byteArrayOutputStream)));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                if (stringBuffer.length() != 0) {
                    stringBuffer.append(StringUtils.LINE_SEP);
                }
                stringBuffer.append(readLine);
            } else {
                this.managingTask.getProject().setNewProperty(str, stringBuffer.toString());
                return;
            }
        }
    }

    public void createStreams() {
        synchronized (this.outMutex) {
            outStreams();
            if (this.alwaysLogOut || this.outputStream == null) {
                OutputStream logOutputStream = new LogOutputStream(this.managingTask, 2);
                if (this.outputStream != null) {
                    logOutputStream = new TeeOutputStream(logOutputStream, this.outputStream);
                }
                this.outputStream = logOutputStream;
            }
            if ((this.outputFilterChains != null && this.outputFilterChains.size() > 0) || !this.outputEncoding.equalsIgnoreCase(this.inputEncoding)) {
                try {
                    LeadPipeInputStream leadPipeInputStream = new LeadPipeInputStream();
                    leadPipeInputStream.setManagingComponent(this.managingTask);
                    Reader inputStreamReader = new InputStreamReader(leadPipeInputStream, this.inputEncoding);
                    if (this.outputFilterChains != null && this.outputFilterChains.size() > 0) {
                        ChainReaderHelper chainReaderHelper = new ChainReaderHelper();
                        chainReaderHelper.setProject(this.managingTask.getProject());
                        chainReaderHelper.setPrimaryReader(inputStreamReader);
                        chainReaderHelper.setFilterChains(this.outputFilterChains);
                        inputStreamReader = chainReaderHelper.getAssembledReader();
                    }
                    Thread thread = new Thread(this.threadGroup, new StreamPumper(new ReaderInputStream(inputStreamReader, this.outputEncoding), this.outputStream, true), "output pumper");
                    thread.setPriority(10);
                    this.outputStream = new PipedOutputStream(leadPipeInputStream);
                    thread.start();
                } catch (IOException e) {
                    throw new BuildException("error setting up output stream", e);
                }
            }
        }
        synchronized (this.errMutex) {
            errorStreams();
            if (this.alwaysLogErr || this.errorStream == null) {
                OutputStream logOutputStream2 = new LogOutputStream(this.managingTask, 1);
                if (this.errorStream != null) {
                    logOutputStream2 = new TeeOutputStream(logOutputStream2, this.errorStream);
                }
                this.errorStream = logOutputStream2;
            }
            if ((this.errorFilterChains != null && this.errorFilterChains.size() > 0) || !this.errorEncoding.equalsIgnoreCase(this.inputEncoding)) {
                try {
                    LeadPipeInputStream leadPipeInputStream2 = new LeadPipeInputStream();
                    leadPipeInputStream2.setManagingComponent(this.managingTask);
                    Reader inputStreamReader2 = new InputStreamReader(leadPipeInputStream2, this.inputEncoding);
                    if (this.errorFilterChains != null && this.errorFilterChains.size() > 0) {
                        ChainReaderHelper chainReaderHelper2 = new ChainReaderHelper();
                        chainReaderHelper2.setProject(this.managingTask.getProject());
                        chainReaderHelper2.setPrimaryReader(inputStreamReader2);
                        chainReaderHelper2.setFilterChains(this.errorFilterChains);
                        inputStreamReader2 = chainReaderHelper2.getAssembledReader();
                    }
                    Thread thread2 = new Thread(this.threadGroup, new StreamPumper(new ReaderInputStream(inputStreamReader2, this.errorEncoding), this.errorStream, true), "error pumper");
                    thread2.setPriority(10);
                    this.errorStream = new PipedOutputStream(leadPipeInputStream2);
                    thread2.start();
                } catch (IOException e2) {
                    throw new BuildException("error setting up error stream", e2);
                }
            }
        }
        synchronized (this.inMutex) {
            if (this.input != null && this.input.length > 0) {
                ProjectComponent projectComponent = this.managingTask;
                StringBuilder sb = new StringBuilder();
                sb.append("Redirecting input from file");
                sb.append(this.input.length == 1 ? "" : "s");
                projectComponent.log(sb.toString(), 3);
                try {
                    this.inputStream = new ConcatFileInputStream(this.input);
                    ((ConcatFileInputStream) this.inputStream).setManagingComponent(this.managingTask);
                } catch (IOException e3) {
                    throw new BuildException(e3);
                }
            } else if (this.inputString != null) {
                StringBuffer stringBuffer = new StringBuffer("Using input ");
                if (this.logInputString) {
                    stringBuffer.append(Typography.f21049a);
                    stringBuffer.append(this.inputString);
                    stringBuffer.append(Typography.f21049a);
                } else {
                    stringBuffer.append("string");
                }
                this.managingTask.log(stringBuffer.toString(), 3);
                this.inputStream = new ByteArrayInputStream(this.inputString.getBytes());
            }
            if (!(this.inputStream == null || this.inputFilterChains == null || this.inputFilterChains.size() <= 0)) {
                ChainReaderHelper chainReaderHelper3 = new ChainReaderHelper();
                chainReaderHelper3.setProject(this.managingTask.getProject());
                try {
                    chainReaderHelper3.setPrimaryReader(new InputStreamReader(this.inputStream, this.inputEncoding));
                    chainReaderHelper3.setFilterChains(this.inputFilterChains);
                    this.inputStream = new ReaderInputStream(chainReaderHelper3.getAssembledReader(), this.inputEncoding);
                } catch (IOException e4) {
                    throw new BuildException("error setting up input stream", e4);
                }
            }
        }
    }

    private void outStreams() {
        File[] fileArr = this.out;
        if (fileArr != null && fileArr.length > 0) {
            StringBuffer stringBuffer = new StringBuffer("Output ");
            stringBuffer.append(this.appendOut ? "appended" : "redirected");
            stringBuffer.append(" to ");
            this.outputStream = foldFiles(this.out, stringBuffer.toString(), 3, this.appendOut, this.createEmptyFilesOut);
        }
        String str = this.outputProperty;
        if (str != null) {
            if (this.baos == null) {
                this.baos = new PropertyOutputStream(str);
                ProjectComponent projectComponent = this.managingTask;
                projectComponent.log("Output redirected to property: " + this.outputProperty, 3);
            }
            OutputStream keepAliveOutputStream = new KeepAliveOutputStream(this.baos);
            OutputStream outputStream = this.outputStream;
            if (outputStream != null) {
                keepAliveOutputStream = new TeeOutputStream(outputStream, keepAliveOutputStream);
            }
            this.outputStream = keepAliveOutputStream;
            return;
        }
        this.baos = null;
    }

    private void errorStreams() {
        OutputStream outputStream;
        File[] fileArr = this.error;
        if (fileArr != null && fileArr.length > 0) {
            StringBuffer stringBuffer = new StringBuffer("Error ");
            stringBuffer.append(this.appendErr ? "appended" : "redirected");
            stringBuffer.append(" to ");
            this.errorStream = foldFiles(this.error, stringBuffer.toString(), 3, this.appendErr, this.createEmptyFilesErr);
        } else if (!this.logError && (outputStream = this.outputStream) != null && this.errorProperty == null) {
            OutputStreamFunneler outputStreamFunneler = new OutputStreamFunneler(outputStream, 0L);
            try {
                this.outputStream = outputStreamFunneler.getFunnelInstance();
                this.errorStream = outputStreamFunneler.getFunnelInstance();
                if (!this.outputIsBinary) {
                    this.outputStream = new LineOrientedOutputStreamRedirector(this.outputStream);
                    this.errorStream = new LineOrientedOutputStreamRedirector(this.errorStream);
                }
            } catch (IOException e) {
                throw new BuildException("error splitting output/error streams", e);
            }
        }
        String str = this.errorProperty;
        if (str != null) {
            if (this.errorBaos == null) {
                this.errorBaos = new PropertyOutputStream(str);
                ProjectComponent projectComponent = this.managingTask;
                projectComponent.log("Error redirected to property: " + this.errorProperty, 3);
            }
            OutputStream keepAliveOutputStream = new KeepAliveOutputStream(this.errorBaos);
            File[] fileArr2 = this.error;
            if (!(fileArr2 == null || fileArr2.length == 0)) {
                keepAliveOutputStream = new TeeOutputStream(this.errorStream, keepAliveOutputStream);
            }
            this.errorStream = keepAliveOutputStream;
            return;
        }
        this.errorBaos = null;
    }

    public ExecuteStreamHandler createHandler() throws BuildException {
        createStreams();
        return new PumpStreamHandler(getOutputStream(), getErrorStream(), getInputStream(), this.input == null && this.inputString == null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleOutput(String str) {
        synchronized (this.outMutex) {
            if (this.outPrintStream == null) {
                this.outPrintStream = new PrintStream(this.outputStream);
            }
            this.outPrintStream.print(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int handleInput(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.inMutex) {
            if (this.inputStream == null) {
                return this.managingTask.getProject().defaultInput(bArr, i, i2);
            }
            return this.inputStream.read(bArr, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleFlush(String str) {
        synchronized (this.outMutex) {
            if (this.outPrintStream == null) {
                this.outPrintStream = new PrintStream(this.outputStream);
            }
            this.outPrintStream.print(str);
            this.outPrintStream.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleErrorOutput(String str) {
        synchronized (this.errMutex) {
            if (this.errorPrintStream == null) {
                this.errorPrintStream = new PrintStream(this.errorStream);
            }
            this.errorPrintStream.print(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleErrorFlush(String str) {
        synchronized (this.errMutex) {
            if (this.errorPrintStream == null) {
                this.errorPrintStream = new PrintStream(this.errorStream);
            }
            this.errorPrintStream.print(str);
            this.errorPrintStream.flush();
        }
    }

    public OutputStream getOutputStream() {
        OutputStream outputStream;
        synchronized (this.outMutex) {
            outputStream = this.outputStream;
        }
        return outputStream;
    }

    public OutputStream getErrorStream() {
        OutputStream outputStream;
        synchronized (this.errMutex) {
            outputStream = this.errorStream;
        }
        return outputStream;
    }

    public InputStream getInputStream() {
        InputStream inputStream;
        synchronized (this.inMutex) {
            inputStream = this.inputStream;
        }
        return inputStream;
    }

    public void complete() throws IOException {
        System.out.flush();
        System.err.flush();
        synchronized (this.inMutex) {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
        }
        synchronized (this.outMutex) {
            this.outputStream.flush();
            this.outputStream.close();
        }
        synchronized (this.errMutex) {
            this.errorStream.flush();
            this.errorStream.close();
        }
        synchronized (this) {
            while (this.threadGroup.activeCount() > 0) {
                try {
                    ProjectComponent projectComponent = this.managingTask;
                    projectComponent.log("waiting for " + this.threadGroup.activeCount() + " Threads:", 4);
                    Thread[] threadArr = new Thread[this.threadGroup.activeCount()];
                    this.threadGroup.enumerate(threadArr);
                    for (int i = 0; i < threadArr.length && threadArr[i] != null; i++) {
                        try {
                            this.managingTask.log(threadArr[i].toString(), 4);
                        } catch (NullPointerException unused) {
                        }
                    }
                    wait(1000L);
                } catch (InterruptedException unused2) {
                    Thread[] threadArr2 = new Thread[this.threadGroup.activeCount()];
                    this.threadGroup.enumerate(threadArr2);
                    for (int i2 = 0; i2 < threadArr2.length && threadArr2[i2] != null; i2++) {
                        threadArr2[i2].interrupt();
                    }
                }
            }
        }
        setProperties();
        synchronized (this.inMutex) {
            this.inputStream = null;
        }
        synchronized (this.outMutex) {
            this.outputStream = null;
            this.outPrintStream = null;
        }
        synchronized (this.errMutex) {
            this.errorStream = null;
            this.errorPrintStream = null;
        }
    }

    public void setProperties() {
        synchronized (this.outMutex) {
            if (this.baos != null) {
                try {
                    this.baos.close();
                } catch (IOException unused) {
                }
            }
        }
        synchronized (this.errMutex) {
            if (this.errorBaos != null) {
                try {
                    this.errorBaos.close();
                } catch (IOException unused2) {
                }
            }
        }
    }

    private OutputStream foldFiles(File[] fileArr, String str, int i, boolean z, boolean z2) {
        LazyFileOutputStream lazyFileOutputStream = new LazyFileOutputStream(fileArr[0], z, z2);
        ProjectComponent projectComponent = this.managingTask;
        projectComponent.log(str + fileArr[0], i);
        char[] cArr = new char[str.length()];
        Arrays.fill(cArr, ' ');
        String str2 = new String(cArr);
        for (int i2 = 1; i2 < fileArr.length; i2++) {
            this.outputStream = new TeeOutputStream(this.outputStream, new LazyFileOutputStream(fileArr[i2], z, z2));
            ProjectComponent projectComponent2 = this.managingTask;
            projectComponent2.log(str2 + fileArr[i2], i);
        }
        return lazyFileOutputStream;
    }
}
