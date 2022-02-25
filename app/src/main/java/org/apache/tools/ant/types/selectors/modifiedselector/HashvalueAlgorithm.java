package org.apache.tools.ant.types.selectors.modifiedselector;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class HashvalueAlgorithm implements Algorithm {
    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public boolean isValid() {
        return true;
    }

    public String toString() {
        return "HashvalueAlgorithm";
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public String getValue(File file) {
        FileReader fileReader;
        Throwable th;
        FileReader fileReader2 = null;
        try {
            if (!file.canRead()) {
                FileUtils.close((Reader) null);
                return null;
            }
            fileReader = new FileReader(file);
            try {
                String num = Integer.toString(FileUtils.readFully(fileReader).hashCode());
                FileUtils.close(fileReader);
                return num;
            } catch (Exception unused) {
                FileUtils.close(fileReader);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileReader2 = fileReader;
                FileUtils.close(fileReader2);
                throw th;
            }
        } catch (Exception unused2) {
            fileReader = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
