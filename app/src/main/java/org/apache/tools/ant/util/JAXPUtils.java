package org.apache.tools.ant.util;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.tools.ant.BuildException;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* loaded from: classes2.dex */
public class JAXPUtils {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static SAXParserFactory parserFactory = null;
    private static SAXParserFactory nsParserFactory = null;
    private static DocumentBuilderFactory builderFactory = null;

    public static synchronized SAXParserFactory getParserFactory() throws BuildException {
        SAXParserFactory sAXParserFactory;
        synchronized (JAXPUtils.class) {
            if (parserFactory == null) {
                parserFactory = newParserFactory();
            }
            sAXParserFactory = parserFactory;
        }
        return sAXParserFactory;
    }

    public static synchronized SAXParserFactory getNSParserFactory() throws BuildException {
        SAXParserFactory sAXParserFactory;
        synchronized (JAXPUtils.class) {
            if (nsParserFactory == null) {
                nsParserFactory = newParserFactory();
                nsParserFactory.setNamespaceAware(true);
            }
            sAXParserFactory = nsParserFactory;
        }
        return sAXParserFactory;
    }

    public static SAXParserFactory newParserFactory() throws BuildException {
        try {
            return SAXParserFactory.newInstance();
        } catch (FactoryConfigurationError e) {
            throw new BuildException("XML parser factory has not been configured correctly: " + e.getMessage(), e);
        }
    }

    public static Parser getParser() throws BuildException {
        try {
            return newSAXParser(getParserFactory()).getParser();
        } catch (SAXException e) {
            throw convertToBuildException(e);
        }
    }

    public static XMLReader getXMLReader() throws BuildException {
        try {
            return newSAXParser(getParserFactory()).getXMLReader();
        } catch (SAXException e) {
            throw convertToBuildException(e);
        }
    }

    public static XMLReader getNamespaceXMLReader() throws BuildException {
        try {
            return newSAXParser(getNSParserFactory()).getXMLReader();
        } catch (SAXException e) {
            throw convertToBuildException(e);
        }
    }

    public static String getSystemId(File file) {
        return FILE_UTILS.toURI(file.getAbsolutePath());
    }

    public static DocumentBuilder getDocumentBuilder() throws BuildException {
        try {
            return getDocumentBuilderFactory().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new BuildException(e);
        }
    }

    private static SAXParser newSAXParser(SAXParserFactory sAXParserFactory) throws BuildException {
        try {
            return sAXParserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new BuildException("Cannot create parser for the given configuration: " + e.getMessage(), e);
        } catch (SAXException e2) {
            throw convertToBuildException(e2);
        }
    }

    private static BuildException convertToBuildException(SAXException sAXException) {
        Exception exception = sAXException.getException();
        if (exception != null) {
            return new BuildException(exception);
        }
        return new BuildException(sAXException);
    }

    private static synchronized DocumentBuilderFactory getDocumentBuilderFactory() throws BuildException {
        DocumentBuilderFactory documentBuilderFactory;
        synchronized (JAXPUtils.class) {
            if (builderFactory == null) {
                try {
                    builderFactory = DocumentBuilderFactory.newInstance();
                } catch (FactoryConfigurationError e) {
                    throw new BuildException("Document builder factory has not been configured correctly: " + e.getMessage(), e);
                }
            }
            documentBuilderFactory = builderFactory;
        }
        return documentBuilderFactory;
    }
}
