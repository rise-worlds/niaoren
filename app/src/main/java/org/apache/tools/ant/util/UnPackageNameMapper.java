package org.apache.tools.ant.util;

import java.io.File;
import org.apache.commons.p105io.FilenameUtils;

/* loaded from: classes2.dex */
public class UnPackageNameMapper extends GlobPatternMapper {
    @Override // org.apache.tools.ant.util.GlobPatternMapper
    protected String extractVariablePart(String str) {
        return str.substring(this.prefixLength, str.length() - this.postfixLength).replace(FilenameUtils.EXTENSION_SEPARATOR, File.separatorChar);
    }
}
