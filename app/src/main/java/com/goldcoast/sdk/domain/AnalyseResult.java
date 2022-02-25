package com.goldcoast.sdk.domain;

/* loaded from: classes.dex */
public interface AnalyseResult {
    void onException(String str);

    void onFailed(String str);

    void onProgress(String str);

    void onSuccess(String str);
}
