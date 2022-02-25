package com.cyjh.mobileanjian.ipc.uip;

import android.util.JsonReader;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.IOException;

/* loaded from: classes.dex */
public interface IUipJsonParser {
    Button parseButton(JsonReader jsonReader) throws IOException;

    CheckBox parseCheckBox(JsonReader jsonReader) throws IOException;

    EditText parseEditText(JsonReader jsonReader) throws IOException;

    FlowLayout parseHorizontalLinearLayout(JsonReader jsonReader) throws IOException;

    Spinner parseSpinner(JsonReader jsonReader) throws IOException;

    TextView parseTextView(JsonReader jsonReader) throws IOException;
}
