package com.cyjh.ddysdk.device.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.annotation.IntRange;
import com.blankj.utilcode.util.EncodeUtils;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.oksocket.ControlSocket;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddy.media.oksocket.SocketHelper;
import com.cyjh.ddysdk.device.camera.NV21EncoderH264;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.bean.DefineOrderInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.tools.ant.taskdefs.email.EmailTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DdyDeviceCameraHelper implements NoProGuard, DdyDeviceCameraContract, NV21EncoderH264.EncoderListener {

    /* renamed from: a */
    private static final String f7688a = "sdk-cameraIng";

    /* renamed from: b */
    private static DdyDeviceCameraHelper f7689b;

    /* renamed from: c */
    private Camera f7690c;

    /* renamed from: d */
    private ControlSocket f7691d;

    /* renamed from: e */
    private SurfaceTexture f7692e;

    /* renamed from: i */
    private NV21EncoderH264 f7696i;

    /* renamed from: f */
    private String f7693f = "cameraData";

    /* renamed from: g */
    private int f7694g = 0;

    /* renamed from: h */
    private int f7695h = 1000;

    /* renamed from: j */
    private int f7697j = 1280;

    /* renamed from: k */
    private int f7698k = 720;

    /* renamed from: l */
    private boolean f7699l = false;

    /* renamed from: m */
    private int f7700m = 20;

    public void setFrameRate(@IntRange(from = 10, m25695to = 30) int i) {
        this.f7700m = i;
        NV21EncoderH264 nV21EncoderH264 = this.f7696i;
        if (nV21EncoderH264 != null) {
            nV21EncoderH264.m21291a(i);
        }
    }

    public static DdyDeviceCameraHelper getInstance() {
        if (f7689b == null) {
            f7689b = new DdyDeviceCameraHelper();
        }
        return f7689b;
    }

    @Override // com.cyjh.ddysdk.device.camera.DdyDeviceCameraContract
    public void upMediaPing(int i) {
        this.f7694g = i;
    }

    @Override // com.cyjh.ddysdk.device.camera.DdyDeviceCameraContract
    public void start(DdyOrderInfo ddyOrderInfo, String str, int i) {
        stop();
        this.f7695h = i;
        DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f7697j = jSONObject.optInt("width");
            this.f7698k = jSONObject.optInt("height");
            this.f7699l = jSONObject.optBoolean("isFace");
            CLog.m21882i("sdk-cameraIng", defineOrderInfo.DeviceTcpHost + "width" + this.f7697j + "height" + this.f7698k + "isFace" + this.f7699l);
            if (this.f7699l) {
                this.f7693f = "cameraData01";
            } else {
                this.f7693f = "cameraData";
            }
            this.f7691d = new ControlSocket(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.camera.DdyDeviceCameraHelper.1
                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
                public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                    CLog.m21882i("sdk-cameraIng", "onMessage()");
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onConnected(ControlSocket controlSocket) {
                    CLog.m21882i("sdk-cameraIng", "onConnected()");
                    DdyDeviceCameraHelper ddyDeviceCameraHelper = DdyDeviceCameraHelper.this;
                    ddyDeviceCameraHelper.m21298a(ddyDeviceCameraHelper.f7699l, DdyDeviceCameraHelper.this.f7697j, DdyDeviceCameraHelper.this.f7698k);
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onSended(ControlSocket controlSocket) {
                    CLog.m21882i("sdk-cameraIng", "onSended()");
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onFailure(ControlSocket controlSocket, String str2) {
                    CLog.m21883e("sdk-cameraIng", "onFailure()" + str2);
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onClosed(String str2) {
                    CLog.m21882i("sdk-cameraIng", "onClosed()" + str2);
                }
            });
        } catch (JSONException e) {
            CLog.m21883e("sdk-cameraIng", "start " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21298a(boolean z, int i, int i2) {
        CLog.m21882i("sdk-cameraIng", "openCamera");
        if (z) {
            this.f7690c = Camera.open(1);
        } else {
            this.f7690c = Camera.open(0);
        }
        Camera.Parameters parameters = this.f7690c.getParameters();
        if (!z) {
            parameters.setFocusMode(EmailTask.AUTO);
        }
        CLog.m21882i("sdk-cameraIng", "swidth" + i + "sheight" + i2);
        Camera.Size a = m21301a(parameters, i, i2);
        CLog.m21882i("sdk-cameraIng", "ewidth" + a.width + "eheight" + a.height);
        int i3 = a.width;
        int i4 = a.height;
        parameters.setPreviewFormat(17);
        parameters.setPreviewSize(i3, i4);
        this.f7690c.setParameters(parameters);
        try {
            this.f7692e = new SurfaceTexture(36197);
            this.f7690c.setPreviewTexture(this.f7692e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f7696i = new NV21EncoderH264(i3, i4);
        this.f7696i.m21289a(this);
        this.f7696i.m21291a(this.f7700m);
        this.f7690c.setPreviewCallback(new Camera.PreviewCallback() { // from class: com.cyjh.ddysdk.device.camera.DdyDeviceCameraHelper.2
            @Override // android.hardware.Camera.PreviewCallback
            public void onPreviewFrame(byte[] bArr, Camera camera) {
                if (DdyDeviceCameraHelper.this.f7694g < DdyDeviceCameraHelper.this.f7695h) {
                    DdyDeviceCameraHelper.this.f7696i.m21285a(bArr);
                } else {
                    CLog.m21882i("sdk-cameraIng", "Caton");
                }
            }
        });
        this.f7690c.startPreview();
    }

    /* renamed from: a */
    private Camera.Size m21301a(Camera.Parameters parameters, int i, int i2) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            if (i > i2) {
                if (size.width <= i && size.height <= i2) {
                    arrayList.add(size);
                }
            } else if (size.width <= i2 && size.height <= i) {
                arrayList.add(size);
            }
        }
        if (arrayList.size() > 0) {
            return (Camera.Size) Collections.max(arrayList, new Comparator<Camera.Size>() { // from class: com.cyjh.ddysdk.device.camera.DdyDeviceCameraHelper.3
                public int compare(Camera.Size size2, Camera.Size size3) {
                    return Long.signum((size2.width * size2.height) - (size3.width * size3.height));
                }
            });
        }
        return supportedPreviewSizes.get(0);
    }

    @Override // com.cyjh.ddysdk.device.camera.DdyDeviceCameraContract
    public void stop() {
        CLog.m21882i("sdk-cameraIng", "stopCamera");
        NV21EncoderH264 nV21EncoderH264 = this.f7696i;
        if (nV21EncoderH264 != null) {
            nV21EncoderH264.m21292a();
        }
        Camera camera = this.f7690c;
        if (camera != null) {
            camera.setPreviewCallback(null);
            this.f7690c.stopPreview();
            this.f7690c.release();
            this.f7690c = null;
        }
        ControlSocket controlSocket = this.f7691d;
        if (controlSocket != null) {
            controlSocket.m21538a();
        }
    }

    @Override // com.cyjh.ddysdk.device.camera.NV21EncoderH264.EncoderListener
    public void h264(byte[] bArr) {
        CLog.m21882i("sdk-cameraIngup", "doUpCamera" + bArr.length);
        this.f7691d.m21534a(SocketHelper.m21520a(this.f7693f, EncodeUtils.m22387b(bArr), 7, ""));
        CLog.m21882i("sdk-cameraIngup", "doUpCameraEnd");
    }
}
