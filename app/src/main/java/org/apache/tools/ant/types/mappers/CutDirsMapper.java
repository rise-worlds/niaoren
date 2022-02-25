package org.apache.tools.ant.types.mappers;

import java.io.File;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileNameMapper;

/* loaded from: classes2.dex */
public class CutDirsMapper implements FileNameMapper {
    private int dirs = 0;

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String str) {
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String str) {
    }

    public void setDirs(int i) {
        this.dirs = i;
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String str) {
        if (this.dirs > 0) {
            char c = File.separatorChar;
            String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, c).replace(IOUtils.DIR_SEPARATOR_WINDOWS, c);
            int indexOf = replace.indexOf(c);
            for (int i = 1; indexOf > -1 && i < this.dirs; i++) {
                indexOf = replace.indexOf(c, indexOf + 1);
            }
            if (indexOf == -1) {
                return null;
            }
            return new String[]{str.substring(indexOf + 1)};
        }
        throw new BuildException("dirs must be set to a positive number");
    }
}
