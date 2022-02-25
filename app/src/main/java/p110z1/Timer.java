package p110z1;

import java.util.Date;
import java.util.TimerTask;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aJ\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001aL\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a\u001a\u0010\u0010\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001aJ\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001aL\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a$\u0010\u0011\u001a\u00020\f2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a0\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a8\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a0\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a8\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a8\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b\u001a8\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\b¨\u0006\u0016"}, m8860e = {"fixedRateTimer", "Ljava/util/Timer;", "name", "", "daemon", "", "startAt", "Ljava/util/Date;", "period", "", "action", "Lkotlin/Function1;", "Ljava/util/TimerTask;", "", "Lkotlin/ExtensionFunctionType;", "initialDelay", "timer", "timerTask", "schedule", "time", "delay", "scheduleAtFixedRate", "kotlin-stdlib"})
@cgo(m5270a = "TimersKt")
/* renamed from: z1.ccc */
/* loaded from: classes3.dex */
public final class Timer {
    @cey
    /* renamed from: a */
    private static final TimerTask m5669a(@NotNull java.util.Timer timer, long j, chd<? super TimerTask, Unit> chdVar) {
        C4902a aVar = new C4902a(chdVar);
        timer.schedule(aVar, j);
        return aVar;
    }

    @cey
    /* renamed from: a */
    private static final TimerTask m5667a(@NotNull java.util.Timer timer, Date date, chd<? super TimerTask, Unit> chdVar) {
        C4902a aVar = new C4902a(chdVar);
        timer.schedule(aVar, date);
        return aVar;
    }

    @cey
    /* renamed from: a */
    private static final TimerTask m5670a(@NotNull java.util.Timer timer, long j, long j2, chd<? super TimerTask, Unit> chdVar) {
        C4902a aVar = new C4902a(chdVar);
        timer.schedule(aVar, j, j2);
        return aVar;
    }

    @cey
    /* renamed from: a */
    private static final TimerTask m5668a(@NotNull java.util.Timer timer, Date date, long j, chd<? super TimerTask, Unit> chdVar) {
        C4902a aVar = new C4902a(chdVar);
        timer.schedule(aVar, date, j);
        return aVar;
    }

    @cey
    /* renamed from: b */
    private static final TimerTask m5661b(@NotNull java.util.Timer timer, long j, long j2, chd<? super TimerTask, Unit> chdVar) {
        C4902a aVar = new C4902a(chdVar);
        timer.scheduleAtFixedRate(aVar, j, j2);
        return aVar;
    }

    @cey
    /* renamed from: b */
    private static final TimerTask m5660b(@NotNull java.util.Timer timer, Date date, long j, chd<? super TimerTask, Unit> chdVar) {
        C4902a aVar = new C4902a(chdVar);
        timer.scheduleAtFixedRate(aVar, date, j);
        return aVar;
    }

    @bwt
    @NotNull
    /* renamed from: a */
    public static final java.util.Timer m5675a(@dbs String str, boolean z) {
        return str == null ? new java.util.Timer(z) : new java.util.Timer(str, z);
    }

    /* renamed from: a */
    static /* synthetic */ java.util.Timer m5673a(String str, boolean z, long j, long j2, chd chdVar, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        java.util.Timer a = m5675a(str, z);
        a.schedule(new C4902a(chdVar), j, j2);
        return a;
    }

    @cey
    /* renamed from: a */
    private static final java.util.Timer m5674a(String str, boolean z, long j, long j2, chd<? super TimerTask, Unit> chdVar) {
        java.util.Timer a = m5675a(str, z);
        a.schedule(new C4902a(chdVar), j, j2);
        return a;
    }

    /* renamed from: a */
    static /* synthetic */ java.util.Timer m5671a(String str, boolean z, Date date, long j, chd chdVar, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        java.util.Timer a = m5675a(str, z);
        a.schedule(new C4902a(chdVar), date, j);
        return a;
    }

    @cey
    /* renamed from: a */
    private static final java.util.Timer m5672a(String str, boolean z, Date date, long j, chd<? super TimerTask, Unit> chdVar) {
        java.util.Timer a = m5675a(str, z);
        a.schedule(new C4902a(chdVar), date, j);
        return a;
    }

    /* renamed from: b */
    static /* synthetic */ java.util.Timer m5664b(String str, boolean z, long j, long j2, chd chdVar, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        java.util.Timer a = m5675a(str, z);
        a.scheduleAtFixedRate(new C4902a(chdVar), j, j2);
        return a;
    }

    @cey
    /* renamed from: b */
    private static final java.util.Timer m5665b(String str, boolean z, long j, long j2, chd<? super TimerTask, Unit> chdVar) {
        java.util.Timer a = m5675a(str, z);
        a.scheduleAtFixedRate(new C4902a(chdVar), j, j2);
        return a;
    }

    /* renamed from: b */
    static /* synthetic */ java.util.Timer m5662b(String str, boolean z, Date date, long j, chd chdVar, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        java.util.Timer a = m5675a(str, z);
        a.scheduleAtFixedRate(new C4902a(chdVar), date, j);
        return a;
    }

    @cey
    /* renamed from: b */
    private static final java.util.Timer m5663b(String str, boolean z, Date date, long j, chd<? super TimerTask, Unit> chdVar) {
        java.util.Timer a = m5675a(str, z);
        a.scheduleAtFixedRate(new C4902a(chdVar), date, j);
        return a;
    }

    /* compiled from: Timer.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m8860e = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"})
    /* renamed from: z1.ccc$a */
    /* loaded from: classes3.dex */
    public static final class C4902a extends TimerTask {

        /* renamed from: a */
        final /* synthetic */ chd f20548a;

        public C4902a(chd chdVar) {
            this.f20548a = chdVar;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.f20548a.invoke(this);
        }
    }

    @cey
    /* renamed from: a */
    private static final TimerTask m5666a(chd<? super TimerTask, Unit> chdVar) {
        return new C4902a(chdVar);
    }
}
