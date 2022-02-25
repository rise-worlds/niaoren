package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: measureTime.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0018\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u000e\u001a\u00020\u0005HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\bJ-\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m8860e = {"Lkotlin/time/TimedValue;", TessBaseAPI.f9204e, "", SizeSelector.SIZE_KEY, "duration", "Lkotlin/time/Duration;", "(Ljava/lang/Object;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDuration", "()D", "D", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "copy-RFiDyg4", "(Ljava/lang/Object;D)Lkotlin/time/TimedValue;", "equals", "", "other", "hashCode", "", "toString", "", "kotlin-stdlib"})
@ExperimentalTime
/* renamed from: z1.cqs */
/* loaded from: classes3.dex */
public final class cqs<T> {

    /* renamed from: a */
    private final T f21096a;

    /* renamed from: b */
    private final double f21097b;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static /* synthetic */ cqs m3534a(cqs cqsVar, Object obj, double d, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = cqsVar.f21096a;
        }
        if ((i & 2) != 0) {
            d = cqsVar.f21097b;
        }
        return cqsVar.m3535a(obj, d);
    }

    @NotNull
    /* renamed from: a */
    public final cqs<T> m3535a(T t, double d) {
        return new cqs<>(t, d);
    }

    /* renamed from: c */
    public final T m3532c() {
        return this.f21096a;
    }

    /* renamed from: d */
    public final double m3531d() {
        return this.f21097b;
    }

    public boolean equals(@dbs Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cqs)) {
            return false;
        }
        cqs cqsVar = (cqs) obj;
        return cji.m5184a(this.f21096a, cqsVar.f21096a) && Double.compare(this.f21097b, cqsVar.f21097b) == 0;
    }

    public int hashCode() {
        T t = this.f21096a;
        int hashCode = t != null ? t.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.f21097b);
        return (hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    @NotNull
    public String toString() {
        return "TimedValue(value=" + this.f21096a + ", duration=" + Duration.m3608u(this.f21097b) + ")";
    }

    private cqs(T t, double d) {
        this.f21096a = t;
        this.f21097b = d;
    }

    public /* synthetic */ cqs(Object obj, double d, DefaultConstructorMarker civVar) {
        this(obj, d);
    }

    /* renamed from: a */
    public final T m3536a() {
        return this.f21096a;
    }

    /* renamed from: b */
    public final double m3533b() {
        return this.f21097b;
    }
}
