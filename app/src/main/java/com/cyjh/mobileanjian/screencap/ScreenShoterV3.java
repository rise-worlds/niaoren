package com.cyjh.mobileanjian.screencap;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.WindowManager;
import com.cyjh.mq.p049d.C1363e;
import java.nio.ByteBuffer;

@TargetApi(21)
/* loaded from: classes.dex */
public final class ScreenShoterV3 {

    /* renamed from: a */
    private static final String f8762a = "ScreenShoterV3";

    /* renamed from: b */
    private Context f8763b;

    /* renamed from: c */
    private MediaProjection f8764c;

    /* renamed from: d */
    private Object f8765d;

    /* renamed from: e */
    private HandlerThread f8766e;

    /* renamed from: f */
    private Handler f8767f;

    /* renamed from: g */
    private Intent f8768g;

    /* renamed from: h */
    private ImageReader f8769h;

    /* renamed from: i */
    private VirtualDisplay f8770i;

    /* renamed from: j */
    private ScreenShotImage f8771j;

    /* renamed from: k */
    private Bitmap f8772k;

    /* renamed from: l */
    private int f8773l;

    /* renamed from: m */
    private int f8774m;

    /* renamed from: n */
    private int f8775n;

    /* renamed from: o */
    private int f8776o;

    /* renamed from: p */
    private boolean f8777p;

    /* renamed from: q */
    private boolean f8778q;

    /* renamed from: r */
    private volatile boolean f8779r;

    /* synthetic */ ScreenShoterV3(byte b) {
        this();
    }

    private ScreenShoterV3() {
        this.f8765d = new Object();
        this.f8777p = false;
        this.f8778q = false;
        this.f8779r = false;
    }

    /* renamed from: com.cyjh.mobileanjian.screencap.ScreenShoterV3$a */
    /* loaded from: classes.dex */
    private static class C1349a {

        /* renamed from: a */
        private static final ScreenShoterV3 f8781a = new ScreenShoterV3((byte) 0);

        private C1349a() {
        }
    }

    public static synchronized ScreenShoterV3 getInstance() {
        ScreenShoterV3 screenShoterV3;
        synchronized (ScreenShoterV3.class) {
            screenShoterV3 = C1349a.f8781a;
        }
        return screenShoterV3;
    }

    public final void init(Context context, Intent intent) {
        if (!this.f8778q) {
            this.f8778q = true;
            this.f8763b = context.getApplicationContext();
            this.f8768g = intent;
            this.f8775n = Resources.getSystem().getDisplayMetrics().densityDpi;
        }
    }

    public final void init(Context context, Intent intent, MediaProjection mediaProjection) {
        if (!this.f8778q) {
            this.f8778q = true;
            this.f8763b = context.getApplicationContext();
            this.f8768g = intent;
            this.f8775n = Resources.getSystem().getDisplayMetrics().densityDpi;
            this.f8764c = mediaProjection;
        }
    }

    public final boolean isInited() {
        return this.f8778q;
    }

    /* renamed from: a */
    private ScreenShotImage m20582a() throws IllegalStateException {
        Image acquireLatestImage = this.f8769h.acquireLatestImage();
        if (acquireLatestImage == null) {
            return this.f8771j;
        }
        ScreenShotImage b = m20572b(acquireLatestImage);
        this.f8771j = b;
        return b;
    }

