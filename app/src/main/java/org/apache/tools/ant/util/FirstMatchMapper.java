package org.apache.tools.ant.util;

/* loaded from: classes2.dex */
public class FirstMatchMapper extends ContainerMapper {
    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String str) {
        String[] mapFileName;
        for (FileNameMapper fileNameMapper : getMappers()) {
            if (!(fileNameMapper == null || (mapFileName = fileNameMapper.mapFileName(str)) == null)) {
                return mapFileName;
            }
        }
        return null;
    }
}
