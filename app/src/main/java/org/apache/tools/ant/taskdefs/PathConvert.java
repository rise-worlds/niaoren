package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.Resources;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class PathConvert extends Task {
    private static boolean onWindows = C3209Os.isFamily(C3209Os.FAMILY_DOS);
    private boolean preserveDuplicates;
    private Resources path = null;
    private Reference refid = null;
    private String targetOS = null;
    private boolean targetWindows = false;
    private boolean setonempty = true;
    private String property = null;
    private Vector prefixMap = new Vector();
    private String pathSep = null;
    private String dirSep = null;
    private Mapper mapper = null;

    /* loaded from: classes2.dex */
    public class MapEntry {
        private String from = null;

        /* renamed from: to */
        private String f14755to = null;

        public MapEntry() {
        }

        public void setFrom(String str) {
            this.from = str;
        }

        public void setTo(String str) {
            this.f14755to = str;
        }

        public String apply(String str) {
            if (this.from == null || this.f14755to == null) {
                throw new BuildException("Both 'from' and 'to' must be set in a map entry");
            }
            if (!(PathConvert.onWindows ? str.toLowerCase().replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX) : str).startsWith(PathConvert.onWindows ? this.from.toLowerCase().replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX) : this.from)) {
                return str;
            }
            return this.f14755to + str.substring(this.from.length());
        }
    }

    /* loaded from: classes2.dex */
    public static class TargetOs extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{C3209Os.FAMILY_WINDOWS, C3209Os.FAMILY_UNIX, C3209Os.FAMILY_NETWARE, C3209Os.FAMILY_OS2, C3209Os.FAMILY_TANDEM};
        }
    }

    public Path createPath() {
        if (!isReference()) {
            Path path = new Path(getProject());
            add(path);
            return path;
        }
        throw noChildrenAllowed();
    }

    public void add(ResourceCollection resourceCollection) {
        if (!isReference()) {
            getPath().add(resourceCollection);
            return;
        }
        throw noChildrenAllowed();
    }

    private synchronized Resources getPath() {
        if (this.path == null) {
            this.path = new Resources(getProject());
            this.path.setCache(true);
        }
        return this.path;
    }

    public MapEntry createMap() {
        MapEntry mapEntry = new MapEntry();
        this.prefixMap.addElement(mapEntry);
        return mapEntry;
    }

    @Deprecated
    public void setTargetos(String str) {
        TargetOs targetOs = new TargetOs();
        targetOs.setValue(str);
        setTargetos(targetOs);
    }

    public void setTargetos(TargetOs targetOs) {
        this.targetOS = targetOs.getValue();
        this.targetWindows = !this.targetOS.equals(C3209Os.FAMILY_UNIX) && !this.targetOS.equals(C3209Os.FAMILY_TANDEM);
    }

    public void setSetonempty(boolean z) {
        this.setonempty = z;
    }

    public void setProperty(String str) {
        this.property = str;
    }

    public void setRefid(Reference reference) {
        if (this.path == null) {
            this.refid = reference;
            return;
        }
        throw noChildrenAllowed();
    }

    public void setPathSep(String str) {
        this.pathSep = str;
    }

    public void setDirSep(String str) {
        this.dirSep = str;
    }

    public void setPreserveDuplicates(boolean z) {
        this.preserveDuplicates = z;
    }

    public boolean isPreserveDuplicates() {
        return this.preserveDuplicates;
    }

    public boolean isReference() {
        return this.refid != null;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Resources resources = this.path;
        String str = this.pathSep;
        String str2 = this.dirSep;
        try {
            if (isReference()) {
                Object referencedObject = this.refid.getReferencedObject(getProject());
                if (referencedObject instanceof ResourceCollection) {
                    getPath().add((ResourceCollection) referencedObject);
                } else {
                    throw new BuildException("refid '" + this.refid.getRefId() + "' does not refer to a resource collection.");
                }
            }
            validateSetup();
            String str3 = onWindows ? "\\" : "/";
            StringBuffer stringBuffer = new StringBuffer();
            Iterable union = isPreserveDuplicates() ? this.path : new Union(this.path);
            ArrayList<String> arrayList = new ArrayList();
            FileNameMapper identityMapper = this.mapper == null ? new IdentityMapper() : this.mapper.getImplementation();
            Iterator<Resource> it = union.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String[] mapFileName = identityMapper.mapFileName(String.valueOf(it.next()));
                for (int i = 0; mapFileName != null && i < mapFileName.length; i++) {
                    arrayList.add(mapFileName[i]);
                }
            }
            boolean z = true;
            for (String str4 : arrayList) {
                String mapElement = mapElement(str4);
                if (!z) {
                    stringBuffer.append(this.pathSep);
                }
                StringTokenizer stringTokenizer = new StringTokenizer(mapElement, str3, true);
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (str3.equals(nextToken)) {
                        nextToken = this.dirSep;
                    }
                    stringBuffer.append(nextToken);
                }
                z = false;
            }
            if (this.setonempty || stringBuffer.length() > 0) {
                String stringBuffer2 = stringBuffer.toString();
                if (this.property == null) {
                    log(stringBuffer2);
                } else {
                    log("Set property " + this.property + " = " + stringBuffer2, 3);
                    getProject().setNewProperty(this.property, stringBuffer2);
                }
            }
        } finally {
            this.path = resources;
            this.dirSep = str2;
            this.pathSep = str;
        }
    }

    private String mapElement(String str) {
        int size = this.prefixMap.size();
        if (size == 0) {
            return str;
        }
        for (int i = 0; i < size; i++) {
            String apply = ((MapEntry) this.prefixMap.elementAt(i)).apply(str);
            if (apply != str) {
                return apply;
            }
        }
        return str;
    }

    public void addMapper(Mapper mapper) {
        if (this.mapper == null) {
            this.mapper = mapper;
            return;
        }
        throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS);
    }

    public void add(FileNameMapper fileNameMapper) {
        Mapper mapper = new Mapper(getProject());
        mapper.add(fileNameMapper);
        addMapper(mapper);
    }

    private void validateSetup() throws BuildException {
        if (this.path != null) {
            String str = File.separator;
            String str2 = File.pathSeparator;
            if (this.targetOS != null) {
                str2 = this.targetWindows ? C4963cj.f20745b : ":";
                str = this.targetWindows ? "\\" : "/";
            }
            String str3 = this.pathSep;
            if (str3 != null) {
                str2 = str3;
            }
            String str4 = this.dirSep;
            if (str4 != null) {
                str = str4;
            }
            this.pathSep = str2;
            this.dirSep = str;
            return;
        }
        throw new BuildException("You must specify a path to convert");
    }

    private BuildException noChildrenAllowed() {
        return new BuildException("You must not specify nested elements when using the refid attribute.");
    }
}
