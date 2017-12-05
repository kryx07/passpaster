package com.github.kryx07.passpaster.controller;

import com.github.kryx07.passpaster.model.Shortcut;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class PasswordLabelCell extends TextFieldTableCell<Shortcut, String> {

    public PasswordLabelCell() {
        super(new DefaultStringConverter());
        this.setEditable(true);
    }

    private String genDotString(int len) {
        String dots = "";

        for (int i = 0; i < len; i++) {
            dots += "\u2022";
        }

        return dots;
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            if (!empty) {
                setText(genDotString(item.length()));
            }
        }

    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        if (getText() != null) {
            if (!getText().isEmpty()) {
                setText(genDotString(getText().length()));
            }
        }
    }

    @Override
    public void commitEdit(String newValue) {
        super.commitEdit(newValue);
        setText(genDotString(newValue.length()));
    }
}
