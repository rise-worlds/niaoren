package com.nrzs.data.game.bean.response;

import com.nrzs.data.game.bean.GameVIPInfoBean;
import com.nrzs.data.game.bean.PlatformVIPInfoBean;
import com.nrzs.data.game.bean.TwitterInfo;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class ScriptInfoResponse implements Serializable {
    private GameVIPInfoBean GameVIPInfo;
    private PlatformVIPInfoBean PlatformVIPInfo;
    private TwitterInfo TwitterInfo;

    public TwitterInfo getTwitterInfo() {
        return this.TwitterInfo;
    }

    public void setTwitterInfo(TwitterInfo twitterInfo) {
        this.TwitterInfo = twitterInfo;
    }

    public GameVIPInfoBean getGameVIPInfo() {
        return this.GameVIPInfo;
    }

    public void setGameVIPInfo(GameVIPInfoBean gameVIPInfoBean) {
        this.GameVIPInfo = gameVIPInfoBean;
    }

    public PlatformVIPInfoBean getPlatformVIPInfo() {
        return this.PlatformVIPInfo;
    }

    public void setPlatformVIPInfo(PlatformVIPInfoBean platformVIPInfoBean) {
        this.PlatformVIPInfo = platformVIPInfoBean;
    }
}
