package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.types.resources.FileResource;

/* loaded from: classes2.dex */
public class LoadFile extends LoadResource {
    public final void setSrcFile(File file) {
        addConfigured(new FileResource(file));
    }
}
