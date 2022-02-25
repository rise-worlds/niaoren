package com.cyjh.ddysdk.device.media;

import android.view.ViewGroup;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.media.bean.DdyUserInfo;
import com.cyjh.ddy.media.media.listener.IHwySDKListener;
import com.cyjh.ddy.media.media.listener.IHwySdkFuncProcess;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import java.util.Set;

/* loaded from: classes.dex */
public interface DdyDeviceMediaContract extends NoProGuard {

    /* loaded from: classes.dex */
    public interface Callback extends NoProGuard {
        void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str);

        void success(Object obj);
    }

    /* loaded from: classes.dex */
    public interface IBusiness extends NoProGuard {
        void addProcessRequest(String str, IHwySdkFuncProcess iHwySdkFuncProcess);
    }

    /* loaded from: classes.dex */
    public interface IDefineRoom extends NoProGuard {
        void createRoom(long j, String str, String str2, String str3, String str4, Callback callback);

        void destoryRoom(String str, Callback callback);

        void visitRoom(String str, String str2, boolean z, Callback callback);
    }

    /* loaded from: classes.dex */
    public interface IMedia extends NoProGuard {
        void changeBitrate(int i);

        void changeMedia(long j, String str, String str2, Callback callback);

        void doKeyEvent(long j, int i);

        boolean init(DdyUserInfo ddyUserInfo, String str, String str2, IHwySDKListener iHwySDKListener, ViewGroup viewGroup, boolean z);

        boolean isCanCtrl();

        void playMedia(long j, String str, String str2, Callback callback);

        void setPingLevel(int i, int i2, int i3);

        void setPullStreamRate(String str, String str2);

        void setReConnect(boolean z);

        void switchControllerKeyEvent(boolean z);

        void switchVoice(boolean z);

        void turnoffAudio();

        void turnonAudio();

        void uninit();
    }

    /* loaded from: classes.dex */
    public interface IRoom extends NoProGuard {
        void applyRoomCtrl();

        void createRoom(long j, String str, String str2, String str3, Callback callback);

        void destoryRoom(String str, String str2, Callback callback);

        Set<String> getRoomDetail(boolean z);

        void sendRoomMsg(String str);

        void setRoomCtrl(String str);

        void setRoomQuit(String str);

        void visitRoom(String str, String str2, Callback callback);
    }
}
