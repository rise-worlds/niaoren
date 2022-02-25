package p110z1;

import org.apache.tools.ant.taskdefs.optional.clearcase.ClearCase;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m8860e = {"synchronized", "R", ClearCase.COMMAND_LOCK, "", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}, m8859f = "kotlin/StandardKt", m8857h = 1)
/* renamed from: z1.bxb */
/* loaded from: classes3.dex */
class Synchronized extends bxa {
    @cey
    /* renamed from: a */
    private static final <R> R m8734a(Object obj, chc<? extends R> chcVar) {
        R r;
        synchronized (obj) {
            try {
                r = (R) chcVar.invoke();
                InlineMarker.m5201b(1);
            } catch (Throwable th) {
                InlineMarker.m5201b(1);
                InlineMarker.m5200c(1);
                throw th;
            }
        }
        InlineMarker.m5200c(1);
        return r;
    }
}
