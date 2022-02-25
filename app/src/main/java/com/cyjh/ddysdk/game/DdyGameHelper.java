package com.cyjh.ddysdk.game;

import android.text.TextUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.SdkUtils;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddy.net.utils.DomainUtils;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.PingData;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper;
import com.cyjh.ddysdk.game.DdyGameContract;
import com.cyjh.ddysdk.game.bean.ActivateAccountInfo;
import com.cyjh.ddysdk.game.bean.CloudAccountInfo;
import com.cyjh.ddysdk.game.bean.EncodeServerIP;
import com.cyjh.ddysdk.game.bean.GamePlayInfo;
import com.cyjh.ddysdk.game.bean.NetSpeedInfo;
import com.cyjh.ddysdk.game.model.DdyGameRequestModel;
import com.cyjh.ddysdk.game.p040a.YunGameConstants;
import com.cyjh.ddysdk.game.utils.NetSpeedUtil;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.bean.OrderInfoRespone;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p110z1.Consts;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public class DdyGameHelper implements DdyGameContract.IAccountPresenter, DdyGameContract.IPresenter {
    private DdyGameHelper() {
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {

        /* renamed from: a */
        private static final DdyGameHelper f8012a = new DdyGameHelper();

        private LazyHolder() {
        }
    }

    public static DdyGameHelper getInstance() {
        if (SdkUtils.m21760b()) {
            return LazyHolder.f8012a;
        }
        CLog.m21883e("sdk-game", "just yungame can call this function.");
        return null;
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void countPriority(String str, final DdyOrderContract.TCallback<List<Integer>> tCallback) {
        CLog.m21882i("sdk-game", "countPriority ucid=" + str);
        DomainUtils.m21393a(false);
        new DdyGameRequestModel().m21166a(str, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.1
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-game", "countPriority wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1 || baseResultWrapper.data == 0 || ((List) baseResultWrapper.data).isEmpty()) {
                    CLog.m21883e("sdk-game", "countPriority onFail code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    DdyGameHelper.this.m21171a((List) baseResultWrapper.data, tCallback);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-game", "requestQueueHeart uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void requestGetAuthCode(long j, String str, int i, String str2, String str3, DdyOrderContract.TCallback<GamePlayInfo> tCallback) {
        m21176a(j, str, i, null, str2, str3, tCallback);
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void requestGetAuthCode(long j, String str, List<Integer> list, String str2, String str3, DdyOrderContract.TCallback<GamePlayInfo> tCallback) {
        m21176a(j, str, 0, list, str2, str3, tCallback);
    }

    /* renamed from: a */
    private void m21176a(long j, String str, int i, List<Integer> list, String str2, String str3, final DdyOrderContract.TCallback<GamePlayInfo> tCallback) {
        CLog.m21882i("sdk-game", "requestGetAuthCode gameId=" + j + ",authCode=" + str + ",ucid=" + str2);
        new DdyGameRequestModel().m21169a(j, str, i, list, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.2
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-game", "requestGetAuthCode wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-game", "requestGetAuthCode onFail  code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    tCallback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-game", "requestGetAuthCode uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void requestQueueHeart(String str, String str2, String str3, final DdyOrderContract.TCallback<GamePlayInfo> tCallback) {
        CLog.m21882i("sdk-game", "requestQueueHeart queueId=" + str + ",ucid=" + str2);
        new DdyGameRequestModel().m21164a(str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.3
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-game", "requestQueueHeart wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-game", "requestQueueHeart onFail code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    tCallback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-game", "requestQueueHeart uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void requestQuitQueue(String str, String str2, String str3, final DdyOrderContract.Callback callback) {
        CLog.m21882i("sdk-game", "requestQuitQueue queueId=" + str + ",ucid=" + str2);
        new DdyGameRequestModel().m21163b(str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.4
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-game", "requestQuitQueue wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-game", "requestQuitQueue onFail code=" + baseResultWrapper.code);
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    callback.onSuccess(null);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-game", "requestQuitQueue uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void quitGame(DdyDeviceMediaHelper ddyDeviceMediaHelper, String str, String str2) {
        m21175a(ddyDeviceMediaHelper, str, str2, new YunGameConstants().f8018f, true);
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void recordPlayTime(DdyDeviceMediaHelper ddyDeviceMediaHelper, String str, String str2) {
        m21175a(ddyDeviceMediaHelper, str, str2, new YunGameConstants().f8019g, false);
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IPresenter
    public void netSpeedTest(String str, final int i, final boolean z, final List<Integer> list, final DdyOrderContract.TCallback<NetSpeedInfo> tCallback) {
        CLog.m21882i("sdk-game", "netBandwidth ucid=" + str);
        DomainUtils.m21393a(false);
        new DdyGameRequestModel().m21166a(str, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.5
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-game", "netBandwidth wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1 || baseResultWrapper.data == 0 || ((List) baseResultWrapper.data).isEmpty()) {
                    CLog.m21883e("sdk-game", "netBandwidth onFail code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    List<EncodeServerIP> list2 = (List) baseResultWrapper.data;
                    List list3 = list;
                    if (list3 != null && !list3.isEmpty()) {
                        int intValue = ((Integer) list.get(0)).intValue();
                        ArrayList arrayList = new ArrayList();
                        for (EncodeServerIP encodeServerIP : list2) {
                            if (encodeServerIP.DeviceRegion == intValue) {
                                arrayList.add(encodeServerIP);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            list2 = arrayList;
                        }
                    }
                    EncodeServerIP encodeServerIP2 = (EncodeServerIP) list2.get((int) (Math.random() * list2.size()));
                    if (encodeServerIP2 != null) {
                        final String str2 = encodeServerIP2.f8023Ip;
                        final int i2 = z ? 21001 : 21002;
                        NetSpeedUtil.m21162a().m21157a(str2, i2, i, z, new NetSpeedUtil.INetCallback() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.5.1
                            @Override // com.cyjh.ddysdk.game.utils.NetSpeedUtil.INetCallback
                            public void onResult(boolean z2, long j, long j2) {
                                if (tCallback != null) {
                                    DdyOrderContract.TCallback tCallback2 = tCallback;
                                    tCallback2.onSuccess(new NetSpeedInfo(str2 + ":" + i2, z2, j, j2));
                                }
                            }
                        });
                    }
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-game", "requestQueueHeart uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: a */
    private void m21175a(DdyDeviceMediaHelper ddyDeviceMediaHelper, String str, String str2, String str3, boolean z) {
        long j;
        long j2;
        long j3;
        if (ddyDeviceMediaHelper != null) {
            long avgPing = ddyDeviceMediaHelper.getAvgPing();
            long maxPing = ddyDeviceMediaHelper.getMaxPing();
            long firstFrameTime = ddyDeviceMediaHelper.getFirstFrameTime();
            if (z) {
                ddyDeviceMediaHelper.clearPingAndFirst();
            }
            j2 = avgPing;
            j = maxPing;
            j3 = firstFrameTime;
        } else {
            j3 = 0;
            j2 = 0;
            j = 0;
        }
        new DdyGameRequestModel().m21168a(str, j3, j2, j, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.6
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21171a(final List<EncodeServerIP> list, final DdyOrderContract.TCallback<List<Integer>> tCallback) {
        final ArrayList arrayList = new ArrayList();
        int i = 1;
        if (list.size() == 1) {
            CLog.m21882i("sdk-game", "countPriority DeviceRegion: " + list.get(0).DeviceRegion + "; ping: " + list.get(0).ping + "; ip: " + list.get(0).f8023Ip);
            arrayList.add(Integer.valueOf(list.get(0).DeviceRegion));
            tCallback.onSuccess(arrayList);
            return;
        }
        final ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            final EncodeServerIP encodeServerIP = list.get(i2);
            CLog.m21882i("sdk-game", "countPriority DeviceRegion: " + encodeServerIP.DeviceRegion + "; ip: " + encodeServerIP.f8023Ip);
            final boolean z = i2 == list.size() - i;
            new Thread(new Runnable() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.7
                @Override // java.lang.Runnable
                public void run() {
                    EncodeServerIP encodeServerIP2 = encodeServerIP;
                    encodeServerIP2.ping = DdyGameHelper.this.m21172a(encodeServerIP2.f8023Ip);
                    arrayList2.add(encodeServerIP);
                    CLog.m21882i("sdk-game", "countPriority DeviceRegion: " + encodeServerIP.DeviceRegion + "; ping: " + encodeServerIP.ping + "; ip: " + encodeServerIP.f8023Ip);
                    if (z) {
                        while (arrayList2.size() != list.size()) {
                            try {
                                Thread.sleep(100L);
                            } catch (Exception unused) {
                            }
                        }
                        Collections.sort(list, new Comparator<EncodeServerIP>() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.7.1
                            public int compare(EncodeServerIP encodeServerIP3, EncodeServerIP encodeServerIP4) {
                                return (int) (encodeServerIP3.ping - encodeServerIP4.ping);
                            }
                        });
                        for (EncodeServerIP encodeServerIP3 : list) {
                            arrayList.add(Integer.valueOf(encodeServerIP3.DeviceRegion));
                        }
                        tCallback.onSuccess(arrayList);
                    }
                }
            }).start();
            i2++;
            i = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public long m21172a(String str) {
        ShellUtils.C0985a a;
        String str2 = "";
        try {
            a = ShellUtils.m23276a("ping -c 2 -i 0.5 -s 64 -f  " + str, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!a.f6749b.contains("min/avg/max/mdev")) {
            CLog.m21882i("sdk-game", str + ExpandableTextView.f6958c + a.f6749b);
            return 99999L;
        }
        String substring = a.f6749b.substring(a.f6749b.indexOf("min/avg/max/mdev"));
        str2 = substring.substring(substring.indexOf(SimpleComparison.f23609c) + 1, substring.indexOf("ms") - 1).trim();
        if (TextUtils.isEmpty(str2)) {
            return 99999L;
        }
        PingData pingData = new PingData(str2);
        if (TextUtils.equals(pingData.avg, "?")) {
            return 99999L;
        }
        try {
            return Long.valueOf(pingData.avg.substring(0, pingData.avg.indexOf(Consts.f23430h))).longValue();
        } catch (Exception unused) {
            return 99999L;
        }
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IAccountPresenter
    public void runAppCreateAccount(String str, final DdyOrderInfo ddyOrderInfo, final String str2, final String str3, final DdyOrderContract.TCallback<Integer> tCallback) {
        new DdyGameRequestModel().m21165a(str, str2, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.8
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-game", "getWaitActivateAccount wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1 || baseResultWrapper.data == 0) {
                    CLog.m21883e("sdk-game", "getWaitActivateAccount onFail code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    ActivateAccountInfo activateAccountInfo = (ActivateAccountInfo) baseResultWrapper.data;
                    List<String> list = activateAccountInfo.AccountFilePaths;
                    String str4 = activateAccountInfo.GameAccount;
                    String format = String.format("%s %s", activateAccountInfo.GameAccount, activateAccountInfo.GamePassword);
                    SPUtils.m23341a().m23332a("game_account_string", C1123f.m21845a(activateAccountInfo));
                    DdyDeviceCommandHelper.getInstance().setClipBoard(ddyOrderInfo, format, new DdyDeviceCommandContract.Callback<Integer>() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.8.1
                        @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                        public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str5) {
                        }

                        public void success(Integer num) {
                        }
                    });
                    DdyDeviceCommandHelper.getInstance().runAppSave(ddyOrderInfo, str2, str3, null, str4, list, 4, new DdyDeviceCommandContract.Callback<Integer>() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.8.2
                        public void success(Integer num) {
                            tCallback.onSuccess(num);
                        }

                        @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                        public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str5) {
                            tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(ddyDeviceErrorConstants.ordinal())), str5);
                        }
                    });
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-game", "getWaitActivateAccount uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.game.DdyGameContract.IAccountPresenter
    public void runAppLoaderAccount(String str, final DdyOrderInfo ddyOrderInfo, final String str2, final String str3, int i, final DdyOrderContract.TCallback<Integer> tCallback) {
        SPUtils.m23341a().m23332a("game_account_string", "");
        OrderInfoRespone orderInfoRespone = (OrderInfoRespone) ddyOrderInfo;
        new DdyGameRequestModel().m21167a(str, orderInfoRespone.OrderId, str2, orderInfoRespone.DeviceToken, i, new IUIDataListener() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.9
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-game", "getCloudAccount wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1 || baseResultWrapper.data == 0) {
                    CLog.m21883e("sdk-game", "getCloudAccount onFail code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    CloudAccountInfo cloudAccountInfo = (CloudAccountInfo) baseResultWrapper.data;
                    DdyDeviceCommandHelper.getInstance().runAppSave(ddyOrderInfo, str2, str3, null, cloudAccountInfo.GameAccount, cloudAccountInfo.AccountFilePaths, 3, new DdyDeviceCommandContract.Callback<Integer>() { // from class: com.cyjh.ddysdk.game.DdyGameHelper.9.1
                        public void success(Integer num) {
                            tCallback.onSuccess(num);
                        }

                        @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                        public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str4) {
                            tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(ddyDeviceErrorConstants.ordinal())), str4);
                        }
                    });
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-game", "getCloudAccount uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }
}
