package com.nrzs.data.xnkj.bean.response;

/* loaded from: classes2.dex */
public class XJBaseAppReponse<T> {
    public int Code;
    public T Data;
    public String Msg;

    public String toString() {
        return "XJBaseAppReponse{Code=" + this.Code + ", Msg='" + this.Msg + "', Data=" + this.Data + '}';
    }
}