    /* renamed from: a */
    private Bitmap m20581a(int i, int i2) throws IllegalStateException {
        Image acquireLatestImage = this.f8769h.acquireLatestImage();
        if (acquireLatestImage == null) {
            return this.f8772k;
        }
        Bitmap a = m20579a(acquireLatestImage, i, i2);
        this.f8772k = a;
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00e1  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap m20579a(android.media.Image r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mobileanjian.screencap.ScreenShoterV3.m20579a(android.media.Image, int, int):android.graphics.Bitmap");
    }

    public final void updateScreenSize() {
        WindowManager windowManager;
        if (this.f8778q) {
            int i = 0;
            this.f8779r = false;
            int[] a = C1350a.m20567a(this.f8763b);
            int i2 = a[0];
            int i3 = a[1];
            Context context = this.f8763b;
            if (!(context == null || (windowManager = (WindowManager) context.getSystemService("window")) == null)) {
                i = windowManager.getDefaultDisplay().getRotation();
            }
            this.f8776o = i;
            if (this.f8773l != i2 || this.f8774m != i3) {
                this.f8773l = i2;
                this.f8774m = i3;
            }
        }
    }

    public final void recycler() {
        this.f8779r = false;
        this.f8777p = false;
        this.f8774m = 0;
        this.f8773l = 0;
        this.f8776o = 0;
        synchronized (this.f8765d) {
            if (this.f8766e != null) {
                this.f8766e.quit();
                this.f8766e = null;
            }
            if (this.f8767f != null) {
                this.f8767f.removeCallbacksAndMessages(null);
                this.f8767f = null;
            }
            if (this.f8764c != null) {
                this.f8764c.stop();
                this.f8764c = null;
            }
            if (this.f8770i != null) {
                this.f8770i.release();
                this.f8770i = null;
            }
            if (this.f8769h != null) {
                this.f8769h.close();
                this.f8769h = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public ScreenShotImage m20572b(Image image) {
        try {
            ScreenShotImage screenShotImage = new ScreenShotImage();
            int width = image.getWidth();
            int height = image.getHeight();
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer buffer = planes[0].getBuffer();
            int rowStride = planes[0].getRowStride();
            int pixelStride = planes[0].getPixelStride();
            byte[] bArr = new byte[buffer.capacity()];
            buffer.get(bArr);
            screenShotImage.width = width;
            screenShotImage.height = height;
            screenShotImage.rotation = this.f8776o;
            screenShotImage.rowStride = rowStride;
            screenShotImage.pixelStride = pixelStride;
            screenShotImage.data = bArr;
            if (image != null) {
                image.close();
            }
            return screenShotImage;
        } catch (Exception unused) {
            if (image == null) {
                return null;
            }
            image.close();
            return null;
        } catch (Throwable th) {
            if (image != null) {
                image.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    private synchronized void m20573b() {
        recycler();
        updateScreenSize();
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) this.f8763b.getSystemService("media_projection");
        if (this.f8764c == null && mediaProjectionManager != null) {
            this.f8764c = mediaProjectionManager.getMediaProjection(-1, this.f8768g);
        }
        this.f8766e = new HandlerThread("imageReaderHandler");
        this.f8766e.start();
        this.f8767f = new Handler(this.f8766e.getLooper());
        C1363e.C1364a a = C1363e.m20438a("getprop phone.id");
        if (a.f8876b == null || a.f8876b.length() <= 0) {
            this.f8769h = ImageReader.newInstance(this.f8773l, this.f8774m, 1, 3);
        } else {
            this.f8769h = ImageReader.newInstance(this.f8773l, this.f8774m, 2, 3);
        }
        this.f8770i = this.f8764c.createVirtualDisplay("screen-mirror", this.f8773l, this.f8774m, this.f8775n, 16, this.f8769h.getSurface(), null, null);
        this.f8777p = true;
        if (this.f8769h != null) {
            this.f8769h.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: com.cyjh.mobileanjian.screencap.ScreenShoterV3.1
                @Override // android.media.ImageReader.OnImageAvailableListener
                public final void onImageAvailable(ImageReader imageReader) {
                    if (ScreenShoterV3.this.f8777p) {
                        Image acquireLatestImage = ScreenShoterV3.this.f8769h.acquireLatestImage();
                        if (acquireLatestImage != null) {
                            ScreenShoterV3 screenShoterV3 = ScreenShoterV3.this;
                            screenShoterV3.f8771j = screenShoterV3.m20572b(acquireLatestImage);
                            ScreenShoterV3.this.f8772k = ScreenShoterV3.m20579a(acquireLatestImage, 0, 0);
                            ScreenShoterV3.this.f8779r = true;
                            ScreenShoterV3.this.f8777p = false;
                            if (ScreenShoterV3.this.f8769h != null) {
                                ScreenShoterV3.this.f8769h.setOnImageAvailableListener(null, null);
                                return;
                            }
                            return;
                        }
                        ScreenShoterV3.this.f8779r = false;
                    }
                }
            }, this.f8767f);
        }
    }

    /* renamed from: c */
    private boolean m20570c() {
        return this.f8779r;
    }

    public final ScreenShotImage obtainScreenShotImage() {
        ScreenShotImage a;
        if (this.f8779r) {
            try {
                return m20582a();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        synchronized (this.f8765d) {
            m20573b();
            int i = 0;
            while (!this.f8779r && (i = i + 1) < 50) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            a = m20582a();
        }
        return a;
    }

    public final Bitmap obtainScreenShotImageReturnBitmap(int i, int i2) {
        Bitmap a;
        if (this.f8779r) {
            try {
                return m20581a(i, i2);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        synchronized (this.f8765d) {
            m20573b();
            int i3 = 0;
            while (!this.f8779r && (i3 = i3 + 1) < 50) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            a = m20581a(i, i2);
        }
        return a;
    }
}
