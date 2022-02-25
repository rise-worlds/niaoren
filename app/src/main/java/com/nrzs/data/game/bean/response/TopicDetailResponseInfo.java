package com.nrzs.data.game.bean.response;

import com.nrzs.data.game.bean.GameVIPInfoBean;
import com.nrzs.data.game.bean.PagesBean;
import com.nrzs.data.game.bean.PlatformVIPInfoBean;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.TopTwitterBean;
import com.nrzs.data.game.bean.TopicInfoBean;
import com.nrzs.data.game.bean.TopicNewsFlashBean;
import java.util.List;

/* loaded from: classes2.dex */
public class TopicDetailResponseInfo {
    private GameVIPInfoBean GameVIPInfo;
    private PlatformVIPInfoBean PlatformVIPInfo;
    private List<TopTwitterBean> TopTwitter;
    private TopicInfoBean TopicInfo;
    private TopicNewsFlashBean TopicNewsFlash;
    private PagesBean pages;
    private List<RdataBean> rdata;

    public TopicInfoBean getTopicInfo() {
        return this.TopicInfo;
    }

    public void setTopicInfo(TopicInfoBean topicInfoBean) {
        this.TopicInfo = topicInfoBean;
    }

    public TopicNewsFlashBean getTopicNewsFlash() {
        return this.TopicNewsFlash;
    }

    public void setTopicNewsFlash(TopicNewsFlashBean topicNewsFlashBean) {
        this.TopicNewsFlash = topicNewsFlashBean;
    }

    public PagesBean getPages() {
        return this.pages;
    }

    public void setPages(PagesBean pagesBean) {
        this.pages = pagesBean;
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

    public List<TopTwitterBean> getTopTwitter() {
        return this.TopTwitter;
    }

    public void setTopTwitter(List<TopTwitterBean> list) {
        this.TopTwitter = list;
    }

    public List<RdataBean> getRdata() {
        return this.rdata;
    }

    public void setRdata(List<RdataBean> list) {
        this.rdata = list;
    }
}
