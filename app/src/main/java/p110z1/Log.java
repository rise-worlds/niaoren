package p110z1;

/* renamed from: z1.uh */
/* loaded from: classes3.dex */
public interface Log {
    /* renamed from: a */
    void mo622a(EnumC5569a aVar, String str);

    /* renamed from: a */
    void mo621a(EnumC5569a aVar, String str, Throwable th);

    /* renamed from: a */
    boolean mo623a(EnumC5569a aVar);

    /* compiled from: Log.java */
    /* renamed from: z1.uh$a */
    /* loaded from: classes3.dex */
    public enum EnumC5569a {
        TRACE(1),
        DEBUG(2),
        INFO(3),
        WARNING(4),
        ERROR(5),
        FATAL(6);
        
        private int level;

        EnumC5569a(int i) {
            this.level = i;
        }

        public boolean isEnabled(EnumC5569a aVar) {
            return this.level <= aVar.level;
        }
    }
}
