package com.lbd.xj.socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.common.utils.StringUtil;
import com.common.utils.VMProperUtil;
import com.common.utils.log.LogUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lbd.xj.app.AppConfig;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.manager.AppUpdatemanger;
import com.lbd.xj.ui.dialog.FloatDialog;
import com.lbd.xj.socket.model.CheckUpdate;
import com.lbd.xj.socket.model.FileBean;
import com.lbd.xj.socket.model.FileTransfer;
import com.lbd.xj.socket.model.FileType;
import com.lbd.xj.socket.model.GetKeyBean;
import com.lbd.xj.socket.model.ImprotBean;
import com.lbd.xj.socket.model.MessageMapBean;
import com.lbd.xj.socket.model.MessgeBean;
import com.lbd.xj.socket.model.NotificationSet;
import com.lbd.xj.socket.model.Resolution;
import com.lbd.xj.socket.model.ResolvingModle;
import com.lbd.xj.socket.model.SaveBean;
import com.lbd.xj.socket.model.SendBean;
import com.lbd.xj.socket.model.SendListBean;
import com.lbd.xj.socket.model.SetInfo;
import com.lbd.xj.socket.model.SocketData;
import com.nrzs.data.xnkj.bean.request.FeedBackRequestInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p110z1.EventBus;
import p110z1.EventBusMessage;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FileSizeCallback;
import p110z1.FileTask;
import p110z1.FloatingPermissionCompat;
import p110z1.FwManager;
import p110z1.GeneralUtil;
import p110z1.GsonUtil;
import p110z1.PreferencesUtil;
import p110z1.SharepreferenceUtil;
import p110z1.SocketConstants;
import p110z1.ValReceiver;
import p110z1.ValShare;
import p110z1.acf;
import p110z1.aee;
import p110z1.aef;
import p110z1.aes;
import p110z1.apa;

/* renamed from: com.lbd.xj.socket.SocketManagerServer */
/* loaded from: classes.dex */
public class SocketManagerServer {

    /* renamed from: f */
    private static final String f9493f = "SocketManagerServer";

    /* renamed from: i */
    private static SocketManagerServer f9494i;

    /* renamed from: a */
    Bitmap f9495a;

    /* renamed from: b */
    AbstractC1534a f9496b;

    /* renamed from: c */
    private String f9497c;

    /* renamed from: d */
    private String f9498d;

    /* renamed from: e */
    private String f9499e;

    /* renamed from: g */
    private Context f9500g;

    /* renamed from: h */
    private SocketManager f9501h;

    /* renamed from: com.lbd.xj.socket.SocketManagerServer$a */
    /* loaded from: classes.dex */
    public interface AbstractC1534a {
        /* renamed from: a */
        void mo19536a();
    }

    public native int killuid(int i);

    /* renamed from: com.lbd.xj.socket.SocketManagerServer$b */
    /* loaded from: classes.dex */
    public class C1535b {

        /* renamed from: a */
        SocketManagerServer f9535a;

        public C1535b(SocketManagerServer socketManagerServer) {
            this.f9535a = socketManagerServer;
        }
    }

    /* renamed from: a */
    static void m19701a(SocketManagerServer socketManagerServer, String str, int i) {
        socketManagerServer.m19669l(str, i);
    }

    /* renamed from: a */
    static Context m19702a(SocketManagerServer socketManagerServer) {
        return socketManagerServer.f9500g;
    }

    /* renamed from: b */
    static String m19690b(SocketManagerServer socketManagerServer) {
        return socketManagerServer.f9497c;
    }

    /* renamed from: c */
    static String m19685c(SocketManagerServer socketManagerServer) {
        return socketManagerServer.f9498d;
    }

    /* renamed from: a */
    public void m19705a() {
        this.f9501h.m19643a(new SocketLongCallBack() { // from class: com.lbd.xj.socket.SocketManagerServer.1
            @Override // com.lbd.xj.socket.SocketLongCallBack
            /* renamed from: a */
            public void mo19653a() {
            }

            @Override // com.lbd.xj.socket.SocketLongCallBack
            /* renamed from: b */
            public void mo19651b() {
            }

            @Override // com.lbd.xj.socket.SocketLongCallBack
            /* renamed from: a */
            public void mo19652a(String str, int i) {
                LogUtils.m22036e("SocketLongCallBack", "data:" + str + "  SocketId:" + i);
                SocketManagerServer.m19701a(SocketManagerServer.this, str, i);
            }
        });
    }

