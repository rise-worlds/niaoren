package p110z1;

import android.app.NotificationManager;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: z1.ahm */
/* loaded from: classes3.dex */
public abstract class BaseNotificationItem {

    /* renamed from: a */
    private int f15824a;

    /* renamed from: b */
    private int f15825b;

    /* renamed from: c */
    private int f15826c;

    /* renamed from: d */
    private String f15827d;

    /* renamed from: e */
    private String f15828e;

    /* renamed from: f */
    private int f15829f = 0;

    /* renamed from: g */
    private int f15830g = 0;

    /* renamed from: h */
    private NotificationManager f15831h;

    /* renamed from: a */
    public abstract void m13292a(boolean z, int i, boolean z2);

    public BaseNotificationItem(int i, String str, String str2) {
        this.f15824a = i;
        this.f15827d = str;
        this.f15828e = str2;
    }

    /* renamed from: a */
    public void m13293a(boolean z) {
        m13292a(m13278j(), m13280h(), z);
    }

    /* renamed from: a */
    public void m13295a(int i, int i2) {
        this.f15825b = i;
        this.f15826c = i2;
        m13293a(true);
    }

    /* renamed from: a */
    public void m13296a(int i) {
        this.f15829f = i;
    }

    /* renamed from: a */
    public void m13297a() {
        m13291b().cancel(this.f15824a);
    }

    /* renamed from: b */
    protected NotificationManager m13291b() {
        if (this.f15831h == null) {
            this.f15831h = (NotificationManager) FileDownloadHelper.m13229a().getSystemService(ServiceManagerNative.NOTIFICATION);
        }
        return this.f15831h;
    }

    /* renamed from: c */
    public int m13288c() {
        return this.f15824a;
    }

    /* renamed from: b */
    public void m13290b(int i) {
        this.f15824a = i;
    }

    /* renamed from: d */
    public int m13286d() {
        return this.f15825b;
    }

    /* renamed from: c */
    public void m13287c(int i) {
        this.f15825b = i;
    }

    /* renamed from: e */
    public int m13284e() {
        return this.f15826c;
    }

    /* renamed from: d */
    public void m13285d(int i) {
        this.f15826c = i;
    }

    /* renamed from: f */
    public String m13282f() {
        return this.f15827d;
    }

    /* renamed from: a */
    public void m13294a(String str) {
        this.f15827d = str;
    }

    /* renamed from: g */
    public String m13281g() {
        return this.f15828e;
    }

    /* renamed from: b */
    public void m13289b(String str) {
        this.f15828e = str;
    }

    /* renamed from: h */
    public int m13280h() {
        int i = this.f15829f;
        this.f15830g = i;
        return i;
    }

    /* renamed from: e */
    public void m13283e(int i) {
        this.f15829f = i;
    }

    /* renamed from: i */
    public int m13279i() {
        return this.f15830g;
    }

    /* renamed from: j */
    public boolean m13278j() {
        return this.f15830g != this.f15829f;
    }
}
