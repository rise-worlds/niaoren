package p110z1;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mqm.MiscUtilities;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.p105io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: z1.abz */
/* loaded from: classes3.dex */
public class ScriptUiModel implements IScriptUiModel {

    /* renamed from: a */
    protected ArrayList<CheckBox> f15142a = new ArrayList<>();

    /* renamed from: b */
    protected ArrayList<Spinner> f15143b = new ArrayList<>();

    /* renamed from: c */
    protected ArrayList<EditText> f15144c = new ArrayList<>();

    /* renamed from: d */
    private Context f15145d;

    /* renamed from: e */
    private File f15146e;

    /* renamed from: f */
    private File f15147f;

    /* renamed from: g */
    private String f15148g;

    public ScriptUiModel(Context context, String str, String str2) {
        this.f15146e = new File(str);
        this.f15147f = new File(str2);
        this.f15145d = context;
        Log.e("a111111", "ScriptUiModel " + str);
    }

    public ScriptUiModel(Context context) {
        this.f15145d = context;
    }

    @Override // p110z1.IScriptUiModel
    /* renamed from: a */
    public LinearLayout mo14399a() throws Exception {
        if (!this.f15146e.exists()) {
            return new LinearLayout(this.f15145d);
        }
        String LoadUIFile = new MiscUtilities().LoadUIFile(this.f15146e.getAbsolutePath(), true);
        Log.e("a111111", "ScriptUiModel parseui begin");
        LinearLayout b = m14406b(LoadUIFile);
        Log.e("a111111", "ScriptUiModel end");
        return b;
    }

    @Override // p110z1.IScriptUiModel
    /* renamed from: a */
    public void mo14398a(String str) {
        this.f15148g = str;
    }

