package p110z1;

import android.net.LocalSocket;
import com.common.utils.SharedPreferencesUtil;
import com.common.utils.log.LogUtils;
import java.io.DataInputStream;

/* renamed from: z1.adl */
/* loaded from: classes3.dex */
public abstract class XJLiveInitTask2 extends Thread {

    /* renamed from: a */
    LocalSocket f15298a;

    /* renamed from: b */
    private boolean f15299b;

    /* renamed from: a */
    public abstract void mo14299a(int i);

    public XJLiveInitTask2(LocalSocket localSocket, boolean z) {
        this.f15299b = z;
        this.f15298a = localSocket;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            LogUtils.m22038d("SHENG DEBUG", "MyDealProgressThread intread");
            DataInputStream dataInputStream = new DataInputStream(this.f15298a.getInputStream());
            while (true) {
                int readInt = dataInputStream.readInt();
                LogUtils.m22038d("SHENG DEBUG", "MyDealProgressThread intread:" + readInt);
                if (readInt == 666666) {
                    EventBus.m3448a().m3423f(new EventBusMessage(acf.f15184i));
                    SharedPreferencesUtil.getInstance().putBooleanValue(acf.f15186k, true);
                    SharedPreferencesUtil.getInstance().putLongValue(acf.f15187l, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    mo14299a(SocketConstants.f15236ah);
                    return;
                } else if (readInt == -1) {
                    EventBus.m3448a().m3423f(new EventBusMessage(acf.f15183h));
                    SharedPreferencesUtil.getInstance().putLongValue(acf.f15187l, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return;
                } else if (readInt != 21) {
                    boolean z = this.f15299b;
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.m22039d("XJLiveInitTask2 Exception:" + e.toString());
        }
    }
}
