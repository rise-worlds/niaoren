package com.cyjh.ddysdk.device.media;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.SdkKeyUtil;
import com.cyjh.ddy.base.utils.SdkUtils;
import com.cyjh.ddy.media.bean.DdyUserInfo;
import com.cyjh.ddy.media.bean.DefineUserInfo;
import com.cyjh.ddy.media.bean.MultiVideoParams;
import com.cyjh.ddy.media.bean.d310.EncodeParams;
import com.cyjh.ddy.media.beaninner.MWYSdkBean;
import com.cyjh.ddy.media.beaninner.XBYUserInfo;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.ddy.media.media.HwyManager;
import com.cyjh.ddy.media.media.listener.IHwyManager;
import com.cyjh.ddy.media.media.listener.IHwyManagerListener;
import com.cyjh.ddy.media.media.listener.IHwySDKListener;
import com.cyjh.ddy.media.media.listener.IHwySdkFuncProcess;
import com.cyjh.ddy.media.service.MediaService;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.device.C1187a;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.AddClientStatDataRequest;
import com.cyjh.ddysdk.device.bean.BlockFrameNumbersBean;
import com.cyjh.ddysdk.device.bean.PingData;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaContract;
import com.cyjh.ddysdk.device.room.DeviceRoomProxy;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.DdyOrderHelperEx;
import com.cyjh.ddysdk.order.base.bean.DdyOrderStatusAlterRespone;
import com.cyjh.ddysdk.order.base.bean.NoticeSyncInfo;
import com.cyjh.ddysdk.order.base.bean.OrderSteamServerRespone;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;
import com.cyjh.ddysdk.order.base.p041a.EncodeServiceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p110z1.Consts;
import p110z1.EventConstants;

/* loaded from: classes.dex */
public class DdyDeviceMediaHelper extends DeviceRoomProxy implements NoProGuard, DdyDeviceMediaContract.IBusiness, DdyDeviceMediaContract.IMedia {

    /* renamed from: e */
    private IHwyManager f7909e;

    /* renamed from: i */
    private ViewGroup f7913i;

    /* renamed from: l */
    private String f7916l;

    /* renamed from: m */
    private long f7917m;

    /* renamed from: n */
    private long f7918n;

    /* renamed from: o */
    private long f7919o;

    /* renamed from: p */
    private long f7920p;

    /* renamed from: q */
    private long f7921q;

    /* renamed from: r */
    private long f7922r;

    /* renamed from: s */
    private long f7923s;

    /* renamed from: t */
    private long f7924t;

    /* renamed from: w */
    private String f7927w;

    /* renamed from: x */
    private String f7928x;

    /* renamed from: z */
    private boolean f7930z;

    /* renamed from: f */
    private IHwyManagerListener f7910f = null;

    /* renamed from: g */
    private XBYUserInfo f7911g = null;

    /* renamed from: h */
    private DefineUserInfo f7912h = null;

    /* renamed from: j */
    private String f7914j = EventConstants.f16417U;

    /* renamed from: k */
    private String f7915k = EventConstants.f16434b;

    /* renamed from: u */
    private Map<String, Long> f7925u = new HashMap();

    /* renamed from: v */
    private Map<Integer, Integer> f7926v = new HashMap();

    /* renamed from: y */
    private String f7929y = "";

    /* renamed from: A */
    private String f7905A = "";

    /* renamed from: B */
    private int f7906B = 100;

    /* renamed from: C */
    private int f7907C = 200;

    /* renamed from: D */
    private int f7908D = 1000;

    /* renamed from: e */
    static /* synthetic */ long m21212e(DdyDeviceMediaHelper ddyDeviceMediaHelper) {
        long j = ddyDeviceMediaHelper.f7920p;
        ddyDeviceMediaHelper.f7920p = 1 + j;
        return j;
    }

    /* renamed from: g */
    static /* synthetic */ long m21208g(DdyDeviceMediaHelper ddyDeviceMediaHelper) {
        long j = ddyDeviceMediaHelper.f7918n;
        ddyDeviceMediaHelper.f7918n = 1 + j;
        return j;
    }

