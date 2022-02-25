package p110z1;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/* renamed from: z1.cip */
/* loaded from: classes3.dex */
public abstract class CallableReference implements Serializable, KCallable {
    @bwy(m8750a = "1.1")
    public static final Object NO_RECEIVER = C4962a.INSTANCE;
    @bwy(m8750a = "1.1")
    protected final Object receiver;
    private transient KCallable reflected;

    protected abstract KCallable computeReflected();

    /* compiled from: CallableReference.java */
    @bwy(m8750a = "1.2")
    /* renamed from: z1.cip$a */
    /* loaded from: classes3.dex */
    private static class C4962a implements Serializable {
        private static final C4962a INSTANCE = new C4962a();

        private C4962a() {
        }

        private Object readResolve() throws ObjectStreamException {
            return INSTANCE;
        }
    }

    public CallableReference() {
        this(NO_RECEIVER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @bwy(m8750a = "1.1")
    public CallableReference(Object obj) {
        this.receiver = obj;
    }

    @bwy(m8750a = "1.1")
    public Object getBoundReceiver() {
        return this.receiver;
    }

    @bwy(m8750a = "1.1")
    public KCallable compute() {
        KCallable cmvVar = this.reflected;
        if (cmvVar != null) {
            return cmvVar;
        }
        KCallable computeReflected = computeReflected();
        this.reflected = computeReflected;
        return computeReflected;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @bwy(m8750a = "1.1")
    public KCallable getReflected() {
        KCallable compute = compute();
        if (compute != this) {
            return compute;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    public KDeclarationContainer getOwner() {
        throw new AbstractMethodError();
    }

    @Override // p110z1.KCallable
    public String getName() {
        throw new AbstractMethodError();
    }

    public String getSignature() {
        throw new AbstractMethodError();
    }

    @Override // p110z1.KCallable
    public List<KParameter> getParameters() {
        return getReflected().getParameters();
    }

    @Override // p110z1.KCallable
    public KType getReturnType() {
        return getReflected().getReturnType();
    }

    @Override // p110z1.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return getReflected().getAnnotations();
    }

    @Override // p110z1.KCallable
    @bwy(m8750a = "1.1")
    public List<KTypeParameter> getTypeParameters() {
        return getReflected().getTypeParameters();
    }

    @Override // p110z1.KCallable
    public Object call(Object... objArr) {
        return getReflected().call(objArr);
    }

    @Override // p110z1.KCallable
    public Object callBy(Map map) {
        return getReflected().callBy(map);
    }

    @Override // p110z1.KCallable
    @bwy(m8750a = "1.1")
    public KVisibility getVisibility() {
        return getReflected().getVisibility();
    }

    @Override // p110z1.KCallable
    @bwy(m8750a = "1.1")
    public boolean isFinal() {
        return getReflected().isFinal();
    }

    @Override // p110z1.KCallable
    @bwy(m8750a = "1.1")
    public boolean isOpen() {
        return getReflected().isOpen();
    }

    @Override // p110z1.KCallable
    @bwy(m8750a = "1.1")
    public boolean isAbstract() {
        return getReflected().isAbstract();
    }

    @Override // p110z1.KCallable
    @bwy(m8750a = "1.3")
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }
}
