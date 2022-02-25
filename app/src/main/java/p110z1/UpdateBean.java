package p110z1;

import java.io.Serializable;

/* renamed from: z1.ajk */
/* loaded from: classes3.dex */
public class UpdateBean implements Serializable {
    private String AppName;
    private String ChannelName;
    private String CreateTime;
    private int DeviceType;

    /* renamed from: Id */
    private int f16068Id;
    private String IdCode;
    private int IsCompulsory;
    private int IsOpenBox;
    private double Size;
    private String UpdateContent;
    private String Url;
    private String Version;
    private int VersionCode;

    public int getId() {
        return this.f16068Id;
    }

    public void setId(int i) {
        this.f16068Id = i;
    }

    public int getDeviceType() {
        return this.DeviceType;
    }

    public void setDeviceType(int i) {
        this.DeviceType = i;
    }

    public String getVersion() {
        return this.Version;
    }

    public void setVersion(String str) {
        this.Version = str;
    }

    public String getUrl() {
        return this.Url;
    }

    public void setUrl(String str) {
        this.Url = str;
    }

    public String getUpdateContent() {
        return this.UpdateContent;
    }

    public void setUpdateContent(String str) {
        this.UpdateContent = str;
    }

    public int getIsCompulsory() {
        return this.IsCompulsory;
    }

    public void setIsCompulsory(int i) {
        this.IsCompulsory = i;
    }

    public String getIdCode() {
        return this.IdCode;
    }

    public void setIdCode(String str) {
        this.IdCode = str;
    }

    public String getChannelName() {
        return this.ChannelName;
    }

    public void setChannelName(String str) {
        this.ChannelName = str;
    }

    public double getSize() {
        return this.Size;
    }

    public void setSize(double d) {
        this.Size = d;
    }

    public String getAppName() {
        return this.AppName;
    }

    public void setAppName(String str) {
        this.AppName = str;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String str) {
        this.CreateTime = str;
    }

    public int getIsOpenBox() {
        return this.IsOpenBox;
    }

    public void setIsOpenBox(int i) {
        this.IsOpenBox = i;
    }

    public int getVersionCode() {
        return this.VersionCode;
    }

    public void setVersionCode(int i) {
        this.VersionCode = i;
    }
}
