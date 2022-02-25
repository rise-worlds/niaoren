package com.goldcoast.sdk.domain;

import android.annotation.TargetApi;
import android.util.Pair;
import com.cyjh.mq.p049d.C1363e;
import com.goldcoast.sdk.p050a.FileInfo;
import com.goldcoast.sdk.p052c.LogUtil;
import com.p007a.p008a.p009a.CommandResult;
import com.p007a.p008a.p009a.Shell;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EntryPoint.java */
/* renamed from: com.goldcoast.sdk.domain.j */
/* loaded from: classes.dex */
public final class RunnableC1403j implements Runnable {

    /* renamed from: a */
    final /* synthetic */ FileInfo f9076a;

    /* renamed from: b */
    final /* synthetic */ EntryPoint f9077b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC1403j(EntryPoint entryPoint, FileInfo dVar) {
        this.f9077b = entryPoint;
        this.f9076a = dVar;
    }

    @Override // java.lang.Runnable
    @TargetApi(5)
    public final void run() {
        Pair pair;
        int i;
        int i2;
        this.f9077b.f9043D = true;
        pair = this.f9077b.f9045F;
        if (pair != null) {
            this.f9077b.f9045F = null;
        }
        EntryPoint.m20280f(this.f9077b);
        StringBuilder sb = new StringBuilder();
        sb.append("######\n");
        i = this.f9077b.f9050s;
        sb.append(String.format("In <<<<<< %d  >>>>>> \n", Integer.valueOf(i)));
        LogUtil.m20321a();
        LogUtil.m20318b(sb.toString());
        EntryPoint entryPoint = this.f9077b;
        i2 = entryPoint.f9050s;
        entryPoint.m20289b(String.format("正在计算第  <%d>  个$$$方案 请等待...", Integer.valueOf(i2)), 2);
        this.f9077b.m20289b(String.format("key:%s\norder:%d name: %s", this.f9076a.m20371e(), Integer.valueOf(this.f9076a.m20372d()), this.f9076a.m20377a()), 2);
        StringBuilder sb2 = new StringBuilder();
        FileInfo dVar = this.f9076a;
        dVar.m20374b(dVar.m20375b().replace("\n", ""));
        try {
            CommandResult a = Shell.m25525a(C1363e.f8871b, String.format("%s %d %s\n", EntryPoint.m20298a(this.f9076a.m20377a(), this.f9076a.m20373c()), 2, this.f9076a.m20375b()));
            LogUtil.m20321a();
            LogUtil.m20319a(a.m25526a());
            Thread.sleep(6000L);
        } catch (Exception e) {
            this.f9077b.m20289b(String.format("A9DDDF2A4F7D94594EC2EA98407A410E1 exception: %s", e.getMessage()), 2);
        }
        File file = new File(EntryPoint.extractDir.getAbsolutePath() + File.separator + this.f9076a.m20377a());
        if (file.exists() && file.delete()) {
            LogUtil.m20321a();
            LogUtil.m20319a("delete file");
        }
        this.f9077b.f9045F = new Pair("no", sb2.toString());
        this.f9077b.f9043D = false;
        this.f9077b.f9042C = false;
    }
}
