package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SequenceBuilder.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000R\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aN\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2/\b\t\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0011H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aN\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014\"\u0004\b\u0000\u0010\n2/\b\t\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0011H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aM\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2/\b\u0001\u0010\u0017\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0011H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aM\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014\"\u0004\b\u0000\u0010\n2/\b\u0001\u0010\u0017\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0011H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0004\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0006\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0007\u001a\u00060\u0001j\u0002`\u0002X\u0082T¢\u0006\u0002\n\u0000*V\b\u0007\u0010\u0019\u001a\u0004\b\u0000\u0010\n\"\b\u0012\u0004\u0012\u0002H\n0\r2\b\u0012\u0004\u0012\u0002H\n0\rB6\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u001c\b\u001d\u0012\u0018\b\u000bB\u0014\b\u001e\u0012\u0006\b\u001f\u0012\u0002\b\f\u0012\b\b \u0012\u0004\b\b(!\u0012\n\b\"\u0012\u0006\b\n0#8$*\f\b\u0002\u0010%\"\u00020\u00012\u00020\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, m8860e = {"State_Done", "", "Lkotlin/sequences/State;", "State_Failed", "State_ManyNotReady", "State_ManyReady", "State_NotReady", "State_Ready", "buildIterator", "", TessBaseAPI.f9204e, "builderAction", "Lkotlin/Function2;", "Lkotlin/sequences/SequenceScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "buildSequence", "Lkotlin/sequences/Sequence;", "(Lkotlin/jvm/functions/Function2;)Lkotlin/sequences/Sequence;", "iterator", "block", "sequence", "SequenceBuilder", "Lkotlin/Deprecated;", "message", "Use SequenceScope class instead.", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "SequenceScope<T>", "level", "Lkotlin/DeprecationLevel;", "ERROR", "State", "kotlin-stdlib"}, m8859f = "kotlin/sequences/SequencesKt", m8857h = 1)
/* renamed from: z1.cof */
/* loaded from: classes3.dex */
public class cof {

    /* renamed from: a */
    private static final int f20941a = 0;

    /* renamed from: b */
    private static final int f20942b = 1;

    /* renamed from: c */
    private static final int f20943c = 2;

    /* renamed from: d */
    private static final int f20944d = 3;

    /* renamed from: e */
    private static final int f20945e = 4;

    /* renamed from: f */
    private static final int f20946f = 5;

    @Annotations(m8902a = "Use SequenceScope class instead.", m8901b = @bwu(m8768a = "SequenceScope<T>", m8767b = {}), m8900c = bvk.ERROR)
    /* renamed from: a */
    public static /* synthetic */ void m4454a() {
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004¸\u0006\u0000"}, m8860e = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.cof$a */
    /* loaded from: classes3.dex */
    public static final class C5022a implements Sequence<T> {

        /* renamed from: a */
        final /* synthetic */ cho f20947a;

        public C5022a(cho choVar) {
            this.f20947a = choVar;
        }

        @Override // p110z1.Sequence
        @NotNull
        /* renamed from: a */
        public Iterator<T> mo3707a() {
            return coe.m4452b(this.f20947a);
        }
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004¸\u0006\u0000"}, m8860e = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.cof$b */
    /* loaded from: classes3.dex */
    public static final class C5023b implements Sequence<T> {

        /* renamed from: a */
        final /* synthetic */ cho f20948a;

        public C5023b(cho choVar) {
            this.f20948a = choVar;
        }

        @Override // p110z1.Sequence
        @NotNull
        /* renamed from: a */
        public Iterator<T> mo3707a() {
            return coe.m4452b(this.f20948a);
        }
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4453a(@Inference @NotNull cho<? super cod<? super T>, ? super Continuation<? super Unit>, ? extends Object> choVar) {
        cji.m5162f(choVar, "block");
        return new C5023b(choVar);
    }

    @bwy(m8750a = "1.3")
    @Annotations(m8902a = "Use 'sequence { }' function instead.", m8901b = @bwu(m8768a = "sequence(builderAction)", m8767b = {}), m8900c = bvk.ERROR)
    @cey
    /* renamed from: c */
    private static final <T> Sequence<T> m4451c(@Inference cho<? super cod<? super T>, ? super Continuation<? super Unit>, ? extends Object> choVar) {
        return new C5022a(choVar);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: b */
    public static final <T> Iterator<T> m4452b(@Inference @NotNull cho<? super cod<? super T>, ? super Continuation<? super Unit>, ? extends Object> choVar) {
        cji.m5162f(choVar, "block");
        coc cocVar = new coc();
        cocVar.m4461a(cdz.m5532a(choVar, cocVar, cocVar));
        return cocVar;
    }

    @bwy(m8750a = "1.3")
    @Annotations(m8902a = "Use 'iterator { }' function instead.", m8901b = @bwu(m8768a = "iterator(builderAction)", m8767b = {}), m8900c = bvk.ERROR)
    @cey
    /* renamed from: d */
    private static final <T> Iterator<T> m4450d(@Inference cho<? super cod<? super T>, ? super Continuation<? super Unit>, ? extends Object> choVar) {
        return coe.m4452b(choVar);
    }
}
