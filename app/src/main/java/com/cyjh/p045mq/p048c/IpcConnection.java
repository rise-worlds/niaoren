package com.cyjh.p045mq.p048c;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.LocalSocket;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.inputmethod.InputConnection;
import com.alipay.sdk.widget.C0675j;
import com.cyjh.mobileanjian.ipc.AppAgent;
import com.cyjh.mobileanjian.ipc.CrashRunnerState;
import com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.interfaces.OnMqScriptCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnRpcCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScreenShotCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptMessageCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnUiElementCallback;
import com.cyjh.mobileanjian.ipc.interfaces.ScriptStateObserver;
import com.cyjh.mobileanjian.ipc.rpc.Invocator;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.cyjh.mobileanjian.ipc.share.proto.IpcRaw;
import com.cyjh.mobileanjian.ipc.utils.ContactsUtils;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.p045mq.p046a.MyApplication;
import com.cyjh.p045mq.p047b.ClientInfo;
import com.cyjh.p045mq.sdk.MqRunner;
import com.cyjh.p045mq.sdk.inf.OnElfCallback;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;
import com.ime.input.InputMethodManager;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

/* renamed from: com.cyjh.mq.c.b */
/* loaded from: classes.dex */
public final class IpcConnection extends BaseSocketConnection {

    /* renamed from: t */
    private static final int f8803t = 4096;

    /* renamed from: u */
    private static final int f8804u = 1024;

    /* renamed from: c */
    Context f8807c;

    /* renamed from: f */
    public ClientInfo f8810f;

    /* renamed from: m */
    public OnRequestCallback f8817m;

    /* renamed from: n */
    OnMqScriptCallback f8818n;

    /* renamed from: o */
    OnElfCallback f8819o;

    /* renamed from: p */
    OnUiElementCallback f8820p;

    /* renamed from: q */
    OnSpecialMqCmdCallback f8821q;

    /* renamed from: v */
    private MqmHandler f8824v;

    /* renamed from: a */
    boolean f8805a = true;

    /* renamed from: b */
    boolean f8806b = false;

    /* renamed from: d */
    boolean f8808d = false;

    /* renamed from: e */
    public boolean f8809e = false;

    /* renamed from: g */
    List<EngineStateObserver> f8811g = new ArrayList();

    /* renamed from: w */
    private Vector<ScriptStateObserver> f8825w = new Vector<>();

    /* renamed from: h */
    public OnRecordScriptCallback f8812h = null;

    /* renamed from: i */
    OnScreenShotCallback f8813i = null;

    /* renamed from: j */
    OnScriptMessageCallback f8814j = null;

    /* renamed from: k */
    BasicScriptListener f8815k = null;

    /* renamed from: l */
    public OnScriptListener f8816l = null;

    /* renamed from: r */
    ArrayBlockingQueue<Ipc.IpcMessage> f8822r = new ArrayBlockingQueue<>(1024);

