package com.cyjh.ddysdk.game.bean;

import com.cyjh.ddy.base.p033a.NoProGuard;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class DeviceGameInfo implements NoProGuard, Serializable {
    public String AuthCode;
    public int DeviceRegion;
    public String GameActivityName;
    public List<String> GameDataPath;
    public String GamePackageName;
    public long OrderId;
    public long PlayTime;
}
