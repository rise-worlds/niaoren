package org.apache.tools.ant.taskdefs.launcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class VmsCommandLauncher extends Java13CommandLauncher {
    @Override // org.apache.tools.ant.taskdefs.launcher.CommandLauncher
    public Process exec(Project project, String[] strArr, String[] strArr2) throws IOException {
        File createCommandFile = createCommandFile(strArr, strArr2);
        Process exec = super.exec(project, new String[]{createCommandFile.getPath()}, strArr2);
        deleteAfter(createCommandFile, exec);
        return exec;
    }

    @Override // org.apache.tools.ant.taskdefs.launcher.Java13CommandLauncher, org.apache.tools.ant.taskdefs.launcher.CommandLauncher
    public Process exec(Project project, String[] strArr, String[] strArr2, File file) throws IOException {
        File createCommandFile = createCommandFile(strArr, strArr2);
        Process exec = super.exec(project, new String[]{createCommandFile.getPath()}, strArr2, file);
        deleteAfter(createCommandFile, exec);
        return exec;
    }

    private File createCommandFile(String[] strArr, String[] strArr2) throws IOException {
        Throwable th;
        File createTempFile = FILE_UTILS.createTempFile("ANT", ".COM", null, true, true);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(createTempFile));
            if (strArr2 != null) {
                for (int i = 0; i < strArr2.length; i++) {
                    try {
                        int indexOf = strArr2[i].indexOf(61);
                        if (indexOf != -1) {
                            bufferedWriter.write("$ DEFINE/NOLOG ");
                            bufferedWriter.write(strArr2[i].substring(0, indexOf));
                            bufferedWriter.write(" \"");
                            bufferedWriter.write(strArr2[i].substring(indexOf + 1));
                            bufferedWriter.write(34);
                            bufferedWriter.newLine();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        FileUtils.close(bufferedWriter);
                        throw th;
                    }
                }
            }
            bufferedWriter.write("$ " + strArr[0]);
            for (int i2 = 1; i2 < strArr.length; i2++) {
                bufferedWriter.write(" -");
                bufferedWriter.newLine();
                bufferedWriter.write(strArr[i2]);
            }
            FileUtils.close(bufferedWriter);
            return createTempFile;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.tools.ant.taskdefs.launcher.VmsCommandLauncher$1] */
    private void deleteAfter(final File file, final Process process) {
        new Thread() { // from class: org.apache.tools.ant.taskdefs.launcher.VmsCommandLauncher.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    process.waitFor();
                } catch (InterruptedException unused) {
                }
                FileUtils.delete(file);
            }
        }.start();
    }
}
