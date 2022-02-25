package javax.mail;

/* loaded from: classes2.dex */
public final class PasswordAuthentication {
    private String password;
    private String userName;

    public PasswordAuthentication(String str, String str2) {
        this.userName = str;
        this.password = str2;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final String getPassword() {
        return this.password;
    }
}
