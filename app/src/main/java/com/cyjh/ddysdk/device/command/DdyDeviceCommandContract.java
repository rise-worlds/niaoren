package com.cyjh.ddysdk.device.command;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.AppInfo;
import com.cyjh.ddysdk.device.bean.ResponseNoticeApps;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface DdyDeviceCommandContract extends NoProGuard {

    /* loaded from: classes.dex */
    public interface Callback<T> extends NoProGuard {
        void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str);

        void success(T t);
    }

    /* loaded from: classes.dex */
    public interface IApp extends NoProGuard {
        void amStartApp(DdyOrderInfo ddyOrderInfo, String str, String str2, Callback<String> callback);

        void clearApps(DdyOrderInfo ddyOrderInfo, List<String> list, Callback<Integer> callback);

        void getAppsInfo(DdyOrderInfo ddyOrderInfo, Callback<List<AppInfo>> callback);

        void getInstallState(DdyOrderInfo ddyOrderInfo, String str, Callback<String> callback);

        void getTopApp(DdyOrderInfo ddyOrderInfo, Callback<String> callback);

        void installApp(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, boolean z, Map<String, String> map, Callback<Integer> callback);

        void installAppTar(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, boolean z, Map<String, String> map, Callback<Integer> callback);

        void runApp(DdyOrderInfo ddyOrderInfo, String str, String str2, Map<String, String> map, Callback<Integer> callback);

        void runAppSave(DdyOrderInfo ddyOrderInfo, String str, String str2, Map<String, String> map, String str3, List<String> list, int i, Callback<Integer> callback);

        void stopApps(DdyOrderInfo ddyOrderInfo, List<String> list, Callback<Integer> callback);

        void uninstallApps(DdyOrderInfo ddyOrderInfo, List<String> list, Callback<Integer> callback);
    }

    /* loaded from: classes.dex */
    public interface IBusiness extends NoProGuard {
        void addNotifyPackages(DdyOrderInfo ddyOrderInfo, List<String> list, Callback<Integer> callback);

        void appsFolders(DdyOrderInfo ddyOrderInfo, String str, Callback<String> callback);

        void cameraCancel(DdyOrderInfo ddyOrderInfo, Callback<String> callback);

        void getNoticeApps(DdyOrderInfo ddyOrderInfo, boolean z, Callback<List<ResponseNoticeApps>> callback);

        void haveSysEnvironment(DdyOrderInfo ddyOrderInfo, Callback<String> callback);

        void installSysEnvironment(DdyOrderInfo ddyOrderInfo, Callback<Integer> callback);

        void photograph(DdyOrderInfo ddyOrderInfo, String str, String str2, ObsCert obsCert, Callback<String> callback);

        void qrcode(DdyOrderInfo ddyOrderInfo, String str, Callback<String> callback);

        void queryRootState(DdyOrderInfo ddyOrderInfo, Callback<Boolean> callback);

        void removeNotifyPackages(DdyOrderInfo ddyOrderInfo, List<String> list, Callback<Integer> callback);

        void scan(DdyOrderInfo ddyOrderInfo, Callback<String> callback);

        void screenshot(DdyOrderInfo ddyOrderInfo, Callback<String> callback);

        void setGPS(DdyOrderInfo ddyOrderInfo, double d, double d2, Callback<String> callback);

        void setNotifyPackages(DdyOrderInfo ddyOrderInfo, List<String> list, Callback<Integer> callback);

        void setPresetApp(DdyOrderInfo ddyOrderInfo, String str, Callback<String> callback);

        void shake(DdyOrderInfo ddyOrderInfo, Callback<String> callback);
    }

    /* loaded from: classes.dex */
    public interface ScreenCap extends NoProGuard {

        /* loaded from: classes.dex */
        public interface IPresenter extends NoProGuard {
            void screenCap(long j, String str, long j2, long j3, String str2);
        }

        /* loaded from: classes.dex */
        public interface IView extends NoProGuard {
            void updateScreenCap(long j, byte[] bArr);

            void updateScreenCapFailure(long j, DdyDeviceErrorConstants ddyDeviceErrorConstants, String str);
        }

        /* loaded from: classes.dex */
        public interface Imodel extends NoProGuard {
            void requestScreenCap(String str, long j, long j2, String str2, Callback<byte[]> callback);
        }
    }
}
