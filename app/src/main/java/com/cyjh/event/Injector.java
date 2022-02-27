package com.cyjh.event;

import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.alipay.sdk.widget.C0675j;
import com.cyjh.mobileanjian.ipc.AppAgent;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.ui.FloatAlertDialog;
import com.cyjh.mobileanjian.ipc.ui.FloatInputDialog;
import com.cyjh.mobileanjian.ipc.ui.UiFactory;
import com.cyjh.mobileanjian.ipc.ui.UiManagerLite;
import com.cyjh.mobileanjian.ipc.ui.UiShowLayout;
import com.cyjh.mobileanjian.ipc.ui.WidgetType;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.C1335c;
import com.cyjh.mobileanjian.ipc.utils.ContactsUtils;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mobileanjian.ipc.view.ShowTapPic;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.mq.p046a.MyApplication;
import com.cyjh.mq.p049d.C1363e;
import com.cyjh.mq.p049d.TelephonyUtils;
import com.cyjh.mq.sdk.MqRunner;
import com.cyjh.mq.sdk.MqRunnerLite;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.p105io.FileUtils;
import org.json.JSONException;
import p110z1.BarcodeFormat;
import p110z1.BitMatrix;
import p110z1.C4963cj;
import p110z1.EncodeHintType;
import p110z1.ErrorCorrectionLevel;
import p110z1.QRCodeWriter;
import p110z1.WriterException;

/* loaded from: classes.dex */
public final class Injector {

    /* renamed from: a */
    private static final String f8143a = "Injector";

    /* renamed from: b */
    private static Instrumentation f8144b = null;

    /* renamed from: c */
    private static Context f8145c = null;

    /* renamed from: d */
    private static ExToast f8146d = null;

    /* renamed from: e */
    private static AppAgent f8147e = null;

    /* renamed from: f */
    private static UiManagerLite f8148f = null;

    /* renamed from: g */
    private static TessBaseAPI f8149g = null;

    /* renamed from: h */
    private static boolean f8150h = false;

    /* renamed from: j */
    private static final float f8152j = 0.0f;

    /* renamed from: k */
    private static final float f8153k = -1.5707964f;

    /* renamed from: l */
    private static final float f8154l = -3.1415927f;

    /* renamed from: m */
    private static final float f8155m = 1.5707964f;

    /* renamed from: n */
    private static boolean f8156n = false;

    /* renamed from: o */
    private static int f8157o = 0;

    /* renamed from: p */
    private static String f8158p = null;

    /* renamed from: q */
    private static final int f8159q = 1;

    /* renamed from: r */
    private static final int f8160r = 2;

    /* renamed from: s */
    private static final String f8161s = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11";

    /* renamed from: t */
    private static final int f8162t = 5;

    /* renamed from: u */
    private static final int f8163u = 1;

    /* renamed from: v */
    private static final int f8164v = 2;

    /* renamed from: w */
    private static final int f8165w = 3;

    /* renamed from: i */
    private static Handler f8151i = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.event.Injector.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Injector.m21117a((MessageForSingleProcess) message.obj);
                    return;
                case 2:
                    Injector.m21112b((MessageForSingleProcess) message.obj);
                    return;
                case 3:
                    Injector.m21108c((MessageForSingleProcess) message.obj);
                    return;
                case 4:
                    Injector.m21105d((MessageForSingleProcess) message.obj);
                    return;
                case 5:
                    Injector.m21114b();
                    return;
                case 6:
                    Injector.m21123a();
                    return;
                case 7:
                    Injector.m21115a((byte[]) message.obj);
                    return;
                case 8:
                    MessageForSingleProcess bVar = (MessageForSingleProcess) message.obj;
                    if (MqRunnerLite.getInstance().f8888e) {
                        if (MqRunnerLite.getInstance().f8887d != null) {
                            MqRunnerLite.getInstance().f8887d.onCallback(bVar.f8183a, bVar.f8186d);
                        }
                        MqRunnerLite.getInstance();
                        return;
                    } else if (MqRunner.getInstance().f8880g != null) {
                        MqRunner.getInstance().f8880g.onCallback(bVar.f8183a, bVar.f8186d);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };

    /* renamed from: x */
    private static C1258a[] f8166x = null;

    /* renamed from: y */
    private static MotionEvent.PointerProperties[] f8167y = null;

    /* renamed from: z */
    private static MotionEvent.PointerCoords[] f8168z = null;

    /* renamed from: com.cyjh.event.Injector$b */
    /* loaded from: classes.dex */
    interface AbstractC1259b {

        /* renamed from: a */
        public static final int f8173a = 1;

        /* renamed from: b */
        public static final int f8174b = 2;

        /* renamed from: c */
        public static final int f8175c = 3;

        /* renamed from: d */
        public static final int f8176d = 4;

        /* renamed from: e */
        public static final int f8177e = 5;

        /* renamed from: f */
        public static final int f8178f = 6;

        /* renamed from: g */
        public static final int f8179g = 7;

        /* renamed from: h */
        public static final int f8180h = 8;
    }

    public static void ClearCaches(String str) {
    }

    public static String GetFullUiElement() {
        return "";
    }

    public static String GetUiElement() {
        return "";
    }

    public static int GetVersion() {
        return 6;
    }

    public static String GetWebViewElement(String str) {
        return "";
    }

    public static int InjectWebViewElement(String str, String str2) {
        return 0;
    }

    public static void NotifyApp(String str) {
    }

    public static int SetAirplaneMode(boolean z) {
        return -1;
    }

    public static void StopScript() {
    }

    public static void TracePrint(String str) {
    }

    public static int UninstallApp(String str, String str2) {
        return -1;
    }

