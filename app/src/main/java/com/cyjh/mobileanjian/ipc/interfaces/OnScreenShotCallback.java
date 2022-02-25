package com.cyjh.mobileanjian.ipc.interfaces;

import com.google.protobuf.ByteString;

/* loaded from: classes.dex */
public interface OnScreenShotCallback {
    void onScreenShotDone(String str, ByteString byteString);

    void onScreenShotFailed(int i);
}
