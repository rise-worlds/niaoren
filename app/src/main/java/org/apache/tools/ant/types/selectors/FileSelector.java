package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public interface FileSelector {
    boolean isSelected(File file, String str, File file2) throws BuildException;
}
