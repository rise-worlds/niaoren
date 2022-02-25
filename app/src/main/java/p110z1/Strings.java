package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012:\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0096\u0002RB\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m8860e = {"Lkotlin/text/DelimitedRangesSequence;", "Lkotlin/sequences/Sequence;", "Lkotlin/ranges/IntRange;", "input", "", "startIndex", "", "limit", "getNextMatch", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "currentIndex", "Lkotlin/Pair;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/CharSequence;IILkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.cpa */
/* loaded from: classes3.dex */
public final class Strings implements Sequence<cme> {

    /* renamed from: a */
    private final CharSequence f21006a;

    /* renamed from: b */
    private final int f21007b;

    /* renamed from: c */
    private final int f21008c;

    /* renamed from: d */
    private final cho<CharSequence, Integer, Tuples<Integer, Integer>> f21009d;

    /* JADX WARN: Multi-variable type inference failed */
    public Strings(@NotNull CharSequence charSequence, int i, int i2, @NotNull cho<? super CharSequence, ? super Integer, Tuples<Integer, Integer>> choVar) {
        cji.m5162f(charSequence, "input");
        cji.m5162f(choVar, "getNextMatch");
        this.f21006a = charSequence;
        this.f21007b = i;
        this.f21008c = i2;
        this.f21009d = choVar;
    }

    /* compiled from: Strings.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000%\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\t\u0010\u0019\u001a\u00020\u001aH\u0096\u0002J\t\u0010\u001b\u001a\u00020\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u001a\u0010\u0014\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\b¨\u0006\u001c"}, m8860e = {"kotlin/text/DelimitedRangesSequence$iterator$1", "", "Lkotlin/ranges/IntRange;", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextItem", "getNextItem", "()Lkotlin/ranges/IntRange;", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "nextState", "getNextState", "setNextState", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"})
    /* renamed from: z1.cpa$a */
    /* loaded from: classes3.dex */
    public static final class C5068a implements Iterator<cme>, KMarkers {

        /* renamed from: b */
        private int f21011b = -1;

        /* renamed from: c */
        private int f21012c;

        /* renamed from: d */
        private int f21013d;
        @dbs

        /* renamed from: e */
        private cme f21014e;

        /* renamed from: f */
        private int f21015f;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C5068a() {
            this.f21012c = cmi.m4791a(Strings.this.f21007b, 0, Strings.this.f21006a.length());
            this.f21013d = this.f21012c;
        }

        /* renamed from: a */
        public final int m4215a() {
            return this.f21011b;
        }

        /* renamed from: a */
        public final void m4214a(int i) {
            this.f21011b = i;
        }

        /* renamed from: b */
        public final int m4212b() {
            return this.f21012c;
        }

        /* renamed from: b */
        public final void m4211b(int i) {
            this.f21012c = i;
        }

        /* renamed from: c */
        public final int m4210c() {
            return this.f21013d;
        }

        /* renamed from: c */
        public final void m4209c(int i) {
            this.f21013d = i;
        }

        /* renamed from: a */
        public final void m4213a(@dbs cme cmeVar) {
            this.f21014e = cmeVar;
        }

        @dbs
        /* renamed from: d */
        public final cme m4208d() {
            return this.f21014e;
        }

        /* renamed from: d */
        public final void m4207d(int i) {
            this.f21015f = i;
        }

        /* renamed from: e */
        public final int m4206e() {
            return this.f21015f;
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0025, code lost:
            if (r6.f21015f < r6.f21010a.f21008c) goto L_0x0027;
         */
        /* renamed from: g */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void m4204g() {
            /*
                r6 = this;
                int r0 = r6.f21013d
                r1 = 0
                if (r0 >= 0) goto L_0x000e
                r6.f21011b = r1
                r0 = 0
                z1.cme r0 = (p110z1.cme) r0
                r6.f21014e = r0
                goto L_0x00a4
            L_0x000e:
                z1.cpa r0 = p110z1.Strings.this
                int r0 = p110z1.Strings.m4219a(r0)
                r2 = -1
                r3 = 1
                if (r0 <= 0) goto L_0x0027
                int r0 = r6.f21015f
                int r0 = r0 + r3
                r6.f21015f = r0
                int r0 = r6.f21015f
                z1.cpa r4 = p110z1.Strings.this
                int r4 = p110z1.Strings.m4219a(r4)
                if (r0 >= r4) goto L_0x0035
            L_0x0027:
                int r0 = r6.f21013d
                z1.cpa r4 = p110z1.Strings.this
                java.lang.CharSequence r4 = p110z1.Strings.m4218b(r4)
                int r4 = r4.length()
                if (r0 <= r4) goto L_0x004b
            L_0x0035:
                int r0 = r6.f21012c
                z1.cme r1 = new z1.cme
                z1.cpa r4 = p110z1.Strings.this
                java.lang.CharSequence r4 = p110z1.Strings.m4218b(r4)
                int r4 = p110z1.cpl.m3850g(r4)
                r1.<init>(r0, r4)
                r6.f21014e = r1
                r6.f21013d = r2
                goto L_0x00a2
            L_0x004b:
                z1.cpa r0 = p110z1.Strings.this
                z1.cho r0 = p110z1.Strings.m4217c(r0)
                z1.cpa r4 = p110z1.Strings.this
                java.lang.CharSequence r4 = p110z1.Strings.m4218b(r4)
                int r5 = r6.f21013d
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r0 = r0.invoke(r4, r5)
                z1.bwo r0 = (p110z1.Tuples) r0
                if (r0 != 0) goto L_0x007b
                int r0 = r6.f21012c
                z1.cme r1 = new z1.cme
                z1.cpa r4 = p110z1.Strings.this
                java.lang.CharSequence r4 = p110z1.Strings.m4218b(r4)
                int r4 = p110z1.cpl.m3850g(r4)
                r1.<init>(r0, r4)
                r6.f21014e = r1
                r6.f21013d = r2
                goto L_0x00a2
            L_0x007b:
                java.lang.Object r2 = r0.component1()
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                java.lang.Object r0 = r0.component2()
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r4 = r6.f21012c
                z1.cme r4 = p110z1.cmi.m4739b(r4, r2)
                r6.f21014e = r4
                int r2 = r2 + r0
                r6.f21012c = r2
                int r2 = r6.f21012c
                if (r0 != 0) goto L_0x009f
                r1 = 1
            L_0x009f:
                int r2 = r2 + r1
                r6.f21013d = r2
            L_0x00a2:
                r6.f21011b = r3
            L_0x00a4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.Strings.C5068a.m4204g():void");
        }

        @NotNull
        /* renamed from: f */
        public cme next() {
            if (this.f21011b == -1) {
                m4204g();
            }
            if (this.f21011b != 0) {
                cme cmeVar = this.f21014e;
                if (cmeVar != null) {
                    this.f21014e = null;
                    this.f21011b = -1;
                    return cmeVar;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f21011b == -1) {
                m4204g();
            }
            return this.f21011b == 1;
        }
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<cme> mo3707a() {
        return new C5068a();
    }
}
