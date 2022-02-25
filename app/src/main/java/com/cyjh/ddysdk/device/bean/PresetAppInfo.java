package com.cyjh.ddysdk.device.bean;

import java.util.List;

/* loaded from: classes.dex */
public class PresetAppInfo {
    public DesktopRefreshObsInfoBean DesktopRefreshObsInfo;
    public List<InstallAppsBean> InstallApps;
    public List<UnInstallAppsBean> UnInstallApps;

    /* loaded from: classes.dex */
    public static class DesktopRefreshObsInfoBean {

        /* renamed from: AK */
        public String f7686AK;
        public String BucketName;
        public String EndPoint;
        public String ObsKey;
        public String RefreshCommand;
        public int RefreshMode;
        public String RemoteFilePath;

        /* renamed from: SK */
        public String f7687SK;
    }

    /* loaded from: classes.dex */
    public static class InstallAppsBean {
        public int AppID;
        public String AppName;
        public String AppPackageName;
        public String AppUrl;
    }

    /* loaded from: classes.dex */
    public static class UnInstallAppsBean {
        public String AppPackageName;
    }
}
