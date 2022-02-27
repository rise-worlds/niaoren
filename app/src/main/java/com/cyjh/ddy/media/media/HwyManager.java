package com.cyjh.ddy.media.media;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.ViewGroup;
import com.blankj.utilcode.util.TimeUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.base.utils.SdkKeyUtil;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.bean.d310.EncodeParams;
import com.cyjh.ddy.media.bean.socket.BaseSocketResponse;
import com.cyjh.ddy.media.bean.socket.ControlResponse;
import com.cyjh.ddy.media.beaninner.MWYSdkBean;
import com.cyjh.ddy.media.beaninner.XBYUserInfo;
import com.cyjh.ddy.media.media.listener.HwySurfaceListener;
import com.cyjh.ddy.media.media.listener.IHwyControl;
import com.cyjh.ddy.media.media.listener.IHwyControlListener;
import com.cyjh.ddy.media.media.listener.IHwyManager;
import com.cyjh.ddy.media.media.listener.IHwyManagerListener;
import com.cyjh.ddy.media.media.listener.IHwyMedia;
import com.cyjh.ddy.media.media.listener.IHwyMediaListener;
import com.cyjh.ddy.media.media.listener.IHwySdkFuncProcess;
import com.cyjh.ddy.media.media.listener.OnPreparedListener;
import com.cyjh.ddy.media.media.qemu.HwyMedia;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddy.media.record.HwyVoice;
import com.cyjh.ddy.media.serverlogger.HwyServerLogger;
import com.cyjh.ddysdk.order.base.bean.NoticeSyncInfo;
import com.cyjh.ddysdk.order.base.p041a.EncodeServiceManager;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.connection.ReConnectMgr;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.TypeToken;

/* loaded from: classes.dex */
public class HwyManager implements HwySurfaceListener, IHwyManager, OnPreparedListener {

    /* renamed from: a */
    public static final String f7247a = "HwyManager";

    /* renamed from: b */
    public static final int f7248b = -1;

    /* renamed from: c */
    public static final int f7249c = 0;

    /* renamed from: d */
    public static final int f7250d = 1;

    /* renamed from: e */
    public static final int f7251e = 2;

    /* renamed from: f */
    public static final int f7252f = 3;

    /* renamed from: g */
    public static final int f7253g = 1;

    /* renamed from: h */
    public static final int f7254h = 2;

    /* renamed from: t */
    private static long f7255t = 0;

    /* renamed from: u */
    private static String f7256u = "";

    /* renamed from: j */
    private HwyVoice f7260j;

    /* renamed from: k */
    private String f7261k;

    /* renamed from: n */
    private MWYSdkBean f7264n;

    /* renamed from: o */
    private IHwyManagerListener f7265o;

    /* renamed from: p */
    private HwyServerLogger f7266p;

    /* renamed from: w */
    private Context f7271w;

    /* renamed from: x */
    private IHwyMedia f7272x;

    /* renamed from: y */
    private IHwyControl f7273y;

    /* renamed from: i */
    private boolean f7259i = false;

    /* renamed from: l */
    private int f7262l = -1;

    /* renamed from: m */
    private boolean f7263m = false;

    /* renamed from: q */
    private long f7267q = 0;

    /* renamed from: r */
    private long f7268r = 0;

    /* renamed from: s */
    private long f7269s = 0;

    /* renamed from: v */
    private long f7270v = 0;

