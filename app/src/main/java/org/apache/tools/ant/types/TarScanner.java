package org.apache.tools.ant.types;

import java.io.IOException;
import java.util.Map;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.resources.TarResource;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

/* loaded from: classes2.dex */
public class TarScanner extends ArchiveScanner {
    @Override // org.apache.tools.ant.types.ArchiveScanner
    protected void fillMapsFromArchive(Resource resource, String str, Map<String, Resource> map, Map<String, Resource> map2, Map<String, Resource> map3, Map<String, Resource> map4) {
        Throwable th;
        IOException e;
        TarInputStream tarInputStream = null;
        try {
            try {
                TarInputStream tarInputStream2 = new TarInputStream(resource.getInputStream(), str);
                while (true) {
                    try {
                        TarEntry nextEntry = tarInputStream2.getNextEntry();
                        if (nextEntry != null) {
                            TarResource tarResource = new TarResource(resource, nextEntry);
                            String name = nextEntry.getName();
                            if (nextEntry.isDirectory()) {
                                String trimSeparator = trimSeparator(name);
                                map3.put(trimSeparator, tarResource);
                                if (match(trimSeparator)) {
                                    map4.put(trimSeparator, tarResource);
                                }
                            } else {
                                map.put(name, tarResource);
                                if (match(name)) {
                                    map2.put(name, tarResource);
                                }
                            }
                        } else {
                            FileUtils.close(tarInputStream2);
                            return;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        tarInputStream = tarInputStream2;
                        throw new BuildException("problem reading " + this.srcFile, e);
                    } catch (Throwable th2) {
                        th = th2;
                        tarInputStream = tarInputStream2;
                        FileUtils.close(tarInputStream);
                        throw th;
                    }
                }
            } catch (IOException e3) {
                try {
                    throw new BuildException("problem opening " + this.srcFile, e3);
                } catch (IOException e4) {
                    e = e4;
                    throw new BuildException("problem reading " + this.srcFile, e);
                }
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
