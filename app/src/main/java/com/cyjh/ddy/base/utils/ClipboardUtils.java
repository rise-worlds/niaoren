package com.cyjh.ddy.base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import com.blankj.utilcode.util.Utils;

/* renamed from: com.cyjh.ddy.base.utils.b */
/* loaded from: classes.dex */
public final class ClipboardUtils {
    private ClipboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m21871a(CharSequence charSequence) {
        ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text/plain", charSequence));
    }

    /* renamed from: a */
    public static CharSequence m21874a() {
        ClipData primaryClip = ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).getPrimaryClip();
        return (primaryClip == null || primaryClip.getItemCount() <= 0) ? "" : primaryClip.getItemAt(0).coerceToText(Utils.m24103a());
    }

    /* renamed from: a */
    public static void m21872a(Uri uri) {
        ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).setPrimaryClip(ClipData.newUri(Utils.m24103a().getContentResolver(), "uri", uri));
    }

    /* renamed from: b */
    public static Uri m21870b() {
        ClipData primaryClip = ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        return primaryClip.getItemAt(0).getUri();
    }

    /* renamed from: a */
    public static void m21873a(Intent intent) {
        ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).setPrimaryClip(ClipData.newIntent("intent", intent));
    }

    /* renamed from: c */
    public static Intent m21869c() {
        ClipData primaryClip = ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        return primaryClip.getItemAt(0).getIntent();
    }

    public static void addClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
        ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).addPrimaryClipChangedListener(onPrimaryClipChangedListener);
    }

    public static void removeClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
        ((ClipboardManager) Utils.m24103a().getSystemService("clipboard")).removePrimaryClipChangedListener(onPrimaryClipChangedListener);
    }
}
