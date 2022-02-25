package com.cyjh.mobileanjian.ipc.utils;

import android.os.Environment;
import android.text.format.DateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.f */
/* loaded from: classes.dex */
public final class FileLogger {

    /* renamed from: a */
    private File f8684a;

    /* renamed from: b */
    private FileWriter f8685b;

    private FileLogger(String str) {
        this.f8684a = new File(Environment.getExternalStorageDirectory(), str);
        try {
            this.f8685b = new FileWriter(this.f8684a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m20652a(String str) {
        if (this.f8685b != null) {
            String charSequence = DateFormat.format("yyyy-MM-hh HH:mm:ss", System.currentTimeMillis()).toString();
            try {
                FileWriter fileWriter = this.f8685b;
                fileWriter.write(charSequence + "  " + str + "\n");
                this.f8685b.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
