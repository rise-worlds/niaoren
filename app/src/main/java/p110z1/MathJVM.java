package p110z1;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m8860e = {"Lkotlin/math/Constants;", "", "()V", "LN2", "", "epsilon", "taylor_2_bound", "taylor_n_bound", "upper_taylor_2_bound", "upper_taylor_n_bound", "kotlin-stdlib"})
/* renamed from: z1.ckz */
/* loaded from: classes3.dex */
final class MathJVM {
    @JvmPlatformAnnotations

    /* renamed from: e */
    public static final double f20790e;
    @JvmPlatformAnnotations

    /* renamed from: f */
    public static final double f20791f;

    /* renamed from: g */
    public static final MathJVM f20792g = new MathJVM();
    @JvmPlatformAnnotations

    /* renamed from: a */
    public static final double f20786a = Math.log(2.0d);
    @JvmPlatformAnnotations

    /* renamed from: b */
    public static final double f20787b = Math.ulp(1.0d);
    @JvmPlatformAnnotations

    /* renamed from: c */
    public static final double f20788c = Math.sqrt(f20787b);
    @JvmPlatformAnnotations

    /* renamed from: d */
    public static final double f20789d = Math.sqrt(f20788c);

    static {
        double d = 1;
        f20790e = d / f20788c;
        f20791f = d / f20789d;
    }

    private MathJVM() {
    }
}
