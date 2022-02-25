package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tools.ant.types.selectors.ContainsSelector;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\u0087\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a?\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010,\u001a\u0012\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010.\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010/\u001a\u000200*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u0082\u0002\b\n\u0006\b\u0011(+0\u0001¨\u00061"}, m8860e = {"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", ContainsSelector.CONTAINS_KEY, "", HttpRequest.PARAM_CHARSET, "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", TessBaseAPI.f9204e, "block", "Lkotlin/sequences/Sequence;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, m8859f = "kotlin/io/FilesKt", m8857h = 1)
/* renamed from: z1.cfz */
/* loaded from: classes3.dex */
public class FileReadWrite extends cfy {
    /* renamed from: a */
    static /* synthetic */ InputStreamReader m5356a(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        return new InputStreamReader(new FileInputStream(file), charset);
    }

    @cey
    /* renamed from: c */
    private static final InputStreamReader m5342c(@NotNull File file, Charset charset) {
        return new InputStreamReader(new FileInputStream(file), charset);
    }

    /* renamed from: a */
    static /* synthetic */ BufferedReader m5357a(File file, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.f20995a;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, i);
    }

    @cey
    /* renamed from: a */
    private static final BufferedReader m5358a(@NotNull File file, Charset charset, int i) {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, i);
    }

    /* renamed from: b */
    static /* synthetic */ OutputStreamWriter m5346b(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        return new OutputStreamWriter(new FileOutputStream(file), charset);
    }

    @cey
    /* renamed from: d */
    private static final OutputStreamWriter m5340d(@NotNull File file, Charset charset) {
        return new OutputStreamWriter(new FileOutputStream(file), charset);
    }

    /* renamed from: b */
    static /* synthetic */ BufferedWriter m5347b(File file, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.f20995a;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, i);
    }

    @cey
    /* renamed from: b */
    private static final BufferedWriter m5348b(@NotNull File file, Charset charset, int i) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, i);
    }

    /* renamed from: c */
    static /* synthetic */ PrintWriter m5341c(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @cey
    /* renamed from: e */
    private static final PrintWriter m5337e(@NotNull File file, Charset charset) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @NotNull
    /* renamed from: e */
    public static final byte[] m5338e(@NotNull File file) {
        cji.m5162f(file, "$this$readBytes");
        th = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int i = 0;
            long length = file.length();
            if (length <= Integer.MAX_VALUE) {
                int i2 = (int) length;
                byte[] bArr = new byte[i2];
                while (i2 > 0) {
                    int read = fileInputStream.read(bArr, i, i2);
                    if (read < 0) {
                        break;
                    }
                    i2 -= read;
                    i += read;
                }
                if (i2 != 0) {
                    bArr = Arrays.copyOf(bArr, i);
                    cji.m5175b(bArr, "java.util.Arrays.copyOf(this, newSize)");
                }
                return bArr;
            }
            throw new OutOfMemoryError("File " + file + " is too big (" + length + " bytes) to fit in memory.");
        } finally {
            try {
            } finally {
            }
        }
    }

    /* renamed from: a */
    public static final void m5352a(@NotNull File file, @NotNull byte[] bArr) {
        cji.m5162f(file, "$this$writeBytes");
        cji.m5162f(bArr, "array");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        th = null;
        try {
            fileOutputStream.write(bArr);
            Unit bydVar = Unit.f20418a;
        } finally {
            try {
            } finally {
            }
        }
    }

    /* renamed from: b */
    public static final void m5343b(@NotNull File file, @NotNull byte[] bArr) {
        cji.m5162f(file, "$this$appendBytes");
        cji.m5162f(bArr, "array");
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        th = null;
        try {
            fileOutputStream.write(bArr);
            Unit bydVar = Unit.f20418a;
        } finally {
            try {
            } finally {
            }
        }
    }

    @NotNull
    /* renamed from: a */
    public static final String m5359a(@NotNull File file, @NotNull Charset charset) {
        cji.m5162f(file, "$this$readText");
        cji.m5162f(charset, HttpRequest.PARAM_CHARSET);
        return new String(cfx.m5338e(file), charset);
    }

    /* renamed from: d */
    public static /* synthetic */ String m5339d(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        return cfx.m5359a(file, charset);
    }

    /* renamed from: a */
    public static final void m5361a(@NotNull File file, @NotNull String str, @NotNull Charset charset) {
        cji.m5162f(file, "$this$writeText");
        cji.m5162f(str, ContainsSelector.CONTAINS_KEY);
        cji.m5162f(charset, HttpRequest.PARAM_CHARSET);
        byte[] bytes = str.getBytes(charset);
        cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
        cfx.m5352a(file, bytes);
    }

    /* renamed from: a */
    public static /* synthetic */ void m5360a(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.f20995a;
        }
        cfx.m5361a(file, str, charset);
    }

    /* renamed from: b */
    public static final void m5351b(@NotNull File file, @NotNull String str, @NotNull Charset charset) {
        cji.m5162f(file, "$this$appendText");
        cji.m5162f(str, ContainsSelector.CONTAINS_KEY);
        cji.m5162f(charset, HttpRequest.PARAM_CHARSET);
        byte[] bytes = str.getBytes(charset);
        cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
        cfx.m5343b(file, bytes);
    }

    /* renamed from: b */
    public static /* synthetic */ void m5350b(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.f20995a;
        }
        cfx.m5351b(file, str, charset);
    }

    /* renamed from: a */
    public static final void m5353a(@NotNull File file, @NotNull cho<? super byte[], ? super Integer, Unit> choVar) {
        cji.m5162f(file, "$this$forEachBlock");
        cji.m5162f(choVar, "action");
        cfx.m5362a(file, 4096, choVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [byte[], java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m5362a(@p110z1.NotNull java.io.File r3, int r4, @p110z1.NotNull p110z1.cho<? super byte[], ? super java.lang.Integer, p110z1.Unit> r5) {
        /*
            java.lang.String r0 = "$this$forEachBlock"
            p110z1.cji.m5162f(r3, r0)
            java.lang.String r0 = "action"
            p110z1.cji.m5162f(r5, r0)
            r0 = 512(0x200, float:7.175E-43)
            int r4 = p110z1.cmi.m4715c(r4, r0)
            byte[] r4 = new byte[r4]
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r3)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r1 = r0
            java.io.FileInputStream r1 = (java.io.FileInputStream) r1     // Catch: Throwable -> 0x0035
        L_0x001f:
            int r2 = r1.read(r4)     // Catch: Throwable -> 0x0035
            if (r2 > 0) goto L_0x002b
            z1.byd r4 = p110z1.Unit.f20418a     // Catch: Throwable -> 0x0035
            p110z1.Closeable.m5424a(r0, r3)
            return
        L_0x002b:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: Throwable -> 0x0035
            r5.invoke(r4, r2)     // Catch: Throwable -> 0x0035
            goto L_0x001f
        L_0x0033:
            r4 = move-exception
            goto L_0x0037
        L_0x0035:
            r3 = move-exception
            throw r3     // Catch: all -> 0x0033
        L_0x0037:
            p110z1.Closeable.m5424a(r0, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.FileReadWrite.m5362a(java.io.File, int, z1.cho):void");
    }

    /* renamed from: a */
    public static /* synthetic */ void m5354a(File file, Charset charset, chd chdVar, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        cfx.m5355a(file, charset, chdVar);
    }

    /* renamed from: a */
    public static final void m5355a(@NotNull File file, @NotNull Charset charset, @NotNull chd<? super String, Unit> chdVar) {
        cji.m5162f(file, "$this$forEachLine");
        cji.m5162f(charset, HttpRequest.PARAM_CHARSET);
        cji.m5162f(chdVar, "action");
        cgh.m5292a(new BufferedReader(new InputStreamReader(new FileInputStream(file), charset)), chdVar);
    }

    @cey
    /* renamed from: f */
    private static final FileInputStream m5335f(@NotNull File file) {
        return new FileInputStream(file);
    }

    @cey
    /* renamed from: g */
    private static final FileOutputStream m5334g(@NotNull File file) {
        return new FileOutputStream(file);
    }

    /* renamed from: e */
    public static /* synthetic */ List m5336e(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        return cfx.m5349b(file, charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FileReadWrite.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m8860e = {"<anonymous>", "", "it", "", "invoke"})
    /* renamed from: z1.cfz$a */
    /* loaded from: classes3.dex */
    public static final class C4954a extends Lambda implements chd<String, Unit> {
        final /* synthetic */ ArrayList $result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C4954a(ArrayList arrayList) {
            super(1);
            this.$result = arrayList;
        }

        @Override // p110z1.chd
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.f20418a;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull String str) {
            cji.m5162f(str, "it");
            this.$result.add(str);
        }
    }

    @NotNull
    /* renamed from: b */
    public static final List<String> m5349b(@NotNull File file, @NotNull Charset charset) {
        cji.m5162f(file, "$this$readLines");
        cji.m5162f(charset, HttpRequest.PARAM_CHARSET);
        ArrayList arrayList = new ArrayList();
        cfx.m5355a(file, charset, new C4954a(arrayList));
        return arrayList;
    }

    /* renamed from: b */
    public static /* synthetic */ Object m5344b(File file, Charset charset, chd chdVar, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        cji.m5162f(file, "$this$useLines");
        cji.m5162f(charset, HttpRequest.PARAM_CHARSET);
        cji.m5162f(chdVar, "block");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        Throwable th = null;
        try {
            Object invoke = chdVar.invoke(cgh.m5298a(bufferedReader));
            InlineMarker.m5201b(1);
            if (cfe.m5471a(1, 1, 0)) {
                Closeable.m5424a(bufferedReader, th);
            } else {
                bufferedReader.close();
            }
            InlineMarker.m5200c(1);
            return invoke;
        } finally {
            try {
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* renamed from: b */
    public static final <T> T m5345b(@NotNull File file, @NotNull Charset charset, @NotNull chd<? super Sequence<String>, ? extends T> chdVar) {
        cji.m5162f(file, "$this$useLines");
        cji.m5162f(charset, HttpRequest.PARAM_CHARSET);
        cji.m5162f(chdVar, "block");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        Throwable th = null;
        try {
            T t = (T) chdVar.invoke(cgh.m5298a(bufferedReader));
            InlineMarker.m5201b(1);
            if (cfe.m5471a(1, 1, 0)) {
                Closeable.m5424a(bufferedReader, th);
            } else {
                bufferedReader.close();
            }
            InlineMarker.m5200c(1);
            return t;
        } finally {
            try {
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
