package com.lidroid.xutils.http.callback;

import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;

/* loaded from: classes.dex */
public class StringDownloadHandler {
    /* JADX WARN: Finally extract failed */
    public String handleEntity(HttpEntity httpEntity, RequestCallBackHandler requestCallBackHandler, String str) throws IOException {
        long j;
        InputStream inputStream = null;
        if (httpEntity == null) {
            return null;
        }
        long j2 = 0;
        long contentLength = httpEntity.getContentLength();
        if (requestCallBackHandler != null && !requestCallBackHandler.updateProgress(contentLength, 0L, true)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = httpEntity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append('\n');
                    j2 += OtherUtils.sizeOfString(readLine, str);
                    if (requestCallBackHandler != null && !requestCallBackHandler.updateProgress(contentLength, j2, false)) {
                        j = j2;
                        break;
                    }
                } else {
                    j = j2;
                    break;
                }
            }
            if (requestCallBackHandler != null) {
                requestCallBackHandler.updateProgress(contentLength, j, true);
            }
            IOUtils.closeQuietly(inputStream);
            return sb.toString().trim();
        } catch (Throwable th) {
            IOUtils.closeQuietly(inputStream);
            throw th;
        }
    }
}
