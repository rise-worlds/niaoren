package com.cyjh.ddysdk.game;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper;
import com.cyjh.ddysdk.game.bean.GamePlayInfo;
import com.cyjh.ddysdk.game.bean.NetSpeedInfo;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface DdyGameContract extends NoProGuard {

    /* loaded from: classes.dex */
    public interface IAccountPresenter extends NoProGuard {
        void runAppCreateAccount(String str, DdyOrderInfo ddyOrderInfo, String str2, String str3, DdyOrderContract.TCallback<Integer> tCallback);

        void runAppLoaderAccount(String str, DdyOrderInfo ddyOrderInfo, String str2, String str3, int i, DdyOrderContract.TCallback<Integer> tCallback);
    }

    /* loaded from: classes.dex */
    public interface IPresenter extends NoProGuard {
        void countPriority(String str, DdyOrderContract.TCallback<List<Integer>> tCallback);

        void netSpeedTest(String str, int i, boolean z, List<Integer> list, DdyOrderContract.TCallback<NetSpeedInfo> tCallback);

        void quitGame(DdyDeviceMediaHelper ddyDeviceMediaHelper, String str, String str2);

        void recordPlayTime(DdyDeviceMediaHelper ddyDeviceMediaHelper, String str, String str2);

        void requestGetAuthCode(long j, String str, int i, String str2, String str3, DdyOrderContract.TCallback<GamePlayInfo> tCallback);

        void requestGetAuthCode(long j, String str, List<Integer> list, String str2, String str3, DdyOrderContract.TCallback<GamePlayInfo> tCallback);

        void requestQueueHeart(String str, String str2, String str3, DdyOrderContract.TCallback<GamePlayInfo> tCallback);

        void requestQuitQueue(String str, String str2, String str3, DdyOrderContract.Callback callback);
    }
}
