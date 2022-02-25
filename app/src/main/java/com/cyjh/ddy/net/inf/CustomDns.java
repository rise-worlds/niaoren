package com.cyjh.ddy.net.inf;

import com.cyjh.ddy.base.p033a.NoProGuard;
import java.util.ArrayList;

/* loaded from: classes.dex */
public interface CustomDns extends NoProGuard {
    String getIpByHost(String str);

    void setPreResolveHosts(ArrayList<String> arrayList);
}
