package com.lbd.xj.device.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.common.utils.log.LogUtils;
import java.io.IOException;
import java.util.List;

/* renamed from: com.lbd.xj.device.camera.CameraController */
/* loaded from: classes.dex */
public class CameraController {
    private static int mZoom;
    private Handler mCameraHandler;
    private HandlerThread mCameraThreadHandler;
    private Camera mCamera = null;
    private int mCameraHeight = 0;
    private String mCameraId = null;
    private CameraManager mCameraManager = null;
    private int mCameraPixel = 0;
    private CameraState mCameraState = null;
    private SurfaceTexture mCameraSurfaceText = null;
    private Thread mCameraThread = null;
    private int mCameraWidth = 0;

    /* renamed from: com.lbd.xj.device.camera.CameraController$CameraPreviewListener */
    /* loaded from: classes.dex */
    public interface CameraPreviewListener {
        void cameraPreview(String str, byte[] bArr);
    }

    /* renamed from: com.lbd.xj.device.camera.CameraController$CameraState */
    /* loaded from: classes.dex */
    public enum CameraState {
        ECDS_CONSTRUCTED,
        ECDS_INITIALIZED,
        ECDS_CONNECTED,
        ECDS_STARTED,
        ECDS_STOPED,
        ECDS_EVICTED
    }

    public CameraController() {
        initCamera();
    }

    public void initCamera() {
        this.mCameraState = CameraState.ECDS_CONSTRUCTED;
    }

    public int cameraConnect() {
        this.mCameraState = CameraState.ECDS_CONNECTED;
        return 1;
    }

    public int cameraDisConnect() {
        this.mCameraState = CameraState.ECDS_INITIALIZED;
        return 1;
    }

