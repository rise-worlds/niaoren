package org.apache.tools.ant.types;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.resources.MappedResource;
import org.apache.tools.ant.types.resources.PropertyResource;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.regexp.RegexpMatcher;
import org.apache.tools.ant.util.regexp.RegexpMatcherFactory;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class PropertySet extends DataType implements ResourceCollection {
    private Set<String> cachedNames;
    private Mapper mapper;
    private boolean dynamic = true;
    private boolean negate = false;
    private List<PropertyRef> ptyRefs = new ArrayList();
    private List<PropertySet> setRefs = new ArrayList();
    private boolean noAttributeSet = true;

    /* loaded from: classes2.dex */
    public static class PropertyRef {
        private String builtin;
        private int count;
        private String name;
        private String prefix;
        private String regex;

        public void setName(String str) {
            assertValid("name", str);
            this.name = str;
        }

        public void setRegex(String str) {
            assertValid(FilenameSelector.REGEX_KEY, str);
            this.regex = str;
        }

        public void setPrefix(String str) {
            assertValid("prefix", str);
            this.prefix = str;
        }

        public void setBuiltin(BuiltinPropertySetName builtinPropertySetName) {
            String value = builtinPropertySetName.getValue();
            assertValid("builtin", value);
            this.builtin = value;
        }

        private void assertValid(String str, String str2) {
            if (str2 == null || str2.length() < 1) {
                throw new BuildException("Invalid attribute: " + str);
            }
            int i = this.count + 1;
            this.count = i;
            if (i != 1) {
                throw new BuildException("Attributes name, regex, and prefix are mutually exclusive");
            }
        }

        public String toString() {
            return "name=" + this.name + ", regex=" + this.regex + ", prefix=" + this.prefix + ", builtin=" + this.builtin;
        }
    }

    public void appendName(String str) {
        PropertyRef propertyRef = new PropertyRef();
        propertyRef.setName(str);
        addPropertyref(propertyRef);
    }

    public void appendRegex(String str) {
        PropertyRef propertyRef = new PropertyRef();
        propertyRef.setRegex(str);
        addPropertyref(propertyRef);
    }

    public void appendPrefix(String str) {
        PropertyRef propertyRef = new PropertyRef();
        propertyRef.setPrefix(str);
        addPropertyref(propertyRef);
    }

    public void appendBuiltin(BuiltinPropertySetName builtinPropertySetName) {
        PropertyRef propertyRef = new PropertyRef();
        propertyRef.setBuiltin(builtinPropertySetName);
        addPropertyref(propertyRef);
    }

    public void setMapper(String str, String str2, String str3) {
        Mapper createMapper = createMapper();
        Mapper.MapperType mapperType = new Mapper.MapperType();
        mapperType.setValue(str);
        createMapper.setType(mapperType);
        createMapper.setFrom(str2);
        createMapper.setTo(str3);
    }

    public void addPropertyref(PropertyRef propertyRef) {
        assertNotReference();
        setChecked(false);
        this.ptyRefs.add(propertyRef);
    }

    public void addPropertyset(PropertySet propertySet) {
        assertNotReference();
        setChecked(false);
        this.setRefs.add(propertySet);
    }

    public Mapper createMapper() {
        assertNotReference();
        if (this.mapper == null) {
            this.mapper = new Mapper(getProject());
            setChecked(false);
            return this.mapper;
        }
        throw new BuildException("Too many <mapper>s!");
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    public void setDynamic(boolean z) {
        assertNotReference();
        this.dynamic = z;
    }

    public void setNegate(boolean z) {
        assertNotReference();
        this.negate = z;
    }

    public boolean getDynamic() {
        if (isReference()) {
            return getRef().dynamic;
        }
        dieOnCircularReference();
        return this.dynamic;
    }

    public Mapper getMapper() {
        if (isReference()) {
            return getRef().mapper;
        }
        dieOnCircularReference();
        return this.mapper;
    }

    private Hashtable<String, Object> getAllSystemProperties() {
        Hashtable<String, Object> hashtable = new Hashtable<>();
        Enumeration<?> propertyNames = System.getProperties().propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            hashtable.put(str, System.getProperties().getProperty(str));
        }
        return hashtable;
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.putAll(getPropertyMap());
        return properties;
    }

    private Map<String, Object> getPropertyMap() {
        String[] mapFileName;
        if (isReference()) {
            return getRef().getPropertyMap();
        }
        dieOnCircularReference();
        Mapper mapper = getMapper();
        FileNameMapper implementation = mapper == null ? null : mapper.getImplementation();
        Map<String, Object> effectiveProperties = getEffectiveProperties();
        Set<String> propertyNames = getPropertyNames(effectiveProperties);
        HashMap hashMap = new HashMap();
        for (String str : propertyNames) {
            Object obj = effectiveProperties.get(str);
            if (obj != null) {
                if (!(implementation == null || (mapFileName = implementation.mapFileName(str)) == null)) {
                    str = mapFileName[0];
                }
                hashMap.put(str, obj);
            }
        }
        return hashMap;
    }

    private Map<String, Object> getEffectiveProperties() {
        Project project = getProject();
        Hashtable<String, Object> allSystemProperties = project == null ? getAllSystemProperties() : project.getProperties();
        for (PropertySet propertySet : this.setRefs) {
            allSystemProperties.putAll(propertySet.getPropertyMap());
        }
        return allSystemProperties;
    }

    private Set<String> getPropertyNames(Map<String, Object> map) {
        Set<String> set;
        if (getDynamic() || (set = this.cachedNames) == null) {
            set = new HashSet<>();
            addPropertyNames(set, map);
            for (PropertySet propertySet : this.setRefs) {
                set.addAll(propertySet.getPropertyMap().keySet());
            }
            if (this.negate) {
                HashSet hashSet = new HashSet(map.keySet());
                hashSet.removeAll(set);
                set = hashSet;
            }
            if (!getDynamic()) {
                this.cachedNames = set;
            }
        }
        return set;
    }

    private void addPropertyNames(Set<String> set, Map<String, Object> map) {
        if (isReference()) {
            getRef().addPropertyNames(set, map);
        }
        dieOnCircularReference();
        for (PropertyRef propertyRef : this.ptyRefs) {
            if (propertyRef.name != null) {
                if (map.get(propertyRef.name) != null) {
                    set.add(propertyRef.name);
                }
            } else if (propertyRef.prefix != null) {
                for (String str : map.keySet()) {
                    if (str.startsWith(propertyRef.prefix)) {
                        set.add(str);
                    }
                }
            } else if (propertyRef.regex != null) {
                RegexpMatcher newRegexpMatcher = new RegexpMatcherFactory().newRegexpMatcher();
                newRegexpMatcher.setPattern(propertyRef.regex);
                for (String str2 : map.keySet()) {
                    if (newRegexpMatcher.matches(str2)) {
                        set.add(str2);
                    }
                }
            } else if (propertyRef.builtin == null) {
                throw new BuildException("Impossible: Invalid PropertyRef!");
            } else if (propertyRef.builtin.equals("all")) {
                set.addAll(map.keySet());
            } else if (propertyRef.builtin.equals("system")) {
                set.addAll(getAllSystemProperties().keySet());
            } else if (propertyRef.builtin.equals("commandline")) {
                set.addAll(getProject().getUserProperties().keySet());
            } else {
                throw new BuildException("Impossible: Invalid builtin attribute!");
            }
        }
    }

    protected PropertySet getRef() {
        return (PropertySet) getCheckedRef(PropertySet.class, "propertyset");
    }

    @Override // org.apache.tools.ant.types.DataType
    public final void setRefid(Reference reference) {
        if (this.noAttributeSet) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    protected final void assertNotReference() {
        if (!isReference()) {
            this.noAttributeSet = false;
            return;
        }
        throw tooManyAttributes();
    }

    /* loaded from: classes2.dex */
    public static class BuiltinPropertySetName extends EnumeratedAttribute {
        static final String ALL = "all";
        static final String COMMANDLINE = "commandline";
        static final String SYSTEM = "system";

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{ALL, SYSTEM, COMMANDLINE};
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        if (isReference()) {
            return getRef().toString();
        }
        dieOnCircularReference();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(getPropertyMap()).entrySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append((String) entry.getKey());
            sb.append(SimpleComparison.f23609c);
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public Iterator<Resource> iterator() {
        if (isReference()) {
            return getRef().iterator();
        }
        dieOnCircularReference();
        Set<String> propertyNames = getPropertyNames(getEffectiveProperties());
        Mapper mapper = getMapper();
        final FileNameMapper implementation = mapper == null ? null : mapper.getImplementation();
        final Iterator<String> it = propertyNames.iterator();
        return new Iterator<Resource>() { // from class: org.apache.tools.ant.types.PropertySet.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public Resource next() {
                PropertyResource propertyResource = new PropertyResource(PropertySet.this.getProject(), (String) it.next());
                FileNameMapper fileNameMapper = implementation;
                return fileNameMapper == null ? propertyResource : new MappedResource(propertyResource, fileNameMapper);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public int size() {
        return isReference() ? getRef().size() : getProperties().size();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        if (isReference()) {
            return getRef().isFilesystemOnly();
        }
        dieOnCircularReference();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                if (this.mapper != null) {
                    pushAndInvokeCircularReferenceCheck(this.mapper, stack, project);
                }
                for (PropertySet propertySet : this.setRefs) {
                    pushAndInvokeCircularReferenceCheck(propertySet, stack, project);
                }
                setChecked(true);
            }
        }
    }
}
