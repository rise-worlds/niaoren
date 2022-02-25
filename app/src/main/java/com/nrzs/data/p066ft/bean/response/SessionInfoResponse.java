package com.nrzs.data.p066ft.bean.response;

import com.nrzs.data.DataApp;
import com.nrzs.data.p066ft.bean.SessionInfo;
import java.util.List;

/* renamed from: com.nrzs.data.ft.bean.response.SessionInfoResponse */
/* loaded from: classes2.dex */
public class SessionInfoResponse {
    private int OpenNum;
    private List<SessionInfo> UserGoldSessions;
    private List<SessionInfo> UserSessions;

    public List<SessionInfo> getUserSessions() {
        return this.UserSessions;
    }

    private List<SessionInfo> swap(List<SessionInfo> list) {
        if (list != null && !list.isEmpty()) {
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (list.get(i2).getDeviceCode().equals(DataApp.m18939d().m18941c())) {
                    i = i2;
                }
            }
            if (i != 0) {
                list.remove(i);
                list.add(0, list.get(i));
            }
        }
        return list;
    }

    public void setUserSessions(List<SessionInfo> list) {
        this.UserSessions = list;
    }

    public int getOpenNum() {
        return this.OpenNum;
    }

    public void setOpenNum(int i) {
        this.OpenNum = i;
    }

    public List<SessionInfo> getUserGoldSessions() {
        return this.UserGoldSessions;
    }

    public void setUserGoldSessions(List<SessionInfo> list) {
        this.UserGoldSessions = list;
    }
}
