package com.cyjh.mq.p048c;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;
import com.alipay.sdk.widget.C0675j;
import com.cyjh.mobileanjian.ipc.AppAgent;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScreenShotCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptMessageCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnUiElementCallback;
import com.cyjh.mobileanjian.ipc.p044ui.FloatAlertDialog;
import com.cyjh.mobileanjian.ipc.p044ui.FloatInputDialog;
import com.cyjh.mobileanjian.ipc.p044ui.UiManager;
import com.cyjh.mobileanjian.ipc.p044ui.UiShowFloat;
import com.cyjh.mobileanjian.ipc.rpc.Invocator;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.cyjh.mobileanjian.ipc.share.proto.IpcRaw;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.ContactsUtils;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mobileanjian.ipc.view.ShowTapPic;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.mq.p046a.MyApplication;
import com.cyjh.mq.p047b.ClientInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.ime.input.InputKb;
import com.ime.input.InputMethodManager;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;
import org.apache.commons.mail.EmailConstants;
import p110z1.BarcodeFormat;
import p110z1.BitMatrix;
import p110z1.EncodeHintType;
import p110z1.ErrorCorrectionLevel;
import p110z1.QRCodeWriter;
import p110z1.WriterException;

/* renamed from: com.cyjh.mq.c.d */
/* loaded from: classes.dex */
public final class MqmHandler {

    /* renamed from: h */
    private static final int f8838h = 1;

    /* renamed from: i */
    private static final int f8839i = 2;

    /* renamed from: j */
    private static final int f8840j = 3;

    /* renamed from: k */
    private static final int f8841k = 4;

    /* renamed from: l */
    private static final int f8842l = 5;

    /* renamed from: m */
    private static final int f8843m = 10;

    /* renamed from: n */
    private static final int f8844n = 6;

    /* renamed from: o */
    private static final int f8845o = 7;

    /* renamed from: p */
    private static final int f8846p = 8;

    /* renamed from: q */
    private static final int f8847q = 9;

    /* renamed from: r */
    private static final int f8848r = 16;

    /* renamed from: s */
    private static final int f8849s = 17;

    /* renamed from: t */
    private static final int f8850t = 18;

    /* renamed from: u */
    private static final int f8851u = 19;

    /* renamed from: v */
    private static final int f8852v = 20;

    /* renamed from: w */
    private static final int f8853w = 21;

    /* renamed from: x */
    private static final int f8854x = 22;

    /* renamed from: y */
    private static final int f8855y = 23;

    /* renamed from: a */
    Context f8856a;

    /* renamed from: b */
    AppAgent f8857b;

    /* renamed from: c */
    IpcConnection f8858c;

    /* renamed from: d */
    UiManager f8859d;

    /* renamed from: f */
    private TessBaseAPI f8861f = null;

    /* renamed from: g */
    private boolean f8862g = false;

    /* renamed from: e */
    Handler f8860e = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mq.c.d.1

