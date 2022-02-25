package org.apache.tools.ant.input;

import java.util.Arrays;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.ReflectUtil;

/* loaded from: classes2.dex */
public class SecureInputHandler extends DefaultInputHandler {
    @Override // org.apache.tools.ant.input.DefaultInputHandler, org.apache.tools.ant.input.InputHandler
    public void handleInput(InputRequest inputRequest) throws BuildException {
        String prompt = getPrompt(inputRequest);
        try {
            Object invokeStatic = ReflectUtil.invokeStatic(System.class, "console");
            do {
                char[] cArr = (char[]) ReflectUtil.invoke(invokeStatic, "readPassword", String.class, prompt, Object[].class, null);
                inputRequest.setInput(new String(cArr));
                Arrays.fill(cArr, ' ');
            } while (!inputRequest.isInputValid());
        } catch (Exception unused) {
            super.handleInput(inputRequest);
        }
    }
}
