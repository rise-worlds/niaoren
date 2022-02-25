package com.nrzs.data.other.bean.response;

import com.nrzs.data.other.bean.GifBagsBean;
import java.util.List;

/* loaded from: classes2.dex */
public class PreSetListResponse {
    private List<GifBagsBean> GifBags;
    private RootInfoBean RootInfo;
    private RdataBean rdata;

    public RootInfoBean getRootInfo() {
        return this.RootInfo;
    }

    public void setRootInfo(RootInfoBean rootInfoBean) {
        this.RootInfo = rootInfoBean;
    }

    public List<GifBagsBean> getGifBags() {
        return this.GifBags;
    }

    public void setGifBags(List<GifBagsBean> list) {
        this.GifBags = list;
    }

    public RdataBean getRdata() {
        return this.rdata;
    }

    public void setRdata(RdataBean rdataBean) {
        this.rdata = rdataBean;
    }

    /* loaded from: classes2.dex */
    public static class RootInfoBean {
        private Object AppName;
        private Object AppSize;
        private Object AppUrl;
        private Object AppVersion;
        private Object Package;

        public Object getAppUrl() {
            return this.AppUrl;
        }

        public void setAppUrl(Object obj) {
            this.AppUrl = obj;
        }

        public Object getAppName() {
            return this.AppName;
        }

        public void setAppName(Object obj) {
            this.AppName = obj;
        }

        public Object getPackage() {
            return this.Package;
        }

        public void setPackage(Object obj) {
            this.Package = obj;
        }

        public Object getAppVersion() {
            return this.AppVersion;
        }

        public void setAppVersion(Object obj) {
            this.AppVersion = obj;
        }

        public Object getAppSize() {
            return this.AppSize;
        }

        public void setAppSize(Object obj) {
            this.AppSize = obj;
        }
    }

    /* loaded from: classes2.dex */
    public static class RdataBean {
        private AppShareInfoBean AppShareInfo;
        private String BackgroundHangNavShieldingChannels;
        private BubbleModelBean BubbleModel;
        private boolean CardPayIfOpen;
        private String CardPayLink;
        private String DDYCourseUrl;
        private String DDYNavShieldingChannels;
        private String DetectIMGUrl;
        private String GameBlackIco;
        private List<GameBlackListBean> GameBlackList;
        private List<HomeShortcutsBean> HomeShortcuts;
        private String ImageOcrTextSize;
        private String ImageOcrTextUrl;
        private String ImageOcrTextVersion;
        private String NewGameTip;
        private String QuestionURL;
        private String RedPackEnter_IsOpen;
        private String RedPackEnter_Logo;
        private String RedPackEnter_SecTitle;
        private String RedPackEnter_Title;
        private List<?> RootBlackList;
        private String ShieldTopicIds;
        private String VaCourseUrl;
        private WGPopConfigBean WGPopConfig;
        private String WechatSettingCourseURL;
        private String _32BitNRDownUrl;
        private String _64BitNRDownUrl;
        private String certificateURL;
        private String customerURL;
        private String faqurl;
        private LhpCourceInfoBean lhpCourceInfo;

        public String getShieldTopicIds() {
            return this.ShieldTopicIds;
        }

        public void setShieldTopicIds(String str) {
            this.ShieldTopicIds = str;
        }

        public String getImageOcrTextUrl() {
            return this.ImageOcrTextUrl;
        }

        public void setImageOcrTextUrl(String str) {
            this.ImageOcrTextUrl = str;
        }

        public String getImageOcrTextVersion() {
            return this.ImageOcrTextVersion;
        }

        public void setImageOcrTextVersion(String str) {
            this.ImageOcrTextVersion = str;
        }

        public String getImageOcrTextSize() {
            return this.ImageOcrTextSize;
        }

        public void setImageOcrTextSize(String str) {
            this.ImageOcrTextSize = str;
        }

        public String getDDYNavShieldingChannels() {
            return this.DDYNavShieldingChannels;
        }

        public String getBackgroundHangNavShieldingChannels() {
            return this.BackgroundHangNavShieldingChannels;
        }

        public void setBackgroundHangNavShieldingChannels(String str) {
            this.BackgroundHangNavShieldingChannels = str;
        }

        public void setDDYNavShieldingChannels(String str) {
            this.DDYNavShieldingChannels = str;
        }

        public String getRedPackEnter_IsOpe() {
            return this.RedPackEnter_IsOpen;
        }

