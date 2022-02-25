package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Reference;
import org.slf4j.Marker;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class AntStructure extends Task {
    private static final String LINE_SEP = System.getProperty("line.separator");
    private File output;
    private StructurePrinter printer = new DTDPrinter();

    /* loaded from: classes2.dex */
    public interface StructurePrinter {
        void printElementDecl(PrintWriter printWriter, Project project, String str, Class<?> cls);

        void printHead(PrintWriter printWriter, Project project, Hashtable<String, Class<?>> hashtable, Hashtable<String, Class<?>> hashtable2);

        void printTail(PrintWriter printWriter);

        void printTargetDecl(PrintWriter printWriter);
    }

    public void setOutput(File file) {
        this.output = file;
    }

    public void add(StructurePrinter structurePrinter) {
        this.printer = structurePrinter;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File file = this.output;
        if (file != null) {
            PrintWriter printWriter = null;
            try {
                try {
                    try {
                        printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
                    } catch (Throwable th) {
                        if (printWriter != null) {
                            printWriter.close();
                        }
                        throw th;
                    }
                } catch (UnsupportedEncodingException unused) {
                    printWriter = new PrintWriter(new FileWriter(this.output));
                }
                this.printer.printHead(printWriter, getProject(), new Hashtable<>(getProject().getTaskDefinitions()), new Hashtable<>(getProject().getDataTypeDefinitions()));
                this.printer.printTargetDecl(printWriter);
                for (String str : getProject().getCopyOfDataTypeDefinitions().keySet()) {
                    this.printer.printElementDecl(printWriter, getProject(), str, getProject().getDataTypeDefinitions().get(str));
                }
                for (String str2 : getProject().getCopyOfTaskDefinitions().keySet()) {
                    this.printer.printElementDecl(printWriter, getProject(), str2, getProject().getTaskDefinitions().get(str2));
                }
                this.printer.printTail(printWriter);
                if (!printWriter.checkError()) {
                    printWriter.close();
                    return;
                }
                throw new IOException("Encountered an error writing Ant structure");
            } catch (IOException e) {
                throw new BuildException("Error writing " + this.output.getAbsolutePath(), e, getLocation());
            }
        } else {
            throw new BuildException("output attribute is required", getLocation());
        }
    }

    /* loaded from: classes2.dex */
    private static class DTDPrinter implements StructurePrinter {
        private static final String BOOLEAN = "%boolean;";
        private static final String TASKS = "%tasks;";
        private static final String TYPES = "%types;";
        private final Hashtable<String, String> visited;

        private DTDPrinter() {
            this.visited = new Hashtable<>();
        }

        @Override // org.apache.tools.ant.taskdefs.AntStructure.StructurePrinter
        public void printTail(PrintWriter printWriter) {
            this.visited.clear();
        }

        @Override // org.apache.tools.ant.taskdefs.AntStructure.StructurePrinter
        public void printHead(PrintWriter printWriter, Project project, Hashtable<String, Class<?>> hashtable, Hashtable<String, Class<?>> hashtable2) {
            printHead(printWriter, hashtable.keys(), hashtable2.keys());
        }

        private void printHead(PrintWriter printWriter, Enumeration<String> enumeration, Enumeration<String> enumeration2) {
            printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            printWriter.println("<!ENTITY % boolean \"(true|false|on|off|yes|no)\">");
            printWriter.print("<!ENTITY % tasks \"");
            boolean z = true;
            boolean z2 = true;
            while (enumeration.hasMoreElements()) {
                String nextElement = enumeration.nextElement();
                if (!z2) {
                    printWriter.print(" | ");
                } else {
                    z2 = false;
                }
                printWriter.print(nextElement);
            }
            printWriter.println("\">");
            printWriter.print("<!ENTITY % types \"");
            while (enumeration2.hasMoreElements()) {
                String nextElement2 = enumeration2.nextElement();
                if (!z) {
                    printWriter.print(" | ");
                } else {
                    z = false;
                }
                printWriter.print(nextElement2);
            }
            printWriter.println("\">");
            printWriter.println("");
            printWriter.print("<!ELEMENT project (target | extension-point | ");
            printWriter.print(TASKS);
            printWriter.print(" | ");
            printWriter.print(TYPES);
            printWriter.println(")*>");
            printWriter.println("<!ATTLIST project");
            printWriter.println("          name    CDATA #IMPLIED");
            printWriter.println("          default CDATA #IMPLIED");
            printWriter.println("          basedir CDATA #IMPLIED>");
            printWriter.println("");
        }

        @Override // org.apache.tools.ant.taskdefs.AntStructure.StructurePrinter
        public void printTargetDecl(PrintWriter printWriter) {
            printWriter.print("<!ELEMENT target (");
            printWriter.print(TASKS);
            printWriter.print(" | ");
            printWriter.print(TYPES);
            printWriter.println(")*>");
            printWriter.println("");
            printTargetAttrs(printWriter, "target");
            printWriter.println("<!ELEMENT extension-point EMPTY>");
            printWriter.println("");
            printTargetAttrs(printWriter, "extension-point");
        }

        private void printTargetAttrs(PrintWriter printWriter, String str) {
            printWriter.print("<!ATTLIST ");
            printWriter.println(str);
            printWriter.println("          id                      ID    #IMPLIED");
            printWriter.println("          name                    CDATA #REQUIRED");
            printWriter.println("          if                      CDATA #IMPLIED");
            printWriter.println("          unless                  CDATA #IMPLIED");
            printWriter.println("          depends                 CDATA #IMPLIED");
            printWriter.println("          extensionOf             CDATA #IMPLIED");
            printWriter.println("          onMissingExtensionPoint CDATA #IMPLIED");
            printWriter.println("          description             CDATA #IMPLIED>");
            printWriter.println("");
        }

        @Override // org.apache.tools.ant.taskdefs.AntStructure.StructurePrinter
        public void printElementDecl(PrintWriter printWriter, Project project, String str, Class<?> cls) {
            if (!this.visited.containsKey(str)) {
                this.visited.put(str, "");
                try {
                    IntrospectionHelper helper = IntrospectionHelper.getHelper(project, cls);
                    StringBuffer stringBuffer = new StringBuffer("<!ELEMENT ");
                    stringBuffer.append(str);
                    stringBuffer.append(ExpandableTextView.f6958c);
                    if (Reference.class.equals(cls)) {
                        stringBuffer.append("EMPTY>");
                        stringBuffer.append(AntStructure.LINE_SEP);
                        stringBuffer.append("<!ATTLIST ");
                        stringBuffer.append(str);
                        stringBuffer.append(AntStructure.LINE_SEP);
                        stringBuffer.append("          id ID #IMPLIED");
                        stringBuffer.append(AntStructure.LINE_SEP);
                        stringBuffer.append("          refid IDREF #IMPLIED");
                        stringBuffer.append(SimpleComparison.f23610d);
                        stringBuffer.append(AntStructure.LINE_SEP);
                        printWriter.println(stringBuffer);
                        return;
                    }
                    Vector vector = new Vector();
                    if (helper.supportsCharacters()) {
                        vector.addElement("#PCDATA");
                    }
                    if (TaskContainer.class.isAssignableFrom(cls)) {
                        vector.addElement(TASKS);
                    }
                    Enumeration<String> nestedElements = helper.getNestedElements();
                    while (nestedElements.hasMoreElements()) {
                        vector.addElement(nestedElements.nextElement());
                    }
                    if (vector.isEmpty()) {
                        stringBuffer.append("EMPTY");
                    } else {
                        stringBuffer.append("(");
                        int size = vector.size();
                        for (int i = 0; i < size; i++) {
                            if (i != 0) {
                                stringBuffer.append(" | ");
                            }
                            stringBuffer.append((String) vector.elementAt(i));
                        }
                        stringBuffer.append(")");
                        if (size > 1 || !((String) vector.elementAt(0)).equals("#PCDATA")) {
                            stringBuffer.append(Marker.ANY_MARKER);
                        }
                    }
                    stringBuffer.append(SimpleComparison.f23610d);
                    printWriter.println(stringBuffer);
                    StringBuffer stringBuffer2 = new StringBuffer("<!ATTLIST ");
                    stringBuffer2.append(str);
                    stringBuffer2.append(AntStructure.LINE_SEP);
                    stringBuffer2.append("          id ID #IMPLIED");
                    Enumeration<String> attributes = helper.getAttributes();
                    while (attributes.hasMoreElements()) {
                        String nextElement = attributes.nextElement();
                        if (!ConnectionModel.f10389a.equals(nextElement)) {
                            stringBuffer2.append(AntStructure.LINE_SEP);
                            stringBuffer2.append("          ");
                            stringBuffer2.append(nextElement);
                            stringBuffer2.append(ExpandableTextView.f6958c);
                            Class<?> attributeType = helper.getAttributeType(nextElement);
                            if (attributeType.equals(Boolean.class) || attributeType.equals(Boolean.TYPE)) {
                                stringBuffer2.append(BOOLEAN);
                                stringBuffer2.append(ExpandableTextView.f6958c);
                            } else if (Reference.class.isAssignableFrom(attributeType)) {
                                stringBuffer2.append("IDREF ");
                            } else if (EnumeratedAttribute.class.isAssignableFrom(attributeType)) {
                                try {
                                    String[] values = ((EnumeratedAttribute) attributeType.newInstance()).getValues();
                                    if (!(values == null || values.length == 0 || !areNmtokens(values))) {
                                        stringBuffer2.append("(");
                                        for (int i2 = 0; i2 < values.length; i2++) {
                                            if (i2 != 0) {
                                                stringBuffer2.append(" | ");
                                            }
                                            stringBuffer2.append(values[i2]);
                                        }
                                        stringBuffer2.append(") ");
                                    }
                                    stringBuffer2.append("CDATA ");
                                } catch (IllegalAccessException unused) {
                                    stringBuffer2.append("CDATA ");
                                } catch (InstantiationException unused2) {
                                    stringBuffer2.append("CDATA ");
                                }
                            } else if (attributeType.getSuperclass() == null || !attributeType.getSuperclass().getName().equals("java.lang.Enum")) {
                                stringBuffer2.append("CDATA ");
                            } else {
                                try {
                                    Object[] objArr = (Object[]) attributeType.getMethod("values", null).invoke(null, null);
                                    if (objArr.length == 0) {
                                        stringBuffer2.append("CDATA ");
                                    } else {
                                        stringBuffer2.append('(');
                                        for (int i3 = 0; i3 < objArr.length; i3++) {
                                            if (i3 != 0) {
                                                stringBuffer2.append(" | ");
                                            }
                                            stringBuffer2.append(attributeType.getMethod("name", null).invoke(objArr[i3], null));
                                        }
                                        stringBuffer2.append(") ");
                                    }
                                } catch (Exception unused3) {
                                    stringBuffer2.append("CDATA ");
                                }
                            }
                            stringBuffer2.append("#IMPLIED");
                        }
                    }
                    stringBuffer2.append(SimpleComparison.f23610d);
                    stringBuffer2.append(AntStructure.LINE_SEP);
                    printWriter.println(stringBuffer2);
                    int size2 = vector.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        String str2 = (String) vector.elementAt(i4);
                        if (!"#PCDATA".equals(str2) && !TASKS.equals(str2) && !TYPES.equals(str2)) {
                            printElementDecl(printWriter, project, str2, helper.getElementType(str2));
                        }
                    }
                } catch (Throwable unused4) {
                }
            }
        }

        public static final boolean isNmtoken(String str) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (!(Character.isLetterOrDigit(charAt) || charAt == '.' || charAt == '-' || charAt == '_' || charAt == ':')) {
                    return false;
                }
            }
            return true;
        }

        public static final boolean areNmtokens(String[] strArr) {
            for (String str : strArr) {
                if (!isNmtoken(str)) {
                    return false;
                }
            }
            return true;
        }
    }

    protected boolean isNmtoken(String str) {
        return DTDPrinter.isNmtoken(str);
    }

    protected boolean areNmtokens(String[] strArr) {
        return DTDPrinter.areNmtokens(strArr);
    }
}
