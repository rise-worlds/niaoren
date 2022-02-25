package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.commons.logging.LogFactory;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aJ\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u001a0\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\fH\u0087\b¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, m8860e = {"thread", "Ljava/lang/Thread;", "start", "", "isDaemon", "contextClassLoader", "Ljava/lang/ClassLoader;", "name", "", LogFactory.PRIORITY_KEY, "", "block", "Lkotlin/Function0;", "", "getOrSet", TessBaseAPI.f9204e, "", "Ljava/lang/ThreadLocal;", "default", "(Ljava/lang/ThreadLocal;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"})
@cgo(m5270a = "ThreadsKt")
/* renamed from: z1.ccb */
/* loaded from: classes3.dex */
public final class Thread {

    /* compiled from: Thread.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m8860e = {"kotlin/concurrent/ThreadsKt$thread$thread$1", "Ljava/lang/Thread;", "run", "", "kotlin-stdlib"})
    /* renamed from: z1.ccb$a */
    /* loaded from: classes3.dex */
    public static final class C4901a extends Thread {

        /* renamed from: a */
        final /* synthetic */ chc f20547a;

        C4901a(chc chcVar) {
            this.f20547a = chcVar;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.f20547a.invoke();
        }
    }

    @NotNull
    /* renamed from: a */
    public static final Thread m5677a(boolean z, boolean z2, @dbs ClassLoader classLoader, @dbs String str, int i, @NotNull chc<Unit> chcVar) {
        cji.m5162f(chcVar, "block");
        C4901a aVar = new C4901a(chcVar);
        if (z2) {
            aVar.setDaemon(true);
        }
        if (i > 0) {
            aVar.setPriority(i);
        }
        if (str != null) {
            aVar.setName(str);
        }
        if (classLoader != null) {
            aVar.setContextClassLoader(classLoader);
        }
        if (z) {
            aVar.start();
        }
        return aVar;
    }

    @cey
    /* renamed from: a */
    private static final <T> T m5678a(@NotNull ThreadLocal<T> threadLocal, chc<? extends T> chcVar) {
        T t = threadLocal.get();
        if (t != null) {
            return t;
        }
        T t2 = (T) chcVar.invoke();
        threadLocal.set(t2);
        return t2;
    }
}
