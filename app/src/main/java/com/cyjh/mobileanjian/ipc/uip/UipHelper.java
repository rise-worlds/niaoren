package com.cyjh.mobileanjian.ipc.uip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.JsonReader;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mqsdk.C1375R;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.p105io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UipHelper implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final int UIP_MESSAGE = 0;

    /* renamed from: a */
    private static final String f8619a = new File(Environment.getExternalStorageDirectory(), ".uip.config.json").getAbsolutePath();

    /* renamed from: b */
    private Context f8620b;

    /* renamed from: c */
    private SlidingTabLayout f8621c;

    /* renamed from: d */
    private CircleIndicator f8622d;

    /* renamed from: e */
    private HSettingLayout f8623e;

    /* renamed from: f */
    private List<ScrollView> f8624f;

    /* renamed from: g */
    private boolean f8625g;

    /* renamed from: h */
    private Handler f8626h = new Handler() { // from class: com.cyjh.mobileanjian.ipc.uip.UipHelper.2
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what == 0) {
                try {
                    UiMessage.CommandToUip parseFrom = UiMessage.CommandToUip.parseFrom((ByteString) message.obj);
                    UiMessage.CommandToUip.Command_Type command = parseFrom.getCommand();
                    String controlId = parseFrom.getControlId();
                    new StringBuilder("GET UIP Command -->").append(parseFrom);
                    if (command == UiMessage.CommandToUip.Command_Type.GET_UIP_ATTRIBUTE) {
                        UipHelper.m20715a(UipHelper.this, controlId);
                    } else if (command == UiMessage.CommandToUip.Command_Type.SET_UIP_ATTRIBUTE) {
                        UipHelper.m20714a(UipHelper.this, controlId, parseFrom.getUipAttributeData());
                    }
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    };

    /* renamed from: i */
    private JSONObject f8627i;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public UipHelper(Context context) {
        this.f8620b = context;
    }

    public LinearLayout parseLayoutFromJson(String str, IUipJsonParser iUipJsonParser) {
        if (this.f8620b.getResources().getConfiguration().orientation == 2) {
            return m20708c(str, iUipJsonParser);
        }
        return m20709b(str, iUipJsonParser);
    }

    /* renamed from: a */
    private LinearLayout m20712a(String str, IUipJsonParser iUipJsonParser) {
        if (iUipJsonParser == null) {
            iUipJsonParser = new DefaultUipJsonParser(this.f8620b);
        }
        LinearLayout linearLayout = new LinearLayout(this.f8620b);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        ViewPager viewPager = new ViewPager(this.f8620b);
        TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        viewPager.setLayoutParams(layoutParams2);
        linearLayout.addView(viewPager);
        this.f8622d = new CircleIndicator(this.f8620b);
        this.f8622d.setLayoutParams(new LinearLayout.LayoutParams(-2, this.f8622d.m20703a(20.0f)));
        CircleIndicator aVar = this.f8622d;
        aVar.setPadding(0, aVar.m20703a(10.0f), 0, 0);
        linearLayout.addView(this.f8622d);
        List<ScrollView> arrayList = new ArrayList<>(32);
        ArrayList arrayList2 = new ArrayList(32);
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext() && jsonReader.nextName().startsWith(this.f8620b.getString(C1375R.string.ui_activity))) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String nextName = jsonReader.nextName();
                    LinearLayout linearLayout2 = new LinearLayout(this.f8620b);
                    linearLayout2.setOrientation(1);
                    linearLayout2.setLayoutParams(layoutParams);
                    ScrollView scrollView = new ScrollView(this.f8620b);
                    scrollView.setTag(nextName);
                    scrollView.setLayoutParams(layoutParams);
                    scrollView.addView(linearLayout2);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName2 = jsonReader.nextName();
                        if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_linearlayout))) {
                            linearLayout2.addView(iUipJsonParser.parseHorizontalLinearLayout(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_textview))) {
                            linearLayout2.addView(iUipJsonParser.parseTextView(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_button))) {
                            linearLayout2.addView(iUipJsonParser.parseButton(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_edittext))) {
                            linearLayout2.addView(iUipJsonParser.parseEditText(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_checkbox))) {
                            linearLayout2.addView(iUipJsonParser.parseCheckBox(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_spinner))) {
                            linearLayout2.addView(iUipJsonParser.parseSpinner(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList.size() < 32) {
                        arrayList2.add(nextName);
                        arrayList.add(scrollView);
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
        viewPager.setAdapter(new UipPagerAdapter(arrayList, arrayList2));
        this.f8622d.setViewPager(viewPager);
        bindEvent(arrayList);
        saveToConfigFile(f8619a);
        return linearLayout;
    }

    /* renamed from: b */
    private LinearLayout m20709b(String str, IUipJsonParser iUipJsonParser) {
        if (iUipJsonParser == null) {
            iUipJsonParser = new DefaultUipJsonParser(this.f8620b);
        }
        LinearLayout linearLayout = new LinearLayout(this.f8620b);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        this.f8621c = new SlidingTabLayout(this.f8620b);
        this.f8621c.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f8621c.setSelectedIndicatorColors(this.f8620b.getResources().getColor(C1375R.color.ui_show_blue));
        this.f8621c.setDistributeEvenly(true);
        linearLayout.addView(this.f8621c);
        ViewPager viewPager = new ViewPager(this.f8620b);
        TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        viewPager.setLayoutParams(layoutParams2);
        linearLayout.addView(viewPager);
        List<ScrollView> arrayList = new ArrayList<>(32);
        ArrayList arrayList2 = new ArrayList(32);
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext() && jsonReader.nextName().startsWith(this.f8620b.getString(C1375R.string.ui_activity))) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String nextName = jsonReader.nextName();
                    LinearLayout linearLayout2 = new LinearLayout(this.f8620b);
                    linearLayout2.setOrientation(1);
                    linearLayout2.setLayoutParams(layoutParams);
                    ScrollView scrollView = new ScrollView(this.f8620b);
                    scrollView.setTag(nextName);
                    scrollView.setLayoutParams(layoutParams);
                    scrollView.addView(linearLayout2);
                    scrollView.setDescendantFocusability(131072);
                    scrollView.setFocusable(true);
                    scrollView.setFocusableInTouchMode(true);
                    scrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyjh.mobileanjian.ipc.uip.UipHelper.1
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            view.requestFocusFromTouch();
                            return false;
                        }
                    });
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName2 = jsonReader.nextName();
                        if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_linearlayout))) {
                            linearLayout2.addView(iUipJsonParser.parseHorizontalLinearLayout(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_textview))) {
                            linearLayout2.addView(iUipJsonParser.parseTextView(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_button))) {
                            linearLayout2.addView(iUipJsonParser.parseButton(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_edittext))) {
                            linearLayout2.addView(iUipJsonParser.parseEditText(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_checkbox))) {
                            linearLayout2.addView(iUipJsonParser.parseCheckBox(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_spinner))) {
                            linearLayout2.addView(iUipJsonParser.parseSpinner(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList.size() < 32) {
                        arrayList2.add(nextName);
                        arrayList.add(scrollView);
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
        viewPager.setAdapter(new UipPagerAdapter(arrayList, arrayList2));
        this.f8621c.setViewPager(viewPager);
        bindEvent(arrayList);
        saveToConfigFile(f8619a);
        return linearLayout;
    }

    /* renamed from: c */
    private LinearLayout m20708c(String str, IUipJsonParser iUipJsonParser) {
        if (iUipJsonParser == null) {
            iUipJsonParser = new DefaultUipJsonParser(this.f8620b);
        }
        this.f8623e = new HSettingLayout(this.f8620b);
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext() && jsonReader.nextName().startsWith(this.f8620b.getString(C1375R.string.ui_activity))) {
                jsonReader.beginObject();
                for (int i = 0; i < 32 && jsonReader.hasNext(); i++) {
                    String nextName = jsonReader.nextName();
                    HSettingLayout cVar = this.f8623e;
                    RadioButton radioButton = new RadioButton(cVar.getContext());
                    radioButton.setId(HSettingLayout.f8661a[cVar.f8668g]);
                    radioButton.setPadding(0, 0, 0, cVar.f8665d);
                    radioButton.setButtonDrawable(new BitmapDrawable((Bitmap) null));
                    radioButton.setTextColor(cVar.getResources().getColorStateList(C1375R.color.selector_tab));
                    radioButton.setText(nextName);
                    radioButton.setLines(1);
                    radioButton.setEllipsize(TextUtils.TruncateAt.END);
                    cVar.f8663b.addView(radioButton);
                    cVar.f8667f = new ScrollView(cVar.getContext());
                    cVar.f8667f.setDescendantFocusability(131072);
                    cVar.f8667f.setFocusable(true);
                    cVar.f8667f.setFocusableInTouchMode(true);
                    cVar.f8667f.setOnTouchListener(new HSettingLayout.View$OnTouchListenerC13331());
                    cVar.f8667f.setTag(nextName);
                    cVar.f8667f.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                    LinearLayout linearLayout = new LinearLayout(cVar.getContext());
                    linearLayout.setOrientation(1);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                    cVar.f8666e = linearLayout;
                    cVar.f8667f.addView(cVar.f8666e);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName2 = jsonReader.nextName();
                        if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_linearlayout))) {
                            this.f8623e.m20681a(iUipJsonParser.parseHorizontalLinearLayout(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_textview))) {
                            this.f8623e.m20681a(iUipJsonParser.parseTextView(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_button))) {
                            this.f8623e.m20681a(iUipJsonParser.parseButton(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_edittext))) {
                            this.f8623e.m20681a(iUipJsonParser.parseEditText(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_checkbox))) {
                            this.f8623e.m20681a(iUipJsonParser.parseCheckBox(jsonReader));
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_spinner))) {
                            this.f8623e.m20681a(iUipJsonParser.parseSpinner(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    HSettingLayout cVar2 = this.f8623e;
                    cVar2.f8664c.add(cVar2.f8667f);
                    cVar2.f8668g++;
                }
                jsonReader.endObject();
            }
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
        this.f8623e.setSelectTab(0);
        bindEvent(this.f8623e.getScrollViewList());
        saveToConfigFile(f8619a);
        return this.f8623e;
    }

    public View transform(int i) {
        for (ScrollView scrollView : this.f8624f) {
            ViewParent parent = scrollView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(scrollView);
            }
            new StringBuilder("childCount of scrollview: ").append(((ViewGroup) scrollView.getChildAt(0)).getChildCount());
        }
        if (i == 2) {
            this.f8623e = new HSettingLayout(this.f8620b);
            this.f8623e.m20679a(this.f8624f);
            this.f8623e.setSelectTab(this.f8621c.getViewPager().getCurrentItem());
            return this.f8623e;
        }
        ArrayList arrayList = new ArrayList();
        for (ScrollView scrollView2 : this.f8624f) {
            arrayList.add((String) scrollView2.getTag());
        }
        LinearLayout linearLayout = new LinearLayout(this.f8620b);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        this.f8621c = new SlidingTabLayout(this.f8620b);
        this.f8621c.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f8621c.setSelectedIndicatorColors(this.f8620b.getResources().getColor(C1375R.color.ui_show_blue));
        this.f8621c.setDistributeEvenly(true);
        linearLayout.addView(this.f8621c);
        ViewPager viewPager = new ViewPager(this.f8620b);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(-1, -1);
        layoutParams.weight = 1.0f;
        viewPager.setLayoutParams(layoutParams);
        linearLayout.addView(viewPager);
        viewPager.setAdapter(new UipPagerAdapter(this.f8624f, arrayList));
        viewPager.setCurrentItem(this.f8623e.getCurrentItem());
        this.f8621c.setViewPager(viewPager);
        return linearLayout;
    }

    public void bindEvent(List<ScrollView> list) {
        UipEventStub.registerHandler(this.f8626h);
        this.f8624f = list;
        for (ScrollView scrollView : list) {
            m20716a(scrollView);
        }
    }

    /* renamed from: a */
    private void m20716a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof FlowLayout) || (childAt instanceof LinearLayout)) {
                m20716a((ViewGroup) childAt);
            } else if (!TextUtils.isEmpty((CharSequence) childAt.getTag(C1375R.C1377id.uip_function_key))) {
                if (childAt instanceof Spinner) {
                    ((Spinner) childAt).setOnItemSelectedListener(this);
                } else if (childAt instanceof CheckBox) {
                    ((CheckBox) childAt).setOnClickListener(this);
                } else if (childAt instanceof Button) {
                    ((Button) childAt).setOnClickListener(this);
                }
            }
        }
    }

    public void restoreConfig() {
        configViewFromFile(f8619a);
    }

    /* renamed from: a */
    private void m20713a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Iterator<ScrollView> it = this.f8624f.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            View findViewWithTag = it.next().findViewWithTag(str);
            if (!(findViewWithTag instanceof Spinner)) {
                if (!(findViewWithTag instanceof CheckBox)) {
                    if (!(findViewWithTag instanceof Button)) {
                        if (!(findViewWithTag instanceof EditText)) {
                            if (findViewWithTag instanceof TextView) {
                                jSONObject.put(this.f8620b.getString(C1375R.string.ui_textview_textcontent), ((TextView) findViewWithTag).getText().toString());
                                break;
                            }
                        } else {
                            jSONObject.put(this.f8620b.getString(C1375R.string.ui_edittext_defaultcontent), ((EditText) findViewWithTag).getText().toString());
                            break;
                        }
                    } else {
                        jSONObject.put(this.f8620b.getString(C1375R.string.ui_textview_textcontent), ((Button) findViewWithTag).getText().toString());
                        break;
                    }
                } else {
                    CheckBox checkBox = (CheckBox) findViewWithTag;
                    jSONObject.put(this.f8620b.getString(C1375R.string.ui_checkbox_hintcontent), checkBox.getText().toString());
                    jSONObject.put(this.f8620b.getString(C1375R.string.ui_checkbox_checked), checkBox.isChecked());
                    break;
                }
            } else {
                Spinner spinner = (Spinner) findViewWithTag;
                jSONObject.put(this.f8620b.getString(C1375R.string.ui_spinner_defaultitem), spinner.getSelectedItemPosition());
                List<String> list = ((StringArrayAdapter) spinner.getAdapter()).f8673a;
                JSONArray jSONArray = new JSONArray();
                for (String str2 : list) {
                    jSONArray.put(str2);
                }
                jSONObject.put(this.f8620b.getString(C1375R.string.ui_spinner_items), jSONArray);
            }
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).setUipAttributeData(jSONObject.toString()).build().toByteString());
    }

    /* renamed from: a */
    private void m20711a(String str, String str2) {
        JsonReader jsonReader = new JsonReader(new StringReader(str2));
        try {
            Iterator<ScrollView> it = this.f8624f.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                View findViewWithTag = it.next().findViewWithTag(str);
                if (findViewWithTag instanceof Spinner) {
                    Spinner spinner = (Spinner) findViewWithTag;
                    int i = -1;
                    ArrayList arrayList = null;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName = jsonReader.nextName();
                        if (nextName.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_spinner_defaultitem))) {
                            i = jsonReader.nextInt();
                        } else if (nextName.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_spinner_items))) {
                            arrayList = new ArrayList();
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                arrayList.add(jsonReader.nextString());
                            }
                            jsonReader.endArray();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList != null) {
                        StringArrayAdapter eVar = new StringArrayAdapter(this.f8620b, arrayList);
                        eVar.setDropDownViewResource(17367049);
                        spinner.setAdapter((SpinnerAdapter) eVar);
                    }
                    if (i >= 0 && i < spinner.getCount()) {
                        spinner.setSelection(i);
                    }
                } else if (findViewWithTag instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName2 = jsonReader.nextName();
                        if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_checkbox_hintcontent))) {
                            checkBox.setText(jsonReader.nextString());
                        } else if (nextName2.equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_checkbox_checked))) {
                            checkBox.setChecked(jsonReader.nextBoolean());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (findViewWithTag instanceof Button) {
                    Button button = (Button) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_textview_textcontent))) {
                            button.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (findViewWithTag instanceof EditText) {
                    EditText editText = (EditText) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_edittext_defaultcontent))) {
                            editText.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (findViewWithTag instanceof TextView) {
                    TextView textView = (TextView) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(this.f8620b.getString(C1375R.string.ui_textview_textcontent))) {
                            textView.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).build().toByteString());
    }

    public void configViewFromFile(String str) {
        try {
            configViewFromJson(FileUtils.readFileToString(new File(str)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void configViewFromJson(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f8625g = true;
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    for (ScrollView scrollView : this.f8624f) {
                        View findViewWithTag = scrollView.findViewWithTag(next);
                        if (findViewWithTag != null) {
                            if (findViewWithTag instanceof CheckBox) {
                                ((CheckBox) findViewWithTag).setChecked(jSONObject.getBoolean(next));
                            } else if (findViewWithTag instanceof Spinner) {
                                Spinner spinner = (Spinner) findViewWithTag;
                                spinner.setSelection(jSONObject.getInt(next) < spinner.getAdapter().getCount() ? jSONObject.getInt(next) : 0);
                            } else if (findViewWithTag instanceof EditText) {
                                ((EditText) findViewWithTag).setText(jSONObject.getString(next));
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f8625g = false;
        }
    }

    public String saveConfigToJson() {
        this.f8627i = new JSONObject();
        for (ScrollView scrollView : this.f8624f) {
            try {
                m20710b((LinearLayout) scrollView.getChildAt(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this.f8627i.toString();
    }

    public void saveToConfigFile(String str) {
        try {
            FileUtils.writeStringToFile(new File(str), saveConfigToJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m20710b(ViewGroup viewGroup) throws JSONException {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            String str = (String) childAt.getTag();
            if ((childAt instanceof LinearLayout) || (childAt instanceof FlowLayout)) {
                m20710b((ViewGroup) childAt);
            } else if (childAt instanceof CheckBox) {
                this.f8627i.put(str, ((CheckBox) childAt).isChecked());
            } else if (childAt instanceof Spinner) {
                this.f8627i.put(str, ((Spinner) childAt).getSelectedItemPosition());
            } else if (childAt instanceof EditText) {
                this.f8627i.put(str, ((EditText) childAt).getText().toString());
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.f8625g) {
            UipEventStub.hasEvent(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) view.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).setEventFunctionName((String) view.getTag(C1375R.C1377id.uip_function_key)).build()).build().toByteString());
            new StringBuilder("onClick Function Name: ").append((String) view.getTag(C1375R.C1377id.uip_function_key));
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        new StringBuilder("isCodeEvent: ").append(this.f8625g);
        if (!this.f8625g) {
            UiMessage.ControlEvent build = UiMessage.ControlEvent.newBuilder().setControlId((String) adapterView.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).setEventArgs(String.valueOf(i)).setEventFunctionName((String) adapterView.getTag(C1375R.C1377id.uip_function_key)).build();
            UipEventStub.hasEvent(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(build).build().toByteString());
            new StringBuilder("onItemSelected: ").append(build);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m20715a(UipHelper uipHelper, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Iterator<ScrollView> it = uipHelper.f8624f.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            View findViewWithTag = it.next().findViewWithTag(str);
            if (!(findViewWithTag instanceof Spinner)) {
                if (!(findViewWithTag instanceof CheckBox)) {
                    if (!(findViewWithTag instanceof Button)) {
                        if (!(findViewWithTag instanceof EditText)) {
                            if (findViewWithTag instanceof TextView) {
                                jSONObject.put(uipHelper.f8620b.getString(C1375R.string.ui_textview_textcontent), ((TextView) findViewWithTag).getText().toString());
                                break;
                            }
                        } else {
                            jSONObject.put(uipHelper.f8620b.getString(C1375R.string.ui_edittext_defaultcontent), ((EditText) findViewWithTag).getText().toString());
                            break;
                        }
                    } else {
                        jSONObject.put(uipHelper.f8620b.getString(C1375R.string.ui_textview_textcontent), ((Button) findViewWithTag).getText().toString());
                        break;
                    }
                } else {
                    CheckBox checkBox = (CheckBox) findViewWithTag;
                    jSONObject.put(uipHelper.f8620b.getString(C1375R.string.ui_checkbox_hintcontent), checkBox.getText().toString());
                    jSONObject.put(uipHelper.f8620b.getString(C1375R.string.ui_checkbox_checked), checkBox.isChecked());
                    break;
                }
            } else {
                Spinner spinner = (Spinner) findViewWithTag;
                jSONObject.put(uipHelper.f8620b.getString(C1375R.string.ui_spinner_defaultitem), spinner.getSelectedItemPosition());
                List<String> list = ((StringArrayAdapter) spinner.getAdapter()).f8673a;
                JSONArray jSONArray = new JSONArray();
                for (String str2 : list) {
                    jSONArray.put(str2);
                }
                jSONObject.put(uipHelper.f8620b.getString(C1375R.string.ui_spinner_items), jSONArray);
            }
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).setUipAttributeData(jSONObject.toString()).build().toByteString());
    }

    /* renamed from: a */
    static /* synthetic */ void m20714a(UipHelper uipHelper, String str, String str2) {
        JsonReader jsonReader = new JsonReader(new StringReader(str2));
        try {
            Iterator<ScrollView> it = uipHelper.f8624f.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                View findViewWithTag = it.next().findViewWithTag(str);
                if (findViewWithTag instanceof Spinner) {
                    Spinner spinner = (Spinner) findViewWithTag;
                    int i = -1;
                    ArrayList arrayList = null;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName = jsonReader.nextName();
                        if (nextName.equalsIgnoreCase(uipHelper.f8620b.getString(C1375R.string.ui_spinner_defaultitem))) {
                            i = jsonReader.nextInt();
                        } else if (nextName.equalsIgnoreCase(uipHelper.f8620b.getString(C1375R.string.ui_spinner_items))) {
                            arrayList = new ArrayList();
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                arrayList.add(jsonReader.nextString());
                            }
                            jsonReader.endArray();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList != null) {
                        StringArrayAdapter eVar = new StringArrayAdapter(uipHelper.f8620b, arrayList);
                        eVar.setDropDownViewResource(17367049);
                        spinner.setAdapter((SpinnerAdapter) eVar);
                    }
                    if (i >= 0 && i < spinner.getCount()) {
                        spinner.setSelection(i);
                    }
                } else if (findViewWithTag instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName2 = jsonReader.nextName();
                        if (nextName2.equalsIgnoreCase(uipHelper.f8620b.getString(C1375R.string.ui_checkbox_hintcontent))) {
                            checkBox.setText(jsonReader.nextString());
                        } else if (nextName2.equalsIgnoreCase(uipHelper.f8620b.getString(C1375R.string.ui_checkbox_checked))) {
                            checkBox.setChecked(jsonReader.nextBoolean());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (findViewWithTag instanceof Button) {
                    Button button = (Button) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(uipHelper.f8620b.getString(C1375R.string.ui_textview_textcontent))) {
                            button.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (findViewWithTag instanceof EditText) {
                    EditText editText = (EditText) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(uipHelper.f8620b.getString(C1375R.string.ui_edittext_defaultcontent))) {
                            editText.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (findViewWithTag instanceof TextView) {
                    TextView textView = (TextView) findViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(uipHelper.f8620b.getString(C1375R.string.ui_textview_textcontent))) {
                            textView.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).build().toByteString());
    }
}
