package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: ReadWrite.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\u0087\b\u001a5\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001f\u0082\u0002\b\n\u0006\b\u0011(\u001e0\u0001¨\u0006 "}, m8860e = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", HttpRequest.PARAM_CHARSET, "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", TessBaseAPI.f9204e, "block", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"})
@cgo(m5270a = "TextStreamsKt")
/* renamed from: z1.cgh */
/* loaded from: classes3.dex */
public final class cgh {
    @cey
    /* renamed from: a */
    private static final BufferedReader m5296a(@NotNull Reader reader, int i) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    @cey
    /* renamed from: a */
    private static final BufferedWriter m5291a(@NotNull Writer writer, int i) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    /* compiled from: ReadWrite.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m8860e = {"<anonymous>", "", "it", "", "invoke"})
    /* renamed from: z1.cgh$a */
    /* loaded from: classes3.dex */
    static final class C4959a extends Lambda implements chd<String, Unit> {
        final /* synthetic */ ArrayList $result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C4959a(ArrayList arrayList) {
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
    /* renamed from: a */
    public static final List<String> m5297a(@NotNull Reader reader) {
        cji.m5162f(reader, "$this$readLines");
        ArrayList arrayList = new ArrayList();
        m5292a(reader, new C4959a(arrayList));
        return arrayList;
    }

    /* renamed from: b */
    public static final <T> T m5284b(@NotNull Reader reader, @NotNull chd<? super Sequence<String>, ? extends T> chdVar) {
        cji.m5162f(reader, "$this$useLines");
        cji.m5162f(chdVar, "block");
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        Throwable th = null;
        try {
            T t = (T) chdVar.invoke(m5298a(bufferedReader));
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

    @cey
    /* renamed from: a */
    private static final StringReader m5289a(@NotNull String str) {
        return new StringReader(str);
    }

    @NotNull
    /* renamed from: a */
    public static final Sequence<String> m5298a(@NotNull BufferedReader bufferedReader) {
        cji.m5162f(bufferedReader, "$this$lineSequence");
        return coe.m4436d(new ReadWrite(bufferedReader));
    }

    @NotNull
    /* renamed from: b */
    public static final String m5285b(@NotNull Reader reader) {
        cji.m5162f(reader, "$this$readText");
        StringWriter stringWriter = new StringWriter();
        m5293a(reader, stringWriter, 0, 2, null);
        String stringWriter2 = stringWriter.toString();
        cji.m5175b(stringWriter2, "buffer.toString()");
        return stringWriter2;
    }

    /* renamed from: a */
    public static /* synthetic */ long m5293a(Reader reader, Writer writer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return m5294a(reader, writer, i);
    }

    /* renamed from: a */
    public static final long m5294a(@NotNull Reader reader, @NotNull Writer writer, int i) {
        cji.m5162f(reader, "$this$copyTo");
        cji.m5162f(writer, "out");
        char[] cArr = new char[i];
        int read = reader.read(cArr);
        long j = 0;
        while (read >= 0) {
            writer.write(cArr, 0, read);
            j += read;
            read = reader.read(cArr);
        }
        return j;
    }

    @cey
    /* renamed from: a */
    private static final String m5287a(@NotNull URL url, Charset charset) {
        return new String(m5288a(url), charset);
    }

    /* renamed from: a */
    static /* synthetic */ String m5286a(URL url, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        return new String(m5288a(url), charset);
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m5288a(@NotNull URL url) {
        cji.m5162f(url, "$this$readBytes");
        th = null;
        try {
            InputStream openStream = url.openStream();
            cji.m5175b(openStream, "it");
            return IOStreams.m5452a(openStream);
        } finally {
            try {
            } finally {
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ BufferedReader m5295a(Reader reader, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    /* renamed from: a */
    static /* synthetic */ BufferedWriter m5290a(Writer writer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    /* renamed from: a */
    public static final void m5292a(@NotNull Reader reader, @NotNull chd<? super String, Unit> chdVar) {
        cji.m5162f(reader, "$this$forEachLine");
        cji.m5162f(chdVar, "action");
        th = null;
        try {
            Iterator<String> a = m5298a(reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192)).mo3707a();
            while (a.hasNext()) {
                chdVar.invoke(a.next());
            }
            Unit bydVar = Unit.f20418a;
        } finally {
            try {
            } finally {
            }
        }
    }
}