        public void setRedPackEnter_IsOpe(String str) {
            this.RedPackEnter_IsOpen = str;
        }

        public String getRedPackEnter_Title() {
            return this.RedPackEnter_Title;
        }

        public void setRedPackEnter_Title(String str) {
            this.RedPackEnter_Title = str;
        }

        public String getRedPackEnter_SecTitle() {
            return this.RedPackEnter_SecTitle;
        }

        public void setRedPackEnter_SecTitle(String str) {
            this.RedPackEnter_SecTitle = str;
        }

        public String getRedPackEnter_Logo() {
            return this.RedPackEnter_Logo;
        }

        public void setRedPackEnter_Logo(String str) {
            this.RedPackEnter_Logo = str;
        }

        public String get_32BitNRDownUrl() {
            return this._32BitNRDownUrl;
        }

        public void set_32BitNRDownUrl(String str) {
            this._32BitNRDownUrl = str;
        }

        public String get_64BitNRDownUrl() {
            return this._64BitNRDownUrl;
        }

        public String getWechatSettingCourseURL() {
            return this.WechatSettingCourseURL;
        }

        public void setWechatSettingCourseURL(String str) {
            this.WechatSettingCourseURL = str;
        }

        public String getVaCourseUrl() {
            return this.VaCourseUrl;
        }

        public void setVaCourseUrl(String str) {
            this.VaCourseUrl = str;
        }

        public boolean isCardPayIfOpen() {
            return this.CardPayIfOpen;
        }

        public void setCardPayIfOpen(boolean z) {
            this.CardPayIfOpen = z;
        }

        public String getCardPayLink() {
            return this.CardPayLink;
        }

        public String getDDYCourseUrl() {
            return this.DDYCourseUrl;
        }

        public void setDDYCourseUrl(String str) {
            this.DDYCourseUrl = str;
        }

        public void setCardPayLink(String str) {
            this.CardPayLink = str;
        }

        public String getFaqurl() {
            return this.faqurl;
        }

        public void setFaqurl(String str) {
            this.faqurl = str;
        }

        public String getNewGameTip() {
            return this.NewGameTip;
        }

        public void setNewGameTip(String str) {
            this.NewGameTip = str;
        }

        public String getGameBlackIco() {
            return this.GameBlackIco;
        }

        public void setGameBlackIco(String str) {
            this.GameBlackIco = str;
        }

        public String getDetectIMGUrl() {
            return this.DetectIMGUrl;
        }

        public void setDetectIMGUrl(String str) {
            this.DetectIMGUrl = str;
        }

        public String getQuestionURL() {
            return this.QuestionURL;
        }

        public void setQuestionURL(String str) {
            this.QuestionURL = str;
        }

        public BubbleModelBean getBubbleModel() {
            return this.BubbleModel;
        }

        public void setBubbleModel(BubbleModelBean bubbleModelBean) {
            this.BubbleModel = bubbleModelBean;
        }

        public AppShareInfoBean getAppShareInfo() {
            return this.AppShareInfo;
        }

        public void setAppShareInfo(AppShareInfoBean appShareInfoBean) {
            this.AppShareInfo = appShareInfoBean;
        }

        public WGPopConfigBean getWGPopConfig() {
            return this.WGPopConfig;
        }

        public void setWGPopConfig(WGPopConfigBean wGPopConfigBean) {
            this.WGPopConfig = wGPopConfigBean;
        }

        public LhpCourceInfoBean getLhpCourceInfo() {
            return this.lhpCourceInfo;
        }

        public void setLhpCourceInfo(LhpCourceInfoBean lhpCourceInfoBean) {
            this.lhpCourceInfo = lhpCourceInfoBean;
        }

        public String getCustomerURL() {
            return this.customerURL;
        }

        public void setCustomerURL(String str) {
            this.customerURL = str;
        }

        public String getCertificateURL() {
            return this.certificateURL;
        }

        public void setCertificateURL(String str) {
            this.certificateURL = str;
        }

        public List<?> getRootBlackList() {
            return this.RootBlackList;
        }

        public void setRootBlackList(List<?> list) {
            this.RootBlackList = list;
        }

        public List<GameBlackListBean> getGameBlackList() {
            return this.GameBlackList;
        }

        public void setGameBlackList(List<GameBlackListBean> list) {
            this.GameBlackList = list;
        }

        public List<HomeShortcutsBean> getHomeShortcuts() {
            return this.HomeShortcuts;
        }