    /* renamed from: a */
    private static final float m21122a(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    public static void switchToIm(String str) {
    }

    private Injector() {
    }

    public static void init(Context context) {
        if (context != null) {
            f8145c = context.getApplicationContext();
            f8147e = new AppAgent(f8145c);
            f8148f = new UiManagerLite(f8145c);
            return;
        }
        throw new NullPointerException("Context is null...");
    }

    public static void release() {
        AppAgent.m21068f();
        f8147e.m21085a();
        UiManagerLite iVar = f8148f;
        iVar.m20791c();
        iVar.f8527b = 0;
        iVar.f8531f = 1;
        for (int i = 0; i < 32; i++) {
            iVar.f8526a[i] = null;
            if (iVar.f8530e[i] != null) {
                iVar.f8530e[i].clear();
            }
        }
        releasePointerInfoArray();
    }

    public static boolean initTessBass() {
        File file = new File(f8145c.getFilesDir().getAbsolutePath() + "/tessdata/", "eng.traineddata");
        File file2 = new File(f8145c.getFilesDir().getAbsolutePath() + "/tessdata/", "chi_sim.traineddata");
        if (!file.exists() || !file2.exists()) {
            return false;
        }
        f8149g = new TessBaseAPI();
        boolean a = f8149g.m20151a(f8145c.getFilesDir().getAbsolutePath(), "chi_sim+eng");
        Log.i("MqmHandler", "initTessBass: init ret=" + a);
        return a;
    }

    /* renamed from: a */
    private static void m21118a(int i, MessageForSingleProcess bVar) {
        StringBuilder sb = new StringBuilder("sendToTargetHandler ");
        sb.append(i);
        sb.append("         ");
        sb.append(bVar);
        f8151i.obtainMessage(i, bVar).sendToTarget();
    }

    /* renamed from: e */
    private static void m21103e(MessageForSingleProcess bVar) {
        if (f8146d == null) {
            f8146d = new ExToast(f8145c);
        }
        f8146d.show(bVar.f8186d, bVar.f8183a, bVar.f8184b, bVar.f8185c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cyjh.event.Injector$2 */
    /* loaded from: classes.dex */
    public static class C12562 implements FloatAlertDialog.AbstractC1300a {
        C12562() {
        }

        @Override // com.cyjh.mobileanjian.ipc.ui.FloatAlertDialog.AbstractC1300a
        /* renamed from: a */
        public final void mo20463a(int i) {
            Injector.setSyncReturnValue(i, "");
        }
    }

    /* renamed from: f */
    private static void m21101f(MessageForSingleProcess bVar) {
        new FloatAlertDialog(f8145c, bVar.f8186d, bVar.f8183a, new C12562()).m20905a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cyjh.event.Injector$3 */
    /* loaded from: classes.dex */
    public static class C12573 implements FloatInputDialog.AbstractC1302a {
        C12573() {
        }

        @Override // com.cyjh.mobileanjian.ipc.ui.FloatInputDialog.AbstractC1302a
        /* renamed from: a */
        public final void mo20462a(String str) {
            Injector.setSyncReturnValue(0, str);
        }
    }

    /* renamed from: g */
    private static void m21099g(MessageForSingleProcess bVar) {
        new FloatInputDialog(f8145c, bVar.f8186d, new C12573()).m20900a();
    }

    /* renamed from: h */
    private static void m21097h(MessageForSingleProcess bVar) {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f8886c;
        if (onScriptListener != null) {
            onScriptListener.onUpdateControlBarPos(bVar.f8187e, bVar.f8183a, bVar.f8184b);
        }
    }

    /* renamed from: c */
    private static void m21110c() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f8886c;
        if (onScriptListener != null) {
            onScriptListener.onResume();
        }
    }

    /* renamed from: d */
    private static void m21107d() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f8886c;
        if (onScriptListener != null) {
            onScriptListener.onPause();
        }
    }

    /* renamed from: b */
    private static void m21111b(byte[] bArr) {
        String str;
        int i;
        try {
            UiMessage.CommandToUi parseFrom = UiMessage.CommandToUi.parseFrom(ByteString.copyFrom(bArr));
            if (parseFrom != null) {
                UiManagerLite iVar = f8148f;
                String controlId = parseFrom.getControlId();
                UiMessage.CommandToUi.Command_Type command = parseFrom.getCommand();
                new StringBuilder("command: ").append(parseFrom);
                int i2 = 3;
                boolean z = true;
                switch (UiManagerLite.C13133.f8537a[command.ordinal()]) {
                    case 1:
                        try {
                            iVar.m20799a(parseFrom.getPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 2:
                        try {
                            try {
                                iVar.m20792b(parseFrom.getPath());
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 3:
                        int width = parseFrom.getWidth();
                        int height = parseFrom.getHeight();
                        iVar.f8527b++;
                        iVar.f8526a[iVar.f8527b] = iVar.f8528c.m20880a(controlId, width, height);
                        iVar.f8529d.put(controlId, Integer.valueOf(iVar.f8527b));
                        iVar.f8530e[iVar.f8527b] = new HashMap();
                        iVar.f8526a[iVar.f8527b].f8579b = new UiManagerLite.View$OnClickListenerC13111(controlId);
                        iVar.f8526a[iVar.f8527b].f8578a = new UiManagerLite.View$OnClickListenerC13122(controlId);
                        UiManagerLite.m20796a(z);
                        return;
                    case 4:
                        if (iVar.f8529d.get(controlId) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                            UiManagerLite.m20796a(false);
                            return;
                        } else if (iVar.f8526a[iVar.f8529d.get(controlId).intValue()].f8580c) {
                            UiManagerLite.m20796a(false);
                            return;
                        } else {
                            iVar.f8526a[iVar.f8529d.get(controlId).intValue()].m20731c();
                            UiManagerLite.m20796a(true);
                            UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                            return;
                        }
                    case 5:
                        UiManagerLite.m20789c(controlId);
                        UiManagerLite.m20796a(z);
                        return;
                    case 6:
                        z = iVar.m20786d(controlId);
                        UiManagerLite.m20796a(z);
                        return;
                    case 7:
                        iVar.m20791c();
                        UiManagerLite.m20796a(z);
                        return;
                    case 8:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiShowLayout kVar = iVar.f8526a[iVar.f8527b];
                            iVar.m20803a();
                            kVar.m20737a(controlId, parseFrom.getParentId(), parseFrom.getWidth(), parseFrom.getHeight());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 9:
                        String text = parseFrom.getText();
                        if (iVar.f8529d.get(controlId) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                            z = false;
                        } else {
                            iVar.f8526a[iVar.f8529d.get(controlId).intValue()].m20739a(text);
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 10:
                        if (iVar.f8529d.get(controlId) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(iVar.f8526a[iVar.f8529d.get(controlId).intValue()].m20747a()).build()).build().toByteString());
                        return;
                    case 11:
                        String controlId2 = parseFrom.getControlId();
                        String color = parseFrom.getColor();
                        if (iVar.f8529d.get(controlId2) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId2);
                            UiManagerLite.m20796a(false);
                            return;
                        } else if (!color.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            iVar.m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                            UiManagerLite.m20796a(false);
                            return;
                        } else {
                            if (!color.startsWith("#")) {
                                color = "#" + color;
                            }
                            iVar.f8526a[iVar.f8529d.get(controlId2).intValue()].m20735b(C1335c.m20658a(Color.parseColor(color)));
                            UiManagerLite.m20796a(true);
                            return;
                        }
                    case 12:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar = iVar.f8528c;
                            iVar.m20803a();
                            LinearLayout b = gVar.m20870b(controlId, parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.LINE);
                            iVar.f8526a[iVar.f8527b].m20741a(b, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 13:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar2 = iVar.f8528c;
                            iVar.m20803a();
                            TextView a = gVar2.m20876a(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.TEXT_VIEW);
                            iVar.f8526a[iVar.f8527b].m20741a(a, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 14:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar3 = iVar.f8528c;
                            iVar.m20803a();
                            EditText d = gVar3.m20864d(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.EDIT_TEXT);
                            iVar.f8526a[iVar.f8527b].m20741a(d, parseFrom.getParentId());
                            d.addTextChangedListener(new UiManagerLite.C1314a(controlId));
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 15:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar4 = iVar.f8528c;
                            iVar.m20803a();
                            Button b2 = gVar4.m20868b(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.BUTTON);
                            b2.setOnClickListener(iVar);
                            iVar.f8526a[iVar.f8527b].m20741a(b2, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 16:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar5 = iVar.f8528c;
                            iVar.m20803a();
                            RadioGroup a2 = gVar5.m20873a(controlId, parseFrom.getItemTextList(), parseFrom.getDefaultItemIndex(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.RADIIO_GROUP);
                            iVar.f8526a[iVar.f8527b].m20741a(a2, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 17:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar6 = iVar.f8528c;
                            iVar.m20803a();
                            CheckBox a3 = gVar6.m20875a(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight(), parseFrom.getDefaultCheckStatus());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.CHECK_BOX);
                            iVar.f8526a[iVar.f8527b].m20741a(a3, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 18:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            ImageView a4 = iVar.f8528c.m20883a(iVar.m20803a(), controlId, parseFrom.getWidth(), parseFrom.getHeight(), parseFrom.getPath());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.IMAGE_VIEW);
                            a4.setOnClickListener(iVar);
                            iVar.f8526a[iVar.f8527b].m20741a(a4, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 19:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar7 = iVar.f8528c;
                            iVar.m20803a();
                            WebView a5 = gVar7.m20878a(controlId, parseFrom.getWidth(), parseFrom.getHeight(), parseFrom.getUrl());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.WEB_VIEW);
                            iVar.f8526a[iVar.f8527b].m20741a(a5, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 20:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar8 = iVar.f8528c;
                            iVar.m20803a();
                            List<String> itemTextList = parseFrom.getItemTextList();
                            int defaultItemIndex = parseFrom.getDefaultItemIndex();
                            parseFrom.getWidth();
                            parseFrom.getHeight();
                            Spinner a6 = gVar8.m20874a(controlId, itemTextList, defaultItemIndex);
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.SPINNER);
                            iVar.f8526a[iVar.f8527b].m20741a(a6, parseFrom.getParentId());
                            a6.setOnItemSelectedListener(iVar);
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 21:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            iVar.f8526a[iVar.f8527b].m20738a(parseFrom.getControlId(), parseFrom.getHeight());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 22:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            z = iVar.f8526a[iVar.f8527b].m20744a(iVar.m20803a(), parseFrom.getControlId(), parseFrom.getParentId(), parseFrom.getText());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                        z = iVar.m20801a(parseFrom);
                        UiManagerLite.m20796a(z);
                        return;
                    case 34:
                        if (iVar.f8529d.get(controlId) != null) {
                            UiManagerLite.m20796a(true);
                        } else if (iVar.f8530e[iVar.f8527b].get(controlId) == null) {
                            UiManagerLite.m20796a(false);
                        } else {
                            View b3 = iVar.f8526a[iVar.f8527b].m20732b(controlId);
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b3.getLayoutParams();
                            layoutParams.width = -1;
                            b3.setLayoutParams(layoutParams);
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 35:
                        iVar.m20790c(parseFrom);
                        return;
                    case 36:
                        iVar.m20794b(parseFrom);
                        return;
                    case 37:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId3 = parseFrom.getControlId();
                        parseFrom.getText();
                        View b4 = iVar.f8526a[iVar.f8527b].m20732b(controlId3);
                        if (b4 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        } else if (iVar.f8530e[iVar.f8527b].get(controlId3) != WidgetType.EDIT_TEXT) {
                            UiManagerLite.m20796a(false);
                            return;
                        } else {
                            ((EditText) b4).setInputType(parseFrom.getEditInputType());
                            UiManagerLite.m20796a(true);
                            return;
                        }
                    case 38:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId4 = parseFrom.getControlId();
                        if (iVar.f8530e[iVar.f8527b].get(controlId4) == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        switch (UiManagerLite.C13133.f8538b[((WidgetType) iVar.f8530e[iVar.f8527b].get(controlId4)).ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                                str = ((TextView) iVar.f8526a[iVar.f8527b].m20732b(controlId4)).getText().toString();
                                break;
                            case 4:
                                str = ((CheckBox) iVar.f8526a[iVar.f8527b].m20732b(controlId4)).getText().toString();
                                break;
                            case 5:
                                RadioButton radioButton = (RadioButton) ((RadioGroup) iVar.f8526a[iVar.f8527b].m20732b(controlId4)).findViewById(parseFrom.getItemIndex());
                                if (radioButton != null) {
                                    str = radioButton.getText().toString();
                                    break;
                                } else {
                                    UiManagerLite.m20796a(false);
                                    return;
                                }
                            case 6:
                                Spinner spinner = (Spinner) iVar.f8526a[iVar.f8527b].m20732b(controlId4);
                                if (parseFrom.getItemIndex() < spinner.getCount()) {
                                    new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(parseFrom.getItemIndex()));
                                    str = (String) spinner.getItemAtPosition(parseFrom.getItemIndex());
                                    break;
                                } else {
                                    UiManagerLite.m20796a(false);
                                    return;
                                }
                            default:
                                UiManagerLite.m20796a(false);
                                return;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(str).build()).build().toByteString());
                        return;
                    case 39:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId5 = parseFrom.getControlId();
                        String color2 = parseFrom.getColor();
                        if (!color2.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            iVar.m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        if (!color2.startsWith("#")) {
                            color2 = "#" + color2;
                        }
                        int a7 = C1335c.m20658a(Color.parseColor(color2));
                        if (iVar.f8529d.get(controlId5) != null) {
                            iVar.f8526a[iVar.f8529d.get(controlId5).intValue()].m20746a(a7);
                        } else {
                            View b5 = iVar.f8526a[iVar.f8527b].m20732b(controlId5);
                            if (b5 == null) {
                                UiManagerLite.m20796a(false);
                                return;
                            }
                            switch (UiManagerLite.C13133.f8538b[((WidgetType) iVar.f8530e[iVar.f8527b].get(controlId5)).ordinal()]) {
                                case 1:
                                case 2:
                                case 3:
                                    ((TextView) b5).setTextColor(a7);
                                    break;
                                case 4:
                                    ((CheckBox) b5).setTextColor(a7);
                                    break;
                                case 5:
                                    RadioButton radioButton2 = (RadioButton) ((RadioGroup) b5).findViewById(parseFrom.getItemIndex());
                                    if (radioButton2 != null) {
                                        radioButton2.setTextColor(a7);
                                        break;
                                    } else {
                                        UiManagerLite.m20796a(false);
                                        return;
                                    }
                                case 6:
                                    if (parseFrom.getItemIndex() >= ((Spinner) b5).getCount()) {
                                        UiManagerLite.m20796a(false);
                                        return;
                                    }
                                    break;
                                default:
                                    UiManagerLite.m20796a(false);
                                    return;
                            }
                        }
                        UiManagerLite.m20796a(true);
                        return;
                    case 40:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId6 = parseFrom.getControlId();
                        View b6 = iVar.f8526a[iVar.f8527b].m20732b(controlId6);
                        if (b6 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String color3 = parseFrom.getColor();
                        if (!color3.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            iVar.m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        if (!color3.startsWith("#")) {
                            color3 = "#" + color3;
                        }
                        int a8 = C1335c.m20658a(Color.parseColor(color3));
                        if (iVar.f8530e[iVar.f8527b].get(controlId6) == WidgetType.BUTTON) {
                            GradientDrawable gradientDrawable = new GradientDrawable();
                            gradientDrawable.setColor(a8);
                            gradientDrawable.setCornerRadius(iVar.m20795b());
                            if (Build.VERSION.SDK_INT >= 16) {
                                b6.setBackground(gradientDrawable);
                            } else {
                                b6.setBackgroundDrawable(gradientDrawable);
                            }
                            UiManagerLite.m20796a(true);
                            return;
                        }
                        if (iVar.f8530e[iVar.f8527b].get(controlId6) != WidgetType.SPINNER) {
                            b6.setBackgroundColor(a8);
                        }
                        UiManagerLite.m20796a(true);
                        return;
                    case 41:
                        iVar.m20784e(controlId);
                        return;
                    case 42:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b7 = iVar.f8526a[iVar.f8527b].m20732b(controlId);
                        if (b7 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        boolean isEnabled = b7.isEnabled();
                        if (iVar.f8530e[iVar.f8527b].get(controlId) == WidgetType.EDIT_TEXT) {
                            isEnabled = ((EditText) b7).isFocusable();
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(isEnabled).build()).build().toByteString());
                        return;
                    case 43:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b8 = iVar.f8526a[iVar.f8527b].m20732b(controlId);
                        if (b8 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        int visibility = b8.getVisibility();
                        if (visibility == 0) {
                            i2 = 1;
                        } else if (visibility == 4) {
                            i2 = 2;
                        } else if (visibility != 8) {
                            i2 = 1;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i2).build()).build().toByteString());
                        return;
                    case 44:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId7 = parseFrom.getControlId();
                        if (iVar.f8530e[iVar.f8527b].get(controlId7) == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        switch (UiManagerLite.C13133.f8538b[((WidgetType) iVar.f8530e[iVar.f8527b].get(controlId7)).ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                                i = ((TextView) iVar.f8526a[iVar.f8527b].m20732b(controlId7)).getPaint().getColor();
                                break;
                            case 4:
                                i = ((CheckBox) iVar.f8526a[iVar.f8527b].m20732b(controlId7)).getPaint().getColor();
                                break;
                            case 5:
                                RadioButton radioButton3 = (RadioButton) ((RadioGroup) iVar.f8526a[iVar.f8527b].m20732b(controlId7)).findViewById(parseFrom.getItemIndex());
                                if (radioButton3 != null) {
                                    i = radioButton3.getPaint().getColor();
                                    break;
                                } else {
                                    UiManagerLite.m20796a(false);
                                    return;
                                }
                            default:
                                UiManagerLite.m20796a(false);
                                return;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.red(i)))).build()).build().toByteString());
                        return;
                    case 45:
                    case 47:
                        UiManagerLite.m20796a(z);
                        return;
                    case 46:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b9 = iVar.f8526a[iVar.f8527b].m20732b(parseFrom.getControlId());
                        if (b9 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) b9.getLayoutParams();
                        layoutParams2.gravity = parseFrom.getAlignType();
                        b9.setLayoutParams(layoutParams2);
                        new StringBuilder("set gravity ok; ").append(layoutParams2);
                        UiManagerLite.m20796a(true);
                        return;
                    case 48:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b10 = iVar.f8526a[iVar.f8527b].m20732b(parseFrom.getControlId());
                        if (b10 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        UiMessage.Padding_Var padding = parseFrom.getPadding();
                        new StringBuilder("padding: ").append(padding.getLeft());
                        new StringBuilder("padding: ").append(padding.getTop());
                        new StringBuilder("padding: ").append(padding.getRight());
                        new StringBuilder("padding: ").append(padding.getBottom());
                        b10.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                        UiManagerLite.m20796a(true);
                        return;
                    default:
                        UiManagerLite.m20796a(z);
                        return;
                }
            }
        } catch (InvalidProtocolBufferException e5) {
            e5.printStackTrace();
        }
    }

    /* renamed from: e */
    private static Instrumentation m21104e() {
        if (f8144b == null) {
            f8144b = new Instrumentation();
        }
        return f8144b;
    }

    public static void Tap(float f, float f2, int i) {
        StringBuilder sb = new StringBuilder("Tap1：x=");
        sb.append(f);
        sb.append(",y = ");
        sb.append(f2);
        sb.append(",delay = ");
        sb.append(i);
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            pointerCoords.x = f;
            pointerCoords.y = f2;
            pointerCoords.orientation = GetTapRotation();
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            StringBuilder sb2 = new StringBuilder("Tap2 ：x=");
            sb2.append(f);
            sb2.append(",y = ");
            sb2.append(f2);
            sb2.append(",delay = ");
            sb2.append(i);
            if (i != 0) {
                try {
                    Thread.sleep(i);
                } catch (Exception unused) {
                }
            }
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, 1, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            StringBuilder sb3 = new StringBuilder("Tap3 ：x=");
            sb3.append(f);
            sb3.append(",y = ");
            sb3.append(f2);
            sb3.append(",delay = ");
            sb3.append(i);
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void KeyPress(int i) {
        if (MqRunnerLite.getInstance().f8885b != null) {
            MqRunnerLite.getInstance().f8885b.keyPress(i);
        }
    }

    public static void Swipe(float f, float f2, float f3, float f4, int i) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, f, f2, 0));
            int i2 = i / 11;
            for (int i3 = 1; i3 <= 11; i3++) {
                try {
                    long uptimeMillis2 = SystemClock.uptimeMillis();
                    float f5 = i3 / 11.0f;
                    m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis2, uptimeMillis2, 2, ((f3 - f) * f5) + f, ((f4 - f2) * f5) + f2, 0));
                    if (i2 != 0) {
                        Thread.sleep(i2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long uptimeMillis3 = SystemClock.uptimeMillis();
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis3, uptimeMillis3, 1, f3, f4, 0));
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void KeyDown(int i) {
        try {
            m21104e().sendKeySync(new KeyEvent(0, i));
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void KeyUp(int i) {
        try {
            m21104e().sendKeySync(new KeyEvent(1, i));
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void sendText(String str, int i) {
        if (MqRunnerLite.getInstance().f8885b != null) {
            MqRunnerLite.getInstance().f8885b.inputText(str);
        }
    }

    public static void TouchMove(int i, float f, float f2, int i2) {
        C1258a b;
        try {
            if (!(f8166x == null || (b = m21113b(i)) == null)) {
                float f3 = b.f8171c;
                float f4 = b.f8172d;
                int i3 = 11;
                if (i2 <= 200) {
                    float f5 = f3 - f;
                    float f6 = f4 - f2;
                    double sqrt = Math.sqrt((f5 * f5) + (f6 * f6));
                    double min = Math.min(MyApplication.f8788g.getApplicationContext().getResources().getDisplayMetrics().widthPixels, MyApplication.f8788g.getApplicationContext().getResources().getDisplayMetrics().heightPixels);
                    if (!(2.0d * sqrt > min)) {
                        i3 = ((int) ((sqrt * 20.0d) / min)) + 1;
                    }
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                for (int i4 = 1; i4 <= i3; i4++) {
                    long uptimeMillis2 = SystemClock.uptimeMillis();
                    float f7 = i4 / i3;
                    m21120a(i, ((f - f3) * f7) + f3, ((f2 - f4) * f7) + f4);
                    int a = m21119a(i, 2);
                    int g = m21100g();
                    m21098h();
                    m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis2, uptimeMillis2, a, g, f8167y, f8168z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
                    if (i2 != 0) {
                        long uptimeMillis3 = (i2 - (SystemClock.uptimeMillis() - uptimeMillis)) / ((i3 + 1) - i4);
                        if (uptimeMillis3 >= 10) {
                            Thread.sleep(uptimeMillis3);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void TouchMoveEvent(int i, float f, float f2, int i2) {
        try {
            if (f8166x != null && m21113b(i) != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                m21120a(i, f, f2);
                int a = m21119a(i, 2);
                int g = m21100g();
                m21098h();
                if (i2 > 10) {
                    Thread.sleep(i2 - 15);
                }
                m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, a, g, f8167y, f8168z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void TouchUp(int i) {
        try {
            if (!(f8166x == null || m21113b(i) == null)) {
                long uptimeMillis = SystemClock.uptimeMillis();
                int a = m21119a(i, 3);
                int g = m21100g();
                m21098h();
                m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, a, g, f8167y, f8168z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
                for (int i2 = 0; i2 < 5; i2++) {
                    if (i == f8166x[i2].f8170b) {
                        f8166x[i2].f8169a = true;
                        f8166x[i2].f8170b = 0;
                        f8166x[i2].f8171c = 0.0f;
                        f8166x[i2].f8172d = 0.0f;
                    }
                }
            }
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void ImageQREnCode(String str, String str2, int i) {
        BitMatrix hyVar;
        QRCodeWriter nqVar = new QRCodeWriter();
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, EmailConstants.UTF_8);
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashMap.put(EncodeHintType.MARGIN, 2);
        try {
            hyVar = nqVar.mo1618a(str2, BarcodeFormat.QR_CODE, 400, 400, hashMap);
        } catch (WriterException e) {
            e.printStackTrace();
            hyVar = null;
        }
        int[] iArr = new int[160000];
        for (int i2 = 0; i2 < 400; i2++) {
            for (int i3 = 0; i3 < 400; i3++) {
                if (hyVar.m2519a(i2, i3)) {
                    iArr[(i2 * 400) + i3] = -16777216;
                } else {
                    iArr[(i2 * 400) + i3] = -1;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, 400, 400, Bitmap.Config.RGB_565);
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
                Log.e("RootIpcFramework", "ImageQREnCode IOException" + e2.toString());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String ImageQRDeCode(java.lang.String r12) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            z1.jl r1 = p110z1.DecodeHintType.CHARACTER_SET
            java.lang.String r2 = "utf-8"
            r0.put(r1, r2)
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: all -> 0x0022, Exception -> 0x0025
            r2.<init>(r12)     // Catch: all -> 0x0022, Exception -> 0x0025
            android.graphics.Bitmap r12 = android.graphics.BitmapFactory.decodeStream(r2)     // Catch: Exception -> 0x0020, all -> 0x009f
            r2.close()     // Catch: IOException -> 0x001a
            goto L_0x001e
        L_0x001a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x001e:
            r2 = r12
            goto L_0x0035
        L_0x0020:
            r12 = move-exception
            goto L_0x0027
        L_0x0022:
            r12 = move-exception
            goto L_0x00a1
        L_0x0025:
            r12 = move-exception
            r2 = r1
        L_0x0027:
            r12.printStackTrace()     // Catch: all -> 0x009f
            if (r2 == 0) goto L_0x0034
            r2.close()     // Catch: IOException -> 0x0030
            goto L_0x0034
        L_0x0030:
            r12 = move-exception
            r12.printStackTrace()
        L_0x0034:
            r2 = r1
        L_0x0035:
            int r12 = r2.getWidth()     // Catch: Exception -> 0x007d
            int r10 = r2.getHeight()     // Catch: Exception -> 0x007d
            int r3 = r12 * r10
            int[] r11 = new int[r3]     // Catch: Exception -> 0x007d
            r4 = 0
            r6 = 0
            r7 = 0
            r3 = r11
            r5 = r12
            r8 = r12
            r9 = r10
            r2.getPixels(r3, r4, r5, r6, r7, r8, r9)     // Catch: Exception -> 0x007d
            z1.oi r2 = new z1.oi     // Catch: Exception -> 0x007d
            r2.<init>(r12, r10, r11)     // Catch: Exception -> 0x007d
            z1.oe r12 = new z1.oe     // Catch: Exception -> 0x007b
            r12.<init>()     // Catch: Exception -> 0x007b
            z1.ht r3 = new z1.ht     // Catch: Exception -> 0x007b
            z1.il r4 = new z1.il     // Catch: Exception -> 0x007b
            r4.<init>(r2)     // Catch: Exception -> 0x007b
            r3.<init>(r4)     // Catch: Exception -> 0x007b
            z1.ol r12 = r12.mo1636a(r3, r0)     // Catch: Exception -> 0x007b
            java.lang.String r3 = "RootIpcFramework"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: Exception -> 0x007b
            java.lang.String r5 = "syncDecodeQRCode: result is:"
            r4.<init>(r5)     // Catch: Exception -> 0x007b
            java.lang.String r5 = r12.f22707a     // Catch: Exception -> 0x007b
            r4.append(r5)     // Catch: Exception -> 0x007b
            java.lang.String r4 = r4.toString()     // Catch: Exception -> 0x007b
            android.util.Log.i(r3, r4)     // Catch: Exception -> 0x007b
            java.lang.String r12 = r12.f22707a     // Catch: Exception -> 0x007b
            return r12
        L_0x007b:
            r12 = move-exception
            goto L_0x007f
        L_0x007d:
            r12 = move-exception
            r2 = r1
        L_0x007f:
            r12.printStackTrace()
            if (r2 == 0) goto L_0x009e
            z1.oe r12 = new z1.oe     // Catch: Throwable -> 0x009a
            r12.<init>()     // Catch: Throwable -> 0x009a
            z1.ht r3 = new z1.ht     // Catch: Throwable -> 0x009a
            z1.ij r4 = new z1.ij     // Catch: Throwable -> 0x009a
            r4.<init>(r2)     // Catch: Throwable -> 0x009a
            r3.<init>(r4)     // Catch: Throwable -> 0x009a
            z1.ol r12 = r12.mo1636a(r3, r0)     // Catch: Throwable -> 0x009a
            java.lang.String r12 = r12.f22707a     // Catch: Throwable -> 0x009a
            return r12
        L_0x009a:
            r12 = move-exception
            r12.printStackTrace()
        L_0x009e:
            return r1
        L_0x009f:
            r12 = move-exception
            r1 = r2
        L_0x00a1:
            if (r1 == 0) goto L_0x00ab
            r1.close()     // Catch: IOException -> 0x00a7
            goto L_0x00ab
        L_0x00a7:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00ab:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.event.Injector.ImageQRDeCode(java.lang.String):java.lang.String");
    }

    public static void InsertImageToGallery(String str, int i) {
        File file = new File(str);
        if (file.exists()) {
            if (i == 1) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/anjian/";
                File file2 = new File(str2);
                if (!file2.exists()) {
                    file2.mkdir();
                }
                File file3 = new File(str2 + file.getName());
                if (file3.exists()) {
                    file3.delete();
                }
                try {
                    FileUtils.copyFile(file, file3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file = file3;
            }
            if (Build.VERSION.SDK_INT < 29) {
                try {
                    MediaStore.Images.Media.insertImage(f8145c.getContentResolver(), file.getAbsolutePath(), file.getName(), (String) null);
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put("description", "This is an qr image");
                contentValues.put("_display_name", file.getName());
                contentValues.put("mime_type", "image/jpeg");
                contentValues.put(C0675j.f373k, "Image.jpg");
                contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = f8145c.getContentResolver();
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
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (Build.VERSION.SDK_INT >= 19) {
                MediaScannerConnection.scanFile(f8145c, new String[]{file.getAbsolutePath()}, null, null);
                return;
            }
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            f8145c.sendBroadcast(intent);
        }
    }

    public static void InsertVideoToGallery(String str) {
        File file = new File(str);
        if (file.exists()) {
            String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/anjian/";
            File file2 = new File(str2);
            if (!file2.exists()) {
                file2.mkdir();
            }
            File file3 = new File(str2 + file.getName());
            if (file3.exists()) {
                file3.delete();
            }
            try {
                FileUtils.copyFile(file, file3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            long currentTimeMillis = System.currentTimeMillis();
            ContentResolver contentResolver = f8145c.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(C0675j.f373k, file3.getName());
            contentValues.put("_display_name", file3.getName());
            contentValues.put("mime_type", "video/3gp");
            contentValues.put("datetaken", Long.valueOf(currentTimeMillis));
            contentValues.put("date_modified", Long.valueOf(currentTimeMillis));
            contentValues.put("date_added", Long.valueOf(currentTimeMillis));
            contentValues.put("_data", file3.getAbsolutePath());
            contentValues.put("_size", Long.valueOf(file3.length()));
            f8145c.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
        }
    }

    public static void ClearAllPhotos() {
        String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/anjian/";
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
        String str2 = "_data like \"" + str + "%\"";
        f8145c.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str2, null);
        f8145c.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str2, null);
    }

    public static void SaveSnapShot(int[] iArr, int i, int i2, int i3, String str, int i4) {
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.ARGB_8888);
        if (createBitmap != null) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                createBitmap.compress(Bitmap.CompressFormat.JPEG, i4, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                Log.e("RootIpcFramework", "SaveSnapShot IOException" + e.toString());
            }
        }
    }

    public static void SetCellularDataEnable(boolean z) {
        ConnectivityManager connectivityManager = (ConnectivityManager) f8145c.getSystemService("connectivity");
        try {
            connectivityManager.getClass().getMethod("setMobileDataEnabled", Boolean.TYPE).invoke(connectivityManager, Boolean.valueOf(z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SetWifiEnable(boolean z) {
        ((WifiManager) f8145c.getSystemService("wifi")).setWifiEnabled(z);
    }

    public static void SetBTEnable(boolean z) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (z) {
            defaultAdapter.enable();
        } else {
            defaultAdapter.disable();
        }
    }

    public static void SetRotationLockEnable(boolean z) {
        Settings.System.putInt(f8145c.getContentResolver(), "accelerometer_rotation", !z ? 1 : 0);
    }

    public static String GetNetType() {
        return f8147e.m21064i();
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01c5 A[Catch: IOException -> 0x01c1, TRY_LEAVE, TryCatch #3 {IOException -> 0x01c1, blocks: (B:67:0x01bd, B:71:0x01c5), top: B:76:0x01bd }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String UrlRequestEx(int r17, java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.event.Injector.UrlRequestEx(int, java.lang.String):java.lang.String");
    }

    public static void RandomTap(float f, float f2, int i, String str) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            StringBuilder sb = new StringBuilder("RandomTap：x=");
            sb.append(f);
            sb.append(",y = ");
            sb.append(f2);
            sb.append(",random = ");
            sb.append(i);
            double d = i;
            float random = f + ((int) ((Math.random() * d) - (i / 2)));
            float random2 = f2 + ((int) ((Math.random() * d) - (i / 2)));
            StringBuilder sb2 = new StringBuilder("RandomTap：realX=");
            sb2.append(random);
            sb2.append(",realY = ");
            sb2.append(random2);
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            pointerCoords.x = random;
            pointerCoords.y = random2;
            pointerCoords.orientation = GetTapRotation();
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            try {
                Thread.sleep(50L);
            } catch (Exception unused) {
            }
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, 1, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            new ShowTapPic(f8145c).m20586a((int) random, (int) random2, str);
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void RandomsTap(float f, float f2, int i, String str) {
        float f3;
        InterruptedException e;
        long uptimeMillis;
        StringBuilder sb = new StringBuilder("RandomsTap：x=");
        sb.append(f);
        sb.append(",y = ");
        sb.append(f2);
        sb.append(",random = ");
        sb.append(i);
        double d = i;
        double d2 = i / 2;
        float random = f + ((int) ((Math.random() * d) - d2));
        float random2 = f2 + ((int) ((Math.random() * d) - d2));
        StringBuilder sb2 = new StringBuilder("RandomTap：x2=");
        sb2.append(random);
        sb2.append(",y2 = ");
        sb2.append(random2);
        long uptimeMillis2 = SystemClock.uptimeMillis();
        m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis2, uptimeMillis2, 0, f, f2, 0));
        try {
            uptimeMillis = SystemClock.uptimeMillis();
            f3 = random;
        } catch (InterruptedException e2) {
            e = e2;
            f3 = random;
        }
        try {
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, 2, random, random2, 0));
            Thread.sleep(20L);
        } catch (InterruptedException e3) {
            e = e3;
            e.printStackTrace();
            long uptimeMillis3 = SystemClock.uptimeMillis();
            m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis3, uptimeMillis3, 1, f3, random2, 0));
            new ShowTapPic(f8145c).m20586a((int) f3, (int) random2, str);
        }
        long uptimeMillis32 = SystemClock.uptimeMillis();
        m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis32, uptimeMillis32, 1, f3, random2, 0));
        new ShowTapPic(f8145c).m20586a((int) f3, (int) random2, str);
    }

    public static void MoveZoomIn(float f, float f2, float f3, float f4, int i) {
        Log.e("RootIpcFramework", "MoveZoomIn");
        float f5 = (f + f3) / 2.0f;
        float f6 = (f2 + f4) / 2.0f;
        TouchDown(10123, f5, f6);
        TouchDown(10124, f5, f6);
        int i2 = i / 11;
        if (i2 < 10) {
            i2 = 10;
        }
        float f7 = (f - f5) / 11.0f;
        float f8 = (f2 - f6) / 11.0f;
        float f9 = (f3 - f5) / 11.0f;
        float f10 = (f4 - f6) / 11.0f;
        try {
            Thread.sleep(20L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float f11 = f5;
        float f12 = f6;
        float f13 = f12;
        float f14 = f11;
        for (int i3 = 0; i3 < 11; i3++) {
            f14 += f7;
            f12 += f8;
            f11 += f9;
            f13 += f10;
            TouchMoveEvent(10123, f14, f12, 0);
            TouchMoveEvent(10124, f11, f13, 0);
            try {
                Thread.sleep(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        TouchUp(10123);
        TouchUp(10124);
    }

    public static void MoveZoomOut(float f, float f2, float f3, float f4, int i) {
        Log.e("RootIpcFramework", "MoveZoomOut");
        float f5 = (f + f3) / 2.0f;
        float f6 = (f2 + f4) / 2.0f;
        TouchDown(10125, f, f2);
        TouchDown(10126, f3, f4);
        int i2 = i / 11;
        if (i2 < 10) {
            i2 = 10;
        }
        float f7 = (f5 - f) / 11.0f;
        float f8 = (f6 - f2) / 11.0f;
        float f9 = (f5 - f3) / 11.0f;
        float f10 = (f6 - f4) / 11.0f;
        try {
            Thread.sleep(20L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float f11 = f4;
        float f12 = f3;
        float f13 = f2;
        float f14 = f;
        for (int i3 = 0; i3 < 11; i3++) {
            f14 += f7;
            f13 += f8;
            f12 += f9;
            f11 += f10;
            TouchMoveEvent(10125, f14, f13, 0);
            TouchMoveEvent(10126, f12, f11, 0);
            try {
                Thread.sleep(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        TouchUp(10125);
        TouchUp(10126);
    }

    public static String GetAppPath(String str) {
        try {
            return f8145c.getPackageManager().getApplicationInfo(str, 0).sourceDir;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String OcrText(byte[] bArr, int i, int i2, int i3) {
        Log.e("TAG", "GetOrcText: width=" + i2 + " hight=" + i3 + " size=" + i);
        if (!f8150h) {
            f8150h = initTessBass();
        }
        if (!f8150h) {
            return "";
        }
        int i4 = i / 4;
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < 4; i6++) {
                iArr[i5] = iArr[i5] + ((bArr[(i5 * 4) + i6] & 255) << ((3 - i6) * 8));
            }
        }
        f8149g.m20159a(Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.ARGB_8888));
        return f8149g.m20162a();
    }

    public static boolean GetVPNStatus() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp()) {
                    String name = networkInterface.getName();
                    Log.e("GetVPNStatus", "GetVPNStatus name:" + networkInterface.getName());
                    if (name.contains("tun") || name.contains("ppp") || name.contains("pptp")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void SetVPNEnable(boolean z) {
        f8147e.m21062k();
    }

    public static boolean IsRoot() {
        String str = System.getenv("PATH");
        new ArrayList();
        for (String str2 : str.split(":")) {
            File file = new File(str2, C1363e.f8870a);
            if (file.exists() && file.canExecute()) {
                return true;
            }
        }
        return false;
    }

    public static boolean Is64Bit() {
        Class<?> cls;
        Method declaredMethod;
        Object invoke;
        Method declaredMethod2;
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 21 || (cls = Class.forName("dalvik.system.VMRuntime")) == null || (declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0])) == null || (invoke = declaredMethod.invoke(null, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0])) == null) {
            return false;
        }
        Object invoke2 = declaredMethod2.invoke(invoke, new Object[0]);
        if (invoke2 instanceof Boolean) {
            return ((Boolean) invoke2).booleanValue();
        }
        return false;
    }

    public static void SetControlBarPos(float f, int i, int i2) {
        MessageForSingleProcess bVar = new MessageForSingleProcess();
        bVar.f8187e = f;
        bVar.f8183a = i;
        bVar.f8184b = i2;
        m21118a(4, bVar);
    }

    public static String GetSdcardDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static void ShowMessage(String str, int i, int i2, int i3) {
        MessageForSingleProcess bVar = new MessageForSingleProcess();
        bVar.f8183a = i2;
        bVar.f8184b = i3;
        bVar.f8185c = i;
        bVar.f8186d = str;
        m21118a(1, bVar);
    }

    public static void Vibrate(int i) {
        f8147e.m21084a(i);
    }

    public static void KeepScreen(boolean z) {
        f8147e.m21080b(z ? 10 : 0);
    }

    public static void AddContact(String str, String str2, String str3) {
        ContactsUtils.m20655a(f8145c, str, str2, str3);
    }

    public static void DeleteContact(String str) {
        ContactsUtils.m20656a(f8145c, str);
    }

    public static void ClearContacts() {
        ContactsUtils.m20657a(f8145c);
    }

    public static void RunApp(String str, String str2) {
        if (MqRunnerLite.getInstance().f8885b != null) {
            MqRunnerLite.getInstance().f8885b.launchApp(str);
        }
    }

    public static void KillApp(String str) {
        if (MqRunnerLite.getInstance().f8885b != null) {
            MqRunnerLite.getInstance().f8885b.killApp(str);
        }
    }

    public static void SpecialFunction(int i, String str) {
        StringBuilder sb = new StringBuilder("SpecialFunction   ");
        sb.append(i);
        sb.append(", ");
        sb.append(str);
        if (MqRunnerLite.getInstance().f8885b != null) {
            MqRunnerLite.getInstance().f8885b.doSpecialFuction(i, str);
        }
    }

    public static void PlaySound(String str) {
        AppAgent.m21075c(str);
    }

    public static void StopPlay() {
        AppAgent.m21068f();
    }

    public static void FreeupMemory() {
        f8147e.m21071e();
    }

    public static int GetDisplayDpi() {
        return f8145c.getResources().getDisplayMetrics().densityDpi;
    }

    public static String GetRunningApp() {
        return MqRunnerLite.getInstance().f8885b.getRunningPackages();
    }

    public static String GetForegroundPackage() {
        return MqRunnerLite.getInstance().f8885b != null ? MqRunnerLite.getInstance().f8885b.getForegroundPackage() : "";
    }

    public static void OnPause() {
        f8151i.sendEmptyMessage(6);
    }

    public static void OnResume() {
        f8151i.sendEmptyMessage(5);
    }

    public static void LockScreen() {
        f8147e.m21066g();
    }

    public static void UnlockScreen() {
        f8147e.m21065h();
    }

    public static void SetAutoLockTime(int i) {
        f8147e.m21076c(i);
    }

    public static String GetDeviceName() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && !TextUtils.isEmpty(defaultAdapter.getName())) {
                return defaultAdapter.getName();
            }
        } catch (Exception unused) {
        }
        return Build.MODEL;
    }

    public static void SetBacklightLevel(int i) {
        if (i > 100) {
            i = 100;
        }
        f8147e.m21073d(i);
    }

    public static void SetVolumeLevel(int i) {
        if (i > 100) {
            i = 100;
        }
        f8147e.m21070e(i);
    }

    public static void setSyncReturnValue(int i, String str) {
        f8157o = i;
        f8158p = str;
        f8156n = true;
    }

    public static int ShowMsgBox(String str, int i, int i2, int i3) {
        f8156n = false;
        MessageForSingleProcess bVar = new MessageForSingleProcess();
        bVar.f8183a = i;
        bVar.f8184b = i2;
        bVar.f8185c = i3;
        bVar.f8186d = str;
        m21118a(2, bVar);
        while (!f8156n) {
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return f8157o;
    }

    public static String ShowInputDialog(String str, int i, int i2) {
        f8156n = false;
        MessageForSingleProcess bVar = new MessageForSingleProcess();
        bVar.f8183a = i;
        bVar.f8184b = i2;
        bVar.f8186d = str;
        m21118a(3, bVar);
        while (!f8156n) {
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new StringBuilder("InputDialog typed text: ").append(f8158p);
        return f8158p;
    }

    public static boolean SendSimpleEmail(String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str6)) {
            return false;
        }
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setHostName(str);
        simpleEmail.setSmtpPort(465);
        simpleEmail.setAuthentication(str2, str3);
        simpleEmail.setSSLOnConnect(true);
        try {
            simpleEmail.setFrom(str2);
            simpleEmail.setSubject(str4);
            simpleEmail.setMsg(str5);
            for (String str7 : str6.split(C4963cj.f20745b)) {
                simpleEmail.addTo(str7.trim());
            }
            simpleEmail.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static float GetTapRotation() {
        switch (GetScreenRotation()) {
            case 0:
                Log.e(f8143a, "Tap Tap ROTATION_0");
                return 0.0f;
            case 1:
                Log.e(f8143a, "Tap Tap ROTATION_90");
                return f8153k;
            case 2:
                Log.e(f8143a, "Tap Tap ROTATION_180");
                return f8154l;
            case 3:
                Log.e(f8143a, "Tap Tap ROTATION_270");
                return f8155m;
            default:
                return 0.0f;
        }
    }

    public static byte[] SendUiRequest(byte[] bArr) {
        f8151i.obtainMessage(7, bArr).sendToTarget();
        return UiTransHelper.m21094a();
    }

    public static byte[] GetUiEvent() {
        return UiTransHelper.m21092b();
    }

    public static byte[] GetFloatEvent() {
        Log.e(f8143a, "GetFloatEvent: ");
        ByteString d = UiTransHelper.m21088d();
        if (d == null) {
            return null;
        }
        return d.toByteArray();
    }

    public static void FloatEventThreadExit() {
        UiTransHelper.m21087e();
        Log.e(f8143a, "FloatEventThreadExit: ");
    }

    public static String UrlRequest(int i, String str, String str2, int i2) {
        if (i2 <= 0) {
            i2 = 5;
        }
        try {
            if (i == 1) {
                int i3 = i2 * 1000;
                String body = HttpRequest.get(m21116a(str)).connectTimeout(i3).readTimeout(i3).useCaches(false).body();
                return body == null ? "" : body;
            } else if (i != 2) {
                return "";
            } else {
                int i4 = i2 * 1000;
                String body2 = new HttpRequest(str, "POST").header("User-Agent", f8161s).followRedirects(false).connectTimeout(i4).readTimeout(i4).useCaches(false).send(str2).body();
                return body2 == null ? "" : body2;
            }
        } catch (HttpRequest.HttpRequestException unused) {
            return "";
        }
    }

    /* renamed from: a */
    private static String m21116a(String str) {
        try {
            Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(str);
            while (matcher.find()) {
                String group = matcher.group();
                str = str.replaceAll(group, URLEncoder.encode(group, "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void OnResponse(int i, String str) {
        MessageForSingleProcess bVar = new MessageForSingleProcess();
        bVar.f8183a = i;
        bVar.f8186d = str;
        m21118a(8, bVar);
    }

    public static void OnElfCallback(int i, String str) {
        MessageForSingleProcess bVar = new MessageForSingleProcess();
        bVar.f8183a = i;
        bVar.f8186d = str;
        m21118a(8, bVar);
    }

    public static String GetAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.cyjh.event.Injector$a */
    /* loaded from: classes.dex */
    public static class C1258a {

        /* renamed from: a */
        public boolean f8169a;

        /* renamed from: b */
        public int f8170b;

        /* renamed from: c */
        public float f8171c;

        /* renamed from: d */
        public float f8172d;

        private C1258a() {
        }

        /* synthetic */ C1258a(byte b) {
            this();
        }
    }

    /* renamed from: f */
    private static void m21102f() {
        if (f8166x == null) {
            f8166x = new C1258a[5];
            for (int i = 0; i < 5; i++) {
                C1258a aVar = new C1258a((byte) 0);
                aVar.f8169a = true;
                aVar.f8170b = 0;
                aVar.f8171c = 0.0f;
                aVar.f8172d = 0.0f;
                f8166x[i] = aVar;
            }
        }
    }

    public static void releasePointerInfoArray() {
        f8166x = null;
    }

    /* renamed from: a */
    private static void m21121a(int i) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (i == f8166x[i2].f8170b) {
                C1258a[] aVarArr = f8166x;
                aVarArr[i2].f8169a = true;
                aVarArr[i2].f8170b = 0;
                aVarArr[i2].f8171c = 0.0f;
                aVarArr[i2].f8172d = 0.0f;
            }
        }
    }

    /* renamed from: a */
    private static void m21120a(int i, float f, float f2) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f8166x[i2].f8169a && f8166x[i2].f8170b == i) {
                C1258a[] aVarArr = f8166x;
                aVarArr[i2].f8170b = i;
                aVarArr[i2].f8171c = f;
                aVarArr[i2].f8172d = f2;
                aVarArr[i2].f8169a = false;
                return;
            }
        }
        for (int i3 = 0; i3 < 5; i3++) {
            if (f8166x[i3].f8169a) {
                C1258a[] aVarArr2 = f8166x;
                aVarArr2[i3].f8170b = i;
                aVarArr2[i3].f8171c = f;
                aVarArr2[i3].f8172d = f2;
                aVarArr2[i3].f8169a = false;
                return;
            }
        }
    }

    /* renamed from: b */
    private static C1258a m21113b(int i) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f8166x[i2].f8169a && f8166x[i2].f8170b == i) {
                return f8166x[i2];
            }
        }
        return null;
    }

    /* renamed from: c */
    private static int m21109c(int i) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f8166x[i2].f8169a && f8166x[i2].f8170b == i) {
                return i2;
            }
        }
        return -1;
    }

    /* renamed from: d */
    private static int m21106d(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (!f8166x[i3].f8169a) {
                i2++;
            }
        }
        return i2;
    }

    /* renamed from: g */
    private static int m21100g() {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f8166x[i2].f8169a) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: h */
    private static void m21098h() {
        int g = m21100g();
        if (g != 0) {
            f8167y = new MotionEvent.PointerProperties[g];
            f8168z = new MotionEvent.PointerCoords[g];
            int i = 0;
            for (int i2 = 0; i2 < 5; i2++) {
                if (!f8166x[i2].f8169a) {
                    if (i < g) {
                        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
                        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                        pointerProperties.id = i2;
                        pointerCoords.x = f8166x[i2].f8171c;
                        pointerCoords.y = f8166x[i2].f8172d;
                        pointerCoords.orientation = GetTapRotation();
                        f8167y[i] = pointerProperties;
                        f8168z[i] = pointerCoords;
                    }
                    i++;
                }
            }
        }
    }

    /* renamed from: a */
    private static int m21119a(int i, int i2) {
        int g = m21100g();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= 5) {
                i4 = -1;
                break;
            }
            if (!f8166x[i4].f8169a && f8166x[i4].f8170b == i) {
                break;
            }
            i4++;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            if (!f8166x[i6].f8169a) {
                i5++;
            }
        }
        switch (i2) {
            case 1:
                if (1 != g) {
                    i3 = (i5 * 256) + 5;
                    break;
                }
                break;
            case 2:
                if (1 == g) {
                    i3 = 2;
                    break;
                } else {
                    i3 = (i5 * 256) + 2;
                    break;
                }
            case 3:
                if (1 == g) {
                    i3 = 1;
                    break;
                } else {
                    i3 = (i5 * 256) + 6;
                    break;
                }
        }
        StringBuilder sb = new StringBuilder("injector>>>>>>touchType:");
        sb.append(i2);
        sb.append("action:");
        sb.append(i3);
        sb.append("---pointerNum:");
        sb.append(g);
        sb.append("---pointerId:");
        sb.append(i5);
        return i3;
    }

    public static void TouchDown(int i, float f, float f2) {
        try {
            if (f8166x == null) {
                f8166x = new C1258a[5];
                for (int i2 = 0; i2 < 5; i2++) {
                    C1258a aVar = new C1258a((byte) 0);
                    aVar.f8169a = true;
                    aVar.f8170b = 0;
                    aVar.f8171c = 0.0f;
                    aVar.f8172d = 0.0f;
                    f8166x[i2] = aVar;
                }
            }
            if (m21100g() < 5) {
                m21120a(i, f, f2);
                long uptimeMillis = SystemClock.uptimeMillis();
                int a = m21119a(i, 1);
                int g = m21100g();
                m21098h();
                m21104e().sendPointerSync(MotionEvent.obtain(uptimeMillis, uptimeMillis, a, g, f8167y, f8168z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            }
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static String GetDeviceID() {
        Context applicationContext = MyApplication.f8788g.getApplicationContext();
        String deviceId = ((TelephonyManager) applicationContext.getSystemService(ShippingInfoWidget.f12563f)).getDeviceId();
        if (deviceId == null || deviceId.equals("")) {
            deviceId = TelephonyUtils.m20431a(applicationContext);
        }
        return TextUtils.isEmpty(deviceId) ? TelephonyUtils.m20431a(MyApplication.f8788g.getApplicationContext()) : deviceId;
    }

    public static int GetScreenRotation() {
        return ((WindowManager) MyApplication.f8788g.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    /* renamed from: a */
    static /* synthetic */ void m21117a(MessageForSingleProcess bVar) {
        if (f8146d == null) {
            f8146d = new ExToast(f8145c);
        }
        f8146d.show(bVar.f8186d, bVar.f8183a, bVar.f8184b, bVar.f8185c);
    }

    /* renamed from: b */
    static /* synthetic */ void m21112b(MessageForSingleProcess bVar) {
        new FloatAlertDialog(f8145c, bVar.f8186d, bVar.f8183a, new C12562()).m20905a();
    }

    /* renamed from: c */
    static /* synthetic */ void m21108c(MessageForSingleProcess bVar) {
        new FloatInputDialog(f8145c, bVar.f8186d, new C12573()).m20900a();
    }

    /* renamed from: d */
    static /* synthetic */ void m21105d(MessageForSingleProcess bVar) {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f8886c;
        if (onScriptListener != null) {
            onScriptListener.onUpdateControlBarPos(bVar.f8187e, bVar.f8183a, bVar.f8184b);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m21123a() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f8886c;
        if (onScriptListener != null) {
            onScriptListener.onPause();
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m21114b() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f8886c;
        if (onScriptListener != null) {
            onScriptListener.onResume();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m21115a(byte[] bArr) {
        String str;
        int i;
        try {
            UiMessage.CommandToUi parseFrom = UiMessage.CommandToUi.parseFrom(ByteString.copyFrom(bArr));
            if (parseFrom != null) {
                UiManagerLite iVar = f8148f;
                String controlId = parseFrom.getControlId();
                UiMessage.CommandToUi.Command_Type command = parseFrom.getCommand();
                new StringBuilder("command: ").append(parseFrom);
                int i2 = 3;
                boolean z = true;
                switch (UiManagerLite.C13133.f8537a[command.ordinal()]) {
                    case 1:
                        try {
                            iVar.m20799a(parseFrom.getPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 2:
                        try {
                            try {
                                iVar.m20792b(parseFrom.getPath());
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 3:
                        int width = parseFrom.getWidth();
                        int height = parseFrom.getHeight();
                        iVar.f8527b++;
                        iVar.f8526a[iVar.f8527b] = iVar.f8528c.m20880a(controlId, width, height);
                        iVar.f8529d.put(controlId, Integer.valueOf(iVar.f8527b));
                        iVar.f8530e[iVar.f8527b] = new HashMap();
                        iVar.f8526a[iVar.f8527b].f8579b = new UiManagerLite.View$OnClickListenerC13111(controlId);
                        iVar.f8526a[iVar.f8527b].f8578a = new UiManagerLite.View$OnClickListenerC13122(controlId);
                        UiManagerLite.m20796a(z);
                        return;
                    case 4:
                        if (iVar.f8529d.get(controlId) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                            UiManagerLite.m20796a(false);
                            return;
                        } else if (iVar.f8526a[iVar.f8529d.get(controlId).intValue()].f8580c) {
                            UiManagerLite.m20796a(false);
                            return;
                        } else {
                            iVar.f8526a[iVar.f8529d.get(controlId).intValue()].m20731c();
                            UiManagerLite.m20796a(true);
                            UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                            return;
                        }
                    case 5:
                        UiManagerLite.m20789c(controlId);
                        UiManagerLite.m20796a(z);
                        return;
                    case 6:
                        z = iVar.m20786d(controlId);
                        UiManagerLite.m20796a(z);
                        return;
                    case 7:
                        iVar.m20791c();
                        UiManagerLite.m20796a(z);
                        return;
                    case 8:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiShowLayout kVar = iVar.f8526a[iVar.f8527b];
                            iVar.m20803a();
                            kVar.m20737a(controlId, parseFrom.getParentId(), parseFrom.getWidth(), parseFrom.getHeight());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 9:
                        String text = parseFrom.getText();
                        if (iVar.f8529d.get(controlId) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                            z = false;
                        } else {
                            iVar.f8526a[iVar.f8529d.get(controlId).intValue()].m20739a(text);
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 10:
                        if (iVar.f8529d.get(controlId) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(iVar.f8526a[iVar.f8529d.get(controlId).intValue()].m20747a()).build()).build().toByteString());
                        return;
                    case 11:
                        String controlId2 = parseFrom.getControlId();
                        String color = parseFrom.getColor();
                        if (iVar.f8529d.get(controlId2) == null) {
                            iVar.m20802a(C1375R.string.ui_show_not_found_ui, controlId2);
                            UiManagerLite.m20796a(false);
                            return;
                        } else if (!color.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            iVar.m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                            UiManagerLite.m20796a(false);
                            return;
                        } else {
                            if (!color.startsWith("#")) {
                                color = "#" + color;
                            }
                            iVar.f8526a[iVar.f8529d.get(controlId2).intValue()].m20735b(C1335c.m20658a(Color.parseColor(color)));
                            UiManagerLite.m20796a(true);
                            return;
                        }
                    case 12:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar = iVar.f8528c;
                            iVar.m20803a();
                            LinearLayout b = gVar.m20870b(controlId, parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.LINE);
                            iVar.f8526a[iVar.f8527b].m20741a(b, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 13:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar2 = iVar.f8528c;
                            iVar.m20803a();
                            TextView a = gVar2.m20876a(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.TEXT_VIEW);
                            iVar.f8526a[iVar.f8527b].m20741a(a, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 14:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar3 = iVar.f8528c;
                            iVar.m20803a();
                            EditText d = gVar3.m20864d(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.EDIT_TEXT);
                            iVar.f8526a[iVar.f8527b].m20741a(d, parseFrom.getParentId());
                            d.addTextChangedListener(new UiManagerLite.C1314a(controlId));
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 15:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar4 = iVar.f8528c;
                            iVar.m20803a();
                            Button b2 = gVar4.m20868b(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.BUTTON);
                            b2.setOnClickListener(iVar);
                            iVar.f8526a[iVar.f8527b].m20741a(b2, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 16:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar5 = iVar.f8528c;
                            iVar.m20803a();
                            RadioGroup a2 = gVar5.m20873a(controlId, parseFrom.getItemTextList(), parseFrom.getDefaultItemIndex(), parseFrom.getWidth(), parseFrom.getHeight());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.RADIIO_GROUP);
                            iVar.f8526a[iVar.f8527b].m20741a(a2, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 17:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar6 = iVar.f8528c;
                            iVar.m20803a();
                            CheckBox a3 = gVar6.m20875a(controlId, parseFrom.getText(), parseFrom.getWidth(), parseFrom.getHeight(), parseFrom.getDefaultCheckStatus());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.CHECK_BOX);
                            iVar.f8526a[iVar.f8527b].m20741a(a3, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 18:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            ImageView a4 = iVar.f8528c.m20883a(iVar.m20803a(), controlId, parseFrom.getWidth(), parseFrom.getHeight(), parseFrom.getPath());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.IMAGE_VIEW);
                            a4.setOnClickListener(iVar);
                            iVar.f8526a[iVar.f8527b].m20741a(a4, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 19:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar7 = iVar.f8528c;
                            iVar.m20803a();
                            WebView a5 = gVar7.m20878a(controlId, parseFrom.getWidth(), parseFrom.getHeight(), parseFrom.getUrl());
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.WEB_VIEW);
                            iVar.f8526a[iVar.f8527b].m20741a(a5, parseFrom.getParentId());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 20:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            UiFactory gVar8 = iVar.f8528c;
                            iVar.m20803a();
                            List<String> itemTextList = parseFrom.getItemTextList();
                            int defaultItemIndex = parseFrom.getDefaultItemIndex();
                            parseFrom.getWidth();
                            parseFrom.getHeight();
                            Spinner a6 = gVar8.m20874a(controlId, itemTextList, defaultItemIndex);
                            iVar.f8530e[iVar.f8527b].put(controlId, WidgetType.SPINNER);
                            iVar.f8526a[iVar.f8527b].m20741a(a6, parseFrom.getParentId());
                            a6.setOnItemSelectedListener(iVar);
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 21:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            iVar.f8526a[iVar.f8527b].m20738a(parseFrom.getControlId(), parseFrom.getHeight());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 22:
                        if (iVar.f8526a[iVar.f8527b] != null) {
                            z = iVar.f8526a[iVar.f8527b].m20744a(iVar.m20803a(), parseFrom.getControlId(), parseFrom.getParentId(), parseFrom.getText());
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                        z = iVar.m20801a(parseFrom);
                        UiManagerLite.m20796a(z);
                        return;
                    case 34:
                        if (iVar.f8529d.get(controlId) != null) {
                            UiManagerLite.m20796a(true);
                        } else if (iVar.f8530e[iVar.f8527b].get(controlId) == null) {
                            UiManagerLite.m20796a(false);
                        } else {
                            View b3 = iVar.f8526a[iVar.f8527b].m20732b(controlId);
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b3.getLayoutParams();
                            layoutParams.width = -1;
                            b3.setLayoutParams(layoutParams);
                        }
                        UiManagerLite.m20796a(z);
                        return;
                    case 35:
                        iVar.m20790c(parseFrom);
                        return;
                    case 36:
                        iVar.m20794b(parseFrom);
                        return;
                    case 37:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId3 = parseFrom.getControlId();
                        parseFrom.getText();
                        View b4 = iVar.f8526a[iVar.f8527b].m20732b(controlId3);
                        if (b4 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        } else if (iVar.f8530e[iVar.f8527b].get(controlId3) != WidgetType.EDIT_TEXT) {
                            UiManagerLite.m20796a(false);
                            return;
                        } else {
                            ((EditText) b4).setInputType(parseFrom.getEditInputType());
                            UiManagerLite.m20796a(true);
                            return;
                        }
                    case 38:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId4 = parseFrom.getControlId();
                        if (iVar.f8530e[iVar.f8527b].get(controlId4) == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        switch (UiManagerLite.C13133.f8538b[((WidgetType) iVar.f8530e[iVar.f8527b].get(controlId4)).ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                                str = ((TextView) iVar.f8526a[iVar.f8527b].m20732b(controlId4)).getText().toString();
                                break;
                            case 4:
                                str = ((CheckBox) iVar.f8526a[iVar.f8527b].m20732b(controlId4)).getText().toString();
                                break;
                            case 5:
                                RadioButton radioButton = (RadioButton) ((RadioGroup) iVar.f8526a[iVar.f8527b].m20732b(controlId4)).findViewById(parseFrom.getItemIndex());
                                if (radioButton != null) {
                                    str = radioButton.getText().toString();
                                    break;
                                } else {
                                    UiManagerLite.m20796a(false);
                                    return;
                                }
                            case 6:
                                Spinner spinner = (Spinner) iVar.f8526a[iVar.f8527b].m20732b(controlId4);
                                if (parseFrom.getItemIndex() < spinner.getCount()) {
                                    new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(parseFrom.getItemIndex()));
                                    str = (String) spinner.getItemAtPosition(parseFrom.getItemIndex());
                                    break;
                                } else {
                                    UiManagerLite.m20796a(false);
                                    return;
                                }
                            default:
                                UiManagerLite.m20796a(false);
                                return;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(str).build()).build().toByteString());
                        return;
                    case 39:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId5 = parseFrom.getControlId();
                        String color2 = parseFrom.getColor();
                        if (!color2.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            iVar.m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        if (!color2.startsWith("#")) {
                            color2 = "#" + color2;
                        }
                        int a7 = C1335c.m20658a(Color.parseColor(color2));
                        if (iVar.f8529d.get(controlId5) != null) {
                            iVar.f8526a[iVar.f8529d.get(controlId5).intValue()].m20746a(a7);
                        } else {
                            View b5 = iVar.f8526a[iVar.f8527b].m20732b(controlId5);
                            if (b5 == null) {
                                UiManagerLite.m20796a(false);
                                return;
                            }
                            switch (UiManagerLite.C13133.f8538b[((WidgetType) iVar.f8530e[iVar.f8527b].get(controlId5)).ordinal()]) {
                                case 1:
                                case 2:
                                case 3:
                                    ((TextView) b5).setTextColor(a7);
                                    break;
                                case 4:
                                    ((CheckBox) b5).setTextColor(a7);
                                    break;
                                case 5:
                                    RadioButton radioButton2 = (RadioButton) ((RadioGroup) b5).findViewById(parseFrom.getItemIndex());
                                    if (radioButton2 != null) {
                                        radioButton2.setTextColor(a7);
                                        break;
                                    } else {
                                        UiManagerLite.m20796a(false);
                                        return;
                                    }
                                case 6:
                                    if (parseFrom.getItemIndex() >= ((Spinner) b5).getCount()) {
                                        UiManagerLite.m20796a(false);
                                        return;
                                    }
                                    break;
                                default:
                                    UiManagerLite.m20796a(false);
                                    return;
                            }
                        }
                        UiManagerLite.m20796a(true);
                        return;
                    case 40:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId6 = parseFrom.getControlId();
                        View b6 = iVar.f8526a[iVar.f8527b].m20732b(controlId6);
                        if (b6 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String color3 = parseFrom.getColor();
                        if (!color3.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            iVar.m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        if (!color3.startsWith("#")) {
                            color3 = "#" + color3;
                        }
                        int a8 = C1335c.m20658a(Color.parseColor(color3));
                        if (iVar.f8530e[iVar.f8527b].get(controlId6) == WidgetType.BUTTON) {
                            GradientDrawable gradientDrawable = new GradientDrawable();
                            gradientDrawable.setColor(a8);
                            gradientDrawable.setCornerRadius(iVar.m20795b());
                            if (Build.VERSION.SDK_INT >= 16) {
                                b6.setBackground(gradientDrawable);
                            } else {
                                b6.setBackgroundDrawable(gradientDrawable);
                            }
                            UiManagerLite.m20796a(true);
                            return;
                        }
                        if (iVar.f8530e[iVar.f8527b].get(controlId6) != WidgetType.SPINNER) {
                            b6.setBackgroundColor(a8);
                        }
                        UiManagerLite.m20796a(true);
                        return;
                    case 41:
                        iVar.m20784e(controlId);
                        return;
                    case 42:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b7 = iVar.f8526a[iVar.f8527b].m20732b(controlId);
                        if (b7 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        boolean isEnabled = b7.isEnabled();
                        if (iVar.f8530e[iVar.f8527b].get(controlId) == WidgetType.EDIT_TEXT) {
                            isEnabled = ((EditText) b7).isFocusable();
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(isEnabled).build()).build().toByteString());
                        return;
                    case 43:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b8 = iVar.f8526a[iVar.f8527b].m20732b(controlId);
                        if (b8 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        int visibility = b8.getVisibility();
                        if (visibility == 0) {
                            i2 = 1;
                        } else if (visibility == 4) {
                            i2 = 2;
                        } else if (visibility != 8) {
                            i2 = 1;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i2).build()).build().toByteString());
                        return;
                    case 44:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        String controlId7 = parseFrom.getControlId();
                        if (iVar.f8530e[iVar.f8527b].get(controlId7) == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        switch (UiManagerLite.C13133.f8538b[((WidgetType) iVar.f8530e[iVar.f8527b].get(controlId7)).ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                                i = ((TextView) iVar.f8526a[iVar.f8527b].m20732b(controlId7)).getPaint().getColor();
                                break;
                            case 4:
                                i = ((CheckBox) iVar.f8526a[iVar.f8527b].m20732b(controlId7)).getPaint().getColor();
                                break;
                            case 5:
                                RadioButton radioButton3 = (RadioButton) ((RadioGroup) iVar.f8526a[iVar.f8527b].m20732b(controlId7)).findViewById(parseFrom.getItemIndex());
                                if (radioButton3 != null) {
                                    i = radioButton3.getPaint().getColor();
                                    break;
                                } else {
                                    UiManagerLite.m20796a(false);
                                    return;
                                }
                            default:
                                UiManagerLite.m20796a(false);
                                return;
                        }
                        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.red(i)))).build()).build().toByteString());
                        return;
                    case 45:
                    case 47:
                        UiManagerLite.m20796a(z);
                        return;
                    case 46:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b9 = iVar.f8526a[iVar.f8527b].m20732b(parseFrom.getControlId());
                        if (b9 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) b9.getLayoutParams();
                        layoutParams2.gravity = parseFrom.getAlignType();
                        b9.setLayoutParams(layoutParams2);
                        new StringBuilder("set gravity ok; ").append(layoutParams2);
                        UiManagerLite.m20796a(true);
                        return;
                    case 48:
                        if (iVar.f8526a[iVar.f8527b] == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        View b10 = iVar.f8526a[iVar.f8527b].m20732b(parseFrom.getControlId());
                        if (b10 == null) {
                            UiManagerLite.m20796a(false);
                            return;
                        }
                        UiMessage.Padding_Var padding = parseFrom.getPadding();
                        new StringBuilder("padding: ").append(padding.getLeft());
                        new StringBuilder("padding: ").append(padding.getTop());
                        new StringBuilder("padding: ").append(padding.getRight());
                        new StringBuilder("padding: ").append(padding.getBottom());
                        b10.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                        UiManagerLite.m20796a(true);
                        return;
                    default:
                        UiManagerLite.m20796a(z);
                        return;
                }
            }
        } catch (InvalidProtocolBufferException e5) {
            e5.printStackTrace();
        }
    }
}
