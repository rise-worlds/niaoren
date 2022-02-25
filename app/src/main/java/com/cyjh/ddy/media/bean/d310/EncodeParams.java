package com.cyjh.ddy.media.bean.d310;

import com.blankj.utilcode.util.GsonUtils;

/* loaded from: classes.dex */
public class EncodeParams {
    public int audio_port;
    public int bitrate;
    public int encode_place;
    public String order_id;
    public String other_param;
    public String phoneIP;
    public String sign;
    public int video_port;
    public int videowidth = -1;
    public int videoheight = -1;
    public int transport_mode = 1;
    public int encode_type = 0;
    public int isPush = 0;

    public String toJson() {
        return GsonUtils.m22058a(this);
    }
}
