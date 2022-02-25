package org.apache.tools.ant.taskdefs.optional;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.taskdefs.Move;
import org.apache.tools.ant.types.Mapper;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class RenameExtensions extends MatchingTask {
    private File srcDir;
    private String fromExtension = "";
    private String toExtension = "";
    private boolean replace = false;
    private Mapper.MapperType globType = new Mapper.MapperType();

    public RenameExtensions() {
        this.globType.setValue("glob");
    }

    public void setFromExtension(String str) {
        this.fromExtension = str;
    }

    public void setToExtension(String str) {
        this.toExtension = str;
    }

    public void setReplace(boolean z) {
        this.replace = z;
    }

    public void setSrcDir(File file) {
        this.srcDir = file;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.fromExtension == null || this.toExtension == null || this.srcDir == null) {
            throw new BuildException("srcDir, fromExtension and toExtension attributes must be set!");
        }
        log("DEPRECATED - The renameext task is deprecated.  Use move instead.", 1);
        log("Replace this with:", 2);
        log("<move todir=\"" + this.srcDir + "\" overwrite=\"" + this.replace + "\">", 2);
        StringBuilder sb = new StringBuilder();
        sb.append("  <fileset dir=\"");
        sb.append(this.srcDir);
        sb.append("\" />");
        log(sb.toString(), 2);
        log("  <mapper type=\"glob\"", 2);
        log("          from=\"*" + this.fromExtension + "\"", 2);
        log("          to=\"*" + this.toExtension + "\" />", 2);
        log("</move>", 2);
        log("using the same patterns on <fileset> as you've used here", 2);
        Move move = new Move();
        move.bindToOwner(this);
        move.setOwningTarget(getOwningTarget());
        move.setTaskName(getTaskName());
        move.setLocation(getLocation());
        move.setTodir(this.srcDir);
        move.setOverwrite(this.replace);
        this.fileset.setDir(this.srcDir);
        move.addFileset(this.fileset);
        Mapper createMapper = move.createMapper();
        createMapper.setType(this.globType);
        createMapper.setFrom(Marker.ANY_MARKER + this.fromExtension);
        createMapper.setTo(Marker.ANY_MARKER + this.toExtension);
        move.execute();
    }
}
