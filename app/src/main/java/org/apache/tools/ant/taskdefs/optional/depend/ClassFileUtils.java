package org.apache.tools.ant.taskdefs.optional.depend;

import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;

/* loaded from: classes2.dex */
public class ClassFileUtils {
    public static String convertSlashName(String str) {
        return str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, FilenameUtils.EXTENSION_SEPARATOR).replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR);
    }

    public static String convertDotName(String str) {
        return str.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX);
    }
}
