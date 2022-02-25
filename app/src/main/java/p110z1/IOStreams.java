package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import com.tendcloud.tenddata.C2970ih;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u000b\u001a\u00020\f*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\r\u0010\u0013\u001a\u00020\u000e*\u00020\u0014H\u0087\b\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0001H\u0086\u0002\u001a\f\u0010\u0019\u001a\u00020\u0014*\u00020\u0002H\u0007\u001a\u0016\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b¨\u0006\u001f"}, m8860e = {"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", HttpRequest.PARAM_CHARSET, "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", C2970ih.C2971a.LENGTH, "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"})
@cgo(m5270a = "ByteStreamsKt")
/* renamed from: z1.cfm */
/* loaded from: classes3.dex */
public final class IOStreams {

    /* compiled from: IOStreams.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u0096\u0002J\b\u0010\b\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007¨\u0006\u0015"}, m8860e = {"kotlin/io/ByteStreamsKt$iterator$1", "Lkotlin/collections/ByteIterator;", "finished", "", "getFinished", "()Z", "setFinished", "(Z)V", "nextByte", "", "getNextByte", "()I", "setNextByte", "(I)V", "nextPrepared", "getNextPrepared", "setNextPrepared", "hasNext", "", "prepareNext", "", "kotlin-stdlib"})
    /* renamed from: z1.cfm$a */
    /* loaded from: classes3.dex */
    public static final class C4946a extends bzi {

        /* renamed from: a */
        final /* synthetic */ BufferedInputStream f20647a;

        /* renamed from: b */
        private int f20648b = -1;

        /* renamed from: c */
        private boolean f20649c;

        /* renamed from: d */
        private boolean f20650d;

        C4946a(BufferedInputStream bufferedInputStream) {
            this.f20647a = bufferedInputStream;
        }

        /* renamed from: a */
        public final void m5431a(int i) {
            this.f20648b = i;
        }

        /* renamed from: c */
        public final int m5428c() {
            return this.f20648b;
        }

        /* renamed from: a */
        public final void m5430a(boolean z) {
            this.f20649c = z;
        }

        /* renamed from: d */
        public final boolean m5427d() {
            return this.f20649c;
        }

        /* renamed from: b */
        public final void m5429b(boolean z) {
            this.f20650d = z;
        }

        /* renamed from: e */
        public final boolean m5426e() {
            return this.f20650d;
        }

        /* renamed from: f */
        private final void m5425f() {
            if (!this.f20649c && !this.f20650d) {
                this.f20648b = this.f20647a.read();
                boolean z = true;
                this.f20649c = true;
                if (this.f20648b != -1) {
                    z = false;
                }
                this.f20650d = z;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            m5425f();
            return !this.f20650d;
        }

        @Override // p110z1.bzi
        /* renamed from: b */
        public byte mo5262b() {
            m5425f();
            if (!this.f20650d) {
                byte b = (byte) this.f20648b;
                this.f20649c = false;
                return b;
            }
            throw new NoSuchElementException("Input stream is over.");
        }
    }

    @NotNull
    /* renamed from: a */
    public static final bzi m5453a(@NotNull BufferedInputStream bufferedInputStream) {
        cji.m5162f(bufferedInputStream, "$this$iterator");
        return new C4946a(bufferedInputStream);
    }

    @cey
    /* renamed from: a */
    private static final ByteArrayInputStream m5441a(@NotNull String str, Charset charset) {
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
            return new ByteArrayInputStream(bytes);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: a */
    static /* synthetic */ ByteArrayInputStream m5440a(String str, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
            return new ByteArrayInputStream(bytes);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: a */
    private static final ByteArrayInputStream m5439a(@NotNull byte[] bArr) {
        return new ByteArrayInputStream(bArr);
    }

    @cey
    /* renamed from: a */
    private static final ByteArrayInputStream m5438a(@NotNull byte[] bArr, int i, int i2) {
        return new ByteArrayInputStream(bArr, i, i2);
    }

    @cey
    /* renamed from: b */
    private static final BufferedInputStream m5437b(@NotNull InputStream inputStream, int i) {
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, i);
    }

    @cey
    /* renamed from: a */
    private static final InputStreamReader m5447a(@NotNull InputStream inputStream, Charset charset) {
        return new InputStreamReader(inputStream, charset);
    }

    /* renamed from: a */
    static /* synthetic */ InputStreamReader m5446a(InputStream inputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        return new InputStreamReader(inputStream, charset);
    }

    @cey
    /* renamed from: b */
    private static final BufferedReader m5435b(@NotNull InputStream inputStream, Charset charset) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
    }

    /* renamed from: b */
    static /* synthetic */ BufferedReader m5434b(InputStream inputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
    }

    @cey
    /* renamed from: a */
    private static final BufferedOutputStream m5445a(@NotNull OutputStream outputStream, int i) {
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, i);
    }

    @cey
    /* renamed from: a */
    private static final OutputStreamWriter m5443a(@NotNull OutputStream outputStream, Charset charset) {
        return new OutputStreamWriter(outputStream, charset);
    }

    /* renamed from: a */
    static /* synthetic */ OutputStreamWriter m5442a(OutputStream outputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        return new OutputStreamWriter(outputStream, charset);
    }

    @cey
    /* renamed from: b */
    private static final BufferedWriter m5433b(@NotNull OutputStream outputStream, Charset charset) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    /* renamed from: b */
    static /* synthetic */ BufferedWriter m5432b(OutputStream outputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    /* renamed from: a */
    public static /* synthetic */ long m5448a(InputStream inputStream, OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return m5449a(inputStream, outputStream, i);
    }

    /* renamed from: a */
    public static final long m5449a(@NotNull InputStream inputStream, @NotNull OutputStream outputStream, int i) {
        cji.m5162f(inputStream, "$this$copyTo");
        cji.m5162f(outputStream, "out");
        byte[] bArr = new byte[i];
        int read = inputStream.read(bArr);
        long j = 0;
        while (read >= 0) {
            outputStream.write(bArr, 0, read);
            j += read;
            read = inputStream.read(bArr);
        }
        return j;
    }

    /* renamed from: b */
    public static /* synthetic */ byte[] m5436b(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return m5451a(inputStream, i);
    }

    @Annotations(m8902a = "Use readBytes() overload without estimatedSize parameter", m8901b = @bwu(m8768a = "readBytes()", m8767b = {}))
    @NotNull
    /* renamed from: a */
    public static final byte[] m5451a(@NotNull InputStream inputStream, int i) {
        cji.m5162f(inputStream, "$this$readBytes");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(i, inputStream.available()));
        m5448a(inputStream, byteArrayOutputStream, 0, 2, null);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        cji.m5175b(byteArray, "buffer.toByteArray()");
        return byteArray;
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final byte[] m5452a(@NotNull InputStream inputStream) {
        cji.m5162f(inputStream, "$this$readBytes");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, inputStream.available()));
        m5448a(inputStream, byteArrayOutputStream, 0, 2, null);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        cji.m5175b(byteArray, "buffer.toByteArray()");
        return byteArray;
    }

    /* renamed from: a */
    static /* synthetic */ BufferedInputStream m5450a(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, i);
    }

    /* renamed from: a */
    static /* synthetic */ BufferedOutputStream m5444a(OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, i);
    }
}
