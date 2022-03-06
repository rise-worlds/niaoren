package com.lbd.xj.socket.model;

import com.nrzs.data.xnkj.bean.request.FeedBackRequestInfo;
import java.util.HashMap;
import p110z1.SocketConstants;

/* renamed from: com.lbd.xj.socket.model.SocketClassType */
/* loaded from: classes.dex */
public class SocketClassType {
    private static final HashMap<String, Class> map = new HashMap<>();

    static {
        map.put(SocketConstants.f15242e, String.class);
        map.put(SocketConstants.f15243f, FileTransfer.class);
        map.put(SocketConstants.f15244g, FileTransfer.class);
        map.put(SocketConstants.f15253p, SetInfo.class);
        map.put(SocketConstants.f15252o, Resolution.class);
        map.put(SocketConstants.f15257t, CheckUpdate.class);
        map.put(SocketConstants.f15259v, FeedBackRequestInfo.class);
    }

    public static Class getClassWithKey(String str) {
        return map.get(str);
    }
}
