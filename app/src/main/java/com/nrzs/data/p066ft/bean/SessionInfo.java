package com.nrzs.data.p066ft.bean;

/* renamed from: com.nrzs.data.ft.bean.SessionInfo */
/* loaded from: classes2.dex */
public class SessionInfo {
    private String DeviceCode;
    private String DeviceModel;
    private String DeviceNote;
    private boolean IsTwentyFourHour;
    private String LastLoginTime;
    private String LastRunTime;
    private String SessionId;
    private long UserId;

    public boolean isTwentyFourHour() {
        return this.IsTwentyFourHour;
    }

    public void setTwentyFourHour(boolean z) {
        this.IsTwentyFourHour = z;
    }

    public String getLastRunTime() {
        return this.LastRunTime;
    }

    public void setLastRunTime(String str) {
        this.LastRunTime = str;
    }

    public String getSessionId() {
        return this.SessionId;
    }

    public void setSessionId(String str) {
        this.SessionId = str;
    }

    public String getDeviceCode() {
        return this.DeviceCode;
    }

    public void setDeviceCode(String str) {
        this.DeviceCode = str;
    }

    public String getDeviceModel() {
        return this.DeviceModel;
    }

    public void setDeviceModel(String str) {
        this.DeviceModel = str;
    }

    public String getDeviceNote() {
        return this.DeviceNote;
    }

    public void setDeviceNote(String str) {
        this.DeviceNote = str;
    }

    public long getUserId() {
        return this.UserId;
    }

    public void setUserId(long j) {
        this.UserId = j;
    }

    public String getLastLoginTime() {
        return this.LastLoginTime;
    }

    public void setLastLoginTime(String str) {
        this.LastLoginTime = str;
    }
}
