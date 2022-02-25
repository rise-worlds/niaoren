package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import com.cyjh.p045mq.sdk.entity.Script4Run;
import java.io.File;
import org.apache.commons.p105io.FileUtils;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.o */
/* loaded from: classes.dex */
public final class ScriptHelper {

    /* renamed from: a */
    private static final String f8710a = "script";

    /* renamed from: a */
    private static Script4Run m20621a(Context context, @NonNull Script4Run script4Run) throws Exception {
        File file = new File(context.getFilesDir(), f8710a);
        if (!file.exists()) {
            file.mkdirs();
            file.setReadable(true, false);
            file.setExecutable(true, false);
        } else {
            FileUtils.deleteDirectory(file);
        }
        File[] fileArr = {new File(script4Run.getLcPath()), new File(script4Run.getAtcPath()), new File(script4Run.getConfigPath())};
        File file2 = new File(file, FilenameUtils.getName(script4Run.getLcPath()));
        File file3 = new File(file, FilenameUtils.getName(script4Run.getAtcPath()));
        File file4 = new File(file, FilenameUtils.getName(script4Run.getConfigPath()));
        File[] fileArr2 = new File[3];
        fileArr2[0] = file2;
        fileArr2[1] = file3;
        fileArr2[2] = file4;
        for (int i = 0; i < 3; i++) {
            if (fileArr[i].exists()) {
                FileUtils.copyFile(fileArr[i], fileArr2[i]);
                fileArr2[i].setReadable(true, false);
            } else {
                fileArr2[i] = fileArr[i];
            }
        }
        Script4Run clone = script4Run.clone();
        clone.setLcPath(file2.getAbsolutePath()).setAtcPath(file3.getAbsolutePath()).setConfigPath(file4.getAbsolutePath());
        return clone;
    }
}
