package com.nrzs.data.game.bean;

/* loaded from: classes2.dex */
public class AuthorScriptBean {
    private double Gold;

    /* renamed from: ID */
    private long f10643ID;
    private String OnlyID;
    private String ReleaseDate;
    private String ScriptIcon;
    private String ScriptName;
    private int ShowOrder;
    private long TopicId;
    public String TopicName;

    public long getID() {
        return this.f10643ID;
    }

    public void setID(long j) {
        this.f10643ID = j;
    }

    public String getOnlyID() {
        return this.OnlyID;
    }

    public void setOnlyID(String str) {
        this.OnlyID = str;
    }

    public String getScriptName() {
        return this.ScriptName;
    }

    public void setScriptName(String str) {
        this.ScriptName = str;
    }

    public long getTopicId() {
        return this.TopicId;
    }

    public void setTopicId(long j) {
        this.TopicId = j;
    }

    public String getReleaseDate() {
        return this.ReleaseDate;
    }

    public void setReleaseDate(String str) {
        this.ReleaseDate = str;
    }

    public double getGold() {
        return this.Gold;
    }

    public void setGold(double d) {
        this.Gold = d;
    }

    public String getTopicName() {
        return this.TopicName;
    }

    public void setTopicName(String str) {
        this.TopicName = str;
    }

    public int getShowOrder() {
        return this.ShowOrder;
    }

    public void setShowOrder(int i) {
        this.ShowOrder = i;
    }

    public String getScriptIcon() {
        return this.ScriptIcon;
    }

    public void setScriptIcon(String str) {
        this.ScriptIcon = str;
    }

    public String toString() {
        return "AuthorScriptBean{ID=" + this.f10643ID + ", OnlyID='" + this.OnlyID + "', ScriptName='" + this.ScriptName + "', TopicId=" + this.TopicId + ", ReleaseDate='" + this.ReleaseDate + "', Gold=" + this.Gold + ", TopicName='" + this.TopicName + "', ShowOrder=" + this.ShowOrder + ", ScriptIcon=" + this.ScriptIcon + '}';
    }
}
