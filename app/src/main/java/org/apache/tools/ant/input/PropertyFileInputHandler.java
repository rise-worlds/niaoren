package org.apache.tools.ant.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public class PropertyFileInputHandler implements InputHandler {
    public static final String FILE_NAME_KEY = "ant.input.properties";
    private Properties props = null;

    @Override // org.apache.tools.ant.input.InputHandler
    public void handleInput(InputRequest inputRequest) throws BuildException {
        readProps();
        Object obj = this.props.get(inputRequest.getPrompt());
        if (obj != null) {
            inputRequest.setInput(obj.toString());
            if (!inputRequest.isInputValid()) {
                throw new BuildException("Found invalid input " + obj + " for '" + inputRequest.getPrompt() + "'");
            }
            return;
        }
        throw new BuildException("Unable to find input for '" + inputRequest.getPrompt() + "'");
    }

    private synchronized void readProps() throws BuildException {
        if (this.props == null) {
            String property = System.getProperty(FILE_NAME_KEY);
            if (property != null) {
                this.props = new Properties();
                try {
                    this.props.load(new FileInputStream(property));
                } catch (IOException e) {
                    throw new BuildException("Couldn't load " + property, e);
                }
            } else {
                throw new BuildException("System property ant.input.properties for PropertyFileInputHandler not set");
            }
        }
    }
}
