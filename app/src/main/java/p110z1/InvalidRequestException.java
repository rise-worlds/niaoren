package p110z1;

/* renamed from: z1.arc */
/* loaded from: classes3.dex */
public class InvalidRequestException extends StripeException {
    private final String param;

    public InvalidRequestException(String str, String str2, String str3, Integer num, Throwable th) {
        super(str, str3, num, th);
        this.param = str2;
    }

    public String getParam() {
        return this.param;
    }
}
