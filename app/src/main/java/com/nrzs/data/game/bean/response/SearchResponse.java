package com.nrzs.data.game.bean.response;

import com.nrzs.data.game.bean.PagesBean;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.TopicListBean;
import com.nrzs.data.game.bean.UserListBean;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class SearchResponse implements Serializable {
    private List<TopicListBean> TopicList;
    private List<UserListBean> UserList;
    private PagesBean pages;
    private List<RdataBean> rdata;

    public PagesBean getPages() {
        return this.pages;
    }

    public void setPages(PagesBean pagesBean) {
        this.pages = pagesBean;
    }

    public List<TopicListBean> getTopicList() {
        return this.TopicList;
    }

    public void setTopicList(List<TopicListBean> list) {
        this.TopicList = list;
    }

    public List<UserListBean> getUserList() {
        return this.UserList;
    }

    public void setUserList(List<UserListBean> list) {
        this.UserList = list;
    }

    public List<RdataBean> getRdata() {
        return this.rdata;
    }

    public void setRdata(List<RdataBean> list) {
        this.rdata = list;
    }
}
