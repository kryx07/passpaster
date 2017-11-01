package com.krystiankowalik.passpaster.controller;

import com.google.common.eventbus.Subscribe;
import com.krystiankowalik.passpaster.EventBusProvider;
import com.krystiankowalik.passpaster.HotKeyHandler;
import com.krystiankowalik.passpaster.event.ApplicationStop;
import com.krystiankowalik.passpaster.io.StreamFileHelper;
import com.krystiankowalik.passpaster.model.Shortcut;
import com.krystiankowalik.passpaster.parser.ShortcutParser;
import com.sun.org.apache.xml.internal.utils.StringBufferPool;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextArea text;
    private StreamFileHelper streamFileHelper;
    private HotKeyHandler hotKeyHandler;
    private ShortcutParser shortcutParser;


    private void init() {
        streamFileHelper = new StreamFileHelper();
        hotKeyHandler = new HotKeyHandler();
        shortcutParser = new ShortcutParser();
        text.setWrapText(true);
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();

        text.setText(getFormattedFileContents());

        hotKeyHandler.control(shortcutParser.parse(getConfigFileContents()));
    }

    private List<String> getConfigFileContents() {
        return streamFileHelper.readAllLines(Paths.get(getClass().getResource("/shortcuts.cfg").getFile()));

    }

    private String getFormattedFileContents() {
        List<String> lines = getConfigFileContents();
        StringBuilder stringBuilder = new StringBuilder();
        lines.forEach(line -> {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        });

        return stringBuilder.toString();
    }

    @Subscribe
    public void handleApplicationStop(ApplicationStop applicationStop) {
        hotKeyHandler.stop();

    }
}
