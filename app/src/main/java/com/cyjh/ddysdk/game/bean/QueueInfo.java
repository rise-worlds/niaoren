package com.cyjh.ddysdk.game.bean;

import com.cyjh.ddy.base.p033a.NoProGuard;
import java.io.Serializable;

/* loaded from: classes.dex */
public class QueueInfo implements NoProGuard, Serializable {
    public long CurrentNum;
    public long ExpectedWaitTime;
    public int HeartInterval;
    public String QueueId;
}
