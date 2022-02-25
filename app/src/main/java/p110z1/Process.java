package p110z1;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b¨\u0006\u0004"}, m8860e = {"exitProcess", "", "status", "", "kotlin-stdlib"})
@cgo(m5270a = "ProcessKt")
/* renamed from: z1.cor */
/* loaded from: classes3.dex */
public final class Process {
    @cey
    /* renamed from: a */
    private static final Void m4255a(int i) {
        System.exit(i);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
