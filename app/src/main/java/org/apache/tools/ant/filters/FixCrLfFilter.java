package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* loaded from: classes2.dex */
public final class FixCrLfFilter extends BaseParamFilterReader implements ChainableReader {
    private static final char CTRLZ = 26;
    private static final int DEFAULT_TAB_LENGTH = 8;
    private static final int MAX_TAB_LENGTH = 80;
    private static final int MIN_TAB_LENGTH = 2;
    private AddAsisRemove ctrlz;
    private CrLf eol;
    private int tabLength = 8;
    private boolean javafiles = false;
    private boolean fixlast = true;
    private boolean initialized = false;
    private AddAsisRemove tabs = AddAsisRemove.ASIS;

    public FixCrLfFilter() {
        if (C3209Os.isFamily(C3209Os.FAMILY_MAC) && !C3209Os.isFamily(C3209Os.FAMILY_UNIX)) {
            this.ctrlz = AddAsisRemove.REMOVE;
            setEol(CrLf.MAC);
        } else if (C3209Os.isFamily(C3209Os.FAMILY_DOS)) {
            this.ctrlz = AddAsisRemove.ASIS;
            setEol(CrLf.DOS);
        } else {
            this.ctrlz = AddAsisRemove.REMOVE;
            setEol(CrLf.UNIX);
        }
    }

