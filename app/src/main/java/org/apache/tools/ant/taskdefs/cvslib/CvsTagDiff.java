package org.apache.tools.ant.taskdefs.cvslib;

import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.AbstractCvsTask;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.util.CollectionUtils;
import org.apache.tools.ant.util.DOMElementWriter;
import org.apache.tools.ant.util.DOMUtils;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes2.dex */
public class CvsTagDiff extends AbstractCvsTask {
    static final String FILE_HAS_CHANGED = " changed from revision ";
    static final String FILE_IS_NEW = " is new;";
    static final String FILE_STRING = "File ";
    static final String FILE_WAS_REMOVED = " is removed";
    static final String REVISION = "revision ";
    static final String TO_STRING = " to ";
    private File mydestfile;
    private String myendDate;
    private String myendTag;
    private String mypackage;
    private String mystartDate;
    private String mystartTag;
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final DOMElementWriter DOM_WRITER = new DOMElementWriter();
    static final int FILE_STRING_LENGTH = 5;
    private boolean ignoreRemoved = false;
    private List packageNames = new ArrayList();
    private String[] packageNamePrefixes = null;
    private int[] packageNamePrefixLengths = null;

    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask
    public void setPackage(String str) {
        this.mypackage = str;
    }

    public void setStartTag(String str) {
        this.mystartTag = str;
    }

    public void setStartDate(String str) {
        this.mystartDate = str;
    }

    public void setEndTag(String str) {
        this.myendTag = str;
    }

    public void setEndDate(String str) {
        this.myendDate = str;
    }

    public void setDestFile(File file) {
        this.mydestfile = file;
    }

