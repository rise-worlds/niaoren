package com.cyjh.ddysdk.device.extendcommand;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;

/* loaded from: classes.dex */
public class DdyDeviceExCommandContract implements NoProGuard {

    /* loaded from: classes.dex */
    public interface Callback extends NoProGuard {
        void onClosed(IPresenter iPresenter, String str);

        void onConnected(IPresenter iPresenter);

        void onFailure(IPresenter iPresenter, String str);

        void onMessage(IPresenter iPresenter, String str, String str2, int i);

        void onSended(IPresenter iPresenter);
    }

    /* loaded from: classes.dex */
    public interface IPresenter extends NoProGuard {
        boolean init(DdyOrderInfo ddyOrderInfo, String str, Callback callback);

        boolean sendMsg(String str, String str2);

        boolean sendMsg(String str, String str2, int i);

        void uninit();
    }
}