        public void setHomeShortcuts(List<HomeShortcutsBean> list) {
            this.HomeShortcuts = list;
        }

        /* loaded from: classes2.dex */
        public static class BubbleModelBean {
            private int bubble;
            private String searchkey;

            public int getBubble() {
                return this.bubble;
            }

            public void setBubble(int i) {
                this.bubble = i;
            }

            public String getSearchkey() {
                return this.searchkey;
            }

            public void setSearchkey(String str) {
                this.searchkey = str;
            }
        }

        /* loaded from: classes2.dex */
        public static class AppShareInfoBean {
            private String Content;
            private String Logo;
            private String Title;
            private String Url;

            public String getTitle() {
                return this.Title;
            }

            public void setTitle(String str) {
                this.Title = str;
            }

            public String getContent() {
                return this.Content;
            }

            public void setContent(String str) {
                this.Content = str;
            }

            public String getLogo() {
                return this.Logo;
            }

            public void setLogo(String str) {
                this.Logo = str;
            }

            public String getUrl() {
                return this.Url;
            }

            public void setUrl(String str) {
                this.Url = str;
            }
        }

        /* loaded from: classes2.dex */
        public static class WGPopConfigBean {
            private AppInfoBean appInfo;
            private int isOpen;
            private String popImg;

            public int getIsOpen() {
                return this.isOpen;
            }

            public void setIsOpen(int i) {
                this.isOpen = i;
            }

            public String getPopImg() {
                return this.popImg;
            }

            public void setPopImg(String str) {
                this.popImg = str;
            }

            public AppInfoBean getAppInfo() {
                return this.appInfo;
            }

            public void setAppInfo(AppInfoBean appInfoBean) {
                this.appInfo = appInfoBean;
            }

            /* loaded from: classes2.dex */
            public static class AppInfoBean {
                private String AppName;
                private String AppUrl;
                private String Package;

                public String getAppName() {
                    return this.AppName;
                }

                public void setAppName(String str) {
                    this.AppName = str;
                }

                public String getPackage() {
                    return this.Package;
                }

                public void setPackage(String str) {
                    this.Package = str;
                }

                public String getAppUrl() {
                    return this.AppUrl;
                }

                public void setAppUrl(String str) {
                    this.AppUrl = str;
                }
            }
        }

        /* loaded from: classes2.dex */
        public static class LhpCourceInfoBean {
            private boolean isInet;
            private String lhpCourceURL;

            public String getLhpCourceURL() {
                return this.lhpCourceURL;
            }

            public void setLhpCourceURL(String str) {
                this.lhpCourceURL = str;
            }

            public boolean isIsInet() {
                return this.isInet;
            }

            public void setIsInet(boolean z) {
                this.isInet = z;
            }
        }

        /* loaded from: classes2.dex */
        public static class GameBlackListBean {
            private String GameName;
            private String PackageNames;

            public String getGameName() {
                return this.GameName;
            }

            public void setGameName(String str) {
                this.GameName = str;
            }

            public String getPackageNames() {
                return this.PackageNames;
            }

            public void setPackageNames(String str) {
                this.PackageNames = str;
            }
        }

        /* loaded from: classes2.dex */
        public static class HomeShortcutsBean {
            private String Data;
            private String Icon;
            private String Icon1;
            private String Name;
            private Object NavigationType;
            private int TargetType;
            private Object minIco;
            private Object minIco1;

            public Object getMinIco() {
                return this.minIco;
            }

            public void setMinIco(Object obj) {
                this.minIco = obj;
            }

            public Object getMinIco1() {
                return this.minIco1;
            }

            public void setMinIco1(Object obj) {
                this.minIco1 = obj;
            }

            public String getName() {
                return this.Name;
            }

            public void setName(String str) {
                this.Name = str;
            }

            public String getData() {
                return this.Data;
            }

            public void setData(String str) {
                this.Data = str;
            }

            public int getTargetType() {
                return this.TargetType;
            }

            public void setTargetType(int i) {
                this.TargetType = i;
            }

            public String getIcon() {
                return this.Icon;
            }

            public void setIcon(String str) {
                this.Icon = str;
            }

            public Object getNavigationType() {
                return this.NavigationType;
            }

            public void setNavigationType(Object obj) {
                this.NavigationType = obj;
            }

            public String getIcon1() {
                return this.Icon1;
            }

            public void setIcon1(String str) {
                this.Icon1 = str;
            }
        }
    }
}
