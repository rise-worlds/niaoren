package org.apache.tools.ant.util;

import org.apache.tools.ant.DynamicConfiguratorNS;
import org.apache.tools.ant.DynamicElementNS;
import org.apache.tools.ant.ProjectComponent;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: classes2.dex */
public class XMLFragment extends ProjectComponent implements DynamicElementNS {
    private Document doc = JAXPUtils.getDocumentBuilder().newDocument();
    private DocumentFragment fragment = this.doc.createDocumentFragment();

    public DocumentFragment getFragment() {
        return this.fragment;
    }

    public void addText(String str) {
        addText(this.fragment, str);
    }

    @Override // org.apache.tools.ant.DynamicElementNS
    public Object createDynamicElement(String str, String str2, String str3) {
        Element element;
        if (str.equals("")) {
            element = this.doc.createElement(str2);
        } else {
            element = this.doc.createElementNS(str, str3);
        }
        this.fragment.appendChild(element);
        return new Child(element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addText(Node node, String str) {
        String replaceProperties = getProject().replaceProperties(str);
        if (replaceProperties != null && !replaceProperties.trim().equals("")) {
            node.appendChild(this.doc.createTextNode(replaceProperties.trim()));
        }
    }

    /* loaded from: classes2.dex */
    public class Child implements DynamicConfiguratorNS {

        /* renamed from: e */
        private Element f14782e;

        Child(Element element) {
            this.f14782e = element;
        }

        public void addText(String str) {
            XMLFragment.this.addText(this.f14782e, str);
        }

        @Override // org.apache.tools.ant.DynamicAttributeNS
        public void setDynamicAttribute(String str, String str2, String str3, String str4) {
            if (str.equals("")) {
                this.f14782e.setAttribute(str2, str4);
            } else {
                this.f14782e.setAttributeNS(str, str3, str4);
            }
        }

        @Override // org.apache.tools.ant.DynamicElementNS
        public Object createDynamicElement(String str, String str2, String str3) {
            Element createElement = str.equals("") ? XMLFragment.this.doc.createElement(str2) : XMLFragment.this.doc.createElementNS(str, str3);
            this.f14782e.appendChild(createElement);
            return new Child(createElement);
        }
    }
}
