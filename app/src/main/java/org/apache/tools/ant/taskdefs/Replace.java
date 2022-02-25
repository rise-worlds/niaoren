package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class Replace extends MatchingTask {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private int fileCount;
    private int replaceCount;
    private Union resources;
    private File sourceFile = null;
    private NestedString token = null;
    private NestedString value = new NestedString();
    private Resource propertyResource = null;
    private Resource replaceFilterResource = null;
    private Properties properties = null;
    private ArrayList replacefilters = new ArrayList();
    private File dir = null;
    private boolean summary = false;
    private String encoding = null;
    private boolean preserveLastModified = false;
    private boolean failOnNoReplacements = false;

    static /* synthetic */ int access$304(Replace replace) {
        int i = replace.replaceCount + 1;
        replace.replaceCount = i;
        return i;
    }

    /* loaded from: classes2.dex */
    public class NestedString {
        private boolean expandProperties = false;
        private StringBuffer buf = new StringBuffer();

        public NestedString() {
        }

        public void setExpandProperties(boolean z) {
            this.expandProperties = z;
        }

        public void addText(String str) {
            this.buf.append(str);
        }

        public String getText() {
            String stringBuffer = this.buf.toString();
            return this.expandProperties ? Replace.this.getProject().replaceProperties(stringBuffer) : stringBuffer;
        }
    }

    /* loaded from: classes2.dex */
    public class Replacefilter {
        private StringBuffer inputBuffer;
        private StringBuffer outputBuffer = new StringBuffer();
        private String property;
        private String replaceValue;
        private NestedString token;
        private NestedString value;

        public Replacefilter() {
        }

        public void validate() throws BuildException {
            NestedString nestedString = this.token;
            if (nestedString == null) {
                throw new BuildException("token is a mandatory for replacefilter.");
            } else if ("".equals(nestedString.getText())) {
                throw new BuildException("The token must not be an empty string.");
            } else if (this.value == null || this.property == null) {
                if (this.property != null) {
                    if (Replace.this.propertyResource == null) {
                        throw new BuildException("The replacefilter's property attribute can only be used with the replacetask's propertyFile/Resource attribute.");
                    } else if (Replace.this.properties == null || Replace.this.properties.getProperty(this.property) == null) {
                        throw new BuildException("property \"" + this.property + "\" was not found in " + Replace.this.propertyResource.getName());
                    }
                }
                this.replaceValue = getReplaceValue();
            } else {
                throw new BuildException("Either value or property can be specified, but a replacefilter element cannot have both.");
            }
        }

        public String getReplaceValue() {
            if (this.property != null) {
                return Replace.this.properties.getProperty(this.property);
            }
            NestedString nestedString = this.value;
            if (nestedString != null) {
                return nestedString.getText();
            }
            return Replace.this.value != null ? Replace.this.value.getText() : "";
        }

        public void setToken(String str) {
            createReplaceToken().addText(str);
        }

        public String getToken() {
            return this.token.getText();
        }

        public void setValue(String str) {
            createReplaceValue().addText(str);
        }

        public String getValue() {
            return this.value.getText();
        }

        public void setProperty(String str) {
            this.property = str;
        }

        public String getProperty() {
            return this.property;
        }

        public NestedString createReplaceToken() {
            if (this.token == null) {
                this.token = new NestedString();
            }
            return this.token;
        }

        public NestedString createReplaceValue() {
            if (this.value == null) {
                this.value = new NestedString();
            }
            return this.value;
        }

        StringBuffer getOutputBuffer() {
            return this.outputBuffer;
        }

        void setInputBuffer(StringBuffer stringBuffer) {
            this.inputBuffer = stringBuffer;
        }

        boolean process() {
            String token = getToken();
            if (this.inputBuffer.length() <= token.length()) {
                return false;
            }
            int max = Math.max(this.inputBuffer.length() - token.length(), replace());
            this.outputBuffer.append(this.inputBuffer.substring(0, max));
            this.inputBuffer.delete(0, max);
            return true;
        }

        void flush() {
            replace();
            this.outputBuffer.append(this.inputBuffer);
            StringBuffer stringBuffer = this.inputBuffer;
            stringBuffer.delete(0, stringBuffer.length());
        }

        private int replace() {
            String token = getToken();
            int indexOf = this.inputBuffer.indexOf(token);
            int length = token.length();
            int length2 = this.replaceValue.length();
            int i = -1;
            while (indexOf >= 0) {
                this.inputBuffer.replace(indexOf, indexOf + length, this.replaceValue);
                i = indexOf + length2;
                indexOf = this.inputBuffer.indexOf(token, i);
                Replace.access$304(Replace.this);
            }
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class FileInput {
        private static final int BUFF_SIZE = 4096;

        /* renamed from: is */
        private final InputStream f14756is;
        private Reader reader;
        private StringBuffer outputBuffer = new StringBuffer();
        private char[] buffer = new char[4096];

        FileInput(File file) throws IOException {
            this.f14756is = new FileInputStream(file);
            try {
                this.reader = new BufferedReader(Replace.this.encoding != null ? new InputStreamReader(this.f14756is, Replace.this.encoding) : new InputStreamReader(this.f14756is));
            } finally {
                if (this.reader == null) {
                    this.f14756is.close();
                }
            }
        }

        StringBuffer getOutputBuffer() {
            return this.outputBuffer;
        }

        boolean readChunk() throws IOException {
            int read = this.reader.read(this.buffer);
            if (read < 0) {
                return false;
            }
            this.outputBuffer.append(new String(this.buffer, 0, read));
            return true;
        }

        public void close() throws IOException {
            this.f14756is.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class FileOutput {
        private StringBuffer inputBuffer;

        /* renamed from: os */
        private final OutputStream f14757os;
        private Writer writer;

        FileOutput(File file) throws IOException {
            this.f14757os = new FileOutputStream(file);
            try {
                this.writer = new BufferedWriter(Replace.this.encoding != null ? new OutputStreamWriter(this.f14757os, Replace.this.encoding) : new OutputStreamWriter(this.f14757os));
            } finally {
                if (this.writer == null) {
                    this.f14757os.close();
                }
            }
        }

        void setInputBuffer(StringBuffer stringBuffer) {
            this.inputBuffer = stringBuffer;
        }

        boolean process() throws IOException {
            this.writer.write(this.inputBuffer.toString());
            StringBuffer stringBuffer = this.inputBuffer;
            stringBuffer.delete(0, stringBuffer.length());
            return false;
        }

        void flush() throws IOException {
            process();
            this.writer.flush();
        }

        public void close() throws IOException {
            this.f14757os.close();
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        ArrayList arrayList = (ArrayList) this.replacefilters.clone();
        Properties properties = this.properties;
        Properties properties2 = properties == null ? null : (Properties) properties.clone();
        if (this.token != null) {
            StringBuffer stringBuffer = new StringBuffer(this.value.getText());
            stringReplace(stringBuffer, "\r\n", "\n");
            stringReplace(stringBuffer, "\n", StringUtils.LINE_SEP);
            StringBuffer stringBuffer2 = new StringBuffer(this.token.getText());
            stringReplace(stringBuffer2, "\r\n", "\n");
            stringReplace(stringBuffer2, "\n", StringUtils.LINE_SEP);
            Replacefilter createPrimaryfilter = createPrimaryfilter();
            createPrimaryfilter.setToken(stringBuffer2.toString());
            createPrimaryfilter.setValue(stringBuffer.toString());
        }
        try {
            if (this.replaceFilterResource != null) {
                Properties properties3 = getProperties(this.replaceFilterResource);
                for (Object obj : properties3.keySet()) {
                    String obj2 = obj.toString();
                    Replacefilter createReplacefilter = createReplacefilter();
                    createReplacefilter.setToken(obj2);
                    createReplacefilter.setValue(properties3.getProperty(obj2));
                }
            }
            validateAttributes();
            if (this.propertyResource != null) {
                this.properties = getProperties(this.propertyResource);
            }
            validateReplacefilters();
            this.fileCount = 0;
            this.replaceCount = 0;
            if (this.sourceFile != null) {
                processFile(this.sourceFile);
            }
            if (this.dir != null) {
                for (String str : super.getDirectoryScanner(this.dir).getIncludedFiles()) {
                    processFile(new File(this.dir, str));
                }
            }
            if (this.resources != null) {
                Iterator<Resource> it = this.resources.iterator();
                while (it.hasNext()) {
                    processFile(((FileProvider) it.next().mo14823as(FileProvider.class)).getFile());
                }
            }
            if (this.summary) {
                log("Replaced " + this.replaceCount + " occurrences in " + this.fileCount + " files.", 2);
            }
            if (this.failOnNoReplacements && this.replaceCount == 0) {
                throw new BuildException("didn't replace anything");
            }
        } finally {
            this.replacefilters = arrayList;
            this.properties = properties2;
        }
    }

    public void validateAttributes() throws BuildException {
        if (this.sourceFile == null && this.dir == null && this.resources == null) {
            throw new BuildException("Either the file or the dir attribute or nested resources must be specified", getLocation());
        }
        Resource resource = this.propertyResource;
        if (resource != null && !resource.isExists()) {
            throw new BuildException("Property file " + this.propertyResource.getName() + DirectoryScanner.DOES_NOT_EXIST_POSTFIX, getLocation());
        } else if (this.token == null && this.replacefilters.size() == 0) {
            throw new BuildException("Either token or a nested replacefilter must be specified", getLocation());
        } else {
            NestedString nestedString = this.token;
            if (nestedString != null && "".equals(nestedString.getText())) {
                throw new BuildException("The token attribute must not be an empty string.", getLocation());
            }
        }
    }

    public void validateReplacefilters() throws BuildException {
        int size = this.replacefilters.size();
        for (int i = 0; i < size; i++) {
            ((Replacefilter) this.replacefilters.get(i)).validate();
        }
    }

    public Properties getProperties(File file) throws BuildException {
        return getProperties(new FileResource(getProject(), file));
    }

    public Properties getProperties(Resource resource) throws BuildException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            try {
                inputStream = resource.getInputStream();
                properties.load(inputStream);
                return properties;
            } catch (IOException unused) {
                throw new BuildException("Property resource (" + resource.getName() + ") cannot be loaded.");
            }
        } finally {
            FileUtils.close(inputStream);
        }
    }

    private void processFile(File file) throws BuildException {
        if (file.exists()) {
            int i = this.replaceCount;
            logFilterChain(file.getPath());
            try {
                File createTempFile = FILE_UTILS.createTempFile("rep", ".tmp", file.getParentFile(), false, true);
                FileInput fileInput = new FileInput(file);
                FileOutput fileOutput = new FileOutput(createTempFile);
                fileOutput.setInputBuffer(buildFilterChain(fileInput.getOutputBuffer()));
                while (fileInput.readChunk()) {
                    if (processFilterChain()) {
                        fileOutput.process();
                    }
                }
                flushFilterChain();
                fileOutput.flush();
                fileOutput.close();
                fileInput.close();
                if (this.replaceCount != i) {
                    this.fileCount++;
                    long lastModified = file.lastModified();
                    FILE_UTILS.rename(createTempFile, file);
                    if (this.preserveLastModified) {
                        FILE_UTILS.setFileLastModified(file, lastModified);
                    }
                }
                if (createTempFile.isFile() && !createTempFile.delete()) {
                    createTempFile.deleteOnExit();
                }
            } catch (IOException e) {
                throw new BuildException("IOException in " + file + " - " + e.getClass().getName() + ":" + e.getMessage(), e, getLocation());
            }
        } else {
            throw new BuildException("Replace: source file " + file.getPath() + " doesn't exist", getLocation());
        }
    }

    private void flushFilterChain() {
        int size = this.replacefilters.size();
        for (int i = 0; i < size; i++) {
            ((Replacefilter) this.replacefilters.get(i)).flush();
        }
    }

    private boolean processFilterChain() {
        int size = this.replacefilters.size();
        for (int i = 0; i < size; i++) {
            if (!((Replacefilter) this.replacefilters.get(i)).process()) {
                return false;
            }
        }
        return true;
    }

    private StringBuffer buildFilterChain(StringBuffer stringBuffer) {
        int size = this.replacefilters.size();
        for (int i = 0; i < size; i++) {
            Replacefilter replacefilter = (Replacefilter) this.replacefilters.get(i);
            replacefilter.setInputBuffer(stringBuffer);
            stringBuffer = replacefilter.getOutputBuffer();
        }
        return stringBuffer;
    }

    private void logFilterChain(String str) {
        int size = this.replacefilters.size();
        for (int i = 0; i < size; i++) {
            Replacefilter replacefilter = (Replacefilter) this.replacefilters.get(i);
            log("Replacing in " + str + ": " + replacefilter.getToken() + " --> " + replacefilter.getReplaceValue(), 3);
        }
    }

    public void setFile(File file) {
        this.sourceFile = file;
    }

    public void setSummary(boolean z) {
        this.summary = z;
    }

    public void setReplaceFilterFile(File file) {
        setReplaceFilterResource(new FileResource(getProject(), file));
    }

    public void setReplaceFilterResource(Resource resource) {
        this.replaceFilterResource = resource;
    }

    public void setDir(File file) {
        this.dir = file;
    }

    public void setToken(String str) {
        createReplaceToken().addText(str);
    }

    public void setValue(String str) {
        createReplaceValue().addText(str);
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public NestedString createReplaceToken() {
        if (this.token == null) {
            this.token = new NestedString();
        }
        return this.token;
    }

    public NestedString createReplaceValue() {
        return this.value;
    }

    public void setPropertyFile(File file) {
        setPropertyResource(new FileResource(file));
    }

    public void setPropertyResource(Resource resource) {
        this.propertyResource = resource;
    }

    public Replacefilter createReplacefilter() {
        Replacefilter replacefilter = new Replacefilter();
        this.replacefilters.add(replacefilter);
        return replacefilter;
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        if (resourceCollection.isFilesystemOnly()) {
            if (this.resources == null) {
                this.resources = new Union();
            }
            this.resources.add(resourceCollection);
            return;
        }
        throw new BuildException("only filesystem resources are supported");
    }

    public void setPreserveLastModified(boolean z) {
        this.preserveLastModified = z;
    }

    public void setFailOnNoReplacements(boolean z) {
        this.failOnNoReplacements = z;
    }

    private Replacefilter createPrimaryfilter() {
        Replacefilter replacefilter = new Replacefilter();
        this.replacefilters.add(0, replacefilter);
        return replacefilter;
    }

    private void stringReplace(StringBuffer stringBuffer, String str, String str2) {
        int indexOf = stringBuffer.indexOf(str);
        int length = str.length();
        int length2 = str2.length();
        while (indexOf >= 0) {
            stringBuffer.replace(indexOf, indexOf + length, str2);
            indexOf = stringBuffer.indexOf(str, indexOf + length2);
        }
    }
}