    @Override // p110z1.IScriptUiModel
    /* renamed from: b */
    public void mo14397b() throws JSONException {
        try {
            m14407b(this.f15147f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p110z1.IScriptUiModel
    /* renamed from: c */
    public String mo14396c() throws Exception {
        return m14409a(this.f15147f);
    }

    /* renamed from: b */
    private LinearLayout m14406b(String str) {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        LinearLayout linearLayout = new LinearLayout(this.f15145d);
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.nextName().startsWith(this.f15145d.getString(ResourceUtil.m14395a(this.f15145d, "string", "ui_activity")))) {
                    linearLayout = m14410a(jsonReader);
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            jsonReader.close();
        } catch (Exception unused) {
        }
        return linearLayout;
    }

    /* renamed from: a */
    private LinearLayout m14410a(JsonReader jsonReader) throws IOException {
        LinearLayout linearLayout = new LinearLayout(this.f15145d);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Context context = this.f15145d;
            if (context.getString(ResourceUtil.m14395a(context, "string", "ui_linearlayout")).equalsIgnoreCase(nextName)) {
                linearLayout.addView(m14408b(jsonReader));
            } else {
                Context context2 = this.f15145d;
                if (context2.getString(ResourceUtil.m14395a(context2, "string", "ui_textview")).equalsIgnoreCase(nextName)) {
                    linearLayout.addView(m14405c(jsonReader));
                } else {
                    Context context3 = this.f15145d;
                    if (context3.getString(ResourceUtil.m14395a(context3, "string", "ui_edittext")).equalsIgnoreCase(nextName)) {
                        linearLayout.addView(m14404d(jsonReader));
                    } else {
                        Context context4 = this.f15145d;
                        if (context4.getString(ResourceUtil.m14395a(context4, "string", "ui_checkbox")).equals(nextName)) {
                            linearLayout.addView(m14403e(jsonReader));
                        } else {
                            Context context5 = this.f15145d;
                            if (context5.getString(ResourceUtil.m14395a(context5, "string", "ui_spinner")).equalsIgnoreCase(nextName)) {
                                linearLayout.addView(m14402f(jsonReader));
                            }
                        }
                    }
                }
            }
        }
        jsonReader.endObject();
        return linearLayout;
    }

    /* renamed from: b */
    private LinearLayout m14408b(JsonReader jsonReader) throws IOException {
        LinearLayout linearLayout = new LinearLayout(this.f15145d);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Context context = this.f15145d;
            if (context.getString(ResourceUtil.m14395a(context, "string", "ui_linearlayout")).equalsIgnoreCase(nextName)) {
                linearLayout.addView(m14408b(jsonReader));
            } else {
                Context context2 = this.f15145d;
                if (context2.getString(ResourceUtil.m14395a(context2, "string", "ui_textview")).equalsIgnoreCase(nextName)) {
                    linearLayout.addView(m14405c(jsonReader));
                } else {
                    Context context3 = this.f15145d;
                    if (context3.getString(ResourceUtil.m14395a(context3, "string", "ui_edittext")).equalsIgnoreCase(nextName)) {
                        linearLayout.addView(m14404d(jsonReader));
                    } else {
                        Context context4 = this.f15145d;
                        if (context4.getString(ResourceUtil.m14395a(context4, "string", "ui_checkbox")).equals(nextName)) {
                            linearLayout.addView(m14403e(jsonReader));
                        } else {
                            Context context5 = this.f15145d;
                            if (context5.getString(ResourceUtil.m14395a(context5, "string", "ui_spinner")).equalsIgnoreCase(nextName)) {
                                linearLayout.addView(m14402f(jsonReader));
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                    }
                }
            }
        }
        jsonReader.endObject();
        return linearLayout;
    }

    /* renamed from: c */
    private TextView m14405c(JsonReader jsonReader) throws IOException {
        TextView textView = new TextView(this.f15145d);
        textView.setGravity(17);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize(m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textSize_normal")));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Context context = this.f15145d;
            if (context.getString(ResourceUtil.m14395a(context, "string", "ui_name")).equalsIgnoreCase(nextName)) {
                textView.setTag(jsonReader.nextString());
            } else {
                Context context2 = this.f15145d;
                if (context2.getString(ResourceUtil.m14395a(context2, "string", "ui_textview_textcontent")).equalsIgnoreCase(nextName)) {
                    textView.setText(jsonReader.nextString());
                } else {
                    Context context3 = this.f15145d;
                    if (context3.getString(ResourceUtil.m14395a(context3, "string", "ui_textsize")).equalsIgnoreCase(nextName)) {
                        int nextInt = jsonReader.nextInt();
                        if (nextInt > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textsize_min"))) {
                            textView.setTextSize(nextInt);
                        } else if (nextInt != m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textSize_default"))) {
                            textView.setTextSize(m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textsize_min")));
                        }
                    } else {
                        Context context4 = this.f15145d;
                        if (context4.getString(ResourceUtil.m14395a(context4, "string", "ui_layout_height")).equalsIgnoreCase(nextName)) {
                            int nextInt2 = jsonReader.nextInt();
                            if (nextInt2 > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_default_height"))) {
                                textView.setHeight(nextInt2);
                            }
                        } else {
                            Context context5 = this.f15145d;
                            if (context5.getString(ResourceUtil.m14395a(context5, "string", "ui_layout_width")).equalsIgnoreCase(nextName)) {
                                int nextInt3 = jsonReader.nextInt();
                                if (nextInt3 > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_default_width"))) {
                                    textView.setWidth(nextInt3);
                                }
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                    }
                }
            }
        }
        jsonReader.endObject();
        return textView;
    }

    /* renamed from: d */
    private EditText m14404d(JsonReader jsonReader) throws IOException {
        EditText editText = new EditText(this.f15145d);
        editText.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        editText.setTextSize(m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textSize_normal")));
        editText.setSingleLine(true);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Context context = this.f15145d;
            if (context.getString(ResourceUtil.m14395a(context, "string", "ui_name")).equalsIgnoreCase(nextName)) {
                editText.setTag(jsonReader.nextString());
            } else {
                Context context2 = this.f15145d;
                if (context2.getString(ResourceUtil.m14395a(context2, "string", "ui_edittext_hintcontent")).equalsIgnoreCase(nextName)) {
                    editText.setHint(jsonReader.nextString());
                } else {
                    Context context3 = this.f15145d;
                    if (context3.getString(ResourceUtil.m14395a(context3, "string", "ui_textsize")).equalsIgnoreCase(nextName)) {
                        int nextInt = jsonReader.nextInt();
                        if (nextInt > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textsize_min"))) {
                            editText.setTextSize(nextInt);
                        } else if (nextInt != m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textSize_default"))) {
                            editText.setTextSize(m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textsize_min")));
                        }
                    } else {
                        Context context4 = this.f15145d;
                        if (context4.getString(ResourceUtil.m14395a(context4, "string", "ui_layout_height")).equalsIgnoreCase(nextName)) {
                            int nextInt2 = jsonReader.nextInt();
                            if (nextInt2 > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_default_height"))) {
                                editText.setHeight(nextInt2);
                            }
                        } else {
                            Context context5 = this.f15145d;
                            if (context5.getString(ResourceUtil.m14395a(context5, "string", "ui_layout_width")).equalsIgnoreCase(nextName)) {
                                int nextInt3 = jsonReader.nextInt();
                                if (nextInt3 > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_default_width"))) {
                                    editText.setWidth(nextInt3);
                                }
                            } else {
                                Context context6 = this.f15145d;
                                boolean z = false;
                                if (context6.getString(ResourceUtil.m14395a(context6, "string", "ui_edittext_maxlength")).equalsIgnoreCase(nextName)) {
                                    int nextInt4 = jsonReader.nextInt();
                                    if (nextInt4 > 0) {
                                        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(nextInt4)});
                                    }
                                } else {
                                    Context context7 = this.f15145d;
                                    if (context7.getString(ResourceUtil.m14395a(context7, "string", "ui_edittext_inputnumber")).equalsIgnoreCase(nextName)) {
                                        try {
                                            z = jsonReader.nextBoolean();
                                        } catch (Exception unused) {
                                        }
                                        if (z) {
                                            editText.setInputType(2);
                                        }
                                    } else {
                                        Context context8 = this.f15145d;
                                        if (context8.getString(ResourceUtil.m14395a(context8, "string", "ui_edittext_defaultcontent")).equalsIgnoreCase(nextName)) {
                                            editText.setText(jsonReader.nextString());
                                        } else {
                                            jsonReader.skipValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.f15144c.add(editText);
        jsonReader.endObject();
        return editText;
    }

    /* renamed from: e */
    private CheckBox m14403e(JsonReader jsonReader) throws IOException {
        CheckBox checkBox = new CheckBox(this.f15145d);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        checkBox.setTextSize(m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textSize_normal")));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Context context = this.f15145d;
            if (context.getString(ResourceUtil.m14395a(context, "string", "ui_name")).equalsIgnoreCase(nextName)) {
                checkBox.setTag(jsonReader.nextString());
            } else {
                Context context2 = this.f15145d;
                if (context2.getString(ResourceUtil.m14395a(context2, "string", "ui_checkbox_hintcontent")).equalsIgnoreCase(nextName)) {
                    checkBox.setText(jsonReader.nextString());
                } else {
                    Context context3 = this.f15145d;
                    if (!context3.getString(ResourceUtil.m14395a(context3, "string", "ui_checkbox_checked")).equalsIgnoreCase(nextName)) {
                        Context context4 = this.f15145d;
                        if (context4.getString(ResourceUtil.m14395a(context4, "string", "ui_textsize")).equalsIgnoreCase(nextName)) {
                            int nextInt = jsonReader.nextInt();
                            if (nextInt > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textsize_min"))) {
                                checkBox.setTextSize(nextInt);
                            } else if (nextInt != m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textSize_default"))) {
                                checkBox.setTextSize(m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_textsize_min")));
                            }
                        } else {
                            Context context5 = this.f15145d;
                            if (context5.getString(ResourceUtil.m14395a(context5, "string", "ui_layout_height")).equalsIgnoreCase(nextName)) {
                                int nextInt2 = jsonReader.nextInt();
                                if (nextInt2 > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_default_height"))) {
                                    checkBox.setHeight(nextInt2);
                                }
                            } else {
                                Context context6 = this.f15145d;
                                if (context6.getString(ResourceUtil.m14395a(context6, "string", "ui_layout_width")).equalsIgnoreCase(nextName)) {
                                    int nextInt3 = jsonReader.nextInt();
                                    if (nextInt3 > m14411a(ResourceUtil.m14395a(this.f15145d, "integer", "ui_default_width"))) {
                                        checkBox.setWidth(nextInt3);
                                    }
                                } else {
                                    jsonReader.skipValue();
                                }
                            }
                        }
                    } else if (jsonReader.nextBoolean()) {
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                    }
                }
            }
        }
        this.f15142a.add(checkBox);
        jsonReader.endObject();
        return checkBox;
    }

    /* renamed from: f */
    private Spinner m14402f(JsonReader jsonReader) throws IOException {
        Spinner spinner = new Spinner(this.f15145d);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            try {
                String nextName = jsonReader.nextName();
                ArrayList arrayList = new ArrayList();
                if (this.f15145d.getString(ResourceUtil.m14395a(this.f15145d, "string", "ui_name")).equalsIgnoreCase(nextName)) {
                    spinner.setTag(jsonReader.nextString());
                } else if (this.f15145d.getString(ResourceUtil.m14395a(this.f15145d, "string", "ui_spinner_items")).equalsIgnoreCase(nextName)) {
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        try {
                            arrayList.add(jsonReader.nextString());
                        } catch (Exception unused) {
                        }
                    }
                    jsonReader.endArray();
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this.f15145d, 17367048, arrayList);
                    arrayAdapter.setDropDownViewResource(17367049);
                    spinner.setAdapter((SpinnerAdapter) arrayAdapter);
                } else if (this.f15145d.getString(ResourceUtil.m14395a(this.f15145d, "string", "ui_spinner_defaultitem")).equalsIgnoreCase(nextName)) {
                    try {
                        spinner.setSelection(jsonReader.nextInt(), true);
                    } catch (Exception unused2) {
                        spinner.setSelection(0);
                    }
                } else {
                    jsonReader.skipValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jsonReader.endObject();
        this.f15143b.add(spinner);
        return spinner;
    }

    /* renamed from: b */
    private void m14407b(File file) throws JSONException {
        if (file != null && file.exists() && !file.isDirectory() && file.length() != 0) {
            String str = null;
            try {
                if (TextUtils.isEmpty(this.f15148g)) {
                    str = FileUtils.readFileToString(file, "UTF-8");
                } else {
                    str = this.f15148g;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jSONObject = new JSONObject(str);
            Iterator<Spinner> it = this.f15143b.iterator();
            while (it.hasNext()) {
                Spinner next = it.next();
                try {
                    int i = jSONObject.getInt(next.getTag().toString());
                    if (i >= next.getCount()) {
                        next.setSelection(0);
                    } else {
                        next.setSelection(i);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Iterator<CheckBox> it2 = this.f15142a.iterator();
            while (it2.hasNext()) {
                CheckBox next2 = it2.next();
                try {
                    next2.setChecked(jSONObject.getBoolean(next2.getTag().toString()));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            Iterator<EditText> it3 = this.f15144c.iterator();
            while (it3.hasNext()) {
                EditText next3 = it3.next();
                try {
                    next3.setText(jSONObject.getString(next3.getTag().toString()));
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public String m14409a(File file) throws Exception {
        if (file == null || file.isDirectory()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        Iterator<CheckBox> it = this.f15142a.iterator();
        while (it.hasNext()) {
            CheckBox next = it.next();
            try {
                jSONObject.put(next.getTag().toString(), next.isChecked());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Iterator<Spinner> it2 = this.f15143b.iterator();
        while (it2.hasNext()) {
            Spinner next2 = it2.next();
            try {
                jSONObject.put(next2.getTag().toString(), next2.getSelectedItemPosition());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Iterator<EditText> it3 = this.f15144c.iterator();
        while (it3.hasNext()) {
            EditText next3 = it3.next();
            try {
                jSONObject.put(next3.getTag().toString(), next3.getText().toString());
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        FileUtils.writeStringToFile(file, jSONObject.toString(), "UTF-8");
        return jSONObject.toString();
    }

    /* renamed from: a */
    private int m14411a(int i) {
        return this.f15145d.getResources().getInteger(i);
    }
}
