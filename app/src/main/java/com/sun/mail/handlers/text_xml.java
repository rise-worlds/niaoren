package com.sun.mail.handlers;

import javax.activation.ActivationDataFlavor;

/* loaded from: classes2.dex */
public class text_xml extends text_plain {
    private static ActivationDataFlavor myDF = new ActivationDataFlavor(String.class, "text/xml", "XML String");

    @Override // com.sun.mail.handlers.text_plain
    protected ActivationDataFlavor getDF() {
        return myDF;
    }
}
