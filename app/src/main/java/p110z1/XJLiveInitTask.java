package p110z1;

import android.net.LocalSocket;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.common.utils.SharedPreferencesUtil;
import java.io.DataInputStream;

/* renamed from: z1.adk */
/* loaded from: classes3.dex */
public abstract class XJLiveInitTask extends ThreadUtils.AbstractRunnableC0995b<Integer> {

    /* renamed from: a */
    LocalSocket f15296a;

    /* renamed from: b */
    private boolean f15297b;

    @Override // com.blankj.utilcode.util.ThreadUtils.AbstractRunnableC0995b
    /* renamed from: a */
    public void mo14306a() {
    }

    /* renamed from: a */
    public abstract void m14305a(int i);

    @Override // com.blankj.utilcode.util.ThreadUtils.AbstractRunnableC0995b
    /* renamed from: a */
    public void mo14302a(Throwable th) {
    }

    public XJLiveInitTask(LocalSocket localSocket, boolean z) {
        this.f15297b = z;
        this.f15296a = localSocket;
    }

    /* renamed from: f */
    public Integer mo14301b() throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            DataInputStream dataInputStream = new DataInputStream(this.f15296a.getInputStream());
            LogUtils.m23742b("SHENG DEBUG", "MyDealProgressThread intread");
            while (true) {
                int readInt = dataInputStream.readInt();
                LogUtils.m23742b("SHENG DEBUG", "MyDealProgressThread intread:" + readInt);
                if (readInt == 666666) {
                    EventBus.m3448a().m3423f(new EventBusMessage(acf.f15184i));
                    SharedPreferencesUtil.getInstance().putBooleanValue(acf.f15186k, true);
                    SharedPreferencesUtil.getInstance().putLongValue(acf.f15187l, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return Integer.valueOf(readInt);
                } else if (readInt == -1) {
                    EventBus.m3448a().m3423f(new EventBusMessage(acf.f15183h));
                    SharedPreferencesUtil.getInstance().putLongValue(acf.f15187l, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return null;
                } else if (readInt == 21) {
                    return null;
                } else {
                    boolean z = this.f15297b;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo14303a(Integer num) {
        if (num != null) {
            m14305a(num.intValue());
        }
    }
}
