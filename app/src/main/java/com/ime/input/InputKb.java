package com.ime.input;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

/* loaded from: classes.dex */
public class InputKb extends InputMethodService {

    /* renamed from: b */
    public static Handler f9374b;

    /* renamed from: a */
    EditorInfo f9375a;

    /* renamed from: c */
    private KeyboardView f9376c;

    @Override // android.inputmethodservice.InputMethodService, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i("InputKb", "InputKb - onCreate()");
        InputMethodManager.m19885a().f9378a = this;
    }

    @Override // android.inputmethodservice.InputMethodService
    public View onCreateInputView() {
        return this.f9376c;
    }

    @Override // android.inputmethodservice.InputMethodService
    public boolean onEvaluateFullscreenMode() {
        EditorInfo editorInfo;
        return (getResources().getConfiguration().orientation != 2 || (editorInfo = this.f9375a) == null || (editorInfo.imeOptions & 268435456) == 0) ? false : false;
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onFinishInputView(boolean z) {
        super.onFinishInputView(z);
    }

    @Override // android.inputmethodservice.InputMethodService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        InputMethodManager.m19885a().f9378a = null;
    }
}
