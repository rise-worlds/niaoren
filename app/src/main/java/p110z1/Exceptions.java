package p110z1;

/* renamed from: z1.atz */
/* loaded from: classes3.dex */
public final class Exceptions {
    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static RuntimeException m9984a(@AbstractC3889atm Throwable th) {
        throw ExceptionHelper.m9432a(th);
    }

    /* renamed from: b */
    public static void m9983b(@AbstractC3889atm Throwable th) {
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        } else if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        } else if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }
}
