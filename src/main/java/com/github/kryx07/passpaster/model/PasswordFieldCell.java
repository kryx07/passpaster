package com.github.kryx07.passpaster.model;

import com.github.kryx07.passpaster.model.Shortcut;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

public class PasswordFieldCell extends TextFieldTableCell{

    private final TextField lbl;


    public PasswordFieldCell() {
        lbl = new TextField();
        lbl.setEditable(true);
        //this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        //this.setGraphic(null);

    }

    private String generatePasswordString(int len) {
        String dots = "";

        for (int i = 0; i < len; i++) {
            dots += "*";
        }

        return dots;
    }

   /* @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty) {
            int row = getIndex();
            Shortcut shortcut = getTableView().getItems().get(row);
            if (shortcut.getCustomText() != null) {
                lbl.setText(generatePasswordString(item.length()));
                //      setGraphic(lbl);
            }

            //if (user.isManager()) { // mask password

            //} else { // unmask password
            //  lbl.setText(item);
            //}

        } else {
            lbl.setText(null);
            //setGraphic(null);
        }
    }*/
}