    /* renamed from: x */
    private Thread f8826x = new Thread("send_thread") { // from class: com.cyjh.mq.c.b.1
        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            super.run();
            while (IpcConnection.this.f8805a) {
                try {
                    IpcRaw bVar = new IpcRaw(IpcConnection.this.f8822r.take());
                    IpcConnection bVar2 = IpcConnection.this;
                    int length = bVar.mo20932a().length;
                    ByteBuffer wrap = ByteBuffer.wrap(new byte[length + 4]);
                    wrap.putInt(length);
                    wrap.put(bVar.mo20932a());
                    wrap.flip();
                    bVar2.m20546a(wrap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /* renamed from: s */
    OnRpcCallback f8823s = null;

    public IpcConnection(Context context, Socket socket) {
        super(socket);
        this.f8824v = null;
        this.f8807c = null;
        this.f8807c = context;
        this.f8824v = new MqmHandler(context, this);
    }

    public IpcConnection(Context context, LocalSocket localSocket) {
        super(localSocket);
        this.f8824v = null;
        this.f8807c = null;
        this.f8807c = context;
        this.f8824v = new MqmHandler(context, this);
    }

    /* renamed from: d */
    private boolean m20521d() {
        return this.f8806b;
    }

    /* renamed from: e */
    private int m20519e() {
        byte[] bArr = new byte[4];
        if (m20545a(bArr) < 0) {
            return -1;
        }
        return ByteBuffer.wrap(bArr).getInt();
    }

    /* renamed from: a */
    public final synchronized void m20531a(Ipc.IpcMessage ipcMessage) {
        this.f8822r.add(ipcMessage);
    }

    /* renamed from: a */
    private void m20538a(OnRpcCallback onRpcCallback) {
        this.f8823s = onRpcCallback;
    }

    /* renamed from: g */
    private OnRpcCallback m20515g() {
        return this.f8823s;
    }

    /* renamed from: b */
    public final void m20525b() {
        InputConnection currentInputConnection;
        this.f8806b = true;
        this.f8826x.start();
        while (true) {
            boolean z = false;
            boolean z2 = false;
            int i = 0;
            if (this.f8805a) {
                Ipc.IpcMessage f = m20517f();
                MqmHandler dVar = this.f8824v;
                if (f != null) {
                    new StringBuilder("handMessage msg.getCmd() -> ").append(f.getCmd());
                    OnRecordScriptCallback onRecordScriptCallback = dVar.f8858c.f8812h;
                    int cmd = f.getCmd();
                    switch (cmd) {
                        case 4:
                            dVar.f8858c.f8809e = true;
                            dVar.m20487a(C1375R.string.toast_on_start_script);
                            dVar.f8860e.sendEmptyMessage(2);
                            continue;
                        case 5:
                            dVar.f8860e.sendEmptyMessage(4);
                            continue;
                        case 6:
                            dVar.f8857b.m21085a();
                            AppAgent.m21068f();
                            int arg1 = f.getArg1(0);
                            dVar.f8858c.f8809e = false;
                            if (arg1 == 105 || arg1 == 0) {
                                dVar.m20487a(C1375R.string.toast_on_stop_script);
                            }
                            dVar.f8860e.obtainMessage(3, arg1, 0, f.getArg2(0)).sendToTarget();
                            continue;
                        default:
                            switch (cmd) {
                                case 27:
                                    OnUiElementCallback onUiElementCallback = dVar.f8858c.f8820p;
                                    if (onUiElementCallback != null) {
                                        onUiElementCallback.onUiElementback(f.getArg2(0));
                                        break;
                                    } else {
                                        continue;
                                    }
                                case 28:
                                    String valueOf = String.valueOf((f.getArg1(0) << 16) | f.getArg1(1));
                                    OnUiElementCallback onUiElementCallback2 = dVar.f8858c.f8820p;
                                    if (onUiElementCallback2 != null) {
                                        onUiElementCallback2.onScreenShotDone(valueOf, f.getFileData());
                                        break;
                                    } else {
                                        continue;
                                    }
                                default:
                                    switch (cmd) {
                                        case 32:
                                            OnScriptMessageCallback onScriptMessageCallback = dVar.f8858c.f8814j;
                                            if (onScriptMessageCallback != null) {
                                                onScriptMessageCallback.onTracePrint(f.getArg2(0));
                                                break;
                                            } else {
                                                continue;
                                            }
                                        case 33:
                                            dVar.f8860e.obtainMessage(1, f).sendToTarget();
                                            continue;
                                        case 34:
                                            dVar.f8857b.m21084a(f.getArg1(0));
                                            continue;
                                        case 35:
                                            AppAgent.m21075c(f.getArg2(0));
                                            continue;
                                        case 36:
                                            dVar.f8857b.m21083a(f.getArg2(0));
                                            continue;
                                        case 37:
                                            dVar.f8857b.m21080b(f.getArg1(0));
                                            continue;
                                        case 38:
                                            AppAgent aVar = dVar.f8857b;
                                            String arg2 = f.getArg2(0);
                                            InputMethodManager aVar2 = aVar.f8198b;
                                            if (!(aVar2.f9378a == null || (currentInputConnection = aVar2.f9378a.getCurrentInputConnection()) == null)) {
                                                currentInputConnection.commitText(arg2, arg2.length());
                                            }
                                            dVar.f8858c.m20531a(IpcRaw.m20933a(38));
                                            continue;
                                        case 39:
                                            dVar.f8857b.m21079b(f.getArg2(0));
                                            continue;
                                        case 40:
                                            dVar.f8860e.obtainMessage(16, (int) (f.getArg3(0) * 100.0f), f.getArg1(0), Integer.valueOf(f.getArg1(1))).sendToTarget();
                                            continue;
                                        case 41:
                                            dVar.f8857b.m21071e();
                                            continue;
                                        case 42:
                                            dVar.f8860e.sendEmptyMessage(8);
                                            continue;
                                        case 43:
                                            dVar.f8860e.sendEmptyMessage(9);
                                            continue;
                                        default:
                                            switch (cmd) {
                                                case 50:
                                                    dVar.f8858c.f8823s.onRpcReturn(f.getArg2(0));
                                                    continue;
                                                case 51:
                                                    AppAgent.m21068f();
                                                    continue;
                                                case 52:
                                                    ContactsUtils.m20655a(dVar.f8856a, f.getArg2(0), f.getArg2(1), f.getArg2(2));
                                                    continue;
                                                case 53:
                                                    ContactsUtils.m20656a(dVar.f8856a, f.getArg2(0));
                                                    continue;
                                                case 54:
                                                    ContactsUtils.m20657a(dVar.f8856a);
                                                    continue;
                                                case 55:
                                                    dVar.f8857b.m21066g();
                                                    continue;
                                                case 56:
                                                    dVar.f8857b.m21065h();
                                                    continue;
                                                case 57:
                                                    dVar.f8857b.m21076c(f.getArg1(0));
                                                    continue;
                                                case 58:
                                                    dVar.f8857b.m21073d(f.getArg1(0));
                                                    continue;
                                                case 59:
                                                    dVar.f8857b.m21070e(f.getArg1(0));
                                                    continue;
                                                default:
                                                    switch (cmd) {
                                                        case 64:
                                                            dVar.f8860e.obtainMessage(18, f.getFileData()).sendToTarget();
                                                            continue;
                                                        case 65:
                                                            continue;
                                                            continue;
                                                            continue;
                                                            continue;
                                                        case 66:
                                                            dVar.f8860e.obtainMessage(6, f).sendToTarget();
                                                            continue;
                                                        case 67:
                                                            dVar.f8860e.obtainMessage(7, f).sendToTarget();
                                                            continue;
                                                        default:
                                                            Ipc.IpcMessage ipcMessage = null;
                                                            switch (cmd) {
                                                                case 98:
                                                                    dVar.f8860e.obtainMessage(20, f.getArg2(0)).sendToTarget();
                                                                    continue;
                                                                case 99:
                                                                    dVar.f8860e.obtainMessage(22, f.getArg1(0), 0, f.getArg2(0)).sendToTarget();
                                                                    continue;
                                                                case 100:
                                                                    dVar.m20482a(f.getArg2(0), f.getArg2(1));
                                                                    continue;
                                                                case 101:
                                                                    String arg22 = f.getArg2(0);
                                                                    f.getArg2(1);
                                                                    File file = new File(arg22);
                                                                    ContentResolver contentResolver = dVar.f8856a.getContentResolver();
                                                                    ContentValues contentValues = new ContentValues();
                                                                    contentValues.put(C0675j.f373k, file.getName());
                                                                    contentValues.put("_display_name", file.getName());
                                                                    contentValues.put("mime_type", "video/3gp");
                                                                    contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                                                                    contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                                                                    contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                                                                    contentValues.put("_data", file.getAbsolutePath());
                                                                    contentValues.put("_size", Long.valueOf(file.length()));
                                                                    dVar.f8856a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
                                                                    continue;
                                                                case 102:
                                                                    String str = "_data like \"" + f.getArg2(0) + "%\"";
                                                                    dVar.f8856a.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str, null);
                                                                    dVar.f8856a.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str, null);
                                                                    continue;
                                                                case 103:
                                                                    if (f.getArg1(0) == 1) {
                                                                        z = true;
                                                                    }
                                                                    ((WifiManager) dVar.f8856a.getSystemService("wifi")).setWifiEnabled(z);
                                                                    continue;
                                                                case 104:
                                                                    if (f.getArg1(0) == 1) {
                                                                        i = 1;
                                                                    }
                                                                    Settings.System.putInt(dVar.f8857b.f8197a.getContentResolver(), "accelerometer_rotation", i ^ 1);
                                                                    continue;
                                                                case 105:
                                                                    if (f.getArg1(0) == 1) {
                                                                        z2 = true;
                                                                    }
                                                                    AppAgent aVar3 = dVar.f8857b;
                                                                    ContentResolver contentResolver2 = aVar3.f8197a.getContentResolver();
                                                                    int i2 = z2 ? 1 : 0;
                                                                    int i3 = z2 ? 1 : 0;
                                                                    int i4 = z2 ? 1 : 0;
                                                                    Settings.System.putInt(contentResolver2, "airplane_mode_on", i2);
                                                                    Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
                                                                    intent.putExtra(ShippingInfoWidget.f12562e, z2);
                                                                    aVar3.f8197a.sendBroadcast(intent);
                                                                    continue;
                                                                case 106:
                                                                    dVar.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(106).addArg2(dVar.f8857b.m21064i()).build());
                                                                    continue;
                                                                case 107:
                                                                    dVar.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(107).addArg2(dVar.f8857b.m21072d(f.getArg2(0))).build());
                                                                    continue;
                                                                case 108:
                                                                    dVar.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(108).addArg2(dVar.f8857b.m21063j()).build());
                                                                    continue;
                                                                case 109:
                                                                    String a = dVar.m20484a(f.getArg2Bytes(0), f.getArg1(0), f.getArg1(1), f.getArg1(2));
                                                                    Log.e("GET_OCR_TEXT", "strOrcText:" + a);
                                                                    dVar.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(109).addArg2(a).build());
                                                                    continue;
                                                                case 110:
                                                                    dVar.f8860e.obtainMessage(23, f).sendToTarget();
                                                                    continue;
                                                                case 111:
                                                                    f.getArg1(0);
                                                                    dVar.f8857b.m21062k();
                                                                    continue;
                                                                case 112:
                                                                    MqmHandler.m20481a(f.getArg2(0), f.getArg2(1), f.getArg1(0));
                                                                    continue;
                                                                default:
                                                                    switch (cmd) {
                                                                        case 14:
                                                                            ClientInfo.C1352a aVar4 = new ClientInfo.C1352a();
                                                                            aVar4.f8795a = f.getArg1(0);
                                                                            aVar4.f8796b = f.getArg1(1);
                                                                            aVar4.f8797c = f.getArg1(2) != 0;
                                                                            dVar.f8858c.f8810f = new ClientInfo(aVar4, (byte) 0);
                                                                            new C13542().start();
                                                                            continue;
                                                                        case 19:
                                                                            if (onRecordScriptCallback != null) {
                                                                                onRecordScriptCallback.onOpenRecord();
                                                                                break;
                                                                            } else {
                                                                                continue;
                                                                            }
                                                                        case 21:
                                                                            int arg12 = f.getArg1(0);
                                                                            if (onRecordScriptCallback != null) {
                                                                                onRecordScriptCallback.onStartRecord(arg12);
                                                                                break;
                                                                            } else {
                                                                                continue;
                                                                            }
                                                                        case 23:
                                                                            String arg23 = f.getArg2(0);
                                                                            if (onRecordScriptCallback != null) {
                                                                                onRecordScriptCallback.onFinishRecord(arg23);
                                                                                break;
                                                                            } else {
                                                                                continue;
                                                                            }
                                                                        case 25:
                                                                            int arg13 = f.getArg1(0);
                                                                            int arg14 = f.getArg1(1);
                                                                            int arg15 = f.getArg1(2);
                                                                            String valueOf2 = String.valueOf((arg13 << 16) | arg14);
                                                                            OnScreenShotCallback onScreenShotCallback = dVar.f8858c.f8813i;
                                                                            if (arg15 == 0) {
                                                                                dVar.f8858c.f8808d = false;
                                                                            }
                                                                            if (onScreenShotCallback != null) {
                                                                                onScreenShotCallback.onScreenShotDone(valueOf2, f.getFileData());
                                                                                break;
                                                                            } else {
                                                                                continue;
                                                                            }
                                                                        case 48:
                                                                            Object invoke = Invocator.invoke(f.getPkgName(), f.getClassName(), f.getMethodName(), f.getTypesList(), f.getParamsList(), 0);
                                                                            new StringBuilder("retObj = ").append(invoke);
                                                                            if (f.getIsSyncCall()) {
                                                                                Ipc.FundType type = f.getRetValue().getType();
                                                                                Ipc.ReturnValue.Builder type2 = Ipc.ReturnValue.newBuilder().setType(type);
                                                                                Ipc.IpcMessage.Builder waitId = Ipc.IpcMessage.newBuilder().setCmd(f.getCmd()).setIsSyncCall(true).setWaitId(f.getWaitId());
                                                                                try {
                                                                                    switch (type) {
                                                                                        case BOOLEAN:
                                                                                            type2.setValBoolean(((Boolean) invoke).booleanValue());
                                                                                            break;
                                                                                        case INT:
                                                                                            type2.setValInt(((Integer) invoke).intValue());
                                                                                            break;
                                                                                        case LONG:
                                                                                            type2.setValLong(((Long) invoke).longValue());
                                                                                            break;
                                                                                        case FLOAT:
                                                                                            type2.setValFloat(((Float) invoke).floatValue());
                                                                                            break;
                                                                                        case DOUBLE:
                                                                                            type2.setValDouble(((Double) invoke).doubleValue());
                                                                                            break;
                                                                                        case STRING:
                                                                                            type2.setValString(invoke == null ? "" : (String) invoke);
                                                                                            break;
                                                                                    }
                                                                                } catch (Throwable th) {
                                                                                    th.printStackTrace();
                                                                                }
                                                                                if (type != Ipc.FundType.VOID) {
                                                                                    waitId.setRetValue(type2.build());
                                                                                }
                                                                                try {
                                                                                    ipcMessage = waitId.build();
                                                                                } catch (UninitializedMessageException e) {
                                                                                    e.printStackTrace();
                                                                                }
                                                                                if (ipcMessage == null) {
                                                                                    break;
                                                                                } else {
                                                                                    dVar.f8858c.m20531a(ipcMessage);
                                                                                    break;
                                                                                }
                                                                            } else {
                                                                                continue;
                                                                            }
                                                                        case 81:
                                                                            dVar.f8860e.obtainMessage(21, f.getArg1(0), 0, f.getArg2(0)).sendToTarget();
                                                                            continue;
                                                                        case 96:
                                                                            dVar.f8860e.obtainMessage(19, f.getArg1(0), 0, f.getArg2(0)).sendToTarget();
                                                                            continue;
                                                                        case IpcCommand.f8356aC /* 129 */:
                                                                            dVar.f8860e.obtainMessage(17, f.getArg1(0), 0).sendToTarget();
                                                                            continue;
                                                                        case 257:
                                                                            OnScriptMessageCallback onScriptMessageCallback2 = dVar.f8858c.f8814j;
                                                                            if (onScriptMessageCallback2 != null) {
                                                                                onScriptMessageCallback2.onDebugMessage(f.getFileData());
                                                                                break;
                                                                            } else {
                                                                                continue;
                                                                            }
                                                                        case 65535:
                                                                            dVar.f8858c.m20523c();
                                                                            continue;
                                                                        default:
                                                                            continue;
                                                                            continue;
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
                }
            } else {
                this.f8806b = false;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cyjh.p045mq.p048c.BaseSocketConnection
    /* renamed from: a */
    public final void mo20543a() {
        super.mo20543a();
        this.f8805a = false;
        m20531a(IpcRaw.m20933a(3));
    }

    /* renamed from: h */
    private ClientInfo m20514h() {
        return this.f8810f;
    }

    /* renamed from: a */
    private void m20530a(ClientInfo aVar) {
        this.f8810f = aVar;
    }

    /* renamed from: a */
    private void m20526a(boolean z) {
        this.f8809e = z;
    }

    /* renamed from: i */
    private boolean m20513i() {
        return this.f8809e;
    }

    /* renamed from: j */
    private void m20512j() {
        this.f8808d = false;
    }

    /* renamed from: k */
    private boolean m20511k() {
        return this.f8808d;
    }

    /* renamed from: a */
    private void m20532a(ScriptStateObserver scriptStateObserver) {
        this.f8825w.add(scriptStateObserver);
    }

    /* renamed from: l */
    private Vector<ScriptStateObserver> m20510l() {
        return this.f8825w;
    }

    /* renamed from: a */
    private void m20542a(BasicScriptListener basicScriptListener) {
        this.f8815k = basicScriptListener;
    }

    /* renamed from: m */
    private BasicScriptListener m20509m() {
        return this.f8815k;
    }

    /* renamed from: a */
    private void m20536a(OnScriptListener onScriptListener) {
        this.f8816l = onScriptListener;
    }

    /* renamed from: n */
    private OnScriptListener m20508n() {
        return this.f8816l;
    }

    /* renamed from: a */
    private void m20540a(OnRecordScriptCallback onRecordScriptCallback) {
        this.f8812h = onRecordScriptCallback;
    }

    /* renamed from: o */
    private OnRecordScriptCallback m20507o() {
        return this.f8812h;
    }

    /* renamed from: a */
    private void m20537a(OnScreenShotCallback onScreenShotCallback) {
        this.f8813i = onScreenShotCallback;
    }

    /* renamed from: p */
    private OnScreenShotCallback m20506p() {
        return this.f8813i;
    }

    /* renamed from: a */
    private void m20535a(OnScriptMessageCallback onScriptMessageCallback) {
        this.f8814j = onScriptMessageCallback;
    }

    /* renamed from: q */
    private OnScriptMessageCallback m20505q() {
        return this.f8814j;
    }

    /* renamed from: a */
    private void m20527a(List<EngineStateObserver> list) {
        this.f8811g = list;
    }

    /* renamed from: a */
    private void m20539a(OnRequestCallback onRequestCallback) {
        this.f8817m = onRequestCallback;
    }

    /* renamed from: r */
    private OnRequestCallback m20504r() {
        return this.f8817m;
    }

    /* renamed from: a */
    private void m20541a(OnMqScriptCallback onMqScriptCallback) {
        this.f8818n = onMqScriptCallback;
    }

    /* renamed from: s */
    private OnMqScriptCallback m20503s() {
        return this.f8818n;
    }

    /* renamed from: t */
    private OnElfCallback m20502t() {
        return this.f8819o;
    }

    /* renamed from: a */
    private void m20528a(OnElfCallback onElfCallback) {
        this.f8819o = onElfCallback;
    }

    /* renamed from: u */
    private OnUiElementCallback m20501u() {
        return this.f8820p;
    }

    /* renamed from: a */
    private void m20533a(OnUiElementCallback onUiElementCallback) {
        this.f8820p = onUiElementCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: IpcConnection.java */
    /* renamed from: com.cyjh.mq.c.b$2 */
    /* loaded from: classes.dex */
    public final class C13542 extends Thread {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C13542() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            for (EngineStateObserver engineStateObserver : IpcConnection.this.f8811g) {
                engineStateObserver.onConnected(IpcConnection.this);
            }
            if (MyApplication.f8791j != null) {
                MyApplication.f8791j.onEngineStart(IpcConnection.this.f8810f.f8792a);
            }
        }
    }

    /* renamed from: v */
    private void m20500v() {
        new C13542().start();
    }

    /* renamed from: c */
    public final void m20523c() {
        for (EngineStateObserver engineStateObserver : this.f8811g) {
            engineStateObserver.onExit();
        }
    }

    /* renamed from: w */
    private void m20499w() {
        OnScreenShotCallback onScreenShotCallback;
        CrashRunnerState bVar = new CrashRunnerState();
        bVar.f8216a = MqRunner.getInstance().isScriptStarted();
        if (MqRunner.getInstance().isScriptStarted()) {
            this.f8824v.f8860e.sendEmptyMessage(10);
            if (this.f8815k != null) {
                Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP root进程异常终止");
                this.f8815k.onStopScript(1002, "root进程异常终止");
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.cyjh.mq.c.b.3
                @Override // java.lang.Runnable
                public final void run() {
                    if (IpcConnection.this.f8816l != null) {
                        Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP root进程异常终止 2");
                        IpcConnection.this.f8816l.onStopScript(1002, "root进程异常终止");
                    }
                    ExToast.makeText(IpcConnection.this.f8807c, C1375R.string.toast_engine_crash_then_restart, 2000).show();
                }
            });
        }
        if (this.f8808d && (onScreenShotCallback = this.f8813i) != null) {
            this.f8808d = false;
            onScreenShotCallback.onScreenShotFailed(1002);
        }
        for (EngineStateObserver engineStateObserver : this.f8811g) {
            engineStateObserver.onCrash(bVar);
        }
    }

    /* renamed from: x */
    private OnSpecialMqCmdCallback m20498x() {
        return this.f8821q;
    }

    /* renamed from: a */
    private void m20534a(OnSpecialMqCmdCallback onSpecialMqCmdCallback) {
        this.f8821q = onSpecialMqCmdCallback;
    }

    /* renamed from: f */
    private Ipc.IpcMessage m20517f() {
        byte[] bArr = new byte[4];
        int i = m20545a(bArr) < 0 ? -1 : ByteBuffer.wrap(bArr).getInt();
        if (i < 0) {
            mo20543a();
            m20499w();
            return null;
        }
        byte[] bArr2 = new byte[i];
        if (m20545a(bArr2) < 0) {
            mo20543a();
            m20499w();
            return null;
        }
        try {
            return Ipc.IpcMessage.parseFrom(bArr2);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return null;
        }
    }
}
