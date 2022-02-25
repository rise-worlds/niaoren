package org.apache.tools.ant.util;

import java.io.File;

/* loaded from: classes2.dex */
public class FlatFileNameMapper implements FileNameMapper {
    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String str) {
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String str) {
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String str) {
        return new String[]{new File(str).getName()};
    }
}
