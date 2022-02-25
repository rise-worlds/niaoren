package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import p110z1.cda;

/* compiled from: ContinuationInterceptor.kt */
@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H&¨\u0006\u0007"}, m8860e = {"Lkotlin/coroutines/experimental/ContinuationInterceptor;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "interceptContinuation", "Lkotlin/coroutines/experimental/Continuation;", TessBaseAPI.f9204e, "continuation", "Key", "kotlin-stdlib-coroutines"})
/* renamed from: z1.ccz */
/* loaded from: classes3.dex */
public interface ccz extends cda.AbstractC4924b {

    /* renamed from: a */
    public static final C4920b f20561a = C4920b.f20562a;

    /* compiled from: ContinuationInterceptor.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.ccz$a */
    /* loaded from: classes3.dex */
    public static final class C4919a {
        /* renamed from: a */
        public static <R> R m5625a(ccz cczVar, R r, @NotNull cho<? super R, ? super cda.AbstractC4924b, ? extends R> choVar) {
            cji.m5162f(choVar, "operation");
            return (R) cda.AbstractC4924b.C4925a.m5610a(cczVar, r, choVar);
        }

        @dbs
        /* renamed from: a */
        public static <E extends cda.AbstractC4924b> E m5624a(ccz cczVar, @NotNull cda.AbstractC4926c<E> cVar) {
            cji.m5162f(cVar, "key");
            return (E) cda.AbstractC4924b.C4925a.m5609a(cczVar, cVar);
        }

        @NotNull
        /* renamed from: a */
        public static cda m5623a(ccz cczVar, @NotNull cda cdaVar) {
            cji.m5162f(cdaVar, "context");
            return cda.AbstractC4924b.C4925a.m5608a(cczVar, cdaVar);
        }

        @NotNull
        /* renamed from: b */
        public static cda m5622b(ccz cczVar, @NotNull cda.AbstractC4926c<?> cVar) {
            cji.m5162f(cVar, "key");
            return cda.AbstractC4924b.C4925a.m5607b(cczVar, cVar);
        }
    }

    @NotNull
    /* renamed from: a */
    <T> Coroutines<T> mo5550a(@NotNull Coroutines<? super T> ccyVar);

    /* compiled from: ContinuationInterceptor.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m8860e = {"Lkotlin/coroutines/experimental/ContinuationInterceptor$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "()V", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.ccz$b */
    /* loaded from: classes3.dex */
    public static final class C4920b implements cda.AbstractC4926c<ccz> {

        /* renamed from: a */
        static final /* synthetic */ C4920b f20562a = new C4920b();

        private C4920b() {
        }
    }
}
