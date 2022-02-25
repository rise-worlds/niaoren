package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.List;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;

/* compiled from: SlidingWindow.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000¨\u0006\u000f"}, m8860e = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", TessBaseAPI.f9204e, "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"})
/* renamed from: z1.cbd */
/* loaded from: classes3.dex */
public final class cbd {
    /* renamed from: a */
    public static final void m6333a(int i, int i2) {
        String str;
        if (!(i > 0 && i2 > 0)) {
            if (i != i2) {
                str = "Both size " + i + " and step " + i2 + " must be greater than zero.";
            } else {
                str = "size " + i + " must be greater than zero.";
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004¸\u0006\u0000"}, m8860e = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.cbd$b */
    /* loaded from: classes3.dex */
    public static final class C4877b implements Sequence<List<? extends T>> {

        /* renamed from: a */
        final /* synthetic */ Sequence f20509a;

        /* renamed from: b */
        final /* synthetic */ int f20510b;

        /* renamed from: c */
        final /* synthetic */ int f20511c;

        /* renamed from: d */
        final /* synthetic */ boolean f20512d;

        /* renamed from: e */
        final /* synthetic */ boolean f20513e;

        public C4877b(Sequence cobVar, int i, int i2, boolean z, boolean z2) {
            this.f20509a = cobVar;
            this.f20510b = i;
            this.f20511c = i2;
            this.f20512d = z;
            this.f20513e = z2;
        }

        @Override // p110z1.Sequence
        @NotNull
        /* renamed from: a */
        public Iterator<List<? extends T>> mo3707a() {
            return cbd.m6332a(this.f20509a.mo3707a(), this.f20510b, this.f20511c, this.f20512d, this.f20513e);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<List<T>> m6331a(@NotNull Sequence<? extends T> cobVar, int i, int i2, boolean z, boolean z2) {
        cji.m5162f(cobVar, "$this$windowedSequence");
        m6333a(i, i2);
        return new C4877b(cobVar, i, i2, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SlidingWindow.kt */
    @DebugMetadata(m5517b = "SlidingWindow.kt", m5516c = {33, 39, 46, 52, 55}, m5515d = {"$this$iterator", "gap", "buffer", MSVSSConstants.WRITABLE_SKIP, "e", "$this$iterator", "gap", "buffer", MSVSSConstants.WRITABLE_SKIP, "$this$iterator", "gap", "buffer", "e", "$this$iterator", "gap", "buffer", "$this$iterator", "gap", "buffer"}, m5514e = {"L$0", "I$0", "L$1", "I$1", "L$2", "L$0", "I$0", "L$1", "I$1", "L$0", "I$0", "L$1", "L$2", "L$0", "I$0", "L$1", "L$0", "I$0", "L$1"}, m5513f = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4}, m5512g = "invokeSuspend", m5511h = "kotlin.collections.SlidingWindowKt$windowedIterator$1")
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
    /* renamed from: z1.cbd$a */
    /* loaded from: classes3.dex */
    public static final class C4876a extends cem implements cho<cod<? super List<? extends T>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Iterator $iterator;
        final /* synthetic */ boolean $partialWindows;
        final /* synthetic */ boolean $reuseBuffer;
        final /* synthetic */ int $size;
        final /* synthetic */ int $step;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* renamed from: p$ */
        private cod f20508p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C4876a(int i, int i2, Iterator it, boolean z, boolean z2, Continuation ccpVar) {
            super(2, ccpVar);
            this.$step = i;
            this.$size = i2;
            this.$iterator = it;
            this.$reuseBuffer = z;
            this.$partialWindows = z2;
        }

        @Override // p110z1.ContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@dbs Object obj, @NotNull Continuation<?> ccpVar) {
            cji.m5162f(ccpVar, "completion");
            C4876a aVar = new C4876a(this.$step, this.$size, this.$iterator, this.$reuseBuffer, this.$partialWindows, ccpVar);
            aVar.f20508p$ = (cod) obj;
            return aVar;
        }

        @Override // p110z1.cho
        public final Object invoke(Object obj, Continuation<? super Unit> ccpVar) {
            return ((C4876a) create(obj, ccpVar)).invokeSuspend(Unit.f20418a);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00b8  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00e9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00fd  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x013a  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0145  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x016d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00ad -> B:25:0x00b0). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x012d -> B:51:0x0130). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0164 -> B:64:0x0167). Please submit an issue!!! */
        @Override // p110z1.ContinuationImpl
        @p110z1.dbs
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@p110z1.NotNull java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 410
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.cbd.C4876a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Iterator<List<T>> m6332a(@NotNull Iterator<? extends T> it, int i, int i2, boolean z, boolean z2) {
        cji.m5162f(it, "iterator");
        return !it.hasNext() ? bzw.f20476a : coe.m4452b(new C4876a(i2, i, it, z2, z, null));
    }
}
