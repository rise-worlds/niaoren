package com.cyjh.ddy.media.media.qemu;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.SurfaceTexture;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.Utils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.bean.socket.ControlResponse;
import com.cyjh.ddy.media.beaninner.VedioPackage;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.ddy.media.media.CustomTextureView;
import com.cyjh.ddy.media.media.listener.HwySurfaceListener;
import com.cyjh.ddy.media.media.listener.IHwyControl;
import com.cyjh.ddy.media.media.listener.IHwyMedia;
import com.cyjh.ddy.media.media.listener.IHwyMediaListener;
import com.cyjh.ddy.media.media.listener.IMediaServiceListener;
import com.cyjh.ddy.media.media.listener.OnPreparedListener;
import com.cyjh.ddy.media.oksocket.MsgDataBean;
import com.cyjh.ddy.media.oksocket.SocketHelper;
import com.cyjh.ddy.media.service.IMediaWrap;
import com.cyjh.ddy.media.service.MediaService;
import com.cyjh.ddy.media.service.MediaWrap;
import com.cyjh.ddysdk.order.base.p041a.EncodeServiceManager;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import p110z1.C4963cj;
import p110z1.SimpleComparison;

/* renamed from: com.cyjh.ddy.media.media.qemu.b */
/* loaded from: classes.dex */
public class HwyMedia implements TextureView.SurfaceTextureListener, View.OnLayoutChangeListener, IHwyMedia, IMediaServiceListener {

    /* renamed from: a */
    public static final String f7315a = "Media";

    /* renamed from: A */
    private MediaWrap f7316A;

    /* renamed from: b */
    FileOutputStream f7323b;

    /* renamed from: c */
    DataOutputStream f7324c;

    /* renamed from: d */
    boolean f7325d;

    /* renamed from: e */
    FileOutputStream f7326e;

    /* renamed from: f */
    DataOutputStream f7327f;

    /* renamed from: g */
    boolean f7328g;

    /* renamed from: h */
    private IHwyMediaListener f7329h;

    /* renamed from: i */
    private IHwyControl f7330i;

    /* renamed from: j */
    private CustomMediaPlayer f7331j;

    /* renamed from: o */
    private String f7336o;

    /* renamed from: p */
    private String f7337p;

    /* renamed from: q */
    private String f7338q;

    /* renamed from: t */
    private HwySurfaceListener f7341t;

    /* renamed from: w */
    private IMediaWrap f7344w;

    /* renamed from: x */
    private int f7345x;

    /* renamed from: y */
    private ServiceConnection f7346y;

    /* renamed from: z */
    private boolean f7347z;

    /* renamed from: k */
    private boolean f7332k = false;

    /* renamed from: l */
    private Integer f7333l = -1;

    /* renamed from: m */
    private int f7334m = 0;

    /* renamed from: n */
    private int f7335n = 0;

    /* renamed from: r */
    private File f7339r = new File("/sdcard/ddy.h264");

    /* renamed from: s */
    private File f7340s = new File("/sdcard/ddy.aac");

    /* renamed from: u */
    private Thread f7342u = null;

    /* renamed from: v */
    private Runnable f7343v = null;

    /* renamed from: B */
    private Integer f7317B = -1;

    /* renamed from: C */
    private long f7318C = 0;

    /* renamed from: D */
    private long f7319D = 0;

    /* renamed from: E */
    private Integer f7320E = -1;

    /* renamed from: F */
    private long f7321F = 0;

    /* renamed from: G */
    private long f7322G = 0;

    /* renamed from: c */
    private void m21604c() {
    }

    /* renamed from: f */
    private void m21597f() {
    }

