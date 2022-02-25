package org.apache.tools.ant.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JAXPUtils;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* loaded from: classes2.dex */
public class XMLCatalog extends DataType implements Cloneable, URIResolver, EntityResolver {
    public static final String APACHE_RESOLVER = "org.apache.tools.ant.types.resolver.ApacheCatalogResolver";
    public static final String CATALOG_RESOLVER = "org.apache.xml.resolver.tools.CatalogResolver";
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private Path catalogPath;
    private Path classpath;
    private Vector<ResourceLocation> elements = new Vector<>();
    private CatalogResolver catalogResolver = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface CatalogResolver extends URIResolver, EntityResolver {
        @Override // javax.xml.transform.URIResolver
        Source resolve(String str, String str2) throws TransformerException;

        @Override // org.xml.sax.EntityResolver
        InputSource resolveEntity(String str, String str2);
    }

    public XMLCatalog() {
        setChecked(false);
    }

    private Vector<ResourceLocation> getElements() {
        return getRef().elements;
    }

    private Path getClasspath() {
        return getRef().classpath;
    }

    public Path createClasspath() {
        if (!isReference()) {
            if (this.classpath == null) {
                this.classpath = new Path(getProject());
            }
            setChecked(false);
            return this.classpath.createPath();
        }
        throw noChildrenAllowed();
    }

    public void setClasspath(Path path) {
        if (!isReference()) {
            Path path2 = this.classpath;
            if (path2 == null) {
                this.classpath = path;
            } else {
                path2.append(path);
            }
            setChecked(false);
            return;
        }
        throw tooManyAttributes();
    }

    public void setClasspathRef(Reference reference) {
        if (!isReference()) {
            createClasspath().setRefid(reference);
            setChecked(false);
            return;
        }
        throw tooManyAttributes();
    }

    public Path createCatalogPath() {
        if (!isReference()) {
            if (this.catalogPath == null) {
                this.catalogPath = new Path(getProject());
            }
            setChecked(false);
            return this.catalogPath.createPath();
        }
        throw noChildrenAllowed();
    }

    public void setCatalogPathRef(Reference reference) {
        if (!isReference()) {
            createCatalogPath().setRefid(reference);
            setChecked(false);
            return;
        }
        throw tooManyAttributes();
    }

    public Path getCatalogPath() {
        return getRef().catalogPath;
    }

    public void addDTD(ResourceLocation resourceLocation) throws BuildException {
        if (!isReference()) {
            getElements().addElement(resourceLocation);
            setChecked(false);
            return;
        }
        throw noChildrenAllowed();
    }

    public void addEntity(ResourceLocation resourceLocation) throws BuildException {
        addDTD(resourceLocation);
    }

