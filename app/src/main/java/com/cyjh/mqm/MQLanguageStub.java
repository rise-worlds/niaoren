package com.cyjh.mqm;

import android.app.Application;
import com.cyjh.event.DebugHelper;
import java.util.ArrayList;

public class MQLanguageStub {
    public static final int SCRIPT_COMPILECODE_SUCCEED = 0;
    public static final int SCRIPT_RUNCODE_SUCCEED = 0;
    public static final int TYPE = 2;
    private volatile long a = 0L;
    private volatile long b = 0L;

    static
    {
        System.loadLibrary("mqm");
    }

    public static native void InitElf(String paramString1, String paramString2, String paramString3);

    public static native void InitHost(String paramString);

    public static native void SetHeartBeatTime(int paramInt1, int paramInt2);

    public static native void SetRegCode(String paramString);

    public native int Compile(String paramString1, String paramString2, String paramString3, ArrayList<String> paramArrayList);

    public native int Debug(String paramString1, String paramString2, String paramString3, int paramInt);

    public byte[] DebugMessage(byte[] paramArrayOfByte)
    {
        return DebugHelper.m21096a();
    }

    public native void InitRunner(Application paramApplication, String paramString);

    public native int Pause();

    public native void Request(String paramString);

    public native int Resume();

    public native int Run(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, long paramLong);

    public native int Run(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, long paramLong);

    public native int Run(byte[] paramArrayOfByte, String paramString1, String paramString2);

    public native int Run(byte[] paramArrayOfByte, String paramString1, String paramString2, int paramInt1, int paramInt2, long paramLong);

    public native void SetDeviceSessionId(String paramString);

    public native void SetLocalDir(String paramString1, String paramString2, String paramString3);

    public native void SetSid(long paramLong);

    public native void SetWriteLog2File(boolean paramBoolean);

    public native int Stop();

    public synchronized long getGundamRunner()
    {
        return this.b;
    }

    public synchronized long getRunner()
    {
        return this.a;
    }

    public synchronized void setGundamRunner(long paramLong)
    {
        this.b = paramLong;
    }

    public synchronized void setRunner(long paramLong)
    {
        this.a = paramLong;
    }

    public static class MQAuxiliary
    {
        public MQAuxiliary() {}

        public native void Clear();

        public native int CompareColorEx(String paramString, float paramFloat);

        public native boolean FindMultiColor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, String paramString2, int paramInt5, float paramFloat, int[] paramArrayOfInt);

        public native void Init();

        public native void KeepCapture(boolean paramBoolean);

        public native byte[] ScreenCap(int[] paramArrayOfInt);

        public native void SetImageCrop(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4);

        public native void SetScreenRotation(int paramInt);

        public native void SetScreenScale(float paramFloat1, float paramFloat2);

        public native int WaitKey();
    }
}
