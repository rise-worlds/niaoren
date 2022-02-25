package org.apache.tools.ant.taskdefs.optional;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.taskdefs.optional.native2ascii.Native2AsciiAdapter;
import org.apache.tools.ant.taskdefs.optional.native2ascii.Native2AsciiAdapterFactory;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.ant.util.SourceFileScanner;
import org.apache.tools.ant.util.facade.FacadeTaskHelper;
import org.apache.tools.ant.util.facade.ImplementationSpecificArgument;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class Native2Ascii extends MatchingTask {
    private FacadeTaskHelper facade;
    private Mapper mapper;
    private boolean reverse = false;
    private String encoding = null;
    private File srcDir = null;
    private File destDir = null;
    private String extension = null;
    private Native2AsciiAdapter nestedAdapter = null;

    public Native2Ascii() {
        this.facade = null;
        this.facade = new FacadeTaskHelper(Native2AsciiAdapterFactory.getDefault());
    }

    public void setReverse(boolean z) {
        this.reverse = z;
    }

    public boolean getReverse() {
        return this.reverse;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setSrc(File file) {
        this.srcDir = file;
    }

    public void setDest(File file) {
        this.destDir = file;
    }

    public void setExt(String str) {
        this.extension = str;
    }

    public void setImplementation(String str) {
        if ("default".equals(str)) {
            this.facade.setImplementation(Native2AsciiAdapterFactory.getDefault());
        } else {
            this.facade.setImplementation(str);
        }
    }

    public Mapper createMapper() throws BuildException {
        if (this.mapper == null) {
            this.mapper = new Mapper(getProject());
            return this.mapper;
        }
        throw new BuildException(Expand.ERROR_MULTIPLE_MAPPERS, getLocation());
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    public ImplementationSpecificArgument createArg() {
        ImplementationSpecificArgument implementationSpecificArgument = new ImplementationSpecificArgument();
        this.facade.addImplementationArgument(implementationSpecificArgument);
        return implementationSpecificArgument;
    }

    public Path createImplementationClasspath() {
        return this.facade.getImplementationClasspath(getProject());
    }

    public void add(Native2AsciiAdapter native2AsciiAdapter) {
        if (this.nestedAdapter == null) {
            this.nestedAdapter = native2AsciiAdapter;
            return;
        }
        throw new BuildException("Can't have more than one native2ascii adapter");
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        FileNameMapper fileNameMapper;
        if (this.srcDir == null) {
            this.srcDir = getProject().resolveFile(Consts.f23430h);
        }
        File file = this.destDir;
        if (file == null) {
            throw new BuildException("The dest attribute must be set.");
        } else if (this.srcDir.equals(file) && this.extension == null && this.mapper == null) {
            throw new BuildException("The ext attribute or a mapper must be set if src and dest dirs are the same.");
        } else {
            Mapper mapper = this.mapper;
            if (mapper != null) {
                fileNameMapper = mapper.getImplementation();
            } else if (this.extension == null) {
                fileNameMapper = new IdentityMapper();
            } else {
                fileNameMapper = new ExtMapper();
            }
            String[] restrict = new SourceFileScanner(this).restrict(getDirectoryScanner(this.srcDir).getIncludedFiles(), this.srcDir, this.destDir, fileNameMapper);
            int length = restrict.length;
            if (length != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Converting ");
                sb.append(length);
                sb.append(" file");
                sb.append(length != 1 ? "s" : "");
                sb.append(" from ");
                String sb2 = sb.toString();
                log(sb2 + this.srcDir + " to " + this.destDir);
                for (int i = 0; i < restrict.length; i++) {
                    convert(restrict[i], fileNameMapper.mapFileName(restrict[i])[0]);
                }
            }
        }
    }

    private void convert(String str, String str2) throws BuildException {
        File file = new File(this.srcDir, str);
        File file2 = new File(this.destDir, str2);
        if (!file.equals(file2)) {
            String parent = file2.getParent();
            if (parent != null) {
                File file3 = new File(parent);
                if (!file3.exists() && !file3.mkdirs() && !file3.isDirectory()) {
                    throw new BuildException("cannot create parent directory " + parent);
                }
            }
            log("converting " + str, 3);
            Native2AsciiAdapter native2AsciiAdapter = this.nestedAdapter;
            if (native2AsciiAdapter == null) {
                native2AsciiAdapter = Native2AsciiAdapterFactory.getAdapter(this.facade.getImplementation(), this, createImplementationClasspath());
            }
            if (!native2AsciiAdapter.convert(this, file, file2)) {
                throw new BuildException("conversion failed");
            }
            return;
        }
        throw new BuildException("file " + file + " would overwrite its self");
    }

    public String[] getCurrentArgs() {
        return this.facade.getArgs();
    }

    /* loaded from: classes2.dex */
    private class ExtMapper implements FileNameMapper {
        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setFrom(String str) {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setTo(String str) {
        }

        private ExtMapper() {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public String[] mapFileName(String str) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                return new String[]{str.substring(0, lastIndexOf) + Native2Ascii.this.extension};
            }
            return new String[]{str + Native2Ascii.this.extension};
        }
    }
}
