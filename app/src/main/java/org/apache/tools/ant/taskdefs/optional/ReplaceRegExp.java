package org.apache.tools.ant.taskdefs.optional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.Substitution;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.regexp.Regexp;
import org.apache.tools.ant.util.regexp.RegexpUtil;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class ReplaceRegExp extends Task {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private Union resources;
    private boolean preserveLastModified = false;
    private String encoding = null;
    private File file = null;
    private String flags = "";
    private boolean byline = false;
    private RegularExpression regex = null;
    private Substitution subs = null;

    public void setFile(File file) {
        this.file = file;
    }

    public void setMatch(String str) {
        if (this.regex == null) {
            this.regex = new RegularExpression();
            this.regex.setPattern(str);
            return;
        }
        throw new BuildException("Only one regular expression is allowed");
    }

    public void setReplace(String str) {
        if (this.subs == null) {
            this.subs = new Substitution();
            this.subs.setExpression(str);
            return;
        }
        throw new BuildException("Only one substitution expression is allowed");
    }

    public void setFlags(String str) {
        this.flags = str;
    }

    @Deprecated
    public void setByLine(String str) {
        Boolean valueOf = Boolean.valueOf(str);
        if (valueOf == null) {
            valueOf = Boolean.FALSE;
        }
        this.byline = valueOf.booleanValue();
    }

    public void setByLine(boolean z) {
        this.byline = z;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void addFileset(FileSet fileSet) {
        addConfigured(fileSet);
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        if (resourceCollection.isFilesystemOnly()) {
            if (this.resources == null) {
                this.resources = new Union();
            }
            this.resources.add(resourceCollection);
            return;
        }
        throw new BuildException("only filesystem resources are supported");
    }

    public RegularExpression createRegexp() {
        if (this.regex == null) {
            this.regex = new RegularExpression();
            return this.regex;
        }
        throw new BuildException("Only one regular expression is allowed.");
    }

    public Substitution createSubstitution() {
        if (this.subs == null) {
            this.subs = new Substitution();
            return this.subs;
        }
        throw new BuildException("Only one substitution expression is allowed");
    }

    public void setPreserveLastModified(boolean z) {
        this.preserveLastModified = z;
    }

    protected String doReplace(RegularExpression regularExpression, Substitution substitution, String str, int i) {
        Regexp regexp = regularExpression.getRegexp(getProject());
        if (!regexp.matches(str, i)) {
            return str;
        }
        log("Found match; substituting", 4);
        return regexp.substitute(str, substitution.getExpression(getProject()), i);
    }

    protected void doReplace(File file, int i) throws IOException {
        String str;
        boolean z;
        int read;
        File createTempFile = FILE_UTILS.createTempFile(MSVSSConstants.WRITABLE_REPLACE, ".txt", null, true, true);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader inputStreamReader = this.encoding != null ? new InputStreamReader(fileInputStream, this.encoding) : new InputStreamReader(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            try {
                BufferedWriter outputStreamWriter = this.encoding != null ? new OutputStreamWriter(fileOutputStream, this.encoding) : new OutputStreamWriter(fileOutputStream);
                StringBuilder sb = new StringBuilder();
                sb.append("Replacing pattern '");
                sb.append(this.regex.getPattern(getProject()));
                sb.append("' with '");
                sb.append(this.subs.getExpression(getProject()));
                sb.append("' in '");
                sb.append(file.getPath());
                sb.append("'");
                sb.append(this.byline ? " by line" : "");
                if (this.flags.length() > 0) {
                    str = " with flags: '" + this.flags + "'";
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(Consts.f23430h);
                log(sb.toString(), 3);
                if (this.byline) {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    StringBuffer stringBuffer = new StringBuffer();
                    boolean z2 = false;
                    z = false;
                    do {
                        read = bufferedReader.read();
                        if (read == 13) {
                            if (z2) {
                                z |= replaceAndWrite(stringBuffer.toString(), bufferedWriter, i);
                                bufferedWriter.write(13);
                                stringBuffer = new StringBuffer();
                                continue;
                            } else {
                                z2 = true;
                                continue;
                            }
                        } else if (read == 10) {
                            z |= replaceAndWrite(stringBuffer.toString(), bufferedWriter, i);
                            if (z2) {
                                bufferedWriter.write(13);
                                z2 = false;
                            }
                            bufferedWriter.write(10);
                            stringBuffer = new StringBuffer();
                            continue;
                        } else {
                            if (z2 || read < 0) {
                                z |= replaceAndWrite(stringBuffer.toString(), bufferedWriter, i);
                                if (z2) {
                                    bufferedWriter.write(13);
                                    z2 = false;
                                }
                                stringBuffer = new StringBuffer();
                            }
                            if (read >= 0) {
                                stringBuffer.append((char) read);
                                continue;
                            } else {
                                continue;
                            }
                        }
                    } while (read >= 0);
                    outputStreamWriter = bufferedWriter;
                    inputStreamReader = bufferedReader;
                } else {
                    z = multilineReplace(inputStreamReader, outputStreamWriter, i);
                }
                inputStreamReader.close();
                outputStreamWriter.close();
                fileInputStream.close();
                if (z) {
                    log("File has changed; saving the updated file", 3);
                    try {
                        long lastModified = file.lastModified();
                        FILE_UTILS.rename(createTempFile, file);
                        if (this.preserveLastModified) {
                            FILE_UTILS.setFileLastModified(file, lastModified);
                        }
                        createTempFile = null;
                    } catch (IOException e) {
                        throw new BuildException("Couldn't rename temporary file " + createTempFile, e, getLocation());
                    }
                } else {
                    log("No change made", 4);
                }
            } finally {
                fileOutputStream.close();
            }
        } finally {
            if (createTempFile != null) {
                createTempFile.delete();
            }
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.regex == null) {
            throw new BuildException("No expression to match.");
        } else if (this.subs == null) {
            throw new BuildException("Nothing to replace expression with.");
        } else if (this.file == null || this.resources == null) {
            int asOptions = RegexpUtil.asOptions(this.flags);
            File file = this.file;
            if (file != null && file.exists()) {
                try {
                    doReplace(this.file, asOptions);
                } catch (IOException e) {
                    log("An error occurred processing file: '" + this.file.getAbsolutePath() + "': " + e.toString(), 0);
                }
            } else if (this.file != null) {
                log("The following file is missing: '" + this.file.getAbsolutePath() + "'", 0);
            }
            Union union = this.resources;
            if (union != null) {
                Iterator<Resource> it = union.iterator();
                while (it.hasNext()) {
                    File file2 = ((FileProvider) it.next().mo14823as(FileProvider.class)).getFile();
                    if (file2.exists()) {
                        try {
                            doReplace(file2, asOptions);
                        } catch (Exception e2) {
                            log("An error occurred processing file: '" + file2.getAbsolutePath() + "': " + e2.toString(), 0);
                        }
                    } else {
                        log("The following file is missing: '" + file2.getAbsolutePath() + "'", 0);
                    }
                }
            }
        } else {
            throw new BuildException("You cannot supply the 'file' attribute and resource collections at the same time.");
        }
    }

    private boolean multilineReplace(Reader reader, Writer writer, int i) throws IOException {
        return replaceAndWrite(FileUtils.safeReadFully(reader), writer, i);
    }

    private boolean replaceAndWrite(String str, Writer writer, int i) throws IOException {
        String doReplace = doReplace(this.regex, this.subs, str, i);
        writer.write(doReplace);
        return !doReplace.equals(str);
    }
}
