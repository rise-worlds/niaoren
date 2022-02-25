package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.filters.ChainableReader;
import org.apache.tools.ant.filters.FixCrLfFilter;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.FilterSetCollection;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class FixCRLF extends MatchingTask implements ChainableReader {
    public static final String ERROR_FILE_AND_SRCDIR = "<fixcrlf> error: srcdir and file are mutually exclusive";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final String FIXCRLF_ERROR = "<fixcrlf> error: ";
    private File file;
    private File srcDir;
    private boolean preserveLastModified = false;
    private File destDir = null;
    private FixCrLfFilter filter = new FixCrLfFilter();
    private Vector<FilterChain> fcv = null;
    private String encoding = null;
    private String outputEncoding = null;

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader reader) {
        return this.filter.chain(reader);
    }

    public void setSrcdir(File file) {
        this.srcDir = file;
    }

    public void setDestdir(File file) {
        this.destDir = file;
    }

    public void setJavafiles(boolean z) {
        this.filter.setJavafiles(z);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setEol(CrLf crLf) {
        this.filter.setEol(FixCrLfFilter.CrLf.newInstance(crLf.getValue()));
    }

    public void setCr(AddAsisRemove addAsisRemove) {
        log("DEPRECATED: The cr attribute has been deprecated,", 1);
        log("Please use the eol attribute instead", 1);
        String value = addAsisRemove.getValue();
        CrLf crLf = new CrLf();
        if (value.equals("remove")) {
            crLf.setValue("lf");
        } else if (value.equals("asis")) {
            crLf.setValue("asis");
        } else {
            crLf.setValue("crlf");
        }
        setEol(crLf);
    }

    public void setTab(AddAsisRemove addAsisRemove) {
        this.filter.setTab(FixCrLfFilter.AddAsisRemove.newInstance(addAsisRemove.getValue()));
    }

    public void setTablength(int i) throws BuildException {
        try {
            this.filter.setTablength(i);
        } catch (IOException e) {
            throw new BuildException(e.getMessage(), e);
        }
    }

    public void setEof(AddAsisRemove addAsisRemove) {
        this.filter.setEof(FixCrLfFilter.AddAsisRemove.newInstance(addAsisRemove.getValue()));
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setOutputEncoding(String str) {
        this.outputEncoding = str;
    }

    public void setFixlast(boolean z) {
        this.filter.setFixlast(z);
    }

    public void setPreserveLastModified(boolean z) {
        this.preserveLastModified = z;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        validate();
        String str = this.encoding;
        if (str == null) {
            str = "default";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("options: eol=");
        sb.append(this.filter.getEol().getValue());
        sb.append(" tab=");
        sb.append(this.filter.getTab().getValue());
        sb.append(" eof=");
        sb.append(this.filter.getEof().getValue());
        sb.append(" tablength=");
        sb.append(this.filter.getTablength());
        sb.append(" encoding=");
        sb.append(str);
        sb.append(" outputencoding=");
        String str2 = this.outputEncoding;
        if (str2 != null) {
            str = str2;
        }
        sb.append(str);
        log(sb.toString(), 3);
        for (String str3 : super.getDirectoryScanner(this.srcDir).getIncludedFiles()) {
            processFile(str3);
        }
    }

    private void validate() throws BuildException {
        if (this.file != null) {
            if (this.srcDir == null) {
                this.fileset.setFile(this.file);
                this.srcDir = this.file.getParentFile();
            } else {
                throw new BuildException(ERROR_FILE_AND_SRCDIR);
            }
        }
        File file = this.srcDir;
        if (file == null) {
            throw new BuildException("<fixcrlf> error: srcdir attribute must be set!");
        } else if (!file.exists()) {
            throw new BuildException("<fixcrlf> error: srcdir does not exist: '" + this.srcDir + "'");
        } else if (this.srcDir.isDirectory()) {
            File file2 = this.destDir;
            if (file2 == null) {
                return;
            }
            if (!file2.exists()) {
                throw new BuildException("<fixcrlf> error: destdir does not exist: '" + this.destDir + "'");
            } else if (!this.destDir.isDirectory()) {
                throw new BuildException("<fixcrlf> error: destdir is not a directory: '" + this.destDir + "'");
            }
        } else {
            throw new BuildException("<fixcrlf> error: srcdir is not a directory: '" + this.srcDir + "'");
        }
    }

    private void processFile(String str) throws BuildException {
        File file;
        Throwable th;
        IOException e;
        boolean z;
        File file2 = new File(this.srcDir, str);
        long lastModified = file2.lastModified();
        File file3 = this.destDir;
        if (file3 == null) {
            file3 = this.srcDir;
        }
        if (this.fcv == null) {
            FilterChain filterChain = new FilterChain();
            filterChain.add(this.filter);
            this.fcv = new Vector<>(1);
            this.fcv.add(filterChain);
        }
        File createTempFile = FILE_UTILS.createTempFile("fixcrlf", "", null, true, true);
        try {
            z = true;
        } catch (IOException e2) {
            e = e2;
            file = createTempFile;
        } catch (Throwable th2) {
            th = th2;
            file = createTempFile;
        }
        try {
            FILE_UTILS.copyFile(file2, createTempFile, (FilterSetCollection) null, (Vector) this.fcv, true, false, this.encoding, this.outputEncoding == null ? this.encoding : this.outputEncoding, getProject());
            File file4 = new File(file3, str);
            if (file4.exists()) {
                log("destFile " + file4 + " exists", 4);
                file = createTempFile;
                try {
                    try {
                        boolean z2 = !FILE_UTILS.contentEquals(file4, file);
                        StringBuilder sb = new StringBuilder();
                        sb.append(file4);
                        sb.append(z2 ? " is being written" : " is not written, as the contents are identical");
                        log(sb.toString(), 4);
                        z = z2;
                    } catch (IOException e3) {
                        e = e3;
                        throw new BuildException("error running fixcrlf on file " + file2, e);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (file != null && file.exists()) {
                        FILE_UTILS.tryHardToDelete(file);
                    }
                    throw th;
                }
            } else {
                file = createTempFile;
            }
            if (z) {
                FILE_UTILS.rename(file, file4);
                if (this.preserveLastModified) {
                    log("preserved lastModified for " + file4, 4);
                    FILE_UTILS.setFileLastModified(file4, lastModified);
                }
            }
            if (file != null && file.exists()) {
                FILE_UTILS.tryHardToDelete(file);
            }
        } catch (IOException e4) {
            e = e4;
            file = createTempFile;
        } catch (Throwable th4) {
            th = th4;
            file = createTempFile;
            if (file != null) {
                FILE_UTILS.tryHardToDelete(file);
            }
            throw th;
        }
    }

    /* loaded from: classes2.dex */
    protected class OneLiner implements Enumeration<Object> {
        private static final char CTRLZ = 26;
        private static final int INBUFLEN = 8192;
        private static final int LINEBUFLEN = 200;
        private static final int LOOKING = 1;
        private static final int NOTJAVA = 0;
        private static final int UNDEF = -1;
        private BufferedReader reader;
        private File srcFile;
        private int state = FixCRLF.this.filter.getJavafiles() ? 1 : 0;
        private StringBuffer eolStr = new StringBuffer(200);
        private StringBuffer eofStr = new StringBuffer();
        private StringBuffer line = new StringBuffer();
        private boolean reachedEof = false;

        public OneLiner(File file) throws BuildException {
            this.srcFile = file;
            try {
                this.reader = new BufferedReader(FixCRLF.this.encoding == null ? new FileReader(file) : new InputStreamReader(new FileInputStream(file), FixCRLF.this.encoding), 8192);
                nextLine();
            } catch (IOException e) {
                throw new BuildException(file + ": " + e.getMessage(), e, FixCRLF.this.getLocation());
            }
        }

        protected void nextLine() throws BuildException {
            char c;
            this.eolStr = new StringBuffer();
            this.line = new StringBuffer();
            try {
                int read = this.reader.read();
                while (read != -1 && read != 13 && read != 10) {
                    this.line.append((char) read);
                    read = this.reader.read();
                }
                if (read == -1 && this.line.length() == 0) {
                    this.reachedEof = true;
                    return;
                }
                char c2 = (char) read;
                if (c2 == '\n') {
                    this.eolStr.append('\n');
                    c = 1;
                } else if (c2 != '\r') {
                    c = 0;
                } else {
                    this.eolStr.append('\r');
                    this.reader.mark(2);
                    int read2 = this.reader.read();
                    if (read2 != -1) {
                        if (read2 == 10) {
                            this.eolStr.append('\n');
                            c = 2;
                        } else if (read2 != 13) {
                            this.reader.reset();
                        } else if (((char) this.reader.read()) == '\n') {
                            c = 3;
                            this.eolStr.append("\r\n");
                        } else {
                            this.reader.reset();
                        }
                    }
                    c = 1;
                }
                if (c == 0) {
                    int length = this.line.length();
                    while (true) {
                        length--;
                        if (length < 0 || this.line.charAt(length) != 26) {
                            break;
                        }
                    }
                    if (length < this.line.length() - 1) {
                        int i = length + 1;
                        this.eofStr.append(this.line.toString().substring(i));
                        if (length < 0) {
                            this.line.setLength(0);
                            this.reachedEof = true;
                            return;
                        }
                        this.line.setLength(i);
                    }
                }
            } catch (IOException e) {
                throw new BuildException(this.srcFile + ": " + e.getMessage(), e, FixCRLF.this.getLocation());
            }
        }

        public String getEofStr() {
            return this.eofStr.substring(0);
        }

        public int getState() {
            return this.state;
        }

        public void setState(int i) {
            this.state = i;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return !this.reachedEof;
        }

        @Override // java.util.Enumeration
        public Object nextElement() throws NoSuchElementException {
            if (hasMoreElements()) {
                BufferLine bufferLine = new BufferLine(this.line.toString(), this.eolStr.substring(0));
                nextLine();
                return bufferLine;
            }
            throw new NoSuchElementException("OneLiner");
        }

        public void close() throws IOException {
            BufferedReader bufferedReader = this.reader;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        /* loaded from: classes2.dex */
        class BufferLine {
            private int column;
            private String eolStr;
            private String line;
            private int lookahead = -1;
            private int next;

            public BufferLine(String str, String str2) throws BuildException {
                this.next = 0;
                this.column = 0;
                this.next = 0;
                this.column = 0;
                this.line = str;
                this.eolStr = str2;
            }

            public int getNext() {
                return this.next;
            }

            public void setNext(int i) {
                this.next = i;
            }

            public int getLookahead() {
                return this.lookahead;
            }

            public void setLookahead(int i) {
                this.lookahead = i;
            }

            public char getChar(int i) {
                return this.line.charAt(i);
            }

            public char getNextChar() {
                return getChar(this.next);
            }

            public char getNextCharInc() {
                int i = this.next;
                this.next = i + 1;
                return getChar(i);
            }

            public int getColumn() {
                return this.column;
            }

            public void setColumn(int i) {
                this.column = i;
            }

            public int incColumn() {
                int i = this.column;
                this.column = i + 1;
                return i;
            }

            public int length() {
                return this.line.length();
            }

            public int getEolLength() {
                return this.eolStr.length();
            }

            public String getLineString() {
                return this.line;
            }

            public String getEol() {
                return this.eolStr;
            }

            public String substring(int i) {
                return this.line.substring(i);
            }

            public String substring(int i, int i2) {
                return this.line.substring(i, i2);
            }

            public void setState(int i) {
                OneLiner.this.setState(i);
            }

            public int getState() {
                return OneLiner.this.getState();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class AddAsisRemove extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"add", "asis", "remove"};
        }
    }

    /* loaded from: classes2.dex */
    public static class CrLf extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"asis", "cr", "lf", "crlf", C3209Os.FAMILY_MAC, C3209Os.FAMILY_UNIX, C3209Os.FAMILY_DOS};
        }
    }
}
