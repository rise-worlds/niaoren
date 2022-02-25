package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.http.client.multipart.MultipartEntity;

/* loaded from: classes.dex */
public abstract class AbstractContentBody implements ContentBody {
    protected MultipartEntity.CallBackInfo callBackInfo = MultipartEntity.CallBackInfo.DEFAULT;
    private final String mediaType;
    private final String mimeType;
    private final String subType;

    public AbstractContentBody(String str) {
        if (str != null) {
            this.mimeType = str;
            int indexOf = str.indexOf(47);
            if (indexOf != -1) {
                this.mediaType = str.substring(0, indexOf);
                this.subType = str.substring(indexOf + 1);
                return;
            }
            this.mediaType = str;
            this.subType = null;
            return;
        }
        throw new IllegalArgumentException("MIME type may not be null");
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getMimeType() {
        return this.mimeType;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getMediaType() {
        return this.mediaType;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getSubType() {
        return this.subType;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void setCallBackInfo(MultipartEntity.CallBackInfo callBackInfo) {
        this.callBackInfo = callBackInfo;
    }
}
