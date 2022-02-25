package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class TwitterInfo implements Serializable {
    private String AuthorName;
    private String AuthorPersonalInfo;
    private int AuthorRewardSGBTotalNum;
    private String AuthorRewardSGBTotalNumStr;
    private int AuthorRewardSGBUserNum;
    private String EncryptKey;
    private float Gold;
    private int IsEncrypt;
    private String NickName;
    private String OnlyID;
    private String ReleaseDateStr;
    private int ScriptAuthor;
    private String ScriptDesc;
    private int ScriptID;
    private String ScriptIco;
    private String ScriptName;
    private String ScriptPath;
    private double ScriptScore;
    private String ScriptVersion;
    private boolean ToolVip;
    private int TopicID;
    private int TwitterID;
    private int UseOcrText;

    public int getUseOcrText() {
        return this.UseOcrText;
    }

    public void setUseOcrText(int i) {
        this.UseOcrText = i;
    }

    public String getNickName() {
        return this.NickName;
    }

    public void setNickName(String str) {
        this.NickName = str;
    }

    public int getAuthorRewardSGBUserNum() {
        return this.AuthorRewardSGBUserNum;
    }

    public String getAuthorRewardSGBTotalNumStr() {
        return this.AuthorRewardSGBTotalNumStr;
    }

    public void setAuthorRewardSGBTotalNumStr(String str) {
        this.AuthorRewardSGBTotalNumStr = str;
    }

    public int getAuthorRewardSGBTotalNum() {
        return this.AuthorRewardSGBTotalNum;
    }

    public void setAuthorRewardSGBTotalNum(int i) {
        this.AuthorRewardSGBTotalNum = i;
    }

    public void setAuthorRewardSGBUserNum(int i) {
        this.AuthorRewardSGBUserNum = i;
    }

    public String getAuthorPersonalInfo() {
        return this.AuthorPersonalInfo;
    }

    public void setAuthorPersonalInfo(String str) {
        this.AuthorPersonalInfo = str;
    }

    public int getTwitterID() {
        return this.TwitterID;
    }

    public void setTwitterID(int i) {
        this.TwitterID = i;
    }

    public String getReleaseDateStr() {
        return this.ReleaseDateStr;
    }

    public void setReleaseDateStr(String str) {
        this.ReleaseDateStr = str;
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

    public String getScriptDesc() {
        return this.ScriptDesc;
    }

    public void setScriptDesc(String str) {
        this.ScriptDesc = str;
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

    public int getScriptAuthor() {
        return this.ScriptAuthor;
    }

    public void setScriptAuthor(int i) {
        this.ScriptAuthor = i;
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

    public String getAuthorName() {
        return this.AuthorName;
    }

    public void setAuthorName(String str) {
        this.AuthorName = str;
    }

    public double getScriptScore() {
        return this.ScriptScore;
    }

    public void setScriptScore(double d) {
        this.ScriptScore = d;
    }

    public String getScriptIco() {
        return this.ScriptIco;
    }

    public void setScriptIco(String str) {
        this.ScriptIco = str;
    }

    public float getGold() {
        return this.Gold;
    }

    public void setGold(float f) {
        this.Gold = f;
    }
}
