package org.apache.tools.ant.taskdefs.optional.native2ascii;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.optional.Native2Ascii;

/* loaded from: classes2.dex */
public interface Native2AsciiAdapter {
    boolean convert(Native2Ascii native2Ascii, File file, File file2) throws BuildException;
}
