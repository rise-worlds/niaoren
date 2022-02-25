package com.lody.virtual.helper;

import android.os.Build;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.compat.BuildCompat;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import p110z1.C4745bt;
import p110z1.VMRuntime;

/* loaded from: classes.dex */
public class DexOptimizer {
    public static void optimizeDex(String str, String str2) throws IOException {
        if (VirtualRuntime.isArt() && Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 25) {
            try {
                interpretDex2Oat(str, str2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DexFile.loadDex(str, str2, 0).close();
    }

    public static void interpretDex2Oat(String str, String str2) throws IOException {
        File file = new File(str2);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("dex2oat");
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList.add("--runtime-arg");
            arrayList.add("-classpath");
            arrayList.add("--runtime-arg");
            arrayList.add(C4745bt.f20071b);
        }
        arrayList.add("--dex-file=" + str);
        arrayList.add("--oat-file=" + str2);
        arrayList.add("--instruction-set=" + VMRuntime.getCurrentInstructionSet.call(new Object[0]));
        arrayList.add("--compiler-filter=everything");
        if (Build.VERSION.SDK_INT >= 22 && !BuildCompat.isQ()) {
            arrayList.add("--compile-pic");
        }
        if (Build.VERSION.SDK_INT > 25) {
            arrayList.add("--inline-max-code-units=0");
        } else if (Build.VERSION.SDK_INT >= 23) {
            arrayList.add("--inline-depth-limit=0");
        }
        ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
        processBuilder.redirectErrorStream(true);
        Process start = processBuilder.start();
        StreamConsumer.consumeInputStream(start.getInputStream());
        StreamConsumer.consumeInputStream(start.getErrorStream());
        try {
            int waitFor = start.waitFor();
            if (waitFor != 0) {
                throw new IOException("dex2oat works unsuccessfully, exit code: " + waitFor);
            }
        } catch (InterruptedException e) {
            throw new IOException("dex2oat is interrupted, msg: " + e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class StreamConsumer {
        static final Executor STREAM_CONSUMER = Executors.newSingleThreadExecutor();

        private StreamConsumer() {
        }

        static void consumeInputStream(final InputStream inputStream) {
            STREAM_CONSUMER.execute(new Runnable() { // from class: com.lody.virtual.helper.DexOptimizer.StreamConsumer.1
                @Override // java.lang.Runnable
                public void run() {
                    if (inputStream != null) {
                        do {
                            try {
                            } catch (IOException unused) {
                            } catch (Throwable th) {
                                try {
                                    inputStream.close();
                                } catch (Exception unused2) {
                                }
                                throw th;
                            }
                        } while (inputStream.read(new byte[256]) > 0);
                        try {
                            inputStream.close();
                        } catch (Exception unused3) {
                        }
                    }
                }
            });
        }
    }
}