        /* renamed from: b */
        private ExToast f8864b = null;

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            switch (i) {
                case 1:
                    if (this.f8864b == null) {
                        this.f8864b = new ExToast(MqmHandler.this.f8856a);
                    }
                    Ipc.IpcMessage ipcMessage = (Ipc.IpcMessage) message.obj;
                    this.f8864b.show(ipcMessage.getArg2(0), ipcMessage.getArg1(0), ipcMessage.getArg1(1), ipcMessage.getArg1(2));
                    return;
                case 2:
                    if (MqmHandler.this.f8858c.f8815k != null) {
                        MqmHandler.this.f8858c.f8815k.onStartScript();
                    }
                    if (MqmHandler.this.f8858c.f8816l != null) {
                        MqmHandler.this.f8858c.f8816l.onStartScript();
                        return;
                    }
                    return;
                case 3:
                    Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP SCRIPT_STOPPED");
                    UiManager hVar = MqmHandler.this.f8859d;
                    hVar.f8506f.clear();
                    hVar.f8507g.clear();
                    hVar.f8508h.clear();
                    hVar.m20859a();
                    hVar.f8502b = 0;
                    hVar.f8509i = 1;
                    for (int i2 = 0; i2 < 32; i2++) {
                        hVar.f8501a[i2] = null;
                        if (hVar.f8503c[i2] != null) {
                            hVar.f8503c[i2].clear();
                        }
                    }
                    hVar.f8504d = new UiShowFloat[32];
                    hVar.f8505e = 0;
                    ExToast exToast = this.f8864b;
                    if (exToast != null) {
                        exToast.hide();
                    }
                    if (MqmHandler.this.f8858c.f8815k != null) {
                        Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP mConnection.getBasicScriptListener()");
                        MqmHandler.this.f8858c.f8815k.onStopScript(message.arg1, (String) message.obj);
                    }
                    if (MqmHandler.this.f8858c.f8816l != null) {
                        Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP mConnection.getOnScriptListener() != null");
                        MqmHandler.this.f8858c.f8816l.onStopScript(message.arg1, (String) message.obj);
                        return;
                    }
                    return;
                case 4:
                    if (MqmHandler.this.f8858c.f8815k != null) {
                        MqmHandler.this.f8858c.f8815k.onScriptIsRunning();
                    }
                    if (MqmHandler.this.f8858c.f8816l != null) {
                        MqmHandler.this.f8858c.f8816l.onScriptIsRunning();
                        return;
                    }
                    return;
                case 5:
                    if (message.obj == null) {
                        Toast.makeText(MqmHandler.this.f8856a, message.arg1, 0).show();
                        return;
                    } else {
                        Toast.makeText(MqmHandler.this.f8856a, (CharSequence) message.obj, 0).show();
                        return;
                    }
                case 6:
                    Ipc.IpcMessage ipcMessage2 = (Ipc.IpcMessage) message.obj;
                    String arg2 = ipcMessage2.getArg2(0);
                    int arg1 = ipcMessage2.getArg1(0);
                    ipcMessage2.getArg1(0);
                    ipcMessage2.getArg1(0);
                    new FloatAlertDialog(MqmHandler.this.f8856a, arg2, arg1, new FloatAlertDialog.AbstractC1300a() { // from class: com.cyjh.mq.c.d.1.1
                        @Override // com.cyjh.mobileanjian.ipc.p044ui.FloatAlertDialog.AbstractC1300a
                        /* renamed from: a */
                        public final void mo20463a(int i3) {
                            MqmHandler.this.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(66).addArg1(i3).build());
                        }
                    }).m20905a();
                    return;
                case 7:
                    Ipc.IpcMessage ipcMessage3 = (Ipc.IpcMessage) message.obj;
                    String arg22 = ipcMessage3.getArg2(0);
                    ipcMessage3.getArg1(0);
                    ipcMessage3.getArg1(0);
                    new FloatInputDialog(MqmHandler.this.f8856a, arg22, new FloatInputDialog.AbstractC1302a() { // from class: com.cyjh.mq.c.d.1.2
                        @Override // com.cyjh.mobileanjian.ipc.p044ui.FloatInputDialog.AbstractC1302a
                        /* renamed from: a */
                        public final void mo20462a(String str) {
                            MqmHandler.this.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(67).addArg2(str).build());
                        }
                    }).m20900a();
                    return;
                case 8:
                    if (MqmHandler.this.f8858c.f8815k != null) {
                        MqmHandler.this.f8858c.f8815k.onPause();
                    }
                    if (MqmHandler.this.f8858c.f8816l != null) {
                        MqmHandler.this.f8858c.f8816l.onPause();
                        return;
                    }
                    return;
                case 9:
                    if (MqmHandler.this.f8858c.f8815k != null) {
                        MqmHandler.this.f8858c.f8815k.onResume();
                    }
                    if (MqmHandler.this.f8858c.f8816l != null) {
                        MqmHandler.this.f8858c.f8816l.onResume();
                        return;
                    }
                    return;
                case 10:
                    ExToast exToast2 = this.f8864b;
                    if (exToast2 != null) {
                        exToast2.hide();
                        return;
                    }
                    return;
                default:
                    switch (i) {
                        case 16:
                            if (MqmHandler.this.f8858c.f8816l != null) {
                                MqmHandler.this.f8858c.f8816l.onUpdateControlBarPos(message.arg1 / 100.0f, message.arg2, ((Integer) message.obj).intValue());
                                return;
                            }
                            return;
                        case 17:
                            OnKeyEventListener onKeyEventListener = MyApplication.f8789h;
                            if (onKeyEventListener != null) {
                                onKeyEventListener.onKeyEvent(message.arg1);
                                return;
                            }
                            return;
                        case 18:
                            try {
                                UiMessage.CommandToUi parseFrom = UiMessage.CommandToUi.parseFrom((ByteString) message.obj);
                                if (parseFrom != null) {
                                    MqmHandler.this.f8859d.m20857a(parseFrom);
                                    return;
                                }
                                return;
                            } catch (InvalidProtocolBufferException e) {
                                e.printStackTrace();
                                return;
                            }
                        case 19:
                            if (MqmHandler.this.f8858c.f8817m != null) {
                                MqmHandler.this.f8858c.f8817m.onCallback(message.arg1, (String) message.obj);
                                return;
                            }
                            return;
                        case 20:
                            if (MqmHandler.this.f8858c.f8818n != null) {
                                MqmHandler.this.f8858c.f8818n.callback((String) message.obj);
                                return;
                            }
                            return;
                        case 21:
                            if (MqmHandler.this.f8858c.f8819o != null) {
                                MqmHandler.this.f8858c.f8819o.callback(message.arg1, (String) message.obj);
                                return;
                            }
                            return;
                        case 22:
                            if (MqmHandler.this.f8858c.f8821q != null) {
                                MqmHandler.this.f8858c.f8821q.doSpecialFuction(message.arg1, (String) message.obj);
                                return;
                            }
                            return;
                        case 23:
                            ShowTapPic bVar = new ShowTapPic(MqmHandler.this.f8856a);
                            Ipc.IpcMessage ipcMessage4 = (Ipc.IpcMessage) message.obj;
                            bVar.m20586a(ipcMessage4.getArg1(0), ipcMessage4.getArg1(1), ipcMessage4.getArg2(0));
                            return;
                        default:
                            return;
                    }
            }
        }
    };

    /* renamed from: a */
    private boolean m20488a() {
        File file = new File(this.f8856a.getFilesDir().getAbsolutePath() + "/tessdata/", "eng.traineddata");
        File file2 = new File(this.f8856a.getFilesDir().getAbsolutePath() + "/tessdata/", "chi_sim.traineddata");
        if (!file.exists() || !file2.exists()) {
            return false;
        }
        this.f8861f = new TessBaseAPI();
        boolean a = this.f8861f.m20151a(this.f8856a.getFilesDir().getAbsolutePath(), "chi_sim+eng");
        Log.d("MqmHandler", "initTessBass: init ret=" + a);
        return a;
    }

    public MqmHandler(Context context, IpcConnection bVar) {
        this.f8858c = null;
        this.f8856a = context;
        this.f8859d = new UiManager(this.f8856a);
        this.f8857b = new AppAgent(this.f8856a);
        this.f8858c = bVar;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f8856a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.densityDpi;
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        String d = this.f8857b.m21074d();
        String c = this.f8857b.m21077c();
        String b = this.f8857b.m21081b();
        String str = this.f8856a.getPackageName() + "/" + InputKb.class.getName();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String str2 = UUID.randomUUID().toString() + ".tmp";
        d = d == null ? "UNKNOW" : d;
        c = c == null ? "UNKNOW" : c;
        File file = new File(absolutePath, str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Ipc.IpcMessage.Builder cmd = Ipc.IpcMessage.newBuilder().setCmd(14);
        cmd.addArg1(i).addArg1(i2).addArg1(i3).addArg2(d).addArg2(c).addArg2(b).addArg2(str).addArg2(str2).addArg2(absolutePath).addArg2(this.f8856a.getPackageName());
        this.f8858c.m20531a(cmd.build());
    }

    /* renamed from: a */
    private void m20486a(Ipc.IpcMessage ipcMessage) {
        InputConnection currentInputConnection;
        if (ipcMessage != null) {
            new StringBuilder("handMessage msg.getCmd() -> ").append(ipcMessage.getCmd());
            OnRecordScriptCallback onRecordScriptCallback = this.f8858c.f8812h;
            int cmd = ipcMessage.getCmd();
            boolean z = true;
            boolean z2 = true;
            boolean z3 = true;
            int i = 0;
            switch (cmd) {
                case 4:
                    this.f8858c.f8809e = true;
                    m20487a(C1375R.string.toast_on_start_script);
                    this.f8860e.sendEmptyMessage(2);
                    return;
                case 5:
                    this.f8860e.sendEmptyMessage(4);
                    return;
                case 6:
                    this.f8857b.m21085a();
                    AppAgent.m21068f();
                    int arg1 = ipcMessage.getArg1(0);
                    this.f8858c.f8809e = false;
                    if (arg1 == 105 || arg1 == 0) {
                        m20487a(C1375R.string.toast_on_stop_script);
                    }
                    this.f8860e.obtainMessage(3, arg1, 0, ipcMessage.getArg2(0)).sendToTarget();
                    return;
                default:
                    switch (cmd) {
                        case 27:
                            OnUiElementCallback onUiElementCallback = this.f8858c.f8820p;
                            if (onUiElementCallback != null) {
                                onUiElementCallback.onUiElementback(ipcMessage.getArg2(0));
                                return;
                            }
                            return;
                        case 28:
                            String valueOf = String.valueOf((ipcMessage.getArg1(0) << 16) | ipcMessage.getArg1(1));
                            OnUiElementCallback onUiElementCallback2 = this.f8858c.f8820p;
                            if (onUiElementCallback2 != null) {
                                onUiElementCallback2.onScreenShotDone(valueOf, ipcMessage.getFileData());
                                return;
                            }
                            return;
                        default:
                            switch (cmd) {
                                case 32:
                                    OnScriptMessageCallback onScriptMessageCallback = this.f8858c.f8814j;
                                    if (onScriptMessageCallback != null) {
                                        onScriptMessageCallback.onTracePrint(ipcMessage.getArg2(0));
                                        return;
                                    }
                                    return;
                                case 33:
                                    this.f8860e.obtainMessage(1, ipcMessage).sendToTarget();
                                    return;
                                case 34:
                                    this.f8857b.m21084a(ipcMessage.getArg1(0));
                                    return;
                                case 35:
                                    AppAgent.m21075c(ipcMessage.getArg2(0));
                                    return;
                                case 36:
                                    this.f8857b.m21083a(ipcMessage.getArg2(0));
                                    return;
                                case 37:
                                    this.f8857b.m21080b(ipcMessage.getArg1(0));
                                    return;
                                case 38:
                                    AppAgent aVar = this.f8857b;
                                    String arg2 = ipcMessage.getArg2(0);
                                    InputMethodManager aVar2 = aVar.f8198b;
                                    if (!(aVar2.f9378a == null || (currentInputConnection = aVar2.f9378a.getCurrentInputConnection()) == null)) {
                                        currentInputConnection.commitText(arg2, arg2.length());
                                    }
                                    this.f8858c.m20531a(IpcRaw.m20933a(38));
                                    return;
                                case 39:
                                    this.f8857b.m21079b(ipcMessage.getArg2(0));
                                    return;
                                case 40:
                                    this.f8860e.obtainMessage(16, (int) (ipcMessage.getArg3(0) * 100.0f), ipcMessage.getArg1(0), Integer.valueOf(ipcMessage.getArg1(1))).sendToTarget();
                                    return;
                                case 41:
                                    this.f8857b.m21071e();
                                    return;
                                case 42:
                                    this.f8860e.sendEmptyMessage(8);
                                    return;
                                case 43:
                                    this.f8860e.sendEmptyMessage(9);
                                    return;
                                default:
                                    switch (cmd) {
                                        case 50:
                                            this.f8858c.f8823s.onRpcReturn(ipcMessage.getArg2(0));
                                            return;
                                        case 51:
                                            AppAgent.m21068f();
                                            return;
                                        case 52:
                                            ContactsUtils.m20655a(this.f8856a, ipcMessage.getArg2(0), ipcMessage.getArg2(1), ipcMessage.getArg2(2));
                                            return;
                                        case 53:
                                            ContactsUtils.m20656a(this.f8856a, ipcMessage.getArg2(0));
                                            return;
                                        case 54:
                                            ContactsUtils.m20657a(this.f8856a);
                                            return;
                                        case 55:
                                            this.f8857b.m21066g();
                                            return;
                                        case 56:
                                            this.f8857b.m21065h();
                                            return;
                                        case 57:
                                            this.f8857b.m21076c(ipcMessage.getArg1(0));
                                            return;
                                        case 58:
                                            this.f8857b.m21073d(ipcMessage.getArg1(0));
                                            return;
                                        case 59:
                                            this.f8857b.m21070e(ipcMessage.getArg1(0));
                                            return;
                                        default:
                                            switch (cmd) {
                                                case 64:
                                                    this.f8860e.obtainMessage(18, ipcMessage.getFileData()).sendToTarget();
                                                    return;
                                                case 65:
                                                    return;
                                                case 66:
                                                    this.f8860e.obtainMessage(6, ipcMessage).sendToTarget();
                                                    return;
                                                case 67:
                                                    this.f8860e.obtainMessage(7, ipcMessage).sendToTarget();
                                                    return;
                                                default:
                                                    Ipc.IpcMessage ipcMessage2 = null;
                                                    switch (cmd) {
                                                        case 98:
                                                            this.f8860e.obtainMessage(20, ipcMessage.getArg2(0)).sendToTarget();
                                                            return;
                                                        case 99:
                                                            this.f8860e.obtainMessage(22, ipcMessage.getArg1(0), 0, ipcMessage.getArg2(0)).sendToTarget();
                                                            return;
                                                        case 100:
                                                            m20482a(ipcMessage.getArg2(0), ipcMessage.getArg2(1));
                                                            return;
                                                        case 101:
                                                            String arg22 = ipcMessage.getArg2(0);
                                                            ipcMessage.getArg2(1);
                                                            File file = new File(arg22);
                                                            ContentResolver contentResolver = this.f8856a.getContentResolver();
                                                            ContentValues contentValues = new ContentValues();
                                                            contentValues.put(C0675j.f373k, file.getName());
                                                            contentValues.put("_display_name", file.getName());
                                                            contentValues.put("mime_type", "video/3gp");
                                                            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                                                            contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                                                            contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                                                            contentValues.put("_data", file.getAbsolutePath());
                                                            contentValues.put("_size", Long.valueOf(file.length()));
                                                            this.f8856a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
                                                            return;
                                                        case 102:
                                                            String str = "_data like \"" + ipcMessage.getArg2(0) + "%\"";
                                                            this.f8856a.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str, null);
                                                            this.f8856a.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str, null);
                                                            return;
                                                        case 103:
                                                            if (ipcMessage.getArg1(0) != 1) {
                                                                z = false;
                                                            }
                                                            ((WifiManager) this.f8856a.getSystemService("wifi")).setWifiEnabled(z);
                                                            return;
                                                        case 104:
                                                            if (ipcMessage.getArg1(0) == 1) {
                                                                i = 1;
                                                            }
                                                            Settings.System.putInt(this.f8857b.f8197a.getContentResolver(), "accelerometer_rotation", i ^ 1);
                                                            return;
                                                        case 105:
                                                            if (ipcMessage.getArg1(0) != 1) {
                                                                z3 = false;
                                                            }
                                                            AppAgent aVar3 = this.f8857b;
                                                            ContentResolver contentResolver2 = aVar3.f8197a.getContentResolver();
                                                            int i2 = z3 ? 1 : 0;
                                                            int i3 = z3 ? 1 : 0;
                                                            int i4 = z3 ? 1 : 0;
                                                            Settings.System.putInt(contentResolver2, "airplane_mode_on", i2);
                                                            Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
                                                            intent.putExtra(ShippingInfoWidget.f12562e, z3);
                                                            aVar3.f8197a.sendBroadcast(intent);
                                                            return;
                                                        case 106:
                                                            this.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(106).addArg2(this.f8857b.m21064i()).build());
                                                            return;
                                                        case 107:
                                                            this.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(107).addArg2(this.f8857b.m21072d(ipcMessage.getArg2(0))).build());
                                                            return;
                                                        case 108:
                                                            this.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(108).addArg2(this.f8857b.m21063j()).build());
                                                            return;
                                                        case 109:
                                                            String a = m20484a(ipcMessage.getArg2Bytes(0), ipcMessage.getArg1(0), ipcMessage.getArg1(1), ipcMessage.getArg1(2));
                                                            Log.e("GET_OCR_TEXT", "strOrcText:" + a);
                                                            this.f8858c.m20531a(Ipc.IpcMessage.newBuilder().setCmd(109).addArg2(a).build());
                                                            return;
                                                        case 110:
                                                            this.f8860e.obtainMessage(23, ipcMessage).sendToTarget();
                                                            return;
                                                        case 111:
                                                            ipcMessage.getArg1(0);
                                                            this.f8857b.m21062k();
                                                            return;
                                                        case 112:
                                                            m20481a(ipcMessage.getArg2(0), ipcMessage.getArg2(1), ipcMessage.getArg1(0));
                                                            return;
                                                        default:
                                                            switch (cmd) {
                                                                case 14:
                                                                    ClientInfo.C1352a aVar4 = new ClientInfo.C1352a();
                                                                    aVar4.f8795a = ipcMessage.getArg1(0);
                                                                    aVar4.f8796b = ipcMessage.getArg1(1);
                                                                    if (ipcMessage.getArg1(2) == 0) {
                                                                        z2 = false;
                                                                    }
                                                                    aVar4.f8797c = z2;
                                                                    ClientInfo aVar5 = new ClientInfo(aVar4, (byte) 0);
                                                                    IpcConnection bVar = this.f8858c;
                                                                    bVar.f8810f = aVar5;
                                                                    new IpcConnection.C13542().start();
                                                                    return;
                                                                case 19:
                                                                    if (onRecordScriptCallback != null) {
                                                                        onRecordScriptCallback.onOpenRecord();
                                                                        return;
                                                                    }
                                                                    return;
                                                                case 21:
                                                                    int arg12 = ipcMessage.getArg1(0);
                                                                    if (onRecordScriptCallback != null) {
                                                                        onRecordScriptCallback.onStartRecord(arg12);
                                                                        return;
                                                                    }
                                                                    return;
                                                                case 23:
                                                                    String arg23 = ipcMessage.getArg2(0);
                                                                    if (onRecordScriptCallback != null) {
                                                                        onRecordScriptCallback.onFinishRecord(arg23);
                                                                        return;
                                                                    }
                                                                    return;
                                                                case 25:
                                                                    int arg13 = ipcMessage.getArg1(0);
                                                                    int arg14 = ipcMessage.getArg1(1);
                                                                    int arg15 = ipcMessage.getArg1(2);
                                                                    String valueOf2 = String.valueOf((arg13 << 16) | arg14);
                                                                    OnScreenShotCallback onScreenShotCallback = this.f8858c.f8813i;
                                                                    if (arg15 == 0) {
                                                                        this.f8858c.f8808d = false;
                                                                    }
                                                                    if (onScreenShotCallback != null) {
                                                                        onScreenShotCallback.onScreenShotDone(valueOf2, ipcMessage.getFileData());
                                                                        return;
                                                                    }
                                                                    return;
                                                                case 48:
                                                                    Object invoke = Invocator.invoke(ipcMessage.getPkgName(), ipcMessage.getClassName(), ipcMessage.getMethodName(), ipcMessage.getTypesList(), ipcMessage.getParamsList(), 0);
                                                                    new StringBuilder("retObj = ").append(invoke);
                                                                    if (ipcMessage.getIsSyncCall()) {
                                                                        Ipc.FundType type = ipcMessage.getRetValue().getType();
                                                                        Ipc.ReturnValue.Builder type2 = Ipc.ReturnValue.newBuilder().setType(type);
                                                                        Ipc.IpcMessage.Builder waitId = Ipc.IpcMessage.newBuilder().setCmd(ipcMessage.getCmd()).setIsSyncCall(true).setWaitId(ipcMessage.getWaitId());
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
                                                                            ipcMessage2 = waitId.build();
                                                                        } catch (UninitializedMessageException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        if (ipcMessage2 != null) {
                                                                            this.f8858c.m20531a(ipcMessage2);
                                                                            return;
                                                                        }
                                                                        return;
                                                                    }
                                                                    return;
                                                                case 81:
                                                                    this.f8860e.obtainMessage(21, ipcMessage.getArg1(0), 0, ipcMessage.getArg2(0)).sendToTarget();
                                                                    return;
                                                                case 96:
                                                                    this.f8860e.obtainMessage(19, ipcMessage.getArg1(0), 0, ipcMessage.getArg2(0)).sendToTarget();
                                                                    return;
                                                                case IpcCommand.f8356aC /* 129 */:
                                                                    this.f8860e.obtainMessage(17, ipcMessage.getArg1(0), 0).sendToTarget();
                                                                    return;
                                                                case 257:
                                                                    OnScriptMessageCallback onScriptMessageCallback2 = this.f8858c.f8814j;
                                                                    if (onScriptMessageCallback2 != null) {
                                                                        onScriptMessageCallback2.onDebugMessage(ipcMessage.getFileData());
                                                                        return;
                                                                    }
                                                                    return;
                                                                case 65535:
                                                                    this.f8858c.m20523c();
                                                                    return;
                                                                default:
                                                                    return;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
    }

    /* renamed from: b */
    private void m20478b(Ipc.IpcMessage ipcMessage) {
        ClientInfo.C1352a aVar = new ClientInfo.C1352a();
        aVar.f8795a = ipcMessage.getArg1(0);
        boolean z = true;
        aVar.f8796b = ipcMessage.getArg1(1);
        if (ipcMessage.getArg1(2) == 0) {
            z = false;
        }
        aVar.f8797c = z;
        this.f8858c.f8810f = new ClientInfo(aVar, (byte) 0);
    }

    /* renamed from: b */
    private void m20479b() {
        this.f8858c.f8809e = true;
        m20487a(C1375R.string.toast_on_start_script);
        this.f8860e.sendEmptyMessage(2);
    }

    /* renamed from: c */
    private void m20475c() {
        this.f8860e.sendEmptyMessage(8);
    }

    /* renamed from: d */
    private void m20471d() {
        this.f8860e.sendEmptyMessage(9);
    }

    /* renamed from: c */
    private void m20474c(Ipc.IpcMessage ipcMessage) {
        this.f8857b.m21085a();
        AppAgent.m21068f();
        int arg1 = ipcMessage.getArg1(0);
        this.f8858c.f8809e = false;
        if (arg1 == 105 || arg1 == 0) {
            m20487a(C1375R.string.toast_on_stop_script);
        }
        this.f8860e.obtainMessage(3, arg1, 0, ipcMessage.getArg2(0)).sendToTarget();
    }

    /* renamed from: d */
    private void m20470d(Ipc.IpcMessage ipcMessage) {
        OnScriptMessageCallback onScriptMessageCallback = this.f8858c.f8814j;
        if (onScriptMessageCallback != null) {
            onScriptMessageCallback.onTracePrint(ipcMessage.getArg2(0));
        }
    }

    /* renamed from: e */
    private void m20468e(Ipc.IpcMessage ipcMessage) {
        OnScriptMessageCallback onScriptMessageCallback = this.f8858c.f8814j;
        if (onScriptMessageCallback != null) {
            onScriptMessageCallback.onDebugMessage(ipcMessage.getFileData());
        }
    }

    /* renamed from: f */
    private void m20466f(Ipc.IpcMessage ipcMessage) {
        int arg1 = ipcMessage.getArg1(0);
        int arg12 = ipcMessage.getArg1(1);
        int arg13 = ipcMessage.getArg1(2);
        String valueOf = String.valueOf((arg1 << 16) | arg12);
        OnScreenShotCallback onScreenShotCallback = this.f8858c.f8813i;
        if (arg13 == 0) {
            this.f8858c.f8808d = false;
        }
        if (onScreenShotCallback != null) {
            onScreenShotCallback.onScreenShotDone(valueOf, ipcMessage.getFileData());
        }
    }

    /* renamed from: g */
    private void m20465g(Ipc.IpcMessage ipcMessage) {
        String valueOf = String.valueOf((ipcMessage.getArg1(0) << 16) | ipcMessage.getArg1(1));
        OnUiElementCallback onUiElementCallback = this.f8858c.f8820p;
        if (onUiElementCallback != null) {
            onUiElementCallback.onScreenShotDone(valueOf, ipcMessage.getFileData());
        }
    }

    /* renamed from: h */
    private void m20464h(Ipc.IpcMessage ipcMessage) {
        OnUiElementCallback onUiElementCallback = this.f8858c.f8820p;
        if (onUiElementCallback != null) {
            onUiElementCallback.onUiElementback(ipcMessage.getArg2(0));
        }
    }

    /* renamed from: e */
    private void m20469e() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f8856a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.densityDpi;
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        String d = this.f8857b.m21074d();
        String c = this.f8857b.m21077c();
        String b = this.f8857b.m21081b();
        String str = this.f8856a.getPackageName() + "/" + InputKb.class.getName();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String str2 = UUID.randomUUID().toString() + ".tmp";
        if (d == null) {
            d = "UNKNOW";
        }
        if (c == null) {
            c = "UNKNOW";
        }
        File file = new File(absolutePath, str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Ipc.IpcMessage.Builder cmd = Ipc.IpcMessage.newBuilder().setCmd(14);
        cmd.addArg1(i).addArg1(i2).addArg1(i3).addArg2(d).addArg2(c).addArg2(b).addArg2(str).addArg2(str2).addArg2(absolutePath).addArg2(this.f8856a.getPackageName());
        this.f8858c.m20531a(cmd.build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m20487a(int i) {
        Message obtainMessage = this.f8860e.obtainMessage(5);
        obtainMessage.arg1 = i;
        obtainMessage.sendToTarget();
    }

    /* renamed from: a */
    private void m20483a(String str) {
        this.f8860e.obtainMessage(5, str).sendToTarget();
    }

    /* renamed from: f */
    private void m20467f() {
        this.f8860e.sendEmptyMessage(10);
    }

    /* renamed from: a */
    public final void m20482a(String str, String str2) {
        Log.e("InsertImageToGallery", "InsertImageToGallery:" + str + "|" + str2);
        File file = new File(str);
        if (Build.VERSION.SDK_INT < 29) {
            try {
                MediaStore.Images.Media.insertImage(this.f8856a.getContentResolver(), file.getAbsolutePath(), file.getName(), (String) null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("description", "This is an qr image");
            contentValues.put("_display_name", str2);
            contentValues.put("mime_type", "image/jpeg");
            contentValues.put(C0675j.f373k, "Image.jpg");
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver contentResolver = this.f8856a.getContentResolver();
            Uri insert = contentResolver.insert(uri, contentValues);
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                OutputStream openOutputStream = insert != null ? contentResolver.openOutputStream(insert) : null;
                if (openOutputStream != null) {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        openOutputStream.write(bArr, 0, read);
                    }
                    openOutputStream.flush();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            MediaScannerConnection.scanFile(this.f8856a, new String[]{file.getAbsolutePath()}, null, null);
            return;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        this.f8856a.sendBroadcast(intent);
    }

    /* renamed from: b */
    private void m20476b(String str) {
        File file = new File(str);
        ContentResolver contentResolver = this.f8856a.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C0675j.f373k, file.getName());
        contentValues.put("_display_name", file.getName());
        contentValues.put("mime_type", "video/3gp");
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("_size", Long.valueOf(file.length()));
        this.f8856a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
    }

    /* renamed from: c */
    private void m20472c(String str) {
        String str2 = "_data like \"" + str + "%\"";
        this.f8856a.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str2, null);
        this.f8856a.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str2, null);
    }

    /* renamed from: a */
    private void m20480a(boolean z) {
        ((WifiManager) this.f8856a.getSystemService("wifi")).setWifiEnabled(z);
    }

    /* renamed from: a */
    public final String m20484a(ByteString byteString, int i, int i2, int i3) {
        Log.e("TAG", "GetOrcText: width=" + i + " hight=" + i2 + " size=" + i3);
        byte[] byteArray = byteString.toByteArray();
        int i4 = i3 / 4;
        int[] iArr = new int[i4];
        boolean z = false;
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < 4; i6++) {
                iArr[i5] = iArr[i5] + ((byteArray[(i5 * 4) + i6] & 255) << ((3 - i6) * 8));
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i, i2, Bitmap.Config.ARGB_8888);
        if (!this.f8862g) {
            File file = new File(this.f8856a.getFilesDir().getAbsolutePath() + "/tessdata/", "eng.traineddata");
            File file2 = new File(this.f8856a.getFilesDir().getAbsolutePath() + "/tessdata/", "chi_sim.traineddata");
            if (file.exists() && file2.exists()) {
                this.f8861f = new TessBaseAPI();
                z = this.f8861f.m20151a(this.f8856a.getFilesDir().getAbsolutePath(), "chi_sim+eng");
                Log.d("MqmHandler", "initTessBass: init ret=" + z);
            }
            this.f8862g = z;
        }
        if (!this.f8862g) {
            return "";
        }
        this.f8861f.m20159a(createBitmap);
        String a = this.f8861f.m20162a();
        createBitmap.recycle();
        return a;
    }

    /* renamed from: a */
    public static void m20481a(String str, String str2, int i) {
        BitMatrix hyVar;
        QRCodeWriter nqVar = new QRCodeWriter();
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, EmailConstants.UTF_8);
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashMap.put(EncodeHintType.MARGIN, 2);
        try {
            hyVar = nqVar.mo1618a(str2, BarcodeFormat.QR_CODE, i, i, hashMap);
        } catch (WriterException e) {
            e.printStackTrace();
            hyVar = null;
        }
        int[] iArr = new int[i * i];
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                if (hyVar.m2519a(i2, i3)) {
                    iArr[(i2 * i) + i3] = -16777216;
                } else {
                    iArr[(i2 * i) + i3] = -1;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i, i, Bitmap.Config.RGB_565);
        if (createBitmap != null) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                if (str.endsWith(".png")) {
                    createBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                } else {
                    createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                Log.e("RootIpcDex", "ImageQREnCode IOException" + e2.toString());
            }
        }
    }
}
