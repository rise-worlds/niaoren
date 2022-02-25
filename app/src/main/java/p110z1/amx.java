package p110z1;

import java.io.Serializable;

/* compiled from: FileInfo.java */
/* renamed from: z1.amx */
/* loaded from: classes3.dex */
public class amx implements Serializable {
    private String MD5;
    private String fileName;
    private int finished;
    private int length;
    private String saveDir;
    private String saveUri;
    private double speed;
    private String url;
    private boolean isStop = false;
    private boolean isDownLoading = false;

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public int getFinished() {
        return this.finished;
    }

    public void setFinished(int i) {
        this.finished = i;
    }

    public boolean isStop() {
        return this.isStop;
    }

    public void setStop(boolean z) {
        this.isStop = z;
    }

    public boolean isDownLoading() {
        return this.isDownLoading;
    }

    public void setDownLoading(boolean z) {
        this.isDownLoading = z;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    public void setSaveDir(String str) {
        this.saveDir = str;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double d) {
        this.speed = d;
    }

    public String getSaveUri() {
        return this.saveUri;
    }

    public void setSaveUri(String str) {
        this.saveUri = str;
    }

    public String getMD5() {
        return this.MD5;
    }

    public void setMD5(String str) {
        this.MD5 = str;
    }
}
