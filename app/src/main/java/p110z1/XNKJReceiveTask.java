package p110z1;

import com.blankj.utilcode.util.LogUtils;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;
import p110z1.aph;

/* renamed from: z1.aqm */
/* loaded from: classes3.dex */
public class XNKJReceiveTask extends aph.AbstractRunnableC3836c {

    /* renamed from: a */
    private InputStream f17367a;

    /* renamed from: b */
    private LinkedBlockingQueue<Integer> f17368b = new LinkedBlockingQueue<>();

    /* renamed from: c */
    private boolean f17369c;

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11548a(Throwable th) {
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: b */
    public void mo11547b() {
    }

    public XNKJReceiveTask(InputStream inputStream) {
        this.f17367a = inputStream;
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public Object mo11551a() throws Throwable {
        while (!this.f17369c) {
            int intValue = this.f17368b.take().intValue();
            LogUtils.m23742b("doInBackground", "infoInt=" + intValue);
            if (-1 == intValue) {
                return null;
            }
            m11553b(intValue);
        }
        return null;
    }

    /* renamed from: d */
    public boolean m11552d() {
        return this.f17369c;
    }

    /* renamed from: a */
    public void m11554a(boolean z) {
        this.f17369c = z;
    }

    /* renamed from: b */
    private void m11553b(int i) {
        LogUtils.m23734c("handlerInfo", "infoInt=" + i);
        FloatXnkjManager.INSTANCE.handlerInfo(i);
    }

    /* renamed from: a */
    public void m11556a(int i) {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = this.f17368b;
        if (linkedBlockingQueue != null) {
            try {
                linkedBlockingQueue.put(Integer.valueOf(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11549a(Object obj) {
        LogUtils.m23734c("newEngin", "XNKJReceiveTask 退出");
    }

    /* renamed from: a */
    public static boolean m11555a(XNKJReceiveTask aqmVar) {
        if (aqmVar != null) {
            return aqmVar.f17369c;
        }
        return true;
    }
}
