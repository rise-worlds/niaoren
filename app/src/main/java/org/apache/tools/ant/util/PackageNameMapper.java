package org.apache.tools.ant.util;

import java.io.File;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;

/* loaded from: classes2.dex */
public class PackageNameMapper extends GlobPatternMapper {
    @Override // org.apache.tools.ant.util.GlobPatternMapper
    protected String extractVariablePart(String str) {
        String substring = str.substring(this.prefixLength, str.length() - this.postfixLength);
        if (getHandleDirSep()) {
            substring = substring.replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR).replace(IOUtils.DIR_SEPARATOR_WINDOWS, FilenameUtils.EXTENSION_SEPARATOR);
        }
        return substring.replace(File.separatorChar, FilenameUtils.EXTENSION_SEPARATOR);
    }
}
