package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.property.GetProperty;
import org.apache.tools.ant.property.ParseProperties;
import org.apache.tools.ant.types.PropertySet;

/* loaded from: classes2.dex */
public final class ExpandProperties extends BaseFilterReader implements ChainableReader {
    private static final int EOF = -1;
    private char[] buffer;
    private int index;
    private PropertySet propertySet;

    public ExpandProperties() {
    }

    public ExpandProperties(Reader reader) {
        super(reader);
    }

    public void add(PropertySet propertySet) {
        if (this.propertySet == null) {
            this.propertySet = propertySet;
            return;
        }
        throw new BuildException("expandproperties filter accepts only one propertyset");
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        GetProperty getProperty;
        if (this.index > -1) {
            if (this.buffer == null) {
                String readFully = readFully();
                Project project = getProject();
                PropertySet propertySet = this.propertySet;
                if (propertySet == null) {
                    getProperty = PropertyHelper.getPropertyHelper(project);
                } else {
                    final Properties properties = propertySet.getProperties();
                    getProperty = new GetProperty() { // from class: org.apache.tools.ant.filters.ExpandProperties.1
                        @Override // org.apache.tools.ant.property.GetProperty
                        public Object getProperty(String str) {
                            return properties.getProperty(str);
                        }
                    };
                }
                Object parseProperties = new ParseProperties(project, PropertyHelper.getPropertyHelper(project).getExpanders(), getProperty).parseProperties(readFully);
                this.buffer = parseProperties == null ? new char[0] : parseProperties.toString().toCharArray();
            }
            int i = this.index;
            char[] cArr = this.buffer;
            if (i < cArr.length) {
                this.index = i + 1;
                return cArr[i];
            }
            this.index = -1;
        }
        return -1;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        ExpandProperties expandProperties = new ExpandProperties(reader);
        expandProperties.setProject(getProject());
        expandProperties.add(this.propertySet);
        return expandProperties;
    }
}
