package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class RdataBean implements Serializable, Cloneable {
    private int AuthorRewardSGBTotalNum;
    private String AuthorRewardSGBTotalNumStr;
    private int AuthorRewardSGBUserNum;
    private String AuthorTitle;
    private float Gold;
    private String HeadImgPath;
    private String Ico;
    private int IfAuthentic;
    private String NickName;
    private String OnlyID;
    private String ReleaseDateStr;
    private int ScriptAuthorID;
    private long ScriptID;
    private String ScriptIco;
    private String ScriptName;
    private String ScriptPath;
    private double ScriptScore;
    private String ScriptVersion;
    private boolean ToolVip;
    private int TopicID;
    private String TwitterContent;
    private int TwitterID;
    private int UseOcrText;
    private int UserID;

    public int getUseOcrText() {
        return this.UseOcrText;
    }

    public void setUseOcrText(int i) {
        this.UseOcrText = i;
    }

    public int getAuthorRewardSGBTotalNum() {
        return this.AuthorRewardSGBTotalNum;
    }

    public String getAuthorRewardSGBTotalNumStr() {
        return this.AuthorRewardSGBTotalNumStr;
    }

    public void setAuthorRewardSGBTotalNumStr(String str) {
        this.AuthorRewardSGBTotalNumStr = str;
    }

    public void setAuthorRewardSGBTotalNum(int i) {
        this.AuthorRewardSGBTotalNum = i;
    }

    public int getAuthorRewardSGBUserNum() {
        return this.AuthorRewardSGBUserNum;
    }

    public void setAuthorRewardSGBUserNum(int i) {
        this.AuthorRewardSGBUserNum = i;
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

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int i) {
        this.UserID = i;
    }

    public int getTopicID() {
        return this.TopicID;
    }

    public void setTopicID(int i) {
        this.TopicID = i;
    }

    public long getScriptID() {
        return this.ScriptID;
    }

    public float getGold() {
        return this.Gold;
    }

    public void setGold(float f) {
        this.Gold = f;
    }

    public void setScriptID(long j) {
        this.ScriptID = j;
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

    public boolean isToolVip() {
        return this.ToolVip;
    }

    public void setToolVip(boolean z) {
        this.ToolVip = z;
    }

    public double getScriptScore() {
        return this.ScriptScore;
    }

    public void setScriptScore(double d) {
        this.ScriptScore = d;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
