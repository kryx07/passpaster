package com.github.kryx07.passpaster.controller;

import com.github.kryx07.passpaster.event.ApplicationStop;
import com.github.kryx07.passpaster.hotkey.HotKeyHandler;
import com.github.kryx07.passpaster.io.StreamFileHelper;
import com.github.kryx07.passpaster.model.Shortcut;
import com.github.kryx07.passpaster.util.EventBusProvider;
import com.github.kryx07.passpaster.util.LICENSE;
import com.google.common.eventbus.Subscribe;
import com.github.kryx07.passpaster.parser.ShortcutParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button addNewShortcut;
    @FXML
    private Button saveConfigButton;
    @FXML
    private Button loadConfigButton;
    @FXML
    private Button aboutButton;
    @FXML
    private TableView<Shortcut> shortcutTableView;

    private TableColumn<Shortcut, String> keyCombinationColumn;
    private TableColumn<Shortcut, String> customTextColumn;

    private StreamFileHelper streamFileHelper;
    private ShortcutParser shortcutParser;
    private HotKeyHandler hotKeyHandler;

    private ObservableList<Shortcut> shortcutList;


    private void init() {

        hotKeyHandler = new HotKeyHandler();
        addNewShortcut.setOnMouseClicked(event -> {
            shortcutList.add(new Shortcut());
            shortcutTableView.getSelectionModel().clearAndSelect(shortcutTableView.getItems().size() - 1);
        });
        loadConfigButton.setOnMouseClicked(event -> loadConfig());
        saveConfigButton.setOnMouseClicked(event -> saveConfig());
        aboutButton.setOnMouseClicked(event -> displayAboutPopup());

        streamFileHelper = new StreamFileHelper();
        shortcutParser = new ShortcutParser();

        EventBusProvider.getInstance().register(this);

        initTable();
        handleDeleteKey();
    }

    private void displayAboutPopup() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("About");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setText(LICENSE.LICENSE_TEXT);

        Scene scene1 = new Scene(textArea, 500, 450);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

    private void initTable() {
        keyCombinationColumn = new TableColumn<>("Key Combination");
        customTextColumn = new TableColumn<>("Password");

        keyCombinationColumn.setCellValueFactory(param -> param.getValue().keyCombinationProperty());
        keyCombinationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        customTextColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        customTextColumn.setCellValueFactory(param -> param.getValue().customTextProperty());

        shortcutTableView.setEditable(true);
        keyCombinationColumn.setEditable(true);
        customTextColumn.setEditable(true);

        shortcutList = FXCollections.observableArrayList();
        shortcutList.add(new Shortcut("Key combo", "Custom text"));

        shortcutTableView.setItems(shortcutList);
        shortcutTableView.getColumns().setAll(keyCombinationColumn, customTextColumn);
    }
/*
    public void printTable() {
        shortcutTableView.getItems().forEach(System.out::println);
    }*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        loadConfig();
        runHotKeyHandler();
    }

    private void runHotKeyHandler() {
        hotKeyHandler.reset();
        hotKeyHandler.control(shortcutParser.parse(getConfigFileContents()));
    }

    private void saveConfig() {
        streamFileHelper.writeAllLines(shortcutParser.unParse(shortcutList), getConfigLocation());
        runHotKeyHandler();
    }

    private void loadConfig() {
        shortcutList.setAll(shortcutParser.parse(getConfigFileContents()));
    }

    private List<String> getConfigFileContents() {

        return streamFileHelper.readAllLines(getConfigLocation());

    }

    private Path getConfigLocation() {
        String configLocation = null;
        try {
            configLocation = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Paths.get(configLocation + File.separator + "config" + File.separator + "shortcuts.cfg");
    }

    private void handleDeleteKey() {
        shortcutTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        shortcutTableView.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DELETE) {
                shortcutTableView.getItems().removeAll(shortcutTableView.getSelectionModel().getSelectedItems());

                int currentlySelectedIndex = shortcutTableView.getSelectionModel().getSelectedIndex();
                if (currentlySelectedIndex > 0
                        && currentlySelectedIndex + 1 < shortcutTableView.getItems().size()) {
                    shortcutTableView.getSelectionModel().clearSelection();
                    shortcutTableView.getSelectionModel().select(currentlySelectedIndex + 1);
                }
            }
        });


    }

    @Subscribe
    public void handleApplicationStop(ApplicationStop applicationStop) {
        hotKeyHandler.stop();

    }
}
