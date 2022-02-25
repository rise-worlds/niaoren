package com.cyjh.mobileanjian.ipc.uip;

import android.content.Context;
import android.text.InputFilter;
import android.util.JsonReader;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mobileanjian.ipc.uip.FlowLayout;
import com.cyjh.mqsdk.C1375R;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DefaultUipJsonParser implements IUipJsonParser {
    protected Context mContext;
    private FlowLayout.C1332a mDefaultFlowLayoutChildLayoutParams = new FlowLayout.C1332a(-2, -2);

    public DefaultUipJsonParser(Context context) {
        this.mContext = context;
        this.mDefaultFlowLayoutChildLayoutParams.f8660a = 17;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public FlowLayout parseHorizontalLinearLayout(JsonReader jsonReader) throws IOException, IllegalStateException {
        FlowLayout bVar = new FlowLayout(this.mContext);
        bVar.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        bVar.setGravity(16);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (this.mContext.getString(C1375R.string.ui_linearlayout).equalsIgnoreCase(nextName)) {
                bVar.addView(parseHorizontalLinearLayout(jsonReader));
            } else if (this.mContext.getString(C1375R.string.ui_textview).equalsIgnoreCase(nextName)) {
                bVar.addView(parseTextView(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C1375R.string.ui_edittext).equalsIgnoreCase(nextName)) {
                bVar.addView(parseEditText(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C1375R.string.ui_checkbox).equals(nextName)) {
                bVar.addView(parseCheckBox(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C1375R.string.ui_spinner).equalsIgnoreCase(nextName)) {
                bVar.addView(parseSpinner(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C1375R.string.ui_button).equalsIgnoreCase(nextName)) {
                bVar.addView(parseButton(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return bVar;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public TextView parseTextView(JsonReader jsonReader) throws IOException, IllegalStateException {
        TextView textView = new TextView(this.mContext);
        textView.setGravity(17);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (this.mContext.getString(C1375R.string.ui_name).equalsIgnoreCase(nextName)) {
                textView.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_textview_textcontent).equalsIgnoreCase(nextName)) {
                textView.setText(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_textsize).equalsIgnoreCase(nextName)) {
                int nextInt = jsonReader.nextInt();
                if (nextInt > this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min)) {
                    textView.setTextSize(nextInt);
                } else if (nextInt != this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_default)) {
                    textView.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_height).equalsIgnoreCase(nextName)) {
                int nextInt2 = jsonReader.nextInt();
                if (nextInt2 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_height)) {
                    textView.setHeight(nextInt2);
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_width).equalsIgnoreCase(nextName)) {
                int nextInt3 = jsonReader.nextInt();
                if (nextInt3 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_width)) {
                    textView.setWidth(nextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return textView;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public Button parseButton(JsonReader jsonReader) throws IOException, IllegalStateException {
        Button button = new Button(this.mContext);
        button.setGravity(17);
        button.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        button.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (this.mContext.getString(C1375R.string.ui_name).equalsIgnoreCase(nextName)) {
                button.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_textview_textcontent).equalsIgnoreCase(nextName)) {
                button.setText(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_onclick).equalsIgnoreCase(nextName)) {
                button.setTag(C1375R.C1377id.uip_function_key, jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_textsize).equalsIgnoreCase(nextName)) {
                int nextInt = jsonReader.nextInt();
                if (nextInt > this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min)) {
                    button.setTextSize(nextInt);
                } else if (nextInt != this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_default)) {
                    button.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_height).equalsIgnoreCase(nextName)) {
                int nextInt2 = jsonReader.nextInt();
                if (nextInt2 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_height)) {
                    button.setHeight(nextInt2);
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_width).equalsIgnoreCase(nextName)) {
                int nextInt3 = jsonReader.nextInt();
                if (nextInt3 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_width)) {
                    button.setWidth(nextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return button;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public EditText parseEditText(JsonReader jsonReader) throws IOException, IllegalStateException {
        EditText editText = new EditText(this.mContext);
        editText.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        editText.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_normal));
        editText.setSingleLine(true);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (this.mContext.getString(C1375R.string.ui_name).equalsIgnoreCase(nextName)) {
                editText.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_edittext_hintcontent).equalsIgnoreCase(nextName)) {
                editText.setHint(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_textsize).equalsIgnoreCase(nextName)) {
                int nextInt = jsonReader.nextInt();
                if (nextInt > this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min)) {
                    editText.setTextSize(nextInt);
                } else if (nextInt != this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_default)) {
                    editText.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_height).equalsIgnoreCase(nextName)) {
                int nextInt2 = jsonReader.nextInt();
                if (nextInt2 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_height)) {
                    editText.setHeight(nextInt2);
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_width).equalsIgnoreCase(nextName)) {
                int nextInt3 = jsonReader.nextInt();
                if (nextInt3 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_width)) {
                    editText.setWidth(nextInt3);
                }
            } else {
                boolean z = false;
                if (this.mContext.getString(C1375R.string.ui_edittext_maxlength).equalsIgnoreCase(nextName)) {
                    int nextInt4 = jsonReader.nextInt();
                    if (nextInt4 > 0) {
                        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(nextInt4)});
                    }
                } else if (this.mContext.getString(C1375R.string.ui_edittext_inputnumber).equalsIgnoreCase(nextName)) {
                    try {
                        z = jsonReader.nextBoolean();
                    } catch (Exception unused) {
                    }
                    if (z) {
                        editText.setInputType(2);
                    }
                } else if (this.mContext.getString(C1375R.string.ui_edittext_defaultcontent).equalsIgnoreCase(nextName)) {
                    editText.setText(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                }
            }
        }
        jsonReader.endObject();
        editText.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.uip.DefaultUipJsonParser.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
            }
        });
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.cyjh.mobileanjian.ipc.uip.DefaultUipJsonParser.2
            @Override // android.view.ActionMode.Callback
            public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public final void onDestroyActionMode(ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
        return editText;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public CheckBox parseCheckBox(JsonReader jsonReader) throws IOException, IllegalStateException {
        CheckBox checkBox = new CheckBox(this.mContext);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        checkBox.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (this.mContext.getString(C1375R.string.ui_name).equalsIgnoreCase(nextName)) {
                checkBox.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_onclick).equalsIgnoreCase(nextName)) {
                checkBox.setTag(C1375R.C1377id.uip_function_key, jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_checkbox_hintcontent).equalsIgnoreCase(nextName)) {
                checkBox.setText(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_checkbox_checked).equalsIgnoreCase(nextName)) {
                if (jsonReader.nextBoolean()) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            } else if (this.mContext.getString(C1375R.string.ui_textsize).equalsIgnoreCase(nextName)) {
                int nextInt = jsonReader.nextInt();
                if (nextInt > this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min)) {
                    checkBox.setTextSize(nextInt);
                } else if (nextInt != this.mContext.getResources().getInteger(C1375R.integer.ui_textSize_default)) {
                    checkBox.setTextSize(this.mContext.getResources().getInteger(C1375R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_height).equalsIgnoreCase(nextName)) {
                int nextInt2 = jsonReader.nextInt();
                if (nextInt2 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_height)) {
                    checkBox.setHeight(nextInt2);
                }
            } else if (this.mContext.getString(C1375R.string.ui_layout_width).equalsIgnoreCase(nextName)) {
                int nextInt3 = jsonReader.nextInt();
                if (nextInt3 > this.mContext.getResources().getInteger(C1375R.integer.ui_default_width)) {
                    checkBox.setWidth(nextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return checkBox;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public Spinner parseSpinner(JsonReader jsonReader) throws IOException, IllegalStateException {
        Spinner spinner = new Spinner(this.mContext);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        spinner.setBackgroundResource(C1375R.C1376drawable.spinner_bg);
        spinner.setPadding(0, 0, 5, 0);
        spinner.setGravity(3);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            ArrayList arrayList = new ArrayList();
            if (this.mContext.getString(C1375R.string.ui_name).equalsIgnoreCase(nextName)) {
                spinner.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_onSelect).equalsIgnoreCase(nextName)) {
                spinner.setTag(C1375R.C1377id.uip_function_key, jsonReader.nextString());
            } else if (this.mContext.getString(C1375R.string.ui_spinner_items).equalsIgnoreCase(nextName)) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                StringArrayAdapter eVar = new StringArrayAdapter(this.mContext, arrayList);
                eVar.setDropDownViewResource(17367049);
                spinner.setAdapter((SpinnerAdapter) eVar);
            } else if (this.mContext.getString(C1375R.string.ui_spinner_defaultitem).equalsIgnoreCase(nextName)) {
                int nextInt = jsonReader.nextInt();
                if (nextInt >= spinner.getAdapter().getCount()) {
                    nextInt = 0;
                }
                spinner.setSelection(nextInt);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return spinner;
    }
}
