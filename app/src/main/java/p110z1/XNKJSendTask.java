package p110z1;

import com.blankj.utilcode.util.LogUtils;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;
import p110z1.aph;

/* renamed from: z1.aqn */
/* loaded from: classes3.dex */
public class XNKJSendTask extends aph.AbstractRunnableC3836c {

    /* renamed from: b */
    private OutputStream f17371b;

    /* renamed from: c */
    private BufferedWriter f17372c;

    /* renamed from: a */
    private LinkedBlockingQueue<Integer> f17370a = new LinkedBlockingQueue<>();

    /* renamed from: d */
    private boolean f17373d = true;

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11548a(Throwable th) {
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: b */
    public void mo11547b() {
    }

    public XNKJSendTask(OutputStream outputStream) {
        this.f17371b = outputStream;
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public Object mo11551a() throws Throwable {
        int intValue;
        while (this.f17373d && -1 != (intValue = this.f17370a.take().intValue())) {
            m11546b(intValue);
        }
        return null;
    }

    /* renamed from: b */
    private void m11546b(int i) {
        LogUtils.m23734c("newEngin", "handlerData = " + i);
        try {
            new DataOutputStream(this.f17371b).writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m11550a(int i) {
        try {
            LogUtils.m23734c("newEngin", "sendData = " + i);
            this.f17370a.put(Integer.valueOf(i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // p110z1.aph.AbstractRunnableC3836c
    /* renamed from: a */
    public void mo11549a(Object obj) {
        LogUtils.m23734c("newEngin", "XNKJSendTask 退出");
    }
}
