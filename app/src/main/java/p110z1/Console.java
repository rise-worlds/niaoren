package p110z1;

import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0012H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0013H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0014H\u0087\b\u001a\t\u0010\u0015\u001a\u00020\nH\u0087\b\u001a\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0012H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0013H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0014H\u0087\b\u001a\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u001a\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\f\u0010\u001a\u001a\u00020\r*\u00020\u001bH\u0002\u001a\f\u0010\u001c\u001a\u00020\n*\u00020\u001dH\u0002\u001a\u0018\u0010\u001e\u001a\u00020\n*\u00020\u001b2\n\u0010\u001f\u001a\u00060 j\u0002`!H\u0002\u001a$\u0010\"\u001a\u00020\r*\u00020\u00042\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\rH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006'"}, m8860e = {"BUFFER_SIZE", "", "LINE_SEPARATOR_MAX_LENGTH", "decoder", "Ljava/nio/charset/CharsetDecoder;", "getDecoder", "()Ljava/nio/charset/CharsetDecoder;", "decoder$delegate", "Lkotlin/Lazy;", "print", "", "message", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "inputStream", "Ljava/io/InputStream;", "endsWithLineSeparator", "Ljava/nio/CharBuffer;", "flipBack", "Ljava/nio/Buffer;", "offloadPrefixTo", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "tryDecode", "byteBuffer", "Ljava/nio/ByteBuffer;", "charBuffer", "isEndOfStream", "kotlin-stdlib"})
@cgo(m5270a = "ConsoleKt")
/* renamed from: z1.cfo */
/* loaded from: classes3.dex */
public final class Console {

    /* renamed from: b */
    private static final int f20652b = 32;

    /* renamed from: c */
    private static final int f20653c = 2;

    /* renamed from: a */
    static final /* synthetic */ cnf[] f20651a = {Reflection.m5113a(new PropertyReference0Impl(Reflection.m5108b(Console.class, "kotlin-stdlib"), "decoder", "getDecoder()Ljava/nio/charset/CharsetDecoder;"))};

    /* renamed from: d */
    private static final bvz f20654d = bwa.m8867a((chc) C4947a.INSTANCE);

    /* renamed from: c */
    private static final CharsetDecoder m5395c() {
        bvz bvzVar = f20654d;
        cnf cnfVar = f20651a[0];
        return (CharsetDecoder) bvzVar.getValue();
    }

    @cey
    /* renamed from: a */
    private static final void m5414a(Object obj) {
        System.out.print(obj);
    }

    @cey
    /* renamed from: a */
    private static final void m5417a(int i) {
        System.out.print(i);
    }

    @cey
    /* renamed from: a */
    private static final void m5416a(long j) {
        System.out.print(j);
    }

    @cey
    /* renamed from: a */
    private static final void m5421a(byte b) {
        System.out.print(Byte.valueOf(b));
    }

    @cey
    /* renamed from: a */
    private static final void m5409a(short s) {
        System.out.print(Short.valueOf(s));
    }

    @cey
    /* renamed from: a */
    private static final void m5420a(char c) {
        System.out.print(c);
    }

    @cey
    /* renamed from: a */
    private static final void m5408a(boolean z) {
        System.out.print(z);
    }

    @cey
    /* renamed from: a */
    private static final void m5418a(float f) {
        System.out.print(f);
    }

    @cey
    /* renamed from: a */
    private static final void m5419a(double d) {
        System.out.print(d);
    }

    @cey
    /* renamed from: a */
    private static final void m5407a(char[] cArr) {
        System.out.print(cArr);
    }

    @cey
    /* renamed from: b */
    private static final void m5399b(Object obj) {
        System.out.println(obj);
    }

    @cey
    /* renamed from: b */
    private static final void m5401b(int i) {
        System.out.println(i);
    }

    @cey
    /* renamed from: b */
    private static final void m5400b(long j) {
        System.out.println(j);
    }

    @cey
    /* renamed from: b */
    private static final void m5405b(byte b) {
        System.out.println(Byte.valueOf(b));
    }

    @cey
    /* renamed from: b */
    private static final void m5398b(short s) {
        System.out.println(Short.valueOf(s));
    }

    @cey
    /* renamed from: b */
    private static final void m5404b(char c) {
        System.out.println(c);
    }

    @cey
    /* renamed from: b */
    private static final void m5397b(boolean z) {
        System.out.println(z);
    }

    @cey
    /* renamed from: b */
    private static final void m5402b(float f) {
        System.out.println(f);
    }

    @cey
    /* renamed from: b */
    private static final void m5403b(double d) {
        System.out.println(d);
    }

    @cey
    /* renamed from: b */
    private static final void m5396b(char[] cArr) {
        System.out.println(cArr);
    }

    @cey
    /* renamed from: b */
    private static final void m5406b() {
        System.out.println();
    }

    /* compiled from: Console.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, m8860e = {"<anonymous>", "Ljava/nio/charset/CharsetDecoder;", "kotlin.jvm.PlatformType", "invoke"})
    /* renamed from: z1.cfo$a */
    /* loaded from: classes3.dex */
    static final class C4947a extends Lambda implements chc<CharsetDecoder> {
        public static final C4947a INSTANCE = new C4947a();

        C4947a() {
            super(0);
        }

        @Override // p110z1.chc
        public final CharsetDecoder invoke() {
            return Charset.defaultCharset().newDecoder();
        }
    }

    @dbs
    /* renamed from: a */
    public static final String m5422a() {
        InputStream inputStream = System.in;
        cji.m5175b(inputStream, "System.`in`");
        return m5415a(inputStream, m5395c());
    }

    @dbs
    /* renamed from: a */
    public static final String m5415a(@NotNull InputStream inputStream, @NotNull CharsetDecoder charsetDecoder) {
        cji.m5162f(inputStream, "inputStream");
        cji.m5162f(charsetDecoder, "decoder");
        if (charsetDecoder.maxCharsPerByte() <= ((float) 1)) {
            ByteBuffer allocate = ByteBuffer.allocate(32);
            CharBuffer allocate2 = CharBuffer.allocate(4);
            StringBuilder sb = new StringBuilder();
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            do {
                allocate.put((byte) read);
                cji.m5175b(allocate, "byteBuffer");
                cji.m5175b(allocate2, "charBuffer");
                if (m5410a(charsetDecoder, allocate, allocate2, false)) {
                    if (m5412a(allocate2)) {
                        break;
                    } else if (allocate2.remaining() < 2) {
                        m5411a(allocate2, sb);
                    }
                }
                read = inputStream.read();
            } while (read != -1);
            m5410a(charsetDecoder, allocate, allocate2, true);
            charsetDecoder.reset();
            int position = allocate2.position();
            if (position > 0 && allocate2.get(position - 1) == '\n' && position - 1 > 0 && allocate2.get(position - 1) == '\r') {
                position--;
            }
            allocate2.flip();
            for (int i = 0; i < position; i++) {
                sb.append(allocate2.get());
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Encodings with multiple chars per byte are not supported".toString());
    }

    /* renamed from: a */
    private static final boolean m5410a(@NotNull CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer, boolean z) {
        int position = charBuffer.position();
        byteBuffer.flip();
        CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, z);
        if (decode.isError()) {
            decode.throwException();
        }
        boolean z2 = charBuffer.position() > position;
        if (z2) {
            byteBuffer.clear();
        } else {
            m5413a((Buffer) byteBuffer);
        }
        return z2;
    }

    /* renamed from: a */
    private static final boolean m5412a(@NotNull CharBuffer charBuffer) {
        int position = charBuffer.position();
        return position > 0 && charBuffer.get(position - 1) == '\n';
    }

    /* renamed from: a */
    private static final void m5413a(@NotNull Buffer buffer) {
        buffer.position(buffer.limit());
        buffer.limit(buffer.capacity());
    }

    /* renamed from: a */
    private static final void m5411a(@NotNull CharBuffer charBuffer, StringBuilder sb) {
        charBuffer.flip();
        int limit = charBuffer.limit() - 1;
        for (int i = 0; i < limit; i++) {
            sb.append(charBuffer.get());
        }
        charBuffer.compact();
    }
}
