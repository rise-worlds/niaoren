package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class XmlProperty extends Task {

    /* renamed from: ID */
    private static final String f14761ID = "id";
    private static final String LOCATION = "location";
    private static final String PATH = "path";
    private static final String VALUE = "value";
    private Resource src;
    private static final String REF_ID = "refid";
    private static final String PATHID = "pathid";
    private static final String[] ATTRIBUTES = {"id", REF_ID, "location", "value", "path", PATHID};
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private String prefix = "";
    private boolean keepRoot = true;
    private boolean validate = false;
    private boolean collapseAttributes = false;
    private boolean semanticAttributes = false;
    private boolean includeSemanticAttribute = false;
    private File rootDirectory = null;
    private Hashtable addedAttributes = new Hashtable();
    private XMLCatalog xmlCatalog = new XMLCatalog();
    private String delimiter = ",";

    @Override // org.apache.tools.ant.Task
    public void init() {
        super.init();
        this.xmlCatalog.setProject(getProject());
    }

    protected EntityResolver getEntityResolver() {
        return this.xmlCatalog;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Document document;
        Resource resource = getResource();
        if (resource != null) {
            try {
                log("Loading " + this.src, 3);
                if (resource.isExists()) {
                    DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                    newInstance.setValidating(this.validate);
                    newInstance.setNamespaceAware(false);
                    DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                    newDocumentBuilder.setEntityResolver(getEntityResolver());
                    FileProvider fileProvider = (FileProvider) this.src.mo14823as(FileProvider.class);
                    if (fileProvider != null) {
                        document = newDocumentBuilder.parse(fileProvider.getFile());
                    } else {
                        document = newDocumentBuilder.parse(this.src.getInputStream());
                    }
                    Element documentElement = document.getDocumentElement();
                    this.addedAttributes = new Hashtable();
                    if (this.keepRoot) {
                        addNodeRecursively(documentElement, this.prefix, null);
                        return;
                    }
                    NodeList childNodes = documentElement.getChildNodes();
                    int length = childNodes.getLength();
                    for (int i = 0; i < length; i++) {
                        addNodeRecursively(childNodes.item(i), this.prefix, null);
                    }
                    return;
                }
                log("Unable to find property resource: " + resource, 3);
            } catch (IOException e) {
                throw new BuildException("Failed to load " + this.src, e);
            } catch (ParserConfigurationException e2) {
                throw new BuildException(e2);
            } catch (SAXException e3) {
                Exception exception = e3.getException();
                Exception exc = e3;
                if (exception != null) {
                    exc = e3.getException();
                }
                throw new BuildException("Failed to load " + this.src, exc);
            }
        } else {
            throw new BuildException("XmlProperty task requires a source resource");
        }
    }

    private void addNodeRecursively(Node node, String str, Object obj) {
        if (node.getNodeType() != 3) {
            if (str.trim().length() > 0) {
                str = str + Consts.f23430h;
            }
            str = str + node.getNodeName();
        }
        Object processNode = processNode(node, str, obj);
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                addNodeRecursively(childNodes.item(i), str, processNode);
            }
        }
    }

    void addNodeRecursively(Node node, String str) {
        addNodeRecursively(node, str, null);
    }

    public Object processNode(Node node, String str, Object obj) {
        Path path;
        String str2;
        boolean z = false;
        String str3 = null;
        if (node.hasAttributes()) {
            NamedNodeMap attributes = node.getAttributes();
            Node namedItem = attributes.getNamedItem("id");
            str2 = (!this.semanticAttributes || namedItem == null) ? null : namedItem.getNodeValue();
            path = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                Node item = attributes.item(i);
                if (!this.semanticAttributes) {
                    String attributeName = getAttributeName(item);
                    addProperty(str + attributeName, getAttributeValue(item), null);
                } else {
                    String nodeName = item.getNodeName();
                    String attributeValue = getAttributeValue(item);
                    Path path2 = (obj == null || !(obj instanceof Path)) ? null : (Path) obj;
                    if (nodeName.equals("id")) {
                        continue;
                    } else if (path2 == null || !nodeName.equals("path")) {
                        boolean z2 = obj instanceof Path;
                        if (z2 && nodeName.equals(REF_ID)) {
                            path2.setPath(attributeValue);
                        } else if (z2 && nodeName.equals("location")) {
                            path2.setLocation(resolveFile(attributeValue));
                        } else if (!nodeName.equals(PATHID)) {
                            addProperty(str + getAttributeName(item), attributeValue, str2);
                        } else if (obj == null) {
                            path = new Path(getProject());
                            getProject().addReference(attributeValue, path);
                        } else {
                            throw new BuildException("XmlProperty does not support nested paths");
                        }
                    } else {
                        path2.setPath(attributeValue);
                    }
                }
            }
        } else {
            str2 = null;
            path = null;
        }
        boolean z3 = node.getNodeType() == 1 && this.semanticAttributes && node.hasAttributes() && !(node.getAttributes().getNamedItem("value") == null && node.getAttributes().getNamedItem("location") == null && node.getAttributes().getNamedItem(REF_ID) == null && node.getAttributes().getNamedItem("path") == null && node.getAttributes().getNamedItem(PATHID) == null);
        if (node.getNodeType() == 3) {
            str3 = getAttributeValue(node);
        } else if (node.getNodeType() == 1 && node.getChildNodes().getLength() == 1 && node.getFirstChild().getNodeType() == 4) {
            str3 = node.getFirstChild().getNodeValue();
            if ("".equals(str3) && !z3) {
                z = true;
            }
        } else if (node.getNodeType() == 1 && node.getChildNodes().getLength() == 0 && !z3) {
            str3 = "";
            z = true;
        } else if (node.getNodeType() == 1 && node.getChildNodes().getLength() == 1 && node.getFirstChild().getNodeType() == 3 && "".equals(node.getFirstChild().getNodeValue()) && !z3) {
            str3 = "";
            z = true;
        }
        if (str3 != null) {
            if (this.semanticAttributes && str2 == null && (obj instanceof String)) {
                str2 = (String) obj;
            }
            if (str3.trim().length() != 0 || z) {
                addProperty(str, str3, str2);
            }
        }
        return path != null ? path : str2;
    }

    private void addProperty(String str, String str2, String str3) {
        String str4 = str + ":" + str2;
        if (str3 != null) {
            str4 = str4 + "(id=" + str3 + ")";
        }
        log(str4, 4);
        if (this.addedAttributes.containsKey(str)) {
            str2 = ((String) this.addedAttributes.get(str)) + getDelimiter() + str2;
            getProject().setProperty(str, str2);
            this.addedAttributes.put(str, str2);
        } else if (getProject().getProperty(str) == null) {
            getProject().setNewProperty(str, str2);
            this.addedAttributes.put(str, str2);
        } else {
            log("Override ignored for property " + str, 3);
        }
        if (str3 != null) {
            getProject().addReference(str3, str2);
        }
    }

    private String getAttributeName(Node node) {
        StringBuilder sb;
        String nodeName = node.getNodeName();
        if (!this.semanticAttributes) {
            if (this.collapseAttributes) {
                sb = new StringBuilder();
                sb.append(Consts.f23430h);
            } else {
                sb = new StringBuilder();
                sb.append("(");
                sb.append(nodeName);
                nodeName = ")";
            }
            sb.append(nodeName);
            return sb.toString();
        } else if (nodeName.equals(REF_ID)) {
            return "";
        } else {
            if (isSemanticAttribute(nodeName) && !this.includeSemanticAttribute) {
                return "";
            }
            return Consts.f23430h + nodeName;
        }
    }

    private static boolean isSemanticAttribute(String str) {
        int i = 0;
        while (true) {
            String[] strArr = ATTRIBUTES;
            if (i >= strArr.length) {
                return false;
            }
            if (str.equals(strArr[i])) {
                return true;
            }
            i++;
        }
    }

    private String getAttributeValue(Node node) {
        Object reference;
        String trim = node.getNodeValue().trim();
        if (this.semanticAttributes) {
            String nodeName = node.getNodeName();
            trim = getProject().replaceProperties(trim);
            if (nodeName.equals("location")) {
                return resolveFile(trim).getPath();
            }
            if (nodeName.equals(REF_ID) && (reference = getProject().getReference(trim)) != null) {
                return reference.toString();
            }
        }
        return trim;
    }

    public void setFile(File file) {
        setSrcResource(new FileResource(file));
    }

    public void setSrcResource(Resource resource) {
        if (resource.isDirectory()) {
            throw new BuildException("the source can't be a directory");
        } else if (resource.mo14823as(FileProvider.class) != null || supportsNonFileResources()) {
            this.src = resource;
        } else {
            throw new BuildException("Only FileSystem resources are supported.");
        }
    }

    public void addConfigured(ResourceCollection resourceCollection) {
        if (resourceCollection.size() == 1) {
            setSrcResource(resourceCollection.iterator().next());
            return;
        }
        throw new BuildException("only single argument resource collections are supported as archives");
    }

    public void setPrefix(String str) {
        this.prefix = str.trim();
    }

    public void setKeeproot(boolean z) {
        this.keepRoot = z;
    }

    public void setValidate(boolean z) {
        this.validate = z;
    }

    public void setCollapseAttributes(boolean z) {
        this.collapseAttributes = z;
    }

    public void setSemanticAttributes(boolean z) {
        this.semanticAttributes = z;
    }

    public void setRootDirectory(File file) {
        this.rootDirectory = file;
    }

    public void setIncludeSemanticAttribute(boolean z) {
        this.includeSemanticAttribute = z;
    }

    public void addConfiguredXMLCatalog(XMLCatalog xMLCatalog) {
        this.xmlCatalog.addConfiguredXMLCatalog(xMLCatalog);
    }

    protected File getFile() {
        FileProvider fileProvider = (FileProvider) this.src.mo14823as(FileProvider.class);
        if (fileProvider != null) {
            return fileProvider.getFile();
        }
        return null;
    }

    protected Resource getResource() {
        File file = getFile();
        FileProvider fileProvider = (FileProvider) this.src.mo14823as(FileProvider.class);
        return (file != null && (fileProvider == null || !fileProvider.getFile().equals(file))) ? new FileResource(file) : this.src;
    }

    protected String getPrefix() {
        return this.prefix;
    }

    protected boolean getKeeproot() {
        return this.keepRoot;
    }

    protected boolean getValidate() {
        return this.validate;
    }

    protected boolean getCollapseAttributes() {
        return this.collapseAttributes;
    }

    protected boolean getSemanticAttributes() {
        return this.semanticAttributes;
    }

    protected File getRootDirectory() {
        return this.rootDirectory;
    }

    protected boolean getIncludeSementicAttribute() {
        return this.includeSemanticAttribute;
    }

    private File resolveFile(String str) {
        FileUtils fileUtils = FILE_UTILS;
        File file = this.rootDirectory;
        if (file == null) {
            file = getProject().getBaseDir();
        }
        return fileUtils.resolveFile(file, str);
    }

    protected boolean supportsNonFileResources() {
        return getClass().equals(XmlProperty.class);
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }
}
