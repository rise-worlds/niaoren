package com.cyjh.mqm;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.p105io.FileUtils;

/* loaded from: classes.dex */
public class MQCompiler {
    public static String compile(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        MQLanguageStub mQLanguageStub = new MQLanguageStub();
        mQLanguageStub.SetLocalDir(Environment.getExternalStorageDirectory().getAbsolutePath(), str, "");
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        if (mQLanguageStub.Compile(str2, str3, str4, arrayList) != 0) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                String substring = next.substring(next.indexOf(":") + 1);
                stringBuffer.append(substring + "\n");
            }
        }
        return stringBuffer.toString();
    }

    public static void compile(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4, OnCompiledCallback onCompiledCallback) {
        new AsyncTaskC1374a(onCompiledCallback).execute(str, str2, str3, str4);
    }

    /* renamed from: com.cyjh.mqm.MQCompiler$a */
    /* loaded from: classes.dex */
    private static class AsyncTaskC1374a extends AsyncTask<String, Integer, String> {

        /* renamed from: a */
        private OnCompiledCallback f8917a;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ String doInBackground(String[] strArr) {
            return m20393a(strArr);
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(String str) {
            String str2 = str;
            super.onPostExecute(str2);
            OnCompiledCallback onCompiledCallback = this.f8917a;
            if (onCompiledCallback != null) {
                onCompiledCallback.onCompileFinished(str2);
            }
        }

        public AsyncTaskC1374a(OnCompiledCallback onCompiledCallback) {
            this.f8917a = onCompiledCallback;
        }

        /* renamed from: a */
        private static String m20393a(String... strArr) {
            String str = "";
            String str2 = "";
            try {
                str = FileUtils.readFileToString(new File(strArr[1]), "GBK");
                if (!TextUtils.isEmpty(strArr[2]) && new File(strArr[2]).exists()) {
                    str2 = FileUtils.readFileToString(new File(strArr[2]), "GBK");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return MQCompiler.compile(strArr[0], str, str2, strArr[3]);
        }

        /* renamed from: a */
        private void m20394a(String str) {
            super.onPostExecute(str);
            OnCompiledCallback onCompiledCallback = this.f8917a;
            if (onCompiledCallback != null) {
                onCompiledCallback.onCompileFinished(str);
            }
        }
    }
}
