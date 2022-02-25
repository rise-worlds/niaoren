package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: SequenceBuilder.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aM\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2/\b\u0001\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0011H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aM\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014\"\u0004\b\u0000\u0010\n2/\b\u0001\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0011H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0004\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0006\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0007\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000*\f\b\u0002\u0010\u0016\"\u00020\u00012\u00020\u0001\u0082\u0002\u0004\n\u0002\b\t¨\u0006\u0017"}, m8860e = {"State_Done", "", "Lkotlin/coroutines/experimental/State;", "State_Failed", "State_ManyNotReady", "State_ManyReady", "State_NotReady", "State_Ready", "buildIterator", "", TessBaseAPI.f9204e, "builderAction", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/SequenceBuilder;", "Lkotlin/coroutines/experimental/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "buildSequence", "Lkotlin/sequences/Sequence;", "(Lkotlin/jvm/functions/Function2;)Lkotlin/sequences/Sequence;", "State", "kotlin-stdlib-coroutines"}, m8859f = "kotlin/coroutines/experimental/SequenceBuilderKt", m8857h = 1)
/* renamed from: z1.cdi */
/* loaded from: classes3.dex */
class cdi {

    /* renamed from: a */
    private static final int f20577a = 0;

    /* renamed from: b */
    private static final int f20578b = 1;

    /* renamed from: c */
    private static final int f20579c = 2;

    /* renamed from: d */
    private static final int f20580d = 3;

    /* renamed from: e */
    private static final int f20581e = 4;

    /* renamed from: f */
    private static final int f20582f = 5;

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004¸\u0006\u0000"}, m8860e = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.cdi$a */
    /* loaded from: classes3.dex */
    public static final class C4929a implements Sequence<T> {

        /* renamed from: a */
        final /* synthetic */ cho f20583a;

        public C4929a(cho choVar) {
            this.f20583a = choVar;
        }

        @Override // p110z1.Sequence
        @NotNull
        /* renamed from: a */
        public Iterator<T> mo3707a() {
            return cdh.m5584b(this.f20583a);
        }
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m5585a(@Inference @NotNull cho<? super SequenceBuilder<? super T>, ? super Coroutines<? super Unit>, ? extends Object> choVar) {
        cji.m5162f(choVar, "builderAction");
        return new C4929a(choVar);
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: b */
    public static final <T> Iterator<T> m5584b(@Inference @NotNull cho<? super SequenceBuilder<? super T>, ? super Coroutines<? super Unit>, ? extends Object> choVar) {
        cji.m5162f(choVar, "builderAction");
        cdg cdgVar = new cdg();
        cdgVar.m5588a(cdj.m5580a(choVar, cdgVar, cdgVar));
        return cdgVar;
    }
}
