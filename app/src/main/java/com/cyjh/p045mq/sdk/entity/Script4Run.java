package com.cyjh.p045mq.sdk.entity;

import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.p045mq.sdk.inf.IScript;
import com.cyjh.p045mq.sdk.inf.ISerializableMessage;

/* renamed from: com.cyjh.mq.sdk.entity.Script4Run */
/* loaded from: classes.dex */
public class Script4Run implements IScript, ISerializableMessage, Cloneable {
    public static final int IGNORE_REPEAT = -1;
    public static final int IGNORE_TIME = -2;
    public static final int LOOP = 0;
    public static final int ONE_TIME = 1;
    public String appId;
    public String atcPath;
    public int duration;
    public boolean encrypt;
    public long encryptKey;
    public boolean isFengwoScript;
    public String lcPath;
    public int repeat;
    public int trialTime;
    public String uiCfgPath;
    public String username;

    public Script4Run() {
        this.lcPath = "";
        this.atcPath = "";
        this.uiCfgPath = "";
        this.appId = "";
        this.username = "";
        this.trialTime = 0;
        this.repeat = 1;
        this.duration = -2;
        this.encryptKey = 0L;
        this.encrypt = false;
        this.isFengwoScript = true;
    }

    public Script4Run(String str, String str2, String str3) {
        this.lcPath = "";
        this.atcPath = "";
        this.uiCfgPath = "";
        this.appId = "";
        this.username = "";
        this.trialTime = 0;
        this.repeat = 1;
        this.duration = -2;
        this.encryptKey = 0L;
        this.encrypt = false;
        this.isFengwoScript = true;
        this.lcPath = str;
        this.atcPath = str2;
        this.uiCfgPath = str3;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setLcPath(String str) {
        this.lcPath = str;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setAtcPath(String str) {
        this.atcPath = str;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setConfigPath(String str) {
        this.uiCfgPath = str;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setAppId(String str) {
        this.appId = str;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setUserName(String str) {
        this.username = str;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setEncryptKey(long j) {
        this.encryptKey = j;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setEncrypt(boolean z) {
        this.encrypt = z;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setIsFengwoScript(boolean z) {
        this.isFengwoScript = z;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public String getLcPath() {
        return this.lcPath;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public String getAtcPath() {
        return this.atcPath;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public String getConfigPath() {
        return this.uiCfgPath;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public String getAppId() {
        return this.appId;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public String getUserName() {
        return this.username;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public long getEncryptKey() {
        return this.encryptKey;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public boolean isEncrypt() {
        return this.encrypt;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public boolean isFengwoScript() {
        return this.isFengwoScript;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setRepeat(int i) {
        if (i >= 0) {
            this.repeat = i;
            this.duration = -2;
        }
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public int getRepeat() {
        return this.repeat;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setTimeInMinutes(int i) {
        if (i > 0) {
            this.duration = i;
            this.repeat = -1;
        }
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public int getTimeInMinutes() {
        return this.duration;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public IScript setTrialTime(int i) {
        this.trialTime = i;
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IScript
    public int getTrialTime() {
        return this.trialTime;
    }

    @Override // com.cyjh.p045mq.sdk.inf.ISerializableMessage
    public Ipc.IpcMessage toMessage(int i) {
        Ipc.IpcMessage.Builder cmd = Ipc.IpcMessage.newBuilder().setCmd(i);
        for (int i2 = 0; i2 < 5; i2++) {
            switch (i2) {
                case 0:
                    cmd.addArg1(this.trialTime);
                    cmd.addArg2(this.lcPath);
                    cmd.addArg4(this.encryptKey);
                    break;
                case 1:
                    cmd.addArg1(this.repeat);
                    cmd.addArg2(this.atcPath);
                    break;
                case 2:
                    cmd.addArg1(this.duration);
                    cmd.addArg2(this.uiCfgPath);
                    break;
                case 3:
                    cmd.addArg1(this.isFengwoScript ? 1 : 0);
                    cmd.addArg2(this.appId);
                    break;
                case 4:
                    cmd.addArg2(this.username);
                    break;
            }
        }
        cmd.setEncrypt(this.encrypt);
        return cmd.build();
    }

    public static Script4Run parseFromMessage(Ipc.IpcMessage ipcMessage) {
        Script4Run script4Run = new Script4Run();
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    script4Run.setTrialTime(ipcMessage.getArg1(i)).setLcPath(ipcMessage.getArg2(i)).setEncryptKey(ipcMessage.getArg4(i));
                    break;
                case 1:
                    script4Run.setRepeat(ipcMessage.getArg1(i)).setAtcPath(ipcMessage.getArg2(i));
                    break;
                case 2:
                    script4Run.setTimeInMinutes(ipcMessage.getArg1(i)).setConfigPath(ipcMessage.getArg2(i));
                    break;
                case 3:
                    script4Run.setEncrypt(ipcMessage.getArg1(i) != 0).setAppId(ipcMessage.getArg2(i));
                    break;
                case 4:
                    script4Run.setUserName(ipcMessage.getArg2(i));
                    break;
            }
        }
        script4Run.setEncrypt(ipcMessage.getEncrypt());
        return script4Run;
    }

    public Script4Run clone() {
        try {
            return (Script4Run) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "lcPath=" + getLcPath() + "\natcPath=" + getAtcPath() + "\nuiCfgPath=" + getConfigPath() + "\nappId=" + getAppId() + "\nusername=" + getUserName() + "\ntrialTime=" + getTrialTime() + "\nrepeat=" + getRepeat() + "\nduration=" + getTimeInMinutes() + "\nencryptKey=" + getEncryptKey() + "\nencrypt=" + isEncrypt() + '\n';
    }
}