    public void addConfiguredXMLCatalog(XMLCatalog xMLCatalog) {
        if (!isReference()) {
            getElements().addAll(xMLCatalog.getElements());
            createClasspath().append(xMLCatalog.getClasspath());
            createCatalogPath().append(xMLCatalog.getCatalogPath());
            setChecked(false);
            return;
        }
        throw noChildrenAllowed();
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) throws BuildException {
        if (this.elements.isEmpty()) {
            super.setRefid(reference);
            return;
        }
        throw tooManyAttributes();
    }

    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String str, String str2) throws SAXException, IOException {
        if (isReference()) {
            return getRef().resolveEntity(str, str2);
        }
        dieOnCircularReference();
        log("resolveEntity: '" + str + "': '" + str2 + "'", 4);
        InputSource resolveEntity = getCatalogResolver().resolveEntity(str, str2);
        if (resolveEntity == null) {
            log("No matching catalog entry found, parser will use: '" + str2 + "'", 4);
        }
        return resolveEntity;
    }

    @Override // javax.xml.transform.URIResolver
    public Source resolve(String str, String str2) throws TransformerException {
        URL url;
        if (isReference()) {
            return getRef().resolve(str, str2);
        }
        dieOnCircularReference();
        String removeFragment = removeFragment(str);
        log("resolve: '" + removeFragment + "' with base: '" + str2 + "'", 4);
        SAXSource sAXSource = (SAXSource) getCatalogResolver().resolve(removeFragment, str2);
        if (sAXSource == null) {
            log("No matching catalog entry found, parser will use: '" + str + "'", 4);
            sAXSource = new SAXSource();
            try {
                if (str2 == null) {
                    url = FILE_UTILS.getFileURL(getProject().getBaseDir());
                } else {
                    url = new URL(str2);
                }
                if (removeFragment.length() != 0) {
                    url = new URL(url, removeFragment);
                }
                sAXSource.setInputSource(new InputSource(url.toString()));
            } catch (MalformedURLException unused) {
                sAXSource.setInputSource(new InputSource(removeFragment));
            }
        }
        setEntityResolver(sAXSource);
        return sAXSource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                if (this.classpath != null) {
                    pushAndInvokeCircularReferenceCheck(this.classpath, stack, project);
                }
                if (this.catalogPath != null) {
                    pushAndInvokeCircularReferenceCheck(this.catalogPath, stack, project);
                }
                setChecked(true);
            }
        }
    }

    private XMLCatalog getRef() {
        return !isReference() ? this : (XMLCatalog) getCheckedRef(XMLCatalog.class, "xmlcatalog");
    }

    private CatalogResolver getCatalogResolver() {
        if (this.catalogResolver == null) {
            try {
                Class<?> cls = Class.forName(APACHE_RESOLVER, true, Class.forName(CATALOG_RESOLVER, true, Class.forName(APACHE_RESOLVER, true, getProject().createClassLoader(Path.systemClasspath)).getClassLoader()).getClassLoader());
                this.catalogResolver = new ExternalResolver(cls, cls.newInstance());
            } catch (Throwable th) {
                this.catalogResolver = new InternalResolver();
                if (!(getCatalogPath() == null || getCatalogPath().list().length == 0)) {
                    log("Warning: XML resolver not found; external catalogs will be ignored", 1);
                }
                log("Failed to load Apache resolver: " + th, 4);
            }
        }
        return this.catalogResolver;
    }

    private void setEntityResolver(SAXSource sAXSource) throws TransformerException {
        XMLReader xMLReader = sAXSource.getXMLReader();
        if (xMLReader == null) {
            SAXParserFactory newInstance = SAXParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            try {
                xMLReader = newInstance.newSAXParser().getXMLReader();
            } catch (ParserConfigurationException e) {
                throw new TransformerException(e);
            } catch (SAXException e2) {
                throw new TransformerException(e2);
            }
        }
        xMLReader.setEntityResolver(this);
        sAXSource.setXMLReader(xMLReader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResourceLocation findMatchingEntry(String str) {
        Iterator<ResourceLocation> it = getElements().iterator();
        while (it.hasNext()) {
            ResourceLocation next = it.next();
            if (next.getPublicId().equals(str)) {
                return next;
            }
        }
        return null;
    }

    private String removeFragment(String str) {
        int indexOf = str.indexOf("#");
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputSource filesystemLookup(ResourceLocation resourceLocation) {
        URL fileURL;
        URL url;
        String fromURI;
        String replace = resourceLocation.getLocation().replace(File.separatorChar, IOUtils.DIR_SEPARATOR_UNIX);
        if (resourceLocation.getBase() != null) {
            fileURL = resourceLocation.getBase();
        } else {
            try {
                fileURL = FILE_UTILS.getFileURL(getProject().getBaseDir());
            } catch (MalformedURLException unused) {
                throw new BuildException("Project basedir cannot be converted to a URL");
            }
        }
        try {
            url = new URL(fileURL, replace);
        } catch (MalformedURLException unused2) {
            File file = new File(replace);
            if (!file.exists() || !file.canRead()) {
                log("uri : '" + replace + "' does not match a readable file", 4);
                url = null;
            } else {
                log("uri : '" + replace + "' matches a readable file", 4);
                try {
                    url = FILE_UTILS.getFileURL(file);
                } catch (MalformedURLException unused3) {
                    throw new BuildException("could not find an URL for :" + file.getAbsolutePath());
                }
            }
        }
        if (url == null || !url.getProtocol().equals("file") || (fromURI = FILE_UTILS.fromURI(url.toString())) == null) {
            return null;
        }
        log("fileName " + fromURI, 4);
        File file2 = new File(fromURI);
        if (!file2.exists() || !file2.canRead()) {
            return null;
        }
        try {
            InputSource inputSource = new InputSource(new FileInputStream(file2));
            try {
                String systemId = JAXPUtils.getSystemId(file2);
                inputSource.setSystemId(systemId);
                log("catalog entry matched a readable file: '" + systemId + "'", 4);
                return inputSource;
            } catch (IOException unused4) {
                return inputSource;
            }
        } catch (IOException unused5) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputSource classpathLookup(ResourceLocation resourceLocation) {
        Path path;
        Path path2 = this.classpath;
        if (path2 != null) {
            path = path2.concatSystemClasspath(Definer.OnError.POLICY_IGNORE);
        } else {
            path = new Path(getProject()).concatSystemClasspath("last");
        }
        AntClassLoader createClassLoader = getProject().createClassLoader(path);
        InputStream resourceAsStream = createClassLoader.getResourceAsStream(resourceLocation.getLocation());
        if (resourceAsStream == null) {
            return null;
        }
        InputSource inputSource = new InputSource(resourceAsStream);
        String externalForm = createClassLoader.getResource(resourceLocation.getLocation()).toExternalForm();
        inputSource.setSystemId(externalForm);
        log("catalog entry matched a resource in the classpath: '" + externalForm + "'", 4);
        return inputSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputSource urlLookup(ResourceLocation resourceLocation) {
        URL fileURL;
        URL url;
        InputStream inputStream;
        String location = resourceLocation.getLocation();
        if (resourceLocation.getBase() != null) {
            fileURL = resourceLocation.getBase();
        } else {
            try {
                fileURL = FILE_UTILS.getFileURL(getProject().getBaseDir());
            } catch (MalformedURLException unused) {
                throw new BuildException("Project basedir cannot be converted to a URL");
            }
        }
        try {
            url = new URL(fileURL, location);
        } catch (MalformedURLException unused2) {
            url = null;
        }
        if (url == null) {
            return null;
        }
        try {
            URLConnection openConnection = url.openConnection();
            if (openConnection != null) {
                openConnection.setUseCaches(false);
                inputStream = openConnection.getInputStream();
            } else {
                inputStream = null;
            }
            if (inputStream == null) {
                return null;
            }
            InputSource inputSource = new InputSource(inputStream);
            try {
                String externalForm = url.toExternalForm();
                inputSource.setSystemId(externalForm);
                log("catalog entry matched as a URL: '" + externalForm + "'", 4);
                return inputSource;
            } catch (IOException unused3) {
                return inputSource;
            }
        } catch (IOException unused4) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class InternalResolver implements CatalogResolver {
        public InternalResolver() {
            XMLCatalog.this.log("Apache resolver library not found, internal resolver will be used", 3);
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String str, String str2) {
            ResourceLocation findMatchingEntry = XMLCatalog.this.findMatchingEntry(str);
            if (findMatchingEntry == null) {
                return null;
            }
            XMLCatalog xMLCatalog = XMLCatalog.this;
            xMLCatalog.log("Matching catalog entry found for publicId: '" + findMatchingEntry.getPublicId() + "' location: '" + findMatchingEntry.getLocation() + "'", 4);
            InputSource filesystemLookup = XMLCatalog.this.filesystemLookup(findMatchingEntry);
            if (filesystemLookup == null) {
                filesystemLookup = XMLCatalog.this.classpathLookup(findMatchingEntry);
            }
            return filesystemLookup == null ? XMLCatalog.this.urlLookup(findMatchingEntry) : filesystemLookup;
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, javax.xml.transform.URIResolver
        public Source resolve(String str, String str2) throws TransformerException {
            ResourceLocation resourceLocation;
            ResourceLocation findMatchingEntry = XMLCatalog.this.findMatchingEntry(str);
            if (findMatchingEntry != null) {
                XMLCatalog xMLCatalog = XMLCatalog.this;
                xMLCatalog.log("Matching catalog entry found for uri: '" + findMatchingEntry.getPublicId() + "' location: '" + findMatchingEntry.getLocation() + "'", 4);
                if (str2 != null) {
                    try {
                        URL url = new URL(str2);
                        resourceLocation = new ResourceLocation();
                        try {
                            resourceLocation.setBase(url);
                        } catch (MalformedURLException unused) {
                        }
                    } catch (MalformedURLException unused2) {
                        resourceLocation = findMatchingEntry;
                    }
                } else {
                    resourceLocation = findMatchingEntry;
                }
                resourceLocation.setPublicId(findMatchingEntry.getPublicId());
                resourceLocation.setLocation(findMatchingEntry.getLocation());
                InputSource filesystemLookup = XMLCatalog.this.filesystemLookup(resourceLocation);
                if (filesystemLookup == null) {
                    filesystemLookup = XMLCatalog.this.classpathLookup(resourceLocation);
                }
                if (filesystemLookup == null) {
                    filesystemLookup = XMLCatalog.this.urlLookup(resourceLocation);
                }
                if (filesystemLookup != null) {
                    return new SAXSource(filesystemLookup);
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ExternalResolver implements CatalogResolver {
        private boolean externalCatalogsProcessed = false;
        private Method parseCatalog;
        private Method resolve;
        private Method resolveEntity;
        private Object resolverImpl;
        private Method setXMLCatalog;

        public ExternalResolver(Class<?> cls, Object obj) {
            this.setXMLCatalog = null;
            this.parseCatalog = null;
            this.resolveEntity = null;
            this.resolve = null;
            this.resolverImpl = null;
            this.resolverImpl = obj;
            try {
                this.setXMLCatalog = cls.getMethod("setXMLCatalog", XMLCatalog.class);
                this.parseCatalog = cls.getMethod("parseCatalog", String.class);
                this.resolveEntity = cls.getMethod("resolveEntity", String.class, String.class);
                this.resolve = cls.getMethod("resolve", String.class, String.class);
                XMLCatalog.this.log("Apache resolver library found, xml-commons resolver will be used", 3);
            } catch (NoSuchMethodException e) {
                throw new BuildException(e);
            }
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String str, String str2) {
            processExternalCatalogs();
            ResourceLocation findMatchingEntry = XMLCatalog.this.findMatchingEntry(str);
            if (findMatchingEntry != null) {
                XMLCatalog xMLCatalog = XMLCatalog.this;
                xMLCatalog.log("Matching catalog entry found for publicId: '" + findMatchingEntry.getPublicId() + "' location: '" + findMatchingEntry.getLocation() + "'", 4);
                InputSource filesystemLookup = XMLCatalog.this.filesystemLookup(findMatchingEntry);
                if (filesystemLookup == null) {
                    filesystemLookup = XMLCatalog.this.classpathLookup(findMatchingEntry);
                }
                if (filesystemLookup != null) {
                    return filesystemLookup;
                }
                try {
                    return (InputSource) this.resolveEntity.invoke(this.resolverImpl, str, str2);
                } catch (Exception e) {
                    throw new BuildException(e);
                }
            } else {
                try {
                    return (InputSource) this.resolveEntity.invoke(this.resolverImpl, str, str2);
                } catch (Exception e2) {
                    throw new BuildException(e2);
                }
            }
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, javax.xml.transform.URIResolver
        public Source resolve(String str, String str2) throws TransformerException {
            ResourceLocation resourceLocation;
            processExternalCatalogs();
            ResourceLocation findMatchingEntry = XMLCatalog.this.findMatchingEntry(str);
            if (findMatchingEntry != null) {
                XMLCatalog xMLCatalog = XMLCatalog.this;
                xMLCatalog.log("Matching catalog entry found for uri: '" + findMatchingEntry.getPublicId() + "' location: '" + findMatchingEntry.getLocation() + "'", 4);
                if (str2 != null) {
                    try {
                        URL url = new URL(str2);
                        resourceLocation = new ResourceLocation();
                        try {
                            resourceLocation.setBase(url);
                        } catch (MalformedURLException unused) {
                        }
                    } catch (MalformedURLException unused2) {
                        resourceLocation = findMatchingEntry;
                    }
                } else {
                    resourceLocation = findMatchingEntry;
                }
                resourceLocation.setPublicId(findMatchingEntry.getPublicId());
                resourceLocation.setLocation(findMatchingEntry.getLocation());
                InputSource filesystemLookup = XMLCatalog.this.filesystemLookup(resourceLocation);
                if (filesystemLookup == null) {
                    filesystemLookup = XMLCatalog.this.classpathLookup(resourceLocation);
                }
                if (filesystemLookup != null) {
                    return new SAXSource(filesystemLookup);
                }
                try {
                    return (SAXSource) this.resolve.invoke(this.resolverImpl, str, str2);
                } catch (Exception e) {
                    throw new BuildException(e);
                }
            } else {
                if (str2 == null) {
                    try {
                        str2 = XMLCatalog.FILE_UTILS.getFileURL(XMLCatalog.this.getProject().getBaseDir()).toString();
                    } catch (MalformedURLException e2) {
                        throw new TransformerException(e2);
                    }
                }
                try {
                    return (SAXSource) this.resolve.invoke(this.resolverImpl, str, str2);
                } catch (Exception e3) {
                    throw new BuildException(e3);
                }
            }
        }

        private void processExternalCatalogs() {
            if (!this.externalCatalogsProcessed) {
                try {
                    this.setXMLCatalog.invoke(this.resolverImpl, XMLCatalog.this);
                    if (XMLCatalog.this.getCatalogPath() != null) {
                        XMLCatalog.this.log("Using catalogpath '" + XMLCatalog.this.getCatalogPath() + "'", 4);
                        for (String str : XMLCatalog.this.getCatalogPath().list()) {
                            File file = new File(str);
                            XMLCatalog.this.log("Parsing " + file, 4);
                            try {
                                this.parseCatalog.invoke(this.resolverImpl, file.getPath());
                            } catch (Exception e) {
                                throw new BuildException(e);
                            }
                        }
                    }
                } catch (Exception e2) {
                    throw new BuildException(e2);
                }
            }
            this.externalCatalogsProcessed = true;
        }
    }
}
