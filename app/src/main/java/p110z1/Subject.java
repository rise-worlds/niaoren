package p110z1;

/* renamed from: z1.buz */
/* loaded from: classes3.dex */
public abstract class Subject<T> extends Observable<T> implements Observer<T> {
    /* renamed from: R */
    public abstract boolean mo8947R();

    @atn
    /* renamed from: S */
    public abstract Throwable mo8946S();

    /* renamed from: b */
    public abstract boolean mo8939b();

    /* renamed from: c */
    public abstract boolean mo8936c();

    @AbstractC3889atm
    /* renamed from: aa */
    public final Subject<T> m8952aa() {
        return this instanceof SerializedSubject ? this : new SerializedSubject(this);
    }
}