    public HwyMedia(IHwyMediaListener gVar, IHwyControl bVar) {
        this.f7329h = gVar;
        this.f7330i = bVar;
        m21604c();
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: a */
    public void mo21621a(Context context, ViewGroup viewGroup, OnPreparedListener jVar, HwySurfaceListener aVar, int i) {
        this.f7341t = aVar;
        this.f7345x = i;
        CustomTextureView customTextureView = new CustomTextureView(context);
        customTextureView.setTag(CustomTextureView.class.getSimpleName());
        viewGroup.addView(customTextureView);
        customTextureView.setSurfaceTextureListener(this);
        customTextureView.setOnTouchEventListener(this.f7330i);
        customTextureView.setOnKeyEventListener(this.f7330i);
        this.f7331j = new CustomMediaPlayer(customTextureView);
        this.f7331j.setOnPreparedListener(jVar);
        viewGroup.addOnLayoutChangeListener(this);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: a */
    public void mo21623a() {
        IMediaWrap iMediaWrap;
        this.f7332k = true;
        if (this.f7345x == 1 && (iMediaWrap = this.f7344w) != null) {
            iMediaWrap.release();
        }
        m21601d();
        this.f7331j.m21627e();
    }

    /* renamed from: d */
    private void m21601d() {
        try {
            if (this.f7324c != null) {
                this.f7324c.close();
                this.f7324c = null;
            }
            if (this.f7323b != null) {
                this.f7323b.close();
                this.f7323b = null;
            }
        } catch (Exception unused) {
        }
        try {
            if (this.f7327f != null) {
                this.f7327f.close();
                this.f7327f = null;
            }
            if (this.f7326e != null) {
                this.f7326e.close();
                this.f7326e = null;
            }
        } catch (Exception unused2) {
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: a */
    public void mo21613a(String str, String str2, String str3, int i, boolean z) {
        this.f7347z = z;
        this.f7331j.m21630c(z);
        this.f7333l = -1;
        this.f7335n = 0;
        this.f7334m = 0;
        this.f7336o = str;
        this.f7337p = str2;
        this.f7338q = str3;
        boolean z2 = i == 2;
        CLog.m21882i("onMessageMedia", "play streamAdd=" + str + ", ctrlAddress=" + str3 + ",isAnbox=" + z2);
        if (!z2) {
            this.f7329h.MediaConnectRefuse(1001, String.format("only supper anbox devicetype(%d!=2).", Integer.valueOf(i)));
            return;
        }
        String a = SocketHelper.m21521a(this.f7336o);
        int b = SocketHelper.m21518b(this.f7336o);
        this.f7332k = false;
        int i2 = this.f7345x;
        if (i2 == 2) {
            m21614a(a, b, this.f7338q);
        } else if (i2 == 1) {
            if (this.f7344w == null) {
                this.f7344w = new MediaWrap();
            }
            this.f7344w.connect(a, b, str3, this.f7329h, str2, this, z);
        }
        m21597f();
        m21593h();
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: a */
    public void mo21612a(boolean z) {
        this.f7331j.m21636a(z);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: b */
    public void mo21606b(boolean z) {
        this.f7331j.m21633b(z);
    }

    /* renamed from: a */
    public void m21614a(final String str, final int i, final String str2) {
        Intent intent = new Intent(m21599e(), MediaService.class);
        this.f7346y = new ServiceConnection() { // from class: com.cyjh.ddy.media.media.qemu.HwyMedia$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                IMediaWrap iMediaWrap;
                IHwyMediaListener gVar;
                String str3;
                boolean z;
                HwyMedia.this.f7344w = ((MediaService.LocalBinder) iBinder).getServerInstance();
                iMediaWrap = HwyMedia.this.f7344w;
                String str4 = str;
                int i2 = i;
                String str5 = str2;
                gVar = HwyMedia.this.f7329h;
                str3 = HwyMedia.this.f7337p;
                IMediaServiceListener hVar = this;
                z = HwyMedia.this.f7347z;
                iMediaWrap.connect(str4, i2, str5, gVar, str3, hVar, z);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                HwyMedia.this.f7344w = null;
            }
        };
        m21599e().bindService(intent, this.f7346y, 1);
        m21599e().startService(intent);
    }

    @Override // com.cyjh.ddy.media.media.listener.IMediaServiceListener
    /* renamed from: a */
    public void mo21611a(byte[] bArr) {
        m21605b(bArr);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: b */
    public void mo21610b() {
        CLog.m21882i(EncodeServiceManager.f8091a, "[INFO]停止服务：收到广播");
        try {
            if (this.f7346y != null) {
                m21599e().unbindService(this.f7346y);
                this.f7346y = null;
            }
        } catch (Exception e) {
            CLog.m21883e(EncodeServiceManager.f8091a, e.getMessage());
        }
        try {
            m21599e().stopService(new Intent(m21599e(), MediaService.class));
        } catch (Exception e2) {
            CLog.m21883e(EncodeServiceManager.f8091a, e2.getMessage());
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: a */
    public void mo21622a(int i) {
        CLog.m21882i(f7315a, "changeBitrate:bitrate=" + i);
        m21615a("{\"cmd\":1,\"param\":" + i + C4963cj.f20747d);
    }

    /* renamed from: e */
    private Application m21599e() {
        return Utils.m24103a();
    }

    /* renamed from: b */
    private void m21605b(byte[] bArr) {
        if (!this.f7332k) {
            if (bArr == null || bArr.length == 0) {
                CLog.m21883e("WebSocketProxy-MUSICERR", "收到空包了");
                this.f7329h.MediaConnectRefuse(Integer.valueOf((int) ActionCode.MediaConnectRefuse_1023).intValue(), "【1023】");
                return;
            }
            byte b = bArr[0];
            if (b == 0 || b == -1 || bArr.length != 1) {
                int length = bArr.length;
                if (b == 0) {
                    m21595g();
                    m21603c(length);
                    this.f7331j.m21637a(new VedioPackage(bArr, 0L, length, 0));
                } else if (b == -1) {
                    m21609b(length);
                    this.f7331j.m21628d(new VedioPackage(bArr, 0L, length, 1));
                } else {
                    CLog.m21883e("onMessageMedia", "Unkonw media package b1=" + ((int) b));
                }
            } else {
                CLog.m21883e("WebSocketProxy-MUSICERR", "收到问题包了" + ((int) b) + " l= " + bArr.length);
                String format = String.format("10%d%d", Integer.valueOf(b / 16), Integer.valueOf(b % 16));
                IHwyMediaListener gVar = this.f7329h;
                int intValue = Integer.valueOf(format).intValue();
                gVar.MediaConnectRefuse(intValue, "【" + format + "】b=" + ((int) b));
            }
        }
    }

    /* renamed from: b */
    private void m21609b(int i) {
        int i2 = Calendar.getInstance().get(13);
        synchronized (this.f7317B) {
            if (this.f7317B.intValue() == i2) {
                this.f7318C += i;
            } else {
                if (this.f7317B.intValue() != -1) {
                    this.f7319D = this.f7318C;
                }
                this.f7317B = Integer.valueOf(i2);
                this.f7318C = 0L;
            }
        }
    }

    /* renamed from: c */
    private void m21603c(int i) {
        int i2 = Calendar.getInstance().get(13);
        synchronized (this.f7320E) {
            if (this.f7320E.intValue() == i2) {
                this.f7321F += i;
            } else {
                if (this.f7320E.intValue() != -1) {
                    this.f7322G = this.f7321F;
                }
                this.f7320E = Integer.valueOf(i2);
                this.f7321F = 0L;
            }
        }
    }

    /* renamed from: g */
    private void m21595g() {
        int i = Calendar.getInstance().get(13);
        synchronized (this.f7333l) {
            if (this.f7333l.intValue() == i) {
                this.f7334m++;
            } else {
                if (this.f7333l.intValue() != -1) {
                    this.f7335n = this.f7334m;
                }
                this.f7333l = Integer.valueOf(i);
                this.f7334m = 0;
            }
        }
    }

    /* renamed from: h */
    private void m21593h() {
        if (this.f7343v == null) {
            this.f7343v = new Runnable() { // from class: com.cyjh.ddy.media.media.qemu.HwyMedia$2
                /* JADX WARN: Incorrect condition in loop: B:4:0x0026 */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        r5 = this;
                        java.lang.String r0 = "Media"
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        r1.<init>()
                        java.lang.String r2 = "run: [start]= "
                        r1.append(r2)
                        java.lang.Thread r2 = java.lang.Thread.currentThread()
                        java.lang.String r2 = r2.getName()
                        r1.append(r2)
                        java.lang.String r1 = r1.toString()
                        com.cyjh.ddy.base.utils.CLog.m21882i(r0, r1)
                        r0 = 0
                        r1 = 0
                    L_0x0020:
                        com.cyjh.ddy.media.media.qemu.b r2 = com.cyjh.ddy.media.media.qemu.HwyMedia.this
                        boolean r2 = com.cyjh.ddy.media.media.qemu.HwyMedia.m21598e(r2)
                        if (r2 != 0) goto L_0x005c
                        r2 = 100
                        java.lang.Thread.sleep(r2)     // Catch: Exception -> 0x004b, InterruptedException -> 0x0053
                        int r1 = r1 + 1
                        r2 = 9
                        if (r1 >= r2) goto L_0x0034
                        goto L_0x0020
                    L_0x0034:
                        r5.m21643d()     // Catch: Exception -> 0x0045, InterruptedException -> 0x0048
                        r5.m21645b()     // Catch: Exception -> 0x0045, InterruptedException -> 0x0048
                        r5.m21642e()     // Catch: Exception -> 0x0045, InterruptedException -> 0x0048
                        r5.m21644c()     // Catch: Exception -> 0x0045, InterruptedException -> 0x0048
                        r5.m21646a()     // Catch: Exception -> 0x0045, InterruptedException -> 0x0048
                        r1 = 0
                        goto L_0x0020
                    L_0x0045:
                        r1 = move-exception
                        r2 = 0
                        goto L_0x004f
                    L_0x0048:
                        r1 = move-exception
                        r2 = 0
                        goto L_0x0057
                    L_0x004b:
                        r2 = move-exception
                        r4 = r2
                        r2 = r1
                        r1 = r4
                    L_0x004f:
                        r1.printStackTrace()
                        goto L_0x005a
                    L_0x0053:
                        r2 = move-exception
                        r4 = r2
                        r2 = r1
                        r1 = r4
                    L_0x0057:
                        r1.printStackTrace()
                    L_0x005a:
                        r1 = r2
                        goto L_0x0020
                    L_0x005c:
                        java.lang.String r0 = "Media"
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        r1.<init>()
                        java.lang.String r2 = "run: [stop]= "
                        r1.append(r2)
                        java.lang.Thread r2 = java.lang.Thread.currentThread()
                        java.lang.String r2 = r2.getName()
                        r1.append(r2)
                        java.lang.String r1 = r1.toString()
                        com.cyjh.ddy.base.utils.CLog.m21882i(r0, r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.media.media.qemu.HwyMedia$2.run():void");
                }

                /* renamed from: a */
                private void m21646a() {
                    CustomMediaPlayer aVar;
                    IHwyMediaListener gVar;
                    aVar = HwyMedia.this.f7331j;
                    long g = aVar.m21625g();
                    if (g > 0) {
                        gVar = HwyMedia.this.f7329h;
                        gVar.upFrameTime(g);
                    }
                }

                /* renamed from: b */
                private void m21645b() {
                    Integer num;
                    Integer num2;
                    Integer num3;
                    Integer num4;
                    IHwyMediaListener gVar;
                    long j;
                    long j2;
                    int i = Calendar.getInstance().get(13);
                    num = HwyMedia.this.f7317B;
                    synchronized (num) {
                        num2 = HwyMedia.this.f7317B;
                        if (i - num2.intValue() > 1) {
                            HwyMedia.this.f7319D = 0L;
                        }
                    }
                    num3 = HwyMedia.this.f7320E;
                    synchronized (num3) {
                        num4 = HwyMedia.this.f7320E;
                        if (i - num4.intValue() > 1) {
                            HwyMedia.this.f7322G = 0L;
                        }
                    }
                    gVar = HwyMedia.this.f7329h;
                    j = HwyMedia.this.f7319D;
                    j2 = HwyMedia.this.f7322G;
                    gVar.upTraffic(j, j2);
                }

                /* renamed from: c */
                private void m21644c() {
                    IHwyMediaListener gVar;
                    CustomMediaPlayer aVar;
                    CustomMediaPlayer aVar2;
                    gVar = HwyMedia.this.f7329h;
                    aVar = HwyMedia.this.f7331j;
                    int c = aVar.m21632c();
                    aVar2 = HwyMedia.this.f7331j;
                    gVar.showLeftPacketLength(c, aVar2.m21635b());
                }

                /* renamed from: d */
                private void m21643d() {
                    Integer num;
                    Integer num2;
                    IHwyMediaListener gVar;
                    int i;
                    int i2 = Calendar.getInstance().get(13);
                    num = HwyMedia.this.f7333l;
                    synchronized (num) {
                        num2 = HwyMedia.this.f7333l;
                        if (i2 - num2.intValue() > 1) {
                            HwyMedia.this.f7335n = 0;
                        }
                    }
                    gVar = HwyMedia.this.f7329h;
                    i = HwyMedia.this.f7335n;
                    gVar.showFPS(String.valueOf(i));
                }

                /* renamed from: e */
                private void m21642e() {
                    String str;
                    IHwyMediaListener gVar;
                    str = HwyMedia.this.f7336o;
                    String a = SocketHelper.m21521a(str);
                    try {
                        ShellUtils.C0985a a2 = ShellUtils.m23276a("ping -c 2 -i 0.5 -s 64 -f  " + a, false);
                        if (!a2.f6749b.contains("min/avg/max/mdev")) {
                            CLog.m21882i("CustomMediaData", a + ExpandableTextView.f6958c + a2.f6749b);
                            return;
                        }
                        String substring = a2.f6749b.substring(a2.f6749b.indexOf("min/avg/max/mdev"));
                        String substring2 = substring.substring(substring.indexOf(SimpleComparison.f23609c) + 1, substring.indexOf("ms") - 1);
                        gVar = HwyMedia.this.f7329h;
                        gVar.showPing(substring2.trim());
                    } catch (Throwable unused) {
                    }
                }
            };
        }
        this.f7342u = new Thread(this.f7343v);
        try {
            this.f7342u.setName(String.format("%s_interval", this.f7336o));
            if (!this.f7332k) {
                this.f7342u.start();
            }
        } catch (IllegalThreadStateException e) {
            CLog.m21883e(f7315a, "mIntervalThread.start()1 " + e.getMessage());
        } catch (Exception e2) {
            CLog.m21883e(f7315a, "mIntervalThread.start()2 " + e2.getMessage());
        }
    }

    /* renamed from: a */
    public void m21615a(String str) {
        if (TextUtils.isEmpty(str)) {
            CLog.m21883e(f7315a, "sendMsg is null");
            return;
        }
        MsgDataBean bVar = new MsgDataBean(str);
        IMediaWrap iMediaWrap = this.f7344w;
        if (iMediaWrap != null) {
            iMediaWrap.send(bVar);
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyMedia
    /* renamed from: a */
    public void mo21620a(ControlResponse controlResponse) {
        this.f7331j.m21638a(controlResponse);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.f7341t.mo21674a(surfaceTexture, i, i2);
        this.f7331j.m21639a(new Surface(surfaceTexture));
        this.f7331j.m21629d();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.f7341t.mo21672b(surfaceTexture, i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.f7341t.mo21675a(surfaceTexture);
        mo21623a();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.f7341t.mo21673b(surfaceTexture);
    }

    /* renamed from: i */
    private void m21591i() {
        String str = "";
        try {
            str = this.f7337p;
            CLog.m21882i(f7315a, "sendTcpControlIpAndPortMsg " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m21615a(str);
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.f7332k) {
            return;
        }
        if (i3 - i != i7 - i5 || i4 - i2 != i8 - i6) {
            this.f7331j.m21626f();
        }
    }
}
