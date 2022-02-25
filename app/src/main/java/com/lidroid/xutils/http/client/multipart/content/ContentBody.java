package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public interface ContentBody extends ContentDescriptor {
    String getFilename();

    void setCallBackInfo(MultipartEntity.CallBackInfo callBackInfo);

    void writeTo(OutputStream outputStream) throws IOException;
}
