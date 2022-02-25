package com.cyjh.ddy.media.oksocket;

import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.media.bean.CommandRequestInfo;
import com.cyjh.ddy.media.bean.socket.BaseSocketRequest;
import com.cyjh.ddy.media.bean.socket.ControlRequest;

/* renamed from: com.cyjh.ddy.media.oksocket.d */
/* loaded from: classes.dex */
public class SocketHelper {
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static String m21524a(ControlRequest controlRequest, String str) {
        BaseSocketRequest baseSocketRequest = new BaseSocketRequest();
        baseSocketRequest.data = controlRequest;
        baseSocketRequest.type = 1;
        baseSocketRequest.sign = baseSocketRequest.getSign();
        baseSocketRequest.token = str;
        return JsonUtil.m21804a(baseSocketRequest);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.cyjh.ddy.media.bean.TouchEventRequest] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m21525a(int r1, int r2, int r3, java.util.List<com.cyjh.ddy.media.bean.TouchPoint> r4) {
        /*
            com.cyjh.ddy.media.bean.TouchEventRequest r0 = new com.cyjh.ddy.media.bean.TouchEventRequest
            r0.<init>()
            r0.count = r1
            r0.status = r2
            r0.index = r3
            r0.points = r4
            r1 = 0
            r0.type = r1
            com.cyjh.ddy.media.bean.socket.BaseSocketRequest r1 = new com.cyjh.ddy.media.bean.socket.BaseSocketRequest
            r1.<init>()
            r1.data = r0
            r2 = 1
            r1.type = r2
            java.lang.String r2 = r1.getSign()
            r1.sign = r2
            java.lang.String r1 = com.cyjh.ddy.base.utils.JsonUtil.m21804a(r1)
            java.lang.String r2 = "WebSocketHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "getTouchEventJson: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.cyjh.ddy.base.utils.CLog.m21883e(r2, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.media.oksocket.SocketHelper.m21525a(int, int, int, java.util.List):java.lang.String");
    }

    /* renamed from: a */
    public static String m21519a(String str, String str2, String str3) {
        return m21520a(str, str2, 2, str3);
    }

    /* renamed from: a */
    public static String m21520a(String str, String str2, int i, String str3) {
        CommandRequestInfo commandRequestInfo = new CommandRequestInfo();
        commandRequestInfo.time = System.currentTimeMillis();
        commandRequestInfo.data = str2;
        commandRequestInfo.command = str;
        return m21523a(commandRequestInfo, i, str3);
    }

    /* renamed from: a */
    public static <T> String m21522a(T t, String str) {
        return m21523a(t, 2, str);
    }

    /* renamed from: a */
    public static <T> String m21523a(T t, int i, String str) {
        BaseSocketRequest baseSocketRequest = new BaseSocketRequest();
        baseSocketRequest.data = t;
        baseSocketRequest.type = i;
        baseSocketRequest.sign = baseSocketRequest.getSign();
        baseSocketRequest.token = str;
        String a = JsonUtil.m21804a(baseSocketRequest);
        CLog.m21882i("ANBOX_MSG", "send:--> " + a);
        return a;
    }

    /* renamed from: a */
    public static String m21521a(String str) {
        return str.substring(0, str.lastIndexOf(":"));
    }

    /* renamed from: b */
    public static int m21518b(String str) {
        return Integer.valueOf(str.substring(str.lastIndexOf(":") + 1, str.length())).intValue();
    }
}