    /* renamed from: z */
    private IHwyMediaListener f7274z = new IHwyMediaListener() { // from class: com.cyjh.ddy.media.media.HwyManager.1
        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void mediaConnectError(String str) {
            if (HwyManager.this.f7270v > 0) {
                HwyManager.f7255t += System.currentTimeMillis() - HwyManager.this.f7270v;
                HwyManager.this.f7270v = 0L;
            }
            HwyManager.this.f7265o.upConnTimes(HwyManager.f7255t);
            HwyManager.this.m21705a((int) ActionCode.MediaConnectErr, str);
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void mediaCloseSuccess() {
            if (HwyManager.this.f7270v > 0) {
                HwyManager.f7255t += System.currentTimeMillis() - HwyManager.this.f7270v;
                HwyManager.this.f7270v = 0L;
            }
            if (HwyManager.this.f7265o != null) {
                HwyManager.this.f7265o.upConnTimes(HwyManager.f7255t);
                HwyManager.this.m21705a((int) ActionCode.MediaCloseSuccess, "");
            }
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void mediaFirstFrameSuccess(ConnectionInfo aVar) {
            HwyManager hwyManager = HwyManager.this;
            StringBuilder sb = new StringBuilder();
            sb.append("u:");
            sb.append(aVar == null ? "" : aVar.m15014f());
            hwyManager.m21705a((int) ActionCode.MediaFirstFrameSuccess, sb.toString());
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void mediaConnectSuccess() {
            String str = (String) DateFormat.format("MMddyy", System.currentTimeMillis());
            if (!str.equals(HwyManager.f7256u)) {
                String unused = HwyManager.f7256u = str;
                long unused2 = HwyManager.f7255t = 0L;
            }
            HwyManager.this.f7270v = System.currentTimeMillis();
            HwyManager.this.f7265o.upConnTimes(HwyManager.f7255t);
            HwyManager.this.m21705a((int) ActionCode.MediaConnectSuccess, "MediaConnectSuccess");
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void MediaConnectRefuse(int i, String str) {
            HwyManager.this.m21705a(i, str);
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void showFPS(String str) {
            HwyManager.this.f7265o.upFps(str);
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void showPing(String str) {
            HwyManager.this.f7265o.upPing(str);
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void showLeftPacketLength(int i, int i2) {
            HwyManager.this.f7265o.upLeftPacketLength(i, i2);
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void upTraffic(long j, long j2) {
            HwyManager.this.f7265o.upTraffic(j, j2);
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyMediaListener
        public void upFrameTime(long j) {
            HwyManager.this.f7265o.upFrameTime(j);
        }
    };

    /* renamed from: A */
    private IHwyControlListener f7257A = new IHwyControlListener() { // from class: com.cyjh.ddy.media.media.HwyManager.2
        @Override // com.cyjh.ddy.media.media.listener.IHwyControlListener
        public void controlCloseSuccess() {
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyControlListener
        public void controlConnectError(String str) {
            HwyManager hwyManager = HwyManager.this;
            hwyManager.m21705a((int) ActionCode.CtrlConnectRefuse_2001, "【2001】" + ("cause=controlConnectError,orderid=" + HwyManager.this.f7269s + ",errMsg=" + str));
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyControlListener
        public void controlConnectSuccess() {
            HwyManager.this.f7273y.mo21670a(HwyManager.this.f7264n.f7231g);
        }

        @Override // com.cyjh.ddy.media.media.listener.IHwyControlListener
        public void controlResponse(String str) {
            CLog.m21882i(HwyManager.f7247a, "controlResponse: " + str);
            try {
                int i = new JSONObject(str).getInt("code");
                if (i == 2) {
                    BaseSocketResponse baseSocketResponse = (BaseSocketResponse) JsonUtil.m21802a(str, new TypeToken<BaseSocketResponse<ControlResponse>>() { // from class: com.cyjh.ddy.media.media.HwyManager.2.1
                    }.getType());
                    if (!(baseSocketResponse == null || baseSocketResponse.data == 0)) {
                        ControlResponse controlResponse = (ControlResponse) baseSocketResponse.data;
                        if (controlResponse.type == 5) {
                            HwyManager.this.f7265o.autoRotateScreen(controlResponse.rotate);
                            HwyManager.this.f7272x.mo21620a(controlResponse);
                        }
                    }
                } else if (i == 1) {
                    BaseSocketResponse baseSocketResponse2 = (BaseSocketResponse) JsonUtil.m21802a(str, new TypeToken<BaseSocketResponse<CommandResponseInfo>>() { // from class: com.cyjh.ddy.media.media.HwyManager.2.2
                    }.getType());
                    if (!(baseSocketResponse2 == null || baseSocketResponse2.data == 0)) {
                        CommandResponseInfo commandResponseInfo = (CommandResponseInfo) baseSocketResponse2.data;
                        if ("vc".equals(commandResponseInfo.command) && ResultTypeConstant.f7213z.equals(commandResponseInfo.data)) {
                            HwyManager hwyManager = HwyManager.this;
                            hwyManager.m21705a((int) ActionCode.CtrlConnectRefuse_2002, "【2002】" + ("cause=vcBackToCloseVideo,orderid=" + HwyManager.this.f7269s));
                        } else if ("showdestop".equals(commandResponseInfo.command)) {
                            CLog.m21882i("showdestop", commandResponseInfo.data);
                            if (commandResponseInfo.data.equals("1")) {
                                HwyManager.this.m21705a((int) ActionCode.BusNotice_Destop_Show, "showdestop");
                            } else {
                                HwyManager.this.m21705a((int) ActionCode.BusNotice_Destop_Hide, "hidedestop");
                            }
                        } else if ("copyClipBoard".equals(commandResponseInfo.command)) {
                            HwyManager.this.f7265o.upClipboard(commandResponseInfo.data);
                        } else if ("noticeSync".equals(commandResponseInfo.command)) {
                            HwyManager.this.f7265o.upNoticeSyncInfo((NoticeSyncInfo) JsonUtil.m21799b(commandResponseInfo.data, NoticeSyncInfo.class));
                        } else if ("msgbroadcast".equals(commandResponseInfo.command)) {
                            HwyManager.this.f7265o.upMsgBroadcast(commandResponseInfo.command, commandResponseInfo.data);
                        } else if ("voiceEvent".equals(commandResponseInfo.command)) {
                            HwyManager.this.m21702a(commandResponseInfo);
                        } else if (HwyManager.this.f7258B.containsKey(commandResponseInfo.command)) {
                            CLog.m21882i(HwyManager.f7247a, "process " + commandResponseInfo.command);
                            ((IHwySdkFuncProcess) HwyManager.this.f7258B.get(commandResponseInfo.command)).request(commandResponseInfo.data);
                        } else {
                            CLog.m21883e(HwyManager.f7247a, "lost process " + commandResponseInfo.command + "," + commandResponseInfo.data);
                        }
                    }
                }
            } catch (JSONException unused) {
                CLog.m21883e(HwyManager.f7247a, "JSONException json:" + str);
            }
        }
    };

    /* renamed from: B */
    private Map<String, IHwySdkFuncProcess> f7258B = new HashMap();

    @Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ConnectType {
    }

    @Override // com.cyjh.ddy.media.media.listener.HwySurfaceListener
    /* renamed from: b */
    public void mo21673b(Object obj) {
    }

    @Override // com.cyjh.ddy.media.media.listener.HwySurfaceListener
    /* renamed from: b */
    public void mo21672b(Object obj, int i, int i2) {
    }

    public HwyManager(Context context) {
        this.f7266p = null;
        this.f7271w = context;
        this.f7266p = new HwyServerLogger(context);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: a */
    public void mo21660a(XBYUserInfo xBYUserInfo, String str, IHwyManagerListener eVar, ViewGroup viewGroup, int i) {
        this.f7265o = eVar;
        this.f7261k = str;
        EncodeServiceManager a = EncodeServiceManager.m21141a();
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        a.m21138a(z);
        this.f7273y = new HwyControl(this.f7257A, str);
        this.f7273y.mo21669a(xBYUserInfo);
        this.f7272x = new HwyMedia(this.f7274z, this.f7273y);
        this.f7272x.mo21621a(this.f7271w, viewGroup, this, this, i);
        this.f7260j = new HwyVoice();
        this.f7260j.m21501a(this.f7259i);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: a */
    public void mo21658a(String str, String str2) {
        IHwyControl bVar = this.f7273y;
        if (bVar != null) {
            bVar.mo21667a(str, str2);
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: a */
    public void mo21657a(boolean z) {
        ReConnectMgr.m14986j().m14995a(z);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: b */
    public void mo21654b(boolean z) {
        this.f7259i = z;
    }

    @Override // com.cyjh.ddy.media.media.listener.HwySurfaceListener
    /* renamed from: a */
    public void mo21674a(Object obj, int i, int i2) {
        this.f7262l = 0;
        CLog.m21882i(f7247a, "Media onSurfaceAvailable");
    }

    @Override // com.cyjh.ddy.media.media.listener.HwySurfaceListener
    /* renamed from: a */
    public boolean mo21675a(Object obj) {
        this.f7262l = 3;
        CLog.m21882i(f7247a, "Media onSurfaceDestroyed");
        this.f7273y.mo21671a();
        this.f7272x.mo21623a();
        this.f7260j.m21498b(true);
        return true;
    }

    @Override // com.cyjh.ddy.media.media.listener.OnPreparedListener
    /* renamed from: a */
    public void mo21648a() {
        this.f7262l = 1;
        CLog.m21882i(f7247a, "Media onPrepared");
        if (this.f7263m) {
            mo21661a(this.f7264n.f7231g, this.f7264n);
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: a */
    public void mo21661a(long j, MWYSdkBean aVar) {
        m21703a(j, aVar, false);
    }

    /* renamed from: b */
    private int m21694b(String str, String str2) {
        int i = 0;
        while (str.contains(str2)) {
            str = str.substring(str.indexOf(str2) + str2.length());
            i++;
        }
        return i;
    }

    /* renamed from: a */
    public void m21703a(long j, MWYSdkBean aVar, boolean z) {
        ReConnectMgr.m14986j().m14990f();
        CLog.m21882i(f7247a, String.format("play Media state:  orderId= %d state=%d", Long.valueOf(j), Integer.valueOf(this.f7262l)));
        if (this.f7262l == -1) {
            CLog.m21883e("sdk-device", "playMedia maybe failure!! you can check open hardwareAccelerated , like:  android:hardwareAccelerated=\"true\" ");
        }
        int i = this.f7262l;
        this.f7263m = (i == 1 || i == 2) ? false : true;
        if (!this.f7263m && z) {
            if (z) {
                CLog.m21882i(f7247a, aVar.f7228d + " playMedia release");
                this.f7272x.mo21623a();
                this.f7273y.mo21671a();
                this.f7260j.m21498b(false);
                try {
                    Thread.sleep(150L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.f7262l = 2;
        }
        this.f7264n = aVar;
        if (!this.f7263m) {
            this.f7273y.mo21668a(aVar.f7230f);
            if (this.f7259i) {
                this.f7260j.m21502a(aVar.f7230f, this.f7261k);
            }
            m21696b(j);
            CLog.m21882i(f7247a, aVar.f7228d + " ," + aVar.f7233i + " playMedia to play ");
            if (aVar.f7235k) {
                this.f7272x.mo21613a(aVar.f7227c, aVar.f7228d, aVar.f7230f, aVar.f7232h, aVar.f7234j);
            } else {
                boolean b = aVar.m21706b();
                aVar.f7228d = aVar.f7228d.replace(":", "|");
                if (b) {
                    String[] split = aVar.f7228d.split("\\|");
                    if (split.length >= 3) {
                        String str = split[0];
                        int parseInt = Integer.parseInt(split[1]);
                        int parseInt2 = Integer.parseInt(split[2]);
                        EncodeParams encodeParams = new EncodeParams();
                        encodeParams.phoneIP = str;
                        encodeParams.video_port = parseInt;
                        encodeParams.audio_port = parseInt2;
                        encodeParams.bitrate = Integer.parseInt(aVar.f7229e);
                        encodeParams.sign = aVar.f7233i;
                        encodeParams.videowidth = -1;
                        encodeParams.videoheight = -1;
                        encodeParams.transport_mode = aVar.f7236l;
                        encodeParams.encode_type = aVar.f7237m;
                        encodeParams.isPush = SdkKeyUtil.getInstance().isPush() ? 1 : 0;
                        encodeParams.other_param = aVar.f7238n;
                        encodeParams.order_id = String.valueOf(aVar.f7231g);
                        encodeParams.encode_place = aVar.f7239o ? 1 : 0;
                        this.f7272x.mo21613a(aVar.f7227c, encodeParams.toJson(), aVar.f7230f, aVar.f7232h, aVar.f7234j);
                    } else {
                        return;
                    }
                } else if (m21694b(aVar.f7228d, "|") > 2) {
                    this.f7272x.mo21613a(aVar.f7227c, String.format("%s", aVar.f7228d), aVar.f7230f, aVar.f7232h, aVar.f7234j);
                } else if (TextUtils.isEmpty(aVar.f7233i)) {
                    this.f7272x.mo21613a(aVar.f7227c, String.format("%s|0|%s", aVar.f7228d, aVar.f7229e), aVar.f7230f, aVar.f7232h, aVar.f7234j);
                } else {
                    this.f7272x.mo21613a(aVar.f7227c, String.format("%s|0|%s|{\"sign\":\"%s\",\"videowidth\":-1,\"videoheight\":-1}", aVar.f7228d, aVar.f7229e, aVar.f7233i), aVar.f7230f, aVar.f7232h, aVar.f7234j);
                }
            }
        }
        CLog.m21882i(f7247a, "playMedia end.  firstEnter=" + this.f7263m);
    }

    /* renamed from: b */
    private void m21696b(long j) {
        this.f7269s = this.f7269s == 0 ? j : this.f7268r;
        this.f7268r = j;
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: b */
    public void mo21655b(long j, MWYSdkBean aVar) {
        EncodeServiceManager.m21141a().m21137b();
        CLog.m21882i(f7247a, "changeMedia streamAdd=" + aVar.f7227c + ",controlAdd=" + aVar.f7230f);
        m21703a(j, aVar, true);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: a */
    public void mo21662a(long j, int i) {
        this.f7273y.mo21649a(i);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: c */
    public void mo21652c(boolean z) {
        this.f7272x.mo21606b(z);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: d */
    public void mo21651d(boolean z) {
        this.f7272x.mo21612a(z);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: e */
    public void mo21650e(boolean z) {
        this.f7273y.mo21666a(z);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: b */
    public void mo21656b() {
        this.f7272x.mo21610b();
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: a */
    public void mo21663a(int i) {
        IHwyMedia fVar = this.f7272x;
        if (fVar != null) {
            fVar.mo21622a(i);
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: c */
    public boolean mo21653c() {
        return this.f7273y.mo21664c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21705a(int i, String str) {
        boolean isErrExitAction = ActionCode.isErrExitAction(i);
        if (!isErrExitAction || TimeUtils.m23128a() - this.f7267q >= 500) {
            if (isErrExitAction) {
                this.f7267q = TimeUtils.m23128a();
            }
            try {
                this.f7266p.m21489a(i, this.f7264n, this.f7273y.mo21665b().UCID, str);
            } catch (Exception e) {
                CLog.m21883e(f7247a, "logToServer: " + e.toString());
            }
            this.f7265o.actionCodeCallback(i, str);
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyManager
    /* renamed from: a */
    public void mo21659a(String str, IHwySdkFuncProcess iHwySdkFuncProcess) {
        this.f7258B.put(str, iHwySdkFuncProcess);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21702a(CommandResponseInfo commandResponseInfo) {
        String str = commandResponseInfo.data;
        if (!this.f7258B.containsKey("voiceEvent")) {
            CLog.m21883e(f7247a, "lost process voiceEvent," + commandResponseInfo.data);
            return;
        }
        IHwySdkFuncProcess iHwySdkFuncProcess = this.f7258B.get("voiceEvent");
        if (iHwySdkFuncProcess != null) {
            if (TextUtils.equals("start", str)) {
                if (ActivityCompat.checkSelfPermission(this.f7271w, "android.permission.RECORD_AUDIO") != 0) {
                    CLog.m21883e(f7247a, "voice need Manifest.permission.RECORD_AUDIO");
                    iHwySdkFuncProcess.request("permission");
                    return;
                }
                this.f7260j.m21504a();
            } else if (TextUtils.equals("close", str)) {
                this.f7260j.m21500b();
            }
            iHwySdkFuncProcess.request(commandResponseInfo.data);
        }
    }
}
