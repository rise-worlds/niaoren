package org.apache.tools.ant.input;

/* loaded from: classes2.dex */
public class InputRequest {
    private String defaultValue;
    private String input;
    private final String prompt;

    public boolean isInputValid() {
        return true;
    }

    public InputRequest(String str) {
        if (str != null) {
            this.prompt = str;
            return;
        }
        throw new IllegalArgumentException("prompt must not be null");
    }

    public String getPrompt() {
        return this.prompt;
    }

    public void setInput(String str) {
        this.input = str;
    }

    public String getInput() {
        return this.input;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String str) {
        this.defaultValue = str;
    }
}