    public void setIgnoreRemoved(boolean z) {
        this.ignoreRemoved = z;
    }

    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask, org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Throwable th;
        File file;
        validate();
        addCommandArgument("rdiff");
        addCommandArgument("-s");
        if (this.mystartTag != null) {
            addCommandArgument("-r");
            addCommandArgument(this.mystartTag);
        } else {
            addCommandArgument(MSVSSConstants.FLAG_CODEDIFF);
            addCommandArgument(this.mystartDate);
        }
        if (this.myendTag != null) {
            addCommandArgument("-r");
            addCommandArgument(this.myendTag);
        } else {
            addCommandArgument(MSVSSConstants.FLAG_CODEDIFF);
            addCommandArgument(this.myendDate);
        }
        setCommand("");
        try {
            handlePackageNames();
            file = FILE_UTILS.createTempFile("cvstagdiff", ".log", null, true, true);
            try {
                setOutput(file);
                super.execute();
                writeTagDiff(parseRDiff(file));
                this.packageNamePrefixes = null;
                this.packageNamePrefixLengths = null;
                this.packageNames.clear();
                if (file != null) {
                    file.delete();
                }
            } catch (Throwable th2) {
                th = th2;
                this.packageNamePrefixes = null;
                this.packageNamePrefixLengths = null;
                this.packageNames.clear();
                if (file != null) {
                    file.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            file = null;
        }
    }

    private CvsTagEntry[] parseRDiff(File file) throws BuildException {
        Throwable th;
        IOException e;
        BufferedReader bufferedReader = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            Vector vector = new Vector();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                String removePackageName = removePackageName(readLine, this.packageNamePrefixes, this.packageNamePrefixLengths);
                if (removePackageName != null && !doFileIsNew(vector, removePackageName) && !doFileHasChanged(vector, removePackageName)) {
                    doFileWasRemoved(vector, removePackageName);
                }
            }
            CvsTagEntry[] cvsTagEntryArr = new CvsTagEntry[vector.size()];
            vector.copyInto(cvsTagEntryArr);
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                log(e3.toString(), 0);
            }
            return cvsTagEntryArr;
        } catch (IOException e4) {
            e = e4;
            throw new BuildException("Error in parsing", e);
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                    log(e5.toString(), 0);
                }
            }
            throw th;
        }
    }

    private boolean doFileIsNew(Vector vector, String str) {
        int indexOf = str.indexOf(FILE_IS_NEW);
        if (indexOf == -1) {
            return false;
        }
        String substring = str.substring(0, indexOf);
        String str2 = null;
        int indexOf2 = str.indexOf(REVISION, indexOf);
        if (indexOf2 != -1) {
            str2 = str.substring(indexOf2 + 9);
        }
        CvsTagEntry cvsTagEntry = new CvsTagEntry(substring, str2);
        vector.addElement(cvsTagEntry);
        log(cvsTagEntry.toString(), 3);
        return true;
    }

    private boolean doFileHasChanged(Vector vector, String str) {
        int indexOf = str.indexOf(FILE_HAS_CHANGED);
        if (indexOf == -1) {
            return false;
        }
        String substring = str.substring(0, indexOf);
        int indexOf2 = str.indexOf(TO_STRING, indexOf);
        CvsTagEntry cvsTagEntry = new CvsTagEntry(substring, str.substring(indexOf2 + 4), str.substring(indexOf + 23, indexOf2));
        vector.addElement(cvsTagEntry);
        log(cvsTagEntry.toString(), 3);
        return true;
    }

    private boolean doFileWasRemoved(Vector vector, String str) {
        int indexOf;
        if (this.ignoreRemoved || (indexOf = str.indexOf(FILE_WAS_REMOVED)) == -1) {
            return false;
        }
        String substring = str.substring(0, indexOf);
        int indexOf2 = str.indexOf(REVISION, indexOf);
        CvsTagEntry cvsTagEntry = new CvsTagEntry(substring, null, indexOf2 != -1 ? str.substring(indexOf2 + 9) : null);
        vector.addElement(cvsTagEntry);
        log(cvsTagEntry.toString(), 3);
        return true;
    }

    private void writeTagDiff(CvsTagEntry[] cvsTagEntryArr) throws BuildException {
        UnsupportedEncodingException e;
        IOException e2;
        FileOutputStream fileOutputStream;
        PrintWriter printWriter;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(this.mydestfile);
                } catch (IOException e3) {
                    log(e3.toString(), 0);
                    return;
                }
            } catch (UnsupportedEncodingException e4) {
                e = e4;
            } catch (IOException e5) {
                e2 = e5;
            }
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
            printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            Document newDocument = DOMUtils.newDocument();
            Element createElement = newDocument.createElement("tagdiff");
            if (this.mystartTag != null) {
                createElement.setAttribute("startTag", this.mystartTag);
            } else {
                createElement.setAttribute("startDate", this.mystartDate);
            }
            if (this.myendTag != null) {
                createElement.setAttribute("endTag", this.myendTag);
            } else {
                createElement.setAttribute("endDate", this.myendDate);
            }
            createElement.setAttribute("cvsroot", getCvsRoot());
            createElement.setAttribute(ServiceManagerNative.PACKAGE, CollectionUtils.flattenToString(this.packageNames));
            DOM_WRITER.openElement(createElement, printWriter, 0, "\t");
            printWriter.println();
            for (CvsTagEntry cvsTagEntry : cvsTagEntryArr) {
                writeTagEntry(newDocument, printWriter, cvsTagEntry);
            }
            DOM_WRITER.closeElement(createElement, printWriter, 0, "\t", true);
            printWriter.flush();
        } catch (UnsupportedEncodingException e6) {
            e = e6;
            fileOutputStream2 = fileOutputStream;
            log(e.toString(), 0);
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
                return;
            }
            return;
        } catch (IOException e7) {
            e2 = e7;
            throw new BuildException(e2.toString(), e2);
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e8) {
                    log(e8.toString(), 0);
                }
            }
            throw th;
        }
        if (!printWriter.checkError()) {
            printWriter.close();
            fileOutputStream.close();
            return;
        }
        throw new IOException("Encountered an error writing tagdiff");
    }

    private void writeTagEntry(Document document, PrintWriter printWriter, CvsTagEntry cvsTagEntry) throws IOException {
        Element createElement = document.createElement("entry");
        Element createChildElement = DOMUtils.createChildElement(createElement, "file");
        DOMUtils.appendCDATAElement(createChildElement, "name", cvsTagEntry.getFile());
        if (cvsTagEntry.getRevision() != null) {
            DOMUtils.appendTextElement(createChildElement, "revision", cvsTagEntry.getRevision());
        }
        if (cvsTagEntry.getPreviousRevision() != null) {
            DOMUtils.appendTextElement(createChildElement, "prevrevision", cvsTagEntry.getPreviousRevision());
        }
        DOM_WRITER.write(createElement, printWriter, 1, "\t");
    }

    private void validate() throws BuildException {
        if (this.mypackage == null && getModules().size() == 0) {
            throw new BuildException("Package/module must be set.");
        } else if (this.mydestfile == null) {
            throw new BuildException("Destfile must be set.");
        } else if (this.mystartTag == null && this.mystartDate == null) {
            throw new BuildException("Start tag or start date must be set.");
        } else if (this.mystartTag != null && this.mystartDate != null) {
            throw new BuildException("Only one of start tag and start date must be set.");
        } else if (this.myendTag == null && this.myendDate == null) {
            throw new BuildException("End tag or end date must be set.");
        } else if (this.myendTag != null && this.myendDate != null) {
            throw new BuildException("Only one of end tag and end date must be set.");
        }
    }

    private void handlePackageNames() {
        String str = this.mypackage;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                this.packageNames.add(nextToken);
                addCommandArgument(nextToken);
            }
        }
        for (AbstractCvsTask.Module module : getModules()) {
            this.packageNames.add(module.getName());
        }
        this.packageNamePrefixes = new String[this.packageNames.size()];
        this.packageNamePrefixLengths = new int[this.packageNames.size()];
        int i = 0;
        while (true) {
            String[] strArr = this.packageNamePrefixes;
            if (i < strArr.length) {
                strArr[i] = FILE_STRING + this.packageNames.get(i) + "/";
                this.packageNamePrefixLengths[i] = this.packageNamePrefixes[i].length();
                i++;
            } else {
                return;
            }
        }
    }

    private static String removePackageName(String str, String[] strArr, int[] iArr) {
        if (str.length() < FILE_STRING_LENGTH) {
            return null;
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= strArr.length) {
                break;
            } else if (str.startsWith(strArr[i])) {
                str = str.substring(iArr[i]);
                z = true;
                break;
            } else {
                i++;
            }
        }
        return !z ? str.substring(FILE_STRING_LENGTH) : str;
    }
}
