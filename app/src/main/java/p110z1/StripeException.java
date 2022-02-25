package p110z1;

/* renamed from: z1.arf */
/* loaded from: classes3.dex */
public abstract class StripeException extends Exception {
    protected static final long serialVersionUID = 1;
    private String requestId;
    private Integer statusCode;

    public StripeException(String str, String str2, Integer num) {
        super(str, null);
        this.requestId = str2;
        this.statusCode = num;
    }

    public StripeException(String str, String str2, Integer num, Throwable th) {
        super(str, th);
        this.statusCode = num;
        this.requestId = str2;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str = "";
        if (this.requestId != null) {
            str = "; request-id: " + this.requestId;
        }
        return super.toString() + str;
    }
}
