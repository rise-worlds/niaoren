package com.ime.input;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;

/* renamed from: com.ime.input.a */
/* loaded from: classes.dex */
public final class InputMethodManager {

    /* renamed from: b */
    private static InputMethodManager f9377b;

    /* renamed from: a */
    public InputMethodService f9378a = null;

    private InputMethodManager() {
    }

    /* renamed from: a */
    public static InputMethodManager m19885a() {
        if (f9377b == null) {
            f9377b = new InputMethodManager();
        }
        return f9377b;
    }

    /* renamed from: a */
    private void m19884a(InputMethodService inputMethodService) {
        this.f9378a = inputMethodService;
    }

    /* renamed from: b */
    private void m19882b() {
        this.f9378a = null;
    }

    /* renamed from: a */
    private void m19883a(String str) {
        InputConnection currentInputConnection;
        InputMethodService inputMethodService = this.f9378a;
        if (inputMethodService != null && (currentInputConnection = inputMethodService.getCurrentInputConnection()) != null) {
            currentInputConnection.commitText(str, str.length());
        }
    }

    /* renamed from: b */
    private void m19881b(String str) {
        InputMethodService inputMethodService = this.f9378a;
        if (inputMethodService != null) {
            inputMethodService.switchInputMethod(str);
        }
    }
}
