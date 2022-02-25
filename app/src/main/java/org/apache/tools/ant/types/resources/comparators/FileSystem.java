package org.apache.tools.ant.types.resources.comparators;

import java.io.File;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class FileSystem extends ResourceComparator {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator
    public int resourceCompare(Resource resource, Resource resource2) {
        FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
        if (fileProvider != null) {
            File file = fileProvider.getFile();
            FileProvider fileProvider2 = (FileProvider) resource2.mo14823as(FileProvider.class);
            if (fileProvider2 != null) {
                File file2 = fileProvider2.getFile();
                if (file.equals(file2)) {
                    return 0;
                }
                if (FILE_UTILS.isLeadingPath(file, file2)) {
                    return -1;
                }
                return FILE_UTILS.normalize(file.getAbsolutePath()).compareTo(FILE_UTILS.normalize(file2.getAbsolutePath()));
            }
            throw new ClassCastException(resource2.getClass() + " doesn't provide files");
        }
        throw new ClassCastException(resource.getClass() + " doesn't provide files");
    }
}
