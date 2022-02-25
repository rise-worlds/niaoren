package com.cyjh.mobileanjian.ipc.p044ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.p003v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.C1335c;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.mq.sdk.MqRunner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.p105io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.h */
/* loaded from: classes.dex */
public final class UiManager implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /* renamed from: j */
    private static final String f8499j = "^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})";

    /* renamed from: k */
    private static final int f8500k = 32;

    /* renamed from: l */
    private Context f8510l;

    /* renamed from: m */
    private UiFactory f8511m;

    /* renamed from: o */
    private LinearLayout f8513o;

    /* renamed from: a */
    public UiShowLayout[] f8501a = new UiShowLayout[32];

    /* renamed from: b */
    public int f8502b = 0;

    /* renamed from: n */
    private HashMap<String, Integer> f8512n = new HashMap<>();

    /* renamed from: c */
    public HashMap[] f8503c = new HashMap[32];

    /* renamed from: d */
    public UiShowFloat[] f8504d = new UiShowFloat[32];

    /* renamed from: e */
    public int f8505e = 0;

    /* renamed from: f */
    public HashMap<String, Integer> f8506f = new HashMap<>();

    /* renamed from: g */
    public HashMap<String, WidgetType> f8507g = new HashMap<>();

    /* renamed from: h */
    public HashMap<String, String> f8508h = new HashMap<>();

    /* renamed from: i */
    public int f8509i = 1;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public UiManager(Context context) {
        this.f8510l = context;
        this.f8511m = UiFactory.m20882a(context);
    }

    /* renamed from: b */
    private int m20847b() {
        int i = this.f8509i + 1;
        this.f8509i = i;
        return i;
    }

    /* renamed from: c */
    private int m20841c() {
        return (int) ((this.f8510l.getResources().getDisplayMetrics().densityDpi / 160.0f) * 5.0f);
    }

    /* renamed from: d */
    private void m20837d() {
        this.f8506f.clear();
        this.f8507g.clear();
        this.f8508h.clear();
        m20859a();
        this.f8502b = 0;
        this.f8509i = 1;
        for (int i = 0; i < 32; i++) {
            this.f8501a[i] = null;
            HashMap[] hashMapArr = this.f8503c;
            if (hashMapArr[i] != null) {
                hashMapArr[i].clear();
            }
        }
        this.f8504d = new UiShowFloat[32];
        this.f8505e = 0;
    }

    /* renamed from: a */
    public final void m20857a(UiMessage.CommandToUi commandToUi) {
        String str;
        int i;
        RelativeLayout.LayoutParams layoutParams;
        final String controlId = commandToUi.getControlId();
        UiMessage.CommandToUi.Command_Type command = commandToUi.getCommand();
        new StringBuilder("command: ").append(commandToUi);
        int i2 = 2;
        boolean z = true;
        switch (command) {
            case SAVE_PROFILE:
                try {
                    m20833e(commandToUi.getPath());
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    break;
                }
            case LOAD_PROFILE:
                try {
                    m20831f(commandToUi.getPath());
                    break;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    break;
                } catch (JSONException e4) {
                    e4.printStackTrace();
                    break;
                }
            case NEW_LAYOUT:
                int width = commandToUi.getWidth();
                int height = commandToUi.getHeight();
                this.f8502b++;
                this.f8501a[this.f8502b] = this.f8511m.m20880a(controlId, width, height);
                this.f8512n.put(controlId, Integer.valueOf(this.f8502b));
                this.f8503c[this.f8502b] = new HashMap();
                this.f8501a[this.f8502b].m20734b(new View$OnClickListenerC13072(controlId));
                this.f8501a[this.f8502b].m20742a(new View$OnClickListenerC13083(controlId));
                break;
            case SHOW_LAYOUT:
                if (this.f8512n.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_ui, controlId);
                    m20848a(false);
                    return;
                } else if (this.f8501a[this.f8512n.get(controlId).intValue()].m20727d()) {
                    m20848a(false);
                    return;
                } else {
                    this.f8501a[this.f8512n.get(controlId).intValue()].m20731c();
                    m20848a(true);
                    MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                    return;
                }
            case CLOSE_LAYOUT:
                MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
                break;
            case CLOSE_CONTINUE_ACK:
                z = m20823j(controlId);
                break;
            case CLOSE_EXIT_ACK:
                m20859a();
                break;
            case NEW_ROW:
                UiShowLayout[] kVarArr = this.f8501a;
                int i3 = this.f8502b;
                if (kVarArr[i3] != null) {
                    UiShowLayout kVar = kVarArr[i3];
                    m20847b();
                    kVar.m20737a(controlId, commandToUi.getParentId(), commandToUi.getWidth(), commandToUi.getHeight());
                    break;
                }
                break;
            case SET_TITLE_TEXT:
                String text = commandToUi.getText();
                if (this.f8512n.get(controlId) != null) {
                    this.f8501a[this.f8512n.get(controlId).intValue()].m20739a(text);
                    break;
                } else {
                    m20858a(C1375R.string.ui_show_not_found_ui, controlId);
                    z = false;
                    break;
                }
            case GET_TITLE_TEXT:
                if (this.f8512n.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_ui, controlId);
                    m20848a(false);
                    return;
                }
                MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f8501a[this.f8512n.get(controlId).intValue()].m20747a()).build()).build().toByteString());
                return;
            case SET_TITLE_BACKCOLOR:
                String controlId2 = commandToUi.getControlId();
                String color = commandToUi.getColor();
                if (this.f8512n.get(controlId2) == null) {
                    m20858a(C1375R.string.ui_show_not_found_ui, controlId2);
                    m20848a(false);
                    return;
                } else if (!color.matches(f8499j)) {
                    m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20848a(false);
                    return;
                } else {
                    if (!color.startsWith("#")) {
                        color = "#" + color;
                    }
                    this.f8501a[this.f8512n.get(controlId2).intValue()].m20735b(C1335c.m20658a(Color.parseColor(color)));
                    m20848a(true);
                    return;
                }
            case ADD_LINE:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar = this.f8511m;
                    m20847b();
                    LinearLayout b = gVar.m20870b(controlId, commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.LINE);
                    this.f8501a[this.f8502b].m20741a(b, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_TEXT_VIEW:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar2 = this.f8511m;
                    m20847b();
                    TextView a = gVar2.m20876a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.TEXT_VIEW);
                    this.f8501a[this.f8502b].m20741a(a, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_EDIT_TEXT:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar3 = this.f8511m;
                    m20847b();
                    EditText d = gVar3.m20864d(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.EDIT_TEXT);
                    this.f8501a[this.f8502b].m20741a(d, commandToUi.getParentId());
                    d.addTextChangedListener(new C1310a(controlId));
                    break;
                }
                break;
            case ADD_BUTTON:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar4 = this.f8511m;
                    m20847b();
                    Button b2 = gVar4.m20868b(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.BUTTON);
                    b2.setOnClickListener(this);
                    this.f8501a[this.f8502b].m20741a(b2, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_RADIO_GROUP:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar5 = this.f8511m;
                    m20847b();
                    RadioGroup a2 = gVar5.m20873a(controlId, commandToUi.getItemTextList(), commandToUi.getDefaultItemIndex(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.RADIIO_GROUP);
                    this.f8501a[this.f8502b].m20741a(a2, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_CHECK_BOX:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar6 = this.f8511m;
                    m20847b();
                    CheckBox a3 = gVar6.m20875a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getDefaultCheckStatus());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.CHECK_BOX);
                    this.f8501a[this.f8502b].m20741a(a3, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_IMAGE_VIEW:
                if (this.f8501a[this.f8502b] != null) {
                    ImageView a4 = this.f8511m.m20883a(m20847b(), controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getPath());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.IMAGE_VIEW);
                    a4.setOnClickListener(this);
                    this.f8501a[this.f8502b].m20741a(a4, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_WEB_VIEW:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar7 = this.f8511m;
                    m20847b();
                    WebView a5 = gVar7.m20878a(controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getUrl());
                    this.f8503c[this.f8502b].put(controlId, WidgetType.WEB_VIEW);
                    this.f8501a[this.f8502b].m20741a(a5, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_SPINNER:
                if (this.f8501a[this.f8502b] != null) {
                    UiFactory gVar8 = this.f8511m;
                    m20847b();
                    List<String> itemTextList = commandToUi.getItemTextList();
                    int defaultItemIndex = commandToUi.getDefaultItemIndex();
                    commandToUi.getWidth();
                    commandToUi.getHeight();
                    Spinner a6 = gVar8.m20874a(controlId, itemTextList, defaultItemIndex);
                    this.f8503c[this.f8502b].put(controlId, WidgetType.SPINNER);
                    this.f8501a[this.f8502b].m20741a(a6, commandToUi.getParentId());
                    a6.setOnItemSelectedListener(this);
                    break;
                }
                break;
            case ADD_TAB_HOST:
                UiShowLayout[] kVarArr2 = this.f8501a;
                int i4 = this.f8502b;
                if (kVarArr2[i4] != null) {
                    kVarArr2[i4].m20738a(commandToUi.getControlId(), commandToUi.getHeight());
                    break;
                }
                break;
            case ADD_TAB:
                UiShowLayout[] kVarArr3 = this.f8501a;
                int i5 = this.f8502b;
                if (kVarArr3[i5] != null) {
                    z = kVarArr3[i5].m20744a(m20847b(), commandToUi.getControlId(), commandToUi.getParentId(), commandToUi.getText());
                    break;
                }
                break;
            case SET_TEXT_VIEW:
            case SET_EDIT_TEXT:
            case SET_BOTTON:
            case SET_CHECK_BOX:
            case SET_IMAGE_VIEW:
            case SET_WEB_VIEW:
            case SET_RADIO_GROUP:
            case SET_SPINNER:
            case SET_ENABLED:
            case SET_LINE:
            case SET_VISIBLE:
                z = m20846b(commandToUi);
                break;
            case SET_FULLSCREEN:
                if (this.f8512n.get(controlId) == null) {
                    if (this.f8503c[this.f8502b].get(controlId) != null) {
                        View b3 = this.f8501a[this.f8502b].m20732b(controlId);
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) b3.getLayoutParams();
                        layoutParams2.width = -1;
                        b3.setLayoutParams(layoutParams2);
                        break;
                    } else {
                        m20848a(false);
                        break;
                    }
                } else {
                    m20848a(true);
                    break;
                }
            case SET_TEXT:
                m20834e(commandToUi);
                return;
            case SET_FONT:
                m20840c(commandToUi);
                return;
            case SET_EDIT_INPUT_TYPE:
                if (this.f8501a[this.f8502b] == null) {
                    m20848a(false);
                    return;
                }
                String controlId3 = commandToUi.getControlId();
                commandToUi.getText();
                View b4 = this.f8501a[this.f8502b].m20732b(controlId3);
                if (b4 == null) {
                    m20848a(false);
                    return;
                } else if (this.f8503c[this.f8502b].get(controlId3) != WidgetType.EDIT_TEXT) {
                    m20848a(false);
                    return;
                } else {
                    ((EditText) b4).setInputType(commandToUi.getEditInputType());
                    m20848a(true);
                    return;
                }
            case GET_TEXT:
                if (this.f8501a[this.f8502b] == null) {
                    m20848a(false);
                    return;
                }
                String controlId4 = commandToUi.getControlId();
                if (this.f8503c[this.f8502b].get(controlId4) == null) {
                    m20848a(false);
                    return;
                }
                switch ((WidgetType) this.f8503c[this.f8502b].get(controlId4)) {
                    case TEXT_VIEW:
                    case EDIT_TEXT:
                    case BUTTON:
                        str = ((TextView) this.f8501a[this.f8502b].m20732b(controlId4)).getText().toString();
                        break;
                    case CHECK_BOX:
                        str = ((CheckBox) this.f8501a[this.f8502b].m20732b(controlId4)).getText().toString();
                        break;
                    case RADIIO_GROUP:
                        RadioButton radioButton = (RadioButton) ((RadioGroup) this.f8501a[this.f8502b].m20732b(controlId4)).findViewById(commandToUi.getItemIndex());
                        if (radioButton != null) {
                            str = radioButton.getText().toString();
                            break;
                        } else {
                            m20848a(false);
                            return;
                        }
                    case SPINNER:
                        Spinner spinner = (Spinner) this.f8501a[this.f8502b].m20732b(controlId4);
                        if (commandToUi.getItemIndex() < spinner.getCount()) {
                            new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                            str = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                            break;
                        } else {
                            m20848a(false);
                            return;
                        }
                    default:
                        m20848a(false);
                        return;
                }
                MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(str).build()).build().toByteString());
                return;
            case SET_TEXTCOLOR:
                if (this.f8501a[this.f8502b] == null) {
                    m20848a(false);
                    return;
                }
                String controlId5 = commandToUi.getControlId();
                String color2 = commandToUi.getColor();
                if (!color2.matches(f8499j)) {
                    m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20848a(false);
                    return;
                }
                if (!color2.startsWith("#")) {
                    color2 = "#" + color2;
                }
                int a7 = C1335c.m20658a(Color.parseColor(color2));
                if (this.f8512n.get(controlId5) != null) {
                    this.f8501a[this.f8512n.get(controlId5).intValue()].m20746a(a7);
                } else {
                    View b5 = this.f8501a[this.f8502b].m20732b(controlId5);
                    if (b5 == null) {
                        m20848a(false);
                        return;
                    }
                    switch ((WidgetType) this.f8503c[this.f8502b].get(controlId5)) {
                        case TEXT_VIEW:
                        case EDIT_TEXT:
                        case BUTTON:
                            ((TextView) b5).setTextColor(a7);
                            break;
                        case CHECK_BOX:
                            ((CheckBox) b5).setTextColor(a7);
                            break;
                        case RADIIO_GROUP:
                            RadioButton radioButton2 = (RadioButton) ((RadioGroup) b5).findViewById(commandToUi.getItemIndex());
                            if (radioButton2 != null) {
                                radioButton2.setTextColor(a7);
                                break;
                            } else {
                                m20848a(false);
                                return;
                            }
                        case SPINNER:
                            if (commandToUi.getItemIndex() >= ((Spinner) b5).getCount()) {
                                m20848a(false);
                                return;
                            }
                            break;
                        default:
                            m20848a(false);
                            return;
                    }
                }
                m20848a(true);
                return;
            case SET_BACKCOLOR:
                if (this.f8501a[this.f8502b] == null) {
                    m20848a(false);
                    return;
                }
                String controlId6 = commandToUi.getControlId();
                View b6 = this.f8501a[this.f8502b].m20732b(controlId6);
                if (b6 == null) {
                    m20848a(false);
                    return;
                }
                String color3 = commandToUi.getColor();
                if (!color3.matches(f8499j)) {
                    m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20848a(false);
                    return;
                }
                if (!color3.startsWith("#")) {
                    color3 = "#" + color3;
                }
                int a8 = C1335c.m20658a(Color.parseColor(color3));
                if (this.f8503c[this.f8502b].get(controlId6) == WidgetType.BUTTON) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(a8);
                    gradientDrawable.setCornerRadius(m20841c());
                    b6.setBackground(gradientDrawable);
                } else if (this.f8503c[this.f8502b].get(controlId6) != WidgetType.SPINNER) {
                    b6.setBackgroundColor(a8);
                }
                m20848a(true);
                return;
            case GET_VALUE:
                m20821k(controlId);
                return;
            case GET_ENABLED:
                UiShowLayout[] kVarArr4 = this.f8501a;
                int i6 = this.f8502b;
                if (kVarArr4[i6] == null) {
                    m20848a(false);
                    return;
                }
                View b7 = kVarArr4[i6].m20732b(controlId);
                if (b7 == null) {
                    m20848a(false);
                    return;
                }
                boolean isEnabled = b7.isEnabled();
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.EDIT_TEXT) {
                    isEnabled = ((EditText) b7).isFocusable();
                }
                MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(isEnabled).build()).build().toByteString());
                return;
            case GET_VISIBLE:
                UiShowLayout[] kVarArr5 = this.f8501a;
                int i7 = this.f8502b;
                if (kVarArr5[i7] == null) {
                    m20848a(false);
                    return;
                }
                View b8 = kVarArr5[i7].m20732b(controlId);
                if (b8 == null) {
                    m20848a(false);
                    return;
                }
                int visibility = b8.getVisibility();
                if (visibility == 0) {
                    i2 = 1;
                } else if (visibility != 4) {
                    i2 = visibility != 8 ? 1 : 3;
                }
                MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i2).build()).build().toByteString());
                return;
            case GET_TEXTCOLOR:
                if (this.f8501a[this.f8502b] == null) {
                    m20848a(false);
                    return;
                }
                String controlId7 = commandToUi.getControlId();
                if (this.f8503c[this.f8502b].get(controlId7) == null) {
                    m20848a(false);
                    return;
                }
                switch ((WidgetType) this.f8503c[this.f8502b].get(controlId7)) {
                    case TEXT_VIEW:
                    case EDIT_TEXT:
                    case BUTTON:
                        i = ((TextView) this.f8501a[this.f8502b].m20732b(controlId7)).getPaint().getColor();
                        break;
                    case CHECK_BOX:
                        i = ((CheckBox) this.f8501a[this.f8502b].m20732b(controlId7)).getPaint().getColor();
                        break;
                    case RADIIO_GROUP:
                        RadioButton radioButton3 = (RadioButton) ((RadioGroup) this.f8501a[this.f8502b].m20732b(controlId7)).findViewById(commandToUi.getItemIndex());
                        if (radioButton3 != null) {
                            i = radioButton3.getPaint().getColor();
                            break;
                        } else {
                            m20848a(false);
                            return;
                        }
                    default:
                        m20848a(false);
                        return;
                }
                MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.red(i)))).build()).build().toByteString());
                return;
            case SET_CONTROL_GRAVITY:
                if (this.f8501a[this.f8502b] == null) {
                    m20848a(false);
                    return;
                }
                View b9 = this.f8501a[this.f8502b].m20732b(commandToUi.getControlId());
                if (b9 == null) {
                    m20848a(false);
                    return;
                }
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) b9.getLayoutParams();
                layoutParams3.gravity = commandToUi.getAlignType();
                b9.setLayoutParams(layoutParams3);
                new StringBuilder("set gravity ok; ").append(layoutParams3);
                m20848a(true);
                return;
            case SET_PADDING:
                if (this.f8501a[this.f8502b] == null) {
                    m20848a(false);
                    return;
                }
                View b10 = this.f8501a[this.f8502b].m20732b(commandToUi.getControlId());
                if (b10 == null) {
                    m20848a(false);
                    return;
                }
                UiMessage.Padding_Var padding = commandToUi.getPadding();
                new StringBuilder("padding: ").append(padding.getLeft());
                new StringBuilder("padding: ").append(padding.getTop());
                new StringBuilder("padding: ").append(padding.getRight());
                new StringBuilder("padding: ").append(padding.getBottom());
                b10.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                m20848a(true);
                return;
            case NEW_FLOAT:
                m20852a(controlId, commandToUi.getLeft(), commandToUi.getTop(), commandToUi.getWidth(), commandToUi.getHeight());
                break;
            case SHOW_FLOAT:
                if (this.f8506f.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_float, controlId);
                    m20848a(false);
                    return;
                } else if (this.f8504d[this.f8506f.get(controlId).intValue()].m20752d()) {
                    m20848a(false);
                    return;
                } else {
                    this.f8504d[this.f8506f.get(controlId).intValue()].m20769a();
                    m20848a(true);
                    m20851a(controlId, UiMessage.ControlEvent.Event_Type.ON_SHOW);
                    return;
                }
            case IS_FW_TOUCHMOVE:
                boolean boolParam = commandToUi.getBoolParam();
                if (this.f8506f.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_float, controlId);
                    m20848a(false);
                    return;
                }
                this.f8504d[this.f8506f.get(controlId).intValue()].f8545e = boolParam;
                m20848a(true);
                return;
            case HIDE_FLOAT:
                if (this.f8506f.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_float, controlId);
                    m20848a(false);
                    return;
                } else if (!this.f8504d[this.f8506f.get(controlId).intValue()].m20752d()) {
                    m20848a(false);
                    return;
                } else {
                    this.f8504d[this.f8506f.get(controlId).intValue()].m20761b();
                    m20848a(true);
                    return;
                }
            case CLOSE_FLOAT:
                if (this.f8506f.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_float, controlId);
                    m20848a(false);
                    return;
                }
                this.f8504d[this.f8506f.get(controlId).intValue()].m20755c();
                this.f8506f.remove(controlId);
                m20851a(controlId, UiMessage.ControlEvent.Event_Type.ON_CLOSE);
                m20848a(true);
                return;
            case FW_OPACITY:
                int intParam = commandToUi.getIntParam();
                if (this.f8506f.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_float, controlId);
                    m20848a(false);
                    return;
                }
                this.f8504d[this.f8506f.get(controlId).intValue()].setOpacity(intParam);
                m20848a(true);
                return;
            case SET_FW_SMOOTH:
                int intParam2 = commandToUi.getIntParam();
                if (this.f8506f.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_float, controlId);
                    m20848a(false);
                    return;
                }
                this.f8504d[this.f8506f.get(controlId).intValue()].m20768a(intParam2);
                this.f8504d[this.f8506f.get(controlId).intValue()].m20750e();
                m20848a(true);
                return;
            case SET_FW_BG:
                String strParam = commandToUi.getStrParam();
                if (!strParam.matches(f8499j)) {
                    m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20848a(false);
                    return;
                }
                if (!strParam.startsWith("#")) {
                    strParam = "#" + strParam;
                }
                int a9 = C1335c.m20658a(Color.parseColor(strParam));
                if (this.f8506f.get(controlId) != null) {
                    this.f8504d[this.f8506f.get(controlId).intValue()].m20760b(a9);
                    this.f8504d[this.f8506f.get(controlId).intValue()].m20750e();
                } else {
                    String str2 = this.f8508h.get(controlId);
                    if (str2 == null) {
                        m20848a(false);
                        return;
                    }
                    View a10 = this.f8504d[this.f8506f.get(str2).intValue()].m20764a(controlId);
                    if (a10 == null) {
                        m20848a(false);
                        return;
                    } else if (this.f8507g.get(controlId) == WidgetType.BUTTON) {
                        GradientDrawable gradientDrawable2 = new GradientDrawable();
                        gradientDrawable2.setCornerRadii(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f});
                        gradientDrawable2.setColor(a9);
                        a10.setBackground(gradientDrawable2);
                    } else if (this.f8507g.get(controlId) != WidgetType.SPINNER) {
                        a10.setBackgroundColor(a9);
                    }
                }
                m20848a(true);
                return;
            case SET_FW_PIC:
                String strParam2 = commandToUi.getStrParam();
                if (this.f8506f.get(controlId) == null) {
                    String str3 = this.f8508h.get(controlId);
                    if (str3 != null) {
                        View a11 = this.f8504d[this.f8506f.get(str3).intValue()].m20764a(controlId);
                        if (a11 != null) {
                            if (this.f8507g.get(controlId) == WidgetType.IMAGE_VIEW) {
                                ((ImageView) a11).setImageURI(Uri.fromFile(new File(strParam2)));
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                } else {
                    UiShowFloat jVar = this.f8504d[this.f8506f.get(controlId).intValue()];
                    jVar.f8549i = strParam2;
                    Bitmap decodeFile = BitmapFactory.decodeFile(jVar.f8549i);
                    if (Build.VERSION.SDK_INT >= 16) {
                        jVar.f8543c.setBackground(new BitmapDrawable(decodeFile));
                    }
                    jVar.f8543c.getBackground().setAlpha((jVar.f8548h * 255) / 100);
                    this.f8504d[this.f8506f.get(controlId).intValue()].m20750e();
                    break;
                }
                break;
            case ADD_FW_BUTTON:
                String parentId = commandToUi.getParentId();
                if (this.f8504d[this.f8506f.get(parentId).intValue()] != null) {
                    UiFactory gVar9 = this.f8511m;
                    m20847b();
                    String text2 = commandToUi.getText();
                    int width2 = commandToUi.getWidth();
                    int height2 = commandToUi.getHeight();
                    commandToUi.getLeft();
                    commandToUi.getTop();
                    Button button = (Button) gVar9.f8496a.inflate(C1375R.layout.ui_float_button, (ViewGroup) null);
                    button.setTag(controlId);
                    button.setText(text2);
                    button.setAllCaps(false);
                    if (width2 == 0 && height2 == 0) {
                        layoutParams = new RelativeLayout.LayoutParams(-1, 100);
                    } else {
                        layoutParams = new RelativeLayout.LayoutParams(width2, height2);
                    }
                    button.setLayoutParams(layoutParams);
                    this.f8507g.put(controlId, WidgetType.BUTTON);
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.h.1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UiManager.m20851a(controlId, UiMessage.ControlEvent.Event_Type.ON_CLICK);
                        }
                    });
                    this.f8504d[this.f8506f.get(parentId).intValue()].m20766a(button);
                    this.f8508h.put(controlId, parentId);
                    button.setX(commandToUi.getLeft());
                    button.setY(commandToUi.getTop());
                    break;
                }
                break;
            case ADD_FW_TEXT_VIEW:
                String parentId2 = commandToUi.getParentId();
                if (this.f8504d[this.f8506f.get(parentId2).intValue()] != null) {
                    UiFactory gVar10 = this.f8511m;
                    m20847b();
                    String text3 = commandToUi.getText();
                    int width3 = commandToUi.getWidth();
                    int height3 = commandToUi.getHeight();
                    commandToUi.getLeft();
                    commandToUi.getTop();
                    TextView textView = (TextView) gVar10.f8496a.inflate(C1375R.layout.ui_float_text_view, (ViewGroup) null);
                    textView.setTag(controlId);
                    textView.setText(text3);
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                    if (!(width3 == -2 || height3 == -2)) {
                        layoutParams4.width = width3;
                        layoutParams4.height = height3;
                    }
                    textView.setLayoutParams(layoutParams4);
                    this.f8507g.put(controlId, WidgetType.TEXT_VIEW);
                    this.f8504d[this.f8506f.get(parentId2).intValue()].m20766a(textView);
                    this.f8508h.put(controlId, parentId2);
                    textView.setX(commandToUi.getLeft());
                    textView.setY(commandToUi.getTop());
                    break;
                }
                break;
            case ADD_FW_IMAGE_VIEW:
                String parentId3 = commandToUi.getParentId();
                if (this.f8504d[this.f8506f.get(parentId3).intValue()] != null) {
                    UiFactory gVar11 = this.f8511m;
                    m20847b();
                    String text4 = commandToUi.getText();
                    int width4 = commandToUi.getWidth();
                    int height4 = commandToUi.getHeight();
                    commandToUi.getLeft();
                    commandToUi.getTop();
                    ImageView c = gVar11.m20865c(controlId, text4, width4, height4);
                    this.f8507g.put(controlId, WidgetType.IMAGE_VIEW);
                    this.f8504d[this.f8506f.get(parentId3).intValue()].m20766a(c);
                    this.f8508h.put(controlId, parentId3);
                    c.setX(commandToUi.getLeft());
                    c.setY(commandToUi.getTop());
                    break;
                }
                break;
            case FW_ZORDER:
                if (this.f8506f.get(controlId) == null) {
                    m20858a(C1375R.string.ui_show_not_found_float, controlId);
                    m20848a(false);
                    return;
                }
                if (this.f8504d[this.f8506f.get(controlId).intValue()].m20752d()) {
                    UiShowFloat jVar2 = this.f8504d[this.f8506f.get(controlId).intValue()];
                    if (jVar2.f8543c != null && jVar2.m20752d()) {
                        jVar2.f8541a.removeView(jVar2.f8543c);
                        jVar2.f8541a.addView(jVar2.f8543c, jVar2.f8542b);
                    }
                }
                m20848a(true);
                return;
            case SET_FW_TEXT_COLOR:
                String controlId8 = commandToUi.getControlId();
                String strParam3 = commandToUi.getStrParam();
                if (!strParam3.matches(f8499j)) {
                    m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20848a(false);
                    return;
                }
                if (!strParam3.startsWith("#")) {
                    strParam3 = "#" + strParam3;
                }
                int a12 = C1335c.m20658a(Color.parseColor(strParam3));
                String str4 = this.f8508h.get(controlId8);
                if (str4 == null) {
                    m20848a(false);
                    return;
                }
                View a13 = this.f8504d[this.f8506f.get(str4).intValue()].m20764a(controlId8);
                if (a13 == null) {
                    m20848a(false);
                    return;
                }
                if (this.f8507g.get(controlId8) == WidgetType.BUTTON) {
                    ((Button) a13).setTextColor(a12);
                }
                if (this.f8507g.get(controlId8) == WidgetType.TEXT_VIEW) {
                    ((TextView) a13).setTextColor(a12);
                }
                this.f8504d[this.f8506f.get(str4).intValue()].m20750e();
                m20848a(true);
                return;
            case FW_GET_VALUE:
                JSONObject jSONObject = new JSONObject();
                if (this.f8506f.get(controlId) != null) {
                    try {
                        WindowManager.LayoutParams params = this.f8504d[this.f8506f.get(controlId).intValue()].getParams();
                        jSONObject.put("Left", params.x);
                        jSONObject.put("Top", params.y);
                        jSONObject.put("Width", params.width);
                        jSONObject.put("Height", params.height);
                    } catch (JSONException e5) {
                        e5.printStackTrace();
                    }
                } else {
                    String str5 = this.f8508h.get(controlId);
                    WidgetType mVar = this.f8507g.get(controlId);
                    if (mVar == null) {
                        m20858a(C1375R.string.ui_show_not_found_widget, controlId);
                        MqRunner.getInstance().mo20413a(jSONObject.toString());
                        break;
                    } else {
                        int i8 = C13094.f8521b[mVar.ordinal()];
                        if (i8 == 1) {
                            TextView textView2 = (TextView) this.f8504d[this.f8506f.get(str5).intValue()].m20764a(controlId);
                            try {
                                jSONObject.put("Left", textView2.getLeft());
                                jSONObject.put("Top", textView2.getTop());
                                jSONObject.put("Text", textView2.getText());
                                jSONObject.put("Width", textView2.getWidth());
                                jSONObject.put("Height", textView2.getHeight());
                            } catch (JSONException e6) {
                                e6.printStackTrace();
                            }
                        } else if (i8 == 3) {
                            Button button2 = (Button) this.f8504d[this.f8506f.get(str5).intValue()].m20764a(controlId);
                            try {
                                jSONObject.put("Left", button2.getLeft());
                                jSONObject.put("Top", button2.getTop());
                                jSONObject.put("Text", button2.getText());
                                jSONObject.put("Width", button2.getWidth());
                                jSONObject.put("Height", button2.getHeight());
                            } catch (JSONException e7) {
                                e7.printStackTrace();
                            }
                        } else if (i8 == 7) {
                            ImageView imageView = (ImageView) this.f8504d[this.f8506f.get(str5).intValue()].m20764a(controlId);
                            try {
                                jSONObject.put("Left", imageView.getLeft());
                                jSONObject.put("Top", imageView.getTop());
                                jSONObject.put("Width", imageView.getWidth());
                                jSONObject.put("Height", imageView.getHeight());
                            } catch (JSONException e8) {
                                e8.printStackTrace();
                            }
                        }
                    }
                }
                new StringBuilder("FWGetValue json: ").append(jSONObject.toString());
                MqRunner.getInstance().mo20413a(jSONObject.toString());
                break;
            case SET_FW_BUTTON:
            case SET_FW_TEXT_VIEW:
            case SET_FW_IMAGE_VIEW:
                m20816n(commandToUi);
                return;
            case SET_FW_TEXT_SIZE:
                z = m20814o(commandToUi);
                break;
            case SET_FW_ENABLE:
            case SET_FW_VISIBLE:
                z = m20812p(commandToUi);
                break;
            case GET_FW_ENABLE:
            case GET_FW_TEXT_COLOR:
            case GET_FW_VISIBLE:
                z = m20810q(commandToUi);
                break;
            case FW_SET_LEFT:
                String controlId9 = commandToUi.getControlId();
                if (this.f8506f.get(controlId9) == null) {
                    String str6 = this.f8508h.get(controlId9);
                    if (str6 != null) {
                        View a14 = this.f8504d[this.f8506f.get(str6).intValue()].m20764a(controlId9);
                        if (a14 != null) {
                            if (this.f8507g.get(controlId9) == WidgetType.BUTTON || this.f8507g.get(controlId9) == WidgetType.TEXT_VIEW || this.f8507g.get(controlId9) == WidgetType.IMAGE_VIEW) {
                                new StringBuilder("view.setX ").append(commandToUi.getIntParam());
                                a14.setX(commandToUi.getIntParam());
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                } else {
                    this.f8504d[this.f8506f.get(controlId9).intValue()].setFloatLeft(commandToUi.getIntParam());
                    break;
                }
                break;
            case FW_SET_TOP:
                z = m20808s(commandToUi);
                break;
            case FW_SET_WIDTH:
                z = m20807t(commandToUi);
                break;
            case FW_SET_HEIGHT:
                z = m20806u(commandToUi);
                break;
            case FW_SET_TEXT:
                z = m20805v(commandToUi);
                break;
        }
        m20848a(z);
    }

    /* renamed from: a */
    private static void m20848a(boolean z) {
        MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(z).build().toByteString());
    }

    /* renamed from: b */
    private boolean m20846b(UiMessage.CommandToUi commandToUi) {
        int i = 0;
        if (this.f8501a[this.f8502b] == null) {
            return false;
        }
        String controlId = commandToUi.getControlId();
        View b = this.f8501a[this.f8502b].m20732b(controlId);
        if (b == null) {
            m20858a(C1375R.string.ui_show_not_found_widget, controlId);
            return false;
        }
        switch (commandToUi.getCommand()) {
            case SET_TEXT_VIEW:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.TEXT_VIEW) {
                    TextView textView = (TextView) b;
                    textView.setText(commandToUi.getText());
                    textView.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_EDIT_TEXT:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.EDIT_TEXT) {
                    EditText editText = (EditText) b;
                    editText.setText(commandToUi.getText());
                    editText.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_BOTTON:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.BUTTON) {
                    Button button = (Button) b;
                    button.setText(commandToUi.getText());
                    button.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_CHECK_BOX:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.CHECK_BOX) {
                    CheckBox checkBox = (CheckBox) b;
                    checkBox.setChecked(commandToUi.getDefaultCheckStatus());
                    checkBox.setText(commandToUi.getText());
                    checkBox.getLayoutParams().width = commandToUi.getWidth();
                    checkBox.getLayoutParams().height = commandToUi.getHeight();
                    break;
                } else {
                    return false;
                }
            case SET_IMAGE_VIEW:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.IMAGE_VIEW) {
                    ImageView imageView = (ImageView) b;
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b.getLayoutParams();
                    layoutParams.width = commandToUi.getWidth();
                    layoutParams.height = commandToUi.getHeight();
                    imageView.setLayoutParams(layoutParams);
                    imageView.setImageURI(Uri.fromFile(new File(commandToUi.getPath())));
                    break;
                } else {
                    return false;
                }
            case SET_WEB_VIEW:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.WEB_VIEW) {
                    String url = commandToUi.getUrl();
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://" + url;
                    }
                    WebView webView = (WebView) b;
                    webView.loadUrl(url);
                    webView.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_RADIO_GROUP:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.RADIIO_GROUP) {
                    RadioGroup radioGroup = (RadioGroup) b;
                    radioGroup.clearCheck();
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) b.getLayoutParams();
                    layoutParams2.weight = commandToUi.getWidth();
                    layoutParams2.height = commandToUi.getHeight();
                    radioGroup.setLayoutParams(layoutParams2);
                    radioGroup.removeAllViews();
                    List<String> itemTextList = commandToUi.getItemTextList();
                    for (int i2 = 0; i2 < itemTextList.size(); i2++) {
                        RadioButton radioButton = (RadioButton) LayoutInflater.from(this.f8510l).inflate(C1375R.layout.ui_show_radio_button, (ViewGroup) null);
                        radioButton.setId(i2);
                        radioButton.setText(itemTextList.get(i2));
                        radioButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        radioButton.setPadding(0, m20841c(), 0, m20841c());
                        radioGroup.addView(radioButton);
                    }
                    if (commandToUi.getDefaultItemIndex() < radioGroup.getChildCount()) {
                        i = commandToUi.getDefaultItemIndex();
                    }
                    ((RadioButton) radioGroup.findViewById(i)).setChecked(true);
                    break;
                } else {
                    return false;
                }
            case SET_SPINNER:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.SPINNER) {
                    Spinner spinner = (Spinner) b;
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this.f8510l, C1375R.layout.ui_show_spinner_dropdown_item, commandToUi.getItemTextList());
                    spinner.setAdapter((SpinnerAdapter) arrayAdapter);
                    if (commandToUi.getDefaultItemIndex() < arrayAdapter.getCount()) {
                        i = commandToUi.getDefaultItemIndex();
                    }
                    spinner.setSelection(i);
                    break;
                } else {
                    return false;
                }
            case SET_ENABLED:
                if (this.f8503c[this.f8502b].get(controlId) != WidgetType.RADIIO_GROUP) {
                    if (this.f8503c[this.f8502b].get(controlId) == WidgetType.EDIT_TEXT) {
                        new StringBuilder("set edittext enabled. ").append(commandToUi.getEnabledStatus());
                        EditText editText2 = (EditText) b;
                        editText2.setFocusable(commandToUi.getEnabledStatus());
                        editText2.setFocusableInTouchMode(commandToUi.getEnabledStatus());
                        break;
                    } else {
                        b.setEnabled(commandToUi.getEnabledStatus());
                        break;
                    }
                } else {
                    b.setEnabled(commandToUi.getEnabledStatus());
                    RadioGroup radioGroup2 = (RadioGroup) b;
                    while (i < radioGroup2.getChildCount()) {
                        radioGroup2.getChildAt(i).setEnabled(commandToUi.getEnabledStatus());
                        i++;
                    }
                    break;
                }
            case SET_LINE:
                if (this.f8503c[this.f8502b].get(controlId) == WidgetType.LINE) {
                    ((LinearLayout) b).setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_VISIBLE:
                switch (commandToUi.getVisibleStatus()) {
                    case 2:
                        b.setVisibility(4);
                        break;
                    case 3:
                        b.setVisibility(8);
                        break;
                    default:
                        b.setVisibility(0);
                        break;
                }
        }
        return true;
    }

    /* renamed from: a */
    private void m20855a(String str) {
        if (this.f8512n.get(str) != null) {
            m20848a(true);
        } else if (this.f8503c[this.f8502b].get(str) == null) {
            m20848a(false);
        } else {
            View b = this.f8501a[this.f8502b].m20732b(str);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b.getLayoutParams();
            layoutParams.width = -1;
            b.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: c */
    private void m20840c(UiMessage.CommandToUi commandToUi) {
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        new StringBuilder("SET FONT type: ").append(commandToUi.getFontType());
        String controlId = commandToUi.getControlId();
        Typeface create = Typeface.create(commandToUi.getFontType(), 0);
        if (create == null) {
            m20848a(false);
            return;
        }
        int fontSize = commandToUi.getFontSize() < 0 ? 14 : commandToUi.getFontSize();
        if (this.f8512n.get(controlId) == null || this.f8501a[this.f8512n.get(controlId).intValue()] == null) {
            View b = this.f8501a[this.f8502b].m20732b(controlId);
            if (b == null) {
                m20848a(false);
            } else if (this.f8503c[this.f8502b].get(controlId) == null) {
                m20848a(false);
            } else {
                switch ((WidgetType) this.f8503c[this.f8502b].get(controlId)) {
                    case TEXT_VIEW:
                    case EDIT_TEXT:
                    case BUTTON:
                        TextView textView = (TextView) b;
                        textView.setTypeface(create);
                        textView.setTextSize(2, fontSize);
                        break;
                    case CHECK_BOX:
                        CheckBox checkBox = (CheckBox) b;
                        checkBox.setTypeface(create);
                        checkBox.setTextSize(2, fontSize);
                        break;
                    case RADIIO_GROUP:
                        RadioGroup radioGroup = (RadioGroup) b;
                        radioGroup.findViewById(commandToUi.getItemIndex());
                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
                            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                            radioButton.setTypeface(create);
                            radioButton.setTextSize(2, fontSize);
                        }
                        break;
                    default:
                        m20848a(false);
                        return;
                }
                m20848a(true);
            }
        } else {
            this.f8501a[this.f8512n.get(controlId).intValue()].m20743a(create, fontSize);
            m20848a(true);
        }
    }

    /* renamed from: d */
    private void m20836d(UiMessage.CommandToUi commandToUi) {
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        commandToUi.getText();
        View b = this.f8501a[this.f8502b].m20732b(controlId);
        if (b == null) {
            m20848a(false);
        } else if (this.f8503c[this.f8502b].get(controlId) != WidgetType.EDIT_TEXT) {
            m20848a(false);
        } else {
            ((EditText) b).setInputType(commandToUi.getEditInputType());
            m20848a(true);
        }
    }

    /* renamed from: e */
    private void m20834e(UiMessage.CommandToUi commandToUi) {
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String text = commandToUi.getText();
        View b = this.f8501a[this.f8502b].m20732b(controlId);
        if (b == null) {
            m20848a(false);
        } else if (this.f8503c[this.f8502b].get(controlId) == null) {
            m20848a(false);
        } else {
            switch ((WidgetType) this.f8503c[this.f8502b].get(controlId)) {
                case TEXT_VIEW:
                case EDIT_TEXT:
                case BUTTON:
                    ((TextView) b).setText(text);
                    break;
                case CHECK_BOX:
                    ((CheckBox) b).setText(text);
                    break;
                case RADIIO_GROUP:
                    RadioButton radioButton = (RadioButton) ((RadioGroup) b).findViewById(commandToUi.getItemIndex());
                    if (radioButton != null) {
                        radioButton.setText(text);
                        break;
                    } else {
                        m20848a(false);
                        return;
                    }
                case SPINNER:
                    Spinner spinner = (Spinner) b;
                    if (commandToUi.getItemIndex() < spinner.getCount()) {
                        ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < arrayAdapter.getCount(); i++) {
                            arrayList.add(arrayAdapter.getItem(i));
                        }
                        arrayList.set(commandToUi.getItemIndex(), text);
                        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this.f8510l, C1375R.layout.ui_show_spinner_dropdown_item, arrayList);
                        spinner.setAdapter((SpinnerAdapter) arrayAdapter2);
                        arrayAdapter2.notifyDataSetChanged();
                        break;
                    } else {
                        m20848a(false);
                        return;
                    }
                default:
                    m20848a(false);
                    return;
            }
            m20848a(true);
        }
    }

    /* renamed from: f */
    private void m20832f(UiMessage.CommandToUi commandToUi) {
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (!color.matches(f8499j)) {
            m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20848a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int a = C1335c.m20658a(Color.parseColor(color));
        if (this.f8512n.get(controlId) != null) {
            this.f8501a[this.f8512n.get(controlId).intValue()].m20746a(a);
            m20848a(true);
            return;
        }
        View b = this.f8501a[this.f8502b].m20732b(controlId);
        if (b == null) {
            m20848a(false);
            return;
        }
        switch ((WidgetType) this.f8503c[this.f8502b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                ((TextView) b).setTextColor(a);
                break;
            case CHECK_BOX:
                ((CheckBox) b).setTextColor(a);
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) b).findViewById(commandToUi.getItemIndex());
                if (radioButton != null) {
                    radioButton.setTextColor(a);
                    break;
                } else {
                    m20848a(false);
                    return;
                }
            case SPINNER:
                if (commandToUi.getItemIndex() >= ((Spinner) b).getCount()) {
                    m20848a(false);
                    return;
                }
                break;
            default:
                m20848a(false);
                return;
        }
        m20848a(true);
    }

    /* renamed from: g */
    private void m20830g(UiMessage.CommandToUi commandToUi) {
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        View b = this.f8501a[this.f8502b].m20732b(controlId);
        if (b == null) {
            m20848a(false);
            return;
        }
        String color = commandToUi.getColor();
        if (!color.matches(f8499j)) {
            m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20848a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int a = C1335c.m20658a(Color.parseColor(color));
        if (this.f8503c[this.f8502b].get(controlId) == WidgetType.BUTTON) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(a);
            gradientDrawable.setCornerRadius(m20841c());
            b.setBackground(gradientDrawable);
            m20848a(true);
            return;
        }
        if (this.f8503c[this.f8502b].get(controlId) != WidgetType.SPINNER) {
            b.setBackgroundColor(a);
        }
        m20848a(true);
    }

    /* renamed from: h */
    private void m20828h(UiMessage.CommandToUi commandToUi) {
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        View b = this.f8501a[this.f8502b].m20732b(commandToUi.getControlId());
        if (b == null) {
            m20848a(false);
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b.getLayoutParams();
        layoutParams.gravity = commandToUi.getAlignType();
        b.setLayoutParams(layoutParams);
        new StringBuilder("set gravity ok; ").append(layoutParams);
        m20848a(true);
    }

    /* renamed from: i */
    private void m20826i(UiMessage.CommandToUi commandToUi) {
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        View b = this.f8501a[this.f8502b].m20732b(commandToUi.getControlId());
        if (b == null) {
            m20848a(false);
            return;
        }
        UiMessage.Padding_Var padding = commandToUi.getPadding();
        new StringBuilder("padding: ").append(padding.getLeft());
        new StringBuilder("padding: ").append(padding.getTop());
        new StringBuilder("padding: ").append(padding.getRight());
        new StringBuilder("padding: ").append(padding.getBottom());
        b.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
        m20848a(true);
    }

    /* renamed from: b */
    private void m20844b(String str) {
        UiShowLayout[] kVarArr = this.f8501a;
        int i = this.f8502b;
        if (kVarArr[i] == null) {
            m20848a(false);
            return;
        }
        View b = kVarArr[i].m20732b(str);
        if (b == null) {
            m20848a(false);
            return;
        }
        boolean isEnabled = b.isEnabled();
        if (this.f8503c[this.f8502b].get(str) == WidgetType.EDIT_TEXT) {
            isEnabled = ((EditText) b).isFocusable();
        }
        MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(isEnabled).build()).build().toByteString());
    }

    /* renamed from: c */
    private void m20839c(String str) {
        UiShowLayout[] kVarArr = this.f8501a;
        int i = this.f8502b;
        if (kVarArr[i] == null) {
            m20848a(false);
            return;
        }
        View b = kVarArr[i].m20732b(str);
        if (b == null) {
            m20848a(false);
            return;
        }
        int visibility = b.getVisibility();
        MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(visibility != 0 ? visibility != 4 ? visibility != 8 ? 1 : 3 : 2 : 1).build()).build().toByteString());
    }

    /* renamed from: j */
    private void m20824j(UiMessage.CommandToUi commandToUi) {
        int i;
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f8503c[this.f8502b].get(controlId) == null) {
            m20848a(false);
            return;
        }
        switch ((WidgetType) this.f8503c[this.f8502b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                i = ((TextView) this.f8501a[this.f8502b].m20732b(controlId)).getPaint().getColor();
                break;
            case CHECK_BOX:
                i = ((CheckBox) this.f8501a[this.f8502b].m20732b(controlId)).getPaint().getColor();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f8501a[this.f8502b].m20732b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton != null) {
                    i = radioButton.getPaint().getColor();
                    break;
                } else {
                    m20848a(false);
                    return;
                }
            default:
                m20848a(false);
                return;
        }
        MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.red(i)))).build()).build().toByteString());
    }

    /* renamed from: d */
    private void m20835d(String str) {
        UiShowLayout[] kVarArr = this.f8501a;
        int i = this.f8502b;
        if (kVarArr[i] == null) {
            m20848a(false);
            return;
        }
        View b = kVarArr[i].m20732b(str);
        if (b == null) {
            m20848a(false);
            return;
        }
        int a = C1335c.m20658a(b.getSolidColor());
        MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue("#" + Integer.toHexString(a & ViewCompat.MEASURED_SIZE_MASK)).build()).build().toByteString());
    }

    /* renamed from: k */
    private void m20822k(UiMessage.CommandToUi commandToUi) {
        String str;
        if (this.f8501a[this.f8502b] == null) {
            m20848a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f8503c[this.f8502b].get(controlId) == null) {
            m20848a(false);
            return;
        }
        switch ((WidgetType) this.f8503c[this.f8502b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                str = ((TextView) this.f8501a[this.f8502b].m20732b(controlId)).getText().toString();
                break;
            case CHECK_BOX:
                str = ((CheckBox) this.f8501a[this.f8502b].m20732b(controlId)).getText().toString();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f8501a[this.f8502b].m20732b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton != null) {
                    str = radioButton.getText().toString();
                    break;
                } else {
                    m20848a(false);
                    return;
                }
            case SPINNER:
                Spinner spinner = (Spinner) this.f8501a[this.f8502b].m20732b(controlId);
                if (commandToUi.getItemIndex() < spinner.getCount()) {
                    new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                    str = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                    break;
                } else {
                    m20848a(false);
                    return;
                }
            default:
                m20848a(false);
                return;
        }
        MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(str).build()).build().toByteString());
    }

    /* renamed from: e */
    private void m20833e(String str) throws JSONException, IOException {
        if (this.f8501a[this.f8502b] != null) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : this.f8503c[this.f8502b].entrySet()) {
                String str2 = (String) entry.getKey();
                int i = C13094.f8521b[((WidgetType) entry.getValue()).ordinal()];
                if (i != 2) {
                    switch (i) {
                        case 4:
                            jSONObject.put(str2, ((CheckBox) this.f8501a[this.f8502b].m20732b(str2)).isChecked());
                            continue;
                        case 5:
                            jSONObject.put(str2, ((RadioGroup) this.f8501a[this.f8502b].m20732b(str2)).getCheckedRadioButtonId());
                            continue;
                        case 6:
                            jSONObject.put(str2, ((Spinner) this.f8501a[this.f8502b].m20732b(str2)).getSelectedItemId());
                            continue;
                    }
                } else {
                    jSONObject.put(str2, ((EditText) this.f8501a[this.f8502b].m20732b(str2)).getText().toString());
                }
            }
            FileUtils.writeStringToFile(new File(str), jSONObject.toString());
        }
    }

    /* renamed from: f */
    private void m20831f(String str) throws IOException, JSONException {
        if (this.f8503c[this.f8502b] != null) {
            JSONObject jSONObject = new JSONObject(FileUtils.readFileToString(new File(str)));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (this.f8503c[this.f8502b].get(next) != null) {
                    int i = C13094.f8521b[((WidgetType) this.f8503c[this.f8502b].get(next)).ordinal()];
                    if (i != 2) {
                        switch (i) {
                            case 4:
                                ((CheckBox) this.f8501a[this.f8502b].m20732b(next)).setChecked(jSONObject.getBoolean(next));
                                continue;
                            case 5:
                                RadioGroup radioGroup = (RadioGroup) this.f8501a[this.f8502b].m20732b(next);
                                if (jSONObject.getInt(next) < radioGroup.getChildCount()) {
                                    ((RadioButton) radioGroup.findViewById(jSONObject.getInt(next))).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case 6:
                                Spinner spinner = (Spinner) this.f8501a[this.f8502b].m20732b(next);
                                if (jSONObject.getInt(next) < spinner.getCount()) {
                                    spinner.setSelection(jSONObject.getInt(next));
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    } else {
                        ((EditText) this.f8501a[this.f8502b].m20732b(next)).setText(jSONObject.getString(next));
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m20853a(String str, int i, int i2) {
        this.f8502b++;
        this.f8501a[this.f8502b] = this.f8511m.m20880a(str, i, i2);
        this.f8512n.put(str, Integer.valueOf(this.f8502b));
        this.f8503c[this.f8502b] = new HashMap();
        this.f8501a[this.f8502b].f8579b = new View$OnClickListenerC13072(str);
        this.f8501a[this.f8502b].f8578a = new View$OnClickListenerC13083(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UiManager.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.h$2 */
    /* loaded from: classes.dex */
    public final class View$OnClickListenerC13072 implements View.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ String f8516a;

        View$OnClickListenerC13072(String str) {
            this.f8516a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f8516a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UiManager.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.h$3 */
    /* loaded from: classes.dex */
    public final class View$OnClickListenerC13083 implements View.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ String f8518a;

        View$OnClickListenerC13083(String str) {
            this.f8518a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            for (int i = 31; i > 0; i--) {
                if (UiManager.this.f8501a[i] != null) {
                    UiMessage.UiToCommand build = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f8518a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_EXIT).build()).setIsSuccess(true).build();
                    MqRunner.getInstance().mo20411b(build.toByteString());
                    new StringBuilder("exit event: ").append(build);
                }
            }
        }
    }

    /* renamed from: g */
    private void m20829g(String str) {
        if (this.f8512n.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_ui, str);
            m20848a(false);
        } else if (this.f8501a[this.f8512n.get(str).intValue()].f8580c) {
            m20848a(false);
        } else {
            this.f8501a[this.f8512n.get(str).intValue()].m20731c();
            m20848a(true);
            MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
        }
    }

    /* renamed from: a */
    private boolean m20850a(String str, String str2) {
        if (this.f8512n.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_ui, str);
            return false;
        }
        this.f8501a[this.f8512n.get(str).intValue()].m20739a(str2);
        return true;
    }

    /* renamed from: h */
    private void m20827h(String str) {
        if (this.f8512n.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_ui, str);
            m20848a(false);
            return;
        }
        MqRunner.getInstance().mo20414a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f8501a[this.f8512n.get(str).intValue()].m20747a()).build()).build().toByteString());
    }

    /* renamed from: l */
    private void m20820l(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (this.f8512n.get(controlId) == null) {
            m20858a(C1375R.string.ui_show_not_found_ui, controlId);
            m20848a(false);
        } else if (!color.matches(f8499j)) {
            m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20848a(false);
        } else {
            if (!color.startsWith("#")) {
                color = "#" + color;
            }
            this.f8501a[this.f8512n.get(controlId).intValue()].m20735b(C1335c.m20658a(Color.parseColor(color)));
            m20848a(true);
        }
    }

    /* renamed from: i */
    private static void m20825i(String str) {
        MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
    }

    /* renamed from: j */
    private boolean m20823j(String str) {
        if (this.f8512n.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_ui, str);
            return false;
        } else if (!this.f8501a[this.f8512n.get(str).intValue()].f8580c) {
            return false;
        } else {
            this.f8501a[this.f8512n.get(str).intValue()].m20726e();
            int intValue = this.f8512n.get(str).intValue();
            int i = this.f8502b;
            if (intValue == i) {
                this.f8502b = i - 1;
                while (true) {
                    int i2 = this.f8502b;
                    if (i2 <= 0 || this.f8501a[i2] != null) {
                        break;
                    }
                    this.f8502b = i2 - 1;
                }
                if (this.f8502b < 0) {
                    this.f8502b = 0;
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    public final void m20859a() {
        for (int i = 31; i > 0; i--) {
            UiShowLayout kVar = this.f8501a[i];
            if (kVar != null && kVar.f8580c) {
                kVar.m20726e();
            }
            UiShowFloat jVar = this.f8504d[i];
            if (jVar != null && jVar.f8544d) {
                jVar.m20755c();
            }
        }
    }

    /* renamed from: a */
    private void m20858a(int i, Object... objArr) {
        Context context = this.f8510l;
        ExToast.makeText(context, String.format(context.getString(i), objArr), 3500).show();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) view.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* renamed from: a */
    public static void m20851a(String str, UiMessage.ControlEvent.Event_Type event_Type) {
        MqRunner.getInstance().mo20409c(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(event_Type).build()).setIsSuccess(true).build().toByteString());
    }

    /* renamed from: k */
    private void m20821k(String str) {
        UiMessage.UiToCommand.Builder isSuccess = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true);
        UiMessage.ControlVar.Builder controlId = UiMessage.ControlVar.newBuilder().setControlId(str);
        WidgetType mVar = null;
        int i = 0;
        while (true) {
            if (i >= 32) {
                i = 0;
                break;
            }
            HashMap[] hashMapArr = this.f8503c;
            if (hashMapArr[i] != null && (mVar = (WidgetType) hashMapArr[i].get(str)) != null) {
                break;
            }
            i++;
        }
        StringBuilder sb = new StringBuilder("type=");
        sb.append(mVar);
        sb.append("; tempIndex=");
        sb.append(i);
        if (mVar == null) {
            m20858a(C1375R.string.ui_show_not_found_widget, str);
            MqRunner.getInstance().mo20414a(isSuccess.setIsSuccess(false).build().toByteString());
            return;
        }
        switch (mVar) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                controlId.setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(((TextView) this.f8501a[i].m20732b(str)).getText().toString());
                isSuccess.addVarTable(controlId.build());
                break;
            case CHECK_BOX:
                controlId.setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(((CheckBox) this.f8501a[i].m20732b(str)).isChecked());
                isSuccess.addVarTable(controlId.build());
                break;
            case RADIIO_GROUP:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((RadioGroup) this.f8501a[i].m20732b(str)).getCheckedRadioButtonId());
                isSuccess.addVarTable(controlId.build());
                break;
            case SPINNER:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((Spinner) this.f8501a[i].m20732b(str)).getSelectedItemPosition());
                isSuccess.addVarTable(controlId.build());
                break;
            default:
                isSuccess.setIsSuccess(false);
                break;
        }
        new StringBuilder("Ui.getValue result: ").append(isSuccess.build());
        MqRunner.getInstance().mo20414a(isSuccess.build().toByteString());
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) adapterView.getTag()).setEventArgs(String.valueOf(i)).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UiManager.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.h$a */
    /* loaded from: classes.dex */
    public class C1310a implements TextWatcher {

        /* renamed from: b */
        private String f8523b;

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public C1310a(String str) {
            this.f8523b = str;
        }

        /* renamed from: a */
        private String m20804a() {
            return this.f8523b;
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            MqRunner.getInstance().mo20411b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f8523b).setType(UiMessage.ControlEvent.Event_Type.ON_CHANGE).build()).setIsSuccess(true).build().toByteString());
            UiManager.this.f8501a[UiManager.this.f8502b].m20736b();
        }
    }

    /* renamed from: a */
    private void m20852a(String str, int i, int i2, int i3, int i4) {
        if (this.f8506f.get(str) != null) {
            if (this.f8504d[this.f8506f.get(str).intValue()].f8544d) {
                this.f8504d[this.f8506f.get(str).intValue()].m20761b();
            }
            Iterator<Map.Entry<String, String>> it = this.f8508h.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                String key = next.getKey();
                String value = next.getValue();
                StringBuilder sb = new StringBuilder("doNewFloat key:");
                sb.append(key);
                sb.append(" parentTag:");
                sb.append(value);
                sb.append(" tag:");
                sb.append(str);
                if (value.compareTo(str) == 0) {
                    it.remove();
                    this.f8507g.remove(key);
                }
            }
            this.f8504d[this.f8506f.get(str).intValue()] = this.f8511m.m20879a(str, i, i2, i3, i4);
            return;
        }
        this.f8505e++;
        this.f8504d[this.f8505e] = this.f8511m.m20879a(str, i, i2, i3, i4);
        this.f8506f.put(str, Integer.valueOf(this.f8505e));
    }

    /* renamed from: l */
    private void m20819l(String str) {
        if (this.f8506f.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_float, str);
            m20848a(false);
        } else if (this.f8504d[this.f8506f.get(str).intValue()].f8544d) {
            m20848a(false);
        } else {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            String str2 = (String) jVar.getTag();
            if (jVar.f8549i == null || jVar.f8549i.length() <= 0) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadii(new float[]{jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g});
                try {
                    gradientDrawable.setColor(jVar.f8546f);
                } catch (Exception unused) {
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    jVar.f8543c.setBackground(gradientDrawable);
                }
            } else {
                Bitmap decodeFile = BitmapFactory.decodeFile(jVar.f8549i);
                if (Build.VERSION.SDK_INT >= 16) {
                    jVar.f8543c.setBackground(new BitmapDrawable(decodeFile));
                }
            }
            jVar.f8544d = true;
            jVar.f8543c.setOnTouchListener(new UiShowFloat.View$OnTouchListenerC13151(str2));
            jVar.f8541a.addView(jVar.f8543c, jVar.f8542b);
            jVar.f8543c.getBackground().setAlpha((jVar.f8548h * 255) / 100);
            m20848a(true);
            m20851a(str, UiMessage.ControlEvent.Event_Type.ON_SHOW);
        }
    }

    /* renamed from: m */
    private void m20817m(String str) {
        if (this.f8506f.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_float, str);
            m20848a(false);
        } else if (!this.f8504d[this.f8506f.get(str).intValue()].f8544d) {
            m20848a(true);
        } else {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            if (jVar.f8543c != null && jVar.f8544d) {
                jVar.f8541a.removeView(jVar.f8543c);
                jVar.f8541a.addView(jVar.f8543c, jVar.f8542b);
            }
            m20848a(true);
        }
    }

    /* renamed from: a */
    private void m20849a(String str, boolean z) {
        if (this.f8506f.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_float, str);
            m20848a(false);
            return;
        }
        this.f8504d[this.f8506f.get(str).intValue()].f8545e = z;
        m20848a(true);
    }

    /* renamed from: n */
    private void m20815n(String str) {
        if (this.f8506f.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_float, str);
            m20848a(false);
        } else if (!this.f8504d[this.f8506f.get(str).intValue()].f8544d) {
            m20848a(false);
        } else {
            this.f8504d[this.f8506f.get(str).intValue()].m20761b();
            m20848a(true);
        }
    }

    /* renamed from: o */
    private void m20813o(String str) {
        if (this.f8506f.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_float, str);
            m20848a(false);
            return;
        }
        this.f8504d[this.f8506f.get(str).intValue()].m20755c();
        this.f8506f.remove(str);
        m20851a(str, UiMessage.ControlEvent.Event_Type.ON_CLOSE);
        m20848a(true);
    }

    /* renamed from: a */
    private void m20854a(String str, int i) {
        if (this.f8506f.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_float, str);
            m20848a(false);
            return;
        }
        this.f8504d[this.f8506f.get(str).intValue()].setOpacity(i);
        m20848a(true);
    }

    /* renamed from: b */
    private void m20843b(String str, int i) {
        if (this.f8506f.get(str) == null) {
            m20858a(C1375R.string.ui_show_not_found_float, str);
            m20848a(false);
            return;
        }
        UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
        jVar.f8547g = i;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g});
        try {
            gradientDrawable.setColor(jVar.f8546f);
        } catch (Exception unused) {
        }
        if (Build.VERSION.SDK_INT >= 16) {
            jVar.f8543c.setBackground(gradientDrawable);
        }
        jVar.f8543c.getBackground().setAlpha((jVar.f8548h * 255) / 100);
        this.f8504d[this.f8506f.get(str).intValue()].m20750e();
        m20848a(true);
    }

    /* renamed from: b */
    private void m20842b(String str, String str2) {
        if (!str2.matches(f8499j)) {
            m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20848a(false);
            return;
        }
        if (!str2.startsWith("#")) {
            str2 = "#" + str2;
        }
        int a = C1335c.m20658a(Color.parseColor(str2));
        if (this.f8506f.get(str) != null) {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            jVar.f8546f = a;
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadii(new float[]{jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g, jVar.f8547g});
            try {
                gradientDrawable.setColor(jVar.f8546f);
            } catch (Exception unused) {
            }
            if (Build.VERSION.SDK_INT >= 16) {
                jVar.f8543c.setBackground(gradientDrawable);
            }
            jVar.f8543c.getBackground().setAlpha((jVar.f8548h * 255) / 100);
            this.f8504d[this.f8506f.get(str).intValue()].m20750e();
        } else {
            String str3 = this.f8508h.get(str);
            if (str3 == null) {
                m20848a(false);
                return;
            }
            View a2 = this.f8504d[this.f8506f.get(str3).intValue()].m20764a(str);
            if (a2 == null) {
                m20848a(false);
                return;
            } else if (this.f8507g.get(str) == WidgetType.BUTTON) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadii(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f});
                gradientDrawable2.setColor(a);
                a2.setBackground(gradientDrawable2);
            } else if (this.f8507g.get(str) != WidgetType.SPINNER) {
                a2.setBackgroundColor(a);
            }
        }
        m20848a(true);
    }

    /* renamed from: c */
    private boolean m20838c(String str, String str2) {
        View a;
        if (this.f8506f.get(str) != null) {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            jVar.f8549i = str2;
            Bitmap decodeFile = BitmapFactory.decodeFile(jVar.f8549i);
            if (Build.VERSION.SDK_INT >= 16) {
                jVar.f8543c.setBackground(new BitmapDrawable(decodeFile));
            }
            jVar.f8543c.getBackground().setAlpha((jVar.f8548h * 255) / 100);
            this.f8504d[this.f8506f.get(str).intValue()].m20750e();
            return true;
        }
        String str3 = this.f8508h.get(str);
        if (str3 == null || (a = this.f8504d[this.f8506f.get(str3).intValue()].m20764a(str)) == null) {
            return false;
        }
        if (this.f8507g.get(str) == WidgetType.IMAGE_VIEW) {
            ((ImageView) a).setImageURI(Uri.fromFile(new File(str2)));
        }
        return true;
    }

    /* renamed from: m */
    private void m20818m(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String strParam = commandToUi.getStrParam();
        if (!strParam.matches(f8499j)) {
            m20858a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20848a(false);
            return;
        }
        if (!strParam.startsWith("#")) {
            strParam = "#" + strParam;
        }
        int a = C1335c.m20658a(Color.parseColor(strParam));
        String str = this.f8508h.get(controlId);
        if (str == null) {
            m20848a(false);
            return;
        }
        View a2 = this.f8504d[this.f8506f.get(str).intValue()].m20764a(controlId);
        if (a2 == null) {
            m20848a(false);
            return;
        }
        if (this.f8507g.get(controlId) == WidgetType.BUTTON) {
            ((Button) a2).setTextColor(a);
        }
        if (this.f8507g.get(controlId) == WidgetType.TEXT_VIEW) {
            ((TextView) a2).setTextColor(a);
        }
        this.f8504d[this.f8506f.get(str).intValue()].m20750e();
        m20848a(true);
    }

    /* renamed from: p */
    private void m20811p(String str) {
        JSONObject jSONObject = new JSONObject();
        if (this.f8506f.get(str) != null) {
            try {
                WindowManager.LayoutParams params = this.f8504d[this.f8506f.get(str).intValue()].getParams();
                jSONObject.put("Left", params.x);
                jSONObject.put("Top", params.y);
                jSONObject.put("Width", params.width);
                jSONObject.put("Height", params.height);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            String str2 = this.f8508h.get(str);
            WidgetType mVar = this.f8507g.get(str);
            if (mVar == null) {
                m20858a(C1375R.string.ui_show_not_found_widget, str);
                MqRunner.getInstance().mo20413a(jSONObject.toString());
                return;
            }
            int i = C13094.f8521b[mVar.ordinal()];
            if (i == 1) {
                TextView textView = (TextView) this.f8504d[this.f8506f.get(str2).intValue()].m20764a(str);
                try {
                    jSONObject.put("Left", textView.getLeft());
                    jSONObject.put("Top", textView.getTop());
                    jSONObject.put("Text", textView.getText());
                    jSONObject.put("Width", textView.getWidth());
                    jSONObject.put("Height", textView.getHeight());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else if (i == 3) {
                Button button = (Button) this.f8504d[this.f8506f.get(str2).intValue()].m20764a(str);
                try {
                    jSONObject.put("Left", button.getLeft());
                    jSONObject.put("Top", button.getTop());
                    jSONObject.put("Text", button.getText());
                    jSONObject.put("Width", button.getWidth());
                    jSONObject.put("Height", button.getHeight());
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            } else if (i == 7) {
                ImageView imageView = (ImageView) this.f8504d[this.f8506f.get(str2).intValue()].m20764a(str);
                try {
                    jSONObject.put("Left", imageView.getLeft());
                    jSONObject.put("Top", imageView.getTop());
                    jSONObject.put("Width", imageView.getWidth());
                    jSONObject.put("Height", imageView.getHeight());
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            }
        }
        new StringBuilder("FWGetValue json: ").append(jSONObject.toString());
        MqRunner.getInstance().mo20413a(jSONObject.toString());
    }

    /* renamed from: n */
    private boolean m20816n(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String str = this.f8508h.get(controlId);
        new StringBuilder("doReSetView value: ").append(commandToUi.getCommand().toString());
        if (str == null) {
            m20848a(false);
            return false;
        }
        new StringBuilder("doReSetView value: ").append(commandToUi.getCommand().toString());
        View a = this.f8504d[this.f8506f.get(str).intValue()].m20764a(controlId);
        if (a == null) {
            m20848a(false);
            return false;
        }
        new StringBuilder("doReSetView value: ").append(commandToUi.getCommand().toString());
        switch (commandToUi.getCommand()) {
            case SET_FW_BUTTON:
                if (this.f8507g.get(controlId) == WidgetType.BUTTON) {
                    Button button = (Button) a;
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) a.getLayoutParams();
                    layoutParams.width = commandToUi.getWidth();
                    layoutParams.height = commandToUi.getHeight();
                    button.setLayoutParams(layoutParams);
                    button.setText(commandToUi.getText());
                    button.setX(commandToUi.getLeft());
                    button.setY(commandToUi.getTop());
                    break;
                }
                break;
            case SET_FW_TEXT_VIEW:
                if (this.f8507g.get(controlId) == WidgetType.TEXT_VIEW) {
                    TextView textView = (TextView) a;
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) a.getLayoutParams();
                    layoutParams2.width = commandToUi.getWidth();
                    layoutParams2.height = commandToUi.getHeight();
                    textView.setLayoutParams(layoutParams2);
                    textView.setText(commandToUi.getText());
                    textView.setX(commandToUi.getLeft());
                    textView.setY(commandToUi.getTop());
                    break;
                }
                break;
            case SET_FW_IMAGE_VIEW:
                if (this.f8507g.get(controlId) == WidgetType.IMAGE_VIEW) {
                    ImageView imageView = (ImageView) a;
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                    layoutParams3.width = commandToUi.getWidth();
                    layoutParams3.height = commandToUi.getHeight();
                    imageView.setLayoutParams(layoutParams3);
                    imageView.setX(commandToUi.getLeft());
                    imageView.setY(commandToUi.getTop());
                    imageView.setImageURI(Uri.fromFile(new File(commandToUi.getText())));
                    break;
                }
                break;
        }
        this.f8504d[this.f8506f.get(str).intValue()].m20750e();
        m20848a(true);
        return true;
    }

    /* renamed from: o */
    private boolean m20814o(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        String str = this.f8508h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            if (jVar == null || (a = jVar.m20764a(controlId)) == null) {
                return false;
            }
            int i = C13094.f8521b[this.f8507g.get(controlId).ordinal()];
            if (i == 1 || i == 3) {
                new StringBuilder("setFloatTextSize value: ").append(commandToUi.getFontSize());
                ((TextView) a).setTextSize(2, commandToUi.getFontSize());
            }
            jVar.m20750e();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: p */
    private boolean m20812p(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        String str = this.f8508h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            if (jVar == null || (a = jVar.m20764a(controlId)) == null) {
                return false;
            }
            switch (commandToUi.getCommand()) {
                case SET_FW_ENABLE:
                    new StringBuilder("doFloatSetStatus ").append(commandToUi.getEnabledStatus());
                    a.setEnabled(commandToUi.getEnabledStatus());
                    break;
                case SET_FW_VISIBLE:
                    new StringBuilder("doFloatSetStatus ").append(commandToUi.getVisibleStatus());
                    if (commandToUi.getVisibleStatus() == 0) {
                        a.setVisibility(8);
                        break;
                    } else {
                        a.setVisibility(0);
                        break;
                    }
            }
            jVar.m20750e();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: q */
    private boolean m20810q(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        String str = this.f8508h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            if (jVar == null || (a = jVar.m20764a(controlId)) == null) {
                return false;
            }
            String str2 = "false";
            switch (commandToUi.getCommand()) {
                case GET_FW_ENABLE:
                    if (a.isEnabled()) {
                        str2 = "true";
                        break;
                    }
                    break;
                case GET_FW_TEXT_COLOR:
                    if (this.f8507g.get(controlId) != WidgetType.IMAGE_VIEW) {
                        str2 = String.format("%6s", Integer.toHexString(C1335c.m20658a(((TextView) a).getCurrentTextColor()) & ViewCompat.MEASURED_SIZE_MASK)).replace(ExpandableTextView.f6958c, ResultTypeConstant.f7213z);
                        break;
                    }
                    break;
                case GET_FW_VISIBLE:
                    if (a.getVisibility() == 0) {
                        str2 = "true";
                        break;
                    }
                    break;
            }
            MqRunner.getInstance().mo20413a(str2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: r */
    private boolean m20809r(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        if (this.f8506f.get(controlId) != null) {
            this.f8504d[this.f8506f.get(controlId).intValue()].setFloatLeft(commandToUi.getIntParam());
            return true;
        }
        String str = this.f8508h.get(controlId);
        if (str == null || (a = this.f8504d[this.f8506f.get(str).intValue()].m20764a(controlId)) == null) {
            return false;
        }
        if (this.f8507g.get(controlId) != WidgetType.BUTTON && this.f8507g.get(controlId) != WidgetType.TEXT_VIEW && this.f8507g.get(controlId) != WidgetType.IMAGE_VIEW) {
            return true;
        }
        new StringBuilder("view.setX ").append(commandToUi.getIntParam());
        a.setX(commandToUi.getIntParam());
        return true;
    }

    /* renamed from: s */
    private boolean m20808s(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        if (this.f8506f.get(controlId) != null) {
            this.f8504d[this.f8506f.get(controlId).intValue()].setFloatTop(commandToUi.getIntParam());
            return true;
        }
        String str = this.f8508h.get(controlId);
        if (str == null || (a = this.f8504d[this.f8506f.get(str).intValue()].m20764a(controlId)) == null) {
            return false;
        }
        if (this.f8507g.get(controlId) != WidgetType.BUTTON && this.f8507g.get(controlId) != WidgetType.TEXT_VIEW && this.f8507g.get(controlId) != WidgetType.IMAGE_VIEW) {
            return true;
        }
        new StringBuilder("view.setY ").append(commandToUi.getIntParam());
        a.setY(commandToUi.getIntParam());
        return true;
    }

    /* renamed from: t */
    private boolean m20807t(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        if (this.f8506f.get(controlId) != null) {
            this.f8504d[this.f8506f.get(controlId).intValue()].setFloatWidth(commandToUi.getIntParam());
            return true;
        }
        String str = this.f8508h.get(controlId);
        if (str == null || (a = this.f8504d[this.f8506f.get(str).intValue()].m20764a(controlId)) == null) {
            return false;
        }
        if (this.f8507g.get(controlId) != WidgetType.BUTTON && this.f8507g.get(controlId) != WidgetType.TEXT_VIEW && this.f8507g.get(controlId) != WidgetType.IMAGE_VIEW) {
            return true;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) a.getLayoutParams();
        layoutParams.width = commandToUi.getIntParam();
        a.setLayoutParams(layoutParams);
        return true;
    }

    /* renamed from: u */
    private boolean m20806u(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        if (this.f8506f.get(controlId) != null) {
            this.f8504d[this.f8506f.get(controlId).intValue()].setFloatHeight(commandToUi.getIntParam());
            return true;
        }
        String str = this.f8508h.get(controlId);
        if (str == null || (a = this.f8504d[this.f8506f.get(str).intValue()].m20764a(controlId)) == null) {
            return false;
        }
        if (this.f8507g.get(controlId) != WidgetType.BUTTON && this.f8507g.get(controlId) != WidgetType.TEXT_VIEW && this.f8507g.get(controlId) != WidgetType.IMAGE_VIEW) {
            return true;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) a.getLayoutParams();
        layoutParams.height = commandToUi.getIntParam();
        a.setLayoutParams(layoutParams);
        return true;
    }

    /* renamed from: v */
    private boolean m20805v(UiMessage.CommandToUi commandToUi) {
        View a;
        String controlId = commandToUi.getControlId();
        new StringBuilder("setFloatText : ").append(commandToUi.getStrParam());
        String str = this.f8508h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            UiShowFloat jVar = this.f8504d[this.f8506f.get(str).intValue()];
            if (jVar == null || (a = jVar.m20764a(controlId)) == null) {
                return false;
            }
            int i = C13094.f8521b[this.f8507g.get(controlId).ordinal()];
            if (i == 1) {
                ((TextView) a).setText(commandToUi.getStrParam());
            } else if (i == 3) {
                ((Button) a).setText(commandToUi.getStrParam());
            }
            jVar.m20750e();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
