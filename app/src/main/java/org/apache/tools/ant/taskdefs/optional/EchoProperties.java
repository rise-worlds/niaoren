package org.apache.tools.ant.taskdefs.optional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.PropertySet;
import org.apache.tools.ant.types.selectors.ContainsSelector;
import org.apache.tools.ant.util.CollectionUtils;
import org.apache.tools.ant.util.DOMElementWriter;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes2.dex */
public class EchoProperties extends Task {
    private static final String ATTR_NAME = "name";
    private static final String ATTR_VALUE = "value";
    private static final String PROPERTIES = "properties";
    private static final String PROPERTY = "property";
    private String prefix;
    private String regex;
    private File inFile = null;
    private File destfile = null;
    private boolean failonerror = true;
    private Vector propertySets = new Vector();
    private String format = ContainsSelector.CONTAINS_KEY;

    public void setSrcfile(File file) {
        this.inFile = file;
    }

    public void setDestfile(File file) {
        this.destfile = file;
    }

    public void setFailOnError(boolean z) {
        this.failonerror = z;
    }

    public void setPrefix(String str) {
        if (str != null && str.length() != 0) {
            this.prefix = str;
            PropertySet propertySet = new PropertySet();
            propertySet.setProject(getProject());
            propertySet.appendPrefix(str);
            addPropertyset(propertySet);
        }
    }

    public void setRegex(String str) {
        if (str != null && str.length() != 0) {
            this.regex = str;
            PropertySet propertySet = new PropertySet();
            propertySet.setProject(getProject());
            propertySet.appendRegex(str);
            addPropertyset(propertySet);
        }
    }

    public void addPropertyset(PropertySet propertySet) {
        this.propertySets.addElement(propertySet);
    }

    public void setFormat(FormatAttribute formatAttribute) {
        this.format = formatAttribute.getValue();
    }

