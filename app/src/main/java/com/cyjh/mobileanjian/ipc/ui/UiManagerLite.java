package com.cyjh.mobileanjian.ipc.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.event.UiTransHelper;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.C1335c;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C1375R;
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

/* renamed from: com.cyjh.mobileanjian.ipc.ui.i */
/* loaded from: classes.dex */
public final class UiManagerLite implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /* renamed from: g */
    private static final String f8524g = "^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})";

    /* renamed from: h */
    private static final int f8525h = 32;

    /* renamed from: c */
    public UiFactory f8528c;

    /* renamed from: i */
    private Context f8532i;

    /* renamed from: a */
    public UiShowLayout[] f8526a = new UiShowLayout[32];

    /* renamed from: b */
    public int f8527b = 0;

    /* renamed from: d */
    public HashMap<String, Integer> f8529d = new HashMap<>();

    /* renamed from: e */
    public HashMap[] f8530e = new HashMap[32];

    /* renamed from: f */
    public int f8531f = 1;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public UiManagerLite(Context context) {
        this.f8532i = context;
        this.f8528c = UiFactory.m20882a(context);
    }

    /* renamed from: a */
    public final int m20803a() {
        int i = this.f8531f + 1;
        this.f8531f = i;
        return i;
    }

    /* renamed from: b */
    public final int m20795b() {
        return (int) ((this.f8532i.getResources().getDisplayMetrics().densityDpi / 160.0f) * 5.0f);
    }

    /* renamed from: d */
    private void m20788d() {
        m20791c();
        this.f8527b = 0;
        this.f8531f = 1;
        for (int i = 0; i < 32; i++) {
            this.f8526a[i] = null;
            HashMap[] hashMapArr = this.f8530e;
            if (hashMapArr[i] != null) {
                hashMapArr[i].clear();
            }
        }
    }

    /* renamed from: d */
    private void m20787d(UiMessage.CommandToUi commandToUi) {
        String str;
        int i;
        String controlId = commandToUi.getControlId();
        UiMessage.CommandToUi.Command_Type command = commandToUi.getCommand();
        new StringBuilder("command: ").append(commandToUi);
        int i2 = 3;
        boolean z = true;
        switch (command) {
            case SAVE_PROFILE:
                try {
                    m20799a(commandToUi.getPath());
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
                    m20792b(commandToUi.getPath());
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
                this.f8527b++;
                this.f8526a[this.f8527b] = this.f8528c.m20880a(controlId, width, height);
                this.f8529d.put(controlId, Integer.valueOf(this.f8527b));
                this.f8530e[this.f8527b] = new HashMap();
                this.f8526a[this.f8527b].f8579b = new View$OnClickListenerC13111(controlId);
                this.f8526a[this.f8527b].f8578a = new View$OnClickListenerC13122(controlId);
                break;
            case SHOW_LAYOUT:
                if (this.f8529d.get(controlId) == null) {
                    m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                    m20796a(false);
                    return;
                } else if (this.f8526a[this.f8529d.get(controlId).intValue()].f8580c) {
                    m20796a(false);
                    return;
                } else {
                    this.f8526a[this.f8529d.get(controlId).intValue()].m20731c();
                    m20796a(true);
                    UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                    return;
                }
            case CLOSE_LAYOUT:
                UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
                break;
            case CLOSE_CONTINUE_ACK:
                z = m20786d(controlId);
                break;
            case CLOSE_EXIT_ACK:
                m20791c();
                break;
            case NEW_ROW:
                UiShowLayout[] kVarArr = this.f8526a;
                int i3 = this.f8527b;
                if (kVarArr[i3] != null) {
                    UiShowLayout kVar = kVarArr[i3];
                    m20803a();
                    kVar.m20737a(controlId, commandToUi.getParentId(), commandToUi.getWidth(), commandToUi.getHeight());
                    break;
                }
                break;
            case SET_TITLE_TEXT:
                String text = commandToUi.getText();
                if (this.f8529d.get(controlId) != null) {
                    this.f8526a[this.f8529d.get(controlId).intValue()].m20739a(text);
                    break;
                } else {
                    m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                    z = false;
                    break;
                }
            case GET_TITLE_TEXT:
                if (this.f8529d.get(controlId) == null) {
                    m20802a(C1375R.string.ui_show_not_found_ui, controlId);
                    m20796a(false);
                    return;
                }
                UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f8526a[this.f8529d.get(controlId).intValue()].m20747a()).build()).build().toByteString());
                return;
            case SET_TITLE_BACKCOLOR:
                String controlId2 = commandToUi.getControlId();
                String color = commandToUi.getColor();
                if (this.f8529d.get(controlId2) == null) {
                    m20802a(C1375R.string.ui_show_not_found_ui, controlId2);
                    m20796a(false);
                    return;
                } else if (!color.matches(f8524g)) {
                    m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20796a(false);
                    return;
                } else {
                    if (!color.startsWith("#")) {
                        color = "#" + color;
                    }
                    this.f8526a[this.f8529d.get(controlId2).intValue()].m20735b(C1335c.m20658a(Color.parseColor(color)));
                    m20796a(true);
                    return;
                }
            case ADD_LINE:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar = this.f8528c;
                    m20803a();
                    LinearLayout b = gVar.m20870b(controlId, commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.LINE);
                    this.f8526a[this.f8527b].m20741a(b, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_TEXT_VIEW:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar2 = this.f8528c;
                    m20803a();
                    TextView a = gVar2.m20876a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.TEXT_VIEW);
                    this.f8526a[this.f8527b].m20741a(a, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_EDIT_TEXT:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar3 = this.f8528c;
                    m20803a();
                    EditText d = gVar3.m20864d(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.EDIT_TEXT);
                    this.f8526a[this.f8527b].m20741a(d, commandToUi.getParentId());
                    d.addTextChangedListener(new C1314a(controlId));
                    break;
                }
                break;
            case ADD_BUTTON:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar4 = this.f8528c;
                    m20803a();
                    Button b2 = gVar4.m20868b(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.BUTTON);
                    b2.setOnClickListener(this);
                    this.f8526a[this.f8527b].m20741a(b2, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_RADIO_GROUP:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar5 = this.f8528c;
                    m20803a();
                    RadioGroup a2 = gVar5.m20873a(controlId, commandToUi.getItemTextList(), commandToUi.getDefaultItemIndex(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.RADIIO_GROUP);
                    this.f8526a[this.f8527b].m20741a(a2, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_CHECK_BOX:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar6 = this.f8528c;
                    m20803a();
                    CheckBox a3 = gVar6.m20875a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getDefaultCheckStatus());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.CHECK_BOX);
                    this.f8526a[this.f8527b].m20741a(a3, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_IMAGE_VIEW:
                if (this.f8526a[this.f8527b] != null) {
                    ImageView a4 = this.f8528c.m20883a(m20803a(), controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getPath());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.IMAGE_VIEW);
                    a4.setOnClickListener(this);
                    this.f8526a[this.f8527b].m20741a(a4, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_WEB_VIEW:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar7 = this.f8528c;
                    m20803a();
                    WebView a5 = gVar7.m20878a(controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getUrl());
                    this.f8530e[this.f8527b].put(controlId, WidgetType.WEB_VIEW);
                    this.f8526a[this.f8527b].m20741a(a5, commandToUi.getParentId());
                    break;
                }
                break;
            case ADD_SPINNER:
                if (this.f8526a[this.f8527b] != null) {
                    UiFactory gVar8 = this.f8528c;
                    m20803a();
                    List<String> itemTextList = commandToUi.getItemTextList();
                    int defaultItemIndex = commandToUi.getDefaultItemIndex();
                    commandToUi.getWidth();
                    commandToUi.getHeight();
                    Spinner a6 = gVar8.m20874a(controlId, itemTextList, defaultItemIndex);
                    this.f8530e[this.f8527b].put(controlId, WidgetType.SPINNER);
                    this.f8526a[this.f8527b].m20741a(a6, commandToUi.getParentId());
                    a6.setOnItemSelectedListener(this);
                    break;
                }
                break;
            case ADD_TAB_HOST:
                UiShowLayout[] kVarArr2 = this.f8526a;
                int i4 = this.f8527b;
                if (kVarArr2[i4] != null) {
                    kVarArr2[i4].m20738a(commandToUi.getControlId(), commandToUi.getHeight());
                    break;
                }
                break;
            case ADD_TAB:
                UiShowLayout[] kVarArr3 = this.f8526a;
                int i5 = this.f8527b;
                if (kVarArr3[i5] != null) {
                    z = kVarArr3[i5].m20744a(m20803a(), commandToUi.getControlId(), commandToUi.getParentId(), commandToUi.getText());
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
                z = m20801a(commandToUi);
                break;
            case SET_FULLSCREEN:
                if (this.f8529d.get(controlId) == null) {
                    if (this.f8530e[this.f8527b].get(controlId) != null) {
                        View b3 = this.f8526a[this.f8527b].m20732b(controlId);
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b3.getLayoutParams();
                        layoutParams.width = -1;
                        b3.setLayoutParams(layoutParams);
                        break;
                    } else {
                        m20796a(false);
                        break;
                    }
                } else {
                    m20796a(true);
                    break;
                }
            case SET_TEXT:
                m20790c(commandToUi);
                return;
            case SET_FONT:
                m20794b(commandToUi);
                return;
            case SET_EDIT_INPUT_TYPE:
                if (this.f8526a[this.f8527b] == null) {
                    m20796a(false);
                    return;
                }
                String controlId3 = commandToUi.getControlId();
                commandToUi.getText();
                View b4 = this.f8526a[this.f8527b].m20732b(controlId3);
                if (b4 == null) {
                    m20796a(false);
                    return;
                } else if (this.f8530e[this.f8527b].get(controlId3) != WidgetType.EDIT_TEXT) {
                    m20796a(false);
                    return;
                } else {
                    ((EditText) b4).setInputType(commandToUi.getEditInputType());
                    m20796a(true);
                    return;
                }
            case GET_TEXT:
                if (this.f8526a[this.f8527b] == null) {
                    m20796a(false);
                    return;
                }
                String controlId4 = commandToUi.getControlId();
                if (this.f8530e[this.f8527b].get(controlId4) == null) {
                    m20796a(false);
                    return;
                }
                switch ((WidgetType) this.f8530e[this.f8527b].get(controlId4)) {
                    case TEXT_VIEW:
                    case EDIT_TEXT:
                    case BUTTON:
                        str = ((TextView) this.f8526a[this.f8527b].m20732b(controlId4)).getText().toString();
                        break;
                    case CHECK_BOX:
                        str = ((CheckBox) this.f8526a[this.f8527b].m20732b(controlId4)).getText().toString();
                        break;
                    case RADIIO_GROUP:
                        RadioButton radioButton = (RadioButton) ((RadioGroup) this.f8526a[this.f8527b].m20732b(controlId4)).findViewById(commandToUi.getItemIndex());
                        if (radioButton != null) {
                            str = radioButton.getText().toString();
                            break;
                        } else {
                            m20796a(false);
                            return;
                        }
                    case SPINNER:
                        Spinner spinner = (Spinner) this.f8526a[this.f8527b].m20732b(controlId4);
                        if (commandToUi.getItemIndex() < spinner.getCount()) {
                            new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                            str = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                            break;
                        } else {
                            m20796a(false);
                            return;
                        }
                    default:
                        m20796a(false);
                        return;
                }
                UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(str).build()).build().toByteString());
                return;
            case SET_TEXTCOLOR:
                if (this.f8526a[this.f8527b] == null) {
                    m20796a(false);
                    return;
                }
                String controlId5 = commandToUi.getControlId();
                String color2 = commandToUi.getColor();
                if (!color2.matches(f8524g)) {
                    m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20796a(false);
                    return;
                }
                if (!color2.startsWith("#")) {
                    color2 = "#" + color2;
                }
                int a7 = C1335c.m20658a(Color.parseColor(color2));
                if (this.f8529d.get(controlId5) != null) {
                    this.f8526a[this.f8529d.get(controlId5).intValue()].m20746a(a7);
                } else {
                    View b5 = this.f8526a[this.f8527b].m20732b(controlId5);
                    if (b5 == null) {
                        m20796a(false);
                        return;
                    }
                    switch ((WidgetType) this.f8530e[this.f8527b].get(controlId5)) {
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
                                m20796a(false);
                                return;
                            }
                        case SPINNER:
                            if (commandToUi.getItemIndex() >= ((Spinner) b5).getCount()) {
                                m20796a(false);
                                return;
                            }
                            break;
                        default:
                            m20796a(false);
                            return;
                    }
                }
                m20796a(true);
                return;
            case SET_BACKCOLOR:
                if (this.f8526a[this.f8527b] == null) {
                    m20796a(false);
                    return;
                }
                String controlId6 = commandToUi.getControlId();
                View b6 = this.f8526a[this.f8527b].m20732b(controlId6);
                if (b6 == null) {
                    m20796a(false);
                    return;
                }
                String color3 = commandToUi.getColor();
                if (!color3.matches(f8524g)) {
                    m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
                    m20796a(false);
                    return;
                }
                if (!color3.startsWith("#")) {
                    color3 = "#" + color3;
                }
                int a8 = C1335c.m20658a(Color.parseColor(color3));
                if (this.f8530e[this.f8527b].get(controlId6) == WidgetType.BUTTON) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(a8);
                    gradientDrawable.setCornerRadius(m20795b());
                    if (Build.VERSION.SDK_INT >= 16) {
                        b6.setBackground(gradientDrawable);
                    } else {
                        b6.setBackgroundDrawable(gradientDrawable);
                    }
                    m20796a(true);
                    return;
                }
                if (this.f8530e[this.f8527b].get(controlId6) != WidgetType.SPINNER) {
                    b6.setBackgroundColor(a8);
                }
                m20796a(true);
                return;
            case GET_VALUE:
                m20784e(controlId);
                return;
            case GET_ENABLED:
                UiShowLayout[] kVarArr4 = this.f8526a;
                int i6 = this.f8527b;
                if (kVarArr4[i6] == null) {
                    m20796a(false);
                    return;
                }
                View b7 = kVarArr4[i6].m20732b(controlId);
                if (b7 == null) {
                    m20796a(false);
                    return;
                }
                boolean isEnabled = b7.isEnabled();
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.EDIT_TEXT) {
                    isEnabled = ((EditText) b7).isFocusable();
                }
                UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(isEnabled).build()).build().toByteString());
                return;
            case GET_VISIBLE:
                UiShowLayout[] kVarArr5 = this.f8526a;
                int i7 = this.f8527b;
                if (kVarArr5[i7] == null) {
                    m20796a(false);
                    return;
                }
                View b8 = kVarArr5[i7].m20732b(controlId);
                if (b8 == null) {
                    m20796a(false);
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
            case GET_TEXTCOLOR:
                if (this.f8526a[this.f8527b] == null) {
                    m20796a(false);
                    return;
                }
                String controlId7 = commandToUi.getControlId();
                if (this.f8530e[this.f8527b].get(controlId7) == null) {
                    m20796a(false);
                    return;
                }
                switch ((WidgetType) this.f8530e[this.f8527b].get(controlId7)) {
                    case TEXT_VIEW:
                    case EDIT_TEXT:
                    case BUTTON:
                        i = ((TextView) this.f8526a[this.f8527b].m20732b(controlId7)).getPaint().getColor();
                        break;
                    case CHECK_BOX:
                        i = ((CheckBox) this.f8526a[this.f8527b].m20732b(controlId7)).getPaint().getColor();
                        break;
                    case RADIIO_GROUP:
                        RadioButton radioButton3 = (RadioButton) ((RadioGroup) this.f8526a[this.f8527b].m20732b(controlId7)).findViewById(commandToUi.getItemIndex());
                        if (radioButton3 != null) {
                            i = radioButton3.getPaint().getColor();
                            break;
                        } else {
                            m20796a(false);
                            return;
                        }
                    default:
                        m20796a(false);
                        return;
                }
                UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.red(i)))).build()).build().toByteString());
                return;
            case SET_CONTROL_GRAVITY:
                if (this.f8526a[this.f8527b] == null) {
                    m20796a(false);
                    return;
                }
                View b9 = this.f8526a[this.f8527b].m20732b(commandToUi.getControlId());
                if (b9 == null) {
                    m20796a(false);
                    return;
                }
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) b9.getLayoutParams();
                layoutParams2.gravity = commandToUi.getAlignType();
                b9.setLayoutParams(layoutParams2);
                new StringBuilder("set gravity ok; ").append(layoutParams2);
                m20796a(true);
                return;
            case SET_PADDING:
                if (this.f8526a[this.f8527b] == null) {
                    m20796a(false);
                    return;
                }
                View b10 = this.f8526a[this.f8527b].m20732b(commandToUi.getControlId());
                if (b10 == null) {
                    m20796a(false);
                    return;
                }
                UiMessage.Padding_Var padding = commandToUi.getPadding();
                new StringBuilder("padding: ").append(padding.getLeft());
                new StringBuilder("padding: ").append(padding.getTop());
                new StringBuilder("padding: ").append(padding.getRight());
                new StringBuilder("padding: ").append(padding.getBottom());
                b10.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                m20796a(true);
                return;
        }
        m20796a(z);
    }

    /* renamed from: a */
    public static void m20796a(boolean z) {
        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(z).build().toByteString());
    }

    /* renamed from: a */
    public final boolean m20801a(UiMessage.CommandToUi commandToUi) {
        int i = 0;
        if (this.f8526a[this.f8527b] == null) {
            return false;
        }
        String controlId = commandToUi.getControlId();
        View b = this.f8526a[this.f8527b].m20732b(controlId);
        if (b == null) {
            m20802a(C1375R.string.ui_show_not_found_widget, controlId);
            return false;
        }
        switch (commandToUi.getCommand()) {
            case SET_TEXT_VIEW:
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.TEXT_VIEW) {
                    TextView textView = (TextView) b;
                    textView.setText(commandToUi.getText());
                    textView.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_EDIT_TEXT:
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.EDIT_TEXT) {
                    EditText editText = (EditText) b;
                    editText.setText(commandToUi.getText());
                    editText.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_BOTTON:
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.BUTTON) {
                    Button button = (Button) b;
                    button.setText(commandToUi.getText());
                    button.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                    break;
                } else {
                    return false;
                }
            case SET_CHECK_BOX:
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.CHECK_BOX) {
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
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.IMAGE_VIEW) {
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
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.WEB_VIEW) {
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
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.RADIIO_GROUP) {
                    RadioGroup radioGroup = (RadioGroup) b;
                    radioGroup.clearCheck();
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) b.getLayoutParams();
                    layoutParams2.weight = commandToUi.getWidth();
                    layoutParams2.height = commandToUi.getHeight();
                    radioGroup.setLayoutParams(layoutParams2);
                    radioGroup.removeAllViews();
                    List<String> itemTextList = commandToUi.getItemTextList();
                    for (int i2 = 0; i2 < itemTextList.size(); i2++) {
                        RadioButton radioButton = (RadioButton) LayoutInflater.from(this.f8532i).inflate(C1375R.layout.ui_show_radio_button, (ViewGroup) null);
                        radioButton.setId(i2);
                        radioButton.setText(itemTextList.get(i2));
                        radioButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        radioButton.setPadding(0, m20795b(), 0, m20795b());
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
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.SPINNER) {
                    Spinner spinner = (Spinner) b;
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this.f8532i, C1375R.layout.ui_show_spinner_dropdown_item, commandToUi.getItemTextList());
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
                if (this.f8530e[this.f8527b].get(controlId) != WidgetType.RADIIO_GROUP) {
                    if (this.f8530e[this.f8527b].get(controlId) == WidgetType.EDIT_TEXT) {
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
                if (this.f8530e[this.f8527b].get(controlId) == WidgetType.LINE) {
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

    /* renamed from: f */
    private void m20782f(String str) {
        if (this.f8529d.get(str) != null) {
            m20796a(true);
        } else if (this.f8530e[this.f8527b].get(str) == null) {
            m20796a(false);
        } else {
            View b = this.f8526a[this.f8527b].m20732b(str);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b.getLayoutParams();
            layoutParams.width = -1;
            b.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: b */
    public final void m20794b(UiMessage.CommandToUi commandToUi) {
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        new StringBuilder("SET FONT type: ").append(commandToUi.getFontType());
        String controlId = commandToUi.getControlId();
        Typeface create = Typeface.create(commandToUi.getFontType(), 0);
        if (create == null) {
            m20796a(false);
            return;
        }
        int fontSize = commandToUi.getFontSize() < 0 ? 14 : commandToUi.getFontSize();
        if (this.f8529d.get(controlId) == null || this.f8526a[this.f8529d.get(controlId).intValue()] == null) {
            View b = this.f8526a[this.f8527b].m20732b(controlId);
            if (b == null) {
                m20796a(false);
            } else if (this.f8530e[this.f8527b].get(controlId) == null) {
                m20796a(false);
            } else {
                switch ((WidgetType) this.f8530e[this.f8527b].get(controlId)) {
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
                        m20796a(false);
                        return;
                }
                m20796a(true);
            }
        } else {
            this.f8526a[this.f8529d.get(controlId).intValue()].m20743a(create, fontSize);
            m20796a(true);
        }
    }

    /* renamed from: e */
    private void m20785e(UiMessage.CommandToUi commandToUi) {
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        commandToUi.getText();
        View b = this.f8526a[this.f8527b].m20732b(controlId);
        if (b == null) {
            m20796a(false);
        } else if (this.f8530e[this.f8527b].get(controlId) != WidgetType.EDIT_TEXT) {
            m20796a(false);
        } else {
            ((EditText) b).setInputType(commandToUi.getEditInputType());
            m20796a(true);
        }
    }

    /* renamed from: c */
    public final void m20790c(UiMessage.CommandToUi commandToUi) {
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String text = commandToUi.getText();
        View b = this.f8526a[this.f8527b].m20732b(controlId);
        if (b == null) {
            m20796a(false);
        } else if (this.f8530e[this.f8527b].get(controlId) == null) {
            m20796a(false);
        } else {
            switch ((WidgetType) this.f8530e[this.f8527b].get(controlId)) {
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
                        m20796a(false);
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
                        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this.f8532i, C1375R.layout.ui_show_spinner_dropdown_item, arrayList);
                        spinner.setAdapter((SpinnerAdapter) arrayAdapter2);
                        arrayAdapter2.notifyDataSetChanged();
                        break;
                    } else {
                        m20796a(false);
                        return;
                    }
                default:
                    m20796a(false);
                    return;
            }
            m20796a(true);
        }
    }

    /* renamed from: f */
    private void m20783f(UiMessage.CommandToUi commandToUi) {
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (!color.matches(f8524g)) {
            m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20796a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int a = C1335c.m20658a(Color.parseColor(color));
        if (this.f8529d.get(controlId) != null) {
            this.f8526a[this.f8529d.get(controlId).intValue()].m20746a(a);
            m20796a(true);
            return;
        }
        View b = this.f8526a[this.f8527b].m20732b(controlId);
        if (b == null) {
            m20796a(false);
            return;
        }
        switch ((WidgetType) this.f8530e[this.f8527b].get(controlId)) {
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
                    m20796a(false);
                    return;
                }
            case SPINNER:
                if (commandToUi.getItemIndex() >= ((Spinner) b).getCount()) {
                    m20796a(false);
                    return;
                }
                break;
            default:
                m20796a(false);
                return;
        }
        m20796a(true);
    }

    /* renamed from: g */
    private void m20781g(UiMessage.CommandToUi commandToUi) {
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        View b = this.f8526a[this.f8527b].m20732b(controlId);
        if (b == null) {
            m20796a(false);
            return;
        }
        String color = commandToUi.getColor();
        if (!color.matches(f8524g)) {
            m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20796a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int a = C1335c.m20658a(Color.parseColor(color));
        if (this.f8530e[this.f8527b].get(controlId) == WidgetType.BUTTON) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(a);
            gradientDrawable.setCornerRadius(m20795b());
            if (Build.VERSION.SDK_INT >= 16) {
                b.setBackground(gradientDrawable);
            } else {
                b.setBackgroundDrawable(gradientDrawable);
            }
            m20796a(true);
            return;
        }
        if (this.f8530e[this.f8527b].get(controlId) != WidgetType.SPINNER) {
            b.setBackgroundColor(a);
        }
        m20796a(true);
    }

    /* renamed from: h */
    private void m20779h(UiMessage.CommandToUi commandToUi) {
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        View b = this.f8526a[this.f8527b].m20732b(commandToUi.getControlId());
        if (b == null) {
            m20796a(false);
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b.getLayoutParams();
        layoutParams.gravity = commandToUi.getAlignType();
        b.setLayoutParams(layoutParams);
        new StringBuilder("set gravity ok; ").append(layoutParams);
        m20796a(true);
    }

    /* renamed from: i */
    private void m20777i(UiMessage.CommandToUi commandToUi) {
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        View b = this.f8526a[this.f8527b].m20732b(commandToUi.getControlId());
        if (b == null) {
            m20796a(false);
            return;
        }
        UiMessage.Padding_Var padding = commandToUi.getPadding();
        new StringBuilder("padding: ").append(padding.getLeft());
        new StringBuilder("padding: ").append(padding.getTop());
        new StringBuilder("padding: ").append(padding.getRight());
        new StringBuilder("padding: ").append(padding.getBottom());
        b.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
        m20796a(true);
    }

    /* renamed from: g */
    private void m20780g(String str) {
        UiShowLayout[] kVarArr = this.f8526a;
        int i = this.f8527b;
        if (kVarArr[i] == null) {
            m20796a(false);
            return;
        }
        View b = kVarArr[i].m20732b(str);
        if (b == null) {
            m20796a(false);
            return;
        }
        boolean isEnabled = b.isEnabled();
        if (this.f8530e[this.f8527b].get(str) == WidgetType.EDIT_TEXT) {
            isEnabled = ((EditText) b).isFocusable();
        }
        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(isEnabled).build()).build().toByteString());
    }

    /* renamed from: h */
    private void m20778h(String str) {
        UiShowLayout[] kVarArr = this.f8526a;
        int i = this.f8527b;
        if (kVarArr[i] == null) {
            m20796a(false);
            return;
        }
        View b = kVarArr[i].m20732b(str);
        if (b == null) {
            m20796a(false);
            return;
        }
        int visibility = b.getVisibility();
        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(visibility != 0 ? visibility != 4 ? visibility != 8 ? 1 : 3 : 2 : 1).build()).build().toByteString());
    }

    /* renamed from: j */
    private void m20775j(UiMessage.CommandToUi commandToUi) {
        int i;
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f8530e[this.f8527b].get(controlId) == null) {
            m20796a(false);
            return;
        }
        switch ((WidgetType) this.f8530e[this.f8527b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                i = ((TextView) this.f8526a[this.f8527b].m20732b(controlId)).getPaint().getColor();
                break;
            case CHECK_BOX:
                i = ((CheckBox) this.f8526a[this.f8527b].m20732b(controlId)).getPaint().getColor();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f8526a[this.f8527b].m20732b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton != null) {
                    i = radioButton.getPaint().getColor();
                    break;
                } else {
                    m20796a(false);
                    return;
                }
            default:
                m20796a(false);
                return;
        }
        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.red(i)))).build()).build().toByteString());
    }

    /* renamed from: i */
    private void m20776i(String str) {
        UiShowLayout[] kVarArr = this.f8526a;
        int i = this.f8527b;
        if (kVarArr[i] == null) {
            m20796a(false);
            return;
        }
        View b = kVarArr[i].m20732b(str);
        if (b == null) {
            m20796a(false);
            return;
        }
        int a = C1335c.m20658a(b.getSolidColor());
        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue("#" + Integer.toHexString(a & ViewCompat.MEASURED_SIZE_MASK)).build()).build().toByteString());
    }

    /* renamed from: k */
    private void m20773k(UiMessage.CommandToUi commandToUi) {
        String str;
        if (this.f8526a[this.f8527b] == null) {
            m20796a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f8530e[this.f8527b].get(controlId) == null) {
            m20796a(false);
            return;
        }
        switch ((WidgetType) this.f8530e[this.f8527b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                str = ((TextView) this.f8526a[this.f8527b].m20732b(controlId)).getText().toString();
                break;
            case CHECK_BOX:
                str = ((CheckBox) this.f8526a[this.f8527b].m20732b(controlId)).getText().toString();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f8526a[this.f8527b].m20732b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton != null) {
                    str = radioButton.getText().toString();
                    break;
                } else {
                    m20796a(false);
                    return;
                }
            case SPINNER:
                Spinner spinner = (Spinner) this.f8526a[this.f8527b].m20732b(controlId);
                if (commandToUi.getItemIndex() < spinner.getCount()) {
                    new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                    str = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                    break;
                } else {
                    m20796a(false);
                    return;
                }
            default:
                m20796a(false);
                return;
        }
        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(str).build()).build().toByteString());
    }

    /* renamed from: a */
    public final void m20799a(String str) throws JSONException, IOException {
        if (this.f8526a[this.f8527b] != null) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : this.f8530e[this.f8527b].entrySet()) {
                String str2 = (String) entry.getKey();
                int i = C13133.f8538b[((WidgetType) entry.getValue()).ordinal()];
                if (i != 2) {
                    switch (i) {
                        case 4:
                            jSONObject.put(str2, ((CheckBox) this.f8526a[this.f8527b].m20732b(str2)).isChecked());
                            continue;
                        case 5:
                            jSONObject.put(str2, ((RadioGroup) this.f8526a[this.f8527b].m20732b(str2)).getCheckedRadioButtonId());
                            continue;
                        case 6:
                            jSONObject.put(str2, ((Spinner) this.f8526a[this.f8527b].m20732b(str2)).getSelectedItemId());
                            continue;
                    }
                } else {
                    jSONObject.put(str2, ((EditText) this.f8526a[this.f8527b].m20732b(str2)).getText().toString());
                }
            }
            FileUtils.writeStringToFile(new File(str), jSONObject.toString());
        }
    }

    /* renamed from: b */
    public final void m20792b(String str) throws IOException, JSONException {
        if (this.f8530e[this.f8527b] != null) {
            JSONObject jSONObject = new JSONObject(FileUtils.readFileToString(new File(str)));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (this.f8530e[this.f8527b].get(next) != null) {
                    int i = C13133.f8538b[((WidgetType) this.f8530e[this.f8527b].get(next)).ordinal()];
                    if (i != 2) {
                        switch (i) {
                            case 4:
                                ((CheckBox) this.f8526a[this.f8527b].m20732b(next)).setChecked(jSONObject.getBoolean(next));
                                continue;
                            case 5:
                                RadioGroup radioGroup = (RadioGroup) this.f8526a[this.f8527b].m20732b(next);
                                if (jSONObject.getInt(next) < radioGroup.getChildCount()) {
                                    ((RadioButton) radioGroup.findViewById(jSONObject.getInt(next))).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case 6:
                                Spinner spinner = (Spinner) this.f8526a[this.f8527b].m20732b(next);
                                if (jSONObject.getInt(next) < spinner.getCount()) {
                                    spinner.setSelection(jSONObject.getInt(next));
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    } else {
                        ((EditText) this.f8526a[this.f8527b].m20732b(next)).setText(jSONObject.getString(next));
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m20798a(String str, int i, int i2) {
        this.f8527b++;
        this.f8526a[this.f8527b] = this.f8528c.m20880a(str, i, i2);
        this.f8529d.put(str, Integer.valueOf(this.f8527b));
        this.f8530e[this.f8527b] = new HashMap();
        this.f8526a[this.f8527b].f8579b = new View$OnClickListenerC13111(str);
        this.f8526a[this.f8527b].f8578a = new View$OnClickListenerC13122(str);
    }

    /* compiled from: UiManagerLite.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.i$1 */
    /* loaded from: classes.dex */
    public final class View$OnClickListenerC13111 implements View.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ String f8533a;

        public View$OnClickListenerC13111(String str) {
            this.f8533a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f8533a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
        }
    }

    /* compiled from: UiManagerLite.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.i$2 */
    /* loaded from: classes.dex */
    public final class View$OnClickListenerC13122 implements View.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ String f8535a;

        public View$OnClickListenerC13122(String str) {
            this.f8535a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            UiMessage.UiToCommand build = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f8535a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_EXIT).build()).setIsSuccess(true).build();
            UiTransHelper.m21091b(build.toByteString());
            new StringBuilder("exit event: ").append(build);
        }
    }

    /* renamed from: j */
    private void m20774j(String str) {
        if (this.f8529d.get(str) == null) {
            m20802a(C1375R.string.ui_show_not_found_ui, str);
            m20796a(false);
        } else if (this.f8526a[this.f8529d.get(str).intValue()].f8580c) {
            m20796a(false);
        } else {
            this.f8526a[this.f8529d.get(str).intValue()].m20731c();
            m20796a(true);
            UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
        }
    }

    /* renamed from: a */
    private boolean m20797a(String str, String str2) {
        if (this.f8529d.get(str) == null) {
            m20802a(C1375R.string.ui_show_not_found_ui, str);
            return false;
        }
        this.f8526a[this.f8529d.get(str).intValue()].m20739a(str2);
        return true;
    }

    /* renamed from: k */
    private void m20772k(String str) {
        if (this.f8529d.get(str) == null) {
            m20802a(C1375R.string.ui_show_not_found_ui, str);
            m20796a(false);
            return;
        }
        UiTransHelper.m21093a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f8526a[this.f8529d.get(str).intValue()].m20747a()).build()).build().toByteString());
    }

    /* renamed from: l */
    private void m20771l(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (this.f8529d.get(controlId) == null) {
            m20802a(C1375R.string.ui_show_not_found_ui, controlId);
            m20796a(false);
        } else if (!color.matches(f8524g)) {
            m20802a(C1375R.string.ui_show_invalid_color_value, new Object[0]);
            m20796a(false);
        } else {
            if (!color.startsWith("#")) {
                color = "#" + color;
            }
            this.f8526a[this.f8529d.get(controlId).intValue()].m20735b(C1335c.m20658a(Color.parseColor(color)));
            m20796a(true);
        }
    }

    /* renamed from: c */
    public static void m20789c(String str) {
        UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
    }

    /* renamed from: d */
    public final boolean m20786d(String str) {
        if (this.f8529d.get(str) == null) {
            m20802a(C1375R.string.ui_show_not_found_ui, str);
            return false;
        } else if (!this.f8526a[this.f8529d.get(str).intValue()].f8580c) {
            return false;
        } else {
            this.f8526a[this.f8529d.get(str).intValue()].m20726e();
            int intValue = this.f8529d.get(str).intValue();
            int i = this.f8527b;
            if (intValue == i) {
                this.f8527b = i - 1;
                while (true) {
                    int i2 = this.f8527b;
                    if (i2 <= 0 || this.f8526a[i2] != null) {
                        break;
                    }
                    this.f8527b = i2 - 1;
                }
                if (this.f8527b < 0) {
                    this.f8527b = 0;
                }
            }
            return true;
        }
    }

    /* renamed from: c */
    public final void m20791c() {
        for (int i = 31; i > 0; i--) {
            UiShowLayout kVar = this.f8526a[i];
            if (kVar != null && kVar.f8580c) {
                kVar.m20726e();
            }
        }
    }

    /* renamed from: a */
    public final void m20802a(int i, Object... objArr) {
        Context context = this.f8532i;
        ExToast.makeText(context, String.format(context.getString(i), objArr), 3500).show();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) view.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* renamed from: e */
    public final void m20784e(String str) {
        UiMessage.UiToCommand.Builder isSuccess = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true);
        UiMessage.ControlVar.Builder controlId = UiMessage.ControlVar.newBuilder().setControlId(str);
        WidgetType mVar = null;
        int i = 0;
        while (true) {
            if (i >= 32) {
                i = 0;
                break;
            }
            HashMap[] hashMapArr = this.f8530e;
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
            m20802a(C1375R.string.ui_show_not_found_widget, str);
            UiTransHelper.m21093a(isSuccess.setIsSuccess(false).build().toByteString());
            return;
        }
        switch (mVar) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                controlId.setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(((TextView) this.f8526a[i].m20732b(str)).getText().toString());
                isSuccess.addVarTable(controlId.build());
                break;
            case CHECK_BOX:
                controlId.setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(((CheckBox) this.f8526a[i].m20732b(str)).isChecked());
                isSuccess.addVarTable(controlId.build());
                break;
            case RADIIO_GROUP:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((RadioGroup) this.f8526a[i].m20732b(str)).getCheckedRadioButtonId());
                isSuccess.addVarTable(controlId.build());
                break;
            case SPINNER:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((Spinner) this.f8526a[i].m20732b(str)).getSelectedItemPosition());
                isSuccess.addVarTable(controlId.build());
                break;
            default:
                isSuccess.setIsSuccess(false);
                break;
        }
        new StringBuilder("Ui.getValue result: ").append(isSuccess.build());
        UiTransHelper.m21093a(isSuccess.build().toByteString());
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) adapterView.getTag()).setEventArgs(String.valueOf(i)).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UiManagerLite.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.i$a */
    /* loaded from: classes.dex */
    public class C1314a implements TextWatcher {

        /* renamed from: b */
        private String f8540b;

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public C1314a(String str) {
            this.f8540b = str;
        }

        /* renamed from: a */
        private String m20770a() {
            return this.f8540b;
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            UiTransHelper.m21091b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f8540b).setType(UiMessage.ControlEvent.Event_Type.ON_CHANGE).build()).setIsSuccess(true).build().toByteString());
            UiManagerLite.this.f8526a[UiManagerLite.this.f8527b].m20736b();
        }
    }
}
