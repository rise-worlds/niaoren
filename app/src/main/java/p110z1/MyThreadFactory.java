package p110z1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/* renamed from: z1.aqx */
/* loaded from: classes3.dex */
public class MyThreadFactory implements ThreadFactory {

    /* renamed from: b */
    private String f17422b;

    /* renamed from: a */
    private int f17421a = 0;

    /* renamed from: c */
    private List<String> f17423c = new ArrayList();

    public MyThreadFactory(String str) {
        this.f17422b = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f17422b + "-Thread-" + this.f17421a);
        this.f17423c.add(String.format("Created thread %d with name %s on%s\n", Long.valueOf(thread.getId()), thread.getName(), new Date()));
        this.f17421a = this.f17421a + 1;
        return thread;
    }

    /* renamed from: a */
    public String m11421a() {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : this.f17423c) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }
}