    /* loaded from: classes2.dex */
    public static class FormatAttribute extends EnumeratedAttribute {
        private String[] formats = {"xml", ContainsSelector.CONTAINS_KEY};

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return this.formats;
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Throwable th;
        IOException e;
        Throwable th2;
        FileNotFoundException e2;
        IOException e3;
        FileInputStream fileInputStream;
        if (this.prefix == null || this.regex == null) {
            Hashtable hashtable = new Hashtable();
            OutputStream outputStream = null;
            FileInputStream fileInputStream2 = null;
            FileInputStream fileInputStream3 = null;
            FileInputStream fileInputStream4 = null;
            outputStream = null;
            outputStream = null;
            outputStream = null;
            if (this.inFile == null && this.propertySets.size() == 0) {
                hashtable.putAll(getProject().getProperties());
            } else {
                File file = this.inFile;
                if (file != null) {
                    if (!file.exists() || !this.inFile.isDirectory()) {
                        if (!this.inFile.exists() || this.inFile.canRead()) {
                            try {
                                try {
                                    fileInputStream = new FileInputStream(this.inFile);
                                } catch (Throwable th3) {
                                    th2 = th3;
                                }
                            } catch (FileNotFoundException e4) {
                                e2 = e4;
                            } catch (IOException e5) {
                                e3 = e5;
                            }
                            try {
                                Properties properties = new Properties();
                                properties.load(fileInputStream);
                                hashtable.putAll(properties);
                                FileUtils.close(fileInputStream);
                            } catch (FileNotFoundException e6) {
                                e2 = e6;
                                fileInputStream4 = fileInputStream;
                                String str = "Could not find file " + this.inFile.getAbsolutePath();
                                if (!this.failonerror) {
                                    log(str, 1);
                                    FileUtils.close(fileInputStream4);
                                    return;
                                }
                                throw new BuildException(str, e2, getLocation());
                            } catch (IOException e7) {
                                e3 = e7;
                                fileInputStream3 = fileInputStream;
                                String str2 = "Could not read file " + this.inFile.getAbsolutePath();
                                if (!this.failonerror) {
                                    log(str2, 1);
                                    FileUtils.close(fileInputStream3);
                                    return;
                                }
                                throw new BuildException(str2, e3, getLocation());
                            } catch (Throwable th4) {
                                th2 = th4;
                                fileInputStream2 = fileInputStream;
                                FileUtils.close(fileInputStream2);
                                throw th2;
                            }
                        } else if (!this.failonerror) {
                            log("Can not read from the specified srcfile!", 0);
                            return;
                        } else {
                            throw new BuildException("Can not read from the specified srcfile!", getLocation());
                        }
                    } else if (!this.failonerror) {
                        log("srcfile is a directory!", 0);
                        return;
                    } else {
                        throw new BuildException("srcfile is a directory!", getLocation());
                    }
                }
            }
            Enumeration elements = this.propertySets.elements();
            while (elements.hasMoreElements()) {
                hashtable.putAll(((PropertySet) elements.nextElement()).getProperties());
            }
            try {
                try {
                    try {
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (IOException e8) {
                    e = e8;
                }
                try {
                    if (this.destfile == null) {
                        outputStream = new ByteArrayOutputStream();
                        saveProperties(hashtable, outputStream);
                        log(outputStream.toString(), 2);
                    } else if (!this.destfile.exists() || !this.destfile.isDirectory()) {
                        if (!this.destfile.exists() || this.destfile.canWrite()) {
                            outputStream = new FileOutputStream(this.destfile);
                            saveProperties(hashtable, outputStream);
                        } else if (!this.failonerror) {
                            log("Can not write to the specified destfile!", 0);
                            return;
                        } else {
                            throw new BuildException("Can not write to the specified destfile!", getLocation());
                        }
                    } else if (!this.failonerror) {
                        log("destfile is a directory!", 0);
                        return;
                    } else {
                        throw new BuildException("destfile is a directory!", getLocation());
                    }
                    outputStream.close();
                } catch (IOException e9) {
                    e = e9;
                    if (!this.failonerror) {
                        log(e.getMessage(), 2);
                        if (outputStream != null) {
                            outputStream.close();
                            return;
                        }
                        return;
                    }
                    throw new BuildException(e, getLocation());
                } catch (Throwable th6) {
                    th = th6;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } else {
            throw new BuildException("Please specify either prefix or regex, but not both", getLocation());
        }
    }

    protected void saveProperties(Hashtable hashtable, OutputStream outputStream) throws IOException, BuildException {
        final ArrayList arrayList = new ArrayList(hashtable.keySet());
        Collections.sort(arrayList);
        Properties properties = new Properties() { // from class: org.apache.tools.ant.taskdefs.optional.EchoProperties.1
            private static final long serialVersionUID = 5090936442309201654L;

            @Override // java.util.Hashtable, java.util.Dictionary
            public Enumeration keys() {
                return CollectionUtils.asEnumeration(arrayList.iterator());
            }

            @Override // java.util.Hashtable, java.util.Map
            public Set entrySet() {
                Set entrySet = super.entrySet();
                if (!JavaEnvUtils.isKaffe()) {
                    return entrySet;
                }
                TreeSet treeSet = new TreeSet(new Comparator() { // from class: org.apache.tools.ant.taskdefs.optional.EchoProperties.1.1
                    @Override // java.util.Comparator
                    public int compare(Object obj, Object obj2) {
                        return ((String) ((Map.Entry) obj).getKey()).compareTo((String) ((Map.Entry) obj2).getKey());
                    }
                });
                treeSet.addAll(entrySet);
                return treeSet;
            }
        };
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            String obj = arrayList.get(i).toString();
            properties.setProperty(obj, hashtable.get(obj).toString());
        }
        if (ContainsSelector.CONTAINS_KEY.equals(this.format)) {
            jdkSaveProperties(properties, outputStream, "Ant properties");
        } else if ("xml".equals(this.format)) {
            xmlSaveProperties(properties, outputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Tuple implements Comparable {
        private String key;
        private String value;

        private Tuple(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.key.compareTo(((Tuple) obj).key);
        }
    }

    private List sortProperties(Properties properties) {
        ArrayList arrayList = new ArrayList(properties.size());
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            arrayList.add(new Tuple(str, properties.getProperty(str)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    protected void xmlSaveProperties(Properties properties, OutputStream outputStream) throws IOException {
        Throwable th;
        OutputStreamWriter outputStreamWriter;
        IOException e;
        Document newDocument = getDocumentBuilder().newDocument();
        Element createElement = newDocument.createElement(PROPERTIES);
        for (Tuple tuple : sortProperties(properties)) {
            Element createElement2 = newDocument.createElement("property");
            createElement2.setAttribute("name", tuple.key);
            createElement2.setAttribute("value", tuple.value);
            createElement.appendChild(createElement2);
        }
        try {
            outputStreamWriter = new OutputStreamWriter(outputStream, "UTF8");
            try {
                try {
                    outputStreamWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                    new DOMElementWriter().write(createElement, outputStreamWriter, 0, "\t");
                    outputStreamWriter.flush();
                    FileUtils.close(outputStreamWriter);
                } catch (IOException e2) {
                    e = e2;
                    throw new BuildException("Unable to write XML file", e);
                }
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close(outputStreamWriter);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            outputStreamWriter = null;
        } catch (Throwable th3) {
            th = th3;
            outputStreamWriter = null;
            FileUtils.close(outputStreamWriter);
            throw th;
        }
    }

    protected void jdkSaveProperties(Properties properties, OutputStream outputStream, String str) throws IOException {
        try {
            try {
                properties.store(outputStream, str);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException unused) {
                        log("Failed to close output stream");
                    }
                }
            } catch (IOException e) {
                throw new BuildException(e, getLocation());
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException unused2) {
                    log("Failed to close output stream");
                }
            }
            throw th;
        }
    }

    private static DocumentBuilder getDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
