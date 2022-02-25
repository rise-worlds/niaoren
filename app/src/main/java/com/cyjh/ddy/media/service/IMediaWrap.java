package com.cyjh.ddy.media.service;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.media.media.listener.IHwyMediaListener;
import com.cyjh.ddy.media.media.listener.IMediaServiceListener;
import com.cyjh.ddy.media.oksocket.MsgDataBean;

/* loaded from: classes.dex */
public interface IMediaWrap extends NoProGuard {
    void connect(String str, int i, String str2, IHwyMediaListener gVar, String str3, IMediaServiceListener hVar, boolean z);

    void release();

    void send(MsgDataBean bVar);
}