    public DdyDeviceMediaHelper(Context context) {
        this.f7909e = null;
        this.f7909e = new HwyManager(context);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public boolean init(DdyUserInfo ddyUserInfo, String str, String str2, IHwySDKListener iHwySDKListener, ViewGroup viewGroup, boolean z) {
        this.f7913i = viewGroup;
        m21230a(iHwySDKListener);
        m21189a(this.f7909e, iHwySDKListener);
        if (ddyUserInfo instanceof XBYUserInfo) {
            this.f7911g = (XBYUserInfo) ddyUserInfo;
        } else {
            if (ddyUserInfo instanceof DefineUserInfo) {
                this.f7912h = (DefineUserInfo) ddyUserInfo;
            }
            this.f7911g = new XBYUserInfo();
            this.f7911g.OrderId = ddyUserInfo.OrderId;
            this.f7911g.UCID = ddyUserInfo.UCID;
            XBYUserInfo xBYUserInfo = this.f7911g;
            xBYUserInfo.Channel = "ddysdk";
            xBYUserInfo.isNewUser = false;
            xBYUserInfo.ddyVerCode = C1187a.f7684h;
            xBYUserInfo.AppKey = SdkKeyUtil.getInstance().getSdkKey();
            CLog.m21882i("sdk-version", "build info: 1209 on 2021-04-23 15:57:01 svn version: 4380");
        }
        this.f7916l = str;
        this.f7905A = str2;
        if (!SdkUtils.m21760b() || (!TextUtils.isEmpty(str) && str.length() == 64)) {
            if (z) {
                this.f7909e.mo21660a(this.f7911g, str, this.f7910f, viewGroup, 2);
            } else {
                this.f7909e.mo21660a(this.f7911g, str, this.f7910f, viewGroup, 1);
            }
            this.f7909e.mo21650e(this.f7950c);
            String sdkKey = SdkKeyUtil.getInstance().getSdkKey();
            if (TextUtils.isEmpty(sdkKey) || sdkKey.equals("null")) {
                CLog.m21883e("sdk-device", "DdyDeviceMediaHelper init failure. MetaData  DDY_SDK_APPKEY is empty");
                return false;
            } else if (m21232a() || !z) {
                return true;
            } else {
                CLog.m21883e("sdk-device", "DdyDeviceMediaHelper init failure. service  com.cyjh.ddy.media.service.MediaService  is undef");
                return false;
            }
        } else {
            CLog.m21883e("sdk-device", "DdyDeviceMediaHelper init failure. deviceToken check error");
            return false;
        }
    }

    /* renamed from: a */
    private boolean m21232a() {
        try {
            return this.f7913i.getContext().getPackageManager().getServiceInfo(new ComponentName(this.f7913i.getContext(), MediaService.class), 128) != null;
        } catch (Exception e) {
            CLog.m21883e("sdk-device", e.toString());
            return false;
        }
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void uninit() {
        if (this.f7911g != null) {
            m21223b();
            addClientStatData(this, this.f7905A, this.f7911g.UCID, this.f7911g.OrderId);
        }
        EncodeServiceManager.m21141a().m21137b();
        this.f7909e.mo21656b();
        try {
            this.f7913i.removeAllViews();
        } catch (Exception unused) {
        }
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IBusiness
    public void addProcessRequest(String str, IHwySdkFuncProcess iHwySdkFuncProcess) {
        this.f7909e.mo21659a(str, iHwySdkFuncProcess);
    }

    /* renamed from: a */
    private void m21230a(final IHwySDKListener iHwySDKListener) {
        if (iHwySDKListener instanceof IHwyManagerListener) {
            this.f7910f = (IHwyManagerListener) iHwySDKListener;
        } else {
            this.f7910f = new IHwyManagerListener() { // from class: com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper.1
                @Override // com.cyjh.ddy.media.media.listener.IHwyManagerListener
                public void upConnTimes(long j) {
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwyManagerListener
                public void upLeftPacketLength(int i, int i2) {
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void actionCodeCallback(int i, String str) {
                    CLog.m21882i("sdk-device", "actionCodeCallback code=" + i + ",msg=" + str);
                    iHwySDKListener.actionCodeCallback(i, str);
                    if (SdkUtils.m21760b() && DdyDeviceMediaHelper.this.f7922r > 0 && i == 8004) {
                        DdyDeviceMediaHelper.this.f7924t = System.currentTimeMillis() - DdyDeviceMediaHelper.this.f7922r;
                        if (DdyDeviceMediaHelper.this.f7924t < 0) {
                            DdyDeviceMediaHelper.this.f7924t = 0L;
                        }
                        DdyDeviceMediaHelper ddyDeviceMediaHelper = DdyDeviceMediaHelper.this;
                        ddyDeviceMediaHelper.f7928x = ddyDeviceMediaHelper.f7927w;
                    }
                    if (i == 8004) {
                        DdyDeviceMediaHelper.this.notifyRoomJoin();
                    } else if (ActionCode.isErrExitAction(i)) {
                        DdyDeviceMediaHelper.this.notifyRoomExit();
                    }
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void upFps(String str) {
                    iHwySDKListener.upFps(str);
                    if (SdkUtils.m21760b()) {
                        try {
                            DdyDeviceMediaHelper.this.f7919o += Long.parseLong(str);
                            DdyDeviceMediaHelper.m21212e(DdyDeviceMediaHelper.this);
                        } catch (Exception unused) {
                        }
                    }
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void upPing(String str) {
                    CLog.m21882i("sdk-device", "upPing pingRtt=" + str);
                    iHwySDKListener.upPing(str);
                    if (SdkUtils.m21760b()) {
                        try {
                            PingData pingData = new PingData(str);
                            int i = 0;
                            String substring = pingData.avg.substring(0, pingData.avg.indexOf(Consts.f23430h));
                            DdyDeviceMediaHelper.this.f7917m += Long.valueOf(substring).longValue();
                            DdyDeviceMediaHelper.m21208g(DdyDeviceMediaHelper.this);
                            DdyDeviceMediaHelper.this.f7921q = Math.max(Long.valueOf(pingData.max.substring(0, pingData.max.indexOf(Consts.f23430h))).longValue(), DdyDeviceMediaHelper.this.f7921q);
                            long parseLong = Long.parseLong(substring);
                            if (parseLong > DdyDeviceMediaHelper.this.f7908D) {
                                Integer num = (Integer) DdyDeviceMediaHelper.this.f7926v.get(Integer.valueOf(DdyDeviceMediaHelper.this.f7908D));
                                Map map = DdyDeviceMediaHelper.this.f7926v;
                                Integer valueOf = Integer.valueOf(DdyDeviceMediaHelper.this.f7908D);
                                if (num != null) {
                                    i = num.intValue() + 1;
                                }
                                map.put(valueOf, Integer.valueOf(i));
                            } else if (parseLong > DdyDeviceMediaHelper.this.f7907C) {
                                Integer num2 = (Integer) DdyDeviceMediaHelper.this.f7926v.get(Integer.valueOf(DdyDeviceMediaHelper.this.f7907C));
                                Map map2 = DdyDeviceMediaHelper.this.f7926v;
                                Integer valueOf2 = Integer.valueOf(DdyDeviceMediaHelper.this.f7907C);
                                if (num2 != null) {
                                    i = num2.intValue() + 1;
                                }
                                map2.put(valueOf2, Integer.valueOf(i));
                            } else if (parseLong > DdyDeviceMediaHelper.this.f7906B) {
                                Integer num3 = (Integer) DdyDeviceMediaHelper.this.f7926v.get(Integer.valueOf(DdyDeviceMediaHelper.this.f7906B));
                                Map map3 = DdyDeviceMediaHelper.this.f7926v;
                                Integer valueOf3 = Integer.valueOf(DdyDeviceMediaHelper.this.f7906B);
                                if (num3 != null) {
                                    i = num3.intValue() + 1;
                                }
                                map3.put(valueOf3, Integer.valueOf(i));
                            }
                        } catch (Exception unused) {
                        }
                    }
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void autoRotateScreen(int i) {
                    CLog.m21882i("sdk-device", "autoRotateScreen rotate=" + i);
                    iHwySDKListener.autoRotateScreen(i);
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void upClipboard(String str) {
                    CLog.m21882i("sdk-device", "upClipboard , text= " + str);
                    iHwySDKListener.upClipboard(str);
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void upNoticeSyncInfo(NoticeSyncInfo noticeSyncInfo) {
                    CLog.m21882i("sdk-device", "upNoticeSyncInfo");
                    iHwySDKListener.upNoticeSyncInfo(noticeSyncInfo);
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void upTraffic(long j, long j2) {
                    CLog.m21882i("sdk-device", "showTraffic");
                    iHwySDKListener.upTraffic(j, j2);
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwyManagerListener
                public void upMsgBroadcast(String str, String str2) {
                    CLog.m21883e("sdk-device", "upMsgBroadcast command=" + str + ",data=" + str2);
                    DdyDeviceMediaHelper.this.f7951d.success(str2);
                }

                @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
                public void upFrameTime(long j) {
                    CLog.m21882i("sdk-device", "upFrameTime");
                    iHwySDKListener.upFrameTime(j);
                }
            };
        }
    }

    /* renamed from: b */
    private void m21223b() {
        Long l;
        if (!SdkUtils.m21760b()) {
            CLog.m21883e("sdk-game", "just yungame can call this function addClientStatData.");
            return;
        }
        this.f7923s = System.currentTimeMillis();
        String str = this.f7928x;
        long j = this.f7923s;
        long j2 = this.f7922r;
        long j3 = 0;
        long j4 = j - j2 > 0 ? j - j2 : 0L;
        if (this.f7925u.containsKey(str) && (l = this.f7925u.get(str)) != null) {
            long longValue = l.longValue();
            if (longValue >= 0) {
                j3 = longValue;
            }
        }
        this.f7925u.put(str, Long.valueOf(j3 + j4));
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void setPullStreamRate(String str, String str2) {
        this.f7914j = str;
        this.f7915k = str2;
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void setPingLevel(int i, int i2, int i3) {
        if (i >= i2 || i2 >= i3) {
            throw new IllegalStateException("note: firstLevel < secondLevel < thirdLevel.");
        }
        this.f7906B = i;
        this.f7907C = i2;
        this.f7908D = i3;
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void turnonAudio() {
        this.f7909e.mo21651d(true);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void turnoffAudio() {
        this.f7909e.mo21651d(false);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void doKeyEvent(long j, int i) {
        this.f7909e.mo21662a(j, i);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void playMedia(long j, String str, String str2, DdyDeviceMediaContract.Callback callback) {
        this.f7929y = str2;
        if (this.f7912h == null) {
            m21231a(j, str, str2, callback);
        } else {
            m21222b(j, str, str2, callback);
        }
    }

    /* renamed from: a */
    private void m21231a(long j, String str, String str2, final DdyDeviceMediaContract.Callback callback) {
        CLog.m21882i("sdk-device", "playMediaByXBYUserInfo orderId=" + j + ",ucid=" + str + ",token=" + this.f7916l);
        this.f7918n = 0L;
        this.f7917m = 0L;
        this.f7921q = 0L;
        this.f7922r = System.currentTimeMillis();
        final boolean isEmpty = TextUtils.isEmpty(this.f7948a) ^ true;
        DdyOrderHelperEx.m21149a().m21148a(j, str, str2, this.f7916l, this.f7948a, new DdyOrderContract.Callback() { // from class: com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper.2
            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onSuccess(Object obj) {
                String str3;
                if (!(obj instanceof DdyOrderStatusAlterRespone)) {
                    callback.failuer(DdyDeviceErrorConstants.DDE_Exception, "requestOrderStart object type error.");
                    return;
                }
                DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone = (DdyOrderStatusAlterRespone) obj;
                DdyDeviceMediaHelper.this.f7930z = ddyOrderStatusAlterRespone.isH265;
                String str4 = ddyOrderStatusAlterRespone.isH265 ? DdyDeviceMediaHelper.this.f7915k : DdyDeviceMediaHelper.this.f7914j;
                CLog.m21882i("sdk-device", "setPullStreamRate=" + str4 + ", isH265=" + ddyOrderStatusAlterRespone.isH265);
                DdyDeviceMediaHelper.this.f7927w = str4;
                String str5 = ddyOrderStatusAlterRespone.PullStreamParam;
                if (isEmpty) {
                    ddyOrderStatusAlterRespone.PullStreamParam = ddyOrderStatusAlterRespone.PullStreamParam.replace(":", "|");
                    String a = C1123f.m21845a(new MultiVideoParams(ddyOrderStatusAlterRespone.PullStreamTcpUrl, TextUtils.isEmpty(ddyOrderStatusAlterRespone.DeviceSigner) ? String.format("%s|0|%s", ddyOrderStatusAlterRespone.PullStreamParam, str4) : String.format("%s|0|%s|{\"sign\":\"%s\",\"videowidth\":-1,\"videoheight\":-1}", ddyOrderStatusAlterRespone.PullStreamParam, str4, ddyOrderStatusAlterRespone.DeviceSigner), DdyDeviceMediaHelper.this.f7948a, DdyDeviceMediaHelper.this.f7950c ? 1 : 0));
                    CLog.m21882i("DDY_PARAMS", a);
                    ddyOrderStatusAlterRespone.PullStreamTcpUrl = ddyOrderStatusAlterRespone.ProxyUrl;
                    str3 = a;
                } else {
                    str3 = str5;
                }
                MWYSdkBean aVar = new MWYSdkBean(ddyOrderStatusAlterRespone.PhoneName, ddyOrderStatusAlterRespone.PullStreamTcpUrl, str3, str4, ddyOrderStatusAlterRespone.DeviceTcpHost, ddyOrderStatusAlterRespone.AdbUrl, ddyOrderStatusAlterRespone.OrderId, ddyOrderStatusAlterRespone.YunDeviceType, ddyOrderStatusAlterRespone.DeviceSigner, ddyOrderStatusAlterRespone.isH265, isEmpty, ddyOrderStatusAlterRespone.ServerType, ddyOrderStatusAlterRespone.TransportMode, ddyOrderStatusAlterRespone.OtherParam, false);
                CLog.m21882i("sdk-device", "playmedia onSuccess " + aVar.toString());
                DdyDeviceMediaHelper.this.f7909e.mo21661a(aVar.f7231g, aVar);
                callback.success(aVar);
            }

            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str3) {
                CLog.m21883e("sdk-device", "playmedia onFail code=" + ddyOrderErrorConstants + ",msg=" + str3);
                callback.failuer(DdyDeviceMediaHelper.this.m21224a(ddyOrderErrorConstants), String.format("{\"DdyOrderErrorConstants\":%d,\"msg\":\"%s\"}", Integer.valueOf(ddyOrderErrorConstants.value()), str3));
            }
        });
    }

    /* renamed from: b */
    private void m21222b(long j, String str, final String str2, final DdyDeviceMediaContract.Callback callback) {
        CLog.m21882i("sdk-device", "playMediaByDefineUserInfo orderId=" + j + ",ucid=" + str + ",token=" + this.f7916l);
        if (this.f7912h.OrderId != j) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "orderId not equals UserInfo.OrderId");
            return;
        }
        this.f7918n = 0L;
        this.f7917m = 0L;
        this.f7921q = 0L;
        this.f7922r = System.currentTimeMillis();
        DdyOrderHelperEx.m21149a().m21142a(this.f7948a, this.f7912h.IsHttps, this.f7912h.IsInternalCoding, this.f7912h.AreaNo, new DdyOrderContract.Callback() { // from class: com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper.3
            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onSuccess(Object obj) {
                String str3;
                OrderSteamServerRespone orderSteamServerRespone = (OrderSteamServerRespone) obj;
                if (orderSteamServerRespone != null) {
                    boolean z = !DdyDeviceMediaHelper.this.f7912h.IsInternalCoding && DdyDeviceMediaHelper.this.f7912h.isH265;
                    String str4 = z ? DdyDeviceMediaHelper.this.f7915k : DdyDeviceMediaHelper.this.f7914j;
                    boolean z2 = !TextUtils.isEmpty(DdyDeviceMediaHelper.this.f7948a);
                    String str5 = DdyDeviceMediaHelper.this.f7912h.StreamParam;
                    if (z2) {
                        DdyDeviceMediaHelper.this.f7912h.StreamParam = DdyDeviceMediaHelper.this.f7912h.StreamParam.replace(":", "|");
                        String[] split = str5.split("\\|");
                        if (split.length >= 3) {
                            String str6 = split[0];
                            int parseInt = Integer.parseInt(split[1]);
                            int parseInt2 = Integer.parseInt(split[2]);
                            EncodeParams encodeParams = new EncodeParams();
                            encodeParams.phoneIP = str6;
                            encodeParams.video_port = parseInt;
                            encodeParams.audio_port = parseInt2;
                            encodeParams.bitrate = Integer.parseInt(str4);
                            encodeParams.sign = DdyDeviceMediaHelper.this.f7916l;
                            encodeParams.videowidth = -1;
                            encodeParams.videoheight = -1;
                            encodeParams.transport_mode = orderSteamServerRespone.TransportMode;
                            encodeParams.encode_type = orderSteamServerRespone.ServerType;
                            encodeParams.isPush = SdkKeyUtil.getInstance().isPush() ? 1 : 0;
                            encodeParams.other_param = orderSteamServerRespone.OtherParam;
                            encodeParams.order_id = String.valueOf(DdyDeviceMediaHelper.this.f7912h.OrderId);
                            encodeParams.encode_place = DdyDeviceMediaHelper.this.f7912h.IsInternalCoding ? 1 : 0;
                            String a = C1123f.m21845a(new MultiVideoParams(orderSteamServerRespone.AnboxStreamUrl, encodeParams.toJson(), DdyDeviceMediaHelper.this.f7948a, DdyDeviceMediaHelper.this.f7950c ? 1 : 0));
                            CLog.m21882i("DDY_PARAMS", a);
                            orderSteamServerRespone.AnboxStreamUrl = orderSteamServerRespone.ProxyUrl;
                            str3 = a;
                        } else {
                            return;
                        }
                    } else {
                        str3 = str5;
                    }
                    MWYSdkBean aVar = new MWYSdkBean(DdyDeviceMediaHelper.this.f7912h.Phone, orderSteamServerRespone.AnboxStreamUrl, str3, z ? DdyDeviceMediaHelper.this.f7915k : DdyDeviceMediaHelper.this.f7914j, DdyDeviceMediaHelper.this.f7912h.Ctrl, DdyDeviceMediaHelper.this.f7912h.Adb, DdyDeviceMediaHelper.this.f7912h.OrderId, 2, str2, z, z2, DdyDeviceMediaHelper.this.f7912h.StreamType, orderSteamServerRespone.TransportMode, orderSteamServerRespone.OtherParam, DdyDeviceMediaHelper.this.f7912h.IsInternalCoding);
                    CLog.m21882i("sdk-device", "playmedia onSuccess " + aVar.toString());
                    DdyDeviceMediaHelper.this.f7909e.mo21661a(aVar.f7231g, aVar);
                    callback.success(aVar);
                }
            }

            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str3) {
                CLog.m21883e("sdk-device", "playmedia onFail code=" + ddyOrderErrorConstants + ",msg=" + str3);
                callback.failuer(DdyDeviceMediaHelper.this.m21224a(ddyOrderErrorConstants), String.format("{\"DdyOrderErrorConstants\":%d,\"msg\":\"%s\"}", Integer.valueOf(ddyOrderErrorConstants.value()), str3));
            }
        });
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void changeMedia(long j, String str, String str2, DdyDeviceMediaContract.Callback callback) {
        if (this.f7912h == null) {
            m21217c(j, str, str2, callback);
        } else {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "changeMedia not complete yet.");
        }
    }

    /* renamed from: c */
    private void m21217c(long j, String str, String str2, final DdyDeviceMediaContract.Callback callback) {
        CLog.m21882i("sdk-device", "changemedia orderId=" + j + ",ucid=" + str + ",token=" + this.f7916l);
        m21223b();
        this.f7918n = 0L;
        this.f7917m = 0L;
        this.f7921q = 0L;
        this.f7922r = System.currentTimeMillis();
        DdyOrderHelperEx.m21149a().m21148a(j, str, str2, this.f7916l, (String) null, new DdyOrderContract.Callback() { // from class: com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper.4
            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onSuccess(Object obj) {
                if (!(obj instanceof DdyOrderStatusAlterRespone)) {
                    callback.failuer(DdyDeviceErrorConstants.DDE_Exception, "requestOrderStart object type error.");
                    return;
                }
                DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone = (DdyOrderStatusAlterRespone) obj;
                DdyDeviceMediaHelper.this.f7930z = ddyOrderStatusAlterRespone.isH265;
                String str3 = ddyOrderStatusAlterRespone.isH265 ? DdyDeviceMediaHelper.this.f7915k : DdyDeviceMediaHelper.this.f7914j;
                DdyDeviceMediaHelper.this.f7927w = str3;
                CLog.m21882i("sdk-device", "setPullStreamRate=" + str3 + ", isH265=" + ddyOrderStatusAlterRespone.isH265);
                MWYSdkBean aVar = new MWYSdkBean(ddyOrderStatusAlterRespone.PhoneName, ddyOrderStatusAlterRespone.PullStreamTcpUrl, ddyOrderStatusAlterRespone.PullStreamParam, str3, ddyOrderStatusAlterRespone.DeviceTcpHost, ddyOrderStatusAlterRespone.AdbUrl, (long) ddyOrderStatusAlterRespone.OrderId, ddyOrderStatusAlterRespone.YunDeviceType, ddyOrderStatusAlterRespone.DeviceSigner, ddyOrderStatusAlterRespone.isH265, false, ddyOrderStatusAlterRespone.ServerType, ddyOrderStatusAlterRespone.TransportMode, ddyOrderStatusAlterRespone.OtherParam, false);
                StringBuilder sb = new StringBuilder();
                sb.append("playmedia onSuccess ");
                sb.append(aVar.toString());
                CLog.m21882i("sdk-device", sb.toString());
                DdyDeviceMediaHelper.this.f7909e.mo21655b(ddyOrderStatusAlterRespone.OrderId, aVar);
                callback.success(aVar);
            }

            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str3) {
                CLog.m21883e("sdk-device", "changeMedia onFail code=" + ddyOrderErrorConstants + ",msg=" + str3);
                callback.failuer(DdyDeviceMediaHelper.this.m21224a(ddyOrderErrorConstants), String.format("{\"DdyOrderErrorConstants\":%d,\"msg\":\"%s\"}", Integer.valueOf(ddyOrderErrorConstants.value()), str3));
            }
        });
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void setReConnect(boolean z) {
        this.f7909e.mo21657a(z);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void switchVoice(boolean z) {
        this.f7909e.mo21654b(z);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void switchControllerKeyEvent(boolean z) {
        this.f7909e.mo21652c(z);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public void changeBitrate(int i) {
        this.f7909e.mo21663a(i);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IMedia
    public boolean isCanCtrl() {
        return this.f7909e.mo21653c();
    }

    public void setImeType(String str) {
        XBYUserInfo xBYUserInfo = this.f7911g;
        if (xBYUserInfo != null) {
            xBYUserInfo.imeType = str;
        }
    }

    public long getAvgPing() {
        if (SdkUtils.m21760b()) {
            long j = this.f7918n;
            if (j != 0) {
                long j2 = this.f7917m;
                if (j2 != 0) {
                    return j2 / j;
                }
            }
        }
        return 0L;
    }

    public int getVideoAvgFPS() {
        if (!SdkUtils.m21760b()) {
            return 0;
        }
        long j = this.f7920p;
        if (j == 0) {
            return 0;
        }
        long j2 = this.f7919o;
        if (j2 != 0) {
            return (int) (j2 / j);
        }
        return 0;
    }

    public long getMaxPing() {
        return this.f7921q;
    }

    public long getFirstFrameTime() {
        return this.f7924t;
    }

    public int getStartDelay() {
        return (int) this.f7924t;
    }

    public long getTrialDuration() {
        long j = 0;
        for (Map.Entry<String, Long> entry : this.f7925u.entrySet()) {
            Long value = entry.getValue();
            if (value != null) {
                j += value.longValue();
            }
            if (j == 0) {
                return 0L;
            }
        }
        return j;
    }

    public int getBlockFrameNumber() {
        if (this.f7926v.isEmpty()) {
            return 0;
        }
        int i = 0;
        for (Integer num : this.f7926v.values()) {
            if (num != null) {
                i += num.intValue() > 0 ? num.intValue() : 0;
            }
        }
        return i;
    }

    /* renamed from: c */
    private List<BlockFrameNumbersBean> m21218c() {
        ArrayList arrayList = new ArrayList();
        if (!this.f7926v.isEmpty()) {
            for (Map.Entry<Integer, Integer> entry : this.f7926v.entrySet()) {
                if (entry != null) {
                    BlockFrameNumbersBean blockFrameNumbersBean = new BlockFrameNumbersBean();
                    blockFrameNumbersBean.Ping = entry.getKey().intValue();
                    blockFrameNumbersBean.BlockFrameNumber = entry.getValue().intValue();
                    arrayList.add(blockFrameNumbersBean);
                }
            }
        }
        return arrayList;
    }

    public float getBlockFrameRate() {
        if (this.f7918n == 0) {
            return 0.0f;
        }
        return getBlockFrameNumber() / ((float) this.f7918n);
    }

    public Map<String, Long> getBitRates() {
        return this.f7925u;
    }

    public int getAvgBitRate() {
        long j = 0;
        long j2 = 0;
        for (Map.Entry<String, Long> entry : this.f7925u.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            if (!TextUtils.isEmpty(key) && value != null) {
                j += Integer.parseInt(key) * value.longValue();
                j2 += value.longValue();
            }
            if (j2 == 0) {
                return 0;
            }
        }
        return (int) (((float) j) / ((float) j2));
    }

    public void clearPingAndFirst() {
        this.f7917m = 0L;
        this.f7921q = 0L;
    }

    public void addClientStatData(DdyDeviceMediaHelper ddyDeviceMediaHelper, String str, String str2, long j) {
        if (!SdkUtils.m21760b()) {
            CLog.m21883e("sdk-game", "just yungame can call this function addClientStatData.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Long> entry : ddyDeviceMediaHelper.getBitRates().entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            if (!TextUtils.isEmpty(key) && value != null) {
                int parseInt = Integer.parseInt(key);
                AddClientStatDataRequest.BitRatesBean bitRatesBean = new AddClientStatDataRequest.BitRatesBean();
                bitRatesBean.BitRate = parseInt;
                bitRatesBean.Duration = value.longValue();
                arrayList.add(bitRatesBean);
            }
        }
        long trialDuration = ddyDeviceMediaHelper.getTrialDuration();
        int avgBitRate = ddyDeviceMediaHelper.getAvgBitRate();
        int videoAvgFPS = ddyDeviceMediaHelper.getVideoAvgFPS();
        if (!(trialDuration == 0 || avgBitRate == 0)) {
            new DdyDeviceRequestModel().m21191a(str, this.f7929y, this.f7930z ? 2 : 1, videoAvgFPS, str2, j, ddyDeviceMediaHelper.getStartDelay(), trialDuration, ddyDeviceMediaHelper.getBlockFrameNumber(), ddyDeviceMediaHelper.m21218c(), arrayList, avgBitRate, ddyDeviceMediaHelper.getBlockFrameRate(), new IUIDataListener() { // from class: com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper.5
                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataSuccess(Object obj) {
                    DdyDeviceMediaHelper.this.f7918n = 0L;
                    DdyDeviceMediaHelper.this.f7922r = 0L;
                    DdyDeviceMediaHelper.this.f7924t = 0L;
                }

                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataError(Exception exc) {
                    DdyDeviceMediaHelper.this.f7918n = 0L;
                    DdyDeviceMediaHelper.this.f7922r = 0L;
                    DdyDeviceMediaHelper.this.f7924t = 0L;
                }
            });
        }
    }

    public void setDeviceToken(String str) {
        this.f7916l = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public DdyDeviceErrorConstants m21224a(DdyOrderErrorConstants ddyOrderErrorConstants) {
        DdyDeviceErrorConstants ddyDeviceErrorConstants = DdyDeviceErrorConstants.DDE_DOE_ERROR;
        if (ddyOrderErrorConstants == DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_20) {
            return DdyDeviceErrorConstants.DDE_DOE_CODE_20;
        }
        if (ddyOrderErrorConstants == DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_21) {
            return DdyDeviceErrorConstants.DDE_DOE_CODE_21;
        }
        if (ddyOrderErrorConstants == DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_22) {
            return DdyDeviceErrorConstants.DDE_DOE_CODE_22;
        }
        return ddyOrderErrorConstants == DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_24 ? DdyDeviceErrorConstants.DDE_DOE_CODE_24 : ddyDeviceErrorConstants;
    }
}
