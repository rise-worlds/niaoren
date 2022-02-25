package p110z1;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import com.blankj.utilcode.util.LogUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import p110z1.aph;

/* renamed from: z1.aqk */
/* loaded from: classes3.dex */
public class FtXnkjSocketTask extends aph.AbstractRunnableC3836c {

    /* renamed from: a */
    private String f17355a;

    /* renamed from: b */
    private LocalServerSocket f17356b;

    /* renamed from: c */
    private XNKJLocalSocketTask f17357c;

    /* renamed from: d */
    private LocalSocket f17358d;

    /* renamed from: e */
    private AbstractC3865a f17359e;

    /* renamed from: f */
    private boolean f17360f = true;

    /* renamed from: g */
    private AtomicBoolean f17361g = new AtomicBoolean();

    /* renamed from: h */
    private int f17362h = 0;

    /* compiled from: FtXnkjSocketTask.java */
    /* renamed from: z1.aqk$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3865a {
        /* renamed from: a */
        void mo11564a();

        /* renamed from: b */
        void mo11563b();
    }

    /* renamed from: a */
    private void m11567a(String str) {
    }

    public FtXnkjSocketTask(String str, AbstractC3865a aVar) {
        this.f17355a = str;
        this.f17359e = aVar;
        this.f17361g.set(false);
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public Object mo11551a() throws Throwable {
        this.f17361g.set(true);
        if (this.f17356b == null) {
            this.f17356b = new LocalServerSocket(this.f17355a);
        }
        while (this.f17360f) {
            LogUtils.m23734c("newEngin", "等待客户端连接");
            this.f17358d = this.f17356b.accept();
            LogUtils.m23734c("newEngin", "客户端连接成功，准备通信");
            LocalSocket localSocket = this.f17358d;
            this.f17357c = new XNKJLocalSocketTask(localSocket, localSocket.getOutputStream(), this.f17358d.getInputStream());
            aph.m11745c(this.f17357c);
        }
        return null;
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: b */
    public void mo11547b() {
        this.f17360f = false;
        this.f17361g.set(false);
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11548a(Throwable th) {
        LogUtils.m23734c("newEngin", "出现异常：" + th.getMessage());
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11549a(Object obj) {
        LogUtils.m23734c("newEngin", "成功，是否需要对外通知");
    }

    /* renamed from: a */
    public boolean m11568a(int i) {
        if (this.f17357c == null || !m11565d()) {
            return false;
        }
        return this.f17357c.m11562a(i);
    }

    /* renamed from: d */
    public boolean m11565d() {
        XNKJLocalSocketTask aqlVar = this.f17357c;
        if (aqlVar != null) {
            return !XNKJLocalSocketTask.m11560a(aqlVar);
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m11566a(FtXnkjSocketTask aqkVar) {
        return aqkVar.m11565d();
    }
}
