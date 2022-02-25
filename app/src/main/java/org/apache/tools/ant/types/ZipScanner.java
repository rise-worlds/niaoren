package org.apache.tools.ant.types;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.ZipResource;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/* loaded from: classes2.dex */
public class ZipScanner extends ArchiveScanner {
    @Override // org.apache.tools.ant.types.ArchiveScanner
    protected void fillMapsFromArchive(Resource resource, String str, Map<String, Resource> map, Map<String, Resource> map2, Map<String, Resource> map3, Map<String, Resource> map4) {
        Throwable th;
        FileProvider fileProvider = (FileProvider) resource.mo14823as(FileProvider.class);
        if (fileProvider != null) {
            File file = fileProvider.getFile();
            ZipFile zipFile = null;
            try {
                try {
                    ZipFile zipFile2 = new ZipFile(file, str);
                    try {
                        Enumeration<ZipEntry> entries = zipFile2.getEntries();
                        while (entries.hasMoreElements()) {
                            ZipEntry nextElement = entries.nextElement();
                            ZipResource zipResource = new ZipResource(file, str, nextElement);
                            String name = nextElement.getName();
                            if (nextElement.isDirectory()) {
                                String trimSeparator = trimSeparator(name);
                                map3.put(trimSeparator, zipResource);
                                if (match(trimSeparator)) {
                                    map4.put(trimSeparator, zipResource);
                                }
                            } else {
                                map.put(name, zipResource);
                                if (match(name)) {
                                    map2.put(name, zipResource);
                                }
                            }
                        }
                        ZipFile.closeQuietly(zipFile2);
                    } catch (Throwable th2) {
                        th = th2;
                        zipFile = zipFile2;
                        ZipFile.closeQuietly(zipFile);
                        throw th;
                    }
                } catch (ZipException e) {
                    throw new BuildException("Problem reading " + file, e);
                } catch (IOException e2) {
                    throw new BuildException("Problem opening " + file, e2);
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            throw new BuildException("Only file provider resources are supported");
        }
    }
}
