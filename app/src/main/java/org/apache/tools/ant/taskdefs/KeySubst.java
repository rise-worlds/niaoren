package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;
import org.slf4j.Marker;
import p110z1.C4963cj;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class KeySubst extends Task {
    private File source = null;
    private File dest = null;
    private String sep = Marker.ANY_MARKER;
    private Hashtable<String, String> replacements = new Hashtable<>();

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        BufferedReader bufferedReader;
        Throwable th;
        IOException e;
        BufferedWriter bufferedWriter;
        log("!! KeySubst is deprecated. Use Filter + Copy instead. !!");
        log("Performing Substitutions");
        File file = this.source;
        if (file == null || this.dest == null) {
            log("Source and destinations must not be null");
            return;
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    this.dest.delete();
                    bufferedWriter = new BufferedWriter(new FileWriter(this.dest));
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
        try {
            String str = bufferedReader.readLine();
            while (str != null) {
                if (str.length() == 0) {
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write(replace(str, this.replacements));
                    bufferedWriter.newLine();
                }
                str = bufferedReader.readLine();
            }
            bufferedWriter.flush();
            FileUtils.close(bufferedWriter);
            bufferedWriter2 = str;
        } catch (IOException e4) {
            e = e4;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            FileUtils.close(bufferedWriter2);
            bufferedWriter2 = bufferedWriter2;
            FileUtils.close(bufferedReader);
        } catch (Throwable th4) {
            th = th4;
            bufferedWriter2 = bufferedWriter;
            FileUtils.close(bufferedWriter2);
            FileUtils.close(bufferedReader);
            throw th;
        }
        FileUtils.close(bufferedReader);
    }

    public void setSrc(File file) {
        this.source = file;
    }

    public void setDest(File file) {
        this.dest = file;
    }

    public void setSep(String str) {
        this.sep = str;
    }

    public void setKeys(String str) {
        if (str != null && str.length() > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, this.sep, false);
            while (stringTokenizer.hasMoreTokens()) {
                StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken().trim(), SimpleComparison.f23609c, false);
                this.replacements.put(stringTokenizer2.nextToken(), stringTokenizer2.nextToken());
            }
        }
    }

    public static void main(String[] strArr) {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put("VERSION", "1.0.3");
            hashtable.put("b", "ffff");
            System.out.println(replace("$f ${VERSION} f ${b} jj $", hashtable));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String replace(String str, Hashtable<String, String> hashtable) throws BuildException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int indexOf = str.indexOf("${", i);
            if (indexOf > -1) {
                int i2 = indexOf + 3;
                String substring = str.substring(indexOf + 2, str.indexOf(C4963cj.f20747d, i2));
                stringBuffer.append(str.substring(i, indexOf));
                if (hashtable.containsKey(substring)) {
                    stringBuffer.append(hashtable.get(substring));
                } else {
                    stringBuffer.append("${");
                    stringBuffer.append(substring);
                    stringBuffer.append(C4963cj.f20747d);
                }
                i = substring.length() + i2;
            } else {
                stringBuffer.append(str.substring(i));
                return stringBuffer.toString();
            }
        }
    }
}
