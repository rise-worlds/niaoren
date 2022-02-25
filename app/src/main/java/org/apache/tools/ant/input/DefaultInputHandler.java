package org.apache.tools.ant.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.KeepAliveInputStream;

/* loaded from: classes2.dex */
public class DefaultInputHandler implements InputHandler {
    @Override // org.apache.tools.ant.input.InputHandler
    public void handleInput(InputRequest inputRequest) throws BuildException {
        Throwable th;
        String prompt = getPrompt(inputRequest);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getInputStream()));
            do {
                try {
                    System.err.println(prompt);
                    System.err.flush();
                    try {
                        inputRequest.setInput(bufferedReader.readLine());
                    } catch (IOException e) {
                        throw new BuildException("Failed to read input from Console.", e);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            throw new BuildException("Failed to close input.", e2);
                        }
                    }
                    throw th;
                }
            } while (!inputRequest.isInputValid());
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                throw new BuildException("Failed to close input.", e3);
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getPrompt(InputRequest inputRequest) {
        String prompt = inputRequest.getPrompt();
        String defaultValue = inputRequest.getDefaultValue();
        if (inputRequest instanceof MultipleChoiceInputRequest) {
            StringBuilder sb = new StringBuilder(prompt);
            sb.append(" (");
            boolean z = true;
            Iterator<String> it = ((MultipleChoiceInputRequest) inputRequest).getChoices().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!z) {
                    sb.append(", ");
                }
                if (next.equals(defaultValue)) {
                    sb.append('[');
                }
                sb.append(next);
                if (next.equals(defaultValue)) {
                    sb.append(']');
                }
                z = false;
            }
            sb.append(")");
            return sb.toString();
        } else if (defaultValue == null) {
            return prompt;
        } else {
            return prompt + " [" + defaultValue + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InputStream getInputStream() {
        return KeepAliveInputStream.wrapSystemIn();
    }
}
