package com.nrzs.data.game.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class TopTwitterBean implements Serializable, Cloneable {
    private int Attention;
    private String AuthorTitle;
    private int CNum;
    private String EncryptKey;
    private int Favorite;
    private String HeadImgPath;
    private String Ico;
    private int IfAuthentic;
    private int IfLike;
    private int IfReTrans;
    private int IsEncrypt;
    private int Likes;
    private String LikesStr;
    private String NickName;
    private String OnlyID;
    private String ReleaseDateStr;
    private List<ReplyListBean> ReplyList;
    private int ScriptAuthorID;
    private int ScriptID;
    private String ScriptIco;
    private String ScriptName;
    private String ScriptPath;
    private String ScriptVersion;
    private boolean SportXBY;
    private boolean SportYGJ;
    private boolean ToolVip;
    private String TopTitle;
    private int TopicID;
    private TranInfoBean TranInfo;
    private String TwitterContent;
    private int TwitterID;
    private int UserID;
    private int Watermark;

    public String getTopTitle() {
        return this.TopTitle;
    }

    public void setTopTitle(String str) {
        this.TopTitle = str;
    }

    public int getTwitterID() {
        return this.TwitterID;
    }

    public void setTwitterID(int i) {
        this.TwitterID = i;
    }

    public String getNickName() {
        return this.NickName;
    }

    public void setNickName(String str) {
        this.NickName = str;
    }

    public String getHeadImgPath() {
        return this.HeadImgPath;
    }

    public void setHeadImgPath(String str) {
        this.HeadImgPath = str;
    }

    public String getReleaseDateStr() {
        return this.ReleaseDateStr;
    }

    public void setReleaseDateStr(String str) {
        this.ReleaseDateStr = str;
    }

    public String getTwitterContent() {
        return this.TwitterContent;
    }

    public void setTwitterContent(String str) {
        this.TwitterContent = str;
    }

    public int getFavorite() {
        return this.Favorite;
    }

    public void setFavorite(int i) {
        this.Favorite = i;
    }

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int i) {
        this.UserID = i;
    }

    public int getAttention() {
        return this.Attention;
    }

    public void setAttention(int i) {
        this.Attention = i;
    }

    public int getTopicID() {
        return this.TopicID;
    }

    public void setTopicID(int i) {
        this.TopicID = i;
    }

    public int getScriptID() {
        return this.ScriptID;
    }

    public void setScriptID(int i) {
        this.ScriptID = i;
    }

    public String getScriptIco() {
        return this.ScriptIco;
    }

    public void setScriptIco(String str) {
        this.ScriptIco = str;
    }

    public String getScriptPath() {
        return this.ScriptPath;
    }

    public void setScriptPath(String str) {
        this.ScriptPath = str;
    }

    public String getScriptName() {
        return this.ScriptName;
    }

    public void setScriptName(String str) {
        this.ScriptName = str;
    }

    public int getLikes() {
        return this.Likes;
    }

    public void setLikes(int i) {
        this.Likes = i;
    }

    public String getLikesStr() {
        return this.LikesStr;
    }

    public void setLikesStr(String str) {
        this.LikesStr = str;
    }

    public int getIfLike() {
        return this.IfLike;
    }

    public void setIfLike(int i) {
        this.IfLike = i;
    }

    public int getCNum() {
        return this.CNum;
    }

    public void setCNum(int i) {
        this.CNum = i;
    }

    public int getIfReTrans() {
        return this.IfReTrans;
    }

    public void setIfReTrans(int i) {
        this.IfReTrans = i;
    }

    public TranInfoBean getTranInfo() {
        return this.TranInfo;
    }

    public void setTranInfo(TranInfoBean tranInfoBean) {
        this.TranInfo = tranInfoBean;
    }

    public String getScriptVersion() {
        return this.ScriptVersion;
    }

    public void setScriptVersion(String str) {
        this.ScriptVersion = str;
    }

    public String getOnlyID() {
        return this.OnlyID;
    }

    public void setOnlyID(String str) {
        this.OnlyID = str;
    }

    public int getScriptAuthorID() {
        return this.ScriptAuthorID;
    }

    public void setScriptAuthorID(int i) {
        this.ScriptAuthorID = i;
    }

    public String getAuthorTitle() {
        return this.AuthorTitle;
    }

    public void setAuthorTitle(String str) {
        this.AuthorTitle = str;
    }

    public String getIco() {
        return this.Ico;
    }

    public void setIco(String str) {
        this.Ico = str;
    }

    public int getIfAuthentic() {
        return this.IfAuthentic;
    }

    public void setIfAuthentic(int i) {
        this.IfAuthentic = i;
    }

    public int getWatermark() {
        return this.Watermark;
    }

    public void setWatermark(int i) {
        this.Watermark = i;
    }

    public boolean isToolVip() {
        return this.ToolVip;
    }

    public void setToolVip(boolean z) {
        this.ToolVip = z;
    }

    public int getIsEncrypt() {
        return this.IsEncrypt;
    }

    public void setIsEncrypt(int i) {
        this.IsEncrypt = i;
    }

    public String getEncryptKey() {
        return this.EncryptKey;
    }

    public void setEncryptKey(String str) {
        this.EncryptKey = str;
    }

    public boolean isSportYGJ() {
        return this.SportYGJ;
    }

    public void setSportYGJ(boolean z) {
        this.SportYGJ = z;
    }

    public boolean isSportXBY() {
        return this.SportXBY;
    }

    public void setSportXBY(boolean z) {
        this.SportXBY = z;
    }

    public List<ReplyListBean> getReplyList() {
        return this.ReplyList;
    }

    public void setReplyList(List<ReplyListBean> list) {
        this.ReplyList = list;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
