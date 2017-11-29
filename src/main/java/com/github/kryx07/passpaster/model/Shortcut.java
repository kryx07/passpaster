package com.github.kryx07.passpaster.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Shortcut {

    private StringProperty keyCombination;
    private StringProperty customText;

    public Shortcut(StringProperty keyCombination, StringProperty customText) {
        this.keyCombination = keyCombination;
        this.customText = customText;
    }

    public Shortcut(String keyCombination, String customText) {
        this.keyCombination = new SimpleStringProperty(keyCombination);
        this.customText = new SimpleStringProperty(customText);
    }

    public Shortcut() {
        this.keyCombination = new SimpleStringProperty();
        this.customText = new SimpleStringProperty();
    }

    public String getKeyCombination() {
        return keyCombination.get();
    }

    public StringProperty keyCombinationProperty() {
        return keyCombination;
    }

    public void setKeyCombination(String keyCombination) {
        this.keyCombination.set(keyCombination);
    }

    public String getCustomText() {
        return customText.get();
    }

    public StringProperty customTextProperty() {
        return customText;
    }

    public void setCustomText(String customText) {
        this.customText.set(customText);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Shortcut{");
        sb.append("keyCombination=").append(keyCombination);
        sb.append(", customText=").append(customText);
        sb.append('}');
        return sb.toString();
    }
}
