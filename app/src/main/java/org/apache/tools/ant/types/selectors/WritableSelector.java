package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;

/* loaded from: classes2.dex */
public class WritableSelector implements ResourceSelector, FileSelector {
    @Override // org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        return file2 != null && file2.canWrite();
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public boolean isSelected(Resource resource) {
        FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
        return fileProvider != null && isSelected(null, null, fileProvider.getFile());
    }
}
