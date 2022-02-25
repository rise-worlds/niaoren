package p110z1;

import java.io.Serializable;

/* renamed from: z1.btb */
/* loaded from: classes3.dex */
public enum NotificationLite {
    COMPLETE;

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T getValue(Object obj) {
        return obj;
    }

    public static <T> Object next(T t) {
        return t;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }

    /* compiled from: NotificationLite.java */
    /* renamed from: z1.btb$b */
    /* loaded from: classes3.dex */
    static final class C4748b implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;

        /* renamed from: e */
        final Throwable f20096e;

        C4748b(Throwable th) {
            this.f20096e = th;
        }

        public String toString() {
            return "NotificationLite.Error[" + this.f20096e + "]";
        }

        public int hashCode() {
            return this.f20096e.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof C4748b) {
                return ObjectHelper.m9874a(this.f20096e, ((C4748b) obj).f20096e);
            }
            return false;
        }
    }

    /* compiled from: NotificationLite.java */
    /* renamed from: z1.btb$c */
    /* loaded from: classes3.dex */
    static final class C4749c implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;
        final dby upstream;

        C4749c(dby dbyVar) {
            this.upstream = dbyVar;
        }

        public String toString() {
            return "NotificationLite.Subscription[" + this.upstream + "]";
        }
    }

    /* compiled from: NotificationLite.java */
    /* renamed from: z1.btb$a */
    /* loaded from: classes3.dex */
    static final class C4747a implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;
        final Disposable upstream;

        C4747a(Disposable atrVar) {
            this.upstream = atrVar;
        }

        public String toString() {
            return "NotificationLite.Disposable[" + this.upstream + "]";
        }
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object error(Throwable th) {
        return new C4748b(th);
    }

    public static Object subscription(dby dbyVar) {
        return new C4749c(dbyVar);
    }

    public static Object disposable(Disposable atrVar) {
        return new C4747a(atrVar);
    }

    public static boolean isComplete(Object obj) {
        return obj == COMPLETE;
    }

    public static boolean isError(Object obj) {
        return obj instanceof C4748b;
    }

    public static boolean isSubscription(Object obj) {
        return obj instanceof C4749c;
    }

    public static boolean isDisposable(Object obj) {
        return obj instanceof C4747a;
    }

    public static Throwable getError(Object obj) {
        return ((C4748b) obj).f20096e;
    }

    public static dby getSubscription(Object obj) {
        return ((C4749c) obj).upstream;
    }

    public static Disposable getDisposable(Object obj) {
        return ((C4747a) obj).upstream;
    }

    public static <T> boolean accept(Object obj, Subscriber<? super T> dbxVar) {
        if (obj == COMPLETE) {
            dbxVar.onComplete();
            return true;
        } else if (obj instanceof C4748b) {
            dbxVar.onError(((C4748b) obj).f20096e);
            return true;
        } else {
            dbxVar.onNext(obj);
            return false;
        }
    }

    public static <T> boolean accept(Object obj, Observer<? super T> assVar) {
        if (obj == COMPLETE) {
            assVar.onComplete();
            return true;
        } else if (obj instanceof C4748b) {
            assVar.onError(((C4748b) obj).f20096e);
            return true;
        } else {
            assVar.onNext(obj);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object obj, Subscriber<? super T> dbxVar) {
        if (obj == COMPLETE) {
            dbxVar.onComplete();
            return true;
        } else if (obj instanceof C4748b) {
            dbxVar.onError(((C4748b) obj).f20096e);
            return true;
        } else if (obj instanceof C4749c) {
            dbxVar.onSubscribe(((C4749c) obj).upstream);
            return false;
        } else {
            dbxVar.onNext(obj);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object obj, Observer<? super T> assVar) {
        if (obj == COMPLETE) {
            assVar.onComplete();
            return true;
        } else if (obj instanceof C4748b) {
            assVar.onError(((C4748b) obj).f20096e);
            return true;
        } else if (obj instanceof C4747a) {
            assVar.onSubscribe(((C4747a) obj).upstream);
            return false;
        } else {
            assVar.onNext(obj);
            return false;
        }
    }
}
