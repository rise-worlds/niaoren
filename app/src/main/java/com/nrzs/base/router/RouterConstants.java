package com.nrzs.base.router;

/* loaded from: classes.dex */
public class RouterConstants {
    public static final String MAIN = "/app/activity/main";
    public static final String WEB = "/app/activity/web";
    public static final String WEBVIEW_ACTION = "/webview/activity/actoin";

    /* loaded from: classes.dex */
    public static class ModuleGame {
        public static final String ALL = "/game/activity/all";
        public static final String LOCAL = "/game/activity/local";
        public static final String SCRIPT_INFO = "/game/activity/script_info";
        public static final String SEARCH = "/game/activity/search";
        public static final String TOPIC = "/game/activity/topic";
    }

    /* loaded from: classes.dex */
    public static class ModuleUser {
        public static final String ALTER_PASSWORD = "/user/activity/alter_password";
        public static final String CHOOSE_GAME = "/user/activity/choose_game";
        public static final String CHOOSE_SCTIPT = "/user/activity/choose_script";
        public static final String KICK_OUT = "/user/activity/kickout";
        public static final String LOGIN = "/user/activity/login";
        public static final String QUESTION = "/user/activity/question";
        public static final String REGISTER = "/user/activity/register";
        public static final String REWARD = "/ui/activity/reward";
    }

    /* loaded from: classes.dex */
    public static class ModuleXNKJ {
        public static final String START = "/xnkj/activity/start";
        public static final String XNKJ = "/xnkj/fragment/xnkj";
    }

    /* loaded from: classes.dex */
    public static class Provider {
        public static final String PROVIDER_APP = "/app/provider/invoke";
        public static final String PROVIDER_GAME = "/game/provider/invoke";
        public static final String PROVIDER_GETCHANNEL = "/data/provider/channel";
        public static final String PROVIDER_PAY = "/pay/provider/success";
        public static final String PROVIDER_TOWEB_PAY = "/data/provider/pay";
        public static final String PROVIDER_USER = "/user/provider/invoke";
        public static final String PROVIDER_XNKJ_RUN = "/xnkj/provider/run";
    }
}
