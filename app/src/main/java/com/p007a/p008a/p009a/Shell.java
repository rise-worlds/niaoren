package com.p007a.p008a.p009a;

import android.support.annotation.NonNull;
import com.cyjh.mq.p049d.C1363e;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.a.a.a.b */
/* loaded from: classes.dex */
public final class Shell {

    /* renamed from: a */
    static final String[] f114a = {"echo -BOC-", ConnectionModel.f10389a};

    /* renamed from: a */
    public static CommandResult m25525a(@NonNull String str, @NonNull String... strArr) {
        return m25524b(str, strArr);
    }

    /* renamed from: b */
    private static CommandResult m25524b(@NonNull String str, @NonNull String[] strArr) {
        int i;
        List synchronizedList = Collections.synchronizedList(new ArrayList());
        List synchronizedList2 = Collections.synchronizedList(new ArrayList());
        try {
            try {
                Process exec = Runtime.getRuntime().exec(str, (String[]) null);
                DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
                StreamGobbler cVar = new StreamGobbler(exec.getInputStream(), synchronizedList);
                StreamGobbler cVar2 = new StreamGobbler(exec.getErrorStream(), synchronizedList2);
                cVar.start();
                cVar2.start();
                try {
                    for (String str2 : strArr) {
                        dataOutputStream.write((str2 + "\n").getBytes("UTF-8"));
                        dataOutputStream.flush();
                    }
                    dataOutputStream.write(C1363e.f8872c.getBytes("UTF-8"));
                    dataOutputStream.flush();
                } catch (IOException e) {
                    if (!e.getMessage().contains("EPIPE")) {
                        throw e;
                    }
                }
                i = exec.waitFor();
                try {
                    dataOutputStream.close();
                } catch (IOException unused) {
                }
                cVar.join();
                cVar2.join();
                exec.destroy();
            } catch (InterruptedException unused2) {
                i = -1;
            }
        } catch (IOException unused3) {
            i = -4;
        }
        return new CommandResult(synchronizedList, synchronizedList2, i);
    }
}
