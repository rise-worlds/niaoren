package org.apache.tools.ant.input;

import java.util.LinkedHashSet;
import java.util.Vector;

/* loaded from: classes2.dex */
public class MultipleChoiceInputRequest extends InputRequest {
    private final LinkedHashSet<String> choices;

    public MultipleChoiceInputRequest(String str, Vector<String> vector) {
        super(str);
        if (vector != null) {
            this.choices = new LinkedHashSet<>(vector);
            return;
        }
        throw new IllegalArgumentException("choices must not be null");
    }

    public Vector<String> getChoices() {
        return new Vector<>(this.choices);
    }

    @Override // org.apache.tools.ant.input.InputRequest
    public boolean isInputValid() {
        return this.choices.contains(getInput()) || ("".equals(getInput()) && getDefaultValue() != null);
    }
}