    public FixCrLfFilter(Reader reader) throws IOException {
        super(reader);
        if (C3209Os.isFamily(C3209Os.FAMILY_MAC) && !C3209Os.isFamily(C3209Os.FAMILY_UNIX)) {
            this.ctrlz = AddAsisRemove.REMOVE;
            setEol(CrLf.MAC);
        } else if (C3209Os.isFamily(C3209Os.FAMILY_DOS)) {
            this.ctrlz = AddAsisRemove.ASIS;
            setEol(CrLf.DOS);
        } else {
            this.ctrlz = AddAsisRemove.REMOVE;
            setEol(CrLf.UNIX);
        }
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        try {
            FixCrLfFilter fixCrLfFilter = new FixCrLfFilter(reader);
            fixCrLfFilter.setJavafiles(getJavafiles());
            fixCrLfFilter.setEol(getEol());
            fixCrLfFilter.setTab(getTab());
            fixCrLfFilter.setTablength(getTablength());
            fixCrLfFilter.setEof(getEof());
            fixCrLfFilter.setFixlast(getFixlast());
            fixCrLfFilter.initInternalFilters();
            return fixCrLfFilter;
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    public AddAsisRemove getEof() {
        return this.ctrlz.newInstance();
    }

    public CrLf getEol() {
        return this.eol.newInstance();
    }

    public boolean getFixlast() {
        return this.fixlast;
    }

    public boolean getJavafiles() {
        return this.javafiles;
    }

    public AddAsisRemove getTab() {
        return this.tabs.newInstance();
    }

    public int getTablength() {
        return this.tabLength;
    }

    private static String calculateEolString(CrLf crLf) {
        return (crLf == CrLf.f14737CR || crLf == CrLf.MAC) ? "\r" : (crLf == CrLf.CRLF || crLf == CrLf.DOS) ? "\r\n" : "\n";
    }

    private void initInternalFilters() {
        this.in = this.ctrlz == AddAsisRemove.REMOVE ? new RemoveEofFilter(this.in) : this.in;
        if (this.eol != CrLf.ASIS) {
            this.in = new NormalizeEolFilter(this.in, calculateEolString(this.eol), getFixlast());
        }
        if (this.tabs != AddAsisRemove.ASIS) {
            if (getJavafiles()) {
                this.in = new MaskJavaTabLiteralsFilter(this.in);
            }
            this.in = this.tabs == AddAsisRemove.ADD ? new AddTabFilter(this.in, getTablength()) : new RemoveTabFilter(this.in, getTablength());
        }
        this.in = this.ctrlz == AddAsisRemove.ADD ? new AddEofFilter(this.in) : this.in;
        this.initialized = true;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public synchronized int read() throws IOException {
        if (!this.initialized) {
            initInternalFilters();
        }
        return this.in.read();
    }

    public void setEof(AddAsisRemove addAsisRemove) {
        this.ctrlz = addAsisRemove.resolve();
    }

    public void setEol(CrLf crLf) {
        this.eol = crLf.resolve();
    }

    public void setFixlast(boolean z) {
        this.fixlast = z;
    }

    public void setJavafiles(boolean z) {
        this.javafiles = z;
    }

    public void setTab(AddAsisRemove addAsisRemove) {
        this.tabs = addAsisRemove.resolve();
    }

    public void setTablength(int i) throws IOException {
        if (i < 2 || i > 80) {
            throw new IOException("tablength must be between 2 and 80");
        }
        this.tabLength = i;
    }

    /* loaded from: classes2.dex */
    private static class SimpleFilterReader extends Reader {
        private static final int PREEMPT_BUFFER_LENGTH = 16;

        /* renamed from: in */
        private Reader f14739in;
        private int[] preempt = new int[16];
        private int preemptIndex = 0;

        public SimpleFilterReader(Reader reader) {
            this.f14739in = reader;
        }

        public void push(char c) {
            push((int) c);
        }

        public void push(int i) {
            try {
                int[] iArr = this.preempt;
                int i2 = this.preemptIndex;
                this.preemptIndex = i2 + 1;
                iArr[i2] = i;
            } catch (ArrayIndexOutOfBoundsException unused) {
                int[] iArr2 = this.preempt;
                int[] iArr3 = new int[iArr2.length * 2];
                System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
                this.preempt = iArr3;
                push(i);
            }
        }

        public void push(char[] cArr, int i, int i2) {
            int i3 = (i2 + i) - 1;
            while (i3 >= i) {
                i3--;
                push(cArr[i3]);
            }
        }

        public void push(char[] cArr) {
            push(cArr, 0, cArr.length);
        }

        public boolean editsBlocked() {
            Reader reader = this.f14739in;
            return (reader instanceof SimpleFilterReader) && ((SimpleFilterReader) reader).editsBlocked();
        }

        @Override // java.io.Reader
        public int read() throws IOException {
            int i = this.preemptIndex;
            if (i <= 0) {
                return this.f14739in.read();
            }
            int[] iArr = this.preempt;
            int i2 = i - 1;
            this.preemptIndex = i2;
            return iArr[i2];
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f14739in.close();
        }

        @Override // java.io.Reader
        public void reset() throws IOException {
            this.f14739in.reset();
        }

        @Override // java.io.Reader
        public boolean markSupported() {
            return this.f14739in.markSupported();
        }

        @Override // java.io.Reader
        public boolean ready() throws IOException {
            return this.f14739in.ready();
        }

        @Override // java.io.Reader
        public void mark(int i) throws IOException {
            this.f14739in.mark(i);
        }

        @Override // java.io.Reader
        public long skip(long j) throws IOException {
            return this.f14739in.skip(j);
        }

        @Override // java.io.Reader
        public int read(char[] cArr) throws IOException {
            return read(cArr, 0, cArr.length);
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            int i3 = 0;
            int i4 = i;
            int i5 = 0;
            while (true) {
                i2--;
                if (i2 <= 0 || (i5 = read()) == -1) {
                    break;
                }
                i4++;
                cArr[i4] = (char) i5;
                i3++;
            }
            if (i3 == 0 && i5 == -1) {
                return -1;
            }
            return i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MaskJavaTabLiteralsFilter extends SimpleFilterReader {
        private static final int IN_CHAR_CONST = 2;
        private static final int IN_MULTI_COMMENT = 5;
        private static final int IN_SINGLE_COMMENT = 4;
        private static final int IN_STR_CONST = 3;
        private static final int JAVA = 1;
        private static final int TRANS_FROM_MULTI = 8;
        private static final int TRANS_TO_COMMENT = 6;
        private boolean editsBlocked = false;
        private int state = 1;

        public MaskJavaTabLiteralsFilter(Reader reader) {
            super(reader);
        }

        @Override // org.apache.tools.ant.filters.FixCrLfFilter.SimpleFilterReader
        public boolean editsBlocked() {
            return this.editsBlocked || super.editsBlocked();
        }

        @Override // org.apache.tools.ant.filters.FixCrLfFilter.SimpleFilterReader, java.io.Reader
        public int read() throws IOException {
            int read = super.read();
            int i = this.state;
            this.editsBlocked = i == 2 || i == 3;
            switch (this.state) {
                case 1:
                    if (read != 34) {
                        if (read != 39) {
                            if (read == 47) {
                                this.state = 6;
                                break;
                            }
                        } else {
                            this.state = 2;
                            break;
                        }
                    } else {
                        this.state = 3;
                        break;
                    }
                    break;
                case 2:
                    if (read == 39) {
                        this.state = 1;
                        break;
                    }
                    break;
                case 3:
                    if (read == 34) {
                        this.state = 1;
                        break;
                    }
                    break;
                case 4:
                    if (read == 10 || read == 13) {
                        this.state = 1;
                        break;
                    }
                    break;
                case 5:
                    if (read == 42) {
                        this.state = 8;
                        break;
                    }
                    break;
                case 6:
                    if (read != 34) {
                        if (read != 39) {
                            if (read != 42) {
                                if (read == 47) {
                                    this.state = 4;
                                    break;
                                } else {
                                    this.state = 1;
                                    break;
                                }
                            } else {
                                this.state = 5;
                                break;
                            }
                        } else {
                            this.state = 2;
                            break;
                        }
                    } else {
                        this.state = 3;
                        break;
                    }
                case 8:
                    if (read == 47) {
                        this.state = 1;
                        break;
                    }
                    break;
            }
            return read;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NormalizeEolFilter extends SimpleFilterReader {
        private char[] eol;
        private boolean fixLast;
        private int normalizedEOL = 0;
        private boolean previousWasEOL;

        public NormalizeEolFilter(Reader reader, String str, boolean z) {
            super(reader);
            this.eol = null;
            this.eol = str.toCharArray();
            this.fixLast = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x0069 A[LOOP:0: B:39:0x0069->B:41:0x006d, LOOP_START, PHI: r1 
          PHI: (r1v4 int) = (r1v3 int), (r1v8 int) binds: [B:38:0x0067, B:41:0x006d] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0083  */
        @Override // org.apache.tools.ant.filters.FixCrLfFilter.SimpleFilterReader, java.io.Reader
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int read() throws java.io.IOException {
            /*
                r7 = this;
                int r0 = super.read()
                int r1 = r7.normalizedEOL
                r2 = 1
                if (r1 != 0) goto L_0x0088
                r1 = -1
                r3 = 0
                if (r0 == r1) goto L_0x005a
                r4 = 10
                if (r0 == r4) goto L_0x0057
                r5 = 13
                if (r0 == r5) goto L_0x0034
                r4 = 26
                if (r0 == r4) goto L_0x001a
                goto L_0x0031
            L_0x001a:
                int r4 = super.read()
                if (r4 != r1) goto L_0x002e
                boolean r1 = r7.fixLast
                if (r1 == 0) goto L_0x0065
                boolean r1 = r7.previousWasEOL
                if (r1 != 0) goto L_0x0065
                r7.push(r0)
                r1 = 1
                r4 = 1
                goto L_0x0067
            L_0x002e:
                r7.push(r4)
            L_0x0031:
                r1 = 0
            L_0x0032:
                r4 = 0
                goto L_0x0067
            L_0x0034:
                int r1 = super.read()
                int r6 = super.read()
                if (r1 != r5) goto L_0x0041
                if (r6 != r4) goto L_0x0041
                goto L_0x0055
            L_0x0041:
                if (r1 != r5) goto L_0x0049
                r1 = 2
                r7.push(r6)
                r4 = 0
                goto L_0x0067
            L_0x0049:
                if (r1 != r4) goto L_0x004f
                r7.push(r6)
                goto L_0x0055
            L_0x004f:
                r7.push(r6)
                r7.push(r1)
            L_0x0055:
                r1 = 1
                goto L_0x0032
            L_0x0057:
                r1 = 1
                r4 = 0
                goto L_0x0067
            L_0x005a:
                boolean r1 = r7.fixLast
                if (r1 == 0) goto L_0x0065
                boolean r1 = r7.previousWasEOL
                if (r1 != 0) goto L_0x0065
                r1 = 1
                r4 = 1
                goto L_0x0067
            L_0x0065:
                r1 = 0
                r4 = 1
            L_0x0067:
                if (r1 <= 0) goto L_0x0083
            L_0x0069:
                int r0 = r1 + (-1)
                if (r1 <= 0) goto L_0x007c
                char[] r1 = r7.eol
                r7.push(r1)
                int r1 = r7.normalizedEOL
                char[] r3 = r7.eol
                int r3 = r3.length
                int r1 = r1 + r3
                r7.normalizedEOL = r1
                r1 = r0
                goto L_0x0069
            L_0x007c:
                r7.previousWasEOL = r2
                int r0 = r7.read()
                goto L_0x008b
            L_0x0083:
                if (r4 != 0) goto L_0x008b
                r7.previousWasEOL = r3
                goto L_0x008b
            L_0x0088:
                int r1 = r1 - r2
                r7.normalizedEOL = r1
            L_0x008b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.filters.FixCrLfFilter.NormalizeEolFilter.read():int");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AddEofFilter extends SimpleFilterReader {
        private int lastChar = -1;

        public AddEofFilter(Reader reader) {
            super(reader);
        }

        @Override // org.apache.tools.ant.filters.FixCrLfFilter.SimpleFilterReader, java.io.Reader
        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.lastChar = read;
            } else if (this.lastChar != 26) {
                this.lastChar = 26;
                return this.lastChar;
            }
            return read;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RemoveEofFilter extends SimpleFilterReader {
        private int lookAhead;

        public RemoveEofFilter(Reader reader) {
            super(reader);
            this.lookAhead = -1;
            try {
                this.lookAhead = reader.read();
            } catch (IOException unused) {
                this.lookAhead = -1;
            }
        }

        @Override // org.apache.tools.ant.filters.FixCrLfFilter.SimpleFilterReader, java.io.Reader
        public int read() throws IOException {
            int read = super.read();
            if (read == -1 && this.lookAhead == 26) {
                return -1;
            }
            int i = this.lookAhead;
            this.lookAhead = read;
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AddTabFilter extends SimpleFilterReader {
        private int columnNumber = 0;
        private int tabLength;

        public AddTabFilter(Reader reader, int i) {
            super(reader);
            this.tabLength = 0;
            this.tabLength = i;
        }

        @Override // org.apache.tools.ant.filters.FixCrLfFilter.SimpleFilterReader, java.io.Reader
        public int read() throws IOException {
            int read = super.read();
            if (read != 13) {
                if (read != 32) {
                    switch (read) {
                        case 9:
                            int i = this.columnNumber;
                            int i2 = this.tabLength;
                            this.columnNumber = (((i + i2) - 1) / i2) * i2;
                            break;
                        case 10:
                            break;
                        default:
                            this.columnNumber++;
                            break;
                    }
                } else {
                    this.columnNumber++;
                    if (!editsBlocked()) {
                        int i3 = this.columnNumber;
                        int i4 = this.tabLength;
                        int i5 = (((i3 + i4) - 1) / i4) * i4;
                        int i6 = 1;
                        int i7 = 0;
                        while (true) {
                            int read2 = super.read();
                            if (read2 == -1) {
                                break;
                            } else if (read2 == 9) {
                                this.columnNumber = i5;
                                i7++;
                                i5 += this.tabLength;
                                i6 = 0;
                            } else if (read2 != 32) {
                                push(read2);
                                break;
                            } else {
                                int i8 = this.columnNumber + 1;
                                this.columnNumber = i8;
                                if (i8 == i5) {
                                    i7++;
                                    i5 += this.tabLength;
                                    i6 = 0;
                                } else {
                                    i6++;
                                }
                            }
                        }
                        while (true) {
                            i6--;
                            if (i6 > 0) {
                                push(' ');
                                this.columnNumber--;
                            }
                        }
                        while (true) {
                            i7--;
                            if (i7 <= 0) {
                                break;
                            }
                            push('\t');
                            this.columnNumber -= this.tabLength;
                        }
                        read = super.read();
                        if (read == 9) {
                            this.columnNumber += this.tabLength;
                        } else if (read == 32) {
                            this.columnNumber++;
                        }
                    }
                }
                return read;
            }
            this.columnNumber = 0;
            return read;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RemoveTabFilter extends SimpleFilterReader {
        private int columnNumber = 0;
        private int tabLength;

        public RemoveTabFilter(Reader reader, int i) {
            super(reader);
            this.tabLength = 0;
            this.tabLength = i;
        }

        @Override // org.apache.tools.ant.filters.FixCrLfFilter.SimpleFilterReader, java.io.Reader
        public int read() throws IOException {
            int read = super.read();
            if (read != 13) {
                switch (read) {
                    case 9:
                        int i = this.tabLength;
                        int i2 = i - (this.columnNumber % i);
                        if (!editsBlocked()) {
                            while (i2 > 1) {
                                push(' ');
                                i2--;
                            }
                            read = 32;
                        }
                        this.columnNumber += i2;
                        break;
                    case 10:
                        break;
                    default:
                        this.columnNumber++;
                        break;
                }
                return read;
            }
            this.columnNumber = 0;
            return read;
        }
    }

    /* loaded from: classes2.dex */
    public static class AddAsisRemove extends EnumeratedAttribute {
        private static final AddAsisRemove ASIS = newInstance("asis");
        private static final AddAsisRemove ADD = newInstance("add");
        private static final AddAsisRemove REMOVE = newInstance("remove");

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"add", "asis", "remove"};
        }

        public boolean equals(Object obj) {
            return (obj instanceof AddAsisRemove) && getIndex() == ((AddAsisRemove) obj).getIndex();
        }

        public int hashCode() {
            return getIndex();
        }

        AddAsisRemove resolve() throws IllegalStateException {
            if (equals(ASIS)) {
                return ASIS;
            }
            if (equals(ADD)) {
                return ADD;
            }
            if (equals(REMOVE)) {
                return REMOVE;
            }
            throw new IllegalStateException("No replacement for " + this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public AddAsisRemove newInstance() {
            return newInstance(getValue());
        }

        public static AddAsisRemove newInstance(String str) {
            AddAsisRemove addAsisRemove = new AddAsisRemove();
            addAsisRemove.setValue(str);
            return addAsisRemove;
        }
    }

    /* loaded from: classes2.dex */
    public static class CrLf extends EnumeratedAttribute {
        private static final CrLf ASIS = newInstance("asis");

        /* renamed from: CR */
        private static final CrLf f14737CR = newInstance("cr");
        private static final CrLf CRLF = newInstance("crlf");
        private static final CrLf DOS = newInstance(C3209Os.FAMILY_DOS);

        /* renamed from: LF */
        private static final CrLf f14738LF = newInstance("lf");
        private static final CrLf MAC = newInstance(C3209Os.FAMILY_MAC);
        private static final CrLf UNIX = newInstance(C3209Os.FAMILY_UNIX);

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"asis", "cr", "lf", "crlf", C3209Os.FAMILY_MAC, C3209Os.FAMILY_UNIX, C3209Os.FAMILY_DOS};
        }

        public boolean equals(Object obj) {
            return (obj instanceof CrLf) && getIndex() == ((CrLf) obj).getIndex();
        }

        public int hashCode() {
            return getIndex();
        }

        CrLf resolve() {
            if (equals(ASIS)) {
                return ASIS;
            }
            if (equals(f14737CR) || equals(MAC)) {
                return f14737CR;
            }
            if (equals(CRLF) || equals(DOS)) {
                return CRLF;
            }
            if (equals(f14738LF) || equals(UNIX)) {
                return f14738LF;
            }
            throw new IllegalStateException("No replacement for " + this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CrLf newInstance() {
            return newInstance(getValue());
        }

        public static CrLf newInstance(String str) {
            CrLf crLf = new CrLf();
            crLf.setValue(str);
            return crLf;
        }
    }
}
