package p110z1;

import android.net.LocalSocket;
import com.blankj.utilcode.util.LogUtils;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import p110z1.aph;

/* renamed from: z1.aql */
/* loaded from: classes3.dex */
public class XNKJLocalSocketTask extends aph.AbstractRunnableC3836c {

    /* renamed from: a */
    private LocalSocket f17363a;

    /* renamed from: b */
    private XNKJReceiveTask f17364b;

    /* renamed from: c */
    private XNKJSendTask f17365c;

    /* renamed from: d */
    private boolean f17366d = false;

    /* renamed from: a */
    private void m11561a(String str) {
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11549a(Object obj) {
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: b */
    public void mo11547b() {
    }

    public XNKJLocalSocketTask(LocalSocket localSocket, OutputStream outputStream, InputStream inputStream) {
        this.f17363a = localSocket;
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public Object mo11551a() throws Throwable {
        this.f17364b = new XNKJReceiveTask(this.f17363a.getInputStream());
        this.f17365c = new XNKJSendTask(this.f17363a.getOutputStream());
        aph.m11745c(this.f17364b);
        aph.m11745c(this.f17365c);
        DataInputStream dataInputStream = new DataInputStream(this.f17363a.getInputStream());
        while (true) {
            LogUtils.m23734c("newEngin", "readInt");
            int readInt = dataInputStream.readInt();
            LogUtils.m23734c("newEngin", "readInt ： " + readInt);
            m11559b(readInt);
        }
    }

    /* renamed from: b */
    private void m11559b(int i) {
        XNKJReceiveTask aqmVar = this.f17364b;
        if (aqmVar != null && !aqmVar.m11552d()) {
            this.f17364b.m11556a(i);
        }
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11548a(Throwable th) {
        LogUtils.m23734c("newEngin", "readInt ：exception ");
        m11558b(this);
    }

    /* renamed from: d */
    private void m11557d() {
        this.f17364b.m11556a(-1);
        this.f17365c.m11550a(-1);
        this.f17365c = null;
        this.f17364b = null;
        this.f17366d = true;
    }

    /* renamed from: a */
    public boolean m11562a(int i) {
        XNKJSendTask aqnVar;
        if (m11560a(this) || (aqnVar = this.f17365c) == null) {
            return false;
        }
        aqnVar.m11550a(i);
        return true;
    }

    /* renamed from: a */
    public static boolean m11560a(XNKJLocalSocketTask aqlVar) {
        LogUtils.m23734c("newEngin", "开始判断连接情况");
        if (aqlVar != null) {
            return aqlVar.f17366d;
        }
        return false;
    }

    /* renamed from: b */
    public static void m11558b(XNKJLocalSocketTask aqlVar) {
        aqlVar.m11557d();
    }
}