    /* renamed from: b */
    public static synchronized SocketManagerServer m19692b() {
        SocketManagerServer socketManagerServer;
        synchronized (SocketManagerServer.class) {
            if (f9494i == null) {
                f9494i = new SocketManagerServer();
            }
            socketManagerServer = f9494i;
        }
        return socketManagerServer;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: c */
    private void m19683c(String str, int i) {
        if (str.contains(SocketConstants.f15260w)) {
            NotificationSet notificationSet = (NotificationSet) GsonUtil.m13967a(str.replace(SocketConstants.f15260w, ""), NotificationSet.class);
            if (!StringUtil.isEmpty(notificationSet.getBitmap())) {
                this.f9495a = ConvertUtils.m22439f(EncodeUtils.m22384d(URLDecoder.decode(notificationSet.getBitmap())));
            }
            if (StringUtil.isEmpty(notificationSet.getTitle())) {
                notificationSet.setTitle("");
            }
            if (StringUtil.isEmpty(notificationSet.getContent())) {
                notificationSet.setContent("");
            }
            LogUtils.m22037e(notificationSet.getContent());
        }
    }

    /* renamed from: d */
    private void m19680d(String str, int i) {
        if (str.contains(SocketConstants.f15261x)) {
            LogUtils.m22036e("handelVmosCrash", str.trim());
        }
    }

    /* renamed from: e */
    private void m19677e(String str, int i) {
        try {
            if (str.contains(SocketConstants.f15262y) && str.replace(SocketConstants.f15262y, "").contains(acf.f15189n)) {
                String str2 = (String) PreferencesUtil.m13937a().m13927b(acf.f15189n, "");
                if (str2.equals("")) {
                    ArrayList<FileBean> c = aee.m14178c(this.f9500g);
                    m19698a(acf.f15189n + GsonUtil.m13969a(c), i);
                    return;
                }
                m19698a(acf.f15189n + str2, i);
                new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.8
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            PreferencesUtil.m13937a().m13931a(acf.f15189n, (Object) GsonUtil.m13969a(aee.m14178c(SocketManagerServer.m19702a(SocketManagerServer.this))));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    private void m19675f(String str, int i) {
        if (SocketConstants.f15263z.equals(str)) {
            AbstractC1534a aVar = this.f9496b;
            if (aVar != null) {
                aVar.mo19536a();
            }
            GeneralUtil.m14003f();
            GeneralUtil.m14015d();
            killuid(Process.myUid());
            GeneralUtil.m14024c(this.f9500g.getApplicationContext());
        }
    }

    /* renamed from: g */
    private void m19674g(String str, int i) {
        if (str.contains(SocketConstants.f15202A)) {
            String replace = str.replace(SocketConstants.f15202A, "");
            ArrayList arrayList = new ArrayList();
            SendListBean sendListBean = new SendListBean();
            try {
                LogUtils.m22036e("data.trim()", replace.trim());
                for (GetKeyBean getKeyBean : GsonUtil.m13962b(replace.trim(), GetKeyBean.class)) {
                    SaveBean saveBean = new SaveBean();
                    saveBean.setKey(getKeyBean.getKey());
                    saveBean.setValue((String) PreferencesUtil.m13937a().m13927b(getKeyBean.getKey(), ""));
                    arrayList.add(saveBean);
                }
                sendListBean.setCode(0);
                sendListBean.setMsg("OK");
                sendListBean.setDataList(arrayList);
                m19698a(SocketConstants.f15202A + GsonUtil.m13969a(sendListBean), i);
            } catch (Exception e) {
                LogUtils.m22037e(e.getMessage());
                sendListBean.setCode(1);
                sendListBean.setMsg("Error");
                m19698a(SocketConstants.f15202A + GsonUtil.m13969a(sendListBean), i);
            }
        } else if (str.contains(SocketConstants.f15203B)) {
            String replace2 = str.replace(SocketConstants.f15203B, "");
            SendBean sendBean = new SendBean();
            try {
                for (SaveBean saveBean2 : GsonUtil.m13962b(replace2, SaveBean.class)) {
                    PreferencesUtil.m13937a().m13931a(saveBean2.getKey(), (Object) saveBean2.getValue());
                }
                sendBean.setCode(0);
                sendBean.setMsg("OK");
                m19698a(SocketConstants.f15203B + GsonUtil.m13969a(sendBean), i);
            } catch (Exception unused) {
                sendBean.setCode(1);
                sendBean.setMsg("Error");
                m19698a(SocketConstants.f15203B + GsonUtil.m13969a(sendBean), i);
            }
        }
    }

    /* renamed from: h */
    private void m19673h(String str, final int i) {
        try {
            if (str.contains(SocketConstants.f15204C)) {
                if (str.contains(SocketConstants.f15205D)) {
                    new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.9
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ArrayList<FileBean> a = aee.m14188a(SocketManagerServer.m19702a(SocketManagerServer.this), "docx");
                                a.addAll(aee.m14188a(SocketManagerServer.m19702a(SocketManagerServer.this), "doc"));
                                SocketManagerServer socketManagerServer = SocketManagerServer.this;
                                socketManagerServer.m19698a(SocketConstants.f15205D + GsonUtil.m13969a(a), i);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else if (str.contains(SocketConstants.f15206E)) {
                    new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.10
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ArrayList<FileBean> a = aee.m14188a(SocketManagerServer.m19702a(SocketManagerServer.this), "xlsx");
                                a.addAll(aee.m14188a(SocketManagerServer.m19702a(SocketManagerServer.this), "xls"));
                                SocketManagerServer socketManagerServer = SocketManagerServer.this;
                                socketManagerServer.m19698a(SocketConstants.f15206E + GsonUtil.m13969a(a), i);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else if (str.contains(SocketConstants.f15207F)) {
                    new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.11
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ArrayList<FileBean> a = aee.m14188a(SocketManagerServer.m19702a(SocketManagerServer.this), "pdf");
                                SocketManagerServer socketManagerServer = SocketManagerServer.this;
                                socketManagerServer.m19698a(SocketConstants.f15207F + GsonUtil.m13969a(a), i);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else if (str.contains(SocketConstants.f15208G)) {
                    new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.12
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ArrayList<FileBean> a = aee.m14188a(SocketManagerServer.m19702a(SocketManagerServer.this), "ppt");
                                SocketManagerServer socketManagerServer = SocketManagerServer.this;
                                socketManagerServer.m19698a(SocketConstants.f15208G + GsonUtil.m13969a(a), i);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    private void m19672i(String str, final int i) {
        try {
            if (str.contains(SocketConstants.f15209H)) {
                String replace = str.replace(SocketConstants.f15209H, "");
                if (replace.contains(SocketConstants.f15210I)) {
                    ArrayList<FileBean> d = aee.m14175d(this.f9500g);
                    m19698a(SocketConstants.f15210I + GsonUtil.m13969a(d), i);
                } else if (replace.contains(SocketConstants.f15211J)) {
                    final String replace2 = replace.replace(SocketConstants.f15211J, "");
                    new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.13
                        @Override // java.lang.Runnable
                        public void run() {
                            Bitmap a = ImageUtils.m23941a(ImageUtils.m23918a(replace2), 102400L);
                            String encode = URLEncoder.encode(EncodeUtils.m22387b(ImageUtils.m23939a(a, Bitmap.CompressFormat.PNG)));
                            if (a != null && !a.isRecycled()) {
                                a.recycle();
                            }
                            System.gc();
                            SocketManagerServer socketManagerServer = SocketManagerServer.this;
                            socketManagerServer.m19698a(SocketConstants.f15212K + encode, i);
                        }
                    }).start();
                } else if (replace.contains(SocketConstants.f15212K)) {
                    final String replace3 = replace.replace(SocketConstants.f15212K, "");
                    new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.14
                        @Override // java.lang.Runnable
                        public void run() {
                            String encode = URLEncoder.encode(EncodeUtils.m22387b(ImageUtils.m23939a(ImageUtils.m23918a(replace3), Bitmap.CompressFormat.PNG)));
                            SocketManagerServer socketManagerServer = SocketManagerServer.this;
                            socketManagerServer.m19698a(SocketConstants.f15212K + encode, i);
                        }
                    }).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m19684c(String str) {
        if (str.contains(SocketConstants.f15213L)) {
            String replace = str.replace(SocketConstants.f15213L, "");
            if (!replace.contains(SocketConstants.f15214M)) {
                if (replace.contains(SocketConstants.f15216O)) {
                    m19681d(SocketConstants.f15216O);
                } else if (replace.contains(SocketConstants.f15217P)) {
                    m19681d(SocketConstants.f15217P);
                } else if (replace.contains(SocketConstants.f15218Q)) {
                    m19681d(SocketConstants.f15218Q);
                } else if (replace.contains("RESOLVING")) {
                    String replace2 = replace.replace("RESOLVING", "");
                    ResolvingModle resolvingModle = (ResolvingModle) GsonUtil.m13967a(replace2, ResolvingModle.class);
                    PreferencesUtil.m13937a().m13931a("m_szw", Integer.valueOf(resolvingModle.getWidth()));
                    PreferencesUtil.m13937a().m13931a("m_szh", Integer.valueOf(resolvingModle.getHeight()));
                    PreferencesUtil.m13937a().m13931a("surfaceW", Integer.valueOf(resolvingModle.getWidth()));
                    PreferencesUtil.m13937a().m13931a("surfaceH", Integer.valueOf(resolvingModle.getHeight()));
                    PreferencesUtil.m13937a().m13931a("surfaceDPI", Integer.valueOf(resolvingModle.getDPI()));
                    m19696a(acf.f15170I, (Object) 1);
                    PreferencesUtil.m13937a().m13931a("RESOLVING", (Object) replace2);
                    VMProperUtil.writeDateToLocalFile(VMProperUtil.ISNEEDCLEANLAUNCHER, "true");
                }
            }
        }
    }

    /* renamed from: j */
    private void m19671j(String str, int i) {
        if (str.contains(SocketConstants.f15219R)) {
            this.f9501h.m19632c(i);
        }
    }

    /* renamed from: k */
    private void m19670k(String str, int i) {
        try {
            if (str.contains(SocketConstants.f15222U)) {
                m19698a(SocketConstants.f15222U + GsonUtil.m13969a(aee.m14188a(this.f9500g, "mp4")), i);
            } else if (str.contains(SocketConstants.f15223V)) {
                m19698a(SocketConstants.f15223V + GsonUtil.m13969a(aee.m14188a(this.f9500g, "mp3")), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: l */
    private void m19669l(String str, int i) {
        LogUtils.m22036e("handleSetting", str + " SocketId:" + i);
        m19683c(str, i);
        m19680d(str, i);
        m19675f(str, i);
        m19674g(str, i);
        m19673h(str, i);
        m19670k(str, i);
        m19671j(str, i);
        m19672i(str, i);
        m19677e(str, i);
        m19684c(str);
        if (str.contains(SocketConstants.f15224W)) {
            LogUtils.m22034i(SocketConstants.f15224W, str.replace(SocketConstants.f15224W, ""));
        } else if (str.contains(SocketConstants.f15242e)) {
            m19704a(i);
        } else if (str.contains(SocketConstants.f15245h)) {
            m19691b(i);
        } else if (str.contains(SocketConstants.f15258u)) {
            m19686c(i);
        } else if (str.contains(SocketConstants.f15259v)) {
            m19676f(str);
        } else if (str.contains(SocketConstants.f15246i)) {
            LogUtils.m22035i("收到广告：" + str + ",socketId:" + i);
            m19694a(SocketConstants.f15246i, SharepreferenceUtil.m13882b(ValShare.f16642a, ""), i);
        } else if (str.contains(SocketConstants.f15257t)) {
            m19668m(str, i);
        } else if (str.contains(SocketConstants.f15243f)) {
            boolean z = !str.contains(SocketConstants.f15244g);
            SocketData socketData = (SocketData) GsonUtil.m13967a(str, SocketData.class);
            socketData.initDataBean();
            FileTransfer fileTransfer = (FileTransfer) socketData.mData;
            if (fileTransfer != null) {
                m19700a(fileTransfer, z, i);
            }
        } else if (str.contains(SocketConstants.f15248k)) {
            LogUtils.m22035i("菜单功能");
            FwManager.getInstance().socketMenuXJ();
        } else if (str.contains(SocketConstants.f15249l)) {
            LogUtils.m22035i("通知栏功能");
            FwManager.getInstance().socketNotifyXJ();
        } else if (str.contains(SocketConstants.f15250m)) {
            LogUtils.m22035i("虚拟空间关机");
            FwManager.getInstance().shutDownXJ();
        } else if (str.contains(SocketConstants.f15251n)) {
            LogUtils.m22035i("返回首页");
            FwManager.getInstance().toMain();
        } else if (str.contains(SocketConstants.f15255r)) {
            LogUtils.m22035i("是否有显示悬浮窗权限");
            if (!FloatingPermissionCompat.m14338a().m14337a(XJApp.getInstance().getApplicationContext())) {
                m19694a(SocketConstants.f15255r, ResultTypeConstant.f7213z, i);
            } else {
                m19694a(SocketConstants.f15255r, "1", i);
            }
        } else if (str.contains(SocketConstants.f15253p)) {
            LogUtils.m22035i("是否显示悬浮窗M");
            if (!FloatingPermissionCompat.m14338a().m14337a(XJApp.getInstance().getApplicationContext())) {
                Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), FloatDialog.class);
                intent.setFlags(268435456);
                XJApp.getInstance().getApplicationContext().startActivity(intent);
                m19694a(SocketConstants.f15253p, "1", i);
                return;
            }
            m19678e(str);
            m19694a(SocketConstants.f15253p, ResultTypeConstant.f7213z, i);
        } else if (str.contains(SocketConstants.f15254q)) {
            LogUtils.m22035i("是否显示虚拟键");
            SocketData socketData2 = (SocketData) GsonUtil.m13967a(str, SocketData.class);
            socketData2.initDataBean();
            SetInfo setInfo = (SetInfo) socketData2.mData;
            if (setInfo != null) {
                m19693a(setInfo.isVirtualKey());
            }
            m19694a(SocketConstants.f15254q, ResultTypeConstant.f7213z, i);
        } else if (str.contains(SocketConstants.f15252o)) {
            SocketData socketData3 = (SocketData) GsonUtil.m13967a(str, SocketData.class);
            socketData3.initDataBean();
            Resolution resolution = (Resolution) socketData3.mData;
            if (resolution != null) {
                FwManager.getInstance().socketSetScreenXJ(resolution.getWidth(), resolution.getHeight(), resolution.getDpi());
            }
        } else if (str.contains(SocketConstants.f15225X)) {
            EventBus.m3448a().m3427d(new EventBusMessage(acf.f15184i));
        } else if (str.contains(SocketConstants.f15231ac)) {
            m19687c();
        } else if (str.contains(SocketConstants.f15226Y)) {
            final String replace = str.replace(SocketConstants.f15226Y, "");
            new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.15
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = GsonUtil.m13962b(replace, ImprotBean.class).iterator();
                    while (it.hasNext()) {
                        aef.m14115h(SocketManagerServer.this.f9497c + ((ImprotBean) it.next()).getName());
                    }
                }
            }).start();
        } else if (str.contains(SocketConstants.f15227Z)) {
            String replace2 = str.replace(SocketConstants.f15227Z, "");
            if (replace2.equals("basePath/")) {
                ArrayList arrayList = new ArrayList();
                FileBean fileBean = new FileBean();
                fileBean.setName("内部存储");
                fileBean.setPath(Environment.getExternalStorageDirectory().getAbsolutePath());
                fileBean.setFileType(FileType.directory);
                arrayList.add(fileBean);
                if (!aes.m13854c().equals("无扩展卡")) {
                    FileBean fileBean2 = new FileBean();
                    fileBean2.setName("扩展内存存储");
                    fileBean2.setPath(aes.m13854c());
                    fileBean2.setFileType(FileType.directory);
                    arrayList.add(fileBean2);
                }
                MessgeBean messgeBean = new MessgeBean();
                if (arrayList.size() > 0) {
                    messgeBean.setCode(0);
                    messgeBean.setMsg("OK");
                    messgeBean.setData(arrayList);
                } else {
                    messgeBean.setCode(1);
                    messgeBean.setMsg("Error ");
                    messgeBean.setData(arrayList);
                }
                m19692b().m19699a(SocketConstants.f15227Z + GsonUtil.m13969a(messgeBean));
            } else if (replace2.equals("rootPath/")) {
                FileTask.m14314a().m14311a(Environment.getExternalStorageDirectory().getAbsolutePath(), i);
            } else if (replace2.equals("cardPath/")) {
                FileTask.m14314a().m14311a(aes.m13854c(), i);
            } else {
                FileTask.m14314a().m14311a(replace2, i);
            }
        } else if (str.contains(SocketConstants.f15229aa)) {
            final MessageMapBean messageMapBean = (MessageMapBean) GsonUtil.m13967a(str.replace(SocketConstants.f15229aa, ""), MessageMapBean.class);
            new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.2
                @Override // java.lang.Runnable
                public void run() {
                    for (Map.Entry entry : messageMapBean.getData().entrySet()) {
                        if (aes.m13857b(SocketManagerServer.m19685c(SocketManagerServer.this) + ((FileBean) entry.getValue()).getName(), ((FileBean) entry.getValue()).getPath())) {
                            MessageMapBean messageMapBean2 = new MessageMapBean();
                            messageMapBean2.setCode(0);
                            messageMapBean2.setMsg(((FileBean) entry.getValue()).getName());
                            HashMap hashMap = new HashMap();
                            ((FileBean) entry.getValue()).setVmosPath(SocketManagerServer.m19685c(SocketManagerServer.this) + ((FileBean) entry.getValue()).getName());
                            hashMap.put(((FileBean) entry.getValue()).getName(), entry.getValue());
                            messageMapBean2.setData(hashMap);
                            SocketManagerServer socketManagerServer = SocketManagerServer.this;
                            socketManagerServer.m19699a(SocketConstants.f15229aa + GsonUtil.m13969a(messageMapBean2));
                        } else {
                            MessageMapBean messageMapBean3 = new MessageMapBean();
                            messageMapBean3.setCode(1);
                            messageMapBean3.setMsg(((FileBean) entry.getValue()).getName());
                            HashMap hashMap2 = new HashMap();
                            ((FileBean) entry.getValue()).setVmosPath(SocketManagerServer.m19685c(SocketManagerServer.this) + ((FileBean) entry.getValue()).getName());
                            hashMap2.put(((FileBean) entry.getValue()).getName(), entry.getValue());
                            messageMapBean3.setData(hashMap2);
                            SocketManagerServer socketManagerServer2 = SocketManagerServer.this;
                            socketManagerServer2.m19699a(SocketConstants.f15229aa + GsonUtil.m13969a(messageMapBean3));
                        }
                    }
                }
            }).start();
        } else if (str.contains(SocketConstants.f15230ab)) {
            final MessageMapBean messageMapBean2 = (MessageMapBean) GsonUtil.m13967a(str.replace(SocketConstants.f15230ab, ""), MessageMapBean.class);
            new Thread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.3
                @Override // java.lang.Runnable
                public void run() {
                    for (Map.Entry entry : messageMapBean2.getData().entrySet()) {
                        LogUtils.m22037e("导入目录是否创建成功" + aef.m14124e(SocketManagerServer.this.f9498d));
                        if (aes.m13857b(SocketManagerServer.this.f9498d + ((FileBean) entry.getValue()).getName(), ((FileBean) entry.getValue()).getPath())) {
                            MessageMapBean messageMapBean3 = new MessageMapBean();
                            messageMapBean3.setCode(0);
                            messageMapBean3.setMsg(((FileBean) entry.getValue()).getName());
                            HashMap hashMap = new HashMap();
                            ((FileBean) entry.getValue()).setVmosPath(SocketManagerServer.m19685c(SocketManagerServer.this) + ((FileBean) entry.getValue()).getName());
                            hashMap.put(((FileBean) entry.getValue()).getName(), entry.getValue());
                            messageMapBean3.setData(hashMap);
                            SocketManagerServer socketManagerServer = SocketManagerServer.this;
                            socketManagerServer.m19699a(SocketConstants.f15230ab + GsonUtil.m13969a(messageMapBean3));
                        } else {
                            MessageMapBean messageMapBean4 = new MessageMapBean();
                            messageMapBean4.setCode(1);
                            messageMapBean4.setMsg(((FileBean) entry.getValue()).getName());
                            HashMap hashMap2 = new HashMap();
                            ((FileBean) entry.getValue()).setVmosPath(SocketManagerServer.m19685c(SocketManagerServer.this) + ((FileBean) entry.getValue()).getName());
                            hashMap2.put(((FileBean) entry.getValue()).getName(), entry.getValue());
                            messageMapBean4.setData(hashMap2);
                            SocketManagerServer socketManagerServer2 = SocketManagerServer.this;
                            socketManagerServer2.m19699a(SocketConstants.f15230ab + GsonUtil.m13969a(messageMapBean4));
                        }
                    }
                }
            }).start();
        } else if (str.contains(SocketConstants.f15232ad)) {
            m19689b(str.replace(SocketConstants.f15232ad, ""));
        }
    }

    /* renamed from: a */
    public void m19697a(String str, Context context) {
        if (context == null) {
            LogUtils.m22036e("init", "context  null");
            return;
        }
        this.f9499e = str;
        this.f9500g = context;
        this.f9501h = new SocketManager(str);
        this.f9498d = this.f9500g.getApplicationInfo().dataDir + "/osimg/r/ot01/storage/sdcard/虚拟空间文件中转站/";
        this.f9497c = Environment.getExternalStorageDirectory().getAbsolutePath() + "/虚拟空间文件中转站/";
        aef.m14124e(this.f9498d);
        aef.m14124e(this.f9497c);
    }

    /* renamed from: a */
    public Object m19696a(String str, Object obj) {
        PreferencesUtil.m13937a().m13931a(str, obj);
        return str;
    }

    /* renamed from: a */
    public void m19698a(String str, int i) {
        this.f9501h.m19647a(i, str);
    }

    /* renamed from: a */
    public void m19694a(String str, String str2, int i) {
        this.f9501h.m19646a(i, str2, str);
    }

    /* renamed from: b */
    public void m19688b(String str, int i) {
        this.f9501h.m19634b(i, str);
    }

    /* renamed from: a */
    public void m19699a(String str) {
        if (this.f9501h != null && !StringUtil.isEmpty(str)) {
            this.f9501h.m19639a(str);
        }
    }

    /* renamed from: a */
    public void m19695a(String str, String str2) {
        if (this.f9501h != null && !StringUtil.isEmpty(str)) {
            this.f9501h.m19638a(str, str2);
        }
    }

    /* renamed from: d */
    private void m19681d(String str) {
        Intent intent = new Intent();
        intent.putExtra("key", str);
        intent.setAction(ValReceiver.f15490a);
        XJApp.getInstance().getApplicationContext().sendBroadcast(intent);
    }

    /* renamed from: a */
    public void m19703a(AbstractC1534a aVar) {
        this.f9496b = aVar;
    }

    /* renamed from: b */
    public void m19689b(final String str) {
        ((Activity) this.f9500g).runOnUiThread(new Runnable() { // from class: com.lbd.xj.socket.SocketManagerServer.4
            @Override // java.lang.Runnable
            public void run() {
                ClipboardManager clipboardManager = (ClipboardManager) SocketManagerServer.m19702a(SocketManagerServer.this).getSystemService("clipboard");
                ClipData newPlainText = ClipData.newPlainText(null, str);
                if (newPlainText != null && clipboardManager != null) {
                    clipboardManager.setPrimaryClip(newPlainText);
                }
            }
        });
    }

    /* renamed from: a */
    private void m19704a(int i) {
        if (TextUtils.isEmpty((String) PreferencesUtil.m13937a().m13927b(acf.f15189n, ""))) {
            try {
                PreferencesUtil.m13937a().m13931a(acf.f15189n, (Object) GsonUtil.m13969a(aee.m14178c(XJApp.getInstance().getApplicationContext())));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        m19688b(SocketConstants.f15242e, i);
    }

    /* renamed from: a */
    private void m19700a(final FileTransfer fileTransfer, final boolean z, final int i) {
        C1545f.m19586c(new C1545f.AbstractC1549a() { // from class: com.lbd.xj.socket.SocketManagerServer.5
            @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Object doInBackground() throws Throwable {
                return aes.m13864a(fileTransfer.getSrc(), fileTransfer.getDst(), XJApp.getInstance().getApplicationInfo(), new FileSizeCallback() { // from class: com.lbd.xj.socket.SocketManagerServer.5.1
                    @Override // p110z1.FileSizeCallback
                    /* renamed from: a */
                    public void mo14340a(int i2) {
                        Log.e("CopyAppWithProgress", fileTransfer.toString());
                        if (z) {
                            fileTransfer.setProgress(i2);
                            SocketManagerServer.this.m19694a(SocketConstants.f15243f, fileTransfer.toString(), i);
                        }
                    }

                    @Override // p110z1.FileSizeCallback
                    /* renamed from: a */
                    public void mo14339a(String str) {
                        fileTransfer.setProgress(100);
                        fileTransfer.setPath(str.replace("/data/user/0/" + AppConfig.f9441h + "/osimg/r/ot01", ""));
                        if (z) {
                            SocketManagerServer.this.m19694a(SocketConstants.f15243f, fileTransfer.toString(), i);
                        } else {
                            SocketManagerServer.this.m19694a(SocketConstants.f15244g, fileTransfer.toString(), i);
                        }
                        Log.e("CopyAppWithProgress", fileTransfer.toString());
                    }

                    @Override // p110z1.FileSizeCallback
                    /* renamed from: a */
                    public void mo14341a() {
                        fileTransfer.setProgress(-1);
                        if (z) {
                            SocketManagerServer.this.m19694a(SocketConstants.f15243f, fileTransfer.toString(), i);
                        } else {
                            SocketManagerServer.this.m19694a(SocketConstants.f15244g, fileTransfer.toString(), i);
                        }
                    }
                });
            }
        });
    }

    /* renamed from: b */
    private void m19691b(int i) {
        if (TextUtils.isEmpty((String) PreferencesUtil.m13937a().m13927b(acf.f15188m, ""))) {
            try {
                PreferencesUtil.m13937a().m13931a(acf.f15188m, (Object) GsonUtil.m13969a(aee.m14178c(XJApp.getInstance().getApplicationContext())));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        m19688b(SocketConstants.f15245h, i);
    }

    /* renamed from: c */
    private void m19686c(int i) {
        FeedBackRequestInfo feedBackRequestInfo = new FeedBackRequestInfo();
        feedBackRequestInfo.mobileModel = Build.MODEL;
        feedBackRequestInfo.mobileVendor = Build.BRAND;
        feedBackRequestInfo.sysVersion = Build.VERSION.RELEASE;
        m19694a(SocketConstants.f15258u, apa.m11879a(feedBackRequestInfo), i);
    }

    /* renamed from: e */
    private void m19678e(String str) {
        SocketData socketData = (SocketData) GsonUtil.m13967a(str, SocketData.class);
        socketData.initDataBean();
        SetInfo setInfo = (SetInfo) socketData.mData;
        if (setInfo != null) {
            FwManager.getInstance().socketSetFloatXJ(setInfo.isFloating() ? 0 : 8);
        }
    }

    /* renamed from: c */
    private void m19687c() {
        EventCollectManager.m12642a().m12640a(XJApp.getInstance().getApplicationContext(), "后台挂机虚拟桌面应用导入点击", "后台挂机虚拟桌面应用导入点击", EventConstants.f16416T);
    }

    /* renamed from: a */
    public void m19693a(boolean z) {
        C1545f.m19586c(new C1545f.AbstractC1549a() { // from class: com.lbd.xj.socket.SocketManagerServer.6
            @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Object doInBackground() throws Throwable {
                return null;
            }
        });
    }

    /* renamed from: f */
    private void m19676f(String str) {
        SocketData socketData = (SocketData) GsonUtil.m13967a(str, SocketData.class);
        socketData.initDataBean();
        FeedBackRequestInfo feedBackRequestInfo = (FeedBackRequestInfo) socketData.mData;
    }

    /* renamed from: m */
    private void m19668m(String str, final int i) {
        SocketData socketData = (SocketData) GsonUtil.m13967a(str, SocketData.class);
        socketData.initDataBean();
        final CheckUpdate checkUpdate = (CheckUpdate) socketData.mData;
        if (checkUpdate == null) {
            return;
        }
        if (checkUpdate.getIsUpdate() == 1) {
            checkUpdate.setVersionName("1.3.5");
            AppUpdatemanger.INSTANCE.isNewVersion(new AppUpdatemanger.AbstractC1501a() { // from class: com.lbd.xj.socket.SocketManagerServer.7
                @Override // com.lbd.xj.manager.AppUpdatemanger.AbstractC1501a
                /* renamed from: a */
                public void mo19667a(boolean z) {
                    if (z) {
                        checkUpdate.setIsUpdate(1);
                    } else {
                        checkUpdate.setIsUpdate(0);
                    }
                    SocketManagerServer.this.m19694a(SocketConstants.f15257t, GsonUtil.m13969a(checkUpdate), i);
                }
            });
        } else if (checkUpdate.getIsUpdate() == 3) {
            AppUpdatemanger.INSTANCE.checkAppUpdate(true);
        }
    }
}
