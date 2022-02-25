package com.cyjh.ddy.media.media;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.CLog;

/* loaded from: classes.dex */
public class ActionCode implements NoProGuard {
    public static final int BusNotice_Destop_Hide = 3002;
    public static final int BusNotice_Destop_Show = 3001;
    public static final int CtrlConnectRefuse_2001 = 2001;
    public static final int CtrlConnectRefuse_2002 = 2002;
    public static final int CtrlConnectRefuse_DEFINE_END = 2999;
    public static final int CtrlConnectRefuse_DEFINE_START = 2000;
    public static final int MediaCloseSuccess = 8002;
    public static final int MediaConnectErr = 8003;
    public static final int MediaConnectRefuse_1001 = 1001;
    public static final int MediaConnectRefuse_1022 = 1022;
    public static final int MediaConnectRefuse_1023 = 1023;
    public static final int MediaConnectRefuse_1041 = 1041;
    public static final int MediaConnectRefuse_1043 = 1043;
    public static final int MediaConnectRefuse_1044 = 1044;
    public static final int MediaConnectRefuse_1045 = 1045;
    public static final int MediaConnectRefuse_1046 = 1046;
    public static final int MediaConnectRefuse_1047 = 1047;
    public static final int MediaConnectRefuse_1049 = 1049;
    public static final int MediaConnectRefuse_DEFINE_Begin = 1000;
    public static final int MediaConnectRefuse_DEFINE_END = 1999;
    public static final int MediaConnectSuccess = 8001;
    public static final int MediaFirstFrameSuccess = 8004;
    public static final int MediaReConnectBegin = 8006;
    public static final int MediaReConnectEnd = 8007;
    public static final int PC_MediaReadTimeOut = 8005;
    public static final int RoomNotice_CTRL_APPLY = 3106;
    public static final int RoomNotice_CTRL_OFF = 3103;
    public static final int RoomNotice_CTRL_ON = 3102;
    public static final int RoomNotice_DESTORY = 3105;
    public static final int RoomNotice_KICK = 3104;
    public static final int RoomNotice_PEOPLE_CHANGE = 3101;
    public static final int RoomNotice_RECEIVE_MSG = 3107;

    public static boolean isCtrlRefuseAction(int i) {
        return i >= 2000 && i <= 2999;
    }

    public static boolean isMediaRefuseAction(int i) {
        return i >= 1000 && i <= 1999;
    }

    public static boolean isErrExitAction(int i) {
        if (i == 8003 || isMediaRefuseAction(i) || isCtrlRefuseAction(i) || i == 3105 || i == 3104) {
            CLog.m21882i("sdk-media", "isExitAction " + i + " true");
            return true;
        }
        CLog.m21882i("sdk-media", "isExitAction " + i + " false");
        return false;
    }
}