    public int cameraFrame(int i) {
        if (this.mCameraState != CameraState.ECDS_EVICTED) {
            mZoom = i;
            try {
                if (this.mCamera == null) {
                    return 1;
                }
                Camera.Parameters parameters = this.mCamera.getParameters();
                if (!parameters.isZoomSupported() && !this.mCamera.getParameters().isSmoothZoomSupported()) {
                    return 1;
                }
                parameters.setZoom(mZoom);
                this.mCamera.setParameters(parameters);
            } catch (Exception unused) {
                LogUtils.m22036e("SHENG", "无法打开相机");
            }
        }
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x013d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String cameraList() {
        /*
            Method dump skipped, instructions count: 381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lbd.xj.device.camera.CameraController.cameraList():java.lang.String");
    }

    public void getPreViewImage(final CameraPreviewListener cameraPreviewListener) {
        this.mCamera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() { // from class: com.lbd.xj.device.camera.CameraController.1
            @Override // android.hardware.Camera.PreviewCallback
            public void onPreviewFrame(byte[] bArr, Camera camera) {
                CameraController.this.mCamera.addCallbackBuffer(bArr);
                CameraPreviewListener cameraPreviewListener2 = cameraPreviewListener;
                if (cameraPreviewListener2 != null) {
                    cameraPreviewListener2.cameraPreview(CameraController.this.mCameraId, bArr);
                }
            }
        });
    }

    public void reConnectCamera(CameraPreviewListener cameraPreviewListener) {
        LogUtils.m22036e("SHENG", "MYDBG reConnect" + this.mCameraState);
        if (this.mCameraState == CameraState.ECDS_EVICTED) {
            LogUtils.m22036e("SHENG", "MYDBG reConnect");
            cameraStop(this.mCameraId);
            this.mCameraState = CameraState.ECDS_STARTED;
            cameraStart(this.mCameraId, this.mCameraWidth, this.mCameraHeight, this.mCameraPixel, cameraPreviewListener);
        }
    }

    public int cameraStop(String str) {
        LogUtils.m22036e("SHENG", "Camera stop!!!");
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setPreviewCallback(null);
                this.mCamera.stopPreview();
                this.mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mCamera = null;
        }
        this.mCameraState = CameraState.ECDS_INITIALIZED;
        return 1;
    }

    public int cameraStart(String str, int i, int i2, int i3, final CameraPreviewListener cameraPreviewListener) {
        LogUtils.m22036e("SHENG", String.format("Camera Start :%s, width=%d, height=%d pixel_format=%d", str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        this.mCameraId = str;
        this.mCameraWidth = i;
        this.mCameraHeight = i2;
        this.mCameraPixel = i3;
        Camera.getCameraInfo(Integer.valueOf(this.mCameraId).intValue(), new Camera.CameraInfo());
        while (true) {
            try {
                this.mCamera = Camera.open(Integer.valueOf(this.mCameraId).intValue());
                break;
            } catch (RuntimeException e) {
                LogUtils.m22036e("SHENG", "Camera open error" + e.toString());
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    LogUtils.m22036e("SHENG", e2.toString());
                }
            }
        }
        Camera.Parameters parameters = this.mCamera.getParameters();
        parameters.setPreviewSize(i, i2);
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        } else if (supportedFocusModes.contains("continuous-picture")) {
            parameters.setFocusMode("continuous-video");
        }
        LogUtils.m22036e("SHENG", "Camera pixel_format=" + i3);
        parameters.setPreviewFormat(17);
        if (this.mCameraSurfaceText == null) {
            this.mCameraSurfaceText = new SurfaceTexture(911000);
        }
        try {
            this.mCamera.setPreviewTexture(this.mCameraSurfaceText);
            this.mCamera.setParameters(parameters);
            this.mCamera.setPreviewCallback(new Camera.PreviewCallback() { // from class: com.lbd.xj.device.camera.CameraController.2
                @Override // android.hardware.Camera.PreviewCallback
                public void onPreviewFrame(byte[] bArr, Camera camera) {
                    if (CameraController.this.mCameraState != CameraState.ECDS_EVICTED) {
                        CameraController.this.mCameraState = CameraState.ECDS_STARTED;
                        CameraPreviewListener cameraPreviewListener2 = cameraPreviewListener;
                        if (cameraPreviewListener2 != null) {
                            cameraPreviewListener2.cameraPreview(CameraController.this.mCameraId, bArr);
                        }
                    }
                }
            });
            this.mCamera.startPreview();
            this.mCamera.setErrorCallback(new Camera.ErrorCallback() { // from class: com.lbd.xj.device.camera.CameraController.3
                @Override // android.hardware.Camera.ErrorCallback
                public void onError(int i4, Camera camera) {
                    if (i4 == 2) {
                        CameraController.this.mCameraState = CameraState.ECDS_EVICTED;
                    }
                    LogUtils.m22036e("SHENG", "Camera MYDBG onError error=" + i4);
                }
            });
            this.mCamera.cancelAutoFocus();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        while (this.mCameraState != CameraState.ECDS_STARTED) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e4) {
                e4.printStackTrace();
            }
        }
        return 1;
    }

    public void setCameraDisplayOrientation(int i, Camera camera) {
        int i2;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i2 = (360 - (cameraInfo.orientation % 360)) % 360;
            LogUtils.m22036e("SHENG", "cameraInfo.=");
        } else {
            i2 = (cameraInfo.orientation + 360) % 360;
        }
        camera.setDisplayOrientation(i2);
    }

    public void initCameraLooper() {
        this.mCameraThreadHandler = new HandlerThread("CAMERA2");
        this.mCameraThreadHandler.start();
        this.mCameraHandler = new Handler(this.mCameraThreadHandler.getLooper());
    }

    public Camera getCamera() {
        return this.mCamera;
    }

    public String getCameraId() {
        return this.mCameraId;
    }

    public CameraState getCameraState() {
        return this.mCameraState;
    }

    public void setCameraState(CameraState cameraState) {
        this.mCameraState = cameraState;
    }
}
